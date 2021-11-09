package io.github.hakkelt.ndarrays;

/**
 * Abstract NDArray class for short (16-bit integer) values.
 */
public abstract class AbstractShortNDArray extends AbstractRealNDArray<Short> {

    protected AbstractShortNDArray() {}
    
    protected Short wrapValue(Number value) {
        return Short.valueOf(value.shortValue());
    }

    protected Short zeroT() {
        return Short.valueOf((short) 0);
    }

    protected Short wrapValue(Object value) {
        if (value instanceof Number) {
            return Short.valueOf(((Number)value).shortValue());
        }
        throw new UnsupportedOperationException();
    }

    public Class<?> dtype() {
        return Short.class;
    }

    public void set(Short real, int linearIndex) {
        set((Number)real, linearIndex);
    }

    @Override
    protected double absSum() {
        return stream().mapToLong(Short::longValue).reduce(0, (a,b) -> a + Math.abs(b));
    }

    protected Short accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object ...objects) {
        Short acc = get(linearIndex);
        for (Object item : objects) {
            if (item instanceof NDArray<?>) {
                acc = accumulate(acc, ((NDArray<?>)item), linearIndex, operator);
            } else if (item instanceof Number) {
                acc = accumulate(acc, (Number)item, operator);
            } else throw new IllegalArgumentException();
        }
        return acc;
    }

}