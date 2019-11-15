package akkaTestJS.packetJSON;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import akkaTestJS.testsJSON.TTest;

@JsonAutoDetect
public class TPacketTest {

    private String packageId;
    private String jsScript;
    private String functionName;
    private Test[] tests;
}
