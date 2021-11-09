package io.github.hakkelt.ndarrays;

/**
 * Abstract NDArray class for double (64-bit floating point) values.
 */
public abstract class AbstractDoubleNDArray extends AbstractRealNDArray<Double> {

    protected AbstractDoubleNDArray() {}

    protected Double wrapValue(Number value) {
        return Double.valueOf(value.doubleValue());
    }

    protected Double zeroT() {
        return Double.valueOf(0.f);
    }

    protected Double wrapValue(Object value) {
        return Double.valueOf(((Number) value).doubleValue());
    }

    public Class<?> dtype() {
        return Double.class;
    }

    public void set(Double real, int linearIndex) {
        set((Number) real, linearIndex);
    }

    protected Double accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Double acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?> ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}