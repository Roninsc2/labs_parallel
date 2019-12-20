package zeroMQ;

import org.zeromq.*;
import java.util.Scanner;
import static zeroMQ.TConfig.*;

public class TClient {
    private static final String ERROR = "CLIENT ERROR";

    public static void main(String[] args) {
        try {

        } catch (ZMQException e) {
            System.out.println(ERROR);

        }
    }
}
