package fp.dam.psp.unidad3.ejercicio3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingClient {

    private JTextArea text;

    private void send(ActionEvent e) {
        // enviar el texto del JTextArea al servidor

        // mostrar la respuesta del servidor en el JTextArea

    }

    private void clear(ActionEvent e) {
        // borrar el contenido del JTextArea

        // recuperar foco en el JTextArea

    }

    private void iniciar() {
        JFrame frame = new JFrame("Cliente ECO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(new JScrollPane(text = new JTextArea(40, 80)));
        JPanel panel = new JPanel(new GridLayout(1, 0));
        panel.add(newButton("ENVIAR", this::send));
        panel.add(newButton("LIMPIAR", this::clear));
        contentPane.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel newButton(String text, ActionListener listener) {
        JPanel panel = new JPanel(new GridLayout(1,1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SwingClient()::iniciar);
    }

}