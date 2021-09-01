package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractFloatNDArray;
import io.github.hakkelt.ndarrays.IndexingOperations;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * Reference implementation for the NDArray of float (single-precision, 32 bit floating point) values.
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

    /**
     * Factory method that creates an NDArray from a list or 1D array of float values.
     * 
     * @param array a list or 1D array of float values from which a BasicFloatNDArray is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Float> of(float... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of double values.
     * 
     * @param array a list or 1D array of double values from which a BasicFloatNDArray is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Float> of(double... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of byte values.
     * 
     * @param array a list or 1D array of byte values from which a BasicFloatNDArray is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Float> of(byte... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of short values.
     * 
     * @param array a list or 1D array of short values from which a BasicFloatNDArray is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Float> of(short... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of int values.
     * 
     * @param array a list or 1D array of int values from which a BasicFloatNDArray is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Float> of(int... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of long values.
     * 
     * @param array a list or 1D array of long values from which a BasicFloatNDArray is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Float> of(long... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a multi-dimensional array of numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a BasicFloatNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<Float> of(Object[] array) {
        return new BasicFloatNDArray(IndexingOperations.computeDims(array)).copyFrom(array);
    }

    public NDArray<Float> copyFrom(BasicFloatNDArray array) {
        data = array.data.clone();
        return this;
    }

    public Float get(int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        data[linearIndex] = real.floatValue();
    }

    public Float get(int... indices) {
        return get(IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
    }

    public void set(Float value, int... indices) {
        set(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
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

