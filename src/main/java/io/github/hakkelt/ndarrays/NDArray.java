package io.github.hakkelt.ndarrays;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * General N-dimensional arrays holding either complex or real values.
 * 
 * The aim of this package to create an general framework to handle multidimensional data.
 * The reference implementation is based on array of primitive values or complex values form apache.math3,
 * it is, however, super easy to write a wrapper to any array- or collection-implementation making it possible to combine
 * the convenience of this interface with the advantages of the selected collection.
 * 
 */
public interface NDArray<T> extends Collection<T> {

    /** 
     * Returns the number of elements within this NDArray.
     * 
     * Alias for length().
     * 
     * @return the number of elements in this collection
     */
    public int size();

    /** 
     * Returns the number of elements within this NDArray.
     * 
     * Alias for size().
     * 
     * @return the number of elements in this collection
     */
    public int length();
    
    /** 
     * Returns the number of dimensions.
     * 
     * @return the number of dimensions
     */
    public int ndims();

    /** 
     * Returns the dimensions of the array.
     * 
     * @return the dimensions of the array
     */
    public int[] dims();

    /** 
     * Returns the size of the array along a specific dimension/axis.
     * 
     * @param axis Selected dimension/axis
     * @return the size of the array along a specific dimension/axis
     */
    public int dims(int axis);

    /** 
     * Returns the type of values returned by get method.
     * 
     * Possible values: <code>Float.class</code>, <code>Double.class</code>, <code>Complex.class</code>
     * 
     * @return the type of values returned by get method.
     */
    public Object eltype();
    
    /** 
     * Compares the specified object with this NDArray for equality.
     * 
     * Two arrays are equal, if they are both real or both complex, and
     * they are element-wise equal. If the specified object is not NDArray
     * then the function returns false.
     * 
     * @param obj Object to be compared for equality
     * @return true if the specified object equals with this NDArray
     */
    public boolean equals(Object obj);
    
    /** 
     * Unsupported operation. Available only for compatibily with general Collection interface.
     * 
     * @return always throws exception
     */
    public int hashCode();

    /**
     * Returns an iterator over the elements in this collection.
     * 
     * The iteration is done in column-first order similarly to linear indexing.
     * This function is most likely not used directly, but it is required to be overloaded
     * in order to make streaming of elements possible.
     * 
     * @return an Iterator over the elements in this collection
     */
    public Iterator<T> iterator();

    /**
     * Creates a Spliterator over the elements in this collection.
     * 
     * The iteration is done in column-first order similarly to linear indexing,
     * and the splits are done equally on the remaining elements.
     * This function is most likely not used directly, but it is required to be overloaded
     * in order to make parallel streaming of elements possible.
     * 
     * @return a Spliterator over the elements in this collection
     */
    @Override
    public Spliterator<T> spliterator();

    /** 
     * Returns an element specified by linear indexing.
     * 
     * <p>Linear indexing: It selects the ith element using the column-major
     * iteration order that linearly spans the entire array.
     * 
     * @param linearIndex linear coordinates
     * @return an element specified by cartesian indexing
     */
    public T get(int linearIndex);

    /** 
     * Returns an element specified by cartesian indexing.
     * 
     * <p>Cartesian indexing: The ordinary way to index into an N-dimensional
     * array is to use exactly N indices; each index selects the position(s)
     * in its particular dimension.
     * 
     * @param indices cartesian coordinates
     * @return an element specified by cartesian indexing
     */
    public T get(int ... indices);

    /** 
     * Sets the value of an element specified by linear indexing.
     * 
     * <p>Linear indexing: It selects the ith element using the column-major
     * iteration order that linearly spans the entire array.
     * in its particular dimension.
     * 
     * @param value value to be assigned
     * @param linearIndex linear coordinates
     */
    public void set(T value, int linearIndex);

    /** 
     * Sets the value of an element specified by cartesian indexing.
     * 
     * <p>Cartesian indexing: The ordinary way to index into an N-dimensional
     * array is to use exactly N indices; each index selects the position(s)
     * in its particular dimension.
     * 
     * @param value value to be assigned
     * @param indices cartesian coordinates
     */
    public void set(T value, int... indices);
    
    /** 
     * Sets the value of an element specified by linear indexing.
     * 
     * <p>Linear indexing: It selects the ith element using the column-major
     * iteration order that linearly spans the entire array.
     * in its particular dimension.
     * 
     * @param value value to be assigned
     * @param linearIndex linear coordinates
     */
    public void set(Number value, int linearIndex);

    /** 
     * Sets the value of an element specified by cartesian indexing.
     * 
     * <p>Cartesian indexing: The ordinary way to index into an N-dimensional
     * array is to use exactly N indices; each index selects the position(s)
     * in its particular dimension.
     * 
     * Note: If used on complex arrays, the imaginary part of the assigned
     * element will be zero.
     * 
     * @param value value to be assigned
     * @param indices cartesian coordinates
     */
    public void set(Number value, int... indices);

    /** 
     * Updates this NDArray with the elements of the NDArray given as parameter.
     * 
     * <p>Note: the parameter NDArray must have the same size and this NDArray!
     * 
     * @param array NDArray from which values are copied to this NDArray
     * @return this NDArray
     */
    public NDArray<T> copyFrom(NDArray<?> array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public NDArray<T> copyFrom(float[] array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public NDArray<T> copyFrom(double[] array);

    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public NDArray<T> copyFrom(byte[] array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public NDArray<T> copyFrom(short[] array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public NDArray<T> copyFrom(int[] array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public NDArray<T> copyFrom(long[] array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public NDArray<T> copyFrom(Object[] array);
    
    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Beware! Entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and returns the new value
     */
    public void apply(UnaryOperator<T> func);
    
    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Beware! Entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its linear index and returns the new value
     */
    public void applyWithLinearIndex(BiFunction<T, Integer, T> func);
    
    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Beware! Entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its Cartesian coordinate and returns the new value
     */
    public void applyWithCartesianIndex(BiFunction<T, int[], T> func);
    
    /** 
     * Converts this NDArray to a multidimensional array of Float, Double or Complex.
     * 
     * @return a multidimensional array of Float, Double or Complex
     */
    public Object[] toArray();

    /** 
     * Returns a String containing type and size information.
     * 
     * @return a String containing type and size information.
     */
    public String toString();

    /** 
     * Returns a String containing tabular representation of the array.
     * 
     * For arrays that have more than 2 dimensions, the function prints 2D slices
     * and iterates over all other dimensions incrementally.
     * Note: this function might produce enormous output 
     * as it doesn't truncate result even for large arrays!
     * 
     * @return a String containing tabular representation of the array.
     */
    public String contentToString();

    /** 
     * Returns a String representation of element type.
     * 
     * @return "RealF32", "RealF64", "ComplexF32", or "ComplexF64" depending on the type of array
     */
    public String dataTypeAsString();

    /** 
     * Creates a new NDArray of the same size, and fills it with the element-wise sum of this NDArray and 
     * the parameter NDArrays and scalars. If list of parameters contains scalar values than these will be
     * added to all elements of the resulting array.
     * 
     * <p>Note: The type of this NDArray determines type resulting NDArray.
     * 
     * @param addends NDArrays and scalars to be added together
     * @return a new NDArray of the same size, and fills it with the element-wise sum of this NDArray and 
     * the parameters NDArrays and scalars
     */
    public NDArray<T> add(Object ...addends);
    
    /** 
     * Updates this NDArray with the element-wise sum of this NDArray and the ones given as parameters.
     * 
     * The list of parameters can also contain scalar values - these will be
     * added to all elements of the resulting array.
     * 
     * <p>Note: The type of this NDArray determines type resulting NDArray.
     * 
     * @param addends NDArrays and scalars to be added to this NDArray
     * @return this NDArray after addition
     */
    public NDArray<T> addInplace(Object ...addends);
    
    /** 
     * Creates a new NDArray of the same size, and fills it with the result of the element-wise substraction 
     * the parameter NDArrays and scalars from this NDArray. If list of parameters contains scalar values than these will be
     * substracted from all elements of this NDArray.
     * 
     * <p>Note: The type of this NDArray determines type resulting NDArray.
     * 
     * @param subtrahends NDArrays and scalars to be substracted from this NDArray
     * @return a new NDArray of the same size, and fills it with the result of the element-wise substraction 
     * the parameter NDArrays and scalars from this NDArray
     */
    public NDArray<T> subtract(Object ...subtrahends);
    
    /** 
     * Updates the elements this NDArray with the result of the element-wise substraction 
     * the parameter NDArrays and scalars from this NDArray. If list of parameters contains scalar values than these will be
     * substracted from all elements of this NDArray.
     * 
     * <p>Note: The type of this NDArray determines type resulting NDArray.
     * 
     * @param subtrahends NDArrays and scalars to be substracted from this NDArray
     * @return this NDArray after substraction
     */
    public NDArray<T> subtractInplace(Object ...subtrahends);
    
    /** 
     * Creates a new NDArray of the same size, and fills it with the element-wise product of this NDArray and 
     * the parameter NDArrays and scalars. If list of parameters contains scalar values than these will be
     * multiplied to all elements of the resulting array.
     * 
     * <p>Note: The type of this NDArray determines type resulting NDArray.
     * 
     * @param multiplicands NDArrays and scalars to be multiplied together
     * @return a new NDArray of the same size, and fills it with the element-wise product of this NDArray and 
     * the parameters NDArrays and scalars
     */
    public NDArray<T> multiply(Object ...multiplicands);
    
    /** 
     * Updates the elements of this NDArray with the element-wise product of this NDArray and 
     * the parameter NDArrays and scalars. If list of parameters contains scalar values than these will be
     * multiplied to all elements of the resulting array.
     * 
     * <p>Note: The type of this NDArray determines type resulting NDArray.
     * 
     * @param multiplicands NDArrays and scalars to be multiplied to this NDArray
     * @return this NDArray after multiplication
     */
    public NDArray<T> multiplyInplace(Object ...multiplicands);
    
    /** 
     * Creates a new NDArray of the same size, and fills it with the result of the element-wise division 
     * of this NDArray by the parameter NDArrays and scalars. If list of parameters contains scalar values than
     * all elements of this NDArray will be divided by them.
     * 
     * <p>Note: The type of this NDArray determines type resulting NDArray.
     * 
     * @param divisors divisors of this NDArray
     * @return a new NDArray of the same size, and fills it with the result of the element-wise division 
     * of this NDArray by the parameter NDArrays and scalars
     */
    public NDArray<T> divide(Object ...divisors);
    
    /** 
     * Updates the elements this NDArray with the result of the element-wise division 
     * of this NDArray by the parameter NDArrays and scalars. If list of parameters contains scalar values than
     * all elements of this NDArray will be divided by them.
     * 
     * <p>Note: The type of this NDArray determines type resulting NDArray.
     * 
     * @param divisors divisors of this NDArray
     * @return this NDArray after division
     */
    public NDArray<T> divideInplace(Object ...divisors);

    /** 
     * Returns the sum of all elements in this NDArray.
     * 
     * @return sum of all elements
     */
    public T sum();
    
    /** 
     * Returns the sum of all elements along the specified dimensions in this NDArray.
     * 
     * <p>For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.sum(2)</code> returns
     * a [5 × 8] array, and <code>B.get(1,1) == A.get(1,1,0) + A.get(1,1,1) + A.get(1,1,2)</code>.
     * 
     * @param selectedDims dimensions along which the summation should be performed
     * @return sum of all elementsalong the specified dimensions
     */
    public NDArray<T> sum(int ...selectedDims);

    /**
     * Returns the 2-norm (Euclidean norm) of the vectorized array.
     * 
     * <p>Note: All N-dimensional arrays are treated as if they were reshaped to a 1D vector.</p>
     * 
     * @return the 2-norm (Euclidean norm) of the vectorized array
     */
    public double norm();

    /**
     * Returns the p-norm of the vectorized array.
     * 
     * <p>Note: All N-dimensional arrays are treated as if they were reshaped to a 1D vector.</p>
     * 
     * Possible values:
     * <ul>
     *  <li>p = 0: Hamming distance of the vector from zero (number of non-zero entries) -- it is not a true norm!</li>
     *  <li>0 &#60; p &#60; 1: Hamming distance of the vector from zero (number of non-zero entries) --
     *          it is only a quasi norm (triangle inequality doesn't hold)!</li>
     *  <li>1: Absolute-value norm (sum of the absolute values of the entries)</li>
     *  <li>2: Euclidean norm (square root of sum of the squared entry values)</li>
     *  <li>1 &#60; p: General p-norm (Σ(|p|)ᵖ)^(1/p)</li>
     *  <li>p = Double.POSITIVE_INFINITY: Infinity norm (returns the entry with the maximal absolute value)</li>
     * </ul>
     * 
     * @param p type of norm
     * @return the p-(quasi)norm of the array
     */
    public double norm(Double p);

    /**
     * Returns the p-norm of the vectorized array.
     * 
     * <p>Note: All N-dimensional arrays are treated as if they were reshaped to a 1D vector.</p>
     * 
     * Possible values:
     * <ul>
     *  <li>p = 0: Hamming distance of the vector from zero (number of non-zero entries) -- it is not a true norm!</li>
     *  <li>0 &#60; p &#60; 1: Hamming distance of the vector from zero (number of non-zero entries) --
     *          it is only a quasi norm (triangle inequality doesn't hold)!</li>
     *  <li>1: Absolute-value norm (sum of the absolute values of the entries)</li>
     *  <li>2: Euclidean norm (square root of sum of the squared entry values)</li>
     *  <li>1 &#60; p: General p-norm (Σ(|p|)ᵖ)^(1/p)</li>
     *  <li>p = Double.POSITIVE_INFINITY: Infinity norm (returns the entry with the maximal absolute value)</li>
     * </ul>
     * 
     * @param p type of norm
     * @return the p-(quasi)norm of the array
     */
    public double norm(int p);
    
    /** 
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    public NDArray<T> fill(T value);
    
    /** 
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    public NDArray<T> fill(float value);
    
    /** 
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    public NDArray<T> fill(double value);

    /** 
     * Returns a new array of the same size as this NDArray filled with zeros.
     * 
     * @return a new array of the same size as this NDArray filled with zeros.
     */
    public NDArray<T> similar();

    /** 
     * Returns a copy of this NDArray.
     * 
     * @return a copy of this NDArray.
     */
    public NDArray<T> copy();
    
    /** 
     * Returns a view that references this NDArray as parent, and
     * skips all singleton dimensions not included in the parameter list.
     * 
     * <p>View: An NDArray that references the specified region its parent array.
     * All modifications in the parent array are reflecten in the view, and vice versa.
     * 
     * <p>Singleton dimension: dimension of size 1.
     * 
     * @param selectedDims dimensions kept in the returend view
     * @return  a view that references this NDArray as parent, and
     * skips all singleton dimensions not included in the parameter list
     */
    public NDArray<T> selectDims(int... selectedDims);
    
    /** 
     * Returns a view that references this NDArray as parent, and
     * skips all singleton dimensions included in the parameter list.
     * 
     * <p>View: An NDArray that references the specified region its parent array.
     * All modifications in the parent array are reflecten in the view, and vice versa.
     * 
     * <p>Singleton dimension: dimension of size 1.
     * 
     * @param selectedDims dimensions skipped in the returend view
     * @return a view that references this NDArray as parent, and
     * skips all singleton dimensions included in the parameter list.
     */
    public NDArray<T> dropDims(int... selectedDims);
    
    /** 
     * Returns a view that references this NDArray as parent, and
     * skips all singleton dimensions.
     * 
     * <p>View: An NDArray that references the specified region its parent array.
     * All modifications in the parent array are reflecten in the view, and vice versa.
     * 
     * <p>Singleton dimension: dimension of size 1.
     * 
     * @return a view that references this NDArray as parent, and
     * skips all singleton dimensions.
     */
    public NDArray<T> squeeze();
    
    /** 
     * Returns an array view referencing this NDArray as parent that gives read-write access
     * to a specific multi-dimensional slice of the array.
     * 
     * <p>View: An NDArray that references the specified region its parent array.
     * All modifications in the parent array are reflecten in the view, and vice versa.
     * 
     * <p>Possible slicing expressions:
     * <ul>
     *  <li>integer values: selects a specific slice along the positionally selected dimension</li>
     *  <li>range (string that consists of two integer values specifying the start and the end of the range
     *       separated by a colon): selects a specific range of slices along the positionally selected dimension</li>
     *  <li>all-range (string literal ":"): selects all slices along the positionally selected dimension</li>
     * </ul>
     * 
     * <p>For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.slice(1, "2:5", ":")</code> returns
     * a [3 × 3] array, and <code>B.get(1,1) == A.get(1,3,1)</code>.
     * 
     * @param slicingExpressions Slicing expressions
     * @return an array view that gives read-write access to a specific multi-dimensional slice of the array
     */
    public NDArray<T> slice(Object... slicingExpressions);
    
    /** 
     * Returns a view that references this NDArray as parent but has a different shape.
     * 
     * <p>View: An NDArray that references the specified region its parent array.
     * All modifications in the parent array are reflecten in the view, and vice versa.
     * 
     * <p>For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.reshape(40, 3)</code> returns
     * a [40 × 3] array, and <code>B.get(10) == B.get(10,0) == A.get(1,0,0) == A.get(10)</code>.
     * 
     * <p>Note: the linear indexing of the two arrays remain the same, and the corresponding cartesian indices
     * determined by column-first ordering.
     * 
     * @param newShape new shape/dimensions
     * @return a view that references this NDArray as parent but has a different shape
     */
    public NDArray<T> reshape(int... newShape);
    
    /** 
     * Returns a view that references this NDArray as parent, but the order of 
     * dimensions are swiched in this view.
     * 
     * <p>View: An NDArray that references the specified region its parent array.
     * All modifications in the parent array are reflecten in the view, and vice versa.
     * 
     * <p>For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.permuteDims(1, 0, 2)</code> returns
     * a [8 × 5 × 3] array, and <code>B.get(3,1,0) == A.get(1,3,0)</code>.
     * 
     * @param permutation new order of dimensions / permutation vector
     * @return a view that references this NDArray as parent, but the order of 
     * dimensions are swiched in this view.
     */
    public NDArray<T> permuteDims(int... permutation);
    
    /** 
     * Creates a new NDArray that contains the elements of this NDArrays and all other NDArrays
     * concatenated along the dimension/axis specified by the first parameter.
     * 
     * @param axis Axis/dimension along which the concatenation should occur.
     * @param arrays Arrays to be concatenated to this NDArray.
     * @return a new NDArray that contains the elements of this NDArrays and all other NDArrays
     * concatenated along the dimension/axis specified by the first parameter.
     */
    public NDArray<T> concatenate(int axis, NDArray<?>... arrays);

    /**
     * Returns a stream of linear indices.
     * 
     * Alias for array.streamLinearIndices()
     * 
     * @return a stream of linear indices
     */
    public IntStream streamLinearIndices();

    
    /**
     * Returns a stream of cartesian indices.
     * 
     * Each item in the stream is a int[] that holds cartesian indices.
     * 
     * <p>Cartesian indexing: The ordinary way to index into an N-dimensional
     * array is to use exactly N indices; each index selects the position(s)
     * in its particular dimension.
     * 
     * @return a stream of cartesian indices
     */
    public Stream<int[]> streamCartesianIndices();

}
