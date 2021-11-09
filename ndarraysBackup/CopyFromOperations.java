package io.github.hakkelt.ndarrays;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;

/**
 * Utility class for operations where the NDArray is filled with values copied from a primitive array or another NDArray.
 */
public class CopyFromOperations<T,T2 extends Number> {
    
    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, float[] array) {
        if (me.ndim() > 1 || me.shape[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, double[] array) {
        if (me.ndim() > 1 || me.shape[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, byte[] array) {
        if (me.ndim() > 1 || me.shape[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, short[] array) {
        if (me.ndim() > 1 || me.shape[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, int[] array) {
        if (me.ndim() > 1 || me.shape[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, long[] array) {
        if (me.ndim() > 1 || me.shape[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, Object[] array) {
        int[] arrayDims = IndexingOperations.computeDims(array);
        if (!Arrays.equals(me.shape(), arrayDims))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, Printer.dimsToString(arrayDims), Printer.dimsToString(me.shape())));
        flatten(me, array, 0, 0);
        return me;
    }

    @SuppressWarnings("unchecked")
    public NDArray<T2> copyFrom(RealNDArray<T2> me, NDArray<? extends Number> array) {
        if (!Arrays.equals(me.shape(), array.shape()))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, Printer.dimsToString(array.shape()), Printer.dimsToString(me.shape())));
        me.streamLinearIndices().parallel()
            .forEach(i -> me.set(((AbstractNDArray<T2,T2>)me).wrapValue(array.get(i)), i));
        return me;
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, float[] array) {
        if (me.ndim() > 1 || me.shape(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, double[] array) {
        if (me.ndim() > 1 || me.shape(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, byte[] array) {
        if (me.ndim() > 1 || me.shape(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, short[] array) {
        if (me.ndim() > 1 || me.shape(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, int[] array) {
        if (me.ndim() > 1 || me.shape(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, long[] array) {
        if (me.ndim() > 1 || me.shape(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, array.length, Printer.dimsToString(me.shape())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, Object[] array) {
        int[] arrayDims = IndexingOperations.computeDims(array);
        if (!Arrays.equals(me.shape(), arrayDims))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, Printer.dimsToString(arrayDims), Printer.dimsToString(me.shape())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, NDArray<?> array) {
        if (!Arrays.equals(me.shape(), array.shape()))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, Printer.dimsToString(array.shape()), Printer.dimsToString(me.shape())));
        me.streamLinearIndices().parallel()
            .forEach(i -> {
                if (array.dtype() == Complex.class)
                    me.set((Complex)array.get(i), i);
                else
                    me.set(((AbstractNDArray<Complex,T2>)me).wrapValue((Number)array.get(i)), i);
            });
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, float[] real, float[] imag) {
        if (me.ndim() > 1 || me.shape(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, real.length, Printer.dimsToString(me.shape())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, double[] real, double[] imag) {
        if (me.ndim() > 1 || me.shape(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, real.length, Printer.dimsToString(me.shape())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, byte[] real, byte[] imag) {
        if (me.ndim() > 1 || me.shape(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, real.length, Printer.dimsToString(me.shape())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, short[] real, short[] imag) {
        if (me.ndim() > 1 || me.shape(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, real.length, Printer.dimsToString(me.shape())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, int[] real, int[] imag) {
        if (me.ndim() > 1 || me.shape(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, real.length, Printer.dimsToString(me.shape())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, long[] real, long[] imag) {
        if (me.ndim() > 1 || me.shape(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, real.length, Printer.dimsToString(me.shape())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, Object[] real, Object[] imag) {
        int[] arrayDims = IndexingOperations.computeDims(real);
        if (!Arrays.equals(me.shape(), arrayDims))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, Printer.dimsToString(arrayDims), Printer.dimsToString(me.shape())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArray<T2> me, NDArray<? extends Number> real, NDArray<? extends Number> imag) {
        if (!Arrays.equals(me.shape(), real.shape()))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, Printer.dimsToString(real.shape()), Printer.dimsToString(me.shape())));
        if (!Arrays.equals(real.shape(), imag.shape()))
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SHAPE);
        me.streamLinearIndices().parallel()
            .forEach(i -> {
                me.setReal(real.get(i), i);
                me.setImag(imag.get(i), i);
            });
        return me;
    }

    protected void flatten(AbstractNDArray<T,T2> me, float[] real, int startIndex, int dimension) {
        IntStream.range(0, real.length).parallel()
            .forEach(i -> me.set(real[i], startIndex + i * me.multipliers[dimension]));
    }

    protected void flatten(AbstractNDArray<T,T2> me, double[] real, int startIndex, int dimension) {
        IntStream.range(0, real.length).parallel()
            .forEach(i -> me.set(real[i], startIndex + i * me.multipliers[dimension]));
    }

    protected void flatten(AbstractNDArray<T,T2> me, byte[] real, int startIndex, int dimension) {
        IntStream.range(0, real.length).parallel()
            .forEach(i -> me.set(real[i], startIndex + i * me.multipliers[dimension]));
    }

    protected void flatten(AbstractNDArray<T,T2> me, short[] real, int startIndex, int dimension) {
        IntStream.range(0, real.length).parallel()
            .forEach(i -> me.set(real[i], startIndex + i * me.multipliers[dimension]));
    }

    protected void flatten(AbstractNDArray<T,T2> me, int[] real, int startIndex, int dimension) {
        IntStream.range(0, real.length).parallel()
            .forEach(i -> me.set(real[i], startIndex + i * me.multipliers[dimension]));
    }

    protected void flatten(AbstractNDArray<T,T2> me, long[] real, int startIndex, int dimension) {
        IntStream.range(0, real.length).parallel()
            .forEach(i -> me.set(real[i], startIndex + i * me.multipliers[dimension]));
    }

    protected void flatten(AbstractNDArray<T,T2> me, Object[] realOrComplex, int startIndex, int dimension) {
        int i = 0;
        for (Object obj : realOrComplex) {
            if (obj.getClass().equals(float[].class)) {
                flatten(me, (float[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(double[].class)) {
                flatten(me, (double[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(byte[].class)) {
                flatten(me, (byte[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(short[].class)) {
                flatten(me, (short[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(int[].class)) {
                flatten(me, (int[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(long[].class)) {
                flatten(me, (long[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(Complex[].class)) {
                flatten(me, (Complex[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().isArray()) {
                flatten(me, (Object[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else {
                throw new IllegalArgumentException(String.format(Errors.COPY_FROM_UNSUPPORTED_TYPE, obj.getClass().getSimpleName()));
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArray<T2> me, Complex[] compl, int startIndex, int dimension) {
        IntStream.range(0, compl.length).parallel()
            .forEach(i -> me.set(compl[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]));
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArray<T2> me, float[] real, float[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SHAPE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArray<T2> me, double[] real, double[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SHAPE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArray<T2> me, byte[] real, byte[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SHAPE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArray<T2> me, short[] real, short[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SHAPE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArray<T2> me, int[] real, int[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SHAPE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArray<T2> me, long[] real, long[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SHAPE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    protected void flatten(ComplexNDArray<T2> me, Object[] real, Object[] imag, int startIndex, int dimension) {
        for (int i = 0; i < real.length; i++)
            subFlatten(me, real[i], imag[i], getNewStartIndex(me, i, startIndex, dimension), dimension + 1);
    }

    protected void subFlatten(ComplexNDArray<T2> me, Object real, Object imag, int startIndex, int dimension) {
        if (real.getClass().equals(float[].class) && imag.getClass().equals(float[].class))
            flatten(me, (float[]) real, (float[]) imag, startIndex, dimension);
        else if (real.getClass().equals(double[].class) && imag.getClass().equals(double[].class))
            flatten(me, (double[]) real, (double[]) imag, startIndex, dimension);
        else if (real.getClass().equals(byte[].class) && imag.getClass().equals(byte[].class))
            flatten(me, (byte[]) real, (byte[]) imag, startIndex, dimension);
        else if (real.getClass().equals(short[].class) && imag.getClass().equals(short[].class))
            flatten(me, (short[]) real, (short[]) imag, startIndex, dimension);
        else if (real.getClass().equals(int[].class) && imag.getClass().equals(int[].class))
            flatten(me, (int[]) real, (int[]) imag, startIndex, dimension);
        else if (real.getClass().equals(long[].class) && imag.getClass().equals(long[].class))
            flatten(me, (long[]) real, (long[]) imag, startIndex, dimension);
        else if (real.getClass().isArray() && imag.getClass().isArray())
            flatten(me, (Object[]) real, (Object[]) imag, startIndex, dimension);
        else
            throw new IllegalArgumentException(Errors.NOT_FLOAT_ARRAYS);
    }

    @SuppressWarnings("unchecked")
    protected int getNewStartIndex(ComplexNDArray<T2> me, int i, int startIndex, int dimension) {
        return startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension];
    }

}
