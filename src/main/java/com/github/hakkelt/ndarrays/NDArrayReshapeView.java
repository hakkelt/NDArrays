package com.github.hakkelt.ndarrays;

class NDArrayReshapeView<T> extends AbstractNDArrayView<T> {

    static final String ERROR_LENGTH_MISMATCH = 
        "Cannot reshape %s array to new shape %s: Number of elements doesn't match!";

    public NDArrayReshapeView(NDArray<T> parent, int ...newShape) {
        this.parent = (AbstractNDArray<T>)parent;
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
    public double getReal(int linearIndex) {
        return parent.getReal(linearIndex);
    }

    @Override
    public double getImag(int linearIndex) {
        return parent.getImag(linearIndex);
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

    @Override
    public void set(float value, int linearIndex) {
        parent.set(value, linearIndex);
    }

    @Override
    public void set(double value, int linearIndex) {
        parent.set(value, linearIndex);
    }

    @Override
    public void setReal(float value, int linearIndex) {
        parent.setReal(value, linearIndex);
    }

    @Override
    public void setImag(float value, int linearIndex) {
        parent.setImag(value, linearIndex);
    }

    @Override
    public void setReal(double value, int linearIndex) {
        parent.setReal(value, linearIndex);
    }

    @Override
    public void setImag(double value, int linearIndex) {
        parent.setImag(value, linearIndex);
    }

    @Override
    public NDArray<T> reshape(int... newShape) {
        checkNewShape(newShape, dims, dataLength);
        return new NDArrayReshapeView<>(parent, newShape);
    }

    protected static void checkNewShape(int[] newShape, int[] parentShape, int expectedLength) {
        int actualLength = length(newShape);
        if (actualLength != expectedLength)
            throw new IllegalArgumentException(
                String.format(ERROR_LENGTH_MISMATCH, dimsToString(parentShape), dimsToString(newShape)));
    }
    
}
