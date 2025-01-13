package fp.dam.psp.unidad3.ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

public class RequestTask implements Runnable {

    private final Socket socket;

    public RequestTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (socket) {

            String s = new DataInputStream(socket.getInputStream()).readUTF();
            System.out.println("Petición : " + s + " : " +
                    socket.getInetAddress() + " : " + socket.getPort() +
                    " : " + LocalDateTime.now());
            new DataOutputStream(socket.getOutputStream()).writeUTF(s);
            System.out.println("respuesta : " + s + " : " +
                    socket.getLocalAddress() + " : " + socket.getLocalPort() +
                    " : " + LocalDateTime.now());
        } catch (Exception e) {

        }
    }
}
