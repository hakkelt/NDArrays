/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestMask.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:55 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBasicShortNDArrayMask extends TestBase {

    NDArray<Short> array;
    NDArray<Short> masked;
    NDArray<Short> mask;

    @BeforeEach
    void setup() {
        array = new BasicShortNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToShort(index));
        mask = new BasicShortNDArray(array.mapWithLinearIndices((value, index) -> wrapToShort(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    void testNotEqual() {
        assertNotEquals(masked, new int[0]); // NOSONAR
        assertNotEquals(masked, new BasicComplexFloatNDArray(masked.shape()));
        assertNotEquals(masked, new BasicShortNDArray(4, 15));
        assertNotEquals(masked, new BasicShortNDArray(4, 3, 5));
    }

    @Test
    void testEqualWithSameType() {
        NDArray<Short> masked2 = array.mask(mask);
        assertEquals(masked, masked2);
        NDArray<Short> masked3 = array.mask(value -> wrapToDouble(value) > 20);
        assertNotEquals(masked, masked3);
        NDArray<Short> array2 = array.similar();
        NDArray<Short> masked4 = array2.mask(mask);
        assertNotEquals(masked, masked4);
        NDArray<Short> array3 = array.copy();
        NDArray<Short> masked5 = array3.mask(mask);
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
        assertEquals(array.getNamePrefix() + " NDArrayView<Short>(30)", str);
    }

    @Test
    void testcontentToString() {
        String str = masked.contentToString();
        String numberFormat = "%6d\t";
        String line;
        if (masked.dtype() != Complex.class) {
            line = masked.streamLinearIndices()
                .mapToObj(i -> String.format(numberFormat, wrapToShort(i * 2 + 1)))
                .reduce("", (a, b) -> String.join("", a, b));
        } else {
            line = masked.streamLinearIndices()
                .mapToObj(i -> String.format(numberFormat, wrapToDouble(i * 2 + 1), 0.))
                .reduce("", (a, b) -> String.join("", a, b));
        }
        String expected = new StringBuilder().append(array.getNamePrefix() + " NDArrayView<Short>(30)")
                .append(System.lineSeparator()).append(line).toString();
        assertEquals(expected, str);
    }

    @Test
    void testConcatenate() {
        NDArray<Short> array2 = new BasicShortNDArray(5).fill(1);
        NDArray<Short> array3 = masked.concatenate(0, array2);
        for (int i = 0; i < masked.shape(0); i++)
            assertEquals(masked.get(i), array3.get(i));
        for (int i = masked.shape(0); i < masked.shape(0) + array2.shape(0); i++)
            assertSpecializedEquals(wrapToShort(1), array3.get(i));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Short> array2 = masked.copy().fill(1).slice("1:1");
        NDArray<Short> array3 = new BasicShortNDArray(5).permuteDims(0);
        NDArray<Short> array4 = new BasicShortNDArray(3, 3).fill(2).reshape(9);
        NDArray<Short> array5 = masked.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = masked.shape(0);
        for (int i = start; i < end; i++)
            assertEquals(masked.get(i), array5.get(i));
        start = end;
        end += array2.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToShort(1), array5.get(i));
        start = end;
        end += array3.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToShort(0), array5.get(i));
        start = end;
        end += array4.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToShort(2), array5.get(i));
    }

    @Test
    void testDimensionMismatchMask() {
        mask = new BasicShortNDArray(4, 3, 5).fill(1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.mask(mask));
        assertEquals(String.format(Errors.MASK_DIMENSION_MISMATCH, "4 × 5 × 3", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testIdentityMask() {
        mask = new BasicShortNDArray(array.shape()).fill(1);
        assertTrue(array.mask(mask) instanceof RealNDArrayReshapeView);
    }

    @Test
    void testMask() {
        masked.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(value, wrapToShort(index * 2 + 1)));
        masked.fill(0);
        array.forEachWithLinearIndices(
                (value, index) -> assertSpecializedEquals(value, index % 2 == 0 ? wrapToShort(index) : wrapToShort(0)));
    }

    @Test
    void testMaskWithPredicate() {
        NDArray<Short> masked = array.mask(value -> wrapToDouble(value) > 20);
        masked.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(wrapToDouble(value) <= 20));
    }

    @Test
    void testMaskWithPredicateWithLinearIndices() {
        NDArray<Short> masked = array.maskWithLinearIndices((value, i) -> wrapToDouble(value) > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(wrapToDouble(value) > 20 && i < 10));
        masked.fill(0);
        array.forEachWithLinearIndices((value, i) -> assertTrue(wrapToDouble(value) <= 20 || i >= 10));
    }

    @Test
    void testMaskWithPredicateWithCartesianIndices() {
        NDArray<Short> masked = array.maskWithCartesianIndices((value, idx) -> wrapToDouble(value) > 20 && idx[0] == 0);
        masked.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        masked.fill(0);
        array.forEachWithCartesianIndices((value, idx) -> assertTrue(wrapToDouble(value) <= 20 || idx[0] != 0));
    }

    @Test
    void testMaskMask() {
        NDArray<Short> mask2 = new BasicShortNDArray(
                masked.map(value -> wrapToDouble(value) > 20 ? wrapToShort(1) : wrapToShort(0)));
        NDArray<Short> masked2 = masked.mask(mask2);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20));
    }

    @Test
    void testMaskMaskWithPredicate() {
        NDArray<Short> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<Short> masked2 = masked.mask(value -> wrapToDouble(value) < 25);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20 && wrapToDouble(value) < 25));
        masked2.fill(0);
        array.forEach(value -> assertTrue(wrapToDouble(value) <= 20 || wrapToDouble(value) >= 25));
    }

    @Test
    void testMaskMaskWithPredicateWithLinearIndices() {
        NDArray<Short> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<Short> masked2 = masked.maskWithLinearIndices((value, i) -> wrapToDouble(value) % 2 == 0 && i < 30);
        masked2.forEachWithLinearIndices(
                (value, i) -> assertTrue(wrapToDouble(value) % 2 == 0 && wrapToDouble(value) > 20));
        assertEquals(15, masked2.length());
    }

    @Test
    void testMaskMaskWithPredicateWithCartesianIndices() {
        NDArray<Short> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<Short> masked2 = masked.maskWithCartesianIndices((value, idx) -> idx[0] % 2 == 0);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        assertEquals(masked.length() / 2 + masked.length() % 2, masked2.length());
    }

    @Test
    void testMaskInverseMask() {
        NDArray<Short> mask2 = new BasicShortNDArray(
                masked.map(value -> wrapToDouble(value) > 20 ? wrapToShort(1) : wrapToShort(0)));
        NDArray<Short> masked2 = masked.inverseMask(mask2);
        masked2.forEachSequential(value -> assertTrue(wrapToDouble(value) <= 20));
    }

}
