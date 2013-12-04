package control;

import model.function.onevariablefunction.Function;
import model.function.twovariablefunction.TwoVariableFunction;
import view.MainFrame;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 10:12 PM
 */
public class Main {
    List<Function> functionList = new ArrayList<Function>();
    List<TwoVariableFunction> twoVariableFunctionList = new ArrayList<TwoVariableFunction>();
    Function interpolatedFunction;
    Function linearFunction;
    MainFrame mainFrame = new MainFrame(this);

    public void addFunction(Function function) {
        functionList.add(function);
    }

    public Function[] getAllFunctions() {
        Function[] functions = new Function[functionList.size()];
        for (int i = 0; i < functions.length; i++) {
            functions[i] = functionList.get(i);
        }
        return functions;
    }

    public TwoVariableFunction[] getAllTwoVariableFunctions() {
        TwoVariableFunction[] functions = new TwoVariableFunction[twoVariableFunctionList.size()];
        for (int i = 0; i < functions.length; i++) {
            functions[i] = twoVariableFunctionList.get(i);
        }
        return functions;
    }

    public void addTwoVariablesFunction(TwoVariableFunction twoVariableFunction) {
        twoVariableFunctionList.add(twoVariableFunction);
    }

    public Function getLinearFunction() {
        return linearFunction;
    }

    public void setLinearFunction(Function linearFunction) {
        this.linearFunction = linearFunction;
    }

    public Function getInterpolatedFunction() {
        return interpolatedFunction;
    }

    public void setInterpolatedFunction(Function interpolatedFunction) {
        this.interpolatedFunction = interpolatedFunction;
    }

    public static void main(String[] args) {
        new Main();
    }
}
