package io.github.hakkelt.ndarrays;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

abstract class AbstractComplexNDArray<T extends Number> extends AbstractNDArray<Complex,T> implements InternalComplexNDArray<T> {

    public Object eltype() {
        return Complex.class;
    }

    public Complex get(int linearIndex) {
        return new Complex(getReal(linearIndex).doubleValue(), getImag(linearIndex).doubleValue());
    }
    
    public T getReal(int... indices) {
        return getReal(resolveIndices(indices));
    }

    
    public T getImag(int... indices) {
        return getImag(resolveIndices(indices));
    }

    public void set(Complex value, int linearIndex) {
        setReal(value.getReal(), linearIndex);
        setImag(value.getImaginary(), linearIndex);
    }

    public void set(Number value, int linearIndex) {
        setReal(value, linearIndex);
        setImag(0, linearIndex);
    }
    
    public void set(Number value, int... indices) {
        set(value, resolveIndices(indices));
    }


    public void setReal(Number value, int... indices) {
        setReal(value, resolveIndices(indices));
    }

    
    public void setImag(Number value, int... indices) {
        setImag(value, resolveIndices(indices));
    }

    public String dataTypeAsString() {
        String[] str = eltype2().toString().split("\\.");
        return "Complex " + str[str.length - 1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> copy() {
        return (ComplexNDArray<T>)super.copy();
    }


    @Override
    public NDArray<T> real() {
        return streamLinearIndices()
            .mapToObj(this::getReal).collect(getRealCollectorInternal(dims));
    }


    @Override
    public NDArray<T> imaginary() {
        return streamLinearIndices()
            .mapToObj(this::getImag).collect(getRealCollectorInternal(dims));
    }


    @Override
    public NDArray<T> abs() {
        return streamLinearIndices()
            .mapToObj(i -> get(i).abs()).collect(getRealCollectorInternal(dims));
    }


    @Override
    public NDArray<T> angle() {
        return streamLinearIndices()
            .mapToObj(i -> get(i).getArgument()).collect(getRealCollectorInternal(dims));
    }
    

    @Override
    public ComplexNDArray<T> similar() {
        return createNewNDArrayOfSameTypeAsMe(dims);
    }

    
    protected abstract AbstractComplexNDArray<T> createNewNDArrayOfSameTypeAsMe(int... dims);
    protected abstract AbstractRealNDArray<T> createNewRealNDArrayOfSameTypeAsMe(int... dims);

    
    protected Complex accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object ...objects) {
        Complex acc = get(linearIndex);
        for (Object item : objects) {
            if (item instanceof NDArray<?>) {
                acc = accumulate(acc, ((NDArray<?>)item), linearIndex, operator);
            } else if (item instanceof Complex) {
                acc = accumulate(acc, (Complex)item, operator);
            } else if (item instanceof Number) {
                acc = accumulate(acc, (Number)item, operator);
            }
        }
        return acc;
    }


    @SuppressWarnings("unchecked")
    protected T wrapRealValue(Number value) {
        if (eltype2() == Float.class)
            return (T) Float.valueOf(value.floatValue());
        if (eltype2() == Double.class)
            return (T) Double.valueOf(value.doubleValue());
        if (eltype2() == Byte.class)
            return (T) Byte.valueOf(value.byteValue());
        if (eltype2() == Short.class)
            return (T) Short.valueOf(value.shortValue());
        if (eltype2() == Integer.class)
            return (T) Integer.valueOf(value.intValue());
        if (eltype2() == Long.class)
            return (T) Long.valueOf(value.longValue());
        throw new IllegalArgumentException();
    }

    protected long countNonZero() {
        return stream().filter(item -> item != Complex.ZERO).count();
    }

    protected Object eltype2() {
        Type[] types = ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments();
        return types[types.length - 1];
    }
    
    protected Collector<Object, List<Object>, NDArray<Complex>> getCollectorInternal(int[] dims) {
        return new ComplexNDArrayCollector<>(createNewNDArrayOfSameTypeAsMe(dims));
    }


    protected String printItem(int index, String format) {
        Complex item = get(index);
        String complexFormat = format + (item.getImaginary() < 0 ? "-" : "+") + format + "i";
        return String.format(complexFormat, item.getReal(), Math.abs(item.getImaginary()));
    }

    protected Complex accumulate(Float acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    protected Complex accumulate(Double acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    protected Complex accumulate(Byte acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    protected Complex accumulate(Short acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    protected Complex accumulate(Integer acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    protected Complex accumulate(Long acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    protected Complex accumulate(Complex acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operation) {
        if (array.eltype() == Complex.class)
            return accumulate(acc, (Complex)array.get(linearIndex), operation);
        else if (array.eltype() == Double.class)
            return accumulate(acc, (Double)array.get(linearIndex), operation);
        else
            return accumulate(acc, (Float)array.get(linearIndex), operation);
    }

    protected Complex accumulate(Float acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    protected Complex accumulate(Double acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    protected Complex accumulate(Byte acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    protected Complex accumulate(Short acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    protected Complex accumulate(Integer acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    protected Complex accumulate(Long acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }


    @Override
    protected Complex accumulate(Complex acc, Complex value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value);
            case SUBTRACT: return acc.subtract(value);
            case MULTIPLY: return acc.multiply(value);
            case DIVIDE: return acc.divide(value);
            default: throw new IllegalArgumentException();
        }
    }

    protected Complex accumulate(Complex acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value.doubleValue());
            case SUBTRACT: return acc.subtract(value.doubleValue());
            case MULTIPLY: return acc.multiply(value.doubleValue());
            case DIVIDE: return acc.divide(value.doubleValue());
            default: throw new IllegalArgumentException();
        }
    }

    protected static void checkEqualLength(byte[] real, byte[] imag) {
        if (imag.length != real.length)
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
    }

    protected static void checkEqualLength(short[] real, short[] imag) {
        if (imag.length != real.length)
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
    }

    protected static void checkEqualLength(int[] real, int[] imag) {
        if (imag.length != real.length)
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
    }

    protected static void checkEqualLength(long[] real, long[] imag) {
        if (imag.length != real.length)
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
    }

    protected static void checkEqualLength(float[] real, float[] imag) {
        if (imag.length != real.length)
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
    }

    protected static void checkEqualLength(double[] real, double[] imag) {
        if (imag.length != real.length)
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
    }

    protected static void checkEqualLength(Object[] real, Object[] imag) {
        if (imag.length != real.length)
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
        if (!(real[0] instanceof Complex)) {
            if (real[0] instanceof byte[])
                checkEqualLength((byte[])real[0], (byte[])imag[0]);
            if (real[0] instanceof short[])
                checkEqualLength((short[])real[0], (short[])imag[0]);
            if (real[0] instanceof int[])
                checkEqualLength((int[])real[0], (int[])imag[0]);
            if (real[0] instanceof long[])
                checkEqualLength((long[])real[0], (long[])imag[0]);
            if (real[0] instanceof float[])
                checkEqualLength((float[])real[0], (float[])imag[0]);
            if (real[0] instanceof double[])
                checkEqualLength((double[])real[0], (double[])imag[0]);
        }
    }
    
    protected Complex wrapValue(Object value) {
        if (value instanceof Float)
            return new Complex((Float)value);
        if (value instanceof Double)
            return new Complex((Double)value);
        if (value instanceof Byte)
            return new Complex((Byte)value);
        if (value instanceof Short)
            return new Complex((Short)value);
        if (value instanceof Long)
            return new Complex((Long)value);
        if (value instanceof Integer)
            return new Complex((Integer)value);
        if (value instanceof Complex)
            return (Complex)value;
        throw new UnsupportedOperationException(String.format(ERROR_TYPE_MISMATCH, value.getClass()));
    }

    @SuppressWarnings("unchecked")
    protected T zeroT2() {
        if (eltype2() == Float.class)
            return (T) Float.valueOf(0.f);
        if (eltype2() == Double.class)
            return (T) Double.valueOf(0.);
        if (eltype2() == Byte.class)
            return (T) Byte.valueOf((byte) 0);
        if (eltype2() == Short.class)
            return (T) Short.valueOf((short) 0);
        if (eltype2() == Integer.class)
            return (T) Integer.valueOf(0);
        if (eltype2() == Long.class)
            return (T) Long.valueOf(0);
        throw new UnsupportedOperationException();
    }
    
    protected Collector<Object, List<Object>, NDArray<T>> getRealCollectorInternal(int[] dims) {
        return new RealNDArrayCollector<>(createNewRealNDArrayOfSameTypeAsMe(dims));
    }

    protected Complex wrapValue(Number value) {
        return new Complex(value.doubleValue());
    }
    
    protected Complex zeroT() {
        return Complex.ZERO;
    }

    protected Complex oneT() {
        return Complex.ONE;
    }
}
