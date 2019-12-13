package akkaZookeeper.packet;

public class TServerListPkt {
    private String[] serverList;

    public TServerListPkt(String[] serverList) {
        this.serverList = serverList;
    }
}
