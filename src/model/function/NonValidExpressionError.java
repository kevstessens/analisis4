package model.function;

/**
 * User: Javier Isoldi
 * Date: 12/13/12
 * Time: 7:12 PM
 */
public class NonValidExpressionError extends Exception {
    int nonValidPositionInExpression;

    public NonValidExpressionError(int nonValidPositionInExpression) {
        super();
        this.nonValidPositionInExpression = nonValidPositionInExpression;
    }
}
