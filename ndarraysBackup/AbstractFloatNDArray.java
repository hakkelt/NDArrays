package io.github.hakkelt.ndarrays;

/**
 * Abstract NDArray class for double (64-bit floating point) values.
 */
public abstract class AbstractFloatNDArray extends AbstractRealNDArray<Float> {

    protected AbstractFloatNDArray() {}

    protected Float wrapValue(Number value) {
        return Float.valueOf(value.floatValue());
    }

    protected Float zeroT() {
        return Float.valueOf(0.f);
    }

    protected Float wrapValue(Object value) {
        return Float.valueOf(((Number) value).floatValue());
    }

    public Class<?> dtype() {
        return Float.class;
    }

    public void set(Float real, int linearIndex) {
        set((Number) real, linearIndex);
    }

    protected Float accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Float acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}