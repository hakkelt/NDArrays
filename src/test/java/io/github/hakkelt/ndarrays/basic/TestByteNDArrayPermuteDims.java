package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.Errors;
import io.github.hakkelt.ndarrays.NDArray;

class TestByteNDArrayPermuteDims implements NameTrait {
    NDArray<Byte> array, pArray;

    @BeforeEach
    void setup() {
        array = new BasicByteNDArray(new int[]{ 4, 5, 3 });
        array.applyWithLinearIndices((value, index) -> index.byteValue());
        pArray = array.permuteDims(0, 2, 1);
    }

    @Test
    void testPermuteDimsPermuteDims() {
        NDArray<Byte> ppArray = pArray.permuteDims(0, 2, 1);
        array.forEachWithCartesianIndices((value, indices) -> assertEquals(value, ppArray.get(indices)));
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals((byte)39, pArray.get(-5));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        assertEquals((byte)50, pArray.get(2, -1, -3));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        int parentLinearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in parent
        assertEquals(Byte.valueOf((byte)parentLinearIndex), pArray.get(2, -1, -3));
        int viewLinearIndex = (2 * 3 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in view
        pArray.set(1, viewLinearIndex);
        assertEquals((byte)1, pArray.get(2, -1, -3));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 3 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in view
        pArray.set(1, 2, -1, -3);
        assertEquals((byte)1, pArray.get(linearIndex));
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
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, 60));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, pArray.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, -61));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, pArray.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, 1,3,1));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 3, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, 1,1,-6));
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
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> pArray.set(0, 1, 1, 1, 0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> pArray.set(0, 1,1));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Byte.class, pArray.eltype());
    }

    @Test
    void testToArray() {
        Byte[][][] arr = (Byte[][][])pArray.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr[0][0].length; j++)
                for (int k = 0; k < arr.length; k++) {
                    assertEquals(Byte.valueOf((byte)linearIndex), arr[k][i][j]);
                    linearIndex++;
                }
    }

    @Test
    void testEqual() {
        NDArray<Byte> array2 = new BasicByteNDArray(pArray);
        assertEquals(pArray, array2);
        array2.set(0.f, 5);
        assertNotEquals(pArray, array2);
    }

    @Test
    void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { pArray.hashCode(); });
    }

    @Test
    void testIterator() {
        int linearIndex = 0;
        for (Byte value : pArray)
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
        Byte sum = pArray.stream().parallel()
            .reduce((byte)0, (acc, item) -> (byte)(acc + item));
        Byte acc = 0;
        for (int i = 0; i < array.dims(0); i++)
            for (int j = 0; j < array.dims(1); j++)
                for (int k = 0; k < array.dims(2); k++)
                    acc = (byte)(acc + array.get(i, j, k));
        assertEquals(acc, sum);
    }

    @Test
    void testCollector() {
        NDArray<Byte> increased = pArray.stream()
            .map((value) -> value + 1)
            .collect(BasicByteNDArray.getCollector(pArray.dims()));
        for (int i = 0; i < pArray.length(); i++)
            assertEquals((byte)(pArray.get(i) + 1), increased.get(i));
    }

    @Test
    void testParallelCollector() {
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value + 1)
            .collect(BasicByteNDArray.getCollector(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals((byte)(array.get(i) + 1), increased.get(i));
    }

    @Test
    void testToString() {
        String str = pArray.toString();
        assertEquals("basic NDArray<Byte>(4 × 3 × 5)", str);
    }

    @Test
    void testcontentToString() {
        String str = pArray.contentToString();
        String lineFormat = "%6d\t%6d\t%6d\t%n";
        String expected = new StringBuilder()
            .append("basic NDArray<Byte>(4 × 3 × 5)" + System.lineSeparator())
            .append("[:, :, 0] =" + System.lineSeparator())
            .append(String.format(lineFormat, 0, 20, 40))
            .append(String.format(lineFormat, 1, 21, 41))
            .append(String.format(lineFormat, 2, 22, 42))
            .append(String.format(lineFormat, 3, 23, 43))
            .append(System.lineSeparator())
            .append("[:, :, 1] =" + System.lineSeparator())
            .append(String.format(lineFormat, 4, 24, 44))
            .append(String.format(lineFormat, 5, 25, 45))
            .append(String.format(lineFormat, 6, 26, 46))
            .append(String.format(lineFormat, 7, 27, 47))
            .append(System.lineSeparator())
            .append("[:, :, 2] =" + System.lineSeparator())
            .append(String.format(lineFormat, 8, 28, 48))
            .append(String.format(lineFormat, 9, 29, 49))
            .append(String.format(lineFormat, 10, 30, 50))
            .append(String.format(lineFormat, 11, 31, 51))
            .append(System.lineSeparator())
            .append("[:, :, 3] =" + System.lineSeparator())
            .append(String.format(lineFormat, 12, 32, 52))
            .append(String.format(lineFormat, 13, 33, 53))
            .append(String.format(lineFormat, 14, 34, 54))
            .append(String.format(lineFormat, 15, 35, 55))
            .append(System.lineSeparator())
            .append("[:, :, 4] =" + System.lineSeparator())
            .append(String.format(lineFormat, 16, 36, 56))
            .append(String.format(lineFormat, 17, 37, 57))
            .append(String.format(lineFormat, 18, 38, 58))
            .append(String.format(lineFormat, 19, 39, 59))
            .append(System.lineSeparator())
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testApply() {
        NDArray<Byte> pArray2 = new BasicByteNDArray(array).permuteDims(0, 2, 1);
        pArray2.apply(value -> (byte)Math.sqrt(value));
        for (int i = 0; i < pArray.length(); i++)
            assertEquals((byte)Math.sqrt(pArray.get(i)), pArray2.get(i));
    }

    @Test
    void testApplyWithLinearIndices() {
        NDArray<Byte> pArray2 = new BasicByteNDArray(array).permuteDims(0, 2, 1);
        pArray2.applyWithLinearIndices((value, index) -> (byte)(Math.sqrt(value) + index));
        for (int i = 0; i < pArray.length(); i++)
            assertEquals((byte)(Math.sqrt(pArray.get(i)) + i), pArray2.get(i));
    }

    @Test
    void testApplyWithCartesianIndex() {
        NDArray<Byte> pArray2 = new BasicByteNDArray(array).permuteDims(0, 2, 1);
        pArray2.applyWithCartesianIndices((value, indices) -> (byte)(Math.sqrt(value) + indices[0]));
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = 0; k < pArray.dims(2); k++)
                    assertEquals((byte)(Math.sqrt(pArray.get(i,j,k)) + i), pArray2.get(i,j,k));
    }

    @Test
    void testMap() {
        NDArray<Byte> pArray2 = pArray.map(value -> (byte)Math.sqrt(value));
        for (int i = 0; i < pArray.length(); i++)
            assertEquals((byte)Math.sqrt(pArray.get(i)), pArray2.get(i));
    }

    @Test
    void testMapWithLinearIndices() {
        NDArray<Byte> pArray2 = pArray.mapWithLinearIndices((value, index) -> (byte)(Math.sqrt(value) + index));
        for (int i = 0; i < pArray.length(); i++)
            assertEquals((byte)(Math.sqrt(pArray.get(i)) + i), pArray2.get(i));
    }

    @Test
    void testMapWithCartesianIndex() {
        NDArray<Byte> pArray2 = pArray.mapWithCartesianIndices((value, indices) -> (byte)(Math.sqrt(value) + indices[0]));
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = 0; k < pArray.dims(2); k++)
                    assertEquals((byte)(Math.sqrt(pArray.get(i,j,k)) + i), pArray2.get(i,j,k));
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
        NDArray<Byte> array2 = new BasicByteNDArray(pArray);
        NDArray<Byte> array3 = pArray.add(array2);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals((byte)(pArray.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddpArrayToArray() {
        NDArray<Byte> array2 = new BasicByteNDArray(pArray);
        NDArray<Byte> array3 = array2.add(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals((byte)(pArray.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddpArrayTopArray() {
        NDArray<Byte> pArray2 = array.permuteDims(0, 2, 1);
        NDArray<Byte> array3 = pArray2.add(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals((byte)(pArray.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Byte> pArray2 = pArray.add(5);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals((byte)(pArray.get(i) + 5), pArray2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Byte> array2 = new BasicByteNDArray(array);
        NDArray<Byte> pArray2 = array2.permuteDims(0, 2, 1);
        NDArray<Byte> array3 = pArray2.add(pArray, 5, pArray2, 3);
        for (int i = 0; i < pArray.length(); i++) {
            Byte expected = (byte)(pArray.get(i) * 3 + 5 + 3);
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Byte> array2 = new BasicByteNDArray(array);
        NDArray<Byte> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals((byte)(pArray.get(i) * 2), pArray2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Byte> array2 = new BasicByteNDArray(array);
        NDArray<Byte> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(5);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals((byte)(pArray.get(i) + 5), pArray2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Byte> array2 = new BasicByteNDArray(array);
        NDArray<Byte> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(pArray, 5, pArray2, 3);
        for (int i = 0; i < pArray.length(); i++) {
            Byte expected = (byte)(pArray.get(i) * 3 + 5 + 3);
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void test0Norm() {
        pArray.slice(":", 0, ":").fill(0);
        double norm = pArray.stream()
            .filter(value -> value != 0.)
            .count();
        assertEquals(norm, pArray.norm(0));
    }

    @Test
    void test1Norm() {
        double norm = pArray.stream()
            .mapToDouble(value -> Math.abs(value))
            .reduce(0, (acc, item) -> acc + item);
        assertEquals(norm, pArray.norm(1));
    }

    @Test
    void test2Norm() {
        double norm = Math.sqrt(pArray.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 2))
            .reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, pArray.norm());
    }

    @Test
    void testPQuasinorm() {
        double norm = Math.pow(pArray.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 0.5))
            .reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, pArray.norm(0.5));
    }

    @Test
    void testPNorm() {
        double norm = Math.pow(pArray.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 3.5))
            .reduce(0., (acc, item) -> acc + item), 1 / 3.5);
        assertEquals(norm, pArray.norm(3.5));
    }

    @Test
    void testInfNorm() {
        double norm = pArray.stream()
            .mapToDouble(value -> Math.abs(value))
            .max().getAsDouble();
        assertEquals(norm, pArray.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testCopy() {
        NDArray<Byte> array2 = pArray.copy();
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i), array2.get(i));
        array2.set(0.f, 5);
        assertNotEquals(pArray.get(5), array2.get(5));
    }

    @Test
    void testFillFloat() {
        pArray.fill(3.f);
        for (Byte elem : pArray)
            assertEquals((byte)3, elem);
    }

    @Test
    void testFillReal() {
        pArray.fill(3);
        for (Byte elem : pArray)
            assertEquals((byte)3, elem);
    }

    @Test
    void testMaskPermuted() {
        NDArray<Byte> mask = new BasicByteNDArray(pArray.map(value -> value > 20 ? (byte)1 : (byte)0));
        NDArray<Byte> masked = pArray.mask(mask);
        masked.forEach((value) -> assertTrue(value > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(value <= 20));
    }

    @Test
    void testMaskPermutedWithPredicate() {
        NDArray<Byte> masked = pArray.mask(value -> value > 20);
        masked.forEach((value) -> assertTrue(value > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(value <= 20));
    }

    @Test
    void testMaskPermutedWithPredicateWithLinearIndices() {
        NDArray<Byte> masked = pArray.maskWithLinearIndices((value, i) -> value > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(value > 20 && i < 10));
        masked.fill(0);
        pArray.forEachWithLinearIndices((value, i) -> assertTrue(value <= 20 || i >= 10));
    }

    @Test
    void testMaskPermutedWithPredicateWithCartesianIndices() {
        NDArray<Byte> masked = pArray.maskWithCartesianIndices((value, idx) -> value > 20 && idx[0] == 0);
        masked.forEach(value -> assertTrue(value > 20));
        masked.fill(0);
        pArray.forEachWithCartesianIndices((value, idx) -> assertTrue(value <= 20 || idx[0] != 0));
    }

    @Test
    void testSliceAndToArray() {
        NDArray<Byte> slice = pArray.slice(1, ":", "1:4");
        Byte[][] arr = (Byte[][])slice.toArray();
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[j][i]);
    }

    @Test
    void testConcatenate() {
        NDArray<Byte> array2 = new BasicByteNDArray(new int[]{4, 3, 2}).fill(1);
        NDArray<Byte> array3 = pArray.concatenate(2, array2);
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = 0; k < pArray.dims(2); k++)
                    assertEquals(pArray.get(i, j, k), array3.get(i, j, k));
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = pArray.dims(2); k < pArray.dims(2) + array2.dims(2); k++)
                    assertEquals((byte)1, array3.get(i, j, k));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Byte> array2 = pArray.copy().fill(1).slice(":", ":", "1:3");
        NDArray<Byte> array3 = new BasicByteNDArray(new int[]{5, 3, 4}).permuteDims(2, 1, 0);
        NDArray<Byte> array4 = new BasicByteNDArray(new int[]{36}).fill(2.f).reshape(4, 3, 3);
        NDArray<Byte> array5 = pArray.concatenate(2, array2, array3, array4);
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
                    assertEquals((byte)1, array5.get(i, j, k));
        start = end;
        end += array3.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals((byte)0, array5.get(i, j, k));
        start = end;
        end += array4.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals((byte)2, array5.get(i, j, k));
    }
}
