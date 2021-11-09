package io.github.hakkelt.ndarrays;

/**
 * Abstract NDArray class for byte (8-bit integer) values.
 */
public abstract class AbstractByteNDArray extends AbstractRealNDArray<Byte> {

    protected AbstractByteNDArray() {
    }

    protected Byte wrapValue(Number value) {
        return Byte.valueOf(value.byteValue());
    }

    protected Byte zeroT() {
        return Byte.valueOf((byte) 0);
    }

    protected Byte wrapValue(Object value) {
        return Byte.valueOf(((Number) value).byteValue());
    }

    public Class<?> dtype() {
        return Byte.class;
    }

    public void set(Number value, int linearIndex) {
        set(value.byteValue(), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        set(value.byteValue(), indices);
    }

    @Override
    protected double absSum() {
        return stream().mapToLong(Byte::longValue).reduce(0, (a, b) -> a + Math.abs(b));
    }

    protected Byte accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Byte acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}