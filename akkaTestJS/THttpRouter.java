package akkaTestJS;

import akka.actor.ActorRef;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

public class THttpRouter extends AllDirectives {
    private static final String TEST_STARTED = "TEST STARTED";
    private static final String TEST_PATH = "test";
    private static final String RESULT_PATH = "result";
    private static final String PARAMETER_PACKAGE_ID = "packageId";
    private static final int TIMEOUT = 5000;

    THttpRouter(){
    }

    Route createRoute(ActorRef rootActor) {
        return route()
    }
}
