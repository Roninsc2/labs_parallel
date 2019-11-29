package akkaStreams.pingApp;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.scaladsl.model.HttpResponse;
import akka.stream.javadsl.Flow;
import akkaStreams.actors.TCacheActor;
import org.apache.http.HttpRequest;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
public class TServer {

    private AsyncHttpClient client =  Dsl.asyncHttpClient();
    private ActorRef actor;

    TServer(ActorSystem system) {
        actor = system.actorOf(Props.create(TCacheActor.class));
    }

    FLow<HttpRequest, HttpResponse, NotUsed> getFlow() {
        return Flow.of(HttpRequest.class).map(val ->

        )
        .mapAsync()
    }
}
