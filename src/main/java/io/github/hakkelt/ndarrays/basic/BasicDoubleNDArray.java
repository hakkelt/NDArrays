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
 * Reference implementation for the NDArray of double (double precision, 64 bit floating-point) values.
 */
public class BasicDoubleNDArray extends AbstractDoubleNDArray {

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
     * Load the content of the given file into a new BasicDoubleNDArray.
     * 
     * <p>Only the files written by function writeToFile can be loaded by this function.
     * 
     * <ul><li><b>Example:</b></li></ul>
     * 
     * <blockquote><pre>{@code 
     * NDArray<Float> array = new BasicFloatNDArray(128, 128).fill(5);
     * array.writeToFile(new File("array.nda"));
     * NDArray<Double> array2 = BasicDoubleNDArray.readFromFile(new File("array.nda"));
     * assertEquals(array, array2);
     * }</pre></blockquote>
     * 
     * @param file file from which the content of the NDArray is read
     *             (the extension of the file can be arbitrary, but .nda is recommended)
     * @return a new BasicDoubleNDArray whose shape and content is loaded from the given file
     * @throws IOException when the given file cannot be opened for read
     */
    public static BasicDoubleNDArray readFromFile(File file) throws IOException {
        return new FileOperations<Double,Double>().readFromFile(file, BasicDoubleNDArray::new);
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
