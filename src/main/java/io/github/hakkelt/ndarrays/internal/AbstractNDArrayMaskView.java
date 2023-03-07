package io.github.hakkelt.ndarrays.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.NDArrayUtils;

public abstract class AbstractNDArrayMaskView<T, T2 extends Number> extends AbstractNDArrayView<T,T2> {

    List<Integer> indexMapper = new ArrayList<>();

    @SuppressWarnings("unchecked")
    protected AbstractNDArrayMaskView(NDArray<T> parent, NDArray<?> mask, boolean isInverse) {
        if (!Arrays.equals(parent.shape(), mask.shape()))
            throw new IllegalArgumentException(String.format(Errors.MASK_DIMENSION_MISMATCH,
                    Printer.dimsToString(parent.shape()), Printer.dimsToString(mask.shape())));
        if (parent instanceof AbstractNDArrayMaskView) {
            this.parent = ((AbstractNDArrayMaskView<T, T2>) parent).parent;
            mask.streamLinearIndices()
                .filter(i -> isInverse ^ !this.parent.wrapValue(mask.get(i)).equals(this.parent.zeroT()))
                .forEach(i -> indexMapper.add(((AbstractNDArrayMaskView<T2, T2>) parent).indexMapper.get(i)));
        } else {
            this.parent = (AbstractNDArray<T, T2>) parent;
            mask.streamLinearIndices()
                .filter(i -> isInverse ^ !this.parent.wrapValue(mask.get(i)).equals(this.parent.zeroT()))
                .forEach(i -> indexMapper.add(i));
        }
        this.dataLength = indexMapper.size();
        this.shape = new int[] { dataLength };
        this.multipliers = NDArrayUtils.calculateMultipliers(this.shape);
    }

    @SuppressWarnings("unchecked")
    protected AbstractNDArrayMaskView(NDArray<T> parent, Predicate<T> func) {
        if (parent instanceof AbstractNDArrayMaskView) {
            this.parent = ((AbstractNDArrayMaskView<T, T2>) parent).parent;
            parent.streamLinearIndices()
                .filter(i -> func.test(parent.get(i)))
                .forEach(i -> indexMapper.add(((AbstractNDArrayMaskView<T, T2>) parent).indexMapper.get(i)));
        } else {
            this.parent = (AbstractNDArray<T, T2>) parent;
            parent.streamLinearIndices()
                .filter(i -> func.test(parent.get(i)))
                .forEach(i -> indexMapper.add(i));
        }
        this.dataLength = indexMapper.size();
        this.shape = new int[] { dataLength };
        this.multipliers = NDArrayUtils.calculateMultipliers(this.shape);
    }

    @SuppressWarnings("unchecked")
    protected AbstractNDArrayMaskView(NDArray<T> parent, BiPredicate<T,?> func, boolean withLinearIndices) {
        if (parent instanceof AbstractNDArrayMaskView) {
            this.parent = ((AbstractNDArrayMaskView<T, T2>) parent).parent;
            if (withLinearIndices)
                parent.streamLinearIndices()
                    .filter(i -> ((BiPredicate<T, Integer>) func).test(parent.get(i), i))
                    .forEach(i -> indexMapper.add(((AbstractNDArrayMaskView<T, T2>) parent).indexMapper.get(i)));
            else
                parent.streamCartesianIndices()
                    .filter(indices -> ((BiPredicate<T, int[]>) func).test(parent.get(indices), indices))
                    .mapToInt(indices -> NDArrayUtils.cartesianIndicesToLinearIndex(indices,
                            ((AbstractNDArray<?, ?>) parent).multipliers))
                    .forEach(i -> indexMapper.add(((AbstractNDArrayMaskView<T, T2>) parent).indexMapper.get(i)));
        } else {
            this.parent = (AbstractNDArray<T, T2>) parent;
            if (withLinearIndices)
                parent.streamLinearIndices()
                    .filter(i -> ((BiPredicate<T, Integer>) func).test(parent.get(i), i))
                    .forEach(i -> indexMapper.add(i));
            else
                parent.streamCartesianIndices()
                    .filter(indices -> ((BiPredicate<T, int[]>) func).test(parent.get(indices), indices))
                    .mapToInt(indices -> NDArrayUtils.cartesianIndicesToLinearIndex(indices,
                            this.parent.multipliers))
                    .forEach(i -> indexMapper.add(i));
        }
        this.dataLength = indexMapper.size();
        this.shape = new int[] { dataLength };
        this.multipliers = NDArrayUtils.calculateMultipliers(this.shape);
    }

    @Override
    public NDArray<T> mapOnSlices(BiFunction<NDArray<T>,int[],NDArray<?>> func, int... iterationDims) {
        return SliceOperations.applyOnSlices(copy(), func, iterationDims);
    }

    @Override
    public NDArray<T> applyOnSlices(BiFunction<NDArray<T>,int[],NDArray<?>> func, int... iterationDims) {
        return SliceOperations.applyOnSlices(this, func, iterationDims);
    }

    @Override
    protected T getUnchecked(int linearIndex) {
        return parent.getUnchecked(indexMapper.get(NDArrayUtils.unwrap(linearIndex, length())));
    }

    @Override
    protected T getUnchecked(int... indices) {
        return parent.getUnchecked(indexMapper.get(NDArrayUtils.unwrap(indices[0], length())));
    }

    @Override
    protected void setUnchecked(T value, int linearIndex) {
        parent.setUnchecked(value, indexMapper.get(NDArrayUtils.unwrap(linearIndex, length())));
    }

    @Override
    protected void setUnchecked(T value, int... indices) {
        parent.setUnchecked(value, indexMapper.get(NDArrayUtils.unwrap(indices[0], length())));
    }

    @Override
    protected String printItem(int linearIndex, String format) {
        NDArrayUtils.boundaryCheck(linearIndex, length());
        return parent.printItem(indexMapper.get(NDArrayUtils.unwrap(linearIndex, length())), format);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractNDArrayMaskView) {
            AbstractNDArrayMaskView<?, ?> other = (AbstractNDArrayMaskView<?, ?>) obj;
            if (other.parent == parent && other.indexMapper.equals(indexMapper))
                return true;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}

