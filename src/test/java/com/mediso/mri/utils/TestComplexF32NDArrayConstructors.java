package com.mediso.mri.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;
import org.itk.simple.Image;
import org.itk.simple.PixelIDValueEnum;
import org.itk.simple.VectorUInt32;
import org.junit.jupiter.api.Test;

import rs2d.spinlab.data.DataSet;
import rs2d.spinlab.data.DataSetInterface;
import rs2d.spinlab.tools.param.ModalityEnum;

public class TestComplexF32NDArrayConstructors {

    @Test
    public void test1DDimsConstructor() {
        int[] dims = { 4 };
        NDArray<Complex> array = new ComplexF32NDArray(4);
        assertArrayEquals(dims, array.dims());
        assertEquals(4, array.length());
        assertEquals(4, array.size());
        assertEquals(4, array.dims(0));
        assertEquals(1, array.ndims());
    }

    @Test
    public void testDimsConstructor() {
        int[] dims = { 2, 4 };
        NDArray<Complex> array = new ComplexF32NDArray(2, 4);
        assertArrayEquals(dims, array.dims());
        assertEquals(8, array.length());
        assertEquals(8, array.size());
        assertEquals(2, array.dims(0));
        assertEquals(2, array.ndims());
    }

    @Test
    public void testDimsAnd1DFloatArrayRealOnlyConstructor() {
        int[] dims = { 4, 5, 3 };
        float[] real = new float[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Complex> array = new ComplexF32NDArray(dims, real);
        int idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++)
                    assertEquals(new Complex(idx++, 0), array.get(i, j, k));
    }

    @Test
    public void testDimsAnd1DDoubleArrayRealOnlyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Complex> array = new ComplexF32NDArray(dims, real);
        int idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++)
                    assertEquals(new Complex(idx++, 0), array.get(i, j, k));
    }

    @Test
    public void testDimsAnd1DFloatArrayComplexConstructor() {
        int[] dims = { 4, 5, 3 };
        float[] real = new float[4 * 5 * 3];
        float[] imag = new float[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        NDArray<Complex> array = new ComplexF32NDArray(dims, real, imag);
        int idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    assertEquals(new Complex(idx, -idx), array.get(i, j, k));
                    idx++;
                }
    }

    @Test
    public void testDimsAnd1DDoubleArrayComplexConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        double[] imag = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        NDArray<Complex> array = new ComplexF32NDArray(dims, real, imag);
        int idx = 0;
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    assertEquals(new Complex(idx, -idx), array.get(i, j, k));
                    idx++;
                }
    }

    @Test
    public void test1DFloatArrayRealOnlyConstructors() {
        float[] real = new float[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Complex> array = new ComplexF32NDArray(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    public void test1DDoubleArrayRealOnlyConstructors() {
        double[] real = new double[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Complex> array = new ComplexF32NDArray(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    public void test2DFloatArrayRealOnlyConstructors() {
        float[][] real = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Complex> array = new ComplexF32NDArray(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    public void test2DDoubleArrayRealOnlyConstructors() {
        double[][] real = new double[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Complex> array = new ComplexF32NDArray(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    public void test3DFloatArrayRealOnlyConstructors() {
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Complex> array = new ComplexF32NDArray(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    public void test3DDoubleArrayRealOnlyConstructors() {
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Complex> array = new ComplexF32NDArray(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    public void test1DFloatArrayComplexConstructors() {
        float[] real = new float[16];
        float[] imag = new float[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        NDArray<Complex> array = new ComplexF32NDArray(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    public void test1DDoubleArrayComplexConstructors() {
        double[] real = new double[16];
        double[] imag = new double[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        NDArray<Complex> array = new ComplexF32NDArray(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    public void test2DFloatArrayComplexConstructors() {
        float[][] real = new float[4][5];
        float[][] imag = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = i * real.length + j;
                imag[i][j] = -(i * imag.length + j);
            }
        NDArray<Complex> array = new ComplexF32NDArray(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    public void test2DDoubleArrayComplexConstructors() {
        double[][] real = new double[4][5];
        double[][] imag= new double[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = i * real.length + j;
                imag[i][j] = -(i * imag.length + j);
            }
        NDArray<Complex> array = new ComplexF32NDArray(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    public void test3DFloatArrayComplexConstructors() {
        float[][][] real = new float[4][5][3];
        float[][][] imag = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
                    imag[i][j][k] = -((i * imag.length + j) * imag[i].length + k);
                }
        NDArray<Complex> array = new ComplexF32NDArray(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
    }

    @Test
    public void test3DDoubleArrayComplexConstructors() {
        double[][][] real = new double[4][5][3];
        double[][][] imag = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
                    imag[i][j][k] = -((i * imag.length + j) * imag[i].length + k);
                }
        NDArray<Complex> array = new ComplexF32NDArray(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
    }

    @Test
    public void testCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        double[] imag = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        NDArray<Complex> array1 = new ComplexF32NDArray(dims, real, imag);
        NDArray<Complex> array2 = new ComplexF32NDArray(array1);
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
    public void testComplexF64CopyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[] real = new double[4 * 5 * 3];
        double[] imag = new double[4 * 5 * 3];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        NDArray<Complex> array1 = new ComplexF64NDArray(dims, real, imag);
        NDArray<Complex> array2 = new ComplexF32NDArray(array1);
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
    public void testRS2DConstructor() {
        DataSetInterface dataSet = new DataSet(4, 5, 3, 6, 2, ModalityEnum.MRI);
        float[][][][] realPart = new float[4][5][3][6];
        float[][][][] imagPart = new float[4][5][3][6];
        for (float[][][] image3D : realPart)
            for (float[][] image2D : image3D)
                for (float[] image1D : image2D)
                    Arrays.fill(image1D, (float)0.5);
        for (float[][][] image3D : imagPart)
            for (float[][] image2D : image3D)
                for (float[] image1D : image2D)
                    Arrays.fill(image1D, (float)-0.5);
        dataSet.setData(realPart, imagPart, 0);
        dataSet.setData(realPart, imagPart, 1);

        NDArray<Complex> array = new ComplexF32NDArray(dataSet);
        for (Complex item : array)
            assertEquals(new Complex(0.5, -0.5), item);
    }

    @Test
    public void testSimpleITKConstructor() {
        VectorUInt32 size = new VectorUInt32(3);
        size.set(0, 4);
        size.set(1, 5);
        size.set(2, 3);
        Image image = new Image(size, PixelIDValueEnum.sitkFloat32);
        int counter = 0;
        for (int k = 0; k < 3; k++)
            for (int j = 0; j < 5; j++)
                for (int i = 0; i < 4; i++) {
                    VectorUInt32 idx = new VectorUInt32(3);
                    idx.set(0, i);
                    idx.set(1, j);
                    idx.set(2, k);
                    image.setPixelAsFloat(idx, counter++);
                }
        NDArray<Complex> array = new ComplexF32NDArray(image);
        counter = 0;
        for (int k = 0; k < 3; k++)
            for (int j = 0; j < 5; j++)
                for (int i = 0; i < 4; i++)
                    assertEquals(new Complex(counter++, 0), array.get(i, j, k));
    }
}
