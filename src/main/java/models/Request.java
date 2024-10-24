package models;

import java.util.List;

public class Request {
    public String method;
    public String target;
    public String protocol;
    public List<Header> headers;
    public String body;


    public Request(String method, String target, String protocol) {
        this.method = method;
        this.target = target;
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return "method= " + method + "," +
                "target = " + target + "," +
                "protocol = " + protocol;
    }
}
