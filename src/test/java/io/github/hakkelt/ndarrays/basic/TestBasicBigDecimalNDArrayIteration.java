/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestIteration.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.Errors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBasicBigDecimalNDArrayIteration extends TestBase {

    NDArray<BigDecimal> arrayOriginal;
    NDArray<BigDecimal> array;
    NDArray<BigDecimal> mask;
    NDArray<BigDecimal> masked;
    NDArray<BigDecimal> pArray;
    NDArray<BigDecimal> reshaped;
    NDArray<BigDecimal> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicBigDecimalNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToBigDecimal(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicBigDecimalNDArray(array.mapWithLinearIndices((value, index) -> wrapToBigDecimal(index % 2)));
        masked = array.mask(mask);
    }

    @Test
    void testIteratorWithArray() {
        int linearIndex = 0;
        for (BigDecimal value : array)
            assertEquals(array.get(linearIndex++), value);

        var it = array.iterator();
        for (int i = 0; i < array.length(); i++)
            it.next();
        Exception exception = assertThrows(NoSuchElementException.class, () -> it.next());
        assertEquals(Errors.ITERATOR_OUT_OF_BOUNDS, exception.getMessage());
    }

    @Test
    void testIteratorWithSlice() {
        int linearIndex = 0;
        for (BigDecimal value : slice)
            assertEquals(slice.get(linearIndex++), value);

        var it = slice.iterator();
        for (int i = 0; i < slice.length(); i++)
            it.next();
        Exception exception = assertThrows(NoSuchElementException.class, () -> it.next());
        assertEquals(Errors.ITERATOR_OUT_OF_BOUNDS, exception.getMessage());
    }

    @Test
    void testIteratorWithReshaped() {
        int linearIndex = 0;
        for (BigDecimal value : reshaped)
            assertEquals(reshaped.get(linearIndex++), value);

        var it = reshaped.iterator();
        for (int i = 0; i < reshaped.length(); i++)
            it.next();
        Exception exception = assertThrows(NoSuchElementException.class, () -> it.next());
        assertEquals(Errors.ITERATOR_OUT_OF_BOUNDS, exception.getMessage());
    }

    @Test
    void testIteratorWithPArray() {
        int linearIndex = 0;
        for (BigDecimal value : pArray)
            assertEquals(pArray.get(linearIndex++), value);

        var it = pArray.iterator();
        for (int i = 0; i < pArray.length(); i++)
            it.next();
        Exception exception = assertThrows(NoSuchElementException.class, () -> it.next());
        assertEquals(Errors.ITERATOR_OUT_OF_BOUNDS, exception.getMessage());
    }

    @Test
    void testIteratorWithMasked() {
        int linearIndex = 0;
        for (BigDecimal value : masked)
            assertEquals(masked.get(linearIndex++), value);

        var it = masked.iterator();
        for (int i = 0; i < masked.length(); i++)
            it.next();
        Exception exception = assertThrows(NoSuchElementException.class, () -> it.next());
        assertEquals(Errors.ITERATOR_OUT_OF_BOUNDS, exception.getMessage());
    }

    @Test
    void testSpliteratorWithArray() {
        var recorder = new ArrayList<BigDecimal>();
        Consumer<BigDecimal> consumer = value -> recorder.add(value);

        var spliterator = array.spliterator();

        assertTrue(spliterator.tryAdvance(consumer));
        assertEquals(array.get(0), recorder.get(0));

        spliterator.forEachRemaining(consumer);
        array.streamLinearIndices().forEach(i -> assertEquals(array.get(i), recorder.get(i)));

        assertFalse(spliterator.tryAdvance(consumer));
        spliterator.forEachRemaining(value -> fail());

        spliterator = array.spliterator();
        int numberOfPossibleSplits = (int) Math.floor(Math.log(array.length()) / Math.log(2));
        for (int i = 0; i < numberOfPossibleSplits; i++) {
            spliterator = spliterator.trySplit();
            assertNotNull(spliterator);
        }
        assertNull(spliterator.trySplit());
    }

    @Test
    void testSpliteratorWithSlice() {
        var recorder = new ArrayList<BigDecimal>();
        Consumer<BigDecimal> consumer = value -> recorder.add(value);

        var spliterator = slice.spliterator();

        assertTrue(spliterator.tryAdvance(consumer));
        assertEquals(slice.get(0), recorder.get(0));

        spliterator.forEachRemaining(consumer);
        slice.streamLinearIndices().forEach(i -> assertEquals(slice.get(i), recorder.get(i)));

        assertFalse(spliterator.tryAdvance(consumer));
        spliterator.forEachRemaining(value -> fail());

        spliterator = slice.spliterator();
        int numberOfPossibleSplits = (int) Math.floor(Math.log(slice.length()) / Math.log(2));
        for (int i = 0; i < numberOfPossibleSplits; i++) {
            spliterator = spliterator.trySplit();
            assertNotNull(spliterator);
        }
        assertNull(spliterator.trySplit());
    }

    @Test
    void testSpliteratorWithReshaped() {
        var recorder = new ArrayList<BigDecimal>();
        Consumer<BigDecimal> consumer = value -> recorder.add(value);

        var spliterator = reshaped.spliterator();

        assertTrue(spliterator.tryAdvance(consumer));
        assertEquals(reshaped.get(0), recorder.get(0));

        spliterator.forEachRemaining(consumer);
        reshaped.streamLinearIndices().forEach(i -> assertEquals(reshaped.get(i), recorder.get(i)));

        assertFalse(spliterator.tryAdvance(consumer));
        spliterator.forEachRemaining(value -> fail());

        spliterator = reshaped.spliterator();
        int numberOfPossibleSplits = (int) Math.floor(Math.log(reshaped.length()) / Math.log(2));
        for (int i = 0; i < numberOfPossibleSplits; i++) {
            spliterator = spliterator.trySplit();
            assertNotNull(spliterator);
        }
        assertNull(spliterator.trySplit());
    }

    @Test
    void testSpliteratorWithPArray() {
        var recorder = new ArrayList<BigDecimal>();
        Consumer<BigDecimal> consumer = value -> recorder.add(value);

        var spliterator = pArray.spliterator();

        assertTrue(spliterator.tryAdvance(consumer));
        assertEquals(pArray.get(0), recorder.get(0));

        spliterator.forEachRemaining(consumer);
        pArray.streamLinearIndices().forEach(i -> assertEquals(pArray.get(i), recorder.get(i)));

        assertFalse(spliterator.tryAdvance(consumer));
        spliterator.forEachRemaining(value -> fail());

        spliterator = pArray.spliterator();
        int numberOfPossibleSplits = (int) Math.floor(Math.log(pArray.length()) / Math.log(2));
        for (int i = 0; i < numberOfPossibleSplits; i++) {
            spliterator = spliterator.trySplit();
            assertNotNull(spliterator);
        }
        assertNull(spliterator.trySplit());
    }

    @Test
    void testSpliteratorWithMasked() {
        var recorder = new ArrayList<BigDecimal>();
        Consumer<BigDecimal> consumer = value -> recorder.add(value);

        var spliterator = masked.spliterator();

        assertTrue(spliterator.tryAdvance(consumer));
        assertEquals(masked.get(0), recorder.get(0));

        spliterator.forEachRemaining(consumer);
        masked.streamLinearIndices().forEach(i -> assertEquals(masked.get(i), recorder.get(i)));

        assertFalse(spliterator.tryAdvance(consumer));
        spliterator.forEachRemaining(value -> fail());

        spliterator = masked.spliterator();
        int numberOfPossibleSplits = (int) Math.floor(Math.log(masked.length()) / Math.log(2));
        for (int i = 0; i < numberOfPossibleSplits; i++) {
            spliterator = spliterator.trySplit();
            assertNotNull(spliterator);
        }
        assertNull(spliterator.trySplit());
    }

    @Test
    void testStreamWithArray() {
        final int[] linearIndex = new int[1];
        array.stream().forEach((value) -> {
            assertEquals(array.get(linearIndex[0]++), value);
        });
    }

    @Test
    void testStreamWithSlice() {
        final int[] linearIndex = new int[1];
        slice.stream().forEach((value) -> {
            assertEquals(slice.get(linearIndex[0]++), value);
        });
    }

    @Test
    void testStreamWithReshaped() {
        final int[] linearIndex = new int[1];
        reshaped.stream().forEach((value) -> {
            assertEquals(reshaped.get(linearIndex[0]++), value);
        });
    }

    @Test
    void testStreamWithPArray() {
        final int[] linearIndex = new int[1];
        pArray.stream().forEach((value) -> {
            assertEquals(pArray.get(linearIndex[0]++), value);
        });
    }

    @Test
    void testStreamWithMasked() {
        final int[] linearIndex = new int[1];
        masked.stream().forEach((value) -> {
            assertEquals(masked.get(linearIndex[0]++), value);
        });
    }

    @Test
    void testParallelStreamWithArray() {
        BigDecimal sum = array.stream().parallel().reduce(wrapToBigDecimal(0), (acc, item) -> add(acc, item));
        BigDecimal acc = wrapToBigDecimal(0);
        for (int i = 0; i < array.length(); i++)
            acc = add(acc, array.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testParallelStreamWithSlice() {
        BigDecimal sum = slice.stream().parallel().reduce(wrapToBigDecimal(0), (acc, item) -> add(acc, item));
        BigDecimal acc = wrapToBigDecimal(0);
        for (int i = 0; i < slice.length(); i++)
            acc = add(acc, slice.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testParallelStreamWithReshaped() {
        BigDecimal sum = reshaped.stream().parallel().reduce(wrapToBigDecimal(0), (acc, item) -> add(acc, item));
        BigDecimal acc = wrapToBigDecimal(0);
        for (int i = 0; i < reshaped.length(); i++)
            acc = add(acc, reshaped.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testParallelStreamWithPArray() {
        BigDecimal sum = pArray.stream().parallel().reduce(wrapToBigDecimal(0), (acc, item) -> add(acc, item));
        BigDecimal acc = wrapToBigDecimal(0);
        for (int i = 0; i < pArray.length(); i++)
            acc = add(acc, pArray.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testParallelStreamWithMasked() {
        BigDecimal sum = masked.stream().parallel().reduce(wrapToBigDecimal(0), (acc, item) -> add(acc, item));
        BigDecimal acc = wrapToBigDecimal(0);
        for (int i = 0; i < masked.length(); i++)
            acc = add(acc, masked.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testCollectorWithArray() {
        NDArray<BigDecimal> increased = array.stream()
            .map(value -> add(value, 1))
            .collect(BasicBigDecimalNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(add(array.get(i), 1), increased.get(i));
    }

    @Test
    void testCollectorWithSlice() {
        NDArray<BigDecimal> increased = slice.stream()
            .map(value -> add(value, 1))
            .collect(BasicBigDecimalNDArray.getCollector(slice.shape()));
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(add(slice.get(i), 1), increased.get(i));
    }

    @Test
    void testCollectorWithReshaped() {
        NDArray<BigDecimal> increased = reshaped.stream()
            .map(value -> add(value, 1))
            .collect(BasicBigDecimalNDArray.getCollector(reshaped.shape()));
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(add(reshaped.get(i), 1), increased.get(i));
    }

    @Test
    void testCollectorWithPArray() {
        NDArray<BigDecimal> increased = pArray.stream()
            .map(value -> add(value, 1))
            .collect(BasicBigDecimalNDArray.getCollector(pArray.shape()));
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(add(pArray.get(i), 1), increased.get(i));
    }

    @Test
    void testCollectorWithMasked() {
        NDArray<BigDecimal> increased = masked.stream()
            .map(value -> add(value, 1))
            .collect(BasicBigDecimalNDArray.getCollector(masked.shape()));
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(add(masked.get(i), 1), increased.get(i));
    }

    @Test
    void testParallelCollectorWithArray() {
        NDArray<BigDecimal> increased = array.stream().parallel()
        .map(value -> add(value, 1))
            .collect(BasicBigDecimalNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(add(array.get(i), 1), increased.get(i));
    }

    @Test
    void testParallelCollectorWithSlice() {
        NDArray<BigDecimal> increased = slice.stream().parallel()
        .map(value -> add(value, 1))
            .collect(BasicBigDecimalNDArray.getCollector(slice.shape()));
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(add(slice.get(i), 1), increased.get(i));
    }

    @Test
    void testParallelCollectorWithReshaped() {
        NDArray<BigDecimal> increased = reshaped.stream().parallel()
        .map(value -> add(value, 1))
            .collect(BasicBigDecimalNDArray.getCollector(reshaped.shape()));
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(add(reshaped.get(i), 1), increased.get(i));
    }

    @Test
    void testParallelCollectorWithPArray() {
        NDArray<BigDecimal> increased = pArray.stream().parallel()
        .map(value -> add(value, 1))
            .collect(BasicBigDecimalNDArray.getCollector(pArray.shape()));
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(add(pArray.get(i), 1), increased.get(i));
    }

    @Test
    void testParallelCollectorWithMasked() {
        NDArray<BigDecimal> increased = masked.stream().parallel()
        .map(value -> add(value, 1))
            .collect(BasicBigDecimalNDArray.getCollector(masked.shape()));
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(add(masked.get(i), 1), increased.get(i));
    }

    @Test
    void testApplyWithArray() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(array);
        array.apply(value -> multiply(value, 2));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), 2), array.get(i));
    }

    @Test
    void testApplyWithSlice() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(slice);
        slice.apply(value -> multiply(value, 2));
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), 2), slice.get(i));
    }

    @Test
    void testApplyWithReshaped() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(reshaped);
        reshaped.apply(value -> multiply(value, 2));
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), 2), reshaped.get(i));
    }

    @Test
    void testApplyWithPArray() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(pArray);
        pArray.apply(value -> multiply(value, 2));
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), 2), pArray.get(i));
    }

    @Test
    void testApplyWithMasked() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(masked);
        masked.apply(value -> multiply(value, 2));
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), 2), masked.get(i));
    }

    @Test
    void testApplyWithLinearIndicesWithArray() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(array);
        array.applyWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i), array.get(i));
    }

    @Test
    void testApplyWithLinearIndicesWithSlice() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(slice);
        slice.applyWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i), slice.get(i));
    }

    @Test
    void testApplyWithLinearIndicesWithReshaped() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(reshaped);
        reshaped.applyWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i), reshaped.get(i));
    }

    @Test
    void testApplyWithLinearIndicesWithPArray() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(pArray);
        pArray.applyWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i), pArray.get(i));
    }

    @Test
    void testApplyWithLinearIndicesWithMasked() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(masked);
        masked.applyWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i), masked.get(i));
    }

    @Test
    void testApplyWithCartesianIndexWithArray() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(array);
        array.applyWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i % array2.shape(0)), array.get(i));
    }

    @Test
    void testApplyWithCartesianIndexWithSlice() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(slice);
        slice.applyWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i % array2.shape(0)), slice.get(i));
    }

    @Test
    void testApplyWithCartesianIndexWithReshaped() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(reshaped);
        reshaped.applyWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i % array2.shape(0)), reshaped.get(i));
    }

    @Test
    void testApplyWithCartesianIndexWithPArray() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(pArray);
        pArray.applyWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i % array2.shape(0)), pArray.get(i));
    }

    @Test
    void testApplyWithCartesianIndexWithMasked() {
        NDArray<BigDecimal> array2 = new BasicBigDecimalNDArray(masked);
        masked.applyWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i % array2.shape(0)), masked.get(i));
    }

    @Test
    void testMapWithArray() {
        NDArray<BigDecimal> array2 = array.map(value -> multiply(value, 2));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(multiply(array.get(i), 2), array2.get(i));
    }

    @Test
    void testMapWithSlice() {
        NDArray<BigDecimal> array2 = slice.map(value -> multiply(value, 2));
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(multiply(slice.get(i), 2), array2.get(i));
    }

    @Test
    void testMapWithReshaped() {
        NDArray<BigDecimal> array2 = reshaped.map(value -> multiply(value, 2));
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(multiply(reshaped.get(i), 2), array2.get(i));
    }

    @Test
    void testMapWithPArray() {
        NDArray<BigDecimal> array2 = pArray.map(value -> multiply(value, 2));
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(multiply(pArray.get(i), 2), array2.get(i));
    }

    @Test
    void testMapWithMasked() {
        NDArray<BigDecimal> array2 = masked.map(value -> multiply(value, 2));
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(multiply(masked.get(i), 2), array2.get(i));
    }

    @Test
    void testMapWithLinearIndicesWithArray() {
        NDArray<BigDecimal> array2 = array.mapWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(multiply(array.get(i), i), array2.get(i));
    }

    @Test
    void testMapWithLinearIndicesWithSlice() {
        NDArray<BigDecimal> array2 = slice.mapWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < slice.length(); i++)
            assertSpecializedEquals(multiply(slice.get(i), i), array2.get(i));
    }

    @Test
    void testMapWithLinearIndicesWithReshaped() {
        NDArray<BigDecimal> array2 = reshaped.mapWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < reshaped.length(); i++)
            assertSpecializedEquals(multiply(reshaped.get(i), i), array2.get(i));
    }

    @Test
    void testMapWithLinearIndicesWithPArray() {
        NDArray<BigDecimal> array2 = pArray.mapWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < pArray.length(); i++)
            assertSpecializedEquals(multiply(pArray.get(i), i), array2.get(i));
    }

    @Test
    void testMapWithLinearIndicesWithMasked() {
        NDArray<BigDecimal> array2 = masked.mapWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < masked.length(); i++)
            assertSpecializedEquals(multiply(masked.get(i), i), array2.get(i));
    }

    @Test
    void testMapWithCartesianIndexWithArray() {
        NDArray<BigDecimal> array2 = array
                .mapWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < array2.length(); i++)
            assertSpecializedEquals(multiply(array.get(i), i % array.shape(0)), array2.get(i));
    }

    @Test
    void testMapWithCartesianIndexWithSlice() {
        NDArray<BigDecimal> array2 = slice
                .mapWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < array2.length(); i++)
            assertSpecializedEquals(multiply(slice.get(i), i % slice.shape(0)), array2.get(i));
    }

    @Test
    void testMapWithCartesianIndexWithReshaped() {
        NDArray<BigDecimal> array2 = reshaped
                .mapWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < array2.length(); i++)
            assertSpecializedEquals(multiply(reshaped.get(i), i % reshaped.shape(0)), array2.get(i));
    }

    @Test
    void testMapWithCartesianIndexWithPArray() {
        NDArray<BigDecimal> array2 = pArray
                .mapWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < array2.length(); i++)
            assertSpecializedEquals(multiply(pArray.get(i), i % pArray.shape(0)), array2.get(i));
    }

    @Test
    void testMapWithCartesianIndexWithMasked() {
        NDArray<BigDecimal> array2 = masked
                .mapWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < array2.length(); i++)
            assertSpecializedEquals(multiply(masked.get(i), i % masked.shape(0)), array2.get(i));
    }

    @Test
    void testForEachWithArray() {
        NDArray<BigDecimal> array2 = array.similar().fill(1);
        array2.forEach(value -> assertSpecializedEquals(wrapToBigDecimal(1), value));
    }

    @Test
    void testForEachWithSlice() {
        NDArray<BigDecimal> array2 = slice.similar().fill(1);
        array2.forEach(value -> assertSpecializedEquals(wrapToBigDecimal(1), value));
    }

    @Test
    void testForEachWithReshaped() {
        NDArray<BigDecimal> array2 = reshaped.similar().fill(1);
        array2.forEach(value -> assertSpecializedEquals(wrapToBigDecimal(1), value));
    }

    @Test
    void testForEachWithPArray() {
        NDArray<BigDecimal> array2 = pArray.similar().fill(1);
        array2.forEach(value -> assertSpecializedEquals(wrapToBigDecimal(1), value));
    }

    @Test
    void testForEachWithMasked() {
        NDArray<BigDecimal> array2 = masked.similar().fill(1);
        array2.forEach(value -> assertSpecializedEquals(wrapToBigDecimal(1), value));
    }

    @Test
    void testForEachSequentialWithArray() {
        int[] i = {0};
        array.forEachSequential(value -> assertSpecializedEquals(array.get(i[0]++), value));
    }

    @Test
    void testForEachSequentialWithSlice() {
        int[] i = {0};
        slice.forEachSequential(value -> assertSpecializedEquals(slice.get(i[0]++), value));
    }

    @Test
    void testForEachSequentialWithReshaped() {
        int[] i = {0};
        reshaped.forEachSequential(value -> assertSpecializedEquals(reshaped.get(i[0]++), value));
    }

    @Test
    void testForEachSequentialWithPArray() {
        int[] i = {0};
        pArray.forEachSequential(value -> assertSpecializedEquals(pArray.get(i[0]++), value));
    }

    @Test
    void testForEachSequentialWithMasked() {
        int[] i = {0};
        masked.forEachSequential(value -> assertSpecializedEquals(masked.get(i[0]++), value));
    }

    @Test
    void testForEachWithLinearIndicesWithArray() {
        array.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(array.get(index), value));
    }

    @Test
    void testForEachWithLinearIndicesWithSlice() {
        slice.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(slice.get(index), value));
    }

    @Test
    void testForEachWithLinearIndicesWithReshaped() {
        reshaped.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(reshaped.get(index), value));
    }

    @Test
    void testForEachWithLinearIndicesWithPArray() {
        pArray.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(pArray.get(index), value));
    }

    @Test
    void testForEachWithLinearIndicesWithMasked() {
        masked.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(masked.get(index), value));
    }

    @Test
    void testForEachWithCartesianIndexWithArray() {
        array.forEachWithCartesianIndices((value, indices) -> assertSpecializedEquals(array.get(indices), value));
    }

    @Test
    void testForEachWithCartesianIndexWithSlice() {
        slice.forEachWithCartesianIndices((value, indices) -> assertSpecializedEquals(slice.get(indices), value));
    }

    @Test
    void testForEachWithCartesianIndexWithReshaped() {
        reshaped.forEachWithCartesianIndices((value, indices) -> assertSpecializedEquals(reshaped.get(indices), value));
    }

    @Test
    void testForEachWithCartesianIndexWithPArray() {
        pArray.forEachWithCartesianIndices((value, indices) -> assertSpecializedEquals(pArray.get(indices), value));
    }

    @Test
    void testForEachWithCartesianIndexWithMasked() {
        masked.forEachWithCartesianIndices((value, indices) -> assertSpecializedEquals(masked.get(indices), value));
    }

}
