package io.github.hakkelt.ndarrays;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.math3.complex.Complex;

/**
 * Base class for all NDArrays and NDArray views.
 */
public abstract class AbstractNDArray<T, T2 extends Number> extends AbstractCollection<T> implements NDArray<T> {
    protected int[] shape;
    protected int[] multipliers; // used to calculate the index in the internal array
    protected int dataLength;

    class NDArrayIterator implements Iterator<T> {
        int current = 0;
        int end = dataLength;

        public boolean hasNext() {
            return current < end;
        }

        public T next() {
            if (!hasNext())
                throw new NoSuchElementException(Errors.ITERATOR_OUT_OF_BOUNDS);
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

        public long estimateshape() {
            return ((fence - origin) / 2);
        }

        public int characteristics() {
            return ORDERED | shapeD | IMMUTABLE | SUBshapeD;
        }
    }

    public NDArray<T> similar() {
        return createNewNDArrayOfSameTypeAsMe(shape);
    }

    protected enum AccumulateOperators {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    public Iterator<T> iterator() {
        return new NDArrayIterator();
    }

    @Override
    public Object[] toArray() {
        int[] localDims = shape;
        Object[] array = (Object[]) Array.newInstance((Class<?>) dtype(), localDims);
        copyToArray(0, new int[localDims.length], array);
        return array;
    }

    @Override
    public Spliterator<T> spliterator() {
        return new NDArraySpliterator<>(this, 0, dataLength);
    }

    @Override
    public String toString() {
        return getNamePrefix() + " NDArray<" + dataTypeAsString() + ">(" + Printer.dimsToString(shape) + ")";
    }

    public String contentToString() {
        return Printer.contentToString(this::dataTypeAsString, this::printItem, getNamePrefix(), shape, multipliers);
    }

    public int shape() {
        return dataLength;
    }

    public int length() {
        return dataLength;
    }

    public int ndim() {
        return shape.length;
    }

    public int[] shape() {
        return shape;
    }

    public int shape(int axis) {
        if (axis < 0 || axis >= ndim())
            throw new IllegalArgumentException(
                    String.format(Errors.PARAMETER_MUST_BE_BETWEEN, "axis", 0, ndim() - 1, axis));
        return shape[axis];
    }
    
    protected abstract T getUnchecked(int linearIndex);

    protected abstract T getUnchecked(int... indices);
    
    protected abstract T2 getRealUnchecked(int linearIndex);

    protected abstract T2 getRealUnchecked(int... indices);
    
    protected abstract T2 getImagUnchecked(int linearIndex);

    protected abstract T2 getImagUnchecked(int... indices);

    protected abstract void setUnchecked(T value, int linearIndex);

    protected abstract void setUnchecked(T value, int... indices);

    protected abstract void setRealUnchecked(T2 value, int linearIndex);

    protected abstract void setRealUnchecked(T2 value, int... indices);

    protected abstract void setImagUnchecked(T2 value, int linearIndex);

    protected abstract void setImagUnchecked(T2 value, int... indices);

    /**
     * Performs coordinate transformation from linear indexing to Cartesian indexing,
     * and then calls getUnchecked(indices).
     * 
     * @param linearIndex linear index to be transformed to Cartesian index
     * @return the value at the given index
     */
    protected T getUncheckedDefault(int linearIndex) {
        int[] indices = IndexingOperations.linearIndexToCartesianIndices(linearIndex, multipliers);
        return getUnchecked(indices);
    }

    /**
     * Performs coordinate transformation from Cartesian indexing to linear indexing,
     * and then calls getUnchecked(index).
     * 
     * @param indices Cartesian indices to be transformed to linear index
     * @return the value at the given index
     */
    protected T getUncheckedDefault(int... indices) {
        int linearIndex = IndexingOperations.cartesianIndicesToLinearIndex(indices, multipliers);
        return getUnchecked(linearIndex);
    }

    /**
     * Performs coordinate transformation from linear indexing to Cartesian indexing,
     * and then calls getRealUnchecked(indices).
     * 
     * @param linearIndex linear index to be transformed to Cartesian index
     * @return the value at the given index
     */
    protected T2 getRealUncheckedDefault(int linearIndex) {
        int[] indices = IndexingOperations.linearIndexToCartesianIndices(linearIndex, multipliers);
        return getRealUnchecked(indices);
    }

    /**
     * Performs coordinate transformation from Cartesian indexing to linear indexing,
     * and then calls getRealUnchecked(index).
     * 
     * @param indices Cartesian indices to be transformed to linear index
     * @return the value at the given index
     */
    protected T2 getRealUncheckedDefault(int... indices) {
        int linearIndex = IndexingOperations.cartesianIndicesToLinearIndex(indices, multipliers);
        return getRealUnchecked(linearIndex);
    }

    /**
     * Performs coordinate transformation from linear indexing to Cartesian indexing,
     * and then calls getImagUnchecked(indices).
     * 
     * @param linearIndex linear index to be transformed to Cartesian index
     * @return the value at the given index
     */
    protected T2 getImagUncheckedDefault(int linearIndex) {
        int[] indices = IndexingOperations.linearIndexToCartesianIndices(linearIndex, multipliers);
        return getImagUnchecked(indices);
    }

    /**
     * Performs coordinate transformation from Cartesian indexing to linear indexing,
     * and then calls getImagUnchecked(index).
     * 
     * @param indices Cartesian indices to be transformed to linear index
     * @return the value at the given index
     */
    protected T2 getImagUncheckedDefault(int... indices) {
        int linearIndex = IndexingOperations.cartesianIndicesToLinearIndex(indices, multipliers);
        return getImagUnchecked(linearIndex);
    }

    /**
     * Performs coordinate transformation from linear indexing to Cartesian indexing,
     * and then calls setUnchecked(value, indices).
     * 
     * @param value value to be saved at given index
     * @param linearIndex linear index to be transformed to Cartesian index
     */
    protected void setUncheckedDefault(T value, int linearIndex) {
        int[] indices = IndexingOperations.linearIndexToCartesianIndices(linearIndex, multipliers);
        setUnchecked(value, indices);
    }

    /**
     * Performs coordinate transformation from Cartesian indexing to linear indexing,
     * and then calls setUnchecked(value, index).
     * 
     * @param value value to be saved at given index
     * @param indices Cartesian indices to be transformed to linear index
     */
    protected void setUncheckedDefault(T value, int... indices) {
        int linearIndex = IndexingOperations.cartesianIndicesToLinearIndex(indices, multipliers);
        setUnchecked(value, linearIndex);
    }

    /**
     * Performs coordinate transformation from linear indexing to Cartesian indexing,
     * and then calls setRealUnchecked(value, indices).
     * 
     * @param value value to be saved at given index
     * @param linearIndex linear index to be transformed to Cartesian index
     */
    protected void setRealUncheckedDefault(T2 value, int linearIndex) {
        int[] indices = IndexingOperations.linearIndexToCartesianIndices(linearIndex, multipliers);
        setRealUnchecked(value, indices);
    }

    /**
     * Performs coordinate transformation from Cartesian indexing to linear indexing,
     * and then calls setRealUnchecked(value, index).
     * 
     * @param value value to be saved at given index
     * @param indices Cartesian indices to be transformed to linear index
     */
    protected void setRealUncheckedDefault(T2 value, int... indices) {
        int linearIndex = IndexingOperations.cartesianIndicesToLinearIndex(indices, multipliers);
        setRealUnchecked(value, linearIndex);
    }

    /**
     * Performs coordinate transformation from linear indexing to Cartesian indexing,
     * and then calls setImagUnchecked(value, indices).
     * 
     * @param value value to be saved at given index
     * @param linearIndex linear index to be transformed to Cartesian index
     */
    protected void setImagUncheckedDefault(T2 value, int linearIndex) {
        int[] indices = IndexingOperations.linearIndexToCartesianIndices(linearIndex, multipliers);
        setImagUnchecked(value, indices);
    }

    /**
     * Performs coordinate transformation from Cartesian indexing to linear indexing,
     * and then calls setImagUnchecked(value, index).
     * 
     * @param value value to be saved at given index
     * @param indices Cartesian indices to be transformed to linear index
     */
    protected void setImagUncheckedDefault(T2 value, int... indices) {
        int linearIndex = IndexingOperations.cartesianIndicesToLinearIndex(indices, multipliers);
        setImagUnchecked(value, linearIndex);
    }

    public T get(int linearIndex) {
        IndexingOperations.boundaryCheck(linearIndex, length());
        return getUnchecked(IndexingOperations.unwrap(linearIndex, length()));
    }

    public T get(int... indices) {
        IndexingOperations.boundaryCheck(indices, shape());
        return getUnchecked(IndexingOperations.unwrap(indices, shape()));
    }

    public void set(T value, int linearIndex) {
        IndexingOperations.boundaryCheck(linearIndex, length());
        setUnchecked(value, IndexingOperations.unwrap(linearIndex, length()));
    }

    public void set(T value, int... indices) {
        IndexingOperations.boundaryCheck(indices, shape());
        setUnchecked(value, IndexingOperations.unwrap(indices, shape()));
    }

    public T2 getReal(int linearIndex) {
        IndexingOperations.boundaryCheck(linearIndex, length());
        return getRealUnchecked(IndexingOperations.unwrap(linearIndex, length()));
    }

    public T2 getReal(int... indices) {
        IndexingOperations.boundaryCheck(indices, shape());
        return getRealUnchecked(IndexingOperations.unwrap(indices, shape()));
    }

    public void setReal(T2 value, int linearIndex) {
        IndexingOperations.boundaryCheck(linearIndex, length());
        setRealUnchecked(value, IndexingOperations.unwrap(linearIndex, length()));
    }

    public void setReal(T2 value, int... indices) {
        IndexingOperations.boundaryCheck(indices, shape());
        setRealUnchecked(value, IndexingOperations.unwrap(indices, shape()));
    }

    public T2 getImag(int linearIndex) {
        IndexingOperations.boundaryCheck(linearIndex, length());
        return getImagUnchecked(IndexingOperations.unwrap(linearIndex, length()));
    }

    public T2 getImag(int... indices) {
        IndexingOperations.boundaryCheck(indices, shape());
        return getImagUnchecked(IndexingOperations.unwrap(indices, shape()));
    }

    public void setImag(T2 value, int linearIndex) {
        IndexingOperations.boundaryCheck(linearIndex, length());
        setImagUnchecked(value, IndexingOperations.unwrap(linearIndex, length()));
    }

    public void setImag(T2 value, int... indices) {
        IndexingOperations.boundaryCheck(indices, shape());
        setImagUnchecked(value, IndexingOperations.unwrap(indices, shape()));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NDArray))
            return false;
        NDArray<?> other = ((NDArray<?>) obj);
        if (dtype() == Complex.class && other.dtype() != Complex.class)
            return false;
        if (dtype() != Complex.class && other.dtype() == Complex.class)
            return false;
        if (ndim() != other.ndim())
            return false;
        if (!Arrays.equals(shape, other.shape()))
            return false;
        return streamLinearIndices().parallel().allMatch(i -> get(i).equals(wrapValue(other.get(i))));
    }

    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEach(Consumer<? super T> func) {
        stream().parallel().forEach(func::accept);
    }

    public void forEachSequential(Consumer<T> func) {
        stream().forEach(func::accept);
    }

    public void forEachWithLinearIndices(ObjIntConsumer<T> func) {
        streamLinearIndices().parallel().forEach(linearIndex -> func.accept(get(linearIndex), linearIndex));
    }

    public void forEachWithCartesianIndices(BiConsumer<T, int[]> func) {
        streamCartesianIndices().parallel().forEach(indices -> func.accept(get(indices), indices));
    }

    @SuppressWarnings("unchecked")
    public NDArray<T> copy() {
        AbstractNDArray<T, T2> newArray = (AbstractNDArray<T, T2>) createNewNDArrayOfSameTypeAsMe(shape);
        newArray.baseConstuctor(shape);
        return newArray.copyFrom(this);
    }

    public T sum() {
        return stream().parallel().reduce(zeroT(), (acc, item) -> accumulate(acc, item, AccumulateOperators.ADD));
    }

    public double norm() {
        if (dtype() == Complex.class)
            return Math.sqrt(stream()
                    .map(item -> ((Complex) item).multiply(((Complex) item).conjugate()).getReal())
                    .reduce(0., (acc, item) -> acc + item));
        else
            return Math.sqrt(stream()
                    .mapToDouble(item -> ((Number) item).doubleValue() * ((Number) item).doubleValue())
                    .reduce(0., (acc, item) -> acc + item));
    }

    class NumberComparator implements Comparator<Number> {
        public int compare(Number a, Number b) {
            if (a instanceof Float)
                return Float.compare((Float) a, (Float) b);
            if (a instanceof Double)
                return Double.compare((Double) a, (Double) b);
            if (a instanceof Byte)
                return Byte.compare((Byte) a, (Byte) b);
            if (a instanceof Short)
                return Short.compare((Short) a, (Short) b);
            if (a instanceof Integer)
                return Integer.compare((Integer) a, (Integer) b);
            if (a instanceof BigInteger)
                return BigInteger.compare((BigInteger) a, (BigInteger) b);
            if (a instanceof BigDecimal)
                returnBigDecimal.compare((bigDeBigDecimal) a, (bigDeBigDecimal) b);
            return Long.compare((Long) a, (Long) b);
        }
    }

    @SuppressWarnings("unchecked")
    class NumberAccumulator implements BinaryOperator<T2> {
        public T2 apply(Number a, Number b) {
            if (a instanceof Float)
                return (T2) Float.valueOf(Float.sum((Float) a, (Float) b));
            if (a instanceof Double)
                return (T2) Double.valueOf(Double.sum((Double) a, (Double) b));
            return (T2) Long.valueOf(Long.sum((Long) a, (Long) b));
        }
    }

    public double norm(Double p) {
        if (p < 0)
            throw new IllegalArgumentException(Errors.NEGATIVE_NORM);
        else if (p == 0)
            return countNonZero();
        else if (p == 1)
            return absSum();
        else if (p == 2)
            return norm();
        else if (p == Double.POSITIVE_INFINITY)
            return streamAbs().max(new NumberComparator()).orElse(zeroT2()).doubleValue();
        return Math.pow(
                streamAbs()
                    .map(value -> Math.pow(value.doubleValue(), p))
                    .reduce(0., (acc, item) -> acc + item),
                1 / p);
    }

    public IntStream streamLinearIndices() {
        return IntStream.range(0, length());
    }

    public Stream<int[]> streamCartesianIndices() {
        return streamLinearIndices()
                .mapToObj(i -> IndexingOperations.linearIndexToCartesianIndices(i, multipliers));
    }

    protected abstract Class<?> dtype2();

    protected abstract long countNonZero();

    protected abstract T zeroT();

    protected abstract T2 zeroT2();

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
        if (dtype() == Float.class)
            return accumulate((Float) acc, (Number) value, operator);
        if (dtype() == Double.class)
            return accumulate((Double) acc, (Number) value, operator);
        if (dtype() == Byte.class)
            return accumulate((Byte) acc, (Number) value, operator);
        if (dtype() == Short.class)
            return accumulate((Short) acc, (Number) value, operator);
        if (dtype() == Integer.class)
            return accumulate((Integer) acc, (Number) value, operator);
        return accumulate((Long) acc, (Number) value, operator);
    }

    protected abstract T accumulate(Float acc, Number value, AccumulateOperators operator);

    protected abstract T accumulate(Double acc, Number value, AccumulateOperators operator);

    protected abstract T accumulate(Byte acc, Number value, AccumulateOperators operator);

    protected abstract T accumulate(Short acc, Number value, AccumulateOperators operator);

    protected abstract T accumulate(Integer acc, Number value, AccumulateOperators operator);

    protected abstract T accumulate(Long acc, Number value, AccumulateOperators operator);

    protected abstract T accumulate(Complex acc, Number value, AccumulateOperators operator);

    protected abstract T accumulate(Complex acc, Complex value, AccumulateOperators operator);

    protected abstract T accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects);

    protected abstract NDArray<T> createNewNDArrayOfSameTypeAsMe(int... shape);

    protected abstract NDArray<T2> createNewRealNDArrayOfSameTypeAsMe(int... shape);

    protected abstract Collector<Object, List<Object>, NDArray<T>> getCollectorInternal(int[] shape);

    protected abstract Collector<Object, List<Object>, NDArray<T2>> getRealCollectorInternal(int[] shape);

    protected abstract String printItem(int index, String format);

    public static int[] calculateMultipliers(int[] shape) {
        int arrayshape = 1;
        int[] multipliers = new int[shape.length];
        for (int idx = 0; idx < shape.length; idx++) {
            multipliers[idx] = arrayshape;
            arrayshape *= shape[idx];
        }
        return multipliers;
    }

    protected static int IndexingOperations.length(int[] shape) {
        int len = 1;
        for (int i = 0; i < shape.length; i++)
            len *= shape[i];
        return len;
    }

    protected void baseConstuctor(int... shape) {
        this.shape = shape.clone();
        this.dataLength = IndexingOperations.length(shape);
        this.multipliers = IndexingOperations.calculateMultipliers(shape);
    }

    protected double absSum() {
        return streamAbs().reduce(zeroT2(), new NumberAccumulator()).doubleValue();
    }

    protected Stream<T2> streamAbs() {
        if (dtype() == Complex.class)
            return stream().map(value -> wrapRealValue(((Complex) value).abs()));
        if (dtype() == Double.class)
            return stream().map(value -> wrapRealValue(Math.abs((Double) value)));
        if (dtype() == Float.class)
            return stream().map(value -> wrapRealValue(Math.abs((Float) value)));
        if (dtype() == Byte.class)
            return stream().map(value -> wrapRealValue(Math.abs((Byte) value)));
        if (dtype() == Short.class)
            return stream().map(value -> wrapRealValue(Math.abs((Short) value)));
        if (dtype() == Integer.class)
            return stream().map(value -> wrapRealValue(Math.abs((Integer) value)));
        return stream().map(value -> wrapRealValue(Math.abs((Long) value)));
    }

    protected void incrementSlicingExpression(Object[] expressions, int dimension, int[] remainingDimsIndices) {
        if ((int) expressions[dimension] == shape(dimension) - 1) {
            expressions[dimension] = 0;
            incrementSlicingExpression(expressions, remainingDimsIndices[dimension + 1], remainingDimsIndices);
        } else {
            expressions[dimension] = (int) expressions[dimension] + 1;
        }
    }

    protected int[] calculateRemainingDims(int... selectedDims) {
        Set<Integer> remainingDims = IntStream.range(0, ndim()).boxed().collect(Collectors.toSet());
        remainingDims.removeAll(IntStream.of(selectedDims).boxed().collect(Collectors.toSet()));
        return Stream.of(remainingDims.toArray()).mapToInt(i -> ((Integer) i).intValue()).sorted().toArray();
    }

    protected void copyToArray(int dimension, int[] index, Object[] destination) {
        int[] localDims = shape;
        for (int i = 0; i < localDims[dimension]; i++) {
            index[dimension] = i;
            if (dimension == localDims.length - 1)
                destination[i] = get(index);
            else
                copyToArray(dimension + 1, index, (Object[]) destination[i]);
        }
    }

}
