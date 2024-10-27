package endpoints;

import models.Header;
import models.Request;
import models.Response;
import utils.Constants;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class UserAgentEndpoint implements EndPoint{
    @Override
    public Response handle(Request request) {
        List<Header> headers = request.headers;
        final String userAgentValue = Stream.ofNullable(headers)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .filter(header -> Constants.Header.USER_AGENT.equals(header.key))
                .map(header -> header.value)
                .findAny()
                .orElse("");
        headers.add(Header.of("Content-Length", String.valueOf(userAgentValue.length())));
        headers.add(Header.of("content-TypE", "text/plain"));
        return new Response(200, "OK", headers, userAgentValue.getBytes(StandardCharsets.UTF_8));
    }
}
