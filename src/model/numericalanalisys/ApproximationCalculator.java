package model.numericalanalisys;

import model.function.OperableNumber;
import model.function.Variable;
import model.function.mathoperators.BasicBinaryMathOperation;
import model.function.mathoperators.BinaryMathOperation;
import model.function.onevariablefunction.Function;
import model.function.onevariablefunction.FunctionImplementation;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 11:11 AM
 */
public class ApproximationCalculator {

    public static Function linealApproximation(double[][] values) throws NonValidDataSetException {
        int n = values.length;
        if (n >= 2 && values[0].length == 2) {
            double a, b;
            double ySummation = 0, xSummation = 0, x2Summation = 0, xySummation = 0;
            double xi, yi;

            for (double[] value : values) {
                xi = value[0];
                yi = value[1];
                xSummation += xi;
                ySummation += yi;
                xySummation += xi * yi;
                x2Summation += Math.pow(xi, 2);
            }

            a = (n * xySummation - (xSummation * ySummation)) / (n * x2Summation - Math.pow(xSummation, 2));
            b = (ySummation - a * xSummation) / n;

            Variable x = new Variable("x");
            OperableNumber bOperableNumber = new OperableNumber(b);
            BinaryMathOperation axMultiplication = new BinaryMathOperation(new OperableNumber(a), x,
                    BasicBinaryMathOperation.MULTIPLICATION);
            BinaryMathOperation linearOperation = new BinaryMathOperation(axMultiplication, bOperableNumber,
                    BasicBinaryMathOperation.ADDITION);
            return new FunctionImplementation(x, linearOperation);

        } else {
            throw new NonValidDataSetException();
        }
    }

    public static void main(String[] args) {
        double[][] values = new double[4][2];

                values[0][0] = 1;
                values[0][1] = 2;
                values[1][0] = 2;
                values[1][1] = 4;
                values[2][0] = 3;
                values[2][1] = 6;
                values[3][0] = 4;
                values[3][1] = 8;

        Function function = null;
        try {
            function = linealApproximation(values);
        } catch (NonValidDataSetException e) {
            e.printStackTrace();
        }

        System.out.println(function);
    }
}
