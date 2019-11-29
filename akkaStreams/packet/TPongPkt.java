package akkaStreams.packet;

public class TPongPkt {
    private String url;
    private Integer avrgPongTime;

    public TPongPkt(String u, Integer pongTime) {
        url = u;
        avrgPongTime = pongTime;
    }

    public String getUrl() {
        return url;
    }

    public Integer getAvrgPongTime() {
        return avrgPongTime;
    }

}
