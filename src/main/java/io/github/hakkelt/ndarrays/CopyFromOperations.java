package io.github.hakkelt.ndarrays;

import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;

class CopyFromOperations<T,T2 extends Number> {
    
    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, float[] array) {
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, double[] array) {
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, byte[] array) {
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, short[] array) {
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, int[] array) {
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, long[] array) {
        flatten(me, array, 0, 0);
        return me;
    }

    public NDArray<T> copyFrom(AbstractNDArray<T,T2> me, Object[] array) {
        flatten(me, array, 0, 0);
        return me;
    }

    @SuppressWarnings("unchecked")
    public NDArray<T2> copyFrom(InternalRealNDArray<T2> me, NDArray<? extends Number> array) {
        me.streamLinearIndices().parallel()
            .forEach(i -> {
                me.set(((AbstractNDArray<T2,T2>)me).wrapValue(array.get(i)), i);
            });
        return me;
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, float[] array) {
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, double[] array) {
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, byte[] array) {
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, short[] array) {
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, int[] array) {
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, long[] array) {
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, Object[] array) {
        return (ComplexNDArray<T2>)copyFrom((AbstractNDArray<T,T2>)me, array);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, NDArray<?> array) {
        me.streamLinearIndices().parallel()
            .forEach(i -> {
                if (array.eltype() == Complex.class)
                    me.set((Complex)array.get(i), i);
                else
                    me.set(((AbstractNDArray<Complex,T2>)me).wrapValue((Number)array.get(i)), i);
            });
        return me;
    }

    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, float[] real, float[] imag) {
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, double[] real, double[] imag) {
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, byte[] real, byte[] imag) {
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, short[] real, short[] imag) {
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, int[] real, int[] imag) {
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, long[] real, long[] imag) {
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, Object[] real, Object[] imag) {
        flatten(me, real, imag, 0, 0);
        return me;
    }

    public ComplexNDArray<T2> copyFrom(InternalComplexNDArray<T2> me, NDArray<? extends Number> real, NDArray<? extends Number> imag) {
        if (real.eltype() == Complex.class || imag.eltype() == Complex.class)
            throw new IllegalArgumentException();
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
            } else if (obj.getClass().equals(Complex[].class)) {
                flatten(me, (Complex[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else if (obj.getClass().isArray()) {
                flatten(me, (Object[]) obj, startIndex + i++ * me.multipliers[dimension], dimension + 1);
            } else {
                throw new IllegalArgumentException(Errors.NOT_FLOAT_ARRAY);
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void flatten(InternalComplexNDArray<T2> me, Complex[] compl, int startIndex, int dimension) {
        IntStream.range(0, compl.length).parallel()
            .forEach(i -> me.set(compl[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]));
    }

    @SuppressWarnings("unchecked")
    protected void flatten(InternalComplexNDArray<T2> me, float[] real, float[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(InternalComplexNDArray<T2> me, double[] real, double[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(InternalComplexNDArray<T2> me, byte[] real, byte[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(InternalComplexNDArray<T2> me, short[] real, short[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(InternalComplexNDArray<T2> me, int[] real, int[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(InternalComplexNDArray<T2> me, long[] real, long[] imag, int startIndex, int dimension) {
        if (real.length != imag.length)
            throw new IllegalArgumentException(Errors.ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * ((AbstractNDArray<T,T2>)me).multipliers[dimension]);
            });
    }

    @SuppressWarnings("unchecked")
    protected void flatten(InternalComplexNDArray<T2> me, Object[] real, Object[] imag, int startIndex, int dimension) {
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
