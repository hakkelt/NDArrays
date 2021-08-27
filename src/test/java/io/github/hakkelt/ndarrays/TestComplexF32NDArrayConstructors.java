package io.github.hakkelt.ndarrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.Test;

class TestComplexF32NDArrayConstructors {

    @Test
    void test1DDimsConstructor() {
        int[] dims = { 4 };
        ComplexNDArray<Float> array = new ComplexF32NDArray(4);
        assertArrayEquals(dims, array.dims());
        assertEquals(4, array.length());
        assertEquals(4, array.size());
        assertEquals(4, array.dims(0));
        assertEquals(1, array.ndims());
    }

    @Test
    void testDimsConstructor() {
        int[] dims = { 2, 4 };
        ComplexNDArray<Float> array = new ComplexF32NDArray(2, 4);
        assertArrayEquals(dims, array.dims());
        assertEquals(8, array.length());
        assertEquals(8, array.size());
        assertEquals(2, array.dims(0));
        assertEquals(2, array.ndims());
    }

    @Test
    void testDimsAnd1DFloatArrayRealOnlyConstructor() {
        int[] dims = { 4, 5, 3 };
        float[] real = new float[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Float> array = new ComplexF32NDArray(dims).copyFrom(real);
        int idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++)
                    assertEquals(new Complex(idx++, 0), array.get(i, j, k));
    }

    @Test
    void testDimsAnd1DDoubleArrayRealOnlyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Float> array = new ComplexF32NDArray(dims).copyFrom(real);
        int idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++)
                    assertEquals(new Complex(idx++, 0), array.get(i, j, k));
    }

    @Test
    void testDimsAnd1DFloatArrayComplexConstructor() {
        int[] dims = { 4, 5, 3 };
        float[] real = new float[4 * 5 * 3];
        float[] imag = new float[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        ComplexNDArray<Float> array = new ComplexF32NDArray(dims).copyFrom(real, imag);
        int idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    assertEquals(new Complex(idx, -idx), array.get(i, j, k));
                    idx++;
                }
    }

    @Test
    void testDimsAnd1DDoubleArrayComplexConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        double[] imag = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        ComplexNDArray<Float> array = new ComplexF32NDArray(dims).copyFrom(real, imag);
        int idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    assertEquals(new Complex(idx, -idx), array.get(i, j, k));
                    idx++;
                }
    }

    @Test
    void test1DFloatArrayRealOnlyConstructors() {
        float[] real = new float[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DDoubleArrayRealOnlyConstructors() {
        double[] real = new double[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test2DFloatArrayRealOnlyConstructors() {
        float[][] real = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DDoubleArrayRealOnlyConstructors() {
        double[][] real = new double[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test3DFloatArrayRealOnlyConstructors() {
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DDoubleArrayRealOnlyConstructors() {
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test1DFloatArrayComplexConstructors() {
        float[] real = new float[16];
        float[] imag = new float[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DDoubleArrayComplexConstructors() {
        double[] real = new double[16];
        double[] imag = new double[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test2DFloatArrayComplexConstructors() {
        float[][] real = new float[4][5];
        float[][] imag = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = i * real.length + j;
                imag[i][j] = -(i * imag.length + j);
            }
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DDoubleArrayComplexConstructors() {
        double[][] real = new double[4][5];
        double[][] imag= new double[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = i * real.length + j;
                imag[i][j] = -(i * imag.length + j);
            }
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test3DFloatArrayComplexConstructors() {
        float[][][] real = new float[4][5][3];
        float[][][] imag = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
                    imag[i][j][k] = -((i * imag.length + j) * imag[i].length + k);
                }
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
    }

    @Test
    void test3DDoubleArrayComplexConstructors() {
        double[][][] real = new double[4][5][3];
        double[][][] imag = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
                    imag[i][j][k] = -((i * imag.length + j) * imag[i].length + k);
                }
        ComplexNDArray<Float> array = ComplexF32NDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
    }

    @Test
    void testCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        double[] imag = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        ComplexNDArray<Float> array1 = new ComplexF32NDArray(dims).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = array1.copy();
        array1.set(new Complex(0,0), 2,2,2);
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
    void testComplexF64CopyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        double[] imag = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        ComplexNDArray<Double> array1 = new ComplexF64NDArray(dims).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = new ComplexF32NDArray(array1);
        array1.set(new Complex(0,0), 2,2,2);
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k), array2.get(i, j, k));
                    else
                        assertEquals(array1.get(i, j, k), array2.get(i, j, k));
                }
    }
}
