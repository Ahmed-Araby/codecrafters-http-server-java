package endpoints;

import models.Request;
import utils.Constants;

import java.nio.charset.StandardCharsets;

public class IndexEndpoint implements EndPoint {
    @Override
    public byte[] handle(Request request) {
        return ("HTTP/1.1 200 OK" + Constants.END_LINE + Constants.END_LINE).getBytes(StandardCharsets.UTF_8);
    }
}
