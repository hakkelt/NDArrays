package io.github.hakkelt.ndarrays;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

abstract class AbstractNDArrayPermuteDimsView<T,T2 extends Number> extends AbstractNDArrayView<T,T2> {
    int[] dimsOrder;

    @SuppressWarnings("unchecked")
    protected AbstractNDArrayPermuteDimsView(NDArray<T> parent, int ...dimsOrder) {
        checkDimsOrder(dimsOrder, parent.dims());
        this.parent = (AbstractNDArray<T,T2>)parent;
        this.dimsOrder = dimsOrder;
        this.dataLength = parent.length();
        this.dims = permuteArray(this.parent.dims, this.dimsOrder);
        this.multipliers = calculateMultipliers(this.dims);
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
    public void set(T value, int linearIndex) {
        set(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    public void set(Number value, int linearIndex) {
        set(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    public void set(T value, int ...indices) {
        boundaryCheck(indices, dims);
        parent.set(value, permuteArray(indices, dimsOrder));
    }

    @Override
    public void set(Number value, int ...indices) {
        boundaryCheck(indices, dims);
        parent.set(value, permuteArray(indices, dimsOrder));
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
                String.format(Errors.PERMUTATOR_SIZE_MISMATCH,
                    Printer.printVector(dimsOrder),
                    Printer.dimsToString(dims)));
        List<Integer> dimsOrderSet = IntStream.of(dimsOrder).boxed().collect(Collectors.toList());
        if (IntStream.of(dimsOrder).anyMatch(num -> num < 0 || num >= dims.length || Collections.frequency(dimsOrderSet, num) > 1))
            throw new IllegalArgumentException(
                String.format(Errors.INVALID_PERMUTATOR,
                    Printer.printVector(dimsOrder),
                    Printer.dimsToString(dims)));
    }
    
}
