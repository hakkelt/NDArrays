package io.github.hakkelt.ndarrays;

abstract class AbstractNDArrayReshapeView<T,T2 extends Number> extends AbstractNDArrayView<T,T2> {

    static final String ERROR_LENGTH_MISMATCH = 
        "Cannot reshape %s array to new shape %s: Number of elements doesn't match!";

    @SuppressWarnings("unchecked")
    protected AbstractNDArrayReshapeView(NDArray<T> parent, int ...newShape) {
        this.parent = (AbstractNDArray<T,T2>)parent;
        checkNewShape(newShape, parent.dims(), parent.length());
        this.dataLength = parent.length();
        this.dims = newShape;
        this.multipliers = calculateMultipliers(this.dims);
    }

    @Override
    public T get(int ...indices) {
        boundaryCheck(indices, dims);
        return parent.get(resolveIndices(indices));
    }

    @Override
    public T get(int linearIndex) {
        return parent.get(linearIndex);
    }

    @Override
    public void set(T value, int ...indices) {
        boundaryCheck(indices, dims);
        parent.set(value, resolveIndices(indices));
    }

    @Override
    public void set(T value, int linearIndex) {
        parent.set(value, linearIndex);
    }


    protected static void checkNewShape(int[] newShape, int[] parentShape, int expectedLength) {
        int actualLength = length(newShape);
        if (actualLength != expectedLength)
            throw new IllegalArgumentException(
                String.format(ERROR_LENGTH_MISMATCH, Printer.dimsToString(parentShape), Printer.dimsToString(newShape)));
    }
    
}
