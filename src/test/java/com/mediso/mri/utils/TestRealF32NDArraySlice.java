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

public class TestRealF32NDArraySlice {
    NDArray<Float> array, slice;

    @BeforeEach
    public void setup() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        array = new RealF32NDArray(dims, real);
        slice = array.slice(1, "1:4", ":");
    }

    @Test
    public void testGetNegativeLinearIndexing() {
        assertEquals(45, slice.get(-3));
    }

    @Test
    public void testGetNegativeCartesianIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int linearIndex = (2 * 5 + (1 + 2)) * 4 + 1;
        assertEquals(linearIndex, slice.get(2, -1));
    }

    @Test
    public void testSetLinearIndexingGetCartesianIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int arrayLinearIndex = (2 * 5 + (1 + 2)) * 4 + 1;
        int viewLinearIndex = 2 * 3 + 2;
        assertEquals(arrayLinearIndex, slice.get(2, -1));
        slice.set(1, viewLinearIndex);
        assertEquals(1, slice.get(2, -1));
        assertEquals(1, array.get(1, 3, 2));
    }

    @Test
    public void testSetCartesianIndexingGetLinearIndexing() {
        // linearIndex equal to cartesian index [1,3,2] in original array and [2,2] in slice:
        int viewLinearIndex = 2 * 3 + 2;
        array.set(1, 1, -2, -1);
        assertEquals(1, slice.get(viewLinearIndex));
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
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(0, 10));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, slice.length(), 10),
            exception.getMessage());
    }

    @Test
    public void testWrongSetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(0, -11));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, slice.length(), -11),
            exception.getMessage());
    }

    @Test
    public void testWrongSetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(0, 1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "3 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    public void testWrongSetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> slice.set(0, -4,1));
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
        NDArray<Float> slice2 = array.slice("1:4", "1:4", ":");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> slice2.get(1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    public void testSetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> slice.set(0, 1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 3, 2),
            exception.getMessage());
    }

    @Test
    public void testSetDimensionMismatchNotEnough() {
        NDArray<Float> slice2 = array.slice("1:4", "1:4", ":");
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> slice2.set(0, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    public void testEltype() {
        assertEquals(Float.class, slice.eltype());
    }

    @Test
    public void testToArray() {
        Float[][] arr = (Float[][])slice.toArray();
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[i][j]);
    }

    @Test
    public void testEqual() {
        NDArray<Float> array2 = new RealF32NDArray(slice);
        assertEquals(slice, array2);
        array2.set(0, 5);
        assertNotEquals(slice, array2);
    }

    @Test
    public void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { slice.hashCode(); });
    }

    @Test
    public void testIterator() {
        int linearIndex = 0;
        for (Float value : slice)
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
        Float sum = slice.stream().parallel()
            .reduce(0.f, (acc, item) -> acc + item);
        float acc = 0;
        for (int i = 1; i < array.dims(1) - 1; i++)
            for (int j = 0; j < array.dims(2); j++)
                acc = acc + array.get(1, i, j);
        assertEquals(acc, sum);
    }

    @Test
    public void testCollector() {
        NDArray<Float> increased = slice.stream()
            .map((value) -> value + 1)
            .collect(NDArrayCollectors.toRealF32NDArray(slice.dims()));
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) + 1, increased.get(i));
    }

    @Test
    public void testParallelCollector() {
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value + 1)
            .collect(NDArrayCollectors.toRealF32NDArray(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) + 1, increased.get(i));
    }

    @Test
    public void testToString() {
        String str = slice.toString();
        assertEquals("NDArray<RealF32>(3 × 3)", str);
    }

    @Test
    public void testcontentToString() {
        String str = slice.contentToString();
        String expected = new StringBuilder()
            .append("NDArray<RealF32>(3 × 3)\n")
            .append("5,00000e+00	2,50000e+01	4,50000e+01	\n")
            .append("9,00000e+00	2,90000e+01	4,90000e+01	\n")
            .append("1,30000e+01	3,30000e+01	5,30000e+01	\n")
            .toString();
        assertEquals(expected, str);
    }

    @Test
    public void testAddArrayToSlice() {
        NDArray<Float> array2 = new RealF32NDArray(slice);
        NDArray<Float> array3 = slice.add(array2);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) * 2, array3.get(i));
    }

    @Test
    public void testAddSliceToArray() {
        NDArray<Float> array2 = new RealF32NDArray(slice);
        NDArray<Float> array3 = array2.add(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) * 2, array3.get(i));
    }

    @Test
    public void testAddSliceToSlice() {
        NDArray<Float> slice2 = array.slice(1, "1:4", ":");
        NDArray<Float> array3 = slice2.add(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) * 2, array3.get(i));
    }

    @Test
    public void testAddScalar() {
        NDArray<Float> slice2 = slice.add(5);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) + 5, slice2.get(i));
    }

    @Test
    public void testAddMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> slice2 = array2.slice(1, "1:4", ":");
        NDArray<Float> array3 = slice2.add(slice, 5.3, slice2, 3);
        for (int i = 0; i < slice.length(); i++) {
            float expected = slice.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    public void testAddInplace() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(slice);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) * 2, slice2.get(i));
    }

    @Test
    public void testAddInplaceScalar() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(5);
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i) + 5, slice2.get(i));
    }

    @Test
    public void testAddInplaceMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> slice2 = array2.slice(1, "1:4", ":");
        slice2.addInplace(slice, 5.3, slice2, 3);
        for (int i = 0; i < slice.length(); i++) {
            float expected = slice.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    public void testCopy() {
        NDArray<Float> array2 = slice.copy();
        for (int i = 0; i < slice.length(); i++)
            assertEquals(slice.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(slice.get(5), array2.get(5));
    }

    @Test
    public void testFillFloat() {
        slice.fill(3);
        for (Float elem : slice)
            assertEquals(3, elem);
        NDArray<Float> slice2 = array.slice(0, ":", ":");
        for (Float elem : slice2)
            assertNotEquals(3, elem);
    }

    @Test
    public void testFillReal() {
        slice.fill(3);
        for (Float elem : slice)
            assertEquals(3, elem);
        NDArray<Float> slice2 = array.slice(0, ":", ":");
        for (Float elem : slice2)
            assertNotEquals(3, elem);
    }

    @Test
    public void testPermuteDimsAndToArray() {
        NDArray<Float> pArray = slice.permuteDims(1,0);
        Float[][] arr = (Float[][])pArray.toArray();
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[j][i]);
    }

    @Test
    public void testToRS2DDataSet() {
        DataSet dataSet = slice.toRS2DDataSet();
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++) {
                assertEquals(slice.get(i, j), dataSet.getData(0).getRealElement(i, j, 0, 0));
                assertEquals(0, dataSet.getData(0).getImaginaryElement(i, j, 0, 0));
            }
    }

    @Test
    public void testToRS2DDataSetTooManyDimensions() {
        NDArray<Float> slice2 = new RealF32NDArray(new int[]{2,2,2,2,2,2}).slice(":",":",":",":",":",":");
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
            assertEquals(slice.get(index).floatValue(), image.getPixelAsFloat(idx));
        });
    }

    @Test
    public void testConcatenate() {
        NDArray<Float> array2 = new RealF32NDArray(new int[]{5, 3}).fill(1);
        NDArray<Float> array3 = slice.concatenate(0, array2);
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(slice.get(i, j), array3.get(i, j));
        for (int i = slice.dims(0); i < slice.dims(0) + array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(1, array3.get(i, j));
    }

    @Test
    public void testConcatenateMultiple() {
        NDArray<Float> array2 = slice.copy().fill(1).slice("1:1", ":");
        NDArray<Float> array3 = new RealF32NDArray(new int[]{3, 2}).permuteDims(1, 0);
        NDArray<Float> array4 = new RealF32NDArray(new int[]{9}).fill(2).reshape(3, 3);
        NDArray<Float> array5 = slice.concatenate(0, array2, array3, array4);
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
    public void testReal() {
        NDArray<Double> real = slice.real();
        slice.streamLinearIndices()
            .forEach(i -> assertEquals(slice.get(i).doubleValue(), real.get(i)));
    }

    @Test
    public void testImag() {
        NDArray<Double> imag = slice.imaginary();
        slice.streamLinearIndices()
            .forEach(i -> assertEquals(0, imag.get(i)));
    }

    @Test
    public void testAbs() {
        NDArray<Double> abs = slice.abs();
        slice.streamLinearIndices()
            .forEach(i -> assertEquals(Math.abs(slice.get(i).doubleValue()), abs.get(i)));
    }

    @Test
    public void testAngle() {
        NDArray<Double> angle = slice.angle();
        slice.streamLinearIndices()
            .forEach(i -> assertEquals(0, angle.get(i)));
    }
}
