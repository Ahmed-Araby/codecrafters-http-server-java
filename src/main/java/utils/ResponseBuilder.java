package utils;

public class ResponseBuilder {

    static public String build(String code, String msg) {
        // would StringBuilder be more performant in such a simple scenario ?
        return "HTTP/1.1 " + code + " " + msg + Constants.END_LINE + Constants.END_LINE;
    }

    static public String build404() {
        return build("404", "Not Found");
    }

    static public String build200() {
        return build("200", "OK");
    }

    static public String buildWithBody(String body) {
        return "HTTP/1.1 200 OK" + Constants.END_LINE +
                "Content-Length: " + body.length() + Constants.END_LINE +
                "Content-Type: text/plain" + Constants.END_LINE +
                Constants.END_LINE +
                body;
    }
}
