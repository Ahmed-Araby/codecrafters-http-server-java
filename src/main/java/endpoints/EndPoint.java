package endpoints;

import models.Request;
import models.Response;

public interface EndPoint {
    Response handle(Request request);
}
