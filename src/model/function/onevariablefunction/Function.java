package model.function.onevariablefunction;

/**
 * User: Kev Stessens
 * Date: 12/03/13
 * Time: 2:49 PM
 */
public interface Function {

    public Number functionInX(Number x);

    public Function getDerivative();

    public String toString();
}
