package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

/**
 * N-dimensional arrays holding double-precision (64bit) complex values.
 * 
 */
public class ComplexF64NDArray extends AbstractComplexNDArray<Double> {
    protected double[] data;

    protected ComplexF64NDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public ComplexF64NDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new double[length() * 2];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public ComplexF64NDArray(NDArray<?> array) {
        baseConstuctor(array.dims());
        this.data = new double[length() * 2];
        copyFrom(array);
    }

    public static ComplexNDArray<Double> of(float[] array) {
        return new ComplexF64NDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Double> of(double[] array) {
        return new ComplexF64NDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Double> of(byte[] array) {
        return new ComplexF64NDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Double> of(short[] array) {
        return new ComplexF64NDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Double> of(int[] array) {
        return new ComplexF64NDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Double> of(long[] array) {
        return new ComplexF64NDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Double> of(Object[] realOrComplex) {
        return new ComplexF64NDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }
    
    public static ComplexNDArray<Double> of(float[] real, float[] imag) {
        return new ComplexF64NDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Double> of(double[] real, double[] imag) {
        return new ComplexF64NDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Double> of(byte[] real, byte[] imag) {
        return new ComplexF64NDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Double> of(short[] real, short[] imag) {
        return new ComplexF64NDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Double> of(int[] real, int[] imag) {
        return new ComplexF64NDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Double> of(long[] real, long[] imag) {
        return new ComplexF64NDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Double> of(Object[] real, Object[] imag) {
        return new ComplexF64NDArray(computeDims(real)).copyFrom(real, imag);
    }

    protected String name() {
        return "simple";
    }

    public ComplexNDArray<Double> copyFrom(ComplexF64NDArray array) {
        data = array.data.clone();
        return this;
    }

    public Double getReal(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data[linearIndex * 2];
    }

    public Double getImag(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data[linearIndex * 2 + 1];
    }

    public void setReal(Number real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data[linearIndex * 2] = real.floatValue();
    }

    public void setImag(Number imag, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data[linearIndex * 2 + 1] = imag.floatValue();
    }

    public static Collector<Object, List<Object>, NDArray<Complex>> getCollector(int[] dims) {
        return new ComplexNDArrayCollector<>(new ComplexF64NDArray(dims));
    }

    protected AbstractComplexNDArray<Double> createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new ComplexF64NDArray(dims);
    }

    protected AbstractRealNDArray<Double> createNewRealNDArrayOfSameTypeAsMe(int... dims) {
        return new RealF64NDArray(dims);
    }
    
}
