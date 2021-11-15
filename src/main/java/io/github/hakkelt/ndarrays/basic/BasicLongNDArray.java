/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\basic\BasicByteNDArrayTemplate.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.FileOperations;
import io.github.hakkelt.ndarrays.internal.Generated;
import io.github.hakkelt.ndarrays.internal.RealNDArrayCollector;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * Reference implementation for the NDArray of long (64 bit integer) values.
 */
public final class BasicLongNDArray extends AbstractLongNDArray {

    protected long[] data;

    @SuppressWarnings("unused")
    private BasicLongNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicLongNDArray(int... shape) {
        baseConstuctor(shape);
        this.data = new long[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicLongNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.shape());
        this.data = new long[length()];
        copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of long
     * values.
     * 
     * @param array a list or 1D array of long values from which a BasicLongNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Long> of(float... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of long
     * values.
     * 
     * @param array a list or 1D array of long values from which a BasicLongNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Long> of(double... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of long
     * values.
     * 
     * @param array a list or 1D array of long values from which a BasicLongNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Long> of(byte... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of long
     * values.
     * 
     * @param array a list or 1D array of long values from which a BasicLongNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Long> of(short... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of long
     * values.
     * 
     * @param array a list or 1D array of long values from which a BasicLongNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Long> of(int... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of long
     * values.
     * 
     * @param array a list or 1D array of long values from which a BasicLongNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of long values
     */
    public static NDArray<Long> of(long... array) {
        return new BasicLongNDArray(array.length).copyFrom(array);
    }

    public static BasicLongNDArray readFromFile(File file) throws IOException {
        return new FileOperations<Long,Long>().readFromFile(file, BasicLongNDArray::new);
    }

    /**
     * Factory method that creates an NDArray from a multi-dimensional array of
     * numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a
     *              BasicLongNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<Long> of(Object[] array) {
        return new BasicLongNDArray(NDArrayUtils.computeDims(array)).copyFrom(array);
    }

    @Override
    public NDArray<Long> copyFrom(NDArray<?> array) {
        if (array instanceof BasicLongNDArray) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicLongNDArray) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    @Override
    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object,List<Object>,NDArray<Long>> getCollector(int... shape) {
        return new RealNDArrayCollector<>(new BasicLongNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicLongNDArray
                ? Arrays.equals(data, ((BasicLongNDArray) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected BasicLongNDArray createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicLongNDArray(shape);
    }

    @Override
    protected Long getUnchecked(int linearIndex) {
        return data[linearIndex];
    }

    @Override
    protected Long getUnchecked(int... indices) {
        return getUncheckedDefault(indices);
    }

    @Override
    protected void setUnchecked(Long value, int linearIndex) {
        data[linearIndex] = value;
    }

    @Override
    protected void setUnchecked(Long value, int... indices) {
        setUncheckedDefault(value, indices);
    }

}
