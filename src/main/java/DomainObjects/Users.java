package DomainObjects;

import io.restassured.response.Response;

import java.net.URISyntaxException;

public class Users extends Base {
    public Response getAllUsers(String uri) throws URISyntaxException {
        return makeApiCall(uri);
    }
}
