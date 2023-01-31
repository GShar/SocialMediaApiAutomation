package StepDefinition;

import DomainObjects.Posts;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class RestApiPostStep {
    private Response response;
    Posts posts;

    @Before
    public void before() {
        posts = new Posts();
    }

    @Given("I have a rest service which is available for endpoint {string}")
    public void getUserPosts(String uri) throws URISyntaxException {
        response = RestAssured.given().spec(posts.requestSpecification).get(new URI(uri));
        Assert.assertEquals(200, response.statusCode());
    }

    @When("I create a post for user with body and title and userId")
    public void responseIsStatusCode(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        response = posts.createPost(data);
        Assert.assertEquals(201, response.statusCode());
    }

    @Then("I verify that post endpoint response contains title {string}")
    public void verifyPostTitleIs(String title) {
        response.then().assertThat().body("title", equalTo(title));
    }

    @And("I verify that post endpoint response contains body {string}")
    public void verifyPostBodyIs(String body) {
        response.then().assertThat().body("body", equalTo(body));
    }

    @And("I verify that post endpoint response contains userId {string}")
    public void verifyPostUserIdIs(String userId) {
        response.then().assertThat().body("userId", equalTo(userId));
    }

    @And("I verify post endpoint response header content type is Json")
    public void verifyContentTypeIsJson() {
        response.then().assertThat().header("Content-Type", "application/json; charset=utf-8");
    }

    @And("The post endpoint response time is less than {long} milliseconds")
    public void verifyResponseTime(long seconds) {
        response.then().assertThat().time(lessThan(seconds), TimeUnit.MILLISECONDS);
    }

    @And("I verify post endpoint response time is less than {long} milliseconds")
    public void verifyResponseTimeIsLessThanMilliseconds(long seconds) {
        response.then().assertThat().time(lessThan(seconds), TimeUnit.MILLISECONDS);
    }
}
