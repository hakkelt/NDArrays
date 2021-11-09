/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestBasicComplexFloatNDArrayToArray.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:54 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.Errors;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

class TestBasicComplexFloatNDArrayToArray extends TestBase {

    ComplexNDArray<Float> arrayOriginal;
    ComplexNDArray<Float> array;
    ComplexNDArray<Float> mask;
    ComplexNDArray<Float> masked;
    ComplexNDArray<Float> pArray;
    ComplexNDArray<Float> reshaped;
    ComplexNDArray<Float> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicComplexFloatNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToComplex(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicComplexFloatNDArray(array.mapWithLinearIndices((value, index) -> wrapToComplex(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    void testToArrayWithArray() {
        Complex[][][] arr = (Complex[][][]) array.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        Complex[][][] arr2 = array.toArray(new Complex[array.shape(0)][array.shape(1)][array.shape(2)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        Complex[][][] arr3 = array.toArray(new Complex[array.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        Complex[][][] arr4 = array.toArray(Complex[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr4[idx[0]][idx[1]][idx[2]]));
    }

    @Test
    void testToArrayWithSlice() {
        Complex[][] arr = (Complex[][]) slice.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr[idx[0]][idx[1]]));

        Complex[][] arr2 = slice.toArray(new Complex[slice.shape(0)][slice.shape(1)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr2[idx[0]][idx[1]]));

        Complex[][] arr3 = slice.toArray(new Complex[slice.shape(0)][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr3[idx[0]][idx[1]]));

        Complex[][] arr4 = slice.toArray(Complex[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr4[idx[0]][idx[1]]));
    }

    @Test
    void testToArrayWithReshaped() {
        Complex[][] arr = (Complex[][]) reshaped.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr[idx[0]][idx[1]]));

        Complex[][] arr2 = reshaped.toArray(new Complex[reshaped.shape(0)][reshaped.shape(1)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr2[idx[0]][idx[1]]));

        Complex[][] arr3 = reshaped.toArray(new Complex[reshaped.shape(0)][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr3[idx[0]][idx[1]]));

        Complex[][] arr4 = reshaped.toArray(Complex[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr4[idx[0]][idx[1]]));
    }

    @Test
    void testToArrayWithPArray() {
        Complex[][][] arr = (Complex[][][]) pArray.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        Complex[][][] arr2 = pArray.toArray(new Complex[pArray.shape(0)][pArray.shape(1)][pArray.shape(2)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        Complex[][][] arr3 = pArray.toArray(new Complex[pArray.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        Complex[][][] arr4 = pArray.toArray(Complex[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr4[idx[0]][idx[1]][idx[2]]));
    }

    @Test
    void testToArrayWithMasked() {
        Complex[] arr = (Complex[]) masked.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr[idx[0]]));

        Complex[] arr2 = masked.toArray(new Complex[masked.shape(0)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr2[idx[0]]));

        Complex[] arr3 = masked.toArray(new Complex[0]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr3[idx[0]]));

        Complex[] arr4 = masked.toArray(Complex[]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr4[idx[0]]));
    }

    @Test
    void testToArrayDifferentTypeWithArray() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> array.toArray(Double[][][]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
        exception = assertThrows(ArrayStoreException.class, () -> array.toArray(byte[][][]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
    }

    @Test
    void testToArrayDifferentTypeWithSlice() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> slice.toArray(Double[][]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
        exception = assertThrows(ArrayStoreException.class, () -> slice.toArray(byte[][]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
    }

    @Test
    void testToArrayDifferentTypeWithReshaped() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> reshaped.toArray(Double[][]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
        exception = assertThrows(ArrayStoreException.class, () -> reshaped.toArray(byte[][]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
    }

    @Test
    void testToArrayDifferentTypeWithPArray() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> pArray.toArray(Double[][][]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
        exception = assertThrows(ArrayStoreException.class, () -> pArray.toArray(byte[][][]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
    }

    @Test
    void testToArrayDifferentTypeWithMasked() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> masked.toArray(Double[]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
        exception = assertThrows(ArrayStoreException.class, () -> masked.toArray(byte[]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
    }

}
