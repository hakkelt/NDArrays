package io.github.hakkelt.ndarrays;

import java.math.BigInteger;

import java.math.BigDecimal;

import io.github.hakkelt.ndarrays.internal.AbstractRealNDArray;
import io.github.hakkelt.ndarrays.internal.AccumulateOperators;

/**
 * Abstract NDArray class for arbitrary precision floating point values.
 */
public abstract class AbstractBigDecimalNDArray extends AbstractRealNDArray<BigDecimal> {

    protected AbstractBigDecimalNDArray() {
    }

    @Override
    protected String printItem(int index, String format) {
        return String.format(format, get(index).doubleValue());
    }

    @Override
    protected BigDecimal zeroT() {
        return BigDecimal.ZERO;
    }

    @Override
    protected BigDecimal wrapValue(Number value) {
        if (value instanceof BigInteger)
            return new BigDecimal((BigInteger) value);
        if (value instanceof BigDecimal)
            return (BigDecimal) value;
        return BigDecimal.valueOf(value.doubleValue());
    }

    @Override
    protected BigDecimal wrapValue(Object value) {
        return wrapValue((Number) value);
    }

    @Override
    public Class<?> dtype() {
        return BigDecimal.class;
    }

    @Override
    public void set(Number value, int linearIndex) {
        set(wrapValue(value), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        set(wrapValue(value), indices);
    }

    @Override
    protected double absSum() {
        return stream().reduce(zeroT(), (a, b) -> a.add(b.abs())).doubleValue();
    }

    @Override
    protected BigDecimal accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        BigDecimal acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}

