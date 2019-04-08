package rmi;

import java.util.function.BiFunction;

public enum MathOperations {

    ADD((o1,o2) -> o1 + o2),
    SUB((o1, o2) -> o1 - o2),
    MPY((o1, o2) -> o1 * o2),
    DIV((o1, o2) -> o1 / o2),
    MOD((o1, o2) -> o1 % o2);

    private final BiFunction<Double, Double, Double> function;

    MathOperations(final BiFunction<Double, Double, Double> function) {
        this.function = function;
    }

    public BiFunction<Double, Double, Double> getFunction() {
        return function;
    }

    public Double apply(Double o1, Double o2) {
        return this.getFunction().apply(o1,o2);
    }
}
