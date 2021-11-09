package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

abstract class AbstractRealNDArray<T extends Number> extends AbstractNDArray<T,T> implements RealNDArray<T> {

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

    protected String printItem(int index, String format) {
        return String.format(format, get(index));
    }

    public String dataTypeAsString() {
        String[] str = dtype2().toString().split("\\.");
        return str[str.length - 1];
    }

    public NDArray<T> copyFrom(NDArray<?> array) {
        streamLinearIndices().parallel().forEach(i -> set(wrapValue(array.get(i)), i));
        return this;
    }

    protected T accumulate(Float acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    protected T accumulate(Double acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    protected T accumulate(Byte acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    protected T accumulate(Short acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    protected T accumulate(Integer acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    protected T accumulate(Long acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    protected T accumulate(Complex acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operation) {
        throw new UnsupportedOperationException();
    }

    protected T accumulate(Byte acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.byteValue() + value.byteValue());
            case SUBTRACT: return wrapValue(acc.byteValue() - value.byteValue());
            case MULTIPLY: return wrapValue(acc.byteValue() * value.byteValue());
            case DIVIDE: default: return wrapValue(acc.byteValue() / value.byteValue());
        }
    }

    protected T accumulate(Short acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.shortValue() + value.shortValue());
            case SUBTRACT: return wrapValue(acc.shortValue() - value.shortValue());
            case MULTIPLY: return wrapValue(acc.shortValue() * value.shortValue());
            case DIVIDE: default: return wrapValue(acc.shortValue() / value.shortValue());
        }
    }

    protected T accumulate(Integer acc, Number value, AbstractNDArray.AccumulateOperators operation) {
       switch (operation) { 
            case ADD: return wrapValue(acc.intValue() + value.intValue());
            case SUBTRACT: return wrapValue(acc.intValue() - value.intValue());
            case MULTIPLY: return wrapValue(acc.intValue() * value.intValue());
            case DIVIDE: default: return wrapValue(acc.intValue() / value.intValue());
        }
    }

    protected T accumulate(Long acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.longValue() + value.longValue());
            case SUBTRACT: return wrapValue(acc.longValue() - value.longValue());
            case MULTIPLY: return wrapValue(acc.longValue() * value.longValue());
            case DIVIDE: default: return wrapValue(acc.longValue() / value.longValue());
        }
    }

    protected T accumulate(Float acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.floatValue() + value.floatValue());
            case SUBTRACT: return wrapValue(acc.floatValue() - value.floatValue());
            case MULTIPLY: return wrapValue(acc.floatValue() * value.floatValue());
            case DIVIDE: default: return wrapValue(acc.floatValue() / value.floatValue());
        }
    }

    protected T accumulate(Double acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.doubleValue() + value.doubleValue());
            case SUBTRACT: return wrapValue(acc.doubleValue() - value.doubleValue());
            case MULTIPLY: return wrapValue(acc.doubleValue() * value.doubleValue());
            case DIVIDE: default: return wrapValue(acc.doubleValue() / value.doubleValue());
        }
    }
    
    protected T accumulate(Complex acc, Complex value, AccumulateOperators operator) {
        throw new IllegalArgumentException();
    }
    
    protected T accumulate(Complex acc, Number value, AccumulateOperators operator) {
        throw new IllegalArgumentException();
    }

    protected AbstractRealNDArray<T> createNewRealNDArrayOfSameTypeAsMe(int... shape) {
        return (AbstractRealNDArray<T>)createNewNDArrayOfSameTypeAsMe(shape);
    }

    protected T wrapRealValue(Number value) {
        return wrapValue(value);
    }

    protected T zeroT2() {
        return zeroT();
    }

    protected long countNonZero() {
        return stream().filter(item -> item.doubleValue() != 0.).count();
    }
    
    protected Class<?> dtype2() {
        return dtype();
    }
    
    protected Collector<Object, List<Object>, NDArray<T>> getCollectorInternal(int[] shape) {
        return new RealNDArrayCollector<>(createNewNDArrayOfSameTypeAsMe(shape));
    }

    protected Collector<Object, List<Object>, NDArray<T>> getRealCollectorInternal(int[] shape) {
        return getCollectorInternal(shape);
    }

}