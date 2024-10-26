package endpoints;

import models.Request;
import utils.Configs;
import utils.Constants;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FilesEndpoint implements EndPoint{
    @Override
    public byte[] handle(Request request) {
        final String[] targetTokens = request.target.split("/");
        final String absPath = Configs.FILES_ABSOLUTE_PATH + "/" + targetTokens[2];

        try {
            final FileInputStream fInStr = new FileInputStream(absPath);
            final byte[] responseBodyBytes = fInStr.readAllBytes();

            return ("HTTP/1.1 200 OK" + Constants.END_LINE +
                    "Content-Length: " + responseBodyBytes.length + Constants.END_LINE +
                    // based on the content type the client (e.g. browser) decide how to deal with the response content
                    // e.x: if the content type application/octet-stream -> the client will download the response body / content as file
                    // if the content type text/html the browser will render the content as html
                    // if the content-type header is text/plain the browser will display the content / response body as it is (normal text without special meaning for the html tags) without special rendering.
                    "Content-TypE: application/octet-stream" + Constants.END_LINE +
                    Constants.END_LINE +
                    // this is very stupid, actually I need to find a way to make the end points write bytes directly to the output stream, to avoid constructing the whole response at once
                    new String(responseBodyBytes, StandardCharsets.UTF_8)
            ).getBytes(StandardCharsets.UTF_8);

        } catch (FileNotFoundException exc) {
            return ("HTTP/1.1 404 Not Found" + Constants.END_LINE + Constants.END_LINE).getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
