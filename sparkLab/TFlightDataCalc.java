package sparkLab;

import java.io.Serializable;

public class TFlightDataCalc implements Serializable {
    private float delay;
    private int delayCount;
    private int cancel;
    private int count;

    TFlightDataCalc(float d, int cancel) {
        if (d > 0) {
            delayCount = 1;
        }
        delay = d;
        cancel = c;
    }
}
