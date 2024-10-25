package utils;

import models.Header;
import models.Request;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// [TODO] implement LazyRequestParser, it will be interesting to do the design for it.
public class EagerRequestParser implements Closeable
{
    BufferedReader lineReader;

    public Request parse(InputStream byteStream) throws IOException {
        final InputStreamReader charStream = new InputStreamReader(byteStream, StandardCharsets.UTF_8);
        lineReader = new BufferedReader(charStream);
        final String requestLine = lineReader.readLine();

        if (requestLine == null) {
            // I guess this was a tcp connection with empty payload
            return null;
        }

        final String[] requestLineTokens = requestLine.split(" "); // request line
        final List<Header> headers = this.parseHeaders(); // headers
        return new Request(requestLineTokens[0], requestLineTokens[1], requestLineTokens[2], headers);

    }

    private List<Header> parseHeaders() throws IOException {
        final List<Header> headers = new ArrayList<>();
        String line;
        while(true) {
            line = lineReader.readLine();
            if (line.equals("") || line == null) { // CRLF (\r\n) are being read as empty line
                break;
            }
            final String[] headerTokens = line.split(": ");
            headers.add(new Header(headerTokens[0].toLowerCase(), headerTokens[1]));
        }
        return headers;
    }

    @Override
    public void close() throws IOException {
        this.lineReader.close();
    }
}
