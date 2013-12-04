package view.popup;

import javax.swing.*;
import java.awt.*;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 11:34 PM
 */
public class ErrorFrame extends JDialog {

    public ErrorFrame(Frame aFrame, String name, String message) {
        super(aFrame, true);

        JOptionPane.showMessageDialog(aFrame, message, name, JOptionPane.ERROR_MESSAGE);

    }
}