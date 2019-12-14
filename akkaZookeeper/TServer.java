package akkaZookeeper;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.*;
import akka.http.javadsl.server.AllDirectives;
import akka.pattern.Patterns;
import akkaZookeeper.packet.TServerPkt;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.CompletionStage;

public class TServer extends AllDirectives {

    private static final String URL_NAME = "url";
    private static final String COUNT_NAME = "count";
    private static final long TIMEOUT = 3000;

    private Http http;
    private ActorRef actor;

    TServer(final Http http, int port, ActorRef actor) {
        this.actor = actor;
        this.http = http;
        TServiceZK serviceZK = 

    }

    Flow<HttpRequest, HttpResponse, NotUsed> getFlow(ActorMaterializer materializer) {
        return Flow
                .of(HttpRequest.class)
                .map(val -> {
                    Query requestQuery = val.getUri().query();
                    String url = requestQuery.getOrElse(URL_NAME, "");
                    int count = Integer.parseInt(requestQuery.getOrElse(COUNT_NAME, "-1"));

                    return new TPingPkt(url, count);
                })
                .mapAsync(PARALLELISM, ping -> Patterns.ask(actor, ping, Duration.ofMillis(TIMEOUT))
                        .thenCompose(pong -> {
                            TPongPkt cachePongPkt = (TPongPkt)pong;

                            return cachePongPkt.getAvrgPongTime() == -1
                                    ? pingExecute(ping, materializer)
                                    : CompletableFuture.completedFuture(cachePongPkt);
                        }))
                .map(pong -> {
                    actor.tell(pong , ActorRef.noSender());

                    return HttpResponse
                            .create()
                            .withStatus(StatusCodes.OK)
                            .withEntity(
                                    HttpEntities.create(
                                            pong.getUrl() + " " + pong.getAvrgPongTime()
                                    )
                            );
                });
    }

    private CompletionStage<TPongPkt> pingExecute(TPingPkt ping, ActorMaterializer materializer) {
        return Source.from(Collections.singletonList(ping))
                .toMat(pingSink(), Keep.right())
                .run(materializer)
                .thenApply(summaryTime -> new TPongPkt(
                        ping.getUrl(),
                        summaryTime / ping.getCount())
                );
    }

    private Sink<TPingPkt, CompletionStage<Long>> pingSink() {
        return Flow.<TPingPkt>create()
                .mapConcat(ping -> Collections.nCopies(ping.getCount(), ping.getUrl()))
                .mapAsync(PARALLELISM, url -> {
                    long startTime = System.currentTimeMillis();

                    return client
                            .prepareGet(url)
                            .execute()
                            .toCompletableFuture()
                            .thenApply(pong -> System.currentTimeMillis() - startTime);
                })
                .toMat(Sink.fold(0L, Long::sum), Keep.right());
    }
}
