/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\basic\BasicByteNDArray.java
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
     * Factory method that creates an NDArray from a list or 1D array of byte
     * values.
     * 
     * @param array a list or 1D array of byte values from which a BasicByteNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Byte> of(float... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of byte
     * values.
     * 
     * @param array a list or 1D array of byte values from which a BasicByteNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Byte> of(double... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of byte
     * values.
     * 
     * @param array a list or 1D array of byte values from which a BasicByteNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Byte> of(byte... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of byte
     * values.
     * 
     * @param array a list or 1D array of byte values from which a BasicByteNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Byte> of(short... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of byte
     * values.
     * 
     * @param array a list or 1D array of byte values from which a BasicByteNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of byte values
     */
    public static NDArray<Byte> of(int... array) {
        return new BasicByteNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of byte
     * values.
     * 
     * @param array a list or 1D array of byte values from which a BasicByteNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of byte values
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
        return new BasicByteNDArray(NDArrayUtils.computeDims(array)).copyFrom(array);
    }

    @Override
    public NDArray<Byte> copyFrom(NDArray<?> array) {
        if (array instanceof BasicByteNDArray) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicByteNDArray) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    @Override
    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object,List<Object>,NDArray<Byte>> getCollector(int... shape) {
        return new RealNDArrayCollector<>(new BasicByteNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicByteNDArray
                ? Arrays.equals(data, ((BasicByteNDArray) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected BasicByteNDArray createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicByteNDArray(shape);
    }

    @Override
    protected Byte getUnchecked(int linearIndex) {
        return data[linearIndex];
    }

    @Override
    protected Byte getUnchecked(int... indices) {
        return getUncheckedDefault(indices);
    }

    @Override
    protected void setUnchecked(Byte value, int linearIndex) {
        data[linearIndex] = value;
    }

    @Override
    protected void setUnchecked(Byte value, int... indices) {
        setUncheckedDefault(value, indices);
    }

}
