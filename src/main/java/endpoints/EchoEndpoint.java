package endpoints;

import models.EncodingSchemaE;
import models.Header;
import models.Request;
import models.Response;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class EchoEndpoint implements EndPoint {

    @Override
    public Response handle(Request request) {
        final String[] tokens = request.target.split("/");
        final String pathArgument = tokens[2];
        List<Header> headers = new ArrayList<>();
        headers.add(Header.of("Content-Length", String.valueOf(pathArgument.length())));
        headers.add(Header.of("Content-Type", "text/plain"));
        this.buildEncodingHeaderIfSupported(request).ifPresent(headers::add);

        return new Response(200, "OK", headers, pathArgument.getBytes(StandardCharsets.UTF_8));
    }

    private Optional<Header> buildEncodingHeaderIfSupported(Request request) {
        List<EncodingSchemaE> encodingSchemas = this.getAcceptedEncodingSchemas(request);
        return encodingSchemas.contains(EncodingSchemaE.GZIP) ?
                Optional.of(Header.of("Content-Encoding", EncodingSchemaE.GZIP.toString().toLowerCase())) :
                Optional.empty();
    }

    private List<EncodingSchemaE> getAcceptedEncodingSchemas(Request request) {
        return request.headers.stream()
                // [TODO] public enum for the std header names
                .filter(header -> "accept-encoding".equals(header.key))
                // [TODO] provide getter method in classes and use method reference instead of lambda expression
                .map(header -> header.value)
                .map(encodingSchemas -> encodingSchemas.split(","))
                .flatMap(Arrays::stream)
                .map(encodingSchema -> encodingSchema.trim())
                .map(EncodingSchemaE::constantOf)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
