package io.github.hakkelt.ndarrays;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

class RealNDArrayMaskView<T extends Number> extends AbstractNDArrayMaskView<T,T> implements InternalRealNDArray<T> {
    
    @SuppressWarnings("unchecked")
    public RealNDArrayMaskView(NDArray<T> parent, NDArray<?> mask, boolean isInverse) {
        super(parent instanceof AbstractNDArrayMaskView ? (AbstractNDArrayMaskView<T,T>)parent : parent, mask, isInverse);
    }
    
    @SuppressWarnings("unchecked")
    public RealNDArrayMaskView(NDArray<T> parent, Predicate<T> func) {
        super(parent instanceof AbstractNDArrayMaskView ? (AbstractNDArrayMaskView<T,T>)parent : parent, func);
    }
    
    @SuppressWarnings("unchecked")
    public RealNDArrayMaskView(NDArray<T> parent, BiPredicate<T,?> func, boolean withLinearIndices) {
        super(parent instanceof AbstractNDArrayMaskView ? (AbstractNDArrayMaskView<T,T>)parent : parent, func, withLinearIndices);
    }

    @Override
    public void set(Number value, int linearIndex) {
        linearIndex = IndexingOperations.boundaryCheck(linearIndex, length());
        parent.set(value, indexMapper.get(linearIndex));
    }

    @Override
    public void set(Number value, int... indices) {
        indices = IndexingOperations.boundaryCheck(indices, dims());
        parent.set(value, indexMapper.get(indices[0]));
    }

    public NDArray<T> copyFrom(NDArray<?> array) {
        streamLinearIndices().parallel().forEach(i -> set(wrapValue(array.get(i)), i));
        return this;
    }

}
