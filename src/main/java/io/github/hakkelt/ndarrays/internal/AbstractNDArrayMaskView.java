package io.github.hakkelt.ndarrays.internal;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.NDArrayUtils;

public abstract class AbstractNDArrayMaskView<T, T2 extends Number> extends AbstractNDArrayView<T,T2> {

    List<Integer> indexMapper;

    @SuppressWarnings("unchecked")
    protected AbstractNDArrayMaskView(NDArray<T> parent, NDArray<?> mask, boolean isInverse) {
        if (!Arrays.equals(parent.shape(), mask.shape()))
            throw new IllegalArgumentException(String.format(Errors.MASK_DIMENSION_MISMATCH,
                    Printer.dimsToString(parent.shape()), Printer.dimsToString(mask.shape())));
        if (parent instanceof AbstractNDArrayMaskView) {
            this.parent = ((AbstractNDArrayMaskView<T, T2>) parent).parent;
            indexMapper = mask.streamLinearIndices()
                .filter(i -> isInverse ^ !this.parent.wrapValue(mask.get(i)).equals(this.parent.zeroT()))
                .mapToObj(i -> ((AbstractNDArrayMaskView<T2, T2>) parent).indexMapper.get(i))
                .collect(Collectors.toList());
        } else {
            this.parent = (AbstractNDArray<T, T2>) parent;
            indexMapper = mask.streamLinearIndices()
                .filter(i -> isInverse ^ !this.parent.wrapValue(mask.get(i)).equals(this.parent.zeroT()))
                .boxed()
                .collect(Collectors.toList());
        }
        this.dataLength = indexMapper.size();
        this.shape = new int[] { dataLength };
        this.multipliers = NDArrayUtils.calculateMultipliers(this.shape);
    }

    @SuppressWarnings("unchecked")
    protected AbstractNDArrayMaskView(NDArray<T> parent, Predicate<T> func) {
        if (parent instanceof AbstractNDArrayMaskView) {
            this.parent = ((AbstractNDArrayMaskView<T, T2>) parent).parent;
            indexMapper = parent.streamLinearIndices()
                .filter(i -> func.test(parent.get(i)))
                .mapToObj(i -> ((AbstractNDArrayMaskView<T, T2>) parent).indexMapper.get(i))
                .collect(Collectors.toList());
        } else {
            this.parent = (AbstractNDArray<T, T2>) parent;
            indexMapper = parent.streamLinearIndices()
                .filter(i -> func.test(parent.get(i)))
                .boxed()
                .collect(Collectors.toList());
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
                indexMapper = parent.streamLinearIndices()
                    .filter(i -> ((BiPredicate<T, Integer>) func).test(parent.get(i), i))
                    .mapToObj(i -> ((AbstractNDArrayMaskView<T, T2>) parent).indexMapper.get(i))
                    .collect(Collectors.toList());
            else
                indexMapper = parent.streamCartesianIndices()
                    .filter(indices -> ((BiPredicate<T, int[]>) func).test(parent.get(indices), indices))
                    .mapToInt(indices -> NDArrayUtils.cartesianIndicesToLinearIndex(indices,
                            ((AbstractNDArray<?, ?>) parent).multipliers))
                    .mapToObj(i -> ((AbstractNDArrayMaskView<T, T2>) parent).indexMapper.get(i))
                    .collect(Collectors.toList());
        } else {
            this.parent = (AbstractNDArray<T, T2>) parent;
            if (withLinearIndices)
                indexMapper = parent.streamLinearIndices()
                    .filter(i -> ((BiPredicate<T, Integer>) func).test(parent.get(i), i))
                    .boxed()
                    .collect(Collectors.toList());
            else
                indexMapper = parent.streamCartesianIndices()
                    .filter(indices -> ((BiPredicate<T, int[]>) func).test(parent.get(indices), indices))
                    .map(indices -> NDArrayUtils.cartesianIndicesToLinearIndex(indices,
                            this.parent.multipliers))
                    .collect(Collectors.toList());
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

