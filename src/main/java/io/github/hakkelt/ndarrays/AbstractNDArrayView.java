package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

abstract class AbstractNDArrayView<T,T2 extends Number> extends AbstractNDArray<T,T2> {
    AbstractNDArray<T,T2> parent;

    @Override
    public Object eltype() {
        return parent.eltype();
    }

    @Override
    public Object eltype2() {
        return parent.eltype2();
    }

    @Override
    protected String printItem(int index, String format) {
        return parent.printItem(index, format);
    }

    @Override
    public String dataTypeAsString() {
        return parent.dataTypeAsString();
    }

    protected long countNonZero() {
        if (eltype() == Complex.class)
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
    protected T oneT() {
        return parent.oneT();
    }

    @Override
    protected double absSum() {
        if (eltype() == Complex.class)
            return stream().map(value -> ((Complex)value).abs()).reduce(0., Double::sum);
        return stream().map(value -> ((Number)value).doubleValue()).reduce(0., (a,b) -> Double.sum(a, Math.abs(b)));
    }

    @Override
    protected T accumulate(T acc, T value, AbstractNDArray.AccumulateOperators operator) {
        return parent.accumulate(acc, value, operator);
    }

    protected T accumulate(Float acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    protected T accumulate(Double acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator); 
    }

    protected T accumulate(Byte acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    protected T accumulate(Short acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    protected T accumulate(Integer acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    protected T accumulate(Long acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    protected T accumulate(Complex acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
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

    private T specializedAccumulate(T acc, NDArray<?> item, int linearIndex, AccumulateOperators operator) {
        if (eltype() == Float.class)
            return accumulate((Float)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (eltype() == Double.class)
            return accumulate((Double)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (eltype() == Byte.class)
            return accumulate((Byte)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (eltype() == Short.class)
            return accumulate((Short)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (eltype() == Integer.class)
            return accumulate((Integer)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (eltype() == Long.class)
            return accumulate((Long)acc, ((NDArray<?>)item), linearIndex, operator);
        else if (eltype() == Complex.class)
            return accumulate((Complex)acc, ((NDArray<?>)item), linearIndex, operator);
        else throw new UnsupportedOperationException();
    }

    private T specializedAccumulate(T acc, Number item, AccumulateOperators operator) {
        if (eltype() == Float.class)
            return accumulate((Float)acc, item, operator);
        else if (eltype() == Double.class)
            return accumulate((Double)acc, item, operator);
        else if (eltype() == Byte.class)
            return accumulate((Byte)acc, item, operator);
        else if (eltype() == Short.class)
            return accumulate((Short)acc, item, operator);
        else if (eltype() == Integer.class)
            return accumulate((Integer)acc, item, operator);
        else if (eltype() == Long.class)
            return accumulate((Long)acc, item, operator);
        else if (eltype() == Complex.class)
            return accumulate((Complex)acc, item, operator);
        else throw new UnsupportedOperationException();
    }
    
    protected T accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object ...objects) {
        T acc = get(linearIndex);
        for (Object item : objects) {
            if (item instanceof NDArray<?>)
                acc = specializedAccumulate(acc, (NDArray<?>)item, linearIndex, operator);
            else if (item instanceof Complex && eltype() == Complex.class)
                acc = accumulate((Complex)acc, (Complex)item, operator);
            else if (item instanceof Number)
                acc =specializedAccumulate(acc, (Number)item, operator);
            else
                throw new UnsupportedOperationException();
        }
        return wrapValue(acc);
    }

    @Override
    protected Collector<Object, List<Object>, NDArray<T>> getCollectorInternal(int[] dims) {
        return parent.getCollectorInternal(dims);
    }

    protected Collector<Object, List<Object>, NDArray<T2>> getRealCollectorInternal(int[] dims) {
        return parent.getRealCollectorInternal(dims);
    }

    @Override
    public NDArray<T> similar() {
        return parent.createNewNDArrayOfSameTypeAsMe(dims);
    }

    @Override
    public NDArray<T> copy() {
        NDArray<T> newInstance = parent.createNewNDArrayOfSameTypeAsMe(dims);
        return newInstance.copyFrom(this);
    }
    
    public int[] linearIndexToViewIndices(int linearIndex) {
        return linearIndexToCartesianIndices(linearIndex, multipliers, ndims(), dataLength);
    }

    @Override
    protected NDArray<T> createNewNDArrayOfSameTypeAsMe(int... dims) {
        return parent.createNewNDArrayOfSameTypeAsMe(dims);
    }

    @Override
    protected NDArray<T2> createNewRealNDArrayOfSameTypeAsMe(int... dims) {
        return parent.createNewRealNDArrayOfSameTypeAsMe(dims);
    }

    @Override
    protected T wrapValue(Object value) {
        return parent.wrapValue(value);
    }

    protected T wrapValue(Number value) {
        return parent.wrapValue(value);
    }

    protected T2 wrapRealValue(Number value) {
        return parent.wrapRealValue(value);
    }

    protected String name() {
        return parent.name();
    }
    
    protected static AbstractNDArraySliceView.Range parseRange(String str, int[] dims, int dimension) {
        final Pattern r = Pattern.compile("(\\d*):(\\d*)");
        Matcher m = r.matcher(str);
        if (m.find()) {
            int start = m.group(1).equals("") ? 0 : Integer.parseInt(m.group(1));
            int end = m.group(2).equals("") ? dims[dimension] : Integer.parseInt(m.group(2));
            return new AbstractNDArraySliceView.Range(start, end);
        } else {
            throw new IllegalArgumentException(String.format(Errors.INVALID_RANGE_EXPRESSION, str));
        }
    }
}
