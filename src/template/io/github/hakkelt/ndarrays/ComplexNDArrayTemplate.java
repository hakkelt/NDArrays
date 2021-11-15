package io.github.hakkelt.ndarrays;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.internal.ArrayOperations;
import io.github.hakkelt.ndarrays.internal.CopyFromOperations;
import io.github.hakkelt.ndarrays.internal.ViewOperations;

/**
 * General N-dimensional arrays holding complex values.
 * 
 * The aim of this package to create an general framework to handle multidimensional data.
 * The reference implementation is based on array of complex values form apache.math3,
 * it is, however, super easy to write a wrapper to any array- or collection-implementation making it possible to combine
 * the convenience of this interface with the advantages of the selected collection.
 * 
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays", newName = "ComplexNDArray")
public interface ComplexNDArrayTemplate<T extends Number> extends NDArray<Complex> {

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
    @Patterns({"getReal", "real"})
    @Replacements({"getImag", "imaginary"})
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
    @Patterns({"getReal", "real"})
    @Replacements({"getImag", "imaginary"})
    public T getReal(int... indices);
    
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
    @Patterns({"setReal", "real"})
    @Replacements({"setImag", "imaginary"})
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
    @Patterns({"setReal", "real"})
    @Replacements({"setImag", "imaginary"})
    public void setReal(Number value, int... indices);

    @Override
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long"})
    public default ComplexNDArrayTemplate<T> copyFrom(float[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    @Override
    public default ComplexNDArrayTemplate<T> copyFrom(Object[] array) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, array);
    }

    @Override
    public default ComplexNDArrayTemplate<T> copyFrom(NDArray<?> array) {
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
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long"})
    public default ComplexNDArrayTemplate<T> copyFrom(float[] real, float[] imag) {
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
    @Replace(pattern = "Object[]", replacements = "NDArray<? extends Number>")
    public default ComplexNDArrayTemplate<T> copyFrom(Object[] real, Object[] imag) {
        return new CopyFromOperations<Complex,T>().copyFrom(this, real, imag);
    }

    @Override
    public ComplexNDArrayTemplate<T> fillUsingLinearIndices(IntFunction<Complex> func);
    
    @Override
    public ComplexNDArrayTemplate<T> fillUsingCartesianIndices(Function<int[], Complex> func);
    
    @Override
    public ComplexNDArrayTemplate<T> apply(UnaryOperator<Complex> func);
    
    @Override
    public ComplexNDArrayTemplate<T> applyWithLinearIndices(BiFunction<Complex, Integer, Complex> func);
    
    @Override
    public ComplexNDArrayTemplate<T> applyWithCartesianIndices(BiFunction<Complex, int[], Complex> func);
    
    @Override
    public default ComplexNDArrayTemplate<T> map(UnaryOperator<Complex> func) {
        ComplexNDArrayTemplate<T> newInstance = copy();
        newInstance.apply(func);
        return newInstance;
    }
    
    @Override
    public default ComplexNDArrayTemplate<T> mapWithLinearIndices(BiFunction<Complex, Integer, Complex> func) {
        ComplexNDArrayTemplate<T> newInstance = copy();
        newInstance.applyWithLinearIndices(func);
        return newInstance;
    }
    
    @Override
    public default ComplexNDArrayTemplate<T> mapWithCartesianIndices(BiFunction<Complex, int[], Complex> func) {
        ComplexNDArrayTemplate<T> newInstance = copy();
        newInstance.applyWithCartesianIndices(func);
        return newInstance;
    }
    
    /** 
     * Returns a new array holding the real part of the array
     * 
     * @return the real part of the array
     */
    @Replace(pattern = "real", replacements = "imaginary")
    public NDArray<T> real();
    
    /** 
     * Returns a new array holding the magnitude / absolute values of the elements.
     * 
     * @return a new array holding the magnitude / absolute values of the elements.
     */
    @Patterns({"magnitude / absolute values", "abs"})
    @Replacements({"argument / argument", "argument"})
    public NDArray<T> abs();

    @Override
    @Patterns({"add", "addends"})
    @Replacements({"addInplace", "addends"})
    @Replacements({"subtract", "substrahends"})
    @Replacements({"subtractInplace", "substrahends"})
    @Replacements({"multiply", "multiplicands"})
    @Replacements({"multiplyInplace", "multiplicands"})
    @Replacements({"divide", "divisors"})
    @Replacements({"divideInplace", "divisors"})
    public default ComplexNDArrayTemplate<T> add(Object... addends) {
        return new ArrayOperations<Complex,T>().add(this, addends);
    }
    
    @Override
    @Patterns({"add", "addend"})
    @Replacements({"addInplace", "addend"})
    @Replacements({"subtract", "substrahend"})
    @Replacements({"subtractInplace", "substrahend"})
    @Replacements({"multiply", "multiplicand"})
    @Replacements({"multiplyInplace", "multiplicand"})
    @Replacements({"divide", "divisor"})
    @Replacements({"divideInplace", "divisor"})
    public default ComplexNDArrayTemplate<T> add(NDArray<?> addend) {
        return add((Object) addend);
    }
    
    @Override
    @Patterns({"add", "addend", "byte", "Byte"})
    @Replacements({"add", "addend", "short", "Short"})
    @Replacements({"add", "addend", "int", "Integer"})
    @Replacements({"add", "addend", "long", "Long"})
    @Replacements({"add", "addend", "float", "Float"})
    @Replacements({"add", "addend", "double", "Double"})
    @Replacements({"addInplace", "addend", "byte", "Byte"})
    @Replacements({"addInplace", "addend", "short", "Short"})
    @Replacements({"addInplace", "addend", "int", "Integer"})
    @Replacements({"addInplace", "addend", "long", "Long"})
    @Replacements({"addInplace", "addend", "float", "Float"})
    @Replacements({"addInplace", "addend", "double", "Double"})
    @Replacements({"subtract", "substrahend", "byte", "Byte"})
    @Replacements({"subtract", "substrahend", "short", "Short"})
    @Replacements({"subtract", "substrahend", "int", "Integer"})
    @Replacements({"subtract", "substrahend", "long", "Long"})
    @Replacements({"subtract", "substrahend", "float", "Float"})
    @Replacements({"subtract", "substrahend", "double", "Double"})
    @Replacements({"subtractInplace", "substrahend", "byte", "Byte"})
    @Replacements({"subtractInplace", "substrahend", "short", "Short"})
    @Replacements({"subtractInplace", "substrahend", "int", "Integer"})
    @Replacements({"subtractInplace", "substrahend", "long", "Long"})
    @Replacements({"subtractInplace", "substrahend", "float", "Float"})
    @Replacements({"subtractInplace", "substrahend", "double", "Double"})
    @Replacements({"multiply", "multiplicand", "byte", "Byte"})
    @Replacements({"multiply", "multiplicand", "short", "Short"})
    @Replacements({"multiply", "multiplicand", "int", "Integer"})
    @Replacements({"multiply", "multiplicand", "long", "Long"})
    @Replacements({"multiply", "multiplicand", "float", "Float"})
    @Replacements({"multiply", "multiplicand", "double", "Double"})
    @Replacements({"multiplyInplace", "multiplicand", "byte", "Byte"})
    @Replacements({"multiplyInplace", "multiplicand", "short", "Short"})
    @Replacements({"multiplyInplace", "multiplicand", "int", "Integer"})
    @Replacements({"multiplyInplace", "multiplicand", "long", "Long"})
    @Replacements({"multiplyInplace", "multiplicand", "float", "Float"})
    @Replacements({"multiplyInplace", "multiplicand", "double", "Double"})
    @Replacements({"divide", "divisor", "byte", "Byte"})
    @Replacements({"divide", "divisor", "short", "Short"})
    @Replacements({"divide", "divisor", "int", "Integer"})
    @Replacements({"divide", "divisor", "long", "Long"})
    @Replacements({"divide", "divisor", "float", "Float"})
    @Replacements({"divide", "divisor", "double", "Double"})
    @Replacements({"divideInplace", "divisor", "byte", "Byte"})
    @Replacements({"divideInplace", "divisor", "short", "Short"})
    @Replacements({"divideInplace", "divisor", "int", "Integer"})
    @Replacements({"divideInplace", "divisor", "long", "Long"})
    @Replacements({"divideInplace", "divisor", "float", "Float"})
    @Replacements({"divideInplace", "divisor", "double", "Double"})
    public default ComplexNDArrayTemplate<T> add(byte addend) {
        return add((Object) Byte.valueOf(addend));
    }

    @Override
    public default ComplexNDArrayTemplate<T> sum(int... selectedDims) {
        return new ArrayOperations<Complex,T>().sum(this, selectedDims);
    }
    
    /** 
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    @Replace(pattern = "double", replacements = {"T", "Complex"})
    public default ComplexNDArrayTemplate<T> fill(double value) {
        return new ArrayOperations<Complex,T>().fill(this, value);
    }

    @Override
    public ComplexNDArrayTemplate<T> similar();

    @Override
    public ComplexNDArrayTemplate<T> copy();

    @Override
    public default ComplexNDArrayTemplate<T> slice(Object ...slicingExpressions) {
        return new ViewOperations<Complex,T>().slice(this, slicingExpressions);
    }

    @Override
    public default ComplexNDArrayTemplate<T> mask(NDArray<?> mask) {
        return new ViewOperations<Complex,T>().mask(this, mask, false);
    }

    @Override
    public default ComplexNDArrayTemplate<T> mask(Predicate<Complex> func) {
        return new ViewOperations<Complex,T>().mask(this, func);
    }

    @Override
    public default ComplexNDArrayTemplate<T> maskWithLinearIndices(BiPredicate<Complex, Integer> func) {
        return new ViewOperations<Complex,T>().mask(this, func, true);
    }

    @Override
    public default ComplexNDArrayTemplate<T> maskWithCartesianIndices(BiPredicate<Complex, int[]> func) {
        return new ViewOperations<Complex,T>().mask(this, func, false);
    }

    @Override
    public default ComplexNDArrayTemplate<T> inverseMask(NDArray<?> mask) {
        return new ViewOperations<Complex,T>().mask(this, mask, true);
    }

    @Override
    public default ComplexNDArrayTemplate<T> permuteDims(int... permutation) {
        return new ViewOperations<Complex,T>().permuteDims(this, permutation);
    }

    @Override
    public default ComplexNDArrayTemplate<T> reshape(int... newShape) {
        return new ViewOperations<Complex,T>().reshape(this, newShape);
    }

    @Override
    public default ComplexNDArrayTemplate<T> concatenate(int axis, NDArray<?> ...arrays) {
        return new ArrayOperations<Complex,T>().concatenate(this, axis, arrays);
    }

    @Override
    public default ComplexNDArrayTemplate<T> selectDims(int... selectedDims) {
        return new ViewOperations<Complex,T>().selectDims(this, selectedDims);
    }

    @Override
    public default ComplexNDArrayTemplate<T> dropDims(int... selectedDims) {
        return new ViewOperations<Complex,T>().dropDims(this, selectedDims);
    }

    @Override
    public default ComplexNDArrayTemplate<T> squeeze() {
        return new ViewOperations<Complex,T>().squeeze(this);
    }

}
