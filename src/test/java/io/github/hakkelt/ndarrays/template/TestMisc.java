package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.*;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;
import io.github.hakkelt.ndarrays.internal.Errors;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1Misc")
@Patterns({ "BasicByteNDArray", "/Byte/", "Math.abs(value)", "value.intValue()", "? extends Number", "BasicLongNDArray" })
@Replacements({ "BasicShortNDArray", "Short", "Math.abs(value)", "value.intValue()", "? extends Number", "BasicLongNDArray" })
@Replacements({ "BasicIntegerNDArray", "Integer", "Math.abs(value)", "value.intValue()", "? extends Number", "BasicLongNDArray" })
@Replacements({ "BasicLongNDArray", "Long", "Math.abs(value)", "value.intValue()", "? extends Number", "BasicShortNDArray" })
@Replacements({ "BasicFloatNDArray", "Float", "Math.abs(value)", "value.intValue()", "? extends Number", "BasicLongNDArray" })
@Replacements({ "BasicDoubleNDArray", "Double", "Math.abs(value)", "value.intValue()", "? extends Number", "BasicLongNDArray" })
@Replacements(value = { "BasicBigIntegerNDArray", "BigInteger", "Math.abs(value.doubleValue())", "value.intValue()", "? extends Number", "BasicLongNDArray" },
    extraImports = "java.math.BigInteger")
@Replacements(value = { "BasicBigDecimalNDArray", "BigDecimal", "Math.abs(value.doubleValue())", "value.intValue()", "? extends Number", "BasicLongNDArray" },
    extraImports = "java.math.BigDecimal")
@Replacements(value = { "BasicComplexFloatNDArray", "Complex", "value.abs()", "value.getReal()", "Complex", "BasicComplexDoubleNDArray" },
    extraImports = "org.apache.commons.math3.complex.Complex")
@Replacements(value = { "BasicComplexDoubleNDArray", "Complex", "value.abs()", "value.getReal()", "Complex", "BasicComplexFloatNDArray" },
    extraImports = "org.apache.commons.math3.complex.Complex")
class TestMisc extends TestBase {
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
        array.fillUsingLinearIndices(TestBase::wrapToByte);
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicByteNDArray(array.mapWithLinearIndices((value, index) -> wrapToByte(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    @Patterns({"slice", "3, 3"})
    @Replacements({"reshaped", "20, 3"})
    @Replacements({"pArray", "4, 3, 5"})
    @Replacements({"masked", "30"})
    void testShape() {
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
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testDType() {
        assertEquals(Byte.class, array.dtype());
    }

    @Test
    @Patterns({"[0][][]", "4, 3", "array"})
    @Replacements({"[0][]", "3, 2", "slice"})
    @Replacements({"[0][]", "3, 2", "reshaped"})
    @Replacements({"[0][][]", "4, 3", "pArray"})
    @Replacements({"[0][]", "3, 1", "masked"})
    void testWrongToArray() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> array.toArray(new Byte[0][][][]));
        assertEquals(String.format(Errors.TOARRAY_DEPTH_MISMATCH, 4, 3), exception.getMessage());
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testNotEquals() {
        assertNotEquals(array, new int[0]); // NOSONAR
        assertNotEquals(array, new BasicComplexFloatNDArray(array.shape()));
        assertNotEquals(array, new BasicByteNDArray(IntStream.of(array.shape()).reduce(1, (acc, value) -> acc * value)));
        assertNotEquals(array, new BasicByteNDArray(array.shape()));
    }

    @Test
    void testArrayEqualsWithSameType() {
        NDArray<Byte> array2 = array.copy();
        assertEquals(array, array2);
        array2.set(0, 10);
        assertNotEquals(array, array2);
    }

    @Test
    void testSliceEqualsWithSameType() {
        NDArray<Byte> slice2 = array.slice(1, "1:4", "0:");
        assertEquals(slice, slice2);
        NDArray<Byte> slice3 = array.copy().slice(1, "1:4", "0:");
        assertEquals(slice, slice3);
    }

    @Test
    void testReshapedEqualsWithSameType() {
        NDArray<Byte> reshaped2 = array.reshape(20, 3);
        assertEquals(reshaped, reshaped2);
        NDArray<Byte> reshaped3 = array.copy().reshape(20, 3);
        assertEquals(reshaped, reshaped3);
    }

    @Test
    void testPArrayEqualsWithSameType() {
        NDArray<Byte> pArray2 = array.permuteDims(0, 2, 1);
        assertEquals(pArray, pArray2);
        NDArray<Byte> pArray3 = array.copy().permuteDims(0, 2, 1);
        assertEquals(pArray, pArray3);
    }

    @Test
    void testMaskedEqualsWithSameType() {
        NDArray<Byte> masked2 = array.mask(mask);
        assertEquals(masked, masked2);
        NDArray<Byte> masked3 = array.copy().mask(mask);
        assertEquals(masked, masked3);
        NDArray<Byte> masked4 = array.mask(value -> value.intValue() % 2 == 1);
        assertEquals(masked, masked4);
        NDArray<Byte> masked5 = array.copy().mask(value -> value.intValue() % 2 == 1);
        assertEquals(masked, masked5);
        NDArray<Byte> masked6 = array.maskWithLinearIndices((value, i) -> i.intValue() % 2 == 1);
        assertEquals(masked, masked6);
        NDArray<Byte> masked7 = array.copy().maskWithLinearIndices((value, i) -> i.intValue() % 2 == 1);
        assertEquals(masked, masked7);
        NDArray<Byte> masked8 = array.maskWithCartesianIndices((value, idx) -> value.intValue() % 2 == 1);
        assertEquals(masked, masked8);
        NDArray<Byte> masked9 = array.copy().maskWithCartesianIndices((value, idx) -> value.intValue() % 2 == 1);
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
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> array.hashCode());
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testNegativeNorm() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.norm(-1));
        assertEquals(Errors.NEGATIVE_NORM, exception.getMessage());
    }

    @Test
    void test0Norm() {
        array.slice(":", 0, ":").fill(0);
        double norm = array.stream().filter(value -> Math.abs(value) != 0.).count();
        assertEquals(norm, array.norm(0));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void test1Norm() {
        double norm = array.stream().mapToDouble(value -> Math.abs(value)).reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, array.norm(1));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void test2Norm() {
        double norm = Math
                .sqrt(array.stream().map(value -> Math.pow(Math.abs(value), 2)).reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, array.norm());
        assertEquals(norm, array.norm(2));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testPQuasinorm() {
        double norm = Math.pow(
                array.stream().map(value -> Math.pow(Math.abs(value), 0.5)).reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, array.norm(0.5));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testPNorm() {
        double norm = Math.pow(
                array.stream().map(value -> Math.pow(Math.abs(value), 3.5)).reduce(0., (acc, item) -> acc + item),
                1 / 3.5);
        assertEquals(norm, array.norm(3.5));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testInfNorm() {
        double norm = (float) array.stream().mapToDouble(value -> Math.abs(value)).max().getAsDouble();
        assertEquals(norm, array.norm(Double.POSITIVE_INFINITY));
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
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testFillDouble() {
        array.fill(3);
        for (Byte elem : array)
            assertSpecializedEquals(wrapToByte(3), elem);
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testFillT() {
        array.fill(wrapToByte(3));
        for (Byte elem : array)
            assertSpecializedEquals(wrapToByte(3), elem);
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testContaints() {
        assertTrue(array.contains(wrapToByte(5)));
    }

}
