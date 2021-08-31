package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractByteNDArray;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * N-dimensional arrays holding single-precision (32bit) floating point values.
 * 
 */
public class BasicByteNDArray extends AbstractByteNDArray {
    protected byte[] data;

    protected BasicByteNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public BasicByteNDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new byte[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicByteNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new byte[length()];
        copyFrom(array);
    }

    public static NDArray<Byte> of(float[] array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(double[] array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(byte[] array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(short[] array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(int[] array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(long[] array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    public static NDArray<Byte> of(Object[] realOrComplex) {
        return new BasicByteNDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    public NDArray<Byte> copyFrom(BasicByteNDArray array) {
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
        return new RealNDArrayCollector<>(new BasicByteNDArray(dims));
    }

    protected BasicByteNDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new BasicByteNDArray(dims);
    }

    @Override
    protected String name() {
        return "basic";
    }
    
}

