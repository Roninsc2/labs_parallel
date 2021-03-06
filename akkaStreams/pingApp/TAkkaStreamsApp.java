package akkaStreams.pingApp;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public class TAkkaStreamsApp {

    private static final String ACTOR_SYSTEM = "ping";
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final String START_MESSAGE = "Server started, http://" + HOST + ":" + PORT + "/\n";

    public static void main(String[] args) throws IOException {
        ActorSystem sys = ActorSystem.create(ACTOR_SYSTEM);

        final Http http = Http.get(sys);
        final ActorMaterializer materializer = ActorMaterializer.create(sys);

        final TServer server = new TServer(sys);
        final Flow<HttpRequest, HttpResponse, NotUsed> flow = server.getFlow(materializer);

        final CompletionStage<ServerBinding> bind = http.bindAndHandle(
                flow,
                ConnectHttp.toHost(HOST, PORT),
                materializer
        );

        System.out.println(START_MESSAGE);
        System.in.read();
        bind.thenCompose(ServerBinding::unbind).thenAccept(unbound -> sys.terminate());

    }
}

