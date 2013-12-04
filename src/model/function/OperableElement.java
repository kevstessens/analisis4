package model.function;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 3:38 PM
 */
public interface OperableElement {
    public Number getValue();

    public OperableElement getDerivative();

    public boolean isConstant();

    OperableElement simplify();

}
