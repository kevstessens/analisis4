package view;

import control.Main;
import control.actions.AddDifferentialEquationAction;
import control.actions.SolveDifferentialEquationAction;
import model.function.twovariablefunction.TwoVariableFunction;
import view.popup.ErrorFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 2:28 PM
 */
public class DifferentialEquationPanel extends JPanel {
    Main main;

    JPanel listPanel, functionInsertionPanel, functionSolver, operationsPanel;
    JList<TwoVariableFunction> functionList;
    JLabel listLabel, fxLabel;
    JTextField functionField, x0Field, y0Field, stepField, numberOfResults;
    JButton addFunctionButton, solveButton;
    ResultTable resultTable;

    public DifferentialEquationPanel(Main main) {
        super();
        this.main = main;
        add(initListPanel());
        add(initOperationsPanel());

        LayoutManager springLayout = new SpringLayout();
        setLayout(springLayout);

        configMainFrameLayout((SpringLayout) springLayout);


    }

    private JPanel initOperationsPanel() {
        operationsPanel = new JPanel(new BorderLayout());


        operationsPanel.add(initFunctionPanel(), BorderLayout.NORTH);
        operationsPanel.add(initSolvePanel(), BorderLayout.CENTER);

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

    private JPanel initSolvePanel() {
        functionSolver = new JPanel(new BorderLayout());
        functionSolver.setBorder(new LineBorder(Color.DARK_GRAY, 1));

        JLabel jLabel = new JLabel("Resolver ecuaciones diferenciales: ");
        y0Field = new JTextField();
        x0Field = new JTextField();
        stepField = new JTextField();
        numberOfResults = new JTextField();

        solveButton = new JButton(new SolveDifferentialEquationAction(main, this));
        solveButton.setVerticalTextPosition(JButton.BOTTOM);
        solveButton.setHorizontalTextPosition(JButton.CENTER);

        functionSolver.add(jLabel, BorderLayout.NORTH);

        JPanel jPanel = new JPanel(new GridLayout(2, 4));
        jLabel = new JLabel("Xo = ");
        jPanel.add(jLabel);
        jPanel.add(x0Field);
        jLabel = new JLabel("Yo = ");
        jPanel.add(jLabel);
        jPanel.add(y0Field);
        jLabel = new JLabel("Paso:");
        jPanel.add(jLabel);
        jPanel.add(stepField);
        jLabel = new JLabel("Para: ");
        jPanel.add(jLabel);
        jPanel.add(numberOfResults);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultTable = new ResultTable();
        JScrollPane scrollPane = new JScrollPane(resultTable);
        resultPanel.add(jPanel, BorderLayout.NORTH);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        functionSolver.add(resultPanel, BorderLayout.CENTER);
        functionSolver.add(solveButton, BorderLayout.SOUTH);

        return functionSolver;
    }

    private JPanel initFunctionPanel() {
        functionInsertionPanel = new JPanel(new BorderLayout());
        functionInsertionPanel.setBorder(new LineBorder(Color.DARK_GRAY, 1));

        JPanel fieldPanel = new JPanel(new BorderLayout());
        fxLabel = new JLabel("f(x)´ = ");
        functionField = new JTextField();
        addFunctionButton = new JButton(new AddDifferentialEquationAction(main, this));
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

        functionList = new JList<TwoVariableFunction>();
        listLabel = new JLabel("Ecuaciones diferenciales: ");
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

    public void updateList(TwoVariableFunction[] allFunctions) {
        functionList.setListData(allFunctions);
        functionList.updateUI();
    }

    public TwoVariableFunction getSelectedFunction() {
        return functionList.getSelectedValue();
    }

    public double getX0Value() {
        try {
            return Double.valueOf(x0Field.getText());
        } catch (NumberFormatException e) {
            new ErrorFrame(new JFrame(), "Error", "X0 inválida");
            return Double.NaN;
        }
    }

    public double getY0Value() {
        try {
            return Double.valueOf(y0Field.getText());
        } catch (NumberFormatException e) {
            new ErrorFrame(new JFrame(), "Error", "Y0 inválida");
            return Double.NaN;
        }
    }

    public double getStepValue() {
        try {
            return Double.valueOf(stepField.getText());
        } catch (NumberFormatException e) {
            new ErrorFrame(new JFrame(), "Error", "Valor inválido");
            return Double.NaN;
        }
    }

    public int getNumberOfResultValue() {
        try {
            return Integer.valueOf(numberOfResults.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void updateTableResults(double[][] doubles){
        Double[][] newValue = new Double[doubles.length][2];
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < doubles.length; j++){
                newValue[j][i] = doubles[j][i];
            }
        }
        resultTable.refreshTable(newValue);
    }
}
