/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\internal\ArrayOperations.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.internal;

import io.github.hakkelt.ndarrays.*;

import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;

/**
 * Utility class for array operations (e.g. addition, element-wise multiplication, fill with scalar, array concatenation)
 */
public class ArrayOperations<T, T2 extends Number> {

    public NDArray<T> add(AbstractNDArray<T,T2> me, Object... addends) {
        checkDimensionMatchBeforeCombine(me, addends, "add");
        return me.similar().fillUsingLinearIndices(i -> me.accumulateAtIndex(i, AccumulateOperators.ADD, addends));
    }

    public NDArray<T> subtract(AbstractNDArray<T,T2> me, Object... substrahends) {
        checkDimensionMatchBeforeCombine(me, substrahends, "subtract");
        return me.similar().fillUsingLinearIndices(i -> me.accumulateAtIndex(i, AccumulateOperators.SUBTRACT, substrahends));
    }

    public NDArray<T> multiply(AbstractNDArray<T,T2> me, Object... multiplicands) {
        checkDimensionMatchBeforeCombine(me, multiplicands, "multiply");
        return me.similar().fillUsingLinearIndices(i -> me.accumulateAtIndex(i, AccumulateOperators.MULTIPLY, multiplicands));
    }

    public NDArray<T> divide(AbstractNDArray<T,T2> me, Object... divisors) {
        checkDimensionMatchBeforeCombine(me, divisors, "divide");
        return me.similar().fillUsingLinearIndices(i -> me.accumulateAtIndex(i, AccumulateOperators.DIVIDE, divisors));
    }

    public NDArray<T> addInplace(AbstractNDArray<T,T2> me, Object... addends) {
        checkDimensionMatchBeforeCombine(me, addends, "addInplace");
        me.streamLinearIndices()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AccumulateOperators.ADD, addends), i));
        return me;
    }

    public NDArray<T> subtractInplace(AbstractNDArray<T,T2> me, Object... substrahends) {
        checkDimensionMatchBeforeCombine(me, substrahends, "subtractInplace");
        me.streamLinearIndices()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AccumulateOperators.SUBTRACT, substrahends), i));
        return me;
    }

    public NDArray<T> multiplyInplace(AbstractNDArray<T,T2> me, Object... multiplicands) {
        checkDimensionMatchBeforeCombine(me, multiplicands, "multiplyInplace");
        me.streamLinearIndices()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AccumulateOperators.MULTIPLY, multiplicands), i));
        return me;
    }

    public NDArray<T> divideInplace(AbstractNDArray<T,T2> me, Object... divisors) {
        checkDimensionMatchBeforeCombine(me, divisors, "divideInplace");
        me.streamLinearIndices()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AccumulateOperators.DIVIDE, divisors), i));
        return me;
    }

    public NDArray<T> fill(AbstractNDArray<T,T2> me, double value) {
        me.streamLinearIndices().forEach(i -> me.set(value, i));
        return me;
    }

    public NDArray<T> fill(AbstractNDArray<T,T2> me, T value) {
        me.streamLinearIndices().forEach(i -> me.set(value, i));
        return me;
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> add(ComplexNDArray<T2> me, Object... addends) {
        return (ComplexNDArray<T2>)add((AbstractNDArray<T,T2>)me, addends);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> addInplace(ComplexNDArray<T2> me, Object... addends) {
        return (ComplexNDArray<T2>)addInplace((AbstractNDArray<T,T2>)me, addends);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> subtract(ComplexNDArray<T2> me, Object... substrahends) {
        return (ComplexNDArray<T2>)subtract((AbstractNDArray<T,T2>)me, substrahends);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> subtractInplace(ComplexNDArray<T2> me, Object... substrahends) {
        return (ComplexNDArray<T2>)subtractInplace((AbstractNDArray<T,T2>)me, substrahends);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> multiply(ComplexNDArray<T2> me, Object... multiplicands) {
        return (ComplexNDArray<T2>)multiply((AbstractNDArray<T,T2>)me, multiplicands);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> multiplyInplace(ComplexNDArray<T2> me, Object... multiplicands) {
        return (ComplexNDArray<T2>)multiplyInplace((AbstractNDArray<T,T2>)me, multiplicands);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> divide(ComplexNDArray<T2> me, Object... divisors) {
        return (ComplexNDArray<T2>)divide((AbstractNDArray<T,T2>)me, divisors);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> divideInplace(ComplexNDArray<T2> me, Object... divisors) {
        return (ComplexNDArray<T2>)divideInplace((AbstractNDArray<T,T2>)me, divisors);
    }

    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, double value) {
        me.streamLinearIndices().forEach(i -> me.set(value, i));
        return me;
    }

    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, T2 value) {
        me.streamLinearIndices().forEach(i -> me.set(value, i));
        return me;
    }

    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, Complex value) {
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

    protected void checkDimensionMatchBeforeCombine(AbstractNDArray<T,T2> me, Object[] arrays, String funcName) {
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
