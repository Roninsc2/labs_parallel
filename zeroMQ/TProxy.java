package zeroMQ;

import org.zeromq.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
            long time = System.currentTimeMillis();
            while (!Thread.currentThread().isInterrupted()) {
                poller.poll(1);
                time = cleanCache(commutator, time);

                if (poller.pollin(FRONTEND_MSG) && processFrontend(backend, frontend, commutator)) {
                    break;
                }

                if (poller.pollin(BACKEND_MSG) && processBackend(backend, frontend, commutator)) {
                    break;
                }
            }
        } catch (ZMQException e) {
            System.out.println(ERROR);
            e.printStackTrace();
        }
    }

    private static boolean processFrontend(ZMQ.Socket backend, ZMQ.Socket frontend, Map<ZFrame, TCacheMeta> commutator) {
        ZMsg msg = ZMsg.recvMsg(frontend);
        if (msg == null) {
            return true;
        }
        if (commutator.isEmpty()) {
            sendError(frontend, msg, NO_CACHE_ERROR);
        } else {
            String[] data = msg.getLast().toString().split(DELIMITER);
            if (data[0].equals(GET_CMD)) {
                processFrontendGet()
            } else {
                if (data[0].equals(PUT_CMD)) {
                    processFrontendPut();
                } else {
                    sendError(frontend, msg, INVALID_DATA);
                }
            }
        }
        return false;
    }

    private static boolean processFrontendGet(ZMQ.Socket backend, ZMQ.Socket frontend, Map<ZFrame, TCacheMeta> commutator) {
                for (Map.Entry<ZFrame, TCacheMeta> map : commutator.entrySet()) {
                    if (map.getValue().isIntersect(data[1])) {
                        ZFrame cacheFrame = map.getKey().duplicate();
                        msg.addFirst(cacheFrame);
                        msg.send(backend);
                    }
                }
    }

    private static void processFrontendPut(ZMsg msg, ZMQ.Socket backend, Map<ZFrame, TCacheMeta> commutator) {
        for (Map.Entry<ZFrame, TCacheMeta> map : commutator.entrySet()) {
            if (map.getValue().isIntersect(data[1])) {
                ZMsg tmp = msg.duplicate();
                ZFrame cacheFrame = map.getKey().duplicate();
                tmp.addFirst(cacheFrame);
                tmp.send(backend);
            }
        }
    }

    private static boolean processBackend(ZMQ.Socket backend, ZMQ.Socket frontend, Map<ZFrame, TCacheMeta> commutator) {
        ZMsg msg = ZMsg.recvMsg(backend);
        if (msg == null) {
            return true;
        }
        if (msg.getLast().toString().contains(HEARTBEAT_CMD)) {
            processBackendHeartbeat(commutator, msg);
        } else {
            System.out.println("NO HEARTBEAT ->" + msg);
            msg.pop();
            msg.send(frontend);
        }
        return false;
    }

    private static void processBackendHeartbeat(Map<ZFrame, TCacheMeta> commutator, ZMsg msg) {
        if (!commutator.containsKey(msg.getFirst())) {
            ZFrame data = msg.getLast();
            String[] fields = data.toString().split(DELIMITER);
            TCacheMeta tmp = new TCacheMeta(
                    fields[1],
                    fields[2],
                    System.currentTimeMillis()
            );
            commutator.put(msg.getFirst().duplicate(), tmp);
            System.out.println("New cache -> " + msg.getFirst() + " " + tmp.getLeftBound() + " " + tmp.getRightBound());
        } else {
            commutator.get(msg.getFirst().duplicate()).setTime(System.currentTimeMillis());
        }
    }

    private static void sendError(ZMQ.Socket frontend, ZMsg msg, String noCacheError) {
        ZMsg errMsg = new ZMsg();
        errMsg.add(noCacheError);
        errMsg.wrap(msg.getFirst());
        errMsg.send(frontend);
    }

    private static long cleanCache(Map<ZFrame, TCacheMeta> commutator, long time) {
        if(!commutator.isEmpty() && System.currentTimeMillis() - time > EPS_TIME * 2){
            for(Iterator<Map.Entry<ZFrame, TCacheMeta>> it = commutator.entrySet().iterator(); it.hasNext(); ){
                Map.Entry<ZFrame, TCacheMeta> entry = it.next();
                if(Math.abs(entry.getValue().getTime() - System.currentTimeMillis()) > EPS_TIME * 1.5){
                    System.out.println("CACHE WAS DELETED -> " + entry.getKey());
                    it.remove();
                }
            }
            time = System.currentTimeMillis();
        }
        return time;
    }
}
