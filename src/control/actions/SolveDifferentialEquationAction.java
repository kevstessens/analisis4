package control.actions;

import control.Main;
import model.function.twovariablefunction.TwoVariableFunction;
import model.numericalanalisys.DifferentialEquationCalculator;
import view.DifferentialEquationPanel;
import view.popup.ErrorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 1:29 AM
 */
public class SolveDifferentialEquationAction extends AbstractAction {
    Main main;
    DifferentialEquationPanel panel;

    public SolveDifferentialEquationAction(Main main, DifferentialEquationPanel panel) {
        this.main = main;
        this.panel = panel;
        initParameters();
    }

    private void initParameters() {
        super.putValue(Action.NAME, "Resolver ecuación");
        //ImageIcon imageIcon = new ImageIcon(
        //new ImageIcon("icons/integrate-icon.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        //Image smallIcon = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        //super.putValue(Action.SMALL_ICON, new ImageIcon(smallIcon));
        //super.putValue(Action.LARGE_ICON_KEY, imageIcon);
        super.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        super.putValue(Action.SHORT_DESCRIPTION, "Calcula los valores de Y para un X dado");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Double x0 = panel.getX0Value();
        if (x0.isNaN()) {
            return;
        }
        Double y0 = panel.getY0Value();
        if (x0.isNaN()) {
            return;
        }
        Double step = panel.getStepValue();
        if (step.isNaN()) {
            return;
        }
        Integer n = panel.getNumberOfResultValue();
        if (n <= 0) {
            new ErrorFrame(new JFrame(), "Error", "Para debe ser un numero positivo");
            return;
        }
        TwoVariableFunction selectedFunction = panel.getSelectedFunction();
        if (selectedFunction == null) {
            new ErrorFrame(new JFrame(), "Error", "debe seleccionarse una ecuación");
            return;
        }
        double[][] result = DifferentialEquationCalculator.fourthOrderRungeKuttaMethod(selectedFunction,
                x0, y0, step, n);
        panel.updateTableResults(result);
    }
}
