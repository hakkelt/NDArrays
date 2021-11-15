/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\internal\RealNDArrayMaskView.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.internal;

import io.github.hakkelt.ndarrays.*;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * A view for an NDArray holding real values that selects values based on a specified mask.
 * When mask(...) is called for a real NDArray, an instance of this class is returned.
 */
public class RealNDArrayMaskView<T extends Number> extends AbstractNDArrayMaskView<T,T> implements RealNDArray<T> {

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
    public NDArray<T> copyFrom(NDArray<?> array) {
        return new CopyFromOperations<T,T>().copyFrom(this, array);
    }

    @Override
    public void set(Number value, int linearIndex) {
        NDArrayUtils.boundaryCheck(linearIndex, length());
        setUnchecked(wrapValue(value), NDArrayUtils.unwrap(linearIndex, length()));
    }

    @Override
    public void set(Number value, int... indices) {
        NDArrayUtils.boundaryCheck(indices, shape());
        setUnchecked(wrapValue(value), NDArrayUtils.unwrap(indices, shape()));
    }

    @Override
    protected T getRealUnchecked(int linearIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected T getRealUnchecked(int... indices) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected T getImagUnchecked(int linearIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected T getImagUnchecked(int... indices) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setRealUnchecked(T value, int linearIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setRealUnchecked(T value, int... indices) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setImagUnchecked(T value, int linearIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void setImagUnchecked(T value, int... indices) {
        throw new UnsupportedOperationException();
    }

}
