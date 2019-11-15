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

        for (TTestResult result : testResults) {
            if (!result.isSuccess()) {
                isSuccess = false;
                break;
            }
        }
    }

    public String getPackageId() {
        return packageId;
    }

    public TTestResult[] getTestResults() {
        return testResults;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setPackageId(String id) {
        packageId = id;
    }

    public void setTestResults(TTestResult[] results) {
        testResults = results;
    }

    public void setSuccess(boolean successFlag) {
        isSuccess = successFlag;
    }
    
}
