package endpoints;

import models.Header;
import models.Request;
import models.Response;
import configs.Configs;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilesEndpoint implements EndPoint{
    @Override
    public Response handle(Request request) {
        final String[] targetTokens = request.target.split("/");
        final String absPath = Configs.FILES_ABSOLUTE_PATH + "/" + targetTokens[2];

        try {
            final FileInputStream fInStr = new FileInputStream(absPath);
            final byte[] responseBodyBytes = fInStr.readAllBytes();
            List<Header> headers = new ArrayList<>();
            headers.add(Header.of("Content-Length", String.valueOf(responseBodyBytes.length)));
            headers.add(Header.of("content-TypE", "application/octet-stream"));
            return new Response(200, "OK", headers, responseBodyBytes);
        } catch (FileNotFoundException exc) {
            return new Response(404, "Not Found", Collections.emptyList(), new byte[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
