package io.github.hakkelt.ndarrays;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.apache.commons.math3.complex.Complex;

interface InternalComplexNDArray<T extends Number> extends ComplexNDArray<T> {

    public default ComplexNDArray<T> sum(int... selectedDims) {
        return new ArrayOperations<Complex,T>().sum(this, selectedDims);
    }

    public default ComplexNDArray<T> add(Object ...addends) {
        return new ArrayOperations<Complex,T>().add(this, addends);
    }

    public default ComplexNDArray<T> addInplace(Object ...addends) {
        return new ArrayOperations<Complex,T>().addInplace(this, addends);
    }

    public default ComplexNDArray<T> subtract(Object ...substrahends) {
        return new ArrayOperations<Complex,T>().subtract(this, substrahends);
    }

    public default ComplexNDArray<T> subtractInplace(Object ...substrahends) {
        return new ArrayOperations<Complex,T>().subtractInplace(this, substrahends);
    }

    public default ComplexNDArray<T> multiply(Object... multiplicands) {
        return new ArrayOperations<Complex,T>().multiply(this, multiplicands);
    }

    public default ComplexNDArray<T> multiplyInplace(Object... multiplicands) {
        return new ArrayOperations<Complex,T>().multiplyInplace(this, multiplicands);
    }

    public default ComplexNDArray<T> divide(Object... divisors) {
        return new ArrayOperations<Complex,T>().divide(this, divisors);
    }

    public default ComplexNDArray<T> divideInplace(Object... divisors) {
        return new ArrayOperations<Complex,T>().divideInplace(this, divisors);
    }

    public default ComplexNDArray<T> fill(Complex value) {
        return new ArrayOperations<Complex,T>().fill(this, value);
    }

    public default ComplexNDArray<T> fill(T value) {
        return new ArrayOperations<Complex,T>().fill(this, value);
    }

    public default ComplexNDArray<T> fill(float value) {
        return new ArrayOperations<Complex,T>().fill(this, value);
    }

    public default ComplexNDArray<T> fill(double value) {
        return new ArrayOperations<Complex,T>().fill(this, value);
    }

    public default ComplexNDArray<T> slice(Object ...slicingExpressions) {
        return new ViewOperations<Complex,T>().slice(this, slicingExpressions);
    }

    public default ComplexNDArray<T> mask(NDArray<?> mask) {
        return new ViewOperations<Complex,T>().mask(this, mask, false);
    }

    public default ComplexNDArray<T> mask(Predicate<Complex> func) {
        return new ViewOperations<Complex,T>().mask(this, func);
    }

    public default ComplexNDArray<T> maskWithLinearIndices(BiPredicate<Complex, Integer> func) {
        return new ViewOperations<Complex,T>().mask(this, func, true);
    }

    public default ComplexNDArray<T> maskWithCartesianIndices(BiPredicate<Complex, int[]> func) {
        return new ViewOperations<Complex,T>().mask(this, func, false);
    }

    public default ComplexNDArray<T> inverseMask(NDArray<?> mask) {
        return new ViewOperations<Complex,T>().mask(this, mask, true);
    }

    public default ComplexNDArray<T> permuteDims(int... permutation) {
        return new ViewOperations<Complex,T>().permuteDims(this, permutation);
    }

    public default ComplexNDArray<T> reshape(int... newShape) {
        return new ViewOperations<Complex,T>().reshape(this, newShape);
    }

    public default ComplexNDArray<T> concatenate(int axis, NDArray<?> ...arrays) {
        return new ViewOperations<Complex,T>().concatenate(this, axis, arrays);
    }

    public default ComplexNDArray<T> selectDims(int... selectedDims) {
        return new ViewOperations<Complex,T>().selectDims(this, selectedDims);
    }

    public default ComplexNDArray<T> dropDims(int... selectedDims) {
        return new ViewOperations<Complex,T>().dropDims(this, selectedDims);
    }

    public default ComplexNDArray<T> squeeze() {
        return new ViewOperations<Complex,T>().squeeze(this);
    }

    public default ComplexNDArray<T> copyFrom(float[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(double[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(byte[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(short[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(int[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(long[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(Object[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(NDArray<?> array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(float[] real, float[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }

    public default ComplexNDArray<T> copyFrom(double[] real, double[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }

    public default ComplexNDArray<T> copyFrom(byte[] real, byte[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }

    public default ComplexNDArray<T> copyFrom(short[] real, short[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }

    public default ComplexNDArray<T> copyFrom(int[] real, int[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }

    public default ComplexNDArray<T> copyFrom(long[] real, long[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }

    public default ComplexNDArray<T> copyFrom(Object[] real, Object[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }

    public default ComplexNDArray<T> copyFrom(NDArray<? extends Number> real, NDArray<? extends Number> imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }
}
