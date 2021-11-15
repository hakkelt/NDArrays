/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestComplexSpecific.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import io.github.hakkelt.ndarrays.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestBasicComplexDoubleNDArrayComplexSpecific extends TestBase {

    static Stream<ComplexNDArray<Double>> provideNDArrays() {
        ComplexNDArray<Double> array = new BasicComplexDoubleNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> new Complex(index));
        ComplexNDArray<Double> slice = array.slice(1, "1:4", ":");
        ComplexNDArray<Double> reshaped = array.reshape(20, 3);
        ComplexNDArray<Double> pArray = array.permuteDims(0, 2, 1);
        ComplexNDArray<Double> mask = array.mapWithLinearIndices((value, index) -> new Complex(index % 2));
        ComplexNDArray<Double> masked = array.mask(mask);
        return Stream.of(array, slice, reshaped, pArray, masked);
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void testArrayEqualsWithDifferentType(ComplexNDArray<Double> target) {
        NDArray<?> array2 = new BasicLongNDArray(target.abs());
        assertNotEquals(target, array2);
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void TestFill(ComplexNDArray<Double> target) {
        target.fill(Complex.ONE);
        target.forEach(value -> assertEquals(Complex.ONE, value));
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void TestFillFloat(ComplexNDArray<Double> target) {
        target.fill(Double.valueOf(1));
        target.forEach(value -> {
            assertEquals(1., value.getReal());
            assertEquals(0, value.getImaginary());
        });
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void TestFillDouble(ComplexNDArray<Double> target) {
        target.fill(Double.valueOf(1));
        target.forEach(value -> {
            assertEquals(1., value.getReal());
            assertEquals(0, value.getImaginary());
        });
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void testAbs(ComplexNDArray<Double> target) {
        NDArray<Double> result = target.abs();
        result.forEachWithLinearIndices((value, i) -> assertEquals(target.get(i).abs(), value.doubleValue()));
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void testargument(ComplexNDArray<Double> target) {
        NDArray<Double> result = target.argument();
        result.forEachWithLinearIndices((value, i) -> assertEquals(target.get(i).getArgument(), value.doubleValue()));
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void testReal(ComplexNDArray<Double> target) {
        NDArray<Double> result = target.real();
        result.forEachWithLinearIndices((value, i) -> assertEquals(target.get(i).getReal(), value.doubleValue()));
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void testImaginary(ComplexNDArray<Double> target) {
        NDArray<Double> result = target.imaginary();
        result.forEachWithLinearIndices((value, i) -> assertEquals(target.get(i).getImaginary(), value.doubleValue()));
    }

    @Test
    void testComplexCopyFromWithByteValue() {
        int[] shape = { 4, 5, 3 };
        NDArray<Byte> array1 = new BasicByteNDArray(shape).applyWithLinearIndices((value, i) -> i.byteValue());
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1, array1.multiply(-1));
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2) {
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                        assertNotEquals(-array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getImaginary());
                    } else {
                        assertEquals(new Complex(array1.get(i, j, k), -array1.get(i, j, k)), array2.get(i, j, k));
                    }
                }
    }

    @Test
    void testComplexCopyFromWithShortValue() {
        int[] shape = { 4, 5, 3 };
        NDArray<Short> array1 = new BasicShortNDArray(shape).applyWithLinearIndices((value, i) -> i.shortValue());
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1, array1.multiply(-1));
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2) {
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                        assertNotEquals(-array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getImaginary());
                    } else {
                        assertEquals(new Complex(array1.get(i, j, k), -array1.get(i, j, k)), array2.get(i, j, k));
                    }
                }
    }

    @Test
    void testComplexCopyFromWithIntValue() {
        int[] shape = { 4, 5, 3 };
        NDArray<Integer> array1 = new BasicIntegerNDArray(shape).applyWithLinearIndices((value, i) -> i.intValue());
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1, array1.multiply(-1));
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2) {
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                        assertNotEquals(-array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getImaginary());
                    } else {
                        assertEquals(new Complex(array1.get(i, j, k), -array1.get(i, j, k)), array2.get(i, j, k));
                    }
                }
    }

    @Test
    void testComplexCopyFromWithLongValue() {
        int[] shape = { 4, 5, 3 };
        NDArray<Long> array1 = new BasicLongNDArray(shape).applyWithLinearIndices((value, i) -> i.longValue());
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1, array1.multiply(-1));
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2) {
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                        assertNotEquals(-array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getImaginary());
                    } else {
                        assertEquals(new Complex(array1.get(i, j, k), -array1.get(i, j, k)), array2.get(i, j, k));
                    }
                }
    }

    @Test
    void testComplexCopyFromWithFloatValue() {
        int[] shape = { 4, 5, 3 };
        NDArray<Float> array1 = new BasicFloatNDArray(shape).applyWithLinearIndices((value, i) -> i.floatValue());
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1, array1.multiply(-1));
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2) {
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                        assertNotEquals(-array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getImaginary());
                    } else {
                        assertEquals(new Complex(array1.get(i, j, k), -array1.get(i, j, k)), array2.get(i, j, k));
                    }
                }
    }

    @Test
    void testComplexCopyFromWithDoubleValue() {
        int[] shape = { 4, 5, 3 };
        NDArray<Double> array1 = new BasicDoubleNDArray(shape).applyWithLinearIndices((value, i) -> i.doubleValue());
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1, array1.multiply(-1));
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2) {
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                        assertNotEquals(-array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getImaginary());
                    } else {
                        assertEquals(new Complex(array1.get(i, j, k), -array1.get(i, j, k)), array2.get(i, j, k));
                    }
                }
    }

    static Stream<Arguments> provideNDArraysWithDifferentTypes() {
        NDArray<Byte> rhs = new BasicByteNDArray(4, 5, 3);
        rhs.applyWithLinearIndices((value, index) -> index.byteValue());
        ComplexNDArray<Double> lhs = new BasicComplexDoubleNDArray(rhs);
        return Stream.of(
            Arguments.of(lhs, rhs),
            Arguments.of(lhs, new BasicShortNDArray(rhs)),
            Arguments.of(lhs, new BasicIntegerNDArray(rhs)),
            Arguments.of(lhs, new BasicLongNDArray(rhs)),
            Arguments.of(lhs, new BasicFloatNDArray(rhs)),
            Arguments.of(lhs, new BasicDoubleNDArray(rhs)),
            Arguments.of(lhs, new BasicBigIntegerNDArray(rhs)),
            Arguments.of(lhs, new BasicBigDecimalNDArray(rhs))
        );
    }

    @ParameterizedTest
    @MethodSource("provideNDArraysWithDifferentTypes")
    void testAddDifferentType(ComplexNDArray<Double> lhs, NDArray<? extends Number> rhs) {
        ComplexNDArray<Double> result = lhs.add(rhs);
        result.forEachWithLinearIndices((value, i) -> assertEquals(lhs.get(i).add(rhs.get(i).doubleValue()), value));
    }

    static Stream<Arguments> provideNDArraysWithIndex() {
        ComplexNDArray<Double> array = new BasicComplexDoubleNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> new Complex(index));
        ComplexNDArray<Double> slice = array.copy().slice(1, "1:4", ":");
        ComplexNDArray<Double> reshaped = array.copy().reshape(20, 3);
        ComplexNDArray<Double> pArray = array.copy().permuteDims(0, 2, 1);
        ComplexNDArray<Double> mask = array.mapWithLinearIndices((value, index) -> new Complex(index % 2));
        ComplexNDArray<Double> masked = array.copy().mask(mask);
        return Stream.of(
            Arguments.of(array, new int[] { 0, 1, 0 }),
            Arguments.of(slice, new int[] { 0, 1 }),
            Arguments.of(reshaped, new int[] { 0, 1 }),
            Arguments.of(pArray, new int[] { 0, 1, 0 }),
            Arguments.of(masked, new int[] { 2 })
        );
    }

    static Stream<Number> provideScalar() {
        return Stream.of((byte) 5, (short) 5, 5, (long) 5, 5.f, 5., BigInteger.valueOf(5), new BigDecimal(5));
    }

    static Stream<Arguments> provideNDArraysAndScalars() {
        List<Arguments> combinations = new ArrayList<>();
        provideNDArraysWithIndex().forEach(arg ->
            provideScalar().forEach(scalar -> combinations.add(Arguments.of(arg.get()[0], arg.get()[1], scalar)))
        );
        return combinations.stream();
    }

    @ParameterizedTest
    @MethodSource("provideNDArraysAndScalars")
    void testSetRealImag(ComplexNDArray<Double> target, int[] index, Number scalar) {
        ComplexNDArray<Double> original = target.copy();
        target.setReal(scalar, 0);
        assertEquals(new Complex(scalar.doubleValue(), original.get(0).getImaginary()), target.get(0));
        target.setImag(scalar, 1);
        assertEquals(new Complex(original.get(1).getReal(), scalar.doubleValue()), target.get(1));
        target.setReal(scalar, index);
        assertEquals(new Complex(scalar.doubleValue(), original.get(index).getImaginary()), target.get(index));
        target.setImag(scalar, index);
        assertEquals(new Complex(scalar.doubleValue(), scalar.doubleValue()), target.get(index));
    }

    @ParameterizedTest
    @MethodSource("provideNDArraysWithIndex")
    void testSetComplex(ComplexNDArray<Double> target, int[] index) {
        target.set(new Complex(4.5, 6), 0);
        assertEquals(new Complex(4.5, 6), target.get(0));
        target.set(new Complex(4.5, 6), index);
        assertEquals(new Complex(4.5, 6), target.get(index));
    }

    @Test
    void testPrintComplex() {
        ComplexNDArray<Double> array = new BasicComplexDoubleNDArray(4);
        array.set(new Complex(2, 3), 0);
        array.set(new Complex(-2, 3), 1);
        array.set(new Complex(2, -3), 2);
        array.set(new Complex(-2, -3), 3);
        assertEquals("basic NDArray<Complex Double>(4)" + System.lineSeparator()
                + "2,00000e+00+3,00000e+00i	-2,00000e+00+3,00000e+00i	2,00000e+00-3,00000e+00i	-2,00000e+00-3,00000e+00i	",
                array.contentToString());
    }

}
