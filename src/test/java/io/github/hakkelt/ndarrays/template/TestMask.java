package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;
import io.github.hakkelt.ndarrays.internal.*;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1Mask")
@Patterns({ "BasicByteNDArray", "NDArrayView<Byte>", "/Byte/", "wrapToDouble(value)", "BasicLongNDArray",
        "RealNDArrayReshapeView", "%6d" })
@Replacements({ "BasicShortNDArray", "NDArrayView<Short>", "Short", "wrapToDouble(value)", "BasicLongNDArray",
        "RealNDArrayReshapeView", "%6d" })
@Replacements({ "BasicIntegerNDArray", "NDArrayView<Integer>", "Integer", "wrapToDouble(value)", "BasicLongNDArray",
        "RealNDArrayReshapeView", "%6d" })
@Replacements({ "BasicLongNDArray", "NDArrayView<Long>", "Long", "wrapToDouble(value)", "BasicShortNDArray",
        "RealNDArrayReshapeView", "%6d" })
@Replacements({ "BasicFloatNDArray", "NDArrayView<Float>", "Float", "wrapToDouble(value)", "BasicLongNDArray",
        "RealNDArrayReshapeView", "%10.5e" })
@Replacements({ "BasicDoubleNDArray", "NDArrayView<Double>", "Double", "wrapToDouble(value)", "BasicLongNDArray",
        "RealNDArrayReshapeView", "%10.5e" })
@Replacements(value = { "BasicBigIntegerNDArray", "NDArrayView<BigInteger>", "BigInteger", "wrapToDouble(value)",
        "BasicLongNDArray", "RealNDArrayReshapeView", "%6d" }, extraImports = "java.math.BigInteger")
@Replacements(value = { "BasicBigDecimalNDArray", "NDArrayView<BigDecimal>", "BigDecimal", "wrapToDouble(value)",
        "BasicLongNDArray", "RealNDArrayReshapeView", "%10.5e" }, extraImports = "java.math.BigDecimal")
@Replacements(value = { "BasicComplexFloatNDArray", "NDArrayView<Complex Float>", "Complex", "value.getReal()",
        "BasicComplexDoubleNDArray", "ComplexNDArrayReshapeView",
        "%10.5e+%10.5ei" }, extraImports = "org.apache.commons.math3.complex.Complex")
@Replacements(value = { "BasicComplexDoubleNDArray", "NDArrayView<Complex Double>", "Complex", "value.getReal()",
        "BasicComplexFloatNDArray", "ComplexNDArrayReshapeView",
        "%10.5e+%10.5ei" }, extraImports = "org.apache.commons.math3.complex.Complex")
class TestMask extends TestBase {
    NDArray<Byte> array, masked;
    NDArray<Byte> mask;

    @BeforeEach
    void setup() {
        array = new BasicByteNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToByte(index));
        mask = new BasicByteNDArray(array.mapWithLinearIndices((value, index) -> wrapToByte(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    void testNotEqual() {
        assertNotEquals(masked, new int[0]); // NOSONAR
        assertNotEquals(masked, new BasicComplexFloatNDArray(masked.shape()));
        assertNotEquals(masked, new BasicByteNDArray(4, 15));
        assertNotEquals(masked, new BasicByteNDArray(4, 3, 5));
    }

    @Test
    void testEqualWithSameType() {
        NDArray<Byte> masked2 = array.mask(mask);
        assertEquals(masked, masked2);
        NDArray<Byte> masked3 = array.mask(value -> wrapToDouble(value) > 20);
        assertNotEquals(masked, masked3);
        NDArray<Byte> array2 = array.similar();
        NDArray<Byte> masked4 = array2.mask(mask);
        assertNotEquals(masked, masked4);
        NDArray<Byte> array3 = array.copy();
        NDArray<Byte> masked5 = array3.mask(mask);
        assertEquals(masked, masked5);
    }

    @Test
    void testEqualWithDifferentType() {
        NDArray<?> array2 = new BasicLongNDArray(masked);
        assertEquals(masked, array2);
        array2.set(0, 5);
        assertNotEquals(masked, array2);
    }

    @Test
    void testToString() {
        String str = masked.toString();
        assertEquals(array.getNamePrefix() + " NDArrayView<Byte>(30)", str);
    }

    @Test
    void testcontentToString() {
        String str = masked.contentToString();
        String numberFormat = "%6d\t";
        String line;
        if (masked.dtype() != Complex.class) {
            line = masked.streamLinearIndices()
                .mapToObj(i -> String.format(numberFormat, wrapToByte(i * 2 + 1)))
                .reduce("", (a, b) -> String.join("", a, b));
        } else {
            line = masked.streamLinearIndices()
                .mapToObj(i -> String.format(numberFormat, wrapToDouble(i * 2 + 1), 0.))
                .reduce("", (a, b) -> String.join("", a, b));
        }
        String expected = new StringBuilder().append(array.getNamePrefix() + " NDArrayView<Byte>(30)")
                .append(System.lineSeparator()).append(line).toString();
        assertEquals(expected, str);
    }

    @Test
    void testConcatenate() {
        NDArray<Byte> array2 = new BasicByteNDArray(5).fill(1);
        NDArray<Byte> array3 = masked.concatenate(0, array2);
        for (int i = 0; i < masked.shape(0); i++)
            assertEquals(masked.get(i), array3.get(i));
        for (int i = masked.shape(0); i < masked.shape(0) + array2.shape(0); i++)
            assertSpecializedEquals(wrapToByte(1), array3.get(i));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Byte> array2 = masked.copy().fill(1).slice("1:1");
        NDArray<Byte> array3 = new BasicByteNDArray(5).permuteDims(0);
        NDArray<Byte> array4 = new BasicByteNDArray(3, 3).fill(2).reshape(9);
        NDArray<Byte> array5 = masked.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = masked.shape(0);
        for (int i = start; i < end; i++)
            assertEquals(masked.get(i), array5.get(i));
        start = end;
        end += array2.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToByte(1), array5.get(i));
        start = end;
        end += array3.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToByte(0), array5.get(i));
        start = end;
        end += array4.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToByte(2), array5.get(i));
    }

    @Test
    void testDimensionMismatchMask() {
        mask = new BasicByteNDArray(4, 3, 5).fill(1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.mask(mask));
        assertEquals(String.format(Errors.MASK_DIMENSION_MISMATCH, "4 × 5 × 3", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testIdentityMask() {
        mask = new BasicByteNDArray(array.shape()).fill(1);
        assertTrue(array.mask(mask) instanceof RealNDArrayReshapeView);
    }

    @Test
    void testMask() {
        masked.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(value, wrapToByte(index * 2 + 1)));
        masked.fill(0);
        array.forEachWithLinearIndices(
                (value, index) -> assertSpecializedEquals(value, index % 2 == 0 ? wrapToByte(index) : wrapToByte(0)));
    }

    @Test
    void testMaskWithPredicate() {
        NDArray<Byte> masked = array.mask(value -> wrapToDouble(value) > 20);
        masked.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(wrapToDouble(value) <= 20));
    }

    @Test
    void testMaskWithPredicateWithLinearIndices() {
        NDArray<Byte> masked = array.maskWithLinearIndices((value, i) -> wrapToDouble(value) > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(wrapToDouble(value) > 20 && i < 10));
        masked.fill(0);
        array.forEachWithLinearIndices((value, i) -> assertTrue(wrapToDouble(value) <= 20 || i >= 10));
    }

    @Test
    void testMaskWithPredicateWithCartesianIndices() {
        NDArray<Byte> masked = array.maskWithCartesianIndices((value, idx) -> wrapToDouble(value) > 20 && idx[0] == 0);
        masked.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        masked.fill(0);
        array.forEachWithCartesianIndices((value, idx) -> assertTrue(wrapToDouble(value) <= 20 || idx[0] != 0));
    }

    @Test
    void testMaskMask() {
        NDArray<Byte> mask2 = new BasicByteNDArray(
                masked.map(value -> wrapToDouble(value) > 20 ? wrapToByte(1) : wrapToByte(0)));
        NDArray<Byte> masked2 = masked.mask(mask2);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20));
    }

    @Test
    void testMaskMaskWithPredicate() {
        NDArray<Byte> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<Byte> masked2 = masked.mask(value -> wrapToDouble(value) < 25);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20 && wrapToDouble(value) < 25));
        masked2.fill(0);
        array.forEach(value -> assertTrue(wrapToDouble(value) <= 20 || wrapToDouble(value) >= 25));
    }

    @Test
    void testMaskMaskWithPredicateWithLinearIndices() {
        NDArray<Byte> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<Byte> masked2 = masked.maskWithLinearIndices((value, i) -> wrapToDouble(value) % 2 == 0 && i < 30);
        masked2.forEachWithLinearIndices(
                (value, i) -> assertTrue(wrapToDouble(value) % 2 == 0 && wrapToDouble(value) > 20));
        assertEquals(15, masked2.length());
    }

    @Test
    void testMaskMaskWithPredicateWithCartesianIndices() {
        NDArray<Byte> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<Byte> masked2 = masked.maskWithCartesianIndices((value, idx) -> idx[0] % 2 == 0);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        assertEquals(masked.length() / 2 + masked.length() % 2, masked2.length());
    }
}
