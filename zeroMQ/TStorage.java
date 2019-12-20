package zeroMQ;

import java.util.Scanner;

public class TStorage {
    private static int leftBound, rightBound;
    private static final int BACKEND = 0;
    private static final String ERROR = "STORAGE ERROR";
    private static final String DONE = "PUT DONE";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        leftBound = in.nextInt();
        rightBound = in.nextInt();

        Map<Integer, String> cache = new HashMap<>;
    }
}
