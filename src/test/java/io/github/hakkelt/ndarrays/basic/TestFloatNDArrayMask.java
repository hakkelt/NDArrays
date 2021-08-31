package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.ByteNDArrayConstructorTrait;
import io.github.hakkelt.ndarrays.Errors;
import io.github.hakkelt.ndarrays.FloatNDArrayConstructorTrait;
import io.github.hakkelt.ndarrays.NDArray;

class TestFloatNDArrayMask implements FloatNDArrayConstructorTrait, ByteNDArrayConstructorTrait, ConstructorTrait {
    NDArray<Float> array, masked;
    NDArray<Byte> mask;

    @BeforeEach
    void setup() {
        array = createFloatNDArray(new int[]{ 4, 5, 3 });
        array.applyWithLinearIndices((value, index) -> (float)index);
        mask = createByteNDArray(array.mapWithLinearIndices((value, index) -> (float)index % 2));
        masked = array.mask(mask);
    }

    @Test
    void testMask() {
        masked.forEachWithLinearIndices((value, index) -> assertEquals(value, index * 2 + 1));
        masked.fill(0);
        array.forEachWithLinearIndices((value, index) -> assertEquals(value, index % 2 == 0 ? index : 0));
    }

    @Test
    void testMaskWithPredicate() {
        NDArray<Float> masked = array.mask(value -> value > 20);
        masked.forEach((value) -> assertTrue(value > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(value <= 20));
    }

    @Test
    void testMaskWithPredicateWithLinearIndices() {
        NDArray<Float> masked = array.maskWithLinearIndices((value, i) -> value > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(value > 20 && i < 10));
        masked.fill(0);
        array.forEachWithLinearIndices((value, i) -> assertTrue(value <= 20 || i >= 10));
    }

    @Test
    void testMaskWithPredicateWithCartesianIndices() {
        NDArray<Float> masked = array.maskWithCartesianIndices((value, idx) -> value > 20 && idx[0] == 0);
        masked.forEach(value -> assertTrue(value > 20));
        masked.fill(0);
        array.forEachWithCartesianIndices((value, idx) -> assertTrue(value <= 20 || idx[0] != 0));
    }

    @Test
    void testMaskMask() {
        NDArray<Byte> mask2 = createByteNDArray(masked.map(value -> value > 20 ? (float)1 : (float)0));
        NDArray<Float> masked2 = masked.mask(mask2);
        masked2.forEach((value) -> assertTrue(value > 20));
    }

    @Test
    void testMaskInverseMask() {
        NDArray<Byte> mask2 = createByteNDArray(masked.map(value -> value > 20 ? (float)1 : (float)0));
        NDArray<Float> masked2 = masked.inverseMask(mask2);
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
    void testSetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> masked.set(0, 1,1,0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 3, 1),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Float.class, masked.eltype());
    }

    @Test
    void testToArray() {
        Float[] arr = (Float[])masked.toArray();
        for (int i = 0; i < masked.dims(0); i++)
            assertEquals(array.get(1 + i * 2), arr[i]);
    }

    @Test
    void testEqual() {
        NDArray<Float> array2 = createFloatNDArray(masked);
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
        for (Float value : masked)
            assertEquals(masked.get(linearIndex++), value);
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
        Float sum = masked.stream().parallel()
            .reduce(0.f, (acc, item) -> acc + item);
        float acc = 0;
        for (int i = 1; i < array.length(); i += 2)
            acc += array.get(i);
        assertEquals(acc, sum);
    }

    @Test
    void testCollector() {
        NDArray<Float> increased = masked.stream()
            .map((value) -> value + 1)
            .collect(getFloatNDArrayCollector(masked.dims()));
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i) + 1, increased.get(i));
    }

    @Test
    void testParallelCollector() {
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value + 1)
            .collect(getFloatNDArrayCollector(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) + 1, increased.get(i));
    }

    @Test
    void testToString() {
        String str = masked.toString();
        assertEquals("basic NDArray<Float>(30)", str);
    }

    @Test
    void testcontentToString() {
        String str = masked.contentToString();
        String numberFormat = "%8.5e\t";
        String line = masked.streamLinearIndices()
            .mapToObj(i -> String.format(numberFormat, i * 2 + 1.))
            .reduce("", (a, b) -> String.join("", a, b));
        String expected = new StringBuilder()
            .append("basic NDArray<Float>(30)")
            .append(System.lineSeparator())
            .append(line)
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testAddArrayToMasked() {
        NDArray<Float> array2 = createFloatNDArray(masked);
        NDArray<Float> array3 = masked.add(array2);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i) * 2, array3.get(i));
    }

    @Test
    void testAddMaskedToArray() {
        NDArray<Float> array2 = createFloatNDArray(masked);
        NDArray<Float> array3 = array2.add(masked);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i) * 2, array3.get(i));
    }

    @Test
    void testAddMaskedToMasked() {
        NDArray<Float> masked2 = array.mask(mask);
        NDArray<Float> array3 = masked2.add(masked);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i) * 2, array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Float> masked2 = masked.add(5);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i) + 5, masked2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Float> array2 = createFloatNDArray(array);
        NDArray<Float> masked2 = array2.mask(mask);
        NDArray<Float> array3 = masked2.add(masked, 5.3, masked2, 3);
        for (int i = 0; i < masked.length(); i++) {
            float expected = masked.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Float> array2 = createFloatNDArray(array);
        NDArray<Float> masked2 = array2.mask(mask);
        masked2.addInplace(masked);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i) * 2, masked2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Float> array2 = createFloatNDArray(array);
        NDArray<Float> masked2 = array2.mask(mask);
        masked2.addInplace(5);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i) + 5, masked2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Float> array2 = createFloatNDArray(array);
        NDArray<Float> masked2 = array2.mask(mask);
        masked2.addInplace(masked, 5.3, masked2, 3);
        for (int i = 0; i < masked.length(); i++) {
            float expected = masked.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
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
            .reduce((float)0, (acc, item) -> acc + item);
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
        NDArray<Float> array2 = masked.copy();
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(masked.get(5), array2.get(5));
    }

    @Test
    void testFillReal() {
        masked.fill(3);
        for (Float elem : masked)
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
        NDArray<Float> pArray = masked.permuteDims(0);
        Float[] arr = (Float[])pArray.toArray();
        for (int i = 0; i < pArray.dims(0); i++)
            assertEquals(array.get(i * 2 + 1), arr[i]);
    }

    @Test
    void testConcatenate() {
        NDArray<Float> array2 = createFloatNDArray(5).fill(1);
        NDArray<Float> array3 = masked.concatenate(0, array2);
        for (int i = 0; i < masked.dims(0); i++)
            assertEquals(masked.get(i), array3.get(i));
        for (int i = masked.dims(0); i < masked.dims(0) + array2.dims(0); i++)
            assertEquals(1, array3.get(i));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Float> array2 = masked.copy().fill(1).slice("1:1");
        NDArray<Float> array3 = createFloatNDArray(5).permuteDims(0);
        NDArray<Float> array4 = createFloatNDArray(3, 3).fill(2).reshape(9);
        NDArray<Float> array5 = masked.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = masked.dims(0);
        for (int i = start; i < end; i++)
            assertEquals(masked.get(i), array5.get(i));
        start = end;
        end += array2.dims(0);
        for (int i = start; i < end; i++)
            assertEquals(1, array5.get(i));
        start = end;
        end += array3.dims(0);
        for (int i = start; i < end; i++)
            assertEquals(0, array5.get(i));
        start = end;
        end += array4.dims(0);
        for (int i = start; i < end; i++)
            assertEquals(2, array5.get(i));
    }
}
