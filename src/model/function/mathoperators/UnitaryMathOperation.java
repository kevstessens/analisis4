package model.function.mathoperators;

import model.function.OperableElement;
import model.function.OperableNumber;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 11:25 PM
 */
public class UnitaryMathOperation implements OperableElement {
    OperableElement operand;
    BasicUnitaryMathOperation mathOperation;

    private static final double DERIVATIVE_LOG_CONSTANT = 1 / Math.log(10);

    public UnitaryMathOperation(OperableElement operand, BasicUnitaryMathOperation mathOperation) {
        this.operand = operand;
        this.mathOperation = mathOperation;
    }

    public Number getValue() {
        switch (mathOperation) {
            case LOG:
                return Math.log10(operand.getValue().doubleValue());
            case LN:
                return Math.log(operand.getValue().doubleValue());
            case SIN:
                return Math.sin(operand.getValue().doubleValue());
            case COS:
                return Math.cos(operand.getValue().doubleValue());
            case TAN:
                return Math.tan(operand.getValue().doubleValue());
            default:
                return null;
        }
    }

    public OperableElement getDerivative() {
        switch (mathOperation) {
            case LOG:
                return new BinaryMathOperation(
                        (new BinaryMathOperation(
                                new OperableNumber(DERIVATIVE_LOG_CONSTANT),
                                operand, BasicBinaryMathOperation.DIVISION)),
                        operand.getDerivative(), BasicBinaryMathOperation.MULTIPLICATION);
            case LN:
                return new BinaryMathOperation(
                        operand.getDerivative(), operand, BasicBinaryMathOperation.DIVISION);
            case SIN:
                return new BinaryMathOperation(new UnitaryMathOperation(operand, BasicUnitaryMathOperation.COS),
                        operand.getDerivative(), BasicBinaryMathOperation.MULTIPLICATION);
            case COS:
                return new BinaryMathOperation(
                        new BinaryMathOperation(new OperableNumber(-1),
                                new UnitaryMathOperation(operand, BasicUnitaryMathOperation.SIN),
                                BasicBinaryMathOperation.MULTIPLICATION),
                        operand.getDerivative(), BasicBinaryMathOperation.MULTIPLICATION);
            case TAN:
                return new BinaryMathOperation(operand.getDerivative(),
                        new BinaryMathOperation(
                                new UnitaryMathOperation(operand, BasicUnitaryMathOperation.COS),
                                new OperableNumber(2), BasicBinaryMathOperation.EXPONENTIATION),
                        BasicBinaryMathOperation.DIVISION);
            default:
                return this;
        }
    }

    @Override
    public String toString() {
        switch (mathOperation) {
            case LOG:
                return "log( " + operand.toString() + " )";
            case LN:
                return "ln( " + operand.toString() + " )";
            case SIN:
                return "sin( " + operand.toString() + " )";
            case COS:
                return "cos( " + operand.toString() + " )";
            case TAN:
                return "tan( " + operand.toString() + " )";
            default:
                return "";
        }
    }

    public boolean isConstant() {
        return operand.isConstant();
    }

    public OperableElement simplify() {
        operand.simplify();
        if (operand.isConstant()) {
            return new OperableNumber(getValue());
        } else {
            return this;
        }
    }
}
