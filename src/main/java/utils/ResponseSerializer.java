package utils;

import models.Response;

import java.nio.charset.StandardCharsets;

public class ResponseSerializer {

    public static byte[] serialize(Response response) {
        final String headers = response.headers.stream()
                .map(header -> header.key + ": " + header.value + Constants.END_LINE)
                .reduce(String::concat)
                .orElse("");

        final byte[] partialResponseBytes = (
                "HTTP/1.1 " + response.statusCode + " " + response.statusText + Constants.END_LINE +
                headers +
                Constants.END_LINE).getBytes(StandardCharsets.UTF_8);

        return ArrayUtils.concat(partialResponseBytes, response.body);
    }
}
