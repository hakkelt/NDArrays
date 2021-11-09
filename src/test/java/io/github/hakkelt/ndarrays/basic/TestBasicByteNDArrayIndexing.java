/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestBasicByteNDArrayIndexing.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:55 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.Errors;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.*;

class TestBasicByteNDArrayIndexing extends TestBase {

    NDArray<Byte> arrayOriginal;
    NDArray<Byte> array;
    NDArray<Byte> mask;
    NDArray<Byte> masked;
    NDArray<Byte> pArray;
    NDArray<Byte> reshaped;
    NDArray<Byte> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicByteNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToByte(index.doubleValue()));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicByteNDArray(array.mapWithLinearIndices((value, index) -> wrapToByte((double) (index % 2))));
        masked = array.mask(mask);
    }

    @Test
    void testGetNegativeLinearIndexingWithArray() {
        assertSpecializedEquals(wrapToByte(60 - 5), array.get(-5));
    }

    @Test
    void testGetNegativeLinearIndexingWithSlice() {
        assertSpecializedEquals(wrapToByte((1 * 5 + 2) * 4 + 1), slice.get(-5));
    }

    @Test
    void testGetNegativeLinearIndexingWithReshaped() {
        assertSpecializedEquals(wrapToByte(60 - 5), reshaped.get(-5));
    }

    @Test
    void testGetNegativeLinearIndexingWithPArray() {
        assertSpecializedEquals(wrapToByte((1 * 5 + 4) * 4 + 3), pArray.get(-5));
    }

    @Test
    void testGetNegativeLinearIndexingWithMasked() {
        assertSpecializedEquals(wrapToByte(61 - 6), masked.get(-3));
    }

    @Test
    void testGetNegativeCartesianIndexingWithArray() {
        assertSpecializedEquals(wrapToByte((2 * 5 + 2) * 4 + 2), array.get(2, -3, -1));
    }

    @Test
    void testGetNegativeCartesianIndexingWithSlice() {
        assertSpecializedEquals(wrapToByte((2 * 5 + 3) * 4 + 1), slice.get(2, -1));
    }

    @Test
    void testGetNegativeCartesianIndexingWithReshaped() {
        assertSpecializedEquals(wrapToByte((2 * 5 + 2) * 4 + 2), reshaped.get(10, -1));
    }

    @Test
    void testGetNegativeCartesianIndexingWithPArray() {
        assertSpecializedEquals(wrapToByte((2 * 5 + 3) * 4 + 2), pArray.get(2, -1, -2));
    }

    @Test
    void testGetNegativeCartesianIndexingWithMasked() {
        assertSpecializedEquals(wrapToByte(61 - 6), masked.get(new int[]{-3}));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexingWithArrayAndDouble() {
        array.set(1.5, (2 * 5 + 2) * 4 + 0);
        assertSpecializedEquals(wrapToByte(1.5), array.get(0, 2, 2));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexingWithArrayAndBigInteger() {
        array.set(BigInteger.ONE, (2 * 5 + 2) * 4 + 0);
        assertSpecializedEquals(wrapToByte(1), array.get(0, 2, 2));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexingWithArrayAndBigDecimal() {
        array.set(BigDecimal.ONE, (2 * 5 + 2) * 4 + 0);
        assertSpecializedEquals(wrapToByte(1), array.get(0, 2, 2));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexingWithArray() {
        array.set(1, (2 * 5 + 2) * 4 + 0);
        assertSpecializedEquals(wrapToByte(1), array.get(0, 2, 2));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexingWithSlice() {
        slice.set(1, 2 * 3 + 2);
        assertSpecializedEquals(wrapToByte(1), slice.get(2, -1));
        assertSpecializedEquals(wrapToByte(1), array.get(1, 3, 2));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexingWithReshaped() {
        reshaped.set(1, 2 * 20 + 9);
        assertSpecializedEquals(wrapToByte(1), reshaped.get(9, 2));
        assertSpecializedEquals(wrapToByte(1), array.get(1, 2, 2));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexingWithPArray() {
        pArray.set(1, (4 * 3 + 1) * 4 + 1);
        assertSpecializedEquals(wrapToByte(1), pArray.get(1, 1, -1));
        assertSpecializedEquals(wrapToByte(1), array.get(1, 4, 1));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexingWithMasked() {
        masked.set(1, -3);
        assertSpecializedEquals(wrapToByte(1), masked.get(new int[]{-3}));
        assertSpecializedEquals(wrapToByte(1), array.get(61 - 6));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexingWithArrayAndDouble() {
        array.set(1.5, 0, 2, -1);
        assertSpecializedEquals(wrapToByte(1.5), array.get((2 * 5 + 2) * 4 + 0));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexingWithArrayAndBigInteger() {
        array.set(BigInteger.ONE, 0, 2, -1);
        assertSpecializedEquals(wrapToByte(1), array.get((2 * 5 + 2) * 4 + 0));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexingWithArrayAndBigDecimal() {
        array.set(BigDecimal.ONE, 0, 2, -1);
        assertSpecializedEquals(wrapToByte(1), array.get((2 * 5 + 2) * 4 + 0));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexingWithArray() {
        array.set(1, 0, 2, -1);
        assertSpecializedEquals(wrapToByte(1), array.get((2 * 5 + 2) * 4 + 0));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexingWithSlice() {
        slice.set(1, 2, -1);
        assertSpecializedEquals(wrapToByte(1), slice.get(2 * 3 + 2));
        assertSpecializedEquals(wrapToByte(1), array.get(1, 3, 2));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexingWithReshaped() {
        reshaped.set(1, 9, 2);
        assertSpecializedEquals(wrapToByte(1), reshaped.get(2 * 20 + 9));
        assertSpecializedEquals(wrapToByte(1), array.get(1, 2, 2));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexingWithPArray() {
        pArray.set(1, 1, 1, -1);
        assertSpecializedEquals(wrapToByte(1), pArray.get((4 * 3 + 1) * 4 + 1));
        assertSpecializedEquals(wrapToByte(1), array.get(1, 4, 1));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexingWithMasked() {
        masked.set(1, new int[]{-3});
        assertSpecializedEquals(wrapToByte(1), masked.get(-3));
        assertSpecializedEquals(wrapToByte(1), array.get(61 - 6));
    }

    @Test
    void testWrongGetLinearIndexingWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(60));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), 60), exception.getMessage());
    }

    @Test
    void testWrongGetLinearIndexingWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(9));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, slice.length(), 9), exception.getMessage());
    }

    @Test
    void testWrongGetLinearIndexingWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(60));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, reshaped.length(), 60), exception.getMessage());
    }

    @Test
    void testWrongGetLinearIndexingWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(60));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, pArray.length(), 60), exception.getMessage());
    }

    @Test
    void testWrongGetLinearIndexingWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.get(30));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, masked.length(), 30), exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexingWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(-61));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), -61), exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexingWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(-10));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, slice.length(), -10), exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexingWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(-61));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, reshaped.length(), -61), exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexingWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(-61));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, pArray.length(), -61), exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexingWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.get(-31));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, masked.length(), -31), exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexingWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1, 1, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"), exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexingWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(1, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "3 × 3", "[1, 3]"), exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexingWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(1, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "20 × 3", "[1, 3]"), exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexingWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(1, 3, 1));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 3, 1]"), exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexingWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.get(new int[]{30}));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "30", "[30]"), exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexingWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1, -6, 1));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"), exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexingWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(-4, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "3 × 3", "[-4, 3]"), exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexingWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(-21, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "20 × 3", "[-21, 3]"), exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexingWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(1, -4, 1));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, -4, 1]"), exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexingWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.get(new int[]{-31}));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "30", "[-31]"), exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexingWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.set(0, 60));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), 60), exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexingWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.set(0, 9));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, slice.length(), 9), exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexingWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.set(0, 60));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, reshaped.length(), 60), exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexingWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.set(0, 60));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, pArray.length(), 60), exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexingWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.set(0, 30));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, masked.length(), 30), exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexingWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.set(0, -61));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), -61), exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexingWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.set(0, -10));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, slice.length(), -10), exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexingWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.set(0, -61));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, reshaped.length(), -61), exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexingWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.set(0, -61));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, pArray.length(), -61), exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexingWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.set(0, -31));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, masked.length(), -31), exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexingWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.set(0, 1, 1, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"), exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexingWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.set(0, 1, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "3 × 3", "[1, 3]"), exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexingWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.set(0, 1, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "20 × 3", "[1, 3]"), exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexingWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.set(0, 1, 3, 1));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 3, 1]"), exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexingWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.set(0, new int[]{30}));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "30", "[30]"), exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexingWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.set(0, 1, -6, 1));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"), exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexingWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.set(0, -4, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "3 × 3", "[-4, 3]"), exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexingWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.set(0, -21, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "20 × 3", "[-21, 3]"), exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexingWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.set(0, 1, -4, 1));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, -4, 1]"), exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexingWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.set(0, new int[]{-31}));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "30", "[-31]"), exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooManyWithArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1, 1, 1, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 3), exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooManyWithSlice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.get(1, 1, 0, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 2), exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooManyWithReshaped() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.get(1, 1, 0, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 2), exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooManyWithPArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.get(1, 1, 0, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 3), exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooManyWithMasked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.get(1, 1, 0, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 1), exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnoughWithArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1, 1));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 2, 3), exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnoughWithSlice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.get(new int[]{1}));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 1, 2), exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnoughWithReshaped() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.get(new int[]{1}));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 1, 2), exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnoughWithPArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.get(1, 1));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 2, 3), exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnoughWithMasked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.get(new int[]{}));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 0, 1), exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooManyWithArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.set(0, 1, 1, 1, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 3), exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooManyWithSlice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.set(0, 1, 1, 0, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 2), exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooManyWithReshaped() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.set(0, 1, 1, 0, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 2), exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooManyWithPArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.set(0, 1, 1, 0, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 3), exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooManyWithMasked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.set(0, 1, 1, 0, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 1), exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnoughWithArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.set(0, 1, 1));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 2, 3), exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnoughWithSlice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.set(0, new int[]{1}));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 1, 2), exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnoughWithReshaped() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.set(0, new int[]{1}));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 1, 2), exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnoughWithPArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.set(0, 1, 1));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 2, 3), exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnoughWithMasked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.set(0, new int[]{}));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 0, 1), exception.getMessage());
    }

}
