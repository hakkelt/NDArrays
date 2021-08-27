package io.github.hakkelt.ndarrays;

abstract class AbstractLongNDArray extends AbstractRealNDArray<Long> {

    protected Long wrapValue(Number value) {
        return Long.valueOf(value.longValue());
    }
    
    protected Long zeroT() {
        return Long.valueOf(0);
    }

    protected Long oneT() {
        return Long.valueOf(1);
    }

    protected Long wrapValue(Object value) {
        if (value instanceof Number) {
            return Long.valueOf(((Number)value).longValue());
        }
        throw new UnsupportedOperationException();
    }
    
    public Object eltype() {
        return Long.class;
    }

    public void set(Long real, int linearIndex) {
        set((Number)real, linearIndex);
    }
    
    protected Long accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object ...objects) {
        Long acc = get(linearIndex);
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