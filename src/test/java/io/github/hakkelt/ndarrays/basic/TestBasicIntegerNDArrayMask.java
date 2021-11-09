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

class TestBasicIntegerNDArrayMask extends TestBase {

    NDArray<Integer> array;
    NDArray<Integer> masked;
    NDArray<Integer> mask;

    @BeforeEach
    void setup() {
        array = new BasicIntegerNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToInteger(index));
        mask = new BasicIntegerNDArray(array.mapWithLinearIndices((value, index) -> wrapToInteger(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    void testNotEqual() {
        assertNotEquals(masked, new int[0]); // NOSONAR
        assertNotEquals(masked, new BasicComplexFloatNDArray(masked.shape()));
        assertNotEquals(masked, new BasicIntegerNDArray(4, 15));
        assertNotEquals(masked, new BasicIntegerNDArray(4, 3, 5));
    }

    @Test
    void testEqualWithSameType() {
        NDArray<Integer> masked2 = array.mask(mask);
        assertEquals(masked, masked2);
        NDArray<Integer> masked3 = array.mask(value -> wrapToDouble(value) > 20);
        assertNotEquals(masked, masked3);
        NDArray<Integer> array2 = array.similar();
        NDArray<Integer> masked4 = array2.mask(mask);
        assertNotEquals(masked, masked4);
        NDArray<Integer> array3 = array.copy();
        NDArray<Integer> masked5 = array3.mask(mask);
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
        assertEquals(array.getNamePrefix() + " NDArrayView<Integer>(30)", str);
    }

    @Test
    void testcontentToString() {
        String str = masked.contentToString();
        String numberFormat = "%6d\t";
        String line;
        if (masked.dtype() != Complex.class) {
            line = masked.streamLinearIndices()
                .mapToObj(i -> String.format(numberFormat, wrapToInteger(i * 2 + 1)))
                .reduce("", (a, b) -> String.join("", a, b));
        } else {
            line = masked.streamLinearIndices()
                .mapToObj(i -> String.format(numberFormat, wrapToDouble(i * 2 + 1), 0.))
                .reduce("", (a, b) -> String.join("", a, b));
        }
        String expected = new StringBuilder().append(array.getNamePrefix() + " NDArrayView<Integer>(30)")
                .append(System.lineSeparator()).append(line).toString();
        assertEquals(expected, str);
    }

    @Test
    void testConcatenate() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(5).fill(1);
        NDArray<Integer> array3 = masked.concatenate(0, array2);
        for (int i = 0; i < masked.shape(0); i++)
            assertEquals(masked.get(i), array3.get(i));
        for (int i = masked.shape(0); i < masked.shape(0) + array2.shape(0); i++)
            assertSpecializedEquals(wrapToInteger(1), array3.get(i));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Integer> array2 = masked.copy().fill(1).slice("1:1");
        NDArray<Integer> array3 = new BasicIntegerNDArray(5).permuteDims(0);
        NDArray<Integer> array4 = new BasicIntegerNDArray(3, 3).fill(2).reshape(9);
        NDArray<Integer> array5 = masked.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = masked.shape(0);
        for (int i = start; i < end; i++)
            assertEquals(masked.get(i), array5.get(i));
        start = end;
        end += array2.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToInteger(1), array5.get(i));
        start = end;
        end += array3.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToInteger(0), array5.get(i));
        start = end;
        end += array4.shape(0);
        for (int i = start; i < end; i++)
            assertSpecializedEquals(wrapToInteger(2), array5.get(i));
    }

    @Test
    void testDimensionMismatchMask() {
        mask = new BasicIntegerNDArray(4, 3, 5).fill(1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.mask(mask));
        assertEquals(String.format(Errors.MASK_DIMENSION_MISMATCH, "4 × 5 × 3", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testIdentityMask() {
        mask = new BasicIntegerNDArray(array.shape()).fill(1);
        assertTrue(array.mask(mask) instanceof RealNDArrayReshapeView);
    }

    @Test
    void testMask() {
        masked.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(value, wrapToInteger(index * 2 + 1)));
        masked.fill(0);
        array.forEachWithLinearIndices(
                (value, index) -> assertSpecializedEquals(value, index % 2 == 0 ? wrapToInteger(index) : wrapToInteger(0)));
    }

    @Test
    void testMaskWithPredicate() {
        NDArray<Integer> masked = array.mask(value -> wrapToDouble(value) > 20);
        masked.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(wrapToDouble(value) <= 20));
    }

    @Test
    void testMaskWithPredicateWithLinearIndices() {
        NDArray<Integer> masked = array.maskWithLinearIndices((value, i) -> wrapToDouble(value) > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(wrapToDouble(value) > 20 && i < 10));
        masked.fill(0);
        array.forEachWithLinearIndices((value, i) -> assertTrue(wrapToDouble(value) <= 20 || i >= 10));
    }

    @Test
    void testMaskWithPredicateWithCartesianIndices() {
        NDArray<Integer> masked = array.maskWithCartesianIndices((value, idx) -> wrapToDouble(value) > 20 && idx[0] == 0);
        masked.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        masked.fill(0);
        array.forEachWithCartesianIndices((value, idx) -> assertTrue(wrapToDouble(value) <= 20 || idx[0] != 0));
    }

    @Test
    void testMaskMask() {
        NDArray<Integer> mask2 = new BasicIntegerNDArray(
                masked.map(value -> wrapToDouble(value) > 20 ? wrapToInteger(1) : wrapToInteger(0)));
        NDArray<Integer> masked2 = masked.mask(mask2);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20));
    }

    @Test
    void testMaskMaskWithPredicate() {
        NDArray<Integer> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<Integer> masked2 = masked.mask(value -> wrapToDouble(value) < 25);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20 && wrapToDouble(value) < 25));
        masked2.fill(0);
        array.forEach(value -> assertTrue(wrapToDouble(value) <= 20 || wrapToDouble(value) >= 25));
    }

    @Test
    void testMaskMaskWithPredicateWithLinearIndices() {
        NDArray<Integer> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<Integer> masked2 = masked.maskWithLinearIndices((value, i) -> wrapToDouble(value) % 2 == 0 && i < 30);
        masked2.forEachWithLinearIndices(
                (value, i) -> assertTrue(wrapToDouble(value) % 2 == 0 && wrapToDouble(value) > 20));
        assertEquals(15, masked2.length());
    }

    @Test
    void testMaskMaskWithPredicateWithCartesianIndices() {
        NDArray<Integer> masked = array.mask(value -> wrapToDouble(value) > 20);
        NDArray<Integer> masked2 = masked.maskWithCartesianIndices((value, idx) -> idx[0] % 2 == 0);
        masked2.forEach(value -> assertTrue(wrapToDouble(value) > 20));
        assertEquals(masked.length() / 2 + masked.length() % 2, masked2.length());
    }

    @Test
    void testMaskInverseMask() {
        NDArray<Integer> mask2 = new BasicIntegerNDArray(
                masked.map(value -> wrapToDouble(value) > 20 ? wrapToInteger(1) : wrapToInteger(0)));
        NDArray<Integer> masked2 = masked.inverseMask(mask2);
        masked2.forEachSequential(value -> assertTrue(wrapToDouble(value) <= 20));
    }

}
