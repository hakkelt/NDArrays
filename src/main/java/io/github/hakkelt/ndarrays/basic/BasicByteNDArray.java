package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractByteNDArray;
import io.github.hakkelt.ndarrays.IndexingOperations;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * Reference implementation for the NDArray of byte (8 bit integer) values.
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

    /**
     * Factory method that creates an NDArray from a list or 1D array of float values.
     * 
     * @param array a list or 1D array of float values from which a BasicByteNDArray is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Byte> of(float... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of double values.
     * 
     * @param array a list or 1D array of double values from which a BasicByteNDArray is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Byte> of(double... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of byte values.
     * 
     * @param array a list or 1D array of byte values from which a BasicByteNDArray is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Byte> of(byte... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of short values.
     * 
     * @param array a list or 1D array of short values from which a BasicByteNDArray is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Byte> of(short... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of int values.
     * 
     * @param array a list or 1D array of int values from which a BasicByteNDArray is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Byte> of(int... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of long values.
     * 
     * @param array a list or 1D array of long values from which a BasicByteNDArray is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Byte> of(long... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a multi-dimensional array of numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a BasicByteNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<Byte> of(Object[] array) {
        return new BasicByteNDArray(IndexingOperations.computeDims(array)).copyFrom(array);
    }

    public NDArray<Byte> copyFrom(BasicByteNDArray array) {
        data = array.data.clone();
        return this;
    }

    public Byte get(int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        data[linearIndex] = real.byteValue();
    }
    public Byte get(int... indices) {
        return get(IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
    }

    public void set(Byte value, int... indices) {
        set(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
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

