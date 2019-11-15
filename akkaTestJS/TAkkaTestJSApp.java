package akkaTestJS;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akkaTestJS.actors.TRootActor;

public class TAkkaTestJSApp {

    public static final String ROOT_ACTOR = "rootActor";
    public static final String STORAGE_ACTOR = "storageActor";

    private static final String ACTOR_SYSTEM = "test-js";
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final String START_MESSAGE = "Server starts, http://" + HOST + ":" + PORT "/\n";

    public static void main(String[] args) throws Exception {
        ActorSystem sys = ActorSystem.create(ACTOR_SYSTEM);
        ActorRef rootActor = sys.actorOf(Props.create(TRootActor.class), ROOT_ACTOR);

        final Http http = Http.get(sys);

    }
}
