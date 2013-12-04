package control.actions;

import control.Main;
import model.function.onevariablefunction.Function;
import model.numericalanalisys.IntegralCalculator;
import view.FunctionPanel;
import view.popup.ErrorFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 10:24 PM
 */
public class IntegrateFunctionAction extends AbstractAction {
    Main main;
    FunctionPanel functionPanel;

    public IntegrateFunctionAction(Main main, FunctionPanel functionPanel) {
        this.main = main;
        this.functionPanel = functionPanel;

        initParameters();
    }

    private void initParameters() {
//        super.putValue(Action.NAME, "Integrar");
        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon("icons/integrate-icon.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
        //Image smallIcon = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        //super.putValue(Action.SMALL_ICON, new ImageIcon(smallIcon));
        super.putValue(Action.LARGE_ICON_KEY, imageIcon);
        super.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
//        super.putValue(Action.SHORT_DESCRIPTION, "Integrar la función ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Double from = functionPanel.getIntegrateFromValue();
        if (from.isNaN()){
            return;
        }
        Double to = functionPanel.getIntegrateToValue();
        if (to.isNaN()){
            return;
        }

        Function selectedFunction = functionPanel.getSelectedFunction();
        if (selectedFunction == null){
            new ErrorFrame(new JFrame(),"Error", "Debe seleccionarse una función");
            return;
        }
        Double result = IntegralCalculator.rombergMethod(selectedFunction, from, to, 5);
        if( result.isNaN()){
            new ErrorFrame(new JFrame(),"Error", "Error matemático");
            return;
        }
        functionPanel.setIntegrationResult(result);
    }
}
