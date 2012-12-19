package model.numericalanalisys;

import model.function.NonValidExpressionError;
import model.function.twovariablefunction.FunctionParser;
import model.function.twovariablefunction.TwoVariableFunction;

/**
 * User: Javier Isoldi
 * Date: 12/15/12
 * Time: 8:01 PM
 */
public class DifferentialEquationCalculator {

    public static double[][] fourthOrderRungeKuttaMethod(TwoVariableFunction f, double x0, double y0, double h, int n) {
        double[][] result = new double[n][2];
        result[0][0] = x0;
        result[0][1] = y0;

        double x1 = x0, k1, k2, k3, k4, y1;


        for (int i = 1; i < n ; i++){
            x0 = result[i - 1][0];
            y0 = result[i - 1][1];

            x1 += h;

            k1 = f.functionIn(x0,y0).doubleValue();
            k2 = f.functionIn(x0 + 0.5 * h, y0 + 0.5 * k1 * h).doubleValue();
            k3 = f.functionIn(x0 + 0.5 * h, y0 + 0.5 * k2 * h).doubleValue();
            k4 = f.functionIn(x0 + h, y0 + k3 * h).doubleValue();

            y1 = y0 + ((k1 + 2 * k2 + 2 * k3 + k4) * h) / 6;

            result[i][0] = x1;
            result[i][1] = y1;

        }

        return result;
    }

    public static void main(String[] args) {
        FunctionParser parser = new FunctionParser();

                TwoVariableFunction function = null;
                try {
                    function = parser.getFunctionFromMathExpression("y");
                } catch (NonValidExpressionError nonValidExpressionError) {
                    nonValidExpressionError.printStackTrace();
                }

                double[][] result = DifferentialEquationCalculator.fourthOrderRungeKuttaMethod(function, 0, 1, 0.1, 10);

        for (double[] aResult : result) {
            for (double v : aResult) {
                System.out.println(v);
            }
        }
    }
}
