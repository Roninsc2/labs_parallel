package akkaZookeeper;

import akka.actor.ActorRef;
import org.apache.zookeeper.*;

import java.io.IOException;

public class TServiceZK {
    private static final String ZK_HOST_PORT = "127.0.0.1:2181";
    private static final int TIMEOUT = 3000;
    private static final String ROOT = "/servers"

    private  ZooKeeper zk;
    private ActorRef actor;

    public TServiceZK(ActorRef actor) throws IOException {
        zk = new ZooKeeper()
    }
}
