package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractIntegerNDArray;
import io.github.hakkelt.ndarrays.IndexingOperations;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * Reference implementation for the NDArray of int (32 bit integer) values.
 */
public class BasicIntegerNDArray extends AbstractIntegerNDArray {
    protected int[] data;

    protected BasicIntegerNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public BasicIntegerNDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new int[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicIntegerNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new int[length()];
        copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of float values.
     * 
     * @param array a list or 1D array of float values from which a BasicIntegerNDArray is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Integer> of(float... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of double values.
     * 
     * @param array a list or 1D array of double values from which a BasicIntegerNDArray is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Integer> of(double... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of byte values.
     * 
     * @param array a list or 1D array of byte values from which a BasicIntegerNDArray is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Integer> of(byte... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of short values.
     * 
     * @param array a list or 1D array of short values from which a BasicIntegerNDArray is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Integer> of(short... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of int values.
     * 
     * @param array a list or 1D array of int values from which a BasicIntegerNDArray is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Integer> of(int... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of long values.
     * 
     * @param array a list or 1D array of long values from which a BasicIntegerNDArray is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Integer> of(long... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a multi-dimensional array of numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a BasicIntegerNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<Integer> of(Object[] array) {
        return new BasicIntegerNDArray(IndexingOperations.computeDims(array)).copyFrom(array);
    }

    public NDArray<Integer> copyFrom(BasicIntegerNDArray array) {
        data = array.data.clone();
        return this;
    }

    public Integer get(int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        data[linearIndex] = real.intValue();
    }

    public Integer get(int... indices) {
        return get(IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
    }

    public void set(Integer value, int... indices) {
        set(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
    }

    public static Collector<Object, List<Object>, NDArray<Integer>> getCollector(int[] dims) {
        return new RealNDArrayCollector<>(new BasicIntegerNDArray(dims));
    }

    protected BasicIntegerNDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new BasicIntegerNDArray(dims);
    }

    @Override
    protected String name() {
        return "basic";
    }
    
}

