package akkaTestJS;

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
import akkaTestJS.actors.TRootActor;

import java.util.concurrent.CompletionStage;

public class TAkkaTestJSApp {

    public static final String ROOT_ACTOR = "rootActor";
    public static final String STORAGE_ACTOR = "storageActor";

    private static final String ACTOR_SYSTEM = "test-js";
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final String START_MESSAGE = "Server starts, http://" + HOST + ":" + PORT + "/\n";

    public static void main(String[] args) throws Exception {
        ActorSystem sys = ActorSystem.create(ACTOR_SYSTEM);
        ActorRef rootActor = sys.actorOf(Props.create(TRootActor.class), ROOT_ACTOR);

        final Http http = Http.get(sys);
        final ActorMaterializer materializer = ActorMaterializer.create(sys);
        THttpRouter router = new THttpRouter();

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                router.createRoute(rootActor).flow(sys, materializer);

        final CompletionStage<ServerBinding> bind = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(HOST, PORT),
                materializer
        );

        System.out.println(START_MESSAGE);
        System.in.read();
        bind.thenCompose(ServerBinding::unbind).thenAccept(unbound -> sys.terminate());

    }
}
