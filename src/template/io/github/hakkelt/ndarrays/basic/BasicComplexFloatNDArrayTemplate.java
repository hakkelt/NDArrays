package io.github.hakkelt.ndarrays.basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.ComplexNDArrayCollector;
import io.github.hakkelt.ndarrays.internal.Generated;

/**
 * Reference implementation for the NDArray of complex float (single-precision, 32 bit floating point) values.
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays/basic", newName = "BasicComplex$2NDArray")
@Patterns({ "/float/", "/Float/", "single precision, 32 bit floating-point" })
@Replacements({ "double", "Double", "double precision, 64 bit floating-point" })
public final class BasicComplexFloatNDArrayTemplate extends AbstractComplexNDArray<Float> {
    protected float[] data;

    @SuppressWarnings("unused")
    private BasicComplexFloatNDArrayTemplate() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it
     * with zeros.
     * 
     * @param shape dimensions / shape of the NDArray
     */
    public BasicComplexFloatNDArrayTemplate(int... shape) {
        baseConstuctor(shape);
        this.data = new float[length() * 2];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicComplexFloatNDArrayTemplate(NDArray<?> array) {
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
    public BasicComplexFloatNDArrayTemplate(NDArray<? extends Number> real, NDArray<? extends Number> imag) {
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
    @Replace(pattern = "byte", replacements = { "short", "int", "long", "float", "double" })
    public static ComplexNDArray<Float> of(byte... array) {
        return new BasicComplexFloatNDArrayTemplate(array.length).copyFrom(array);
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
        return new BasicComplexFloatNDArrayTemplate(NDArrayUtils.computeDims(realOrComplex)).copyFrom(realOrComplex);
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
    @Replace(pattern = "byte", replacements = { "short", "int", "long", "float", "double" })
    public static ComplexNDArray<Float> of(byte[] real, byte[] imag) {
        return new BasicComplexFloatNDArrayTemplate(real.length).copyFrom(real, imag);
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
        return new BasicComplexFloatNDArrayTemplate(NDArrayUtils.computeDims(real)).copyFrom(real, imag);
    }

    @Override
    public ComplexNDArray<Float> copyFrom(NDArray<?> array) {
        if (array instanceof BasicComplexFloatNDArrayTemplate) {
            NDArrayUtils.checkShapeCompatibility(this, array.shape());
            data = ((BasicComplexFloatNDArrayTemplate) array).data.clone();
        } else
            super.copyFrom(array);
        return this;
    }

    public String getNamePrefix() {
        return "basic";
    }

    public static Collector<Object, List<Object>, NDArray<Complex>> getCollector(int... shape) {
        return new ComplexNDArrayCollector<>(new BasicComplexFloatNDArrayTemplate(shape));
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof BasicComplexFloatNDArrayTemplate
                ? Arrays.equals(data, ((BasicComplexFloatNDArrayTemplate) other).data)
                : super.equals(other);
    }

    @Generated
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    protected ComplexNDArray<Float> createNewNDArrayOfSameTypeAsMe(int... shape) {
        return new BasicComplexFloatNDArrayTemplate(shape);
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
