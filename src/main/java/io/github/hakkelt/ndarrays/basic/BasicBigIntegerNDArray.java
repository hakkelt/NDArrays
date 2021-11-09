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

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * Reference implementation for the NDArray of BigInteger (arbitrary precision integer) values.
 */
public final class BasicBigIntegerNDArray extends AbstractBigIntegerNDArray {

    protected BigInteger[] data;

    @SuppressWarnings("unused")
    private BasicBigIntegerNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicBigIntegerNDArray(int... shape) {
        baseConstuctor(shape);
        this.data = new BigInteger[length()];        Arrays.fill(this.data, BigInteger.ZERO);
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicBigIntegerNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.shape());
        this.data = new BigInteger[length()];        Arrays.fill(this.data, BigInteger.ZERO);
        copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigInteger
     * values.
     * 
     * @param array a list or 1D array of BigInteger values from which a BasicBigIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigInteger values
     */
    public static NDArray<BigInteger> of(float... array) {
        return new BasicBigIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigInteger
     * values.
     * 
     * @param array a list or 1D array of BigInteger values from which a BasicBigIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigInteger values
     */
    public static NDArray<BigInteger> of(double... array) {
        return new BasicBigIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigInteger
     * values.
     * 
     * @param array a list or 1D array of BigInteger values from which a BasicBigIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigInteger values
     */
    public static NDArray<BigInteger> of(byte... array) {
        return new BasicBigIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigInteger
     * values.
     * 
     * @param array a list or 1D array of BigInteger values from which a BasicBigIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigInteger values
     */
    public static NDArray<BigInteger> of(short... array) {
        return new BasicBigIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigInteger
     * values.
     * 
     * @param array a list or 1D array of BigInteger values from which a BasicBigIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigInteger values
     */
    public static NDArray<BigInteger> of(int... array) {
        return new BasicBigIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigInteger
     * values.
     * 
     * @param array a list or 1D array of BigInteger values from which a BasicBigIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigInteger values
     */
    public static NDArray<BigInteger> of(long... array) {
        return new BasicBigIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a multi-dimensional array of
     * numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a
     *              BasicBigIntegerNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<BigInteger> of(Object[] array) {
        return new BasicBigIntegerNDArray(NDArrayUtils.computeDims(array)).copyFrom(array);
    }

    @Override
    public NDArray<BigInteger> copyFrom(NDArray<?> array) {
        if (array instanceof BasicBigIntegerNDArray) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicBigIntegerNDArray) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    @Override
    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object,List<Object>,NDArray<BigInteger>> getCollector(int... shape) {
        return new RealNDArrayCollector<>(new BasicBigIntegerNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicBigIntegerNDArray
                ? Arrays.equals(data, ((BasicBigIntegerNDArray) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected BasicBigIntegerNDArray createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicBigIntegerNDArray(shape);
    }

    @Override
    protected BigInteger getUnchecked(int linearIndex) {
        return data[linearIndex];
    }

    @Override
    protected BigInteger getUnchecked(int... indices) {
        return getUncheckedDefault(indices);
    }

    @Override
    protected void setUnchecked(BigInteger value, int linearIndex) {
        data[linearIndex] = value;
    }

    @Override
    protected void setUnchecked(BigInteger value, int... indices) {
        setUncheckedDefault(value, indices);
    }

}
