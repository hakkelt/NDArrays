package io.github.hakkelt.ndarrays;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;

class CopyFromOperations<T,T2 extends Number> {
    
    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, float[] array) {
        if (me.ndims() > 1 || me.dims[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, double[] array) {
        if (me.ndims() > 1 || me.dims[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, byte[] array) {
        if (me.ndims() > 1 || me.dims[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, short[] array) {
        if (me.ndims() > 1 || me.dims[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, int[] array) {
        if (me.ndims() > 1 || me.dims[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, long[] array) {
        if (me.ndims() > 1 || me.dims[0] != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims)));
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, Object[] array) {
        int[] arrayDims = IndexingOperations.computeDims(array);
        if (!Arrays.equals(me.dims(), arrayDims))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, Printer.dimsToString(arrayDims), Printer.dimsToString(me.dims())));
        flatten(me, array, 0, 0);
        return me;
    }

    @SuppressWarnings("unchecked")
    public NDArray<T2> copyFrom(InternalRealNDArray<T2> me, NDArray<? extends Number> array) {
        if (!Arrays.equals(me.dims(), array.dims()))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, Printer.dimsToString(array.dims()), Printer.dimsToString(me.dims())));
        me.streamLinearIndices().parallel()
            .forEach(i -> me.set(((AbstractNDArray<T2,T2>)me).wrapValue(array.get(i)), i));
        return me;
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, float[] array) {
        if (me.ndims() > 1 || me.dims(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, double[] array) {
        if (me.ndims() > 1 || me.dims(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, byte[] array) {
        if (me.ndims() > 1 || me.dims(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, short[] array) {
        if (me.ndims() > 1 || me.dims(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, int[] array) {
        if (me.ndims() > 1 || me.dims(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, long[] array) {
        if (me.ndims() > 1 || me.dims(0) != array.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(array.length), Printer.dimsToString(me.dims())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, Object[] array) {
        int[] arrayDims = IndexingOperations.computeDims(array);
        if (!Arrays.equals(me.dims(), arrayDims))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, Printer.dimsToString(arrayDims), Printer.dimsToString(me.dims())));
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, NDArray<?> array) {
        if (!Arrays.equals(me.dims(), array.dims()))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, Printer.dimsToString(array.dims()), Printer.dimsToString(me.dims())));
        me.streamLinearIndices().parallel()
            .forEach(i -> {
                if (array.eltype() == Complex.class)
                    me.set((Complex)array.get(i), i);
                else
                    me.set(((AbstractNDArray<Complex,T2>)me).wrapValue((Number)array.get(i)), i);
            });
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, float[] real, float[] imag) {
        if (me.ndims() > 1 || me.dims(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(real.length), Printer.dimsToString(me.dims())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, double[] real, double[] imag) {
        if (me.ndims() > 1 || me.dims(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(real.length), Printer.dimsToString(me.dims())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, byte[] real, byte[] imag) {
        if (me.ndims() > 1 || me.dims(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(real.length), Printer.dimsToString(me.dims())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, short[] real, short[] imag) {
        if (me.ndims() > 1 || me.dims(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(real.length), Printer.dimsToString(me.dims())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, int[] real, int[] imag) {
        if (me.ndims() > 1 || me.dims(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(real.length), Printer.dimsToString(me.dims())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, long[] real, long[] imag) {
        if (me.ndims() > 1 || me.dims(0) != real.length)
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, String.valueOf(real.length), Printer.dimsToString(me.dims())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, Object[] real, Object[] imag) {
        int[] arrayDims = IndexingOperations.computeDims(real);
        if (!Arrays.equals(me.dims(), arrayDims))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, Printer.dimsToString(arrayDims), Printer.dimsToString(me.dims())));
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(ComplexNDArrayTrait<T2> me, NDArray<? extends Number> real, NDArray<? extends Number> imag) {
        if (!Arrays.equals(me.dims(), real.dims()))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SIZE, Printer.dimsToString(real.dims()), Printer.dimsToString(me.dims())));
        if (!Arrays.equals(real.dims(), imag.dims()))
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
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
            } else if (obj.getClass().equals(long[].class)) {
                flatten(me, (double[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(double[].class)) {
                flatten(me, (byte[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(byte[].class)) {
                flatten(me, (short[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(short[].class)) {
                flatten(me, (int[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(int[].class)) {
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
    protected void flatten(ComplexNDArrayTrait<T2> me, Complex[] compl, int startIndex, int dimension) {
        IntStream.range(0, compl.length).parallel()
            .forEach(i -> me.set(compl[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]));
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArrayTrait<T2> me, float[] real, float[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArrayTrait<T2> me, double[] real, double[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArrayTrait<T2> me, byte[] real, byte[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArrayTrait<T2> me, short[] real, short[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArrayTrait<T2> me, int[] real, int[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArrayTrait<T2> me, long[] real, long[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(ComplexNDArrayTrait<T2> me, Object[] real, Object[] imag, int startIndex, int dimension) {
        for (int i = 0; i < real.length; i++) {
            if (real[i].getClass().equals(float[].class) && imag[i].getClass().equals(float[].class))
                flatten(me, (float[]) real[i], (float[]) imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension], dimension + 1);
            else if (real[i].getClass().equals(double[].class) && imag[i].getClass().equals(double[].class))
                flatten(me, (double[]) real[i], (double[]) imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension], dimension + 1);
            else if (real[i].getClass().isArray() && imag[i].getClass().isArray())
                flatten(me, (Object[]) real[i], (Object[]) imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension], dimension + 1);
            else
                throw new IllegalArgumentException(Errors.NOT_FLOAT_ARRAYS);
        }
    }

}
