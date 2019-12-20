package zeroMQ;

import org.zeromq.*;
import scala.collection.Map;
import scala.collection.mutable.HashMap;

import static zeroMQ.TConfig.*;

public class TProxy {

    private static final String ERROR = "PROXY ERROR";
    private static final String NO_CACHE_ERROR = "NO CACHE AVAILABLE";
    private static final String INVALID_DATA = "INVALID DATA";

    public static void main(String[] args) {
        try {
            ZContext ctx = new ZContext();
            ZMQ.Socket backend = ctx.createSocket(SocketType.ROUTER);
            ZMQ.Socket frontend = ctx.createSocket(SocketType.ROUTER);
            backend.setHWM(0);
            frontend.setHWM(0);
            backend.bind(BACKEND_SOCK);
            frontend.bind(FRONTEND_SOCK);
            ZMQ.Poller poller = ctx.createPoller(2);
            poller.register(frontend, ZMQ.Poller.POLLIN);
            poller.register(backend, ZMQ.Poller.POLLIN);
            Map<ZFrame, TCacheMeta> commutator = new HashMap<>();
        } catch (ZMQException e) {
            System.out.println(ERROR);
            e.printStackTrace();
        }
    }
}
