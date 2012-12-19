package model.numericalanalisys;

import model.function.onevariablefunction.Function;
import model.function.onevariablefunction.FunctionParser;
import model.function.NonValidExpressionError;

/**
 * User: Javier Isoldi
 * Date: 12/14/12
 * Time: 7:11 PM
 */
public class ZeroCalculator {

    public static double newtonRawsonMethod(Function function, double x0) {
        Function functionDerivative = function.getDerivative();
        return newtonRawsonMethod(function, x0, 0, functionDerivative);
    }

    private static double newtonRawsonMethod(Function function, double x, int iteration, Function functionDerivative) {
        double y = function.functionInX(x).doubleValue();
        if (y <= 0.0000000000000000000001) {
            return x;
        }
        x = x - (y / functionDerivative.functionInX(x).doubleValue());
        if (iteration <= 30) {
            iteration++;
            return newtonRawsonMethod(function, x, iteration, functionDerivative);
        }
        if (y > 10) {
            return Double.NaN;
        }
        return x;
    }

    private static double steffesenMethod(Function function, double x0) {
        Function functionDerivative = function.getDerivative();
        return steffesenMethod(function, x0, 0);
    }

    private static double steffesenMethod(Function function, double x, int iteration) {
        double y = function.functionInX(x).doubleValue();
        if (Math.abs(y) <= 0.00000000000000000000001) {
            return x;
        }
        double functionDerivative = (function.functionInX(x + y).doubleValue() - y) / y;
        x = x - (y / functionDerivative);
        if (iteration <= 30) {
            iteration++;
            return steffesenMethod(function, x, iteration);
        }
        if (y > 10) {
            return Double.NaN;
        }
        return x;

    }

    public static void main(String[] args) {
        FunctionParser parser = new FunctionParser();

        Function function = null;
        try {
            function = parser.getFunctionFromMathExpression("x^2 - 1");
        } catch (NonValidExpressionError nonValidExpressionError) {
            nonValidExpressionError.printStackTrace();
        }

        System.out.println(ZeroCalculator.newtonRawsonMethod(function, 1000));
    }
}
