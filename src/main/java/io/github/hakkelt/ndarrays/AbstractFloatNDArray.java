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

    protected Float oneT() {
        return Float.valueOf(1.f);
    }

    protected Float wrapValue(Object value) {
        if (value instanceof Number) {
            return Float.valueOf(((Number)value).floatValue());
        }
        throw new UnsupportedOperationException();
    }
    
    public Object eltype() {
        return Float.class;
    }

    public void set(Float real, int linearIndex) {
        set((Number)real, linearIndex);
    }
    
    protected Float accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object ...objects) {
        Float acc = get(linearIndex);
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