package io.github.hakkelt.ndarrays;

import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.math3.complex.Complex;

abstract class AbstractNDArray<T,T2 extends Number> extends AbstractCollection<T> implements NDArray<T> {
    protected int[] dims;
    protected int[] multipliers; // used to calculate the index in the internal array
    protected int dataLength;
    
    protected static final String ERROR_PARAMETER_MUST_BE_BETWEEN =
        "Parameter '%s' must be between %d and %d, but %d is given!";
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
    protected static final String ERROR_TYPE_MISMATCH =
        "Cannot collect type %s!";
    protected static final String ERROR_INEXACT =
        "Cannot convert value %f to type %s!";
    protected static final String ERROR_INPUT_MUST_BE_ARRAY =
        "A (multidimensional) primitive array is expected as input!";

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
            return ((fence - origin) / 2);
        }

        public int characteristics() {
            return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
        }
    }
    

    public NDArray<T> similar() {
        return createNewNDArrayOfSameTypeAsMe(dims);
    }


    protected enum AccumulateOperators {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    
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
        return name() + " NDArray<" + dataTypeAsString() + ">(" + Printer.dimsToString(dims) + ")";
    }

    
    public String contentToString() {
        return Printer.contentToString(this::resolveIndices, this::dataTypeAsString, this::printItem, name(), dims);
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


    public void set(T value, int... indices) {
        set(value, resolveIndices(indices));
    }


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

    
    public int hashCode() {
        throw new UnsupportedOperationException();
    }


    public void apply(UnaryOperator<T> func) {
        streamLinearIndices().parallel().forEach(linearIndex -> set(func.apply(get(linearIndex)), linearIndex));
    }
    

    public void applyWithLinearIndex(BiFunction<T, Integer, T> func) {
        streamLinearIndices().parallel().forEach(linearIndex -> set(func.apply(get(linearIndex), linearIndex), linearIndex));
    }
    
    
    public void applyWithCartesianIndex(BiFunction<T, int[], T> func) {
        streamCartesianIndices().parallel().forEach(indices -> set(func.apply(get(indices), indices), indices));
    }


    public NDArray<T> copy() {
        AbstractNDArray<T,T2> newArray = createNewNDArrayOfSameTypeAsMe(dims);
        newArray.baseConstuctor(dims);
        return newArray.copyFrom(this);
    }

    public NDArray<T2> abs() {
        return streamLinearIndices()
            .mapToObj(i -> {
                if (eltype() == Float.class)
                    return wrapValue(Math.abs(((Number)get(i)).floatValue()));
                if (eltype() == Double.class)
                    return wrapValue(Math.abs(((Number)get(i)).doubleValue()));
                if (eltype() == Byte.class)
                    return wrapValue(Math.abs(((Number)get(i)).byteValue()));
                if (eltype() == Short.class)
                    return wrapValue(Math.abs(((Number)get(i)).shortValue()));
                if (eltype() == Integer.class)
                    return wrapValue(Math.abs(((Number)get(i)).intValue()));
                if (eltype() == Long.class)
                    return wrapValue(Math.abs(((Number)get(i)).longValue()));
                throw new IllegalArgumentException();
            }).collect(getRealCollectorInternal(dims));
    }

    
    public T sum() {
        return stream().parallel().reduce(zeroT(), (acc, item) -> accumulate(acc, item, AccumulateOperators.ADD));
    }

    public double norm() {
        if (eltype() == Complex.class)
            return Math.sqrt(stream()
                .map(item -> ((Complex)item).multiply(((Complex)item).conjugate()).getReal())
                .reduce(0., (acc, item) -> acc + item));
        else
            return Math.sqrt(stream()
                .mapToDouble(item -> ((Number)item).doubleValue() * ((Number)item).doubleValue())
                .reduce(0., (acc, item) -> acc + item));
    }

    class NumberComparator implements Comparator<Number> {
        public int compare(Number a, Number b) {
            if (a instanceof Float)
                return Float.compare((Float)a, (Float)b);
            if (a instanceof Double)
                return Double.compare((Double)a, (Double)b);
            if (a instanceof Byte)
                return Byte.compare((Byte)a, (Byte)b);
            if (a instanceof Short)
                return Short.compare((Short)a, (Short)b);
            if (a instanceof Integer)
                return Integer.compare((Integer)a, (Integer)b);
            if (a instanceof Long)
                return Long.compare((Long)a, (Long)b);
            throw new IllegalArgumentException();
        }
    }

    @SuppressWarnings("unchecked")
    class NumberAccumulator implements BinaryOperator<T2> {
        public T2 apply(Number a, Number b) {
            if (a instanceof Float)
                return (T2) Float.valueOf(Float.sum((Float)a, (Float)b));
            if (a instanceof Double)
                return (T2) Double.valueOf(Double.sum((Double)a, (Double)b));
            if (a instanceof Byte)
                return (T2) Byte.valueOf((byte)Integer.sum((Byte)a, (Byte)b));
            if (a instanceof Short)
                return (T2) Short.valueOf((short)Integer.sum((Short)a, (Short)b));
            if (a instanceof Integer)
                return (T2) Integer.valueOf(Integer.sum((Integer)a, (Integer)b));
            if (a instanceof Long)
                return (T2) Long.valueOf(Long.sum((Long)a, (Long)b));
            throw new IllegalArgumentException();
        }
    }


    public double norm(Double p) {
        if (p < 0)
            throw new IllegalArgumentException(ERROR_NEGATIVE_NORM);
        else if (p == 0)
            return countNonZero();
        else if (p == 1)
            return streamAbs().reduce(zeroT2(), new NumberAccumulator()).doubleValue();
        else if (p == 2)
            return norm();
        else if (p == Double.POSITIVE_INFINITY) {
            Optional<T2> value = streamAbs().max(new NumberComparator());
            return value.isPresent() ? value.get().doubleValue() : zeroT2().doubleValue();
        } else
            return Math.pow(streamAbs().map(value -> Math.pow(value.doubleValue(), p)).reduce(0., (acc, item) -> acc + item), 1 / p);
    }


    public double norm(int p) {
        return norm((double)p);
    }

    public IntStream streamLinearIndices() {
        return IntStream.range(0, length());
    }
    
    public Stream<int[]> streamCartesianIndices() {
        return streamLinearIndices().mapToObj(i -> linearIndexToCartesianIndices(i, multipliers, ndims(), length()));
    }


    protected abstract String name();
    protected abstract Object eltype2();
    protected abstract long countNonZero();

    protected abstract T zeroT();
    protected abstract T2 zeroT2();
    protected abstract T oneT();
    protected abstract T wrapValue(Number value);
    protected abstract T wrapValue(Object value);
    protected abstract T2 wrapRealValue(Number value);

    protected abstract T accumulate(Float acc, NDArray<?> array, int linearIndex, AccumulateOperators operator);
    protected abstract T accumulate(Double acc, NDArray<?> array, int linearIndex, AccumulateOperators operator);
    protected abstract T accumulate(Byte acc, NDArray<?> array, int linearIndex, AccumulateOperators operator);
    protected abstract T accumulate(Short acc, NDArray<?> array, int linearIndex, AccumulateOperators operator);
    protected abstract T accumulate(Integer acc, NDArray<?> array, int linearIndex, AccumulateOperators operator);
    protected abstract T accumulate(Long acc, NDArray<?> array, int linearIndex, AccumulateOperators operator);
    protected abstract T accumulate(Complex acc, NDArray<?> array, int linearIndex, AccumulateOperators operator);
    protected T accumulate(T acc, T value, AccumulateOperators operator) {
        if (eltype() == Float.class)
            return accumulate((Float)acc, (Number)value, operator);
        if (eltype() == Double.class)
            return accumulate((Double)acc, (Number)value, operator);
        if (eltype() == Byte.class)
            return accumulate((Byte)acc, (Number)value, operator);
        if (eltype() == Short.class)
            return accumulate((Short)acc, (Number)value, operator);
        if (eltype() == Integer.class)
            return accumulate((Integer)acc, (Number)value, operator);
        if (eltype() == Long.class)
            return accumulate((Long)acc, (Number)value, operator);
        throw new UnsupportedOperationException();
    }
    protected abstract T accumulate(Float acc, Number value, AccumulateOperators operator);
    protected abstract T accumulate(Double acc, Number value, AccumulateOperators operator);
    protected abstract T accumulate(Byte acc, Number value, AccumulateOperators operator);
    protected abstract T accumulate(Short acc, Number value, AccumulateOperators operator);
    protected abstract T accumulate(Integer acc, Number value, AccumulateOperators operator);
    protected abstract T accumulate(Long acc, Number value, AccumulateOperators operator);
    protected abstract T accumulate(Complex acc, Number value, AccumulateOperators operator);
    protected abstract T accumulate(Complex acc, Complex value, AccumulateOperators operator);
    protected abstract T accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object ...objects);
    
    protected abstract AbstractNDArray<T,T2> createNewNDArrayOfSameTypeAsMe(int... dims);
    protected abstract AbstractRealNDArray<T2> createNewRealNDArrayOfSameTypeAsMe(int... dims);
    protected abstract Collector<Object, List<Object>, NDArray<T>> getCollectorInternal(int[] dims);
    protected abstract Collector<Object, List<Object>, NDArray<T2>> getRealCollectorInternal(int[] dims);

    protected abstract String printItem(int index, String format);

    
    protected static int[] calculateMultipliers(int[] dims) {
        int arraySize = 1;
        int[] multipliers = new int[dims.length];
        for (int idx = 0; idx < dims.length; idx++) {
          multipliers[idx] = arraySize;
          arraySize *= dims[idx];
        }
        return multipliers;
    }
    
    
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
                    String.format(ERROR_CARTESIAN_BOUNDS_ERROR, Printer.dimsToString(dims), Arrays.toString(indices)));
            if (indices[i] < 0)
                indices[i] += dims[i];
        }
        return indices;
    }

    
    protected static int length(int[] dims) {
        int len = 1;
        for (int i = 0; i < dims.length; i++)
            len *= dims[i];
        return len;
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


    protected static int[] computeDims(Object[] input) {
        List<Integer> dimsList = new ArrayList<>();
        return listToArray(computeDimsInternal(dimsList, input));
    }

    
    protected static List<Integer> computeDimsInternal(List<Integer> dimsSoFar, Object[] input) {
        dimsSoFar.add(input.length);
        if (input[0].getClass().equals(float[].class)) {
            dimsSoFar.add(((float[])input[0]).length);
            return dimsSoFar;
        } else if (input[0].getClass().equals(double[].class)) {
            dimsSoFar.add(((double[])input[0]).length);
            return dimsSoFar;
        } else if (input[0].getClass().isArray())
            return computeDimsInternal(dimsSoFar, (Object[])input[0]);
        else
            throw new IllegalArgumentException(ERROR_INPUT_MUST_BE_ARRAY);
    }

    protected static int[] listToArray(List<Integer> input) {
        return input.stream().mapToInt(Integer::intValue).toArray();
    }


    protected void baseConstuctor(int... dims) {
        this.dims = dims.clone();
        this.dataLength = length(dims);
        this.multipliers = calculateMultipliers(dims);
    }


    protected Stream<T2> streamAbs() {
        if (eltype() == Complex.class)
            return stream().map(value -> wrapRealValue(((Complex)value).abs()));
        if (eltype() == Double.class)
            return stream().map(value -> wrapRealValue(Math.abs((Double)value)));
        if (eltype() == Float.class)
            return stream().map(value -> wrapRealValue(Math.abs((Float)value)));
        if (eltype() == Byte.class)
            return stream().map(value -> wrapRealValue(Math.abs((Byte)value)));
        if (eltype() == Short.class)
            return stream().map(value -> wrapRealValue(Math.abs((Short)value)));
        if (eltype() == Integer.class)
            return stream().map(value -> wrapRealValue(Math.abs((Integer)value)));
        if (eltype() == Long.class)
            return stream().map(value -> wrapRealValue(Math.abs((Long)value)));
        throw new UnsupportedOperationException();
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

    
    protected int resolveIndices(int... indices) {
        indices = boundaryCheck(indices, dims);
        int internalIndex = 0;
        for (int i = 0; i < indices.length; i++)
            internalIndex += indices[i] * multipliers[i];
        return internalIndex;
    }

}
