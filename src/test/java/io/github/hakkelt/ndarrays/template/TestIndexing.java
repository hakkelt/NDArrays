package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.*;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;
import io.github.hakkelt.ndarrays.internal.Errors;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1Indexing")
@Patterns({ "BasicByteNDArray", "/Byte/" })
@Replacements({ "BasicShortNDArray", "Short" })
@Replacements({ "BasicIntegerNDArray", "Integer" })
@Replacements({ "BasicLongNDArray", "Long" })
@Replacements({ "BasicFloatNDArray", "Float" })
@Replacements({ "BasicDoubleNDArray", "Double" })
@Replacements(value = { "BasicBigIntegerNDArray", "BigInteger" }, extraImports = "java.math.BigInteger")
@Replacements(value = { "BasicBigDecimalNDArray", "BigDecimal" }, extraImports = "java.math.BigDecimal")
@Replacements(value = { "BasicComplexFloatNDArray", "Complex" }, extraImports = "org.apache.commons.math3.complex.Complex")
@Replacements(value = { "BasicComplexDoubleNDArray", "Complex" }, extraImports = "org.apache.commons.math3.complex.Complex")
class TestIndexing extends TestBase {
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
    @Patterns({ "array", "60 - 5", "-5" })
    @Replacements({ "slice", "(1 * 5 + 2) * 4 + 1", "-5" }) // -5 -> [1, 1] in slice -> [1, 2, 1] in array
    @Replacements({ "reshaped", "60 - 5", "-5" }) // linear indexing is same in reshaped as it was in array
    @Replacements({ "pArray", "(1 * 5 + 4) * 4 + 3", "-5" }) // -5 -> [3, 1, 4] in pArray -> [3, 4, 1] in array
    @Replacements({ "masked", "61 - 6", "-3" }) // -3 in masked -> -6 in array
    void testGetNegativeLinearIndexing() {
        assertSpecializedEquals(wrapToByte(60 - 5), array.get(-5));
    }

    @Test
    @Patterns({ "array", "(2 * 5 + 2) * 4 + 2", "2, -3, -1" }) // [2, -3, -1] -> [2, 2, 2]
    @Replacements({ "slice", "(2 * 5 + 3) * 4 + 1", "2, -1" }) // [2, -1] -> [2, 2] in slice -> [1, 3, 2] in array
    @Replacements({ "reshaped", "(2 * 5 + 2) * 4 + 2", "10, -1" }) // [10,-1] -> [10,2] in reshaped -> [2,2,2] in array
    @Replacements({ "pArray", "(2 * 5 + 3) * 4 + 2", "2, -1, -2" })//[2,-1,-2] -> [2,2,3] in pArray -> [2,3,2] in array
    @Replacements({ "masked", "61 - 6", "new int[]{-3}" }) // [-3] -> lin. index -6 in array
    void testGetNegativeCartesianIndexing() {
        assertSpecializedEquals(wrapToByte((2 * 5 + 2) * 4 + 2), array.get(2, -3, -1));
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
    @Patterns({ "slice", "2 * 3 + 2", "2, -1", "1, 3, 2" }) // [2, -1] -> [2, 2] in slice -> [1, 3, 2] in array
    @Replacements({ "reshaped", "2 * 20 + 9", "9, 2", "1, 2, 2" }) // [9, 2] in reshaped -> [1, 2, 2] in array
    @Replacements({"pArray","(4 * 3 + 1) * 4 + 1", "1, 1, -1","1, 4, 1"})//[1,1,-1] -> [1,1,4] in pArray -> [1,4,1] in array
    @Replacements({ "masked", "-3", "new int[]{-3}", "61 - 6" }) // -3 in masked -> -6 in array
    void testSetLinearIndexingGetCartesianIndexing() {
        slice.set(1, 2 * 3 + 2);
        assertSpecializedEquals(wrapToByte(1), slice.get(2, -1));
        assertSpecializedEquals(wrapToByte(1), array.get(1, 3, 2));
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
    @Patterns({ "slice", "2 * 3 + 2", "2, -1", "1, 3, 2" }) // [2, -1] -> [2, 2] in slice -> [1, 3, 2] in array
    @Replacements({ "reshaped", "2 * 20 + 9", "9, 2", "1, 2, 2" }) // [9, 2] in reshaped -> [1, 2, 2] in array
    @Replacements({"pArray","(4 * 3 + 1) * 4 + 1","1, 1, -1","1, 4, 1"})//[1,1,-1] -> [1,1,4] in pArray -> [1,4,1] in array
    @Replacements({ "masked", "-3", "new int[]{-3}", "61 - 6" }) // -3 in masked -> -6 in array
    void testSetCartesianIndexingGetLinearIndexing() {
        slice.set(1, 2, -1);
        assertSpecializedEquals(wrapToByte(1), slice.get(2 * 3 + 2));
        assertSpecializedEquals(wrapToByte(1), array.get(1, 3, 2));
    }

    @Test
    @Patterns({ "array", "60" })
    @Replacements({ "slice", "9" })
    @Replacements({ "reshaped", "60" })
    @Replacements({ "pArray", "60" })
    @Replacements({ "masked", "30" })
    void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(60));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), 60), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "-61" })
    @Replacements({ "slice", "-10" })
    @Replacements({ "reshaped", "-61" })
    @Replacements({ "pArray", "-61" })
    @Replacements({ "masked", "-31" })
    void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(-61));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), -61), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "[1, 1, 3]", "1, 1, 3", "4 × 5 × 3" })
    @Replacements({ "slice", "[1, 3]", "1, 3", "3 × 3" })
    @Replacements({ "reshaped", "[1, 3]", "1, 3", "20 × 3" })
    @Replacements({ "pArray", "[1, 3, 1]", "1, 3, 1", "4 × 3 × 5" })
    @Replacements({ "masked", "[30]", "new int[]{30}", "30" })
    void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1, 1, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "[1, -6, 1]", "1, -6, 1", "4 × 5 × 3" })
    @Replacements({ "slice", "[-4, 3]", "-4, 3", "3 × 3" })
    @Replacements({ "reshaped", "[-21, 3]", "-21, 3", "20 × 3" })
    @Replacements({ "pArray", "[1, -4, 1]", "1, -4, 1", "4 × 3 × 5" })
    @Replacements({ "masked", "[-31]", "new int[]{-31}", "30" })
    void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1, -6, 1));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "60" })
    @Replacements({ "slice", "9" })
    @Replacements({ "reshaped", "60" })
    @Replacements({ "pArray", "60" })
    @Replacements({ "masked", "30" })
    void testWrongSetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.set(0, 60));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), 60), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "-61" })
    @Replacements({ "slice", "-10" })
    @Replacements({ "reshaped", "-61" })
    @Replacements({ "pArray", "-61" })
    @Replacements({ "masked", "-31" })
    void testWrongSetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.set(0, -61));
        assertEquals(String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), -61), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "[1, 1, 3]", "1, 1, 3", "4 × 5 × 3" })
    @Replacements({ "slice", "[1, 3]", "1, 3", "3 × 3" })
    @Replacements({ "reshaped", "[1, 3]", "1, 3", "20 × 3" })
    @Replacements({ "pArray", "[1, 3, 1]", "1, 3, 1", "4 × 3 × 5" })
    @Replacements({ "masked", "[30]", "new int[]{30}", "30" })
    void testWrongSetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.set(0, 1, 1, 3));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "[1, -6, 1]", "1, -6, 1", "4 × 5 × 3" })
    @Replacements({ "slice", "[-4, 3]", "-4, 3", "3 × 3" })
    @Replacements({ "reshaped", "[-21, 3]", "-21, 3", "20 × 3" })
    @Replacements({ "pArray", "[1, -4, 1]", "1, -4, 1", "4 × 3 × 5" })
    @Replacements({ "masked", "[-31]", "new int[]{-31}", "30" })
    void testWrongSetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.set(0, 1, -6, 1));
        assertEquals(String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "1, 1, 1, 0", "3" })
    @Replacements({ "slice", "1, 1, 0, 0", "2" })
    @Replacements({ "reshaped", "1, 1, 0, 0", "2" })
    @Replacements({ "pArray", "1, 1, 0, 0", "3" })
    @Replacements({ "masked", "1, 1, 0, 0", "1" })
    void testGetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1, 1, 1, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 3), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "1, 1", "2, 3" })
    @Replacements({ "slice", "new int[]{1}", "1, 2" })
    @Replacements({ "reshaped", "new int[]{1}", "1, 2" })
    @Replacements({ "pArray", "1, 1", "2, 3" })
    @Replacements({ "masked", "new int[]{}", "0, 1" })
    void testGetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1, 1));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 2, 3), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "1, 1, 1, 0", "3" })
    @Replacements({ "slice", "1, 1, 0, 0", "2" })
    @Replacements({ "reshaped", "1, 1, 0, 0", "2" })
    @Replacements({ "pArray", "1, 1, 0, 0", "3" })
    @Replacements({ "masked", "1, 1, 0, 0", "1" })
    void testSetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.set(0, 1, 1, 1, 0));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 4, 3), exception.getMessage());
    }

    @Test
    @Patterns({ "array", "1, 1", "2, 3" })
    @Replacements({ "slice", "new int[]{1}", "1, 2" })
    @Replacements({ "reshaped", "new int[]{1}", "1, 2" })
    @Replacements({ "pArray", "1, 1", "2, 3" })
    @Replacements({ "masked", "new int[]{}", "0, 1" })
    void testSetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.set(0, 1, 1));
        assertEquals(String.format(Errors.DIMENSION_MISMATCH, 2, 3), exception.getMessage());
    }

}
