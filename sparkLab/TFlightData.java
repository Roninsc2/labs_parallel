package sparkLab;

import java.io.Serializable;

public class TFlightData implements Serializable {
    private float delay;
    private int delayCount;
    private int cancelCount;
    private int sumCount;

    TFlightData(float d, int c) {
        delayCount = 0;
        if (d > 0.0) {
            delayCount = 1;
        }
        delay = d;
        cancelCount = c;
        sumCount = 1;
    }

    TFlightData(float d, int dCount, int c, int sum) {
        delayCount = dCount;
        delay = d;
        cancelCount = c;
        sumCount = sum;
    }

    static TFlightData calculate(TFlightData a, TFlightData b) {
        float newDelay = Float.max(a.delay, b.delay);
        int newDelayCount = a.delayCount + b.delayCount;
        int newCancelCount = a.cancelCount + b.cancelCount;
        int newSumCount = a.sumCount + b.sumCount;
        return new TFlightData(newDelay, newDelayCount, newCancelCount, newSumCount);
    }

    public String getStringData() {
        int delayPart = (int)((float) delayCount/sumCount * 100);
        int cancelPart = (int)((float) cancelCount/sumCount * 100);
        String res = "max delay: " + delay;
        res += " | delay part: " + delayPart;
        res += " | cancel part: " + cancelPart + ";";
        return res;
    }
}
