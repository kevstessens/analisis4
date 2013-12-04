package model.numericalanalisys;

import model.function.OperableElement;
import model.function.OperableNumber;
import model.function.Variable;
import model.function.mathoperators.BasicBinaryMathOperation;
import model.function.mathoperators.BinaryMathOperation;
import model.function.onevariablefunction.Function;
import model.function.onevariablefunction.FunctionImplementation;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 2:42 AM
 */
public class InterpolationCalculator {

    public static Function newtonInterpolationMethod(double[][] values) throws NonValidDataSetException {
        int n = values.length;
        double[][] newtonMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            newtonMatrix[i][0] = values[i][1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n  -1- i; j++) {
                double divider = values[i + j][0] - values[j][0];
                if (divider == 0){
                    throw new NonValidDataSetException();
                }
                newtonMatrix[j][i] = (newtonMatrix[j + 1][i - 1] - newtonMatrix[j][i - 1]) /
                        (divider);
            }
        }

        Variable x = new Variable("x");

        OperableElement sumAux = new OperableNumber(newtonMatrix[0][0]);
        for(int i = 1; i < n; i++){
            BinaryMathOperation aux = new BinaryMathOperation(new OperableNumber(newtonMatrix[0][i]),
                    new BinaryMathOperation(x,new OperableNumber(values[0][0]),BasicBinaryMathOperation.SUBTRACTION),
                    BasicBinaryMathOperation.MULTIPLICATION);
            for (int j = 1; j < i; j += 1){
                aux = new BinaryMathOperation(aux,
                                    new BinaryMathOperation(x,new OperableNumber(values[j][0]),BasicBinaryMathOperation.SUBTRACTION),
                                    BasicBinaryMathOperation.MULTIPLICATION);
            }
            sumAux = new BinaryMathOperation(sumAux, aux,BasicBinaryMathOperation.ADDITION);
        }

        return new FunctionImplementation(x,sumAux);
    }

    public static void main(String[] args) {
        double[][] values = new double[4][2];

        values[0][0] = 0.1;
        values[0][1] = 2.31;
        values[1][0] = 0.4;
        values[1][1] = 3.36;
        values[2][0] = 0.7;
        values[2][1] = 4.59;
        values[3][0] = 1;
        values[3][1] = 6;

        Function function = null;
        try {
            function = newtonInterpolationMethod(values);
        } catch (NonValidDataSetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println(function);
    }

}
