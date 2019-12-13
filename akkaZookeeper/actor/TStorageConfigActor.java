package akkaZookeeper.actor;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akkaZookeeper.packet.TServerListPkt;

import java.util.Random;

public class TStorageConfigActor extends AbstractActor {

    private String[] serverList;

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TPingPkt.class, val -> {
                    Long res = cache.getOrDefault(val.getUrl(), -1L);
                    sender().tell(new TPongPkt(val.getUrl(), res), self());
                })
                .match(TPongPkt.class, val -> {
                    cache.put(val.getUrl(), val.getAvrgPongTime());
                })
                .build();
    }
}
