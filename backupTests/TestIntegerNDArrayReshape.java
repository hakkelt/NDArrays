package io.github.hakkelt.ndarrays.backup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.Errors;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.basic.BasicByteNDArray;
import io.github.hakkelt.ndarrays.basic.BasicIntegerNDArray;

class TestIntegerNDArrayReshape implements NameTrait {
    NDArray<Integer> array, reshaped;

    @BeforeEach
    void setup() {
        array = new BasicIntegerNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> index.intValue());
        reshaped = array.reshape(20, 3);
    }

    @Test
    void testReshapeWrongshape() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.reshape(4, 16));
        assertEquals(String.format(Errors.RESHAPE_LENGTH_MISMATCH, "4 × 5 × 3", "4 × 16"), exception.getMessage());
    }

    @Test
    void testReshapeIdentity() {
        reshaped = array.reshape(4, 5, 3);
        assertSame(array, reshaped);
    }

    @Test
    void testReshapeReshaped() {
        NDArray<Integer> reshaped2 = reshaped.reshape(4, 5, 3);
        array.forEachWithCartesianIndices((value, indices) -> assertEquals(value, reshaped2.get(indices)));
        assertSame(array, reshaped2);
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals((int)55, reshaped.get(-5));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in parent
        assertEquals((int)linearIndex, reshaped.get(10, -1));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals((int)linearIndex, reshaped.get(10, -1));
        reshaped.set(1, linearIndex);
        assertEquals((int)1, reshaped.get(10, -1));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        reshaped.set(1, 10, -1);
        assertEquals((int)1, reshaped.get(linearIndex));
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
    void testDType() {
        assertEquals(Integer.class, reshaped.dtype());
    }

    @Test
    void testToArray() {
        Integer[][] arr = (Integer[][])reshaped.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr.length; j++) {
                assertEquals((int)linearIndex, arr[j][i]);
                linearIndex++;
            }
    }

    @Test
    void testEqual() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(reshaped);
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
        for (Integer value : reshaped)
            assertEquals((int)linearIndex++, value);
            
        var it = array.iterator();
        for (int i = 0; i < array.length(); i++)
            it.next();
        Exception exception = assertThrows(NoSuchElementException.class, () -> it.next());
        assertEquals(Errors.ITERATOR_OUT_OF_BOUNDS, exception.getMessage());
    }
    
    @Test
    void testSpliterator() {
        var recorder = new ArrayList<Integer>();
        Consumer<Integer> consumer = value -> recorder.add(value); 

        var spliterator = reshaped.spliterator();

        assertTrue(spliterator.tryAdvance(consumer));
        assertEquals(reshaped.get(0), recorder.get(0));

        spliterator.forEachRemaining(consumer);
        reshaped.streamLinearIndices().forEach(i -> assertEquals(reshaped.get(i), recorder.get(i)));

        assertFalse(spliterator.tryAdvance(consumer));
        spliterator.forEachRemaining(value -> fail());

        spliterator = reshaped.spliterator();
        for (int i = 0; i < 5; i++) {
            spliterator = spliterator.trySplit();
            assertNotNull(spliterator);
        }
        assertNull(spliterator.trySplit());
    }

    @Test
    void testStream() {
        final int[] linearIndex = new int[1];
        reshaped.stream().forEach((value) -> {
            assertEquals((int)linearIndex[0], value);
            linearIndex[0]++;
        });
    }

    @Test
    void testParallelStream() {
        Integer sum = reshaped.stream().parallel()
            .reduce((int)0, (acc, item) -> (int)(acc + item));
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals((int)GaussSum, sum);
    }

    @Test
    void testCollector() {
        NDArray<?> increased = reshaped.stream()
            .map((value) -> value + 1)
            .collect(BasicIntegerNDArray.getCollector(reshaped.shape()));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((int)(reshaped.get(i) + 1), increased.get(i));
    }

    @Test
    void testParallelCollector() {
        NDArray<?> increased = reshaped.stream().parallel()
            .map((value) -> value + 1)
            .collect(BasicIntegerNDArray.getCollector(reshaped.shape()));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((int)(reshaped.get(i) + 1), increased.get(i));
    }

    @Test
    void testToString() {
        String str = reshaped.toString();
        assertEquals(array.getNamePrefix() + " NDArray<Integer>(20 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = reshaped.contentToString();
        String lineFormat = "%6d\t%6d\t%6d\t%n";
        String expected = new StringBuilder()
            .append(array.getNamePrefix() + " NDArray<Integer>(20 × 3)" + System.lineSeparator())
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
    void testApply() {
        NDArray<Integer> reshaped2 = new BasicIntegerNDArray(array).reshape(20, 3);
        reshaped2.apply(value -> (int)Math.sqrt(value));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((int)Math.sqrt(reshaped.get(i)), reshaped2.get(i));
    }

    @Test
    void testApplyWithLinearIndices() {
        NDArray<Integer> reshaped2 = new BasicIntegerNDArray(array).reshape(20, 3);
        reshaped2.applyWithLinearIndices((value, index) -> (int)(Math.sqrt(value) + index));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((int)(Math.sqrt(reshaped.get(i)) + i), reshaped2.get(i));
    }

    @Test
    void testApplyWithCartesianIndex() {
        NDArray<Integer> reshaped2 = new BasicIntegerNDArray(array).reshape(20, 3);
        reshaped2.applyWithCartesianIndices((value, indices) -> (int)(Math.sqrt(value) + indices[0]));
        for (int i = 0; i < reshaped.shape(0); i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertEquals((int)(Math.sqrt(reshaped.get(i,j)) + i), reshaped2.get(i,j));
    }

    @Test
    void testMap() {
        NDArray<Integer> reshaped2 = reshaped.map(value -> (int)Math.sqrt(value));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((int)Math.sqrt(reshaped.get(i)), reshaped2.get(i));
    }

    @Test
    void testMapWithLinearIndices() {
        NDArray<Integer> reshaped2 = reshaped.mapWithLinearIndices((value, index) -> (int)(Math.sqrt(value) + index));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((int)(Math.sqrt(reshaped.get(i)) + i), reshaped2.get(i));
    }

    @Test
    void testMapWithCartesianIndex() {
        NDArray<Integer> reshaped2 = reshaped.mapWithCartesianIndices((value, indices) -> (int)(Math.sqrt(value) + indices[0]));
        for (int i = 0; i < reshaped.shape(0); i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertEquals((int)(Math.sqrt(reshaped.get(i,j)) + i), reshaped2.get(i,j));
    }

    @Test
    void testForEachSequential() {
        AtomicInteger i = new AtomicInteger(0);
        reshaped.forEachSequential(value -> assertEquals(reshaped.get(i.getAndIncrement()), value));
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
        NDArray<Integer> array2 = new BasicIntegerNDArray(reshaped);
        NDArray<Integer> array3 = reshaped.add(array2);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((int)(reshaped.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Integer> array2 = reshaped.add(5.);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((int)(reshaped.get(i) + 5), array2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(reshaped);
        NDArray<Integer> array3 = reshaped.add(array2, 5.3, array2, 3);
        for (int i = 0; i < reshaped.length(); i++) {
            float expected = reshaped.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(reshaped);
        array2.addInplace(reshaped);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((int)(reshaped.get(i) * 2), array2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(reshaped);
        array2.addInplace(5);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals((int)(reshaped.get(i) + 5), array2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(reshaped);
        array2.addInplace(reshaped, 5.3, array2, 3);
        for (int i = 0; i < reshaped.length(); i++) {
            float expected = reshaped.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void testSum() {
        Integer sum = reshaped.sum();
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals((int)GaussSum, sum);
    }

    @Test
    void testSum1D() {
        NDArray<Integer> sum = reshaped.sum(1);
        for (int i = 0; i < sum.shape(0); i++) {
            float GaussSum = (reshaped.get(i,0) + reshaped.get(i,-1)) * 3 / 2;
            assertEquals((int)GaussSum, sum.get(i));
        }
    }

    @Test
    void testSum2D() {
        reshaped = array.reshape(5,4,3);
        NDArray<Integer> sum = reshaped.sum(2, 1);
        for (int i = 0; i < sum.length(); i++) {
            float acc = 0;
            for (int j = 0; j < reshaped.shape(1); j++)
                for (int k = 0; k < reshaped.shape(2); k++)
                    acc = acc + reshaped.get(i,j,k);
            assertEquals((int)acc, sum.get(i));
        }
    }

    @Test
    void testNegativeNorm() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.norm(-1));
        assertEquals(Errors.NEGATIVE_NORM, exception.getMessage());
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
            .reduce((byte)0, (acc, item) -> acc + item);
        assertEquals(norm, reshaped.norm(1));
    }

    @Test
    void test2Norm() {
        double norm = Math.sqrt(reshaped.stream()
            .map(value -> Math.pow(Math.abs(value), 2))
            .reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, reshaped.norm());
    }

    @Test
    void testPQuasinorm() {
        double norm = Math.pow(reshaped.stream()
            .map(value -> Math.pow(Math.abs(value), 0.5))
            .reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, reshaped.norm(0.5));
    }

    @Test
    void testPNorm() {
        double norm = Math.pow(reshaped.stream()
            .map(value -> Math.pow(Math.abs(value), 3.5))
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
        NDArray<Integer> array2 = reshaped.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(reshaped.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(reshaped.get(5), array2.get(5));
    }

    @Test
    void testFillFloat() {
        reshaped.fill(3);
        for (Integer elem : reshaped)
            assertEquals((int)3, elem);
        for (Integer elem : array)
            assertEquals((int)3, elem);
    }

    @Test
    void testFillReal() {
        reshaped.fill(3);
        for (Integer elem : reshaped)
            assertEquals((int)3, elem);
        for (Integer elem : array)
            assertEquals((int)3, elem);
    }

    @Test
    void testPermuteDimsTooIntegerPermutationVector() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(0,2));
        assertEquals(
            String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2]", "5 × 4 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsTooLongPermutationVector() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(0,2,1,4));
        assertEquals(
            String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2, 1, 4]", "5 × 4 × 3"),
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
        NDArray<Byte> mask = new BasicByteNDArray(reshaped.map(value -> value > 20 ? 1 : 0));
        NDArray<Integer> masked = reshaped.mask(mask);
        masked.forEachSequential((value) -> assertTrue(value > 20));
        masked.fill(0);
        array.forEachSequential(value -> assertTrue(value <= 20));
    }

    @Test
    void testMaskReshapedWithPredicate() {
        NDArray<Integer> masked = reshaped.mask(value -> value > 20);
        masked.forEachSequential((value) -> assertTrue(value > 20));
        masked.fill(0);
        array.forEachSequential(value -> assertTrue(value <= 20));
    }

    @Test
    void testMaskReshapedWithPredicateWithLinearIndices() {
        NDArray<Integer> masked = reshaped.maskWithLinearIndices((value, i) -> value > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(value > 20 && i < 10));
        masked.fill(0);
        reshaped.forEachWithLinearIndices((value, i) -> assertTrue(value <= 20 || i >= 10));
    }

    @Test
    void testMaskReshapedWithPredicateWithCartesianIndices() {
        NDArray<Integer> masked = reshaped.maskWithCartesianIndices((value, idx) -> value > 20 && idx[0] == 0);
        masked.forEachSequential(value -> assertTrue(value > 20));
        masked.fill(0);
        reshaped.forEachWithCartesianIndices((value, idx) -> assertTrue(value <= 20 || idx[0] != 0));
    }

    @Test
    void testConcatenate() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(5, 3).fill(1);
        NDArray<Integer> array3 = reshaped.concatenate(0, array2);
        for (int i = 0; i < reshaped.shape(0); i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertEquals(reshaped.get(i, j), array3.get(i, j));
        for (int i = reshaped.shape(0); i < reshaped.shape(0) + array2.shape(0); i++)
            for (int j = 0; j < array2.shape(1); j++)
                assertEquals((int)1, array3.get(i, j));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Integer> array2 = reshaped.copy().fill(1).slice("1:5", ":");
        NDArray<Integer> array3 = new BasicIntegerNDArray(3, 2).permuteDims(1, 0);
        NDArray<Integer> array4 = new BasicIntegerNDArray(9).fill(2).reshape(3, 3);
        NDArray<Integer> array5 = reshaped.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = reshaped.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertEquals(reshaped.get(i, j), array5.get(i, j));
        start = end;
        end += array2.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.shape(1); j++)
                assertEquals((int)1, array5.get(i, j));
        start = end;
        end += array3.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.shape(1); j++)
                assertEquals((int)0, array5.get(i, j));
        start = end;
        end += array4.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.shape(1); j++)
                assertEquals((int)2, array5.get(i, j));
    }
}
