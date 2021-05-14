package com.mediso.mri.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs2d.spinlab.data.DataSet;

public class TestComplexF64NDArrayReshape {
    NDArray<Complex> array, reshaped;

    @BeforeEach
    public void setup() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        double[] imag = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        array = new ComplexF64NDArray(dims, real, imag);
        reshaped = array.reshape(20, 3);
    }

    @Test
    public void testGetNegativeLinearIndexing() {
        assertEquals(new Complex(55, -55), reshaped.get(-5));
    }

    @Test
    public void testGetNegativeCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in parent
        assertEquals(new Complex(linearIndex, -linearIndex), reshaped.get(10, -1));
    }

    @Test
    public void testSetLinearIndexingGetCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals(new Complex(linearIndex, -linearIndex), reshaped.get(10, -1));
        reshaped.set(new Complex(1, 1), linearIndex);
        assertEquals(new Complex(1, 1), reshaped.get(10, -1));
    }

    @Test
    public void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        reshaped.set(new Complex(1, 1), 10, -1);
        assertEquals(new Complex(1, 1), reshaped.get(linearIndex));
    }

    @Test
    public void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, reshaped.length(), 60),
            exception.getMessage());
    }

    @Test
    public void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(-61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, reshaped.length(), -61),
            exception.getMessage());
    }

    @Test
    public void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "20 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    public void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> reshaped.get(-21,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "20 × 3", "[-21, 1]"),
            exception.getMessage());
    }

    @Test
    public void testWrongSetLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(zero, 60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, reshaped.length(), 60),
            exception.getMessage());
    }

    @Test
    public void testWrongSetNegativeLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(zero, -61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, reshaped.length(), -61),
            exception.getMessage());
    }

    @Test
    public void testWrongSetCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(zero, 1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "20 × 3", "[1, 3]"),
            exception.getMessage());
    }

    @Test
    public void testWrongSetNegativeCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> reshaped.set(zero, -21,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "20 × 3", "[-21, 1]"),
            exception.getMessage());
    }

    @Test
    public void testGetDimensionMismatchTooMany() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.get(1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    public void testGetDimensionMismatchNotEnough() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.get(1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    public void testSetDimensionMismatchTooMany() {
        Complex zero = new Complex(0,0);
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> reshaped.set(zero, 1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    public void testSetDimensionMismatchNotEnough() {
        Complex zero = new Complex(0,0);
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> reshaped.set(zero, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    public void testEltype() {
        assertEquals(Complex.class, reshaped.eltype());
    }

    @Test
    public void testToArray() {
        Complex[][] arr = (Complex[][])reshaped.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr.length; j++) {
                assertEquals(new Complex(linearIndex, -linearIndex), arr[j][i]);
                linearIndex++;
            }
    }

    @Test
    public void testEqual() {
        NDArray<Complex> array2 = new ComplexF64NDArray(reshaped);
        assertEquals(reshaped, array2);
        array2.set(new Complex(0,0), 10);
        assertNotEquals(reshaped, array2);
    }

    @Test
    public void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { reshaped.hashCode(); });
    }

    @Test
    public void testIterator() {
        int linearIndex = 0;
        for (Complex value : reshaped) {
            assertEquals(new Complex(linearIndex, -linearIndex), value);
            linearIndex++;
        }
    }

    @Test
    public void testStream() {
        final int[] linearIndex = new int[1];
        reshaped.stream().forEach((value) -> {
            assertEquals(new Complex(linearIndex[0], -linearIndex[0]), value);
            linearIndex[0]++;
        });
    }

    @Test
    public void testParallelStream() {
        Complex sum = reshaped.stream().parallel()
            .reduce(new Complex(0,0), (acc, item) -> acc.add(item));
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(new Complex(GaussSum, -GaussSum), sum);
    }

    @Test
    public void testCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = reshaped.stream()
            .map((value) -> value.add(one))
            .collect(NDArrayCollectors.toComplexF64NDArray(reshaped.dims()));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).add(one), increased.get(i));
    }

    @Test
    public void testParallelCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = reshaped.stream().parallel()
            .map((value) -> value.add(one))
            .collect(NDArrayCollectors.toComplexF64NDArray(reshaped.dims()));
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).add(one), increased.get(i));
    }

    @Test
    public void testToString() {
        String str = reshaped.toString();
        assertEquals("NDArray<ComplexF64>(20 × 3)", str);
    }

    @Test
    public void testcontentToString() {
        String str = reshaped.contentToString();
        String expected = new StringBuilder()
            .append("NDArray<ComplexF64>(20 × 3)\n")
            .append("0,00000e+00+0,00000e+00i	2,00000e+01+-2,00000e+01i	4,00000e+01+-4,00000e+01i	\n")
            .append("1,00000e+00+-1,00000e+00i	2,10000e+01+-2,10000e+01i	4,10000e+01+-4,10000e+01i	\n")
            .append("2,00000e+00+-2,00000e+00i	2,20000e+01+-2,20000e+01i	4,20000e+01+-4,20000e+01i	\n")
            .append("3,00000e+00+-3,00000e+00i	2,30000e+01+-2,30000e+01i	4,30000e+01+-4,30000e+01i	\n")
            .append("4,00000e+00+-4,00000e+00i	2,40000e+01+-2,40000e+01i	4,40000e+01+-4,40000e+01i	\n")
            .append("5,00000e+00+-5,00000e+00i	2,50000e+01+-2,50000e+01i	4,50000e+01+-4,50000e+01i	\n")
            .append("6,00000e+00+-6,00000e+00i	2,60000e+01+-2,60000e+01i	4,60000e+01+-4,60000e+01i	\n")
            .append("7,00000e+00+-7,00000e+00i	2,70000e+01+-2,70000e+01i	4,70000e+01+-4,70000e+01i	\n")
            .append("8,00000e+00+-8,00000e+00i	2,80000e+01+-2,80000e+01i	4,80000e+01+-4,80000e+01i	\n")
            .append("9,00000e+00+-9,00000e+00i	2,90000e+01+-2,90000e+01i	4,90000e+01+-4,90000e+01i	\n")
            .append("1,00000e+01+-1,00000e+01i	3,00000e+01+-3,00000e+01i	5,00000e+01+-5,00000e+01i	\n")
            .append("1,10000e+01+-1,10000e+01i	3,10000e+01+-3,10000e+01i	5,10000e+01+-5,10000e+01i	\n")
            .append("1,20000e+01+-1,20000e+01i	3,20000e+01+-3,20000e+01i	5,20000e+01+-5,20000e+01i	\n")
            .append("1,30000e+01+-1,30000e+01i	3,30000e+01+-3,30000e+01i	5,30000e+01+-5,30000e+01i	\n")
            .append("1,40000e+01+-1,40000e+01i	3,40000e+01+-3,40000e+01i	5,40000e+01+-5,40000e+01i	\n")
            .append("1,50000e+01+-1,50000e+01i	3,50000e+01+-3,50000e+01i	5,50000e+01+-5,50000e+01i	\n")
            .append("1,60000e+01+-1,60000e+01i	3,60000e+01+-3,60000e+01i	5,60000e+01+-5,60000e+01i	\n")
            .append("1,70000e+01+-1,70000e+01i	3,70000e+01+-3,70000e+01i	5,70000e+01+-5,70000e+01i	\n")
            .append("1,80000e+01+-1,80000e+01i	3,80000e+01+-3,80000e+01i	5,80000e+01+-5,80000e+01i	\n")
            .append("1,90000e+01+-1,90000e+01i	3,90000e+01+-3,90000e+01i	5,90000e+01+-5,90000e+01i	\n")        
            .toString();
        assertEquals(expected, str);
    }

    @Test
    public void testAdd() {
        NDArray<Complex> array2 = new ComplexF64NDArray(reshaped);
        NDArray<Complex> array3 = reshaped.add(array2);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).multiply(2), array3.get(i));
    }

    @Test
    public void testAddScalar() {
        NDArray<Complex> array2 = reshaped.add(5);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).add(5), array2.get(i));
    }

    @Test
    public void testAddMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(reshaped);
        NDArray<Complex> array3 = reshaped.add(array2, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < reshaped.length(); i++) {
            Complex expected = reshaped.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e5);
        }
    }

    @Test
    public void testAddInplace() {
        NDArray<Complex> array2 = new ComplexF64NDArray(reshaped);
        array2.addInplace(reshaped);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).multiply(2), array2.get(i));
    }

    @Test
    public void testAddInplaceScalar() {
        NDArray<Complex> array2 = new ComplexF64NDArray(reshaped);
        array2.addInplace(5);
        for (int i = 0; i < reshaped.length(); i++)
            assertEquals(reshaped.get(i).add(5), array2.get(i));
    }

    @Test
    public void testAddInplaceMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(reshaped);
        array2.addInplace(reshaped, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < reshaped.length(); i++) {
            Complex expected = reshaped.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array2.get(i)).abs() < 1e5);
        }
    }

    @Test
    public void testSum() {
        Complex sum = reshaped.sum();
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(new Complex(GaussSum, -GaussSum), sum);
    }

    @Test
    public void testSum1D() {
        NDArray<Complex> sum = reshaped.sum(1);
        for (int i = 0; i < sum.dims(0); i++) {
            double GaussSum = (reshaped.get(i,0).getReal() + reshaped.get(i,-1).getReal()) * 3 / 2;
            assertEquals(new Complex(GaussSum, -GaussSum), sum.get(i));
        }
    }

    @Test
    public void testSum2D() {
        reshaped = array.reshape(5,4,3);
        NDArray<Complex> sum = reshaped.sum(2, 1);
        for (int i = 0; i < sum.length(); i++) {
            Complex acc = new Complex(0,0);
            for (int j = 0; j < reshaped.dims(1); j++)
                for (int k = 0; k < reshaped.dims(2); k++)
                    acc = acc.add(reshaped.get(i,j,k));
            assertEquals(acc, sum.get(i));
        }
    }

    @Test
    public void testCopy() {
        NDArray<Complex> array2 = reshaped.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(reshaped.get(i), array2.get(i));
        array2.set(new Complex(0,0), 5);
        assertNotEquals(reshaped.get(5), array2.get(5));
    }

    @Test
    public void testFillComplex() {
        reshaped.fill(new Complex(3,3));
        for (Complex elem : reshaped)
            assertEquals(new Complex(3, 3), elem);
        for (Complex elem : array)
            assertEquals(new Complex(3, 3), elem);
    }

    @Test
    public void testFillReal() {
        reshaped.fill(3);
        for (Complex elem : reshaped)
            assertEquals(new Complex(3, 0), elem);
        for (Complex elem : array)
            assertEquals(new Complex(3, 0), elem);
    }

    @Test
    public void testPermuteDimsTooShortPermutationVector() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(0,2));
        assertEquals(
            String.format(NDArrayPermuteDimsView.ERROR_PERMUTATOR_SIZE_MISMATCH, "[0, 2]", "5 × 4 × 3"),
            exception.getMessage());
    }

    @Test
    public void testPermuteDimsTooLongPermutationVector() {
        reshaped = array.reshape(5,4,3);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(0,2,1,4));
        assertEquals(
            String.format(NDArrayPermuteDimsView.ERROR_PERMUTATOR_SIZE_MISMATCH, "[0, 2, 1, 4]", "5 × 4 × 3"),
            exception.getMessage());
    }

    @Test
    public void testPermuteDimsRepeatedDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reshaped.permuteDims(1,1));
        assertEquals(
            String.format(NDArrayPermuteDimsView.ERROR_INVALID_PERMUTATOR, "[1, 1]", "20 × 3"),
            exception.getMessage());
    }

    @Test
    public void testToRS2DDataSet() {
        DataSet dataSet = reshaped.toRS2DDataSet();
        for (int i = 0; i < reshaped.dims(0); i++)
            for (int j = 0; j < reshaped.dims(1); j++) {
                assertEquals(reshaped.get(i, j).getReal(), dataSet.getData(0).getRealElement(i, j, 0, 0));
                assertEquals(reshaped.get(i, j).getImaginary(), dataSet.getData(0).getImaginaryElement(i, j, 0, 0));
            }
    }

    @Test
    public void testToRS2DDataSetTooManyDimensions() {
        NDArray<Complex> array2 = new ComplexF64NDArray(new int[]{2,2,2,2,2,2}).reshape(8,8,1,1,1,1);
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> array2.toRS2DDataSet());
        assertEquals(String.format(AbstractNDArray.ERROR_RS2D_DATA_SET_TOO_HIGH_DIMENSIONAL, 6),
                     exception.getMessage());
    }

    @Test
    public void testConcatenate() {
        NDArray<Complex> array2 = new ComplexF64NDArray(new int[]{5, 3}).fill(1);
        NDArray<Complex> array3 = reshaped.concatenate(0, array2);
        for (int i = 0; i < reshaped.dims(0); i++)
            for (int j = 0; j < reshaped.dims(1); j++)
                assertEquals(reshaped.get(i, j), array3.get(i, j));
        for (int i = reshaped.dims(0); i < reshaped.dims(0) + array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                assertEquals(new Complex(1, 0), array3.get(i, j));
    }

    @Test
    public void testConcatenateMultiple() {
        NDArray<Complex> array2 = reshaped.copy().fill(1).slice("1:5", ":");
        NDArray<Complex> array3 = new ComplexF64NDArray(new int[]{3, 2}).permuteDims(1, 0);
        NDArray<Complex> array4 = new ComplexF64NDArray(new int[]{9}).fill(new Complex(2, -2)).reshape(3, 3);
        NDArray<Complex> array5 = reshaped.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = reshaped.dims(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.dims(1); j++)
                assertEquals(reshaped.get(i, j), array5.get(i, j));
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
        NDArray<Double> real = reshaped.real();
        reshaped.streamLinearIndices()
            .forEach(i -> assertEquals(reshaped.get(i).getReal(), real.get(i)));
    }

    @Test
    public void testImag() {
        NDArray<Double> imag = reshaped.imaginary();
        reshaped.streamLinearIndices()
            .forEach(i -> assertEquals(reshaped.get(i).getImaginary(), imag.get(i)));
    }

    @Test
    public void testAbs() {
        NDArray<Double> abs = reshaped.abs();
        reshaped.streamLinearIndices()
            .forEach(i -> assertTrue(reshaped.get(i).abs() - abs.get(i) < 1e-5));
    }

    @Test
    public void testAngle() {
        NDArray<Double> angle = reshaped.angle();
        reshaped.streamLinearIndices()
            .forEach(i -> assertTrue(reshaped.get(i).getArgument() - angle.get(i) < 1e-5));
    }
}
