package endpoints;

import models.Request;
import utils.Constants;

import java.nio.charset.StandardCharsets;

public class NotFoundEndpoint implements EndPoint{
    @Override
    public byte[] handle(Request request) {
        return ("HTTP/1.1 404 Not Found" + Constants.END_LINE + Constants.END_LINE).getBytes(StandardCharsets.UTF_8);

    }
}
