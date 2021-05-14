package com.mediso.mri.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TestRealF32NDArrayConstructors {

    @Test
    public void testDimsConstructor() {
        int[] dims = { 2, 4 };
        NDArray<Float> array = new RealF32NDArray(dims);
        assertArrayEquals(dims, array.dims());
        assertEquals(8, array.length());
        assertEquals(8, array.size());
        assertEquals(2, array.dims(0));
        assertEquals(2, array.ndims());
    }

    @Test
    public void testDimsAnd1DFloatArrayConstructor() {
        int[] dims = { 4, 5, 3 };
        float[] real = new float[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Float> array = new RealF32NDArray(dims, real);
        int idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++)
                    assertEquals(Float.valueOf(idx++), array.get(i, j, k));
    }

    @Test
    public void testDimsAnd1DDoubleArrayConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Float> array = new RealF32NDArray(dims, real);
        int idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++)
                    assertEquals(Float.valueOf(idx++), array.get(i, j, k));
    }

    @Test
    public void test1DFloatArrayConstructors() {
        float[] real = new float[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Float> array = new RealF32NDArray(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(Float.valueOf(i), array.get(i));
    }

    @Test
    public void test1DDoubleArrayConstructors() {
        double[] real = new double[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Float> array = new RealF32NDArray(real);
        for (int i = 0; i < real.length; i++)
        assertEquals(Float.valueOf(i), array.get(i));
    }

    @Test
    public void test2DFloatArrayConstructors() {
        float[][] real = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Float> array = new RealF32NDArray(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Float.valueOf(i * real.length + j), array.get(i, j));
    }

    @Test
    public void test2DDoubleArrayConstructors() {
        double[][] real = new double[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Float> array = new RealF32NDArray(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Float.valueOf(i * real.length + j), array.get(i, j));
    }

    @Test
    public void test3DFloatArrayConstructors() {
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Float> array = new RealF32NDArray(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Float.valueOf((i * real.length + j) * real[i].length + k), array.get(i, j, k));
    }

    @Test
    public void test3DDoubleArrayConstructors() {
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Float> array = new RealF32NDArray(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Float.valueOf((i * real.length + j) * real[i].length + k), array.get(i, j, k));
    }

    @Test
    public void testCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
        }
        NDArray<Float> array1 = new RealF32NDArray(dims, real);
        NDArray<Float> array2 = new RealF32NDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k), array2.get(i, j, k));
                    else
                        assertEquals(array1.get(i, j, k), array2.get(i, j, k));
                }
    }

    @Test
    public void testFloatF64CopyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Double> array1 = new RealF64NDArray(dims, real);
        NDArray<Float> array2 = new RealF32NDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
                    else
                        assertEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
                }
    }
}
