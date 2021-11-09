/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\basic\BasicByteNDArrayTemplate.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:50 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.Generated;
import io.github.hakkelt.ndarrays.internal.RealNDArrayCollector;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

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
     * Factory method that creates an NDArray from a list or 1D array of short
     * values.
     * 
     * @param array a list or 1D array of short values from which a BasicShortNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Short> of(float... array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of short
     * values.
     * 
     * @param array a list or 1D array of short values from which a BasicShortNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Short> of(double... array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of short
     * values.
     * 
     * @param array a list or 1D array of short values from which a BasicShortNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Short> of(byte... array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of short
     * values.
     * 
     * @param array a list or 1D array of short values from which a BasicShortNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Short> of(short... array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of short
     * values.
     * 
     * @param array a list or 1D array of short values from which a BasicShortNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of short values
     */
    public static NDArray<Short> of(int... array) {
        return new BasicShortNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of short
     * values.
     * 
     * @param array a list or 1D array of short values from which a BasicShortNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of short values
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
        return new BasicShortNDArray(NDArrayUtils.computeDims(array)).copyFrom(array);
    }

    @Override
    public NDArray<Short> copyFrom(NDArray<?> array) {
        if (array instanceof BasicShortNDArray) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicShortNDArray) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    @Override
    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object,List<Object>,NDArray<Short>> getCollector(int... shape) {
        return new RealNDArrayCollector<>(new BasicShortNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicShortNDArray
                ? Arrays.equals(data, ((BasicShortNDArray) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected BasicShortNDArray createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicShortNDArray(shape);
    }

    @Override
    protected Short getUnchecked(int linearIndex) {
        return data[linearIndex];
    }

    @Override
    protected Short getUnchecked(int... indices) {
        return getUncheckedDefault(indices);
    }

    @Override
    protected void setUnchecked(Short value, int linearIndex) {
        data[linearIndex] = value;
    }

    @Override
    protected void setUnchecked(Short value, int... indices) {
        setUncheckedDefault(value, indices);
    }

}
