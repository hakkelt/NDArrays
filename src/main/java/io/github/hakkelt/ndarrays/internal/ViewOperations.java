package io.github.hakkelt.ndarrays.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.ndarrays.*;

/**
 * Utility class for operations creating views: slice, permuteDims, reshape, mask, concatenate, selectDims, dropDims, squeeze. 
 */
public class ViewOperations<T,T2 extends Number> {

    public static boolean isThisSlicingAnIdentityOperation(NDArray<?> me, Range[] slicingExpressions) {
        AtomicInteger index = new AtomicInteger(-1);
        return me.ndim() == slicingExpressions.length && Stream.of(slicingExpressions).allMatch(range -> {
            index.incrementAndGet();
            return range == Range.ALL || (range.start() == 0 && range.step() == 1 && range.end() == me.shape(index.get()));
        });
    }

    public static boolean isThisPermutationAnIdentityOperation(int... permutation) {
        return IntStream.range(0, permutation.length).allMatch(i -> i == permutation[i]);
    }

    public static boolean isThisReshapingAnIdentityOperation(int[] shape, int[] newShape) {
        return Arrays.equals(shape, newShape);
    }

    public static void checkSelectedDims(int ndim, int... selectedDims) {
        IntStream.of(selectedDims).forEach(i -> {
            if (i < 0)
                throw new IllegalArgumentException(String.format(Errors.CANNOT_SELECT_DIM_NEGATIVE, i, ndim));
            if (i >= ndim)
                throw new IllegalArgumentException(String.format(Errors.CANNOT_SELECT_DIM_OVERFLOW, i, ndim));
        });
    }

    public static void checkDroppedDims(int ndim, int... droppedDims) {
        IntStream.of(droppedDims).forEach(i -> {
            if (i < 0)
                throw new IllegalArgumentException(String.format(Errors.CANNOT_DROP_DIM_NEGATIVE, i, ndim));
            if (i >= ndim)
                throw new IllegalArgumentException(String.format(Errors.CANNOT_DROP_DIM_OVERFLOW, i, ndim));
        });
    }

    public static int[] getIndicesOfSingletonDims(int... shape) {
        return IntStream.range(0, shape.length).filter(i -> shape[i] != 1).toArray();
    }

    public static Set<Integer> intArrayToSet(int[] array) {
        return IntStream.of(array).boxed().collect(Collectors.toSet());
    }

    public static List<Integer> intArrayToList(int[] array) {
        return IntStream.of(array).boxed().collect(Collectors.toList());
    }

    public static Object[] selectedDimsToSlicingExpression(int[] shape, Set<Integer> selectedDims) {
        return IntStream.range(0, shape.length).mapToObj(i -> {
            if (selectedDims.contains(i)) return ":";
            if (shape[i] != 1) throw new IllegalArgumentException(String.format(Errors.DROPDIMS_NOT_SINGLETON, i));
            return 0;
        }).toArray();
    }

    public NDArray<T2> slice(RealNDArray<T2> me, Object ...slicingExpressions) {
        Range[] expressions = Range.parseExpressions(me.shape(), slicingExpressions);
        if (isThisSlicingAnIdentityOperation(me, expressions)) return me;
        return new RealNDArraySliceView<>(me, expressions);
    }
    
    public ComplexNDArray<T2> slice(ComplexNDArray<T2> me, Object ...slicingExpressions) {
        Range[] expressions = Range.parseExpressions(me.shape(), slicingExpressions);
        if (isThisSlicingAnIdentityOperation(me, expressions)) return me;
        return new ComplexNDArraySliceView<>(me, expressions);
    }

    public NDArray<T2> mask(RealNDArray<T2> me, NDArray<?> mask, boolean isInverse) {
        NDArray<T2> view = new RealNDArrayMaskView<>(me, mask, isInverse);
        return view.length() == me.length() ? me.reshape(me.length()) : view;
    }
    
    public ComplexNDArray<T2> mask(ComplexNDArray<T2> me, NDArray<?> mask, boolean isInverse) {
        ComplexNDArray<T2> view = new ComplexNDArrayMaskView<>(me, mask, isInverse);
        return view.length() == me.length() ? me.reshape(me.length()) : view;
    }

    public NDArray<T2> mask(RealNDArray<T2> me, Predicate<T2> func) {
        NDArray<T2> view = new RealNDArrayMaskView<>(me, func);
        return view.length() == me.length() ? me.reshape(me.length()) : view;
    }
    
    public ComplexNDArray<T2> mask(ComplexNDArray<T2> me, Predicate<Complex> func) {
        ComplexNDArray<T2> view = new ComplexNDArrayMaskView<>(me, func);
        return view.length() == me.length() ? me.reshape(me.length()) : view;
    }

    public NDArray<T2> mask(RealNDArray<T2> me, BiPredicate<T2,?> func, boolean withLinearIndices) {
        NDArray<T2> view = new RealNDArrayMaskView<>(me, func, withLinearIndices);
        return view.length() == me.length() ? me.reshape(me.length()) : view;
    }
    
    public ComplexNDArray<T2> mask(ComplexNDArray<T2> me, BiPredicate<Complex,?> func, boolean withLinearIndices) {
        ComplexNDArray<T2> view = new ComplexNDArrayMaskView<>(me, func, withLinearIndices);
        return view.length() == me.length() ? me.reshape(me.length()) : view;
    }

    public NDArray<T2> permuteDims(RealNDArray<T2> me, int... permutation) {
        if (isThisPermutationAnIdentityOperation(permutation)) return me;
        RealNDArrayPermuteDimsView<T2> view = new RealNDArrayPermuteDimsView<>(me, permutation);
        return IntStream.range(0, me.ndim()).allMatch(i -> i == view.dimsOrder[i]) ? view.parent : view;
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> permuteDims(ComplexNDArray<T2> me, int... permutation) {
        if (isThisPermutationAnIdentityOperation(permutation)) return me;
        ComplexNDArrayPermuteDimsView<T2> view = new ComplexNDArrayPermuteDimsView<>(me, permutation);
        return IntStream.range(0, me.ndim()).allMatch(i -> i == view.dimsOrder[i]) ? (ComplexNDArray<T2>)view.parent : view;
    }

    public NDArray<T2> reshape(RealNDArray<T2> me, int... newShape) {
        if (isThisReshapingAnIdentityOperation(me.shape(), newShape)) return me;
        if (me instanceof RealNDArrayReshapeView &&
                isThisReshapingAnIdentityOperation(newShape, ((RealNDArrayReshapeView<T2>)me).parent.shape()))
            return ((RealNDArrayReshapeView<T2>)me).parent;
        return new RealNDArrayReshapeView<>(me, newShape);
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> reshape(ComplexNDArray<T2> me, int... newShape) {
        if (isThisReshapingAnIdentityOperation(me.shape(), newShape)) return me;
        if (me instanceof ComplexNDArrayReshapeView &&
                isThisReshapingAnIdentityOperation(newShape, ((ComplexNDArrayReshapeView<T2>)me).parent.shape()))
            return (ComplexNDArray<T2>)((ComplexNDArrayReshapeView<T2>)me).parent;
        return new ComplexNDArrayReshapeView<>(me, newShape);
    }
    
    public NDArray<T> selectDims(AbstractNDArray<T,T2> me, int... selectedDims) {
        checkSelectedDims(me.ndim(), selectedDims);
        Set<Integer> set = intArrayToSet(selectedDims);
        if (set.size() == me.ndim()) return me;
        return me.slice(selectedDimsToSlicingExpression(me.shape, set));
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> selectDims(ComplexNDArray<T2> me, int... selectedDims) {
        return (ComplexNDArray<T2>)selectDims((AbstractNDArray<T,T2>)me, selectedDims);
    }

    public NDArray<T> dropDims(AbstractNDArray<T,T2> me, int... droppedDims) {
        checkDroppedDims(me.ndim(), droppedDims);
        List<Integer> set = intArrayToList(droppedDims);
        if (set.isEmpty()) throw new IllegalArgumentException(Errors.ALL_DIMS_DROPPED);
        return me.selectDims(IntStream.range(0, me.ndim()).filter(i -> !set.contains(i)).toArray());
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> dropDims(ComplexNDArray<T2> me, int... droppedDims) {
        return (ComplexNDArray<T2>)dropDims((AbstractNDArray<T,T2>)me, droppedDims);
    }

    public NDArray<T> squeeze(AbstractNDArray<T,T2> me) {
        return me.selectDims(getIndicesOfSingletonDims(me.shape()));
    }

    public ComplexNDArray<T2> squeeze(ComplexNDArray<T2> me) {
        return me.selectDims(getIndicesOfSingletonDims(me.shape()));
    }
    
}
