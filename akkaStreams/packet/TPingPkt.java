package akkaStreams.packet;

public class TPingPkt {
    private String url;
    private int count;

    public TPingPkt(String u, int c) {
        url = u;
        count = c;
    }

    public String getUrl() {
        return url;
    }
}
