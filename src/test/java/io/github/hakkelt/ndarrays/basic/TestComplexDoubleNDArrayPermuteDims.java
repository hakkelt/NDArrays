package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.ByteNDArrayConstructorTrait;
import io.github.hakkelt.ndarrays.ComplexDoubleNDArrayConstructorTrait;
import io.github.hakkelt.ndarrays.ComplexNDArray;
import io.github.hakkelt.ndarrays.Errors;
import io.github.hakkelt.ndarrays.NDArray;

class TestComplexDoubleNDArrayPermuteDims implements ComplexDoubleNDArrayConstructorTrait, ByteNDArrayConstructorTrait, ConstructorTrait {
    ComplexNDArray<Double> array, pArray;

    @BeforeEach
    void setup() {
        array = createComplexDoubleNDArray(new int[]{ 4, 5, 3 });
        array.applyWithLinearIndices((value, index) -> new Complex(index, -index));
        pArray = array.permuteDims(0, 2, 1);
    }

    @Test
    void testPermuteDimsPermuteDims() {
        ComplexNDArray<Double> ppArray = pArray.permuteDims(0, 2, 1);
        array.forEachWithCartesianIndices((value, indices) -> assertEquals(value, ppArray.get(indices)));
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals(new Complex(39, -39), pArray.get(-5));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        assertEquals(new Complex(50, -50), pArray.get(2, -1, -3));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        int parentLinearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in parent
        assertEquals(new Complex(parentLinearIndex, -parentLinearIndex), pArray.get(2, -1, -3));
        int viewLinearIndex = (2 * 3 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in view
        pArray.set(new Complex(1, 1), viewLinearIndex);
        assertEquals(new Complex(1, 1), pArray.get(2, -1, -3));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 3 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in view
        pArray.set(new Complex(1, 1), 2, -1, -3);
        assertEquals(new Complex(1, 1), pArray.get(linearIndex));
    }

    @Test
    void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(60));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, pArray.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(-61));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, pArray.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(1,3,1));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 3, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(1,1,-6));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 1, -6]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(zero, 60));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, pArray.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(zero, -61));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, pArray.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(zero, 1,3,1));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 3, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(zero, 1,1,-6));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 1, -6]"),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.get(1,1,1,0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.get(1,1));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooMany() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> pArray.set(zero, 1, 1, 1, 0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnough() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> pArray.set(zero, 1,1));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Complex.class, pArray.eltype());
    }

    @Test
    void testToArray() {
        Complex[][][] arr = (Complex[][][])pArray.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr[0][0].length; j++)
                for (int k = 0; k < arr.length; k++) {
                    assertEquals(new Complex(linearIndex, -linearIndex), arr[k][i][j]);
                    linearIndex++;
                }
    }

    @Test
    void testEqual() {
        ComplexNDArray<Double> array2 = createComplexDoubleNDArray(pArray);
        assertEquals(pArray, array2);
        array2.set(new Complex(0,0), 5);
        assertNotEquals(pArray, array2);
    }

    @Test
    void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { pArray.hashCode(); });
    }

    @Test
    void testIterator() {
        int linearIndex = 0;
        for (Complex value : pArray)
            assertEquals(pArray.get(linearIndex++), value);
    }

    @Test
    void testStream() {
        final int[] linearIndex = new int[1];
        pArray.stream().forEach((value) -> {
            assertEquals(pArray.get(linearIndex[0]++), value);
        });
    }

    @Test
    void testParallelStream() {
        Complex sum = pArray.stream().parallel()
            .reduce(new Complex(0,0), (acc, item) -> acc.add(item));
        Complex acc = new Complex(0,0);
        for (int i = 0; i < array.dims(0); i++)
            for (int j = 0; j < array.dims(1); j++)
                for (int k = 0; k < array.dims(2); k++)
                acc = acc.add(array.get(i, j, k));
        assertEquals(acc, sum);
    }

    @Test
    void testCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<Complex> increased = pArray.stream()
            .map((value) -> value.add(one))
            .collect(getComplexDoubleNDArrayCollector(pArray.dims()));
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).add(one), increased.get(i));
    }

    @Test
    void testParallelCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value.add(one))
            .collect(getComplexDoubleNDArrayCollector(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(one), increased.get(i));
    }

    @Test
    void testToString() {
        String str = pArray.toString();
        assertEquals("basic NDArray<Complex Double>(4 × 3 × 5)", str);
    }

    @Test
    void testcontentToString() {
        String str = pArray.contentToString();
        String lineFormat = "%8.5e%+8.5ei\t%8.5e%+8.5ei\t%8.5e%+8.5ei\t%n";
        String expected = new StringBuilder()
            .append("basic NDArray<Complex Double>(4 × 3 × 5)" + System.lineSeparator())
            .append("[:, :, 0] =" + System.lineSeparator())
            .append(String.format(lineFormat, 0.0e+00, +0.0e+00, 2.0e+01, -2.0e+01, 4.0e+01, -4.0e+01))
            .append(String.format(lineFormat, 1.0e+00, -1.0e+00, 2.1e+01, -2.1e+01, 4.1e+01, -4.1e+01))
            .append(String.format(lineFormat, 2.0e+00, -2.0e+00, 2.2e+01, -2.2e+01, 4.2e+01, -4.2e+01))
            .append(String.format(lineFormat, 3.0e+00, -3.0e+00, 2.3e+01, -2.3e+01, 4.3e+01, -4.3e+01))
            .append(System.lineSeparator())
            .append("[:, :, 1] =" + System.lineSeparator())
            .append(String.format(lineFormat, 4.0e+00, -4.0e+00, 2.4e+01, -2.4e+01, 4.4e+01, -4.4e+01))
            .append(String.format(lineFormat, 5.0e+00, -5.0e+00, 2.5e+01, -2.5e+01, 4.5e+01, -4.5e+01))
            .append(String.format(lineFormat, 6.0e+00, -6.0e+00, 2.6e+01, -2.6e+01, 4.6e+01, -4.6e+01))
            .append(String.format(lineFormat, 7.0e+00, -7.0e+00, 2.7e+01, -2.7e+01, 4.7e+01, -4.7e+01))
            .append(System.lineSeparator())
            .append("[:, :, 2] =" + System.lineSeparator())
            .append(String.format(lineFormat, 8.0e+00, -8.0e+00, 2.8e+01, -2.8e+01, 4.8e+01, -4.8e+01))
            .append(String.format(lineFormat, 9.0e+00, -9.0e+00, 2.9e+01, -2.9e+01, 4.9e+01, -4.9e+01))
            .append(String.format(lineFormat, 1.0e+01, -1.0e+01, 3.0e+01, -3.0e+01, 5.0e+01, -5.0e+01))
            .append(String.format(lineFormat, 1.1e+01, -1.1e+01, 3.1e+01, -3.1e+01, 5.1e+01, -5.1e+01))
            .append(System.lineSeparator())
            .append("[:, :, 3] =" + System.lineSeparator())
            .append(String.format(lineFormat, 1.2e+01, -1.2e+01, 3.2e+01, -3.2e+01, 5.2e+01, -5.2e+01))
            .append(String.format(lineFormat, 1.3e+01, -1.3e+01, 3.3e+01, -3.3e+01, 5.3e+01, -5.3e+01))
            .append(String.format(lineFormat, 1.4e+01, -1.4e+01, 3.4e+01, -3.4e+01, 5.4e+01, -5.4e+01))
            .append(String.format(lineFormat, 1.5e+01, -1.5e+01, 3.5e+01, -3.5e+01, 5.5e+01, -5.5e+01))
            .append(System.lineSeparator())
            .append("[:, :, 4] =" + System.lineSeparator())
            .append(String.format(lineFormat, 1.6e+01, -1.6e+01, 3.6e+01, -3.6e+01, 5.6e+01, -5.6e+01))
            .append(String.format(lineFormat, 1.7e+01, -1.7e+01, 3.7e+01, -3.7e+01, 5.7e+01, -5.7e+01))
            .append(String.format(lineFormat, 1.8e+01, -1.8e+01, 3.8e+01, -3.8e+01, 5.8e+01, -5.8e+01))
            .append(String.format(lineFormat, 1.9e+01, -1.9e+01, 3.9e+01, -3.9e+01, 5.9e+01, -5.9e+01))
            .append(System.lineSeparator())
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testApply() {
        NDArray<Complex> pArray2 = createComplexDoubleNDArray(array).permuteDims(0,2,1).apply(value -> value.atan());
        for (int i = 1; i < array.length(); i++) {
            assertTrue(Math.abs(pArray.get(i).atan().getReal() - pArray2.get(i).getReal()) / pArray2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(pArray.get(i).atan().getImaginary() - pArray2.get(i).getImaginary()) / pArray2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testApplyWithLinearIndices() {
        NDArray<Complex> pArray2 = createComplexDoubleNDArray(array).permuteDims(0,2,1).applyWithLinearIndices((value, index) -> value.atan().add(index));
        for (int i = 1; i < pArray.length(); i++) {
            assertTrue(Math.abs(pArray.get(i).atan().add(i).getReal() - pArray2.get(i).getReal()) / pArray2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(pArray.get(i).atan().add(i).getImaginary() - pArray2.get(i).getImaginary()) / pArray2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testApplyWithCartesianIndex() {
        NDArray<Complex> pArray2 = createComplexDoubleNDArray(array).permuteDims(0,2,1).applyWithCartesianIndices((value, indices) -> value.atan().add(indices[0]));
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = 0; k < pArray.dims(2); k++) {
                    if (i == 0 && j == 0 && k == 0) continue;
                    assertTrue(Math.abs(pArray.get(i,j,k).atan().add(i).getReal() - pArray2.get(i,j,k).getReal()) / pArray2.get(i,j,k).getReal() < 1e-6);
                    assertTrue(Math.abs(pArray.get(i,j,k).atan().add(i).getImaginary() - pArray2.get(i,j,k).getImaginary()) / pArray2.get(i,j,k).getImaginary() < 1e-6);
                }
    }

    @Test
    void testMap() {
        NDArray<Complex> pArray2 = pArray.map(value -> value.atan());
        for (int i = 1; i < pArray.length(); i++) {
            assertTrue(Math.abs(pArray.get(i).atan().getReal() - pArray2.get(i).getReal()) / pArray2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(pArray.get(i).atan().getImaginary() - pArray2.get(i).getImaginary()) / pArray2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testMapWithLinearIndices() {
        NDArray<Complex> pArray2 = pArray.mapWithLinearIndices((value, index) -> value.atan().add(index));
        for (int i = 1; i < pArray.length(); i++) {
            assertTrue(Math.abs(pArray.get(i).atan().add(i).getReal() - pArray2.get(i).getReal()) / pArray2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(pArray.get(i).atan().add(i).getImaginary() - pArray2.get(i).getImaginary()) / pArray2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testMapWithCartesianIndex() {
        NDArray<Complex> pArray2 = pArray.mapWithCartesianIndices((value, indices) -> value.atan().add(indices[0]));
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = 0; k < pArray.dims(2); k++) {
                    if (i == 0 && j == 0 && k == 0) continue;
                    assertTrue(Math.abs(pArray.get(i,j,k).atan().add(i).getReal() - pArray2.get(i,j,k).getReal()) / pArray2.get(i,j,k).getReal() < 1e-6);
                    assertTrue(Math.abs(pArray.get(i,j,k).atan().add(i).getImaginary() - pArray2.get(i,j,k).getImaginary()) / pArray2.get(i,j,k).getImaginary() < 1e-6);
                }
    }

    @Test
    void testForEach() { 
        AtomicInteger i = new AtomicInteger(0);
        pArray.forEach(value -> assertEquals(pArray.get(i.getAndIncrement()), value));
    }

    @Test
    void testForEachWithLinearIndices() {
        pArray.forEachWithLinearIndices((value, index) -> assertEquals(pArray.get(index), value));
    }

    @Test
    void testForEachWithCartesianIndex() {
        pArray.forEachWithCartesianIndices((value, indices) -> assertEquals(pArray.get(indices), value));
    }

    @Test
    void testAddArrayTopArray() {
        ComplexNDArray<Double> array2 = createComplexDoubleNDArray(pArray);
        ComplexNDArray<Double> array3 = pArray.add(array2);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddpArrayToArray() {
        ComplexNDArray<Double> array2 = createComplexDoubleNDArray(pArray);
        ComplexNDArray<Double> array3 = array2.add(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddpArrayTopArray() {
        ComplexNDArray<Double> pArray2 = array.permuteDims(0, 2, 1);
        ComplexNDArray<Double> array3 = pArray2.add(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddScalar() {
        ComplexNDArray<Double> pArray2 = pArray.add(5);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).add(5), pArray2.get(i));
    }

    @Test
    void testAddMultiple() {
        ComplexNDArray<Double> array2 = createComplexDoubleNDArray(array);
        ComplexNDArray<Double> pArray2 = array2.permuteDims(0, 2, 1);
        ComplexNDArray<Double> array3 = pArray2.add(pArray, 5.3, pArray2, new Complex(3,1));
        for (int i = 0; i < pArray.length(); i++) {
            Complex expected = pArray.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        ComplexNDArray<Double> array2 = createComplexDoubleNDArray(array);
        ComplexNDArray<Double> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).multiply(2), pArray2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        ComplexNDArray<Double> array2 = createComplexDoubleNDArray(array);
        ComplexNDArray<Double> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(5);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).add(5), pArray2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        ComplexNDArray<Double> array2 = createComplexDoubleNDArray(array);
        ComplexNDArray<Double> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(pArray, 5.3, pArray2, new Complex(3,1));
        for (int i = 0; i < pArray.length(); i++) {
            Complex expected = pArray.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(pArray2.get(i)).abs() < 1e5);
        }
    }

    @Test
    void test0Norm() {
        pArray.slice(":", 0, ":").fill(0);
        double norm = pArray.stream()
            .filter(value -> value != Complex.ZERO)
            .count();
        assertEquals(norm, pArray.norm(0));
    }

    @Test
    void test1Norm() {
        double norm = pArray.stream()
            .mapToDouble(value -> value.abs())
            .reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, pArray.norm(1));
    }

    @Test
    void test2Norm() {
        double norm = Math.sqrt(pArray.stream()
            .mapToDouble(value -> Math.pow(value.abs(), 2))
            .reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, pArray.norm());
    }

    @Test
    void testPQuasinorm() {
        double norm = Math.pow(pArray.stream()
            .mapToDouble(value -> Math.pow(value.abs(), 0.5))
            .reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, pArray.norm(0.5));
    }

    @Test
    void testPNorm() {
        double norm = Math.pow(pArray.stream()
            .mapToDouble(value -> Math.pow(value.abs(), 3.5))
            .reduce(0., (acc, item) -> acc + item), 1 / 3.5);
        assertEquals(norm, pArray.norm(3.5));
    }

    @Test
    void testInfNorm() {
        double norm = pArray.stream()
            .mapToDouble(value -> value.abs())
            .max().getAsDouble();
        assertEquals(norm, pArray.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testCopy() {
        ComplexNDArray<Double> array2 = pArray.copy();
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i), array2.get(i));
        array2.set(new Complex(0,0), 5);
        assertNotEquals(pArray.get(5), array2.get(5));
    }

    @Test
    void testFillComplex() {
        pArray.fill(new Complex(3,3));
        for (Complex elem : pArray)
            assertEquals(new Complex(3, 3), elem);
    }

    @Test
    void testFillReal() {
        pArray.fill(3);
        for (Complex elem : pArray)
            assertEquals(new Complex(3, 0), elem);
    }

    @Test
    void testMaskPermuted() {
        NDArray<Byte> mask = createByteNDArray(pArray.abs().map(value -> value > 20 ? 1. : 0.));
        ComplexNDArray<Double> masked = pArray.mask(mask);
        masked.forEach((value) -> assertTrue(value.abs() > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(value.abs() <= 20));
    }

    @Test
    void testMaskPermutedWithPredicate() {
        ComplexNDArray<Double> masked = pArray.mask(value -> value.abs() > 20);
        masked.forEach((value) -> assertTrue(value.abs() > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(value.abs() <= 20));
    }

    @Test
    void testMaskPermutedWithPredicateWithLinearIndices() {
        ComplexNDArray<Double> masked = pArray.maskWithLinearIndices((value, i) -> value.abs() > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(value.abs() > 20 && i < 10));
        masked.fill(0);
        pArray.forEachWithLinearIndices((value, i) -> assertTrue(value.abs() <= 20 || i >= 10));
    }

    @Test
    void testMaskPermutedWithPredicateWithCartesianIndices() {
        ComplexNDArray<Double> masked = pArray.maskWithCartesianIndices((value, idx) -> value.abs() > 20 && idx[0] == 0);
        masked.forEach(value -> assertTrue(value.abs() > 20));
        masked.fill(0);
        pArray.forEachWithCartesianIndices((value, idx) -> assertTrue(value.abs() <= 20 || idx[0] != 0));
    }

    @Test
    void testSliceAndToArray() {
        ComplexNDArray<Double> slice = pArray.slice(1, ":", "1:4");
        Complex[][] arr = (Complex[][])slice.toArray();
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[j][i]);
    }

    @Test
    void testConcatenate() {
        ComplexNDArray<Double> array2 = createComplexDoubleNDArray(new int[]{4, 3, 2}).fill(1);
        ComplexNDArray<Double> array3 = pArray.concatenate(2, array2);
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = 0; k < pArray.dims(2); k++)
                    assertEquals(pArray.get(i, j, k), array3.get(i, j, k));
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = pArray.dims(2); k < pArray.dims(2) + array2.dims(2); k++)
                    assertEquals(new Complex(1, 0), array3.get(i, j, k));
    }

    @Test
    void testConcatenateMultiple() {
        ComplexNDArray<Double> array2 = pArray.copy().fill(1).slice(":", ":", "1:3");
        ComplexNDArray<Double> array3 = createComplexDoubleNDArray(new int[]{5, 3, 4}).permuteDims(2, 1, 0);
        ComplexNDArray<Double> array4 = createComplexDoubleNDArray(new int[]{36}).fill(new Complex(2, -2)).reshape(4, 3, 3);
        ComplexNDArray<Double> array5 = pArray.concatenate(2, array2, array3, array4);
        int start = 0;
        int end = pArray.dims(2);
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(pArray.get(i, j, k), array5.get(i, j, k));
        start = end;
        end += array2.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(new Complex(1, 0), array5.get(i, j, k));
        start = end;
        end += array3.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(new Complex(0, 0), array5.get(i, j, k));
        start = end;
        end += array4.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(new Complex(2, -2), array5.get(i, j, k));
    }

    @Test
    void testReal() {
        NDArray<Double> real = pArray.real();
        pArray.streamLinearIndices()
            .forEach(i -> assertEquals(pArray.get(i).getReal(), real.get(i)));
    }

    @Test
    void testImag() {
        NDArray<Double> imag = pArray.imaginary();
        pArray.streamLinearIndices()
            .forEach(i -> assertEquals(pArray.get(i).getImaginary(), imag.get(i)));
    }

    @Test
    void testAbs() {
        NDArray<Double> abs = pArray.abs();
        pArray.streamLinearIndices()
            .forEach(i -> assertTrue(pArray.get(i).abs() - abs.get(i) < 1e-5));
    }

    @Test
    void testAngle() {
        NDArray<Double> angle = pArray.angle();
        pArray.streamLinearIndices()
            .forEach(i -> assertTrue(pArray.get(i).getArgument() - angle.get(i) < 1e-5));
    }
}
