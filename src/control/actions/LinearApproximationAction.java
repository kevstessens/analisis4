package control.actions;

import control.Main;
import model.function.onevariablefunction.Function;
import model.numericalanalisys.ApproximationCalculator;
import model.numericalanalisys.NonValidDataSetException;
import view.InterpolationPanel;
import view.popup.ErrorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 12:57 PM
 */
public class LinearApproximationAction extends AbstractAction {
    Main main;
        InterpolationPanel interpolationPanel;

        public LinearApproximationAction(Main main, InterpolationPanel interpolationPanel) {
            this.main = main;
            this.interpolationPanel = interpolationPanel;

            initParameters();
        }

        private void initParameters() {
            super.putValue(Action.NAME, "Generar Aproximación lineal");
            //ImageIcon imageIcon = new ImageIcon(
            //        new ImageIcon("icons/integrate-icon.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            //Image smallIcon = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            //super.putValue(Action.SMALL_ICON, new ImageIcon(smallIcon));
            //super.putValue(Action.LARGE_ICON_KEY, imageIcon);
            super.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
            super.putValue(Action.SHORT_DESCRIPTION, "Genera una aproximación lineal para los puntos dados");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object[][] points = interpolationPanel.getPoints();
            int rows = points.length;
            int columns = points[0].length;
            if (rows >= 3) {
                double[][] values = new double[rows - 1][columns];
                try {
                    for (int i = 0; i < columns; i++) {
                        for (int j = 0; j < rows - 1; j++) {
                            values[j][i] = Double.valueOf((points[j][i]).toString());
                        }
                    }

                    Function function = ApproximationCalculator.linealApproximation(values);
                    interpolationPanel.setResultOfLinearApproximation(function);
                    main.setLinearFunction(function);
                } catch (NumberFormatException ex) {
                    new ErrorFrame(new JFrame(), "Error", "Todos los puntos deben ser numeros");
                } catch (NonValidDataSetException e1) {
                    new ErrorFrame(new JFrame(), "Error", "Deben existir al menos dos puntos");
                }
            } else {
                new ErrorFrame(new JFrame(), "Error", "Deben existir al menos dos puntos");
            }
        }
}
