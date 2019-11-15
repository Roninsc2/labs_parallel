package akkaTestJS.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akkaTestJS.TAkkaTestJSApp;
import akkaTestJS.packetJSON.TPacketTest;
import akkaTestJS.testsJSON.TTest;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TTestRunActor extends AbstractActor {

    private static final String ENGINE_NAME = "nashorn";
    private static final String ERROR_RUNTIME = "RUNTIME ERROR";
    private static final String PATH_TO_STORAGE_ACTOR = "/user/" + TAkkaTestJSApp.ROOT_ACTOR + "/" + TAkkaTestJSApp.STORAGE_ACTOR;
    public Receive createRecive() {
        return ReceiveBuilder.create()
                .match(TPacketTest.class, val -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
                    TTest test = val.getTests()[0];
                    String res;

                    try {

                    } catch (Exception ex) {
                        res = ERROR_RUNTIME;
                    }
                        }

                )
    }
}
