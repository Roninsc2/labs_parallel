package zeroMQ;

public final class TConfig {
    public static final int BACKEND_MSG = 1;
    public static final int FRONTEND_MSG = 0;
    public static final int EPS_TIME = 5000;
    public static final String BACKEND_SOCK = "tcp://localhost:31100";
    public static final String FRONTEND_SOCK = "tcp://localhost:31200";
    public static final String HEARTBEAT_CMD = "Heartbeat";
    public static final String GET_CMD = "GET";
    public static final String PUT_CMD = "PUT";
    public static final String DELIMITER = " ";
}
