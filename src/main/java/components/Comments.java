package components;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;

/*
* This Comments class wraps the generic functions for the Comments resource endpoint
* E.g. Create a comment, getting all comments
* */
public class Comments extends Base {
    /**
     * create comment on a post
     * @param comment  the comment text value to be passed
     * @param postId   the id of the post that comment is added to
     * @return  the response of the api call to comment endpoint
     */
    public Response createComment(String comment, Integer postId){
        return  RestAssured.given().spec(requestSpecification)
                .header("Content-type", configFileReader.getJsonContentType())
                .body(createCommentPayload(comment, postId))
                .post(configFileReader.getCommentsUri());
    }

    public String createCommentPayload(String comment, Integer postId){
        JSONObject createCommentJsonObject = new JSONObject();
            createCommentJsonObject.put("postId", postId);
            createCommentJsonObject.put("body", comment);
        return createCommentJsonObject.toString();
    }

    public Response getAllComments() throws URISyntaxException {
        return makeApiCall(configFileReader.getCommentsUri());
    }

    public Response getCommentsOnPost(String postId) throws URISyntaxException {
        return given()
                .spec(requestSpecification)
                .param("postId", postId)
                .get(new URI(configFileReader.getCommentsUri()));
    }
}
