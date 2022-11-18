package io.github.hakkelt.ndarrays.internal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;

/**
 * Utility class for operations where the NDArray is filled with values copied from a primitive array or another NDArray.
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays/internal", newName = "CopyFromOperations")
public class CopyFromOperationsTemplate<T,T2 extends Number> {
    
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long"})
    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, float[] array) {
        if (me.ndim() > 1 || me.shape[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, Object[] array) {
        int[] arrayDims = NDArrayUtils.computeDims(array);
        NDArrayUtils.checkShapeCompatibility(me, arrayDims);
        NDArrayUtils.checkDTypeCompatibility(me, array);
        flatten(me, array, 0, 0);
        return me;
    }

    @SuppressWarnings("unchecked")
    public NDArray<T2> copyFrom(RealNDArray<T2> me, NDArray<?> array) {
        NDArrayUtils.checkShapeCompatibility(me, array.shape());
        AbstractNDArray<T2,T2> castedMe = (AbstractNDArray<T2,T2>) me;
        AbstractNDArray<?, ?> castedArray = (AbstractNDArray<?,?>) array;
        me.streamLinearIndices()
            .forEach(i -> castedMe.setUnchecked(castedMe.wrapValue(castedArray.getUnchecked(i)), i));
        return me;
    }

    @SuppressWarnings("unchecked")
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long"})
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, float[] array) {
        if (me.ndim() > 1 || me.shape(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, Object[] array) {
        int[] arrayDims = NDArrayUtils.computeDims(array);
        NDArrayUtils.checkShapeCompatibility(me, arrayDims);
        NDArrayUtils.checkDTypeCompatibility(me, array);
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, NDArray<?> array) {
        NDArrayUtils.checkShapeCompatibility(me, array.shape());
        AbstractNDArray<Complex, T2> castedMe = (AbstractNDArray<Complex, T2>) me;
        AbstractNDArray<?, ?> castedArray = (AbstractNDArray<?,?>) array;
        if (array.dtype() == Complex.class)
            me.streamLinearIndices()
                .forEach(i -> castedMe.setUnchecked((Complex) castedArray.getUnchecked(i), i));
        else
            me.streamLinearIndices()
                .forEach(i -> castedMe.setUnchecked(castedMe.wrapValue(castedArray.getUnchecked(i)), i));
        return me;
    }

    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long"})
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, float[] real, float[] imag) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SHAPE);
        if (me.ndim() > 1 || me.shape(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, real.length, Printer.dimsToString(me.shape())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, Object[] real, Object[] imag) {
        int[] arrayDimsReal = NDArrayUtils.computeDims(real);
        int[] arrayDimsImag = NDArrayUtils.computeDims(imag);
        if (!Arrays.equals(arrayDimsReal, arrayDimsImag))
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SHAPE);
        NDArrayUtils.checkShapeCompatibility(me, arrayDimsReal);
        NDArrayUtils.checkDTypeCompatibility(me, real);
        NDArrayUtils.checkDTypeCompatibility(me, imag);
        flatten(me, real, imag, 0, 0);
        return me;
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, NDArray<? extends Number> real, NDArray<? extends Number> imag) {
        if (!Arrays.equals(real.shape(), imag.shape()))
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SHAPE);
        NDArrayUtils.checkShapeCompatibility(me, real.shape());
        AbstractNDArray<T, T2> castedMe = (AbstractNDArray<T, T2>) me;
        AbstractNDArray<? extends Number, ?> castedReal = (AbstractNDArray<? extends Number,?>) real;
        AbstractNDArray<? extends Number, ?> castedImag = (AbstractNDArray<? extends Number,?>) imag;
        me.streamLinearIndices()
            .forEach(i -> {
                castedMe.setRealUnchecked(castedMe.wrapRealValue(castedReal.getUnchecked(i)), i);
                castedMe.setImagUnchecked(castedMe.wrapRealValue(castedImag.getUnchecked(i)), i);
            });
        return me;
    }

    public ComplexNDArray<T2> copyFromMagnitudePhase(ComplexNDArray<T2> me, NDArray<? extends Number> magnitude, NDArray<? extends Number> phase) {
        if (!Arrays.equals(magnitude.shape(), phase.shape()))
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SHAPE);
        AbstractNDArray<? extends Number, ?> castedMagnitude = (AbstractNDArray<? extends Number,?>) magnitude;
        AbstractNDArray<? extends Number, ?> castedPhase = (AbstractNDArray<? extends Number,?>) phase;
        me.fillUsingLinearIndices(i -> {
                double m = castedMagnitude.getUnchecked(i).doubleValue();
                double phi = castedPhase.getUnchecked(i).doubleValue();
                return new Complex(Math.cos(phi), Math.sin(phi)).multiply(m);
            });
        return me;
    }

    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long", "BigInteger", "BigDecimal", "Complex"})
    protected void flatten(AbstractNDArray<T,T2> me, float[] real, int startIndex, int dimension) {
        getIndexStream(real.length).forEach(i ->
            me.setUnchecked(me.wrapValue(real[i]), startIndex + i * me.multipliers[dimension]));
    }

    protected void flatten(AbstractNDArray<T,T2> me, Object[] realOrComplex, int startIndex, int dimension) {
        if (flatten1D(me, realOrComplex, startIndex, dimension))
            return;
        int i = 0;
        for (Object obj : realOrComplex) {
            if (obj.getClass().equals(float[].class))
                flatten(me, (float[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            else if (obj.getClass().equals(double[].class))
                flatten(me, (double[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            else if (obj.getClass().equals(byte[].class))
                flatten(me, (byte[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            else if (obj.getClass().equals(short[].class))
                flatten(me, (short[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            else if (obj.getClass().equals(int[].class))
                flatten(me, (int[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            else if (obj.getClass().equals(long[].class))
                flatten(me, (long[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            else if (obj.getClass().equals(BigInteger[].class))
                flatten(me, (BigInteger[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            else if (obj.getClass().equals(BigDecimal[].class))
                flatten(me, (BigDecimal[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            else if (obj.getClass().equals(Complex[].class))
                flatten(me, (Complex[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            else
                flatten(me, (Object[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
        }
    }

    protected boolean flatten1D(AbstractNDArray<T, T2> me, Object[] realOrComplex, int startIndex, int dimension) {
        if (realOrComplex.getClass().equals(BigInteger[].class)) {
            flatten(me, (BigInteger[]) realOrComplex, startIndex, dimension); // NOSONAR
            return true;
        } else if (realOrComplex.getClass().equals(BigDecimal[].class)) {
            flatten(me, (BigDecimal[]) realOrComplex, startIndex, dimension); // NOSONAR
            return true;
        } else if (realOrComplex.getClass().equals(Complex[].class)) {
            flatten(me, (Complex[]) realOrComplex, startIndex, dimension); // NOSONAR
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long", "BigInteger", "BigDecimal"})
    protected void flatten(ComplexNDArray<T2> me, float[] real, float[] imag, int startIndex, int dimension) {
        AbstractNDArray<T, T2> casted = (AbstractNDArray<T, T2>) me;
        getIndexStream(real.length)
            .forEach(i -> {
                int linearIndex = startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension];
                casted.setRealUnchecked(casted.wrapRealValue(real[i]), linearIndex);
                casted.setImagUnchecked(casted.wrapRealValue(imag[i]), linearIndex);
            });
    }

    protected void flatten(ComplexNDArray<T2> me, Object[] real, Object[] imag, int startIndex, int dimension) {
        if (real.getClass().equals(BigInteger[].class)) {
            flatten(me, (BigInteger[]) real, (BigInteger[]) imag, startIndex, dimension); // NOSONAR
            return;
        }
        if (real.getClass().equals(BigDecimal[].class)) {
            flatten(me, (BigDecimal[]) real, (BigDecimal[]) imag, startIndex, dimension); // NOSONAR
            return;
        }
        for (int i = 0; i < real.length; i++)
            subFlatten(me, real[i], imag[i], getNewStartIndex(me, i, startIndex, dimension), dimension + 1);
    }

    protected void subFlatten(ComplexNDArray<T2> me, Object real, Object imag, int startIndex, int dimension) {
        if (!real.getClass().equals(imag.getClass()))
            throw new IllegalArgumentException(Errors.COPYFROM_INPUT_TYPE_DIFERS);
        Class<?> clazz = real.getClass();
        if (clazz.equals(float[].class))
            flatten(me, (float[]) real, (float[]) imag, startIndex, dimension);
        else if (clazz.equals(double[].class))
            flatten(me, (double[]) real, (double[]) imag, startIndex, dimension);
        else if (clazz.equals(byte[].class))
            flatten(me, (byte[]) real, (byte[]) imag, startIndex, dimension);
        else if (clazz.equals(short[].class))
            flatten(me, (short[]) real, (short[]) imag, startIndex, dimension);
        else if (clazz.equals(int[].class))
            flatten(me, (int[]) real, (int[]) imag, startIndex, dimension);
        else if (clazz.equals(long[].class))
            flatten(me, (long[]) real, (long[]) imag, startIndex, dimension);
        else if (clazz.equals(BigInteger[].class))
            flatten(me, (BigInteger[]) real, (BigInteger[]) imag, startIndex, dimension);
        else if (clazz.equals(BigDecimal[].class))
            flatten(me, (BigDecimal[]) real, (BigDecimal[]) imag, startIndex, dimension);
        else
            flatten(me, (Object[]) real, (Object[]) imag, startIndex, dimension);
    }

    protected IntStream getIndexStream(int length) {
        IntStream stream = IntStream.range(0, length);
        return length > NDArrayUtils.PARALLEL_STREAM_THRESHOLD ? stream.parallel() : stream;
    }

    @SuppressWarnings("unchecked")
    protected int getNewStartIndex(ComplexNDArray<T2> me, int i, int startIndex, int dimension) {
        return startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension];
    }

}
