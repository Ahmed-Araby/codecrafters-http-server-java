import endpoints.EndPoint;
import models.Request;
import utils.EagerRequestParser;
import utils.TargetSwitch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Server {

    public static void serve(Socket socket) throws IOException {
        final InputStream byteStream = socket.getInputStream();
        final EagerRequestParser eagerReqParser = new EagerRequestParser();

        final Request request = eagerReqParser.parse(byteStream);
        System.out.println("Request : " + request);

        if(request == null) {
            eagerReqParser.close();
            return;
        }

        final EndPoint endPoint = TargetSwitch.getEndpoint(request);
        final byte[] responseBytes = endPoint.handle(request);
        System.out.println("response = " + new String(responseBytes));
        final OutputStream responseStream = socket.getOutputStream();
        responseStream.write(responseBytes);

        eagerReqParser.close();
        responseStream.close(); // would this be a problem ?
    }
}
