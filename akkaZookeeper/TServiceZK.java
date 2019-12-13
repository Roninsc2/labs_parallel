package akkaZookeeper;

import akka.actor.ActorRef;
import org.apache.zookeeper.*;

import java.io.IOException;

public class TServiceZK {
    private static final String ZK_HOST_PORT = "127.0.0.1:2181";
    private static final int TIMEOUT = 3000;
    private static final String ROOT_PATH = "/servers";
    private static final String NODES_PATH = "/servers/s";

    private  ZooKeeper zk;
    private ActorRef actor;

    public TServiceZK(ActorRef actor) throws IOException {
        zk = new ZooKeeper(ZK_HOST_PORT, TIMEOUT, null);
        this.actor = actor;
    }
}
