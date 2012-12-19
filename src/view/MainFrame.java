package view;

import javax.swing.*;

/**
 * User: Javier Isoldi
 * Date: 12/16/12
 * Time: 2:04 PM
 */
public class MainFrame {
    final static String FUNCTION_PANEL_NAME = "Functions";
    final static String FUNCTION_PANEL_TIP = "Here you can work with function analysis";
    final static String DIFFERENTIAL_EQUATION_PANEL_NAME = "Differential Equations";
    final static String DIFFERENTIAL_EQUATION_PANEL_TIP = "Here you can find solution to differential equation";
    final static String INTERPOLATION_PANEL_NAME = "Interpolation";
    final static String INTERPOLATION_PANEL_TIP = "Here you can interpolate points into a function";

    private JFrame jFrame;
    private JTabbedPane tabbedPane;
    private JPanel functionPanel;
    private JPanel differentialEquationPanel;
    private JPanel interpolationPanel;


    public MainFrame() {
        jFrame = new JFrame("Calculator");

        jFrame.setSize(500, 500);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        jFrame.getContentPane().add(initTabbedPane());
        jFrame.setVisible(true);

    }

    private JTabbedPane initTabbedPane() {
        tabbedPane = new JTabbedPane();

        //function panel
        functionPanel = new FunctionPanel();
        JLabel functions = new JLabel(FUNCTION_PANEL_NAME);
        functionPanel.setBounds(100, 100, 80, 70);
        functionPanel.add(functions);
        tabbedPane.addTab(FUNCTION_PANEL_NAME, null, functionPanel, FUNCTION_PANEL_TIP);

        //differential equation panel
        differentialEquationPanel = new DifferentialEquationPanel();
        JLabel label2 = new JLabel(DIFFERENTIAL_EQUATION_PANEL_NAME);
        differentialEquationPanel.setBounds(100, 100, 80, 70);
        differentialEquationPanel.add(label2);
        tabbedPane.addTab(DIFFERENTIAL_EQUATION_PANEL_NAME, null, differentialEquationPanel
                , DIFFERENTIAL_EQUATION_PANEL_TIP);

        //function panel
        interpolationPanel = new InterpolationPanel();
        JLabel label3 = new JLabel(INTERPOLATION_PANEL_NAME);
        interpolationPanel.setBounds(100, 100, 80, 70);
        interpolationPanel.add(label3);
        tabbedPane.addTab(INTERPOLATION_PANEL_NAME, null, interpolationPanel, INTERPOLATION_PANEL_TIP);

        return tabbedPane;
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
