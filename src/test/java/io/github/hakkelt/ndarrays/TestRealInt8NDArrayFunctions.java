package io.github.hakkelt.ndarrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRealInt8NDArrayFunctions {
    RealInt8NDArray array;

    @BeforeEach
    void setup() {
        array = new RealInt8NDArray(new int[]{ 4, 5, 3 });
        array.applyWithLinearIndex((value, index) -> index.byteValue());
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals((byte)55, array.get(-5));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals((byte)linearIndex, array.get(2, -3, -1));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals((byte)linearIndex, array.get(2, -3, -1));
        array.set(1, linearIndex);
        assertEquals((byte)1, array.get(2, -3, -1));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        array.set(1, 2, -3, -1);
        assertEquals((byte)1, array.get(linearIndex));
    }

    @Test
    void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(-61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1,1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1,-6,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(0, 60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(0, -61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(0, 1,1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(0, 1,-6,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> array.set(0, 1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> array.set(0, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Byte.class, array.eltype());
    }

    @Test
    void testToArray() {
        Byte[][][] arr = (Byte[][][])array.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0][0].length; i++)
            for (int j = 0; j < arr[0].length; j++)
                for (int k = 0; k < arr.length; k++) {
                    assertEquals((byte)linearIndex, arr[k][j][i]);
                    linearIndex++;
                }
    }

    @Test
    void testEqual() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        assertEquals(array, array2);
        array2.set(0, 10);
        assertNotEquals(array, array2);
    }

    @Test
    void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { array.hashCode(); });
    }

    @Test
    void testIterator() {
        int linearIndex = 0;
        for (Byte value : array) {
            assertEquals((byte)linearIndex, value);
            linearIndex++;
        }
    }

    @Test
    void testStream() {
        final int[] linearIndex = new int[1];
        array.stream().forEach((value) -> {
            assertEquals((byte)linearIndex[0], value);
            linearIndex[0]++;
        });
    }

    @Test
    void testParallelStream() {
        Byte sum = array.stream().parallel()
            .reduce((byte)0, (acc, item) -> (byte)(acc + item));
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals((byte)GaussSum, sum);
    }

    @Test
    void testCollector() {
        NDArray<?> increased = array.stream()
            .map((value) -> value + 1)
            .collect(RealInt8NDArray.getCollector(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) + 1), increased.get(i));
    }

    @Test
    void testParallelCollector() {
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value + 1)
            .collect(RealInt8NDArray.getCollector(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) + 1), increased.get(i));
    }

    @Test
    void testToString() {
        String str = array.toString();
        assertEquals("simple NDArray<Byte>(4 × 5 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = array.contentToString();
        String lineFormat = "%6d\t%6d\t%6d\t%6d\t%6d\t%n";
        String expected = new StringBuilder()
            .append("simple NDArray<Byte>(4 × 5 × 3)" + System.lineSeparator())
            .append("[:, :, 0] =" + System.lineSeparator())
            .append(String.format(lineFormat, 0, 4, 8, 12, 16))
            .append(String.format(lineFormat, 1, 5, 9, 13, 17))
            .append(String.format(lineFormat, 2, 6, 10, 14, 18))
            .append(String.format(lineFormat, 3, 7, 11, 15, 19))
            .append(System.lineSeparator())
            .append("[:, :, 1] =" + System.lineSeparator())
            .append(String.format(lineFormat, 20, 24, 28, 32, 36))
            .append(String.format(lineFormat, 21, 25, 29, 33, 37))
            .append(String.format(lineFormat, 22, 26, 30, 34, 38))
            .append(String.format(lineFormat, 23, 27, 31, 35, 39))
            .append(System.lineSeparator())
            .append("[:, :, 2] =" + System.lineSeparator())
            .append(String.format(lineFormat, 40, 44, 48, 52, 56))
            .append(String.format(lineFormat, 41, 45, 49, 53, 57))
            .append(String.format(lineFormat, 42, 46, 50, 54, 58))
            .append(String.format(lineFormat, 43, 47, 51, 55, 59))
            .append(System.lineSeparator())
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testAdd() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        NDArray<Byte> array3 = array.add(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Byte> array2 = array.add(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) + 5), array2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        NDArray<Byte> array3 = array.add(array2, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            double expected = array.get(i) * 3 + 5.3 + 3;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        array2.addInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) * 2), array2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        array2.addInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) + 5), array2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        array2.addInplace(array, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            double expected = array.get(i) * 3 + 5.3 + 3;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void testSubtract() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        NDArray<Byte> array3 = array.subtract(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)0, array3.get(i));
    }

    @Test
    void testSubtractScalar() {
        NDArray<Byte> array2 = array.subtract(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) - 5), array2.get(i));
    }

    @Test
    void testSubtractMultiple() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        NDArray<Byte> array3 = array.subtract(array2, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            float expected = array.get(i) * -1.f - 5.3f - 3.f;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testSubtractInplace() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        array2.subtractInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)0, array2.get(i));
    }

    @Test
    void testSubtractInplaceScalar() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        array2.subtractInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) - 5), array2.get(i));
    }

    @Test
    void testSubtractInplaceMultiple() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        array2.subtractInplace(array, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            float expected = array.get(i) * -1.f - 5.3f - 3.f;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void testMultiply() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        NDArray<Byte> array3 = array.multiply(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) * array.get(i)), array3.get(i));
    }

    @Test
    void testMultiplyScalar() {
        NDArray<Byte> array2 = array.multiply(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) * 5), array2.get(i));
    }

    @Test
    void testMultiplyMultiple() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        NDArray<Byte> array3 = array.multiply(array, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            float expected = array.get(i) * array.get(i) * 5.3f *
                array2.get(i) * 3.f;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e7);
        }
    }

    @Test
    void testMultiplyInplace() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        array2.multiplyInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) * array.get(i)), array2.get(i));
    }

    @Test
    void testMultiplyInplaceScalar() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        array2.multiplyInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) * 5), array2.get(i));
    }

    @Test
    void testMultiplyInplaceMultiple() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        array2.multiplyInplace(array, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            float expected = array.get(i) * array.get(i) * 5.3f *
                array2.get(i) * 3.f;
            if (expected == 0)
                assertTrue(Math.abs(expected - array2.get(i)) < 1e7);
            else
                assertTrue(Math.abs(expected - array2.get(i)) / Math.abs(expected) < 1e7);
        }
    }

    @Test
    void testDivide() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        NDArray<Byte> array3 = array.add(1).divide(array2.add(1));
        for (int i = 0; i < array.length(); i++) {
            assertEquals((byte)1, array3.get(i));
        }
    }

    @Test
    void testDivideScalar() {
        NDArray<Byte> array2 = array.divide(5);
        for (int i = 0; i < array.length(); i++)
            assertTrue(Math.abs(array.get(i) / 5 - array2.get(i)) < 1e-5);
    }

    @Test
    void testDivideMultiple() {
        NDArray<Byte> array2 = new RealInt8NDArray(array.addInplace(1));
        NDArray<Byte> array3 = array.divide(array, 5, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            Byte expected = (byte)(array.get(i) / array.get(i) / 5 / array2.get(i) / 3);
            assertTrue(Math.abs(expected - array3.get(i)) < 1e7);
        }
    }

    @Test
    void testDivideInplace() {
        NDArray<Byte> array2 = new RealInt8NDArray(array.addInplace(1));
        array2.divideInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)1, array2.get(i));
    }

    @Test
    void testDivideInplaceScalar() {
        NDArray<Byte> array2 = new RealInt8NDArray(array);
        array2.divideInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertTrue(Math.abs(array.get(i) / 5 - array2.get(i)) < 1e-5);
    }

    @Test
    void testDivideInplaceMultiple() {
        NDArray<Byte> array2 = new RealInt8NDArray(array.addInplace(1));
        array2.divideInplace(array, 5, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            Byte expected = (byte)(array.get(i) / array.get(i) / 5 / array.get(i) / 3);
            assertTrue(Math.abs(expected - array2.get(i)) < 1e7);
        }
    }

    @Test
    void testSum() {
        Byte sum = array.sum();
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals((byte)GaussSum, sum);
    }

    @Test
    void testSum1D() {
        NDArray<Byte> sum = array.sum(1);
        for (int i = 0; i < sum.dims(0); i++) {
            for (int j = 0; j < sum.dims(1); j++) {
                float GaussSum = (array.get(i,0,j) + array.get(i,-1,j)) * (float)array.dims(1) / 2.f;
                assertEquals((byte)GaussSum, sum.get(i,j));
            }
        }
    }

    @Test
    void testSum2D() {
        NDArray<Byte> sum = array.sum(2, 1);
        for (int i = 0; i < sum.length(); i++) {
            float GaussSum = (array.get(i,0,0) + array.get(i,-1,-1)) * 15.f / 2.f;
            assertEquals((byte)GaussSum, sum.get(i));
        }
    }

    @Test
    void test0Norm() {
        array.slice(":", 0, ":").fill(0);
        double norm = array.stream()
            .filter(value -> value != 0.)
            .count();
        assertEquals(norm, array.norm(0));
    }

    @Test
    void test1Norm() {
        double norm = array.stream()
            .mapToDouble(value -> Math.abs(value))
            .reduce(0., (acc, item) -> acc + item);
        assertTrue(Math.abs((byte)norm - array.norm(1)) / (byte)norm < 1e-6);
    }

    @Test
    void test2Norm() {
        double norm = (float)Math.sqrt(array.stream()
            .map(value -> (float)Math.pow(Math.abs(value), 2))
            .reduce((float)0., (acc, item) -> acc + item));
        assertTrue(Math.abs(norm - array.norm()) / norm < 1e-6);
    }

    @Test
    void testPQuasinorm() {
        double norm = (float)Math.pow(array.stream()
            .map(value -> (float)Math.pow(Math.abs(value), 0.5))
            .reduce((float)0., (acc, item) -> acc + item), 2);
        assertTrue(Math.abs(norm - array.norm(0.5)) / norm < 5e-6);
    }

    @Test
    void testPNorm() {
        double norm = (float)Math.pow(array.stream()
            .map(value -> (float)Math.pow(Math.abs(value), 3.5))
            .reduce((float)0., (acc, item) -> acc + item), 1 / 3.5);
        assertTrue(Math.abs(norm - array.norm(3.5)) / norm < 5e-6);
    }

    @Test
    void testInfNorm() {
        double norm = (float)array.stream()
            .mapToDouble(value -> Math.abs(value))
            .max().getAsDouble();
        assertEquals(norm, array.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testCopy() {
        NDArray<Byte> array2 = array.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(array.get(5), array2.get(5));
    }

    @Test
    void testFillFloat() {
        array.fill(3);
        for (Byte elem : array)
            assertEquals((byte)3, elem);
    }

    @Test
    void testFillReal() {
        array.fill(3);
        for (Byte elem : array)
            assertEquals((byte)3, elem);
    }

    @Test
    void testPermuteDimsTooShortPermutationVector() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,2));
        assertEquals(
            String.format(AbstractNDArrayPermuteDimsView.ERROR_PERMUTATOR_SIZE_MISMATCH, "[0, 2]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsTooLongPermutationVector() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,2,1,4));
        assertEquals(
            String.format(AbstractNDArrayPermuteDimsView.ERROR_PERMUTATOR_SIZE_MISMATCH, "[0, 2, 1, 4]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsRepeatedDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,1,1));
        assertEquals(
            String.format(AbstractNDArrayPermuteDimsView.ERROR_INVALID_PERMUTATOR, "[0, 1, 1]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    void testConcatenate() {
        NDArray<Byte> array2 = new RealInt8NDArray(new int[]{4, 2, 3}).fill(1);
        NDArray<Byte> array3 = array.concatenate(1, array2);
        for (int i = 0; i < array.dims(0); i++)
            for (int j = 0; j < array.dims(1); j++)
                for (int k = 0; k < array.dims(2); k++)
                    assertEquals(array.get(i, j, k), array3.get(i, j, k));
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = array.dims(1); j < array.dims(1) + array2.dims(1); j++)
                for (int k = 0; k < array2.dims(2); k++)
                    assertEquals((byte)1, array3.get(i, j, k));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Byte> array2 = array.copy().fill(1).slice(":", "1:3", ":");
        NDArray<Byte> array3 = new RealInt8NDArray(new int[]{3, 4, 4}).permuteDims(2, 1, 0);
        NDArray<Byte> array4 = new RealInt8NDArray(new int[]{12}).fill(2).reshape(4, 1, 3);
        NDArray<Byte> array5 = array.concatenate(1, array2, array3, array4);
        int start = 0;
        int end = array.dims(1);
        for (int i = 0; i < array.dims(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array.dims(2); k++)
                    assertEquals(array.get(i, j, k), array5.get(i, j, k));
        start = end;
        end += array2.dims(1);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.dims(2); k++)
                    assertEquals((byte)1, array5.get(i, j, k));
        start = end;
        end += array3.dims(1);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.dims(2); k++)
                    assertEquals((byte)0, array5.get(i, j, k));
        start = end;
        end += array4.dims(1);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.dims(2); k++)
                    assertEquals((byte)2, array5.get(i, j, k));
    }

    @Test
    void testAbs() {
        NDArray<Byte> abs = array.abs();
        array.streamLinearIndices()
            .forEach(i -> assertTrue(Math.abs(array.get(i)) - abs.get(i) < 1e-5));
    }
}
