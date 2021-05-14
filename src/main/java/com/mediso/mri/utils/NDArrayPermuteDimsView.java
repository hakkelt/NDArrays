package com.mediso.mri.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class NDArrayPermuteDimsView<T> extends AbstractNDArrayView<T> {
    int[] dimsOrder;

    static final String ERROR_PERMUTATOR_SIZE_MISMATCH =
        "The permutation vector (%s) doesn't fit the size of the array to be permutated (%s)!";
    static final String ERROR_INVALID_PERMUTATOR =
        "The permutation vector (%s) is not a valid permutation vector for the array to be permutated (%s)!";

    public NDArrayPermuteDimsView(NDArray<T> parent, int ...dimsOrder) {
        checkDimsOrder(dimsOrder, parent.dims());
        this.parent = (AbstractNDArray<T>)parent;
        this.dimsOrder = dimsOrder;
        this.dataLength = parent.length();
        this.dims = permuteArray(this.parent.dims, this.dimsOrder);
        this.multipliers = calculateMultipliers(this.dims);
        if (parent.areBartDimsSpecified()) {
            bartDims = new BartDimsEnum[ndims()];
            IntStream.range(0, ndims())
                .forEach(i -> bartDims[i] = this.parent.bartDims[dimsOrder[i]]);
            areBartDimsSpecified = true;
        }
    }

    @Override
    public T get(int linearIndex) {
        return get(linearIndexToViewIndices(linearIndex));
    }

    @Override
    public T get(int ...indices) {
        boundaryCheck(indices, dims);
        return parent.get(permuteArray(indices, dimsOrder));
    }

    @Override
    public double getReal(int linearIndex) {
        return getReal(linearIndexToViewIndices(linearIndex));
    }

    @Override
    public double getReal(int ...indices) {
        boundaryCheck(indices, dims);
        return parent.getReal(permuteArray(indices, dimsOrder));
    }

    @Override
    public double getImag(int linearIndex) {
        return getImag(linearIndexToViewIndices(linearIndex));
    }

    @Override
    public double getImag(int ...indices) {
        boundaryCheck(indices, dims);
        return parent.getImag(permuteArray(indices, dimsOrder));
    }

    @Override
    public void set(T value, int linearIndex) {
        set(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    public void set(float value, int linearIndex) {
        set(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    public void set(double value, int linearIndex) {
        set(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    public void set(T value, int ...indices) {
        boundaryCheck(indices, dims);
        parent.set(value, permuteArray(indices, dimsOrder));
    }

    @Override
    public void set(float value, int ...indices) {
        boundaryCheck(indices, dims);
        parent.set(value, permuteArray(indices, dimsOrder));
    }

    @Override
    public void set(double value, int ...indices) {
        boundaryCheck(indices, dims);
        parent.set(value, permuteArray(indices, dimsOrder));
    }

    @Override
    public void setReal(float value, int linearIndex) {
        setReal(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    public void setReal(float value, int ...indices) {
        boundaryCheck(indices, dims);
        parent.setReal(value, permuteArray(indices, dimsOrder));
    }

    @Override
    public void setImag(float value, int linearIndex) {
        setImag(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    public void setImag(float value, int ...indices) {
        boundaryCheck(indices, dims);
        parent.setImag(value, permuteArray(indices, dimsOrder));
    }

    @Override
    public void setReal(double value, int linearIndex) {
        setReal(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    public void setReal(double value, int ...indices) {
        boundaryCheck(indices, dims);
        parent.setReal(value, permuteArray(indices, dimsOrder));
    }

    @Override
    public void setImag(double value, int linearIndex) {
        setImag(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    public void setImag(double value, int ...indices) {
        boundaryCheck(indices, dims);
        parent.setImag(value, permuteArray(indices, dimsOrder));
    }

    @Override
    protected String printItem(int index, String format) {
        int[] viewIndices = linearIndexToViewIndices(index);
        int parentLinearIndex = parent.resolveIndices(permuteArray(viewIndices, dimsOrder));
        return parent.printItem(parentLinearIndex, format);
    }

    protected static int[] permuteArray(int[] array, int[] permutator) {
        return IntStream.of(permutator).map(d -> array[d]).toArray();
    }

    protected static void checkDimsOrder(int[] dimsOrder, int[] dims) {
        if (dimsOrder.length != dims.length)
            throw new IllegalArgumentException(
                String.format(ERROR_PERMUTATOR_SIZE_MISMATCH,
                    printVector(dimsOrder),
                    dimsToString(dims)));
        List<Integer> dimsOrderSet = IntStream.of(dimsOrder).boxed().collect(Collectors.toList());
        if (IntStream.of(dimsOrder).anyMatch(num -> num < 0 || num >= dims.length || Collections.frequency(dimsOrderSet, num) > 1))
            throw new IllegalArgumentException(
                String.format(ERROR_INVALID_PERMUTATOR,
                    printVector(dimsOrder),
                    dimsToString(dims)));
    }
    
}
