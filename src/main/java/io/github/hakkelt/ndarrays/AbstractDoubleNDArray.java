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
 * Abstract NDArray class for double (8-bit integer) values.
 */
public abstract class AbstractDoubleNDArray extends AbstractRealNDArray<Double> {

    protected AbstractDoubleNDArray() {
    }

    @Override
    protected Double zeroT() {
        return Double.valueOf((double) 0);
    }

    @Override
    protected Double wrapValue(Number value) {
        return Double.valueOf(value.doubleValue());
    }

    @Override
    protected Double wrapValue(Object value) {
        return wrapValue((Number) value);
    }

    @Override
    public Class<?> dtype() {
        return Double.class;
    }

    @Override
    public void set(Number value, int linearIndex) {
        set(value.doubleValue(), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        set(value.doubleValue(), indices);
    }

    @Override
    protected double absSum() {
        return stream().mapToLong(Double::longValue).reduce(0, (a, b) -> a + Math.abs(b));
    }

    @Override
    protected Double accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Double acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}
