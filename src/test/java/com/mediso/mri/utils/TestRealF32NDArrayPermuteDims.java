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

class TestRealF32NDArrayPermuteDims {
    NDArray<Float> array, pArray;

    @BeforeEach
    void setup() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        array = new RealF32NDArray(dims, real);
        pArray = array.permuteDims(0, 2, 1);
    }

    @Test
    void testGetNegativeLinearIndexing() {
        assertEquals(39.f, pArray.get(-5));
    }

    @Test
    void testGetNegativeCartesianIndexing() {
        assertEquals(50.f, pArray.get(2, -1, -3));
    }

    @Test
    void testSetLinearIndexingGetCartesianIndexing() {
        int parentLinearIndex = (2 * 5 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in parent
        assertEquals(Float.valueOf(parentLinearIndex), pArray.get(2, -1, -3));
        int viewLinearIndex = (2 * 3 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in view
        pArray.set(1.f, viewLinearIndex);
        assertEquals(1.f, pArray.get(2, -1, -3));
    }

    @Test
    void testSetCartesianIndexingGetLinearIndexing() {
        int linearIndex = (2 * 3 + 2) * 4 + 2; // equal to cartesian index [2,2,2] in view
        pArray.set(1, 2, -1, -3);
        assertEquals(1.f, pArray.get(linearIndex));
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
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, 60));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, pArray.length(), 60),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeLinearIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, -61));
        assertEquals(
            String.format(AbstractNDArray.ERROR_LINEAR_BOUNDS_ERROR, pArray.length(), -61),
            exception.getMessage());
    }

    @Test
    void testWrongSetCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, 1,3,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_CARTESIAN_BOUNDS_ERROR, "4 × 3 × 5", "[1, 3, 1]"),
            exception.getMessage());
    }

    @Test
    void testWrongSetNegativeCartesianIndexing() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> pArray.set(0, 1,1,-6));
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
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> pArray.set(0, 1, 1, 1, 0));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 4, 3),
            exception.getMessage());
    }

    @Test
    void testSetDimensionMismatchNotEnough() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> pArray.set(0, 1,1));
        assertEquals(
            String.format(AbstractNDArray.ERROR_DIMENSION_MISMATCH, 2, 3),
            exception.getMessage());
    }

    @Test
    void testEltype() {
        assertEquals(Float.class, pArray.eltype());
    }

    @Test
    void testToArray() {
        Float[][][] arr = (Float[][][])pArray.toArray();
        int linearIndex = 0;
        for (int i = 0; i < arr[0].length; i++)
            for (int j = 0; j < arr[0][0].length; j++)
                for (int k = 0; k < arr.length; k++) {
                    assertEquals(Float.valueOf(linearIndex), arr[k][i][j]);
                    linearIndex++;
                }
    }

    @Test
    void testEqual() {
        NDArray<Float> array2 = new RealF32NDArray(pArray);
        assertEquals(pArray, array2);
        array2.set(0.f, 5);
        assertNotEquals(pArray, array2);
    }

    @Test
    void testHashCode() {
        assertThrows(UnsupportedOperationException.class, () -> { pArray.hashCode(); });
    }

    @Test
    void testIterator() {
        int linearIndex = 0;
        for (Float value : pArray)
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
        Float sum = pArray.stream().parallel()
            .reduce(0.f, (acc, item) -> acc + item);
        Float acc = 0.f;
        for (int i = 0; i < array.dims(0); i++)
            for (int j = 0; j < array.dims(1); j++)
                for (int k = 0; k < array.dims(2); k++)
                acc = acc + array.get(i, j, k);
        assertEquals(acc, sum);
    }

    @Test
    void testCollector() {
        NDArray<Float> increased = pArray.stream()
            .map((value) -> value + 1)
            .collect(NDArrayCollectors.toRealF32NDArray(pArray.dims()));
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) + 1, increased.get(i));
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
        String str = pArray.toString();
        assertEquals("NDArray<RealF32>(4 × 3 × 5)", str);
    }

    @Test
    void testcontentToString() {
        String str = pArray.contentToString();
        String expected = new StringBuilder()
            .append("NDArray<RealF32>(4 × 3 × 5)\n")
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
    void testAddArrayTopArray() {
        NDArray<Float> array2 = new RealF32NDArray(pArray);
        NDArray<Float> array3 = pArray.add(array2);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) * 2, array3.get(i));
    }

    @Test
    void testAddpArrayToArray() {
        NDArray<Float> array2 = new RealF32NDArray(pArray);
        NDArray<Float> array3 = array2.add(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) * 2, array3.get(i));
    }

    @Test
    void testAddpArrayTopArray() {
        NDArray<Float> pArray2 = array.permuteDims(0, 2, 1);
        NDArray<Float> array3 = pArray2.add(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) * 2, array3.get(i));
    }

    @Test
    void testAddScalar() {
        NDArray<Float> pArray2 = pArray.add(5);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) + 5, pArray2.get(i));
    }

    @Test
    void testAddMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> pArray2 = array2.permuteDims(0, 2, 1);
        NDArray<Float> array3 = pArray2.add(pArray, 5.3, pArray2, 3.f);
        for (int i = 0; i < pArray.length(); i++) {
            Float expected = pArray.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array3.get(i)) < 1e5);
        }
    }

    @Test
    void testAddInplace() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(pArray);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i) * 2, pArray2.get(i));
    }

    @Test
    void testAddInplaceScalar() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(5);
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i)+ 5, pArray2.get(i));
    }

    @Test
    void testAddInplaceMultiple() {
        NDArray<Float> array2 = new RealF32NDArray(array);
        NDArray<Float> pArray2 = array2.permuteDims(0, 2, 1);
        pArray2.addInplace(pArray, 5.3, pArray2, 3.f);
        for (int i = 0; i < pArray.length(); i++) {
            Float expected = pArray.get(i) * 3.f + 5.3f + 3.f;
            assertTrue(Math.abs(expected - array2.get(i)) < 1e5);
        }
    }

    @Test
    void test0Norm() {
        pArray.slice(":", 0, ":").fill(0);
        double norm = pArray.stream()
            .filter(value -> value != 0.)
            .count();
        assertEquals(norm, pArray.norm(0));
    }

    @Test
    void test1Norm() {
        double norm = pArray.stream()
            .mapToDouble(value -> Math.abs(value))
            .reduce(0., (acc, item) -> acc + item);
        assertEquals(norm, pArray.norm(1));
    }

    @Test
    void test2Norm() {
        double norm = Math.sqrt(pArray.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 2))
            .reduce(0., (acc, item) -> acc + item));
        assertEquals(norm, pArray.norm());
    }

    @Test
    void testPQuasinorm() {
        double norm = Math.pow(pArray.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 0.5))
            .reduce(0., (acc, item) -> acc + item), 2);
        assertEquals(norm, pArray.norm(0.5));
    }

    @Test
    void testPNorm() {
        double norm = Math.pow(pArray.stream()
            .mapToDouble(value -> Math.pow(Math.abs(value), 3.5))
            .reduce(0., (acc, item) -> acc + item), 1 / 3.5);
        assertEquals(norm, pArray.norm(3.5));
    }

    @Test
    void testInfNorm() {
        double norm = pArray.stream()
            .mapToDouble(value -> Math.abs(value))
            .max().getAsDouble();
        assertEquals(norm, pArray.norm(Double.POSITIVE_INFINITY));
    }

    @Test
    void testCopy() {
        NDArray<Float> array2 = pArray.copy();
        for (int i = 0; i < pArray.length(); i++)
            assertEquals(pArray.get(i), array2.get(i));
        array2.set(0.f, 5);
        assertNotEquals(pArray.get(5), array2.get(5));
    }

    @Test
    void testFillFloat() {
        pArray.fill(3.f);
        for (Float elem : pArray)
            assertEquals(3.f, elem);
    }

    @Test
    void testFillReal() {
        pArray.fill(3);
        for (Float elem : pArray)
            assertEquals(3.f, elem);
    }

    @Test
    void testSliceAndToArray() {
        NDArray<Float> slice = pArray.slice(1, ":", "1:4");
        Float[][] arr = (Float[][])slice.toArray();
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
                    assertEquals(array.get(i, k, j), dataSet.getData(0).getRealElement(i, j, k, 0));
                }
    }

    @Test
    void testToRS2DDataSetTooManyDimensions() {
        NDArray<Float> array2 = new RealF32NDArray(new int[]{2,2,2,2,2,2}).permuteDims(5, 4, 3, 2, 1, 0);
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> array2.toRS2DDataSet());
        assertEquals(String.format(AbstractNDArray.ERROR_RS2D_DATA_SET_TOO_HIGH_DIMENSIONAL, 6),
                     exception.getMessage());
    }
    
    @Test
    void testToSimpleITKImage() {
        Image image = pArray.toSimpleITKImage();
        pArray.streamCartesianIndices().forEach(index -> {
            VectorUInt32 idx = new VectorUInt32(pArray.ndims());
            IntStream.range(0, pArray.ndims()).forEach(i -> idx.set(i, index[i]));
            assertEquals(pArray.get(index).floatValue(), image.getPixelAsFloat(idx));
        });
    }

    @Test
    void testConcatenate() {
        NDArray<Float> array2 = new RealF32NDArray(new int[]{4, 3, 2}).fill(1);
        NDArray<Float> array3 = pArray.concatenate(2, array2);
        for (int i = 0; i < pArray.dims(0); i++)
            for (int j = 0; j < pArray.dims(1); j++)
                for (int k = 0; k < pArray.dims(2); k++)
                    assertEquals(pArray.get(i, j, k), array3.get(i, j, k));
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = pArray.dims(2); k < pArray.dims(2) + array2.dims(2); k++)
                    assertEquals(1.f, array3.get(i, j, k));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Float> array2 = pArray.copy().fill(1).slice(":", ":", "1:3");
        NDArray<Float> array3 = new RealF32NDArray(new int[]{5, 3, 4}).permuteDims(2, 1, 0);
        NDArray<Float> array4 = new RealF32NDArray(new int[]{36}).fill(2.f).reshape(4, 3, 3);
        NDArray<Float> array5 = pArray.concatenate(2, array2, array3, array4);
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
                    assertEquals(1.f, array5.get(i, j, k));
        start = end;
        end += array3.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(0.f, array5.get(i, j, k));
        start = end;
        end += array4.dims(2);
        for (int i = 0; i < array2.dims(0); i++)
            for (int j = 0; j < array2.dims(1); j++)
                for (int k = start; k < end; k++)
                    assertEquals(2.f, array5.get(i, j, k));
    }

    @Test
    void testReal() {
        NDArray<Double> real = pArray.real();
        pArray.streamLinearIndices()
            .forEach(i -> assertEquals(pArray.get(i).floatValue(), real.get(i)));
    }

    @Test
    void testImag() {
        NDArray<Double> imag = pArray.imaginary();
        pArray.streamLinearIndices()
            .forEach(i -> assertEquals(0, imag.get(i)));
    }

    @Test
    void testAbs() {
        NDArray<Double> abs = pArray.abs();
        pArray.streamLinearIndices()
            .forEach(i -> assertTrue(Math.abs(pArray.get(i).doubleValue()) - abs.get(i) < 1e-5));
    }

    @Test
    void testAngle() {
        NDArray<Double> angle = pArray.angle();
        pArray.streamLinearIndices()
            .forEach(i -> assertEquals(0, angle.get(i)));
    }
}
