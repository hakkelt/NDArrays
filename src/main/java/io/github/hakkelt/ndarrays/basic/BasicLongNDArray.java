package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractLongNDArray;
import io.github.hakkelt.ndarrays.IndexingOperations;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * Reference implementation for the NDArray of long (64 bit integer) values.
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

    /**
     * Factory method that creates an NDArray from a list or 1D array of float values.
     * 
     * @param array a list or 1D array of float values from which a BasicLongNDArray is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Long> of(float... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of double values.
     * 
     * @param array a list or 1D array of double values from which a BasicLongNDArray is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Long> of(double... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of byte values.
     * 
     * @param array a list or 1D array of byte values from which a BasicLongNDArray is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Long> of(byte... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of short values.
     * 
     * @param array a list or 1D array of short values from which a BasicLongNDArray is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Long> of(short... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of int values.
     * 
     * @param array a list or 1D array of int values from which a BasicLongNDArray is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Long> of(int... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of long values.
     * 
     * @param array a list or 1D array of long values from which a BasicLongNDArray is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Long> of(long... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a multi-dimensional array of numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a BasicLongNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<Long> of(Object[] array) {
        return new BasicLongNDArray(IndexingOperations.computeDims(array)).copyFrom(array);
    }

    public NDArray<Long> copyFrom(BasicLongNDArray array) {
        data = array.data.clone();
        return this;
    }

    public Long get(int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        data[linearIndex] = real.longValue();
    }

    public Long get(int... indices) {
        return get(IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
    }

    public void set(Long value, int... indices) {
        set(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
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

