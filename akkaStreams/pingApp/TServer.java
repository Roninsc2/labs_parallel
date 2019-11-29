package akkaStreams.pingApp;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akkaStreams.actors.TCacheActor;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
public class TServer {

    private AsyncHttpClient client =  Dsl.asyncHttpClient();
    private ActorRef actor;

    TServer(ActorSystem system) {
        actor = system.actorOf(Props.create(TCacheActor.class));
    }
}
