package fp.dam.psp.unidad3.ejercicio3.swing;

import javax.swing.*;

public class SwingClient {

    private void iniciar() {
        JFrame frame = new JFrame("Cliente ECO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MainPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SwingClient()::iniciar);
    }

}