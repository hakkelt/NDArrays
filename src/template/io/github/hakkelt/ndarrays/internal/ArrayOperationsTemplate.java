package io.github.hakkelt.ndarrays.internal;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;

/**
 * Utility class for array operations (e.g. addition, element-wise multiplication, fill with scalar, array concatenation)
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays/internal", newName = "ArrayOperations")
public class ArrayOperationsTemplate<T,T2 extends Number> {
    
    public NDArray<T> sum(AbstractNDArray<T,T2> me, int... selectedDims) {
        Set<Integer> dimSelection = IntStream.of(selectedDims).boxed().collect(Collectors.toSet());
        Object[] expressions = IntStream.range(0, me.ndim())
                .mapToObj(i -> dimSelection.contains(i) ? ":" : 0).toArray();
        int[] remainingDimsIndices = me.calculateRemainingDims(selectedDims);
        int[] remainingDims = IntStream.of(remainingDimsIndices).map(me::shape).toArray();
        return IntStream.range(0, NDArrayUtils.calculateLength(remainingDims))
            .mapToObj(i -> {
                T value = me.slice(expressions).sum();
                if (i < NDArrayUtils.calculateLength(remainingDims) - 1)
                    me.incrementSlicingExpression(expressions, 0, remainingDimsIndices);
                return value;
            })
            .collect(me.getCollectorInternal(remainingDims));
    }

    @Patterns({"add", "addends", "ADD"})
    @Replacements({"subtract", "substrahends", "SUBTRACT"})
    @Replacements({"multiply", "multiplicands", "MULTIPLY"})
    @Replacements({"divide", "divisors", "DIVIDE"})
    public NDArray<T> add(AbstractNDArray<T,T2> me, Object... addends) {
        checkDimensionMatchBeforeCombine(me, addends, "add");
        return me.streamLinearIndices()
            .mapToObj(i -> me.accumulateAtIndex(i, AccumulateOperators.ADD, addends))
            .collect(me.getCollectorInternal(me.shape));
    }

    @Patterns({"addInplace", "addends", "ADD"})
    @Replacements({"subtractInplace", "substrahends", "SUBTRACT"})
    @Replacements({"multiplyInplace", "multiplicands", "MULTIPLY"})
    @Replacements({"divideInplace", "divisors", "DIVIDE"})
    public NDArray<T> addInplace(AbstractNDArray<T,T2> me, Object... addends) {
        checkDimensionMatchBeforeCombine(me, addends, "addInplace");
        me.streamLinearIndices()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AccumulateOperators.ADD, addends), i));
        return me;
    }

    @Replace(pattern = "double", replacements = "T")
    public NDArray<T> fill(AbstractNDArray<T,T2> me, double value) {
        me.streamLinearIndices().forEach(i -> me.set(value, i));
        return me;
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> sum(ComplexNDArray<T2> me, int... selectedDims) {
        return (ComplexNDArray<T2>)sum((AbstractNDArray<T,T2>)me, selectedDims);
    }

    @SuppressWarnings("unchecked")
    @Patterns({"add", "addends", "ADD"})
    @Replacements({"addInplace", "addends", "ADD"})
    @Replacements({"subtract", "substrahends", "SUBTRACT"})
    @Replacements({"subtractInplace", "substrahends", "SUBTRACT"})
    @Replacements({"multiply", "multiplicands", "MULTIPLY"})
    @Replacements({"multiplyInplace", "multiplicands", "MULTIPLY"})
    @Replacements({"divide", "divisors", "DIVIDE"})
    @Replacements({"divideInplace", "divisors", "DIVIDE"})
    public ComplexNDArray<T2> add(ComplexNDArray<T2> me, Object... addends) {
        return (ComplexNDArray<T2>)add((AbstractNDArray<T,T2>)me, addends);
    }

    @Replace(pattern = "double", replacements = {"T2", "Complex"})
    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, double value) {
        me.streamLinearIndices().forEach(i -> me.set(value, i));
        return me;
    }

    @SuppressWarnings("unchecked")
    public NDArray<T> concatenate(AbstractNDArray<T,T2> me, int axis, NDArray<?>... arrays) {
        checkConcatenationDimensions(me, axis, arrays);
        int[] newDims = me.shape.clone();
        for (NDArray<?> array : arrays)
            newDims[axis] += array.shape(axis);
        AbstractNDArray<T,T2> newArray = (AbstractNDArray<T,T2>)me.createNewNDArrayOfSameTypeAsMe(newDims);
        Object[] slicingExpressions = new Object[me.ndim()];
        for (int i = 0; i < me.ndim(); i++) slicingExpressions[i] = ":";
        int start = 0;
        int end = me.shape(axis);
        slicingExpressions[axis] = start + ":" + end;
        NDArray<T> slice = newArray.slice(slicingExpressions);
        slice.copyFrom(me);
        for (NDArray<?> array : arrays) {
            start = end;
            end += array.shape(axis);
            slicingExpressions[axis] = start + ":" + end;
            slice = newArray.slice(slicingExpressions);
            slice.copyFrom(array);
        }
        return newArray;
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> concatenate(ComplexNDArray<T2> me, int axis, NDArray<?>... arrays) {
        return (ComplexNDArray<T2>)concatenate((AbstractNDArray<T,T2>)me, axis, arrays);
    }

    protected void checkDimensionMatchBeforeCombine(AbstractNDArray<T, T2> me, Object[] arrays, String funcName) {
        for (Object array : arrays) {
            if (array instanceof NDArray<?>) {
                if (!Arrays.equals(me.shape, ((NDArray<?>)array).shape()))
                    throw new IllegalArgumentException(String.format(
                        Errors.COMBINE_SHAPE_MISMATCH,
                        Printer.dimsToString(((NDArray<?>)array).shape()),
                        Printer.dimsToString(me.shape)));
            } else {
                checkIfNumber(me, array, funcName);
            }
        }
    }
    
    protected void checkIfNumber(AbstractNDArray<T,T2> me, Object item, String funcName) {
        if (me.dtype() == Complex.class) {
            if (!(item instanceof Number) && !(item instanceof Complex))
                throw new IllegalArgumentException(String.format(
                    Errors.COMBINE_TYPE_MISMATCH_WITH_COMPLEX, funcName, item.getClass()));
        } else {
            if (!(item instanceof Number) && !(item instanceof Double))
                throw new IllegalArgumentException(String.format(
                    Errors.COMBINE_TYPE_MISMATCH, funcName, item.getClass()));
        }
    }

    protected void checkConcatenationDimensions(AbstractNDArray<T,T2> me, int axis, NDArray<?>... arrays) {
        for (NDArray<?> array : arrays) {
            if (array.ndim() != me.ndim())
                throw new IllegalArgumentException(
                    String.format(Errors.CONCATENATION_SHAPE_MISMATCH,
                    Printer.dimsToString(array.shape()), Printer.dimsToString(me.shape), axis));
            for (int i = 0; i < me.ndim(); i++)
                if (i != axis && array.shape(i) != me.shape(i))
                    throw new IllegalArgumentException(
                        String.format(Errors.CONCATENATION_SHAPE_MISMATCH,
                        Printer.dimsToString(array.shape()), Printer.dimsToString(me.shape), axis));
        }
    }
    
}
