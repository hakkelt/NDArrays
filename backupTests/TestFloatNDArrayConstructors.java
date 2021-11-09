package io.github.hakkelt.ndarrays.backup;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.Errors;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.basic.BasicByteNDArray;
import io.github.hakkelt.ndarrays.basic.BasicDoubleNDArray;
import io.github.hakkelt.ndarrays.basic.BasicFloatNDArray;
import io.github.hakkelt.ndarrays.basic.BasicIntegerNDArray;
import io.github.hakkelt.ndarrays.basic.BasicLongNDArray;
import io.github.hakkelt.ndarrays.basic.BasicShortNDArray;

class TestFloatNDArrayConstructors implements NameTrait {

    @Test
    void testDimsConstructor() {
        int[] shape = { 2, 4 };
        NDArray<Float> array = new BasicFloatNDArray(shape);
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
    void test1DFloatArrayConstructors() {
        float[] real = new float[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(Float.valueOf(i), array.get(i));
    }

    @Test
    void test1DDoubleArrayConstructors() {
        double[] real = new double[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
        assertEquals(Float.valueOf(i), array.get(i));
    }

    @Test
    void test1DByteArrayConstructors() {
        byte[] real = new byte[16];
        for (int i = 0; i < real.length; i++)
            real[i] = (byte)i;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(Float.valueOf(i), array.get(i));
    }

    @Test
    void test1DShortArrayConstructors() {
        short[] real = new short[16];
        for (int i = 0; i < real.length; i++)
            real[i] = (short)i;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
        assertEquals(Float.valueOf(i), array.get(i));
    }

    @Test
    void test1DIntegerArrayConstructors() {
        int[] real = new int[16];
        for (int i = 0; i < real.length; i++)
            real[i] = i;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(Float.valueOf(i), array.get(i));
    }

    @Test
    void test1DLongArrayConstructors() {
        long[] real = new long[16];
        for (int i = 0; i < real.length; i++)
            real[i] = (long)i;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
        assertEquals(Float.valueOf(i), array.get(i));
    }

    @Test
    void test2DFloatArrayConstructors() {
        float[][] real = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Float.valueOf(i * real.length + j), array.get(i, j));
    }

    @Test
    void test2DDoubleArrayConstructors() {
        double[][] real = new double[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Float.valueOf(i * real.length + j), array.get(i, j));
    }

    @Test
    void test2DByteArrayConstructors() {
        byte[][] real = new byte[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = (byte)(i * real.length + j);
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Float.valueOf(i * real.length + j), array.get(i, j));
    }

    @Test
    void test2DShortArrayConstructors() {
        short[][] real = new short[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = (short)(i * real.length + j);
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Float.valueOf(i * real.length + j), array.get(i, j));
    }

    @Test
    void test2DIntegerArrayConstructors() {
        int[][] real = new int[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = i * real.length + j;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Float.valueOf(i * real.length + j), array.get(i, j));
    }

    @Test
    void test2DLongArrayConstructors() {
        long[][] real = new long[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = (long)(i * real.length + j);
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(Float.valueOf(i * real.length + j), array.get(i, j));
    }

    @Test
    void test3DFloatArrayConstructors() {
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Float.valueOf((i * real.length + j) * real[i].length + k), array.get(i, j, k));
    }

    @Test
    void test3DDoubleArrayConstructors() {
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Float.valueOf((i * real.length + j) * real[i].length + k), array.get(i, j, k));
    }

    @Test
    void test3DByteArrayConstructors() {
        byte[][][] real = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (byte)((i * real.length + j) * real[i].length + k);
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Float.valueOf((i * real.length + j) * real[i].length + k), array.get(i, j, k));
    }

    @Test
    void test3DShortArrayConstructors() {
        short[][][] real = new short[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (short)((i * real.length + j) * real[i].length + k);
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Float.valueOf((i * real.length + j) * real[i].length + k), array.get(i, j, k));
    }

    @Test
    void test3DIntegerArrayConstructors() {
        int[][][] real = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (i * real.length + j) * real[i].length + k;
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Float.valueOf((i * real.length + j) * real[i].length + k), array.get(i, j, k));
    }

    @Test
    void test3DLongArrayConstructors() {
        long[][][] real = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = (long)((i * real.length + j) * real[i].length + k);
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(Float.valueOf((i * real.length + j) * real[i].length + k), array.get(i, j, k));
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
        NDArray<Float> array2 = new BasicFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
                    else
                        assertEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
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
        NDArray<Float> array2 = new BasicFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
                    else
                        assertEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
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
        NDArray<Float> array2 = new BasicFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(Float.valueOf(array1.get(i, j, k)), array2.get(i, j, k));
                    else
                        assertEquals(Float.valueOf(array1.get(i, j, k)), array2.get(i, j, k));
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
        NDArray<Float> array2 = new BasicFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
                    else
                        assertEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
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
        NDArray<Float> array2 = new BasicFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
                    else
                        assertEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
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
        NDArray<Float> array2 = new BasicFloatNDArray(array1);
        array1.set(0, 2,2,2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
                    else
                        assertEquals(array1.get(i, j, k).floatValue(), array2.get(i, j, k).floatValue());
                }
    }

}
