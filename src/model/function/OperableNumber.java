package model.function;

/**
 * User: Javier Isoldi
 * Date: 12/13/12
 * Time: 3:40 PM
 */
public class OperableNumber implements OperableElement {
    private Number value;

    public OperableNumber(Number value) {
        this.value = value;
    }

    public Number getValue() {
        return value;
    }

    public OperableElement getDerivative() {
        return new OperableNumber(0);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public boolean isConstant() {
        return true;
    }

    public OperableElement simplify() {
        return this;
    }
}
