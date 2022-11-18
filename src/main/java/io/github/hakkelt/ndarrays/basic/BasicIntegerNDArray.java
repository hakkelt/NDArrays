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
 * Reference implementation for the NDArray of int (32 bit integer) values.
 */
public class BasicIntegerNDArray extends AbstractIntegerNDArray {

    protected int[] data;

    @SuppressWarnings("unused")
    private BasicIntegerNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicIntegerNDArray(int... shape) {
        baseConstuctor(shape);
        this.data = new int[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicIntegerNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.shape());
        this.data = new int[length()];
        copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of int
     * values.
     * 
     * @param array a list or 1D array of int values from which a BasicIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Integer> of(float... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of int
     * values.
     * 
     * @param array a list or 1D array of int values from which a BasicIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Integer> of(double... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of int
     * values.
     * 
     * @param array a list or 1D array of int values from which a BasicIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Integer> of(byte... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of int
     * values.
     * 
     * @param array a list or 1D array of int values from which a BasicIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Integer> of(short... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of int
     * values.
     * 
     * @param array a list or 1D array of int values from which a BasicIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Integer> of(int... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of int
     * values.
     * 
     * @param array a list or 1D array of int values from which a BasicIntegerNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of int values
     */
    public static NDArray<Integer> of(long... array) {
        return new BasicIntegerNDArray(array.length).copyFrom(array);
    }

    /**
     * Load the content of the given file into a new BasicIntegerNDArray.
     * 
     * <p>Only the files written by function writeToFile can be loaded by this function.
     * 
     * <ul><li><b>Example:</b></li></ul>
     * 
     * <blockquote><pre>{@code 
     * NDArray<Float> array = new BasicFloatNDArray(128, 128).fill(5);
     * array.writeToFile(new File("array.nda"));
     * NDArray<Integer> array2 = BasicIntegerNDArray.readFromFile(new File("array.nda"));
     * assertEquals(array, array2);
     * }</pre></blockquote>
     * 
     * @param file file from which the content of the NDArray is read
     *             (the extension of the file can be arbitrary, but .nda is recommended)
     * @return a new BasicIntegerNDArray whose shape and content is loaded from the given file
     * @throws IOException when the given file cannot be opened for read
     */
    public static BasicIntegerNDArray readFromFile(File file) throws IOException {
        return new FileOperations<Integer,Integer>().readFromFile(file, BasicIntegerNDArray::new);
    }

    /**
     * Factory method that creates an NDArray from a multi-dimensional array of
     * numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a
     *              BasicIntegerNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<Integer> of(Object[] array) {
        return new BasicIntegerNDArray(NDArrayUtils.computeDims(array)).copyFrom(array);
    }

    @Override
    public NDArray<Integer> copyFrom(NDArray<?> array) {
        if (array instanceof BasicIntegerNDArray) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicIntegerNDArray) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    @Override
    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object,List<Object>,NDArray<Integer>> getCollector(int... shape) {
        return new RealNDArrayCollector<>(new BasicIntegerNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicIntegerNDArray
                ? Arrays.equals(data, ((BasicIntegerNDArray) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected BasicIntegerNDArray createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicIntegerNDArray(shape);
    }

    @Override
    protected Integer getUnchecked(int linearIndex) {
        return data[linearIndex];
    }

    @Override
    protected Integer getUnchecked(int... indices) {
        return getUncheckedDefault(indices);
    }

    @Override
    protected void setUnchecked(Integer value, int linearIndex) {
        data[linearIndex] = value;
    }

    @Override
    protected void setUnchecked(Integer value, int... indices) {
        setUncheckedDefault(value, indices);
    }

}
