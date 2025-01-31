package fp.dam.psp.unidad5.hashserver;

import java.io.*;
import java.net.Socket;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.Base64;

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
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String request = in.readUTF();
            if (request.equals("hash")) {
                MessageDigest md = MessageDigest.getInstance(in.readUTF());
                byte[] buffer = new byte[1024];
                int n;
                while ((n = in.read(buffer)) != -1)
                    md.update(buffer, 0, n);
                byte [] hash = md.digest();
                new DataOutputStream(socket.getOutputStream())
                        .writeUTF(Base64.getEncoder().encodeToString(hash));
            }
        } catch (IOException e) {
            error(e.getLocalizedMessage());
        } catch (NoSuchAlgorithmException e) {
            error(e.getLocalizedMessage());
        }
    }

    private void error(String s) {
        System.err.printf("ERROR: %s: %s\n", LocalTime.now(), s);
    }
}
