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
                    System.out.println("configuration set");
                    this.serverList = val.getServerList();
                })
                .match(TServerPkt.class, val -> {
                    sender().tell(getServer(), self());
                })
                .build();
    }

    private String getServer() {
        return serverList[new Random().nextInt(serverList.length)];
    }
}
