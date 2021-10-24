package co.com.sofka.tasks.jsonplaceholder;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

public class DoGet implements Task {
    private String resource;
    private Map<String, Object> headers;
    private String bodyRequest;

    public DoGet usingThe(String resource) {
        this.resource = resource;
        return this;
    }

    public DoGet with(Map<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    public DoGet and(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(resource)
                        .with(
                                req -> req.relaxedHTTPSValidation()
                                        .headers(headers)
                                        .body(bodyRequest)
                        )
        );
    }

    public static DoGet doGet(){
        return new DoGet();
    }
}
