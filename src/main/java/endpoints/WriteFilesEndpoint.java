package endpoints;

import models.Request;
import utils.Configs;
import utils.Constants;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WriteFilesEndpoint implements EndPoint{
    @Override
    public byte[] handle(Request request) {
        final String fileName = request.target.split("/")[2];
        final String fileAbsPath = Configs.FILES_ABSOLUTE_PATH + "/" + fileName;
        System.out.println("fileName = " + fileName);

        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(fileAbsPath);
            fileOutputStream.write(request.body);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
        return ("HTTP/1.1 201 Created" + Constants.END_LINE + Constants.END_LINE).getBytes(StandardCharsets.UTF_8);
    }
}
