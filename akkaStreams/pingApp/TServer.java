package akkaStreams.pingApp;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.model.*;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Source;
import akka.stream.javadsl.Sink;
import akkaStreams.actors.TCacheActor;
import akkaStreams.packet.TPongPkt;
import akkaStreams.packet.TPingPkt;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class TServer {

    private static final String URL_NAME = "url";
    private static final String COUNT_NAME = "count";
    private static final int PARALLELISM = 2;
    private static final long TIMEOUT = 3000;

    private AsyncHttpClient client =  Dsl.asyncHttpClient();
    private ActorRef actor;

    TServer(ActorSystem system) {
        actor = system.actorOf(Props.create(TCacheActor.class));
    }

    FLow<HttpRequest, HttpResponse, NotUsed> getFlow(ActorMaterializer materializer) {
        return Flow.of(HttpRequest.class)
                .map(val -> {
                    Query requestQuery = val.getUri().query();
                    String url = requestQuery.getOrElse(URL_NAME, "");
                    int count = Integer.parseInt(requestQuery.getOrElse(COUNT_NAME, "-1"));

                    return new TPongPkt(url, count);
                })
                .mapAsync(PARALLELISM, ping -> Patterns.ask(actor, ping, TIMEOUT)
                        .thenCompose(val -> {
                            TPongPkt cachePongPkt = val;

                        })
    }
}
