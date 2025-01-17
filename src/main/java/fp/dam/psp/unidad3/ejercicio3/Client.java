package fp.dam.psp.unidad3.ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        final Socket socket = new Socket("localhost", 9000);
        try (socket) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            for(int i=0; i<10; i++)
                out.writeUTF("hola " + i);
            socket.shutdownOutput();
            for(;;);
        }
    }

}
