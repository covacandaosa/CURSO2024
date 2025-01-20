package fp.dam.psp.unidad3.ejercicio3.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class MainPanel extends JPanel {

    private final JButton sendButton = new JButton("ENVIAR");
    static final Font font;
    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, MainPanel.class.getResourceAsStream("/comic shanns 2.ttf")).deriveFont(21f);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private final Text text = new Text(30, 80, sendButton, font);

    public MainPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(new JScrollPane(text));
        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(sendButton);
        sendButton.setFont(font);
        sendButton.addActionListener(this::send);
        add(panel);
    }

    private void send(ActionEvent evt) {
        sendButton.setEnabled(false);
        new Thread(this::request).start();
    }

    private void request() {
        StringBuilder sb = new StringBuilder();
        try (Socket socket = new Socket("localhost", 9000)) {
            socket.setSoTimeout(5000);
            new DataOutputStream(socket.getOutputStream()).writeUTF(text.getText());
            socket.shutdownOutput();
            sb.append("ECHO\n");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            for (;;) {
                sb.append(in.readUTF());
                sb.append("\n");
            }
        } catch (EOFException e) {
            SwingUtilities.invokeLater(() -> text.setResponse(sb.toString()));
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> text.setError("ERROR: " + e.getLocalizedMessage()));
        }
    }
}
