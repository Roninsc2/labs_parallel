package akkaTestJS.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akkaTestJS.TAkkaTestJSApp;
import akkaTestJS.packetJSON.TPacketTest;
import akkaTestJS.testsJSON.TTest;
import akkaTestJS.testsJSON.TTestResult;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TTestRunActor extends AbstractActor {

    private static final String ENGINE_NAME = "nashorn";
    private static final String ERROR_RUNTIME = "RUNTIME ERROR";
    private static final String PATH_TO_STORAGE_ACTOR = "/user/" + TAkkaTestJSApp.ROOT_ACTOR + "/" + TAkkaTestJSApp.STORAGE_ACTOR;

    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TPacketTest.class, val -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
                    TTest test = val.getTests()[0];
                    String res;

                    try {
                        engine.eval(val.getJsScript());
                        Invocable invocable = (Invocable) engine;
                        Object[] params = test.getParams();
                        res = invocable.invokeFunction(val.getFunctionName(), params).toString();
                    } catch (Exception ex) {
                        res = ERROR_RUNTIME;
                    }

                    System.out.println(test.getName() + " ended");

                    getContext().actorSelection(PATH_TO_STORAGE_ACTOR)
                            .tell(new TTestResult(
                                    val.getPackageId(),
                                    test.getName(),
                                    res.equals(test.getExpectedResult()),
                                    res,
                                    test.getExpectedResult(),
                                    test.getParams()
                            ), self()
                            );
                })
                .build();
    }
}
