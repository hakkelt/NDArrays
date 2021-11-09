package io.github.hakkelt.ndarrays.basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractShortNDArray;
import io.github.hakkelt.ndarrays.IndexingOperations;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * Reference implementation for the NDArray of short (16 bit integer) values.
 */
public final class BasicShortNDArray extends AbstractShortNDArray {
    protected short[] data;

    @SuppressWarnings("unused")
    private BasicShortNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicShortNDArray(int... shape) {
        baseConstuctor(shape);
        this.data = new short[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicShortNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.shape());
        this.data = new short[length()];
        copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of float
     * values.
     * 
     * @param array a list or 1D array of float values from which a
     *              BasicShortNDArray is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Short> of(float... array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of double
     * values.
     * 
     * @param array a list or 1D array of double values from which a
     *              BasicShortNDArray is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Short> of(double... array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of byte
     * values.
     * 
     * @param array a list or 1D array of byte values from which a BasicShortNDArray
     *              is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Short> of(byte... array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of short
     * values.
     * 
     * @param array a list or 1D array of short values from which a
     *              BasicShortNDArray is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Short> of(short... array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of int values.
     * 
     * @param array a list or 1D array of int values from which a BasicShortNDArray
     *              is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Short> of(int... array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of long
     * values.
     * 
     * @param array a list or 1D array of long values from which a BasicShortNDArray
     *              is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Short> of(long... array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a multi-dimensional array of
     * numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a
     *              BasicShortNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<Short> of(Object[] array) {
        return new BasicShortNDArray(IndexingOperations.computeDims(array)).copyFrom(array);
    }

    @Override
    public NDArray<Short> copyFrom(NDArray<?> array) {
        if (array instanceof BasicShortNDArray)
            data = ((BasicShortNDArray) array).data.clone();
        else
            super.copyFrom(array);
        return this;
    }

    public String getNamePrefix() {
        return "basic";
    }

    public Short get(int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        return data[linearIndex];
    }

    public void set(Number real, int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        data[linearIndex] = real.shortValue();
    }

    public Short get(int... indices) {
        return get(IndexingOperations.cartesianIndicesToLinearIndex(indices, shape, multipliers));
    }

    public void set(Short value, int... indices) {
        set(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, shape, multipliers));
    }

    public static Collector<Object, List<Object>, NDArray<Short>> getCollector(int... shape) {
        return new RealNDArrayCollector<>(new BasicShortNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicShortNDArray
                ? Arrays.equals(data, ((BasicShortNDArray) other).data)
                : super.equals(other);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    protected BasicShortNDArray createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicShortNDArray(shape);
    }

}
