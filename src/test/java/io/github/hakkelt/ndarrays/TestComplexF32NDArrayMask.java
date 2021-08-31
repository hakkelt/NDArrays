package io.github.hakkelt.ndarrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestComplexF32NDArrayMask {
    ComplexNDArray<Float> array, masked;
    NDArray<Byte> mask;

    @BeforeEach
    void setup() {
        array = new ComplexF32NDArray(new int[]{ 4, 5, 3 });
        array.applyWithLinearIndices((value, index) -> new Complex(index, -index));
        mask = new RealInt8NDArray(array.abs().mapWithLinearIndices((value, index) -> (float)index % 2));
        masked = array.mask(mask);
    }

    @Test
    void testMask() {
        masked.forEachWithLinearIndices((value, index) -> assertEquals(value, new Complex(index * 2 + 1, -index * 2 - 1)));
        masked.fill(0);
        array.forEachWithLinearIndices((value, index) -> assertEquals(value, index % 2 == 0 ? new Complex(index, -index) : Complex.ZERO));
    }

    @Test
    void testMaskWithPredicate() {
        ComplexNDArray<Float> masked = array.mask(value -> value.abs() > 20);
        masked.forEach((value) -> assertTrue(value.abs() > 20));
        masked.fill(0);
        array.forEach(value -> assertTrue(value.abs() <= 20));
    }

    @Test
    void testMaskWithPredicateWithLinearIndices() {
        ComplexNDArray<Float> masked = array.maskWithLinearIndices((value, i) -> value.abs() > 20 && i < 10);
        masked.forEachWithLinearIndices((value, i) -> assertTrue(value.abs() > 20 && i < 10));
        masked.fill(0);
        array.forEachWithLinearIndices((value, i) -> assertTrue(value.abs() <= 20 || i >= 10));
    }

    @Test
    void testMaskWithPredicateWithCartesianIndices() {
        ComplexNDArray<Float> masked = array.maskWithCartesianIndices((value, idx) -> value.abs() > 20 && idx[0] == 0);
        masked.forEach(value -> assertTrue(value.abs() > 20));
        masked.fill(0);
        array.forEachWithCartesianIndices((value, idx) -> assertTrue(value.abs() <= 20 || idx[0] != 0));
    }

    @Test
    void testMaskMask() {
        NDArray<Byte> mask2 = new RealInt8NDArray(masked.abs().map(value -> value > 20 ? (float)1 : (float)0));
        ComplexNDArray<Float> masked2 = masked.mask(mask2);
        masked2.forEach((value) -> assertTrue(value.abs() > 20));
    }

    @Test
    void testMaskInverseMask() {
        NDArray<Byte> mask2 = new RealInt8NDArray(masked.abs().map(value -> value > 20 ? (float)1 : (float)0));
        ComplexNDArray<Float> masked2 = masked.inverseMask(mask2);
        masked2.forEach((value) -> assertTrue(value.abs() <= 20));
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals(new Complex(55, -55), masked.get(-3));
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
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> masked.set(zero, 30));
        assertEquals(
            String.format(Errors.LINEAR_BOUNDS_ERROR, masked.length(), 30),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> masked.set(zero, -31));
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
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> masked.set(zero, 1,1,0));
        assertEquals(
            String.format(Errors.DIMENSION_MISMATCH, 3, 1),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Complex.class, masked.eltype());
    }

    @Test
    void testToArray() {
        Complex[] arr = (Complex[])masked.toArray();
        for (int i = 0; i < masked.dims(0); i++)
            assertEquals(array.get(1 + i * 2), arr[i]);
    }

    @Test
    void testEqual() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(masked);
        assertEquals(masked, array2);
        array2.set(new Complex(0,0), 5);
        assertNotEquals(masked, array2);
    }

    @Test
    void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { masked.hashCode(); });
    }

    @Test
    void testIterator() {
        int linearIndex = 0;
        for (Complex value : masked)
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
        Complex sum = masked.stream().parallel()
            .reduce(Complex.ZERO, (acc, item) -> acc.add(item));
        Complex acc = Complex.ZERO;
        for (int i = 1; i < array.length(); i += 2)
            acc = acc.add(array.get(i));
        assertEquals(acc, sum);
    }

    @Test
    void testCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<Complex> increased = masked.stream()
            .map((value) -> value.add(one))
            .collect(ComplexF32NDArray.getCollector(masked.dims()));
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i).add(one), increased.get(i));
    }

    @Test
    void testParallelCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value.add(one))
            .collect(ComplexF32NDArray.getCollector(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(one), increased.get(i));
    }

    @Test
    void testToString() {
        String str = masked.toString();
        assertEquals("simple NDArray<Complex Float>(30)", str);
    }

    @Test
    void testcontentToString() {
        String str = masked.contentToString();
        String numberFormat = "%8.5e%+8.5ei\t";
        String line = masked.streamLinearIndices()
            .mapToObj(i -> String.format(numberFormat, i * 2 + 1., -i * 2 - 1.))
            .reduce("", (a, b) -> String.join("", a, b));
        String expected = new StringBuilder()
            .append("simple NDArray<Complex Float>(30)")
            .append(System.lineSeparator())
            .append(line)
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testApply() {
        NDArray<Complex> masked2 = new ComplexF32NDArray(array).mask(mask).apply(value -> value.atan());
        for (int i = 1; i < masked.length(); i++) {
            assertTrue(Math.abs(masked.get(i).atan().getReal() - masked2.get(i).getReal()) / masked2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(masked.get(i).atan().getImaginary() - masked2.get(i).getImaginary()) / masked2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testApplyWithLinearIndices() {
        NDArray<Complex> masked2 = new ComplexF32NDArray(array).mask(mask).applyWithLinearIndices((value, index) -> value.atan().add(index));
        for (int i = 1; i < masked.length(); i++) {
            assertTrue(Math.abs(masked.get(i).atan().add(i).getReal() - masked2.get(i).getReal()) / masked2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(masked.get(i).atan().add(i).getImaginary() - masked2.get(i).getImaginary()) / masked2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testMap() {
        NDArray<Complex> masked2 = masked.map(value -> value.atan());
        for (int i = 1; i < masked.length(); i++) {
            assertTrue(Math.abs(masked.get(i).atan().getReal() - masked2.get(i).getReal()) / masked2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(masked.get(i).atan().getImaginary() - masked2.get(i).getImaginary()) / masked2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testMapWithLinearIndices() {
        NDArray<Complex> masked2 = masked.mapWithLinearIndices((value, index) -> value.atan().add(index));
        for (int i = 1; i < masked.length(); i++) {
            assertTrue(Math.abs(masked.get(i).atan().add(i).getReal() - masked2.get(i).getReal()) / masked2.get(i).getReal() < 1e-6);
            assertTrue(Math.abs(masked.get(i).atan().add(i).getImaginary() - masked2.get(i).getImaginary()) / masked2.get(i).getImaginary() < 1e-6);
        }
    }

    @Test
    void testForEach() {
        AtomicInteger i = new AtomicInteger(0);
        masked.forEach(value -> assertEquals(masked.get(i.getAndIncrement()), value));
    }

    @Test
    void testForEachWithLinearIndices() {
        masked.forEachWithLinearIndices((value, index) -> assertEquals(masked.get(index), value));
    }

    @Test
    void testForEachWithCartesianIndex() {
        masked.forEachWithCartesianIndices((value, indices) -> assertEquals(masked.get(indices), value));
    }

    @Test
    void testAdd() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(masked);
        ComplexNDArray<Float> array3 = masked.add(array2);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddArrayToMasked() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(masked);
        ComplexNDArray<Float> array3 = masked.add(array2);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddMaskedToArray() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(masked);
        ComplexNDArray<Float> array3 = array2.add(masked);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddMaskedToMasked() {
        ComplexNDArray<Float> masked2 = array.mask(mask);
        ComplexNDArray<Float> array3 = masked2.add(masked);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddScalar() {
        ComplexNDArray<Float> masked2 = masked.add(5);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i).add(5), masked2.get(i));
    }

    @Test
    void testAddMultiple() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(array);
        ComplexNDArray<Float> masked2 = array2.mask(mask);
        ComplexNDArray<Float> array3 = masked2.add(masked, 5.3, masked2, new Complex(3,1));
        for (int i = 0; i < masked.length(); i++) {
            Complex expected = masked.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(array);
        ComplexNDArray<Float> masked2 = array2.mask(mask);
        masked2.addInplace(masked);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i).multiply(2), masked2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(array);
        ComplexNDArray<Float> masked2 = array2.mask(mask);
        masked2.addInplace(5);
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i).add(5), masked2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(array);
        ComplexNDArray<Float> masked2 = array2.mask(mask);
        masked2.addInplace(masked, 5.3, masked2, new Complex(3,1));
        for (int i = 0; i < masked.length(); i++) {
            Complex expected = masked.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(masked2.get(i)).abs() < 1e5);
        }
    }

    @Test
    void test0Norm() {
        double norm = masked.stream()
            .filter(value -> value != Complex.ZERO)
            .count();
        assertEquals(norm, masked.norm(0));
    }

    @Test
    void test1Norm() {
        double norm = masked.stream()
            .map(value -> value.abs())
            .reduce(0., (acc, item) -> acc + item);
        assertTrue(Math.abs(norm - masked.norm(1)) / norm < 1e-6);
    }

    @Test
    void test2Norm() {
        double norm = Math.sqrt(masked.stream()
            .map(value -> Math.pow(value.abs(), 2))
            .reduce(0., (acc, item) -> acc + item));
        assertTrue(Math.abs(norm - masked.norm()) / norm < 1e-6);
    }

    @Test
    void testPQuasinorm() {
        double norm = Math.pow(masked.stream()
            .map(value -> Math.pow(value.abs(), 0.5))
            .reduce(0., (acc, item) -> acc + item), 2);
        assertTrue(Math.abs(norm - masked.norm(0.5)) / norm < 5e-6);
    }

    @Test
    void testPNorm() {
        double norm = Math.pow(masked.stream()
            .map(value -> Math.pow(value.abs(), 3.5))
            .reduce(0., (acc, item) -> acc + item), 1 / 3.5);
        assertTrue(Math.abs(norm - masked.norm(3.5)) / norm < 5e-6);
    }

    @Test
    void testInfNorm() {
        double norm = masked.stream()
            .mapToDouble(value -> value.abs())
            .max().getAsDouble();
        assertTrue(Math.abs(norm - masked.norm(Double.POSITIVE_INFINITY)) / norm < 5e-6);
    }

    @Test
    void testCopy() {
        ComplexNDArray<Float> array2 = masked.copy();
        for (int i = 0; i < masked.length(); i++)
            assertEquals(masked.get(i), array2.get(i));
        array2.set(new Complex(0,0), 5);
        assertNotEquals(masked.get(5), array2.get(5));
    }

    @Test
    void testFillComplex() {
        masked.fill(new Complex(3,3));
        for (Complex elem : masked)
            assertEquals(new Complex(3, 3), elem);
        array.forEachWithLinearIndices((value, index) -> {
            if (index % 2 == 0)
                assertEquals(new Complex(index, -index), value);
            else
                assertEquals(new Complex(3, 3), value);
        });
    }

    @Test
    void testFillReal() {
        masked.fill(3);
        for (Complex elem : masked)
            assertEquals(new Complex(3, 0), elem);
        array.forEachWithLinearIndices((value, index) -> {
            if (index % 2 == 0)
                assertEquals(new Complex(index, -index), value);
            else
                assertEquals(new Complex(3, 0), value);
        });
    }

    @Test
    void testPermuteDimsAndToArray() {
        ComplexNDArray<Float> pArray = masked.permuteDims(0);
        Complex[] arr = (Complex[])pArray.toArray();
        for (int i = 0; i < pArray.dims(0); i++)
            assertEquals(array.get(i * 2 + 1), arr[i]);
    }

    @Test
    void testConcatenate() {
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(5).fill(1);
        ComplexNDArray<Float> array3 = masked.concatenate(0, array2);
        for (int i = 0; i < masked.dims(0); i++)
            assertEquals(masked.get(i), array3.get(i));
        for (int i = masked.dims(0); i < masked.dims(0) + array2.dims(0); i++)
            assertEquals(new Complex(1, 0), array3.get(i));
    }

    @Test
    void testConcatenateMultiple() {
        ComplexNDArray<Float> array2 = masked.copy().fill(1).slice("1:1");
        ComplexNDArray<Float> array3 = new ComplexF32NDArray(3).permuteDims(0);
        ComplexNDArray<Float> array4 = new ComplexF32NDArray(3,3).fill(new Complex(2, -2)).reshape(9);
        ComplexNDArray<Float> array5 = masked.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = masked.dims(0);
        for (int i = start; i < end; i++)
            assertEquals(masked.get(i), array5.get(i));
        start = end;
        end += array2.dims(0);
        for (int i = start; i < end; i++)
            assertEquals(new Complex(1, 0), array5.get(i));
        start = end;
        end += array3.dims(0);
        for (int i = start; i < end; i++)
            assertEquals(new Complex(0, 0), array5.get(i));
        start = end;
        end += array4.dims(0);
        for (int i = start; i < end; i++)
            assertEquals(new Complex(2, -2), array5.get(i));
    }

    @Test
    void testReal() {
        NDArray<Float> real = masked.real();
        masked.streamLinearIndices()
            .forEach(i -> assertEquals(masked.get(i).getReal(), real.get(i).doubleValue()));
    }

    @Test
    void testImag() {
        NDArray<Float> imag = masked.imaginary();
        masked.streamLinearIndices()
            .forEach(i -> assertEquals(masked.get(i).getImaginary(), imag.get(i).doubleValue()));
    }

    @Test
    void testAbs() {
        NDArray<Float> abs = masked.abs();
        masked.streamLinearIndices()
            .forEach(i -> assertTrue(masked.get(i).abs() - abs.get(i) < 1e-5));
    }

    @Test
    void testAngle() {
        NDArray<Float> angle = masked.angle();
        masked.streamLinearIndices()
            .forEach(i -> assertTrue(masked.get(i).getArgument() - angle.get(i) < 1e-5));
    }
}
