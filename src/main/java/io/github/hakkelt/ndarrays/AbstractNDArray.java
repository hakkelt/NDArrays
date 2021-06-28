package io.github.hakkelt.ndarrays;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.complex.Complex;

abstract class AbstractNDArray<T> extends AbstractCollection<T> implements NDArray<T> {
    protected int[] dims;
    protected int[] multipliers; // used to calculate the index in the internal array
    protected int dataLength;
    protected BartDimsEnum[] bartDims;
    protected boolean areBartDimsSpecified = false;
    protected ByteBuffer rawData;
    
    protected static final String ERROR_PARAMETER_MUST_BE_BETWEEN =
        "Parameter '%s' must be between %d and %d, but %d is given!";
    protected static final String ERROR_INPUT_MUST_BE_ARRAY =
        "A (multidimensional) primitive array is expected as input!";
    protected static final String ERROR_DIMENSION_MISMATCH =
        "Number of indices (%d) doesn't match the number of dimensions (%d)!";
    protected static final String ERROR_CARTESIAN_BOUNDS_ERROR =
        "Bounds error: Attempt to access element of %s array at index %s!";
    protected static final String ERROR_LINEAR_BOUNDS_ERROR =
        "Bounds error: Attempt to access element of array with %d elements at linear index %d!";
    protected static final String ERROR_ITERATOR_OUT_OF_BOUNDS =
        "Bounds error: Iterator already reached the end of the array!";
    protected static final String ERROR_COMBINE_SIZE_MISMATCH =
        "Size of array (%s) given as parameter doesn't match the size of the array (%s) on the left side!";
    protected static final String ERROR_COMBINE_TYPE_MISMATCH_WITH_COMPLEX =
        "All parameters of %s must be either NDArray, Integer, Float, Double or Complex, but %s is given!";
    protected static final String ERROR_COMBINE_TYPE_MISMATCH =
        "All parameters of %s must be either NDArray, Integer, Float or Double, but %s is given!";
    protected static final String ERROR_INVALID_RANGE_EXPRESSION =
        "Invalid range expression: %s!";
    protected static final String ERROR_RS2D_DATA_SET_TOO_HIGH_DIMENSIONAL =
        "RS2D DataSet can only be up to 5 dimensional, but the current array is %d-dimensional!";
    protected static final String ERROR_SIMPLE_ITK_COMPLEX_NOT_SUPPORTED =
        "Conversion to Simple ITK Image is only supported for NDArrays with real elements!";
    protected static final String ERROR_CONCATENATION_SIZE_MISMATCH =
        "Cannot concatenate %s array to the current array of size %s along dimension %d!";
    protected static final String ERROR_NOT_FLOAT_ARRAY =
        "The input object must be a (multidimensional) primitive array!";
    protected static final String ERROR_NOT_FLOAT_ARRAYS =
        "Both of the input objects must be (multidimensional) primitive array!";
    protected static final String ERROR_ARRAYS_DIFFER_IN_SIZE =
        "Input arrays differ in size!";
    protected static final String ERROR_COPY_FROM_TYPE_MISMATCH =
        "Cannot initialize new ComplexNDArray from NDArray of type %s!";
    protected static final String ERROR_INCOMPATIBLE_SIZE =
        "The size of the input (%d) is incompatible with the given dimensions: %s!";
    protected static final String ERROR_SET_BART_DIMS_SIZE_MISMATCH =
        "The length of the list of BART dimensions doesn't match the number of dimensions (%d)!";
    protected static final String ERROR_SET_BART_DIMS_DUPLICATES =
        "The list of BART dimensions contains duplicates!";
    protected static final String ERROR_DROPDIMS_NOT_SINGLETON =
        "Cannot drop dimension %d because it is not singleton!";
    protected static final String ERROR_COPY_FROM_COMPLEX_UNSUPPORTED =
        "Cannot assign imaginary part to this real array!";
    protected static final String ERROR_UNINITIALIZED_BART_DIMS =
        "Meanings of dimension aren't specified yet!";
    protected static final String ERROR_NEGATIVE_NORM =
        "p must be a positive real number!";
    protected static final String ERROR_CANNOT_SELECT_DIM_NEGATIVE =
        "Axis %d cannot be selected";
    protected static final String ERROR_CANNOT_SELECT_DIM_OVERFLOW =
        "Axis %d cannot be selected because the array has only %d dimensions";
    protected static final String ERROR_CANNOT_DROP_DIM_NEGATIVE =
        "Axis %d cannot be dropped";
    protected static final String ERROR_CANNOT_DROP_DIM_OVERFLOW =
        "Axis %d cannot be dropped because the array has only %d dimensions";

    class NDArrayIterator implements Iterator<T> {
        int current = 0;
        int end = dataLength;

        public boolean hasNext() {
            return current < end;
        }

        public T next() {
            if (!hasNext())
                throw new NoSuchElementException(ERROR_ITERATOR_OUT_OF_BOUNDS);
            return get(current++);
        }
    }

    static class NDArraySpliterator<T> implements Spliterator<T> {
        private final NDArray<?> array;
        private int origin; // current index, advanced on split or traversal
        private final int fence; // one past the greatest index

        NDArraySpliterator(NDArray<?> array, int origin, int fence) {
            this.array = array;
            this.origin = origin;
            this.fence = fence;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super T> action) {
            for (; origin < fence; origin++)
                action.accept((T) array.get(origin));
        }

        @SuppressWarnings("unchecked")
        public boolean tryAdvance(Consumer<? super T> action) {
            if (origin < fence) {
                action.accept((T) array.get(origin++));
                return true;
            } else // cannot advance
                return false;
        }

        public Spliterator<T> trySplit() {
            int lo = origin;
            int mid = origin + (fence - origin) / 2;
            if (lo < mid) { // split out left half
                origin = mid; // reset this Spliterator's origin
                return new NDArraySpliterator<>(array, lo, mid);
            } else // too small to split
                return null;
        }

        public long estimateSize() {
            return (long) ((fence - origin) / 2);
        }

        public int characteristics() {
            return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
        }
    }


    public enum AccumulateOperators {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    
    @Override
    public Iterator<T> iterator() {
        return new NDArrayIterator();
    }


    @Override
    public Object[] toArray() {
        int[] localDims = dims;
        Object[] array = (Object[]) Array.newInstance((Class<?>)eltype(), localDims);
        copyToArray(0, new int[localDims.length], array);
        return array;
    }

    @Override
    public Spliterator<T> spliterator() {
        return new NDArraySpliterator<>(this, 0, dataLength);
    }

    
    @Override
    public String toString() {
        return "NDArray<" + dataTypeAsString() + ">(" + dimsToString(dims) + ")";
    }

    
    public String contentToString() {
        String str = "NDArray<" + dataTypeAsString() + ">(" + dimsToString(dims) + ")" + System.lineSeparator();
        if (ndims() == 1)
            str += printRow(0, dims(0), 1);
        else if (ndims() == 2)
            str += printMatrix(0, dims(1), dims(0));
        else
            str += printNDArray(dims.length - 1, new int[dims.length - 2]);
        return str;
    }


    public int size() {
        return dataLength;
    }

    
    public int length() {
        return dataLength;
    }

    
    public int ndims() {
        return dims.length;
    }

    
    public int[] dims() {
        return dims;
    }

    
    public int dims(int axis) {
        if (axis < 0 || axis >= ndims())
            throw new IllegalArgumentException(String.format(ERROR_PARAMETER_MUST_BE_BETWEEN, "axis", 0, ndims() - 1, axis));
        return dims[axis];
    }

    
    public T get(int ... indices) {
        return get(resolveIndices(indices));
    }

    
    public double getReal(int... indices) {
        return getReal(resolveIndices(indices));
    }

    
    public double getImag(int... indices) {
        return getImag(resolveIndices(indices));
    }


    public void set(T value, int... indices) {
        set(value, resolveIndices(indices));
    }

    
    public void set(float value, int... indices) {
        set(value, resolveIndices(indices));
    }

    
    public void set(double value, int... indices) {
        set(value, resolveIndices(indices));
    }


    public void setReal(float value, int... indices) {
        setReal(value, resolveIndices(indices));
    }

    
    public void setImag(float value, int... indices) {
        setImag(value, resolveIndices(indices));
    }

    
    public void setReal(double value, int... indices) {
        setReal(value, resolveIndices(indices));
    }

    
    public void setImag(double value, int... indices) {
        setImag(value, resolveIndices(indices));
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NDArray)) return false;
        if (eltype() == Complex.class && ((NDArray<?>)obj).eltype() != Complex.class) return false;
        if (ndims() != ((NDArray<?>)obj).ndims()) return false;
        int[] myDims = dims;
        for (int i = 0; i < ndims(); i++)
            if (myDims[i] != ((NDArray<?>)obj).dims(i)) return false;
        return streamLinearIndices().parallel()
            .allMatch(i -> get(i).equals(((NDArray<?>)obj).get(i)));
    }

    
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    
    public NDArray<T> slice(Object ...slicingExpressions) {
        for (int i = 0; i < slicingExpressions.length; i++)
            if (slicingExpressions[i] instanceof String && slicingExpressions[i] != ":")
                slicingExpressions[i] = parseRange((String)slicingExpressions[i], i);
        return new NDArraySliceView<>(this, slicingExpressions);
    }

    
    @Override
    public NDArray<T> add(Object ...addends) {
        checkDimensionMatchBeforeCombine(addends, "add");
        return streamLinearIndices().parallel()
            .mapToObj(i -> accumulateAtIndex(i, AccumulateOperators.ADD, addends)).collect(getCollector(dims));
    }

    
    @Override
    public NDArray<T> addInplace(Object ...addends) {
        checkDimensionMatchBeforeCombine(addends, "addInplace");
        streamLinearIndices().parallel()
            .forEach(i -> set(accumulateAtIndex(i, AccumulateOperators.ADD, addends), i));
        return this;
    }

    
    @Override
    public NDArray<T> subtract(Object ...substrahends) {
        checkDimensionMatchBeforeCombine(substrahends, "substract");
        return streamLinearIndices().parallel()
            .mapToObj(i -> accumulateAtIndex(i, AccumulateOperators.SUBTRACT, substrahends)).collect(getCollector(dims));
    }

    
    @Override
    public NDArray<T> subtractInplace(Object ...substrahends) {
        checkDimensionMatchBeforeCombine(substrahends, "substractInplace");
        streamLinearIndices().parallel()
            .forEach(i -> set(accumulateAtIndex(i, AccumulateOperators.SUBTRACT, substrahends), i));
        return this;
    }

    
    @Override
    public NDArray<T> multiply(Object... multiplicands) {
        checkDimensionMatchBeforeCombine(multiplicands, "multiply");
        return streamLinearIndices().parallel()
            .mapToObj(i -> accumulateAtIndex(i, AccumulateOperators.MULTIPLY, multiplicands)).collect(getCollector(dims));
    }

    
    @Override
    public NDArray<T> multiplyInplace(Object... multiplicands) {
        checkDimensionMatchBeforeCombine(multiplicands, "multiplyInplace");
        streamLinearIndices().parallel()
            .forEach(i -> set(accumulateAtIndex(i, AccumulateOperators.MULTIPLY, multiplicands), i));
        return this;
    }

    
    @Override
    public NDArray<T> divide(Object... divisors) {
        checkDimensionMatchBeforeCombine(divisors, "divide");
        return streamLinearIndices().parallel()
            .mapToObj(i -> accumulateAtIndex(i, AccumulateOperators.DIVIDE, divisors)).collect(getCollector(dims));
    }

    
    @Override
    public NDArray<T> divideInplace(Object... divisors) {
        checkDimensionMatchBeforeCombine(divisors, "divideInplace");
        streamLinearIndices().parallel()
            .forEach(i -> set(accumulateAtIndex(i, AccumulateOperators.DIVIDE, divisors), i));
        return this;
    }

    
    @Override
    public T sum() {
        return stream().parallel().reduce(zeroT(), (acc, item) -> accumulate(acc, item, AccumulateOperators.ADD));
    }

    
    @Override
    public NDArray<T> sum(int... selectedDims) {
        Set<Integer> dimSelection = IntStream.of(selectedDims).boxed().collect(Collectors.toSet());
        Object[] expressions = IntStream.range(0, ndims())
                .mapToObj(i -> dimSelection.contains(i) ? ":" : 0).toArray();
        int[] remainingDimsIndices = calculateRemainingDims(selectedDims);
        int[] remainingDims = IntStream.of(remainingDimsIndices).map(this::dims).toArray();
        return IntStream.range(0, length(remainingDims))
            .mapToObj(i -> {
                T value = slice(expressions).sum();
                if (i < length(remainingDims) - 1)
                    incrementSlicingExpression(expressions, 0, remainingDimsIndices);
                return value;
            })
            .collect(getCollector(remainingDims));
    }


    public double norm() {
        if (eltype() == Complex.class)
            return Math.sqrt(stream()
                .map(item -> ((Complex)item).multiply(((Complex)item).conjugate()).getReal())
                .reduce(0., (acc, item) -> acc + item));
        else if (eltype() == Float.class)
            return Math.sqrt(stream()
                .mapToDouble(item -> ((Float)item).floatValue() * ((Float)item).floatValue())
                .reduce(0., (acc, item) -> acc + item));
        else
            return Math.sqrt(stream()
                .mapToDouble(item -> ((Double)item).doubleValue() * ((Double)item).doubleValue())
                .reduce(0., (acc, item) -> acc + item));
    }


    public double norm(Double p) {
        if (p < 0)
            throw new IllegalArgumentException(ERROR_NEGATIVE_NORM);
        else if (p == 0)
            return (double)countNonZero();
        else if (p == 1)
            return streamAbs().reduce(0., (acc, item) -> acc + item);
        else if (p == 2)
            return norm();
        else if (p == Double.POSITIVE_INFINITY)
            return streamAbs().max().getAsDouble();
        else
            return Math.pow(streamAbs().map(value -> Math.pow(value, p)).reduce(0., (acc, item) -> acc + item), 1 / p);
    }


    public double norm(int p) {
        return norm((double)p);
    }

    
    @Override
    public NDArray<T> fill(T value) {
        streamLinearIndices().parallel().forEach(i -> set(value, i));
        return this;
    }

    
    @Override
    public NDArray<T> fill(float value) {
        streamLinearIndices().parallel().forEach(i -> set(value, i));
        return this;
    }

    
    @Override
    public NDArray<T> fill(double value) {
        streamLinearIndices().parallel().forEach(i -> set(value, i));
        return this;
    }

    
    @Override
    public NDArray<T> permuteDims(int... permutation) {
        return new NDArrayPermuteDimsView<>(this, permutation);
    }

    
    public NDArray<T> permuteDims(BartDimsEnum... permutation) {
        int[] intBartDims = IntStream.range(0, permutation.length)
            .map(i -> permutation[i].ordinal()).toArray();
        int[] permutator = IntStream.range(0, bartDims.length)
            .map(i ->
                IntStream.range(0, bartDims.length)
                    .filter(j -> bartDims[i].ordinal() == intBartDims[j])
                    .findFirst().getAsInt()
            ).toArray();
        return permuteDims(permutator);
    }

    
    @Override
    public NDArray<T> reshape(int... newShape) {
        return new NDArrayReshapeView<>(this, newShape);
    }

    
    @Override
    public NDArray<T> concatenate(int axis, NDArray<?> ...arrays) {
        checkConcatenationDimensions(axis, arrays);
        int[] newDims = dims.clone();
        for (NDArray<?> array : arrays)
            newDims[axis] += array.dims(axis);
        AbstractNDArray<T> newArray = createNewNDArrayOfSameTypeAsMe(newDims);
        Object[] slicingExpressions = new Object[ndims()];
        for (int i = 0; i < ndims(); i++) slicingExpressions[i] = ":";
        int start = 0;
        int end = dims(axis);
        slicingExpressions[axis] = start + ":" + end;
        NDArray<T> slice = newArray.slice(slicingExpressions);
        slice.set(this);
        for (NDArray<?> array : arrays) {
            start = end;
            end += array.dims(axis);
            slicingExpressions[axis] = start + ":" + end;
            slice = newArray.slice(slicingExpressions);
            slice.set(array);
        }
        return newArray;
    }

    
    public NDArray<T> set(NDArray<?> array) {
        streamLinearIndices().parallel()
            .forEach(i -> {
                if (array.eltype() == Complex.class && eltype() == Complex.class) {
                    setReal(array.getReal(i), i);
                    setImag(array.getImag(i), i);
                } else if (array.eltype() == Float.class)
                    setReal(array.getReal(i), i);
                else if (array.eltype() == Double.class)
                    setReal(array.getReal(i), i);
                else
                    throw new IllegalArgumentException(String.format(ERROR_COPY_FROM_TYPE_MISMATCH, array.eltype()));
            });
        if (array.areBartDimsSpecified()) {
            bartDims = array.getBartDims();
            areBartDimsSpecified = true;
        }
        return this;
    }

    
    @Override
    public NDArray<T> set(float[] array) {
        flatten(array, 0, 0);
        return this;
    }

    
    @Override
    public NDArray<T> set(double[] array) {
        flatten(array, 0, 0);
        return this;
    }

    
    public NDArray<T> set(Object[] array) {
        flatten(array, 0, 0);
        return this;
    }

    
    public NDArray<T> set(float[] real, float[] imag) {
        if (eltype() != Complex.class)
            throw new UnsupportedOperationException(ERROR_COPY_FROM_COMPLEX_UNSUPPORTED);
        flatten(real, imag, 0, 0);
        return this;
    }
    

    public NDArray<T> set(double[] real, double[] imag) {
        if (eltype() != Complex.class)
            throw new UnsupportedOperationException(ERROR_COPY_FROM_COMPLEX_UNSUPPORTED);
        flatten(real, imag, 0, 0);
        return this;
    }
    
    
    public NDArray<T> set(Object[] real, Object[] imag) {
        if (eltype() != Complex.class)
            throw new UnsupportedOperationException(ERROR_COPY_FROM_COMPLEX_UNSUPPORTED);
        flatten(real, imag, 0, 0);
        return this;
    }

    
    @SuppressWarnings("unchecked")
    public NDArray<T> set(NDArray<?> real, NDArray<?> imag) {
        if (eltype() != Complex.class)
            throw new UnsupportedOperationException(ERROR_COPY_FROM_COMPLEX_UNSUPPORTED);
        AbstractNDArray<Complex> me = (AbstractNDArray<Complex>)this;
        if (real.eltype() == Complex.class || imag.eltype() == Complex.class)
            throw new IllegalArgumentException();
        streamLinearIndices().parallel()
            .forEach(i -> {
                if (real.eltype() == Float.class) {
                    me.setReal((Float)real.get(i), i);
                    me.setImag((Float)imag.get(i), i);
                } else if (real.eltype() == Double.class) {
                    me.setReal((Double)real.get(i), i);
                    me.setImag((Double)imag.get(i), i);
                } else
                    throw new IllegalArgumentException(String.format(ERROR_COPY_FROM_TYPE_MISMATCH, real.eltype()));
            });
        return this;
    }
    
    
    public boolean areBartDimsSpecified() {
        return areBartDimsSpecified;
    }
    
    public BartDimsEnum[] getBartDims() {
        if (!areBartDimsSpecified)
            throw new IllegalArgumentException(ERROR_UNINITIALIZED_BART_DIMS);
        return bartDims;
    }
    
    public void setBartDims(BartDimsEnum... bartDims) {
        if (bartDims.length != ndims())
            throw new IllegalArgumentException(String.format(ERROR_SET_BART_DIMS_SIZE_MISMATCH, ndims()));
        int[] intBartDims = IntStream.range(0, bartDims.length).map(i -> bartDims[i].ordinal()).toArray();
        if (hasDuplicates(intBartDims))
            throw new IllegalArgumentException(ERROR_SET_BART_DIMS_DUPLICATES);
        this.bartDims = bartDims;
        this.areBartDimsSpecified = true;
    }
    
    
    public NDArray<T> selectDims(int... selectedDims) {
        IntStream.of(selectedDims).forEach(i -> {
            if (i < 0)
                throw new IllegalArgumentException(String.format(ERROR_CANNOT_SELECT_DIM_NEGATIVE, i, ndims()));
            if (i >= ndims())
                throw new IllegalArgumentException(String.format(ERROR_CANNOT_SELECT_DIM_OVERFLOW, i, ndims()));
        });
        Set<Integer> set = IntStream.of(selectedDims).boxed().collect(Collectors.toSet());
        NDArray<T> array = slice(IntStream.range(0, ndims()).mapToObj(i -> {
            if (set.contains(i)) return ":";
            if (dims(i) != 1) throw new IllegalArgumentException(String.format(ERROR_DROPDIMS_NOT_SINGLETON, i));
            return 0;
        }).toArray());
        if (areBartDimsSpecified()) {
            BartDimsEnum[] newBartDims = new BartDimsEnum[selectedDims.length];
            int cnt = 0;
            for (int i : selectedDims)
                newBartDims[cnt++] = bartDims[i];
            array.setBartDims(newBartDims);
        }
        return array;
    }
    
    
    public NDArray<T> selectDims(BartDimsEnum... selectedDims) {
        return selectDims(IntStream.range(0, selectedDims.length).map(i -> selectedDims[i].ordinal()).toArray());
    }
    
    
    public NDArray<T> dropDims(int... selectedDims) {
        IntStream.of(selectedDims).forEach(i -> {
            if (i < 0)
                throw new IllegalArgumentException(String.format(ERROR_CANNOT_DROP_DIM_NEGATIVE, i, ndims()));
            if (i >= ndims())
                throw new IllegalArgumentException(String.format(ERROR_CANNOT_DROP_DIM_OVERFLOW, i, ndims()));
        });
        List<Integer> set = IntStream.of(selectedDims).boxed().collect(Collectors.toList());
        return selectDims(IntStream.range(0, ndims()).filter(i -> !set.contains(i)).toArray());
    }

    
    public NDArray<T> dropDims(BartDimsEnum... selectedDims) {
        return dropDims(IntStream.range(0, selectedDims.length).map(i -> selectedDims[i].ordinal()).toArray());
    }
    

    public NDArray<T> squeeze() {
        return selectDims(IntStream.range(0, dims.length).filter(i -> dims[i] != 1).toArray());
    }


    public IntStream streamLinearIndices() {
        return IntStream.range(0, length());
    }
    
    public Stream<int[]> streamCartesianIndices() {
        return streamLinearIndices().mapToObj(i -> linearIndexToCartesianIndices(i, multipliers, ndims(), length()));
    }

    public ByteBuffer getByteBuffer() {
        return rawData;
    }

    
    protected NDArraySliceView.Range parseRange(String str, int dimension) {
        final Pattern r = Pattern.compile("(\\d*):(\\d*)");
        Matcher m = r.matcher(str);
        if (m.find()) {
            int start = m.group(1).equals("") ? 0 : Integer.parseInt(m.group(1));
            int end = m.group(2).equals("") ? dims(dimension) : Integer.parseInt(m.group(2));
            return new NDArraySliceView.Range(start, end);
        } else {
            throw new IllegalArgumentException(String.format(ERROR_INVALID_RANGE_EXPRESSION, str));
        }
    }

    
    protected boolean hasDuplicates(int[] array) {
        List<Integer> set = IntStream.of(array).boxed().collect(Collectors.toList());
        return IntStream.of(array).anyMatch(num -> Collections.frequency(set, num) > 1);
    }

    
    protected void checkConcatenationDimensions(int axis, NDArray<?> ...arrays) {
        for (NDArray<?> array : arrays) {
            if (array.ndims() != ndims())
                throw new IllegalArgumentException(
                    String.format(ERROR_CONCATENATION_SIZE_MISMATCH,
                    dimsToString(array.dims()), dimsToString(dims), axis));
            for (int i = 0; i < ndims(); i++)
                if (i != axis && array.dims(i) != dims(i))
                    throw new IllegalArgumentException(
                        String.format(ERROR_CONCATENATION_SIZE_MISMATCH,
                        dimsToString(array.dims()), dimsToString(dims), axis));
        }
    }

    
    protected abstract AbstractNDArray<T> createNewNDArrayOfSameTypeAsMe(int[] dims);

    
    protected float[] getRealPart(int matrix1D, int j, int k, int t, int c) {
        float[] realPart = new float[matrix1D];
        for (int i = 0; i < matrix1D; i++) {
            if (ndims() == 5)
                realPart[i] = (float)getReal(i, j, k, t, c);
            else if (ndims() == 4)
                realPart[i] = (float)getReal(i, j, k, t);
            else if (ndims() == 3)
                realPart[i] = (float)getReal(i, j, k);
            else if (ndims() == 2)
                realPart[i] = (float)getReal(i, j);
            else
                realPart[i] = (float)getReal(i);
        }
        return realPart;
    }

    
    protected float[] getImagPart(int matrix1D, int j, int k, int t, int c) {
        float[] imagPart = new float[matrix1D];
        for (int i = 0; i < matrix1D; i++) {
            if (ndims() == 5)
                imagPart[i] = (float)getImag(i, j, k, t, c);
            else if (ndims() == 4)
                imagPart[i] = (float)getImag(i, j, k, t);
            else if (ndims() == 3)
                imagPart[i] = (float)getImag(i, j, k);
            else if (ndims() == 2)
                imagPart[i] = (float)getImag(i, j);
            else
                imagPart[i] = (float)getImag(i);
        }
        return imagPart;
    }

    
    protected void incrementSlicingExpression(Object[] expressions, int dimension, int[] remainingDimsIndices) {
        if ((int)expressions[dimension] == dims(dimension) - 1) {
            expressions[dimension] = 0;
            incrementSlicingExpression(expressions, remainingDimsIndices[dimension + 1], remainingDimsIndices);
        } else {
            expressions[dimension] = (int)expressions[dimension] + 1;
        }
    }

    
    protected int[] calculateRemainingDims(int... selectedDims) {
        Set<Integer> remainingDims = IntStream.range(0, ndims()).boxed().collect(Collectors.toSet());
        remainingDims.removeAll(IntStream.of(selectedDims).boxed().collect(Collectors.toSet()));
        return Stream.of(remainingDims.toArray()).mapToInt(i -> ((Integer)i).intValue()).sorted().toArray();
    }

    
    protected abstract NDArray<T> similar(NDArray<?> array);
    
    protected abstract NDArray<T> copy(NDArray<?> array);

    
    protected static int boundaryCheck(int linearIndex, int length) {
        if (linearIndex < -length || linearIndex >= length)
            throw new ArrayIndexOutOfBoundsException(
                String.format(ERROR_LINEAR_BOUNDS_ERROR, length, linearIndex));
        return linearIndex < 0 ? linearIndex + length : linearIndex;
    }

    
    protected static int[] boundaryCheck(int[] indices, int[] dims) {
        indices = indices.clone();
        if (indices.length != dims.length)
            throw new IllegalArgumentException(
                String.format(ERROR_DIMENSION_MISMATCH, indices.length, dims.length));
        for (int i = 0; i < indices.length; i++) {
            if (indices[i] < -dims[i] || indices[i] >= dims[i])
                throw new ArrayIndexOutOfBoundsException(
                    String.format(ERROR_CARTESIAN_BOUNDS_ERROR, dimsToString(dims), Arrays.toString(indices)));
            if (indices[i] < 0)
                indices[i] += dims[i];
        }
        return indices;
    }

    
    protected void copyToArray(int dimension, int[] index, Object[] destination) {
        int[] localDims = dims;
        for (int i = 0; i < localDims[dimension]; i++) {
            index[dimension] = i;
            if (dimension == localDims.length - 1)
                destination[i] = get(index);
            else
                copyToArray(dimension + 1, index, (Object[]) destination[i]);
        }
    }

    
    protected static int[] calculateMultipliers(int[] dims) {
        int arraySize = 1;
        int[] multipliers = new int[dims.length];
        for (int idx = 0; idx < dims.length; idx++) {
          multipliers[idx] = arraySize;
          arraySize *= dims[idx];
        }
        return multipliers;
    }

    
    protected static String dimsToString(int[] dims) {
        return String.join(" × ", IntStream.of(dims).mapToObj(item -> "" + item).collect(Collectors.toList()));
    }

    
    protected static int length(int[] dims) {
        int len = 1;
        for (int i = 0; i < dims.length; i++)
            len *= dims[i];
        return len;
    }

    
    protected int resolveIndices(int... indices) {
        indices = boundaryCheck(indices, dims);
        int internalIndex = 0;
        for (int i = 0; i < indices.length; i++)
            internalIndex += indices[i] * multipliers[i];
        return internalIndex;
    }

    
    protected abstract String printItem(int index, String format);

    
    protected static List<Integer> computeDims(List<Integer> dimsSoFar, Object[] input) {
        dimsSoFar.add(input.length);
        if (input[0].getClass().equals(float[].class)) {
            dimsSoFar.add(((float[])input[0]).length);
            return dimsSoFar;
        } else if (input[0].getClass().equals(double[].class)) {
            dimsSoFar.add(((double[])input[0]).length);
            return dimsSoFar;
        } else if (input[0].getClass().isArray())
            return computeDims(dimsSoFar, (Object[])input[0]);
        else
            throw new IllegalArgumentException(ERROR_INPUT_MUST_BE_ARRAY);
    }

    
    protected static int[] linearIndexToCartesianIndices(int linearIndex, int[] multipliers, int ndims, int length) {
        linearIndex = boundaryCheck(linearIndex, length);
        int[] indices = new int[ndims];
        for (int i = ndims - 1; i >=0; i--) {
            indices[i] = linearIndex / multipliers[i];
            linearIndex -= indices[i] * multipliers[i];
        }
        return indices;
    }


    protected DoubleStream streamAbs() {
        if (eltype() == Complex.class)
            return stream().mapToDouble(value -> ((Complex)value).abs());
        else if (eltype() == Float.class)
            return stream().mapToDouble(value -> Math.abs(((Float)value)));
        else
            return stream().mapToDouble(value -> Math.abs(((Double)value)));
    }


    protected long countNonZero() {
        if (eltype() == Complex.class)
            return stream().filter(item -> item != Complex.ZERO).count();
        else if (eltype() == Float.class)
            return stream().filter(item -> (Float)item != 0.).count();
        else
            return stream().filter(item -> (Double)item != 0.).count();
    }

    
    protected String printRow(int startIndex, int width, int height, String format) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < width; i++) {
            str.append(printItem(startIndex + i * height, format));
            str.append("\t");
        }
        return str.toString();
    }

    
    protected String printRow(int startIndex, int width, int height) {
        String format = "%10.5e";
        return printRow(startIndex, width, height, format);
    }

    
    protected String printMatrix(int startIndex, int width, int height) {
        String format = "%10.5e";
        return printMatrix(startIndex, width, height, format);
    }

    
    protected String printMatrix(int startIndex, int width, int height, String format) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < height; i++) {
            str.append(printRow(startIndex + i, width, height, format));
            str.append(System.lineSeparator());
        }
        return str.toString();
    }

    
    protected static String printIndices(int[] indices) {
        return "[:, :, " + StringUtils.join(ArrayUtils.toObject(indices), ", ") + "]";
    }

    
    protected static String printVector(int[] indices) {
        return "[" + StringUtils.join(ArrayUtils.toObject(indices), ", ") + "]";
    }

    
    protected String printNDArray(int dimension, int[] indices) {
        StringBuilder str = new StringBuilder();
        for (indices[dimension - 2] = 0; indices[dimension - 2] < dims(dimension); indices[dimension - 2]++) {
            if (dimension == 2) {
                str.append(printIndices(indices));
                str.append(" =" + System.lineSeparator());
                int[] startIndices = new int[ndims()];
                for (int j = 2; j < ndims(); j++)
                    startIndices[j] = indices[j - 2];
                str.append(printMatrix(resolveIndices(startIndices), dims(1), dims(0)));
                str.append(System.lineSeparator());
            } else {
                str.append(printNDArray(dimension - 1, indices));
            }
        }
        return str.toString();
    }

    
    protected void checkIfNumber(Object item, String funcName) {
        if (eltype() == Complex.class) {
            if (!(item instanceof Integer) && !(item instanceof Float) && !(item instanceof Double) && !(item instanceof Complex))
                throw new IllegalArgumentException(String.format(ERROR_COMBINE_TYPE_MISMATCH_WITH_COMPLEX, funcName, item.getClass()));
        } else {
            if (!(item instanceof Integer) && !(item instanceof Float) && !(item instanceof Double))
                throw new IllegalArgumentException(String.format(ERROR_COMBINE_TYPE_MISMATCH, funcName, item.getClass()));
        }
    }

    
    protected void checkDimensionMatchBeforeCombine(Object[] arrays, String funcName) {
        for (Object item : arrays) {
            if (item instanceof NDArray<?>) {
                NDArray<?> array = (NDArray<?>)item;
                if (!Arrays.equals(dims, array.dims()))
                    throw new IllegalArgumentException(String.format(ERROR_COMBINE_SIZE_MISMATCH, dimsToString(array.dims()), dimsToString(dims)));
            } else {
                checkIfNumber(item, funcName);
            }
        }
    }

    
    protected abstract T zeroT();
    protected abstract T oneT();
    protected abstract T accumulate(T acc, NDArray<?> array, int linearIndex, AccumulateOperators operator);
    protected abstract T accumulate(T acc, T value, AccumulateOperators operator);
    protected abstract T accumulate(T acc, Complex value, AccumulateOperators operator);
    protected abstract T accumulate(T acc, Double value, AccumulateOperators operator);
    protected abstract T accumulate(T acc, Float value, AccumulateOperators operator);
    protected abstract T accumulate(T acc, Integer value, AccumulateOperators operator);
    protected abstract Collector<Object, List<Object>, NDArray<T>> getCollector(int[] dims);

    
    protected T accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object ...objects) {
        T acc = get(linearIndex);
        for (Object item : objects) {
            if (item instanceof NDArray<?>) {
                acc = accumulate(acc, ((NDArray<?>)item), linearIndex, operator);
            } else if (item instanceof Complex) {
                acc = accumulate(acc, (Complex)item, operator);
            } else if (item instanceof Double) {
                acc = accumulate(acc, (Double)item, operator);
            } else if (item instanceof Float) {
                acc = accumulate(acc, (Float)item, operator);
            } else if (item instanceof Integer) {
                acc = accumulate(acc, (Integer)item, operator);
            }
        }
        return acc;
    }

    
    protected void flatten(float[] real, int startIndex, int dimension) {
        IntStream.range(0, real.length).parallel()
            .forEach(i -> set(real[i], startIndex + i * multipliers[dimension]));
    }

    
    protected void flatten(double[] real, int startIndex, int dimension) {
        IntStream.range(0, real.length).parallel()
            .forEach(i -> set(real[i], startIndex + i * multipliers[dimension]));
    }

    
    protected void flatten(Object[] realOrComplex, int startIndex, int dimension) {
        int i = 0;
        for (Object obj : realOrComplex) {
            if (obj.getClass().equals(float[].class)) {
                flatten((float[]) obj, startIndex + i++ * multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(double[].class)) {
                flatten((double[]) obj, startIndex + i++ * multipliers[dimension], dimension + 1);
            } else if (obj.getClass().equals(Complex[].class)) {
                flatten((Complex[]) obj, startIndex + i++ * multipliers[dimension], dimension + 1);
            } else if (obj.getClass().isArray()) {
                flatten((Object[]) obj, startIndex + i++ * multipliers[dimension], dimension + 1);
            } else {
                throw new IllegalArgumentException(ERROR_NOT_FLOAT_ARRAY);
            }
        }
    }

    
    @SuppressWarnings("unchecked")
    protected void flatten(Complex[] compl, int startIndex, int dimension) {
        if (eltype() != Complex.class)
            throw new UnsupportedOperationException();
        AbstractNDArray<Complex> me = (AbstractNDArray<Complex>)this;
        IntStream.range(0, compl.length).parallel()
            .forEach(i -> me.set(compl[i], startIndex + i * multipliers[dimension]));
    }

    
    @SuppressWarnings("unchecked")
    protected void flatten(float[] real, float[] imag, int startIndex, int dimension) {
        if (eltype() != Complex.class)
            throw new UnsupportedOperationException();
        AbstractNDArray<Complex> me = (AbstractNDArray<Complex>)this;
        if (real.length != imag.length)
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * multipliers[dimension]);
            });
    }

    
    @SuppressWarnings("unchecked")
    protected void flatten(double[] real, double[] imag, int startIndex, int dimension) {
        if (eltype() != Complex.class)
            throw new UnsupportedOperationException();
        AbstractNDArray<Complex> me = (AbstractNDArray<Complex>)this;
        if (real.length != imag.length)
            throw new IllegalArgumentException(ERROR_ARRAYS_DIFFER_IN_SIZE);
        IntStream.range(0, real.length).parallel()
            .forEach(i -> {
                me.setReal(real[i], startIndex + i * multipliers[dimension]);
                me.setImag(imag[i], startIndex + i * multipliers[dimension]);
            });
    }

    
    protected void flatten(Object[] real, Object[] imag, int startIndex, int dimension) {
        if (eltype() != Complex.class)
            throw new UnsupportedOperationException();
        for (int i = 0; i < real.length; i++) {
            if (real[i].getClass().equals(float[].class) && imag[i].getClass().equals(float[].class))
                flatten((float[]) real[i], (float[]) imag[i], startIndex + i * multipliers[dimension], dimension + 1);
            else if (real[i].getClass().equals(double[].class) && imag[i].getClass().equals(double[].class))
                flatten((double[]) real[i], (double[]) imag[i], startIndex + i * multipliers[dimension], dimension + 1);
            else if (real[i].getClass().isArray() && imag[i].getClass().isArray())
                flatten((Object[]) real[i], (Object[]) imag[i], startIndex + i * multipliers[dimension], dimension + 1);
            else
                throw new IllegalArgumentException(ERROR_NOT_FLOAT_ARRAYS);
        }
    }

    
    protected void checkCompatibleSize(int[] dims, float[] real) {
        if (length(dims) != real.length)
            throw new IllegalArgumentException(
                    String.format(ERROR_INCOMPATIBLE_SIZE, real.length, Arrays.toString(dims)));
    }

    
    protected void checkCompatibleSize(int[] dims, double[] real) {
        if (length(dims) != real.length)
            throw new IllegalArgumentException(
                    String.format(ERROR_INCOMPATIBLE_SIZE, real.length, Arrays.toString(dims)));
    }
    
    
    protected int[] listToArray(List<Integer> input) {
        return input.stream().mapToInt(Integer::intValue).toArray();
    }

}
