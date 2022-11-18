/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\basic\BasicByteNDArray.java
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
 * Reference implementation for the NDArray of byte (8 bit integer) values.
 */
public class BasicByteNDArray extends AbstractByteNDArray {

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
     * Load the content of the given file into a new BasicByteNDArray.
     * 
     * <p>Only the files written by function writeToFile can be loaded by this function.
     * 
     * <ul><li><b>Example:</b></li></ul>
     * 
     * <blockquote><pre>{@code 
     * NDArray<Float> array = new BasicFloatNDArray(128, 128).fill(5);
     * array.writeToFile(new File("array.nda"));
     * NDArray<Byte> array2 = BasicByteNDArray.readFromFile(new File("array.nda"));
     * assertEquals(array, array2);
     * }</pre></blockquote>
     * 
     * @param file file from which the content of the NDArray is read
     *             (the extension of the file can be arbitrary, but .nda is recommended)
     * @return a new BasicByteNDArray whose shape and content is loaded from the given file
     * @throws IOException when the given file cannot be opened for read
     */
    public static BasicByteNDArray readFromFile(File file) throws IOException {
        return new FileOperations<Byte,Byte>().readFromFile(file, BasicByteNDArray::new);
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
