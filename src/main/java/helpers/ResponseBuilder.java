package helpers;

// [TODO] to be deleted
@Deprecated
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
}
