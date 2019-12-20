package zeroMQ;

import org.zeromq.*;
import static zeroMQ.TConfig.*;

public class TProxy {

    private static final String ERROR = "PROXY ERROR";
    private static final String NO_CACHE_ERROR = "NO CACHE AVAILABLE";
    private static final String INVALID_DATA = "INVALID DATA";

    public static void main(String[] args) {
        try {
            ZContext ctx = new ZContext();
            ZMQ.Socket backend = ctx.createSocket(SocketType.ROUTER);
            ZMQ.Socket backend = ctx.createSocket(SocketType.ROUTER);
        } catch (ZMQException e) {
            System.out.println(ERROR);
            e.printStackTrace();
        }
    }
}
