package models;

import java.util.List;

public class Response {
    public int statusCode;
    public String statusText;
    public List<Header> headers;
    public byte[] body;

    public Response(int statusCode, String statusText, List<Header> headers, byte[] body) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.headers = headers;
        this.body = body;
    }

    @Override
    public String toString() {
        return "statusCode = " + statusCode + ", " +
                "statusText = " + statusText +  ", " +
                "headers[] = " + headers + ", " +
                "body = " + new String(body);
    }
}
