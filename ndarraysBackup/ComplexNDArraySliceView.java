package io.github.hakkelt.ndarrays;

import org.apache.commons.math3.complex.Complex;

/**
 * A view for a ComplexNDArray that slices the parent ComplexNDArray.
 * When slice(...) is called for a ComplexNDArray, an instance of this class is returned.
 */
public class ComplexNDArraySliceView<T extends Number> extends AbstractNDArraySliceView<Complex,T> implements ComplexNDArray<T> {
    
    public ComplexNDArraySliceView(NDArray<Complex> parent, Object ...slicingExpressions) {
        super(parent, slicingExpressions);
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
        return ((ComplexNDArray<T>)parent.createNewNDArrayOfSameTypeAsMe(shape));
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
        return getRealUnchecked(linearIndexToViewIndices(linearIndex));
    }

    @Override
    protected T getRealUnchecked(int... indices) {
        return parent.getRealUnchecked(slicingExpression.resolveToParentIndices(indices));
    }

    @Override
    protected T getImagUnchecked(int linearIndex) {
        return getImagUnchecked(linearIndexToViewIndices(linearIndex));
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
    protected void setRealUnchecked(T value, int... indices) {
        parent.setRealUnchecked(value, slicingExpression.resolveToParentIndices(indices));
    }

    @Override
    protected void setImagUnchecked(T value, int linearIndex) {
        setImagUnchecked(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    protected void setImagUnchecked(T value, int... indices) {
        parent.setImagUnchecked(value, slicingExpression.resolveToParentIndices(indices));
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
