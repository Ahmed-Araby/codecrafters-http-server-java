package models;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Request {
    public String method;
    public String target;
    public String protocol;
    public List<Header> headers;
    public byte[] body;


    public Request(String method, String target, String protocol, List<Header> headers, byte[] body) {
        this.method = method;
        this.target = target;
        this.protocol = protocol;
        this.headers = headers;
        this.body = body;
    }

    @Override
    public String toString() {
        return "method= " + method + "," +
                "target = " + target + "," +
                "protocol = " + protocol + "," +
                "headers[] = " + headers +
                "body = " + new String(body, StandardCharsets.UTF_8);
    }
}
