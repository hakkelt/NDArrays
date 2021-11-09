/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestSlice.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:56 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.Errors;

import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

class TestBasicComplexFloatNDArraySlice extends TestBase {

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
    void testSliceIdentity() {
        slice = array.slice(":", "0:5", "::");
        assertSame(array, slice);
    }

    @Test
    void testSliceSlice() {
        NDArray<Complex> slice2 = slice.slice(":", 2);
        slice2.forEachWithCartesianIndices((value, indices) -> assertEquals(value, array.get(1, indices[0] + 1, 2)));
    }

    @Test
    void testStrided() {
        int[] expectedshape = { 1, 3, 3 };
        NDArray<Complex> slice2 = array.slice(new Range(1, 5, 4), ":2:", new Range(0, 3));
        assertArrayEquals(expectedshape, slice2.shape());
        assertEquals(IntStream.of(expectedshape).reduce(1, (acc, value) -> acc * value), slice2.length());
        assertEquals(expectedshape[0], slice2.shape(0));
        assertEquals(expectedshape.length, slice2.ndim());
        int sliceLinearIndex = 0;
        for (int j = 0; j < array.shape(2); j++)
            for (int i = 0; i < array.shape(1); i += 2)
                assertEquals(array.get(1, i, j), slice2.get(sliceLinearIndex++));
    }

    @Test
    void testStridedNegativeStepSize() {
        int[] expectedshape = { 1, 3, 3 };
        NDArray<Complex> slice2 = array.slice(new Range(-1, -5, 0), ":-2:", "0::3");
        assertArrayEquals(expectedshape, slice2.shape());
        assertEquals(IntStream.of(expectedshape).reduce(1, (acc, value) -> acc * value), slice2.length());
        assertEquals(expectedshape[0], slice2.shape(0));
        assertEquals(expectedshape.length, slice2.ndim());
        int sliceLinearIndex = 0;
        for (int j = 0; j < array.shape(2); j++)
            for (int i = array.shape(1) - 1; i >= 0; i -= 2)
                assertEquals(array.get(3, i, j), slice2.get(sliceLinearIndex++));
    }

    @Test
    void testWrongRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Range(0, 0, 5));
        assertEquals(Errors.RANGE_ZERO_STEPSIZE, exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice(4, 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[4, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.slice(3, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "3 × 3", "[3, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.slice(20, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "20 × 3", "[20, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.slice(4, 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 3 × 5", "[4, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.slice(30));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "30", "[30]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice(-5, 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[-5, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.slice(-4, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "3 × 3", "[-4, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.slice(-21, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "20 × 3", "[-21, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.slice(-5, 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 3 × 5", "[-5, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.slice(-31));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "30", "[-31]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsAtRangeStartWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice("4:", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[4:, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsAtRangeStartWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.slice("3:", 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "3 × 3", "[3:, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsAtRangeStartWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.slice("20:", 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "20 × 3", "[20:, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsAtRangeStartWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.slice("4:", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 3 × 5", "[4:, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsAtRangeStartWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.slice("30:"));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "30", "[30:]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsAtRangeEndWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice("1:5", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[1:5, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsAtRangeEndWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.slice("1:4", 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "3 × 3", "[1:4, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsAtRangeEndWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.slice("1:21", 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "20 × 3", "[1:21, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsAtRangeEndWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.slice("1:5", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 3 × 5", "[1:5, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceOutOfBoundsAtRangeEndWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.slice("1:31"));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "30", "[1:31]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsAtRangeStartWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice("-5:", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[-5:, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsAtRangeStartWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.slice("-4:", 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "3 × 3", "[-4:, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsAtRangeStartWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.slice("1:-21", 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "20 × 3", "[1:-21, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsAtRangeStartWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.slice("1:-5", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 3 × 5", "[1:-5, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsAtRangeStartWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.slice("1:-31"));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "30", "[1:-31]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsAtRangeEndWithArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice("1:-5", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[1:-5, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsAtRangeEndWithSlice() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.slice("1:-4", 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "3 × 3", "[1:-4, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsAtRangeEndWithReshaped() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.slice("1:-21", 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "20 × 3", "[1:-21, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsAtRangeEndWithPArray() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.slice("1:-5", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 3 × 5", "[1:-5, 0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNegativeOutOfBoundsAtRangeEndWithMasked() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.slice("1:-31"));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "30", "[1:-31]"), exception.getMessage());
    }

    @Test
    void testWrongSliceTooManyExpressionsWithArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.slice(0, ":", 0, 0));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "4 × 5 × 3", "[0, :, 0, 0]"),
                exception.getMessage());
    }

    @Test
    void testWrongSliceTooManyExpressionsWithSlice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.slice(0, ":", 0, 0));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "3 × 3", "[0, :, 0, 0]"),
                exception.getMessage());
    }

    @Test
    void testWrongSliceTooManyExpressionsWithReshaped() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.slice(0, ":", 0, 0));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "20 × 3", "[0, :, 0, 0]"),
                exception.getMessage());
    }

    @Test
    void testWrongSliceTooManyExpressionsWithPArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.slice(0, ":", 0, 0));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "4 × 3 × 5", "[0, :, 0, 0]"),
                exception.getMessage());
    }

    @Test
    void testWrongSliceTooManyExpressionsWithMasked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.slice(0, ":", 0, 0));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "30", "[0, :, 0, 0]"),
                exception.getMessage());
    }

    @Test
    void testWrongSliceNotEnoughExpressionsWithArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.slice(0, 0));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "4 × 5 × 3", "[0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNotEnoughExpressionsWithSlice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.slice(0));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "3 × 3", "[0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNotEnoughExpressionsWithReshaped() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.slice(0));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "20 × 3", "[0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNotEnoughExpressionsWithPArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.slice(0, 0));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "4 × 3 × 5", "[0, 0]"), exception.getMessage());
    }

    @Test
    void testWrongSliceNotEnoughExpressionsWithMasked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.slice(new Object[0]));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "30", "[]"), exception.getMessage());
    }

    @Test
    void testToString() {
        String str = slice.toString();
        assertEquals(array.getNamePrefix() + " NDArrayView<Complex Float>(3 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = slice.contentToString();
        String lineFormat = "%10.5e+%10.5ei\t%10.5e+%10.5ei\t%10.5e+%10.5ei\t%n";
        String expected;
        if (!array.dtype().equals(Complex.class)) {
            expected = new StringBuilder()
                    .append(array.getNamePrefix() + " NDArrayView<Complex Float>(3 × 3)" + System.lineSeparator())
                    .append(String.format(lineFormat, wrapToComplex(5), wrapToComplex(25), wrapToComplex(45)))
                    .append(String.format(lineFormat, wrapToComplex(9), wrapToComplex(29), wrapToComplex(49)))
                    .append(String.format(lineFormat, wrapToComplex(13), wrapToComplex(33), wrapToComplex(53))).toString();
        } else {
            expected = new StringBuilder()
                    .append(array.getNamePrefix() + " NDArrayView<Complex Float>(3 × 3)" + System.lineSeparator())
                    .append(String.format(lineFormat, 5., 0., 25., 0., 45., 0.))
                    .append(String.format(lineFormat, 9., 0., 29., 0., 49., 0.))
                    .append(String.format(lineFormat, 13., 0., 33., 0., 53., 0.)).toString();
        }
        assertEquals(expected, str);
    }

    @Test
    void testConcatenate() {
        NDArray<Complex> array2 = new BasicComplexFloatNDArray(3, 5).fill(1);
        NDArray<Complex> array3 = slice.concatenate(1, array2);
        for (int i = 0; i < slice.shape(0); i++)
            for (int j = 0; j < slice.shape(1); j++)
                assertSpecializedEquals(slice.get(i, j), array3.get(i, j));
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = slice.shape(1); j < slice.shape(1) + array2.shape(1); j++)
                assertSpecializedEquals(wrapToComplex(1), array3.get(i, j));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Complex> array2 = array.copy().fill(1).slice(":3", "1:3", 1);
        NDArray<Complex> array3 = new BasicComplexFloatNDArray(4, 3).permuteDims(1, 0);
        NDArray<Complex> array4 = new BasicComplexFloatNDArray(15).fill(2).reshape(3, 5);
        NDArray<Complex> array5 = slice.concatenate(1, array2, array3, array4);
        int start = 0;
        int end = slice.shape(1);
        for (int i = 0; i < slice.shape(0); i++)
            for (int j = start; j < end; j++)
                assertSpecializedEquals(slice.get(i, j), array5.get(i, j));
        start = end;
        end += array2.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                assertSpecializedEquals(wrapToComplex(1), array5.get(i, j));
        start = end;
        end += array3.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                assertSpecializedEquals(wrapToComplex(0), array5.get(i, j));
        start = end;
        end += array4.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                assertSpecializedEquals(wrapToComplex(2), array5.get(i, j));
    }

    @Test
    void testEquals() {
        NDArray<Complex> slice2 = array.slice(1, new Range(1, 4), Range.ALL);
        assertEquals(slice, slice2);
    }

}
