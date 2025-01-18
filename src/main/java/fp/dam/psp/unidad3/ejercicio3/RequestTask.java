package fp.dam.psp.unidad3.ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.Socket;
import java.time.LocalDateTime;

public class RequestTask implements Runnable {

    private final Socket socket;
    private String clientIP;
    private int clientPort;

    public RequestTask(Socket socket) {
        this.socket = socket;
        clientIP = socket.getInetAddress().getHostAddress();
        clientPort = socket.getPort();
    }

    @Override
    public void run() {
        try (socket) {
            System.out.printf("Petición : %s:%d : %s\n", clientIP, clientPort, LocalDateTime.now());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            for (; ;)
                out.writeUTF(in.readUTF());
        } catch (EOFException e) {
            System.out.printf("Respuesta : %s:%d : %s\n", clientIP, clientPort, LocalDateTime.now());
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
}
