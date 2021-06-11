package io.github.hakkelt.ndarrays;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

abstract class RealNDArray<T> extends AbstractNDArray<T> {
    protected static final String ERROR_INCOMPATIBLE_SIZE =
        "The size of the input (%d) is incompatible with the given dimensions: %s!";
    protected static final String ERROR_CANNOT_ASSIGN_COMPLEX_VALUE =
        "Cannot assign complex value to any element of a real array!";

    protected abstract void baseConstructor(int[] dims);
    protected abstract void baseConstructor(int[] dims, ByteBuffer buffer);

    protected RealNDArray() {}

    protected RealNDArray(int[] dims) {
        baseConstructor(dims);
    }

    protected RealNDArray(float[] real) {
        baseConstructor(new int[] { real.length });
        set(real);
    }

    protected RealNDArray(double[] real) {
        baseConstructor(new int[] { real.length });
        set(real);
    }

    protected RealNDArray(Object[] real) {
        List<Integer> dimsList = new ArrayList<>();
        baseConstructor(listToArray(computeDims(dimsList, real)));
        set(real);
    }

    protected RealNDArray(int[] dims, float[] real) {
        checkCompatibleSize(dims, real);
        baseConstructor(dims);
        set(real);
    }

    protected RealNDArray(int[] dims, double[] real) {
        checkCompatibleSize(dims, real);
        baseConstructor(dims);
        set(real);
    }

    protected RealNDArray(NDArray<?> array) {
        this(array.dims());
        set(array);
    }

    @Override
    public void set(float value, int linearIndex) {
        setReal(value, linearIndex);
    }
    @Override
    public void set(double value, int linearIndex) {
        setReal(value, linearIndex);
    }

    @Override
    public void setImag(float imag, int linearIndex) {
        throw new UnsupportedOperationException(ERROR_CANNOT_ASSIGN_COMPLEX_VALUE);
    }

    @Override
    public void setImag(double imag, int linearIndex) {
        throw new UnsupportedOperationException(ERROR_CANNOT_ASSIGN_COMPLEX_VALUE);
    }

    @Override
    protected String printItem(int index, String format) {
        return String.format(format, get(index));
    }

    @Override
    protected T accumulate(T acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operation) {
        if (array.eltype() == Float.class)
            return accumulate(acc, (Float)array.get(linearIndex), operation);
        else if (array.eltype() == Double.class)
            return accumulate(acc, (Double)array.get(linearIndex), operation);
        else
            return accumulate(acc, (Float)array.get(linearIndex), operation);
    }
    
    public NDArray<Double> real() {
        return streamLinearIndices()
            .mapToObj(this::getReal).collect(NDArrayCollectors.toRealF64NDArray(dims));
    }
    public NDArray<Double> imaginary() {
        return new RealF64NDArray(dims);
    }
    public NDArray<Double> abs() {
        return streamLinearIndices()
            .mapToObj(i -> Math.abs(getReal(i))).collect(NDArrayCollectors.toRealF64NDArray(dims));
    }
    public NDArray<Double> angle() {
        return new RealF64NDArray(dims);
    }

}