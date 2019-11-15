package akkaTestJS.testsJSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class TTest {

    private String name;
    private String expectedRes;
    private Object[] params;

    public TTest() {
    }

    public TTest(String n, String exRes, Object[] p) {
        name = n;
        expectedRes = exRes;
        params = p;
    }

    public void setName(String n) {
        name = n;
    }

    public void setExpectedRes(String exRes) {
        expectedRes = exRes;
    }

    public void setParams(Object[] p) {
        params = p;
    }

    public String getName() {
        return name;
    }

    public String getExpectedRes() {
        return expectedRes;
    }

    public Object[] getParams() {
        return params;
    }
}
