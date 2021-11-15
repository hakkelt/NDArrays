/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestPermuteDims.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.Errors;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

class TestBasicComplexDoubleNDArrayPermuteDims extends TestBase {

    NDArray<Complex> arrayOriginal;
    NDArray<Complex> array;
    NDArray<Complex> mask;
    NDArray<Complex> masked;
    NDArray<Complex> pArray;
    NDArray<Complex> reshaped;
    NDArray<Complex> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicComplexDoubleNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToComplex(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = array.mapWithLinearIndices((value, index) -> wrapToComplex(index % 2));
        masked = array.mask(mask);
    }

    @Test
    void testNotEqual() {
        assertNotEquals(pArray, new int[0]); // NOSONAR
        assertNotEquals(pArray, new BasicComplexFloatNDArray(pArray.shape()));
        assertNotEquals(pArray, new BasicComplexDoubleNDArray(6, 10));
        assertNotEquals(pArray, new BasicComplexDoubleNDArray(4, 5, 3));
    }

    @Test
    void testEqualWithSameType() {
        NDArray<Complex> pArray2 = array.permuteDims(0, 2, 1);
        assertEquals(pArray, pArray2);
        NDArray<Complex> pArray3 = array.copy().permuteDims(0, 2, 1);
        assertEquals(pArray, pArray3);
    }

    @Test
    void testEqualWithDifferentType() {
        NDArray<?> array2 = new BasicComplexFloatNDArray(pArray);
        assertEquals(pArray, array2);
        array2.set(0, 5);
        assertNotEquals(pArray, array2);
    }

    @Test
    void testToString() {
        String str = pArray.toString();
        assertEquals(array.getNamePrefix() + " NDArrayView<Complex Double>(4 × 3 × 5)", str);
    }

    @Test
    void testContentToString() {
        String str = pArray.contentToString();
        String lineFormat = "%10.5e+%10.5ei\t%10.5e+%10.5ei\t%10.5e+%10.5ei\t%n";
        StringBuilder sb = new StringBuilder();
        sb.append(array.getNamePrefix() + " NDArrayView<Complex Double>(4 × 3 × 5)" + System.lineSeparator());
        for (int i = 0; i < pArray.shape(2); i++) {
            sb.append("[:, :, " + i + "] =" + System.lineSeparator());
            for (int j = 0; j < pArray.shape(0); j++) {
                if (pArray.dtype() == Complex.class)
                    sb.append(String.format(lineFormat, wrapToDouble(i * 4 + j), 0., wrapToDouble(i * 4 + j + 20), 0., wrapToDouble(i * 4 + j + 40), 0.));
                else
                    sb.append(String.format(lineFormat, wrapToComplex(i * 4 + j), wrapToComplex(i * 4 + j + 20), wrapToComplex(i * 4 + j + 40)));
            }
            sb.append(System.lineSeparator());
        }
        String expected = sb.toString();
        assertEquals(expected, str);
    }

    @Test
    void testConcatenate() {
        NDArray<Complex> array2 = new BasicComplexDoubleNDArray(2, 3, 5).fill(1);
        NDArray<Complex> array3 = pArray.concatenate(0, array2);
        for (int i = 0; i < pArray.shape(0); i++)
            for (int j = 0; j < pArray.shape(1); j++)
                for (int k = 0; k < pArray.shape(1); k++)
                    assertEquals(pArray.get(i, j, k), array3.get(i, j, k));
        for (int i = pArray.shape(0); i < pArray.shape(0) + array2.shape(0); i++)
            for (int j = 0; j < pArray.shape(1); j++)
                for (int k = 0; k < pArray.shape(1); k++)
                    assertSpecializedEquals(wrapToComplex(1), array3.get(i, j, k));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Complex> array2 = pArray.copy().fill(1).slice(":", "1:3", ":");
        NDArray<Complex> array3 = new BasicComplexDoubleNDArray(2, 5, 4).permuteDims(2, 0, 1);
        NDArray<Complex> array4 = new BasicComplexDoubleNDArray(20).fill(2).reshape(4, 1, 5);
        NDArray<Complex> array5 = pArray.concatenate(1, array2, array3, array4);
        int start = 0;
        int end = pArray.shape(1);
        for (int i = 0; i < pArray.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < pArray.shape(2); k++)
                    assertSpecializedEquals(pArray.get(i, j, k), array5.get(i, j, k));
        start = end;
        end += array2.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertSpecializedEquals(wrapToComplex(1), array5.get(i, j, k));
        start = end;
        end += array3.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertSpecializedEquals(wrapToComplex(0), array5.get(i, j, k));
        start = end;
        end += array4.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertSpecializedEquals(wrapToComplex(2), array5.get(i, j, k));
    }

    @Test
    void testDimensionMismatchWithArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0, 2, 1, 3));
        String shapeString = String.join(" × ", IntStream.of(array.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2, 1, 3]", shapeString), exception.getMessage());
    }

    @Test
    void testDimensionMismatchWithSlice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.permuteDims(0, 2, 1, 3));
        String shapeString = String.join(" × ", IntStream.of(slice.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2, 1, 3]", shapeString), exception.getMessage());
    }

    @Test
    void testDimensionMismatchWithReshaped() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(0, 2, 1, 3));
        String shapeString = String.join(" × ", IntStream.of(reshaped.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2, 1, 3]", shapeString), exception.getMessage());
    }

    @Test
    void testDimensionMismatchWithPArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.permuteDims(0, 2, 1, 3));
        String shapeString = String.join(" × ", IntStream.of(pArray.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2, 1, 3]", shapeString), exception.getMessage());
    }

    @Test
    void testDimensionMismatchWithMasked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.permuteDims(0, 2, 1, 3));
        String shapeString = String.join(" × ", IntStream.of(masked.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2, 1, 3]", shapeString), exception.getMessage());
    }

    @Test
    void testNegativeDimensionWithArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(-1, 0, 1));
        String shapeString = String.join(" × ", IntStream.of(array.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[-1, 0, 1]", shapeString), exception.getMessage());
    }

    @Test
    void testNegativeDimensionWithSlice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.permuteDims(-1, 0));
        String shapeString = String.join(" × ", IntStream.of(slice.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[-1, 0]", shapeString), exception.getMessage());
    }

    @Test
    void testNegativeDimensionWithReshaped() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(-1, 0));
        String shapeString = String.join(" × ", IntStream.of(reshaped.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[-1, 0]", shapeString), exception.getMessage());
    }

    @Test
    void testNegativeDimensionWithPArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.permuteDims(-1, 0, 1));
        String shapeString = String.join(" × ", IntStream.of(pArray.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[-1, 0, 1]", shapeString), exception.getMessage());
    }

    @Test
    void testNegativeDimensionWithMasked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.permuteDims(-1));
        String shapeString = String.join(" × ", IntStream.of(masked.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[-1]", shapeString), exception.getMessage());
    }

    @Test
    void testTooLargeDimensionWithArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(3, 0, 1));
        String shapeString = String.join(" × ", IntStream.of(array.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[3, 0, 1]", shapeString), exception.getMessage());
    }

    @Test
    void testTooLargeDimensionWithSlice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.permuteDims(2, 0));
        String shapeString = String.join(" × ", IntStream.of(slice.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[2, 0]", shapeString), exception.getMessage());
    }

    @Test
    void testTooLargeDimensionWithReshaped() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(2, 0));
        String shapeString = String.join(" × ", IntStream.of(reshaped.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[2, 0]", shapeString), exception.getMessage());
    }

    @Test
    void testTooLargeDimensionWithPArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.permuteDims(3, 0, 1));
        String shapeString = String.join(" × ", IntStream.of(pArray.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[3, 0, 1]", shapeString), exception.getMessage());
    }

    @Test
    void testTooLargeDimensionWithMasked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.permuteDims(1));
        String shapeString = String.join(" × ", IntStream.of(masked.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[1]", shapeString), exception.getMessage());
    }

    @Test
    void testIdentityPermuteDimsWithArray() {
        assertSame(array, array.permuteDims(0, 1, 2));
    }

    @Test
    void testIdentityPermuteDimsWithSlice() {
        assertSame(slice, slice.permuteDims(0, 1));
    }

    @Test
    void testIdentityPermuteDimsWithReshaped() {
        assertSame(reshaped, reshaped.permuteDims(0, 1));
    }

    @Test
    void testIdentityPermuteDimsWithPArray() {
        assertSame(pArray, pArray.permuteDims(0, 1, 2));
    }

    @Test
    void testIdentityPermuteDimsWithMasked() {
        assertSame(masked, masked.permuteDims(0));
    }

    @Test
    void testPermuteDimsWithArray() {
        NDArray<Complex> pArray2 = array.permuteDims(0, 2, 1);
        pArray2.forEachWithCartesianIndices((value, idx) -> assertSpecializedEquals(value, array.get(idx[0], idx[2], idx[1])));
        pArray2.fill(0);
        array.forEach(value -> assertSpecializedEquals(wrapToComplex(0), value));
    }

    @Test
    void testPermuteDimsWithSlice() {
        NDArray<Complex> pArray2 = slice.permuteDims(1, 0);
        pArray2.forEachWithCartesianIndices((value, idx) -> assertSpecializedEquals(value, slice.get(idx[1], idx[0])));
        pArray2.fill(0);
        slice.forEach(value -> assertSpecializedEquals(wrapToComplex(0), value));
    }

    @Test
    void testPermuteDimsWithReshaped() {
        NDArray<Complex> pArray2 = reshaped.permuteDims(1, 0);
        pArray2.forEachWithCartesianIndices((value, idx) -> assertSpecializedEquals(value, reshaped.get(idx[1], idx[0])));
        pArray2.fill(0);
        reshaped.forEach(value -> assertSpecializedEquals(wrapToComplex(0), value));
    }

    @Test
    void testPermuteDimsWithPArray() {
        NDArray<Complex> pArray2 = pArray.permuteDims(0, 2, 1);
        pArray2.forEachWithCartesianIndices((value, idx) -> assertSpecializedEquals(value, pArray.get(idx[0], idx[2], idx[1])));
        pArray2.fill(0);
        pArray.forEach(value -> assertSpecializedEquals(wrapToComplex(0), value));
    }

    @Test
    void testPermuteDimsWithMasked() {
        NDArray<Complex> pArray2 = masked.permuteDims(0);
        pArray2.forEachWithCartesianIndices((value, idx) -> assertSpecializedEquals(value, masked.get(idx[0])));
        pArray2.fill(0);
        masked.forEach(value -> assertSpecializedEquals(wrapToComplex(0), value));
    }

    @Test
    void testPermuteDimsPermuteDims() {
        NDArray<Complex> ppArray = pArray.permuteDims(0, 2, 1);
        array.forEachWithCartesianIndices((value, indices) -> assertEquals(value, ppArray.get(indices)));
        assertSame(array, ppArray);
    }

}
