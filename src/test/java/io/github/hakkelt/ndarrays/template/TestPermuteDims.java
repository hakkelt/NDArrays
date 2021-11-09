package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;
import io.github.hakkelt.ndarrays.internal.Errors;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1PermuteDims")
@Patterns({ "BasicByteNDArray", "NDArrayView<Byte>", "/Byte/", "BasicLongNDArray", "%6d" })
@Replacements({ "BasicShortNDArray", "NDArrayView<Short>", "Short", "BasicLongNDArray", "%6d" })
@Replacements({ "BasicIntegerNDArray", "NDArrayView<Integer>", "Integer", "BasicLongNDArray", "%6d" })
@Replacements({ "BasicLongNDArray", "NDArrayView<Long>", "Long", "BasicShortNDArray", "%6d" })
@Replacements({ "BasicFloatNDArray", "NDArrayView<Float>", "Float", "BasicLongNDArray", "%10.5e" })
@Replacements({ "BasicDoubleNDArray", "NDArrayView<Double>", "Double", "BasicLongNDArray", "%10.5e" })
@Replacements(value = { "BasicBigIntegerNDArray", "NDArrayView<BigInteger>", "BigInteger", "BasicLongNDArray",
        "%6d" }, extraImports = "java.math.BigInteger")
@Replacements(value = { "BasicBigDecimalNDArray", "NDArrayView<BigDecimal>", "BigDecimal", "BasicLongNDArray",
        "%10.5e" }, extraImports = "java.math.BigDecimal")
@Replacements(value = { "BasicComplexFloatNDArray", "NDArrayView<Complex Float>", "Complex", "BasicComplexDoubleNDArray",
        "%10.5e+%10.5ei" }, extraImports = "org.apache.commons.math3.complex.Complex")
@Replacements(value = { "BasicComplexDoubleNDArray", "NDArrayView<Complex Double>", "Complex", "BasicComplexFloatNDArray",
        "%10.5e+%10.5ei" }, extraImports = "org.apache.commons.math3.complex.Complex")
class TestPermuteDims extends TestBase {
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
    void testNotEqual() {
        assertNotEquals(pArray, new int[0]); // NOSONAR
        assertNotEquals(pArray, new BasicComplexFloatNDArray(pArray.shape()));
        assertNotEquals(pArray, new BasicByteNDArray(6, 10));
        assertNotEquals(pArray, new BasicByteNDArray(4, 5, 3));
    }

    @Test
    void testEqualWithSameType() {
        NDArray<Byte> pArray2 = array.permuteDims(0, 2, 1);
        assertEquals(pArray, pArray2);
        NDArray<Byte> pArray3 = array.copy().permuteDims(0, 2, 1);
        assertEquals(pArray, pArray3);
    }

    @Test
    void testEqualWithDifferentType() {
        NDArray<?> array2 = new BasicLongNDArray(pArray);
        assertEquals(pArray, array2);
        array2.set(0, 5);
        assertNotEquals(pArray, array2);
    }

    @Test
    void testToString() {
        String str = pArray.toString();
        assertEquals(array.getNamePrefix() + " NDArrayView<Byte>(4 × 3 × 5)", str);
    }

    @Test
    void testContentToString() {
        String str = pArray.contentToString();
        String lineFormat = "%6d\t%6d\t%6d\t%n";
        StringBuilder sb = new StringBuilder();
        sb.append(array.getNamePrefix() + " NDArrayView<Byte>(4 × 3 × 5)" + System.lineSeparator());
        for (int i = 0; i < pArray.shape(2); i++) {
            sb.append("[:, :, " + i + "] =" + System.lineSeparator());
            for (int j = 0; j < pArray.shape(0); j++) {
                if (pArray.dtype() == Complex.class)
                    sb.append(String.format(lineFormat, wrapToDouble(i * 4 + j), 0., wrapToDouble(i * 4 + j + 20), 0., wrapToDouble(i * 4 + j + 40), 0.));
                else
                    sb.append(String.format(lineFormat, wrapToByte(i * 4 + j), wrapToByte(i * 4 + j + 20), wrapToByte(i * 4 + j + 40)));
            }
            sb.append(System.lineSeparator());
        }
        String expected = sb.toString();
        assertEquals(expected, str);
    }

    @Test
    void testConcatenate() {
        NDArray<Byte> array2 = new BasicByteNDArray(2, 3, 5).fill(1);
        NDArray<Byte> array3 = pArray.concatenate(0, array2);
        for (int i = 0; i < pArray.shape(0); i++)
            for (int j = 0; j < pArray.shape(1); j++)
                for (int k = 0; k < pArray.shape(1); k++)
                    assertEquals(pArray.get(i, j, k), array3.get(i, j, k));
        for (int i = pArray.shape(0); i < pArray.shape(0) + array2.shape(0); i++)
            for (int j = 0; j < pArray.shape(1); j++)
                for (int k = 0; k < pArray.shape(1); k++)
                    assertSpecializedEquals(wrapToByte(1), array3.get(i, j, k));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Byte> array2 = pArray.copy().fill(1).slice(":", "1:3", ":");
        NDArray<Byte> array3 = new BasicByteNDArray(2, 5, 4).permuteDims(2, 0, 1);
        NDArray<Byte> array4 = new BasicByteNDArray(20).fill(2).reshape(4, 1, 5);
        NDArray<Byte> array5 = pArray.concatenate(1, array2, array3, array4);
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
                    assertSpecializedEquals(wrapToByte(1), array5.get(i, j, k));
        start = end;
        end += array3.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertSpecializedEquals(wrapToByte(0), array5.get(i, j, k));
        start = end;
        end += array4.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertSpecializedEquals(wrapToByte(2), array5.get(i, j, k));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testDimensionMismatch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0, 2, 1, 3));
        String shapeString = String.join(" × ", IntStream.of(array.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2, 1, 3]", shapeString), exception.getMessage());
    }

    @Test
    @Patterns({"array", "-1, 0, 1"})
    @Replacements({"slice", "-1, 0"})
    @Replacements({"reshaped", "-1, 0"})
    @Replacements({"pArray", "-1, 0, 1"})
    @Replacements({"masked", "-1"})
    void testNegativeDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(-1, 0, 1));
        String shapeString = String.join(" × ", IntStream.of(array.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[-1, 0, 1]", shapeString), exception.getMessage());
    }

    @Test
    @Patterns({"array", "3, 0, 1"})
    @Replacements({"slice", "2, 0"})
    @Replacements({"reshaped", "2, 0"})
    @Replacements({"pArray", "3, 0, 1"})
    @Replacements({"masked", "1"})
    void testTooLargeDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(3, 0, 1));
        String shapeString = String.join(" × ", IntStream.of(array.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[3, 0, 1]", shapeString), exception.getMessage());
    }

    @Test
    @Patterns({"array", "0, 1, 2"})
    @Replacements({"slice", "0, 1"})
    @Replacements({"reshaped", "0, 1"})
    @Replacements({"pArray", "0, 1, 2"})
    @Replacements({"masked", "0"})
    void testIdentityPermuteDims() {
        assertSame(array, array.permuteDims(0, 1, 2));
    }

    @Test
    @Patterns({"array", "0, 2, 1", "idx[0], idx[2], idx[1]"})
    @Replacements({"slice", "1, 0", "idx[1], idx[0]"})
    @Replacements({"reshaped", "1, 0", "idx[1], idx[0]"})
    @Replacements({"pArray", "0, 2, 1", "idx[0], idx[2], idx[1]"})
    @Replacements({"masked", "0", "idx[0]"})
    void testPermuteDims() {
        NDArray<Byte> pArray2 = array.permuteDims(0, 2, 1);
        pArray2.forEachWithCartesianIndices((value, idx) -> assertSpecializedEquals(value, array.get(idx[0], idx[2], idx[1])));
        pArray2.fill(0);
        array.forEach(value -> assertSpecializedEquals(wrapToByte(0), value));
    }

    @Test
    void testPermuteDimsPermuteDims() {
        NDArray<Byte> ppArray = pArray.permuteDims(0, 2, 1);
        array.forEachWithCartesianIndices((value, indices) -> assertEquals(value, ppArray.get(indices)));
        assertSame(array, ppArray);
    }
    
}
