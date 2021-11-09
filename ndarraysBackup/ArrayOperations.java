package io.github.hakkelt.ndarrays;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;

/**
 * Utility class for array operations (e.g. addition, element-wise multiplication, fill with scalar, array concatenation)
 */
public class ArrayOperations<T,T2 extends Number> {
    
    public NDArray<T> sum(AbstractNDArray<T,T2> me, int... selectedDims) {
        Set<Integer> dimSelection = IntStream.of(selectedDims).boxed().collect(Collectors.toSet());
        Object[] expressions = IntStream.range(0, me.ndim())
                .mapToObj(i -> dimSelection.contains(i) ? ":" : 0).toArray();
        int[] remainingDimsIndices = me.calculateRemainingDims(selectedDims);
        int[] remainingDims = IntStream.of(remainingDimsIndices).map(me::shape).toArray();
        return IntStream.range(0, IndexingOperations.length(remainingDims))
            .mapToObj(i -> {
                T value = me.slice(expressions).sum();
                if (i < IndexingOperations.length(remainingDims) - 1)
                    me.incrementSlicingExpression(expressions, 0, remainingDimsIndices);
                return value;
            })
            .collect(me.getCollectorInternal(remainingDims));
    }

    public NDArray<T> add(AbstractNDArray<T,T2> me, Object ...addends) {
        checkDimensionMatchBeforeCombine(me, addends, "add");
        return me.streamLinearIndices().parallel()
            .mapToObj(i -> me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.ADD, addends)).collect(me.getCollectorInternal(me.shape));
    }

    public NDArray<T> addInplace(AbstractNDArray<T,T2> me, Object ...addends) {
        checkDimensionMatchBeforeCombine(me, addends, "addInplace");
        me.streamLinearIndices().parallel()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.ADD, addends), i));
        return me;
    }

    public NDArray<T> subtract(AbstractNDArray<T,T2> me, Object ...substrahends) {
        checkDimensionMatchBeforeCombine(me, substrahends, "substract");
        return me.streamLinearIndices().parallel()
            .mapToObj(i -> me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.SUBTRACT, substrahends)).collect(me.getCollectorInternal(me.shape));
    }

    public NDArray<T> subtractInplace(AbstractNDArray<T,T2> me, Object ...substrahends) {
        checkDimensionMatchBeforeCombine(me, substrahends, "substractInplace");
        me.streamLinearIndices().parallel()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.SUBTRACT, substrahends), i));
        return me;
    }

    public NDArray<T> multiply(AbstractNDArray<T,T2> me, Object... multiplicands) {
        checkDimensionMatchBeforeCombine(me, multiplicands, "multiply");
        return me.streamLinearIndices().parallel()
            .mapToObj(i -> me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.MULTIPLY, multiplicands)).collect(me.getCollectorInternal(me.shape));
    }

    public NDArray<T> multiplyInplace(AbstractNDArray<T,T2> me, Object... multiplicands) {
        checkDimensionMatchBeforeCombine(me, multiplicands, "multiplyInplace");
        me.streamLinearIndices().parallel()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.MULTIPLY, multiplicands), i));
        return me;
    }

    public NDArray<T> divide(AbstractNDArray<T,T2> me, Object... divisors) {
        checkDimensionMatchBeforeCombine(me, divisors, "divide");
        return me.streamLinearIndices().parallel()
            .mapToObj(i -> me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.DIVIDE, divisors)).collect(me.getCollectorInternal(me.shape));
    }

    public NDArray<T> divideInplace(AbstractNDArray<T,T2> me, Object... divisors) {
        checkDimensionMatchBeforeCombine(me, divisors, "divideInplace");
        me.streamLinearIndices().parallel()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.DIVIDE, divisors), i));
        return me;
    }

    public NDArray<T> fill(AbstractNDArray<T,T2> me, T value) {
        me.streamLinearIndices().parallel().forEach(i -> me.set(value, i));
        return me;
    }

    public NDArray<T> fill(AbstractNDArray<T,T2> me, float value) {
        me.streamLinearIndices().parallel().forEach(i -> {
            me.set(value, i);
        });
        return me;
    }

    public NDArray<T> fill(AbstractNDArray<T,T2> me, double value) {
        me.streamLinearIndices().parallel().forEach(i -> me.set(value, i));
        return me;
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> sum(ComplexNDArray<T2> me, int... selectedDims) {
        return (ComplexNDArray<T2>)sum((AbstractNDArray<T,T2>)me, selectedDims);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> add(ComplexNDArray<T2> me, Object ...addends) {
        return (ComplexNDArray<T2>)add((AbstractNDArray<T,T2>)me, addends);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> addInplace(ComplexNDArray<T2> me, Object ...addends) {
        return (ComplexNDArray<T2>)addInplace((AbstractNDArray<T,T2>)me, addends);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> subtract(ComplexNDArray<T2> me, Object ...substrahends) {
        return (ComplexNDArray<T2>)subtract((AbstractNDArray<T,T2>)me, substrahends);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> subtractInplace(ComplexNDArray<T2> me, Object ...substrahends) {
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

    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, T2 value) {
        me.streamLinearIndices().parallel().forEach(i -> me.set(value, i));
        return me;
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, float value) {
        return (ComplexNDArray<T2>)fill((AbstractNDArray<T,T2>)me, value);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, double value) {
        return (ComplexNDArray<T2>)fill((AbstractNDArray<T,T2>)me, value);
    }

    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, Complex value) {
        me.streamLinearIndices().parallel().forEach(i -> me.set(value, i));
        return me;
    }

    @SuppressWarnings("unchecked")
    public NDArray<T> concatenate(AbstractNDArray<T,T2> me, int axis, NDArray<?> ...arrays) {
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
    public ComplexNDArray<T2> concatenate(ComplexNDArray<T2> me, int axis, NDArray<?> ...arrays) {
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
            if (!(item instanceof Integer) && !(item instanceof Float) &&
                    !(item instanceof Double) && !(item instanceof Complex))
                throw new IllegalArgumentException(String.format(
                    Errors.COMBINE_TYPE_MISMATCH_WITH_COMPLEX, funcName, item.getClass()));
        } else {
            if (!(item instanceof Integer) && !(item instanceof Float) && !(item instanceof Double))
                throw new IllegalArgumentException(String.format(
                    Errors.COMBINE_TYPE_MISMATCH, funcName, item.getClass()));
        }
    }

    protected void checkConcatenationDimensions(AbstractNDArray<T,T2> me, int axis, NDArray<?> ...arrays) {
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
