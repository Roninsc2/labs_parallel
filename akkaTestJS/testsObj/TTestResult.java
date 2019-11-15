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
    private boolean isSuccess
}
