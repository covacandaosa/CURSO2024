package fp.dam.psp.unidad3.ejercicio3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEcho {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            Socket socket = serverSocket.accept();
            // crear un hilo pasándole el socket que atienda la petición
        }

    }

}
