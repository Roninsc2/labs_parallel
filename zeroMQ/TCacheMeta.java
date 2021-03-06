package zeroMQ;

public class TCacheMeta {
    private String leftBound, rightBound;
    private long time;

    public TCacheMeta(String left, String right, long t) {
        leftBound = left;
        rightBound = right;
        time = t;
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
        int r = Integer.parseInt(rightBound);
        return l <= val && val <= r;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long t) {
        time = t;
    }
}
