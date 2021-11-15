/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\AbstractByteNDArray.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays;

import io.github.hakkelt.ndarrays.internal.AbstractRealNDArray;
import io.github.hakkelt.ndarrays.internal.AccumulateOperators;

/**
 * Abstract NDArray class for byte (8-bit integer) values.
 */
public abstract class AbstractByteNDArray extends AbstractRealNDArray<Byte> {

    protected AbstractByteNDArray() {
    }

    @Override
    protected Byte zeroT() {
        return Byte.valueOf((byte) 0);
    }

    @Override
    protected Byte wrapValue(Number value) {
        return Byte.valueOf(value.byteValue());
    }

    @Override
    protected Byte wrapValue(Object value) {
        return wrapValue((Number) value);
    }

    @Override
    public Class<?> dtype() {
        return Byte.class;
    }

    @Override
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

    @Override
    protected Byte accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Byte acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}
