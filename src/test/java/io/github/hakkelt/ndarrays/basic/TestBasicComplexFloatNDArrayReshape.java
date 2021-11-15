/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestReshape.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

class TestBasicComplexFloatNDArrayReshape extends TestBase {

    NDArray<Complex> arrayOriginal;
    NDArray<Complex> array;
    NDArray<Complex> mask;
    NDArray<Complex> masked;
    NDArray<Complex> pArray;
    NDArray<Complex> reshaped;
    NDArray<Complex> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicComplexFloatNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToComplex(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = array.mapWithLinearIndices((value, index) -> wrapToComplex(index % 2));
        masked = array.mask(mask);
    }

    @Test
    void testNotEqual() {
        assertNotEquals(reshaped, new int[0]); // NOSONAR
        assertNotEquals(reshaped, new BasicComplexFloatNDArray(reshaped.shape()));
        assertNotEquals(reshaped, new BasicComplexFloatNDArray(10, 2, 3));
        assertNotEquals(reshaped, new BasicComplexFloatNDArray(6, 10));
    }

    @Test
    void testEqualWithSameType() {
        NDArray<Complex> reshaped2 = array.reshape(20, 3);
        assertEquals(reshaped, reshaped2);
        NDArray<Complex> reshaped3 = array.copy().reshape(20, 3);
        assertEquals(reshaped, reshaped3);
    }

    @Test
    void testEqualWithDifferentType() {
        NDArray<?> array2 = new BasicComplexDoubleNDArray(reshaped);
        assertEquals(reshaped, array2);
        array2.set(0, 5);
        assertNotEquals(reshaped, array2);
    }

    @Test
    void testToString() {
        String str = reshaped.toString();
        assertEquals(array.getNamePrefix() + " NDArrayView<Complex Float>(20 × 3)", str);
    }

    @Test
    void testContentToString() {
        String str = reshaped.contentToString();
        String lineFormat = "%10.5e+%10.5ei\t%10.5e+%10.5ei\t%10.5e+%10.5ei\t%n";
        StringBuilder sb = new StringBuilder();
        sb.append(array.getNamePrefix() + " NDArrayView<Complex Float>(20 × 3)" + System.lineSeparator());
        for (int i = 0; i < 20; i++)
            if (reshaped.dtype() == Complex.class)
                sb.append(String.format(lineFormat, wrapToDouble(i), 0., wrapToDouble(i + 20), 0., wrapToDouble(i + 40), 0.));
            else
                sb.append(String.format(lineFormat, wrapToComplex(i), wrapToComplex(i + 20), wrapToComplex(i + 40)));
        String expected = sb.toString();
        assertEquals(expected, str);
    }

    @Test
    void testConcatenate() {
        NDArray<Complex> array2 = new BasicComplexFloatNDArray(13, 3).fill(1);
        NDArray<Complex> array3 = reshaped.concatenate(0, array2);
        for (int i = 0; i < reshaped.shape(0); i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertEquals(reshaped.get(i, j), array3.get(i, j));
        for (int i = reshaped.shape(0); i < reshaped.shape(0) + array2.shape(0); i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertSpecializedEquals(wrapToComplex(1), array3.get(i, j));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Complex> array2 = reshaped.copy().fill(1).slice(":10", ":");
        NDArray<Complex> array3 = new BasicComplexFloatNDArray(3, 5).permuteDims(1, 0);
        NDArray<Complex> array4 = new BasicComplexFloatNDArray(3, 6).fill(2).reshape(6, 3);
        NDArray<Complex> array5 = reshaped.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = reshaped.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertEquals(reshaped.get(i, j), array5.get(i, j));
        start = end;
        end += array2.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertSpecializedEquals(wrapToComplex(1), array5.get(i, j));
        start = end;
        end += array3.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertSpecializedEquals(wrapToComplex(0), array5.get(i, j));
        start = end;
        end += array4.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertSpecializedEquals(wrapToComplex(2), array5.get(i, j));
    }

    @Test
    void testDimensionMismatchWithArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.reshape(4, 10));
        String shapeString = String.join(" × ", IntStream.of(array.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.RESHAPE_LENGTH_MISMATCH, shapeString, "4 × 10"), exception.getMessage());
    }

    @Test
    void testDimensionMismatchWithSlice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.reshape(4, 10));
        String shapeString = String.join(" × ", IntStream.of(slice.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.RESHAPE_LENGTH_MISMATCH, shapeString, "4 × 10"), exception.getMessage());
    }

    @Test
    void testDimensionMismatchWithReshaped() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.reshape(4, 10));
        String shapeString = String.join(" × ", IntStream.of(reshaped.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.RESHAPE_LENGTH_MISMATCH, shapeString, "4 × 10"), exception.getMessage());
    }

    @Test
    void testDimensionMismatchWithPArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.reshape(4, 10));
        String shapeString = String.join(" × ", IntStream.of(pArray.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.RESHAPE_LENGTH_MISMATCH, shapeString, "4 × 10"), exception.getMessage());
    }

    @Test
    void testDimensionMismatchWithMasked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.reshape(4, 10));
        String shapeString = String.join(" × ", IntStream.of(masked.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.RESHAPE_LENGTH_MISMATCH, shapeString, "4 × 10"), exception.getMessage());
    }

    @Test
    void testIdentityReshapeWithArray() {
        assertSame(array, array.reshape(array.shape()));
    }

    @Test
    void testIdentityReshapeWithSlice() {
        assertSame(slice, slice.reshape(slice.shape()));
    }

    @Test
    void testIdentityReshapeWithReshaped() {
        assertSame(reshaped, reshaped.reshape(reshaped.shape()));
    }

    @Test
    void testIdentityReshapeWithPArray() {
        assertSame(pArray, pArray.reshape(pArray.shape()));
    }

    @Test
    void testIdentityReshapeWithMasked() {
        assertSame(masked, masked.reshape(masked.shape()));
    }

    @Test
    void testReshapedWithArray() {
        reshaped = array.reshape(20, 3);
        reshaped.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(value, array.get(index)));
        reshaped.fill(0);
        array.forEach(value -> assertSpecializedEquals(wrapToComplex(0), value));
    }

    @Test
    void testReshapedWithSlice() {
        reshaped = slice.reshape(9);
        reshaped.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(value, slice.get(index)));
        reshaped.fill(0);
        slice.forEach(value -> assertSpecializedEquals(wrapToComplex(0), value));
    }

    @Test
    void testReshapedWithReshaped() {
        reshaped = reshaped.reshape(3, 20);
        reshaped.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(value, reshaped.get(index)));
        reshaped.fill(0);
        reshaped.forEach(value -> assertSpecializedEquals(wrapToComplex(0), value));
    }

    @Test
    void testReshapedWithPArray() {
        reshaped = pArray.reshape(20, 3);
        reshaped.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(value, pArray.get(index)));
        reshaped.fill(0);
        pArray.forEach(value -> assertSpecializedEquals(wrapToComplex(0), value));
    }

    @Test
    void testReshapedWithMasked() {
        reshaped = masked.reshape(3, 10);
        reshaped.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(value, masked.get(index)));
        reshaped.fill(0);
        masked.forEach(value -> assertSpecializedEquals(wrapToComplex(0), value));
    }

    @Test
    void testReshapeReshaped() {
        NDArray<Complex> reshaped2 = reshaped.reshape(4, 15);
        array.forEachWithLinearIndices((value, index) -> assertEquals(value, reshaped2.get(index)));
        assertArrayEquals(new int[]{ 4, 15 }, reshaped2.shape());
        assertSame(array, ((ComplexNDArrayReshapeView<?>) reshaped2).getParent());
    }

}
