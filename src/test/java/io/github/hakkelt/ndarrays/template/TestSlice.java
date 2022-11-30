package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;
import io.github.hakkelt.ndarrays.internal.Errors;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1Slice")
@Patterns({ "BasicByteNDArray", "NDArrayView<Byte>", "/Byte/", "%6d" })
@Replacements({ "BasicShortNDArray", "NDArrayView<Short>", "Short", "%6d" })
@Replacements({ "BasicIntegerNDArray", "NDArrayView<Integer>", "Integer", "%6d" })
@Replacements({ "BasicLongNDArray", "NDArrayView<Long>", "Long", "%6d" })
@Replacements({ "BasicFloatNDArray", "NDArrayView<Float>", "Float", "%8.3e" })
@Replacements({ "BasicDoubleNDArray", "NDArrayView<Double>", "Double", "%8.3e" })
@Replacements(value = { "BasicBigIntegerNDArray", "NDArrayView<BigInteger>", "BigInteger",
        "%6d" }, extraImports = "java.math.BigInteger")
@Replacements(value = { "BasicBigDecimalNDArray", "NDArrayView<BigDecimal>", "BigDecimal",
        "%8.3e" }, extraImports = "java.math.BigDecimal")
@Replacements(value = { "BasicComplexFloatNDArray", "NDArrayView<Complex Float>", "Complex",
        "%8.3e+%8.3ei" }, extraImports = "org.apache.commons.math3.complex.Complex")
@Replacements(value = { "BasicComplexDoubleNDArray", "NDArrayView<Complex Double>", "Complex",
        "%8.3e+%8.3ei" }, extraImports = "org.apache.commons.math3.complex.Complex")
class TestSlice extends TestBase {
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
        array.applyWithLinearIndices((value, index) -> wrapToByte(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = array.mapWithLinearIndices((value, index) -> wrapToByte(index % 2));
        masked = array.mask(mask);
    }

    @Test
    void testSliceIdentity() {
        slice = array.slice(":", "0:5", "::");
        assertSame(array, slice);
    }

    @Test
    void testSliceSlice() {
        NDArray<Byte> slice2 = slice.slice(":", 2);
        slice2.forEachWithCartesianIndices((value, indices) -> assertEquals(value, array.get(1, indices[0] + 1, 2)));
    }

    @Test
    void testStrided() {
        int[] expectedshape = { 1, 3, 3 };
        NDArray<Byte> slice2 = array.slice(new Range(1, 5, 4), ":2:", new Range(0, 3));
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
        NDArray<Byte> slice2 = array.slice(new Range(-1, -5, 0), ":-2:", "0::3");
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
    @Patterns({ "array", "4 × 5 × 3", "[4, 0, 0]", "4, 0, 0" })
    @Replacements({ "slice", "3 × 3", "[3, 0]", "3, 0" })
    @Replacements({ "reshaped", "20 × 3", "[20, 0]", "20, 0" })
    @Replacements({ "pArray", "4 × 3 × 5", "[4, 0, 0]", "4, 0, 0" })
    @Replacements({ "masked", "30", "[30]", "30" })
    void testWrongSliceOutOfBounds() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice(4, 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[4, 0, 0]"), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "4 × 5 × 3", "[-5, 0, 0]", "-5, 0, 0" })
    @Replacements({ "slice", "3 × 3", "[-4, 0]", "-4, 0" })
    @Replacements({ "reshaped", "20 × 3", "[-21, 0]", "-21, 0" })
    @Replacements({ "pArray", "4 × 3 × 5", "[-5, 0, 0]", "-5, 0, 0" })
    @Replacements({ "masked", "30", "[-31]", "-31" })
    void testWrongSliceNegativeOutOfBounds() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice(-5, 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[-5, 0, 0]"), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "4 × 5 × 3", "[4:, 0, 0]", "\"4:\", 0, 0" })
    @Replacements({ "slice", "3 × 3", "[3:, 0]", "\"3:\", 0" })
    @Replacements({ "reshaped", "20 × 3", "[20:, 0]", "\"20:\", 0" })
    @Replacements({ "pArray", "4 × 3 × 5", "[4:, 0, 0]", "\"4:\", 0, 0" })
    @Replacements({ "masked", "30", "[30:]", "\"30:\"" })
    void testWrongSliceOutOfBoundsAtRangeStart() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice("4:", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[4:, 0, 0]"), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "4 × 5 × 3", "[1:5, 0, 0]", "\"1:5\", 0, 0" })
    @Replacements({ "slice", "3 × 3", "[1:4, 0]", "\"1:4\", 0" })
    @Replacements({ "reshaped", "20 × 3", "[1:21, 0]", "\"1:21\", 0" })
    @Replacements({ "pArray", "4 × 3 × 5", "[1:5, 0, 0]", "\"1:5\", 0, 0" })
    @Replacements({ "masked", "30", "[1:31]", "\"1:31\"" })
    void testWrongSliceOutOfBoundsAtRangeEnd() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice("1:5", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[1:5, 0, 0]"), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "4 × 5 × 3", "[-5:, 0, 0]", "\"-5:\", 0, 0" })
    @Replacements({ "slice", "3 × 3", "[-4:, 0]", "\"-4:\", 0" })
    @Replacements({ "reshaped", "20 × 3", "[1:-21, 0]", "\"1:-21\", 0" })
    @Replacements({ "pArray", "4 × 3 × 5", "[1:-5, 0, 0]", "\"1:-5\", 0, 0" })
    @Replacements({ "masked", "30", "[1:-31]", "\"1:-31\"" })
    void testWrongSliceNegativeOutOfBoundsAtRangeStart() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice("-5:", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[-5:, 0, 0]"), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "4 × 5 × 3", "[1:-5, 0, 0]", "\"1:-5\", 0, 0" })
    @Replacements({ "slice", "3 × 3", "[1:-4, 0]", "\"1:-4\", 0" })
    @Replacements({ "reshaped", "20 × 3", "[1:-21, 0]", "\"1:-21\", 0" })
    @Replacements({ "pArray", "4 × 3 × 5", "[1:-5, 0, 0]", "\"1:-5\", 0, 0" })
    @Replacements({ "masked", "30", "[1:-31]", "\"1:-31\"" })
    void testWrongSliceNegativeOutOfBoundsAtRangeEnd() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.slice("1:-5", 0, 0));
        assertEquals(String.format(Errors.SLICE_OUT_OF_BOUNDS, "4 × 5 × 3", "[1:-5, 0, 0]"), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "4 × 5 × 3" })
    @Replacements({ "slice", "3 × 3" })
    @Replacements({ "reshaped", "20 × 3" })
    @Replacements({ "pArray", "4 × 3 × 5" })
    @Replacements({ "masked", "30" })
    void testWrongSliceTooManyExpressions() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.slice(0, ":", 0, 0));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "4 × 5 × 3", "[0, :, 0, 0]"),
                exception.getMessage());
    }

    @Test
    @Patterns({ "array", "4 × 5 × 3", "[0, 0]", "0, 0" })
    @Replacements({ "slice", "3 × 3", "[0]", "0" })
    @Replacements({ "reshaped", "20 × 3", "[0]", "0" })
    @Replacements({ "pArray", "4 × 3 × 5", "[0, 0]", "0, 0" })
    @Replacements({ "masked", "30", "[]", "new Object[0]" })
    void testWrongSliceNotEnoughExpressions() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.slice(0, 0));
        assertEquals(String.format(Errors.SLICE_DIMENSION_MISMATCH, "4 × 5 × 3", "[0, 0]"), exception.getMessage());
    }

    @Test
    void testToString() {
        String str = slice.toString();
        assertEquals(array.getNamePrefix() + " NDArrayView<Byte>(3 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = slice.contentToString();
        String lineFormat = "%6d\t%6d\t%6d\t%n";
        String expected;
        if (!array.dtype().equals(Complex.class)) {
            expected = new StringBuilder()
                    .append(array.getNamePrefix() + " NDArrayView<Byte>(3 × 3)" + System.lineSeparator())
                    .append(String.format(lineFormat, wrapToByte(5), wrapToByte(25), wrapToByte(45)))
                    .append(String.format(lineFormat, wrapToByte(9), wrapToByte(29), wrapToByte(49)))
                    .append(String.format(lineFormat, wrapToByte(13), wrapToByte(33), wrapToByte(53))).toString();
        } else {
            expected = new StringBuilder()
                    .append(array.getNamePrefix() + " NDArrayView<Byte>(3 × 3)" + System.lineSeparator())
                    .append(String.format(lineFormat, 5., 0., 25., 0., 45., 0.))
                    .append(String.format(lineFormat, 9., 0., 29., 0., 49., 0.))
                    .append(String.format(lineFormat, 13., 0., 33., 0., 53., 0.)).toString();
        }
        assertEquals(expected, str);
    }

    @Test
    void testConcatenate() {
        NDArray<Byte> array2 = new BasicByteNDArray(3, 5).fill(1);
        NDArray<Byte> array3 = slice.concatenate(1, array2);
        for (int i = 0; i < slice.shape(0); i++)
            for (int j = 0; j < slice.shape(1); j++)
                assertSpecializedEquals(slice.get(i, j), array3.get(i, j));
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = slice.shape(1); j < slice.shape(1) + array2.shape(1); j++)
                assertSpecializedEquals(wrapToByte(1), array3.get(i, j));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Byte> array2 = array.copy().fill(1).slice(":3", "1:3", 1);
        NDArray<Byte> array3 = new BasicByteNDArray(4, 3).permuteDims(1, 0);
        NDArray<Byte> array4 = new BasicByteNDArray(15).fill(2).reshape(3, 5);
        NDArray<Byte> array5 = slice.concatenate(1, array2, array3, array4);
        int start = 0;
        int end = slice.shape(1);
        for (int i = 0; i < slice.shape(0); i++)
            for (int j = start; j < end; j++)
                assertSpecializedEquals(slice.get(i, j), array5.get(i, j));
        start = end;
        end += array2.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                assertSpecializedEquals(wrapToByte(1), array5.get(i, j));
        start = end;
        end += array3.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                assertSpecializedEquals(wrapToByte(0), array5.get(i, j));
        start = end;
        end += array4.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                assertSpecializedEquals(wrapToByte(2), array5.get(i, j));
    }

    @Test
    void testEquals() {
        NDArray<Byte> slice2 = array.slice(1, new Range(1, 4), Range.ALL);
        assertEquals(slice, slice2);
    }

}
