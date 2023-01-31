package components;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigFileReader;

import java.net.URI;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;

public class Base {
    public RequestSpecification requestSpecification;
    ConfigFileReader configFileReader;
    public Base(){
        configFileReader = new ConfigFileReader();
        String BASE_URL = configFileReader.getValuefromKey("BaseUrl");
        requestSpecification = new RequestSpecBuilder().setBaseUri(BASE_URL).build();
    }

    public Response makeApiCall(String uri) throws URISyntaxException {
        return given()
                .spec(requestSpecification)
                .get(new URI(uri));
    }
   
}
