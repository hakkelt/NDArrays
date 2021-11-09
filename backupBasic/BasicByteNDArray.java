package io.github.hakkelt.ndarrays.basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.AbstractByteNDArray;
import io.github.hakkelt.ndarrays.IndexingOperations;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayCollector;

/**
 * Reference implementation for the NDArray of byte (8 bit integer) values.
 */
public final class BasicByteNDArray extends AbstractByteNDArray {
    protected byte[] data;

    @SuppressWarnings("unused")
    private BasicByteNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicByteNDArray(int... shape) {
        baseConstuctor(shape);
        this.data = new byte[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicByteNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.shape());
        this.data = new byte[length()];
        copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of float
     * values.
     * 
     * @param array a list or 1D array of float values from which a BasicByteNDArray
     *              is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Byte> of(float... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of double
     * values.
     * 
     * @param array a list or 1D array of double values from which a
     *              BasicByteNDArray is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Byte> of(double... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of byte
     * values.
     * 
     * @param array a list or 1D array of byte values from which a BasicByteNDArray
     *              is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Byte> of(byte... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of short
     * values.
     * 
     * @param array a list or 1D array of short values from which a BasicByteNDArray
     *              is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Byte> of(short... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of int values.
     * 
     * @param array a list or 1D array of int values from which a BasicByteNDArray
     *              is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Byte> of(int... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of long
     * values.
     * 
     * @param array a list or 1D array of long values from which a BasicByteNDArray
     *              is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Byte> of(long... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a multi-dimensional array of
     * numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a
     *              BasicByteNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<Byte> of(Object[] array) {
        return new BasicByteNDArray(IndexingOperations.computeDims(array)).copyFrom(array);
    }

    @Override
    public NDArray<Byte> copyFrom(NDArray<?> array) {
        if (array instanceof BasicByteNDArray)
            data = ((BasicByteNDArray) array).data.clone();
        else
            super.copyFrom(array);
        return this;
    }

    @Override
    public String getNamePrefix() {
        return "basic";
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
        return get(IndexingOperations.cartesianIndicesToLinearIndex(indices, shape, multipliers));
    }

    public void set(Byte value, int... indices) {
        set(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, shape, multipliers));
    }

    public static Collector<Object, List<Object>, NDArray<Byte>> getCollector(int... shape) {
        return new RealNDArrayCollector<>(new BasicByteNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicByteNDArray
                ? Arrays.equals(data, ((BasicByteNDArray) other).data)
                : super.equals(other);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    protected BasicByteNDArray createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicByteNDArray(shape);
    }

}
