package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractLongNDArray;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * N-dimensional arrays holding single-precision (32bit) floating polong values.
 * 
 */
public class BasicLongNDArray extends AbstractLongNDArray {
    protected long[] data;

    protected BasicLongNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public BasicLongNDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new long[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicLongNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new long[length()];
        copyFrom(array);
    }

    public static NDArray<Long> of(float[] array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(double[] array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(byte[] array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(short[] array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(int[] array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(long[] array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(Object[] realOrComplex) {
        return new BasicLongNDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Long> copyFrom(BasicLongNDArray array) {
        data = array.data.clone();
        return this;
    }

    public Long get(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data[linearIndex] = real.longValue();
    }

    public static Collector<Object, List<Object>, NDArray<Long>> getCollector(int[] dims) {
        return new RealNDArrayCollector<>(new BasicLongNDArray(dims));
    }

    protected BasicLongNDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new BasicLongNDArray(dims);
    }

    @Override
    protected String name() {
        return "basic";
    }
    
}

