package model.numericalanalisys;

import model.function.onevariablefunction.Function;
import model.function.onevariablefunction.FunctionParser;
import model.function.NonValidExpressionError;

/**
 * User: Javier Isoldi
 * Date: 12/15/12
 * Time: 4:37 PM
 */
public class IntegralCalculator {

    public static double trapezoidalMethod(Function function, double a, double b, int n) {
        double h = (b - a) / n;

        double sum = function.functionInX(a).doubleValue();

        for (double hn = a + h; hn < b; hn += h) {
            double y = function.functionInX(hn).doubleValue();
            sum += 2 * y;
        }

        sum += function.functionInX(b).doubleValue();

        return (h / 2) * sum;
    }

    public static double rombergMethod(Function function, double a, double b, int k) {
        int i = k - 1;
        double[][] rombergMatrix = new double[k][k - 1];

        for (int k1 = 0; k1 < k; k1++) {
            rombergMatrix[k1][0] = trapezoidalMethod(function, a, b, (int) Math.pow(2, k1));
        }

        for (int i1 = 1; i1 <= i - 1; i1++) {
            for (int k1 = i1; k1 < k; k1++) {
                double fourPow = Math.pow(4, i1);
                rombergMatrix[k1][i1] = ( ( fourPow * rombergMatrix[k1][i1 - 1] )  - rombergMatrix[k1 - 1][i1 - 1])
                        / (fourPow - 1);
            }
        }

        double fourPow = Math.pow(4, k - 2);
        return (fourPow * rombergMatrix[k - 1][i - 1] - rombergMatrix[k - 2][i - 1]) / (fourPow - 1);
    }

    public static void main(String[] args) {
        FunctionParser parser = new FunctionParser();

        Function function = null;
        try {
            function = parser.getFunctionFromMathExpression("x^2 - 1");
        } catch (NonValidExpressionError nonValidExpressionError) {
            nonValidExpressionError.printStackTrace();
        }

        System.out.println(IntegralCalculator.rombergMethod(function, 2, 3, 5));
    }
}
