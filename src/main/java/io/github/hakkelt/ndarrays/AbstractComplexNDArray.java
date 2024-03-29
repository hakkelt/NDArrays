/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\AbstractComplexNDArray.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays;

import io.github.hakkelt.ndarrays.internal.*;
import io.github.hakkelt.ndarrays.internal.AbstractNDArray;
import io.github.hakkelt.ndarrays.internal.AccumulateOperators;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

/**
 * Abstract NDArray class for Complex values.
 */
public abstract class AbstractComplexNDArray<T extends Number> extends AbstractNDArray<Complex,T> implements ComplexNDArray<T> {

    protected AbstractComplexNDArray() {}

    @Override
    public Class<?> dtype() {
        return Complex.class;
    }

    @Override
    public Class<?> dtype2() {
        Class<?> clazz = this.getClass();
        Type superClass = clazz.getGenericSuperclass();
        for (int i = 0; i < 50; i++) {
            if (superClass instanceof ParameterizedType)
                break;
            clazz = clazz.getSuperclass();
            superClass = clazz.getGenericSuperclass();
        }
        Type[] types = ((ParameterizedType) superClass).getActualTypeArguments();
        return (Class<?>) types[types.length - 1];
    }

    @Override
    public String dataTypeAsString() {
        return "Complex " + dtype2().getSimpleName();
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> copy() {
        return (ComplexNDArray<T>) super.copy();
    }

    @Override
    public NDArray<T> real() {
        return createNewRealNDArrayOfSameTypeAsMe(shape).fillUsingLinearIndices(this::getReal);
    }

    @Override
    public NDArray<T> imaginary() {
        return createNewRealNDArrayOfSameTypeAsMe(shape).fillUsingLinearIndices(this::getImag);
    }

    @Override
    public NDArray<T> abs() {
        return createNewRealNDArrayOfSameTypeAsMe(shape).fillUsingLinearIndices(i -> wrapRealValue(getUnchecked(i).abs()));
    }

    @Override
    public NDArray<T> argument() {
        return createNewRealNDArrayOfSameTypeAsMe(shape).fillUsingLinearIndices(i -> wrapRealValue(getUnchecked(i).getArgument()));
    }

    @Override
    public ComplexNDArray<T> similar() {
        return createNewNDArrayOfSameTypeAsMe(shape);
    }

    @Override
    public void set(Number value, int linearIndex) {
        NDArrayUtils.boundaryCheck(linearIndex, length());
        linearIndex = NDArrayUtils.unwrap(linearIndex, length());
        setRealUnchecked(wrapRealValue(value), linearIndex);
        setImagUnchecked(zeroT2(), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        NDArrayUtils.boundaryCheck(indices, shape());
        indices = NDArrayUtils.unwrap(indices, shape());
        setRealUnchecked(wrapRealValue(value), indices);
        setImagUnchecked(zeroT2(), indices);
    }

    @Override
    public void setReal(Number value, int linearIndex) {
        NDArrayUtils.boundaryCheck(linearIndex, length());
        setRealUnchecked(wrapRealValue(value), NDArrayUtils.unwrap(linearIndex, length()));
    }

    @Override
    public void setReal(Number value, int... indices) {
        NDArrayUtils.boundaryCheck(indices, shape());
        setRealUnchecked(wrapRealValue(value), NDArrayUtils.unwrap(indices, shape()));
    }

    @Override
    public void setImag(Number value, int linearIndex) {
        NDArrayUtils.boundaryCheck(linearIndex, length());
        setImagUnchecked(wrapRealValue(value), NDArrayUtils.unwrap(linearIndex, length()));
    }

    @Override
    public void setImag(Number value, int... indices) {
        NDArrayUtils.boundaryCheck(indices, shape());
        setImagUnchecked(wrapRealValue(value), NDArrayUtils.unwrap(indices, shape()));
    }

    @Override
    public ComplexNDArray<T> fillUsingLinearIndices(IntFunction<Complex> func) {
        super.fillUsingLinearIndices(func);
        return this;
    }

    @Override
    public ComplexNDArray<T> fillUsingCartesianIndices(Function<int[],Complex> func) {
        super.fillUsingCartesianIndices(func);
        return this;
    }

    @Override
    public ComplexNDArray<T> apply(UnaryOperator<Complex> func) {
        super.apply(func);
        return this;
    }

    @Override
    public ComplexNDArray<T> applyWithLinearIndices(BiFunction<Complex,Integer,Complex> func) {
        super.applyWithLinearIndices(func);
        return this;
    }

    @Override
    public ComplexNDArray<T> applyWithCartesianIndices(BiFunction<Complex,int[],Complex> func) {
        super.applyWithCartesianIndices(func);
        return this;
    }

    @Override
    public ComplexNDArray<T> applyOnComplexSlices(BiFunction<ComplexNDArray<T>,int[],NDArray<?>> func, int... iterationDims) {
        return SliceOperations.applyOnSlices(this, func, iterationDims);
    }

    @Override
    public ComplexNDArray<T> mapOnComplexSlices(BiFunction<ComplexNDArray<T>,int[],NDArray<?>> func, int... iterationDims) {
        return SliceOperations.applyOnSlices(copy(), func, iterationDims);
    }

    @Override
    protected abstract ComplexNDArray<T> createNewNDArrayOfSameTypeAsMe(int... shape);

    @Override
    protected Complex accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects) {
        Complex acc = get(linearIndex);
        for (Object item : objects)
            if (item instanceof NDArray<?>)
                acc = accumulate(acc, ((NDArray<?>) item), linearIndex, operator);
            else if (item instanceof Complex)
                acc = accumulate(acc, (Complex) item, operator);
            else
                acc = accumulate(acc, (Number) item, operator);
        return acc;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected T wrapRealValue(Number value) {
        if (dtype2() == Float.class)
            return (T) Float.valueOf(value.floatValue());
        else
            return (T) Double.valueOf(value.doubleValue());
    }

    @Override
    protected long countNonZero() {
        return stream().filter(item -> !item.equals(Complex.ZERO)).count();
    }

    @Override
    protected Collector<Object,List<Object>,NDArray<Complex>> getCollectorInternal(int[] shape) {
        return new ComplexNDArrayCollector<>(createNewNDArrayOfSameTypeAsMe(shape));
    }

    @Override
    protected String printItem(int index, String format) {
        Complex item = get(index);
        String complexFormat = format + (item.getImaginary() < 0 ? "-" : "+") + format + "i";
        return String.format(complexFormat, item.getReal(), Math.abs(item.getImaginary()));
    }

    @Generated
    protected Complex accumulate(Float acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Double acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Byte acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Short acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Integer acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Long acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(BigInteger acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(BigDecimal acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Float acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Double acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Byte acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Short acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Integer acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(Long acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(BigInteger acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Generated
    protected Complex accumulate(BigDecimal acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Complex accumulate(Complex acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        if (array.dtype() == Complex.class)
            return accumulate(acc, (Complex) array.get(linearIndex), operation);
        else if (array.dtype() == Byte.class)
            return accumulate(acc, (Byte) array.get(linearIndex), operation);
        else if (array.dtype() == Short.class)
            return accumulate(acc, (Short) array.get(linearIndex), operation);
        else if (array.dtype() == Integer.class)
            return accumulate(acc, (Integer) array.get(linearIndex), operation);
        else if (array.dtype() == Long.class)
            return accumulate(acc, (Long) array.get(linearIndex), operation);
        else if (array.dtype() == Double.class)
            return accumulate(acc, (Double) array.get(linearIndex), operation);
        else if (array.dtype() == Float.class)
            return accumulate(acc, (Float) array.get(linearIndex), operation);
        else if (array.dtype() == BigInteger.class)
            return accumulate(acc, (BigInteger) array.get(linearIndex), operation);
        else
            return accumulate(acc, (BigDecimal) array.get(linearIndex), operation);
    }

    @Override
    protected Complex accumulate(Complex acc, Complex value, AccumulateOperators operation) {
        switch (operation) {
            case ADD:
                return acc.add(value);
            case SUBTRACT:
                return acc.subtract(value);
            case MULTIPLY:
                return acc.multiply(value);
            case DIVIDE:
            default:
                return acc.divide(value);
        }
    }

    @Override
    protected Complex accumulate(Complex acc, Number value, AccumulateOperators operation) {
        switch (operation) {
            case ADD:
                return acc.add(value.doubleValue());
            case SUBTRACT:
                return acc.subtract(value.doubleValue());
            case MULTIPLY:
                return acc.multiply(value.doubleValue());
            case DIVIDE:
            default:
                return acc.divide(value.doubleValue());
        }
    }

    @Override
    protected Collector<Object,List<Object>,NDArray<T>> getRealCollectorInternal(int[] shape) {
        return new RealNDArrayCollector<>(createNewRealNDArrayOfSameTypeAsMe(shape));
    }

    @Override
    protected Complex wrapValue(Number value) {
        return new Complex(value.doubleValue());
    }

    @Override
    protected Complex wrapValue(Object value) {
        if (value instanceof Number)
            return new Complex(((Number) value).doubleValue());
        return (Complex) value;
    }

    @Override
    protected Complex zeroT() {
        return Complex.ZERO;
    }

    @Override
    protected Complex oneT() {
        return Complex.ONE;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected T zeroT2() {
        if (dtype2() == Float.class)
            return (T) Float.valueOf(0.f);
        return (T) Double.valueOf(0.);
    }

}
