/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestRealNDArrayConstructors.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:56 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.Errors;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

class TestBasicIntegerNDArrayConstructors extends TestBase {

    @Test
    void testDimsConstructor() {
        int[] shape = { 2, 4 };
        NDArray<Integer> array = new BasicIntegerNDArray(shape);
        assertArrayEquals(shape, array.shape());
        assertEquals(8, array.length());
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
    void testWrongType3DArrayConstructor() {
        String[][][] array = new String[4][5][3];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> BasicIntegerNDArray.of(array));
        assertEquals(String.format(Errors.UNSUPPORTED_TYPE, "String"), exception.getMessage());

        String[] array2 = {"asdf", "", null};
        exception = assertThrows(IllegalArgumentException.class, () -> BasicIntegerNDArray.of(array2));
        assertEquals(String.format(Errors.UNSUPPORTED_TYPE, "String"), exception.getMessage());

        char[][][] array3 = new char[4][5][3];
        exception = assertThrows(IllegalArgumentException.class, () -> BasicIntegerNDArray.of(array3));
        assertEquals(String.format(Errors.UNSUPPORTED_TYPE, "char"), exception.getMessage());
    }

    @Test
    void test1DArrayConstructorsWithByte() {
        byte[] real = new byte[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToByte(i);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertSpecializedEquals(Integer.valueOf((int) i), array.get(i));
    }

    @Test
    void test1DArrayConstructorsWithShort() {
        short[] real = new short[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToShort(i);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertSpecializedEquals(Integer.valueOf((int) i), array.get(i));
    }

    @Test
    void test1DArrayConstructorsWithInt() {
        int[] real = new int[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToInteger(i);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertSpecializedEquals(Integer.valueOf((int) i), array.get(i));
    }

    @Test
    void test1DArrayConstructorsWithLong() {
        long[] real = new long[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToLong(i);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertSpecializedEquals(Integer.valueOf((int) i), array.get(i));
    }

    @Test
    void test1DArrayConstructorsWithFloat() {
        float[] real = new float[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToFloat(i);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertSpecializedEquals(Integer.valueOf((int) i), array.get(i));
    }

    @Test
    void test1DArrayConstructorsWithDouble() {
        double[] real = new double[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToDouble(i);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertSpecializedEquals(Integer.valueOf((int) i), array.get(i));
    }

    @Test
    void test1DArrayConstructorsWithBigInteger() {
        BigInteger[] real = new BigInteger[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToBigInteger(i);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertSpecializedEquals(Integer.valueOf((int) i), array.get(i));
    }

    @Test
    void test1DArrayConstructorsWithBigDecimal() {
        BigDecimal[] real = new BigDecimal[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToBigDecimal(i);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertSpecializedEquals(Integer.valueOf((int) i), array.get(i));
    }

    @Test
    void test2DFloatArrayConstructorsWithBigInteger() {
        BigInteger[][] real = new BigInteger[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToBigInteger(i * real.length + j);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertSpecializedEquals(Integer.valueOf((int) (i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DFloatArrayConstructorsWithBigDecimal() {
        BigDecimal[][] real = new BigDecimal[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToBigDecimal(i * real.length + j);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertSpecializedEquals(Integer.valueOf((int) (i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DFloatArrayConstructorsWithByte() {
        byte[][] real = new byte[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToByte(i * real.length + j);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertSpecializedEquals(Integer.valueOf((int) (i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DFloatArrayConstructorsWithShort() {
        short[][] real = new short[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToShort(i * real.length + j);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertSpecializedEquals(Integer.valueOf((int) (i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DFloatArrayConstructorsWithInt() {
        int[][] real = new int[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToInteger(i * real.length + j);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertSpecializedEquals(Integer.valueOf((int) (i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DFloatArrayConstructorsWithLong() {
        long[][] real = new long[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToLong(i * real.length + j);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertSpecializedEquals(Integer.valueOf((int) (i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DFloatArrayConstructorsWithFloat() {
        float[][] real = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToFloat(i * real.length + j);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertSpecializedEquals(Integer.valueOf((int) (i * real.length + j)), array.get(i, j));
    }

    @Test
    void test2DFloatArrayConstructorsWithDouble() {
        double[][] real = new double[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToDouble(i * real.length + j);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertSpecializedEquals(Integer.valueOf((int) (i * real.length + j)), array.get(i, j));
    }

    @Test
    void test3DFloatArrayConstructorsWithBigDecimal() {
        BigDecimal[][][] real = new BigDecimal[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToBigDecimal((i * real.length + j) * real[i].length + k);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertSpecializedEquals(Integer.valueOf((int) ((i * real.length + j) * real[i].length + k)),
                            array.get(i, j, k));
    }

    @Test
    void test3DFloatArrayConstructorsWithBigInteger() {
        BigInteger[][][] real = new BigInteger[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToBigInteger((i * real.length + j) * real[i].length + k);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertSpecializedEquals(Integer.valueOf((int) ((i * real.length + j) * real[i].length + k)),
                            array.get(i, j, k));
    }

    @Test
    void test3DFloatArrayConstructorsWithByte() {
        byte[][][] real = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToByte((i * real.length + j) * real[i].length + k);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertSpecializedEquals(Integer.valueOf((int) ((i * real.length + j) * real[i].length + k)),
                            array.get(i, j, k));
    }

    @Test
    void test3DFloatArrayConstructorsWithShort() {
        short[][][] real = new short[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToShort((i * real.length + j) * real[i].length + k);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertSpecializedEquals(Integer.valueOf((int) ((i * real.length + j) * real[i].length + k)),
                            array.get(i, j, k));
    }

    @Test
    void test3DFloatArrayConstructorsWithInt() {
        int[][][] real = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToInteger((i * real.length + j) * real[i].length + k);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertSpecializedEquals(Integer.valueOf((int) ((i * real.length + j) * real[i].length + k)),
                            array.get(i, j, k));
    }

    @Test
    void test3DFloatArrayConstructorsWithLong() {
        long[][][] real = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToLong((i * real.length + j) * real[i].length + k);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertSpecializedEquals(Integer.valueOf((int) ((i * real.length + j) * real[i].length + k)),
                            array.get(i, j, k));
    }

    @Test
    void test3DFloatArrayConstructorsWithFloat() {
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToFloat((i * real.length + j) * real[i].length + k);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertSpecializedEquals(Integer.valueOf((int) ((i * real.length + j) * real[i].length + k)),
                            array.get(i, j, k));
    }

    @Test
    void test3DFloatArrayConstructorsWithDouble() {
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToDouble((i * real.length + j) * real[i].length + k);
        NDArray<Integer> array = BasicIntegerNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertSpecializedEquals(Integer.valueOf((int) ((i * real.length + j) * real[i].length + k)),
                            array.get(i, j, k));
    }

    @Test
    void testCopyConstructorWithByte() {
        int[] shape = { 4, 5, 3 };
        byte[][][] real = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToByte((i * real.length + j) * real[i].length + k);
        NDArray<Byte> array1 = new BasicByteNDArray(shape).copyFrom(real);
        NDArray<Integer> array2 = new BasicIntegerNDArray(array1);
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                }
    }

    @Test
    void testCopyConstructorWithShort() {
        int[] shape = { 4, 5, 3 };
        short[][][] real = new short[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToShort((i * real.length + j) * real[i].length + k);
        NDArray<Short> array1 = new BasicShortNDArray(shape).copyFrom(real);
        NDArray<Integer> array2 = new BasicIntegerNDArray(array1);
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                }
    }

    @Test
    void testCopyConstructorWithInt() {
        int[] shape = { 4, 5, 3 };
        int[][][] real = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToInteger((i * real.length + j) * real[i].length + k);
        NDArray<Integer> array1 = new BasicIntegerNDArray(shape).copyFrom(real);
        NDArray<Integer> array2 = new BasicIntegerNDArray(array1);
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                }
    }

    @Test
    void testCopyConstructorWithLong() {
        int[] shape = { 4, 5, 3 };
        long[][][] real = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToLong((i * real.length + j) * real[i].length + k);
        NDArray<Long> array1 = new BasicLongNDArray(shape).copyFrom(real);
        NDArray<Integer> array2 = new BasicIntegerNDArray(array1);
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                }
    }

    @Test
    void testCopyConstructorWithFloat() {
        int[] shape = { 4, 5, 3 };
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToFloat((i * real.length + j) * real[i].length + k);
        NDArray<Float> array1 = new BasicFloatNDArray(shape).copyFrom(real);
        NDArray<Integer> array2 = new BasicIntegerNDArray(array1);
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                }
    }

    @Test
    void testCopyConstructorWithDouble() {
        int[] shape = { 4, 5, 3 };
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToDouble((i * real.length + j) * real[i].length + k);
        NDArray<Double> array1 = new BasicDoubleNDArray(shape).copyFrom(real);
        NDArray<Integer> array2 = new BasicIntegerNDArray(array1);
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                }
    }

    @Test
    void testCopyConstructorWithBigInteger() {
        int[] shape = { 4, 5, 3 };
        BigInteger[][][] real = new BigInteger[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToBigInteger((i * real.length + j) * real[i].length + k);
        NDArray<BigInteger> array1 = new BasicBigIntegerNDArray(shape).copyFrom(real);
        NDArray<Integer> array2 = new BasicIntegerNDArray(array1);
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                }
    }

    @Test
    void testCopyConstructorWithBigDecimal() {
        int[] shape = { 4, 5, 3 };
        BigDecimal[][][] real = new BigDecimal[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToBigDecimal((i * real.length + j) * real[i].length + k);
        NDArray<BigDecimal> array1 = new BasicBigDecimalNDArray(shape).copyFrom(real);
        NDArray<Integer> array2 = new BasicIntegerNDArray(array1);
        array1.set(0, 2, 2, 2);
        for (int k = 0; k < shape[2]; k++)
            for (int j = 0; j < shape[1]; j++)
                for (int i = 0; i < shape[0]; i++) {
                    if (i == 2 && j == 2 && k == 2)
                        assertNotEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                    else
                        assertEquals(array1.get(i, j, k).doubleValue(), array2.get(i, j, k).doubleValue());
                }
    }

    @Test
    void testWrongCopyFrom1DArrayWithFloat() {
        NDArray<Integer> array = new BasicIntegerNDArray(4, 3, 5);
        float[] wrongshapedArray = new float[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
        
        NDArray<Integer> array2 = new BasicIntegerNDArray(5);
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "5"), exception.getMessage());

        NDArray<Integer> array3 = new BasicIntegerNDArray(3, 5);
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithDouble() {
        NDArray<Integer> array = new BasicIntegerNDArray(4, 3, 5);
        double[] wrongshapedArray = new double[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
        
        NDArray<Integer> array2 = new BasicIntegerNDArray(5);
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "5"), exception.getMessage());

        NDArray<Integer> array3 = new BasicIntegerNDArray(3, 5);
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithByte() {
        NDArray<Integer> array = new BasicIntegerNDArray(4, 3, 5);
        byte[] wrongshapedArray = new byte[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
        
        NDArray<Integer> array2 = new BasicIntegerNDArray(5);
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "5"), exception.getMessage());

        NDArray<Integer> array3 = new BasicIntegerNDArray(3, 5);
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithShort() {
        NDArray<Integer> array = new BasicIntegerNDArray(4, 3, 5);
        short[] wrongshapedArray = new short[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
        
        NDArray<Integer> array2 = new BasicIntegerNDArray(5);
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "5"), exception.getMessage());

        NDArray<Integer> array3 = new BasicIntegerNDArray(3, 5);
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithInt() {
        NDArray<Integer> array = new BasicIntegerNDArray(4, 3, 5);
        int[] wrongshapedArray = new int[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
        
        NDArray<Integer> array2 = new BasicIntegerNDArray(5);
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "5"), exception.getMessage());

        NDArray<Integer> array3 = new BasicIntegerNDArray(3, 5);
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithLong() {
        NDArray<Integer> array = new BasicIntegerNDArray(4, 3, 5);
        long[] wrongshapedArray = new long[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
        
        NDArray<Integer> array2 = new BasicIntegerNDArray(5);
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "5"), exception.getMessage());

        NDArray<Integer> array3 = new BasicIntegerNDArray(3, 5);
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithBigInteger() {
        NDArray<Integer> array = new BasicIntegerNDArray(4, 3, 5);
        BigInteger[] wrongshapedArray = new BigInteger[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
        
        NDArray<Integer> array2 = new BasicIntegerNDArray(5);
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "5"), exception.getMessage());

        NDArray<Integer> array3 = new BasicIntegerNDArray(3, 5);
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithBigDecimal() {
        NDArray<Integer> array = new BasicIntegerNDArray(4, 3, 5);
        BigDecimal[] wrongshapedArray = new BigDecimal[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
        
        NDArray<Integer> array2 = new BasicIntegerNDArray(5);
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "5"), exception.getMessage());

        NDArray<Integer> array3 = new BasicIntegerNDArray(3, 5);
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "3 × 5"), exception.getMessage());
    }

}
