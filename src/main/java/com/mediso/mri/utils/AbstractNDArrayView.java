package com.mediso.mri.utils;

import java.nio.Buffer;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

abstract class AbstractNDArrayView<T> extends AbstractNDArray<T> {
    AbstractNDArray<T> parent;

    @Override
    public Object eltype() {
        return parent.eltype();
    }

    @Override
    protected String printItem(int index, String format) {
        return parent.printItem(index, format);
    }

    @Override
    public NDArray<T> fill(T value) {
        streamLinearIndices().parallel().forEach(i -> set(value, i));
        return this;
    }

    @Override
    public NDArray<T> fill(float value) {
        streamLinearIndices().parallel().forEach(i -> set(value, i));
        return this;
    }

    @Override
    public NDArray<T> fill(double value) {
        streamLinearIndices().parallel().forEach(i -> set(value, i));
        return this;
    }

    @Override
    public String dataTypeAsString() {
        return parent.dataTypeAsString();
    }

    @Override
    protected T zeroT() {
        return parent.zeroT();
    }

    @Override
    protected T oneT() {
        return parent.oneT();
    }

    @Override
    protected T accumulate(T acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    @Override
    protected T accumulate(T acc, T value, AbstractNDArray.AccumulateOperators operator) {
        return parent.accumulate(acc, value, operator);
    }

    @Override
    protected T accumulate(T acc, Complex value, AbstractNDArray.AccumulateOperators operator) {
        return parent.accumulate(acc, value, operator);
    }

    @Override
    protected T accumulate(T acc, Double value, AbstractNDArray.AccumulateOperators operator) {
        return parent.accumulate(acc, value, operator);
    }

    @Override
    protected T accumulate(T acc, Float value, AbstractNDArray.AccumulateOperators operator) {
        return parent.accumulate(acc, value, operator);
    }

    @Override
    protected T accumulate(T acc, Integer value, AbstractNDArray.AccumulateOperators operator) {
        return parent.accumulate(acc, value, operator);
    }

    @Override
    protected Collector<Object, List<Object>, NDArray<T>> getCollector(int[] dims) {
        return parent.getCollector(dims);
    }

    @Override
    public NDArray<T> similar() {
        return parent.similar(this);
    }

    @Override
    protected NDArray<T> similar(NDArray<?> array) {
        return parent.similar(array);
    }

    @Override
    public NDArray<T> copy() {
        return parent.copy(this);
    }

    @Override
    protected NDArray<T> copy(NDArray<?> array) {
        return parent.copy(array);
    }
    
    public int[] linearIndexToViewIndices(int linearIndex) {
        return linearIndexToCartesianIndices(linearIndex, multipliers, ndims(), dataLength);
    }

    @Override
    protected AbstractNDArray<T> createNewNDArrayOfSameTypeAsMe(int[] dims) {
        return parent.createNewNDArrayOfSameTypeAsMe(dims);
    }
    
    public NDArray<Double> real() {
        return streamLinearIndices()
            .mapToObj(this::getReal).collect(NDArrayCollectors.toRealF64NDArray(dims));
    }
    public NDArray<Double> imaginary() {
        return streamLinearIndices()
            .mapToObj(this::getImag).collect(NDArrayCollectors.toRealF64NDArray(dims));
    }
    public NDArray<Double> abs() {
        if (eltype() == Complex.class)
            return streamLinearIndices()
                .mapToObj(i -> ((Complex)get(i)).abs()).collect(NDArrayCollectors.toRealF64NDArray(dims));
        if (eltype() == Float.class)
            return streamLinearIndices()
                .mapToObj(i -> Math.abs(((Float)get(i)).doubleValue())).collect(NDArrayCollectors.toRealF64NDArray(dims));
        return streamLinearIndices()
            .mapToObj(i -> Math.abs((Double)get(i))).collect(NDArrayCollectors.toRealF64NDArray(dims));
    }
    public NDArray<Double> angle() {
        if (eltype() == Complex.class)
            return streamLinearIndices()
                .mapToObj(i -> ((Complex)get(i)).getArgument()).collect(NDArrayCollectors.toRealF64NDArray(dims));
        return new RealF64NDArray(dims);
    }

    @Override
    public Buffer getBuffer() {
        return copy().getBuffer();
    }
}
