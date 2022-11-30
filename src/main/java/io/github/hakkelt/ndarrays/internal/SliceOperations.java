package io.github.hakkelt.ndarrays.internal;

import java.util.Arrays;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.ndarrays.ComplexNDArray;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.Range;

public class SliceOperations {

    private SliceOperations() {}

    public static <T> NDArray<T> applyOnSlices(NDArray<T> array, BiFunction<NDArray<T>,int[],NDArray<?>> func, int... iterationDims) {
        NormalizedRange[] template = constructSlicingExpressionTemplate(array.shape(), iterationDims);
        NDArray.streamCartesianIndices(getIterationShape(array.shape(), iterationDims)).parallel()
            .forEach(idx -> {
                NormalizedRange[] slicingExpr = constructSlicingExpression(template, idx);
                NDArray<T> slice = array.slice((Object[]) slicingExpr);
                NDArray<?> result = func.apply(slice, idx);
                if (slice != result)
                    slice.copyFrom(result);                
            });
        return array;
    }

    public static <T extends Number> ComplexNDArray<T> applyOnSlices(ComplexNDArray<T> array, BiFunction<ComplexNDArray<T>,int[],NDArray<?>> func, int... iterationDims) {
        NormalizedRange[] template = constructSlicingExpressionTemplate(array.shape(), iterationDims);
        NDArray.streamCartesianIndices(getIterationShape(array.shape(), iterationDims)).parallel()
            .forEach(idx -> {
                NormalizedRange[] slicingExpr = constructSlicingExpression(template, idx);
                ComplexNDArray<T> slice = array.slice((Object[]) slicingExpr);
                NDArray<?> result = func.apply(slice, idx);
                if (slice != result)
                    slice.copyFrom(result);
            });
        return array;
    }

    public static <T> NDArray<T> reduceSlices(NDArray<T> array, BiFunction<NDArray<T>,int[],T> func, int... reducedDims) {
        Set<Integer> reducedDimsSet = Arrays.stream(reducedDims).boxed().collect(Collectors.toSet());
        int[] iterationDims = IntStream.range(0, array.ndim()).filter(d -> !reducedDimsSet.contains(d)).toArray();
        NormalizedRange[] template = constructSlicingExpressionTemplate(array.shape(), iterationDims);
        return ((AbstractNDArray<T,?>) array).createNewNDArrayOfSameTypeAsMe(getIterationShape(array.shape(), iterationDims))
            .fillUsingCartesianIndices(idx -> {
                NormalizedRange[] slicingExpr = constructSlicingExpression(template, idx);
                return func.apply(array.slice((Object[]) slicingExpr), idx);
            });
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> ComplexNDArray<T> reduceComplexSlices(ComplexNDArray<T> array, BiFunction<ComplexNDArray<T>,int[],Complex> func, int... reducedDims) {
        Set<Integer> reducedDimsSet = Arrays.stream(reducedDims).boxed().collect(Collectors.toSet());
        int[] iterationDims = IntStream.range(0, array.ndim()).filter(d -> !reducedDimsSet.contains(d)).toArray();
        NormalizedRange[] template = constructSlicingExpressionTemplate(array.shape(), iterationDims);
        return (ComplexNDArray<T>) ((AbstractNDArray<Complex,T>) array).createNewNDArrayOfSameTypeAsMe(getIterationShape(array.shape(), iterationDims))
            .fillUsingCartesianIndices(idx -> {
                NormalizedRange[] slicingExpr = constructSlicingExpression(template, idx);
                ComplexNDArray<T> slice = array.slice((Object[]) slicingExpr);
                return func.apply(slice, idx);              
            });
    }

    protected static int[] getIterationShape(int[] shape, int[] iterationDims) {
        int[] iterationShape = Arrays.stream(iterationDims).map(i -> mapDimToDimSize(shape, i)).toArray();
        if (!IntStream.range(0, iterationDims.length - 1).allMatch(i -> iterationDims[i] < iterationDims[i + 1]))
            throw new IllegalArgumentException(Errors.ITERATION_DIMENSIONS_MUST_BE_INCREASING);
        return iterationShape;
    }

    protected static NormalizedRange[] constructSlicingExpressionTemplate(int[] shape, int[] iterationDims) {
        return IntStream.range(0, shape.length)
            .mapToObj(i -> Arrays.binarySearch(iterationDims, i) >= 0 ? null : new NormalizedRange(Range.ALL, shape[i]))
            .toArray(NormalizedRange[]::new);
    }

    protected static NormalizedRange[] constructSlicingExpression(NormalizedRange[] template, int[] idx) {
        NormalizedRange[] newExpr = new NormalizedRange[template.length];
        int iterationDimsIndex = 0;
        for (int i = 0; i < template.length; i++)
            newExpr[i] = template[i] != null ? template[i] : new NormalizedRange(idx[iterationDimsIndex++]);
        return newExpr;
    }

    protected static int mapDimToDimSize(int[] shape, int selectedDim) {
        if (selectedDim < 0)
            throw new IllegalArgumentException(String.format(Errors.CANNOT_SELECT_DIM_NEGATIVE, selectedDim));
        if (selectedDim > shape.length)
            throw new IllegalArgumentException(String.format(Errors.CANNOT_SELECT_DIM_OVERFLOW, selectedDim, shape.length));
        return shape[selectedDim];
    }
}
