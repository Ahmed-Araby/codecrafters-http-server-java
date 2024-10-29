package utils.request;

import models.Header;
import models.Request;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// [TODO] implement LazyRequestParser, it will be interesting to do the design for it.
public class EagerRequestDeserializer implements Closeable
{
    BufferedReader lineReader;

    public Request deserialize(InputStream byteStream) throws IOException {
        final InputStreamReader charStream = new InputStreamReader(byteStream, StandardCharsets.UTF_8);
        lineReader = new BufferedReader(charStream);
        final String requestLine = lineReader.readLine();
        // [NOTE] if we tried to consume directly from the underlying streams (i.e. the byte stream or the char stream)
        // it might be a problem because the wrapper stream (i.e. the Buffered Reader) might have consumed
        // the underlying streams and buffered the data in the internal buffer,
        // hence there will be no data in the underlying streams to consume

        if (requestLine == null) {
            // I guess this was a tcp connection with empty payload
            return null;
        }

        final String[] requestLineTokens = requestLine.split(" "); // request line
        final List<Header> headers = this.deserializeHeaders(); // headers
        final byte[] body = this.deserializeBody(headers);
        return new Request(requestLineTokens[0], requestLineTokens[1], requestLineTokens[2], headers, body);

    }

    private List<Header> deserializeHeaders() throws IOException {
        final List<Header> headers = new ArrayList<>();
        String line;
        while(true) {
            line = lineReader.readLine();
            if (line.equals("") || line == null) { // CRLF (\r\n) is being ignore as it represent the line
                // separator / terminator, hence the extracted line is what comes before the CRLF with is empty line
                break;
            }
            final String[] headerTokens = line.split(": ");
            headers.add(new Header(headerTokens[0].toLowerCase(), headerTokens[1]));
        }
        return headers;
    }

    private byte[] deserializeBody(List<Header> headers) throws IOException {
        // [TODO] it is stupid that we extract chars then convert to bytes,
        // and I think the design issue is that we depend on BufferedReader to parse the request,
        // instead we should have operated on the byte level till we build the request.

        int contentLength = this.getContentLength(headers);
        final char[] buf = new char[contentLength];
        int bufLength = 0;
        while(bufLength < contentLength) {
            int bytesCnt = lineReader.read(buf, bufLength, contentLength);
            bufLength += bytesCnt;
        }

        return new String(buf).getBytes(StandardCharsets.UTF_8);
    }

    private int getContentLength(List<Header> headers) {
        return headers.stream()
                .filter(header -> "content-length".equals(header.key))
                .map(header -> header.value)
                .map(Integer::valueOf)
                .findFirst()
                .orElse(0);
    }

    @Override
    public void close() throws IOException {
        this.lineReader.close();
    }
}
