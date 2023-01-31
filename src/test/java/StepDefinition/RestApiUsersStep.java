package StepDefinition;

import DomainObjects.Users;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.User;
import org.junit.Assert;

import java.net.URISyntaxException;
import java.util.List;

public class RestApiUsersStep {
    private Response response;
    Users users;
    List<User> lstActualUser;
    @Before
    public void before() {
        users = new Users();
    }

    @Given("I have a rest service get all users with uri {string}")
    public void getUserList(String uri) throws URISyntaxException {
        response = users.getAllUsers(uri);
        Assert.assertEquals(200, response.statusCode());
    }
    @When("I verify that the response schema is correct")
    public void verifyUserResponseSchema(){
        lstActualUser = List.of(response.getBody().as(User[].class));
    }

    @And("I verify Users list is not empty")
    public void checkListContainsMinUsers(){
        Assert.assertTrue(!lstActualUser.isEmpty());
    }
   /* @And("I verify the user metadata is correct")
    public void verifyUserName(List<String> lstExpectedJson){

        )*/


}
