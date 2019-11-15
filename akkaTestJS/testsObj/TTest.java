package akkaTestJS.testsObj;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class TTest {

    private String name;
    private String expectedRes;
    private Object[] params;

    public TTest() {
    }
}
