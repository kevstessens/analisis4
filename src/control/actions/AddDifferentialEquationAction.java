package control.actions;

import control.Main;
import model.function.NonValidExpressionError;
import model.function.twovariablefunction.FunctionParser;
import view.DifferentialEquationPanel;
import view.popup.ErrorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 1:32 AM
 */
public class AddDifferentialEquationAction extends AbstractAction {
    Main main;
        DifferentialEquationPanel panel;
        FunctionParser parser;

        public AddDifferentialEquationAction(Main main, DifferentialEquationPanel functionPanel) {
            this.main = main;
            this.panel = functionPanel;
            parser = new FunctionParser();

            initParameters();
        }

        private void initParameters() {
                super.putValue(Action.NAME,"Agregar");
//                ImageIcon imageIcon = new ImageIcon("icons/add-function-icon.png");
                //Image smallIcon = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                //super.putValue(Action.SMALL_ICON, new ImageIcon(smallIcon));
//                super.putValue(Action.LARGE_ICON_KEY, imageIcon);
                super.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
                super.putValue(Action.SHORT_DESCRIPTION,"Agregar la ecuación escrita arriba");

            }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                main.addTwoVariablesFunction(parser.getFunctionFromMathExpression(panel.getFunctionFieldText()));
                panel.updateList(main.getAllTwoVariableFunctions());
            } catch (NonValidExpressionError nonValidExpressionError) {
                new ErrorFrame(new JFrame(), "Error", "Expresión inválida");
                panel.setCaretPosInFunctionField(nonValidExpressionError.getNonValidPositionInExpression());
            }
        }
}
