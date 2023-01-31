package components;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * This Posts class wraps the generic functions for the Posts resource endpoint
 * E.g. Create a post
 */
public class Posts extends Base {
    public String createPostPayload(String body, String title, int userId){
        JSONObject createPostJsonObject = new JSONObject();
        createPostJsonObject.put("body", body);
        createPostJsonObject.put("title", title);
        createPostJsonObject.put("userId", userId);
        return createPostJsonObject.toString();
    }
    public Response createPost(String body, String title, int userId){
        return RestAssured.given().spec(requestSpecification)
                .header("Content-type", "application/json; charset=UTF-8")
                .body(createPostPayload(body, title, userId))
                .post(configFileReader.getPostsUri());
    }

    public Response getAllPosts() throws URISyntaxException {
        return makeApiCall(configFileReader.getPostsUri());
    }

}
