package co.com.sofka.stepdefnitions.rest.jsonplaceholder.comments.get;

import co.com.sofka.stepdefnitions.rest.jsonplaceholder.comments.SetUpRetoRest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetCommentsWithCucumberStepDefinition extends SetUpRetoRest {

    private String param;
    private int value;

    @Given("el usuario decidio consultar un comment por el campo \\({string}) con valor \\({int})")
    public void el_usuario_decidio_consultar_un_comment_por_el_campo_con_valor(String param, Integer value) {
        setUp();
        this.param = param;
        this.value = value;
        request = defineGetRequest(RESOURCE, this.param);
    }

    @When("el usuario realiza la peticion")
    public void el_usuario_realiza_la_peticion() {
        actor.attemptsTo(
                Get.resource(request).with(request -> request.pathParam(param, value))
        );
    }

    @Then("deberia obtener resultado exitoso con la informacion relativa al comment solicitado")
    public void deberia_obtener_resultado_exitoso_con_la_informacion_relativa_al_comment_solicitado() {

        actor.should(
                seeThatResponse(
                        "debe aparecer los comments para el id solicitado: ",
                        response -> response
                                .log().all()
                                .statusCode(SC_OK)
                                .body(param ,equalTo(value))
                )
        );
    }

    @Given("el usuario decidio consultar un comment por un campo inexistente \\({string}) con valor \\({int})")
    public void el_usuario_decidio_consultar_un_comment_por_un_campo_inexistente_con_valor(String param, Integer value) {
        setUp();
        this.param = param;
        this.value = value;
        request = defineGetRequest(RESOURCE, param);
        System.out.println(request);
    }

    @Then("deberia obtener resultado de error")
    public void deberia_obtener_resultado_de_error() {

        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_NOT_FOUND,
                        response -> response
                                .log().all()
                                .statusCode(SC_NOT_FOUND)
                )
        );
    }

    private String defineGetRequest(String resource, String param){
        return resource.concat("/{").concat(this.param).concat("}");
    }

}
