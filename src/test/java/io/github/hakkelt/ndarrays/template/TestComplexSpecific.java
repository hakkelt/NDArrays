package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$2ComplexSpecific")
@Patterns({ "Float", "BasicComplexFloatNDArray" })
@Replacements({ "Double", "BasicComplexDoubleNDArray" })
class TestComplexSpecific extends TestBase {

    static Stream<ComplexNDArray<Float>> provideNDArrays() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> new Complex(index));
        ComplexNDArray<Float> slice = array.slice(1, "1:4", ":");
        ComplexNDArray<Float> reshaped = array.reshape(20, 3);
        ComplexNDArray<Float> pArray = array.permuteDims(0, 2, 1);
        ComplexNDArray<Float> mask = array.mapWithLinearIndices((value, index) -> new Complex(index % 2));
        ComplexNDArray<Float> masked = array.mask(mask);
        return Stream.of(array, slice, reshaped, pArray, masked);
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void testArrayEqualsWithDifferentType(ComplexNDArray<Float> target) {
        NDArray<?> array2 = new BasicLongNDArray(target.abs());
        assertNotEquals(target, array2);
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void TestFill(ComplexNDArray<Float> target) {
        target.fill(Complex.ONE);
        target.forEach(value -> assertEquals(Complex.ONE, value));
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void TestFillFloat(ComplexNDArray<Float> target) {
        target.fill(Float.valueOf(1));
        target.forEach(value -> {
            assertEquals(1., value.getReal());
            assertEquals(0, value.getImaginary());
        });
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void TestFillDouble(ComplexNDArray<Float> target) {
        target.fill(Double.valueOf(1));
        target.forEach(value -> {
            assertEquals(1., value.getReal());
            assertEquals(0, value.getImaginary());
        });
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void testAbs(ComplexNDArray<Float> target) {
        NDArray<Float> result = target.abs();
        result.forEachWithLinearIndices((value, i) -> assertEquals(target.get(i).abs(), value.doubleValue()));
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void testAngle(ComplexNDArray<Float> target) {
        NDArray<Float> result = target.angle();
        result.forEachWithLinearIndices((value, i) -> assertEquals(target.get(i).getArgument(), value.doubleValue()));
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void testReal(ComplexNDArray<Float> target) {
        NDArray<Float> result = target.real();
        result.forEachWithLinearIndices((value, i) -> assertEquals(target.get(i).getReal(), value.doubleValue()));
    }

    @ParameterizedTest
    @MethodSource("provideNDArrays")
    void testImaginary(ComplexNDArray<Float> target) {
        NDArray<Float> result = target.imaginary();
        result.forEachWithLinearIndices((value, i) -> assertEquals(target.get(i).getImaginary(), value.doubleValue()));
    }

    @Test
    @Patterns({ "byteValue", "Byte", "BasicByteNDArray" })
    @Replacements({ "shortValue", "Short", "BasicShortNDArray" })
    @Replacements({ "intValue", "Integer", "BasicIntegerNDArray" })
    @Replacements({ "longValue", "Long", "BasicLongNDArray" })
    @Replacements({ "floatValue", "Float", "BasicFloatNDArray" })
    @Replacements({ "doubleValue", "Double", "BasicDoubleNDArray" })
    void testComplexCopyFrom() {
        int[] shape = { 4, 5, 3 };
        NDArray<Byte> array1 = new BasicByteNDArray(shape).applyWithLinearIndices((value, i) -> i.byteValue());
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1, array1.multiply(-1));
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
        ComplexNDArray<Float> lhs = new BasicComplexFloatNDArray(rhs);
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
    void testAddDifferentType(ComplexNDArray<Float> lhs, NDArray<? extends Number> rhs) {
        ComplexNDArray<Float> result = lhs.add(rhs);
        result.forEachWithLinearIndices((value, i) -> assertEquals(lhs.get(i).add(rhs.get(i).doubleValue()), value));
    }

    static Stream<Arguments> provideNDArraysWithIndex() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> new Complex(index));
        ComplexNDArray<Float> slice = array.copy().slice(1, "1:4", ":");
        ComplexNDArray<Float> reshaped = array.copy().reshape(20, 3);
        ComplexNDArray<Float> pArray = array.copy().permuteDims(0, 2, 1);
        ComplexNDArray<Float> mask = array.mapWithLinearIndices((value, index) -> new Complex(index % 2));
        ComplexNDArray<Float> masked = array.copy().mask(mask);
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
    void testSetRealImag(ComplexNDArray<Float> target, int[] index, Number scalar) {
        ComplexNDArray<Float> original = target.copy();
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
    void testSetComplex(ComplexNDArray<Float> target, int[] index) {
        target.set(new Complex(4.5, 6), 0);
        assertEquals(new Complex(4.5, 6), target.get(0));
        target.set(new Complex(4.5, 6), index);
        assertEquals(new Complex(4.5, 6), target.get(index));
    }

    @Test
    void testPrintComplex() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4);
        array.set(new Complex(2, 3), 0);
        array.set(new Complex(-2, 3), 1);
        array.set(new Complex(2, -3), 2);
        array.set(new Complex(-2, -3), 3);
        assertEquals("basic NDArray<Complex Float>(4)" + System.lineSeparator()
                + "2,00000e+00+3,00000e+00i	-2,00000e+00+3,00000e+00i	2,00000e+00-3,00000e+00i	-2,00000e+00-3,00000e+00i	",
                array.contentToString());
    }

}
