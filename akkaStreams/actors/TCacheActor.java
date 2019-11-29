package akkaStreams.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akkaStreams.packet.TPongPkt;
import akkaStreams.packet.TPingPkt;

public class TCacheActor extends AbstractActor {

    private Map<String, int> cache = new HashMap<>();

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
