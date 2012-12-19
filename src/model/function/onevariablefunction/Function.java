package model.function.onevariablefunction;

/**
 * User: Javier Isoldi
 * Date: 12/13/12
 * Time: 2:49 PM
 */
public interface Function {

    public Number functionInX(Number x);

    public Function getDerivative();

    public String toString();
}
