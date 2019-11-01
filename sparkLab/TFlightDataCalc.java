package sparkLab;

import java.io.Serializable;

public class TFlightDataCalc implements Serializable {
    private float delay;
    private int delayCount;
    private int cancelCount;
    private int sumCount;

    TFlightDataCalc(float d, int c) {
        delayCount = 0;
        if (d > 0) {
            delayCount = 1;
        }
        delay = d;
        cancelCount = c;
        sumCount = 1;
    }

    static TFlightDataCalc calculate(TFlightDataCalc a, TFlightDataCalc b) {
        float newDelay = Float.max(a.delay, b.delay);
        int newDelayCount = a.delayCount + b.delayCount;
        int newCancelCount = a.cancelCount + b.cancelCount;
    }
}
