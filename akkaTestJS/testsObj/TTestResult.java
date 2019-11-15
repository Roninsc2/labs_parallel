package akkaTestJS.testsObj;

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
    private String expectedRes;
    private Object[] params;

    public TTestResult() {
    }

    public TTestResult(String id, String n, boolean isOk, ) {
    }

}
