package akkaTestJS.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import akkaTestJS.testsJSON.TTestResult;
import akkaTestJS.packetJSON.TResultPackageID;
import akkaTestJS.packetJSON.TPacketTestResult;

import java.util.*;

public class TStorageActor extends AbstractActor {

    private static final String ERROR_404 = "ERROR 404 : RESULT NOT FOUND";

    private Map<String, List<TTestResult>> storage = new HashMap<>();
    public Receive createRecive() {

    }
}
