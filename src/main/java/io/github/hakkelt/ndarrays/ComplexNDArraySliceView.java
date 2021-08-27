package io.github.hakkelt.ndarrays;

import org.apache.commons.math3.complex.Complex;

class ComplexNDArraySliceView<T extends Number> extends AbstractNDArraySliceView<Complex,T> implements InternalComplexNDArray<T> {
    
    public ComplexNDArraySliceView(NDArray<Complex> parent, Object ...slicingExpressions) {
        super(parent, slicingExpressions);
    }


    public ComplexNDArraySliceView(AbstractNDArraySliceView<Complex,T> parent, Object ...slicingExpressions) {
        super(parent, slicingExpressions);
    }

    @Override
    public T getReal(int linearIndex) {
        return getReal(linearIndexToViewIndices(linearIndex));
    }


    @Override
    @SuppressWarnings("unchecked")
    public T getReal(int... indices) {
        return ((ComplexNDArray<T>)parent).getReal(slicingExpression.resolveToParentIndices(indices));
    }


    @Override
    public T getImag(int linearIndex) {
        return getImag(linearIndexToViewIndices(linearIndex));
    }


    @Override
    @SuppressWarnings("unchecked")
    public T getImag(int... indices) {
        return ((ComplexNDArray<T>)parent).getImag(slicingExpression.resolveToParentIndices(indices));
    }


    @Override
    public void setReal(Number value, int linearIndex) {
        setReal(value, linearIndexToViewIndices(linearIndex));
    }


    @Override
    @SuppressWarnings("unchecked")
    public void setReal(Number value, int... indices) {
        ((ComplexNDArray<T>)parent).setReal(value, slicingExpression.resolveToParentIndices(indices));
    }


    @Override
    public void setImag(Number value, int linearIndex) {
        setImag(value, linearIndexToViewIndices(linearIndex));
    }


    @Override
    @SuppressWarnings("unchecked")
    public void setImag(Number value, int... indices) {
        ((ComplexNDArray<T>)parent).setImag(value, slicingExpression.resolveToParentIndices(indices));
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
