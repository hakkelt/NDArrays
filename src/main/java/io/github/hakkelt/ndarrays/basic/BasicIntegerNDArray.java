package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractIntegerNDArray;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * N-dimensional arrays holding single-precision (32bit) floating point values.
 * 
 */
public class BasicIntegerNDArray extends AbstractIntegerNDArray {
    protected int[] data;

    protected BasicIntegerNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public BasicIntegerNDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new int[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicIntegerNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new int[length()];
        copyFrom(array);
    }

    public static NDArray<Integer> of(float[] array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(double[] array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(byte[] array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(short[] array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(int[] array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(long[] array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(Object[] realOrComplex) {
        return new BasicIntegerNDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Integer> copyFrom(BasicIntegerNDArray array) {
        data = array.data.clone();
        return this;
    }

    public Integer get(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data[linearIndex] = real.intValue();
    }

    public static Collector<Object, List<Object>, NDArray<Integer>> getCollector(int[] dims) {
        return new RealNDArrayCollector<>(new BasicIntegerNDArray(dims));
    }

    protected BasicIntegerNDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new BasicIntegerNDArray(dims);
    }

    @Override
    protected String name() {
        return "basic";
    }
    
}

