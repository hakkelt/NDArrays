/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestCopy.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.Errors;

import org.junit.jupiter.api.*;

class TestBasicDoubleNDArrayCopy extends TestBase {

    NDArray<Double> arrayOriginal;
    NDArray<Double> array;
    NDArray<Double> mask;
    NDArray<Double> masked;
    NDArray<Double> pArray;
    NDArray<Double> reshaped;
    NDArray<Double> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicDoubleNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToDouble(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicDoubleNDArray(array.mapWithLinearIndices((value, index) -> wrapToDouble(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    void testCopyWithArray() {
        NDArray<Double> array2 = array.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(array.get(5), array2.get(5));
    }

    @Test
    void testCopyWithSlice() {
        NDArray<Double> array2 = slice.copy();
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(slice.get(5), array2.get(5));
    }

    @Test
    void testCopyWithReshaped() {
        NDArray<Double> array2 = reshaped.copy();
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(reshaped.get(5), array2.get(5));
    }

    @Test
    void testCopyWithPArray() {
        NDArray<Double> array2 = pArray.copy();
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(pArray.get(5), array2.get(5));
    }

    @Test
    void testCopyWithMasked() {
        NDArray<Double> array2 = masked.copy();
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(masked.get(5), array2.get(5));
    }

    @Test
    void testCopyFromArrayToArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> rhs = array.similar();
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromArrayToSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice.similar();
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromSliceToArray() {
        NDArray<Double> lhs = slice.similar();
        NDArray<Double> rhs = slice;
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromSliceToSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = array.slice(0, "1:4", ":");
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromArrayToReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped.similar();
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromReshapedToArray() {
        NDArray<Double> lhs = reshaped.similar();
        NDArray<Double> rhs = reshaped;
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromReshapedToReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = array.similar().reshape(20, 3);
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromArrayToPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray.similar();
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromPArrayToArray() {
        NDArray<Double> lhs = pArray.similar();
        NDArray<Double> rhs = pArray;
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromPArrayToPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = array.similar().permuteDims(0, 2, 1);
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromArrayToMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked.similar();
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromMaskedToArray() {
        NDArray<Double> lhs = masked.similar();
        NDArray<Double> rhs = masked;
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromMaskedToMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = array.inverseMask(mask);
        NDArray<Double> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    void testCopyFromFloatArrayWithArray() {
        float[][][] primitiveArray = new float[4][5][3];
        array.copyFrom(primitiveArray);
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), array.get(i));
    }

    @Test
    void testCopyFromFloatArrayWithSlice() {
        float[][] primitiveArray = new float[3][3];
        slice.copyFrom(primitiveArray);
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), slice.get(i));
    }

    @Test
    void testCopyFromFloatArrayWithReshaped() {
        float[][] primitiveArray = new float[20][3];
        reshaped.copyFrom(primitiveArray);
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), reshaped.get(i));
    }

    @Test
    void testCopyFromFloatArrayWithPArray() {
        float[][][] primitiveArray = new float[4][3][5];
        pArray.copyFrom(primitiveArray);
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), pArray.get(i));
    }

    @Test
    void testCopyFromFloatArrayWithMasked() {
        float[] primitiveArray = new float[30];
        masked.copyFrom(primitiveArray);
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), masked.get(i));
    }

    @Test
    void testCopyFromDoubleArrayWithArray() {
        double[][][] primitiveArray = new double[4][5][3];
        array.copyFrom(primitiveArray);
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), array.get(i));
    }

    @Test
    void testCopyFromDoubleArrayWithSlice() {
        double[][] primitiveArray = new double[3][3];
        slice.copyFrom(primitiveArray);
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), slice.get(i));
    }

    @Test
    void testCopyFromDoubleArrayWithReshaped() {
        double[][] primitiveArray = new double[20][3];
        reshaped.copyFrom(primitiveArray);
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), reshaped.get(i));
    }

    @Test
    void testCopyFromDoubleArrayWithPArray() {
        double[][][] primitiveArray = new double[4][3][5];
        pArray.copyFrom(primitiveArray);
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), pArray.get(i));
    }

    @Test
    void testCopyFromDoubleArrayWithMasked() {
        double[] primitiveArray = new double[30];
        masked.copyFrom(primitiveArray);
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), masked.get(i));
    }

    @Test
    void testCopyFromByteArrayWithArray() {
        byte[][][] primitiveArray = new byte[4][5][3];
        array.copyFrom(primitiveArray);
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), array.get(i));
    }

    @Test
    void testCopyFromByteArrayWithSlice() {
        byte[][] primitiveArray = new byte[3][3];
        slice.copyFrom(primitiveArray);
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), slice.get(i));
    }

    @Test
    void testCopyFromByteArrayWithReshaped() {
        byte[][] primitiveArray = new byte[20][3];
        reshaped.copyFrom(primitiveArray);
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), reshaped.get(i));
    }

    @Test
    void testCopyFromByteArrayWithPArray() {
        byte[][][] primitiveArray = new byte[4][3][5];
        pArray.copyFrom(primitiveArray);
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), pArray.get(i));
    }

    @Test
    void testCopyFromByteArrayWithMasked() {
        byte[] primitiveArray = new byte[30];
        masked.copyFrom(primitiveArray);
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), masked.get(i));
    }

    @Test
    void testCopyFromShortArrayWithArray() {
        short[][][] primitiveArray = new short[4][5][3];
        array.copyFrom(primitiveArray);
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), array.get(i));
    }

    @Test
    void testCopyFromShortArrayWithSlice() {
        short[][] primitiveArray = new short[3][3];
        slice.copyFrom(primitiveArray);
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), slice.get(i));
    }

    @Test
    void testCopyFromShortArrayWithReshaped() {
        short[][] primitiveArray = new short[20][3];
        reshaped.copyFrom(primitiveArray);
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), reshaped.get(i));
    }

    @Test
    void testCopyFromShortArrayWithPArray() {
        short[][][] primitiveArray = new short[4][3][5];
        pArray.copyFrom(primitiveArray);
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), pArray.get(i));
    }

    @Test
    void testCopyFromShortArrayWithMasked() {
        short[] primitiveArray = new short[30];
        masked.copyFrom(primitiveArray);
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), masked.get(i));
    }

    @Test
    void testCopyFromIntArrayWithArray() {
        int[][][] primitiveArray = new int[4][5][3];
        array.copyFrom(primitiveArray);
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), array.get(i));
    }

    @Test
    void testCopyFromIntArrayWithSlice() {
        int[][] primitiveArray = new int[3][3];
        slice.copyFrom(primitiveArray);
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), slice.get(i));
    }

    @Test
    void testCopyFromIntArrayWithReshaped() {
        int[][] primitiveArray = new int[20][3];
        reshaped.copyFrom(primitiveArray);
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), reshaped.get(i));
    }

    @Test
    void testCopyFromIntArrayWithPArray() {
        int[][][] primitiveArray = new int[4][3][5];
        pArray.copyFrom(primitiveArray);
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), pArray.get(i));
    }

    @Test
    void testCopyFromIntArrayWithMasked() {
        int[] primitiveArray = new int[30];
        masked.copyFrom(primitiveArray);
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), masked.get(i));
    }

    @Test
    void testCopyFromLongArrayWithArray() {
        long[][][] primitiveArray = new long[4][5][3];
        array.copyFrom(primitiveArray);
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), array.get(i));
    }

    @Test
    void testCopyFromLongArrayWithSlice() {
        long[][] primitiveArray = new long[3][3];
        slice.copyFrom(primitiveArray);
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), slice.get(i));
    }

    @Test
    void testCopyFromLongArrayWithReshaped() {
        long[][] primitiveArray = new long[20][3];
        reshaped.copyFrom(primitiveArray);
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), reshaped.get(i));
    }

    @Test
    void testCopyFromLongArrayWithPArray() {
        long[][][] primitiveArray = new long[4][3][5];
        pArray.copyFrom(primitiveArray);
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), pArray.get(i));
    }

    @Test
    void testCopyFromLongArrayWithMasked() {
        long[] primitiveArray = new long[30];
        masked.copyFrom(primitiveArray);
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(wrapToDouble(0), masked.get(i));
    }

    @Test
    void testWrongCopyFromFloatArrayWithArray() {
        float[][][] wrongshapedArray = new float[4][3][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 3 × 5", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromFloatArrayWithSlice() {
        float[][] wrongshapedArray = new float[1][9];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 9", "3 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromFloatArrayWithReshaped() {
        float[][] wrongshapedArray = new float[15][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "15 × 4", "20 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromFloatArrayWithPArray() {
        float[][][] wrongshapedArray = new float[5][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5 × 3 × 4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromFloatArrayWithMasked() {
        float[][] wrongshapedArray = new float[1][30];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 30", "30"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromDoubleArrayWithArray() {
        double[][][] wrongshapedArray = new double[4][3][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 3 × 5", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromDoubleArrayWithSlice() {
        double[][] wrongshapedArray = new double[1][9];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 9", "3 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromDoubleArrayWithReshaped() {
        double[][] wrongshapedArray = new double[15][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "15 × 4", "20 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromDoubleArrayWithPArray() {
        double[][][] wrongshapedArray = new double[5][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5 × 3 × 4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromDoubleArrayWithMasked() {
        double[][] wrongshapedArray = new double[1][30];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 30", "30"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromByteArrayWithArray() {
        byte[][][] wrongshapedArray = new byte[4][3][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 3 × 5", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromByteArrayWithSlice() {
        byte[][] wrongshapedArray = new byte[1][9];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 9", "3 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromByteArrayWithReshaped() {
        byte[][] wrongshapedArray = new byte[15][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "15 × 4", "20 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromByteArrayWithPArray() {
        byte[][][] wrongshapedArray = new byte[5][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5 × 3 × 4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromByteArrayWithMasked() {
        byte[][] wrongshapedArray = new byte[1][30];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 30", "30"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromShortArrayWithArray() {
        short[][][] wrongshapedArray = new short[4][3][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 3 × 5", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromShortArrayWithSlice() {
        short[][] wrongshapedArray = new short[1][9];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 9", "3 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromShortArrayWithReshaped() {
        short[][] wrongshapedArray = new short[15][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "15 × 4", "20 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromShortArrayWithPArray() {
        short[][][] wrongshapedArray = new short[5][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5 × 3 × 4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromShortArrayWithMasked() {
        short[][] wrongshapedArray = new short[1][30];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 30", "30"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromIntArrayWithArray() {
        int[][][] wrongshapedArray = new int[4][3][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 3 × 5", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromIntArrayWithSlice() {
        int[][] wrongshapedArray = new int[1][9];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 9", "3 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromIntArrayWithReshaped() {
        int[][] wrongshapedArray = new int[15][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "15 × 4", "20 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromIntArrayWithPArray() {
        int[][][] wrongshapedArray = new int[5][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5 × 3 × 4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromIntArrayWithMasked() {
        int[][] wrongshapedArray = new int[1][30];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 30", "30"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromLongArrayWithArray() {
        long[][][] wrongshapedArray = new long[4][3][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 3 × 5", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromLongArrayWithSlice() {
        long[][] wrongshapedArray = new long[1][9];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 9", "3 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromLongArrayWithReshaped() {
        long[][] wrongshapedArray = new long[15][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "15 × 4", "20 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromLongArrayWithPArray() {
        long[][][] wrongshapedArray = new long[5][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5 × 3 × 4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromLongArrayWithMasked() {
        long[][] wrongshapedArray = new long[1][30];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 30", "30"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DFloatArrayWithArrayWithArray() {
        float[][][] wrongshapedArray = new float[4][2][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 2 × 5", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DFloatArrayWithArrayWithSlice() {
        float[][][] wrongshapedArray = new float[4][2][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 2 × 5", "3 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DFloatArrayWithArrayWithReshaped() {
        float[][][] wrongshapedArray = new float[4][2][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 2 × 5", "20 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DFloatArrayWithArrayWithPArray() {
        float[][][] wrongshapedArray = new float[4][2][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 2 × 5", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DFloatArrayWithArrayWithMasked() {
        float[][][] wrongshapedArray = new float[4][2][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 2 × 5", "30"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromNDArrayWithArray() {
        NDArray<Double> wrongShapedArray = new BasicDoubleNDArray(20, 3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongShapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "20 × 3", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromNDArrayWithSlice() {
        NDArray<Double> wrongShapedArray = new BasicDoubleNDArray(1, 9);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.copyFrom(wrongShapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 9", "3 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromNDArrayWithReshaped() {
        NDArray<Double> wrongShapedArray = new BasicDoubleNDArray(15, 4);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.copyFrom(wrongShapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "15 × 4", "20 × 3"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromNDArrayWithPArray() {
        NDArray<Double> wrongShapedArray = new BasicDoubleNDArray(5, 3, 4);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.copyFrom(wrongShapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5 × 3 × 4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromNDArrayWithMasked() {
        NDArray<Double> wrongShapedArray = new BasicDoubleNDArray(1, 30);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.copyFrom(wrongShapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "1 × 30", "30"), exception.getMessage());
    }

}
