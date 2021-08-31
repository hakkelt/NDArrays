package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.DoubleNDArrayConstructorTrait;
import io.github.hakkelt.ndarrays.IntegerNDArrayConstructorTrait;
import io.github.hakkelt.ndarrays.NDArray;

class TestIntegerNDArrayConstructors implements IntegerNDArrayConstructorTrait, DoubleNDArrayConstructorTrait, ConstructorTrait {

    @Test
    void testDimsConstructor() {
        int[] dims = { 2, 4 };
        NDArray<Integer> array = createIntegerNDArray(dims);
        assertArrayEquals(dims, array.dims());
        assertEquals(8, array.length());
        assertEquals(8, array.size());
        assertEquals(2, array.dims(0));
        assertEquals(2, array.ndims());
    }

    @Test
    void testDimsAnd1DIntegerArrayConstructor() {
        int[] dims = { 4, 5, 3 };
        float[] real = new float[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Integer> array = createIntegerNDArray(dims).copyFrom(real);
        short idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++)
                    assertEquals(Integer.valueOf(idx++), array.get(i, j, k));
    }

    @Test
    void testDimsAnd1DDoubleArrayConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Integer> array = createIntegerNDArray(dims).copyFrom(real);
        short idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++)
                    assertEquals(Integer.valueOf(idx++), array.get(i, j, k));
    }

    @Test
    void test1DIntegerArrayConstructors() {
        float[] real = new float[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Integer> array = getIntegerNDArrayOf(real);
        for (short i = 0; i < real.length; i++)
            assertEquals(Integer.valueOf(i), array.get(i));
    }

    @Test
    void test1DDoubleArrayConstructors() {
        double[] real = new double[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Integer> array = getIntegerNDArrayOf(real);
        for (short i = 0; i < real.length; i++)
        assertEquals(Integer.valueOf(i), array.get(i));
    }

    @Test
    void test2DIntegerArrayConstructors() {
        float[][] real = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Integer> array = getIntegerNDArrayOf(real);
        for (short i = 0; i < real.length; i++)
            for (short j = 0; j < real[i].length; j++)
                assertEquals(Integer.valueOf((short)(i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DDoubleArrayConstructors() {
        double[][] real = new double[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Integer> array = getIntegerNDArrayOf(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Integer.valueOf((short)(i * real.length + j)), array.get(i, j));
    }

    @Test
    void test3DIntegerArrayConstructors() {
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Integer> array = getIntegerNDArrayOf(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Integer.valueOf((short)((i * real.length + j) * real[i].length + k)), array.get(i, j, k));
    }

    @Test
    void test3DDoubleArrayConstructors() {
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Integer> array = getIntegerNDArrayOf(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Integer.valueOf((short)((i * real.length + j) * real[i].length + k)), array.get(i, j, k));
    }

    @Test
    void testCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
        }
        NDArray<Integer> array1 = createIntegerNDArray(dims).copyFrom(real);
        NDArray<Integer> array2 = createIntegerNDArray(array1);
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
    void testIntegerF64CopyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Double> array1 = createDoubleNDArray(dims).copyFrom(real);
        NDArray<Integer> array2 = createIntegerNDArray(array1);
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
