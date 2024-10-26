import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) throws IOException {
      // You can use print statements as follows for debugging, they'll be visible when running tests.
      System.out.println("Logs from your program will appear here!");

      // Since the tester restarts your program quite often, setting SO_REUSEADDR
      // ensures that we don't run into 'Address already in use' errors

      ServerSocket serverSocket = new ServerSocket(4221);
      serverSocket.setReuseAddress(true);
      final ExecutorService executorService = Executors.newCachedThreadPool();

      while(true) {
          try {
              final Socket socket = serverSocket.accept(); // Wait for connection from client.
              System.out.println("accepted new connection");
              executorService.execute(() -> {
                  try {
                      Server.serve(socket);
                  } catch (IOException e) {
                      throw new RuntimeException(e);
                  }
              });
          } catch (IOException e) {
              System.out.println("IOException: " + e.getMessage());
          }
      }
  }
}
