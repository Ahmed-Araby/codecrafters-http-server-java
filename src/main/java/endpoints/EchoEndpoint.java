package endpoints;

import models.Header;
import models.Request;
import models.Response;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class EchoEndpoint implements EndPoint {

    @Override
    public Response handle(Request request) {
        final String[] tokens = request.target.split("/");
        final String pathArgument = tokens[2];

        List<Header> headers = new ArrayList<>();
        headers.add(Header.of("Content-Length", String.valueOf(pathArgument.length())));
        headers.add(Header.of("Content-Type", "text/plain"));

        return new Response(200, "OK", headers, pathArgument.getBytes(StandardCharsets.UTF_8));
    }
}
