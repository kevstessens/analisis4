package model.function.onevariablefunction;

import model.function.NonValidExpressionError;
import model.function.OperableElement;
import model.function.OperableNumber;
import model.function.Variable;
import model.function.mathoperators.BasicBinaryMathOperation;
import model.function.mathoperators.BasicUnitaryMathOperation;
import model.function.mathoperators.BinaryMathOperation;
import model.function.mathoperators.UnitaryMathOperation;

import java.util.Stack;

/**
 * User: Javier Isoldi
 * Date: 12/13/12
 * Time: 4:54 PM
 */
public class FunctionParser {

    private Stack<Character> operatorStack;
    private Stack<OperableElement> result;
    private Variable variable;

    public Function getFunctionFromMathExpression(String mathExpression) throws NonValidExpressionError {
        variable = new Variable("x");
        return new FunctionImplementation(variable, getOperableElementFormMathExpression(mathExpression));
    }

    private int getOperationPriority(Character c) {
        if (c == '^') {
            return 4;
        } else if (c == '*' || c == '/') {
            return 3;
        } else if (c == '+' || c == '-') {
            return 2;
        } else if (c == '(') {
            return 1;
        } else {
            return -1;
        }
    }

    private void pushOperator(Character operator) throws NonValidExpressionError {
        if (operatorStack.isEmpty()) {
            operatorStack.push(operator);
        } else {
            while (!operatorStack.isEmpty() &&
                    getOperationPriority(operator) <= getOperationPriority(operatorStack.peek())) {
                pushOperatorAsOperableElement(operatorStack.pop());
            }
            operatorStack.push(operator);
        }
    }

    private void pushOperatorAsOperableElement(Character operation) throws NonValidExpressionError {
        OperableElement secondOperable;
        switch (operation) {
            case ('*'):
                secondOperable = result.pop();
                result.push(new BinaryMathOperation(result.pop(), secondOperable, BasicBinaryMathOperation.MULTIPLICATION));
                break;
            case ('/'):
                secondOperable = result.pop();
                result.push(new BinaryMathOperation(result.pop(), secondOperable, BasicBinaryMathOperation.DIVISION));
                break;
            case ('+'):
                secondOperable = result.pop();
                result.push(new BinaryMathOperation(result.pop(), secondOperable, BasicBinaryMathOperation.ADDITION));
                break;
            case ('-'):
                secondOperable = result.pop();
                result.push(new BinaryMathOperation(result.pop(), secondOperable, BasicBinaryMathOperation.SUBTRACTION));
                break;
            case ('^'):
                secondOperable = result.pop();
                result.push(new BinaryMathOperation(result.pop(), secondOperable, BasicBinaryMathOperation.EXPONENTIATION));
                break;
            case ('('):
                throw new NonValidExpressionError(0);
            default:
        }
    }


    public OperableElement getOperableElementFormMathExpression(String mathExpression) throws NonValidExpressionError {
        result = new Stack<OperableElement>();
        operatorStack = new Stack<Character>();

        for (int i = 0; i < mathExpression.length(); i++) {
            Character actualChar = mathExpression.charAt(i);
            if ((actualChar < '(' || actualChar > '9' || actualChar == '´') && actualChar != ' ' && actualChar != 'x'
                    && actualChar != '^' && actualChar != 'l' && actualChar != 's' && actualChar != 'c'
                    && actualChar != 't') {
                throw new NonValidExpressionError(i);
            }
            switch (actualChar) {
                case ('*'):
                    pushOperator('*');
                    break;
                case ('/'):
                    pushOperator('/');
                    break;
                case ('+'):
                    pushOperator('+');
                    break;
                case ('-'):
                    pushOperator('-');
                    break;
                case ('^'):
                    pushOperator('^');
                    break;
                case ('('):
                    pushOpenBracket();
                    break;
                case (')'):
                    pushClosingBracket();
                    break;
                case (' '):
                    break;
                case ('x'):
                    result.push(variable);
                    break;
                case ('l'):
                    i = pushLogarithm(mathExpression, i);
                    break;
                case ('s'):
                    i = pushSin(mathExpression, i);
                    break;
                case ('c'):
                    i = pushCos(mathExpression, i);
                    break;
                case ('t'):
                    i = pushTan(mathExpression, i);
                    break;
                default:
                    i = pushNumber(mathExpression, i, actualChar);
            }
        }

        while (!operatorStack.isEmpty()) {
            pushOperatorAsOperableElement(operatorStack.pop());
        }
        OperableElement operableElement = result.pop();
        if (!result.isEmpty()) {
            throw new NonValidExpressionError(0);
        }
        return operableElement;
    }

    private int pushTan(String mathExpression, int i) throws NonValidExpressionError {
        Character actualChar = mathExpression.charAt(++i);
        if (actualChar == 'a') {
            actualChar = mathExpression.charAt(++i);
            if (actualChar == 'n') {
                actualChar = mathExpression.charAt(++i);
                if (actualChar == '(') {
                    operatorStack.push('t');
                } else {
                    throw new NonValidExpressionError(i);
                }
            } else {
                throw new NonValidExpressionError(i);
            }
        } else {
            throw new NonValidExpressionError(i);
        }
        return i;
    }

    private int pushCos(String mathExpression, int i) throws NonValidExpressionError {
        Character actualChar = mathExpression.charAt(++i);
        if (actualChar == 'o') {
            actualChar = mathExpression.charAt(++i);
            if (actualChar == 's') {
                actualChar = mathExpression.charAt(++i);
                if (actualChar == '(') {
                    operatorStack.push('c');
                } else {
                    throw new NonValidExpressionError(i);
                }
            } else {
                throw new NonValidExpressionError(i);
            }
        } else {
            throw new NonValidExpressionError(i);
        }
        return i;
    }

    private int pushSin(String mathExpression, int i) throws NonValidExpressionError {
        Character actualChar = mathExpression.charAt(++i);
        if (actualChar == 'i') {
            actualChar = mathExpression.charAt(++i);
            if (actualChar == 'n') {
                actualChar = mathExpression.charAt(++i);
                if (actualChar == '(') {
                    operatorStack.push('s');
                } else {
                    throw new NonValidExpressionError(i);
                }
            } else {
                throw new NonValidExpressionError(i);
            }
        } else {
            throw new NonValidExpressionError(i);
        }
        return i;
    }

    private int pushNumber(String mathExpression, int i, Character actualChar) throws NonValidExpressionError {
        String number = "";
        while ((actualChar >= '0' && actualChar <= '9') || actualChar == '.') {
            number = number + actualChar;
            if (i >= mathExpression.length() - 1) {
                break;
            }
            actualChar = mathExpression.charAt(++i);
        }
        if (i < mathExpression.length() - 1) {
            i--;
        }

        Double number1;
        try {
            number1 = Double.valueOf(number);
        } catch (NumberFormatException e) {
            throw new NonValidExpressionError(i);
        }
        result.push(new OperableNumber(number1));
        return i;
    }

    private int pushLogarithm(String mathExpression, int i) throws NonValidExpressionError {
        Character actualChar = mathExpression.charAt(++i);
        if (actualChar == 'o') {
            actualChar = mathExpression.charAt(++i);
            if (actualChar == 'g') {
                actualChar = mathExpression.charAt(++i);
                if (actualChar == '(') {
                    operatorStack.push('l');
                } else {
                    throw new NonValidExpressionError(i);
                }
            } else {
                throw new NonValidExpressionError(i);
            }
        } else if (actualChar == 'n') {
            actualChar = mathExpression.charAt(++i);
            if (actualChar == '(') {
                operatorStack.push('n');
            } else {
                throw new NonValidExpressionError(i);
            }
        } else {
            throw new NonValidExpressionError(i);
        }
        return i;
    }

    private void pushClosingBracket() throws NonValidExpressionError {
        if (operatorStack.isEmpty()) {
            throw new NonValidExpressionError(0);
        } else {
            while (operatorStack.peek() != '(' && operatorStack.peek() != 'l' && operatorStack.peek() != 'n'
                    && operatorStack.peek() != 's' && operatorStack.peek() != 'c' && operatorStack.peek() != 't') {
                pushOperatorAsOperableElement(operatorStack.pop());
                if (operatorStack.isEmpty()) {
                    throw new NonValidExpressionError(0);
                }
            }
            switch (operatorStack.pop()) {
                case '(':
                    break;
                case 'l':
                    result.push(new UnitaryMathOperation(result.pop(), BasicUnitaryMathOperation.LOG));
                    break;
                case 'n':
                    result.push(new UnitaryMathOperation(result.pop(), BasicUnitaryMathOperation.LN));
                    break;
                case 's':
                    result.push(new UnitaryMathOperation(result.pop(), BasicUnitaryMathOperation.SIN));
                    break;
                case 'c':
                    result.push(new UnitaryMathOperation(result.pop(), BasicUnitaryMathOperation.COS));
                    break;
                case 't':
                    result.push(new UnitaryMathOperation(result.pop(), BasicUnitaryMathOperation.TAN));
                    break;
                default:
                    break;
            }
        }
    }

    private void pushOpenBracket() {
        operatorStack.push('(');
    }
}
