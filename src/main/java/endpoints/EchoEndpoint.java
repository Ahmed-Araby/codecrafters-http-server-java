package endpoints;

import models.EncodingSchemaE;
import models.Request;
import utils.Constants;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EchoEndpoint implements EndPoint {

    @Override
    public byte[] handle(Request request) {
        final String[] tokens = request.target.split("/");
        final String pathArgument = tokens[2];
        return (
                "HTTP/1.1 200 OK" + Constants.END_LINE +
                        "Content-Length: " + pathArgument.length() + Constants.END_LINE +
                        "Content-Type: text/plain" + Constants.END_LINE +
                        this.buildEncodingHeaderIfSupported(request) +
                        Constants.END_LINE +
                        pathArgument
                ).getBytes(StandardCharsets.UTF_8);
    }

    private String buildEncodingHeaderIfSupported(Request request) {
        List<EncodingSchemaE> encodingSchemas = this.getAcceptedEncodingSchemas(request);
        return encodingSchemas.contains(EncodingSchemaE.GZIP) ?
                "Content-Encoding: " + EncodingSchemaE.GZIP.toString().toLowerCase() + Constants.END_LINE:
                "";
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
