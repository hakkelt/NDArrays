package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.stream.Collector;

/**
 * N-dimensional arrays holding single-precision (32bit) floating point values.
 * 
 */
public class RealInt8NDArray extends AbstractByteNDArray {
    protected byte[] data;

    protected RealInt8NDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public RealInt8NDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new byte[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public RealInt8NDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new byte[length()];
        copyFrom(array);
    }

    public static NDArray<Byte> of(float[] array) {
        return new RealInt8NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(double[] array) {
        return new RealInt8NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(byte[] array) {
        return new RealInt8NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(short[] array) {
        return new RealInt8NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(int[] array) {
        return new RealInt8NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(long[] array) {
        return new RealInt8NDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(Object[] realOrComplex) {
        return new RealInt8NDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Byte> copyFrom(RealInt8NDArray array) {
        data = array.data.clone();
        return this;
    }

    public Byte get(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data[linearIndex] = real.byteValue();
    }

    public static Collector<Object, List<Object>, NDArray<Byte>> getCollector(int[] dims) {
        return new RealNDArrayCollector<>(new RealInt8NDArray(dims));
    }

    protected RealInt8NDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new RealInt8NDArray(dims);
    }

    @Override
    protected String name() {
        return "simple";
    }
    
}

