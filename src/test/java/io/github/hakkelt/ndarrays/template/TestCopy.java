package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;
import io.github.hakkelt.ndarrays.internal.Errors;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1Copy")
@Patterns({ "BasicByteNDArray", "/Byte/", "BasicLongNDArray" })
@Replacements({ "BasicShortNDArray", "Short", "BasicLongNDArray" })
@Replacements({ "BasicIntegerNDArray", "Integer", "BasicLongNDArray" })
@Replacements({ "BasicLongNDArray", "Long", "BasicShortNDArray" })
@Replacements({ "BasicFloatNDArray", "Float", "BasicLongNDArray" })
@Replacements({ "BasicDoubleNDArray", "Double", "BasicLongNDArray" })
@Replacements(value = { "BasicBigIntegerNDArray", "BigInteger", "BasicLongNDArray" }, extraImports = "java.math.BigInteger")
@Replacements(value = { "BasicBigDecimalNDArray", "BigDecimal", "BasicLongNDArray" }, extraImports = "java.math.BigDecimal")
@Replacements(value = { "BasicComplexFloatNDArray", "Complex", "BasicLongNDArray" }, extraImports = "org.apache.commons.math3.complex.Complex")
@Replacements(value = { "BasicComplexDoubleNDArray", "Complex", "BasicLongNDArray" }, extraImports = "org.apache.commons.math3.complex.Complex")
class TestCopy extends TestBase {
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
        mask = new BasicByteNDArray(array.mapWithLinearIndices((value, index) -> wrapToByte(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testCopy() {
        NDArray<Byte> array2 = array.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(array.get(5), array2.get(5));
    }

    @Test
    @Patterns({"testCopyFromArrayToArray", "lhs = array", "rhs = array.similar()"})
    @Replacements({"testCopyFromArrayToSlice", "lhs = slice", "rhs = slice.similar()"})
    @Replacements({"testCopyFromSliceToArray", "lhs = slice.similar()", "rhs = slice"})
    @Replacements({"testCopyFromSliceToSlice", "lhs = slice", "rhs = array.slice(0, \"1:4\", \":\")"})
    @Replacements({"testCopyFromArrayToReshaped", "lhs = reshaped", "rhs = reshaped.similar()"})
    @Replacements({"testCopyFromReshapedToArray", "lhs = reshaped.similar()", "rhs = reshaped"})
    @Replacements({"testCopyFromReshapedToReshaped", "lhs = reshaped", "rhs = array.similar().reshape(20, 3)"})
    @Replacements({"testCopyFromArrayToPArray", "lhs = pArray", "rhs = pArray.similar()"})
    @Replacements({"testCopyFromPArrayToArray", "lhs = pArray.similar()", "rhs = pArray"})
    @Replacements({"testCopyFromPArrayToPArray", "lhs = pArray", "rhs = array.similar().permuteDims(0, 2, 1)"})
    @Replacements({"testCopyFromArrayToMasked", "lhs = masked", "rhs = masked.similar()"})
    @Replacements({"testCopyFromMaskedToArray", "lhs = masked.similar()", "rhs = masked"})
    @Replacements({"testCopyFromMaskedToMasked", "lhs = masked", "rhs = array.inverseMask(mask)"})
    void testCopyFromArrayToArray() {
        NDArray<Byte> lhs = array;
        NDArray<Byte> rhs = array.similar();
        NDArray<Byte> result = lhs.copyFrom(rhs);
        assertSame(lhs, result);
        for (int i = 0; i < result.length(); i++)
            assertSpecializedEquals(rhs.get(i), result.get(i));
    }

    @Test
    @Patterns({"testCopyFromFloatArrayWithArray", "array", "float[][][]", "float[4][5][3]"})
    @Replacements({"testCopyFromFloatArrayWithSlice", "slice", "float[][]", "float[3][3]"})
    @Replacements({"testCopyFromFloatArrayWithReshaped", "reshaped", "float[][]", "float[20][3]"})
    @Replacements({"testCopyFromFloatArrayWithPArray", "pArray", "float[][][]", "float[4][3][5]"})
    @Replacements({"testCopyFromFloatArrayWithMasked", "masked", "float[]", "float[30]"})
    @Replacements({"testCopyFromDoubleArrayWithArray", "array", "double[][][]", "double[4][5][3]"})
    @Replacements({"testCopyFromDoubleArrayWithSlice", "slice", "double[][]", "double[3][3]"})
    @Replacements({"testCopyFromDoubleArrayWithReshaped", "reshaped", "double[][]", "double[20][3]"})
    @Replacements({"testCopyFromDoubleArrayWithPArray", "pArray", "double[][][]", "double[4][3][5]"})
    @Replacements({"testCopyFromDoubleArrayWithMasked", "masked", "double[]", "double[30]"})
    @Replacements({"testCopyFromByteArrayWithArray", "array", "byte[][][]", "byte[4][5][3]"})
    @Replacements({"testCopyFromByteArrayWithSlice", "slice", "byte[][]", "byte[3][3]"})
    @Replacements({"testCopyFromByteArrayWithReshaped", "reshaped", "byte[][]", "byte[20][3]"})
    @Replacements({"testCopyFromByteArrayWithPArray", "pArray", "byte[][][]", "byte[4][3][5]"})
    @Replacements({"testCopyFromByteArrayWithMasked", "masked", "byte[]", "byte[30]"})
    @Replacements({"testCopyFromShortArrayWithArray", "array", "short[][][]", "short[4][5][3]"})
    @Replacements({"testCopyFromShortArrayWithSlice", "slice", "short[][]", "short[3][3]"})
    @Replacements({"testCopyFromShortArrayWithReshaped", "reshaped", "short[][]", "short[20][3]"})
    @Replacements({"testCopyFromShortArrayWithPArray", "pArray", "short[][][]", "short[4][3][5]"})
    @Replacements({"testCopyFromShortArrayWithMasked", "masked", "short[]", "short[30]"})
    @Replacements({"testCopyFromIntArrayWithArray", "array", "int[][][]", "int[4][5][3]"})
    @Replacements({"testCopyFromIntArrayWithSlice", "slice", "int[][]", "int[3][3]"})
    @Replacements({"testCopyFromIntArrayWithReshaped", "reshaped", "int[][]", "int[20][3]"})
    @Replacements({"testCopyFromIntArrayWithPArray", "pArray", "int[][][]", "int[4][3][5]"})
    @Replacements({"testCopyFromIntArrayWithMasked", "masked", "int[]", "int[30]"})
    @Replacements({"testCopyFromLongArrayWithArray", "array", "long[][][]", "long[4][5][3]"})
    @Replacements({"testCopyFromLongArrayWithSlice", "slice", "long[][]", "long[3][3]"})
    @Replacements({"testCopyFromLongArrayWithReshaped", "reshaped", "long[][]", "long[20][3]"})
    @Replacements({"testCopyFromLongArrayWithPArray", "pArray", "long[][][]", "long[4][3][5]"})
    @Replacements({"testCopyFromLongArrayWithMasked", "masked", "long[]", "long[30]"})
    void testCopyFromFloatArrayWithArray() {
        float[][][] primitiveArray = new float[4][5][3];
        array.copyFrom(primitiveArray);
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(wrapToByte(0), array.get(i));
    }

    @Test
    @Patterns({"testWrongCopyFromFloatArrayWithArray", "float[][][]", "array", "float[4][3][5]", "4 × 3 × 5", "4 × 5 × 3"})
    @Replacements({"testWrongCopyFromFloatArrayWithSlice", "float[][]", "slice", "float[1][9]", "1 × 9", "3 × 3"})
    @Replacements({"testWrongCopyFromFloatArrayWithReshaped", "float[][]", "reshaped", "float[15][4]", "15 × 4", "20 × 3"})
    @Replacements({"testWrongCopyFromFloatArrayWithPArray", "float[][][]", "pArray", "float[5][3][4]", "5 × 3 × 4", "4 × 3 × 5"})
    @Replacements({"testWrongCopyFromFloatArrayWithMasked", "float[][]", "masked", "float[1][30]", "1 × 30", "30"})
    @Replacements({"testWrongCopyFromDoubleArrayWithArray", "double[][][]", "array", "double[4][3][5]", "4 × 3 × 5", "4 × 5 × 3"})
    @Replacements({"testWrongCopyFromDoubleArrayWithSlice", "double[][]", "slice", "double[1][9]", "1 × 9", "3 × 3"})
    @Replacements({"testWrongCopyFromDoubleArrayWithReshaped", "double[][]", "reshaped", "double[15][4]", "15 × 4", "20 × 3"})
    @Replacements({"testWrongCopyFromDoubleArrayWithPArray", "double[][][]", "pArray", "double[5][3][4]", "5 × 3 × 4", "4 × 3 × 5"})
    @Replacements({"testWrongCopyFromDoubleArrayWithMasked", "double[][]", "masked", "double[1][30]", "1 × 30", "30"})
    @Replacements({"testWrongCopyFromByteArrayWithArray", "byte[][][]", "array", "byte[4][3][5]", "4 × 3 × 5", "4 × 5 × 3"})
    @Replacements({"testWrongCopyFromByteArrayWithSlice", "byte[][]", "slice", "byte[1][9]", "1 × 9", "3 × 3"})
    @Replacements({"testWrongCopyFromByteArrayWithReshaped", "byte[][]", "reshaped", "byte[15][4]", "15 × 4", "20 × 3"})
    @Replacements({"testWrongCopyFromByteArrayWithPArray", "byte[][][]", "pArray", "byte[5][3][4]", "5 × 3 × 4", "4 × 3 × 5"})
    @Replacements({"testWrongCopyFromByteArrayWithMasked", "byte[][]", "masked", "byte[1][30]", "1 × 30", "30"})
    @Replacements({"testWrongCopyFromShortArrayWithArray", "short[][][]", "array", "short[4][3][5]", "4 × 3 × 5", "4 × 5 × 3"})
    @Replacements({"testWrongCopyFromShortArrayWithSlice", "short[][]", "slice", "short[1][9]", "1 × 9", "3 × 3"})
    @Replacements({"testWrongCopyFromShortArrayWithReshaped", "short[][]", "reshaped", "short[15][4]", "15 × 4", "20 × 3"})
    @Replacements({"testWrongCopyFromShortArrayWithPArray", "short[][][]", "pArray", "short[5][3][4]", "5 × 3 × 4", "4 × 3 × 5"})
    @Replacements({"testWrongCopyFromShortArrayWithMasked", "short[][]", "masked", "short[1][30]", "1 × 30", "30"})
    @Replacements({"testWrongCopyFromIntArrayWithArray", "int[][][]", "array", "int[4][3][5]", "4 × 3 × 5", "4 × 5 × 3"})
    @Replacements({"testWrongCopyFromIntArrayWithSlice", "int[][]", "slice", "int[1][9]", "1 × 9", "3 × 3"})
    @Replacements({"testWrongCopyFromIntArrayWithReshaped", "int[][]", "reshaped", "int[15][4]", "15 × 4", "20 × 3"})
    @Replacements({"testWrongCopyFromIntArrayWithPArray", "int[][][]", "pArray", "int[5][3][4]", "5 × 3 × 4", "4 × 3 × 5"})
    @Replacements({"testWrongCopyFromIntArrayWithMasked", "int[][]", "masked", "int[1][30]", "1 × 30", "30"})
    @Replacements({"testWrongCopyFromLongArrayWithArray", "long[][][]", "array", "long[4][3][5]", "4 × 3 × 5", "4 × 5 × 3"})
    @Replacements({"testWrongCopyFromLongArrayWithSlice", "long[][]", "slice", "long[1][9]", "1 × 9", "3 × 3"})
    @Replacements({"testWrongCopyFromLongArrayWithReshaped", "long[][]", "reshaped", "long[15][4]", "15 × 4", "20 × 3"})
    @Replacements({"testWrongCopyFromLongArrayWithPArray", "long[][][]", "pArray", "long[5][3][4]", "5 × 3 × 4", "4 × 3 × 5"})
    @Replacements({"testWrongCopyFromLongArrayWithMasked", "long[][]", "masked", "long[1][30]", "1 × 30", "30"})
    void testWrongCopyFromFloatArrayWithArray() {
        float[][][] wrongshapedArray = new float[4][3][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 3 × 5", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    @Patterns({"array", "20, 3", "20 × 3", "4 × 5 × 3"})
    @Replacements({"slice", "1, 9", "1 × 9", "3 × 3"})
    @Replacements({"reshaped", "15, 4", "15 × 4", "20 × 3"})
    @Replacements({"pArray", "5, 3, 4", "5 × 3 × 4", "4 × 3 × 5"})
    @Replacements({"masked", "1, 30", "1 × 30", "30"})
    void testWrongCopyFrom1DFloatArrayWithArray() {
        float[][][] wrongshapedArray = new float[4][2][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4 × 2 × 5", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    @Patterns({"array", "20, 3", "20 × 3", "4 × 5 × 3"})
    @Replacements({"slice", "1, 9", "1 × 9", "3 × 3"})
    @Replacements({"reshaped", "15, 4", "15 × 4", "20 × 3"})
    @Replacements({"pArray", "5, 3, 4", "5 × 3 × 4", "4 × 3 × 5"})
    @Replacements({"masked", "1, 30", "1 × 30", "30"})
    void testWrongCopyFromNDArray() {
        NDArray<Byte> wrongShapedArray = new BasicByteNDArray(20, 3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongShapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "20 × 3", "4 × 5 × 3"), exception.getMessage());
    }
}
