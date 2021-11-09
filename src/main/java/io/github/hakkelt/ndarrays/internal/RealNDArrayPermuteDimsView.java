/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\internal\RealNDArrayPermuteDimsView.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:52 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.internal;

import io.github.hakkelt.ndarrays.*;

/**
 * A view for an NDArray holding real values that permutes the order of dimensions.
 * When permuteDims(...) is called for a real NDArray, an instance of this class is returned.
 */
public class RealNDArrayPermuteDimsView<T extends Number> extends AbstractNDArrayPermuteDimsView<T,T> implements RealNDArray<T> {

    public RealNDArrayPermuteDimsView(NDArray<T> parent, int... dimsOrder) {
        super(parent, dimsOrder);
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
