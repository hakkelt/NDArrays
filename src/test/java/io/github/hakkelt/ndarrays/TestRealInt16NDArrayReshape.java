package io.github.hakkelt.ndarrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRealInt16NDArrayReshape {
    NDArray<Short> array, reshaped;

    @BeforeEach
    void setup() {
        array = new RealInt16NDArray(new int[]{ 4, 5, 3 });
        array.applyWithLinearIndex((value, index) -> index.shortValue());
        reshaped = array.reshape(20, 3);
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals((short)55, reshaped.get(-5));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in parent
        assertEquals((short)linearIndex, reshaped.get(10, -1));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals((short)linearIndex, reshaped.get(10, -1));
        reshaped.set(1, linearIndex);
        assertEquals((short)1, reshaped.get(10, -1));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        reshaped.set(1, 10, -1);
        assertEquals((short)1, reshaped.get(linearIndex));
    }

    @Test
    void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, reshaped.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(-61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, reshaped.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "20 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(-21,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "20 × 3", "[-21, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(0, 60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, reshaped.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(0, -61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, reshaped.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(0, 1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "20 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(0, -21,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "20 × 3", "[-21, 1]"),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooMany() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.get(1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnough() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.get(1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooMany() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> reshaped.set(0, 1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnough() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> reshaped.set(0, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Short.class, reshaped.eltype());
    }

    @Test
    void testToArray() {
        Short[][] arr = (Short[][])reshaped.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr.length; j++) {
                assertEquals((short)linearIndex, arr[j][i]);
                linearIndex++;
            }
    }

    @Test
    void testEqual() {
        NDArray<Short> array2 = new RealInt16NDArray(reshaped);
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
        for (Short value : reshaped) {
            assertEquals((short)linearIndex, value);
            linearIndex++;
        }
    }

    @Test
    void testStream() {
        final int[] linearIndex = new int[1];
        reshaped.stream().forEach((value) -> {
            assertEquals((short)linearIndex[0], value);
            linearIndex[0]++;
        });
    }

    @Test
    void testParallelStream() {
        Short sum = reshaped.stream().parallel()
            .reduce((short)0, (acc, item) -> (short)(acc + item));
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals((short)GaussSum, sum);
    }

    @Test
    void testCollector() {
        NDArray<?> increased = reshaped.stream()
            .map((value) -> value + 1)
            .collect(RealInt16NDArray.getCollector(reshaped.dims()));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((short)(reshaped.get(i) + 1), increased.get(i));
    }

    @Test
    void testParallelCollector() {
        NDArray<?> increased = reshaped.stream().parallel()
            .map((value) -> value + 1)
            .collect(RealInt16NDArray.getCollector(reshaped.dims()));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((short)(reshaped.get(i) + 1), increased.get(i));
    }

    @Test
    void testToString() {
        String str = reshaped.toString();
        assertEquals("simple NDArray<Short>(20 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = reshaped.contentToString();
        String lineFormat = "%6d\t%6d\t%6d\t%n";
        String expected = new StringBuilder()
            .append("simple NDArray<Short>(20 × 3)" + System.lineSeparator())
            .append(String.format(lineFormat, 0, 20, 40))
            .append(String.format(lineFormat, 1, 21, 41))
            .append(String.format(lineFormat, 2, 22, 42))
            .append(String.format(lineFormat, 3, 23, 43))
            .append(String.format(lineFormat, 4, 24, 44))
            .append(String.format(lineFormat, 5, 25, 45))
            .append(String.format(lineFormat, 6, 26, 46))
            .append(String.format(lineFormat, 7, 27, 47))
            .append(String.format(lineFormat, 8, 28, 48))
            .append(String.format(lineFormat, 9, 29, 49))
            .append(String.format(lineFormat, 10, 30, 50))
            .append(String.format(lineFormat, 11, 31, 51))
            .append(String.format(lineFormat, 12, 32, 52))
            .append(String.format(lineFormat, 13, 33, 53))
            .append(String.format(lineFormat, 14, 34, 54))
            .append(String.format(lineFormat, 15, 35, 55))
            .append(String.format(lineFormat, 16, 36, 56))
            .append(String.format(lineFormat, 17, 37, 57))
            .append(String.format(lineFormat, 18, 38, 58))
            .append(String.format(lineFormat, 19, 39, 59))        
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testAdd() {
        NDArray<Short> array2 = new RealInt16NDArray(reshaped);
        NDArray<Short> array3 = reshaped.add(array2);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((short)(reshaped.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Short> array2 = reshaped.add(5);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((short)(reshaped.get(i) + 5), array2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Short> array2 = new RealInt16NDArray(reshaped);
        NDArray<Short> array3 = reshaped.add(array2, 5.3, array2, 3);
        for (int i = 0; i < reshaped.length(); i++) {
            float expected = reshaped.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Short> array2 = new RealInt16NDArray(reshaped);
        array2.addInplace(reshaped);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((short)(reshaped.get(i) * 2), array2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Short> array2 = new RealInt16NDArray(reshaped);
        array2.addInplace(5);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((short)(reshaped.get(i) + 5), array2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Short> array2 = new RealInt16NDArray(reshaped);
        array2.addInplace(reshaped, 5.3, array2, 3);
        for (int i = 0; i < reshaped.length(); i++) {
            float expected = reshaped.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void testSum() {
        Short sum = reshaped.sum();
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals((short)GaussSum, sum);
    }

    @Test
    void testSum1D() {
        NDArray<Short> sum = reshaped.sum(1);
        for (int i = 0; i < sum.dims(0); i++) {
            float GaussSum = (reshaped.get(i,0) + reshaped.get(i,-1)) * 3 / 2;
            assertEquals((short)GaussSum, sum.get(i));
        }
    }

    @Test
    void testSum2D() {
        reshaped = array.reshape(5,4,3);
        NDArray<Short> sum = reshaped.sum(2, 1);
        for (int i = 0; i < sum.length(); i++) {
            float acc = 0;
            for (int j = 0; j < reshaped.dims(1); j++)
                for (int k = 0; k < reshaped.dims(2); k++)
                    acc = acc + reshaped.get(i,j,k);
            assertEquals((short)acc, sum.get(i));
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
            .reduce((short)0, (acc, item) -> (short)(acc + item));
        assertTrue(Math.abs(norm - reshaped.norm(1)) / norm < 1e-6);
    }

    @Test
    void test2Norm() {
        double norm = (float)Math.sqrt(reshaped.stream()
            .map(value -> (float)Math.pow(Math.abs(value), 2))
            .reduce((float)0., (acc, item) -> acc + item));
        assertTrue(Math.abs(norm - reshaped.norm()) / norm < 1e-6);
    }

    @Test
    void testPQuasinorm() {
        double norm = (float)Math.pow(reshaped.stream()
            .map(value -> (float)Math.pow(Math.abs(value), 0.5))
            .reduce((float)0., (acc, item) -> acc + item), 2);
        assertTrue(Math.abs(norm - reshaped.norm(0.5)) / norm < 5e-6);
    }

    @Test
    void testPNorm() {
        double norm = (float)Math.pow(reshaped.stream()
            .map(value -> (float)Math.pow((float)Math.abs(value), 3.5))
            .reduce((float)0., (acc, item) -> acc + item), (float)1 / (float)3.5);
        assertTrue(Math.abs(norm - reshaped.norm(3.5)) / norm < 5e-6);
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
        NDArray<Short> array2 = reshaped.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(reshaped.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(reshaped.get(5), array2.get(5));
    }

    @Test
    void testFillFloat() {
        reshaped.fill(3);
        for (Short elem : reshaped)
            assertEquals((short)3, elem);
        for (Short elem : array)
            assertEquals((short)3, elem);
    }

    @Test
    void testFillReal() {
        reshaped.fill(3);
        for (Short elem : reshaped)
            assertEquals((short)3, elem);
        for (Short elem : array)
            assertEquals((short)3, elem);
    }

    @Test
    void testPermuteDimsTooShortPermutationVector() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(0,2));
        assertEquals(
            String.format(AbstractNDArrayPermuteDimsView.ERROR_PERMUTATOR_SIZE_MISMATCH, "[0, 2]", "5 × 4 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsTooLongPermutationVector() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(0,2,1,4));
        assertEquals(
            String.format(AbstractNDArrayPermuteDimsView.ERROR_PERMUTATOR_SIZE_MISMATCH, "[0, 2, 1, 4]", "5 × 4 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsRepeatedDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(1,1));
        assertEquals(
            String.format(AbstractNDArrayPermuteDimsView.ERROR_INVALID_PERMUTATOR, "[1, 1]", "20 × 3"),
            exception.getMessage());
    }

    @Test
    void testConcatenate() {
        NDArray<Short> array2 = new RealInt16NDArray(new int[]{5, 3}).fill(1);
        NDArray<Short> array3 = reshaped.concatenate(0, array2);
        for (int i = 0; i < reshaped.dims(0); i++)
            for (int j = 0; j < reshaped.dims(1); j++)
                assertEquals(reshaped.get(i, j), array3.get(i, j));
        for (int i = reshaped.dims(0); i < reshaped.dims(0) + array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals((short)1, array3.get(i, j));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Short> array2 = reshaped.copy().fill(1).slice("1:5", ":");
        NDArray<Short> array3 = new RealInt16NDArray(new int[]{3, 2}).permuteDims(1, 0);
        NDArray<Short> array4 = new RealInt16NDArray(new int[]{9}).fill(2).reshape(3, 3);
        NDArray<Short> array5 = reshaped.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = reshaped.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.dims(1); j++)
                assertEquals(reshaped.get(i, j), array5.get(i, j));
        start = end;
        end += array2.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals((short)1, array5.get(i, j));
        start = end;
        end += array3.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals((short)0, array5.get(i, j));
        start = end;
        end += array4.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals((short)2, array5.get(i, j));
    }
}
