package utils;

import endpoints.*;
import models.Request;

import java.util.regex.Pattern;

public class TargetSwitch {

    public static EndPoint getEndpoint(Request request) {

        switch (request.method) {
            case "GET":
                return getSwitch(request);
            case "POST":
                return postSwitch(request);
            default:
                return new NotFoundEndpoint(); // we should indicate that the action method is not supported
        }

    }

    public static EndPoint getSwitch(Request request) {
        final String[] tokens = Pattern.compile("/").split(request.target);
        if (tokens.length == 0) {
            return new IndexEndpoint(); // return index page
        }
        switch (tokens[1]) {
            case "echo":
                return new EchoEndpoint();
            case "user-agent":
                return new UserAgentEndpoint();
            case "files":
                return new FilesEndpoint();
            default:
                return new NotFoundEndpoint();
        }
    }

    public static EndPoint postSwitch(Request request) {
        final String[] tokens = Pattern.compile("/").split(request.target);
        switch (tokens[1]) {
            case "files":
                return new WriteFilesEndpoint();
            default:
                return new NotFoundEndpoint();
        }
    }
}
