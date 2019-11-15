package akkaTestJS.testsObj;

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
}
