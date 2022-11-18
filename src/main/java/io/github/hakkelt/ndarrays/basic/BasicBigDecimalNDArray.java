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
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * Reference implementation for the NDArray of BigDecimal (arbitrary precision floating-point) values.
 */
public class BasicBigDecimalNDArray extends AbstractBigDecimalNDArray {

    protected BigDecimal[] data;

    @SuppressWarnings("unused")
    private BasicBigDecimalNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicBigDecimalNDArray(int... shape) {
        baseConstuctor(shape);
        this.data = new BigDecimal[length()];        Arrays.fill(this.data, BigDecimal.ZERO);
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicBigDecimalNDArray(NDArray<? extends Number> array) {
        baseConstuctor(array.shape());
        this.data = new BigDecimal[length()];        Arrays.fill(this.data, BigDecimal.ZERO);
        copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigDecimal
     * values.
     * 
     * @param array a list or 1D array of BigDecimal values from which a BasicBigDecimalNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigDecimal values
     */
    public static NDArray<BigDecimal> of(float... array) {
        return new BasicBigDecimalNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigDecimal
     * values.
     * 
     * @param array a list or 1D array of BigDecimal values from which a BasicBigDecimalNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigDecimal values
     */
    public static NDArray<BigDecimal> of(double... array) {
        return new BasicBigDecimalNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigDecimal
     * values.
     * 
     * @param array a list or 1D array of BigDecimal values from which a BasicBigDecimalNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigDecimal values
     */
    public static NDArray<BigDecimal> of(byte... array) {
        return new BasicBigDecimalNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigDecimal
     * values.
     * 
     * @param array a list or 1D array of BigDecimal values from which a BasicBigDecimalNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigDecimal values
     */
    public static NDArray<BigDecimal> of(short... array) {
        return new BasicBigDecimalNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigDecimal
     * values.
     * 
     * @param array a list or 1D array of BigDecimal values from which a BasicBigDecimalNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigDecimal values
     */
    public static NDArray<BigDecimal> of(int... array) {
        return new BasicBigDecimalNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a list or 1D array of BigDecimal
     * values.
     * 
     * @param array a list or 1D array of BigDecimal values from which a BasicBigDecimalNDArray
     * is created.
     * @return an NDArray created from a list or 1D array of BigDecimal values
     */
    public static NDArray<BigDecimal> of(long... array) {
        return new BasicBigDecimalNDArray(array.length).copyFrom(array);
    }

    /**
     * Load the content of the given file into a new BasicBigDecimalNDArray.
     * 
     * <p>Only the files written by function writeToFile can be loaded by this function.
     * 
     * <ul><li><b>Example:</b></li></ul>
     * 
     * <blockquote><pre>{@code 
     * NDArray<Float> array = new BasicFloatNDArray(128, 128).fill(5);
     * array.writeToFile(new File("array.nda"));
     * NDArray<BigDecimal> array2 = BasicBigDecimalNDArray.readFromFile(new File("array.nda"));
     * assertEquals(array, array2);
     * }</pre></blockquote>
     * 
     * @param file file from which the content of the NDArray is read
     *             (the extension of the file can be arbitrary, but .nda is recommended)
     * @return a new BasicBigDecimalNDArray whose shape and content is loaded from the given file
     * @throws IOException when the given file cannot be opened for read
     */
    public static BasicBigDecimalNDArray readFromFile(File file) throws IOException {
        return new FileOperations<BigDecimal,BigDecimal>().readFromFile(file, BasicBigDecimalNDArray::new);
    }

    /**
     * Factory method that creates an NDArray from a multi-dimensional array of
     * numeric values.
     * 
     * @param array a multi-dimensional array of numeric values from which a
     *              BasicBigDecimalNDArray is created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static NDArray<BigDecimal> of(Object[] array) {
        return new BasicBigDecimalNDArray(NDArrayUtils.computeDims(array)).copyFrom(array);
    }

    @Override
    public NDArray<BigDecimal> copyFrom(NDArray<?> array) {
        if (array instanceof BasicBigDecimalNDArray) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicBigDecimalNDArray) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    @Override
    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object,List<Object>,NDArray<BigDecimal>> getCollector(int... shape) {
        return new RealNDArrayCollector<>(new BasicBigDecimalNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicBigDecimalNDArray
                ? Arrays.equals(data, ((BasicBigDecimalNDArray) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected BasicBigDecimalNDArray createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicBigDecimalNDArray(shape);
    }

    @Override
    protected BigDecimal getUnchecked(int linearIndex) {
        return data[linearIndex];
    }

    @Override
    protected BigDecimal getUnchecked(int... indices) {
        return getUncheckedDefault(indices);
    }

    @Override
    protected void setUnchecked(BigDecimal value, int linearIndex) {
        data[linearIndex] = value;
    }

    @Override
    protected void setUnchecked(BigDecimal value, int... indices) {
        setUncheckedDefault(value, indices);
    }

}
