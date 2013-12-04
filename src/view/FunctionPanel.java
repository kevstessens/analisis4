package view;

import control.Main;
import control.actions.AddFunctionAction;
import control.actions.CalculateZeroOfAction;
import control.actions.IntegrateFunctionAction;
import model.function.onevariablefunction.Function;
import view.popup.ErrorFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 2:28 PM
 */
public class FunctionPanel extends JPanel {
    Main main;

    JPanel listPanel, functionInsertionPanel, integrationPanel, zeroCalcPanel, operationsPanel;
    JList<Function> functionList;
    JLabel listLabel, fxLabel, zeroResultLabel, integrationResultLabel;
    JTextField functionField, integrateFromField, integrateToField, x0Field;
    JButton addFunctionButton, integrateButton, calcZeroButton;

    public FunctionPanel(Main main) {
        super();
        this.main = main;
        add(initListPanel());
        add(initOperationsPanel());

        LayoutManager springLayout = new SpringLayout();
        setLayout(springLayout);

        configMainFrameLayout((SpringLayout) springLayout);


    }

    private JPanel initOperationsPanel() {
        operationsPanel = new JPanel(new GridLayout(3, 1));


        operationsPanel.add(initFunctionPanel());
        operationsPanel.add(initIntegrationPanel());
        operationsPanel.add(initZeroCalcPanel());

        return operationsPanel;
    }

    private void configMainFrameLayout(SpringLayout mainFrameLayout) {

        mainFrameLayout.putConstraint(SpringLayout.WEST, this,
                -5,
                SpringLayout.WEST, listPanel);

        mainFrameLayout.putConstraint(SpringLayout.NORTH, this,
                5,
                SpringLayout.NORTH, listPanel);

        mainFrameLayout.putConstraint(SpringLayout.SOUTH, this,
                5,
                SpringLayout.SOUTH, listPanel);

        mainFrameLayout.putConstraint(SpringLayout.NORTH, this,
                5,
                SpringLayout.NORTH, operationsPanel);

        mainFrameLayout.putConstraint(SpringLayout.SOUTH, this,
                5,
                SpringLayout.SOUTH, operationsPanel);

        mainFrameLayout.putConstraint(SpringLayout.WEST, operationsPanel,
                5,
                SpringLayout.EAST, listPanel);

        mainFrameLayout.putConstraint(SpringLayout.EAST, this,
                5,
                SpringLayout.EAST, operationsPanel);

    }

    private JPanel initZeroCalcPanel() {
        zeroCalcPanel = new JPanel(new BorderLayout());
        zeroCalcPanel.setBorder(new LineBorder(Color.DARK_GRAY, 1));

        JLabel jLabel = new JLabel("Calcular raíz:");
        zeroCalcPanel.add(jLabel, BorderLayout.NORTH);

        JPanel fieldPanel = new JPanel(new BorderLayout());
        jLabel = new JLabel("Resolver para un X : ");
        x0Field = new JTextField();
        calcZeroButton = new JButton(new CalculateZeroOfAction(main, this));

        fieldPanel.add(jLabel, BorderLayout.WEST);
        fieldPanel.add(x0Field, BorderLayout.CENTER);

        JPanel buttonPanel =  new JPanel(new BorderLayout());
        buttonPanel.add(calcZeroButton, BorderLayout.CENTER);
        buttonPanel.add(fieldPanel, BorderLayout.NORTH);

        zeroCalcPanel.add(buttonPanel, BorderLayout.CENTER);

        zeroResultLabel = new JLabel("Resultado : ");
        zeroCalcPanel.add(zeroResultLabel, BorderLayout.SOUTH);


        return zeroCalcPanel;
    }

    private JPanel initIntegrationPanel() {
        integrationPanel = new JPanel(new BorderLayout());
        integrationPanel.setBorder(new LineBorder(Color.DARK_GRAY, 1));

        JLabel jLabel = new JLabel("Integrar función: ");
        integrateFromField = new JTextField();
        integrateToField = new JTextField();
        integrateButton = new JButton(new IntegrateFunctionAction(main, this));
        integrateButton.setVerticalTextPosition(JButton.BOTTOM);
        integrateButton.setHorizontalTextPosition(JButton.CENTER);

        integrationPanel.add(jLabel, BorderLayout.NORTH);

        JPanel jPanel = new JPanel(new GridLayout(2, 2));
        jLabel = new JLabel("Desde : ");
        jPanel.add(jLabel);
        jPanel.add(integrateFromField);
        jLabel = new JLabel("Hasta : ");
        jPanel.add(jLabel);
        jPanel.add(integrateToField);

        integrationPanel.add(jPanel, BorderLayout.CENTER);
        integrationPanel.add(integrateButton, BorderLayout.EAST);
        integrationResultLabel = new JLabel("Resultado : ");
        integrationPanel.add(integrationResultLabel, BorderLayout.SOUTH);

        return integrationPanel;
    }

    private JPanel initFunctionPanel() {
        functionInsertionPanel = new JPanel(new BorderLayout());
        functionInsertionPanel.setBorder(new LineBorder(Color.DARK_GRAY, 1));

        JPanel fieldPanel = new JPanel(new BorderLayout());
        fxLabel = new JLabel("f(x) = ");
        functionField = new JTextField();
        addFunctionButton = new JButton(new AddFunctionAction(main, this));
        addFunctionButton.setVerticalTextPosition(JButton.TOP);
        addFunctionButton.setHorizontalTextPosition(JButton.CENTER);

        fieldPanel.add(fxLabel, BorderLayout.WEST);
        fieldPanel.add(functionField, BorderLayout.CENTER);

        functionInsertionPanel.add(fieldPanel, BorderLayout.NORTH);
        functionInsertionPanel.add(addFunctionButton, BorderLayout.CENTER);
        functionInsertionPanel.add(new JPanel(), BorderLayout.SOUTH);

        return functionInsertionPanel;
    }

    private JPanel initListPanel() {
        listPanel = new JPanel(new BorderLayout());

        functionList = new JList<Function>();
        listLabel = new JLabel("Funciones : ");
        functionList.setBackground(Color.WHITE);

        listPanel.add(listLabel, BorderLayout.NORTH);
        listPanel.add(functionList, BorderLayout.CENTER);

        return listPanel;
    }

    public String getFunctionFieldText() {
        return functionField.getText();
    }

    public void setCaretPosInFunctionField(int caretPosInFunctionField) {
        functionField.setCaretPosition(caretPosInFunctionField);
    }

    public void updateList(Function[] allFunctions) {
        functionList.setListData(allFunctions);
        functionList.updateUI();
    }

    public Function getSelectedFunction() {
        return functionList.getSelectedValue();
    }

    public double getIntegrateFromValue() {
        try {
            return Double.valueOf(integrateFromField.getText());
        } catch (NumberFormatException e) {
            new ErrorFrame(new JFrame(), "Error", "Extremo inválido");
            return Double.NaN;
        }
    }

    public double getIntegrateToValue() {
        try {
            return Double.valueOf(integrateToField.getText());
        } catch (NumberFormatException e) {
            new ErrorFrame(new JFrame(), "Error", "Extremo inválido");
            return Double.NaN;
        }
    }

    public double getX0Value() {
        try {
            return Double.valueOf(x0Field.getText());
        } catch (NumberFormatException e) {
            new ErrorFrame(new JFrame(), "Error", "Valor de X inválido");
            return Double.NaN;
        }
    }

    public void setIntegrationResult(double result) {
        integrationResultLabel.setText("Resultado : " + result);
    }

    public void setZeroResult(double result) {
        zeroResultLabel.setText("Resultado : " + result);
    }
}
