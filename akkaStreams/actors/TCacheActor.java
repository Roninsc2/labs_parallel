package akkaStreams.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akkaStreams.packet.TPongPkt;
import akkaStreams.packet.TPingPkt;

import java.util.HashMap;

public class TCacheActor extends AbstractActor {

    private Map<String, int> cache = new HashMap<>();

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TPingPkt.class, val -> {
                    int res = cache.getOrDefault(val.getUrl(), -1);
                })
                .match(TResultPackageID.class, val -> {
                    storageActor.tell(val, sender());
                    System.out.println("Test results requested for id = " + val.getPackageId());
                })
                .build();
    }
}
