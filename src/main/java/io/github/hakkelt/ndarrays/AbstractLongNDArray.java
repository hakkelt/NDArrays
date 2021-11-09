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
 * Abstract NDArray class for long (8-bit integer) values.
 */
public abstract class AbstractLongNDArray extends AbstractRealNDArray<Long> {

    protected AbstractLongNDArray() {
    }

    @Override
    protected Long zeroT() {
        return Long.valueOf((long) 0);
    }

    @Override
    protected Long wrapValue(Number value) {
        return Long.valueOf(value.longValue());
    }

    @Override
    protected Long wrapValue(Object value) {
        return wrapValue((Number) value);
    }

    @Override
    public Class<?> dtype() {
        return Long.class;
    }

    @Override
    public void set(Number value, int linearIndex) {
        set(value.longValue(), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        set(value.longValue(), indices);
    }

    @Override
    protected double absSum() {
        return stream().mapToLong(Long::longValue).reduce(0, (a, b) -> a + Math.abs(b));
    }

    @Override
    protected Long accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Long acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}
