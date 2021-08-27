package io.github.hakkelt.ndarrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRealInt32NDArraySlice {
    NDArray<Integer> array, slice;

    @BeforeEach
    void setup() {
        array = new RealInt32NDArray(new int[]{ 4, 5, 3 });
        array.applyWithLinearIndex((value, index) -> index.intValue());
        slice = array.slice(1, "1:4", ":");
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals((int)45, slice.get(-3));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int linearIndex = (2 * 5 + (1 + 2)) * 4 + 1;
        assertEquals((int)linearIndex, slice.get(2, -1));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int arrayLinearIndex = (2 * 5 + (1 + 2)) * 4 + 1;
        int viewLinearIndex = 2 * 3 + 2;
        assertEquals((int)arrayLinearIndex, slice.get(2, -1));
        slice.set(1, viewLinearIndex);
        assertEquals((int)1, slice.get(2, -1));
        assertEquals((int)1, array.get(1, 3, 2));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int viewLinearIndex = 2 * 3 + 2;
        array.set(1, 1, -2, -1);
        assertEquals((int)1, slice.get(viewLinearIndex));
    }

    @Test
    void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(10));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, slice.length(), 10),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(-11));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, slice.length(), -11),
            exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "3 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(-4,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "3 × 3", "[-4, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(0, 10));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, slice.length(), 10),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(0, -11));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, slice.length(), -11),
            exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(0, 1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "3 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(0, -4,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "3 × 3", "[-4, 1]"),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.get(1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 3, 2),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnough() {
        NDArray<Integer> slice2 = array.slice("1:4", "1:4", ":");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice2.get(1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> slice.set(0, 1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 3, 2),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnough() {
        NDArray<Integer> slice2 = array.slice("1:4", "1:4", ":");
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> slice2.set(0, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Integer.class, slice.eltype());
    }

    @Test
    void testToArray() {
        Integer[][] arr = (Integer[][])slice.toArray();
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[i][j]);
    }

    @Test
    void testEqual() {
        NDArray<Integer> array2 = new RealInt32NDArray(slice);
        assertEquals(slice, array2);
        array2.set(0, 5);
        assertNotEquals(slice, array2);
    }

    @Test
    void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { slice.hashCode(); });
    }

    @Test
    void testIterator() {
        int linearIndex = 0;
        for (Integer value : slice)
            assertEquals(slice.get(linearIndex++), value);
    }

    @Test
    void testStream() {
        final int[] linearIndex = new int[1];
        slice.stream().forEach((value) -> {
            assertEquals(slice.get(linearIndex[0]++), value);
        });
    }

    @Test
    void testParallelStream() {
        Integer sum = slice.stream().parallel()
            .reduce((int)0, (acc, item) -> (int)(acc + item));
        float acc = 0;
        for (int i = 1; i < array.dims(1) - 1; i++)
            for (int j = 0; j < array.dims(2); j++)
                acc = acc + array.get(1, i, j);
        assertEquals((int)acc, sum);
    }

    @Test
    void testCollector() {
        NDArray<Integer> increased = slice.stream()
            .map((value) -> value + 1)
            .collect(RealInt32NDArray.getCollector(slice.dims()));
        for (int i = 0; i < slice.length(); i++)
            assertEquals((int)(slice.get(i) + 1), increased.get(i));
    }

    @Test
    void testParallelCollector() {
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value + 1)
            .collect(RealInt32NDArray.getCollector(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals((int)(array.get(i) + 1), increased.get(i));
    }

    @Test
    void testToString() {
        String str = slice.toString();
        assertEquals("simple NDArray<Integer>(3 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = slice.contentToString();
        String lineFormat = "%6d\t%6d\t%6d\t%n";
        String expected = new StringBuilder()
            .append("simple NDArray<Integer>(3 × 3)" + System.lineSeparator())
            .append(String.format(lineFormat, 5, 25, 45))
            .append(String.format(lineFormat, 9, 29, 49))
            .append(String.format(lineFormat, 13, 33, 53))
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testAddArrayToSlice() {
        NDArray<Integer> array2 = new RealInt32NDArray(slice);
        NDArray<Integer> array3 = slice.add(array2);
        for (int i = 0; i < slice.length(); i++)
            assertEquals((int)(slice.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddSliceToArray() {
        NDArray<Integer> array2 = new RealInt32NDArray(slice);
        NDArray<Integer> array3 = array2.add(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals((int)(slice.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddSliceToSlice() {
        NDArray<Integer> slice2 = array.slice(1, "1:4", ":");
        NDArray<Integer> array3 = slice2.add(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals((int)(slice.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Integer> slice2 = slice.add(5.);
        for (int i = 0; i < slice.length(); i++)
            assertEquals((int)(slice.get(i) + 5), slice2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Integer> array2 = new RealInt32NDArray(array);
        NDArray<Integer> slice2 = array2.slice(1, "1:4", ":");
        NDArray<Integer> array3 = slice2.add(slice, 5.3, slice2, 3);
        for (int i = 0; i < slice.length(); i++) {
            float expected = slice.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Integer> array2 = new RealInt32NDArray(array);
        NDArray<Integer> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals((int)(slice.get(i) * 2), slice2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Integer> array2 = new RealInt32NDArray(array);
        NDArray<Integer> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(5);
        for (int i = 0; i < slice.length(); i++)
            assertEquals((int)(slice.get(i) + 5), slice2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Integer> array2 = new RealInt32NDArray(array);
        NDArray<Integer> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(slice, 5.3, slice2, 3);
        for (int i = 0; i < slice.length(); i++) {
            float expected = slice.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void test0Norm() {
        slice.slice(":", 0).fill(0);
        double norm = slice.stream()
            .filter(value -> value != 0.)
            .count();
        assertTrue(Math.abs(norm - slice.norm(0)) / norm < 1e-6);
    }

    @Test
    void test1Norm() {
        double norm = slice.stream()
            .mapToDouble(value -> Math.abs(value))
            .reduce(0., (acc, item) -> acc + item);
        assertTrue(Math.abs(norm - slice.norm(1)) / norm < 1e-6);
    }

    @Test
    void test2Norm() {
        double norm = (float)Math.sqrt(slice.stream()
            .map(value -> (float)Math.pow(Math.abs(value), 2))
            .reduce((float)0., (acc, item) -> acc + item));
        assertTrue(Math.abs(norm - slice.norm()) / norm < 1e-6);
    }

    @Test
    void testPQuasinorm() {
        double norm = (float)Math.pow(slice.stream()
            .map(value -> (float)Math.pow(Math.abs(value), 0.5))
            .reduce((float)0., (acc, item) -> acc + item), 2);
        assertTrue(Math.abs(norm - slice.norm(0.5)) / norm < 1e-6);
    }

    @Test
    void testPNorm() {
        double norm = (float)Math.pow(slice.stream()
            .map(value -> (float)Math.pow(Math.abs(value), 3.5))
            .reduce((float)0., (acc, item) -> acc + item), 1 / 3.5);
        assertTrue(Math.abs(norm - slice.norm(3.5)) / norm < 1e-6);
    }

    @Test
    void testInfNorm() {
        double norm = slice.stream()
            .mapToDouble(value -> Math.abs(value))
            .max().getAsDouble();
        assertTrue(Math.abs(norm - slice.norm(Double.POSITIVE_INFINITY)) / norm < 1e-6);
    }

    @Test
    void testCopy() {
        NDArray<Integer> array2 = slice.copy();
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(slice.get(5), array2.get(5));
    }

    @Test
    void testFillFloat() {
        slice.fill(3);
        for (Integer elem : slice)
            assertEquals((int)3, elem);
        NDArray<Integer> slice2 = array.slice(0, ":", ":");
        for (Integer elem : slice2)
            assertNotEquals((int)3, elem);
    }

    @Test
    void testFillReal() {
        slice.fill(3);
        for (Integer elem : slice)
            assertEquals((int)3, elem);
        NDArray<Integer> slice2 = array.slice(0, ":", ":");
        for (Integer elem : slice2)
            assertNotEquals((int)3, elem);
    }

    @Test
    void testPermuteDimsAndToArray() {
        NDArray<Integer> pArray = slice.permuteDims(1,0);
        Integer[][] arr = (Integer[][])pArray.toArray();
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[j][i]);
    }

    @Test
    void testConcatenate() {
        NDArray<Integer> array2 = new RealInt32NDArray(new int[]{5, 3}).fill(1);
        NDArray<Integer> array3 = slice.concatenate(0, array2);
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(slice.get(i, j), array3.get(i, j));
        for (int i = slice.dims(0); i < slice.dims(0) + array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals((int)1, array3.get(i, j));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Integer> array2 = slice.copy().fill(1).slice("1:1", ":");
        NDArray<Integer> array3 = new RealInt32NDArray(new int[]{3, 2}).permuteDims(1, 0);
        NDArray<Integer> array4 = new RealInt32NDArray(new int[]{9}).fill(2).reshape(3, 3);
        NDArray<Integer> array5 = slice.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = slice.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(slice.get(i, j), array5.get(i, j));
        start = end;
        end += array2.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals((int)1, array5.get(i, j));
        start = end;
        end += array3.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals((int)0, array5.get(i, j));
        start = end;
        end += array4.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals((int)2, array5.get(i, j));
    }
}
