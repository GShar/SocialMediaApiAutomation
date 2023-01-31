package StepDefinition;

import DomainObjects.Comments;
import io.cucumber.java.Before;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.Comment;
import org.junit.Assert;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;

public class RestApiUserCommentsStep {
    private Response response;
    Comments comments;
    List<Comment> lstActualComments;

    @Before
    public void before() {
        comments = new Comments();
    }

    @Given("I have a rest service endpoint for comments")
    public void getAllComments() throws URISyntaxException {
        response = comments.getAllComments();
    }

    @When("I get all comments on a post with postId {string}")
    public void getCommentsForPostWithId(String postId) throws URISyntaxException {
        response = comments.getCommentsOnPost(postId);
        lstActualComments = List.of(response.getBody().as(Comment[].class));
    }
    @And("I verify list contains comment with email {string}")
    public void verifyListOfEmailIds(String expectedEmailID){
        List<String> lstOfActualEmailId = response.then().extract().path("email");
        Assert.assertTrue(lstOfActualEmailId.contains(expectedEmailID));
    }

    @Then("I verify total {int} comments are returned")
    public void verifyTotalCommentsOnPost(Integer expectedCount){
        response.then().assertThat().body("size()", is(expectedCount));
    }

    @And("I verify postId is {int} in all the comments")
    public void verifyPostIDinComments(Integer expectedPostId){
        List<Integer> lstPostID = response.then().extract().path("postId");
        lstPostID.stream().allMatch(actualPostId -> actualPostId == expectedPostId);
    }
    @And("I verify Ids in the comments are in order")
    public void verifyCommentID(@Transpose List<Integer> lstOfExpectedId){
        List<Integer> lstOfActualId = response.then().extract().path("id");
        Assert.assertEquals(lstOfActualId, lstOfExpectedId);
    }

    @And("I verify comment with {string} contains text {string}")
    public void verifyListOfCommentsContainsCommentWithText(String email, String expectedComment) {
        //List<Integer> lstOfActualComments = response.then().extract().path("body");
        List<Comment> filteredComments = lstActualComments.stream()
                .filter(comment -> comment.getEmail().equals(email))
                .toList();

        Assert.assertTrue(filteredComments.size()==1);
        Assert.assertTrue(filteredComments.get(0).getBody().startsWith(expectedComment));
    }


    @And("I verify list contains comment with name {string}")
    public void verifyNameOnComments(String expectedName){
        List<String> lstOfActualNames = response.then().extract().path("name");
        Assert.assertTrue(lstOfActualNames.contains(expectedName));
    }

    @When("I comment on a post using text {string} and postId {int}")
    public void addCommentToPost(String comment, Integer postId){
        response = comments.createComment(comment, postId);
    }

    @And("I verify Status Code is {int}")
    public void verifyStatusCode(Integer expectedStatusCode){
        response
                .then()
                .assertThat()
                .statusCode(expectedStatusCode);
    }

    @And("I verify comment endpoint response body contains text {string}")
    public void verifyResponsyBodyContainsComment(String expectedComment){
        response
                .then()
                .assertThat()
                .body("body", equalTo(expectedComment));
    }

    @And("I verify comment endpoint response header Content Type is Json")
    public void verifyContentTypeIsJson(){
        response
                .then()
                .assertThat()
                .header("Content-Type", "application/json; charset=utf-8");
    }

    @And("I verify comment endpoint response time is less than {long} milliseconds")
    public void verifyResponseTimeIsLessThanMilliseconds(long seconds){
        response.then().assertThat().time(lessThan(seconds), TimeUnit.MILLISECONDS);
    }


    @And("I verify comment endpoint response body contains postId {int}")
    public void verifyResponseBodyContainsPostId(int expectedPostId) {
        response
                .then()
                .assertThat()
                .body("postId", equalTo(expectedPostId));
    }
}
