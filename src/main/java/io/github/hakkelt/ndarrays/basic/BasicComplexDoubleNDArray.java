/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\basic\BasicComplexFloatNDArrayTemplate.java
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
 * Reference implementation for the NDArray of complex double (single-precision,
 * 32 bit doubleing point) values.
 */
public class BasicComplexDoubleNDArray extends AbstractComplexNDArray<Double> {

    protected double[] data;

    @SuppressWarnings("unused")
    private BasicComplexDoubleNDArray() {
    }

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicComplexDoubleNDArray(int... shape) {
        baseConstuctor(shape);
        this.data = new double[length() * 2];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicComplexDoubleNDArray(NDArray<?> array) {
        baseConstuctor(array.shape());
        this.data = new double[length() * 2];
        copyFrom(array);
    }

    /**
     * Copy constructor.
     * 
     * @param real NDArray from which real part of entries are copied from.
     * @param imag NDArray from which imaginary part of entries are copied from.
     */
    public BasicComplexDoubleNDArray(NDArray<? extends Number> real, NDArray<? extends Number> imag) {
        baseConstuctor(real.shape());
        this.data = new double[length() * 2];
        copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of byte
     * values.
     * 
     * @param array a list or 1D array of byte values from which a
     *              BasicComplexDoubleNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of byte values
     */
    public static ComplexNDArray<Double> of(byte... array) {
        return new BasicComplexDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of short
     * values.
     * 
     * @param array a list or 1D array of short values from which a
     *              BasicComplexDoubleNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of short values
     */
    public static ComplexNDArray<Double> of(short... array) {
        return new BasicComplexDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of int
     * values.
     * 
     * @param array a list or 1D array of int values from which a
     *              BasicComplexDoubleNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of int values
     */
    public static ComplexNDArray<Double> of(int... array) {
        return new BasicComplexDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of long
     * values.
     * 
     * @param array a list or 1D array of long values from which a
     *              BasicComplexDoubleNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of long values
     */
    public static ComplexNDArray<Double> of(long... array) {
        return new BasicComplexDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of float
     * values.
     * 
     * @param array a list or 1D array of float values from which a
     *              BasicComplexDoubleNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of float values
     */
    public static ComplexNDArray<Double> of(float... array) {
        return new BasicComplexDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates a ComplexNDArray from a list or 1D array of double
     * values.
     * 
     * @param array a list or 1D array of double values from which a
     *              BasicComplexDoubleNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of double values
     */
    public static ComplexNDArray<Double> of(double... array) {
        return new BasicComplexDoubleNDArray(array.length).copyFrom(array);
    }

    /**
     * Factory method that creates an NDArray from a multi-dimensional array of
     * numeric values (including Complex type).
     * 
     * @param realOrComplex a multi-dimensional array of numeric values (including
     *                      Complex type) from which a BasicComplexDoubleNDArray is
     *                      created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static ComplexNDArray<Double> of(Object[] realOrComplex) {
        return new BasicComplexDoubleNDArray(NDArrayUtils.computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of byte
     * values.
     * 
     * @param real a 1D array of byte values from which the real part of the created
     *             BasicComplexDoubleNDArray is read.
     * @param imag a 1D array of byte values from which the imaginary part of the
     *             created BasicComplexDoubleNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of byte values
     */
    public static ComplexNDArray<Double> of(byte[] real, byte[] imag) {
        return new BasicComplexDoubleNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of short
     * values.
     * 
     * @param real a 1D array of short values from which the real part of the created
     *             BasicComplexDoubleNDArray is read.
     * @param imag a 1D array of short values from which the imaginary part of the
     *             created BasicComplexDoubleNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of short values
     */
    public static ComplexNDArray<Double> of(short[] real, short[] imag) {
        return new BasicComplexDoubleNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of int
     * values.
     * 
     * @param real a 1D array of int values from which the real part of the created
     *             BasicComplexDoubleNDArray is read.
     * @param imag a 1D array of int values from which the imaginary part of the
     *             created BasicComplexDoubleNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of int values
     */
    public static ComplexNDArray<Double> of(int[] real, int[] imag) {
        return new BasicComplexDoubleNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of long
     * values.
     * 
     * @param real a 1D array of long values from which the real part of the created
     *             BasicComplexDoubleNDArray is read.
     * @param imag a 1D array of long values from which the imaginary part of the
     *             created BasicComplexDoubleNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of long values
     */
    public static ComplexNDArray<Double> of(long[] real, long[] imag) {
        return new BasicComplexDoubleNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of float
     * values.
     * 
     * @param real a 1D array of float values from which the real part of the created
     *             BasicComplexDoubleNDArray is read.
     * @param imag a 1D array of float values from which the imaginary part of the
     *             created BasicComplexDoubleNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of float values
     */
    public static ComplexNDArray<Double> of(float[] real, float[] imag) {
        return new BasicComplexDoubleNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of double
     * values.
     * 
     * @param real a 1D array of double values from which the real part of the created
     *             BasicComplexDoubleNDArray is read.
     * @param imag a 1D array of double values from which the imaginary part of the
     *             created BasicComplexDoubleNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of double values
     */
    public static ComplexNDArray<Double> of(double[] real, double[] imag) {
        return new BasicComplexDoubleNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two multi-dimensional
     * arrays of numeric values.
     * 
     * @param real a multi-dimensional array of numeric values from which the real
     *             part of the created BasicComplexDoubleNDArray is read.
     * @param imag a multi-dimensional array of numeric values from which the
     *             imaginary part of the created BasicComplexDoubleNDArray is read.
     * @return a ComplexNDArray created from the two multi-dimensional arrays of
     *         numeric values
     */
    public static ComplexNDArray<Double> of(Object[] real, Object[] imag) {
        return new BasicComplexDoubleNDArray(NDArrayUtils.computeDims(real)).copyFrom(real, imag);
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
    public static ComplexNDArray<Double> ofMagnitudePhase(Object[] magnitude, Object[] phase) {
        return new CopyFromOperations<Complex, Double>()
            .copyFromMagnitudePhase(
                new BasicComplexDoubleNDArray(NDArrayUtils.computeDims(magnitude)),
                BasicDoubleNDArray.of(magnitude),
                BasicDoubleNDArray.of(phase));
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
    public static ComplexNDArray<Double> ofMagnitudePhase(NDArray<? extends Number> magnitude, NDArray<? extends Number> phase) {
        return new CopyFromOperations<Complex, Double>()
            .copyFromMagnitudePhase(new BasicComplexDoubleNDArray(magnitude.shape()), magnitude, phase);
    }

    /**
     * Load the content of the given file into a new BasicByteNDArray.
     * 
     * <p>Only the files written by function writeToFile can be loaded by this function.
     * 
     * <ul><li><b>Example:</b></li></ul>
     * 
     * <blockquote><pre>{@code 
     * ComplexNDArray<Double> array = new BasicComplexDoubleNDArray(128, 128).fill(5);
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
    public static BasicComplexDoubleNDArray readFromFile(File file) throws IOException {
        return new FileOperations<Complex,Double>().readFromFile(file, BasicComplexDoubleNDArray::new);
    }

    @Override
    public ComplexNDArray<Double> copyFrom(NDArray<?> array) {
        if (array instanceof BasicComplexDoubleNDArray) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicComplexDoubleNDArray) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object,List<Object>,NDArray<Complex>> getCollector(int... shape) {
        return new ComplexNDArrayCollector<>(new BasicComplexDoubleNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicComplexDoubleNDArray
                ? Arrays.equals(data, ((BasicComplexDoubleNDArray) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    protected ComplexNDArray<Double> createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicComplexDoubleNDArray(shape);
    }

    protected NDArray<Double> createNewRealNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicDoubleNDArray(shape);
    }

    @Override
    protected Complex getUnchecked(int linearIndex) {
        int pos = linearIndex * 2;
        return new Complex(data[pos], data[pos + 1]);
    }

    @Override
    protected Complex getUnchecked(int... indices) {
        return getUncheckedDefault(indices);
    }

    @Override
    protected Double getRealUnchecked(int linearIndex) {
        return data[linearIndex * 2];
    }

    @Override
    protected Double getRealUnchecked(int... indices) {
        return getRealUncheckedDefault(indices);
    }

    @Override
    protected Double getImagUnchecked(int linearIndex) {
        return data[linearIndex * 2 + 1];
    }

    @Override
    protected Double getImagUnchecked(int... indices) {
        return getImagUncheckedDefault(indices);
    }

    @Override
    protected void setUnchecked(Complex value, int linearIndex) {
        int pos = linearIndex * 2;
        data[pos] = value.getReal();
        data[pos + 1] = value.getImaginary();
    }

    @Override
    protected void setUnchecked(Complex value, int... indices) {
        setUncheckedDefault(value, indices);
    }

    @Override
    protected void setRealUnchecked(Double value, int linearIndex) {
        data[linearIndex * 2] = value;
    }

    @Override
    protected void setRealUnchecked(Double value, int... indices) {
        setRealUncheckedDefault(value, indices);
    }

    @Override
    protected void setImagUnchecked(Double value, int linearIndex) {
        data[linearIndex * 2 + 1] = value;
    }

    @Override
    protected void setImagUnchecked(Double value, int... indices) {
        setImagUncheckedDefault(value, indices);
    }

}
