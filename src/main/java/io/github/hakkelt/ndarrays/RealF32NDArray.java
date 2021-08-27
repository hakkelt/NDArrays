package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.stream.Collector;

/**
 * N-dimensional arrays holding single-precision (32bit) floating point values.
 * 
 */
public class RealF32NDArray extends AbstractFloatNDArray {
    protected float[] data;

    protected RealF32NDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public RealF32NDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new float[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public RealF32NDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new float[length()];
        copyFrom(array);
    }

    public static NDArray<Float> of(float[] array) {
        return new RealF32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(double[] array) {
        return new RealF32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(byte[] array) {
        return new RealF32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(short[] array) {
        return new RealF32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(int[] array) {
        return new RealF32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(long[] array) {
        return new RealF32NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Float> of(Object[] realOrComplex) {
        return new RealF32NDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Float> copyFrom(RealF32NDArray array) {
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
        return new RealNDArrayCollector<>(new RealF32NDArray(dims));
    }


    protected RealF32NDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new RealF32NDArray(dims);
    }

    @Override
    protected String name() {
        return "simple";
    }
    
}

