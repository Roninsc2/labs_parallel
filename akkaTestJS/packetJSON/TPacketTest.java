package akkaTestJS.packetJSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import akkaTestJS.testsJSON.TTest;

@JsonAutoDetect
public class TPacketTest {

    private String packageId;
    private String jsScript;
    private String functionName;
    private TTest[] tests;

    public TPacketTest() {
    }

    public TPacketTest(String id, String script, String fName, TTest[] t) {
        packageId = id;
        jsScript = script;
        functionName = fName;
        tests = t;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public TTest[] getTests() {
        return tests;
    }
}
