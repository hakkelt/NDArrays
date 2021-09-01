package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractDoubleNDArray;
import io.github.hakkelt.ndarrays.IndexingOperations;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * Reference implementation for the NDArray of double (double-precision, 64 bit floating point) values.
 */
public class BasicDoubleNDArray extends AbstractDoubleNDArray {
    protected double[] data;

    protected BasicDoubleNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public BasicDoubleNDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new double[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicDoubleNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.dims());
        this.data = new double[length()];
        copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of float values.
     * 
     * @param array a list or 1D array of float values from which a BasicDoubleNDArray is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Double> of(float... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of double values.
     * 
     * @param array a list or 1D array of double values from which a BasicDoubleNDArray is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Double> of(double... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of byte values.
     * 
     * @param array a list or 1D array of byte values from which a BasicDoubleNDArray is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Double> of(byte... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of short values.
     * 
     * @param array a list or 1D array of short values from which a BasicDoubleNDArray is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Double> of(short... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of int values.
     * 
     * @param array a list or 1D array of int values from which a BasicDoubleNDArray is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Double> of(int... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a list or 1D array of long values.
     * 
     * @param array a list or 1D array of long values from which a BasicDoubleNDArray is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Double> of(long... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }
    
    /**
     * Factory method that creates an NDArray from a multi-dimensional array of numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a BasicDoubleNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<Double> of(Object[] array) {
        return new BasicDoubleNDArray(IndexingOperations.computeDims(array)).copyFrom(array);
    }

    public NDArray<Double> copyFrom(BasicDoubleNDArray array) {
        data = array.data.clone();
        return this;
    }

    public Double get(int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        data[linearIndex] = real.doubleValue();
    }

    public Double get(int... indices) {
        return get(IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
    }

    public void set(Double value, int... indices) {
        set(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
    }

    public static Collector<Object, List<Object>, NDArray<Double>> getCollector(int[] dims) {
        return new RealNDArrayCollector<>(new BasicDoubleNDArray(dims));
    }

    protected BasicDoubleNDArray createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new BasicDoubleNDArray(dims);
    }

    @Override
    protected String name() {
        return "basic";
    }
    
}

