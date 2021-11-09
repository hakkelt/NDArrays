package io.github.hakkelt.ndarrays;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.apache.commons.math3.complex.Complex;

/**
 * General N-dimensional arrays holding complex values.
 * 
 * The aim of this package to create an general framework to handle multidimensional data.
 * The reference implementation is based on array of complex values form apache.math3,
 * it is, however, super easy to write a wrapper to any array- or collection-implementation making it possible to combine
 * the convenience of this interface with the advantages of the selected collection.
 * 
 */
public interface ComplexNDArray<T extends Number> extends NDArray<Complex> {

    /** 
     * Returns the type of values returned by getReal and getImag methods.
     * 
     * Possible values: <code>Float.class</code>, <code>Double.class</code>.
     * 
     * @return the type of values returned by get method.
     */
    public Class<?> dtype2();

    /** 
     * Returns the real part of an element specified by linear indexing.
     * 
     * <p>Linear indexing: It selects the ith element using the column-major
     * iteration order that linearly spans the entire array.
     * 
     * <p>Negative indexing is supported: e.g. -1 refers to the last element,
     * -2 refers to the item before the last one, etc. 
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
     * <p>Negative indexing is supported: e.g. assuming a 3×4 NDArray,
     * index [2,-1] equals to [2,3], and [-1,-3] is an equivalent of [2,1].
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
     * <p>Negative indexing is supported: e.g. -1 refers to the last element,
     * -2 refers to the item before the last one, etc. 
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
     * <p>Negative indexing is supported: e.g. assuming a 3×4 NDArray,
     * index [2,-1] equals to [2,3], and [-1,-3] is an equivalent of [2,1].
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
     * <p>Negative indexing is supported: e.g. -1 refers to the last element,
     * -2 refers to the item before the last one, etc. 
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
     * <p>Negative indexing is supported: e.g. assuming a 3×4 NDArray,
     * index [2,-1] equals to [2,3], and [-1,-3] is an equivalent of [2,1].
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
     * <p>Negative indexing is supported: e.g. -1 refers to the last element,
     * -2 refers to the item before the last one, etc. 
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
     * <p>Negative indexing is supported: e.g. assuming a 3×4 NDArray,
     * index [2,-1] equals to [2,3], and [-1,-3] is an equivalent of [2,1].
     * 
     * <p>Note: If used on complex arrays, the real part of the assigned
     * element will be untouched.
     * 
     * @param value real value to be assigned as imaginary part of some element
     * @param indices cartesian coordinates
     */
    public void setImag(Number value, int... indices);

    public default ComplexNDArray<T> copyFrom(float[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(double[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(byte[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(short[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(int[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(long[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(Object[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    public default ComplexNDArray<T> copyFrom(NDArray<?> array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same shape and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public default ComplexNDArray<T> copyFrom(float[] real, float[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same shape and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public default ComplexNDArray<T> copyFrom(double[] real, double[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same shape and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public default ComplexNDArray<T> copyFrom(byte[] real, byte[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same shape and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public default ComplexNDArray<T> copyFrom(short[] real, short[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same shape and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public default ComplexNDArray<T> copyFrom(int[] real, int[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same shape and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public default ComplexNDArray<T> copyFrom(long[] real, long[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }

    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same shape and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public default ComplexNDArray<T> copyFrom(Object[] real, Object[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }
    
    /** 
     * Updates this NDArray with the elements of the arrays given as parameters.
     * 
     * <p>Note: both parameter arrays must have the same shape and this NDArray!
     * <p>Note: if this function is called on real arrays, it will throw UnsupportedOperationException!
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public default ComplexNDArray<T> copyFrom(NDArray<? extends Number> real, NDArray<? extends Number> imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }
    
    public default ComplexNDArray<T> apply(UnaryOperator<Complex> func) {
        streamLinearIndices().parallel().forEach(linearIndex -> set(func.apply(get(linearIndex)), linearIndex));
        return this;
    }
    
    public default ComplexNDArray<T> applyWithLinearIndices(BiFunction<Complex, Integer, Complex> func) {
        streamLinearIndices().parallel().forEach(linearIndex -> set(func.apply(get(linearIndex), linearIndex), linearIndex));
        return this;
    }
    
    public default ComplexNDArray<T> applyWithCartesianIndices(BiFunction<Complex, int[], Complex> func) {
        streamCartesianIndices().parallel().forEach(indices -> set(func.apply(get(indices), indices), indices));
        return this;
    }
    
    public default ComplexNDArray<T> map(UnaryOperator<Complex> func) {
        ComplexNDArray<T> newInstance = copy();
        newInstance.apply(func);
        return newInstance;
    }
    
    public default ComplexNDArray<T> mapWithLinearIndices(BiFunction<Complex, Integer, Complex> func) {
        ComplexNDArray<T> newInstance = copy();
        newInstance.applyWithLinearIndices(func);
        return newInstance;
    }
    
    public default ComplexNDArray<T> mapWithCartesianIndices(BiFunction<Complex, int[], Complex> func) {
        ComplexNDArray<T> newInstance = copy();
        newInstance.applyWithCartesianIndices(func);
        return newInstance;
    }
    
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
     * Returns a new array holding the magnitude / absolute values of the elements.
     * 
     * For real arrays, it returns an array filled with zeros.
     * 
     * @return a new array holding the magnitude / absolute values of the elements.
     */
    public NDArray<T> abs();

    /** 
     * Returns a new array holding the angle / argument of the elements.
     * 
     * For real arrays, it returns an array filled with zeros.
     * 
     * @return a new array holding the angle / argument of the elements.
     */
    public NDArray<T> angle();

    public default ComplexNDArray<T> add(Object ...addends) {
        return new ArrayOperations<Complex,T>().add(this, addends);
    }

    public default ComplexNDArray<T> addInplace(Object ...addends) {
        return new ArrayOperations<Complex,T>().addInplace(this, addends);
    }

    public default ComplexNDArray<T> subtract(Object ...substrahends) {
        return new ArrayOperations<Complex,T>().subtract(this, substrahends);
    }
    
    public default ComplexNDArray<T> subtractInplace(Object ...substrahends) {
        return new ArrayOperations<Complex,T>().subtractInplace(this, substrahends);
    }
    public default ComplexNDArray<T> multiply(Object... multiplicands) {
        return new ArrayOperations<Complex,T>().multiply(this, multiplicands);
    }

    public default ComplexNDArray<T> multiplyInplace(Object... multiplicands) {
        return new ArrayOperations<Complex,T>().multiplyInplace(this, multiplicands);
    }

    public default ComplexNDArray<T> divide(Object... divisors) {
        return new ArrayOperations<Complex,T>().divide(this, divisors);
    }

    public default ComplexNDArray<T> divideInplace(Object... divisors) {
        return new ArrayOperations<Complex,T>().divideInplace(this, divisors);
    }

    public default ComplexNDArray<T> sum(int... selectedDims) {
        return new ArrayOperations<Complex,T>().sum(this, selectedDims);
    }
    
    /** 
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    public default ComplexNDArray<T> fill(Complex value) {
        return new ArrayOperations<Complex,T>().fill(this, value);
    }
    
    /** 
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */

    public default ComplexNDArray<T> fill(T value) {
        return new ArrayOperations<Complex,T>().fill(this, value);
    }

    public default ComplexNDArray<T> fill(float value) {
        return new ArrayOperations<Complex,T>().fill(this, value);
    }

    public default ComplexNDArray<T> fill(double value) {
        return new ArrayOperations<Complex,T>().fill(this, value);
    }

    public ComplexNDArray<T> similar();
    public ComplexNDArray<T> copy();

    public default ComplexNDArray<T> slice(Object ...slicingExpressions) {
        return new ViewOperations<Complex,T>().slice(this, slicingExpressions);
    }

    public default ComplexNDArray<T> mask(NDArray<?> mask) {
        return new ViewOperations<Complex,T>().mask(this, mask, false);
    }

    public default ComplexNDArray<T> mask(Predicate<Complex> func) {
        return new ViewOperations<Complex,T>().mask(this, func);
    }

    public default ComplexNDArray<T> maskWithLinearIndices(BiPredicate<Complex, Integer> func) {
        return new ViewOperations<Complex,T>().mask(this, func, true);
    }

    public default ComplexNDArray<T> maskWithCartesianIndices(BiPredicate<Complex, int[]> func) {
        return new ViewOperations<Complex,T>().mask(this, func, false);
    }

    public default ComplexNDArray<T> inverseMask(NDArray<?> mask) {
        return new ViewOperations<Complex,T>().mask(this, mask, true);
    }

    public default ComplexNDArray<T> permuteDims(int... permutation) {
        return new ViewOperations<Complex,T>().permuteDims(this, permutation);
    }

    public default ComplexNDArray<T> reshape(int... newShape) {
        return new ViewOperations<Complex,T>().reshape(this, newShape);
    }

    public default ComplexNDArray<T> concatenate(int axis, NDArray<?> ...arrays) {
        return new ArrayOperations<Complex,T>().concatenate(this, axis, arrays);
    }

    public default ComplexNDArray<T> selectDims(int... selectedDims) {
        return new ViewOperations<Complex,T>().selectDims(this, selectedDims);
    }

    public default ComplexNDArray<T> dropDims(int... selectedDims) {
        return new ViewOperations<Complex,T>().dropDims(this, selectedDims);
    }

    public default ComplexNDArray<T> squeeze() {
        return new ViewOperations<Complex,T>().squeeze(this);
    }

}
