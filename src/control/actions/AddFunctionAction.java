package control.actions;

import control.Main;
import model.function.NonValidExpressionError;
import model.function.onevariablefunction.FunctionParser;
import view.FunctionPanel;
import view.popup.ErrorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 10:13 PM
 */
public class AddFunctionAction extends AbstractAction {
    Main main;
    FunctionPanel functionPanel;
    FunctionParser parser;

    public AddFunctionAction(Main main, FunctionPanel functionPanel) {
        this.main = main;
        this.functionPanel = functionPanel;
        parser = new FunctionParser();

        initParameters();
    }

    private void initParameters() {
            super.putValue(Action.NAME,"Agregar");
//            ImageIcon imageIcon = new ImageIcon("icons/add-function-icon.png");
            //Image smallIcon = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            //super.putValue(Action.SMALL_ICON, new ImageIcon(smallIcon));
//            super.putValue(Action.LARGE_ICON_KEY, imageIcon);
            super.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
            super.putValue(Action.SHORT_DESCRIPTION,"Agregar la ecuación escrita arriba");

        }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            main.addFunction(parser.getFunctionFromMathExpression(functionPanel.getFunctionFieldText()));
            functionPanel.updateList(main.getAllFunctions());
        } catch (NonValidExpressionError nonValidExpressionError) {
            new ErrorFrame(new JFrame(), "Error", "Expresión inválida");
            functionPanel.setCaretPosInFunctionField(nonValidExpressionError.getNonValidPositionInExpression());
        }
    }
}
