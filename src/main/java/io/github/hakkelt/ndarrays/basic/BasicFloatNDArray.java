package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractFloatNDArray;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * N-dimensional arrays holding single-precision (32bit) floating point values.
 * 
 */
public class BasicFloatNDArray extends AbstractFloatNDArray {
    protected float[] data;

    protected BasicFloatNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public BasicFloatNDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new float[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicFloatNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new float[length()];
        copyFrom(array);
    }

    public static NDArray<Float> of(float[] array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(double[] array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(byte[] array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(short[] array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(int[] array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(long[] array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(Object[] realOrComplex) {
        return new BasicFloatNDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Float> copyFrom(BasicFloatNDArray array) {
        data = array.data.clone();
        return this;
    }

    public Float get(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data[linearIndex] = real.floatValue();
    }

    public static Collector<Object, List<Object>, NDArray<Float>> getCollector(int[] dims) {
        return new RealNDArrayCollector<>(new BasicFloatNDArray(dims));
    }

    protected BasicFloatNDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new BasicFloatNDArray(dims);
    }

    @Override
    protected String name() {
        return "basic";
    }
    
}

