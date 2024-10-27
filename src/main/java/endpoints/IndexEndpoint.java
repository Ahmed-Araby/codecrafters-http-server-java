package endpoints;

import models.Request;
import models.Response;

import java.util.Collections;

public class IndexEndpoint implements EndPoint {
    @Override
    public Response handle(Request request) {
        return new Response(200, "OK", Collections.emptyList(), new byte[0]);
    }
}
