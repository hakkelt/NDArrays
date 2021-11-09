package io.github.hakkelt.ndarrays;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.commons.math3.complex.Complex;

/**
 * A view for a ComplexNDArray that selects values based on a specified mask.
 * When mask(...) is called for a ComplexNDArray, an instance of this class is returned.
 */
public class ComplexNDArrayMaskView<T extends Number> extends AbstractNDArrayMaskView<Complex,T> implements ComplexNDArray<T> {
    
    @SuppressWarnings("unchecked")
    public ComplexNDArrayMaskView(NDArray<Complex> parent, NDArray<?> mask, boolean isInverse) {
        super(parent instanceof AbstractNDArrayMaskView ? (AbstractNDArrayMaskView<Complex,T>)parent : parent, mask, isInverse);
    }
    
    @SuppressWarnings("unchecked")
    public ComplexNDArrayMaskView(NDArray<Complex> parent, Predicate<Complex> func) {
        super(parent instanceof AbstractNDArrayMaskView ? (AbstractNDArrayMaskView<Complex,T>)parent : parent, func);
    }
    
    @SuppressWarnings("unchecked")
    public ComplexNDArrayMaskView(NDArray<Complex> parent, BiPredicate<Complex,?> func, boolean withLinearIndices) {
        super(parent instanceof AbstractNDArrayMaskView ? (AbstractNDArrayMaskView<Complex,T>)parent : parent, func, withLinearIndices);
    }
    
    public NDArray<T> real() {
        return streamLinearIndices()
            .mapToObj(this::getReal).collect(parent.getRealCollectorInternal(shape));
    }

    public NDArray<T> imaginary() {
        return streamLinearIndices()
            .mapToObj(this::getImag).collect(parent.getRealCollectorInternal(shape));
    }

    @Override
    public NDArray<T> abs() {
        return streamLinearIndices()
            .mapToObj(i -> (get(i)).abs()).collect(parent.getRealCollectorInternal(shape));
    }

    public NDArray<T> angle() {
        return streamLinearIndices()
            .mapToObj(i -> (get(i)).getArgument()).collect(parent.getRealCollectorInternal(shape));
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> similar() {
        return (ComplexNDArray<T>)parent.createNewNDArrayOfSameTypeAsMe(shape);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> copy() {
        ComplexNDArray<T> newInstance = (ComplexNDArray<T>)parent.createNewNDArrayOfSameTypeAsMe(shape);
        return newInstance.copyFrom(this);
    }

    @Override
    public void set(Number value, int linearIndex) {
        IndexingOperations.boundaryCheck(linearIndex, length());
        linearIndex = IndexingOperations.unwrap(linearIndex, length());
        setRealUnchecked(wrapRealValue(value), linearIndex);
        setImagUnchecked(zeroT2(), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        IndexingOperations.boundaryCheck(indices, shape());
        indices = IndexingOperations.unwrap(indices, shape());
        setRealUnchecked(wrapRealValue(value), indices);
        setImagUnchecked(zeroT2(), indices);
    }

    @Override
    public void setReal(Number value, int linearIndex) {
        setReal(wrapRealValue(value), linearIndex);
    }

    @Override
    public void setReal(Number value, int... indices) {
        setReal(wrapRealValue(value), indices);
    }

    @Override
    public void setImag(Number value, int linearIndex) {
        setImag(wrapRealValue(value), linearIndex);
    }

    @Override
    public void setImag(Number value, int... indices) {
        setImag(wrapRealValue(value), indices);
    }

    @Override
    protected T getRealUnchecked(int linearIndex) {
        return parent.getRealUnchecked(indexMapper.get(linearIndex));
    }

    @Override
    protected T getRealUnchecked(int... indices) {
        return parent.getRealUnchecked(indexMapper.get(
            IndexingOperations.cartesianIndicesToLinearIndex(indices, parent.multipliers)));
    }

    @Override
    protected T getImagUnchecked(int linearIndex) {
        return parent.getImagUnchecked(indexMapper.get(linearIndex));
    }

    @Override
    protected T getImagUnchecked(int... indices) {
        return parent.getImagUnchecked(indexMapper.get(
            IndexingOperations.cartesianIndicesToLinearIndex(indices, parent.multipliers)));
    }

    @Override
    protected void setRealUnchecked(T value, int linearIndex) {
        parent.setRealUnchecked(value, indexMapper.get(linearIndex).intValue());
    }

    @Override
    protected void setRealUnchecked(T value, int... indices) {
        parent.setRealUnchecked(value, indexMapper.get(indices[0]));
    }

    @Override
    protected void setImagUnchecked(T value, int linearIndex) {
        parent.setImagUnchecked(value, indexMapper.get(linearIndex));
    }

    @Override
    protected void setImagUnchecked(T value, int... indices) {
        parent.setImagUnchecked(value, indexMapper.get(indices[0]));
    }

    @Override
    protected Complex accumulate(Complex acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operator) {
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
    protected Complex accumulate(Complex acc, Complex value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value);
            case SUBTRACT: return acc.subtract(value);
            case MULTIPLY: return acc.multiply(value);
            case DIVIDE: default: return acc.divide(value);
        }
    }

    @Override
    protected Complex accumulate(Complex acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value.doubleValue());
            case SUBTRACT: return acc.subtract(value.doubleValue());
            case MULTIPLY: return acc.multiply(value.doubleValue());
            case DIVIDE: default: return acc.divide(value.doubleValue());
        }
    }
    
}
