package akkaStreams.pingApp;

import akka.actor.ActorRef;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
public class TServer {

    private AsyncHttpClient client =  Dsl.asyncHttpClient();
    private ActorRef actor;
}
