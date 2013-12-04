package model.function.twovariablefunction;

import model.function.NonValidExpressionError;
import model.function.OperableElement;
import model.function.Variable;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 8:15 PM
 */
public class TwoVariableFunctionImplementation implements TwoVariableFunction {
    private Variable x = new Variable("x");
    private Variable y = new Variable("y");
    private OperableElement operationRoot;

    public TwoVariableFunctionImplementation(Variable x, Variable y, OperableElement operationRoot) {
        this.x = x;
        this.y = y;
        this.operationRoot = operationRoot;
        this.operationRoot.simplify();
    }

    public Number functionIn(Number x, Number y) {
        this.x.setValue(x);
        this.y.setValue(y);

        return operationRoot.getValue();
    }

    @Override
    public String toString() {
        return operationRoot.toString();
    }

    public static void main(String[] args) {
        FunctionParser functionParser = new FunctionParser();

        TwoVariableFunction function = null;

        try {
            function = functionParser.getFunctionFromMathExpression("y + 1");
        } catch (NonValidExpressionError nonValidExpressionError) {
            nonValidExpressionError.printStackTrace();
        }
        if (function != null) {
            System.out.println(function.functionIn(2, 1));
            System.out.println(function.functionIn(3, -1));
            System.out.println(function.functionIn(8, 0));
            System.out.println(function.functionIn(0, -1));
            System.out.println(function.functionIn(8, 5));
            System.out.println(function);
        }
    }
}
