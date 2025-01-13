package fp.dam.psp.unidad3.ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket("localhost", 9000);
        try (socket) {
            new DataOutputStream(socket.getOutputStream()).writeUTF("hola");
            String s = new DataInputStream(socket.getInputStream()).readUTF();
            System.out.println(s);
        }
    }

}
