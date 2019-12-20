package zeroMQ;

public class TCacheMeta {
    private String leftBound, rightBound;
    private long time;

    public TCacheMeta() {

    }

    public String getLeftBound() {
        return leftBound;
    }

    public String getRightBound() {
        return rightBound;
    }

    public boolean isIntersect(String v) {
        int val = Integer.parseInt(v);
        int l = Integer.parseInt(leftBound);
    }
}
