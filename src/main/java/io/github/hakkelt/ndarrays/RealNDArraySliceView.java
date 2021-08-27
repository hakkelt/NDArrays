package io.github.hakkelt.ndarrays;

class RealNDArraySliceView<T extends Number> extends AbstractNDArraySliceView<T,T> implements InternalRealNDArray<T> {

    public RealNDArraySliceView(NDArray<T> parent, Object ...slicingExpressions) {
        super(parent, slicingExpressions);
    }


    public RealNDArraySliceView(AbstractNDArraySliceView<T,T> parent, Object ...slicingExpressions) {
        super(parent, slicingExpressions);
    }

    
    @Override
    public void set(Number value, int linearIndex) {
        set(value, linearIndexToViewIndices(linearIndex));
    }


    @Override
    public void set(Number value, int... indices) {
        parent.set(value, slicingExpression.resolveToParentIndices(indices));
    }


    public NDArray<T> copyFrom(NDArray<?> array) {
        streamLinearIndices().parallel().forEach(i -> set(wrapValue(array.get(i)), i));
        return this;
    }

}
