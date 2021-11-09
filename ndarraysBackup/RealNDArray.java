package io.github.hakkelt.ndarrays;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * General N-dimensional arrays holding real values.
 * 
 * The aim of this package to create an general framework to handle multidimensional data.
 * The reference implementation is based on array of floating point or integer,
 * it is, however, super easy to write a wrapper to any array- or collection-implementation making it possible to combine
 * the convenience of this interface with the advantages of the selected collection.
 * 
 */
public interface RealNDArray<T extends Number> extends NDArray<T> {

    @SuppressWarnings("unchecked")
    public default NDArray<T> sum(int... selectedDims) {
        return new ArrayOperations<T,T>().sum((AbstractNDArray<T,T>)this, selectedDims);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> add(Object ...addends) {
        return new ArrayOperations<T,T>().add((AbstractNDArray<T,T>)this, addends);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> addInplace(Object ...addends) {
        return new ArrayOperations<T,T>().addInplace((AbstractNDArray<T,T>)this, addends);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> subtract(Object ...substrahends) {
        return new ArrayOperations<T,T>().subtract((AbstractNDArray<T,T>)this, substrahends);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> subtractInplace(Object ...substrahends) {
        return new ArrayOperations<T,T>().subtractInplace((AbstractNDArray<T,T>)this, substrahends);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> multiply(Object... multiplicands) {
        return new ArrayOperations<T,T>().multiply((AbstractNDArray<T,T>)this, multiplicands);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> multiplyInplace(Object... multiplicands) {
        return new ArrayOperations<T,T>().multiplyInplace((AbstractNDArray<T,T>)this, multiplicands);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> divide(Object... divisors) {
        return new ArrayOperations<T,T>().divide((AbstractNDArray<T,T>)this, divisors);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> divideInplace(Object... divisors) {
        return new ArrayOperations<T,T>().divideInplace((AbstractNDArray<T,T>)this, divisors);
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<T> fill(T value) {
        return new ArrayOperations<T,T>().fill((AbstractNDArray<T,T>)this, value);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> fill(float value) {
        return new ArrayOperations<T,T>().fill((AbstractNDArray<T,T>)this, value);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> fill(double value) {
        return new ArrayOperations<T,T>().fill((AbstractNDArray<T,T>)this, value);
    }

    public default NDArray<T> slice(Object ...slicingExpressions) {
        return new ViewOperations<T,T>().slice(this, slicingExpressions);
    }

    public default NDArray<T> mask(NDArray<?> mask) {
        return new ViewOperations<T,T>().mask(this, mask, false);
    }

    public default NDArray<T> mask(Predicate<T> func) {
        return new ViewOperations<T,T>().mask(this, func);
    }

    public default NDArray<T> maskWithLinearIndices(BiPredicate<T, Integer> func) {
        return new ViewOperations<T,T>().mask(this, func, true);
    }

    public default NDArray<T> maskWithCartesianIndices(BiPredicate<T, int[]> func) {
        return new ViewOperations<T,T>().mask(this, func, false);
    }

    public default NDArray<T> inverseMask(NDArray<?> mask) {
        return new ViewOperations<T,T>().mask(this, mask, true);
    }

    public default NDArray<T> permuteDims(int... permutation) {
        return new ViewOperations<T,T>().permuteDims(this, permutation);
    }

    public default NDArray<T> reshape(int... newShape) {
        return new ViewOperations<T,T>().reshape(this, newShape);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> concatenate(int axis, NDArray<?> ...arrays) {
        return new ArrayOperations<T,T>().concatenate((AbstractNDArray<T,T>)this, axis, arrays);
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<T> selectDims(int... selectedDims) {
        return new ViewOperations<T,T>().selectDims((AbstractNDArray<T,T>)this, selectedDims);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> dropDims(int... selectedDims) {
        return new ViewOperations<T,T>().dropDims((AbstractNDArray<T,T>)this, selectedDims);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> squeeze() {
        return new ViewOperations<T,T>().squeeze((AbstractNDArray<T,T>)this);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(float[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(double[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(byte[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(short[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(int[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(long[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }

    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(Object[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }

    public default NDArray<T> apply(UnaryOperator<T> func) {
        streamLinearIndices().parallel().forEach(linearIndex -> set(func.apply(get(linearIndex)), linearIndex));
        return this;
    }

    public default NDArray<T> applyWithLinearIndices(BiFunction<T, Integer, T> func) {
        streamLinearIndices().parallel().forEach(linearIndex -> set(func.apply(get(linearIndex), linearIndex), linearIndex));
        return this;
    }

    public default NDArray<T> applyWithCartesianIndices(BiFunction<T, int[], T> func) {
        streamCartesianIndices().parallel().forEach(indices -> set(func.apply(get(indices), indices), indices));
        return this;
    }

    public default NDArray<T> map(UnaryOperator<T> func) {
        NDArray<T> newInstance = copy();
        newInstance.apply(func);
        return newInstance;
    }

    public default NDArray<T> mapWithLinearIndices(BiFunction<T, Integer, T> func) {
        NDArray<T> newInstance = copy();
        newInstance.applyWithLinearIndices(func);
        return newInstance;
    }

    public default NDArray<T> mapWithCartesianIndices(BiFunction<T, int[], T> func) {
        NDArray<T> newInstance = copy();
        newInstance.applyWithCartesianIndices(func);
        return newInstance;
    }

}
