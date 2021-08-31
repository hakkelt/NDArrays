package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

public abstract class AbstractRealNDArray<T extends Number> extends AbstractNDArray<T,T> implements InternalRealNDArray<T> {
    
    @Override
    public void set(Number value, int... indices) {
        set(value, resolveIndices(indices));
    }

    protected String printItem(int index, String format) {
        return String.format(format, get(index));
    }

    public String dataTypeAsString() {
        String[] str = eltype2().toString().split("\\.");
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
            case DIVIDE: return wrapValue(acc.byteValue() / value.byteValue());
            default: throw new IllegalArgumentException();
        }
    }

    protected T accumulate(Short acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.shortValue() + value.shortValue());
            case SUBTRACT: return wrapValue(acc.shortValue() - value.shortValue());
            case MULTIPLY: return wrapValue(acc.shortValue() * value.shortValue());
            case DIVIDE: return wrapValue(acc.shortValue() / value.shortValue());
            default: throw new IllegalArgumentException();
        }
    }

    protected T accumulate(Integer acc, Number value, AbstractNDArray.AccumulateOperators operation) {
       switch (operation) { 
            case ADD: return wrapValue(acc.intValue() + value.intValue());
            case SUBTRACT: return wrapValue(acc.intValue() - value.intValue());
            case MULTIPLY: return wrapValue(acc.intValue() * value.intValue());
            case DIVIDE: return wrapValue(acc.intValue() / value.intValue());
            default: throw new IllegalArgumentException();
        }
    }

    protected T accumulate(Long acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.longValue() + value.longValue());
            case SUBTRACT: return wrapValue(acc.longValue() - value.longValue());
            case MULTIPLY: return wrapValue(acc.longValue() * value.longValue());
            case DIVIDE: return wrapValue(acc.longValue() / value.longValue());
            default: throw new IllegalArgumentException();
        }
    }

    protected T accumulate(Float acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.floatValue() + value.floatValue());
            case SUBTRACT: return wrapValue(acc.floatValue() - value.floatValue());
            case MULTIPLY: return wrapValue(acc.floatValue() * value.floatValue());
            case DIVIDE: return wrapValue(acc.floatValue() / value.floatValue());
            default: throw new IllegalArgumentException();
        }
    }

    protected T accumulate(Double acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.doubleValue() + value.doubleValue());
            case SUBTRACT: return wrapValue(acc.doubleValue() - value.doubleValue());
            case MULTIPLY: return wrapValue(acc.doubleValue() * value.doubleValue());
            case DIVIDE: return wrapValue(acc.doubleValue() / value.doubleValue());
            default: throw new IllegalArgumentException();
        }
    }
    
    protected T accumulate(Complex acc, Complex value, AccumulateOperators operator) {
        throw new IllegalArgumentException();
    }
    
    protected T accumulate(Complex acc, Number value, AccumulateOperators operator) {
        throw new IllegalArgumentException();
    }

    protected AbstractRealNDArray<T> createNewRealNDArrayOfSameTypeAsMe(int... dims) {
        return (AbstractRealNDArray<T>)createNewNDArrayOfSameTypeAsMe(dims);
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
    
    protected Object eltype2() {
        return eltype();
    }
    
    protected Collector<Object, List<Object>, NDArray<T>> getCollectorInternal(int[] dims) {
        return new RealNDArrayCollector<>(createNewNDArrayOfSameTypeAsMe(dims));
    }

    protected Collector<Object, List<Object>, NDArray<T>> getRealCollectorInternal(int[] dims) {
        return getCollectorInternal(dims);
    }

}