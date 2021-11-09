/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestMisc.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:55 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.Errors;

import java.util.stream.IntStream;

import org.junit.jupiter.api.*;

class TestBasicIntegerNDArrayMisc extends TestBase {

    NDArray<Integer> arrayOriginal;
    NDArray<Integer> array;
    NDArray<Integer> mask;
    NDArray<Integer> masked;
    NDArray<Integer> pArray;
    NDArray<Integer> reshaped;
    NDArray<Integer> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicIntegerNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToInteger(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicIntegerNDArray(array.mapWithLinearIndices((value, index) -> wrapToInteger(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    void testShapeWithSlice() {
        int[] expectedshape = { 3, 3 };
        assertArrayEquals(expectedshape, slice.shape());
        assertEquals(IntStream.of(expectedshape).reduce(1, (a, b) -> a * b), slice.length());
        assertEquals(expectedshape[0], slice.shape(0));
        assertEquals(expectedshape.length, slice.ndim());
        int ndim = slice.ndim();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.shape(ndim));
        assertEquals(String.format(Errors.PARAMETER_MUST_BE_BETWEEN, "axis", 0, slice.ndim() - 1, ndim),
                exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class, () -> slice.shape(-1));
        assertEquals(String.format(Errors.PARAMETER_MUST_BE_BETWEEN, "axis", 0, slice.ndim() - 1, -1),
                exception.getMessage());
    }

    @Test
    void testShapeWithReshaped() {
        int[] expectedshape = { 20, 3 };
        assertArrayEquals(expectedshape, reshaped.shape());
        assertEquals(IntStream.of(expectedshape).reduce(1, (a, b) -> a * b), reshaped.length());
        assertEquals(expectedshape[0], reshaped.shape(0));
        assertEquals(expectedshape.length, reshaped.ndim());
        int ndim = reshaped.ndim();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.shape(ndim));
        assertEquals(String.format(Errors.PARAMETER_MUST_BE_BETWEEN, "axis", 0, reshaped.ndim() - 1, ndim),
                exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class, () -> reshaped.shape(-1));
        assertEquals(String.format(Errors.PARAMETER_MUST_BE_BETWEEN, "axis", 0, reshaped.ndim() - 1, -1),
                exception.getMessage());
    }

    @Test
    void testShapeWithPArray() {
        int[] expectedshape = { 4, 3, 5 };
        assertArrayEquals(expectedshape, pArray.shape());
        assertEquals(IntStream.of(expectedshape).reduce(1, (a, b) -> a * b), pArray.length());
        assertEquals(expectedshape[0], pArray.shape(0));
        assertEquals(expectedshape.length, pArray.ndim());
        int ndim = pArray.ndim();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.shape(ndim));
        assertEquals(String.format(Errors.PARAMETER_MUST_BE_BETWEEN, "axis", 0, pArray.ndim() - 1, ndim),
                exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class, () -> pArray.shape(-1));
        assertEquals(String.format(Errors.PARAMETER_MUST_BE_BETWEEN, "axis", 0, pArray.ndim() - 1, -1),
                exception.getMessage());
    }

    @Test
    void testShapeWithMasked() {
        int[] expectedshape = { 30 };
        assertArrayEquals(expectedshape, masked.shape());
        assertEquals(IntStream.of(expectedshape).reduce(1, (a, b) -> a * b), masked.length());
        assertEquals(expectedshape[0], masked.shape(0));
        assertEquals(expectedshape.length, masked.ndim());
        int ndim = masked.ndim();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.shape(ndim));
        assertEquals(String.format(Errors.PARAMETER_MUST_BE_BETWEEN, "axis", 0, masked.ndim() - 1, ndim),
                exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class, () -> masked.shape(-1));
        assertEquals(String.format(Errors.PARAMETER_MUST_BE_BETWEEN, "axis", 0, masked.ndim() - 1, -1),
                exception.getMessage());
    }

    @Test
    void testDTypeWithArray() {
        assertEquals(Integer.class, array.dtype());
    }

    @Test
    void testDTypeWithSlice() {
        assertEquals(Integer.class, slice.dtype());
    }

    @Test
    void testDTypeWithReshaped() {
        assertEquals(Integer.class, reshaped.dtype());
    }

    @Test
    void testDTypeWithPArray() {
        assertEquals(Integer.class, pArray.dtype());
    }

    @Test
    void testDTypeWithMasked() {
        assertEquals(Integer.class, masked.dtype());
    }

    @Test
    void testWrongToArrayWithArray() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> array.toArray(new Integer[0][][][]));
        assertEquals(String.format(Errors.TOARRAY_DEPTH_MISMATCH, 4, 3), exception.getMessage());
    }

    @Test
    void testWrongToArrayWithSlice() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> slice.toArray(new Integer[0][][]));
        assertEquals(String.format(Errors.TOARRAY_DEPTH_MISMATCH, 3, 2), exception.getMessage());
    }

    @Test
    void testWrongToArrayWithReshaped() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> reshaped.toArray(new Integer[0][][]));
        assertEquals(String.format(Errors.TOARRAY_DEPTH_MISMATCH, 3, 2), exception.getMessage());
    }

    @Test
    void testWrongToArrayWithPArray() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> pArray.toArray(new Integer[0][][][]));
        assertEquals(String.format(Errors.TOARRAY_DEPTH_MISMATCH, 4, 3), exception.getMessage());
    }

    @Test
    void testWrongToArrayWithMasked() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> masked.toArray(new Integer[0][][]));
        assertEquals(String.format(Errors.TOARRAY_DEPTH_MISMATCH, 3, 1), exception.getMessage());
    }

    @Test
    void testNotEqualsWithArray() {
        assertNotEquals(array, new int[0]); // NOSONAR
        assertNotEquals(array, new BasicComplexFloatNDArray(array.shape()));
        assertNotEquals(array, new BasicIntegerNDArray(IntStream.of(array.shape()).reduce(1, (acc, value) -> acc * value)));
        assertNotEquals(array, new BasicIntegerNDArray(array.shape()));
    }

    @Test
    void testNotEqualsWithSlice() {
        assertNotEquals(slice, new int[0]); // NOSONAR
        assertNotEquals(slice, new BasicComplexFloatNDArray(slice.shape()));
        assertNotEquals(slice, new BasicIntegerNDArray(IntStream.of(slice.shape()).reduce(1, (acc, value) -> acc * value)));
        assertNotEquals(slice, new BasicIntegerNDArray(slice.shape()));
    }

    @Test
    void testNotEqualsWithReshaped() {
        assertNotEquals(reshaped, new int[0]); // NOSONAR
        assertNotEquals(reshaped, new BasicComplexFloatNDArray(reshaped.shape()));
        assertNotEquals(reshaped, new BasicIntegerNDArray(IntStream.of(reshaped.shape()).reduce(1, (acc, value) -> acc * value)));
        assertNotEquals(reshaped, new BasicIntegerNDArray(reshaped.shape()));
    }

    @Test
    void testNotEqualsWithPArray() {
        assertNotEquals(pArray, new int[0]); // NOSONAR
        assertNotEquals(pArray, new BasicComplexFloatNDArray(pArray.shape()));
        assertNotEquals(pArray, new BasicIntegerNDArray(IntStream.of(pArray.shape()).reduce(1, (acc, value) -> acc * value)));
        assertNotEquals(pArray, new BasicIntegerNDArray(pArray.shape()));
    }

    @Test
    void testNotEqualsWithMasked() {
        assertNotEquals(masked, new int[0]); // NOSONAR
        assertNotEquals(masked, new BasicComplexFloatNDArray(masked.shape()));
        assertNotEquals(masked, new BasicIntegerNDArray(IntStream.of(masked.shape()).reduce(1, (acc, value) -> acc * value)));
        assertNotEquals(masked, new BasicIntegerNDArray(masked.shape()));
    }

    @Test
    void testArrayEqualsWithSameType() {
        NDArray<Integer> array2 = array.copy();
        assertEquals(array, array2);
        array2.set(0, 10);
        assertNotEquals(array, array2);
    }

    @Test
    void testSliceEqualsWithSameType() {
        NDArray<Integer> slice2 = array.slice(1, "1:4", "0:");
        assertEquals(slice, slice2);
        NDArray<Integer> slice3 = array.copy().slice(1, "1:4", "0:");
        assertEquals(slice, slice3);
    }

    @Test
    void testReshapedEqualsWithSameType() {
        NDArray<Integer> reshaped2 = array.reshape(20, 3);
        assertEquals(reshaped, reshaped2);
        NDArray<Integer> reshaped3 = array.copy().reshape(20, 3);
        assertEquals(reshaped, reshaped3);
    }

    @Test
    void testPArrayEqualsWithSameType() {
        NDArray<Integer> pArray2 = array.permuteDims(0, 2, 1);
        assertEquals(pArray, pArray2);
        NDArray<Integer> pArray3 = array.copy().permuteDims(0, 2, 1);
        assertEquals(pArray, pArray3);
    }

    @Test
    void testMaskedEqualsWithSameType() {
        NDArray<Integer> masked2 = array.mask(mask);
        assertEquals(masked, masked2);
        NDArray<Integer> masked3 = array.copy().mask(mask);
        assertEquals(masked, masked3);
        NDArray<Integer> masked4 = array.mask(value -> value.intValue() % 2 == 1);
        assertEquals(masked, masked4);
        NDArray<Integer> masked5 = array.copy().mask(value -> value.intValue() % 2 == 1);
        assertEquals(masked, masked5);
        NDArray<Integer> masked6 = array.maskWithLinearIndices((value, i) -> i.intValue() % 2 == 1);
        assertEquals(masked, masked6);
        NDArray<Integer> masked7 = array.copy().maskWithLinearIndices((value, i) -> i.intValue() % 2 == 1);
        assertEquals(masked, masked7);
        NDArray<Integer> masked8 = array.maskWithCartesianIndices((value, idx) -> value.intValue() % 2 == 1);
        assertEquals(masked, masked8);
        NDArray<Integer> masked9 = array.copy().maskWithCartesianIndices((value, idx) -> value.intValue() % 2 == 1);
        assertEquals(masked, masked9);
    }

    @Test
    void testArrayEqualsWithDifferentType() {
        NDArray<?> array2 = new BasicLongNDArray(array);
        assertEquals(array, array2);
        array2.set(0, 10);
        assertNotEquals(array, array2);
    }

    @Test
    void testSliceEqualsWithDifferentType() {
        NDArray<?> slice2 = new BasicLongNDArray(array).slice(1, "1:4", "0:");
        assertEquals(slice, slice2);
    }

    @Test
    void testReshapedEqualsWithDifferentType() {
        NDArray<?> reshaped2 = new BasicLongNDArray(array).reshape(20, 3);
        assertEquals(reshaped, reshaped2);
    }

    @Test
    void testPArrayEqualsWithDifferentType() {
        NDArray<?> pArray2 = new BasicLongNDArray(array).permuteDims(0, 2, 1);
        assertEquals(pArray, pArray2);
    }

    @Test
    void testMaskedEqualsWithDifferentType() {
        NDArray<? extends Number> array2 = new BasicLongNDArray(array);
        NDArray<? extends Number> masked2 = array2.mask(mask);
        assertEquals(masked, masked2);
        NDArray<? extends Number> masked4 = array2.mask(value -> value.intValue() % 2 == 1);
        assertEquals(masked, masked4);
        NDArray<?> masked6 = array2.maskWithLinearIndices((value, i) -> i.intValue() % 2 == 1);
        assertEquals(masked, masked6);
        NDArray<?> masked8 = array2.maskWithCartesianIndices((value, idx) -> value.intValue() % 2 == 1);
        assertEquals(masked, masked8);
    }

    @Test
    void testHashCodeWithArray() {
        assertThrows(UnsupportedOperationException.class, () -> array.hashCode());
    }

    @Test
    void testHashCodeWithSlice() {
        assertThrows(UnsupportedOperationException.class, () -> slice.hashCode());
    }

    @Test
    void testHashCodeWithReshaped() {
        assertThrows(UnsupportedOperationException.class, () -> reshaped.hashCode());
    }

    @Test
    void testHashCodeWithPArray() {
        assertThrows(UnsupportedOperationException.class, () -> pArray.hashCode());
    }

    @Test
    void testHashCodeWithMasked() {
        assertThrows(UnsupportedOperationException.class, () -> masked.hashCode());
    }

    @Test
    void testNegativeNormWithArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.norm(-1));
        assertEquals(Errors.NEGATIVE_NORM, exception.getMessage());
    }

    @Test
    void testNegativeNormWithSlice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.norm(-1));
        assertEquals(Errors.NEGATIVE_NORM, exception.getMessage());
    }

    @Test
    void testNegativeNormWithReshaped() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.norm(-1));
        assertEquals(Errors.NEGATIVE_NORM, exception.getMessage());
    }

    @Test
    void testNegativeNormWithPArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.norm(-1));
        assertEquals(Errors.NEGATIVE_NORM, exception.getMessage());
    }

    @Test
    void testNegativeNormWithMasked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.norm(-1));
        assertEquals(Errors.NEGATIVE_NORM, exception.getMessage());
    }

    @Test
    void test0Norm() {
        array.slice(":", 0, ":").fill(0);
        double norm = array.stream().filter(value -> Math.abs(value) != 0.).count();
        assertEquals(norm, array.norm(0));
    }

    @Test
    void test1NormWithArray() {
        double norm = array.stream().mapToDouble(value -> Math.abs(value)).reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, array.norm(1));
    }

    @Test
    void test1NormWithSlice() {
        double norm = slice.stream().mapToDouble(value -> Math.abs(value)).reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, slice.norm(1));
    }

    @Test
    void test1NormWithReshaped() {
        double norm = reshaped.stream().mapToDouble(value -> Math.abs(value)).reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, reshaped.norm(1));
    }

    @Test
    void test1NormWithPArray() {
        double norm = pArray.stream().mapToDouble(value -> Math.abs(value)).reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, pArray.norm(1));
    }

    @Test
    void test1NormWithMasked() {
        double norm = masked.stream().mapToDouble(value -> Math.abs(value)).reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, masked.norm(1));
    }

    @Test
    void test2NormWithArray() {
        double norm = Math
                .sqrt(array.stream().map(value -> Math.pow(Math.abs(value), 2)).reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, array.norm());
        assertEquals(norm, array.norm(2));
    }

    @Test
    void test2NormWithSlice() {
        double norm = Math
                .sqrt(slice.stream().map(value -> Math.pow(Math.abs(value), 2)).reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, slice.norm());
        assertEquals(norm, slice.norm(2));
    }

    @Test
    void test2NormWithReshaped() {
        double norm = Math
                .sqrt(reshaped.stream().map(value -> Math.pow(Math.abs(value), 2)).reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, reshaped.norm());
        assertEquals(norm, reshaped.norm(2));
    }

    @Test
    void test2NormWithPArray() {
        double norm = Math
                .sqrt(pArray.stream().map(value -> Math.pow(Math.abs(value), 2)).reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, pArray.norm());
        assertEquals(norm, pArray.norm(2));
    }

    @Test
    void test2NormWithMasked() {
        double norm = Math
                .sqrt(masked.stream().map(value -> Math.pow(Math.abs(value), 2)).reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, masked.norm());
        assertEquals(norm, masked.norm(2));
    }

    @Test
    void testPQuasinormWithArray() {
        double norm = Math.pow(
                array.stream().map(value -> Math.pow(Math.abs(value), 0.5)).reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, array.norm(0.5));
    }

    @Test
    void testPQuasinormWithSlice() {
        double norm = Math.pow(
                slice.stream().map(value -> Math.pow(Math.abs(value), 0.5)).reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, slice.norm(0.5));
    }

    @Test
    void testPQuasinormWithReshaped() {
        double norm = Math.pow(
                reshaped.stream().map(value -> Math.pow(Math.abs(value), 0.5)).reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, reshaped.norm(0.5));
    }

    @Test
    void testPQuasinormWithPArray() {
        double norm = Math.pow(
                pArray.stream().map(value -> Math.pow(Math.abs(value), 0.5)).reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, pArray.norm(0.5));
    }

    @Test
    void testPQuasinormWithMasked() {
        double norm = Math.pow(
                masked.stream().map(value -> Math.pow(Math.abs(value), 0.5)).reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, masked.norm(0.5));
    }

    @Test
    void testPNormWithArray() {
        double norm = Math.pow(
                array.stream().map(value -> Math.pow(Math.abs(value), 3.5)).reduce(0., (acc, item) -> acc + item),
                1 / 3.5);
        assertEquals(norm, array.norm(3.5));
    }

    @Test
    void testPNormWithSlice() {
        double norm = Math.pow(
                slice.stream().map(value -> Math.pow(Math.abs(value), 3.5)).reduce(0., (acc, item) -> acc + item),
                1 / 3.5);
        assertEquals(norm, slice.norm(3.5));
    }

    @Test
    void testPNormWithReshaped() {
        double norm = Math.pow(
                reshaped.stream().map(value -> Math.pow(Math.abs(value), 3.5)).reduce(0., (acc, item) -> acc + item),
                1 / 3.5);
        assertEquals(norm, reshaped.norm(3.5));
    }

    @Test
    void testPNormWithPArray() {
        double norm = Math.pow(
                pArray.stream().map(value -> Math.pow(Math.abs(value), 3.5)).reduce(0., (acc, item) -> acc + item),
                1 / 3.5);
        assertEquals(norm, pArray.norm(3.5));
    }

    @Test
    void testPNormWithMasked() {
        double norm = Math.pow(
                masked.stream().map(value -> Math.pow(Math.abs(value), 3.5)).reduce(0., (acc, item) -> acc + item),
                1 / 3.5);
        assertEquals(norm, masked.norm(3.5));
    }

    @Test
    void testInfNormWithArray() {
        double norm = (float) array.stream().mapToDouble(value -> Math.abs(value)).max().getAsDouble();
        assertEquals(norm, array.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testInfNormWithSlice() {
        double norm = (float) slice.stream().mapToDouble(value -> Math.abs(value)).max().getAsDouble();
        assertEquals(norm, slice.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testInfNormWithReshaped() {
        double norm = (float) reshaped.stream().mapToDouble(value -> Math.abs(value)).max().getAsDouble();
        assertEquals(norm, reshaped.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testInfNormWithPArray() {
        double norm = (float) pArray.stream().mapToDouble(value -> Math.abs(value)).max().getAsDouble();
        assertEquals(norm, pArray.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testInfNormWithMasked() {
        double norm = (float) masked.stream().mapToDouble(value -> Math.abs(value)).max().getAsDouble();
        assertEquals(norm, masked.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testCopyWithArray() {
        NDArray<Integer> array2 = array.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(array.get(5), array2.get(5));
    }

    @Test
    void testCopyWithSlice() {
        NDArray<Integer> array2 = slice.copy();
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(slice.get(5), array2.get(5));
    }

    @Test
    void testCopyWithReshaped() {
        NDArray<Integer> array2 = reshaped.copy();
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(reshaped.get(5), array2.get(5));
    }

    @Test
    void testCopyWithPArray() {
        NDArray<Integer> array2 = pArray.copy();
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(pArray.get(5), array2.get(5));
    }

    @Test
    void testCopyWithMasked() {
        NDArray<Integer> array2 = masked.copy();
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(masked.get(5), array2.get(5));
    }

    @Test
    void testFillDoubleWithArray() {
        array.fill(3);
        for (Integer elem : array)
            assertSpecializedEquals(wrapToInteger(3), elem);
    }

    @Test
    void testFillDoubleWithSlice() {
        slice.fill(3);
        for (Integer elem : slice)
            assertSpecializedEquals(wrapToInteger(3), elem);
    }

    @Test
    void testFillDoubleWithReshaped() {
        reshaped.fill(3);
        for (Integer elem : reshaped)
            assertSpecializedEquals(wrapToInteger(3), elem);
    }

    @Test
    void testFillDoubleWithPArray() {
        pArray.fill(3);
        for (Integer elem : pArray)
            assertSpecializedEquals(wrapToInteger(3), elem);
    }

    @Test
    void testFillDoubleWithMasked() {
        masked.fill(3);
        for (Integer elem : masked)
            assertSpecializedEquals(wrapToInteger(3), elem);
    }

    @Test
    void testFillTWithArray() {
        array.fill(wrapToInteger(3));
        for (Integer elem : array)
            assertSpecializedEquals(wrapToInteger(3), elem);
    }

    @Test
    void testFillTWithSlice() {
        slice.fill(wrapToInteger(3));
        for (Integer elem : slice)
            assertSpecializedEquals(wrapToInteger(3), elem);
    }

    @Test
    void testFillTWithReshaped() {
        reshaped.fill(wrapToInteger(3));
        for (Integer elem : reshaped)
            assertSpecializedEquals(wrapToInteger(3), elem);
    }

    @Test
    void testFillTWithPArray() {
        pArray.fill(wrapToInteger(3));
        for (Integer elem : pArray)
            assertSpecializedEquals(wrapToInteger(3), elem);
    }

    @Test
    void testFillTWithMasked() {
        masked.fill(wrapToInteger(3));
        for (Integer elem : masked)
            assertSpecializedEquals(wrapToInteger(3), elem);
    }

    @Test
    void testContaintsWithArray() {
        assertTrue(array.contains(wrapToInteger(5)));
    }

    @Test
    void testContaintsWithSlice() {
        assertTrue(slice.contains(wrapToInteger(5)));
    }

    @Test
    void testContaintsWithReshaped() {
        assertTrue(reshaped.contains(wrapToInteger(5)));
    }

    @Test
    void testContaintsWithPArray() {
        assertTrue(pArray.contains(wrapToInteger(5)));
    }

    @Test
    void testContaintsWithMasked() {
        assertTrue(masked.contains(wrapToInteger(5)));
    }

}
