package fp.dam.psp.unidad5.hashserver;

import java.io.Console;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;

public class RequestHandler implements Runnable {

    private final Socket socket;
    private String clientIP;
    private int clientPort;

    public RequestHandler(Socket socket) {
        this.socket = socket;
        clientIP = socket.getInetAddress().getHostAddress();
        clientPort = socket.getPort();
    }

    @Override
    public void run() {
        try (socket) {

        } catch (IOException e) {
            error(e.getLocalizedMessage());
        }
    }

    private void error(String s) {
        System.err.printf("ERROR: %s: %s\n", LocalTime.now(), s);
    }
}
