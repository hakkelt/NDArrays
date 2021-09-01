package io.github.hakkelt.ndarrays;

class RealNDArrayReshapeView<T extends Number> extends AbstractNDArrayReshapeView<T,T> implements InternalRealNDArray<T> {

    public RealNDArrayReshapeView(NDArray<T> parent, int ...newShape) {
        super(parent, newShape);
    }

    public RealNDArrayReshapeView(AbstractNDArraySliceView<T,T> parent, int ...newShape) {
        super(parent, newShape);
    }

    @Override
    public void set(Number value, int linearIndex) {
        parent.set(value, linearIndex);
    }

    @Override
    public void set(Number value, int... indices) {
        set(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
    }

    public NDArray<T> copyFrom(NDArray<?> array) {
        streamLinearIndices().parallel().forEach(i -> set(wrapValue(array.get(i)), i));
        return this;
    }

}
