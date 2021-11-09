package io.github.hakkelt.ndarrays.backup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.Errors;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.RealNDArrayReshapeView;
import io.github.hakkelt.ndarrays.basic.BasicByteNDArray;
import io.github.hakkelt.ndarrays.basic.BasicIntegerNDArray;

class TestIntegerNDArrayMask implements NameTrait {
    NDArray<Integer> array, masked;
    NDArray<Byte> mask;

    @BeforeEach
    void setup() {
        array = new BasicIntegerNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> index.intValue());
        mask = new BasicByteNDArray(array.mapWithLinearIndices((value, index) -> index.intValue() % 2));
        masked = array.mask(mask);
    }

    @Test
    void testDimensionMismatchMask() {
        mask = new BasicByteNDArray(4, 3, 5).fill(1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.mask(mask));
        assertEquals(String.format(Errors.MASK_DIMENSION_MISMATCH, "4 × 5 × 3", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testIdentityMask() {
        mask = new BasicByteNDArray(array.shape()).fill(1);
        assertTrue(array.mask(mask) instanceof RealNDArrayReshapeView);
    }

    @Test
    void testMask() {
        masked.forEachWithLinearIndices((value, index) -> assertEquals(value, index * 2 + 1));
        masked.fill(0);
        array.forEachWithLinearIndices((value, index) -> assertEquals(value, index % 2 == 0 ? index : 0));
    }

    @Test
    void testMaskWithPredicate() {
        NDArray<Integer> masked = array.mask(value -> value > 20);
        masked.forEach((value) -> assertTrue(value > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(value <= 20));
    }

    @Test
    void testMaskWithPredicateWithLinearIndices() {
        NDArray<Integer> masked = array.maskWithLinearIndices((value, i) -> value > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(value > 20 && i < 10));
        masked.fill(0);
        array.forEachWithLinearIndices((value, i) -> assertTrue(value <= 20 || i >= 10));
    }

    @Test
    void testMaskWithPredicateWithCartesianIndices() {
        NDArray<Integer> masked = array.maskWithCartesianIndices((value, idx) -> value > 20 && idx[0] == 0);
        masked.forEach(value -> assertTrue(value > 20));
        masked.fill(0);
        array.forEachWithCartesianIndices((value, idx) -> assertTrue(value <= 20 || idx[0] != 0));
    }

    @Test
    void testMaskMask() {
        NDArray<Byte> mask2 = new BasicByteNDArray(masked.map(value -> value > 20 ? 1 : 0));
        NDArray<Integer> masked2 = masked.mask(mask2);
        masked2.forEach((value) -> assertTrue(value > 20));
    }

    @Test
    void testMaskMaskWithPredicate() {
        NDArray<Integer> masked = array.mask(value -> value > 20);
        NDArray<Integer> masked2 = masked.mask(value -> value < 25);
        masked2.forEach((value) -> assertTrue(value > 20 && value < 25));
        masked2.fill(0);
        array.forEach(value -> assertTrue(value <= 20 || value >= 25));
    }

    @Test
    void testMaskMaskWithPredicateWithLinearIndices() {
        NDArray<Integer> masked = array.mask(value -> value > 20);
        NDArray<Integer> masked2 = masked.maskWithLinearIndices((value, i) -> value % 2 == 0 && i < 30);
        masked2.forEachWithLinearIndices((value, i) -> assertTrue(value % 2 == 0 && value > 20));
        assertEquals(15, masked2.length());
    }

    @Test
    void testMaskMaskWithPredicateWithCartesianIndices() {
        NDArray<Integer> masked = array.mask(value -> value > 20);
        NDArray<Integer> masked2 = masked.maskWithCartesianIndices((value, idx) -> idx[0] % 2 == 0);
        masked2.forEach(value -> assertTrue(value > 20));
        assertEquals(masked.length() / 2 + masked.length() % 2, masked2.length());
    }

    @Test
    void testMaskInverseMask() {
        NDArray<Byte> mask2 = new BasicByteNDArray(masked.map(value -> value > 20 ? 1 : 0));
        NDArray<Integer> masked2 = masked.inverseMask(mask2);
        masked2.forEach((value) -> assertTrue(value <= 20));
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals(55, masked.get(-3));
    }

    @Test
    void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.get(30));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, masked.length(), 30),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> masked.get(-31));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, masked.length(), -31),
            exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> masked.set(0, 30));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, masked.length(), 30),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> masked.set(0, -31));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, masked.length(), -31),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> masked.get(1,1,0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 3, 1),
            exception.getMessage());
    }

    @Test
    void testSetCartesianIndexing() {
        masked.set(0, new int[]{ 1 });
        assertEquals((byte)0, array.get(3));
    }

    @Test
    void testSetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> masked.set(0, 1,1,0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 3, 1),
            exception.getMessage());
    }

    @Test
    void testDType() {
        assertEquals(Integer.class, masked.dtype());
    }

    @Test
    void testToArray() {
        Integer[] arr = (Integer[])masked.toArray();
        for (int i = 0; i < masked.shape(0); i++)
            assertEquals(array.get(1 + i * 2), arr[i]);
    }

    @Test
    void testEqual() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(masked);
        assertEquals(masked, array2);
        array2.set(0, 5);
        assertNotEquals(masked, array2);
    }

    @Test
    void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { masked.hashCode(); });
    }

    @Test
    void testIterator() {
        int linearIndex = 0;
        for (Integer value : masked)
            assertEquals(masked.get(linearIndex++), value);
            
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

        var spliterator = masked.spliterator();

        assertTrue(spliterator.tryAdvance(consumer));
        assertEquals(masked.get(0), recorder.get(0));

        spliterator.forEachRemaining(consumer);
        masked.streamLinearIndices().forEach(i -> assertEquals(masked.get(i), recorder.get(i)));

        assertFalse(spliterator.tryAdvance(consumer));
        spliterator.forEachRemaining(value -> fail());

        spliterator = masked.spliterator();
        for (int i = 0; i < 4; i++) {
            spliterator = spliterator.trySplit();
            assertNotNull(spliterator);
        }
        assertNull(spliterator.trySplit());
    }

    @Test
    void testStream() {
        final int[] linearIndex = new int[1];
        masked.stream().forEach((value) -> {
            assertEquals(masked.get(linearIndex[0]++), value);
        });
    }

    @Test
    void testParallelStream() {
        Integer sum = masked.stream().parallel()
            .reduce(0, (acc, item) -> (acc + item));
        int acc = 0;
        for (int i = 1; i < array.length(); i += 2)
            acc += array.get(i);
        assertEquals(acc, sum);
    }

    @Test
    void testCollector() {
        NDArray<Integer> increased = masked.stream()
            .map((value) -> value + 1)
            .collect(BasicIntegerNDArray.getCollector(masked.shape()));
        for (int i = 0; i < masked.length(); i++)
            assertEquals((masked.get(i) + 1), increased.get(i));
    }

    @Test
    void testParallelCollector() {
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value + 1)
            .collect(BasicIntegerNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) + 1, increased.get(i));
    }

    @Test
    void testToString() {
        String str = masked.toString();
        assertEquals(array.getNamePrefix() + " NDArray<Integer>(30)", str);
    }

    @Test
    void testcontentToString() {
        String str = masked.contentToString();
        String numberFormat = "%6d\t";
        String line = masked.streamLinearIndices()
            .mapToObj(i -> String.format(numberFormat, i * 2 + 1))
            .reduce("", (a, b) -> String.join("", a, b));
        String expected = new StringBuilder()
            .append(array.getNamePrefix() + " NDArray<Integer>(30)")
            .append(System.lineSeparator())
            .append(line)
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testAddArrayToMasked() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(masked);
        NDArray<Integer> array3 = masked.add(array2);
        for (int i = 0; i < masked.length(); i++)
            assertEquals((masked.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddMaskedToArray() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(masked);
        NDArray<Integer> array3 = array2.add(masked);
        for (int i = 0; i < masked.length(); i++)
            assertEquals((masked.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddMaskedToMasked() {
        NDArray<Integer> masked2 = array.mask(mask);
        NDArray<Integer> array3 = masked2.add(masked);
        for (int i = 0; i < masked.length(); i++)
            assertEquals((masked.get(i) * 2), array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Integer> masked2 = masked.add(5.);
        for (int i = 0; i < masked.length(); i++)
            assertEquals((masked.get(i) + 5), masked2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(array);
        NDArray<Integer> masked2 = array2.mask(mask);
        NDArray<Integer> array3 = masked2.add(masked, 5, masked2, 3);
        for (int i = 0; i < masked.length(); i++) {
            int expected = (masked.get(i) * 3 + 5 + 3);
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(array);
        NDArray<Integer> masked2 = array2.mask(mask);
        masked2.addInplace(masked);
        for (int i = 0; i < masked.length(); i++)
            assertEquals((masked.get(i) * 2), masked2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(array);
        NDArray<Integer> masked2 = array2.mask(mask);
        masked2.addInplace(5);
        for (int i = 0; i < masked.length(); i++)
            assertEquals((masked.get(i) + 5), masked2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(array);
        NDArray<Integer> masked2 = array2.mask(mask);
        masked2.addInplace(masked, 5.3, masked2, 3);
        for (int i = 0; i < masked.length(); i++) {
            int expected = (masked.get(i) * 3 + 5 + 3);
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void testNegativeNorm() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.norm(-1));
        assertEquals(Errors.NEGATIVE_NORM, exception.getMessage());
    }

    @Test
    void test0Norm() {
        double norm = masked.stream()
            .filter(value -> value != 0)
            .count();
        assertEquals(norm, masked.norm(0));
    }

    @Test
    void test1Norm() {
        double norm = masked.stream()
            .map(value -> Math.abs(value))
            .reduce(0, (acc, item) -> acc + item);
        assertTrue(Math.abs(norm - masked.norm(1)) / norm < 1e-6);
    }

    @Test
    void test2Norm() {
        double norm = Math.sqrt(masked.stream()
            .map(value -> Math.pow(Math.abs(value), 2))
            .reduce(0., (acc, item) -> acc + item));
        assertTrue(Math.abs(norm - masked.norm()) / norm < 1e-6);
    }

    @Test
    void testPQuasinorm() {
        double norm = Math.pow(masked.stream()
            .map(value -> Math.pow(Math.abs(value), 0.5))
            .reduce(0., (acc, item) -> acc + item), 2);
        assertTrue(Math.abs(norm - masked.norm(0.5)) / norm < 5e-6);
    }

    @Test
    void testPNorm() {
        double norm = Math.pow(masked.stream()
            .map(value -> Math.pow(Math.abs(value), 3.5))
            .reduce(0., (acc, item) -> acc + item), 1 / 3.5);
        assertTrue(Math.abs(norm - masked.norm(3.5)) / norm < 5e-6);
    }

    @Test
    void testInfNorm() {
        double norm = masked.stream()
            .mapToDouble(value -> Math.abs(value))
            .max().getAsDouble();
        assertEquals(norm, masked.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testCopy() {
        NDArray<Integer> array2 = masked.copy();
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(masked.get(5), array2.get(5));
    }

    @Test
    void testFillReal() {
        masked.fill(3);
        for (Integer elem : masked)
            assertEquals(3, elem);
        array.forEachWithLinearIndices((value, index) -> {
            if (index % 2 == 0)
                assertEquals(index, value);
            else
                assertEquals(3, value);
        });
    }

    @Test
    void testPermuteDimsAndToArray() {
        NDArray<Integer> pArray = masked.permuteDims(0);
        Integer[] arr = (Integer[])pArray.toArray();
        for (int i = 0; i < pArray.shape(0); i++)
            assertEquals(array.get(i * 2 + 1), arr[i]);
    }

    @Test
    void testConcatenate() {
        NDArray<Integer> array2 = new BasicIntegerNDArray(5).fill(1);
        NDArray<Integer> array3 = masked.concatenate(0, array2);
        for (int i = 0; i < masked.shape(0); i++)
            assertEquals(masked.get(i), array3.get(i));
        for (int i = masked.shape(0); i < masked.shape(0) + array2.shape(0); i++)
            assertEquals(1, array3.get(i));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Integer> array2 = masked.copy().fill(1).slice("1:1");
        NDArray<Integer> array3 = new BasicIntegerNDArray(5).permuteDims(0);
        NDArray<Integer> array4 = new BasicIntegerNDArray(3, 3).fill(2).reshape(9);
        NDArray<Integer> array5 = masked.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = masked.shape(0);
        for (int i = start; i < end; i++)
            assertEquals(masked.get(i), array5.get(i));
        start = end;
        end += array2.shape(0);
        for (int i = start; i < end; i++)
            assertEquals(1, array5.get(i));
        start = end;
        end += array3.shape(0);
        for (int i = start; i < end; i++)
            assertEquals(0, array5.get(i));
        start = end;
        end += array4.shape(0);
        for (int i = start; i < end; i++)
            assertEquals(2, array5.get(i));
    }
}
