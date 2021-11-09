/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\AbstractByteNDArrayTemplate.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:49 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays;

import io.github.hakkelt.ndarrays.internal.AbstractRealNDArray;
import io.github.hakkelt.ndarrays.internal.AccumulateOperators;

/**
 * Abstract NDArray class for short (8-bit integer) values.
 */
public abstract class AbstractShortNDArray extends AbstractRealNDArray<Short> {

    protected AbstractShortNDArray() {
    }

    @Override
    protected Short zeroT() {
        return Short.valueOf((short) 0);
    }

    @Override
    protected Short wrapValue(Number value) {
        return Short.valueOf(value.shortValue());
    }

    @Override
    protected Short wrapValue(Object value) {
        return wrapValue((Number) value);
    }

    @Override
    public Class<?> dtype() {
        return Short.class;
    }

    @Override
    public void set(Number value, int linearIndex) {
        set(value.shortValue(), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        set(value.shortValue(), indices);
    }

    @Override
    protected double absSum() {
        return stream().mapToLong(Short::longValue).reduce(0, (a, b) -> a + Math.abs(b));
    }

    @Override
    protected Short accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Short acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}
