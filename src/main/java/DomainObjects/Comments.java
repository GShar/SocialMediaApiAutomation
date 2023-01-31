package DomainObjects;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;

public class Comments extends Base {
    public Response createComment(String comment, Integer postId){
        return  RestAssured.given().spec(requestSpecification)
                .header("Content-type", "application/json; charset=UTF-8")
                .body(createCommentPayload(comment, postId))
                .post("/posts");
    }
    public String createCommentPayload(String comment, Integer postId){
        JSONObject createCommentJsonObject = new JSONObject();
            createCommentJsonObject.put("postId", postId);
            createCommentJsonObject.put("body", comment);
        return createCommentJsonObject.toString();
    }
    public Response getAllComments() throws URISyntaxException {
        return makeApiCall("/comments");
    }

    public Response getCommentsOnPost(String postId) throws URISyntaxException {
        return given()
                .spec(requestSpecification)
                .param("postId", postId)
                .get(new URI("/comments"));
    }
}
