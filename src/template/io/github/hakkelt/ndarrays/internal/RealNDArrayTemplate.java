package io.github.hakkelt.ndarrays.internal;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;

/**
 * General N-dimensional arrays holding real values.
 * 
 * The aim of this package to create an general framework to handle multidimensional data.
 * The reference implementation is based on array of floating point or integer,
 * it is, however, super easy to write a wrapper to any array- or collection-implementation making it possible to combine
 * the convenience of this interface with the advantages of the selected collection.
 * 
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays/internal", newName = "RealNDArray")
public interface RealNDArrayTemplate<T extends Number> extends NDArray<T> {

    @Replace(pattern = "sum", replacements = "prod")
    @Override
    @SuppressWarnings("unchecked")
    public default NDArray<T> sum(int... selectedDims) {
        return SliceOperations.reduceSlices((AbstractNDArray<T,T>)this, (slice, idx) -> slice.sum(), selectedDims);
    }

    @Override
    @SuppressWarnings("unchecked")
    public default NDArray<T> accumulate(BinaryOperator<T> func, int... selectedDims) {
        return SliceOperations.reduceSlices((AbstractNDArray<T,T>)this, (slice, idx) -> slice.accumulate(func), selectedDims);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Patterns({"add", "addends"})
    @Replacements({"addInplace", "addends"})
    @Replacements({"subtract", "substrahends"})
    @Replacements({"subtractInplace", "substrahends"})
    @Replacements({"multiply", "multiplicand"})
    @Replacements({"multiplyInplace", "multiplicand"})
    @Replacements({"divide", "divisor"})
    @Replacements({"divideInplace", "divisor"})
    public default NDArray<T> add(Object... addends) {
        return new ArrayOperations<T,T>().add((AbstractNDArray<T,T>)this, addends);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Replace(pattern = "double", replacements = "T")
    public default NDArray<T> fill(double value) {
        return new ArrayOperations<T,T>().fill((AbstractNDArray<T,T>)this, value);
    }

    @Override
    public default NDArray<T> slice(Object ...slicingExpressions) {
        return new ViewOperations<T,T>().slice(this, slicingExpressions);
    }

    @Override
    public default NDArray<T> mask(NDArray<?> mask) {
        return new ViewOperations<T,T>().mask(this, mask, false);
    }

    @Override
    public default NDArray<T> mask(Predicate<T> func) {
        return new ViewOperations<T,T>().mask(this, func);
    }

    @Override
    public default NDArray<T> maskWithLinearIndices(BiPredicate<T, Integer> func) {
        return new ViewOperations<T,T>().mask(this, func, true);
    }

    @Override
    public default NDArray<T> maskWithCartesianIndices(BiPredicate<T, int[]> func) {
        return new ViewOperations<T,T>().mask(this, func, false);
    }

    @Override
    public default NDArray<T> inverseMask(NDArray<?> mask) {
        return new ViewOperations<T,T>().mask(this, mask, true);
    }

    @Override
    public default NDArray<T> permuteDims(int... permutation) {
        return new ViewOperations<T,T>().permuteDims(this, permutation);
    }

    @Override
    public default NDArray<T> reshape(int... newShape) {
        return new ViewOperations<T,T>().reshape(this, newShape);
    }

    @Override
    @SuppressWarnings("unchecked")
    public default NDArray<T> concatenate(int axis, NDArray<?> ...arrays) {
        return new ArrayOperations<T,T>().concatenate((AbstractNDArray<T,T>)this, axis, arrays);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public default NDArray<T> selectDims(int... selectedDims) {
        return new ViewOperations<T,T>().selectDims((AbstractNDArray<T,T>)this, selectedDims);
    }

    @Override
    @SuppressWarnings("unchecked")
    public default NDArray<T> dropDims(int... selectedDims) {
        return new ViewOperations<T,T>().dropDims((AbstractNDArray<T,T>)this, selectedDims);
    }

    @Override
    @SuppressWarnings("unchecked")
    public default NDArray<T> squeeze() {
        return new ViewOperations<T,T>().squeeze((AbstractNDArray<T,T>)this);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long", "Object"})
    public default NDArray<T> copyFrom(float[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }

    @Override
    public default NDArray<T> map(UnaryOperator<T> func) {
        NDArray<T> newInstance = copy();
        newInstance.apply(func);
        return newInstance;
    }

    @Override
    public default NDArray<T> mapWithLinearIndices(BiFunction<T, Integer, T> func) {
        NDArray<T> newInstance = copy();
        newInstance.applyWithLinearIndices(func);
        return newInstance;
    }

    @Override
    public default NDArray<T> mapWithCartesianIndices(BiFunction<T, int[], T> func) {
        NDArray<T> newInstance = copy();
        newInstance.applyWithCartesianIndices(func);
        return newInstance;
    }

}
