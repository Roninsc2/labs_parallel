package akkaTestJS.packetJSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import akkaTestJS.testsJSON.TTestResult;

@JsonAutoDetect
public class TPacketTestResult {

    private String packageId;
    private boolean isSuccess;
    private TTestResult[] testResults;

    public TPacketTestResult() {
    }

    public TPacketTestResult(String id, TTestResult[] results) {
        packageId = id;
        testResults = results;
        isSuccess = true;
    }
}
