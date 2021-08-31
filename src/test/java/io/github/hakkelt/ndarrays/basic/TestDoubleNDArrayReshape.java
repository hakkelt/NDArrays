package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.Errors;
import io.github.hakkelt.ndarrays.ByteNDArrayConstructorTrait;
import io.github.hakkelt.ndarrays.DoubleNDArrayConstructorTrait;
import io.github.hakkelt.ndarrays.NDArray;

class TestDoubleNDArrayReshape implements DoubleNDArrayConstructorTrait, ByteNDArrayConstructorTrait, ConstructorTrait {
    NDArray<Double> array, reshaped;

    @BeforeEach
    void setup() {
        array = createDoubleNDArray(new int[]{ 4, 5, 3 });
        array.applyWithLinearIndices((value, index) -> (double)index);
        reshaped = array.reshape(20, 3);
    }

    @Test
    void testReshapeReshaped() {
        NDArray<Double> reshaped2 = reshaped.reshape(4, 5, 3);
        array.forEachWithCartesianIndices((value, indices) -> assertEquals(value, reshaped2.get(indices)));
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals(55, reshaped.get(-5));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in parent
        assertEquals(linearIndex, reshaped.get(10, -1));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals(linearIndex, reshaped.get(10, -1));
        reshaped.set(1, linearIndex);
        assertEquals(1, reshaped.get(10, -1));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        reshaped.set(1, 10, -1);
        assertEquals(1, reshaped.get(linearIndex));
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
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(0, 60));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, reshaped.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(0, -61));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, reshaped.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(0, 1,3));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "20 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(0, -21,1));
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
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> reshaped.set(0, 1,1,1,0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnough() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> reshaped.set(0, 1,1));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Double.class, reshaped.eltype());
    }

    @Test
    void testToArray() {
        Double[][] arr = (Double[][])reshaped.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr.length; j++) {
                assertEquals(linearIndex, arr[j][i]);
                linearIndex++;
            }
    }

    @Test
    void testEqual() {
        NDArray<Double> array2 = createDoubleNDArray(reshaped);
        assertEquals(reshaped, array2);
        array2.set(0, 10);
        assertNotEquals(reshaped, array2);
    }

    @Test
    void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { reshaped.hashCode(); });
    }

    @Test
    void testIterator() {
        int linearIndex = 0;
        for (Double value : reshaped) {
            assertEquals(linearIndex, value);
            linearIndex++;
        }
    }

    @Test
    void testStream() {
        final int[] linearIndex = new int[1];
        reshaped.stream().forEach((value) -> {
            assertEquals(linearIndex[0], value);
            linearIndex[0]++;
        });
    }

    @Test
    void testParallelStream() {
        Double sum = reshaped.stream().parallel()
            .reduce(0., (acc, item) -> acc + item);
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(GaussSum, sum);
    }

    @Test
    void testCollector() {
        NDArray<?> increased = reshaped.stream()
            .map((value) -> value + 1)
            .collect(getDoubleNDArrayCollector(reshaped.dims()));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i) + 1, increased.get(i));
    }

    @Test
    void testParallelCollector() {
        NDArray<?> increased = reshaped.stream().parallel()
            .map((value) -> value + 1)
            .collect(getDoubleNDArrayCollector(reshaped.dims()));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i) + 1, increased.get(i));
    }

    @Test
    void testToString() {
        String str = reshaped.toString();
        assertEquals("basic NDArray<Double>(20 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = reshaped.contentToString();
        String lineFormat = "%8.5e\t%8.5e\t%8.5e\t%n";
        String expected = new StringBuilder()
            .append("basic NDArray<Double>(20 × 3)" + System.lineSeparator())
            .append(String.format(lineFormat, 0.00000e+00, 2.00000e+01, 4.00000e+01))
            .append(String.format(lineFormat, 1.00000e+00, 2.10000e+01, 4.10000e+01))
            .append(String.format(lineFormat, 2.00000e+00, 2.20000e+01, 4.20000e+01))
            .append(String.format(lineFormat, 3.00000e+00, 2.30000e+01, 4.30000e+01))
            .append(String.format(lineFormat, 4.00000e+00, 2.40000e+01, 4.40000e+01))
            .append(String.format(lineFormat, 5.00000e+00, 2.50000e+01, 4.50000e+01))
            .append(String.format(lineFormat, 6.00000e+00, 2.60000e+01, 4.60000e+01))
            .append(String.format(lineFormat, 7.00000e+00, 2.70000e+01, 4.70000e+01))
            .append(String.format(lineFormat, 8.00000e+00, 2.80000e+01, 4.80000e+01))
            .append(String.format(lineFormat, 9.00000e+00, 2.90000e+01, 4.90000e+01))
            .append(String.format(lineFormat, 1.00000e+01, 3.00000e+01, 5.00000e+01))
            .append(String.format(lineFormat, 1.10000e+01, 3.10000e+01, 5.10000e+01))
            .append(String.format(lineFormat, 1.20000e+01, 3.20000e+01, 5.20000e+01))
            .append(String.format(lineFormat, 1.30000e+01, 3.30000e+01, 5.30000e+01))
            .append(String.format(lineFormat, 1.40000e+01, 3.40000e+01, 5.40000e+01))
            .append(String.format(lineFormat, 1.50000e+01, 3.50000e+01, 5.50000e+01))
            .append(String.format(lineFormat, 1.60000e+01, 3.60000e+01, 5.60000e+01))
            .append(String.format(lineFormat, 1.70000e+01, 3.70000e+01, 5.70000e+01))
            .append(String.format(lineFormat, 1.80000e+01, 3.80000e+01, 5.80000e+01))
            .append(String.format(lineFormat, 1.90000e+01, 3.90000e+01, 5.90000e+01))        
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testAdd() {
        NDArray<Double> array2 = createDoubleNDArray(reshaped);
        NDArray<Double> array3 = reshaped.add(array2);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i) * 2, array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Double> array2 = reshaped.add(5);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i) + 5, array2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Double> array2 = createDoubleNDArray(reshaped);
        NDArray<Double> array3 = reshaped.add(array2, 5.3, array2, 3);
        for (int i = 0; i < reshaped.length(); i++) {
            double expected = reshaped.get(i) * 3. + 5.3 + 3.;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Double> array2 = createDoubleNDArray(reshaped);
        array2.addInplace(reshaped);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i) * 2, array2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Double> array2 = createDoubleNDArray(reshaped);
        array2.addInplace(5);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i) + 5, array2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Double> array2 = createDoubleNDArray(reshaped);
        array2.addInplace(reshaped, 5.3, array2, 3);
        for (int i = 0; i < reshaped.length(); i++) {
            double expected = reshaped.get(i) * 3. + 5.3f + 3.;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void testSum() {
        Double sum = reshaped.sum();
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(GaussSum, sum);
    }

    @Test
    void testSum1D() {
        NDArray<Double> sum = reshaped.sum(1);
        for (int i = 0; i < sum.dims(0); i++) {
            double GaussSum = (reshaped.get(i,0) + reshaped.get(i,-1)) * 3 / 2;
            assertEquals(GaussSum, sum.get(i));
        }
    }

    @Test
    void testSum2D() {
        reshaped = array.reshape(5,4,3);
        NDArray<Double> sum = reshaped.sum(2, 1);
        for (int i = 0; i < sum.length(); i++) {
            double acc = 0;
            for (int j = 0; j < reshaped.dims(1); j++)
                for (int k = 0; k < reshaped.dims(2); k++)
                    acc = acc + reshaped.get(i,j,k);
            assertEquals(acc, sum.get(i));
        }
    }

    @Test
    void test0Norm() {
        reshaped.slice(":", 0).fill(0);
        double norm = reshaped.stream()
            .filter(value -> value != 0.)
            .count();
        assertEquals(norm, reshaped.norm(0));
    }

    @Test
    void test1Norm() {
        double norm = reshaped.stream()
            .mapToDouble(value -> Math.abs(value))
            .reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, reshaped.norm(1));
    }

    @Test
    void test2Norm() {
        double norm = Math.sqrt(reshaped.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 2))
            .reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, reshaped.norm());
    }

    @Test
    void testPQuasinorm() {
        double norm = Math.pow(reshaped.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 0.5))
            .reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, reshaped.norm(0.5));
    }

    @Test
    void testPNorm() {
        double norm = Math.pow(reshaped.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 3.5))
            .reduce(0., (acc, item) -> acc + item), 1 / 3.5);
        assertEquals(norm, reshaped.norm(3.5));
    }

    @Test
    void testInfNorm() {
        double norm = reshaped.stream()
            .mapToDouble(value -> Math.abs(value))
            .max().getAsDouble();
        assertEquals(norm, reshaped.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testCopy() {
        NDArray<Double> array2 = reshaped.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(reshaped.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(reshaped.get(5), array2.get(5));
    }

    @Test
    void testFillDouble() {
        reshaped.fill(3);
        for (Double elem : reshaped)
            assertEquals(3, elem);
        for (Double elem : array)
            assertEquals(3, elem);
    }

    @Test
    void testFillReal() {
        reshaped.fill(3);
        for (Double elem : reshaped)
            assertEquals(3, elem);
        for (Double elem : array)
            assertEquals(3, elem);
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
        NDArray<Byte> mask = createByteNDArray(reshaped.map(value -> value > 20 ? (double)1 : (double)0));
        NDArray<Double> masked = reshaped.mask(mask);
        masked.forEach((value) -> assertTrue(value > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(value <= 20));
    }

    @Test
    void testMaskReshapedWithPredicate() {
        NDArray<Double> masked = reshaped.mask(value -> value > 20);
        masked.forEach((value) -> assertTrue(value > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(value <= 20));
    }

    @Test
    void testMaskReshapedWithPredicateWithLinearIndices() {
        NDArray<Double> masked = reshaped.maskWithLinearIndices((value, i) -> value > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(value > 20 && i < 10));
        masked.fill(0);
        reshaped.forEachWithLinearIndices((value, i) -> assertTrue(value <= 20 || i >= 10));
    }

    @Test
    void testMaskReshapedWithPredicateWithCartesianIndices() {
        NDArray<Double> masked = reshaped.maskWithCartesianIndices((value, idx) -> value > 20 && idx[0] == 0);
        masked.forEach(value -> assertTrue(value > 20));
        masked.fill(0);
        reshaped.forEachWithCartesianIndices((value, idx) -> assertTrue(value <= 20 || idx[0] != 0));
    }

    @Test
    void testConcatenate() {
        NDArray<Double> array2 = createDoubleNDArray(new int[]{5, 3}).fill(1);
        NDArray<Double> array3 = reshaped.concatenate(0, array2);
        for (int i = 0; i < reshaped.dims(0); i++)
            for (int j = 0; j < reshaped.dims(1); j++)
                assertEquals(reshaped.get(i, j), array3.get(i, j));
        for (int i = reshaped.dims(0); i < reshaped.dims(0) + array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(1, array3.get(i, j));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Double> array2 = reshaped.copy().fill(1).slice("1:5", ":");
        NDArray<Double> array3 = createDoubleNDArray(new int[]{3, 2}).permuteDims(1, 0);
        NDArray<Double> array4 = createDoubleNDArray(new int[]{9}).fill(2).reshape(3, 3);
        NDArray<Double> array5 = reshaped.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = reshaped.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.dims(1); j++)
                assertEquals(reshaped.get(i, j), array5.get(i, j));
        start = end;
        end += array2.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(1, array5.get(i, j));
        start = end;
        end += array3.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(0, array5.get(i, j));
        start = end;
        end += array4.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(2, array5.get(i, j));
    }
    
}
