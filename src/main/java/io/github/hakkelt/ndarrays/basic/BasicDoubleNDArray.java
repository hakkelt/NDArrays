package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractDoubleNDArray;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * N-dimensional arrays holding double-precision (64bit) doubleing point values.
 * 
 */
public class BasicDoubleNDArray extends AbstractDoubleNDArray {
    protected double[] data;

    protected BasicDoubleNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public BasicDoubleNDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new double[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicDoubleNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new double[length()];
        copyFrom(array);
    }

    public static NDArray<Double> of(float[] array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(double[] array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(byte[] array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(short[] array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(int[] array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(long[] array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Double> of(Object[] realOrComplex) {
        return new BasicDoubleNDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Double> copyFrom(BasicDoubleNDArray array) {
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
        return new RealNDArrayCollector<>(new BasicDoubleNDArray(dims));
    }

    protected BasicDoubleNDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new BasicDoubleNDArray(dims);
    }

    @Override
    protected String name() {
        return "basic";
    }
    
}

