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
        ReceiveBuilder.create()
                .match(TTestResult.class, val -> {
                    storage.computeIfAbsent(val.getPackageId(), k -> new ArrayList<>());
                    storage.get(val.getPackageId()).add(val);
                })
                .match(TResultPackageID.class, val -> {
                    List<TTestResult> results = storage.get(val.getPackageId());
                    if (results != null) {
                        results.sort(Comparator.comparing(TTestResult::getName));
                        TPacketTestResult pktResult = new TPacketTestResult(
                                val.getPackageId(),
                                results.toArray(new TTestResult[0])
                        );

                        sender().tell(pktResult, self());
                    } else {
                        sender().tell(ERROR_404, self());
                    }
                })

                )
    }
}
