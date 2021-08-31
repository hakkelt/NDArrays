package io.github.hakkelt.ndarrays;

import org.apache.commons.math3.complex.Complex;

public class ComplexNDArrayPermuteDimsView<T extends Number> extends AbstractNDArrayPermuteDimsView<Complex,T> implements ComplexNDArrayTrait<T> {

    public ComplexNDArrayPermuteDimsView(NDArray<Complex> parent, int ...dimsOrder) {
        super(parent, dimsOrder);
    }

    public ComplexNDArrayPermuteDimsView(AbstractNDArraySliceView<Complex,T> parent, int ...dimsOrder) {
        super(parent, dimsOrder);
    }

    @Override
    public T getReal(int linearIndex) {
        return getReal(linearIndexToViewIndices(linearIndex));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getReal(int ...indices) {
        boundaryCheck(indices, dims);
        return ((ComplexNDArray<T>)parent).getReal(permuteArray(indices, dimsOrder));
    }

    @Override
    public T getImag(int linearIndex) {
        return getImag(linearIndexToViewIndices(linearIndex));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getImag(int ...indices) {
        boundaryCheck(indices, dims);
        return ((ComplexNDArray<T>)parent).getImag(permuteArray(indices, dimsOrder));
    }

    @Override
    public void setReal(Number value, int linearIndex) {
        setReal(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setReal(Number value, int ...indices) {
        boundaryCheck(indices, dims);
        ((ComplexNDArray<T>)parent).setReal(value, permuteArray(indices, dimsOrder));
    }

    @Override
    public void setImag(Number value, int linearIndex) {
        setImag(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setImag(Number value, int ...indices) {
        boundaryCheck(indices, dims);
        ((ComplexNDArray<T>)parent).setImag(value, permuteArray(indices, dimsOrder));
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
