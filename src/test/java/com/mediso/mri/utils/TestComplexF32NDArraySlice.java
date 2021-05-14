package com.mediso.mri.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs2d.spinlab.data.DataSet;

public class TestComplexF32NDArraySlice {
    NDArray<Complex> array, slice;

    @BeforeEach
    public void setup() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        double[] imag = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        array = new ComplexF32NDArray(dims, real, imag);
        slice = array.slice(1, "1:4", ":");
    }

    @Test
    public void testGetNegativeLinearIndexing() {
        assertEquals(new Complex(45, -45), slice.get(-3));
    }

    @Test
    public void testGetNegativeCartesianIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int linearIndex = (2 * 5 + (1 + 2)) * 4 + 1;
        assertEquals(new Complex(linearIndex, -linearIndex), slice.get(2, -1));
    }

    @Test
    public void testSetLinearIndexingGetCartesianIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int arrayLinearIndex = (2 * 5 + (1 + 2)) * 4 + 1;
        int viewLinearIndex = 2 * 3 + 2;
        assertEquals(new Complex(arrayLinearIndex, -arrayLinearIndex), slice.get(2, -1));
        slice.set(new Complex(1, 1), viewLinearIndex);
        assertEquals(new Complex(1, 1), slice.get(2, -1));
        assertEquals(new Complex(1, 1), array.get(1, 3, 2));
    }

    @Test
    public void testSetCartesianIndexingGetLinearIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int viewLinearIndex = 2 * 3 + 2;
        array.set(new Complex(1, 1), 1, -2, -1);
        assertEquals(new Complex(1, 1), slice.get(viewLinearIndex));
    }

    @Test
    public void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(10));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, slice.length(), 10),
            exception.getMessage());
    }

    @Test
    public void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(-11));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, slice.length(), -11),
            exception.getMessage());
    }

    @Test
    public void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "3 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    public void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slice.get(-4,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "3 × 3", "[-4, 1]"),
            exception.getMessage());
    }

    @Test
    public void testWrongSetLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(zero, 10));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, slice.length(), 10),
            exception.getMessage());
    }

    @Test
    public void testWrongSetNegativeLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(zero, -11));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, slice.length(), -11),
            exception.getMessage());
    }

    @Test
    public void testWrongSetCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(zero, 1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "3 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    public void testWrongSetNegativeCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(zero, -4,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "3 × 3", "[-4, 1]"),
            exception.getMessage());
    }

    @Test
    public void testGetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice.get(1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 3, 2),
            exception.getMessage());
    }

    @Test
    public void testGetDimensionMismatchNotEnough() {
        NDArray<Complex> slice2 = array.slice("1:4", "1:4", ":");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice2.get(1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    public void testSetDimensionMismatchTooMany() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> slice.set(zero, 1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 3, 2),
            exception.getMessage());
    }

    @Test
    public void testSetDimensionMismatchNotEnough() {
        Complex zero = new Complex(0,0);
        NDArray<Complex> slice2 = array.slice("1:4", "1:4", ":");
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> slice2.set(zero, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    public void testEltype() {
        assertEquals(Complex.class, slice.eltype());
    }

    @Test
    public void testToArray() {
        Complex[][] arr = (Complex[][])slice.toArray();
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[i][j]);
    }

    @Test
    public void testEqual() {
        NDArray<Complex> array2 = new ComplexF32NDArray(slice);
        assertEquals(slice, array2);
        array2.set(new Complex(0,0), 5);
        assertNotEquals(slice, array2);
    }

    @Test
    public void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { slice.hashCode(); });
    }

    @Test
    public void testIterator() {
        int linearIndex = 0;
        for (Complex value : slice)
            assertEquals(slice.get(linearIndex++), value);
    }

    @Test
    public void testStream() {
        final int[] linearIndex = new int[1];
        slice.stream().forEach((value) -> {
            assertEquals(slice.get(linearIndex[0]++), value);
        });
    }

    @Test
    public void testParallelStream() {
        Complex sum = slice.stream().parallel()
            .reduce(new Complex(0,0), (acc, item) -> acc.add(item));
        Complex acc = new Complex(0,0);
        for (int i = 1; i < array.dims(1) - 1; i++)
            for (int j = 0; j < array.dims(2); j++)
                acc = acc.add(array.get(1, i, j));
        assertEquals(acc, sum);
    }

    @Test
    public void testCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<Complex> increased = slice.stream()
            .map((value) -> value.add(one))
            .collect(NDArrayCollectors.toComplexF32NDArray(slice.dims()));
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i).add(one), increased.get(i));
    }

    @Test
    public void testParallelCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value.add(one))
            .collect(NDArrayCollectors.toComplexF32NDArray(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(one), increased.get(i));
    }

    @Test
    public void testToString() {
        String str = slice.toString();
        assertEquals("NDArray<ComplexF32>(3 × 3)", str);
    }

    @Test
    public void testcontentToString() {
        String str = slice.contentToString();
        String expected = new StringBuilder()
            .append("NDArray<ComplexF32>(3 × 3)\n")
            .append("5,00000e+00+-5,00000e+00i	2,50000e+01+-2,50000e+01i	4,50000e+01+-4,50000e+01i	\n")
            .append("9,00000e+00+-9,00000e+00i	2,90000e+01+-2,90000e+01i	4,90000e+01+-4,90000e+01i	\n")
            .append("1,30000e+01+-1,30000e+01i	3,30000e+01+-3,30000e+01i	5,30000e+01+-5,30000e+01i	\n")
            .toString();
        assertEquals(expected, str);
    }

    @Test
    public void testAddArrayToSlice() {
        NDArray<Complex> array2 = new ComplexF32NDArray(slice);
        NDArray<Complex> array3 = slice.add(array2);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i).multiply(2), array3.get(i));
    }

    @Test
    public void testAddSliceToArray() {
        NDArray<Complex> array2 = new ComplexF32NDArray(slice);
        NDArray<Complex> array3 = array2.add(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i).multiply(2), array3.get(i));
    }

    @Test
    public void testAddSliceToSlice() {
        NDArray<Complex> slice2 = array.slice(1, "1:4", ":");
        NDArray<Complex> array3 = slice2.add(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i).multiply(2), array3.get(i));
    }

    @Test
    public void testAddScalar() {
        NDArray<Complex> slice2 = slice.add(5);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i).add(5), slice2.get(i));
    }

    @Test
    public void testAddMultiple() {
        NDArray<Complex> array2 = new ComplexF32NDArray(array);
        NDArray<Complex> slice2 = array2.slice(1, "1:4", ":");
        NDArray<Complex> array3 = slice2.add(slice, 5.3, slice2, new Complex(3,1));
        for (int i = 0; i < slice.length(); i++) {
            Complex expected = slice.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e5);
        }
    }

    @Test
    public void testAddInplace() {
        NDArray<Complex> array2 = new ComplexF32NDArray(array);
        NDArray<Complex> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i).multiply(2), slice2.get(i));
    }

    @Test
    public void testAddInplaceScalar() {
        NDArray<Complex> array2 = new ComplexF32NDArray(array);
        NDArray<Complex> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(5);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i).add(5), slice2.get(i));
    }

    @Test
    public void testAddInplaceMultiple() {
        NDArray<Complex> array2 = new ComplexF32NDArray(array);
        NDArray<Complex> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(slice, 5.3, slice2, new Complex(3,1));
        for (int i = 0; i < slice.length(); i++) {
            Complex expected = slice.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(slice2.get(i)).abs() < 1e5);
        }
    }

    @Test
    public void testCopy() {
        NDArray<Complex> array2 = slice.copy();
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i), array2.get(i));
        array2.set(new Complex(0,0), 5);
        assertNotEquals(slice.get(5), array2.get(5));
    }

    @Test
    public void testFillComplex() {
        slice.fill(new Complex(3,3));
        for (Complex elem : slice)
            assertEquals(new Complex(3, 3), elem);
        NDArray<Complex> slice2 = array.slice(0, ":", ":");
        for (Complex elem : slice2)
            assertNotEquals(new Complex(3, 3), elem);
    }

    @Test
    public void testFillReal() {
        slice.fill(3);
        for (Complex elem : slice)
            assertEquals(new Complex(3, 0), elem);
        NDArray<Complex> slice2 = array.slice(0, ":", ":");
        for (Complex elem : slice2)
            assertNotEquals(new Complex(3, 0), elem);
    }

    @Test
    public void testPermuteDimsAndToArray() {
        NDArray<Complex> pArray = slice.permuteDims(1,0);
        Complex[][] arr = (Complex[][])pArray.toArray();
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[j][i]);
    }

    @Test
    public void testToRS2DDataSet() {
        DataSet dataSet = slice.toRS2DDataSet();
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++) {
                assertEquals(slice.get(i, j).getReal(), dataSet.getData(0).getRealElement(i, j, 0, 0));
                assertEquals(slice.get(i, j).getImaginary(), dataSet.getData(0).getImaginaryElement(i, j, 0, 0));
            }
    }

    @Test
    public void testToRS2DDataSetTooManyDimensions() {
        NDArray<Complex> slice2 = new ComplexF32NDArray(new int[]{2,2,2,2,2,2}).slice(":",":",":",":",":",":");
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> slice2.toRS2DDataSet());
        assertEquals(String.format(AbstractNDArray.ERROR_RS2D_DATA_SET_TOO_HIGH_DIMENSIONAL, 6),
                     exception.getMessage());
    }

    @Test
    public void testConcatenate() {
        NDArray<Complex> array2 = new ComplexF32NDArray(new int[]{5, 3}).fill(1);
        NDArray<Complex> array3 = slice.concatenate(0, array2);
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(slice.get(i, j), array3.get(i, j));
        for (int i = slice.dims(0); i < slice.dims(0) + array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(new Complex(1, 0), array3.get(i, j));
    }

    @Test
    public void testConcatenateMultiple() {
        NDArray<Complex> array2 = slice.copy().fill(1).slice("1:1", ":");
        NDArray<Complex> array3 = new ComplexF32NDArray(new int[]{3, 2}).permuteDims(1, 0);
        NDArray<Complex> array4 = new ComplexF32NDArray(new int[]{9}).fill(new Complex(2, -2)).reshape(3, 3);
        NDArray<Complex> array5 = slice.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = slice.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(slice.get(i, j), array5.get(i, j));
        start = end;
        end += array2.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(new Complex(1, 0), array5.get(i, j));
        start = end;
        end += array3.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(new Complex(0, 0), array5.get(i, j));
        start = end;
        end += array4.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(new Complex(2, -2), array5.get(i, j));
    }

    @Test
    public void testReal() {
        NDArray<Double> real = slice.real();
        slice.streamLinearIndices()
            .forEach(i -> assertEquals(slice.get(i).getReal(), real.get(i)));
    }

    @Test
    public void testImag() {
        NDArray<Double> imag = slice.imaginary();
        slice.streamLinearIndices()
            .forEach(i -> assertEquals(slice.get(i).getImaginary(), imag.get(i)));
    }

    @Test
    public void testAbs() {
        NDArray<Double> abs = slice.abs();
        slice.streamLinearIndices()
            .forEach(i -> assertTrue(slice.get(i).abs() - abs.get(i) < 1e-5));
    }

    @Test
    public void testAngle() {
        NDArray<Double> angle = slice.angle();
        slice.streamLinearIndices()
            .forEach(i -> assertTrue(slice.get(i).getArgument() - angle.get(i) < 1e-5));
    }
}
