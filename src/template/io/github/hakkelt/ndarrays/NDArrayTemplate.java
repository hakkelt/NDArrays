package io.github.hakkelt.ndarrays;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import io.github.hakkelt.generator.*;

import io.github.hakkelt.ndarrays.internal.CartesianIndexSpliterator;

/**
 * General N-dimensional arrays holding either complex or real values.
 * 
 * The aim of this package to create an general framework to handle multidimensional data. The reference implementation
 * is based on array of primitive values or complex values form apache.math3, it is, however, super easy to write a
 * wrapper to any array- or collection-implementation making it possible to combine the convenience of this interface
 * with the advantages of the selected collection.
 * 
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays", newName = "NDArray")
public interface NDArrayTemplate<T> extends Iterable<T> {

    /**
     * Returns a stream of int arrays that represent all Cartesian indices of a virtual n-dimensional array with the
     * specified shape.
     * 
     * @param shape Shape of the virtual n-dimensional array for which Cartesian indices are streamed
     * @return a stream of int arrays that represent all Cartesian indices of a virtual NDArray
     */
    public static Stream<int[]> streamCartesianIndices(int... shape) {
        return StreamSupport.stream(new CartesianIndexSpliterator(shape), false);
    }

    /**
     * Returns a string that identifies the implementation behind this current object. The output of the toString()
     * function starts with this string.
     * 
     * @return a string that identifies the implementation
     */
    public String getNamePrefix();

    /**
     * Returns the number of elements within this NDArray.
     * 
     * @return the number of elements in this collection
     */
    public int length();

    /**
     * Returns the number of dimensions.
     * 
     * @return the number of dimensions
     */
    public int ndim();

    /**
     * Returns the dimensions of the array.
     * 
     * @return the dimensions of the array
     */
    public int[] shape();

    /**
     * Returns the shape of the array along a specific dimension/axis.
     * 
     * @param axis Selected dimension/axis
     * @return the shape of the array along a specific dimension/axis
     */
    public int shape(int axis);

    /**
     * Returns the type of values returned by get method.
     * 
     * Possible values: <code>Float.class</code>, <code>Double.class</code>, <code>Complex.class</code>
     * <code>Byte.class</code>, <code>Short.class</code>, <code>Integer.class</code>, <code>Long.class</code>.
     * 
     * @return the type of values returned by get method.
     */
    public Class<?> dtype();

    /**
     * Compares the specified object with this NDArray for equality.
     * 
     * Two arrays are equal, if they are both real or both complex, and they are element-wise equal. If the specified
     * object is not NDArray then the function returns false.
     * 
     * @param obj Object to be compared for equality
     * @return true if the specified object equals with this NDArray
     */
    public boolean equals(Object obj);

    /**
     * This method is unsupported because NDArrays should not be used as keys in hash-based collections.
     * 
     * @return always throws UnsupportedOperationsException
     */
    public int hashCode();

    /**
     * Returns an iterator over the elements in this collection.
     * 
     * The iteration is done in column-first order similarly to linear indexing. This function is most likely not used
     * directly, but it is required to be overloaded in order to make streaming of elements possible.
     * 
     * @return an Iterator over the elements in this collection
     */
    @Override
    public Iterator<T> iterator();

    /**
     * Creates a Spliterator over the elements in this collection.
     * 
     * The iteration is done in column-first order similarly to linear indexing, and the splits are done equally on the
     * remaining elements. This function is most likely not used directly, but it is required to be overloaded in order
     * to make parallel streaming of elements possible.
     * 
     * @return a Spliterator over the elements in this collection
     */
    @Override
    public Spliterator<T> spliterator();

    /**
     * Returns a sequential Stream with this collection as its source.
     * 
     * @return a sequential Stream with this collection as its source.
     */
    public Stream<T> stream();

    /**
     * Returns a parallel Stream with this collection as its source.
     * 
     * @return a parallel Stream with this collection as its source.
     */
    public Stream<T> parallelStream();

    /**
     * Returns an element specified by linear indexing.
     * 
     * <p>
     * Linear indexing: It selects the ith element using the column-major iteration order that linearly spans the entire
     * array.
     * 
     * <p>
     * Negative indexing is supported, e.g. -1 refers to the last element, -2 refers to the item before the last one,
     * etc.
     * 
     * @param linearIndex linear coordinates
     * @return an element specified by cartesian indexing
     */
    public T get(int linearIndex);

    /**
     * Returns an element specified by cartesian indexing.
     * 
     * <p>
     * Cartesian indexing: The ordinary way to index into an N-dimensional array is to use exactly N indices; each index
     * selects the position(s) in its particular dimension.
     * 
     * <p>
     * Negative indexing is supported, e.g. assuming a 3×4 NDArray, index [2,-1] equals to [2,3], and [-1,-3] is an
     * equivalent of [2,1].
     * 
     * @param indices cartesian coordinates
     * @return an element specified by cartesian indexing
     */
    public T get(int... indices);

    /**
     * Sets the value of an element specified by linear indexing.
     * 
     * <p>
     * Linear indexing: It selects the ith element using the column-major iteration order that linearly spans the entire
     * array. in its particular dimension.
     * 
     * <p>
     * Negative indexing is supported, e.g. -1 refers to the last element, -2 refers to the item before the last one,
     * etc.
     * 
     * @param value value to be assigned
     * @param linearIndex linear coordinates
     */
    @Replace(pattern = "Number", replacements = { "T" })
    public void set(Number value, int linearIndex);

    /**
     * Sets the value of an element specified by cartesian indexing.
     * 
     * <p>
     * Cartesian indexing: The ordinary way to index into an N-dimensional array is to use exactly N indices; each index
     * selects the position(s) in its particular dimension.
     * 
     * <p>
     * Negative indexing is supported, e.g. assuming a 3×4 NDArray, index [2,-1] equals to [2,3], and [-1,-3] is an
     * equivalent of [2,1].
     * 
     * @param value value to be assigned
     * @param indices cartesian coordinates
     */
    @Replace(pattern = "Number", replacements = { "T" })
    public void set(Number value, int... indices);

    public boolean contains(Object o);

    /**
     * Updates this NDArray with the elements of the NDArray given as parameter.
     * 
     * <p>
     * Note: the parameter NDArray must have the same shape and this NDArray!
     * 
     * @param array NDArray from which values are copied to this NDArray
     * @return this NDArray
     */
    public NDArrayTemplate<T> copyFrom(NDArrayTemplate<?> array);

    /**
     * Updates this NDArray with the elements of the array given as parameter.
     * 
     * <p>
     * Note: the parameter array must have the same shape and this NDArray!
     * 
     * @param array array holding the new values to be copied to this NDArray
     * @return this NDArray
     */
    @Replace(pattern = "byte", replacements = { "short", "int", "long", "float", "double", "Object" })
    public NDArrayTemplate<T> copyFrom(byte[] array);

    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Please note that entries might not be processed in a sequential order!
     * 
     * @param func function that receives the linear index of the current entry, and returns the new value
     * @return itself after the update
     */
    public NDArrayTemplate<T> fillUsingLinearIndices(IntFunction<T> func);

    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Please note that entries might not be processed in a sequential order!
     * 
     * @param func function that receives the Cartesian coordinate of the current entry, and returns the new value
     * @return itself after the update
     */
    public NDArrayTemplate<T> fillUsingCartesianIndices(Function<int[], T> func);

    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Please note that entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry, and returns the new value
     * @return itself after the update
     */
    public NDArrayTemplate<T> apply(UnaryOperator<T> func);

    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Please note that entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its linear index, and returns the new value
     * @return itself after the update
     */
    public NDArrayTemplate<T> applyWithLinearIndices(BiFunction<T, Integer, T> func);

    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Please note that entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its Cartesian coordinate, and returns the
     *            new value
     * @return itself after the update
     */
    public NDArrayTemplate<T> applyWithCartesianIndices(BiFunction<T, int[], T> func);

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
    public NDArray<T> applyOnSlices(BiFunction<NDArray<T>, int[], NDArray<?>> func, int... iterationDims);

    /**
     * Apply the given function to each element of the array, and create a new NDArray with the calculated new values.
     * 
     * Please note that entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry, and returns the new value
     * @return the new NDArray with the calculated new values
     */
    public NDArrayTemplate<T> map(UnaryOperator<T> func);

    /**
     * Apply the given function to each element of the array, and create a new NDArray with the calculated new values.
     * 
     * Please note that entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its linear index, and returns the new value
     * @return the new NDArray with the calculated new values
     */
    public NDArrayTemplate<T> mapWithLinearIndices(BiFunction<T, Integer, T> func);

    /**
     * Apply the given function to each element of the array, and create a new NDArray with the calculated new values.
     * 
     * Please note that entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its Cartesian coordinate, and returns the
     *            new value
     * @return the new NDArray with the calculated new values
     */
    public NDArrayTemplate<T> mapWithCartesianIndices(BiFunction<T, int[], T> func);

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
    public NDArrayTemplate<T> mapOnSlices(BiFunction<NDArrayTemplate<T>, int[], NDArrayTemplate<?>> func,
            int... iterationDims);

    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Please note that entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry
     */
    @Override
    public void forEach(Consumer<? super T> func);

    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Please note that entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its linear index
     */
    public void forEachWithLinearIndices(ObjIntConsumer<T> func);

    /**
     * Apply the given function to each element of the array, and override each entry with the calculated new values.
     * 
     * Please note that entries might not be processed in a sequential order!
     * 
     * @param func function that receives the value of the current entry and its Cartesian coordinate
     */
    public void forEachWithCartesianIndices(BiConsumer<T, int[]> func);

    /**
     * Returns an array containing all of the elements in this collection. If this collection makes any guarantees as to
     * what order its elements are returned by its iterator, this method must return the elements in the same order. The
     * returned array's runtime component type is Object.
     *
     * <p>
     * The returned array will be "safe" in that no references to it are maintained by this collection. (In other words,
     * this method must allocate a new array even if this collection is backed by an array). The caller is thus free to
     * modify the returned array.
     * 
     * @return an array, whose runtime component type is Object, containing all of the elements in this collection
     */
    public Object[] toArray();

    /**
     * Returns an array containing all of the elements in this collection; the runtime type of the returned array is
     * that of the specified array. If the collection fits in the specified array, it is returned therein. Otherwise, a
     * new array is allocated with the runtime type of the specified array and the shape of this collection.
     * 
     * <p>
     * If this collection fits in the specified array with room to spare (i.e., the array has more elements than this
     * collection), the element in the array immediately following the end of the collection is set to null. (This is
     * useful in determining the length of this collection only if the caller knows that this collection does not
     * contain any null elements.)
     * 
     * <p>
     * If this collection makes any guarantees as to what order its elements are returned by its iterator, this method
     * must return the elements in the same order.
     * 
     * @param <A> the component type of the array to contain the collection
     * @param array the array into which the elements of this collection are to be stored, if it is big enough;
     *            otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return an array containing all of the elements in this collection
     */
    public <A> A toArray(A array);

    /**
     * Returns an array containing all of the elements in this collection, using the provided generator function to
     * allocate the returned array.
     * 
     * <p>
     * If this collection makes any guarantees as to what order its elements are returned by its iterator, this method
     * must return the elements in the same order.
     * 
     * @param <A> the component type of the array to contain the collection
     * @param generator a function which produces a new array of the desired type and the provided length
     * @return an array containing all of the elements in this collection
     */
    public <A> A toArray(IntFunction<A> generator);

    /**
     * Returns a String containing type and shape information.
     * 
     * @return a String containing type and shape information.
     */
    public String toString();

    /**
     * Returns a String containing tabular representation of the array.
     * 
     * For arrays that have more than 2 dimensions, the function prints 2D slices and iterates over all other dimensions
     * incrementally. Note: this function might produce enormous output as it doesn't truncate result even for large
     * arrays!
     * 
     * @return a String containing tabular representation of the array.
     */
    public String contentToString();

    /**
     * Returns a String containing tabular representation of the array.
     * 
     * For arrays that have more than 2 dimensions, the function prints 2D slices and iterates over all other dimensions
     * incrementally. Note: this function might produce enormous output as it doesn't truncate result even for large
     * arrays!
     * 
     * @param format format specifier for individual elements (e.g. 5.3f)
     * @return a String containing tabular representation of the array.
     */
    public String contentToString(String format);

    /**
     * Returns a String representation of element type.
     * 
     * @return "Byte", "Short", "Integer", "Long", "Float", "Double", "Complex Float", or "Complex Double" depending on
     *         the type of array
     */
    public String dataTypeAsString();

    /**
     * Creates a new NDArray of the same shape and fills it with the values of this NDArray incremented by the given
     * value.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param addend scalar value to be added to each element of the NDArray
     * @return a new NDArray of the same shape and fills it with the values of this NDArray incremented by the given
     *         value
     */
    @Replace(pattern = "byte", replacements = { "short", "int", "long", "float", "double" })
    public default NDArrayTemplate<T> add(byte addend) {
        return add((Object) addend);
    }

    /**
     * Creates a new NDArray of the same shape and fills it with the element-wise sum of this NDArray and the NDArray
     * given as parameter.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param addend NDArray to be added to this NDArray
     * @return a new NDArray of the same shape and fills it with the element-wise sum of this NDArray and the NDArray
     *         given as parameter.
     */
    public default NDArrayTemplate<T> add(NDArrayTemplate<?> addend) {
        return add((Object) addend);
    }

    /**
     * Creates a new NDArray of the same shape and fills it with the element-wise sum of this NDArray and the parameter
     * NDArrays and scalars. If list of parameters contains scalar values than these will be added to all elements of
     * the resulting array.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param addends NDArrays and scalars to be added to this NDArray
     * @return a new NDArray of the same shape, and fills it with the element-wise sum of this NDArray and the
     *         parameters NDArrays and scalars
     */
    public NDArrayTemplate<T> add(Object... addends);

    /**
     * Updates this NDArray by incrementing all entries with the given value.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param addend scalar value to be added to each element of the NDArray
     * @return this NDArray after addition
     */
    @Replace(pattern = "byte", replacements = { "short", "int", "long", "float", "double" })
    public default NDArrayTemplate<T> addInplace(byte addend) {
        return addInplace((Object) addend);
    }

    /**
     * Updates this NDArray with the element-wise sum of this NDArray and the one given as parameter.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param addend NDArray to be added to this NDArray
     * @return this NDArray after addition
     */
    public default NDArrayTemplate<T> addInplace(NDArrayTemplate<?> addend) {
        return addInplace((Object) addend);
    }

    /**
     * Updates this NDArray with the element-wise sum of this NDArray and the ones given as parameters.
     * 
     * The list of parameters can also contain scalar values - these will be added to all elements of the resulting
     * array.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param addends NDArrays and scalars to be added to this NDArray
     * @return this NDArray after addition
     */
    public NDArrayTemplate<T> addInplace(Object... addends);

    /**
     * Creates a new NDArray of the same shape and fills it with the values of this NDArray minus the given value.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param subtrahend scalar value to be subtracted from each element of the NDArray
     * @return a new NDArray of the same shape and fills it with the values of this NDArray minus the given value
     */
    @Replace(pattern = "byte", replacements = { "short", "int", "long", "float", "double" })
    public default NDArrayTemplate<T> subtract(byte subtrahend) {
        return subtract((Object) subtrahend);
    }

    /**
     * Creates a new NDArray of the same shape and fills it with the element-wise subtraction of the given NDArray from
     * this NDArray.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param subtrahend NDArray to be subtracted from this NDArray
     * @return a new NDArray filled with the element-wise subtraction of the given NDArray from this NDArray.
     */
    public default NDArrayTemplate<T> subtract(NDArrayTemplate<?> subtrahend) {
        return subtract((Object) subtrahend);
    }

    /**
     * Creates a new NDArray of the same shape and fills it with the result of the element-wise substraction the
     * parameter NDArrays and scalars from this NDArray. If list of parameters contains scalar values than these will be
     * substracted from all elements of this NDArray.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param subtrahends NDArrays and scalars to be substracted from this NDArray
     * @return a new NDArray filled with the result of the element-wise substraction of the parameter NDArrays and
     *         scalars from this NDArray
     */
    public NDArrayTemplate<T> subtract(Object... subtrahends);

    /**
     * Updates this NDArray by subtracting the given value from all entries of this NDArray.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param subtrahend scalar value to be subtracted from each element of the NDArray
     * @return this NDArray after subtraction
     */
    @Replace(pattern = "byte", replacements = { "short", "int", "long", "float", "double" })
    public default NDArrayTemplate<T> subtractInplace(byte subtrahend) {
        return subtractInplace((Object) subtrahend);
    }

    /**
     * Updates this NDArray with the element-wise subtraction of the given NDArray from this NDArray.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param subtrahend NDArray to be subtracted from this NDArray
     * @return this NDArray after subtraction
     */
    public default NDArrayTemplate<T> subtractInplace(NDArrayTemplate<?> subtrahend) {
        return subtractInplace((Object) subtrahend);
    }

    /**
     * Updates the elements this NDArray with the result of the element-wise subtraction the parameter NDArrays and
     * scalars from this NDArray. If list of parameters contains scalar values than these will be substracted from all
     * elements of this NDArray.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param subtrahends NDArrays and scalars to be substracted from this NDArray
     * @return this NDArray after substraction
     */
    public NDArrayTemplate<T> subtractInplace(Object... subtrahends);

    /**
     * Creates a new NDArray of the same shape and fills it with the values of this NDArray multiplied by the given
     * value.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param multiplicand scalar value with which each element of the NDArray are multiplied
     * @return a new NDArray of the same shape and fills it with the values of this NDArray multiplied by the given
     *         value
     */
    @Replace(pattern = "byte", replacements = { "short", "int", "long", "float", "double" })
    public default NDArrayTemplate<T> multiply(byte multiplicand) {
        return multiply((Object) multiplicand);
    }

    /**
     * Creates a new NDArray of the same shape and fills it with the element-wise product of this NDArray and the
     * NDArray given as parameter.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param multiplicand NDArray to be multiplied with this NDArray
     * @return a new NDArray of the same shape and fills it with the element-wise product of this NDArray and the
     *         NDArray given as parameter.
     */
    public default NDArrayTemplate<T> multiply(NDArrayTemplate<?> multiplicand) {
        return multiply((Object) multiplicand);
    }

    /**
     * Creates a new NDArray of the same shape and fills it with the element-wise product of this NDArray and the
     * parameter NDArrays and scalars. If list of parameters contains scalar values than these will be multiplied to all
     * elements of the resulting array.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param multiplicands NDArrays and scalars to be multiplied together
     * @return a new NDArray of the same shape, and fills it with the element-wise product of this NDArray and the
     *         parameters NDArrays and scalars
     */
    public NDArrayTemplate<T> multiply(Object... multiplicands);

    /**
     * Updates the elements of this NDArray with the values of this NDArray multiplied by the given value.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param multiplicand scalar value with which each element of the NDArray are multiplied
     * @return this NDArray after multiplication
     */
    @Replace(pattern = "byte", replacements = { "short", "int", "long", "float", "double" })
    public default NDArrayTemplate<T> multiplyInplace(byte multiplicand) {
        return multiplyInplace((Object) multiplicand);
    }

    /**
     * Updates the elements of this NDArray with the element-wise product of this NDArray and the NDArray given as
     * parameter.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param multiplicand NDArray to be multiplied with this NDArray
     * @return this NDArray after multiplication
     */
    public default NDArrayTemplate<T> multiplyInplace(NDArrayTemplate<?> multiplicand) {
        return multiplyInplace((Object) multiplicand);
    }

    /**
     * Updates the elements of this NDArray with the element-wise product of this NDArray and the parameter NDArrays and
     * scalars. If list of parameters contains scalar values than these will be multiplied to all elements of the
     * resulting array.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param multiplicands NDArrays and scalars to be multiplied with this NDArray
     * @return this NDArray after multiplication
     */
    public NDArrayTemplate<T> multiplyInplace(Object... multiplicands);

    /**
     * Creates a new NDArray of the same shape and fills it with the values of this NDArray divided by the given value.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param divisor scalar value with which each element of the NDArray are divided
     * @return a new NDArray of the same shape and fills it with the values of this NDArray divided by the given value
     */
    @Replace(pattern = "byte", replacements = { "short", "int", "long", "float", "double" })
    public default NDArrayTemplate<T> divide(byte divisor) {
        return divide((Object) divisor);
    }

    /**
     * Creates a new NDArray of the same shape and fills it with the element-wise division of this NDArray and the
     * NDArray given as parameter.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param divisor divisor of this NDArray
     * @return a new NDArray of the same shape and fills it with the element-wise division of this NDArray and the
     *         NDArray given as parameter.
     */
    public default NDArrayTemplate<T> divide(NDArrayTemplate<?> divisor) {
        return divide((Object) divisor);
    }

    /**
     * Creates a new NDArray of the same shape and fills it with the result of the element-wise division of this NDArray
     * by the parameter NDArrays and scalars. If list of parameters contains scalar values than all elements of this
     * NDArray will be divided by them.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param divisors divisors of this NDArray
     * @return a new NDArray of the same shape, and fills it with the result of the element-wise division of this
     *         NDArray by the parameter NDArrays and scalars
     */
    public NDArrayTemplate<T> divide(Object... divisors);

    /**
     * Updates the elements this NDArray with the values of this NDArray divided by the given value.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param divisor scalar value with which each element of the NDArray are divided
     * @return this NDArray after division
     */
    @Replace(pattern = "byte", replacements = { "short", "int", "long", "float", "double" })
    public default NDArrayTemplate<T> divideInplace(byte divisor) {
        return divideInplace((Object) divisor);
    }

    /**
     * Updates the elements this NDArray with the element-wise division of this NDArray and the NDArray given as
     * parameter.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param divisor divisor of this NDArray
     * @return this NDArray after division
     */
    public default NDArrayTemplate<T> divideInplace(NDArrayTemplate<?> divisor) {
        return divideInplace((Object) divisor);
    }

    /**
     * Updates the elements this NDArray with the result of the element-wise division of this NDArray by the parameter
     * NDArrays and scalars. If list of parameters contains scalar values than all elements of this NDArray will be
     * divided by them.
     * 
     * <p>
     * Note: The type of this NDArray determines type of the resulting NDArray.
     * 
     * @param divisors divisors of this NDArray
     * @return this NDArray after division
     */
    public NDArrayTemplate<T> divideInplace(Object... divisors);

    /**
     * Returns the sum of all elements in this NDArray.
     * 
     * @return sum of all elements
     */
    public T sum();

    /**
     * Returns the sum of all elements along the specified dimensions in this NDArray.
     * 
     * <p>
     * For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.sum(2)</code> returns a [5 × 8] array,
     * and <code>B.get(1,1) == A.get(1,1,0) + A.get(1,1,1) + A.get(1,1,2)</code>.
     * 
     * @param selectedDims dimensions along which the summation should be performed
     * @return sum of all elementsalong the specified dimensions
     */
    public NDArrayTemplate<T> sum(int... selectedDims);

    /**
     * Returns the product of all elements in this NDArray.
     * 
     * @return product of all elements
     */
    public T prod();

    /**
     * Returns the product of all elements along the specified dimensions in this NDArray.
     * 
     * <p>
     * For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.prod(2)</code> returns a [5 × 8] array,
     * and <code>B.get(1,1) == A.get(1,1,0) * A.get(1,1,1) * A.get(1,1,2)</code>.
     * 
     * @param selectedDims dimensions along which multiplication should be performed
     * @return product of all elements along the specified dimensions
     */
    public NDArray<T> prod(int... selectedDims);

    /**
     * Returns the result of accumulation of all elements in this NDArray.
     * 
     * @param func accumulation function that accepts two elements and returns accumulation result
     * @return result of accumulation
     */
    public T accumulate(BinaryOperator<T> func);

    /**
     * Returns the accumulation of all elements along the specified dimensions in this NDArray.
     * 
     * <p>
     * For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.accumulate(func, 2)</code> returns a
     * [5 × 8] array, and <code>B.get(1,1) == func(func(A.get(1,1,0), A.get(1,1,1)), A.get(1,1,2))</code>.
     * 
     * @param func accumulation function that accepts two elements and returns accumulation result
     * @param selectedDims dimensions along which the accumulation should be performed
     * @return result of accumulation of all elements along the specified dimensions
     */
    public NDArray<T> accumulate(BinaryOperator<T> func, int... selectedDims);

    /**
     * Reduces slices along the specified dimensions in this NDArray to scalar values, reducing the number of
     * dimensions.
     * 
     * <p>
     * For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.reduce(func, 2)</code> returns a [5 × 8]
     * array, and <code>B.get(1,1) == func(A.slice(1, 1, ":"), new int[] { 1, 1 })</code>.
     * 
     * @param func reduction function that accepts a slice and its index and returns reduction result
     * @param selectedDims dimensions along which the reduction should be performed
     * @return result of reduction of all slices along the specified dimensions
     */
    public NDArray<T> reduceSlices(BiFunction<NDArray<T>, int[], T> func, int... selectedDims);

    /**
     * Returns the 2-norm (Euclidean norm) of the vectorized array.
     * 
     * <p>
     * Note: All N-dimensional arrays are treated as if they were reshaped to a 1D vector.
     * </p>
     * 
     * @return the 2-norm (Euclidean norm) of the vectorized array
     */
    public double norm();

    /**
     * Returns the p-norm of the vectorized array.
     * 
     * <p>
     * Note: All N-dimensional arrays are treated as if they were reshaped to a 1D vector.
     * </p>
     * 
     * Possible values:
     * <ul>
     * <li>p = 0: Hamming distance of the vector from zero (number of non-zero entries) -- it is not a true norm!</li>
     * <li>0 &#60; p &#60; 1: Hamming distance of the vector from zero (number of non-zero entries) -- it is only a
     * quasi norm (triargument inequality doesn't hold)!</li>
     * <li>1: Absolute-value norm (sum of the absolute values of the entries)</li>
     * <li>2: Euclidean norm (square root of sum of the squared entry values)</li>
     * <li>1 &#60; p: General p-norm (Σ(|p|)ᵖ)^(1/p)</li>
     * <li>p = Double.POSITIVE_INFINITY: Infinity norm (returns the entry with the maximal absolute value)</li>
     * </ul>
     * 
     * @param p type of norm
     * @return the p-(quasi)norm of the array
     */
    public default double norm(int p) {
        return norm(Double.valueOf(p));
    }

    /**
     * Returns the p-norm of the vectorized array.
     * 
     * <p>
     * Note: All N-dimensional arrays are treated as if they were reshaped to a 1D vector.
     * </p>
     * 
     * Possible values:
     * <ul>
     * <li>p = 0: Hamming distance of the vector from zero (number of non-zero entries) -- it is not a true norm!</li>
     * <li>0 &#60; p &#60; 1: Hamming distance of the vector from zero (number of non-zero entries) -- it is only a
     * quasi norm (triargument inequality doesn't hold)!</li>
     * <li>1: Absolute-value norm (sum of the absolute values of the entries)</li>
     * <li>2: Euclidean norm (square root of sum of the squared entry values)</li>
     * <li>1 &#60; p: General p-norm (Σ(|p|)ᵖ)^(1/p)</li>
     * <li>p = Double.POSITIVE_INFINITY: Infinity norm (returns the entry with the maximal absolute value)</li>
     * </ul>
     * 
     * @param p type of norm
     * @return the p-(quasi)norm of the array
     */
    public double norm(Double p);

    /**
     * Fill this NDArray with the specified value
     * 
     * @param value value assigned to all elements of this NDArray
     * @return this NDArray
     */
    @Replace(pattern = "double", replacements = { "T" })
    public NDArrayTemplate<T> fill(double value);

    /**
     * Returns a new array of the same shape as this NDArray filled with zeros.
     * 
     * @return a new array of the same shape as this NDArray filled with zeros.
     */
    public NDArrayTemplate<T> similar();

    /**
     * Returns a copy of this NDArray.
     * 
     * @return a copy of this NDArray.
     */
    public NDArrayTemplate<T> copy();

    /**
     * Returns a view that references this NDArray as parent, and skips all singleton dimensions not included in the
     * parameter list.
     * 
     * <p>
     * View: An NDArray that references the specified region its parent array. All modifications in the parent array are
     * reflected in the view, and vice versa.
     * 
     * <p>
     * Singleton dimension: dimension of shape 1.
     * 
     * @param selectedDims dimensions kept in the returned view
     * @return a view that references this NDArray as parent, and skips all singleton dimensions not included in the
     *         parameter list
     */
    @Patterns({ "not included", "kept", "selectDims", "selectedDims" })
    @Replacements({ "included", "skipped", "dropDims", "droppedDims" })
    public NDArrayTemplate<T> selectDims(int... selectedDims);

    /**
     * Returns a view that references this NDArray as parent, and skips all singleton dimensions.
     * 
     * <p>
     * View: An NDArray that references the specified region its parent array. All modifications in the parent array are
     * reflected in the view, and vice versa.
     * 
     * <p>
     * Singleton dimension: dimension of shape 1.
     * 
     * @return a view that references this NDArray as parent, and skips all singleton dimensions.
     */
    public NDArrayTemplate<T> squeeze();

    /**
     * Returns an array view referencing this NDArray as parent that gives read-write access to a specific
     * multi-dimensional slice of the array.
     * 
     * <p>
     * View: An NDArray that references the specified region its parent array. All modifications in the parent array are
     * reflected in the view, and vice versa.
     * 
     * <p>
     * Possible slicing expressions:
     * <ul>
     * <li>integer values: selects a specific slice along the positionally selected dimension</li>
     * <li>range (string that consists of two integer values specifying the start and the end of the range separated by
     * a colon): selects a specific range of slices along the positionally selected dimension</li>
     * <li>all-range (string literal ":" or Range.ALL): selects all slices along the positionally selected
     * dimension</li>
     * </ul>
     * 
     * <p>
     * For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.slice(1, "2:5", ":")</code> returns a [3
     * × 3] array, and <code>B.get(1,1) == A.get(1,3,1)</code>.
     * 
     * @param slicingExpressions Slicing expressions
     * @return an array view that gives read-write access to a specific multi-dimensional slice of the array
     */
    public NDArrayTemplate<T> slice(Object... slicingExpressions);

    /**
     * Returns an array view referencing this NDArray as parent that gives read-write access to a specific elements of
     * the array selected by the given mask.
     * 
     * The mask must have the same shape as this array, and those entries are selected which has the same indices as the
     * non-zero entries in the mask. In other words: All places where the mask contains a zero value are skipped, and
     * all other values are copied into a new vector.
     * 
     * @param mask NDArray in which non-zero entries marks elements to keep
     * @return an array view that gives read-write access to a specific elements of the array selected by the given mask
     */
    @Patterns({ "mask", "zero", "non-zero" })
    @Replacements({ "inverseMask", "non-zero", "zero" })
    public NDArrayTemplate<T> mask(NDArrayTemplate<?> mask);

    /**
     * Returns an array view referencing this NDArray as parent that gives read-write access to a specific elements for
     * which the given function returns true.
     * 
     * @param func function that accepts the values of entries as input and returns boolean
     * @return an array view that gives read-write access to a specific elements for which the given function returns
     *         true
     */
    @Patterns({ "mask", "/Predicate<T>/", "entries" })
    @Replacements({ "maskWithLinearIndices", "BiPredicate<T, Integer>", "entries and their linear indices" })
    @Replacements({ "maskWithCartesianIndices", "BiPredicate<T, int[]>", "entries and their Cartesian indices" })
    public NDArrayTemplate<T> mask(Predicate<T> func);

    /**
     * Returns a view that references this NDArray as parent but has a different shape.
     * 
     * <p>
     * View: An NDArray that references the specified region its parent array. All modifications in the parent array are
     * reflected in the view, and vice versa.
     * 
     * <p>
     * For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.reshape(40, 3)</code> returns a [40 × 3]
     * array, and <code>B.get(10) == B.get(10,0) == A.get(1,0,0) == A.get(10)</code>.
     * 
     * <p>
     * Note: the linear indexing of the two arrays remain the same, and the corresponding cartesian indices determined
     * by column-first ordering.
     * 
     * @param newShape new shape/dimensions
     * @return a view that references this NDArray as parent but has a different shape
     */
    public NDArrayTemplate<T> reshape(int... newShape);

    /**
     * Returns a view that references this NDArray as parent, but the order of dimensions are swiched in this view.
     * 
     * <p>
     * View: An NDArray that references the specified region its parent array. All modifications in the parent array are
     * reflected in the view, and vice versa.
     * 
     * <p>
     * For example, if <code>A</code> is a [5 × 8 × 3] array, then <code>B = A.permuteDims(1, 0, 2)</code> returns a [8
     * × 5 × 3] array, and <code>B.get(3,1,0) == A.get(1,3,0)</code>.
     * 
     * @param permutation new order of dimensions / permutation vector
     * @return a view that references this NDArray as parent, but the order of dimensions are swiched in this view.
     */
    public NDArrayTemplate<T> permuteDims(int... permutation);

    /**
     * Creates a new NDArray that contains the elements of this NDArrays and all other NDArrays concatenated along the
     * dimension/axis specified by the first parameter.
     * 
     * @param axis Axis/dimension along which the concatenation should occur.
     * @param arrays Arrays to be concatenated to this NDArray.
     * @return a new NDArray that contains the elements of this NDArrays and all other NDArrays concatenated along the
     *         dimension/axis specified by the first parameter.
     */
    public NDArrayTemplate<T> concatenate(int axis, NDArrayTemplate<?>... arrays);

    /**
     * Returns a stream of linear indices.
     * 
     * @return a stream of linear indices
     */
    public IntStream streamLinearIndices();

    /**
     * Returns a stream of cartesian indices.
     * 
     * Each item in the stream is a int[] that holds cartesian indices.
     * 
     * <p>
     * Cartesian indexing: The ordinary way to index into an N-dimensional array is to use exactly N indices; each index
     * selects the position(s) in its particular dimension.
     * 
     * @return a stream of cartesian indices
     */
    public Stream<int[]> streamCartesianIndices();

    /**
     * Save content of the NDArray to the given file.
     * 
     * <p>
     * Files written that way can be later loaded by the static function readFromFile.
     * 
     * <ul>
     * <li><b>Example:</b></li>
     * </ul>
     * 
     * <blockquote>
     * 
     * <pre>{@code
     * 
     * NDArray<Float> array = new BasicFloatNDArray(128, 128).fill(5);
     * array.writeToFile(new File("array.nda"));
     * NDArray<Integer> array2 = BasicIntegerNDArray.readFromFile(new File("array.nda"));
     * assertEquals(array, array2);
     * }</pre>
     * 
     * </blockquote>
     * 
     * @param file file into which the content of the NDArray is written (the extension of the file can be arbitrary,
     *            but .nda is recommended)
     * @throws IOException when the given file cannot be opened for write
     */
    public void writeToFile(File file) throws IOException;

}
