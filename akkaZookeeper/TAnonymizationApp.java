package akkaZookeeper;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akkaZookeeper.actor.TStorageConfigActor;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public class TAnonymizationApp {

    private static final String ACTOR_SYSTEM = "anonymization";
    private static final String HOST = "localhost";
    private static final String START_MESSAGE = "Server started, http://" + HOST + ":";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        int port = Integer.parseInt(args[0]);
        ActorSystem sys = ActorSystem.create(ACTOR_SYSTEM);
        ActorRef actor = sys.actorOf(Props.create(TStorageConfigActor.class));
        final Http http = Http.get(sys);
        final ActorMaterializer materializer = ActorMaterializer.create(sys);

        final TServer server = new TServer(http, port, actor);
        final Flow<HttpRequest, HttpResponse, NotUsed> flow =
                server.createRoute().flow(sys, materializer);

        final CompletionStage<ServerBinding> bind = http.bindAndHandle(
                flow,
                ConnectHttp.toHost(HOST, port),
                materializer
        );

        System.out.println(START_MESSAGE + port + "/\n");
        System.in.read();
        bind.thenCompose(ServerBinding::unbind).thenAccept(unbound -> sys.terminate());

    }
}
