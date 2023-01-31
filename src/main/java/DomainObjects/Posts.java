package DomainObjects;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class Posts extends Base {
    public String createPostPayload( List<Map<String, String>> data){
        JSONObject createPostJsonObject = new JSONObject();
        for (Map<String, String> form : data) {
            createPostJsonObject.put(form.get("JsonKey"), form.get("JsonValue"));
        }
        return createPostJsonObject.toString();
    }
    public Response createPost(List<Map<String, String>> data){
        return RestAssured.given().spec(requestSpecification)
                .header("Content-type", "application/json; charset=UTF-8")
                .body(createPostPayload(data))
                .post("/posts");
    }

}
