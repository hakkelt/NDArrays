package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ViewOperations<T,T2 extends Number> {

    private void prepareSlicingExpressions(NDArray<?> me, Object ...slicingExpressions) {
        for (int i = 0; i < slicingExpressions.length; i++)
            if (slicingExpressions[i] instanceof String && slicingExpressions[i] != ":")
                slicingExpressions[i] = AbstractNDArrayView.parseRange((String)slicingExpressions[i], me.dims(), i);
    }

    public NDArray<T2> slice(InternalRealNDArray<T2> me, Object ...slicingExpressions) {
        prepareSlicingExpressions(me, slicingExpressions);
        return new RealNDArraySliceView<>(me, slicingExpressions);
    }
    
    public ComplexNDArray<T2> slice(InternalComplexNDArray<T2> me, Object ...slicingExpressions) {
        prepareSlicingExpressions(me, slicingExpressions);
        return new ComplexNDArraySliceView<>(me, slicingExpressions);
    }

    public NDArray<T2> permuteDims(InternalRealNDArray<T2> me, int... permutation) {
        return new RealNDArrayPermuteDimsView<>(me, permutation);
    }

    public ComplexNDArray<T2> permuteDims(InternalComplexNDArray<T2> me, int... permutation) {
        return new ComplexNDArrayPermuteDimsView<>(me, permutation);
    }

    public NDArray<T2> reshape(InternalRealNDArray<T2> me, int... newShape) {
        return new RealNDArrayReshapeView<>(me, newShape);
    }

    public ComplexNDArray<T2> reshape(InternalComplexNDArray<T2> me, int... newShape) {
        return new ComplexNDArrayReshapeView<>(me, newShape);
    }

    public NDArray<T> concatenate(AbstractNDArray<T,T2> me, int axis, NDArray<?> ...arrays) {
        checkConcatenationDimensions(me, axis, arrays);
        int[] newDims = me.dims.clone();
        for (NDArray<?> array : arrays)
            newDims[axis] += array.dims(axis);
        AbstractNDArray<T,T2> newArray = me.createNewNDArrayOfSameTypeAsMe(newDims);
        Object[] slicingExpressions = new Object[me.ndims()];
        for (int i = 0; i < me.ndims(); i++) slicingExpressions[i] = ":";
        int start = 0;
        int end = me.dims(axis);
        slicingExpressions[axis] = start + ":" + end;
        NDArray<T> slice = newArray.slice(slicingExpressions);
        slice.copyFrom(me);
        for (NDArray<?> array : arrays) {
            start = end;
            end += array.dims(axis);
            slicingExpressions[axis] = start + ":" + end;
            slice = newArray.slice(slicingExpressions);
            slice.copyFrom(array);
        }
        return newArray;
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> concatenate(InternalComplexNDArray<T2> me, int axis, NDArray<?> ...arrays) {
        return (ComplexNDArray<T2>)concatenate((AbstractNDArray<T,T2>)me, axis, arrays);
    }
    
    public NDArray<T> selectDims(AbstractNDArray<T,T2> me, int... selectedDims) {
        IntStream.of(selectedDims).forEach(i -> {
            if (i < 0)
                throw new IllegalArgumentException(String.format(Errors.CANNOT_SELECT_DIM_NEGATIVE, i, me.ndims()));
            if (i >= me.ndims())
                throw new IllegalArgumentException(String.format(Errors.CANNOT_SELECT_DIM_OVERFLOW, i, me.ndims()));
        });
        Set<Integer> set = IntStream.of(selectedDims).boxed().collect(Collectors.toSet());
        return me.slice(IntStream.range(0, me.ndims()).mapToObj(i -> {
            if (set.contains(i)) return ":";
            if (me.dims(i) != 1) throw new IllegalArgumentException(String.format(Errors.DROPDIMS_NOT_SINGLETON, i));
            return 0;
        }).toArray());
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> selectDims(InternalComplexNDArray<T2> me, int... selectedDims) {
        return (ComplexNDArray<T2>)selectDims((AbstractNDArray<T,T2>)me, selectedDims);
    }

    public NDArray<T> dropDims(AbstractNDArray<T,T2> me, int... selectedDims) {
        IntStream.of(selectedDims).forEach(i -> {
            if (i < 0)
                throw new IllegalArgumentException(String.format(Errors.CANNOT_DROP_DIM_NEGATIVE, i, me.ndims()));
            if (i >= me.ndims())
                throw new IllegalArgumentException(String.format(Errors.CANNOT_DROP_DIM_OVERFLOW, i, me.ndims()));
        });
        List<Integer> set = IntStream.of(selectedDims).boxed().collect(Collectors.toList());
        return me.selectDims(IntStream.range(0, me.ndims()).filter(i -> !set.contains(i)).toArray());
    }

    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> dropDims(InternalComplexNDArray<T2> me, int... selectedDims) {
        return (ComplexNDArray<T2>)dropDims((AbstractNDArray<T,T2>)me, selectedDims);
    }

    public NDArray<T> squeeze(AbstractNDArray<T,T2> me) {
        return me.selectDims(IntStream.range(0, me.dims.length).filter(i -> me.dims[i] != 1).toArray());
    }

    public ComplexNDArray<T2> squeeze(InternalComplexNDArray<T2> me) {
        return me.selectDims(IntStream.range(0, me.ndims()).filter(i -> me.dims(i) != 1).toArray());
    }

    protected void checkConcatenationDimensions(AbstractNDArray<T,T2> me, int axis, NDArray<?> ...arrays) {
        for (NDArray<?> array : arrays) {
            if (array.ndims() != me.ndims())
                throw new IllegalArgumentException(
                    String.format(Errors.CONCATENATION_SIZE_MISMATCH,
                    Printer.dimsToString(array.dims()), Printer.dimsToString(me.dims), axis));
            for (int i = 0; i < me.ndims(); i++)
                if (i != axis && array.dims(i) != me.dims(i))
                    throw new IllegalArgumentException(
                        String.format(Errors.CONCATENATION_SIZE_MISMATCH,
                        Printer.dimsToString(array.dims()), Printer.dimsToString(me.dims), axis));
        }
    }
    
}
