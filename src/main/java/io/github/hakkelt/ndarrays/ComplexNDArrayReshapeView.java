package io.github.hakkelt.ndarrays;

import org.apache.commons.math3.complex.Complex;

class ComplexNDArrayReshapeView<T extends Number> extends AbstractNDArrayReshapeView<Complex,T> implements InternalComplexNDArray<T> {
    
    public ComplexNDArrayReshapeView(NDArray<Complex> parent, int ...newShape) {
        super(parent, newShape);
    }


    public ComplexNDArrayReshapeView(AbstractNDArraySliceView<Complex,T> parent, int ...newShape) {
        super(parent, newShape);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getReal(int linearIndex) {
        return ((ComplexNDArray<T>)parent).getReal(linearIndex);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getReal(int ...indices) {
        boundaryCheck(indices, dims);
        return ((ComplexNDArray<T>)parent).getReal(resolveIndices(indices));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getImag(int ...indices) {
        boundaryCheck(indices, dims);
        return ((ComplexNDArray<T>)parent).getImag(resolveIndices(indices));
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getImag(int linearIndex) {
        return ((ComplexNDArray<T>)parent).getImag(linearIndex);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setReal(Number value, int linearIndex) {
        ((ComplexNDArray<T>)parent).setReal(value, linearIndex);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setReal(Number value, int ...indices) {
        boundaryCheck(indices, dims);
        ((ComplexNDArray<T>)parent).setReal(value, resolveIndices(indices));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setImag(Number value, int linearIndex) {
        ((ComplexNDArray<T>)parent).setImag(value, linearIndex);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setImag(Number value, int ...indices) {
        boundaryCheck(indices, dims);
        ((ComplexNDArray<T>)parent).setImag(value, resolveIndices(indices));
    }

    @Override
    public ComplexNDArray<T> reshape(int... newShape) {
        checkNewShape(newShape, dims, dataLength);
        return new ComplexNDArrayReshapeView<>(parent, newShape);
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
    public void set(Number value, int linearIndex) {
        setReal(value, linearIndex);
        setImag(0, linearIndex);
    }


    @Override
    public void set(Number value, int... indices) {
        setReal(value, indices);
        setImag(0, indices);
    }

    
    @Override
    protected Complex accumulate(Complex acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
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
    
}
