package com.mediso.mri.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.IntStream;

import org.itk.simple.Image;
import org.itk.simple.VectorUInt32;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs2d.spinlab.data.DataSet;

class TestRealF64NDArraySlice {
    NDArray<Double> array, slice;

    @BeforeEach
    void setup() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        array = new RealF64NDArray(dims, real);
        slice = array.slice(1, "1:4", ":");
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals(45, slice.get(-3));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int linearIndex = (2 * 5 + (1 + 2)) * 4 + 1;
        assertEquals(linearIndex, slice.get(2, -1));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int arrayLinearIndex = (2 * 5 + (1 + 2)) * 4 + 1;
        int viewLinearIndex = 2 * 3 + 2;
        assertEquals(arrayLinearIndex, slice.get(2, -1));
        slice.set(1, viewLinearIndex);
        assertEquals(1, slice.get(2, -1));
        assertEquals(1, array.get(1, 3, 2));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int viewLinearIndex = 2 * 3 + 2;
        array.set(1, 1, -2, -1);
        assertEquals(1, slice.get(viewLinearIndex));
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
        NDArray<Double> slice2 = array.slice("1:4", "1:4", ":");
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
        NDArray<Double> slice2 = array.slice("1:4", "1:4", ":");
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> slice2.set(0, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Double.class, slice.eltype());
    }

    @Test
    void testToArray() {
        Double[][] arr = (Double[][])slice.toArray();
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[i][j]);
    }

    @Test
    void testEqual() {
        NDArray<Double> array2 = new RealF64NDArray(slice);
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
        for (Double value : slice)
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
        Double sum = slice.stream().parallel()
            .reduce(0., (acc, item) -> acc + item);
        double acc = 0;
        for (int i = 1; i < array.dims(1) - 1; i++)
            for (int j = 0; j < array.dims(2); j++)
                acc = acc + array.get(1, i, j);
        assertEquals(acc, sum);
    }

    @Test
    void testCollector() {
        NDArray<Double> increased = slice.stream()
            .map((value) -> value + 1)
            .collect(NDArrayCollectors.toRealF64NDArray(slice.dims()));
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) + 1, increased.get(i));
    }

    @Test
    void testParallelCollector() {
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value + 1)
            .collect(NDArrayCollectors.toRealF64NDArray(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) + 1, increased.get(i));
    }

    @Test
    void testToString() {
        String str = slice.toString();
        assertEquals("NDArray<RealF64>(3 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = slice.contentToString();
        String expected = new StringBuilder()
            .append("NDArray<RealF64>(3 × 3)\n")
            .append("5,00000e+00	2,50000e+01	4,50000e+01	\n")
            .append("9,00000e+00	2,90000e+01	4,90000e+01	\n")
            .append("1,30000e+01	3,30000e+01	5,30000e+01	\n")
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testAddArrayToSlice() {
        NDArray<Double> array2 = new RealF64NDArray(slice);
        NDArray<Double> array3 = slice.add(array2);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) * 2, array3.get(i));
    }

    @Test
    void testAddSliceToArray() {
        NDArray<Double> array2 = new RealF64NDArray(slice);
        NDArray<Double> array3 = array2.add(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) * 2, array3.get(i));
    }

    @Test
    void testAddSliceToSlice() {
        NDArray<Double> slice2 = array.slice(1, "1:4", ":");
        NDArray<Double> array3 = slice2.add(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) * 2, array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Double> slice2 = slice.add(5);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) + 5, slice2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Double> array2 = new RealF64NDArray(array);
        NDArray<Double> slice2 = array2.slice(1, "1:4", ":");
        NDArray<Double> array3 = slice2.add(slice, 5.3, slice2, 3);
        for (int i = 0; i < slice.length(); i++) {
            double expected = slice.get(i) * 3. + 5.3 + 3.;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Double> array2 = new RealF64NDArray(array);
        NDArray<Double> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) * 2, slice2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Double> array2 = new RealF64NDArray(array);
        NDArray<Double> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(5);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) + 5, slice2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Double> array2 = new RealF64NDArray(array);
        NDArray<Double> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(slice, 5.3, slice2, 3);
        for (int i = 0; i < slice.length(); i++) {
            double expected = slice.get(i) * 3. + 5.3 + 3.;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void test0Norm() {
        slice.slice(":", 0).fill(0);
        double norm = slice.stream()
            .filter(value -> value != 0.)
            .count();
        assertEquals(norm, slice.norm(0));
    }

    @Test
    void test1Norm() {
        double norm = slice.stream()
            .mapToDouble(value -> Math.abs(value))
            .reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, slice.norm(1));
    }

    @Test
    void test2Norm() {
        double norm = Math.sqrt(slice.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 2))
            .reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, slice.norm());
    }

    @Test
    void testPQuasinorm() {
        double norm = Math.pow(slice.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 0.5))
            .reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, slice.norm(0.5));
    }

    @Test
    void testPNorm() {
        double norm = Math.pow(slice.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 3.5))
            .reduce(0., (acc, item) -> acc + item), 1 / 3.5);
        assertEquals(norm, slice.norm(3.5));
    }

    @Test
    void testInfNorm() {
        double norm = slice.stream()
            .mapToDouble(value -> Math.abs(value))
            .max().getAsDouble();
        assertEquals(norm, slice.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testCopy() {
        NDArray<Double> array2 = slice.copy();
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(slice.get(5), array2.get(5));
    }

    @Test
    void testFillDouble() {
        slice.fill(3);
        for (Double elem : slice)
            assertEquals(3, elem);
        NDArray<Double> slice2 = array.slice(0, ":", ":");
        for (Double elem : slice2)
            assertNotEquals(3, elem);
    }

    @Test
    void testFillReal() {
        slice.fill(3);
        for (Double elem : slice)
            assertEquals(3, elem);
        NDArray<Double> slice2 = array.slice(0, ":", ":");
        for (Double elem : slice2)
            assertNotEquals(3, elem);
    }

    @Test
    void testPermuteDimsAndToArray() {
        NDArray<Double> pArray = slice.permuteDims(1,0);
        Double[][] arr = (Double[][])pArray.toArray();
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[j][i]);
    }

    @Test
    void testToRS2DDataSet() {
        DataSet dataSet = slice.toRS2DDataSet();
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++) {
                assertEquals(slice.get(i, j), dataSet.getData(0).getRealElement(i, j, 0, 0));
                assertEquals(0, dataSet.getData(0).getImaginaryElement(i, j, 0, 0));
            }
    }

    @Test
    void testToRS2DDataSetTooManyDimensions() {
        NDArray<Double> slice2 = new RealF64NDArray(new int[]{2,2,2,2,2,2}).slice(":",":",":",":",":",":");
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> slice2.toRS2DDataSet());
        assertEquals(String.format(AbstractNDArray.ERROR_RS2D_DATA_SET_TOO_HIGH_DIMENSIONAL, 6),
                     exception.getMessage());
    }
    
    @Test
    void testToSimpleITKImage() {
        Image image = slice.toSimpleITKImage();
        slice.streamCartesianIndices().forEach(index -> {
            VectorUInt32 idx = new VectorUInt32(slice.ndims());
            IntStream.range(0, slice.ndims()).forEach(i -> idx.set(i, index[i]));
            assertEquals(slice.get(index).doubleValue(), image.getPixelAsDouble(idx));
        });
    }

    @Test
    void testConcatenate() {
        NDArray<Double> array2 = new RealF64NDArray(new int[]{5, 3}).fill(1);
        NDArray<Double> array3 = slice.concatenate(0, array2);
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(slice.get(i, j), array3.get(i, j));
        for (int i = slice.dims(0); i < slice.dims(0) + array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(1, array3.get(i, j));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Double> array2 = slice.copy().fill(1).slice("1:1", ":");
        NDArray<Double> array3 = new RealF64NDArray(new int[]{3, 2}).permuteDims(1, 0);
        NDArray<Double> array4 = new RealF64NDArray(new int[]{9}).fill(2).reshape(3, 3);
        NDArray<Double> array5 = slice.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = slice.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(slice.get(i, j), array5.get(i, j));
        start = end;
        end += array2.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(1, array5.get(i, j));
        start = end;
        end += array3.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(0, array5.get(i, j));
        start = end;
        end += array4.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(2, array5.get(i, j));
    }

    @Test
    void testReal() {
        NDArray<Double> real = slice.real();
        slice.streamLinearIndices()
            .forEach(i -> assertEquals(slice.get(i).doubleValue(), real.get(i)));
    }

    @Test
    void testImag() {
        NDArray<Double> imag = slice.imaginary();
        slice.streamLinearIndices()
            .forEach(i -> assertEquals(0, imag.get(i)));
    }

    @Test
    void testAbs() {
        NDArray<Double> abs = slice.abs();
        slice.streamLinearIndices()
            .forEach(i -> assertEquals(Math.abs(slice.get(i).doubleValue()), abs.get(i)));
    }

    @Test
    void testAngle() {
        NDArray<Double> angle = slice.angle();
        slice.streamLinearIndices()
            .forEach(i -> assertEquals(0, angle.get(i)));
    }
}
