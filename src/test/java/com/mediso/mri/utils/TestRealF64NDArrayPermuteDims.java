package com.mediso.mri.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs2d.spinlab.data.DataSet;

public class TestRealF64NDArrayPermuteDims {
    NDArray<Double> array, pArray;

    @BeforeEach
    public void setup() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        array = new RealF64NDArray(dims, real);
        pArray = array.permuteDims(0, 2, 1);
    }

    @Test
    public void testGetNegativeLinearIndexing() {
        assertEquals(39., pArray.get(-5));
    }

    @Test
    public void testGetNegativeCartesianIndexing() {
        assertEquals(50., pArray.get(2, -1, -3));
    }

    @Test
    public void testSetLinearIndexingGetCartesianIndexing() {
        int parentLinearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in parent
        assertEquals(Double.valueOf(parentLinearIndex), pArray.get(2, -1, -3));
        int viewLinearIndex = (2 * 3 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in view
        pArray.set(1., viewLinearIndex);
        assertEquals(1., pArray.get(2, -1, -3));
    }

    @Test
    public void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 3 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in view
        pArray.set(1, 2, -1, -3);
        assertEquals(1., pArray.get(linearIndex));
    }

    @Test
    public void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, pArray.length(), 60),
            exception.getMessage());
    }

    @Test
    public void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(-61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, pArray.length(), -61),
            exception.getMessage());
    }

    @Test
    public void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(1,3,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 3, 1]"),
            exception.getMessage());
    }

    @Test
    public void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(1,1,-6));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 1, -6]"),
            exception.getMessage());
    }

    @Test
    public void testWrongSetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, 60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, pArray.length(), 60),
            exception.getMessage());
    }

    @Test
    public void testWrongSetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, -61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, pArray.length(), -61),
            exception.getMessage());
    }

    @Test
    public void testWrongSetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, 1,3,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 3, 1]"),
            exception.getMessage());
    }

    @Test
    public void testWrongSetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, 1,1,-6));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 1, -6]"),
            exception.getMessage());
    }

    @Test
    public void testGetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.get(1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    public void testGetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.get(1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    public void testSetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> pArray.set(0, 1, 1, 1, 0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    public void testSetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> pArray.set(0, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    public void testEltype() {
        assertEquals(Double.class, pArray.eltype());
    }

    @Test
    public void testToArray() {
        Double[][][] arr = (Double[][][])pArray.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr[0][0].length; j++)
                for (int k = 0; k < arr.length; k++) {
                    assertEquals(Double.valueOf(linearIndex), arr[k][i][j]);
                    linearIndex++;
                }
    }

    @Test
    public void testEqual() {
        NDArray<Double> array2 = new RealF64NDArray(pArray);
        assertEquals(pArray, array2);
        array2.set(0., 5);
        assertNotEquals(pArray, array2);
    }

    @Test
    public void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { pArray.hashCode(); });
    }

    @Test
    public void testIterator() {
        int linearIndex = 0;
        for (Double value : pArray)
            assertEquals(pArray.get(linearIndex++), value);
    }

    @Test
    public void testStream() {
        final int[] linearIndex = new int[1];
        pArray.stream().forEach((value) -> {
            assertEquals(pArray.get(linearIndex[0]++), value);
        });
    }

    @Test
    public void testParallelStream() {
        Double sum = pArray.stream().parallel()
            .reduce(0., (acc, item) -> acc + item);
        Double acc = 0.;
        for (int i = 0; i < array.dims(0); i++)
            for (int j = 0; j < array.dims(1); j++)
                for (int k = 0; k < array.dims(2); k++)
                acc = acc + array.get(i, j, k);
        assertEquals(acc, sum);
    }

    @Test
    public void testCollector() {
        NDArray<Double> increased = pArray.stream()
            .map((value) -> value + 1)
            .collect(NDArrayCollectors.toRealF64NDArray(pArray.dims()));
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) + 1, increased.get(i));
    }

    @Test
    public void testParallelCollector() {
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value + 1)
            .collect(NDArrayCollectors.toRealF64NDArray(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) + 1, increased.get(i));
    }

    @Test
    public void testToString() {
        String str = pArray.toString();
        assertEquals("NDArray<RealF64>(4 × 3 × 5)", str);
    }

    @Test
    public void testcontentToString() {
        String str = pArray.contentToString();
        String expected = new StringBuilder()
            .append("NDArray<RealF64>(4 × 3 × 5)\n")
            .append("[:, :, 0] =\n")
            .append("0,00000e+00	2,00000e+01	4,00000e+01	\n")
            .append("1,00000e+00	2,10000e+01	4,10000e+01	\n")
            .append("2,00000e+00	2,20000e+01	4,20000e+01	\n")
            .append("3,00000e+00	2,30000e+01	4,30000e+01	\n")
            .append("\n")
            .append("[:, :, 1] =\n")
            .append("4,00000e+00	2,40000e+01	4,40000e+01	\n")
            .append("5,00000e+00	2,50000e+01	4,50000e+01	\n")
            .append("6,00000e+00	2,60000e+01	4,60000e+01	\n")
            .append("7,00000e+00	2,70000e+01	4,70000e+01	\n")
            .append("\n")
            .append("[:, :, 2] =\n")
            .append("8,00000e+00	2,80000e+01	4,80000e+01	\n")
            .append("9,00000e+00	2,90000e+01	4,90000e+01	\n")
            .append("1,00000e+01	3,00000e+01	5,00000e+01	\n")
            .append("1,10000e+01	3,10000e+01	5,10000e+01	\n")
            .append("\n")
            .append("[:, :, 3] =\n")
            .append("1,20000e+01	3,20000e+01	5,20000e+01	\n")
            .append("1,30000e+01	3,30000e+01	5,30000e+01	\n")
            .append("1,40000e+01	3,40000e+01	5,40000e+01	\n")
            .append("1,50000e+01	3,50000e+01	5,50000e+01	\n")
            .append("\n")
            .append("[:, :, 4] =\n")
            .append("1,60000e+01	3,60000e+01	5,60000e+01	\n")
            .append("1,70000e+01	3,70000e+01	5,70000e+01	\n")
            .append("1,80000e+01	3,80000e+01	5,80000e+01	\n")
            .append("1,90000e+01	3,90000e+01	5,90000e+01	\n")
            .append("\n")
            .toString();
        assertEquals(expected, str);
    }

    @Test
    public void testAddArrayTopArray() {
        NDArray<Double> array2 = new RealF64NDArray(pArray);
        NDArray<Double> array3 = pArray.add(array2);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) * 2, array3.get(i));
    }

    @Test
    public void testAddpArrayToArray() {
        NDArray<Double> array2 = new RealF64NDArray(pArray);
        NDArray<Double> array3 = array2.add(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) * 2, array3.get(i));
    }

    @Test
    public void testAddpArrayTopArray() {
        NDArray<Double> pArray2 = array.permuteDims(0, 2, 1);
        NDArray<Double> array3 = pArray2.add(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) * 2, array3.get(i));
    }

    @Test
    public void testAddScalar() {
        NDArray<Double> pArray2 = pArray.add(5);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) + 5, pArray2.get(i));
    }

    @Test
    public void testAddMultiple() {
        NDArray<Double> array2 = new RealF64NDArray(array);
        NDArray<Double> pArray2 = array2.permuteDims(0, 2, 1);
        NDArray<Double> array3 = pArray2.add(pArray, 5.3, pArray2, 3.);
        for (int i = 0; i < pArray.length(); i++) {
            double expected = pArray.get(i) * 3. + 5.3f + 3.;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    public void testAddInplace() {
        NDArray<Double> array2 = new RealF64NDArray(array);
        NDArray<Double> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) * 2, pArray2.get(i));
    }

    @Test
    public void testAddInplaceScalar() {
        NDArray<Double> array2 = new RealF64NDArray(array);
        NDArray<Double> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(5);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i)+ 5, pArray2.get(i));
    }

    @Test
    public void testAddInplaceMultiple() {
        NDArray<Double> array2 = new RealF64NDArray(array);
        NDArray<Double> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(pArray, 5.3, pArray2, 3.);
        for (int i = 0; i < pArray.length(); i++) {
            double expected = pArray.get(i) * 3. + 5.3 + 3.;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    public void testCopy() {
        NDArray<Double> array2 = pArray.copy();
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i), array2.get(i));
        array2.set(0., 5);
        assertNotEquals(pArray.get(5), array2.get(5));
    }

    @Test
    public void testFillFloat() {
        pArray.fill(3.);
        for (Double elem : pArray)
            assertEquals(3., elem);
    }

    @Test
    public void testFillReal() {
        pArray.fill(3);
        for (Double elem : pArray)
            assertEquals(3., elem);
    }

    @Test
    public void testSliceAndToArray() {
        NDArray<Double> slice = pArray.slice(1, ":", "1:4");
        Double[][] arr = (Double[][])slice.toArray();
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[j][i]);
    }

    @Test
    public void testToRS2DDataSet() {
        DataSet dataSet = pArray.toRS2DDataSet();
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = 0; k < pArray.dims(2); k++) {
                    assertEquals(array.get(i, k, j), dataSet.getData(0).getRealElement(i, j, k, 0));
                }
    }

    @Test
    public void testToRS2DDataSetTooManyDimensions() {
        NDArray<Double> array2 = new RealF64NDArray(new int[]{2,2,2,2,2,2}).permuteDims(5, 4, 3, 2, 1, 0);
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> array2.toRS2DDataSet());
        assertEquals(String.format(AbstractNDArray.ERROR_RS2D_DATA_SET_TOO_HIGH_DIMENSIONAL, 6),
                     exception.getMessage());
    }

    @Test
    public void testConcatenate() {
        NDArray<Double> array2 = new RealF64NDArray(new int[]{4, 3, 2}).fill(1);
        NDArray<Double> array3 = pArray.concatenate(2, array2);
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = 0; k < pArray.dims(2); k++)
                    assertEquals(pArray.get(i, j, k), array3.get(i, j, k));
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = pArray.dims(2); k < pArray.dims(2) + array2.dims(2); k++)
                    assertEquals(1., array3.get(i, j, k));
    }

    @Test
    public void testConcatenateMultiple() {
        NDArray<Double> array2 = pArray.copy().fill(1).slice(":", ":", "1:3");
        NDArray<Double> array3 = new RealF64NDArray(new int[]{5, 3, 4}).permuteDims(2, 1, 0);
        NDArray<Double> array4 = new RealF64NDArray(new int[]{36}).fill(2.).reshape(4, 3, 3);
        NDArray<Double> array5 = pArray.concatenate(2, array2, array3, array4);
        int start = 0;
        int end = pArray.dims(2);
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(pArray.get(i, j, k), array5.get(i, j, k));
        start = end;
        end += array2.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(1., array5.get(i, j, k));
        start = end;
        end += array3.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(0., array5.get(i, j, k));
        start = end;
        end += array4.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(2., array5.get(i, j, k));
    }

    @Test
    public void testReal() {
        NDArray<Double> real = pArray.real();
        pArray.streamLinearIndices()
            .forEach(i -> assertEquals(pArray.get(i).floatValue(), real.get(i)));
    }

    @Test
    public void testImag() {
        NDArray<Double> imag = pArray.imaginary();
        pArray.streamLinearIndices()
            .forEach(i -> assertEquals(0, imag.get(i)));
    }

    @Test
    public void testAbs() {
        NDArray<Double> abs = pArray.abs();
        pArray.streamLinearIndices()
            .forEach(i -> assertTrue(Math.abs(pArray.get(i)) - abs.get(i) < 1e-5));
    }

    @Test
    public void testAngle() {
        NDArray<Double> angle = pArray.angle();
        pArray.streamLinearIndices()
            .forEach(i -> assertEquals(0, angle.get(i)));
    }
}