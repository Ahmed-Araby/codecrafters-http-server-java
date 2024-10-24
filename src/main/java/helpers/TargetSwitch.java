package helpers;

import endpoints.EndPoint;
import endpoints.IndexEndpoint;
import endpoints.NotSupportedEndPoints;
import models.Request;

public class TargetSwitch {

    public static EndPoint getEndpoint(Request request) {
        switch (request.target) {
//            case "echo":
//                return new EchoEndpoint();
            case "/":
                return new IndexEndpoint();
            default:
                return new NotSupportedEndPoints();
        }
    }
}
