package ihm;

import controller.Grid;

import javax.swing.*;
import java.util.Objects;

public class GUI {
    private static final int width = 1000;
    private static final int height = 800;

    public GUI(Grid grid) {
        /**
         * TODO IMPLEMENTATION DE RUNNABLE ?
         */
        JFrame frame = setupFrame();
        JPanel panel = setupMainPanel();

        frame.setVisible(true);
    }

    JPanel setupMainPanel() {
        JPanel panel = new JPanel();

        return panel;
    }

    JFrame setupFrame() {
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setResizable(true);
        frame.setTitle("Super Concurrence Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // try to get icon image
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("images\\icon.png")));
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // center frame
        frame.setLocationRelativeTo(null);

        return frame;
    }

    public static void main(String[] args) {
        new GUI(null);
    }

}
