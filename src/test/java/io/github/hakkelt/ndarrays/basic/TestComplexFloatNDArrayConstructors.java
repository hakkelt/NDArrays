package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.ComplexNDArray;
import io.github.hakkelt.ndarrays.NDArray;

class TestComplexFloatNDArrayConstructors implements NameTrait {

    @Test
    void testDimsConstructor() {
        int[] dims = { 2, 4 };
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(dims);
        assertArrayEquals(dims, array.dims());
        assertEquals(8, array.length());
        assertEquals(8, array.size());
        assertEquals(2, array.dims(0));
        assertEquals(2, array.ndims());
    }

    @Test
    void test1DFloatArrayRealOnlyConstructors() {
        float[] real = new float[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DDoubleArrayRealOnlyConstructors() {
        double[] real = new double[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DByteArrayRealOnlyConstructors() {
        byte[] real = new byte[16];
        for (int i = 0; i < real.length; i++)
            real[i] = (byte)i;
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DShortArrayRealOnlyConstructors() {
        short[] real = new short[16];
        for (int i = 0; i < real.length; i++)
            real[i] = (short)i;
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DIntegerArrayRealOnlyConstructors() {
        int[] real = new int[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DLongArrayRealOnlyConstructors() {
        long[] real = new long[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test2DFloatArrayRealOnlyConstructors() {
        float[][] real = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
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
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DByteArrayRealOnlyConstructors() {
        byte[][] real = new byte[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = (byte)(i * real.length + j);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DShortArrayRealOnlyConstructors() {
        short[][] real = new short[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = (short)(i * real.length + j);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DIntegerArrayRealOnlyConstructors() {
        int[][] real = new int[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DLongArrayRealOnlyConstructors() {
        long[][] real = new long[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
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
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
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
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DByteArrayRealOnlyConstructors() {
        byte[][][] real = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (byte)((i * real.length + j) * real[i].length + k);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DShortArrayRealOnlyConstructors() {
        short[][][] real = new short[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (short)((i * real.length + j) * real[i].length + k);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DIntegerArrayRealOnlyConstructors() {
        int[][][] real = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DLongArrayRealOnlyConstructors() {
        long[][][] real = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
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
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
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
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DByteArrayComplexConstructors() {
        byte[] real = new byte[16];
        byte[] imag = new byte[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = (byte)i;
            imag[i] = (byte)-i;
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DShortArrayComplexConstructors() {
        short[] real = new short[16];
        short[] imag = new short[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = (short)i;
            imag[i] = (short)-i;
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DIntegerArrayComplexConstructors() {
        int[] real = new int[16];
        int[] imag = new int[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DLongArrayComplexConstructors() {
        long[] real = new long[16];
        long[] imag = new long[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = i;
            imag[i] = -i;
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
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
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
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
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DByteArrayComplexConstructors() {
        byte[][] real = new byte[4][5];
        byte[][] imag = new byte[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = (byte)(i * real.length + j);
                imag[i][j] = (byte)(-(i * imag.length + j));
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DShortArrayComplexConstructors() {
        short[][] real = new short[4][5];
        short[][] imag= new short[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = (short)(i * real.length + j);
                imag[i][j] = (short)-(i * imag.length + j);
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DIntegerArrayComplexConstructors() {
        int[][] real = new int[4][5];
        int[][] imag = new int[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = i * real.length + j;
                imag[i][j] = -(i * imag.length + j);
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DLongArrayComplexConstructors() {
        long[][] real = new long[4][5];
        long[][] imag= new long[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = i * real.length + j;
                imag[i][j] = -(i * imag.length + j);
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
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
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
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
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
    }

    @Test
    void test3DByteArrayComplexConstructors() {
        byte[][][] real = new byte[4][5][3];
        byte[][][] imag = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (byte)((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = (byte)-((i * imag.length + j) * imag[i].length + k);
                }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
    }

    @Test
    void test3DShortArrayComplexConstructors() {
        short[][][] real = new short[4][5][3];
        short[][][] imag = new short[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (short)((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = (short)-((i * imag.length + j) * imag[i].length + k);
                }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
    }

    @Test
    void test3DIntegerArrayComplexConstructors() {
        int[][][] real = new int[4][5][3];
        int[][][] imag = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
                    imag[i][j][k] = -((i * imag.length + j) * imag[i].length + k);
                }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
    }

    @Test
    void test3DLongArrayComplexConstructors() {
        long[][][] real = new long[4][5][3];
        long[][][] imag = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
                    imag[i][j][k] = -((i * imag.length + j) * imag[i].length + k);
                }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
    }

    @Test
    void testComplexFloatCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        float[][][] real = new float[4][5][3];
        float[][][] imag = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
                    imag[i][j][k] = -((i * imag.length + j) * imag[i].length + k);
                }
        ComplexNDArray<Float> array1 = new BasicComplexFloatNDArray(dims).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
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
    void testComplexDoubleCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        float[][][] real = new float[4][5][3];
        float[][][] imag = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
                    imag[i][j][k] = -((i * imag.length + j) * imag[i].length + k);
                }
        ComplexNDArray<Float> array1 = new BasicComplexFloatNDArray(dims).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
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
    void testDoubleCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Float> array1 = new BasicFloatNDArray(dims).copyFrom(real);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }

    @Test
    void testFloatCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Float> array1 = new BasicFloatNDArray(dims).copyFrom(real);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }

    @Test
    void testByteCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        byte[][][] real = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (byte)((i * real.length + j) * real[i].length + k);
        NDArray<Byte> array1 = new BasicByteNDArray(dims).copyFrom(real);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }
    

    @Test
    void testShortCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        short[][][] real = new short[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (short)((i * real.length + j) * real[i].length + k);
        NDArray<Short> array1 = new BasicShortNDArray(dims).copyFrom(real);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }
    

    @Test
    void testIntegerCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        int[][][] real = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Integer> array1 = new BasicIntegerNDArray(dims).copyFrom(real);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }
    

    @Test
    void testLongCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        long[][][] real = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Long> array1 = new BasicLongNDArray(dims).copyFrom(real);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }

}
