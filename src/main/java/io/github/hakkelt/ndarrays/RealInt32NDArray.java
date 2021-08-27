package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.stream.Collector;

/**
 * N-dimensional arrays holding single-precision (32bit) floating point values.
 * 
 */
public class RealInt32NDArray extends AbstractIntegerNDArray {
    protected int[] data;

    protected RealInt32NDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public RealInt32NDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new int[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public RealInt32NDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new int[length()];
        copyFrom(array);
    }

    public static NDArray<Integer> of(float[] array) {
        return new RealInt32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(double[] array) {
        return new RealInt32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(byte[] array) {
        return new RealInt32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(short[] array) {
        return new RealInt32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(int[] array) {
        return new RealInt32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(long[] array) {
        return new RealInt32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Integer> of(Object[] realOrComplex) {
        return new RealInt32NDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Integer> copyFrom(RealInt32NDArray array) {
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
        return new RealNDArrayCollector<>(new RealInt32NDArray(dims));
    }


    protected RealInt32NDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new RealInt32NDArray(dims);
    }

    @Override
    protected String name() {
        return "simple";
    }
    
}

