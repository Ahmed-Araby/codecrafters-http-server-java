import endpoints.EndPoint;
import models.Request;
import models.Response;
import utils.request.EagerRequestParser;
import utils.response.ResponseProcessor;
import utils.response.ResponseSerializer;
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
        final Response response = endPoint.handle(request);
        ResponseProcessor.process(response, request);
        System.out.println("response = " + response);

        // [TODO] I think there should be a way to determine how much bytes to write at once, should this be application level concern ?
        final byte[] responseBytes = ResponseSerializer.serialize(response);
        final OutputStream responseStream = socket.getOutputStream();
        responseStream.write(responseBytes);

        // [TODO] think more about what to close and in what order, and what is the effect of closing something which is already closed
        eagerReqParser.close();
        responseStream.close(); // would this be a problem ?
    }
}
