package endpoints;

import models.Request;
import utils.Constants;

import java.nio.charset.StandardCharsets;

public class BodyEndpoint implements EndPoint{
    @Override
    public byte[] handle(Request request) {
        final String[] tokens = request.target.split("/");
        final String body = tokens[2];
        return (
                "HTTP/1.1 200 OK" + Constants.END_LINE +
                        "Content-Length: " + body.length() + Constants.END_LINE +
                        "Content-Type: text/plain" + Constants.END_LINE +
                        Constants.END_LINE +
                        body
                ).getBytes(StandardCharsets.UTF_8);
    }
}
