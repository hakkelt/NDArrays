package io.github.hakkelt.ndarrays.internal;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import io.github.hakkelt.ndarrays.ComplexNDArray;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.Range;

public class ApplyOnSlices {

    private ApplyOnSlices() {}

    public static <T> NDArray<T> applyOnSlices(NDArray<T> array, BiConsumer<NDArray<T>,int[]> func, int... iterationDims) {
        NormalizedRange[] template = constructSlicingExpressionTemplate(array.shape(), iterationDims);
        NDArray.streamCartesianIndices(getIterationShape(array.shape(), iterationDims)).parallel()
            .forEach(idx -> {
                NormalizedRange[] slicingExpr = constructSlicingExpression(template, idx);
                NDArray<T> slice = array.slice((Object[]) slicingExpr);
                func.accept(slice, idx);            
            });
        return array;
    }

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

    public static <T extends Number> ComplexNDArray<T> applyOnSlices(ComplexNDArray<T> array, BiConsumer<ComplexNDArray<T>,int[]> func, int... iterationDims) {
        NormalizedRange[] template = constructSlicingExpressionTemplate(array.shape(), iterationDims);
        NDArray.streamCartesianIndices(getIterationShape(array.shape(), iterationDims)).parallel()
            .forEach(idx -> {
                NormalizedRange[] slicingExpr = constructSlicingExpression(template, idx);
                ComplexNDArray<T> slice = array.slice((Object[]) slicingExpr);
                func.accept(slice, idx);            
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
