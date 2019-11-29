package akkaStreams.packet;

public class TPingPkt {
    private String url;
    private Integer count;

    public TPingPkt(String u, int c) {
        url = u;
        count = c;
    }

    public String getUrl() {
        return url;
    }

    public int getCount() {
        return count;
    }
}
