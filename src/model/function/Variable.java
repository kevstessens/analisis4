package model.function;

/**
 * User: Javier Isoldi
 * Date: 12/13/12
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
