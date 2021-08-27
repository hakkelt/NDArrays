package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.stream.Collector;

/**
 * N-dimensional arrays holding double-precision (64bit) doubleing point values.
 * 
 */
public class RealF64NDArray extends AbstractDoubleNDArray {
    protected double[] data;

    protected RealF64NDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public RealF64NDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new double[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public RealF64NDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new double[length()];
        copyFrom(array);
    }

    public static NDArray<Double> of(float[] array) {
        return new RealF64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(double[] array) {
        return new RealF64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(byte[] array) {
        return new RealF64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(short[] array) {
        return new RealF64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(int[] array) {
        return new RealF64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(long[] array) {
        return new RealF64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(Object[] realOrComplex) {
        return new RealF64NDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Double> copyFrom(RealF64NDArray array) {
        data = array.data.clone();
        return this;
    }

    public Double get(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data[linearIndex] = real.doubleValue();
    }

    public static Collector<Object, List<Object>, NDArray<Double>> getCollector(int[] dims) {
        return new RealNDArrayCollector<>(new RealF64NDArray(dims));
    }


    protected RealF64NDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new RealF64NDArray(dims);
    }

    @Override
    protected String name() {
        return "simple";
    }
    
}

