package StepDefinition;

import components.Users;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.User;
import org.junit.Assert;

import java.net.URISyntaxException;
import java.util.List;

public class UsersStep {
    private Response response;
    Users users;
    List<User> lstActualUser;

    @Before
    public void before() {
        users = new Users();
    }

    @Given("I have a rest service endpoint for users")
    public void getUserList() throws URISyntaxException {
        response = users.getAllUsers();
        Assert.assertEquals(200, response.statusCode());
    }

    @When("I verify that the response schema is correct")
    public void verifyUserResponseSchema() {
        lstActualUser = List.of(response.getBody().as(User[].class));
    }

    @And("I verify Users list is not empty")
    public void checkListContainsMinUsers() {
        Assert.assertFalse(lstActualUser.isEmpty());
    }
}
