package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.stream.Collector;

/**
 * N-dimensional arrays holding single-precision (32bit) floating polong values.
 * 
 */
public class RealInt64NDArray extends AbstractLongNDArray {
    protected long[] data;

    protected RealInt64NDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public RealInt64NDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new long[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public RealInt64NDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new long[length()];
        copyFrom(array);
    }

    public static NDArray<Long> of(float[] array) {
        return new RealInt64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(double[] array) {
        return new RealInt64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(byte[] array) {
        return new RealInt64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(short[] array) {
        return new RealInt64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(int[] array) {
        return new RealInt64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(long[] array) {
        return new RealInt64NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Long> of(Object[] realOrComplex) {
        return new RealInt64NDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Long> copyFrom(RealInt64NDArray array) {
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
        return new RealNDArrayCollector<>(new RealInt64NDArray(dims));
    }


    protected RealInt64NDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new RealInt64NDArray(dims);
    }

    @Override
    protected String name() {
        return "simple";
    }
    
}

