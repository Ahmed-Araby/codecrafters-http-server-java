package endpoints;

import models.Header;
import models.Request;
import utils.Constants;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class UserAgentEndpoint implements EndPoint{
    @Override
    public byte[] handle(Request request) {
        List<Header> headers = request.headers;
        final String userAgentValue = Stream.ofNullable(headers)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .filter(header -> Constants.Header.USER_AGENT.equals(header.key))
                .map(header -> header.value)
                .findAny()
                .orElse("");
        return ("HTTP/1.1 200 OK" + Constants.END_LINE +
                "Content-Length: " + userAgentValue.length() + Constants.END_LINE +
                "Content-type: text/plain" + Constants.END_LINE +
                Constants.END_LINE +
                userAgentValue).getBytes(StandardCharsets.UTF_8);
    }
}
