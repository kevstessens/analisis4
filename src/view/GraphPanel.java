package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * User: Javier Isoldi
 * Date: 12/16/12
 * Time: 1:20 PM
 */
public class GraphPanel extends JPanel {
    double xMax, xMin, yMax, yMin;
    Point2D.Double center;


    public void paintComponent(Graphics g) {

// fill with the color you want
        int wide = getWidth();
        int tall = getHeight();
        g.setColor(Color.white);
        g.fillRect(0, 0, wide, tall);

// go into Graphics2D for all the fine art, more options
// optional, here I just get variable Stroke sizes
        Graphics2D g2 = (Graphics2D) g;
        int w = wide / 10;
        int h = tall / 10;
        g2.setColor(Color.cyan);

        g2.setStroke(new BasicStroke(1));
// the verticles
        for (int i = 1; i < 10; i++) {
            g2.drawLine(i * w, 0, i * w, tall);
        }
// the horizontals
        for (int i = 1; i < 10; i++) {
            g2.drawLine(0, i * h, wide, i * h);
        }

// that will have a little glitch with the integer math
// the grid will have the bottom row slightly larger
// to overcome that, you must use double

        g2.setColor(Color.red);
        double rowH = getHeight() / 10.0;
        for (int i = 1; i < 10; i++) {
            Line2D line = new Line2D.Double(0.0, (double) i * rowH,
                    (double) getWidth(), (double) i * rowH);
            g2.draw(line);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GraphPanel graphPanel = new GraphPanel();
        frame.add(graphPanel);
        graphPanel.setPreferredSize(new Dimension(200,200));
        graphPanel.repaint();
        frame.setVisible(true);
    }

}
