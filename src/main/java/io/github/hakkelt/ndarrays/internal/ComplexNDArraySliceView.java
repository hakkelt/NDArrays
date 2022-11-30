/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\internal\ComplexNDArraySliceView.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.internal;

import io.github.hakkelt.ndarrays.*;

import java.util.function.*;

import org.apache.commons.math3.complex.Complex;

/**
 * A view for a ComplexNDArray that slices the parent ComplexNDArray.
 * When slice(...) is called for a ComplexNDArray, an instance of this class is returned.
 */
public class ComplexNDArraySliceView<T extends Number> extends AbstractNDArraySliceView<Complex,T> implements ComplexNDArray<T> {

    public ComplexNDArraySliceView(NDArray<Complex> parent, Range[] slicingExpressions) {
        super(parent, slicingExpressions);
    }

    @Override
    public ComplexNDArray<T> mapOnComplexSlices(BiFunction<ComplexNDArray<T>,int[],NDArray<?>> func, int... iterationDims) {
        return SliceOperations.applyOnSlices(copy(), func, iterationDims);
    }

    @Override
    public ComplexNDArray<T> applyOnComplexSlices(BiFunction<ComplexNDArray<T>,int[],NDArray<?>> func, int... iterationDims) {
        return SliceOperations.applyOnSlices(this, func, iterationDims);
    }

    @Override
    public ComplexNDArray<T> reduceComplexSlices(BiFunction<ComplexNDArray<T>,int[],Complex> func, int... iterationDims) {
        return SliceOperations.reduceComplexSlices(this, func, iterationDims);
    }

    @Override
    public NDArray<T> real() {
        return streamLinearIndices()
            .mapToObj(this::getReal)
            .collect(parent.getRealCollectorInternal(shape));
    }

    @Override
    public NDArray<T> imaginary() {
        return streamLinearIndices()
            .mapToObj(this::getImag)
            .collect(parent.getRealCollectorInternal(shape));
    }

    @Override
    public NDArray<T> abs() {
        return streamLinearIndices()
            .mapToObj(i -> get(i).abs())
            .collect(parent.getRealCollectorInternal(shape));
    }

    @Override
    public NDArray<T> argument() {
        return streamLinearIndices()
            .mapToObj(i -> get(i).getArgument())
            .collect(parent.getRealCollectorInternal(shape));
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> similar() {
        return ((ComplexNDArray<T>)parent.createNewNDArrayOfSameTypeAsMe(shape));
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> copy() {
        ComplexNDArray<T> newInstance = (ComplexNDArray<T>)parent.createNewNDArrayOfSameTypeAsMe(shape);
        return newInstance.copyFrom(this);
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
        super.setReal(wrapRealValue(value), linearIndex);
    }

    @Override
    public void setImag(Number value, int linearIndex) {
        super.setImag(wrapRealValue(value), linearIndex);
    }

    @Override
    public void setReal(Number value, int... indices) {
        super.setReal(wrapRealValue(value), indices);
    }

    @Override
    public void setImag(Number value, int... indices) {
        super.setImag(wrapRealValue(value), indices);
    }

    @Override
    protected T getRealUnchecked(int linearIndex) {
        return getRealUnchecked(linearIndexToViewIndices(linearIndex));
    }

    @Override
    protected T getImagUnchecked(int linearIndex) {
        return getImagUnchecked(linearIndexToViewIndices(linearIndex));
    }

    @Override
    protected T getRealUnchecked(int... indices) {
        return parent.getRealUnchecked(slicingExpression.resolveToParentIndices(indices));
    }

    @Override
    protected T getImagUnchecked(int... indices) {
        return parent.getImagUnchecked(slicingExpression.resolveToParentIndices(indices));
    }

    @Override
    protected void setRealUnchecked(T value, int linearIndex) {
        setRealUnchecked(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    protected void setImagUnchecked(T value, int linearIndex) {
        setImagUnchecked(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    protected void setRealUnchecked(T value, int... indices) {
        parent.setRealUnchecked(value, slicingExpression.resolveToParentIndices(indices));
    }

    @Override
    protected void setImagUnchecked(T value, int... indices) {
        parent.setImagUnchecked(value, slicingExpression.resolveToParentIndices(indices));
    }

    @Override
    protected Complex accumulate(Complex acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    @Override
    protected Complex accumulate(Float acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    @Override
    protected Complex accumulate(Double acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    @Override
    protected Complex accumulate(Byte acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    @Override
    protected Complex accumulate(Short acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    @Override
    protected Complex accumulate(Integer acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    @Override
    protected Complex accumulate(Long acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    @Override
    protected Complex accumulate(Complex acc, Complex value, AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value);
            case SUBTRACT: return acc.subtract(value);
            case MULTIPLY: return acc.multiply(value);
            case DIVIDE: return acc.divide(value);
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    protected Complex accumulate(Complex acc, Number value, AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value.doubleValue());
            case SUBTRACT: return acc.subtract(value.doubleValue());
            case MULTIPLY: return acc.multiply(value.doubleValue());
            case DIVIDE: return acc.divide(value.doubleValue());
            default: throw new IllegalArgumentException();
        }
    }

}
