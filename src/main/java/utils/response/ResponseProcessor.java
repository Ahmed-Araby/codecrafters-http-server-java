package utils.response;

import compression.GzipCompressor;
import models.EncodingSchemaE;
import models.Header;
import models.Request;
import models.Response;
import utils.HeaderUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ResponseProcessor {

    public static void process(Response response, Request request) {
        System.out.println("process response");
        compressBody(response, request);

    }

    private static void compressBody(Response response, Request request) {
        final List<EncodingSchemaE> encodingSchemas = HeaderUtils.getAcceptedEncodingSchemas(request.headers);

        encodingSchemas.stream()
                .filter(EncodingSchemaE.GZIP::equals)
                .findFirst()
                .ifPresent((encodingSchemaE) -> {
                    response.headers.add(Header.of("Content-Encoding", encodingSchemaE.toString()));
                    try {
                        final ByteArrayOutputStream byteArrayOutputStream =
                                (ByteArrayOutputStream) new GzipCompressor().compress(new ByteArrayInputStream(response.body));
                        response.body = byteArrayOutputStream.toByteArray();
                        HeaderUtils.updateHeaderValue(response.headers, "Content-Length", String.valueOf(byteArrayOutputStream.size()));
                    } catch (IOException e) {
                        System.out.println("failed to compress response data using gZIP");
                        throw new RuntimeException(e);
                    }
                });
    }

}
