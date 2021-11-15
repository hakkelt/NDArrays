/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestMask.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.*;

import java.math.BigDecimal;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBasicBigDecimalNDArrayMask extends TestBase {

    NDArray<BigDecimal> array;
    NDArray<BigDecimal> masked;
    NDArray<BigDecimal> mask;

    @BeforeEach
    void setup() {
        array = new BasicBigDecimalNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToBigDecimal(index));
        mask = new BasicBigDecimalNDArray(array.mapWithLinearIndices((value, index) -> wrapToBigDecimal(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    void testNotEqual() {
        assertNotEquals(masked, new int[0]); // NOSONAR
        assertNotEquals(masked, new BasicComplexFloatNDArray(masked.shape()));
        assertNotEquals(masked, new BasicBigDecimalNDArray(4, 15));
        assertNotEquals(masked, new BasicBigDecimalNDArray(4, 3, 5));
    }

    @Test
    void testEqualWithSameType() {
        NDArray<BigDecimal> masked2 = array.mask(mask);
        assertEquals(masked, masked2);
        NDArray<BigDecimal> masked3 = array.mask(value -> wrapToDouble(value) > 20);
        assertNotEquals(masked, masked3);
        NDArray<BigDecimal> array2 = array.similar();
        NDArray<BigDecimal> masked4 = array2.mask(mask);
        assertNotEquals(masked, masked4);
        NDArray<BigDecimal> array3 = array.copy();
        NDArray<BigDecimal> masked5 = array3.mask(mask);
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
        assertEquals(array.getNamePrefix() + " NDArrayView<BigDecimal>(30)", str);
    }

    @Test
    void testcontentToString() {
        String str = masked.contentToString();
        String numberFormat = "%10.5e\t";
        String line;
        if (masked.dtype() != Complex.class) {
            line = masked.streamLinearIndices()
                .mapToObj(i -> String.format(numberFormat, wrapToBigDecimal(i * 2 + 1)))
                .reduce("", (a, b) -> String.join("", a, b));
        } else {
            line = masked.streamLinearIndices()
                .mapToObj(i -> String.format(numberFormat, wrapToDouble(i * 2 + 1), 0.))
                .reduce("", (a, b) -> String.join("", a, b));
        }
        String expected = new StringBuilder().append(array.getNamePrefix() + " NDArrayView<BigDecimal>(30)")
                .append(System.lineSeparator()).append(line).toString();
        assertEquals(expected, str);
    }

    @Test
    void testConcatenate() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(5).fill(1);
        NDArray<BigDecimal> array3 = masked.concatenate(0, array2);
        for (int i = 0; i < masked.shape(0); i++)
            assertEquals(masked.get(i), array3.get(i));
        for (int i = masked.shape(0); i < masked.shape(0) + array2.shape(0); i++)
            assertSpecializedEquals(wrapToBigDecimal(1), array3.get(i));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<BigDecimal> array2 = masked.copy().fill(1).slice("1:1");
        NDArray<BigDecimal> array3 = new BasicBigDecimalNDArray(5).permuteDims(0);
        NDArray<BigDecimal> array4 = new BasicBigDecimalNDArray(3, 3).fill(2).reshape(9);
        NDArray<BigDecimal> array5 = masked.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = masked.shape(0);
        for (int i = start; i < end; i++)
            assertEquals(masked.get(i), array5.get(i));
        start = end;
        end += array2.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToBigDecimal(1), array5.get(i));
        start = end;
        end += array3.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToBigDecimal(0), array5.get(i));
        start = end;
        end += array4.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToBigDecimal(2), array5.get(i));
    }

    @Test
    void testDimensionMismatchMask() {
        mask = new BasicBigDecimalNDArray(4, 3, 5).fill(1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.mask(mask));
        assertEquals(String.format(Errors.MASK_DIMENSION_MISMATCH, "4 × 5 × 3", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testIdentityMask() {
        mask = new BasicBigDecimalNDArray(array.shape()).fill(1);
        assertTrue(array.mask(mask) instanceof RealNDArrayReshapeView);
    }

    @Test
    void testMask() {
        masked.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(value, wrapToBigDecimal(index * 2 + 1)));
        masked.fill(0);
        array.forEachWithLinearIndices(
                (value, index) -> assertSpecializedEquals(value, index % 2 == 0 ? wrapToBigDecimal(index) : wrapToBigDecimal(0)));
    }

    @Test
    void testMaskWithPredicate() {
        NDArray<BigDecimal> masked = array.mask(value -> wrapToDouble(value) > 20);
        masked.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(wrapToDouble(value) <= 20));
    }

    @Test
    void testMaskWithPredicateWithLinearIndices() {
        NDArray<BigDecimal> masked = array.maskWithLinearIndices((value, i) -> wrapToDouble(value) > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(wrapToDouble(value) > 20 && i < 10));
        masked.fill(0);
        array.forEachWithLinearIndices((value, i) -> assertTrue(wrapToDouble(value) <= 20 || i >= 10));
    }

    @Test
    void testMaskWithPredicateWithCartesianIndices() {
        NDArray<BigDecimal> masked = array.maskWithCartesianIndices((value, idx) -> wrapToDouble(value) > 20 && idx[0] == 0);
        masked.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        masked.fill(0);
        array.forEachWithCartesianIndices((value, idx) -> assertTrue(wrapToDouble(value) <= 20 || idx[0] != 0));
    }

    @Test
    void testMaskMask() {
        NDArray<BigDecimal> mask2 = new BasicBigDecimalNDArray(
                masked.map(value -> wrapToDouble(value) > 20 ? wrapToBigDecimal(1) : wrapToBigDecimal(0)));
        NDArray<BigDecimal> masked2 = masked.mask(mask2);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20));
    }

    @Test
    void testMaskMaskWithPredicate() {
        NDArray<BigDecimal> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<BigDecimal> masked2 = masked.mask(value -> wrapToDouble(value) < 25);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20 && wrapToDouble(value) < 25));
        masked2.fill(0);
        array.forEach(value -> assertTrue(wrapToDouble(value) <= 20 || wrapToDouble(value) >= 25));
    }

    @Test
    void testMaskMaskWithPredicateWithLinearIndices() {
        NDArray<BigDecimal> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<BigDecimal> masked2 = masked.maskWithLinearIndices((value, i) -> wrapToDouble(value) % 2 == 0 && i < 30);
        masked2.forEachWithLinearIndices(
                (value, i) -> assertTrue(wrapToDouble(value) % 2 == 0 && wrapToDouble(value) > 20));
        assertEquals(15, masked2.length());
    }

    @Test
    void testMaskMaskWithPredicateWithCartesianIndices() {
        NDArray<BigDecimal> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<BigDecimal> masked2 = masked.maskWithCartesianIndices((value, idx) -> idx[0] % 2 == 0);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        assertEquals(masked.length() / 2 + masked.length() % 2, masked2.length());
    }

    @Test
    void testMaskInverseMask() {
        NDArray<BigDecimal> mask2 = new BasicBigDecimalNDArray(
                masked.map(value -> wrapToDouble(value) > 20 ? wrapToBigDecimal(1) : wrapToBigDecimal(0)));
        NDArray<BigDecimal> masked2 = masked.inverseMask(mask2);
        masked2.forEachSequential(value -> assertTrue(wrapToDouble(value) <= 20));
    }

}
