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
 * Reference implementation for the NDArray of double (double precision, 64 bit floating-point) values.
 */
public final class BasicDoubleNDArray extends AbstractDoubleNDArray {

    protected double[] data;

    @SuppressWarnings("unused")
    private BasicDoubleNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicDoubleNDArray(int... shape) {
        baseConstuctor(shape);
        this.data = new double[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicDoubleNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.shape());
        this.data = new double[length()];
        copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of double
     * values.
     * 
     * @param array a list or 1D array of double values from which a BasicDoubleNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Double> of(float... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of double
     * values.
     * 
     * @param array a list or 1D array of double values from which a BasicDoubleNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Double> of(double... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of double
     * values.
     * 
     * @param array a list or 1D array of double values from which a BasicDoubleNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Double> of(byte... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of double
     * values.
     * 
     * @param array a list or 1D array of double values from which a BasicDoubleNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Double> of(short... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of double
     * values.
     * 
     * @param array a list or 1D array of double values from which a BasicDoubleNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Double> of(int... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of double
     * values.
     * 
     * @param array a list or 1D array of double values from which a BasicDoubleNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of double values
     */
    public static NDArray<Double> of(long... array) {
        return new BasicDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a multi-dimensional array of
     * numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a
     *              BasicDoubleNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<Double> of(Object[] array) {
        return new BasicDoubleNDArray(NDArrayUtils.computeDims(array)).copyFrom(array);
    }

    @Override
    public NDArray<Double> copyFrom(NDArray<?> array) {
        if (array instanceof BasicDoubleNDArray) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicDoubleNDArray) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    @Override
    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object,List<Object>,NDArray<Double>> getCollector(int... shape) {
        return new RealNDArrayCollector<>(new BasicDoubleNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicDoubleNDArray
                ? Arrays.equals(data, ((BasicDoubleNDArray) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected BasicDoubleNDArray createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicDoubleNDArray(shape);
    }

    @Override
    protected Double getUnchecked(int linearIndex) {
        return data[linearIndex];
    }

    @Override
    protected Double getUnchecked(int... indices) {
        return getUncheckedDefault(indices);
    }

    @Override
    protected void setUnchecked(Double value, int linearIndex) {
        data[linearIndex] = value;
    }

    @Override
    protected void setUnchecked(Double value, int... indices) {
        setUncheckedDefault(value, indices);
    }

}
