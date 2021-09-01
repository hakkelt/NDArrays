package io.github.hakkelt.ndarrays;

abstract class AbstractNDArrayReshapeView<T,T2 extends Number> extends AbstractNDArrayView<T,T2> {

    @SuppressWarnings("unchecked")
    protected AbstractNDArrayReshapeView(NDArray<T> parent, int ...newShape) {
        if (parent instanceof AbstractNDArrayReshapeView)
            this.parent = ((AbstractNDArrayReshapeView<T,T2>)parent).parent;
        else
            this.parent = (AbstractNDArray<T,T2>)parent;
        checkNewShape(newShape, parent.dims(), parent.length());
        this.dataLength = parent.length();
        this.dims = newShape;
        this.multipliers = calculateMultipliers(this.dims);
    }

    @Override
    public T get(int ...indices) {
        IndexingOperations.boundaryCheck(indices, dims);
        return parent.get(IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
    }

    @Override
    public T get(int linearIndex) {
        return parent.get(linearIndex);
    }

    @Override
    public void set(T value, int ...indices) {
        IndexingOperations.boundaryCheck(indices, dims);
        parent.set(value, IndexingOperations.cartesianIndicesToLinearIndex(indices, dims, multipliers));
    }

    @Override
    public void set(T value, int linearIndex) {
        parent.set(value, linearIndex);
    }

    protected static void checkNewShape(int[] newShape, int[] parentShape, int expectedLength) {
        int actualLength = length(newShape);
        if (actualLength != expectedLength)
            throw new IllegalArgumentException(
                String.format(Errors.RESHAPE_LENGTH_MISMATCH, Printer.dimsToString(parentShape), Printer.dimsToString(newShape)));
    }
    
}
