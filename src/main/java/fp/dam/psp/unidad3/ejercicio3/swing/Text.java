package fp.dam.psp.unidad3.ejercicio3.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Text extends JTextArea {

    private final Color responseColor = new Color(0xCD, 0xEB, 0xC5);
    private final Color errorColor = new Color(0xfd, 0xaa, 0xaa);
    private final JButton sendButton;

    public Text(int rows, int columns, JButton sendButton, Font font) {
        super(rows, columns);
        this.sendButton = sendButton;
        setFont(font);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!isEditable()) {
                    setText("");
                    setBackground(Color.WHITE);
                    setEditable(true);
                    sendButton.setEnabled(true);
                    requestFocus();
                }
                else if (e.getKeyChar() == '\n' && e.isControlDown()) {
                    sendButton.doClick();
                }
            }
        });
    }

    public void setResponse(String response) {
        setText(response);
        setEditable(false);
        setBackground(responseColor);
        requestFocus();
    }

    public void setError(String error) {
        setText(error);
        setEditable(false);
        setBackground(errorColor);
        requestFocus();
    }

}
