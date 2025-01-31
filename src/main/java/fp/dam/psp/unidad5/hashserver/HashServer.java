package fp.dam.psp.unidad5.hashserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HashServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9000);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        System.out.println("Servidor ECO escuchando en puerto 9000");
        while (true) {
            Socket socket = server.accept();
            socket.setSoTimeout(5000);
            executorService.submit(new RequestHandler(socket));
        }
    }
}
