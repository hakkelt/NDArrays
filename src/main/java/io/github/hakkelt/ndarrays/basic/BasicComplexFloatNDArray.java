/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\basic\BasicComplexFloatNDArray.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.ComplexNDArrayCollector;
import io.github.hakkelt.ndarrays.internal.CopyFromOperations;
import io.github.hakkelt.ndarrays.internal.FileOperations;
import io.github.hakkelt.ndarrays.internal.Generated;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

/**
 * Reference implementation for the NDArray of complex float (single-precision,
 * 32 bit floating point) values.
 */
public final class BasicComplexFloatNDArray extends AbstractComplexNDArray<Float> {

    protected float[] data;

    @SuppressWarnings("unused")
    private BasicComplexFloatNDArray() {
    }

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicComplexFloatNDArray(int... shape) {
        baseConstuctor(shape);
        this.data = new float[length() * 2];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicComplexFloatNDArray(NDArray<?> array) {
        baseConstuctor(array.shape());
        this.data = new float[length() * 2];
        copyFrom(array);
    }

    /**
     * Copy constructor.
     * 
     * @param real NDArray from which real part of entries are copied from.
     * @param imag NDArray from which imaginary part of entries are copied from.
     */
    public BasicComplexFloatNDArray(NDArray<? extends Number> real, NDArray<? extends Number> imag) {
        baseConstuctor(real.shape());
        this.data = new float[length() * 2];
        copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of byte
     * values.
     * 
     * @param array a list or 1D array of byte values from which a
     *              BasicComplexFloatNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of byte values
     */
    public static ComplexNDArray<Float> of(byte... array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of short
     * values.
     * 
     * @param array a list or 1D array of short values from which a
     *              BasicComplexFloatNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of short values
     */
    public static ComplexNDArray<Float> of(short... array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of int
     * values.
     * 
     * @param array a list or 1D array of int values from which a
     *              BasicComplexFloatNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of int values
     */
    public static ComplexNDArray<Float> of(int... array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of long
     * values.
     * 
     * @param array a list or 1D array of long values from which a
     *              BasicComplexFloatNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of long values
     */
    public static ComplexNDArray<Float> of(long... array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of float
     * values.
     * 
     * @param array a list or 1D array of float values from which a
     *              BasicComplexFloatNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of float values
     */
    public static ComplexNDArray<Float> of(float... array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of double
     * values.
     * 
     * @param array a list or 1D array of double values from which a
     *              BasicComplexFloatNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of double values
     */
    public static ComplexNDArray<Float> of(double... array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a multi-dimensional array of
     * numeric values (including Complex type).
     * 
     * @param realOrComplex a multi-dimensional array of numeric values (including
     *                      Complex type) from which a BasicComplexFloatNDArray is
     *                      created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static ComplexNDArray<Float> of(Object[] realOrComplex) {
        return new BasicComplexFloatNDArray(NDArrayUtils.computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of byte
     * values.
     * 
     * @param real a 1D array of byte values from which the real part of the created
     *             BasicComplexFloatNDArray is read.
     * @param imag a 1D array of byte values from which the imaginary part of the
     *             created BasicComplexFloatNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of byte values
     */
    public static ComplexNDArray<Float> of(byte[] real, byte[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of short
     * values.
     * 
     * @param real a 1D array of short values from which the real part of the created
     *             BasicComplexFloatNDArray is read.
     * @param imag a 1D array of short values from which the imaginary part of the
     *             created BasicComplexFloatNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of short values
     */
    public static ComplexNDArray<Float> of(short[] real, short[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of int
     * values.
     * 
     * @param real a 1D array of int values from which the real part of the created
     *             BasicComplexFloatNDArray is read.
     * @param imag a 1D array of int values from which the imaginary part of the
     *             created BasicComplexFloatNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of int values
     */
    public static ComplexNDArray<Float> of(int[] real, int[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of long
     * values.
     * 
     * @param real a 1D array of long values from which the real part of the created
     *             BasicComplexFloatNDArray is read.
     * @param imag a 1D array of long values from which the imaginary part of the
     *             created BasicComplexFloatNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of long values
     */
    public static ComplexNDArray<Float> of(long[] real, long[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of float
     * values.
     * 
     * @param real a 1D array of float values from which the real part of the created
     *             BasicComplexFloatNDArray is read.
     * @param imag a 1D array of float values from which the imaginary part of the
     *             created BasicComplexFloatNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of float values
     */
    public static ComplexNDArray<Float> of(float[] real, float[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of double
     * values.
     * 
     * @param real a 1D array of double values from which the real part of the created
     *             BasicComplexFloatNDArray is read.
     * @param imag a 1D array of double values from which the imaginary part of the
     *             created BasicComplexFloatNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of double values
     */
    public static ComplexNDArray<Float> of(double[] real, double[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two multi-dimensional
     * arrays of numeric values.
     * 
     * @param real a multi-dimensional array of numeric values from which the real
     *             part of the created BasicComplexFloatNDArray is read.
     * @param imag a multi-dimensional array of numeric values from which the
     *             imaginary part of the created BasicComplexFloatNDArray is read.
     * @return a ComplexNDArray created from the two multi-dimensional arrays of
     *         numeric values
     */
    public static ComplexNDArray<Float> of(Object[] real, Object[] imag) {
        return new BasicComplexFloatNDArray(NDArrayUtils.computeDims(real)).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two multi-dimensional
     * arrays of numeric values.
     * 
     * @param magnitude a multi-dimensional array of numeric values that stores the
     *                  magnitude of the complex array to be created.
     * @param phase     a multi-dimensional array of numeric values that stores the
     *                  phase of the complex array to be created.
     * @return a ComplexNDArray created from the two multi-dimensional arrays of
     *         numeric values
     */
    public static ComplexNDArray<Float> ofMagnitudePhase(Object[] magnitude, Object[] phase) {
        return new CopyFromOperations<Complex, Float>()
            .copyFromMagnitudePhase(
                new BasicComplexFloatNDArray(NDArrayUtils.computeDims(magnitude)),
                BasicFloatNDArray.of(magnitude),
                BasicFloatNDArray.of(phase));
    }

    /**
     * Factory method that creates a ComplexNDArray from two multi-dimensional
     * arrays of numeric values.
     * 
     * @param magnitude a real-valued NDArray that stores the
     *                  magnitude of the complex array to be created.
     * @param phase     a real-valued NDArray that stores the
     *                  phase of the complex array to be created.
     * @return a ComplexNDArray created from the two multi-dimensional arrays of
     *         numeric values
     */
    public static ComplexNDArray<Float> ofMagnitudePhase(NDArray<? extends Number> magnitude, NDArray<? extends Number> phase) {
        return new CopyFromOperations<Complex, Float>()
            .copyFromMagnitudePhase(new BasicComplexFloatNDArray(magnitude.shape()), magnitude, phase);
    }

    /**
     * Load the content of the given file into a new BasicByteNDArray.
     * 
     * <p>Only the files written by function writeToFile can be loaded by this function.
     * 
     * <ul><li><b>Example:</b></li></ul>
     * 
     * <blockquote><pre>{@code 
     * ComplexNDArray<Float> array = new BasicComplexFloatNDArray(128, 128).fill(5);
     * array.writeToFile(new File("array.nda"));
     * ComplexNDArray<Double> array2 = BasicComplexDoubleNDArray.readFromFile(new File("array.nda"));
     * assertEquals(array, array2);
     * }</pre></blockquote>
     * 
     * @param file file from which the content of the NDArray is read
     *             (the extension of the file can be arbitrary, but .nda is recommended)
     * @return a new BasicByteNDArray whose shape and content is loaded from the given file
     * @throws IOException when the given file cannot be opened for read
     */
    public static BasicComplexFloatNDArray readFromFile(File file) throws IOException {
        return new FileOperations<Complex,Float>().readFromFile(file, BasicComplexFloatNDArray::new);
    }

    @Override
    public ComplexNDArray<Float> copyFrom(NDArray<?> array) {
        if (array instanceof BasicComplexFloatNDArray) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicComplexFloatNDArray) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object,List<Object>,NDArray<Complex>> getCollector(int... shape) {
        return new ComplexNDArrayCollector<>(new BasicComplexFloatNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicComplexFloatNDArray
                ? Arrays.equals(data, ((BasicComplexFloatNDArray) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    protected ComplexNDArray<Float> createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicComplexFloatNDArray(shape);
    }

    protected NDArray<Float> createNewRealNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicFloatNDArray(shape);
    }

    @Override
    protected Complex getUnchecked(int linearIndex) {
        return new Complex(getRealUnchecked(linearIndex), getImagUnchecked(linearIndex));
    }

    @Override
    protected Complex getUnchecked(int... indices) {
        return getUncheckedDefault(indices);
    }

    @Override
    protected Float getRealUnchecked(int linearIndex) {
        return data[linearIndex * 2];
    }

    @Override
    protected Float getRealUnchecked(int... indices) {
        return getRealUncheckedDefault(indices);
    }

    @Override
    protected Float getImagUnchecked(int linearIndex) {
        return data[linearIndex * 2 + 1];
    }

    @Override
    protected Float getImagUnchecked(int... indices) {
        return getImagUncheckedDefault(indices);
    }

    @Override
    protected void setUnchecked(Complex value, int linearIndex) {
        setRealUnchecked(wrapRealValue(value.getReal()), linearIndex);
        setImagUnchecked(wrapRealValue(value.getImaginary()), linearIndex);
    }

    @Override
    protected void setUnchecked(Complex value, int... indices) {
        setUncheckedDefault(value, indices);
    }

    @Override
    protected void setRealUnchecked(Float value, int linearIndex) {
        data[linearIndex * 2] = value;
    }

    @Override
    protected void setRealUnchecked(Float value, int... indices) {
        setRealUncheckedDefault(value, indices);
    }

    @Override
    protected void setImagUnchecked(Float value, int linearIndex) {
        data[linearIndex * 2 + 1] = value;
    }

    @Override
    protected void setImagUnchecked(Float value, int... indices) {
        setImagUncheckedDefault(value, indices);
    }

}
