package akkaStreams.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akkaStreams.packet.TPongPkt;
import akkaStreams.packet.TPingPkt;

import java.util.HashMap;
import java.util.Map;

public class TCacheActor extends AbstractActor {

    private Map<String, int> cache = new HashMap<>();

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TPingPkt.class, val -> {
                    int res = cache.getOrDefault(val.getUrl(), -1);
                    sender().tell(new TPingPkt(val.getUrl(), res), self());
                })
                .match(TPongPkt.class, val -> {
                    cache.put(val.getUrl(), val.get);
                })
                .build();
    }
}
