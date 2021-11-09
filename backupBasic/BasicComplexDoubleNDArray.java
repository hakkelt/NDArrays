package io.github.hakkelt.ndarrays.basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.ndarrays.AbstractComplexNDArray;
import io.github.hakkelt.ndarrays.ComplexNDArray;
import io.github.hakkelt.ndarrays.ComplexNDArrayCollector;
import io.github.hakkelt.ndarrays.IndexingOperations;
import io.github.hakkelt.ndarrays.NDArray;

/**
 * Reference implementation for the NDArray of complex double (double-precision,
 * 64 bit floating point) values.
 */
public final class BasicComplexDoubleNDArray extends AbstractComplexNDArray<Double> {
    protected double[] data;

    @SuppressWarnings("unused")
    private BasicComplexDoubleNDArray() {}

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
     * Factory method that creates a ComplexNDArray from a list or 1D array of
     * double values.
     * 
     * @param array a list or 1D array of double values from which a
     *              BasicComplexDoubleNDArray is created.
     * @return a ComplexNDArray created from a list or 1D array of double values
     */
    public static ComplexNDArray<Double> of(double... array) {
        return new BasicComplexDoubleNDArray(array.length).copyFrom(array);
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
     * @param array a list or 1D array of double values from which a
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
     * Factory method that creates an NDArray from a multi-dimensional array of
     * numeric values (including Complex type).
     * 
     * @param realOrComplex a multi-dimensional array of numeric values (including
     *                      Complex type) from which a BasicComplexDoubleNDArray is
     *                      created.
     * @return an NDArray created from a multi-dimensional array of numeric values
     */
    public static ComplexNDArray<Double> of(Object[] realOrComplex) {
        return new BasicComplexDoubleNDArray(IndexingOperations.computeDims(realOrComplex)).copyFrom(realOrComplex);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of float
     * values.
     * 
     * @param real a 1D array of float values from which the real part of the
     *             created BasicComplexDoubleNDArray is read.
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
     * @param real a 1D array of double values from which the real part of the
     *             created BasicComplexDoubleNDArray is read.
     * @param imag a 1D array of double values from which the imaginary part of the
     *             created BasicComplexDoubleNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of double values
     */
    public static ComplexNDArray<Double> of(double[] real, double[] imag) {
        return new BasicComplexDoubleNDArray(real.length).copyFrom(real, imag);
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
     * Factory method that creates a ComplexNDArray from a list or 1D array of short
     * values.
     * 
     * @param real a 1D array of short values from which the real part of the
     *             created BasicComplexDoubleNDArray is read.
     * @param imag a 1D array of short values from which the imaginary part of the
     *             created BasicComplexDoubleNDArray is read.
     * @return a ComplexNDArray created from the two 1D array of short values
     */
    public static ComplexNDArray<Double> of(short[] real, short[] imag) {
        return new BasicComplexDoubleNDArray(real.length).copyFrom(real, imag);
    }

    /**
     * Factory method that creates a ComplexNDArray from two 1D array of int values.
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
        return new BasicComplexDoubleNDArray(IndexingOperations.computeDims(real)).copyFrom(real, imag);
    }

    @Override
    public ComplexNDArray<Double> copyFrom(NDArray<?> array) {
        if (array instanceof BasicComplexDoubleNDArray)
            data = ((BasicComplexDoubleNDArray) array).data.clone();
        else
            super.copyFrom(array);
        return this;
    }

    public String getNamePrefix() {
        return "basic";
    }

    public Complex get(int linearIndex) {
        return new Complex(getReal(linearIndex).doubleValue(), getImag(linearIndex).doubleValue());
    }

    public Complex get(int... indices) {
        return get(IndexingOperations.cartesianIndicesToLinearIndex(indices, shape, multipliers));
    }

    public Double getReal(int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        return data[linearIndex * 2];
    }

    public Double getReal(int... indices) {
        return getReal(IndexingOperations.cartesianIndicesToLinearIndex(indices, shape, multipliers));
    }

    public Double getImag(int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        return data[linearIndex * 2 + 1];
    }

    public Double getImag(int... indices) {
        return getImag(IndexingOperations.cartesianIndicesToLinearIndex(indices, shape, multipliers));
    }

    public void set(Complex value, int linearIndex) {
        setReal(value.getReal(), linearIndex);
        setImag(value.getImaginary(), linearIndex);
    }

    public void set(Number value, int linearIndex) {
        setReal(value, linearIndex);
        setImag(0, linearIndex);
    }

    public void set(Number value, int... indices) {
        set(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, shape, multipliers));
    }

    public void set(Complex value, int... indices) {
        set(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, shape, multipliers));
    }

    public void setReal(Number real, int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        data[linearIndex * 2] = real.doubleValue();
    }

    public void setReal(Number value, int... indices) {
        setReal(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, shape, multipliers));
    }

    public void setImag(Number imag, int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        data[linearIndex * 2 + 1] = imag.doubleValue();
    }

    public void setImag(Number value, int... indices) {
        setImag(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, shape, multipliers));
    }

    public static Collector<Object, List<Object>, NDArray<Complex>> getCollector(int... shape) {
        return new ComplexNDArrayCollector<>(new BasicComplexDoubleNDArray(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicComplexDoubleNDArray
                ? Arrays.equals(data, ((BasicComplexDoubleNDArray) other).data)
                : super.equals(other);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    protected AbstractComplexNDArray<Double> createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicComplexDoubleNDArray(shape);
    }

    protected NDArray<Double> createNewRealNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicDoubleNDArray(shape);
    }

}
