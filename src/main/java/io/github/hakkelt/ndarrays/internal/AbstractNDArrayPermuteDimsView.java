package io.github.hakkelt.ndarrays.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.NDArrayUtils;

abstract class AbstractNDArrayPermuteDimsView<T, T2 extends Number> extends AbstractNDArrayView<T,T2> {

    protected int[] dimsOrder;
    protected int[] inverseDimsOrder;

    public int[] getDimsOrder() {
        return dimsOrder;
    }

    @SuppressWarnings("unchecked")
    protected AbstractNDArrayPermuteDimsView(NDArray<T> parent, int... dimsOrder) {
        checkDimsOrder(dimsOrder, parent.shape());
        if (parent instanceof AbstractNDArrayPermuteDimsView) {
            this.parent = ((AbstractNDArrayPermuteDimsView<T, T2>) parent).parent;
            this.dimsOrder = permuteArray(dimsOrder, ((AbstractNDArrayPermuteDimsView<T, T2>) parent).dimsOrder);
        } else {
            this.parent = (AbstractNDArray<T, T2>) parent;
            this.dimsOrder = dimsOrder;
        }
        this.inverseDimsOrder = new int[dimsOrder.length];
        for (int i = 0; i < dimsOrder.length; i++)
            this.inverseDimsOrder[dimsOrder[i]] = i;
        this.dataLength = parent.length();
        this.shape = permuteArray(this.parent.shape, this.dimsOrder);
        this.multipliers = NDArrayUtils.calculateMultipliers(this.shape);
    }

    @Override
    public NDArray<T> mapOnSlices(BiConsumer<NDArray<T>,int[]> func, int... iterationDims) {
        return ApplyOnSlices.applyOnSlices(copy(), func, iterationDims);
    }

    @Override
    public NDArray<T> mapOnSlices(BiFunction<NDArray<T>,int[],NDArray<?>> func, int... iterationDims) {
        return ApplyOnSlices.applyOnSlices(copy(), func, iterationDims);
    }

    @Override
    public NDArray<T> applyOnSlices(BiConsumer<NDArray<T>,int[]> func, int... iterationDims) {
        return ApplyOnSlices.applyOnSlices(this, func, iterationDims);
    }

    @Override
    public NDArray<T> applyOnSlices(BiFunction<NDArray<T>,int[],NDArray<?>> func, int... iterationDims) {
        return ApplyOnSlices.applyOnSlices(this, func, iterationDims);
    }

    @Override
    protected T getUnchecked(int linearIndex) {
        return getUnchecked(linearIndexToViewIndices(linearIndex));
    }

    @Override
    protected T getUnchecked(int... indices) {
        NDArrayUtils.boundaryCheck(indices, shape);
        return parent.getUnchecked(permuteArray(indices, inverseDimsOrder));
    }

    @Override
    protected void setUnchecked(T value, int linearIndex) {
        setUnchecked(value, linearIndexToViewIndices(linearIndex));
        
    }

    @Override
    protected void setUnchecked(T value, int... indices) {
        NDArrayUtils.boundaryCheck(indices, shape);
        parent.setUnchecked(value, permuteArray(indices, inverseDimsOrder));
    }

    @Override
    protected String printItem(int index, String format) {
        int[] viewIndices = linearIndexToViewIndices(index);
        int parentLinearIndex = NDArrayUtils.cartesianIndicesToLinearIndex(permuteArray(viewIndices, inverseDimsOrder),
                parent.multipliers);
        return parent.printItem(parentLinearIndex, format);
    }

    protected static int[] permuteArray(int[] array, int[] permutator) {
        return IntStream.of(permutator).map(d -> array[d]).toArray();
    }

    protected static void checkDimsOrder(int[] dimsOrder, int[] shape) {
        if (dimsOrder.length != shape.length)
            throw new IllegalArgumentException(String.format(Errors.PERMUTATOR_SHAPE_MISMATCH,
                    Printer.printVector(dimsOrder), Printer.dimsToString(shape)));
        List<Integer> dimsOrderSet = IntStream.of(dimsOrder).boxed().collect(Collectors.toList());
        if (IntStream.of(dimsOrder)
                .anyMatch(num -> num < 0 || num >= shape.length || Collections.frequency(dimsOrderSet, num) > 1))
            throw new IllegalArgumentException(String.format(Errors.INVALID_PERMUTATOR, Printer.printVector(dimsOrder),
                    Printer.dimsToString(shape)));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractNDArrayPermuteDimsView) {
            AbstractNDArrayPermuteDimsView<?, ?> other = (AbstractNDArrayPermuteDimsView<?, ?>) obj;
            if (other.parent == parent && Arrays.equals(other.dimsOrder, dimsOrder))
                return true;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}

