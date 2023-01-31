package components;

import io.restassured.response.Response;

import java.net.URISyntaxException;

public class Users extends Base {
    public Response getAllUsers() throws URISyntaxException {
        return makeApiCall(configFileReader.getUsersUri());
    }
}
