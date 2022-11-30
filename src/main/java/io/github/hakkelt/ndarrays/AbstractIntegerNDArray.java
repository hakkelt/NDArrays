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
 * Abstract NDArray class for int (8-bit integer) values.
 */
public abstract class AbstractIntegerNDArray extends AbstractRealNDArray<Integer> {

    protected AbstractIntegerNDArray() {
    }

    @Override
    protected Integer zeroT() {
        return Integer.valueOf((int) 0);
    }

    @Override
    protected Integer oneT() {
        return Integer.valueOf((int) 1);
    }

    @Override
    protected Integer wrapValue(Number value) {
        return Integer.valueOf(value.intValue());
    }

    @Override
    protected Integer wrapValue(Object value) {
        return wrapValue((Number) value);
    }

    @Override
    public Class<?> dtype() {
        return Integer.class;
    }

    @Override
    public void set(Number value, int linearIndex) {
        set(value.intValue(), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        set(value.intValue(), indices);
    }

    @Override
    protected double absSum() {
        return stream().mapToLong(Integer::longValue).reduce(0, (a, b) -> a + Math.abs(b));
    }

    @Override
    protected Integer accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Integer acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}
