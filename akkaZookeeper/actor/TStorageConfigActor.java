package akkaZookeeper.actor;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akkaZookeeper.packet.TServerListPkt;
import akkaZookeeper.packet.TServerPkt;

import java.util.Random;

public class TStorageConfigActor extends AbstractActor {

    private String[] serverList;

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TServerListPkt.class, val -> {
                    
                })
                .match(TPongPkt.class, val -> {
                    cache.put(val.getUrl(), val.getAvrgPongTime());
                })
                .build();
    }
}
