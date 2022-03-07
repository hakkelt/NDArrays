package io.github.hakkelt.ndarrays;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.github.hakkelt.ndarrays.internal.AbstractRealNDArray;
import io.github.hakkelt.ndarrays.internal.AccumulateOperators;

/**
 * Abstract NDArray class for arbitrary precision integer values.
 */
public abstract class AbstractBigIntegerNDArray extends AbstractRealNDArray<BigInteger> {

    protected AbstractBigIntegerNDArray() {
    }

    @Override
    protected String printItem(int index, String format) {
        return String.format(format, get(index).longValue());
    }

    @Override
    protected BigInteger zeroT() {
        return BigInteger.ZERO;
    }

    @Override
    protected BigInteger wrapValue(Number value) {
        if (value instanceof BigInteger)
            return (BigInteger) value;
        if (value instanceof BigDecimal)
            return ((BigDecimal) value).toBigInteger();
        return BigDecimal.valueOf(value.doubleValue()).toBigInteger();
    }

    @Override
    protected BigInteger wrapValue(Object value) {
        return wrapValue((Number) value);
    }

    @Override
    public Class<?> dtype() {
        return BigInteger.class;
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
    protected BigInteger accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        BigInteger acc = get(linearIndex);
        for (Object item : objects)
            acc = item instanceof NDArray<?>
                    ? accumulate(acc, ((NDArray<?>) item), linearIndex, operator)
                    : accumulate(acc, (Number) item, operator);
        return acc;
    }

}

