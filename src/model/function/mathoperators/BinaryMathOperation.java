package model.function.mathoperators;

import model.function.OperableElement;
import model.function.OperableNumber;

/**
 * User: Javier Isoldi
 * Date: 12/13/12
 * Time: 3:40 PM
 */
public class BinaryMathOperation implements OperableElement {
    OperableElement firstOperand;
    OperableElement secondOperand;
    BasicBinaryMathOperation mathOperation;

    public BinaryMathOperation(OperableElement firstOperand, OperableElement secondOperand,
                               BasicBinaryMathOperation mathOperation) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.mathOperation = mathOperation;
    }

    public Number getValue() {
        switch (mathOperation) {
            case ADDITION:
                return firstOperand.getValue().doubleValue() + secondOperand.getValue().doubleValue();
            case SUBTRACTION:
                return firstOperand.getValue().doubleValue() - secondOperand.getValue().doubleValue();
            case MULTIPLICATION:
                return firstOperand.getValue().doubleValue() * secondOperand.getValue().doubleValue();
            case DIVISION:
                return firstOperand.getValue().doubleValue() / secondOperand.getValue().doubleValue();
            case EXPONENTIATION:
                return Math.pow(firstOperand.getValue().doubleValue(), secondOperand.getValue().doubleValue());
            default:
                return null;
        }
    }

    public OperableElement getDerivative() {
        switch (mathOperation) {
            case ADDITION:
                return new BinaryMathOperation(firstOperand.getDerivative(), secondOperand.getDerivative(),
                        BasicBinaryMathOperation.ADDITION);
            case SUBTRACTION:
                return new BinaryMathOperation(firstOperand.getDerivative(), secondOperand.getDerivative(),
                        BasicBinaryMathOperation.SUBTRACTION);
            case MULTIPLICATION:
                return new BinaryMathOperation(new BinaryMathOperation(firstOperand.getDerivative(), secondOperand,
                        BasicBinaryMathOperation.MULTIPLICATION)
                        , new BinaryMathOperation(firstOperand, secondOperand.getDerivative(),
                        BasicBinaryMathOperation.MULTIPLICATION)
                        , BasicBinaryMathOperation.ADDITION);
            case DIVISION:
                return new BinaryMathOperation(new BinaryMathOperation(new BinaryMathOperation(firstOperand.getDerivative(),
                        secondOperand, BasicBinaryMathOperation.MULTIPLICATION)
                        , new BinaryMathOperation(firstOperand, secondOperand.getDerivative(),
                        BasicBinaryMathOperation.MULTIPLICATION)
                        , BasicBinaryMathOperation.SUBTRACTION),
                        new BinaryMathOperation(secondOperand, new OperableNumber(2), BasicBinaryMathOperation.EXPONENTIATION),
                        BasicBinaryMathOperation.DIVISION);
            case EXPONENTIATION:
                return new BinaryMathOperation(
                        new BinaryMathOperation(firstOperand,
                                new BinaryMathOperation(secondOperand, new OperableNumber(1),
                                        BasicBinaryMathOperation.SUBTRACTION),
                                BasicBinaryMathOperation.EXPONENTIATION),
                        new BinaryMathOperation(
                                new BinaryMathOperation(secondOperand, firstOperand.getDerivative(),
                                        BasicBinaryMathOperation.MULTIPLICATION),
                                new BinaryMathOperation(new BinaryMathOperation(firstOperand,
                                        new UnitaryMathOperation(firstOperand, BasicUnitaryMathOperation.LOG),
                                        BasicBinaryMathOperation.MULTIPLICATION),
                                        secondOperand.getDerivative(), BasicBinaryMathOperation.MULTIPLICATION),
                                BasicBinaryMathOperation.ADDITION),
                        BasicBinaryMathOperation.MULTIPLICATION);
            default:
                return this;

        }
    }

    @Override
    public String toString() {
        switch (mathOperation) {
            case ADDITION:
                return "" + firstOperand.toString() + "+" + secondOperand.toString() + "";
            case SUBTRACTION:
                return "" + firstOperand.toString() + "-" + secondOperand.toString() + "";
            case MULTIPLICATION:
                return "(" + firstOperand.toString() + ")*(" + secondOperand.toString() + ")";
            case DIVISION:
                return "(" + firstOperand.toString() + ")/(" + secondOperand.toString() + ")";
            case EXPONENTIATION:
                return "(" + firstOperand.toString() + ")^(" + secondOperand.toString() + ")";
            default:
                return "";
        }
    }

    public boolean isConstant() {
        switch (mathOperation) {
            case EXPONENTIATION:
                return (secondOperand.isConstant() && secondOperand.getValue().doubleValue() == 0) ||
                        (firstOperand.isConstant() && secondOperand.isConstant());
            default:
                return firstOperand.isConstant() && secondOperand.isConstant();
        }
    }

    public OperableElement simplify() {
        if (firstOperand.isConstant() && secondOperand.isConstant()) {
            return new OperableNumber(getValue());
        }
        switch (mathOperation) {
            case ADDITION:
                if (firstOperand.isConstant() && firstOperand.getValue().doubleValue() == 0) {
                    return secondOperand.simplify();
                } else if (secondOperand.isConstant() && secondOperand.getValue().doubleValue() == 0) {
                    return firstOperand.simplify();
                }
                firstOperand = firstOperand.simplify();
                secondOperand = secondOperand.simplify();
                return this;
            case SUBTRACTION:
                if (secondOperand.isConstant() && secondOperand.getValue().doubleValue() == 0) {
                    return firstOperand.simplify();
                }
                firstOperand = firstOperand.simplify();
                secondOperand = secondOperand.simplify();
                return this;

            case MULTIPLICATION:
                if (firstOperand.isConstant()) {
                    if (firstOperand.getValue().doubleValue() == 0) {
                        return new OperableNumber(0);
                    } else if (firstOperand.getValue().doubleValue() == 1) {
                        return secondOperand;
                    }
                } else if (secondOperand.isConstant()) {
                    if (secondOperand.getValue().doubleValue() == 0) {
                        return new OperableNumber(0);
                    } else if (secondOperand.getValue().doubleValue() == 1) {
                        return firstOperand;
                    }
                }
                firstOperand = firstOperand.simplify();
                secondOperand = secondOperand.simplify();
                return this;
            case DIVISION:
                if (firstOperand.isConstant() && firstOperand.getValue().doubleValue() == 0) {
                    return new OperableNumber(0);
                } else if (secondOperand.isConstant() && secondOperand.getValue().doubleValue() == 1) {
                    return firstOperand;
                }
                firstOperand = firstOperand.simplify();
                secondOperand = secondOperand.simplify();
                return this;
            case EXPONENTIATION:
                if (firstOperand.isConstant()) {
                    if (firstOperand.getValue().doubleValue() == 0) {
                        return new OperableNumber(0);
                    } else if (firstOperand.getValue().doubleValue() == 1) {
                        return new OperableNumber(1);
                    }
                } else if (secondOperand.isConstant()) {
                    if (secondOperand.getValue().doubleValue() == 0) {
                        return new OperableNumber(1);
                    } else if (secondOperand.getValue().doubleValue() == 1) {
                        return firstOperand;
                    }
                }
                firstOperand = firstOperand.simplify();
                secondOperand = secondOperand.simplify();
                return this;
            default:
                firstOperand = firstOperand.simplify();
                secondOperand = secondOperand.simplify();
                return this;
        }
    }
}
