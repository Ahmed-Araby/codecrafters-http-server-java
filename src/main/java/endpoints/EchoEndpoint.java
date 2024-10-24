package endpoints;
import models.Request;

import java.nio.charset.StandardCharsets;

public class EchoEndpoint implements EndPoint{

    @Override
    public byte[] handle(Request request) {
        final String target = request.target;
        final String pathArg = target.split("/")[1];
        return pathArg.getBytes(StandardCharsets.UTF_8);
    }
}
