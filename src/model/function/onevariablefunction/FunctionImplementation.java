package model.function.onevariablefunction;

import model.function.NonValidExpressionError;
import model.function.OperableElement;
import model.function.Variable;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 2:51 PM
 */
public class FunctionImplementation implements Function {
    private Variable x = new Variable("x");
    private OperableElement operationRoot;

    public FunctionImplementation(Variable x, OperableElement operationRoot) {
        this.x = x;
        this.operationRoot = operationRoot;
        this.operationRoot.simplify();
    }

    public FunctionImplementation(OperableElement operationRoot) {
        this.operationRoot = operationRoot;
        this.operationRoot.simplify();
    }


    public Number functionInX(Number x) {
        this.x.setValue(x);

        return operationRoot.getValue();
    }

    public Function getDerivative() {
        return new FunctionImplementation(x, operationRoot.getDerivative());
    }

    public static void main(String[] args) {
        FunctionParser functionParser = new FunctionParser();

        Function function = null;

        try {
            function = functionParser.getFunctionFromMathExpression("x + 1");
        } catch (NonValidExpressionError nonValidExpressionError) {
            nonValidExpressionError.printStackTrace();
        }
        if (function != null) {
            System.out.println(function);
            System.out.println(function.getDerivative());
            System.out.println(function.getDerivative().functionInX(1));
            System.out.println(function.getDerivative().functionInX(2));
            System.out.println(function.getDerivative().functionInX(3));
            System.out.println(function.getDerivative().functionInX(4));
        }
    }

    @Override
    public String toString() {
        return operationRoot.toString();
    }
}
