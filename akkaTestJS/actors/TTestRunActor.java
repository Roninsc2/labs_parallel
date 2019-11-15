package akkaTestJS.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akkaTestJS.packetJSON.TPacketTest;
import akkaTestJS.testsJSON.TTest;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TTestRunActor extends AbstractActor {

    private static final String ENGINE_NAME = "nashorn";

    public Receive createRecive() {
        return ReceiveBuilder.create()
                .match(TPacketTest.class, val -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
                    TTest test = val.getTests()[0];
                        }

                )
    }
}
