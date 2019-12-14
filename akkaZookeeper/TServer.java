package akkaZookeeper;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.*;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akkaZookeeper.packet.TServerPkt;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.CompletionStage;

public class TServer extends AllDirectives {

    private static final String URL_NAME = "url";
    private static final String COUNT_NAME = "count";
    private static final long TIMEOUT = 3000;
    private static final String SERVER_URL = "http://localhost:";

    private Http http;
    private ActorRef actor;

    TServer(final Http http, int port, ActorRef actor) throws KeeperException, InterruptedException, IOException {
        this.actor = actor;
        this.http = http;
        TServiceZK serviceZK = new TServiceZK(actor);
        serviceZK.createServer(SERVER_URL + port);
    }

    public Route createRoute() {
        return get(() ->
            parameter(URL_NAME, url ->
                    parameter(COUNT_NAME, count -> {
                            int c = Integer.parseInt(count);

                            return Integer.parseInt(count) == 0 ?
                                completeWithFuture(fetch(url)) :
                                completeWithFuture(fetch(url, count))
                        }
                    )
            )
        );
    }
}
