package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractShortNDArray;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * N-dimensional arrays holding single-precision (32bit) floating point values.
 * 
 */
public class BasicShortNDArray extends AbstractShortNDArray {
    protected short[] data;

    protected BasicShortNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public BasicShortNDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new short[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicShortNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new short[length()];
        copyFrom(array);
    }

    public static NDArray<Short> of(float[] array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(double[] array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(byte[] array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(short[] array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(int[] array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(long[] array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Short> of(Object[] realOrComplex) {
        return new BasicShortNDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Short> copyFrom(BasicShortNDArray array) {
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
        return new RealNDArrayCollector<>(new BasicShortNDArray(dims));
    }

    protected BasicShortNDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new BasicShortNDArray(dims);
    }

    @Override
    protected String name() {
        return "basic";
    }
    
}

