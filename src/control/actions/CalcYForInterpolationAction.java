package control.actions;

import control.Main;
import model.function.onevariablefunction.Function;
import view.InterpolationPanel;
import view.popup.ErrorFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 1:02 PM
 */
public class CalcYForInterpolationAction extends AbstractAction{
    Main main;
                InterpolationPanel interpolationPanel;

                public CalcYForInterpolationAction(Main main, InterpolationPanel interpolationPanel) {
                    this.main = main;
                    this.interpolationPanel = interpolationPanel;

                    initParameters();
                }

                private void initParameters() {
                    super.putValue(Action.NAME, "Calcular f(x)");
                    //ImageIcon imageIcon = new ImageIcon(
                    //        new ImageIcon("icons/integrate-icon.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                    //Image smallIcon = imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                    //super.putValue(Action.SMALL_ICON, new ImageIcon(smallIcon));
                    //super.putValue(Action.LARGE_ICON_KEY, imageIcon);
                    super.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
                    super.putValue(Action.SHORT_DESCRIPTION, "Calcula la Y para un X dado");
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    Function function = main.getInterpolatedFunction();
                    Double x = interpolationPanel.getXForInterpolation();
                    if (x.isNaN()){
                        return;
                    }
                    if (function != null){
                        interpolationPanel.setYResultOfInterpolation(function.functionInX(x).doubleValue());
                    } else {
                        new ErrorFrame(new JFrame(), "Error", "Debe haberse realizado una interpolación");
                    }

                }
}
