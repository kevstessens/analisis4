package view;

import com.alee.laf.WebLookAndFeel;
import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import control.Main;

import javax.swing.*;
import java.awt.*;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 2:04 PM
 */
public class MainFrame {
    final static String FUNCTION_PANEL_NAME = "Funciones";
    final static String FUNCTION_PANEL_TIP = "En esta sección se puede trabajar con análisis de funciones";
    final static String DIFFERENTIAL_EQUATION_PANEL_NAME = "Ecuaciones Diferenciales";
    final static String DIFFERENTIAL_EQUATION_PANEL_TIP = "En esta sección se pueden resolver ecuaciones diferenciales";
    final static String INTERPOLATION_PANEL_NAME = "Interpolación y regresión";
    final static String INTERPOLATION_PANEL_TIP = "En esta sección se pueden interpolar puntos en una función, o hacer" +
            " una regresión lineal con ellos";

    private JFrame jFrame;
    private JTabbedPane tabbedPane;
    private JPanel functionPanel;
    private JPanel differentialEquationPanel;
    private JPanel interpolationPanel;

    private Main main;

    public MainFrame(Main main) {
        this.main = main;

        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
//            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//            UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
//            UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
            UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
//            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");  O ESTE
//            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
//            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");

//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            UIManager.setLookAndFeel(SeaGlassLookAndFeel.class.getCanonicalName());
//            UIManager.setLookAndFeel ( WebLookAndFeel.class.getCanonicalName () );

        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        jFrame = new JFrame("Calculadora");

        jFrame.setSize(new Dimension(800, 400));
        jFrame.setResizable(false);
        jFrame.getContentPane().add(initTabbedPane());

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    private JTabbedPane initTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);

        //function panel
        functionPanel = new FunctionPanel(main);
        functionPanel.setBounds(100, 100, 80, 70);
        tabbedPane.addTab(FUNCTION_PANEL_NAME, null, functionPanel, FUNCTION_PANEL_TIP);

        //differential equation panel
        differentialEquationPanel = new DifferentialEquationPanel(main);
        differentialEquationPanel.setBounds(100, 100, 80, 70);
        tabbedPane.addTab(DIFFERENTIAL_EQUATION_PANEL_NAME, null, differentialEquationPanel
                , DIFFERENTIAL_EQUATION_PANEL_TIP);

        //function panel
        interpolationPanel = new InterpolationPanel(main);
        interpolationPanel.setBounds(100, 100, 80, 70);
        tabbedPane.addTab(INTERPOLATION_PANEL_NAME, null, interpolationPanel, INTERPOLATION_PANEL_TIP);

        return tabbedPane;
    }
}
