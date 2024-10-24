import endpoints.EndPoint;
import helpers.EagerRequestParser;
import helpers.TargetSwitch;
import models.Request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");

//     Uncomment this block to pass the first stage

     try {
       ServerSocket serverSocket = new ServerSocket(4221);

       // Since the tester restarts your program quite often, setting SO_REUSEADDR
       // ensures that we don't run into 'Address already in use' errors
       serverSocket.setReuseAddress(true);

       final Socket socket = serverSocket.accept(); // Wait for connection from client.
       System.out.println("accepted new connection");

       final InputStream byteStream = socket.getInputStream();
       final EagerRequestParser eagerReqParser = new EagerRequestParser();

       final Request request = eagerReqParser.parse(byteStream);
       System.out.println(request);

       final EndPoint endPoint = TargetSwitch.getEndpoint(request);
       final byte[] responseBytes = endPoint.handle(request);

       final OutputStream responseStream = socket.getOutputStream();
       responseStream.write(responseBytes);

       eagerReqParser.close();
       responseStream.close(); // would this be a problem

     } catch (IOException e) {
       System.out.println("IOException: " + e.getMessage());
     }
  }
}
