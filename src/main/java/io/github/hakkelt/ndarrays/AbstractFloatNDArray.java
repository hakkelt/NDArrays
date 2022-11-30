/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\AbstractByteNDArrayTemplate.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays;

import io.github.hakkelt.ndarrays.internal.AbstractRealNDArray;
import io.github.hakkelt.ndarrays.internal.AccumulateOperators;

/**
 * Abstract NDArray class for float (8-bit integer) values.
 */
public abstract class AbstractFloatNDArray extends AbstractRealNDArray<Float> {

    protected AbstractFloatNDArray() {
    }

    @Override
    protected Float zeroT() {
        return Float.valueOf((float) 0);
    }

    @Override
    protected Float oneT() {
        return Float.valueOf((float) 1);
    }

    @Override
    protected Float wrapValue(Number value) {
        return Float.valueOf(value.floatValue());
    }

    @Override
    protected Float wrapValue(Object value) {
        return wrapValue((Number) value);
    }

    @Override
    public Class<?> dtype() {
        return Float.class;
    }

    @Override
    public void set(Number value, int linearIndex) {
        set(value.floatValue(), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        set(value.floatValue(), indices);
    }

    @Override
    protected double absSum() {
        return stream().mapToLong(Float::longValue).reduce(0, (a, b) -> a + Math.abs(b));
    }

    @Override
    protected Float accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Float acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}
