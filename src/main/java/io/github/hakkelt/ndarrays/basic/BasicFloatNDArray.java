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
 * Reference implementation for the NDArray of float (single precision, 32 bit floating-point) values.
 */
public final class BasicFloatNDArray extends AbstractFloatNDArray {

    protected float[] data;

    @SuppressWarnings("unused")
    private BasicFloatNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicFloatNDArray(int... shape) {
        baseConstuctor(shape);
        this.data = new float[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicFloatNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.shape());
        this.data = new float[length()];
        copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of float
     * values.
     * 
     * @param array a list or 1D array of float values from which a BasicFloatNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Float> of(float... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of float
     * values.
     * 
     * @param array a list or 1D array of float values from which a BasicFloatNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Float> of(double... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of float
     * values.
     * 
     * @param array a list or 1D array of float values from which a BasicFloatNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Float> of(byte... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of float
     * values.
     * 
     * @param array a list or 1D array of float values from which a BasicFloatNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Float> of(short... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of float
     * values.
     * 
     * @param array a list or 1D array of float values from which a BasicFloatNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Float> of(int... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of float
     * values.
     * 
     * @param array a list or 1D array of float values from which a BasicFloatNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of float values
     */
    public static NDArray<Float> of(long... array) {
        return new BasicFloatNDArray(array.length).copyFrom(array);
    }

    public static BasicFloatNDArray readFromFile(File file) throws IOException {
        return new FileOperations<Float,Float>().readFromFile(file, BasicFloatNDArray::new);
    }

    /**
     * Factory method that creates an NDArray from a multi-dimensional array of
     * numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a
     *              BasicFloatNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<Float> of(Object[] array) {
        return new BasicFloatNDArray(NDArrayUtils.computeDims(array)).copyFrom(array);
    }

    @Override
    public NDArray<Float> copyFrom(NDArray<?> array) {
        if (array instanceof BasicFloatNDArray) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicFloatNDArray) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    @Override
    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object,List<Object>,NDArray<Float>> getCollector(int... shape) {
        return new RealNDArrayCollector<>(new BasicFloatNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicFloatNDArray
                ? Arrays.equals(data, ((BasicFloatNDArray) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected BasicFloatNDArray createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicFloatNDArray(shape);
    }

    @Override
    protected Float getUnchecked(int linearIndex) {
        return data[linearIndex];
    }

    @Override
    protected Float getUnchecked(int... indices) {
        return getUncheckedDefault(indices);
    }

    @Override
    protected void setUnchecked(Float value, int linearIndex) {
        data[linearIndex] = value;
    }

    @Override
    protected void setUnchecked(Float value, int... indices) {
        setUncheckedDefault(value, indices);
    }

}
