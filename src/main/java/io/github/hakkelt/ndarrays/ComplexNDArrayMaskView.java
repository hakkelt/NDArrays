package io.github.hakkelt.ndarrays;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.commons.math3.complex.Complex;

class ComplexNDArrayMaskView<T extends Number> extends AbstractNDArrayMaskView<Complex,T> implements ComplexNDArrayTrait<T> {
    
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

    @Override
    @SuppressWarnings("unchecked")
    public T getReal(int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        return ((ComplexNDArray<T>)parent).getReal(indexMapper.get(linearIndex));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getReal(int... indices) {
        indices = IndexingOperations.boundaryCheck(indices, dims());
        return ((ComplexNDArray<T>)parent).getReal(indexMapper.get(
            IndexingOperations.cartesianIndicesToLinearIndex(indices, parent.dims, parent.multipliers)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getImag(int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        return ((ComplexNDArray<T>)parent).getImag(indexMapper.get(linearIndex));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getImag(int... indices) {
        indices = IndexingOperations.boundaryCheck(indices, dims());
        return ((ComplexNDArray<T>)parent).getImag(indexMapper.get(
            IndexingOperations.cartesianIndicesToLinearIndex(indices, parent.dims, parent.multipliers)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setReal(Number value, int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        ((ComplexNDArray<T>)parent).setReal(value, indexMapper.get(linearIndex));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setReal(Number value, int... indices) {
        indices = IndexingOperations.boundaryCheck(indices, dims());
        ((ComplexNDArray<T>)parent).setReal(value, indexMapper.get(
            IndexingOperations.cartesianIndicesToLinearIndex(indices, parent.dims, parent.multipliers)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setImag(Number value, int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        ((ComplexNDArray<T>)parent).setImag(value, indexMapper.get(linearIndex));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setImag(Number value, int... indices) {
        indices = IndexingOperations.boundaryCheck(indices, dims());
        ((ComplexNDArray<T>)parent).setImag(value, indexMapper.get(
            IndexingOperations.cartesianIndicesToLinearIndex(indices, parent.dims, parent.multipliers)));
    }
    
    public NDArray<T> real() {
        return streamLinearIndices()
            .mapToObj(this::getReal).collect(parent.getRealCollectorInternal(dims));
    }

    public NDArray<T> imaginary() {
        return streamLinearIndices()
            .mapToObj(this::getImag).collect(parent.getRealCollectorInternal(dims));
    }

    @Override
    public NDArray<T> abs() {
        return streamLinearIndices()
            .mapToObj(i -> (get(i)).abs()).collect(parent.getRealCollectorInternal(dims));
    }

    public NDArray<T> angle() {
        return streamLinearIndices()
            .mapToObj(i -> (get(i)).getArgument()).collect(parent.getRealCollectorInternal(dims));
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> similar() {
        return ((ComplexNDArray<T>)parent.createNewNDArrayOfSameTypeAsMe(dims));
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> copy() {
        ComplexNDArray<T> newInstance = (ComplexNDArray<T>)parent.createNewNDArrayOfSameTypeAsMe(dims);
        return newInstance.copyFrom(this);
    }

    public void set(Number value, int linearIndex) {
        setReal(value, linearIndex);
        setImag(0, linearIndex);
    }

    public void set(Number value, int... indices) {
        setReal(value, indices);
        setImag(0, indices);
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
            case DIVIDE: return acc.divide(value);
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    protected Complex accumulate(Complex acc, Number value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value.doubleValue());
            case SUBTRACT: return acc.subtract(value.doubleValue());
            case MULTIPLY: return acc.multiply(value.doubleValue());
            case DIVIDE: return acc.divide(value.doubleValue());
            default: throw new IllegalArgumentException();
        }
    }
    
}
