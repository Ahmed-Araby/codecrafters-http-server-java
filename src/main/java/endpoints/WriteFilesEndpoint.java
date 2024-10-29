package endpoints;

import models.Request;
import models.Response;
import configs.Configs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;

public class WriteFilesEndpoint implements EndPoint{
    @Override
    public Response handle(Request request) {
        final String fileName = request.target.split("/")[2];
        final String fileAbsPath = Configs.FILES_ABSOLUTE_PATH + "/" + fileName;
        System.out.println("fileName = " + fileName);

        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(fileAbsPath);
            fileOutputStream.write(request.body);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
        return new Response(201, "Created", Collections.emptyList(), new byte[0]);
    }
}
