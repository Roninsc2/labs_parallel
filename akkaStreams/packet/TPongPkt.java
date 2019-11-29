package akkaStreams.packet;

public class TPongPkt {
    private String url;
    private int avrgPongTime;

    public TPongPkt(String u, int pongTime) {
        url = u;
        avrgPongTime = pongTime;
    }

    String getUrl() {
        return url;
    }

    int getAvrgPongTime() {
        return avrgPongTime;
    }

}
