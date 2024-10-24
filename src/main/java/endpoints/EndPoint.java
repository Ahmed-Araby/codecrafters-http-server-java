package endpoints;
import models.Request;

public interface EndPoint {
    byte[] handle(Request request);
}
