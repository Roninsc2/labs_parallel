package sparkLab;

import java.io.Serializable;

public class TFlightDataCalc implements Serializable {
    private float delay;
    private int delayCount;
    private int cancelCount;
    private int sumCount;

    TFlightDataCalc(float d, int c) {
        delayCount = 0;
        if (d > 0.0) {
            delayCount = 1;
        }
        delay = d;
        cancelCount = c;
        sumCount = 1;
    }

    TFlightDataCalc(float d, int dCount, int c, int sum) {
        delayCount = dCount;
        delay = d;
        cancelCount = c;
        sumCount = sum;
    }

    static TFlightDataCalc calculate(TFlightDataCalc a, TFlightDataCalc b) {
        float newDelay = Float.max(a.delay, b.delay);
        int newDelayCount = a.delayCount + b.delayCount;
        int newCancelCount = a.cancelCount + b.cancelCount;
        int newSumCount = a.sumCount + b.sumCount;
        return new TFlightDataCalc(newDelay, newDelayCount, newCancelCount, newSumCount);
    }

    public String toString() {
        int delayPart = (int)((float) delayCount/sumCount * 100);
        int cancelPart = (int)((float) cancelCount/sumCount * 100);
        System.out.println(delayCount + " " + cancelCount + " " + sumCount "\n");
        String res = "max delay: " + delay;
        res += " | delay part: " + delayPart;
        res += " | cancel part: " + cancelPart + ";";
        return res;
    }
}
