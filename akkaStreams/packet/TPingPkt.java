package akkaStreams.packet;

public class TPingPkt {
    private Stirng url;
    private int count;

    public TPingPkt(String u, int c) {
        url = u;
    }
}
