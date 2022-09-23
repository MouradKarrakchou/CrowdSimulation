package ihm;

import controller.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GUI {
    private static final int border = 800;
    private final JPanel[][] myGridPanel;

    public GUI(Grid grid) {
        /**
         * TODO IMPLEMENTATION DE RUNNABLE ?
         */
        myGridPanel = setupGridPanel(grid);

        JFrame frame = setupFrame();
        JPanel panel = setupMainPanel(grid);
        frame.setContentPane(panel);

        frame.setVisible(true);
    }

    JPanel setupMainPanel(Grid grid) {
        // Setup the grid inside a gridLayout;
        // Each case of the grid is a JPanel of the matrix
        GridLayout layout = new GridLayout(grid.getHeight(), grid.getWidth());
        JPanel panel = new JPanel(layout);

        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                panel.add(myGridPanel[i][j]);
            }
        }

        return panel;
    }

    JPanel[][] setupGridPanel(Grid grid) {
        JPanel[][] gridPanel = new JPanel[grid.getHeight()][grid.getWidth()];

        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                JPanel panel = new JPanel();
                panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel[i][j] = panel;
            }
        }

        return gridPanel;
    }

    JFrame setupFrame() {
        JFrame frame = new JFrame();
        frame.setSize(border, border);
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
        new GUI(new Grid(10,10));
    }

}
