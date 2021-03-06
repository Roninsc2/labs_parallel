package akkaTestJS.testsJSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonAutoDetect
@JsonPropertyOrder({"name", "success", "result", "expectedRes", "params"})
public class TTestResult {

    @JsonIgnore
    private String packageId;
    @JsonProperty("success")
    private boolean isSuccess;
    private String result;
    private String name;
    private String expectedResult;
    private Object[] params;

    public TTestResult() {
    }

    public TTestResult(String id, String n, boolean successFlag,
                       String res, String exRes,
                       Object[] p)
    {
        packageId = id;
        name = n;
        isSuccess = successFlag;
        result = res;
        expectedResult = exRes;
        params = p;
    }

    public void setPackageId(String id) {
        packageId = id;
    }

    public void setName(String n) {
        name = n;
    }

    public void setSuccess(boolean successFlag) {
        isSuccess = successFlag;
    }

    public void setResult(String res) {
        result = res;
    }

    public void setExpectedResult(String exRes) {
        expectedResult = exRes;
    }

    public void setParams(Object[] p) {
        params = p;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Object[] getParams() {
        return params;
    }

}
