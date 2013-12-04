package model.function;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 4:45 PM
 */
public class Variable implements OperableElement {

    private Number value;
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    public Number getValue(){
        return value;
    }

    public OperableElement getDerivative() {
        return new OperableNumber(1);
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isConstant() {
        return false;
    }

    public OperableElement simplify() {
        return this;
    }
}
