package io.github.hakkelt.ndarrays;

abstract class AbstractShortNDArray extends AbstractRealNDArray<Short> {

    protected Short wrapValue(Number value) {
        return Short.valueOf(value.shortValue());
    }

    protected Short zeroT() {
        return Short.valueOf((short) 0);
    }

    protected Short oneT() {
        return Short.valueOf((short) 1);
    }

    protected Short wrapValue(Object value) {
        if (value instanceof Number) {
            return Short.valueOf(((Number)value).shortValue());
        }
        throw new UnsupportedOperationException();
    }

    public Object eltype() {
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