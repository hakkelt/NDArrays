package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.NDArray;

class TestShortNDArrayConstructors implements NameTrait {

    @Test
    void testDimsConstructor() {
        int[] dims = { 2, 4 };
        NDArray<Short> array = new BasicShortNDArray(dims);
        assertArrayEquals(dims, array.dims());
        assertEquals(8, array.length());
        assertEquals(8, array.size());
        assertEquals(2, array.dims(0));
        assertEquals(2, array.ndims());
    }

    @Test
    void test1DFloatArrayConstructors() {
        float[] real = new float[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (byte i = 0; i < real.length; i++)
            assertEquals(Short.valueOf(i), array.get(i));
    }

    @Test
    void test1DDoubleArrayConstructors() {
        double[] real = new double[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (byte i = 0; i < real.length; i++)
        assertEquals(Short.valueOf(i), array.get(i));
    }

    @Test
    void test1DByteArrayConstructors() {
        byte[] real = new byte[16];
        for (int i = 0; i < real.length; i++)
            real[i] = (byte)i;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (byte i = 0; i < real.length; i++)
            assertEquals(Short.valueOf(i), array.get(i));
    }

    @Test
    void test1DShortArrayConstructors() {
        short[] real = new short[16];
        for (int i = 0; i < real.length; i++)
            real[i] = (short)i;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (byte i = 0; i < real.length; i++)
        assertEquals(Short.valueOf(i), array.get(i));
    }

    @Test
    void test1DIntegerArrayConstructors() {
        int[] real = new int[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (byte i = 0; i < real.length; i++)
            assertEquals(Short.valueOf(i), array.get(i));
    }

    @Test
    void test1DLongArrayConstructors() {
        long[] real = new long[16];
        for (int i = 0; i < real.length; i++)
            real[i] = (long)i;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (byte i = 0; i < real.length; i++)
        assertEquals(Short.valueOf(i), array.get(i));
    }

    @Test
    void test2DFloatArrayConstructors() {
        float[][] real = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (byte i = 0; i < real.length; i++)
            for (byte j = 0; j < real[i].length; j++)
                assertEquals(Short.valueOf((byte)(i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DDoubleArrayConstructors() {
        double[][] real = new double[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Short.valueOf((byte)(i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DByteArrayConstructors() {
        byte[][] real = new byte[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = (byte)(i * real.length + j);
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (byte i = 0; i < real.length; i++)
            for (byte j = 0; j < real[i].length; j++)
                assertEquals(Short.valueOf((byte)(i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DShortArrayConstructors() {
        short[][] real = new short[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = (short)(i * real.length + j);
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Short.valueOf((byte)(i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DIntegerArrayConstructors() {
        int[][] real = new int[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (byte i = 0; i < real.length; i++)
            for (byte j = 0; j < real[i].length; j++)
                assertEquals(Short.valueOf((byte)(i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DLongArrayConstructors() {
        long[][] real = new long[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = (long)(i * real.length + j);
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Short.valueOf((byte)(i * real.length + j)), array.get(i, j));
    }

    @Test
    void test3DFloatArrayConstructors() {
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Short.valueOf((byte)((i * real.length + j) * real[i].length + k)), array.get(i, j, k));
    }

    @Test
    void test3DDoubleArrayConstructors() {
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Short.valueOf((byte)((i * real.length + j) * real[i].length + k)), array.get(i, j, k));
    }

    @Test
    void test3DByteArrayConstructors() {
        byte[][][] real = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (byte)((i * real.length + j) * real[i].length + k);
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Short.valueOf((byte)((i * real.length + j) * real[i].length + k)), array.get(i, j, k));
    }

    @Test
    void test3DShortArrayConstructors() {
        short[][][] real = new short[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (short)((i * real.length + j) * real[i].length + k);
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Short.valueOf((byte)((i * real.length + j) * real[i].length + k)), array.get(i, j, k));
    }

    @Test
    void test3DIntegerArrayConstructors() {
        int[][][] real = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Short.valueOf((byte)((i * real.length + j) * real[i].length + k)), array.get(i, j, k));
    }

    @Test
    void test3DLongArrayConstructors() {
        long[][][] real = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (long)((i * real.length + j) * real[i].length + k);
        NDArray<Short> array = BasicShortNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Short.valueOf((byte)((i * real.length + j) * real[i].length + k)), array.get(i, j, k));
    }

    @Test
    void testDoubleCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Double> array1 = new BasicDoubleNDArray(dims).copyFrom(real);
        NDArray<Short> array2 = new BasicShortNDArray(array1);
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

    @Test
    void testFloatCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Float> array1 = new BasicFloatNDArray(dims).copyFrom(real);
        NDArray<Short> array2 = new BasicShortNDArray(array1);
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

    @Test
    void testByteCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        byte[][][] real = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (byte)((i * real.length + j) * real[i].length + k);
        NDArray<Byte> array1 = new BasicByteNDArray(dims).copyFrom(real);
        NDArray<Short> array2 = new BasicShortNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < dims[2]; k++)
            for (int j = 0; j < dims[1]; j++)
                for (int i = 0; i < dims[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(Short.valueOf(array1.get(i, j, k)), array2.get(i, j, k));
                    else
                        assertEquals(Short.valueOf(array1.get(i, j, k)), array2.get(i, j, k));
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
        NDArray<Short> array2 = new BasicShortNDArray(array1);
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
    

    @Test
    void testIntegerCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        int[][][] real = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Integer> array1 = new BasicIntegerNDArray(dims).copyFrom(real);
        NDArray<Short> array2 = new BasicShortNDArray(array1);
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
    

    @Test
    void testLongCopyConstructor() {
        int[] dims = { 4, 5, 3 };
        long[][][] real = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Long> array1 = new BasicLongNDArray(dims).copyFrom(real);
        NDArray<Short> array2 = new BasicShortNDArray(array1);
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
