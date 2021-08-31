package io.github.hakkelt.ndarrays;

public abstract class AbstractIntegerNDArray extends AbstractRealNDArray<Integer> {

    protected Integer wrapValue(Number value) {
        return Integer.valueOf(value.intValue());
    }

    protected Integer zeroT() {
        return Integer.valueOf(0);
    }

    protected Integer oneT() {
        return Integer.valueOf(1);
    }

    protected Integer wrapValue(Object value) {
        if (value instanceof Number) {
            return Integer.valueOf(((Number)value).intValue());
        }
        throw new UnsupportedOperationException();
    }

    public Object eltype() {
        return Integer.class;
    }

    public void set(Integer real, int linearIndex) {
        set((Number)real, linearIndex);
    }

    @Override
    protected double absSum() {
        return stream().mapToLong(Integer::longValue).reduce(0, (a,b) -> a + Math.abs(b));
    }

    protected Integer accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object ...objects) {
        Integer acc = get(linearIndex);
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