package co.com.sofka.runner.jsonplaceholder.comments;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/jsonplaceholder/comments/getComments.feature"},
        glue = {"co.com.sofka.stepdefnitions.rest.jsonplaceholder.comments.get"}
)
public class GetCommentsWithCucumberTest {
}
