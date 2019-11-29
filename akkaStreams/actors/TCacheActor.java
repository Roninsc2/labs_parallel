package akkaStreams.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akkaStreams.packet.TPongPkt;
import akkaStreams.packet.TPingPkt;

public class TCacheActor extends AbstractActor {

    private static final int INITIAL_POOL_SIZE = 3;

    private ActorRef storageActor = getContext().actorOf(Props.create(TStorageActor.class), TAkkaTestJSApp.STORAGE_ACTOR);
    private ActorRef testRouter = getContext().actorOf(
            new RoundRobinPool(INITIAL_POOL_SIZE).props(Props.create(TTestRunActor.class))
    );

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TPacketTest.class, val -> {
                    for (TTest test : val.getTests()) {
                        testRouter.tell(new TPacketTest(
                                        val.getPackageId(),
                                        val.getJsScript(),
                                        val.getFunctionName(),
                                        new TTest[]{test}
                                ), self()
                        );
                        System.out.println(test.getName() + " started");
                    }
                })
                .match(TResultPackageID.class, val -> {
                    storageActor.tell(val, sender());
                    System.out.println("Test results requested for id = " + val.getPackageId());
                })
                .build();
    }
}
