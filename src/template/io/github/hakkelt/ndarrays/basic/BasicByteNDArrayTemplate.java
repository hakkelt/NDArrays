package io.github.hakkelt.ndarrays.basic;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.FileOperations;
import io.github.hakkelt.ndarrays.internal.Generated;
import io.github.hakkelt.ndarrays.internal.RealNDArrayCollector;

/**
 * Reference implementation for the NDArray of byte (8 bit integer) values.
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays/basic", newName = "Basic$3NDArray")
@Patterns({ "new byte[length()];", "/byte/", "/Byte/", "8 bit integer" })
@Replacements({ "new short[length()];", "short", "Short", "16 bit integer" })
@Replacements({ "new int[length()];", "int", "Integer", "32 bit integer" })
@Replacements({ "new long[length()];", "long", "Long", "64 bit integer" })
@Replacements({ "new float[length()];", "float", "Float", "single precision, 32 bit floating-point" })
@Replacements({ "new double[length()];", "double", "Double", "double precision, 64 bit floating-point" })
@Replacements(value = { "new BigInteger[length()];        Arrays.fill(this.data, BigInteger.ZERO);", "BigInteger",
    "BigInteger", "arbitrary precision integer" }, extraImports = "java.math.BigInteger")
@Replacements(value = { "new BigDecimal[length()];        Arrays.fill(this.data, BigDecimal.ZERO);", "BigDecimal",
    "BigDecimal", "arbitrary precision floating-point" }, extraImports = "java.math.BigDecimal")
public class BasicByteNDArrayTemplate extends AbstractByteNDArray {
    protected byte[] data;

    @SuppressWarnings("unused")
    private BasicByteNDArrayTemplate() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicByteNDArrayTemplate(int... shape) {
        baseConstuctor(shape);
        this.data = new byte[length()];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicByteNDArrayTemplate(NDArray<? extends Number> array) {
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
    @Replace(pattern = "float", replacements = { "double", "byte", "short", "int", "long" })
    public static NDArray<Byte> of(float... array) {
        return new BasicByteNDArrayTemplate(array.length).copyFrom(array);
    }

    /**
     * Load the content of the given file into a new BasicByteNDArray.
     * 
     * <p>Only the files written by function writeToFile can be loaded by this function.
     * 
     * <ul><li><b>Example:</b></li></ul>
     * 
     * <blockquote><pre>{@code 
NDArray<Float> array = new BasicFloatNDArray(128, 128).fill(5);
array.writeToFile(new File("array.nda"));
NDArray<Byte> array2 = BasicByteNDArray.readFromFile(new File("array.nda"));
assertEquals(array, array2);
     * }</pre></blockquote>
     * 
     * @param file file from which the content of the NDArray is read
     *             (the extension of the file can be arbitrary, but .nda is recommended)
     * @return     a new BasicByteNDArray whose shape and content is loaded from the given file
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
        return new BasicByteNDArrayTemplate(NDArrayUtils.computeDims(array)).copyFrom(array);
    }

    @Override
    public NDArray<Byte> copyFrom(NDArray<?> array) {
        if (array instanceof BasicByteNDArrayTemplate) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicByteNDArrayTemplate) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    @Override
    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object, List<Object>, NDArray<Byte>> getCollector(int... shape) {
        return new RealNDArrayCollector<>(new BasicByteNDArrayTemplate(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicByteNDArrayTemplate
                ? Arrays.equals(data, ((BasicByteNDArrayTemplate) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected BasicByteNDArrayTemplate createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicByteNDArrayTemplate(shape);
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
