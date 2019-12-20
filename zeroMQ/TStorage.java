package zeroMQ;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.zeromq.*;
import static zeroMQ.TConfig.*;

public class TStorage {
    private static int leftBound, rightBound;
    private static final int BACKEND = 0;
    private static final String ERROR = "STORAGE ERROR";
    private static final String DONE = "PUT DONE";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        leftBound = in.nextInt();
        rightBound = in.nextInt();

        Map<Integer, String> cache = new HashMap<>();

        try {
            ZContext ctx = new ZContext();
            ZMQ.Socket backendSoc = ctx.createSocket(SocketType.DEALER);
            backendSoc.setHWM(0);
            backendSoc.connect(BACKEND_SOCK);
            ZMQ.Poller poller = ctx.createPoller(1);
            poller.register(backendSoc, ZMQ.Poller.POLLIN);
            long time = System.currentTimeMillis();
            while (!Thread.currentThread().isInterrupted()) {
                poller.poll(1);
                if (System.currentTimeMillis() - time > EPS_TIME) {
                    ZMsg msgTime = new ZMsg();
                    msgTime.addLast(
                            HEARTBEAT_CMD + DELIMITER + Integer.toString(leftBound)
                            + DELIMITER + Integer.toString(rightBound)
                    );
                    msgTime.send(backendSoc);
                    time = System.currentTimeMillis();
                }
                if (poller.pollin(BACKEND)) {

                }
            }
        }
    }

    private void pollinBackend(ZMQ.Socket socket) {
        ZMsg msg = ZMsg.recvMsg(socket);
        System.out.println("Message ->" + msg.toString());
        ZFrame content = msg.getLast();
        
    }
}
