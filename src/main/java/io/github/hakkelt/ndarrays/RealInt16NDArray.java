package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.stream.Collector;

/**
 * N-dimensional arrays holding single-precision (32bit) floating point values.
 * 
 */
public class RealInt16NDArray extends AbstractShortNDArray {
    protected short[] data;

    protected RealInt16NDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public RealInt16NDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new short[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public RealInt16NDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new short[length()];
        copyFrom(array);
    }

    public static NDArray<Short> of(float[] array) {
        return new RealInt16NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(double[] array) {
        return new RealInt16NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(byte[] array) {
        return new RealInt16NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(short[] array) {
        return new RealInt16NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(int[] array) {
        return new RealInt16NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(long[] array) {
        return new RealInt16NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(Object[] realOrComplex) {
        return new RealInt16NDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Short> copyFrom(RealInt16NDArray array) {
        data = array.data.clone();
        return this;
    }

    public Short get(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data[linearIndex] = real.shortValue();
    }

    public static Collector<Object, List<Object>, NDArray<Short>> getCollector(int[] dims) {
        return new RealNDArrayCollector<>(new RealInt16NDArray(dims));
    }


    protected RealInt16NDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new RealInt16NDArray(dims);
    }

    @Override
    protected String name() {
        return "simple";
    }
    
}

