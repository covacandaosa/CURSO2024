package fp.dam.psp.unidad3.ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 9000)) {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            for(int i=0; i<10; i++)
                out.writeUTF("hola " + i);
            socket.shutdownOutput();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            try {
                for (; ; )
                    System.out.println(in.readUTF());
            } catch (EOFException e) {
            }
        }
    }

}
