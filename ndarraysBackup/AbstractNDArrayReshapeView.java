package io.github.hakkelt.ndarrays;

abstract class AbstractNDArrayReshapeView<T, T2 extends Number> extends AbstractNDArrayView<T, T2> {

    @SuppressWarnings("unchecked")
    protected AbstractNDArrayReshapeView(NDArray<T> parent, int... newShape) {
        if (parent instanceof AbstractNDArrayReshapeView)
            this.parent = ((AbstractNDArrayReshapeView<T, T2>) parent).parent;
        else
            this.parent = (AbstractNDArray<T, T2>) parent;
        checkNewShape(newShape, parent.shape(), parent.length());
        this.dataLength = parent.length();
        this.shape = newShape;
        this.multipliers = IndexingOperations.calculateMultipliers(this.shape);
    }

    @Override
    protected T getUnchecked(int linearIndex) {
        return parent.getUnchecked(linearIndex);
    }

    @Override
    protected T getUnchecked(int... indices) {
        return parent.getUnchecked(IndexingOperations.cartesianIndicesToLinearIndex(indices, multipliers));
    }

    @Override
    protected void setUnchecked(T value, int linearIndex) {
        parent.setUnchecked(value, linearIndex);
        
    }

    @Override
    protected void setUnchecked(T value, int... indices) {
        parent.setUnchecked(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, multipliers));
    }

    protected static void checkNewShape(int[] newShape, int[] parentShape, int expectedLength) {
        int actualLength = IndexingOperations.length(newShape);
        if (actualLength != expectedLength)
            throw new IllegalArgumentException(String.format(Errors.RESHAPE_LENGTH_MISMATCH,
                    Printer.dimsToString(parentShape), Printer.dimsToString(newShape)));
    }

}
