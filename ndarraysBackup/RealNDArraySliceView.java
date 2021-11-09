package io.github.hakkelt.ndarrays;

/**
 * A view for an NDArray holding real values that slices the parent NDArray.
 * When slice(...) is called for a real NDArray, an instance of this class is returned.
 */
public class RealNDArraySliceView<T extends Number> extends AbstractNDArraySliceView<T,T> implements RealNDArray<T> {

    public RealNDArraySliceView(NDArray<T> parent, Object ...slicingExpressions) {
        super(parent, slicingExpressions);
    }

    public NDArray<T> copyFrom(NDArray<?> array) {
        streamLinearIndices().parallel().forEach(i -> set(wrapValue(array.get(i)), i));
        return this;
    }

    @Override
    public void set(Number value, int linearIndex) {
        set(wrapValue(value), linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        set(wrapValue(value), indices);
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
