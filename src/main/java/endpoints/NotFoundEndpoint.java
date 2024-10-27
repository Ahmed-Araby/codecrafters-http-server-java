package endpoints;

import models.Request;
import models.Response;

import java.util.Collections;

public class NotFoundEndpoint implements EndPoint{
    @Override
    public Response handle(Request request) {
        return new Response(404, "Not Found", Collections.emptyList(), new byte[0]);
    }
}
