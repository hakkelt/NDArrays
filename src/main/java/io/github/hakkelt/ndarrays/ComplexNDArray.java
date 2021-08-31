package io.github.hakkelt.ndarrays;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.apache.commons.math3.complex.Complex;

/**
 * General N-dimensional arrays holding either complex or real values.
 * 
 * The aim of this package to create an general framework to handle multidimensional data.
 * The reference implementation is based on array of primitive values or complex values form apache.math3,
 * it is, however, super easy to write a wrapper to any array- or collection-implementation making it possible to combine
 * the convenience of this interface with the advantages of the selected collection.
 * 
 */
public interface ComplexNDArray<T extends Number> extends NDArray<Complex> {

    /** 
     * Returns the real part of an element specified by linear indexing.
     * 
     * <p>Linear indexing: It selects the ith element using the column-major
     * iteration order that linearly spans the entire array.
     * 
     * @param linearIndex linear index
     * @return the real part of an element specified by linear indexing
     */
    public T getReal(int linearIndex);

    /** 
     * Returns the real part of an element specified by cartesian indexing.
     * 
     * <p>Cartesian indexing: The ordinary way to index into an N-dimensional
     * array is to use exactly N indices; each index selects the position(s)
     * in its particular dimension.
     * 
     * @param indices cartesian coordinates
     * @return the real part of an element specified by cartesian indexing
     */
    public T getReal(int... indices);

    /** 
     * Returns the imaginary part of an element specified by linear indexing.
     * 
     * <p>Linear indexing: It selects the ith element using the column-major
     * iteration order that linearly spans the entire array.
     * 
     * @param linearIndex linear index
     * @return the imaginary part of an element specified by linear indexing
     */
    public T getImag(int linearIndex);
    
    /** 
     * Returns the imaginary part of an element specified by cartesian indexing.
     * 
     * <p>Cartesian indexing: The ordinary way to index into an N-dimensional
     * array is to use exactly N indices; each index selects the position(s)
     * in its particular dimension.
     * 
     * @param indices cartesian coordinates
     * @return the imaginary part of an element specified by cartesian indexing
     */
    public T getImag(int... indices);
    
    /** 
     * Sets the real part of an element specified by linear indexing.
     * 
     * <p>Linear indexing: It selects the ith element using the column-major
     * iteration order that linearly spans the entire array.
     * 
     * <p>Note: If used on complex arrays, the imaginary part of the assigned
     * element will be untouched.
     * 
     * @param value real value to be assigned as real part of some element
     * @param linearIndex linear index
     */
    public void setReal(Number value, int linearIndex);

    /** 
     * Sets the real part of an element specified by cartesian indexing.
     * 
     * <p>Cartesian indexing: The ordinary way to index into an N-dimensional
     * array is to use exactly N indices; each index selects the position(s)
     * in its particular dimension.
     * 
     * <p>Note: If used on complex arrays, the imaginary part of the assigned
     * element will be untouched.
     * 
     * @param value real value to be assigned as real part of some element
     * @param indices cartesian coordinates
     */
    public void setReal(Number value, int... indices);
    
    /** 
     * Sets the imaginary part of an element specified by linear indexing.
     * 
     * <p>Linear indexing: It selects the ith element using the column-major
     * iteration order that linearly spans the entire array.
     * 
     * <p>Note: If used on complex arrays, the real part of the assigned
     * element will be untouched.
     * 
     * @param value real value to be assigned as imaginary part of some element
     * @param linearIndex linear index
     */
    public void setImag(Number value, int linearIndex);

    /** 
     * Sets the imaginary part of an element specified by cartesian indexing.
     * 
     * <p>Cartesian indexing: The ordinary way to index into an N-dimensional
     * array is to use exactly N indices; each index selects the position(s)
     * in its particular dimension.
     * 
     * <p>Note: If used on complex arrays, the real part of the assigned
     * element will be untouched.
     * 
     * @param value real value to be assigned as imaginary part of some element
     * @param indices cartesian coordinates
     */
    public void setImag(Number value, int... indices);

    /** 
     * Updates this NDArray with the elements of the NDArray given as parameter.
     * 
     * <p>Note: the parameter NDArray must have the same size and this NDArray!
     * 
     * @param array NDArray from which values are copied to this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(NDArray<?> array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(float[] array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(double[] array);

    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(byte[] array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(short[] array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(int[] array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(long[] array);
    
    /** 
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>Note: the parameter array must have the same size and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(Object[] array);
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same size and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(float[] real, float[] imag);
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same size and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(double[] real, double[] imag);
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same size and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(byte[] real, byte[] imag);
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same size and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(short[] real, short[] imag);
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same size and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(int[] real, int[] imag);
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same size and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(long[] real, long[] imag);

    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same size and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(Object[] real, Object[] imag);
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same size and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public ComplexNDArray<T> copyFrom(NDArray<? extends Number> real, NDArray<? extends Number> imag);
    
    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Beware! Entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and returns the new value
     * @return itself after the update
     */
    public ComplexNDArray<T> apply(UnaryOperator<Complex> func);
    
    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Beware! Entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its linear index and returns the new value
     * @return itself after the update
     */
    public ComplexNDArray<T> applyWithLinearIndices(BiFunction<Complex, Integer, Complex> func);
    
    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Beware! Entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its Cartesian coordinate and returns the new value
     * @return itself after the update
     */
    public ComplexNDArray<T> applyWithCartesianIndices(BiFunction<Complex, int[], Complex> func);
    
    /**
     * Apply the given function to each element of the array, and create a new NDArray with the calculated new values.
     * 
     * Beware! Entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and returns the new value
     * @return the new NDArray with the calculated new values
     */
    public ComplexNDArray<T> map(UnaryOperator<Complex> func);
    
    /**
     * Apply the given function to each element of the array, and create a new NDArray with the calculated new values.
     * 
     * Beware! Entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its linear index and returns the new value
     * @return the new NDArray with the calculated new values
     */
    public ComplexNDArray<T> mapWithLinearIndices(BiFunction<Complex, Integer, Complex> func);
    
    /**
     * Apply the given function to each element of the array, and create a new NDArray with the calculated new values.
     * 
     * Beware! Entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its Cartesian coordinate and returns the new value
     * @return the new NDArray with the calculated new values
     */
    public ComplexNDArray<T> mapWithCartesianIndices(BiFunction<Complex, int[], Complex> func);
    
    /** 
     * Returns a new array holding the real part of the array
     * 
     * @return the real part of the array
     */
    public NDArray<T> real();

    /** 
     * Returns a new array holding the imaginary part of the array
     * 
     * @return the imaginary part of the array
     */
    public NDArray<T> imaginary();
    
    /** 
     * Returns a new array holding the magnitude / absolute values of elements.
     * 
     * For real arrays, it returns an array filled with zeros.
     * 
     * @return a new array holding the magnitude / absolute values of elements.
     */
    public NDArray<T> abs();

    /** 
     * Returns a new array holding the angle / argument of elements.
     * 
     * For real arrays, it returns an array filled with zeros.
     * 
     * @return a new array holding the angle / argument of elements.
     */
    public NDArray<T> angle();

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
    public ComplexNDArray<T> add(Object ...addends);
    
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
    public ComplexNDArray<T> addInplace(Object ...addends);
    
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
    public ComplexNDArray<T> subtract(Object ...subtrahends);
    
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
    public ComplexNDArray<T> subtractInplace(Object ...subtrahends);
    
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
    public ComplexNDArray<T> multiply(Object ...multiplicands);
    
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
    public ComplexNDArray<T> multiplyInplace(Object ...multiplicands);
    
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
    public ComplexNDArray<T> divide(Object ...divisors);
    
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
    public ComplexNDArray<T> divideInplace(Object ...divisors);
    
    /** 
     * Returns the sum of all elements along the specified dimensions in this NDArray.
     * 
     * <p>For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.sum(2)</code> returns
     * a [5 × 8] array, and <code>B.get(1,1) == A.get(1,1,0) + A.get(1,1,1) + A.get(1,1,2)</code>.
     * 
     * @param selectedDims dimensions along which the summation should be performed
     * @return sum of all elementsalong the specified dimensions
     */
    public ComplexNDArray<T> sum(int ...selectedDims);
    
    /** 
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> fill(Complex value);
    
    /** 
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> fill(T value);
    
    /** 
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> fill(float value);
    
    /** 
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    public ComplexNDArray<T> fill(double value);

    /** 
     * Returns a new array of the same size as this NDArray filled with zeros.
     * 
     * @return a new array of the same size as this NDArray filled with zeros.
     */
    public ComplexNDArray<T> similar();

    /** 
     * Returns a copy of this NDArray.
     * 
     * @return a copy of this NDArray.
     */
    public ComplexNDArray<T> copy();
    
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
    public ComplexNDArray<T> selectDims(int... selectedDims);
    
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
    public ComplexNDArray<T> dropDims(int... selectedDims);
    
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
    public ComplexNDArray<T> squeeze();
    
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
    public ComplexNDArray<T> slice(Object... slicingExpressions);
    
    /** 
     * Returns an array view referencing this NDArray as parent that gives read-write access
     * to a specific elements of the array selected by the given mask.
     * 
     * The mask must have the same shape as this array, and those entries are selected
     * which has the same indices as the non-zero entries in the mask. In other words: All places where
     * the mask contains a zero value are skipped, and all other values are copied into a new vector.
     * 
     * @param mask mask
     * @return an array view that gives read-write access to a specific elements of the array selected by the given mask
     */
    public ComplexNDArray<T> mask(NDArray<?> mask);
    
    /** 
     * Returns an array view referencing this NDArray as parent that gives read-write access
     * to a specific elements for which the given function returns true.
     * 
     * The mask must have the same shape as this array, and those entries are selected
     * which has the same indices as the non-zero entries in the mask. In other words: All places where
     * the mask contains a zero value are skipped, and all other values are copied into a new vector.
     * 
     * @param func function that accepts the values of entries as input and returns boolean
     * @return an array view that gives read-write access to a specific elements of the array selected by the given mask
     */
    public ComplexNDArray<T> mask(Predicate<Complex> func);
    
    /** 
     * Returns an array view referencing this NDArray as parent that gives read-write access
     * to a specific elements for which the given function returns true.
     * 
     * @param mask mask
     * @return an array view that gives read-write access to a specific elements for which the given function returns true
     */
    public ComplexNDArray<T> maskWithLinearIndices(BiPredicate<Complex,Integer> func);
    
    /** 
     * Returns an array view referencing this NDArray as parent that gives read-write access
     * to a specific elements for which the given function returns true.
     * 
     * @param mask mask
     * @return an array view that gives read-write access to a specific elements for which the given function returns true
     */
    public ComplexNDArray<T> maskWithCartesianIndices(BiPredicate<Complex,int[]> func);
    
    /** 
     * Returns an array view referencing this NDArray as parent that gives read-write access
     * to a specific elements of the array selected by the given mask.
     * 
     * The mask must have the same shape as this array, and those entries are selected
     * which has the same indices as the zero entries in the mask. In other words: All places where
     * the mask contains a non-zero value are skipped, and all other values are copied into a new vector.
     * 
     * @param mask mask
     * @return an array view that gives read-write access to a specific elements of the array selected by the given mask
     */
    public ComplexNDArray<T> inverseMask(NDArray<?> mask);
    
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
    public ComplexNDArray<T> reshape(int... newShape);
    
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
    public ComplexNDArray<T> permuteDims(int... permutation);
    
    /** 
     * Creates a new NDArray that contains the elements of this NDArrays and all other NDArrays
     * concatenated along the dimension/axis specified by the first parameter.
     * 
     * @param axis Axis/dimension along which the concatenation should occur.
     * @param arrays Arrays to be concatenated to this NDArray.
     * @return a new NDArray that contains the elements of this NDArrays and all other NDArrays
     * concatenated along the dimension/axis specified by the first parameter.
     */
    public ComplexNDArray<T> concatenate(int axis, NDArray<?>... arrays);

}
