package ihm;

import javax.swing.*;
import java.awt.*;

public class GridBoxUI extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillOval(0,0,10,10);
    }
}
