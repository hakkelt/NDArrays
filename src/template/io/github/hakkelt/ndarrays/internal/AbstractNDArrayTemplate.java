package io.github.hakkelt.ndarrays.internal;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.NDArrayUtils;

/**
 * Base class for all NDArrays and NDArray views.
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays/internal", newName = "AbstractNDArray")
public abstract class AbstractNDArrayTemplate<T, T2 extends Number> implements NDArray<T> {
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

        public long estimateSize() {
            return (long) fence - origin;
        }

        public int characteristics() {
            return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
        }
    }

    @Override
    public NDArray<T> similar() {
        return createNewNDArrayOfSameTypeAsMe(shape);
    }

    @Override
    public boolean contains(Object o) {
        T wrapped = wrapValue(o);
        return stream().anyMatch(wrapped::equals);
    } 

    @Override
    public Iterator<T> iterator() {
        return new NDArrayIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = (Object[]) Array.newInstance(dtype(), shape);
        copyToArray(0, new int[shape.length], array);
        return array;
    }

    @Override
    public <A> A toArray(A array) {
        Class<?> dtype = getDType(array);
        int depth = getArrayDepth(array);
        if (depth != shape.length)
            throw new ArrayStoreException(String.format(Errors.TOARRAY_DEPTH_MISMATCH, depth, shape.length));
        array = prepareArray(array, dtype, 0);
        copyToArray(0, new int[shape.length], array);
        return array;
    }

    @Override
    public <A> A toArray(IntFunction<A> generator) {
        return toArray(generator.apply(0));
    }

    @SuppressWarnings("unchecked")
    protected <A> A prepareArray(Object array, Class<?> dtype, int dimension) {
        if (array == null || Array.getLength(array) < shape[dimension]) {
            int[] remainingDims = Arrays.copyOfRange(shape, dimension, shape.length);
            return (A) Array.newInstance(dtype, remainingDims);
        } else {
            if (dimension < shape.length - 1) {
                for (int i = 0; i < shape[dimension]; i++)
                    Array.set(array, i, prepareArray(Array.get(array, i), dtype, dimension + 1));
            }
            if (Array.getLength(array) > shape[dimension])
                Array.set(array, shape[dimension], null);
            return (A) array;
        }
    }

    protected Class<?> getDType(Object array) {
        Class<?> dtype = array.getClass();
        while (dtype.isArray())
            dtype = dtype.getComponentType();
        return dtype;
    }

    protected int getArrayDepth(Object array) {
        int depth = 0;
        Class<?> dtype = array.getClass();
        while (dtype.isArray()) {
            dtype = dtype.getComponentType();
            depth++;
        }
        return depth;
    }

    @Override
    public Spliterator<T> spliterator() {
        return new NDArraySpliterator<>(this, 0, dataLength);
    }

    protected String getTypeName() {
        return "NDArray"; // NOSONAR
    }

    public String toString() {
        return getNamePrefix() + " " + getTypeName() + "<" + dataTypeAsString() + ">("
            + Printer.dimsToString(shape) + ")";
    }

    @Override
    public String contentToString() {
        return Printer.contentToString(dataTypeAsString(), getTypeName(), this::printItem,
            getNamePrefix(), shape, multipliers);
    }

    @Override
    public int length() {
        return dataLength;
    }

    @Override
    public int ndim() {
        return shape.length;
    }

    @Override
    public int[] shape() {
        return shape.clone();
    }

    @Override
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
    @Patterns({"T", "/getUnchecked/"})
    @Replacements({"T2", "getRealUnchecked"})
    @Replacements({"T2", "getImagUnchecked"})
    protected T getUncheckedDefault(int linearIndex) {
        int[] indices = NDArrayUtils.linearIndexToCartesianIndices(linearIndex, multipliers);
        return getUnchecked(indices);
    }

    /**
     * Performs coordinate transformation from Cartesian indexing to linear indexing,
     * and then calls getUnchecked(index).
     * 
     * @param indices Cartesian indices to be transformed to linear index
     * @return the value at the given index
     */
    @Patterns({"T", "/getUnchecked/"})
    @Replacements({"T2", "getRealUnchecked"})
    @Replacements({"T2", "getImagUnchecked"})
    protected T getUncheckedDefault(int... indices) {
        int linearIndex = NDArrayUtils.cartesianIndicesToLinearIndex(indices, multipliers);
        return getUnchecked(linearIndex);
    }

    /**
     * Performs coordinate transformation from linear indexing to Cartesian indexing,
     * and then calls setUnchecked(value, indices).
     * 
     * @param value value to be saved at given index
     * @param linearIndex linear index to be transformed to Cartesian index
     */
    @Patterns({"T", "/setUnchecked/"})
    @Replacements({"T2", "setRealUnchecked"})
    @Replacements({"T2", "setImagUnchecked"})
    protected void setUncheckedDefault(T value, int linearIndex) {
        int[] indices = NDArrayUtils.linearIndexToCartesianIndices(linearIndex, multipliers);
        setUnchecked(value, indices);
    }

    /**
     * Performs coordinate transformation from Cartesian indexing to linear indexing,
     * and then calls setUnchecked(value, index).
     * 
     * @param value value to be saved at given index
     * @param indices Cartesian indices to be transformed to linear index
     */
    @Patterns({"T", "/setUnchecked/"})
    @Replacements({"T2", "setRealUnchecked"})
    @Replacements({"T2", "setImagUnchecked"})
    protected void setUncheckedDefault(T value, int... indices) {
        int linearIndex = NDArrayUtils.cartesianIndicesToLinearIndex(indices, multipliers);
        setUnchecked(value, linearIndex);
    }

    @Override
    public T get(int linearIndex) {
        NDArrayUtils.boundaryCheck(linearIndex, length());
        return getUnchecked(NDArrayUtils.unwrap(linearIndex, length()));
    }

    @Patterns({"T2", "/getReal/"})
    @Replacements({"T2", "getImag"})
    public T2 getReal(int linearIndex) {
        NDArrayUtils.boundaryCheck(linearIndex, length());
        return getRealUnchecked(NDArrayUtils.unwrap(linearIndex, length()));
    }

    @Override
    public T get(int... indices) {
        NDArrayUtils.boundaryCheck(indices, shape());
        return getUnchecked(NDArrayUtils.unwrap(indices, shape()));
    }

    @Patterns({"T2", "/getReal/"})
    @Replacements({"T2", "getImag"})
    public T2 getReal(int... indices) {
        NDArrayUtils.boundaryCheck(indices, shape());
        return getRealUnchecked(NDArrayUtils.unwrap(indices, shape()));
    }

    @Override
    public void set(T value, int linearIndex) {
        NDArrayUtils.boundaryCheck(linearIndex, length());
        setUnchecked(value, NDArrayUtils.unwrap(linearIndex, length()));
    }

    @Patterns({"T2", "/setReal/"})
    @Replacements({"T2", "setImag"})
    public void setReal(T2 value, int linearIndex) {
        NDArrayUtils.boundaryCheck(linearIndex, length());
        setRealUnchecked(value, NDArrayUtils.unwrap(linearIndex, length()));
    }

    @Override
    public void set(T value, int... indices) {
        NDArrayUtils.boundaryCheck(indices, shape());
        setUnchecked(value, NDArrayUtils.unwrap(indices, shape()));
    }

    @Patterns({"T2", "/setReal/"})
    @Replacements({"T2", "setImag"})
    public void setReal(T2 value, int... indices) {
        NDArrayUtils.boundaryCheck(indices, shape());
        setRealUnchecked(value, NDArrayUtils.unwrap(indices, shape()));
    }

    @Override
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
        if (dtype() == BigDecimal.class)
            return streamLinearIndices().parallel().allMatch(i ->
                0 == ((BigDecimal) get(i)).compareTo((BigDecimal) wrapValue(other.get(i))));
        return streamLinearIndices().parallel().allMatch(i -> get(i).equals(wrapValue(other.get(i))));
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Stream<T> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }

    @Override
    public NDArray<T> fillUsingLinearIndices(IntFunction<T> func) {
        streamLinearIndices().parallel()
            .forEach(linearIndex -> setUnchecked(func.apply(linearIndex), linearIndex));
        return this;
    }

    @Override
    public NDArray<T> fillUsingCartesianIndices(Function<int[],T> func) {
        streamCartesianIndices().parallel()
            .forEach(indices -> setUnchecked(func.apply(indices), indices));
        return this;
    }

    @Override
    public NDArray<T> apply(UnaryOperator<T> func) {
        streamLinearIndices().parallel()
            .forEach(linearIndex -> setUnchecked(func.apply(getUnchecked(linearIndex)), linearIndex));
        return this;
    }

    @Override
    public NDArray<T> applyWithLinearIndices(BiFunction<T,Integer,T> func) {
        streamLinearIndices().parallel()
            .forEach(linearIndex -> setUnchecked(func.apply(getUnchecked(linearIndex), linearIndex), linearIndex));
        return this;
    }

    @Override
    public NDArray<T> applyWithCartesianIndices(BiFunction<T,int[],T> func) {
        streamCartesianIndices().parallel()
            .forEach(indices -> setUnchecked(func.apply(getUnchecked(indices), indices), indices));
        return this;
    }

    @Override
    public void forEach(Consumer<? super T> func) {
        stream().parallel().forEach(func::accept);
    }

    @Override
    public void forEachSequential(Consumer<T> func) {
        stream().forEach(func::accept);
    }

    @Override
    public void forEachWithLinearIndices(ObjIntConsumer<T> func) {
        streamLinearIndices().parallel().forEach(linearIndex -> func.accept(getUnchecked(linearIndex), linearIndex));
    }

    @Override
    public void forEachWithCartesianIndices(BiConsumer<T, int[]> func) {
        streamCartesianIndices().parallel().forEach(indices -> func.accept(getUnchecked(indices), indices));
    }

    @Override
    @SuppressWarnings("unchecked")
    public NDArray<T> copy() {
        AbstractNDArray<T, T2> newArray = (AbstractNDArray<T, T2>) createNewNDArrayOfSameTypeAsMe(shape);
        newArray.baseConstuctor(shape);
        return newArray.copyFrom(this);
    }

    @Override
    public T sum() {
        return stream().parallel().reduce(zeroT(), (acc, item) -> accumulate(acc, item, AccumulateOperators.ADD));
    }

    @Override
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
            if (a instanceof Long)
                return Long.compare((Long) a, (Long) b);
            if (a instanceof BigInteger)
                return ((BigInteger) a).compareTo((BigInteger) b);
            return ((BigDecimal) a).compareTo((BigDecimal) b);
        }
    }

    @SuppressWarnings("unchecked")
    class NumberAccumulator implements BinaryOperator<T2> {
        public T2 apply(Number a, Number b) {
            if (a instanceof Byte)
                return (T2) Byte.valueOf((byte) Integer.sum((Byte) a, (Byte) b));
            if (a instanceof Short)
                return (T2) Short.valueOf((short) Integer.sum((Short) a, (Short) b));
            if (a instanceof Integer)
                return (T2) Integer.valueOf(Integer.sum((Integer) a, (Integer) b));
            if (a instanceof Long)
                return (T2) Long.valueOf(Long.sum((Long) a, (Long) b));
            if (a instanceof Float)
                return (T2) Float.valueOf(Float.sum((Float) a, (Float) b));
            if (a instanceof Double)
                return (T2) Double.valueOf(Double.sum((Double) a, (Double) b));
            if (a instanceof BigInteger)
                return (T2) ((BigInteger) a).add((BigInteger) b);
            return (T2) ((BigDecimal) a).add((BigDecimal) b);
        }
    }

    @Override
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

    @Override
    public IntStream streamLinearIndices() {
        return IntStream.range(0, length());
    }

    @Override
    public Stream<int[]> streamCartesianIndices() {
        return streamLinearIndices()
                .mapToObj(i -> NDArrayUtils.linearIndexToCartesianIndices(i, multipliers));
    }

    protected abstract Class<?> dtype2();

    protected abstract long countNonZero();

    protected abstract T zeroT();

    protected abstract T2 zeroT2();

    protected abstract T wrapValue(Number value);

    protected abstract T wrapValue(Object value);

    protected abstract T2 wrapRealValue(Number value);

    @Replace(pattern = "Float", replacements = {"Double", "Byte", "Short", "Integer", "Long", "Complex", "BigInteger", "BigDecimal"})
    protected abstract T accumulate(Float acc, NDArray<?> array, int linearIndex, AccumulateOperators operator);

    @Replace(pattern = "Float", replacements = {"Double", "Byte", "Short", "Integer", "Long", "Complex", "BigInteger", "BigDecimal"})
    protected abstract T accumulate(Float acc, Number value, AccumulateOperators operator);

    protected abstract T accumulate(Complex acc, Complex value, AccumulateOperators operator);

    protected abstract T accumulateAtIndex(int linearIndex, AccumulateOperators operator, Object... objects);

    protected abstract NDArray<T> createNewNDArrayOfSameTypeAsMe(int... shape);

    protected abstract NDArray<T2> createNewRealNDArrayOfSameTypeAsMe(int... shape);

    protected abstract Collector<Object, List<Object>, NDArray<T>> getCollectorInternal(int[] shape);

    protected abstract Collector<Object, List<Object>, NDArray<T2>> getRealCollectorInternal(int[] shape);

    protected abstract String printItem(int index, String format);

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
        if (dtype() == Long.class)
            return accumulate((Long) acc, (Number) value, operator);
        if (dtype() == BigInteger.class)
            return accumulate((BigInteger) acc, (Number) value, operator);
        return accumulate((BigDecimal) acc, (Number) value, operator);
    }

    protected void baseConstuctor(int... shape) {
        this.shape = shape.clone();
        this.dataLength = NDArrayUtils.calculateLength(shape);
        this.multipliers = NDArrayUtils.calculateMultipliers(shape);
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
        if (dtype() == Long.class)
            return stream().map(value -> wrapRealValue(Math.abs((Long) value)));
        if (dtype() == BigInteger.class)
            return stream().map(value -> wrapRealValue(((BigInteger) value).abs()));
        return stream().map(value -> wrapRealValue(((BigDecimal) value).abs()));
    }

    protected void incrementSlicingExpression(Object[] expressions, int dimension, int[] remainingDimsIndices) {
        if (expressions[dimension] instanceof String) {
            incrementSlicingExpression(expressions, dimension + 1, remainingDimsIndices);
        } else if ((int) expressions[dimension] == shape(dimension) - 1) {
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

    @Patterns({"byte", "byteValue"})
    @Replacements({"short", "shortValue"})
    @Replacements({"int", "intValue"})
    @Replacements({"long", "longValue"})
    @Replacements({"float", "floatValue"})
    @Replacements({"double", "doubleValue"})
    @Replacements({"Byte", "byteValue"})
    @Replacements({"Short", "shortValue"})
    @Replacements({"Integer", "intValue"})
    @Replacements({"Long", "longValue"})
    @Replacements({"Float", "floatValue"})
    @Replacements({"Double", "doubleValue"})
    protected void copyTo1DArray(byte[] array, int[] index) {
        for (int i = 0; i < shape[shape.length - 1]; i++) {
            index[shape.length - 1] = i;
            array[i] = ((Number) get(index)).byteValue();
        }
    }

    protected void copyTo1DArray(BigInteger[] array, int[] index) {
        if (dtype().equals(BigInteger.class)) {
            for (int i = 0; i < shape[shape.length - 1]; i++) {
                index[shape.length - 1] = i;
                array[i] = (BigInteger) get(index);
            }
        } else {
            for (int i = 0; i < shape[shape.length - 1]; i++) {
                index[shape.length - 1] = i;
                array[i] = BigInteger.valueOf(((Number) get(index)).longValue());
            }
        }
    }

    protected void copyTo1DArray(BigDecimal[] array, int[] index) {
        if (dtype().equals(BigDecimal.class)) {
            for (int i = 0; i < shape[shape.length - 1]; i++) {
                index[shape.length - 1] = i;
                array[i] = (BigDecimal) get(index);
            }
        } else if (dtype().equals(BigInteger.class)) {
            for (int i = 0; i < shape[shape.length - 1]; i++) {
                index[shape.length - 1] = i;
                array[i] = new BigDecimal((BigInteger) get(index));
            }
        } else {
            for (int i = 0; i < shape[shape.length - 1]; i++) {
                index[shape.length - 1] = i;
                array[i] = BigDecimal.valueOf(((Number) get(index)).doubleValue());
            }
        }
    }

    protected void copyTo1DArray(Complex[] array, int[] index) {
        if (dtype().equals(Complex.class)) {
            for (int i = 0; i < shape[shape.length - 1]; i++) {
                index[shape.length - 1] = i;
                array[i] = (Complex) get(index);
            }
        } else {
            for (int i = 0; i < shape[shape.length - 1]; i++) {
                index[shape.length - 1] = i;
                array[i] = new Complex(((Number) get(index)).doubleValue());
            }
        }
    }

    protected void copyToArray(int dimension, int[] index, Object destination) { // NOSONAR
        if (dimension < shape.length - 1) {
            for (int i = 0; i < shape[dimension]; i++) {
                index[dimension] = i;
                copyToArray(dimension + 1, index, Array.get(destination, i));
            }
        } else {
            Class<?> type = destination.getClass().getComponentType();
            if (!type.equals(Complex.class) && dtype().equals(Complex.class))
                throw new ArrayStoreException(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY);
            else if (destination instanceof byte[])
                copyTo1DArray((byte[]) destination, index);
            else if (destination instanceof short[])
                copyTo1DArray((short[]) destination, index);
            else if (destination instanceof int[])
                copyTo1DArray((int[]) destination, index);
            else if (destination instanceof long[])
                copyTo1DArray((long[]) destination, index);
            else if (destination instanceof float[])
                copyTo1DArray((float[]) destination, index);
            else if (destination instanceof double[])
                copyTo1DArray((double[]) destination, index);
            else if (destination instanceof Byte[])
                copyTo1DArray((Byte[]) destination, index);
            else if (destination instanceof Short[])
                copyTo1DArray((Short[]) destination, index);
            else if (destination instanceof Integer[])
                copyTo1DArray((Integer[]) destination, index);
            else if (destination instanceof Long[])
                copyTo1DArray((Long[]) destination, index);
            else if (destination instanceof Float[])
                copyTo1DArray((Float[]) destination, index);
            else if (destination instanceof Double[])
                copyTo1DArray((Double[]) destination, index);
            else if (destination instanceof BigInteger[])
                copyTo1DArray((BigInteger[]) destination, index);
            else if (destination instanceof BigDecimal[])
                copyTo1DArray((BigDecimal[]) destination, index);
            else
                copyTo1DArray((Complex[]) destination, index);
        }
    }

    @Override
    public void writeToFile(File file) throws IOException {
        new FileOperations<T,T2>().writeToFile(file, this);
    }

    @Replace(pattern = "ByteBuffer", replacements = {"ShortBuffer", "IntBuffer", "LongBuffer", "FloatBuffer", "DoubleBuffer"})
    protected void fillFromBuffer(ByteBuffer buffer) {
        for (int i = 0; i < length(); i++) {
            setUnchecked(wrapValue(buffer.get()), i);
        }
    }

}
