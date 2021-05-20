package com.mediso.mri.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs2d.spinlab.data.DataSet;

class TestComplexF64NDArrayPermuteDims {
    NDArray<Complex> array, pArray;

    @BeforeEach
    void setup() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        double[] imag = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        array = new ComplexF64NDArray(dims, real, imag);
        pArray = array.permuteDims(0, 2, 1);
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals(new Complex(39, -39), pArray.get(-5));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        assertEquals(new Complex(50, -50), pArray.get(2, -1, -3));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        int parentLinearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in parent
        assertEquals(new Complex(parentLinearIndex, -parentLinearIndex), pArray.get(2, -1, -3));
        int viewLinearIndex = (2 * 3 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in view
        pArray.set(new Complex(1, 1), viewLinearIndex);
        assertEquals(new Complex(1, 1), pArray.get(2, -1, -3));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 3 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in view
        pArray.set(new Complex(1, 1), 2, -1, -3);
        assertEquals(new Complex(1, 1), pArray.get(linearIndex));
    }

    @Test
    void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, pArray.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(-61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, pArray.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(1,3,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 3, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> pArray.get(1,1,-6));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 1, -6]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(zero, 60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, pArray.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(zero, -61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, pArray.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(zero, 1,3,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 3, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(zero, 1,1,-6));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 1, -6]"),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.get(1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pArray.get(1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooMany() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> pArray.set(zero, 1, 1, 1, 0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnough() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> pArray.set(zero, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Complex.class, pArray.eltype());
    }

    @Test
    void testToArray() {
        Complex[][][] arr = (Complex[][][])pArray.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr[0][0].length; j++)
                for (int k = 0; k < arr.length; k++) {
                    assertEquals(new Complex(linearIndex, -linearIndex), arr[k][i][j]);
                    linearIndex++;
                }
    }

    @Test
    void testEqual() {
        NDArray<Complex> array2 = new ComplexF64NDArray(pArray);
        assertEquals(pArray, array2);
        array2.set(new Complex(0,0), 5);
        assertNotEquals(pArray, array2);
    }

    @Test
    void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { pArray.hashCode(); });
    }

    @Test
    void testIterator() {
        int linearIndex = 0;
        for (Complex value : pArray)
            assertEquals(pArray.get(linearIndex++), value);
    }

    @Test
    void testStream() {
        final int[] linearIndex = new int[1];
        pArray.stream().forEach((value) -> {
            assertEquals(pArray.get(linearIndex[0]++), value);
        });
    }

    @Test
    void testParallelStream() {
        Complex sum = pArray.stream().parallel()
            .reduce(new Complex(0,0), (acc, item) -> acc.add(item));
        Complex acc = new Complex(0,0);
        for (int i = 0; i < array.dims(0); i++)
            for (int j = 0; j < array.dims(1); j++)
                for (int k = 0; k < array.dims(2); k++)
                acc = acc.add(array.get(i, j, k));
        assertEquals(acc, sum);
    }

    @Test
    void testCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<Complex> increased = pArray.stream()
            .map((value) -> value.add(one))
            .collect(NDArrayCollectors.toComplexF64NDArray(pArray.dims()));
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).add(one), increased.get(i));
    }

    @Test
    void testParallelCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value.add(one))
            .collect(NDArrayCollectors.toComplexF64NDArray(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(one), increased.get(i));
    }

    @Test
    void testToString() {
        String str = pArray.toString();
        assertEquals("NDArray<ComplexF64>(4 × 3 × 5)", str);
    }

    @Test
    void testcontentToString() {
        String str = pArray.contentToString();
        String expected = new StringBuilder()
            .append("NDArray<ComplexF64>(4 × 3 × 5)\n")
            .append("[:, :, 0] =\n")
            .append("0,00000e+00+0,00000e+00i	2,00000e+01+-2,00000e+01i	4,00000e+01+-4,00000e+01i	\n")
            .append("1,00000e+00+-1,00000e+00i	2,10000e+01+-2,10000e+01i	4,10000e+01+-4,10000e+01i	\n")
            .append("2,00000e+00+-2,00000e+00i	2,20000e+01+-2,20000e+01i	4,20000e+01+-4,20000e+01i	\n")
            .append("3,00000e+00+-3,00000e+00i	2,30000e+01+-2,30000e+01i	4,30000e+01+-4,30000e+01i	\n")
            .append("\n")
            .append("[:, :, 1] =\n")
            .append("4,00000e+00+-4,00000e+00i	2,40000e+01+-2,40000e+01i	4,40000e+01+-4,40000e+01i	\n")
            .append("5,00000e+00+-5,00000e+00i	2,50000e+01+-2,50000e+01i	4,50000e+01+-4,50000e+01i	\n")
            .append("6,00000e+00+-6,00000e+00i	2,60000e+01+-2,60000e+01i	4,60000e+01+-4,60000e+01i	\n")
            .append("7,00000e+00+-7,00000e+00i	2,70000e+01+-2,70000e+01i	4,70000e+01+-4,70000e+01i	\n")
            .append("\n")
            .append("[:, :, 2] =\n")
            .append("8,00000e+00+-8,00000e+00i	2,80000e+01+-2,80000e+01i	4,80000e+01+-4,80000e+01i	\n")
            .append("9,00000e+00+-9,00000e+00i	2,90000e+01+-2,90000e+01i	4,90000e+01+-4,90000e+01i	\n")
            .append("1,00000e+01+-1,00000e+01i	3,00000e+01+-3,00000e+01i	5,00000e+01+-5,00000e+01i	\n")
            .append("1,10000e+01+-1,10000e+01i	3,10000e+01+-3,10000e+01i	5,10000e+01+-5,10000e+01i	\n")
            .append("\n")
            .append("[:, :, 3] =\n")
            .append("1,20000e+01+-1,20000e+01i	3,20000e+01+-3,20000e+01i	5,20000e+01+-5,20000e+01i	\n")
            .append("1,30000e+01+-1,30000e+01i	3,30000e+01+-3,30000e+01i	5,30000e+01+-5,30000e+01i	\n")
            .append("1,40000e+01+-1,40000e+01i	3,40000e+01+-3,40000e+01i	5,40000e+01+-5,40000e+01i	\n")
            .append("1,50000e+01+-1,50000e+01i	3,50000e+01+-3,50000e+01i	5,50000e+01+-5,50000e+01i	\n")
            .append("\n")
            .append("[:, :, 4] =\n")
            .append("1,60000e+01+-1,60000e+01i	3,60000e+01+-3,60000e+01i	5,60000e+01+-5,60000e+01i	\n")
            .append("1,70000e+01+-1,70000e+01i	3,70000e+01+-3,70000e+01i	5,70000e+01+-5,70000e+01i	\n")
            .append("1,80000e+01+-1,80000e+01i	3,80000e+01+-3,80000e+01i	5,80000e+01+-5,80000e+01i	\n")
            .append("1,90000e+01+-1,90000e+01i	3,90000e+01+-3,90000e+01i	5,90000e+01+-5,90000e+01i	\n")
            .append("\n")
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testAddArrayTopArray() {
        NDArray<Complex> array2 = new ComplexF64NDArray(pArray);
        NDArray<Complex> array3 = pArray.add(array2);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddpArrayToArray() {
        NDArray<Complex> array2 = new ComplexF64NDArray(pArray);
        NDArray<Complex> array3 = array2.add(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddpArrayTopArray() {
        NDArray<Complex> pArray2 = array.permuteDims(0, 2, 1);
        NDArray<Complex> array3 = pArray2.add(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).multiply(2), array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Complex> pArray2 = pArray.add(5);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).add(5), pArray2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> pArray2 = array2.permuteDims(0, 2, 1);
        NDArray<Complex> array3 = pArray2.add(pArray, 5.3, pArray2, new Complex(3,1));
        for (int i = 0; i < pArray.length(); i++) {
            Complex expected = pArray.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).multiply(2), pArray2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(5);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i).add(5), pArray2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(pArray, 5.3, pArray2, new Complex(3,1));
        for (int i = 0; i < pArray.length(); i++) {
            Complex expected = pArray.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(pArray2.get(i)).abs() < 1e5);
        }
    }

    @Test
    void testCopy() {
        NDArray<Complex> array2 = pArray.copy();
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i), array2.get(i));
        array2.set(new Complex(0,0), 5);
        assertNotEquals(pArray.get(5), array2.get(5));
    }

    @Test
    void testFillComplex() {
        pArray.fill(new Complex(3,3));
        for (Complex elem : pArray)
            assertEquals(new Complex(3, 3), elem);
    }

    @Test
    void testFillReal() {
        pArray.fill(3);
        for (Complex elem : pArray)
            assertEquals(new Complex(3, 0), elem);
    }

    @Test
    void testSliceAndToArray() {
        NDArray<Complex> slice = pArray.slice(1, ":", "1:4");
        Complex[][] arr = (Complex[][])slice.toArray();
        for (int i = 0; i < slice.dims(0); i++)
            for (int j = 0; j < slice.dims(1); j++)
                assertEquals(array.get(1, 1 + i, j), arr[j][i]);
    }

    @Test
    void testToRS2DDataSet() {
        DataSet dataSet = pArray.toRS2DDataSet();
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = 0; k < pArray.dims(2); k++) {
                    assertEquals(array.get(i, k, j).getReal(), dataSet.getData(0).getRealElement(i, j, k, 0));
                    assertEquals(array.get(i, k, j).getImaginary(), dataSet.getData(0).getImaginaryElement(i, j, k, 0));
                }
    }

    @Test
    void testToRS2DDataSetTooManyDimensions() {
        NDArray<Complex> array2 = new ComplexF64NDArray(new int[]{2,2,2,2,2,2}).permuteDims(5, 4, 3, 2, 1, 0);
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> array2.toRS2DDataSet());
        assertEquals(String.format(AbstractNDArray.ERROR_RS2D_DATA_SET_TOO_HIGH_DIMENSIONAL, 6),
                     exception.getMessage());
    }

    @Test
    void testConcatenate() {
        NDArray<Complex> array2 = new ComplexF64NDArray(new int[]{4, 3, 2}).fill(1);
        NDArray<Complex> array3 = pArray.concatenate(2, array2);
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = 0; k < pArray.dims(2); k++)
                    assertEquals(pArray.get(i, j, k), array3.get(i, j, k));
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = pArray.dims(2); k < pArray.dims(2) + array2.dims(2); k++)
                    assertEquals(new Complex(1, 0), array3.get(i, j, k));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Complex> array2 = pArray.copy().fill(1).slice(":", ":", "1:3");
        NDArray<Complex> array3 = new ComplexF64NDArray(new int[]{5, 3, 4}).permuteDims(2, 1, 0);
        NDArray<Complex> array4 = new ComplexF64NDArray(new int[]{36}).fill(new Complex(2, -2)).reshape(4, 3, 3);
        NDArray<Complex> array5 = pArray.concatenate(2, array2, array3, array4);
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
                    assertEquals(new Complex(1, 0), array5.get(i, j, k));
        start = end;
        end += array3.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(new Complex(0, 0), array5.get(i, j, k));
        start = end;
        end += array4.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(new Complex(2, -2), array5.get(i, j, k));
    }

    @Test
    void testReal() {
        NDArray<Double> real = pArray.real();
        pArray.streamLinearIndices()
            .forEach(i -> assertEquals(pArray.get(i).getReal(), real.get(i)));
    }

    @Test
    void testImag() {
        NDArray<Double> imag = pArray.imaginary();
        pArray.streamLinearIndices()
            .forEach(i -> assertEquals(pArray.get(i).getImaginary(), imag.get(i)));
    }

    @Test
    void testAbs() {
        NDArray<Double> abs = pArray.abs();
        pArray.streamLinearIndices()
            .forEach(i -> assertTrue(pArray.get(i).abs() - abs.get(i) < 1e-5));
    }

    @Test
    void testAngle() {
        NDArray<Double> angle = pArray.angle();
        pArray.streamLinearIndices()
            .forEach(i -> assertTrue(pArray.get(i).getArgument() - angle.get(i) < 1e-5));
    }
}
