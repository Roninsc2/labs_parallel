package zeroMQ;

import org.zeromq.*;
import java.util.Scanner;
import static zeroMQ.TConfig.*;

public class TClient {
    private static final String ERROR = "CLIENT ERROR";
    private static final String EXIT_CMD = "EXIT";
    private static final String READY = "CLIENT READY";
    private static final String INCORRECT_INPUT = "INCORRECT INPUT";

    public static void main(String[] args) {
        try {
            ZContext ctx = new ZContext();
            ZMQ.Socket client = ctx.createSocket(SocketType.REQ);
            client.setHWM(0);
            client.connect(FRONTEND_SOCK);
            System.out.println(READY);
            Scanner.in = new Scanner(System.in);
        } catch (ZMQException e) {
            System.out.println(ERROR);
            e.printStackTrace();
        }
    }
}
