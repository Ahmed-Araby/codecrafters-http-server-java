package compression;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GzipCompressor implements Compressor {

    @Override
    public OutputStream compress(InputStream byteStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);

        while(true) {
            final byte[] buf = byteStream.readNBytes(1000); // [TODO] use constant
            if (buf.length == 0) {
                break;
            }
            gzipOutputStream.write(buf, 0, buf.length);
        }
        gzipOutputStream.close();

        return byteArrayOutputStream;
    }
}
