package endpoints;

import helpers.Constants;
import models.Request;

import java.nio.charset.StandardCharsets;

public class NotSupportedEndPoints implements EndPoint{
    @Override
    public byte[] handle(Request request) {
        return (Constants.HTTP_1_1 + " " +
                        "404 Not Found" + " " +
                        Constants.END_LINE + Constants.END_LINE).getBytes(StandardCharsets.UTF_8);
    }
}
