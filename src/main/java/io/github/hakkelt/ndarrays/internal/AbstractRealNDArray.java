/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\internal\AbstractRealNDArray.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.internal;

import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.NDArrayUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

public abstract class AbstractRealNDArray<T extends Number> extends AbstractNDArray<T,T> implements RealNDArray<T> {

    @Override
    public String dataTypeAsString() {
        return ((Class<?>) dtype2()).getSimpleName();
    }

    @Override
    public NDArray<T> copyFrom(NDArray<?> array) {
        NDArrayUtils.checkShapeCompatibility(this, array.shape());
        return new CopyFromOperations<T,T>().copyFrom(this, array);
    }

    @Override
    protected T getRealUnchecked(int linearIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected T getRealUnchecked(int... indices) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected T getImagUnchecked(int linearIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected T getImagUnchecked(int... indices) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setRealUnchecked(T value, int linearIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setRealUnchecked(T value, int... indices) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setImagUnchecked(T value, int linearIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setImagUnchecked(T value, int... indices) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String printItem(int index, String format) {
        return String.format(format, get(index));
    }

    @Override
    protected T accumulate(Float acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    @Override
    protected T accumulate(Double acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    @Override
    protected T accumulate(Byte acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    @Override
    protected T accumulate(Short acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    @Override
    protected T accumulate(Integer acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    @Override
    protected T accumulate(Long acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    @Override
    protected T accumulate(BigInteger acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    @Override
    protected T accumulate(BigDecimal acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    @Override
    protected T accumulate(Complex acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected T accumulate(Byte acc, Number value, AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.byteValue() + value.byteValue());
            case SUBTRACT: return wrapValue(acc.byteValue() - value.byteValue());
            case MULTIPLY: return wrapValue(acc.byteValue() * value.byteValue());
            case DIVIDE: default: return wrapValue(acc.byteValue() / value.byteValue());
        }
    }

    @Override
    protected T accumulate(Short acc, Number value, AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.shortValue() + value.shortValue());
            case SUBTRACT: return wrapValue(acc.shortValue() - value.shortValue());
            case MULTIPLY: return wrapValue(acc.shortValue() * value.shortValue());
            case DIVIDE: default: return wrapValue(acc.shortValue() / value.shortValue());
        }
    }

    @Override
    protected T accumulate(Integer acc, Number value, AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.intValue() + value.intValue());
            case SUBTRACT: return wrapValue(acc.intValue() - value.intValue());
            case MULTIPLY: return wrapValue(acc.intValue() * value.intValue());
            case DIVIDE: default: return wrapValue(acc.intValue() / value.intValue());
        }
    }

    @Override
    protected T accumulate(Long acc, Number value, AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.longValue() + value.longValue());
            case SUBTRACT: return wrapValue(acc.longValue() - value.longValue());
            case MULTIPLY: return wrapValue(acc.longValue() * value.longValue());
            case DIVIDE: default: return wrapValue(acc.longValue() / value.longValue());
        }
    }

    @Override
    protected T accumulate(Float acc, Number value, AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.floatValue() + value.floatValue());
            case SUBTRACT: return wrapValue(acc.floatValue() - value.floatValue());
            case MULTIPLY: return wrapValue(acc.floatValue() * value.floatValue());
            case DIVIDE: default: return wrapValue(acc.floatValue() / value.floatValue());
        }
    }

    @Override
    protected T accumulate(Double acc, Number value, AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.doubleValue() + value.doubleValue());
            case SUBTRACT: return wrapValue(acc.doubleValue() - value.doubleValue());
            case MULTIPLY: return wrapValue(acc.doubleValue() * value.doubleValue());
            case DIVIDE: default: return wrapValue(acc.doubleValue() / value.doubleValue());
        }
    }

    protected BigInteger toBigInteger(Number value) {
        if (value instanceof BigInteger)
            return (BigInteger) value;
        if (value instanceof BigDecimal)
            return ((BigDecimal) value).toBigInteger();
        return BigDecimal.valueOf(value.doubleValue()).toBigInteger();
    }

    @Override
    protected T accumulate(BigInteger acc, Number value, AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.add(toBigInteger(value)));
            case SUBTRACT: return wrapValue(acc.subtract(toBigInteger(value)));
            case MULTIPLY: return wrapValue(acc.multiply(toBigInteger(value)));
            case DIVIDE: default: return wrapValue(acc.divide(toBigInteger(value)));
        }
    }

    protected BigDecimal toBigDecimal(Number value) {
        if (value instanceof BigInteger)
            return new BigDecimal((BigInteger) value);
        if (value instanceof BigDecimal)
            return (BigDecimal) value;
        return BigDecimal.valueOf(value.doubleValue());
    }

    @Override
    protected T accumulate(BigDecimal acc, Number value, AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.add(toBigDecimal(value)));
            case SUBTRACT: return wrapValue(acc.subtract(toBigDecimal(value)));
            case MULTIPLY: return wrapValue(acc.multiply(toBigDecimal(value)));
            case DIVIDE: default: return wrapValue(acc.divide(toBigDecimal(value)));
        }
    }

    @Override
    protected T accumulate(Complex acc, Number value, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected T accumulate(Complex acc, Complex value, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected AbstractRealNDArray<T> createNewRealNDArrayOfSameTypeAsMe(int... shape) {
        return (AbstractRealNDArray<T>)createNewNDArrayOfSameTypeAsMe(shape);
    }

    @Override
    protected T wrapRealValue(Number value) {
        return wrapValue(value);
    }

    @Override
    protected T zeroT2() {
        return zeroT();
    }

    @Override
    protected long countNonZero() {
        return stream().filter(item -> item.doubleValue() != 0.).count();
    }

    @Override
    protected Class<?> dtype2() {
        return dtype();
    }

    @Override
    protected Collector<Object,List<Object>,NDArray<T>> getCollectorInternal(int[] shape) {
        return new RealNDArrayCollector<>(createNewNDArrayOfSameTypeAsMe(shape));
    }

    @Override
    protected Collector<Object,List<Object>,NDArray<T>> getRealCollectorInternal(int[] shape) {
        return getCollectorInternal(shape);
    }

}
