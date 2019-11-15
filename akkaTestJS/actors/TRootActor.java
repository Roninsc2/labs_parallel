package akkaTestJS.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import akkaTestJS.TAkkaTestJSApp;

public class TRootActor extends AbstractActor {

    private static final int INITIAL_POOL_SIZE = 3;

    private ActorRef storageActor = getContext().actorOf(Props.create(TStorageActor.class), TAkkaTestJSApp.STORAGE_ACTOR);
    private ActorRef testActor = getContext().actorOf(
            new RoundRobinPool(INITIAL_POOL_SIZE).props(Props.create(TTestRunActor.class))
    );

}
