package co.com.sofka.stepdefnitions.rest.jsonplaceholder.comments;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class SetUpRetoRest {
    protected static final String URL_BASE = "https://jsonplaceholder.typicode.com";
    protected static final String RESOURCE = "/comments";
    protected String request;
    protected Actor actor;

    private void setUpActor(){
        actor = Actor.named("Cristian");
    }

    private void setUpActorAndApi(){
        actor.can(CallAnApi.at(URL_BASE));
    }

    protected void setUp(){
        setUpActor();
        setUpActorAndApi();
    }

    protected HashMap<String, Object> headers(){
        HashMap<String, Object> headersCollection = new HashMap<>();
        headersCollection.put("Content-Type", "application/json; charset=utf-8");
        //headersCollection.put("SOAPAction", "https://www.w3schools.com/xml/FahrenheitToCelsius");
        return headersCollection;
    }

    protected String fromLastResponseBy(Actor actor){
        return  new String(
                LastResponse.received().answeredBy(actor).asByteArray(),
                StandardCharsets.UTF_8
        );
    }
}
