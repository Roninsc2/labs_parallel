package akkaTestJS;

import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akkaTestJS.packetJSON.TPacketTest;
import akkaTestJS.packetJSON.TResultPackageID;

public class THttpRouter extends AllDirectives {
    private static final String TEST_STARTED = "TEST STARTED";
    private static final String TEST_PATH = "test";
    private static final String RESULT_PATH = "result";
    private static final String PARAMETER_PACKAGE_ID = "packageId";
    private static final int TIMEOUT = 5000;

    THttpRouter(){
    }

    Route createRoute(ActorRef rootActor) {
        return route(
          path(TEST_PATH, () ->
                  post(() ->
                          entity(Jackson.unmarshaller(TPacketTest.class), val -> {
                              rootActor.tell(val, ActorRef.noSender());
                              return complete(TEST_STARTED);
                          })
                  )
          ),
          path(RESULT_PATH, () ->
                  get(() ->
                          parameter(PARAMETER_PACKAGE_ID, val -> {
                              Future<Object> result = Patterns.ask(rootActor, new TResultPackageID(val), TIMEOUT);
                              return completeOKWithFuture(result, Jackson.marshaller());
                          })
                  )
          )
        );
    }
}
