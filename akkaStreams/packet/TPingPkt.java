package akkaStreams.packet;

public class TPingPkt {
    private String url;
    private Integer count;

    public TPingPkt(String u, Integer c) {
        url = u;
        count = c;
    }

    public String getUrl() {
        return url;
    }

    public Integer getCount() {
        return count;
    }
}
