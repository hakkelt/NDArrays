package io.github.hakkelt.ndarrays;

abstract class AbstractDoubleNDArray extends AbstractRealNDArray<Double> {

    protected Double wrapValue(Number value) {
        return Double.valueOf(value.doubleValue());
    }
    
    protected Double zeroT() {
        return Double.valueOf(0.f);
    }

    protected Double oneT() {
        return Double.valueOf(1.f);
    }

    protected Double wrapValue(Object value) {
        if (value instanceof Number) {
            return Double.valueOf(((Number)value).doubleValue());
        }
        throw new UnsupportedOperationException();
    }
    
    public Object eltype() {
        return Double.class;
    }

    public void set(Double real, int linearIndex) {
        set((Number)real, linearIndex);
    }
    
    protected Double accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object ...objects) {
        Double acc = get(linearIndex);
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