package io.github.hakkelt.ndarrays;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.complex.Complex;

abstract class ComplexNDArray extends AbstractNDArray<Complex> {

    protected abstract void baseConstructor(int[] dims);
    protected abstract void baseConstructor(int[] dims, ByteBuffer buffer);

    protected ComplexNDArray() {}

    protected ComplexNDArray(int[] dims) {
        baseConstructor(dims);
    }

    protected ComplexNDArray(float[] real) {
        baseConstructor(new int[] { real.length });
        set(real);
    }

    protected ComplexNDArray(double[] real) {
        baseConstructor(new int[] { real.length });
        set(real);
    }

    protected ComplexNDArray(Complex[] compl) {
        baseConstructor(new int[] { compl.length });
        set(compl);
    }

    protected ComplexNDArray(Object[] realOrComplex) {
        List<Integer> dimsList = new ArrayList<>();
        baseConstructor(listToArray(computeDims(dimsList, realOrComplex)));
        set(realOrComplex);
    }

    protected ComplexNDArray(float[] real, float[] imag) {
        checkEqualLength(real, imag);
        baseConstructor(new int[] { real.length });
        set(real, imag);
    }

    protected ComplexNDArray(double[] real, double[] imag) {
        checkEqualLength(real, imag);
        baseConstructor(new int[] { real.length });
        set(real, imag);
    }

    protected ComplexNDArray(Object[] real, Object[] imag) {
        List<Integer> realDimsList = new ArrayList<>();
        computeDims(realDimsList, real);
        List<Integer> imagDimsList = new ArrayList<>();
        computeDims(imagDimsList, real);
        checkCompatibleSize(realDimsList, imagDimsList);
        baseConstructor(listToArray(realDimsList));
        set(real, imag);
    }

    protected ComplexNDArray(int[] dims, float[] real) {
        checkCompatibleSize(dims, real);
        baseConstructor(dims);
        set(real);
    }

    protected ComplexNDArray(int[] dims, double[] real) {
        checkCompatibleSize(dims, real);
        baseConstructor(dims);
        set(real);
    }

    protected ComplexNDArray(int[] dims, float[] real, float[] imag) {
        checkEqualLength(real, imag);
        checkCompatibleSize(dims, real);
        baseConstructor(dims);
        set(real, imag);
    }

    protected ComplexNDArray(int[] dims, double[] real, double[] imag) {
        checkEqualLength(real, imag);
        checkCompatibleSize(dims, real);
        baseConstructor(dims);
        set(real, imag);
    }

    protected ComplexNDArray(NDArray<?> array) {
        this(array.dims());
        set(array);
    }

    @Override
    public void set(float value, int linearIndex) {
        setReal(value, linearIndex);
        setImag(0, linearIndex);
    }
    @Override
    public void set(double value, int linearIndex) {
        setReal(value, linearIndex);
        setImag(0, linearIndex);
    }

    @Override
    public void set(Complex value, int linearIndex) {
        setReal(value.getReal(), linearIndex);
        setImag(value.getImaginary(), linearIndex);
    }

    protected void set(float real, float imag, int linearIndex) {
        setReal(real, linearIndex);
        setImag(imag, linearIndex);
    }

    protected void set(double real, double imag, int linearIndex) {
        setReal(real, linearIndex);
        setImag(imag, linearIndex);
    }

    @Override
    public Object eltype() {
        return Complex.class;
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
        return streamLinearIndices()
            .mapToObj(i -> get(i).abs()).collect(NDArrayCollectors.toRealF64NDArray(dims));
    }
    public NDArray<Double> angle() {
        return streamLinearIndices()
            .mapToObj(i -> get(i).getArgument()).collect(NDArrayCollectors.toRealF64NDArray(dims));
    }

    @Override
    protected String printItem(int index, String format) {
        Complex item = get(index);
        String complexFormat = format + "+" + format + "i";
        return String.format(complexFormat, item.getReal(), item.getImaginary());
    }

    @Override
    protected Complex zeroT() {
        return new Complex(0, 0);
    }

    @Override
    protected Complex oneT() {
        return new Complex(1, 0);
    }

    protected Complex accumulate(Complex acc, Complex value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value);
            case SUBTRACT: return acc.subtract(value);
            case MULTIPLY: return acc.multiply(value);
            case DIVIDE: return acc.divide(value);
            default: throw new IllegalArgumentException();
        }
    }

    protected Complex accumulate(Complex acc, Double value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value);
            case SUBTRACT: return acc.subtract(value);
            case MULTIPLY: return acc.multiply(value);
            case DIVIDE: return acc.divide(value);
            default: throw new IllegalArgumentException();
        }
    }

    protected Complex accumulate(Complex acc, Float value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value);
            case SUBTRACT: return acc.subtract(value);
            case MULTIPLY: return acc.multiply(value);
            case DIVIDE: return acc.divide(value);
            default: throw new IllegalArgumentException();
        }
    }

    protected Complex accumulate(Complex acc, Integer value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value);
            case SUBTRACT: return acc.subtract(value);
            case MULTIPLY: return acc.multiply(value);
            case DIVIDE: return acc.divide(value);
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    protected Complex accumulate(Complex acc, NDArray<?> array, int linearIndex, AbstractNDArray.AccumulateOperators operation) {
        if (array.eltype() == Complex.class)
            return accumulate(acc, (Complex)array.get(linearIndex), operation);
        else if (array.eltype() == Double.class)
            return accumulate(acc, (Double)array.get(linearIndex), operation);
        else
            return accumulate(acc, (Float)array.get(linearIndex), operation);
    }

    protected void checkCompatibleSize(List<Integer> realDimsList, List<Integer> imagDimsList) {
        if (realDimsList.size() != imagDimsList.size())
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
        for (int i = 0; i < realDimsList.size(); i++)
            if (!realDimsList.get(i).equals(imagDimsList.get(i)))
                throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
    }

    protected void checkEqualLength(float[] real, float[] imag) {
        if (imag.length != real.length)
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
    }

    protected void checkEqualLength(double[] real, double[] imag) {
        if (imag.length != real.length)
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
    }
}
