package io.github.hakkelt.ndarrays;

abstract class AbstractByteNDArray extends AbstractRealNDArray<Byte> {

    protected Byte wrapValue(Number value) {
        return Byte.valueOf(value.byteValue());
    }
    
    protected Byte zeroT() {
        return Byte.valueOf((byte) 0);
    }

    protected Byte oneT() {
        return Byte.valueOf((byte) 1);
    }

    protected Byte wrapValue(Object value) {
        if (value instanceof Number) {
            return Byte.valueOf(((Number)value).byteValue());
        }
        throw new UnsupportedOperationException();
    }
    
    public Object eltype() {
        return Byte.class;
    }

    public void set(Byte real, int linearIndex) {
        set((Number)real, linearIndex);
    }
    
    protected Byte accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object ...objects) {
        Byte acc = get(linearIndex);
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