package akkaZookeeper;

import akka.actor.ActorRef;
import akkaZookeeper.packet.TServerListPkt;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TServiceZK {
    private static final String ZK_HOST_PORT = "127.0.0.1:2181";
    private static final int TIMEOUT = 3000;
    private static final String ROOT_PATH = "/servers";

    private  ZooKeeper zk;
    private ActorRef actor;

    public TServiceZK(ActorRef actor) throws IOException {
        zk = new ZooKeeper(ZK_HOST_PORT, TIMEOUT, null);
        this.actor = actor;
        watchServers();
    }

    public void createServer(String host, int port) throws KeeperException, InterruptedException {
        zk.create(
                ROOT_PATH + "/" + port,
                (host+port).getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL
        );
    }

    private void watchServers() {
        try {
            List<String> serverNodes = zk.getChildren(ROOT_PATH, event -> {
                if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                    watchServers();
                }
            });

            List<String> servers = new ArrayList<>();

            for (String nodeName : serverNodes) {
                byte[] serverUrl = zk.getData(ROOT_PATH + "/" + nodeName, null, null);
                servers.add(new String(serverUrl));
            }

            actor.tell(new TServerListPkt(servers.toArray(new String[0])), ActorRef.noSender());
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}
