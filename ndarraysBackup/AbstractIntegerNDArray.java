package io.github.hakkelt.ndarrays;

/**
 * Abstract NDArray class for int (32-bit integer) values.
 */
public abstract class AbstractIntegerNDArray extends AbstractRealNDArray<Integer> {

    protected AbstractIntegerNDArray() {}

    protected Integer wrapValue(Number value) {
        return Integer.valueOf(value.intValue());
    }

    protected Integer zeroT() {
        return Integer.valueOf(0);
    }

    protected Integer wrapValue(Object value) {
        return Integer.valueOf(((Number) value).intValue());
    }

    public Class<?> dtype() {
        return Integer.class;
    }

    public void set(Integer real, int linearIndex) {
        set((Number) real, linearIndex);
    }

    @Override
    protected double absSum() {
        return stream().mapToLong(Integer::longValue).reduce(0, (a, b) -> a + Math.abs(b));
    }

    protected Integer accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Integer acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}