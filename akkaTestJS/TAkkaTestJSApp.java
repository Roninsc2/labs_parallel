package akkaTestJS;

import akka.actor.ActorSystem;

public class TAkkaTestJSApp {

    public static final String ROOT_ACTOR = "rootActor";
    public static final String STORAGE_ACTOR = "storageActor";

    private static final String ACTOR_SYSTEM = "test-js";
    private static final String HOST_NAME = "localhost";
    private static final String
    public static void main(String[] args) throws Exception {
        ActorSystem sys = ActorSystem.create()
    }
}
