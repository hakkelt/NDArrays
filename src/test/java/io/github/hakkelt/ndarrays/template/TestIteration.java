package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;
import io.github.hakkelt.ndarrays.internal.Errors;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1Iteration")
@Patterns({ "BasicByteNDArray", "/Byte/" })
@Replacements({ "BasicShortNDArray", "Short" })
@Replacements({ "BasicIntegerNDArray", "Integer" })
@Replacements({ "BasicLongNDArray", "Long" })
@Replacements({ "BasicFloatNDArray", "Float" })
@Replacements({ "BasicDoubleNDArray", "Double" })
@Replacements(value = { "BasicBigIntegerNDArray", "BigInteger" }, extraImports = "java.math.BigInteger")
@Replacements(value = { "BasicBigDecimalNDArray", "BigDecimal" }, extraImports = "java.math.BigDecimal")
@Replacements(value = { "BasicComplexFloatNDArray", "Complex" }, extraImports = "org.apache.commons.math3.complex.Complex")
@Replacements(value = { "BasicComplexDoubleNDArray", "Complex" }, extraImports = "org.apache.commons.math3.complex.Complex")
class TestIteration extends TestBase {
    NDArray<Byte> arrayOriginal;
    NDArray<Byte> array;
    NDArray<Byte> mask;
    NDArray<Byte> masked;
    NDArray<Byte> pArray;
    NDArray<Byte> reshaped;
    NDArray<Byte> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicByteNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToByte(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicByteNDArray(array.mapWithLinearIndices((value, index) -> wrapToByte(index % 2)));
        masked = array.mask(mask);
    }
    
    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testIterator() {
        int linearIndex = 0;
        for (Byte value : array)
            assertEquals(array.get(linearIndex++), value);

        var it = array.iterator();
        for (int i = 0; i < array.length(); i++)
            it.next();
        Exception exception = assertThrows(NoSuchElementException.class, () -> it.next());
        assertEquals(Errors.ITERATOR_OUT_OF_BOUNDS, exception.getMessage());
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testSpliterator() {
        var recorder = new ArrayList<Byte>();
        Consumer<Byte> consumer = value -> recorder.add(value);

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
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testStream() {
        final int[] linearIndex = new int[1];
        array.stream().forEach((value) -> {
            assertEquals(array.get(linearIndex[0]++), value);
        });
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testParallelStream() {
        Byte sum = array.parallelStream().reduce(wrapToByte(0), (acc, item) -> add(acc, item));
        Byte acc = wrapToByte(0);
        for (int i = 0; i < array.length(); i++)
            acc = add(acc, array.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testCollector() {
        NDArray<Byte> increased = array.stream()
            .map(value -> add(value, 1))
            .collect(BasicByteNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(add(array.get(i), 1), increased.get(i));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testParallelCollector() {
        NDArray<Byte> increased = array.parallelStream()
            .map(value -> add(value, 1))
            .collect(BasicByteNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(add(array.get(i), 1), increased.get(i));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testApply() {
        NDArray<Byte> array2 = new BasicByteNDArray(array);
        array.apply(value -> multiply(value, 2));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), 2), array.get(i));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testApplyWithLinearIndices() {
        NDArray<Byte> array2 = new BasicByteNDArray(array);
        array.applyWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i), array.get(i));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testApplyWithCartesianIndex() {
        NDArray<Byte> array2 = new BasicByteNDArray(array);
        array.applyWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(multiply(array2.get(i), i % array2.shape(0)), array.get(i));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testMap() {
        NDArray<Byte> array2 = array.map(value -> multiply(value, 2));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(multiply(array.get(i), 2), array2.get(i));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testMapWithLinearIndices() {
        NDArray<Byte> array2 = array.mapWithLinearIndices((value, index) -> multiply(value, index));
        for (int i = 0; i < array.length(); i++)
            assertSpecializedEquals(multiply(array.get(i), i), array2.get(i));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testMapWithCartesianIndex() {
        NDArray<Byte> array2 = array
                .mapWithCartesianIndices((value, indices) -> multiply(value, indices[0]));
        for (int i = 0; i < array2.length(); i++)
            assertSpecializedEquals(multiply(array.get(i), i % array.shape(0)), array2.get(i));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testForEach() {
        NDArray<Byte> array2 = array.similar().fill(1);
        array2.forEach(value -> assertSpecializedEquals(wrapToByte(1), value));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testForEachWithLinearIndices() {
        array.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(array.get(index), value));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testForEachWithCartesianIndex() {
        array.forEachWithCartesianIndices((value, indices) -> assertSpecializedEquals(array.get(indices), value));
    }

}
