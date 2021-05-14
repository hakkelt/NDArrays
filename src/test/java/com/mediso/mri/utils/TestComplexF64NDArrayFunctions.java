package com.mediso.mri.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs2d.spinlab.data.DataSet;

public class TestComplexF64NDArrayFunctions {
    ComplexF64NDArray array;

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
    }

    @Test
    public void testGetNegativeLinearIndexing() {
        assertEquals(new Complex(55, -55), array.get(-5));
    }

    @Test
    public void testGetNegativeCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals(new Complex(linearIndex, -linearIndex), array.get(2, -3, -1));
    }

    @Test
    public void testSetLinearIndexingGetCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals(new Complex(linearIndex, -linearIndex), array.get(2, -3, -1));
        array.set(new Complex(1, 1), linearIndex);
        assertEquals(new Complex(1, 1), array.get(2, -3, -1));
    }

    @Test
    public void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        array.set(new Complex(1, 1), 2, -3, -1);
        assertEquals(new Complex(1, 1), array.get(linearIndex));
    }

    @Test
    public void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), 60),
            exception.getMessage());
    }

    @Test
    public void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(-61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), -61),
            exception.getMessage());
    }

    @Test
    public void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1,1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"),
            exception.getMessage());
    }

    @Test
    public void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1,-6,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"),
            exception.getMessage());
    }

    @Test
    public void testWrongSetLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(zero, 60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), 60),
            exception.getMessage());
    }

    @Test
    public void testWrongSetNegativeLinearIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(zero, -61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), -61),
            exception.getMessage());
    }

    @Test
    public void testWrongSetCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(zero, 1,1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"),
            exception.getMessage());
    }

    @Test
    public void testWrongSetNegativeCartesianIndexing() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(zero, 1,-6,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"),
            exception.getMessage());
    }

    @Test
    public void testGetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    public void testGetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    public void testSetDimensionMismatchTooMany() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> array.set(zero, 1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    public void testSetDimensionMismatchNotEnough() {
        Complex zero = new Complex(0,0);
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> array.set(zero, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    public void testEltype() {
        assertEquals(Complex.class, array.eltype());
    }

    @Test
    public void testToArray() {
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
    public void testEqual() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        assertEquals(array, array2);
        array2.set(new Complex(0,0), 10);
        assertNotEquals(array, array2);
    }

    @Test
    public void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { array.hashCode(); });
    }

    @Test
    public void testIterator() {
        int linearIndex = 0;
        for (Complex value : array) {
            assertEquals(new Complex(linearIndex, -linearIndex), value);
            linearIndex++;
        }
    }

    @Test
    public void testStream() {
        final int[] linearIndex = new int[1];
        array.stream().forEach((value) -> {
            assertEquals(new Complex(linearIndex[0], -linearIndex[0]), value);
            linearIndex[0]++;
        });
    }

    @Test
    public void testParallelStream() {
        Complex sum = array.stream().parallel()
            .reduce(new Complex(0,0), (acc, item) -> acc.add(item));
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(new Complex(GaussSum, -GaussSum), sum);
    }

    @Test
    public void testCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = array.stream()
            .map((value) -> value.add(one))
            .collect(NDArrayCollectors.toComplexF64NDArray(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(one), increased.get(i));
    }

    @Test
    public void testParallelCollector() {
        final Complex one = new Complex(1,-1);
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value.add(one))
            .collect(NDArrayCollectors.toComplexF64NDArray(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(one), increased.get(i));
    }

    @Test
    public void testToString() {
        String str = array.toString();
        assertEquals("NDArray<ComplexF64>(4 × 5 × 3)", str);
    }

    @Test
    public void testcontentToString() {
        String str = array.contentToString();
        String expected = new StringBuilder()
            .append("NDArray<ComplexF64>(4 × 5 × 3)\n")
            .append("[:, :, 0] =\n")
            .append("0,00000e+00+0,00000e+00i	4,00000e+00+-4,00000e+00i	8,00000e+00+-8,00000e+00i	1,20000e+01+-1,20000e+01i	1,60000e+01+-1,60000e+01i	\n")
            .append("1,00000e+00+-1,00000e+00i	5,00000e+00+-5,00000e+00i	9,00000e+00+-9,00000e+00i	1,30000e+01+-1,30000e+01i	1,70000e+01+-1,70000e+01i	\n")
            .append("2,00000e+00+-2,00000e+00i	6,00000e+00+-6,00000e+00i	1,00000e+01+-1,00000e+01i	1,40000e+01+-1,40000e+01i	1,80000e+01+-1,80000e+01i	\n")
            .append("3,00000e+00+-3,00000e+00i	7,00000e+00+-7,00000e+00i	1,10000e+01+-1,10000e+01i	1,50000e+01+-1,50000e+01i	1,90000e+01+-1,90000e+01i	\n")
            .append("\n")
            .append("[:, :, 1] =\n")
            .append("2,00000e+01+-2,00000e+01i	2,40000e+01+-2,40000e+01i	2,80000e+01+-2,80000e+01i	3,20000e+01+-3,20000e+01i	3,60000e+01+-3,60000e+01i	\n")
            .append("2,10000e+01+-2,10000e+01i	2,50000e+01+-2,50000e+01i	2,90000e+01+-2,90000e+01i	3,30000e+01+-3,30000e+01i	3,70000e+01+-3,70000e+01i	\n")
            .append("2,20000e+01+-2,20000e+01i	2,60000e+01+-2,60000e+01i	3,00000e+01+-3,00000e+01i	3,40000e+01+-3,40000e+01i	3,80000e+01+-3,80000e+01i	\n")
            .append("2,30000e+01+-2,30000e+01i	2,70000e+01+-2,70000e+01i	3,10000e+01+-3,10000e+01i	3,50000e+01+-3,50000e+01i	3,90000e+01+-3,90000e+01i	\n")
            .append("\n")
            .append("[:, :, 2] =\n")
            .append("4,00000e+01+-4,00000e+01i	4,40000e+01+-4,40000e+01i	4,80000e+01+-4,80000e+01i	5,20000e+01+-5,20000e+01i	5,60000e+01+-5,60000e+01i	\n")
            .append("4,10000e+01+-4,10000e+01i	4,50000e+01+-4,50000e+01i	4,90000e+01+-4,90000e+01i	5,30000e+01+-5,30000e+01i	5,70000e+01+-5,70000e+01i	\n")
            .append("4,20000e+01+-4,20000e+01i	4,60000e+01+-4,60000e+01i	5,00000e+01+-5,00000e+01i	5,40000e+01+-5,40000e+01i	5,80000e+01+-5,80000e+01i	\n")
            .append("4,30000e+01+-4,30000e+01i	4,70000e+01+-4,70000e+01i	5,10000e+01+-5,10000e+01i	5,50000e+01+-5,50000e+01i	5,90000e+01+-5,90000e+01i	\n")
            .append("\n")
            .toString();
        assertEquals(expected, str);
    }

    @Test
    public void testAdd() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> array3 = array.add(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(2), array3.get(i));
    }

    @Test
    public void testAddScalar() {
        NDArray<Complex> array2 = array.add(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(5), array2.get(i));
    }

    @Test
    public void testAddMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> array3 = array.add(array2, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e5);
        }
    }

    @Test
    public void testAddInplace() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        array2.addInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(2), array2.get(i));
    }

    @Test
    public void testAddInplaceScalar() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        array2.addInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).add(5), array2.get(i));
    }

    @Test
    public void testAddInplaceMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        array2.addInplace(array, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).multiply(3).add(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array2.get(i)).abs() < 1e5);
        }
    }

    @Test
    public void testSubtract() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> array3 = array.subtract(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals(new Complex(0,0), array3.get(i));
    }

    @Test
    public void testSubtractScalar() {
        NDArray<Complex> array2 = array.subtract(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).subtract(5), array2.get(i));
    }

    @Test
    public void testSubtractMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> array3 = array.subtract(array2, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).multiply(-1).subtract(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e5);
        }
    }

    @Test
    public void testSubtractInplace() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        array2.subtractInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals(new Complex(0,0), array2.get(i));
    }

    @Test
    public void testSubtractInplaceScalar() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        array2.subtractInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).subtract(5), array2.get(i));
    }

    @Test
    public void testSubtractInplaceMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        array2.subtractInplace(array, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).multiply(-1).subtract(new Complex(5.3 + 3,1));
            assertTrue(expected.subtract(array2.get(i)).abs() < 1e5);
        }
    }

    @Test
    public void testMultiply() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> array3 = array.multiply(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(array.get(i)), array3.get(i));
    }

    @Test
    public void testMultiplyScalar() {
        NDArray<Complex> array2 = array.multiply(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(5), array2.get(i));
    }

    @Test
    public void testMultiplyMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> array3 = array.multiply(array, 5.3, array2, new Complex(3,1));
        for (int i = 0; i < array.length(); i++) {
            Complex expected = array.get(i).multiply(array.get(i)).multiply((float)5.3)
                .multiply(array2.get(i)).multiply(new Complex(3,1));
            assertTrue(expected.subtract(array3.get(i)).abs() < 1e7);
        }
    }

    @Test
    public void testMultiplyInplace() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        array2.multiplyInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(array.get(i)), array2.get(i));
    }

    @Test
    public void testMultiplyInplaceScalar() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        array2.multiplyInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i).multiply(5), array2.get(i));
    }

    @Test
    public void testMultiplyInplaceMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
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
    public void testDivide() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> array3 = array.divide(array2);
        for (int i = 0; i < array.length(); i++) {
            if (array.get(i).equals(new Complex(0,0)))
                assertTrue(array3.get(i).isNaN());
            else
                assertEquals(new Complex(1,0), array3.get(i));
        }
    }

    @Test
    public void testDivideScalar() {
        NDArray<Complex> array2 = array.divide(5);
        for (int i = 0; i < array.length(); i++)
            assertTrue(array.get(i).divide(5).subtract(array2.get(i)).abs() < 1e-5);
    }

    @Test
    public void testDivideMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        NDArray<Complex> array3 = array.divide(array, 5.3, array2, new Complex(3,1));
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
    public void testDivideInplace() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        array2.divideInplace(array);
        for (int i = 0; i < array.length(); i++)
        if (array.get(i).equals(new Complex(0,0)))
            assertTrue(array2.get(i).isNaN());
        else
            assertEquals(new Complex(1,0), array2.get(i));
    }

    @Test
    public void testDivideInplaceScalar() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
        array2.divideInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertTrue(array.get(i).divide(5).subtract(array2.get(i)).abs() < 1e-5);
    }

    @Test
    public void testDivideInplaceMultiple() {
        NDArray<Complex> array2 = new ComplexF64NDArray(array);
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
    public void testSum() {
        Complex sum = array.sum();
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(new Complex(GaussSum, -GaussSum), sum);
    }

    @Test
    public void testSum1D() {
        NDArray<Complex> sum = array.sum(1);
        for (int i = 0; i < sum.dims(0); i++) {
            for (int j = 0; j < sum.dims(1); j++) {
                double GaussSum = (array.get(i,0,j).getReal() + array.get(i,-1,j).getReal()) * 5 / 2;
                assertEquals(new Complex(GaussSum, -GaussSum), sum.get(i,j));
            }
        }
    }

    @Test
    public void testSum2D() {
        NDArray<Complex> sum = array.sum(2, 1);
        for (int i = 0; i < sum.length(); i++) {
            double GaussSum = (array.get(i,0,0).getReal() + array.get(i,-1,-1).getReal()) * (5 * 3) / 2;
            assertEquals(new Complex(GaussSum, -GaussSum), sum.get(i));
        }
    }

    @Test
    public void testCopy() {
        NDArray<Complex> array2 = array.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i), array2.get(i));
        array2.set(new Complex(0,0), 5);
        assertNotEquals(array.get(5), array2.get(5));
    }

    @Test
    public void testFillComplex() {
        array.fill(new Complex(3,3));
        for (Complex elem : array)
            assertEquals(new Complex(3, 3), elem);
    }

    @Test
    public void testFillReal() {
        array.fill(3);
        for (Complex elem : array)
            assertEquals(new Complex(3, 0), elem);
    }

    @Test
    public void testPermuteDimsTooShortPermutationVector() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,2));
        assertEquals(
            String.format(NDArrayPermuteDimsView.ERROR_PERMUTATOR_SIZE_MISMATCH, "[0, 2]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    public void testPermuteDimsTooLongPermutationVector() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,2,1,4));
        assertEquals(
            String.format(NDArrayPermuteDimsView.ERROR_PERMUTATOR_SIZE_MISMATCH, "[0, 2, 1, 4]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    public void testPermuteDimsRepeatedDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,1,1));
        assertEquals(
            String.format(NDArrayPermuteDimsView.ERROR_INVALID_PERMUTATOR, "[0, 1, 1]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    public void testToRS2DDataSet() {
        DataSet dataSet = array.toRS2DDataSet();
        for (int i = 0; i < array.dims(0); i++)
            for (int j = 0; j < array.dims(1); j++)
                for (int k = 0; k < array.dims(2); k++) {
                    assertEquals(array.get(i, j, k).getReal(), dataSet.getData(0).getRealElement(i, j, k, 0));
                    assertEquals(array.get(i, j, k).getImaginary(), dataSet.getData(0).getImaginaryElement(i, j, k, 0));
                }
    }

    @Test
    public void testToRS2DDataSetTooManyDimensions() {
        NDArray<Complex> array2 = new ComplexF64NDArray(new int[]{2,2,2,2,2,2});
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> array2.toRS2DDataSet());
        assertEquals(String.format(AbstractNDArray.ERROR_RS2D_DATA_SET_TOO_HIGH_DIMENSIONAL, 6),
                     exception.getMessage());
    }

    @Test
    public void testConcatenate() {
        NDArray<Complex> array2 = new ComplexF64NDArray(new int[]{4, 2, 3}).fill(1);
        NDArray<Complex> array3 = array.concatenate(1, array2);
        for (int i = 0; i < array.dims(0); i++)
            for (int j = 0; j < array.dims(1); j++)
                for (int k = 0; k < array.dims(2); k++)
                    assertEquals(array.get(i, j, k), array3.get(i, j, k));
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = array.dims(1); j < array.dims(1) + array2.dims(1); j++)
                for (int k = 0; k < array2.dims(2); k++)
                    assertEquals(new Complex(1, 0), array3.get(i, j, k));
    }

    @Test
    public void testConcatenateMultiple() {
        NDArray<Complex> array2 = array.copy().fill(1).slice(":", "1:3", ":");
        NDArray<Complex> array3 = new ComplexF64NDArray(new int[]{3, 4, 4}).permuteDims(2, 1, 0);
        NDArray<Complex> array4 = new ComplexF64NDArray(new int[]{12}).fill(new Complex(2, -2)).reshape(4, 1, 3);
        NDArray<Complex> array5 = array.concatenate(1, array2, array3, array4);
        int start = 0;
        int end = array.dims(1);
        for (int i = 0; i < array.dims(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array.dims(2); k++)
                    assertEquals(array.get(i, j, k), array5.get(i, j, k));
        start = end;
        end += array2.dims(1);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.dims(2); k++)
                    assertEquals(new Complex(1, 0), array5.get(i, j, k));
        start = end;
        end += array3.dims(1);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.dims(2); k++)
                    assertEquals(new Complex(0, 0), array5.get(i, j, k));
        start = end;
        end += array4.dims(1);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.dims(2); k++)
                    assertEquals(new Complex(2, -2), array5.get(i, j, k));
    }

    @Test
    public void testReal() {
        NDArray<Double> real = array.real();
        array.streamLinearIndices()
            .forEach(i -> assertEquals(array.get(i).getReal(), real.get(i)));
    }

    @Test
    public void testImag() {
        NDArray<Double> imag = array.imaginary();
        array.streamLinearIndices()
            .forEach(i -> assertEquals(array.get(i).getImaginary(), imag.get(i)));
    }

    @Test
    public void testAbs() {
        NDArray<Double> abs = array.abs();
        array.streamLinearIndices()
            .forEach(i -> assertTrue(array.get(i).abs() - abs.get(i) < 1e-5));
    }

    @Test
    public void testAngle() {
        NDArray<Double> angle = array.angle();
        array.streamLinearIndices()
            .forEach(i -> assertTrue(array.get(i).getArgument() - angle.get(i) < 1e-5));
    }
}
