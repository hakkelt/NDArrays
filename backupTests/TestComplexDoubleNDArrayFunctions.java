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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.ComplexNDArray;
import io.github.hakkelt.ndarrays.Errors;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.basic.BasicComplexDoubleNDArray;
import io.github.hakkelt.ndarrays.basic.BasicComplexFloatNDArray;
import io.github.hakkelt.ndarrays.basic.BasicDoubleNDArray;
import io.github.hakkelt.ndarrays.basic.BasicFloatNDArray;

class TestComplexDoubleNDArrayFunctions implements NameTrait {
    ComplexNDArray<Double> array;

    @BeforeEach
    void setup() {
        array = new BasicComplexDoubleNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> new Complex(index, -index));
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals(new Complex(55, -55), array.get(-5));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals(new Complex(linearIndex, -linearIndex), array.get(2, -3, -1));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals(new Complex(linearIndex, -linearIndex), array.get(2, -3, -1));
        array.set(new Complex(1, 1), linearIndex);
        assertEquals(new Complex(1, 1), array.get(2, -3, -1));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        array.set(new Complex(1, 1), 2, -3, -1);
        assertEquals(new Complex(1, 1), array.get(linearIndex));
    }

    @Test
    void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(60));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(-61));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1,1,3));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1,-6,1));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(zero, 60));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(zero, -61));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, array.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(zero, 1,1,3));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(zero, 1,-6,1));
        assertEquals(
            String.format(Errors.CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1,1,1,0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1,1));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooMany() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> array.set(zero, 1,1,1,0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnough() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> array.set(zero, 1,1));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testDType() {
        assertEquals(Complex.class, array.dtype());
    }

    @Test
    void testToArray() {
        Complex[][][] arr = (Complex[][][])array.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0][0].length; i++)
            for (int j = 0; j < arr[0].length; j++)
                for (int k = 0; k < arr.length; k++) {
                    assertEquals(new Complex(linearIndex, -linearIndex), arr[k][j][i]);
                    linearIndex++;
                }
    }

    @Test
    void testNotEqual() {
        assertNotEquals(array, new int[0]); // NOSONAR
        assertNotEquals(array, new BasicFloatNDArray(array.shape()));
        assertNotEquals(array, new BasicComplexFloatNDArray(4, 15));
        assertNotEquals(array, new BasicComplexFloatNDArray(4, 3, 5));
    }

    @Test
    void testEqual() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        assertEquals(array, array2);
        array2.set(new Complex(0,0), 10);
        assertNotEquals(array, array2);
    }

    @Test
    void testEqualComplexFloatNDArray() {
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array);
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
        for (Complex value : array)
            assertEquals(new Complex(linearIndex, -linearIndex++), value);

        var it = array.iterator();
        for (int i = 0; i < array.length(); i++)
            it.next();
        Exception exception = assertThrows(NoSuchElementException.class, () -> it.next());
        assertEquals(Errors.ITERATOR_OUT_OF_BOUNDS, exception.getMessage());
    }
    
    @Test
    void testSpliterator() {
        var recorder = new ArrayList<Complex>();
        Consumer<Complex> consumer = value -> recorder.add(value); 

        var spliterator = array.spliterator();

        assertTrue(spliterator.tryAdvance(consumer));
        assertEquals(array.get(0), recorder.get(0));

        spliterator.forEachRemaining(consumer);
        array.streamLinearIndices().forEach(i -> assertEquals(array.get(i), recorder.get(i)));

        assertFalse(spliterator.tryAdvance(consumer));
        spliterator.forEachRemaining(value -> fail());

        spliterator = array.spliterator();
        for (int i = 0; i < 5; i++) {
            spliterator = spliterator.trySplit();
            assertNotNull(spliterator);
        }
        assertNull(spliterator.trySplit());
    }

    @Test
    void testStream() {
        final int[] linearIndex = new int[1];
        array.stream().forEach((value) -> {
            assertEquals(new Complex(linearIndex[0], -linearIndex[0]), value);
            linearIndex[0]++;
        });
    }

    @Test
    void testParallelStream() {
        Complex sum = array.stream().parallel()
            .reduce(new Complex(0,0), (acc, item) -> acc.add(item));
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(new Complex(GaussSum, -GaussSum), sum);
    }

    @Test
    void testByteCollector() {
        NDArray<?> array2 = IntStream.range(0, array.length()).mapToObj(i -> Byte.valueOf((byte) i))
            .collect(BasicComplexDoubleNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(new Complex(i), array2.get(i));
    }

    @Test
    void testShortCollector() {
        NDArray<?> array2 = IntStream.range(0, array.length()).mapToObj(i -> Short.valueOf((short) i))
            .collect(BasicComplexDoubleNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(new Complex(i), array2.get(i));
    }

    @Test
    void testIntegerCollector() {
        NDArray<?> array2 = IntStream.range(0, array.length()).mapToObj(i -> Integer.valueOf(i))
            .collect(BasicComplexDoubleNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(new Complex(i), array2.get(i));
    }

    @Test
    void testLongCollector() {
        NDArray<?> array2 = IntStream.range(0, array.length()).mapToObj(i -> Long.valueOf(i))
            .collect(BasicComplexDoubleNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(new Complex(i), array2.get(i));
    }

    @Test
    void testFloatCollector() {
        NDArray<?> array2 = IntStream.range(0, array.length()).mapToObj(i -> Float.valueOf((float) i))
            .collect(BasicComplexDoubleNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(new Complex(i), array2.get(i));
    }

    @Test
    void testDoubleCollector() {
        NDArray<?> array2 = IntStream.range(0, array.length()).mapToObj(i -> Double.valueOf((double) i))
            .collect(BasicComplexDoubleNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(new Complex(i), array2.get(i));
    }

    @Test
    void testComplexCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = array.stream()
            .map((value) -> value.add(one))
            .collect(BasicComplexDoubleNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(one), increased.get(i));
    }

    @Test
    void testParallelCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value.add(one))
            .collect(BasicComplexDoubleNDArray.getCollector(array.shape()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(one), increased.get(i));
    }

    @Test
    void testToString() {
        String str = array.toString();
        assertEquals(array.getNamePrefix() + " NDArray<Complex Double>(4 × 5 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = array.contentToString();
        String lineFormat = "%8.5e%+8.5ei\t%8.5e%+8.5ei\t%8.5e%+8.5ei\t%8.5e%+8.5ei\t%8.5e%+8.5ei\t%n";
        String expected = new StringBuilder()
            .append(array.getNamePrefix() + " NDArray<Complex Double>(4 × 5 × 3)" + System.lineSeparator())
            .append("[:, :, 0] =" + System.lineSeparator())
            .append(String.format(lineFormat, 0.0e+00, +0.0e+00, 4.0e+00, -4.0e+00, 8.0e+00, -8.0e+00, 1.2e+01, -1.2e+01, 1.6e+01, -1.6e+01))
            .append(String.format(lineFormat, 1.0e+00, -1.0e+00, 5.0e+00, -5.0e+00, 9.0e+00, -9.0e+00, 1.3e+01, -1.3e+01, 1.7e+01, -1.7e+01))
            .append(String.format(lineFormat, 2.0e+00, -2.0e+00, 6.0e+00, -6.0e+00, 1.0e+01, -1.0e+01, 1.4e+01, -1.4e+01, 1.8e+01, -1.8e+01))
            .append(String.format(lineFormat, 3.0e+00, -3.0e+00, 7.0e+00, -7.0e+00, 1.1e+01, -1.1e+01, 1.5e+01, -1.5e+01, 1.9e+01, -1.9e+01))
            .append(System.lineSeparator())
            .append("[:, :, 1] =" + System.lineSeparator())
            .append(String.format(lineFormat, 2.0e+01, -2.0e+01, 2.4e+01, -2.4e+01, 2.8e+01, -2.8e+01, 3.2e+01, -3.2e+01, 3.6e+01, -3.6e+01))
            .append(String.format(lineFormat, 2.1e+01, -2.1e+01, 2.5e+01, -2.5e+01, 2.9e+01, -2.9e+01, 3.3e+01, -3.3e+01, 3.7e+01, -3.7e+01))
            .append(String.format(lineFormat, 2.2e+01, -2.2e+01, 2.6e+01, -2.6e+01, 3.0e+01, -3.0e+01, 3.4e+01, -3.4e+01, 3.8e+01, -3.8e+01))
            .append(String.format(lineFormat, 2.3e+01, -2.3e+01, 2.7e+01, -2.7e+01, 3.1e+01, -3.1e+01, 3.5e+01, -3.5e+01, 3.9e+01, -3.9e+01))
            .append(System.lineSeparator())
            .append("[:, :, 2] =" + System.lineSeparator())
            .append(String.format(lineFormat, 4.0e+01, -4.0e+01, 4.4e+01, -4.4e+01, 4.8e+01, -4.8e+01, 5.2e+01, -5.2e+01, 5.6e+01, -5.6e+01))
            .append(String.format(lineFormat, 4.1e+01, -4.1e+01, 4.5e+01, -4.5e+01, 4.9e+01, -4.9e+01, 5.3e+01, -5.3e+01, 5.7e+01, -5.7e+01))
            .append(String.format(lineFormat, 4.2e+01, -4.2e+01, 4.6e+01, -4.6e+01, 5.0e+01, -5.0e+01, 5.4e+01, -5.4e+01, 5.8e+01, -5.8e+01))
            .append(String.format(lineFormat, 4.3e+01, -4.3e+01, 4.7e+01, -4.7e+01, 5.1e+01, -5.1e+01, 5.5e+01, -5.5e+01, 5.9e+01, -5.9e+01))
            .append(System.lineSeparator())
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testApply() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array).apply(value -> value.atan());
        for (int i = 1; i < array.length(); i++) {
            assertTrue(Math.abs(array.get(i).atan().getReal() - array2.get(i).getReal()) / array2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(array.get(i).atan().getImaginary() - array2.get(i).getImaginary()) / array2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testApplyWithLinearIndices() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array).applyWithLinearIndices((value, index) -> value.atan().add(index));
        for (int i = 1; i < array.length(); i++) {
            assertTrue(Math.abs(array.get(i).atan().add(i).getReal() - array2.get(i).getReal()) / array2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(array.get(i).atan().add(i).getImaginary() - array2.get(i).getImaginary()) / array2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testApplyWithCartesianIndex() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array).applyWithCartesianIndices((value, indices) -> value.atan().add(indices[0]));
        for (int i = 0; i < array.shape(0); i++)
            for (int j = 0; j < array.shape(1); j++)
                for (int k = 0; k < array.shape(2); k++) {
                    if (i == 0 && j == 0 && k == 0) continue;
                    assertTrue(Math.abs(array.get(i,j,k).atan().add(i).getReal() - array2.get(i,j,k).getReal()) / array2.get(i,j,k).getReal() < 1e-6);
                    assertTrue(Math.abs(array.get(i,j,k).atan().add(i).getImaginary() - array2.get(i,j,k).getImaginary()) / array2.get(i,j,k).getImaginary() < 1e-6);
                }
    }

    @Test
    void testMap() {
        ComplexNDArray<Double> array2 = array.map(value -> value.atan());
        for (int i = 1; i < array.length(); i++) {
            assertTrue(Math.abs(array.get(i).atan().getReal() - array2.get(i).getReal()) / array2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(array.get(i).atan().getImaginary() - array2.get(i).getImaginary()) / array2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testMapWithLinearIndices() {
        ComplexNDArray<Double> array2 = array.mapWithLinearIndices((value, index) -> value.atan().add(index));
        for (int i = 1; i < array.length(); i++) {
            assertTrue(Math.abs(array.get(i).atan().add(i).getReal() - array2.get(i).getReal()) / array2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(array.get(i).atan().add(i).getImaginary() - array2.get(i).getImaginary()) / array2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testMapWithCartesianIndex() {
        ComplexNDArray<Double> array2 = array.mapWithCartesianIndices((value, indices) -> value.atan().add(indices[0]));
        for (int i = 0; i < array.shape(0); i++)
            for (int j = 0; j < array.shape(1); j++)
                for (int k = 0; k < array.shape(2); k++) {
                    if (i == 0 && j == 0 && k == 0) continue;
                    assertTrue(Math.abs(array.get(i,j,k).atan().add(i).getReal() - array2.get(i,j,k).getReal()) / array2.get(i,j,k).getReal() < 1e-6);
                    assertTrue(Math.abs(array.get(i,j,k).atan().add(i).getImaginary() - array2.get(i,j,k).getImaginary()) / array2.get(i,j,k).getImaginary() < 1e-6);
                }
    }

    @Test
    void testForEach() {
        ComplexNDArray<Double> array2 = array.similar().fill(1);
        array2.forEach(value -> assertEquals(Complex.ONE, value));
    }

    @Test
    void testForEachSequential() {
        AtomicInteger i = new AtomicInteger(0);
        array.forEachSequential(value -> assertEquals(array.get(i.getAndIncrement()), value));
    }

    @Test
    void testForEachWithLinearIndices() {
        array.forEachWithLinearIndices((value, index) -> assertEquals(array.get(index), value));
    }

    @Test
    void testForEachWithCartesianIndex() {
        array.forEachWithCartesianIndices((value, indices) -> assertEquals(array.get(indices), value));
    }

    @Test
    void testAddComplexDoubleNDArray() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        ComplexNDArray<Double> array3 = array.add(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddDoubleNDArray() {
        NDArray<Double> array2 = new BasicDoubleNDArray(array.abs());
        ComplexNDArray<Double> array3 = array.add(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(array.get(i).abs()), array3.get(i));
    }

    @Test
    void testAddFloatNDArray() {
        NDArray<Float> array2 = new BasicFloatNDArray(array.abs());
        ComplexNDArray<Double> array3 = array.add(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add((float)array.get(i).abs()), array3.get(i));
    }

    @Test
    void testAddScalar() {
        ComplexNDArray<Double> array2 = array.add(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(5), array2.get(i));
    }

    @Test
    void testAddMultiple() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        ComplexNDArray<Double> array3 = array.add(array2, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.addInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(2), array2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.addInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(5), array2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.addInplace(array, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array2.get(i)).abs() < 1e5);
        }
    }

    @Test
    void testSubtract() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        ComplexNDArray<Double> array3 = array.subtract(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals(new Complex(0,0), array3.get(i));
    }

    @Test
    void testSubtractScalar() {
        ComplexNDArray<Double> array2 = array.subtract(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).subtract(5), array2.get(i));
    }

    @Test
    void testSubtractMultiple() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        ComplexNDArray<Double> array3 = array.subtract(array2, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).multiply(-1).subtract(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e5);
        }
    }

    @Test
    void testSubtractInplace() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.subtractInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals(new Complex(0,0), array2.get(i));
    }

    @Test
    void testSubtractInplaceScalar() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.subtractInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).subtract(5), array2.get(i));
    }

    @Test
    void testSubtractInplaceMultiple() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.subtractInplace(array, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).multiply(-1).subtract(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array2.get(i)).abs() < 1e5);
        }
    }

    @Test
    void testMultiply() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        ComplexNDArray<Double> array3 = array.multiply(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(array.get(i)), array3.get(i));
    }

    @Test
    void testMultiplyScalar() {
        ComplexNDArray<Double> array2 = array.multiply(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(5), array2.get(i));
    }

    @Test
    void testMultiplyMultiple() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        ComplexNDArray<Double> array3 = array.multiply(array, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).multiply(array.get(i)).multiply((float)5.3)
                .multiply(array2.get(i)).multiply(new Complex(3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e7);
        }
    }

    @Test
    void testMultiplyInplace() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.multiplyInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(array.get(i)), array2.get(i));
    }

    @Test
    void testMultiplyInplaceScalar() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.multiplyInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(5), array2.get(i));
    }

    @Test
    void testMultiplyInplaceMultiple() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.multiplyInplace(array, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).multiply(array.get(i)).multiply((float)5.3)
                .multiply(array.get(i)).multiply(new Complex(3,1));
            if (expected.abs() == 0)
                assertTrue(expected.subtract(array2.get(i)).abs() < 1e7);
            else
                assertTrue(expected.subtract(array2.get(i)).divide(expected.abs()).abs() < 1e7);
        }
    }

    @Test
    void testDivide() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        ComplexNDArray<Double> array3 = array.divide(array2);
        for (int i = 0; i < array.length(); i++) {
            if (array.get(i).equals(new Complex(0,0)))
                assertTrue(array3.get(i).isNaN());
            else
                assertEquals(new Complex(1,0), array3.get(i));
        }
    }

    @Test
    void testDivideScalar() {
        ComplexNDArray<Double> array2 = array.divide(5);
        for (int i = 0; i < array.length(); i++)
            assertTrue(array.get(i).divide(5).subtract(array2.get(i)).abs() < 1e-5);
    }

    @Test
    void testDivideMultiple() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        ComplexNDArray<Double> array3 = array.divide(array, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).divide(array.get(i)).divide((float)5.3)
                .divide(array2.get(i)).divide(new Complex(3,1));
            if (expected.isNaN())
                assertTrue(array3.get(i).isNaN());
            else
                assertTrue(expected.subtract(array3.get(i)).abs() < 1e7);
        }
    }

    @Test
    void testDivideInplace() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.divideInplace(array);
        for (int i = 0; i < array.length(); i++)
        if (array.get(i).equals(new Complex(0,0)))
            assertTrue(array2.get(i).isNaN());
        else
            assertEquals(new Complex(1,0), array2.get(i));
    }

    @Test
    void testDivideInplaceScalar() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.divideInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertTrue(array.get(i).divide(5).subtract(array2.get(i)).abs() < 1e-5);
    }

    @Test
    void testDivideInplaceMultiple() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array);
        array2.divideInplace(array, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).divide(array.get(i)).divide((float)5.3)
                .divide(array.get(i)).divide(new Complex(3,1));
            if (expected.isNaN())
                assertTrue(array2.get(i).isNaN());
            else
                assertTrue(expected.subtract(array2.get(i)).abs() < 1e7);
        }
    }

    @Test
    void testSum() {
        Complex sum = array.sum();
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(new Complex(GaussSum, -GaussSum), sum);
    }

    @Test
    void testSum1D() {
        ComplexNDArray<Double> sum = array.sum(1);
        for (int i = 0; i < sum.shape(0); i++) {
            for (int j = 0; j < sum.shape(1); j++) {
                double GaussSum = (array.get(i,0,j).getReal() + array.get(i,-1,j).getReal()) * 5 / 2;
                assertEquals(new Complex(GaussSum, -GaussSum), sum.get(i,j));
            }
        }
    }

    @Test
    void testSum2D() {
        ComplexNDArray<Double> sum = array.sum(2, 1);
        for (int i = 0; i < sum.length(); i++) {
            double GaussSum = (array.get(i,0,0).getReal() + array.get(i,-1,-1).getReal()) * (5 * 3) / 2;
            assertEquals(new Complex(GaussSum, -GaussSum), sum.get(i));
        }
    }

    @Test
    void testNegativeNorm() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.norm(-1));
        assertEquals(Errors.NEGATIVE_NORM, exception.getMessage());
    }

    @Test
    void test0Norm() {
        array.slice(":", 0, ":").fill(0);
        double norm = array.stream()
            .filter(value -> value != Complex.ZERO)
            .count();
        assertEquals(norm, array.norm(0));
    }

    @Test
    void test1Norm() {
        double norm = array.stream()
            .mapToDouble(value -> value.abs())
            .reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, array.norm(1));
    }

    @Test
    void test2Norm() {
        double norm = Math.sqrt(array.stream()
            .mapToDouble(value -> Math.pow(value.abs(), 2))
            .reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, array.norm());
    }

    @Test
    void testPQuasinorm() {
        double norm = Math.pow(array.stream()
            .mapToDouble(value -> Math.pow(value.abs(), 0.5))
            .reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, array.norm(0.5));
    }

    @Test
    void testPNorm() {
        double norm = Math.pow(array.stream()
            .mapToDouble(value -> Math.pow(value.abs(), 3.5))
            .reduce(0., (acc, item) -> acc + item), 1 / 3.5);
        assertEquals(norm, array.norm(3.5));
    }

    @Test
    void testInfNorm() {
        double norm = array.stream()
            .mapToDouble(value -> value.abs())
            .max().getAsDouble();
        assertEquals(norm, array.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testCopy() {
        ComplexNDArray<Double> array2 = array.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i), array2.get(i));
        array2.set(new Complex(0,0), 5);
        assertNotEquals(array.get(5), array2.get(5));
    }

    @Test
    void testSimilar() {
        ComplexNDArray<Double> array2 = array.similar();
        for (int i = 0; i < array.length(); i++)
            assertEquals(Complex.ZERO, array2.get(i));
        array2.set(new Complex(0,0), 5);
        assertNotEquals(array.get(5), array2.get(5));
    }

    @Test
    void testFillComplex() {
        array.fill(new Complex(3,3));
        for (Complex elem : array)
            assertEquals(new Complex(3, 3), elem);
    }

    @Test
    void testFillReal() {
        array.fill(3);
        for (Complex elem : array)
            assertEquals(new Complex(3, 0), elem);
    }

    @Test
    void testPermuteDimsTooShortPermutationVector() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,2));
        assertEquals(
            String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsTooLongPermutationVector() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,2,1,4));
        assertEquals(
            String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2, 1, 4]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsRepeatedDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,1,1));
        assertEquals(
            String.format(Errors.INVALID_PERMUTATOR, "[0, 1, 1]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsNegativeDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,-1,1));
        assertEquals(
            String.format(Errors.INVALID_PERMUTATOR, "[0, -1, 1]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsTooLargeDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,3,1));
        assertEquals(
            String.format(Errors.INVALID_PERMUTATOR, "[0, 3, 1]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    void testConcatenate() {
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(4, 2, 3).fill(1);
        ComplexNDArray<Double> array3 = array.concatenate(1, array2);
        for (int i = 0; i < array.shape(0); i++)
            for (int j = 0; j < array.shape(1); j++)
                for (int k = 0; k < array.shape(2); k++)
                    assertEquals(array.get(i, j, k), array3.get(i, j, k));
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = array.shape(1); j < array.shape(1) + array2.shape(1); j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertEquals(new Complex(1, 0), array3.get(i, j, k));
    }

    @Test
    void testConcatenateMultiple() {
        ComplexNDArray<Double> array2 = array.copy().fill(1).slice(":", "1:3", ":");
        ComplexNDArray<Double> array3 = new BasicComplexDoubleNDArray(3, 4, 4).permuteDims(2, 1, 0);
        ComplexNDArray<Double> array4 = new BasicComplexDoubleNDArray(12).fill(new Complex(2, -2)).reshape(4, 1, 3);
        ComplexNDArray<Double> array5 = array.concatenate(1, array2, array3, array4);
        int start = 0;
        int end = array.shape(1);
        for (int i = 0; i < array.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array.shape(2); k++)
                    assertEquals(array.get(i, j, k), array5.get(i, j, k));
        start = end;
        end += array2.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertEquals(new Complex(1, 0), array5.get(i, j, k));
        start = end;
        end += array3.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertEquals(new Complex(0, 0), array5.get(i, j, k));
        start = end;
        end += array4.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertEquals(new Complex(2, -2), array5.get(i, j, k));
    }

    @Test
    void testReal() {
        NDArray<Double> real = array.real();
        array.streamLinearIndices()
            .forEach(i -> assertEquals(array.get(i).getReal(), real.get(i)));
    }

    @Test
    void testImag() {
        NDArray<Double> imag = array.imaginary();
        array.streamLinearIndices()
            .forEach(i -> assertEquals(array.get(i).getImaginary(), imag.get(i)));
    }

    @Test
    void testAbs() {
        NDArray<Double> abs = array.abs();
        array.streamLinearIndices()
            .forEach(i -> assertTrue(array.get(i).abs() - abs.get(i) < 1e-5));
    }

    @Test
    void testAngle() {
        NDArray<Double> angle = array.angle();
        array.streamLinearIndices()
            .forEach(i -> assertTrue(array.get(i).getArgument() - angle.get(i) < 1e-5));
    }
}
