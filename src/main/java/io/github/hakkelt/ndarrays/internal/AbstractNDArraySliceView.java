package io.github.hakkelt.ndarrays.internal;

import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.NDArrayUtils;
import io.github.hakkelt.ndarrays.Range;

abstract class AbstractNDArraySliceView<T, T2 extends Number> extends AbstractNDArrayView<T,T2> {

    protected SlicingExpression slicingExpression;

    @SuppressWarnings("unchecked")
    protected AbstractNDArraySliceView(NDArray<T> parent, Range[] slicingExpressions) {
        if (parent instanceof AbstractNDArraySliceView) {
            this.parent = ((AbstractNDArraySliceView<T,T2>)parent).parent;
            this.slicingExpression = new SlicingExpression(((AbstractNDArraySliceView<T,T2>)parent).slicingExpression, slicingExpressions);
        } else {
            this.parent = (AbstractNDArray<T,T2>)parent;
            this.slicingExpression = new SlicingExpression(parent.shape(), slicingExpressions);
        }
        this.shape = slicingExpression.resolveViewDims();
        this.multipliers = NDArrayUtils.calculateMultipliers(this.shape);
        this.dataLength = NDArrayUtils.calculateLength(this.shape);
    }

    @Override
    protected T getUnchecked(int linearIndex) {
        return getUnchecked(linearIndexToViewIndices(linearIndex));
    }

    @Override
    protected T getUnchecked(int... indices) {
        return parent.getUnchecked(slicingExpression.resolveToParentIndices(indices));
    }

    @Override
    protected void setUnchecked(T value, int linearIndex) {
        setUnchecked(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    protected void setUnchecked(T value, int... indices) {
        parent.setUnchecked(value, slicingExpression.resolveToParentIndices(indices));
    }

    @Override
    protected String printItem(int linearIndex, String format) {
        int[] parentIndices = slicingExpression.resolveToParentIndices(linearIndexToViewIndices(linearIndex));
        int parentIndex = NDArrayUtils.cartesianIndicesToLinearIndex(parentIndices, parent.multipliers);
        return parent.printItem(parentIndex, format);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractNDArraySliceView) {
            AbstractNDArraySliceView<?, ?> other = (AbstractNDArraySliceView<?, ?>) obj;
            if (other.parent == parent && slicingExpression.equals(other.slicingExpression))
                return true;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}

