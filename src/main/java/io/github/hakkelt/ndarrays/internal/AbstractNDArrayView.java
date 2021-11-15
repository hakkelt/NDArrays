/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\internal\AbstractNDArrayView.java
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

abstract class AbstractNDArrayView<T, T2 extends Number> extends AbstractNDArray<T,T2> {

    protected AbstractNDArray<T,T2> parent;

    @Override
    public String getNamePrefix() {
        return parent.getNamePrefix();
    }

    @Override
    public Class<?> dtype() {
        return parent.dtype();
    }

    @Override
    public Class<?> dtype2() {
        return parent.dtype2();
    }

    @Override
    protected String printItem(int index, String format) {
        return parent.printItem(index, format);
    }

    @Override
    public String dataTypeAsString() {
        return parent.dataTypeAsString();
    }

    public AbstractNDArray<T,T2> getParent() {
        return parent;
    }

    @Override
    protected long countNonZero() {
        if (dtype() == Complex.class)
            return stream().filter(item -> item != Complex.ZERO).count();
        return stream().filter(item -> ((Number)item).doubleValue() != 0.).count();
    }

    @Override
    protected T zeroT() {
        return parent.zeroT();
    }

    @Override
    protected T2 zeroT2() {
        return parent.zeroT2();
    }

    @Override
    protected double absSum() {
        if (dtype() == Complex.class)
            return stream().map(value -> ((Complex)value).abs()).reduce(0., Double::sum);
        return stream().map(value -> ((Number)value).doubleValue()).reduce(0., (a,b) -> Double.sum(a, Math.abs(b)));
    }

    @Override
    protected T accumulate(T acc, T value, AccumulateOperators operator) {
        return parent.accumulate(acc, value, operator);
    }

    @Override
    protected T accumulate(Float acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    @Override
    protected T accumulate(Double acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    @Override
    protected T accumulate(Byte acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    @Override
    protected T accumulate(Short acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    @Override
    protected T accumulate(Integer acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    @Override
    protected T accumulate(Long acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    @Override
    protected T accumulate(Complex acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    @Override
    protected T accumulate(BigInteger acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    @Override
    protected T accumulate(BigDecimal acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
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
        throw new IllegalArgumentException();
    }

    @Override
    protected T accumulate(Complex acc, Complex value, AccumulateOperators operator) {
        throw new IllegalArgumentException();
    }

    private T specializedAccumulate(T acc, NDArray<?> item, int linearIndex, AccumulateOperators operator) {
        if (dtype() == Float.class)
            return accumulate((Float)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (dtype() == Double.class)
            return accumulate((Double)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (dtype() == Byte.class)
            return accumulate((Byte)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (dtype() == Short.class)
            return accumulate((Short)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (dtype() == Integer.class)
            return accumulate((Integer)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (dtype() == Long.class)
            return accumulate((Long)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (dtype() == Complex.class)
            return accumulate((Complex)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (dtype() == BigInteger.class)
            return accumulate((BigInteger)acc, ((NDArray<?>)item), linearIndex, operator);
        else
            return accumulate((BigDecimal)acc, ((NDArray<?>)item), linearIndex, operator);
    }

    private T specializedAccumulate(T acc, Number item, AccumulateOperators operator) {
        if (dtype() == Float.class)
            return accumulate((Float)acc, item, operator);
        else if (dtype() == Double.class)
            return accumulate((Double)acc, item, operator);
        else if (dtype() == Byte.class)
            return accumulate((Byte)acc, item, operator);
        else if (dtype() == Short.class)
            return accumulate((Short)acc, item, operator);
        else if (dtype() == Integer.class)
            return accumulate((Integer)acc, item, operator);
        else if (dtype() == Long.class)
            return accumulate((Long)acc, item, operator);
        else if (dtype() == Complex.class)
            return accumulate((Complex)acc, item, operator);
        else if (dtype() == BigInteger.class)
            return accumulate((BigInteger)acc, item, operator);
        else
            return accumulate((BigDecimal)acc, item, operator);
    }

    @Override
    protected T accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        T acc = get(linearIndex);
        for (Object item : objects)
            if (item instanceof NDArray<?>)
                acc = specializedAccumulate(acc, (NDArray<?>)item, linearIndex, operator);
            else if (item instanceof Complex && dtype() == Complex.class)
                acc = accumulate((Complex)acc, (Complex)item, operator);
            else
                acc = specializedAccumulate(acc, (Number)item, operator);
        return wrapValue(acc);
    }

    @Override
    protected Collector<Object,List<Object>,NDArray<T>> getCollectorInternal(int[] shape) {
        return parent.getCollectorInternal(shape);
    }

    @Override
    protected Collector<Object,List<Object>,NDArray<T2>> getRealCollectorInternal(int[] shape) {
        return parent.getRealCollectorInternal(shape);
    }

    @Override
    public NDArray<T> similar() {
        return parent.createNewNDArrayOfSameTypeAsMe(shape);
    }

    @Override
    public NDArray<T> copy() {
        NDArray<T> newInstance = parent.createNewNDArrayOfSameTypeAsMe(shape);
        return newInstance.copyFrom(this);
    }

    public int[] linearIndexToViewIndices(int linearIndex) {
        return NDArrayUtils.linearIndexToCartesianIndices(
            NDArrayUtils.unwrap(linearIndex, length()), multipliers);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getTypeName() {
        return "NDArrayView";
    }

    @Override
    protected NDArray<T> createNewNDArrayOfSameTypeAsMe(int... shape) {
        return parent.createNewNDArrayOfSameTypeAsMe(shape);
    }

    @Override
    protected NDArray<T2> createNewRealNDArrayOfSameTypeAsMe(int... shape) {
        return parent.createNewRealNDArrayOfSameTypeAsMe(shape);
    }

    @Override
    protected T wrapValue(Object value) {
        return parent.wrapValue(value);
    }

    @Override
    protected T wrapValue(Number value) {
        return parent.wrapValue(value);
    }

    @Override
    protected T2 wrapRealValue(Number value) {
        return parent.wrapRealValue(value);
    }

}
