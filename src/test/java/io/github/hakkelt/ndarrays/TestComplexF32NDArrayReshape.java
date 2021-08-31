package io.github.hakkelt.ndarrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestComplexF32NDArrayReshape {
    ComplexNDArray<Float> array, reshaped;

    @BeforeEach
    void setup() {
        array = new ComplexF32NDArray(new int[]{ 4, 5, 3 });
        array.applyWithLinearIndices((value, index) -> new Complex(index, -index));
        reshaped = array.reshape(20, 3);
    }

    @Test
    void testReshapeReshaped() {
        ComplexNDArray<Float> reshaped2 = reshaped.reshape(4, 5, 3);
        array.forEachWithCartesianIndices((value, indices) -> assertEquals(value, reshaped2.get(indices)));
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals(new Complex(55, -55), reshaped.get(-5));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in parent
        assertEquals(new Complex(linearIndex, -linearIndex), reshaped.get(10, -1));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals(new Complex(linearIndex, -linearIndex), reshaped.get(10, -1));
        reshaped.set(new Complex(1, 1), linearIndex);
        assertEquals(new Complex(1, 1), reshaped.get(10, -1));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        reshaped.set(new Complex(1, 1), 10, -1);
        assertEquals(new Complex(1, 1), reshaped.get(linearIndex));
    }

    @Test
    void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(60));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, reshaped.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(-61));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, reshaped.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(1,3));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "20 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(-21,1));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "20 × 3", "[-21, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(zero, 60));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, reshaped.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(zero, -61));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, reshaped.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(zero, 1,3));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "20 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(zero, -21,1));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "20 × 3", "[-21, 1]"),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooMany() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.get(1,1,1,0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnough() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.get(1,1));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooMany() {
        Complex zero = new Complex(0,0);
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> reshaped.set(zero, 1,1,1,0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnough() {
        Complex zero = new Complex(0,0);
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> reshaped.set(zero, 1,1));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Complex.class, reshaped.eltype());
    }

    @Test
    void testToArray() {
        Complex[][] arr = (Complex[][])reshaped.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr.length; j++) {
                assertEquals(new Complex(linearIndex, -linearIndex), arr[j][i]);
                linearIndex++;
            }
    }

    @Test
    void testEqual() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(reshaped);
        assertEquals(reshaped, array2);
        array2.set(new Complex(0,0), 10);
        assertNotEquals(reshaped, array2);
    }

    @Test
    void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { reshaped.hashCode(); });
    }

    @Test
    void testIterator() {
        int linearIndex = 0;
        for (Complex value : reshaped) {
            assertEquals(new Complex(linearIndex, -linearIndex), value);
            linearIndex++;
        }
    }

    @Test
    void testStream() {
        final int[] linearIndex = new int[1];
        reshaped.stream().forEach((value) -> {
            assertEquals(new Complex(linearIndex[0], -linearIndex[0]), value);
            linearIndex[0]++;
        });
    }

    @Test
    void testParallelStream() {
        Complex sum = reshaped.stream().parallel()
            .reduce(new Complex(0,0), (acc, item) -> acc.add(item));
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(new Complex(GaussSum, -GaussSum), sum);
    }

    @Test
    void testCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = reshaped.stream()
            .map((value) -> value.add(one))
            .collect(ComplexF32NDArray.getCollector(reshaped.dims()));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).add(one), increased.get(i));
    }

    @Test
    void testParallelCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = reshaped.stream().parallel()
            .map((value) -> value.add(one))
            .collect(ComplexF32NDArray.getCollector(reshaped.dims()));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).add(one), increased.get(i));
    }

    @Test
    void testToString() {
        String str = reshaped.toString();
        assertEquals("simple NDArray<Complex Float>(20 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = reshaped.contentToString();
        String lineFormat = "%8.5e%+8.5ei\t%8.5e%+8.5ei\t%8.5e%+8.5ei\t%n";
        String expected = new StringBuilder()
            .append("simple NDArray<Complex Float>(20 × 3)" + System.lineSeparator())
            .append(String.format(lineFormat, 0.0e+00, +0.0e+00, 2.0e+01, -2.0e+01, 4.0e+01, -4.0e+01))
            .append(String.format(lineFormat, 1.0e+00, -1.0e+00, 2.1e+01, -2.1e+01, 4.1e+01, -4.1e+01))
            .append(String.format(lineFormat, 2.0e+00, -2.0e+00, 2.2e+01, -2.2e+01, 4.2e+01, -4.2e+01))
            .append(String.format(lineFormat, 3.0e+00, -3.0e+00, 2.3e+01, -2.3e+01, 4.3e+01, -4.3e+01))
            .append(String.format(lineFormat, 4.0e+00, -4.0e+00, 2.4e+01, -2.4e+01, 4.4e+01, -4.4e+01))
            .append(String.format(lineFormat, 5.0e+00, -5.0e+00, 2.5e+01, -2.5e+01, 4.5e+01, -4.5e+01))
            .append(String.format(lineFormat, 6.0e+00, -6.0e+00, 2.6e+01, -2.6e+01, 4.6e+01, -4.6e+01))
            .append(String.format(lineFormat, 7.0e+00, -7.0e+00, 2.7e+01, -2.7e+01, 4.7e+01, -4.7e+01))
            .append(String.format(lineFormat, 8.0e+00, -8.0e+00, 2.8e+01, -2.8e+01, 4.8e+01, -4.8e+01))
            .append(String.format(lineFormat, 9.0e+00, -9.0e+00, 2.9e+01, -2.9e+01, 4.9e+01, -4.9e+01))
            .append(String.format(lineFormat, 1.0e+01, -1.0e+01, 3.0e+01, -3.0e+01, 5.0e+01, -5.0e+01))
            .append(String.format(lineFormat, 1.1e+01, -1.1e+01, 3.1e+01, -3.1e+01, 5.1e+01, -5.1e+01))
            .append(String.format(lineFormat, 1.2e+01, -1.2e+01, 3.2e+01, -3.2e+01, 5.2e+01, -5.2e+01))
            .append(String.format(lineFormat, 1.3e+01, -1.3e+01, 3.3e+01, -3.3e+01, 5.3e+01, -5.3e+01))
            .append(String.format(lineFormat, 1.4e+01, -1.4e+01, 3.4e+01, -3.4e+01, 5.4e+01, -5.4e+01))
            .append(String.format(lineFormat, 1.5e+01, -1.5e+01, 3.5e+01, -3.5e+01, 5.5e+01, -5.5e+01))
            .append(String.format(lineFormat, 1.6e+01, -1.6e+01, 3.6e+01, -3.6e+01, 5.6e+01, -5.6e+01))
            .append(String.format(lineFormat, 1.7e+01, -1.7e+01, 3.7e+01, -3.7e+01, 5.7e+01, -5.7e+01))
            .append(String.format(lineFormat, 1.8e+01, -1.8e+01, 3.8e+01, -3.8e+01, 5.8e+01, -5.8e+01))
            .append(String.format(lineFormat, 1.9e+01, -1.9e+01, 3.9e+01, -3.9e+01, 5.9e+01, -5.9e+01))        
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testApply() {
        NDArray<Complex> reshaped2 = new ComplexF32NDArray(array).reshape(20, 3).apply(value -> value.atan());
        for (int i = 1; i < array.length(); i++) {
            assertTrue(Math.abs(reshaped.get(i).atan().getReal() - reshaped2.get(i).getReal()) / reshaped2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(reshaped.get(i).atan().getImaginary() - reshaped2.get(i).getImaginary()) / reshaped2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testApplyWithLinearIndices() {
        NDArray<Complex> reshaped2 = new ComplexF32NDArray(array).reshape(20, 3).applyWithLinearIndices((value, index) -> value.atan().add(index));
        for (int i = 1; i < reshaped.length(); i++) {
            assertTrue(Math.abs(reshaped.get(i).atan().add(i).getReal() - reshaped2.get(i).getReal()) / reshaped2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(reshaped.get(i).atan().add(i).getImaginary() - reshaped2.get(i).getImaginary()) / reshaped2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testApplyWithCartesianIndex() {
        NDArray<Complex> reshaped2 = new ComplexF32NDArray(array).reshape(20, 3).applyWithCartesianIndices((value, indices) -> value.atan().add(indices[0]));
        for (int i = 0; i < reshaped.dims(0); i++)
            for (int j = 0; j < reshaped.dims(1); j++) {
                if (i == 0 && j == 0) continue;
                assertTrue(Math.abs(reshaped.get(i,j).atan().add(i).getReal() - reshaped2.get(i,j).getReal()) / reshaped2.get(i,j).getReal() < 1e-6);
                assertTrue(Math.abs(reshaped.get(i,j).atan().add(i).getImaginary() - reshaped2.get(i,j).getImaginary()) / reshaped2.get(i,j).getImaginary() < 1e-6);
            }
    }

    @Test
    void testMap() {
        NDArray<Complex> reshaped2 = reshaped.map(value -> value.atan());
        for (int i = 1; i < reshaped.length(); i++) {
            assertTrue(Math.abs(reshaped.get(i).atan().getReal() - reshaped2.get(i).getReal()) / reshaped2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(reshaped.get(i).atan().getImaginary() - reshaped2.get(i).getImaginary()) / reshaped2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testMapWithLinearIndices() {
        NDArray<Complex> reshaped2 = reshaped.mapWithLinearIndices((value, index) -> value.atan().add(index));
        for (int i = 1; i < reshaped.length(); i++) {
            assertTrue(Math.abs(reshaped.get(i).atan().add(i).getReal() - reshaped2.get(i).getReal()) / reshaped2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(reshaped.get(i).atan().add(i).getImaginary() - reshaped2.get(i).getImaginary()) / reshaped2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testMapWithCartesianIndex() {
        NDArray<Complex> reshaped2 = reshaped.mapWithCartesianIndices((value, indices) -> value.atan().add(indices[0]));
        for (int i = 0; i < reshaped.dims(0); i++)
            for (int j = 0; j < reshaped.dims(1); j++) {
                if (i == 0 && j == 0) continue;
                assertTrue(Math.abs(reshaped.get(i,j).atan().add(i).getReal() - reshaped2.get(i,j).getReal()) / reshaped2.get(i,j).getReal() < 1e-6);
                assertTrue(Math.abs(reshaped.get(i,j).atan().add(i).getImaginary() - reshaped2.get(i,j).getImaginary()) / reshaped2.get(i,j).getImaginary() < 1e-6);
            }
    }

    @Test
    void testForEach() {
        AtomicInteger i = new AtomicInteger(0);
        reshaped.forEach(value -> assertEquals(reshaped.get(i.getAndIncrement()), value));
    }

    @Test
    void testForEachWithLinearIndices() {
        reshaped.forEachWithLinearIndices((value, index) -> assertEquals(reshaped.get(index), value));
    }

    @Test
    void testForEachWithCartesianIndex() {
        reshaped.forEachWithCartesianIndices((value, indices) -> assertEquals(reshaped.get(indices), value));
    }

    @Test
    void testAdd() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(reshaped);
        ComplexNDArray<Float> array3 = reshaped.add(array2);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddScalar() {
        ComplexNDArray<Float> array2 = reshaped.add(5);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).add(5), array2.get(i));
    }

    @Test
    void testAddMultiple() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(reshaped);
        ComplexNDArray<Float> array3 = reshaped.add(array2, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < reshaped.length(); i++) {
            Complex expected = reshaped.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(reshaped);
        array2.addInplace(reshaped);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).multiply(2), array2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(reshaped);
        array2.addInplace(5);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).add(5), array2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(reshaped);
        array2.addInplace(reshaped, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < reshaped.length(); i++) {
            Complex expected = reshaped.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array2.get(i)).abs() < 1e5);
        }
    }

    @Test
    void testSum() {
        Complex sum = reshaped.sum();
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(new Complex(GaussSum, -GaussSum), sum);
    }

    @Test
    void testSum1D() {
        ComplexNDArray<Float> sum = reshaped.sum(1);
        for (int i = 0; i < sum.dims(0); i++) {
            double GaussSum = (reshaped.get(i,0).getReal() + reshaped.get(i,-1).getReal()) * 3 / 2;
            assertEquals(new Complex(GaussSum, -GaussSum), sum.get(i));
        }
    }

    @Test
    void testSum2D() {
        reshaped = array.reshape(5,4,3);
        ComplexNDArray<Float> sum = reshaped.sum(2, 1);
        for (int i = 0; i < sum.length(); i++) {
            Complex acc = new Complex(0,0);
            for (int j = 0; j < reshaped.dims(1); j++)
                for (int k = 0; k < reshaped.dims(2); k++)
                    acc = acc.add(reshaped.get(i,j,k));
            assertEquals(acc, sum.get(i));
        }
    }

    @Test
    void test0Norm() {
        reshaped.slice(":", 0).fill(0);
        double norm = reshaped.stream()
            .filter(value -> value != Complex.ZERO)
            .count();
        assertEquals(norm, reshaped.norm(0));
    }

    @Test
    void test1Norm() {
        double norm = (float)reshaped.stream()
            .map(value -> (float)value.abs())
            .reduce((float)0., (acc, item) -> acc + item);
        assertTrue(Math.abs(norm - reshaped.norm(1)) / norm < 1e-6);
    }

    @Test
    void test2Norm() {
        double norm = (float)Math.sqrt(reshaped.stream()
            .map(value -> (float)Math.pow(value.abs(), 2))
            .reduce((float)0., (acc, item) -> acc + item));
        assertTrue(Math.abs(norm - reshaped.norm()) / norm < 1e-6);
    }

    @Test
    void testPQuasinorm() {
        double norm = (float)Math.pow(reshaped.stream()
            .map(value -> (float)Math.pow(value.abs(), 0.5))
            .reduce((float)0., (acc, item) -> acc + item), 2);
        assertTrue(Math.abs(norm - reshaped.norm(0.5)) / norm < 5e-6);
    }

    @Test
    void testPNorm() {
        double norm = (float)Math.pow(reshaped.stream()
            .map(value -> (float)Math.pow(value.abs(), 3.5))
            .reduce((float)0., (acc, item) -> acc + item), 1 / 3.5);
        assertTrue(Math.abs(norm - reshaped.norm(3.5)) / norm < 1e-6);
    }

    @Test
    void testInfNorm() {
        double norm = (float)reshaped.stream()
            .mapToDouble(value -> value.abs())
            .max().getAsDouble();
        assertEquals(norm, reshaped.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testCopy() {
        ComplexNDArray<Float> array2 = reshaped.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(reshaped.get(i), array2.get(i));
        array2.set(new Complex(0,0), 5);
        assertNotEquals(reshaped.get(5), array2.get(5));
    }

    @Test
    void testFillComplex() {
        reshaped.fill(new Complex(3,3));
        for (Complex elem : reshaped)
            assertEquals(new Complex(3, 3), elem);
        for (Complex elem : array)
            assertEquals(new Complex(3, 3), elem);
    }

    @Test
    void testFillReal() {
        reshaped.fill(3);
        for (Complex elem : reshaped)
            assertEquals(new Complex(3, 0), elem);
        for (Complex elem : array)
            assertEquals(new Complex(3, 0), elem);
    }

    @Test
    void testPermuteDimsTooShortPermutationVector() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(0,2));
        assertEquals(
            String.format(Errors.PERMUTATOR_SIZE_MISMATCH, "[0, 2]", "5 × 4 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsTooLongPermutationVector() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(0,2,1,4));
        assertEquals(
            String.format(Errors.PERMUTATOR_SIZE_MISMATCH, "[0, 2, 1, 4]", "5 × 4 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsRepeatedDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(1,1));
        assertEquals(
            String.format(Errors.INVALID_PERMUTATOR, "[1, 1]", "20 × 3"),
            exception.getMessage());
    }

    @Test
    void testMaskReshaped() {
        NDArray<Byte> mask = new RealInt8NDArray(reshaped.abs().map(value -> value > 20 ? (float)1 : (float)0));
        ComplexNDArray<Float> masked = reshaped.mask(mask);
        masked.forEach((value) -> assertTrue(value.abs() > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(value.abs() <= 20));
    }

    @Test
    void testMaskReshapedWithPredicate() {
        ComplexNDArray<Float> masked = reshaped.mask(value -> value.abs() > 20);
        masked.forEach((value) -> assertTrue(value.abs() > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(value.abs() <= 20));
    }

    @Test
    void testMaskReshapedWithPredicateWithLinearIndices() {
        ComplexNDArray<Float> masked = reshaped.maskWithLinearIndices((value, i) -> value.abs() > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(value.abs() > 20 && i < 10));
        masked.fill(0);
        reshaped.forEachWithLinearIndices((value, i) -> assertTrue(value.abs() <= 20 || i >= 10));
    }

    @Test
    void testMaskReshapedWithPredicateWithCartesianIndices() {
        ComplexNDArray<Float> masked = reshaped.maskWithCartesianIndices((value, idx) -> value.abs() > 20 && idx[0] == 0);
        masked.forEach(value -> assertTrue(value.abs() > 20));
        masked.fill(0);
        reshaped.forEachWithCartesianIndices((value, idx) -> assertTrue(value.abs() <= 20 || idx[0] != 0));
    }

    @Test
    void testConcatenate() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(new int[]{5, 3}).fill(1);
        ComplexNDArray<Float> array3 = reshaped.concatenate(0, array2);
        for (int i = 0; i < reshaped.dims(0); i++)
            for (int j = 0; j < reshaped.dims(1); j++)
                assertEquals(reshaped.get(i, j), array3.get(i, j));
        for (int i = reshaped.dims(0); i < reshaped.dims(0) + array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(new Complex(1, 0), array3.get(i, j));
    }

    @Test
    void testConcatenateMultiple() {
        ComplexNDArray<Float> array2 = reshaped.copy().fill(1).slice("1:5", ":");
        ComplexNDArray<Float> array3 = new ComplexF32NDArray(new int[]{3, 2}).permuteDims(1, 0);
        ComplexNDArray<Float> array4 = new ComplexF32NDArray(new int[]{9}).fill(new Complex(2, -2)).reshape(3, 3);
        ComplexNDArray<Float> array5 = reshaped.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = reshaped.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.dims(1); j++)
                assertEquals(reshaped.get(i, j), array5.get(i, j));
        start = end;
        end += array2.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(new Complex(1, 0), array5.get(i, j));
        start = end;
        end += array3.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(new Complex(0, 0), array5.get(i, j));
        start = end;
        end += array4.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(new Complex(2, -2), array5.get(i, j));
    }

    @Test
    void testReal() {
        NDArray<Float> real = reshaped.real();
        reshaped.streamLinearIndices()
            .forEach(i -> assertEquals(reshaped.get(i).getReal(), real.get(i).doubleValue()));
    }

    @Test
    void testImag() {
        NDArray<Float> imag = reshaped.imaginary();
        reshaped.streamLinearIndices()
            .forEach(i -> assertEquals(reshaped.get(i).getImaginary(), imag.get(i).doubleValue()));
    }

    @Test
    void testAbs() {
        NDArray<Float> abs = reshaped.abs();
        reshaped.streamLinearIndices()
            .forEach(i -> assertTrue(reshaped.get(i).abs() - abs.get(i) < 1e-5));
    }

    @Test
    void testAngle() {
        NDArray<Float> angle = reshaped.angle();
        reshaped.streamLinearIndices()
            .forEach(i -> assertTrue(reshaped.get(i).getArgument() - angle.get(i) < 1e-5));
    }
}
