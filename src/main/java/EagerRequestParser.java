import models.Request;

import java.io.*;
import java.nio.charset.StandardCharsets;

// [TODO] implement LazyRequestParser, it will be interesting to do the design for it.
public class EagerRequestParser implements Closeable
{
    BufferedReader lineReader;

    public Request parse(InputStream byteStream) throws IOException {
        final InputStreamReader charStream = new InputStreamReader(byteStream, StandardCharsets.UTF_8);
        lineReader = new BufferedReader(charStream);
        final String requestLine = lineReader.readLine();

        final String[] requestLineTokens = requestLine.split(" ");

        return new Request(requestLineTokens[0], requestLineTokens[1], requestLineTokens[2]);

    }

    @Override
    public void close() throws IOException {
        this.lineReader.close();
    }
}
