package akkaStreams.packet;

public class TPongPkt {
    private String url;
    private Long avrgPongTime;

    public TPongPkt(String u, Long pongTime) {
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
