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
 * Reference implementation for the NDArray of short (16 bit integer) values.
 */
public class BasicShortNDArray extends AbstractShortNDArray {

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
     * Load the content of the given file into a new BasicShortNDArray.
     * 
     * <p>Only the files written by function writeToFile can be loaded by this function.
     * 
     * <ul><li><b>Example:</b></li></ul>
     * 
     * <blockquote><pre>{@code 
     * NDArray<Float> array = new BasicFloatNDArray(128, 128).fill(5);
     * array.writeToFile(new File("array.nda"));
     * NDArray<Short> array2 = BasicShortNDArray.readFromFile(new File("array.nda"));
     * assertEquals(array, array2);
     * }</pre></blockquote>
     * 
     * @param file file from which the content of the NDArray is read
     *             (the extension of the file can be arbitrary, but .nda is recommended)
     * @return a new BasicShortNDArray whose shape and content is loaded from the given file
     * @throws IOException when the given file cannot be opened for read
     */
    public static BasicShortNDArray readFromFile(File file) throws IOException {
        return new FileOperations<Short,Short>().readFromFile(file, BasicShortNDArray::new);
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
