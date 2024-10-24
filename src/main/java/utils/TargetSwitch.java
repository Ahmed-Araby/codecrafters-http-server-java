package utils;

import endpoints.BodyEndpoint;
import endpoints.EndPoint;
import endpoints.IndexEndpoint;
import endpoints.NotFoundEndpoint;
import models.Request;

import java.util.regex.Pattern;

public class TargetSwitch {

    public static EndPoint getEndpoint(Request request) {
        final String[] tokens = Pattern.compile("/").split(request.target);
        if (tokens.length == 0) {
            return new IndexEndpoint(); // return index page
        }
        switch (tokens[1]) {
            case "echo":
                return new BodyEndpoint();
            default:
                return new NotFoundEndpoint();
        }
    }
}
