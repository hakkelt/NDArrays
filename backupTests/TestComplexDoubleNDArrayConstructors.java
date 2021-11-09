package io.github.hakkelt.ndarrays.backup;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.ComplexNDArray;
import io.github.hakkelt.ndarrays.Errors;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.basic.BasicByteNDArray;
import io.github.hakkelt.ndarrays.basic.BasicComplexDoubleNDArray;
import io.github.hakkelt.ndarrays.basic.BasicComplexFloatNDArray;
import io.github.hakkelt.ndarrays.basic.BasicDoubleNDArray;
import io.github.hakkelt.ndarrays.basic.BasicFloatNDArray;
import io.github.hakkelt.ndarrays.basic.BasicIntegerNDArray;
import io.github.hakkelt.ndarrays.basic.BasicLongNDArray;
import io.github.hakkelt.ndarrays.basic.BasicShortNDArray;

class TestComplexDoubleNDArrayConstructors implements NameTrait {

    @Test
    void testDimsConstructor() {
        int[] shape = { 2, 4 };
        ComplexNDArray<Double> array = new BasicComplexDoubleNDArray(shape);
        assertArrayEquals(shape, array.shape());
        assertEquals(8, array.length());
        assertEquals(8, array.shape());
        assertEquals(2, array.shape(0));
        assertEquals(2, array.ndim());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.shape(2));
        assertEquals(String.format(Errors.PARAMETER_MUST_BE_BETWEEN, "axis", 0, array.ndim() - 1, 2),
                exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class, () -> array.shape(-1));
        assertEquals(String.format(Errors.PARAMETER_MUST_BE_BETWEEN, "axis", 0, array.ndim() - 1, -1),
                exception.getMessage());
    }

    @Test
    void test1DFloatArrayRealOnlyConstructors() {
        float[] real = new float[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DDoubleArrayRealOnlyConstructors() {
        double[] real = new double[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DByteArrayRealOnlyConstructors() {
        byte[] real = new byte[16];
        for (int i = 0; i < real.length; i++)
            real[i] = (byte)i;
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DShortArrayRealOnlyConstructors() {
        short[] real = new short[16];
        for (int i = 0; i < real.length; i++)
            real[i] = (short)i;
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DIntegerArrayRealOnlyConstructors() {
        int[] real = new int[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DLongArrayRealOnlyConstructors() {
        long[] real = new long[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test2DFloatArrayRealOnlyConstructors() {
        float[][] real = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
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
        ComplexNDArray<Double> array = BasicComplexDoubleNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
    }

    @Test
    void testComplexDoubleCopyConstructor() {
        int[] shape = { 4, 5, 3 };
        float[][][] real = new float[4][5][3];
        float[][][] imag = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
                    imag[i][j][k] = -((i * imag.length + j) * imag[i].length + k);
                }
        ComplexNDArray<Double> array1 = new BasicComplexDoubleNDArray(shape).copyFrom(real, imag);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(new Complex(0,0), 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k), array2.get(i, j, k));
                    else
                        assertEquals(array1.get(i, j, k), array2.get(i, j, k));
                }
    }

    @Test
    void testComplexFloatCopyConstructor() {
        int[] shape = { 4, 5, 3 };
        float[][][] real = new float[4][5][3];
        float[][][] imag = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
                    imag[i][j][k] = -((i * imag.length + j) * imag[i].length + k);
                }
        ComplexNDArray<Float> array1 = new BasicComplexFloatNDArray(shape).copyFrom(real, imag);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(new Complex(0,0), 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k), array2.get(i, j, k));
                    else
                        assertEquals(array1.get(i, j, k), array2.get(i, j, k));
                }
    }

    @Test
    void testDoubleCopyConstructor() {
        int[] shape = { 4, 5, 3 };
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Double> array1 = new BasicDoubleNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }

    @Test
    void testFloatCopyConstructor() {
        int[] shape = { 4, 5, 3 };
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Float> array1 = new BasicFloatNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }

    @Test
    void testByteCopyConstructor() {
        int[] shape = { 4, 5, 3 };
        byte[][][] real = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (byte)((i * real.length + j) * real[i].length + k);
        NDArray<Byte> array1 = new BasicByteNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }
    

    @Test
    void testShortCopyConstructor() {
        int[] shape = { 4, 5, 3 };
        short[][][] real = new short[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (short)((i * real.length + j) * real[i].length + k);
        NDArray<Short> array1 = new BasicShortNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }
    

    @Test
    void testIntegerCopyConstructor() {
        int[] shape = { 4, 5, 3 };
        int[][][] real = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Integer> array1 = new BasicIntegerNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }
    

    @Test
    void testLongCopyConstructor() {
        int[] shape = { 4, 5, 3 };
        long[][][] real = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Long> array1 = new BasicLongNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).getReal());
                }
    }

}
