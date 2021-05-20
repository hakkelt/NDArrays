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

class TestRealF32NDArrayFunctions {
    RealF32NDArray array;

    @BeforeEach
    void setup() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        array = new RealF32NDArray(dims, real);
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals(55, array.get(-5));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals(linearIndex, array.get(2, -3, -1));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        assertEquals(linearIndex, array.get(2, -3, -1));
        array.set(1, linearIndex);
        assertEquals(1, array.get(2, -3, -1));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2]
        array.set(1, 2, -3, -1);
        assertEquals(1, array.get(linearIndex));
    }

    @Test
    void testWrongGetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(-61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongGetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1,1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongGetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(1,-6,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(0, 60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(0, -61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, array.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(0, 1,1,3));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, 1, 3]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> array.set(0, 1,-6,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 5 × 3", "[1, -6, 1]"),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testGetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.get(1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchTooMany() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> array.set(0, 1,1,1,0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> array.set(0, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Float.class, array.eltype());
    }

    @Test
    void testToArray() {
        Float[][][] arr = (Float[][][])array.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0][0].length; i++)
            for (int j = 0; j < arr[0].length; j++)
                for (int k = 0; k < arr.length; k++) {
                    assertEquals(linearIndex, arr[k][j][i]);
                    linearIndex++;
                }
    }

    @Test
    void testEqual() {
        NDArray<Float> array2 = new RealF32NDArray(array);
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
        for (Float value : array) {
            assertEquals(linearIndex, value);
            linearIndex++;
        }
    }

    @Test
    void testStream() {
        final int[] linearIndex = new int[1];
        array.stream().forEach((value) -> {
            assertEquals(linearIndex[0], value);
            linearIndex[0]++;
        });
    }

    @Test
    void testParallelStream() {
        Float sum = array.stream().parallel()
            .reduce(0.f, (acc, item) -> acc + item);
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(GaussSum, sum);
    }

    @Test
    void testCollector() {
        NDArray<?> increased = array.stream()
            .map((value) -> value + 1)
            .collect(NDArrayCollectors.toRealF32NDArray(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) + 1, increased.get(i));
    }

    @Test
    void testParallelCollector() {
        NDArray<?> increased = array.stream().parallel()
            .map((value) -> value + 1)
            .collect(NDArrayCollectors.toRealF32NDArray(array.dims()));
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) + 1, increased.get(i));
    }

    @Test
    void testToString() {
        String str = array.toString();
        assertEquals("NDArray<RealF32>(4 × 5 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = array.contentToString();
        String expected = new StringBuilder()
            .append("NDArray<RealF32>(4 × 5 × 3)\n")
            .append("[:, :, 0] =\n")
            .append("0,00000e+00	4,00000e+00	8,00000e+00	1,20000e+01	1,60000e+01	\n")
            .append("1,00000e+00	5,00000e+00	9,00000e+00	1,30000e+01	1,70000e+01	\n")
            .append("2,00000e+00	6,00000e+00	1,00000e+01	1,40000e+01	1,80000e+01	\n")
            .append("3,00000e+00	7,00000e+00	1,10000e+01	1,50000e+01	1,90000e+01	\n")
            .append("\n")
            .append("[:, :, 1] =\n")
            .append("2,00000e+01	2,40000e+01	2,80000e+01	3,20000e+01	3,60000e+01	\n")
            .append("2,10000e+01	2,50000e+01	2,90000e+01	3,30000e+01	3,70000e+01	\n")
            .append("2,20000e+01	2,60000e+01	3,00000e+01	3,40000e+01	3,80000e+01	\n")
            .append("2,30000e+01	2,70000e+01	3,10000e+01	3,50000e+01	3,90000e+01	\n")
            .append("\n")
            .append("[:, :, 2] =\n")
            .append("4,00000e+01	4,40000e+01	4,80000e+01	5,20000e+01	5,60000e+01	\n")
            .append("4,10000e+01	4,50000e+01	4,90000e+01	5,30000e+01	5,70000e+01	\n")
            .append("4,20000e+01	4,60000e+01	5,00000e+01	5,40000e+01	5,80000e+01	\n")
            .append("4,30000e+01	4,70000e+01	5,10000e+01	5,50000e+01	5,90000e+01	\n")
            .append("\n")
            .toString();
        assertEquals(expected, str);
    }

    @Test
    void testAdd() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> array3 = array.add(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) * 2, array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Float> array2 = array.add(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) + 5, array2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> array3 = array.add(array2, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            double expected = array.get(i) * 3 + 5.3 + 3;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.addInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) * 2, array2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.addInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) + 5, array2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.addInplace(array, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            double expected = array.get(i) * 3 + 5.3 + 3;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void testSubtract() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> array3 = array.subtract(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals(0, array3.get(i));
    }

    @Test
    void testSubtractScalar() {
        NDArray<Float> array2 = array.subtract(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) - 5, array2.get(i));
    }

    @Test
    void testSubtractMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> array3 = array.subtract(array2, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            float expected = array.get(i) * -1.f - 5.3f - 3.f;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testSubtractInplace() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.subtractInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals(0, array2.get(i));
    }

    @Test
    void testSubtractInplaceScalar() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.subtractInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) - 5.f, array2.get(i));
    }

    @Test
    void testSubtractInplaceMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.subtractInplace(array, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            float expected = array.get(i) * -1.f - 5.3f - 3.f;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void testMultiply() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> array3 = array.multiply(array2);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) * array.get(i), array3.get(i));
    }

    @Test
    void testMultiplyScalar() {
        NDArray<Float> array2 = array.multiply(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) * 5.f, array2.get(i));
    }

    @Test
    void testMultiplyMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> array3 = array.multiply(array, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            float expected = array.get(i) * array.get(i) * 5.3f *
                array2.get(i) * 3.f;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e7);
        }
    }

    @Test
    void testMultiplyInplace() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.multiplyInplace(array);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) * array.get(i), array2.get(i));
    }

    @Test
    void testMultiplyInplaceScalar() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.multiplyInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i) * 5.f, array2.get(i));
    }

    @Test
    void testMultiplyInplaceMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.multiplyInplace(array, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            float expected = array.get(i) * array.get(i) * 5.3f *
                array2.get(i) * 3.f;
            if (expected == 0)
                assertTrue(Math.abs(expected - array2.get(i)) < 1e7);
            else
                assertTrue(Math.abs(expected - array2.get(i)) / Math.abs(expected) < 1e7);
        }
    }

    @Test
    void testDivide() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> array3 = array.divide(array2);
        for (int i = 0; i < array.length(); i++) {
            if (array.get(i) == 0)
                assertTrue(array3.get(i).isNaN());
            else
                assertEquals(1, array3.get(i));
        }
    }

    @Test
    void testDivideScalar() {
        NDArray<Float> array2 = array.divide(5);
        for (int i = 0; i < array.length(); i++)
            assertTrue(Math.abs(array.get(i) / 5.f - array2.get(i)) < 1e-5);
    }

    @Test
    void testDivideMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> array3 = array.divide(array, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            Float expected = array.get(i) / array.get(i) / 5.3f
                / array2.get(i) / 3.0f;
            if (expected.isNaN())
                assertTrue(array3.get(i).isNaN());
            else
                assertTrue(Math.abs(expected - array3.get(i)) < 1e7);
        }
    }

    @Test
    void testDivideInplace() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.divideInplace(array);
        for (int i = 0; i < array.length(); i++)
        if (array.get(i) == 0)
            assertTrue(array2.get(i).isNaN());
        else
            assertEquals(1, array2.get(i));
    }

    @Test
    void testDivideInplaceScalar() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.divideInplace(5);
        for (int i = 0; i < array.length(); i++)
            assertTrue(Math.abs(array.get(i) / 5.f - array2.get(i)) < 1e-5);
    }

    @Test
    void testDivideInplaceMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        array2.divideInplace(array, 5.3, array2, 3);
        for (int i = 0; i < array.length(); i++) {
            Float expected = array.get(i) / array.get(i) / 5.3f
                / array.get(i) / 3.f;
            if (expected.isNaN())
                assertTrue(array2.get(i).isNaN());
            else
                assertTrue(Math.abs(expected - array2.get(i)) < 1e7);
        }
    }

    @Test
    void testSum() {
        Float sum = array.sum();
        int GaussSum = (0 + array.length() - 1) * array.length() / 2;
        assertEquals(GaussSum, sum);
    }

    @Test
    void testSum1D() {
        NDArray<Float> sum = array.sum(1);
        for (int i = 0; i < sum.dims(0); i++) {
            for (int j = 0; j < sum.dims(1); j++) {
                float GaussSum = (array.get(i,0,j) + array.get(i,-1,j)) * (float)array.dims(1) / 2.f;
                assertEquals(GaussSum, sum.get(i,j));
            }
        }
    }

    @Test
    void testSum2D() {
        NDArray<Float> sum = array.sum(2, 1);
        for (int i = 0; i < sum.length(); i++) {
            float GaussSum = (array.get(i,0,0) + array.get(i,-1,-1)) * 15.f / 2.f;
            assertEquals(GaussSum, sum.get(i));
        }
    }

    @Test
    void test0Norm() {
        array.slice(":", 0, ":").fill(0);
        double norm = array.stream()
            .filter(value -> value != 0.)
            .count();
        assertEquals(norm, array.norm(0));
    }

    @Test
    void test1Norm() {
        double norm = array.stream()
            .mapToDouble(value -> Math.abs(value))
            .reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, array.norm(1));
    }

    @Test
    void test2Norm() {
        double norm = Math.sqrt(array.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 2))
            .reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, array.norm());
    }

    @Test
    void testPQuasinorm() {
        double norm = Math.pow(array.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 0.5))
            .reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, array.norm(0.5));
    }

    @Test
    void testPNorm() {
        double norm = Math.pow(array.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 3.5))
            .reduce(0., (acc, item) -> acc + item), 1 / 3.5);
        assertEquals(norm, array.norm(3.5));
    }

    @Test
    void testInfNorm() {
        double norm = array.stream()
            .mapToDouble(value -> Math.abs(value))
            .max().getAsDouble();
        assertEquals(norm, array.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testCopy() {
        NDArray<Float> array2 = array.copy();
        for (int i = 0; i < array.length(); i++)
            assertEquals(array.get(i), array2.get(i));
        array2.set(0, 5);
        assertNotEquals(array.get(5), array2.get(5));
    }

    @Test
    void testFillFloat() {
        array.fill(3);
        for (Float elem : array)
            assertEquals(3, elem);
    }

    @Test
    void testFillReal() {
        array.fill(3);
        for (Float elem : array)
            assertEquals(3, elem);
    }

    @Test
    void testPermuteDimsTooShortPermutationVector() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,2));
        assertEquals(
            String.format(NDArrayPermuteDimsView.ERROR_PERMUTATOR_SIZE_MISMATCH, "[0, 2]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsTooLongPermutationVector() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,2,1,4));
        assertEquals(
            String.format(NDArrayPermuteDimsView.ERROR_PERMUTATOR_SIZE_MISMATCH, "[0, 2, 1, 4]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    void testPermuteDimsRepeatedDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0,1,1));
        assertEquals(
            String.format(NDArrayPermuteDimsView.ERROR_INVALID_PERMUTATOR, "[0, 1, 1]", "4 × 5 × 3"),
            exception.getMessage());
    }

    @Test
    void testToRS2DDataSet() {
        DataSet dataSet = array.toRS2DDataSet();
        for (int i = 0; i < array.dims(0); i++)
            for (int j = 0; j < array.dims(1); j++)
                for (int k = 0; k < array.dims(2); k++)
                    assertEquals(array.get(i, j, k), dataSet.getData(0).getRealElement(i, j, k, 0));
    }

    @Test
    void testToRS2DDataSetTooManyDimensions() {
        NDArray<Float> array2 = new RealF32NDArray(new int[]{2,2,2,2,2,2});
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> array2.toRS2DDataSet());
        assertEquals(String.format(AbstractNDArray.ERROR_RS2D_DATA_SET_TOO_HIGH_DIMENSIONAL, 6),
                     exception.getMessage());
    }
    
    @Test
    void testToSimpleITKImage() {
        Image image = array.toSimpleITKImage();
        array.streamCartesianIndices().forEach(index -> {
            VectorUInt32 idx = new VectorUInt32(array.ndims());
            IntStream.range(0, array.ndims()).forEach(i -> idx.set(i, index[i]));
            assertEquals(array.get(index).floatValue(), image.getPixelAsFloat(idx));
        });
    }

    @Test
    void testConcatenate() {
        NDArray<Float> array2 = new RealF32NDArray(new int[]{4, 2, 3}).fill(1);
        NDArray<Float> array3 = array.concatenate(1, array2);
        for (int i = 0; i < array.dims(0); i++)
            for (int j = 0; j < array.dims(1); j++)
                for (int k = 0; k < array.dims(2); k++)
                    assertEquals(array.get(i, j, k), array3.get(i, j, k));
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = array.dims(1); j < array.dims(1) + array2.dims(1); j++)
                for (int k = 0; k < array2.dims(2); k++)
                    assertEquals(1, array3.get(i, j, k));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Float> array2 = array.copy().fill(1).slice(":", "1:3", ":");
        NDArray<Float> array3 = new RealF32NDArray(new int[]{3, 4, 4}).permuteDims(2, 1, 0);
        NDArray<Float> array4 = new RealF32NDArray(new int[]{12}).fill(2).reshape(4, 1, 3);
        NDArray<Float> array5 = array.concatenate(1, array2, array3, array4);
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
                    assertEquals(1, array5.get(i, j, k));
        start = end;
        end += array3.dims(1);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.dims(2); k++)
                    assertEquals(0, array5.get(i, j, k));
        start = end;
        end += array4.dims(1);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.dims(2); k++)
                    assertEquals(2, array5.get(i, j, k));
    }

    @Test
    void testReal() {
        NDArray<Double> real = array.real();
        array.streamLinearIndices()
            .forEach(i -> assertEquals(array.get(i).floatValue(), real.get(i)));
    }

    @Test
    void testImag() {
        NDArray<Double> imag = array.imaginary();
        array.streamLinearIndices()
            .forEach(i -> assertEquals(0, imag.get(i)));
    }

    @Test
    void testAbs() {
        NDArray<Double> abs = array.abs();
        array.streamLinearIndices()
            .forEach(i -> assertTrue(Math.abs(array.get(i)) - abs.get(i) < 1e-5));
    }

    @Test
    void testAngle() {
        NDArray<Double> angle = array.angle();
        array.streamLinearIndices()
            .forEach(i -> assertEquals(0, angle.get(i)));
    }
}
