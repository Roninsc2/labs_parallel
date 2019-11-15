package akkaTestJS.packetJSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import akkaTestJS.testsJSON.TTestResult;

@JsonAutoDetect
public class TPacketTestResult {

    private String packageId;
    private boolean isSuccess;
    private TTestResult[] testResults;
}
