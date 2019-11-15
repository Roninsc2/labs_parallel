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

    public void setPackageId(String id) {
        packageId = id;
    }

    public void setJsScript(String script) {
        jsScript = script;
    }

    public void setFunctionName(String fName) {
        functionName = fName;
    }

    public void setTests(TTest[] t) {
        tests = t;
    }
    
}
