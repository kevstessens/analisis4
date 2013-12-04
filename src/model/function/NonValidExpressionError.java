package model.function;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 7:12 PM
 */
public class NonValidExpressionError extends Exception {
    int nonValidPositionInExpression;

    public NonValidExpressionError(int nonValidPositionInExpression) {
        super();
        this.nonValidPositionInExpression = nonValidPositionInExpression;
    }

    public int getNonValidPositionInExpression() {
        return nonValidPositionInExpression;
    }
}
