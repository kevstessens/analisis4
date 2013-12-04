package view;

import control.Main;
import control.actions.CalcYForInterpolationAction;
import control.actions.CalcYForLinerApproximationAction;
import control.actions.InterpolatePointsAction;
import control.actions.LinearApproximationAction;
import model.function.onevariablefunction.Function;
import view.popup.ErrorFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 2:28 PM
 */
public class InterpolationPanel extends JPanel implements ActionListener {
    Main main;

    JPanel dataPanel, interpolationPanel, linearApproximationPanel, operationsPanel;
    JLabel interpolationResultLabel, interpolationYResultLabel, linearApproximationResultLabel,
            linearApproximationYResultLabel;
    JTextField interpolationXField, linearApproximationXField;
    JButton interpolationButton, calcForInterpolationButton, linearApproximationButton, calcForLinearApproximationButton,
            resetTableButton;
    InsertionTable dataTable;

    public InterpolationPanel(Main main) {
        super();
        this.main = main;
        add(initListPanel());
        add(initOperationsPanel());

        LayoutManager springLayout = new SpringLayout();
        setLayout(springLayout);

        configMainFrameLayout((SpringLayout) springLayout);


    }

    private JPanel initOperationsPanel() {
        operationsPanel = new JPanel(new GridLayout(2, 1));


        operationsPanel.add(initInterpolationPanel());
        operationsPanel.add(initLinearApproximationPanel());

        return operationsPanel;
    }

    private void configMainFrameLayout(SpringLayout mainFrameLayout) {

        mainFrameLayout.putConstraint(SpringLayout.WEST, this,
                -5,
                SpringLayout.WEST, dataPanel);

        mainFrameLayout.putConstraint(SpringLayout.NORTH, this,
                5,
                SpringLayout.NORTH, dataPanel);

        mainFrameLayout.putConstraint(SpringLayout.SOUTH, this,
                5,
                SpringLayout.SOUTH, dataPanel);

        mainFrameLayout.putConstraint(SpringLayout.NORTH, this,
                5,
                SpringLayout.NORTH, operationsPanel);

        mainFrameLayout.putConstraint(SpringLayout.SOUTH, this,
                5,
                SpringLayout.SOUTH, operationsPanel);

        mainFrameLayout.putConstraint(SpringLayout.WEST, operationsPanel,
                5,
                SpringLayout.EAST, dataPanel);

        mainFrameLayout.putConstraint(SpringLayout.EAST, this,
                5,
                SpringLayout.EAST, operationsPanel);

    }

    private JPanel initLinearApproximationPanel() {
        linearApproximationPanel = new JPanel(new BorderLayout());
        linearApproximationPanel.setBorder(new LineBorder(Color.DARK_GRAY, 1));

        JLabel label = new JLabel("Aproximación lineal");
        linearApproximationPanel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        linearApproximationResultLabel = new JLabel("y = ");
        linearApproximationButton = new JButton(new LinearApproximationAction(main, this));
        linearApproximationButton.setVerticalTextPosition(JButton.TOP);
        linearApproximationButton.setHorizontalTextPosition(JButton.CENTER);

        buttonPanel.add(linearApproximationResultLabel, BorderLayout.SOUTH);
        buttonPanel.add(linearApproximationButton, BorderLayout.CENTER);

        JPanel calcPanel = new JPanel(new BorderLayout());
        linearApproximationYResultLabel = new JLabel("Resultado : ");
        label = new JLabel("X : ");
        linearApproximationXField = new JTextField();
        calcForLinearApproximationButton = new JButton(new CalcYForLinerApproximationAction(main, this));

        calcPanel.add(calcForLinearApproximationButton, BorderLayout.NORTH);
        calcPanel.add(linearApproximationXField, BorderLayout.CENTER);
        calcPanel.add(label, BorderLayout.WEST);
        calcPanel.add(linearApproximationYResultLabel, BorderLayout.SOUTH);

        linearApproximationPanel.add(buttonPanel, BorderLayout.CENTER);
        linearApproximationPanel.add(calcPanel, BorderLayout.SOUTH);

        return linearApproximationPanel;
    }

    private JPanel initInterpolationPanel() {
        interpolationPanel = new JPanel(new BorderLayout());
        interpolationPanel.setBorder(new LineBorder(Color.DARK_GRAY, 1));

        JLabel label = new JLabel("Interpolación");
        interpolationPanel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        interpolationResultLabel = new JLabel("f(x) = ");
        interpolationButton = new JButton(new InterpolatePointsAction(main, this));
        interpolationButton.setVerticalTextPosition(JButton.TOP);
        interpolationButton.setHorizontalTextPosition(JButton.CENTER);

        buttonPanel.add(interpolationResultLabel, BorderLayout.SOUTH);
        buttonPanel.add(interpolationButton, BorderLayout.CENTER);

        JPanel calcPanel = new JPanel(new BorderLayout());
        interpolationYResultLabel = new JLabel("Resultado : ");
        label = new JLabel("X : ");
        interpolationXField = new JTextField();
        calcForInterpolationButton = new JButton(new CalcYForInterpolationAction(main, this));

        calcPanel.add(calcForInterpolationButton, BorderLayout.NORTH);
        calcPanel.add(interpolationXField, BorderLayout.CENTER);
        calcPanel.add(label, BorderLayout.WEST);
        calcPanel.add(interpolationYResultLabel, BorderLayout.SOUTH);

        interpolationPanel.add(buttonPanel, BorderLayout.CENTER);
        interpolationPanel.add(calcPanel, BorderLayout.SOUTH);

        return interpolationPanel;
    }

    private JPanel initListPanel() {
        dataPanel = new JPanel(new BorderLayout());


        dataTable = new InsertionTable();
        JLabel label = new JLabel("Puntos: ");
        resetTableButton = new JButton("Limpiar tabla");
        resetTableButton.addActionListener(this);

        dataPanel.add(label, BorderLayout.NORTH);
        dataPanel.add(dataTable, BorderLayout.CENTER);
        dataPanel.add(resetTableButton, BorderLayout.SOUTH);

        return dataPanel;
    }

    public Object[][] getPoints() {
        return dataTable.getData();
    }

    public double getXForInterpolation() {
        try {
            return Double.valueOf(interpolationXField.getText());
        } catch (NumberFormatException e) {
            new ErrorFrame(new JFrame(), "Error", "Valor de X inválido");
            return Double.NaN;
        }
    }

    public double getXForLinearApproximation() {
        try {
            return Double.valueOf(linearApproximationXField.getText());
        } catch (NumberFormatException e) {
            new ErrorFrame(new JFrame(), "Error", "Valor de X inválido");
            return Double.NaN;
        }
    }

    public void setResultOfInterpolation(Function function) {
        interpolationResultLabel.setText("f(x)  = " + function.toString());
    }

    public void setResultOfLinearApproximation(Function function) {
        linearApproximationResultLabel.setText("y = " + function.toString());
    }

    public void setYResultOfInterpolation(double result) {
        interpolationYResultLabel.setText("Resultado :  " + result);
    }

    public void setYResultOfLinearApproximation(double result) {
        linearApproximationYResultLabel.setText("Resultado :  " + result);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dataTable.resetValues();
    }
}
