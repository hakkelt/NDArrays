package io.github.hakkelt.ndarrays;

/**
 * Abstract NDArray class for long (64-bit integer) values.
 */
public abstract class AbstractLongNDArray extends AbstractRealNDArray<Long> {

    protected AbstractLongNDArray() {}

    protected Long wrapValue(Number value) {
        return Long.valueOf(value.longValue());
    }

    protected Long zeroT() {
        return Long.valueOf(0);
    }

    protected Long wrapValue(Object value) {
        return Long.valueOf(((Number) value).longValue());
    }

    public Class<?> dtype() {
        return Long.class;
    }

    public void set(Long real, int linearIndex) {
        set((Number) real, linearIndex);
    }

    protected Long accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Long acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}