package control.actions;

import control.Main;
import model.function.onevariablefunction.Function;
import model.numericalanalisys.ZeroCalculator;
import view.FunctionPanel;
import view.popup.ErrorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 10:28 PM
 */
public class CalculateZeroOfAction extends AbstractAction {
    Main main;
    FunctionPanel functionPanel;

    public CalculateZeroOfAction(Main main, FunctionPanel functionPanel) {
        this.main = main;
        this.functionPanel = functionPanel;

        initParameters();
    }

    private void initParameters() {
        super.putValue(Action.NAME, "Calcular Raíz");
        //ImageIcon imageIcon = new ImageIcon("src//algoritmos//tpa10//gui//icons//open.png");
        //Image smallIcon = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        //super.putValue(Action.SMALL_ICON, new ImageIcon(smallIcon));
        //super.putValue(Action.LARGE_ICON_KEY, imageIcon);
        super.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        super.putValue(Action.SHORT_DESCRIPTION, "Calcular raíz de la función");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Double x0 = functionPanel.getX0Value();
        if (x0.isNaN()) {
            return;
        }
        Function selectedFunction = functionPanel.getSelectedFunction();
        if (selectedFunction == null) {
            new ErrorFrame(new JFrame(), "Error", "Debe seleccionar una función");
            return;
        }
        Double result = ZeroCalculator.newtonRawsonMethod(selectedFunction, x0);
        if (result.isNaN()) {
            new ErrorFrame(new JFrame(), "Error", "Error matemático");
            return;
        }
        functionPanel.setZeroResult(result);

    }
}
