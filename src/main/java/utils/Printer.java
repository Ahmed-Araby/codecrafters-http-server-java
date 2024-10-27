package utils;

import java.io.IOException;
import java.io.InputStream;

public class Printer {

    public static void printStream(InputStream byteStream) throws IOException {
        while(true) {
            if(byteStream.available() == 0) {
                break;
            }
            byte[] buf = new byte[1];
            byteStream.read(buf, 0, 1);
            System.out.println("buf char = " + new String(buf));
        }
    }
}
