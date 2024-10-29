package compression;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Compressor {

    OutputStream compress(InputStream byteStream) throws IOException;
}
