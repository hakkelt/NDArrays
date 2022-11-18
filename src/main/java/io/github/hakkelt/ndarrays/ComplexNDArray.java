/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\template\io\github\hakkelt\ndarrays\ComplexNDArray.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays;

import io.github.hakkelt.ndarrays.internal.ArrayOperations;
import io.github.hakkelt.ndarrays.internal.CopyFromOperations;
import io.github.hakkelt.ndarrays.internal.ViewOperations;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntFunction;
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
     * Sets the imaginary part of an element specified by linear indexing.
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
     * @param value imaginary value to be assigned as imaginary part of some element
     * @param linearIndex linear index
     */
    public void setImag(Number value, int linearIndex);

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
     * Sets the imaginary part of an element specified by cartesian indexing.
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
     * @param value imaginary value to be assigned as imaginary part of some element
     * @param indices cartesian coordinates
     */
    public void setImag(Number value, int... indices);

    @Override
    public default ComplexNDArray<T> copyFrom(float[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    @Override
    public default ComplexNDArray<T> copyFrom(double[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    @Override
    public default ComplexNDArray<T> copyFrom(byte[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    @Override
    public default ComplexNDArray<T> copyFrom(short[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    @Override
    public default ComplexNDArray<T> copyFrom(int[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    @Override
    public default ComplexNDArray<T> copyFrom(long[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    @Override
    public default ComplexNDArray<T> copyFrom(Object[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    @Override
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
     * 
     * @param real array holding the new values to be copied to this NDArray as real part
     * @param imag array holding the new values to be copied to this NDArray as imaginary part
     * @return this NDArray
     */
    public default ComplexNDArray<T> copyFrom(NDArray<? extends Number> real, NDArray<? extends Number> imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }

    @Override
    public ComplexNDArray<T> fillUsingLinearIndices(IntFunction<Complex> func);

    @Override
    public ComplexNDArray<T> fillUsingCartesianIndices(Function<int[],Complex> func);

    @Override
    public ComplexNDArray<T> apply(UnaryOperator<Complex> func);

    @Override
    public ComplexNDArray<T> applyWithLinearIndices(BiFunction<Complex,Integer,Complex> func);

    @Override
    public ComplexNDArray<T> applyWithCartesianIndices(BiFunction<Complex,int[],Complex> func);

    /**
     * Apply the given function to each slices of the array that overrides each entry with the calculated new values.
     * 
     * Please note that slices might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its Cartesian coordinate, and modifies the
     *            passed NDArray
     * @param iterationDims dimensions along which iteration is performed
     * @return itself after the update
     */
    public ComplexNDArray<T> applyOnComplexSlices(BiConsumer<ComplexNDArray<T>,int[]> func, int... iterationDims);

    /**
     * Apply the given function to each slices of the array, and override each entry with the returned slice.
     * 
     * Please note that slices might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its Cartesian coordinate, and returns a
     *            slice from which values are copied to original array
     * @param iterationDims dimensions along which iteration is performed
     * @return itself after the update
     */
    public ComplexNDArray<T> applyOnComplexSlices(BiFunction<ComplexNDArray<T>,int[],NDArray<?>> func, int... iterationDims);

    @Override
    public default ComplexNDArray<T> map(UnaryOperator<Complex> func) {
        ComplexNDArray<T> newInstance = copy();
        newInstance.apply(func);
        return newInstance;
    }

    @Override
    public default ComplexNDArray<T> mapWithLinearIndices(BiFunction<Complex,Integer,Complex> func) {
        ComplexNDArray<T> newInstance = copy();
        newInstance.applyWithLinearIndices(func);
        return newInstance;
    }

    @Override
    public default ComplexNDArray<T> mapWithCartesianIndices(BiFunction<Complex,int[],Complex> func) {
        ComplexNDArray<T> newInstance = copy();
        newInstance.applyWithCartesianIndices(func);
        return newInstance;
    }

    /**
     * Apply the given function to each slices of the array, and create a new NDArray with the calculated new values.
     * 
     * Please note that slices might not be processed in a sequential order!
     * 
     * @param func function that receives slice and its Cartesian coordinate along the iteration dimensions, and overwrites
     *            the values in the slice with the calculated new values
     * @param iterationDims dimensions along which iteration is performed
     * @return the new NDArray with the calculated new values
     */
    public ComplexNDArray<T> mapOnComplexSlices(BiConsumer<ComplexNDArray<T>,int[]> func, int... iterationDims);

    /**
     * Apply the given function to each slices of the array, and create a new NDArray with the calculated new values.
     * 
     * Please note that slices might not be processed in a sequential order!
     * 
     * @param func function that receives slice and its Cartesian coordinate along the iteration dimensions, and returns
     *            a new array with the same size as the slice
     * @param iterationDims dimensions along which iteration is performed
     * @return the new NDArray with the calculated new values
     */
    public ComplexNDArray<T> mapOnComplexSlices(BiFunction<ComplexNDArray<T>,int[],NDArray<?>> func, int... iterationDims);

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
     * @return a new array holding the magnitude / absolute values of the elements.
     */
    public NDArray<T> abs();

    /**
     * Returns a new array holding the argument / argument of the elements.
     * 
     * @return a new array holding the argument / argument of the elements.
     */
    public NDArray<T> argument();

    @Override
    public default ComplexNDArray<T> add(Object... addends) {
        return new ArrayOperations<Complex,T>().add(this, addends);
    }

    @Override
    public default ComplexNDArray<T> addInplace(Object... addends) {
        return new ArrayOperations<Complex,T>().addInplace(this, addends);
    }

    @Override
    public default ComplexNDArray<T> subtract(Object... substrahends) {
        return new ArrayOperations<Complex,T>().subtract(this, substrahends);
    }

    @Override
    public default ComplexNDArray<T> subtractInplace(Object... substrahends) {
        return new ArrayOperations<Complex,T>().subtractInplace(this, substrahends);
    }

    @Override
    public default ComplexNDArray<T> multiply(Object... multiplicands) {
        return new ArrayOperations<Complex,T>().multiply(this, multiplicands);
    }

    @Override
    public default ComplexNDArray<T> multiplyInplace(Object... multiplicands) {
        return new ArrayOperations<Complex,T>().multiplyInplace(this, multiplicands);
    }

    @Override
    public default ComplexNDArray<T> divide(Object... divisors) {
        return new ArrayOperations<Complex,T>().divide(this, divisors);
    }

    @Override
    public default ComplexNDArray<T> divideInplace(Object... divisors) {
        return new ArrayOperations<Complex,T>().divideInplace(this, divisors);
    }

    @Override
    public default ComplexNDArray<T> add(NDArray<?> addend) {
        return add((Object) addend);
    }

    @Override
    public default ComplexNDArray<T> addInplace(NDArray<?> addend) {
        return addInplace((Object) addend);
    }

    @Override
    public default ComplexNDArray<T> subtract(NDArray<?> substrahend) {
        return subtract((Object) substrahend);
    }

    @Override
    public default ComplexNDArray<T> subtractInplace(NDArray<?> substrahend) {
        return subtractInplace((Object) substrahend);
    }

    @Override
    public default ComplexNDArray<T> multiply(NDArray<?> multiplicand) {
        return multiply((Object) multiplicand);
    }

    @Override
    public default ComplexNDArray<T> multiplyInplace(NDArray<?> multiplicand) {
        return multiplyInplace((Object) multiplicand);
    }

    @Override
    public default ComplexNDArray<T> divide(NDArray<?> divisor) {
        return divide((Object) divisor);
    }

    @Override
    public default ComplexNDArray<T> divideInplace(NDArray<?> divisor) {
        return divideInplace((Object) divisor);
    }

    @Override
    public default ComplexNDArray<T> add(byte addend) {
        return add((Object) Byte.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> add(short addend) {
        return add((Object) Short.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> add(int addend) {
        return add((Object) Integer.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> add(long addend) {
        return add((Object) Long.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> add(float addend) {
        return add((Object) Float.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> add(double addend) {
        return add((Object) Double.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> addInplace(byte addend) {
        return addInplace((Object) Byte.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> addInplace(short addend) {
        return addInplace((Object) Short.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> addInplace(int addend) {
        return addInplace((Object) Integer.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> addInplace(long addend) {
        return addInplace((Object) Long.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> addInplace(float addend) {
        return addInplace((Object) Float.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> addInplace(double addend) {
        return addInplace((Object) Double.valueOf(addend));
    }

    @Override
    public default ComplexNDArray<T> subtract(byte substrahend) {
        return subtract((Object) Byte.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> subtract(short substrahend) {
        return subtract((Object) Short.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> subtract(int substrahend) {
        return subtract((Object) Integer.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> subtract(long substrahend) {
        return subtract((Object) Long.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> subtract(float substrahend) {
        return subtract((Object) Float.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> subtract(double substrahend) {
        return subtract((Object) Double.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> subtractInplace(byte substrahend) {
        return subtractInplace((Object) Byte.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> subtractInplace(short substrahend) {
        return subtractInplace((Object) Short.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> subtractInplace(int substrahend) {
        return subtractInplace((Object) Integer.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> subtractInplace(long substrahend) {
        return subtractInplace((Object) Long.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> subtractInplace(float substrahend) {
        return subtractInplace((Object) Float.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> subtractInplace(double substrahend) {
        return subtractInplace((Object) Double.valueOf(substrahend));
    }

    @Override
    public default ComplexNDArray<T> multiply(byte multiplicand) {
        return multiply((Object) Byte.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> multiply(short multiplicand) {
        return multiply((Object) Short.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> multiply(int multiplicand) {
        return multiply((Object) Integer.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> multiply(long multiplicand) {
        return multiply((Object) Long.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> multiply(float multiplicand) {
        return multiply((Object) Float.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> multiply(double multiplicand) {
        return multiply((Object) Double.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> multiplyInplace(byte multiplicand) {
        return multiplyInplace((Object) Byte.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> multiplyInplace(short multiplicand) {
        return multiplyInplace((Object) Short.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> multiplyInplace(int multiplicand) {
        return multiplyInplace((Object) Integer.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> multiplyInplace(long multiplicand) {
        return multiplyInplace((Object) Long.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> multiplyInplace(float multiplicand) {
        return multiplyInplace((Object) Float.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> multiplyInplace(double multiplicand) {
        return multiplyInplace((Object) Double.valueOf(multiplicand));
    }

    @Override
    public default ComplexNDArray<T> divide(byte divisor) {
        return divide((Object) Byte.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> divide(short divisor) {
        return divide((Object) Short.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> divide(int divisor) {
        return divide((Object) Integer.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> divide(long divisor) {
        return divide((Object) Long.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> divide(float divisor) {
        return divide((Object) Float.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> divide(double divisor) {
        return divide((Object) Double.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> divideInplace(byte divisor) {
        return divideInplace((Object) Byte.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> divideInplace(short divisor) {
        return divideInplace((Object) Short.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> divideInplace(int divisor) {
        return divideInplace((Object) Integer.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> divideInplace(long divisor) {
        return divideInplace((Object) Long.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> divideInplace(float divisor) {
        return divideInplace((Object) Float.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> divideInplace(double divisor) {
        return divideInplace((Object) Double.valueOf(divisor));
    }

    @Override
    public default ComplexNDArray<T> sum(int... selectedDims) {
        return new ArrayOperations<Complex,T>().sum(this, selectedDims);
    }

    /**
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    public default ComplexNDArray<T> fill(double value) {
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

    /**
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    public default ComplexNDArray<T> fill(Complex value) {
        return new ArrayOperations<Complex,T>().fill(this, value);
    }

    @Override
    public ComplexNDArray<T> similar();

    @Override
    public ComplexNDArray<T> copy();

    @Override
    public default ComplexNDArray<T> slice(Object... slicingExpressions) {
        return new ViewOperations<Complex,T>().slice(this, slicingExpressions);
    }

    @Override
    public default ComplexNDArray<T> mask(NDArray<?> mask) {
        return new ViewOperations<Complex,T>().mask(this, mask, false);
    }

    @Override
    public default ComplexNDArray<T> mask(Predicate<Complex> func) {
        return new ViewOperations<Complex,T>().mask(this, func);
    }

    @Override
    public default ComplexNDArray<T> maskWithLinearIndices(BiPredicate<Complex,Integer> func) {
        return new ViewOperations<Complex,T>().mask(this, func, true);
    }

    @Override
    public default ComplexNDArray<T> maskWithCartesianIndices(BiPredicate<Complex,int[]> func) {
        return new ViewOperations<Complex,T>().mask(this, func, false);
    }

    @Override
    public default ComplexNDArray<T> inverseMask(NDArray<?> mask) {
        return new ViewOperations<Complex,T>().mask(this, mask, true);
    }

    @Override
    public default ComplexNDArray<T> permuteDims(int... permutation) {
        return new ViewOperations<Complex,T>().permuteDims(this, permutation);
    }

    @Override
    public default ComplexNDArray<T> reshape(int... newShape) {
        return new ViewOperations<Complex,T>().reshape(this, newShape);
    }

    @Override
    public default ComplexNDArray<T> concatenate(int axis, NDArray<?>... arrays) {
        return new ArrayOperations<Complex,T>().concatenate(this, axis, arrays);
    }

    @Override
    public default ComplexNDArray<T> selectDims(int... selectedDims) {
        return new ViewOperations<Complex,T>().selectDims(this, selectedDims);
    }

    @Override
    public default ComplexNDArray<T> dropDims(int... selectedDims) {
        return new ViewOperations<Complex,T>().dropDims(this, selectedDims);
    }

    @Override
    public default ComplexNDArray<T> squeeze() {
        return new ViewOperations<Complex,T>().squeeze(this);
    }

}
