package model.function;

/**
 * User: Javier Isoldi
 * Date: 12/13/12
 * Time: 3:38 PM
 */
public interface OperableElement {
    public Number getValue();

    public OperableElement getDerivative();

    public boolean isConstant();

    OperableElement simplify();

}
