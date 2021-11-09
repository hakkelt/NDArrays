/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestBasicComplexFloatNDArrayConstructors.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:54 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.Errors;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.Test;

class TestBasicComplexFloatNDArrayConstructors extends TestBase {

    @Test
    void testDimsConstructor() {
        int[] shape = { 2, 4 };
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(shape);
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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> BasicComplexFloatNDArray.of(array));
        assertEquals(String.format(Errors.UNSUPPORTED_TYPE, "String"), exception.getMessage());

        String[] array2 = {"asdf", "", null};
        exception = assertThrows(IllegalArgumentException.class, () -> BasicComplexFloatNDArray.of(array2));
        assertEquals(String.format(Errors.UNSUPPORTED_TYPE, "String"), exception.getMessage());

        char[][][] array3 = new char[4][5][3];
        exception = assertThrows(IllegalArgumentException.class, () -> BasicComplexFloatNDArray.of(array3));
        assertEquals(String.format(Errors.UNSUPPORTED_TYPE, "char"), exception.getMessage());
    }

    @Test
    void test1DArraySingleParameterConstructorsWithByte() {
        byte[] real = new byte[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToByte(i);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DArraySingleParameterConstructorsWithShort() {
        short[] real = new short[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToShort(i);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DArraySingleParameterConstructorsWithInt() {
        int[] real = new int[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToInteger(i);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DArraySingleParameterConstructorsWithLong() {
        long[] real = new long[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToLong(i);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DArraySingleParameterConstructorsWithFloat() {
        float[] real = new float[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToFloat(i);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DArraySingleParameterConstructorsWithDouble() {
        double[] real = new double[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToDouble(i);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DArraySingleParameterConstructorsWithBigInteger() {
        BigInteger[] real = new BigInteger[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToBigInteger(i);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DArraySingleParameterConstructorsWithBigDecimal() {
        BigDecimal[] real = new BigDecimal[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToBigDecimal(i);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test1DArraySingleParameterConstructorsWithComplex() {
        Complex[] real = new Complex[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToComplex(i);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    void test2DArraySingleParameterConstructorsWithByte() {
        byte[][] real = new byte[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToByte(i * real.length + j);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DArraySingleParameterConstructorsWithShort() {
        short[][] real = new short[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToShort(i * real.length + j);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DArraySingleParameterConstructorsWithInt() {
        int[][] real = new int[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToInteger(i * real.length + j);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DArraySingleParameterConstructorsWithLong() {
        long[][] real = new long[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToLong(i * real.length + j);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DArraySingleParameterConstructorsWithFloat() {
        float[][] real = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToFloat(i * real.length + j);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DArraySingleParameterConstructorsWithDouble() {
        double[][] real = new double[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToDouble(i * real.length + j);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DArraySingleParameterConstructorsWithBigInteger() {
        BigInteger[][] real = new BigInteger[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToBigInteger(i * real.length + j);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DArraySingleParameterConstructorsWithBigDecimal() {
        BigDecimal[][] real = new BigDecimal[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToBigDecimal(i * real.length + j);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test2DArraySingleParameterConstructorsWithComplex() {
        Complex[][] real = new Complex[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToComplex(i * real.length + j);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertEquals(new Complex(i * real.length + j, 0), array.get(i, j));
    }

    @Test
    void test3DArraySingleParameterConstructorsWithByte() {
        byte[][][] real = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToByte((i * real.length + j) * real[i].length + k);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DArraySingleParameterConstructorsWithShort() {
        short[][][] real = new short[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToShort((i * real.length + j) * real[i].length + k);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DArraySingleParameterConstructorsWithInt() {
        int[][][] real = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToInteger((i * real.length + j) * real[i].length + k);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DArraySingleParameterConstructorsWithLong() {
        long[][][] real = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToLong((i * real.length + j) * real[i].length + k);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DArraySingleParameterConstructorsWithFloat() {
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToFloat((i * real.length + j) * real[i].length + k);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DArraySingleParameterConstructorsWithDouble() {
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToDouble((i * real.length + j) * real[i].length + k);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DArraySingleParameterConstructorsWithBigInteger() {
        BigInteger[][][] real = new BigInteger[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToBigInteger((i * real.length + j) * real[i].length + k);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DArraySingleParameterConstructorsWithBigDecimal() {
        BigDecimal[][][] real = new BigDecimal[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToBigDecimal((i * real.length + j) * real[i].length + k);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test3DArraySingleParameterConstructorsWithComplex() {
        Complex[][][] real = new Complex[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToComplex((i * real.length + j) * real[i].length + k);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertEquals(new Complex((i * real.length + j) * real[i].length + k, 0), array.get(i, j, k));
    }

    @Test
    void test1DArrayComplexConstructorsWithByte() {
        byte[] real = new byte[16];
        byte[] imag = new byte[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = wrapToByte(i);
            imag[i] = wrapToByte(-i);
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DArrayComplexConstructorsWithShort() {
        short[] real = new short[16];
        short[] imag = new short[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = wrapToShort(i);
            imag[i] = wrapToShort(-i);
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DArrayComplexConstructorsWithInt() {
        int[] real = new int[16];
        int[] imag = new int[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = wrapToInteger(i);
            imag[i] = wrapToInteger(-i);
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DArrayComplexConstructorsWithLong() {
        long[] real = new long[16];
        long[] imag = new long[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = wrapToLong(i);
            imag[i] = wrapToLong(-i);
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DArrayComplexConstructorsWithFloat() {
        float[] real = new float[16];
        float[] imag = new float[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = wrapToFloat(i);
            imag[i] = wrapToFloat(-i);
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DArrayComplexConstructorsWithDouble() {
        double[] real = new double[16];
        double[] imag = new double[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = wrapToDouble(i);
            imag[i] = wrapToDouble(-i);
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DArrayComplexConstructorsWithBigInteger() {
        BigInteger[] real = new BigInteger[16];
        BigInteger[] imag = new BigInteger[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = wrapToBigInteger(i);
            imag[i] = wrapToBigInteger(-i);
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test1DArrayComplexConstructorsWithBigDecimal() {
        BigDecimal[] real = new BigDecimal[16];
        BigDecimal[] imag = new BigDecimal[16];
        for (int i = 0; i < real.length; i++) {
            real[i] = wrapToBigDecimal(i);
            imag[i] = wrapToBigDecimal(-i);
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, -i), array.get(i));
    }

    @Test
    void test2DArrayComplexConstructorsWithByte() {
        byte[][] real = new byte[4][5];
        byte[][] imag = new byte[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = wrapToByte(i * real.length + j);
                imag[i][j] = wrapToByte(-(i * imag.length + j));
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DArrayComplexConstructorsWithShort() {
        short[][] real = new short[4][5];
        short[][] imag = new short[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = wrapToShort(i * real.length + j);
                imag[i][j] = wrapToShort(-(i * imag.length + j));
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DArrayComplexConstructorsWithInt() {
        int[][] real = new int[4][5];
        int[][] imag = new int[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = wrapToInteger(i * real.length + j);
                imag[i][j] = wrapToInteger(-(i * imag.length + j));
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DArrayComplexConstructorsWithLong() {
        long[][] real = new long[4][5];
        long[][] imag = new long[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = wrapToLong(i * real.length + j);
                imag[i][j] = wrapToLong(-(i * imag.length + j));
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DArrayComplexConstructorsWithFloat() {
        float[][] real = new float[4][5];
        float[][] imag = new float[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = wrapToFloat(i * real.length + j);
                imag[i][j] = wrapToFloat(-(i * imag.length + j));
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DArrayComplexConstructorsWithDouble() {
        double[][] real = new double[4][5];
        double[][] imag = new double[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = wrapToDouble(i * real.length + j);
                imag[i][j] = wrapToDouble(-(i * imag.length + j));
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DArrayComplexConstructorsWithBigInteger() {
        BigInteger[][] real = new BigInteger[4][5];
        BigInteger[][] imag = new BigInteger[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = wrapToBigInteger(i * real.length + j);
                imag[i][j] = wrapToBigInteger(-(i * imag.length + j));
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test2DArrayComplexConstructorsWithBigDecimal() {
        BigDecimal[][] real = new BigDecimal[4][5];
        BigDecimal[][] imag = new BigDecimal[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                real[i][j] = wrapToBigDecimal(i * real.length + j);
                imag[i][j] = wrapToBigDecimal(-(i * imag.length + j));
            }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++) {
                int val = i * real.length + j;
                assertEquals(new Complex(val, -val), array.get(i, j));
            }
    }

    @Test
    void test3DArrayComplexConstructorsWithByte() {
        byte[][][] real = new byte[4][5][3];
        byte[][][] imag = new byte[4][5][3];
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToByte((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToByte(-((i * imag.length + j) * imag[i].length + k));
                }
            }
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
            }
        }
    }

    @Test
    void test3DArrayComplexConstructorsWithShort() {
        short[][][] real = new short[4][5][3];
        short[][][] imag = new short[4][5][3];
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToShort((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToShort(-((i * imag.length + j) * imag[i].length + k));
                }
            }
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
            }
        }
    }

    @Test
    void test3DArrayComplexConstructorsWithInt() {
        int[][][] real = new int[4][5][3];
        int[][][] imag = new int[4][5][3];
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToInteger((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToInteger(-((i * imag.length + j) * imag[i].length + k));
                }
            }
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
            }
        }
    }

    @Test
    void test3DArrayComplexConstructorsWithLong() {
        long[][][] real = new long[4][5][3];
        long[][][] imag = new long[4][5][3];
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToLong((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToLong(-((i * imag.length + j) * imag[i].length + k));
                }
            }
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
            }
        }
    }

    @Test
    void test3DArrayComplexConstructorsWithFloat() {
        float[][][] real = new float[4][5][3];
        float[][][] imag = new float[4][5][3];
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToFloat((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToFloat(-((i * imag.length + j) * imag[i].length + k));
                }
            }
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
            }
        }
    }

    @Test
    void test3DArrayComplexConstructorsWithDouble() {
        double[][][] real = new double[4][5][3];
        double[][][] imag = new double[4][5][3];
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToDouble((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToDouble(-((i * imag.length + j) * imag[i].length + k));
                }
            }
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
            }
        }
    }

    @Test
    void test3DArrayComplexConstructorsWithBigInteger() {
        BigInteger[][][] real = new BigInteger[4][5][3];
        BigInteger[][][] imag = new BigInteger[4][5][3];
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToBigInteger((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToBigInteger(-((i * imag.length + j) * imag[i].length + k));
                }
            }
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
            }
        }
    }

    @Test
    void test3DArrayComplexConstructorsWithBigDecimal() {
        BigDecimal[][][] real = new BigDecimal[4][5][3];
        BigDecimal[][][] imag = new BigDecimal[4][5][3];
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToBigDecimal((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToBigDecimal(-((i * imag.length + j) * imag[i].length + k));
                }
            }
        }
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real, imag);
        for (int i = 0; i < real.length; i++) {
            for (int j = 0; j < real[i].length; j++) {
                for (int k = 0; k < imag[i][j].length; k++) {
                    int val = (i * real.length + j) * real[i].length + k;
                    assertEquals(new Complex(val, -val), array.get(i, j, k));
                }
            }
        }
    }

    @Test
    void testRealCopyConstructorWithBigInteger() {
        int[] shape = { 4, 5, 3 };
        BigInteger[][][] real = new BigInteger[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToBigInteger((i * real.length + j) * real[i].length + k);
        NDArray<? extends Number> array1 = new BasicByteNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2, 2, 2);
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
    void testRealCopyConstructorWithBigDecimal() {
        int[] shape = { 4, 5, 3 };
        BigDecimal[][][] real = new BigDecimal[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToBigDecimal((i * real.length + j) * real[i].length + k);
        NDArray<? extends Number> array1 = new BasicByteNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2, 2, 2);
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
    void testRealCopyConstructorWithByte() {
        int[] shape = { 4, 5, 3 };
        byte[][][] real = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToByte((i * real.length + j) * real[i].length + k);
        NDArray<? extends Number> array1 = new BasicByteNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2, 2, 2);
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
    void testRealCopyConstructorWithShort() {
        int[] shape = { 4, 5, 3 };
        short[][][] real = new short[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToShort((i * real.length + j) * real[i].length + k);
        NDArray<? extends Number> array1 = new BasicByteNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2, 2, 2);
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
    void testRealCopyConstructorWithInt() {
        int[] shape = { 4, 5, 3 };
        int[][][] real = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToInteger((i * real.length + j) * real[i].length + k);
        NDArray<? extends Number> array1 = new BasicByteNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2, 2, 2);
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
    void testRealCopyConstructorWithLong() {
        int[] shape = { 4, 5, 3 };
        long[][][] real = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToLong((i * real.length + j) * real[i].length + k);
        NDArray<? extends Number> array1 = new BasicByteNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2, 2, 2);
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
    void testRealCopyConstructorWithFloat() {
        int[] shape = { 4, 5, 3 };
        float[][][] real = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToFloat((i * real.length + j) * real[i].length + k);
        NDArray<? extends Number> array1 = new BasicByteNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2, 2, 2);
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
    void testRealCopyConstructorWithDouble() {
        int[] shape = { 4, 5, 3 };
        double[][][] real = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToDouble((i * real.length + j) * real[i].length + k);
        NDArray<? extends Number> array1 = new BasicByteNDArray(shape).copyFrom(real);
        ComplexNDArray<Double> array2 = new BasicComplexDoubleNDArray(array1);
        array1.set(0, 2, 2, 2);
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
    void testComplexCopyConstructorWithBigDecimal() {
        int[] shape = { 4, 5, 3 };
        BigDecimal[][][] real = new BigDecimal[4][5][3];
        BigDecimal[][][] imag = new BigDecimal[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToBigDecimal((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToBigDecimal(-((i * imag.length + j) * imag[i].length + k));
                }
        ComplexNDArray<?> array1 = new BasicComplexDoubleNDArray(shape).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(new Complex(0, 0), 2, 2, 2);
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
    void testComplexCopyConstructorWithBigInteger() {
        int[] shape = { 4, 5, 3 };
        BigInteger[][][] real = new BigInteger[4][5][3];
        BigInteger[][][] imag = new BigInteger[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToBigInteger((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToBigInteger(-((i * imag.length + j) * imag[i].length + k));
                }
        ComplexNDArray<?> array1 = new BasicComplexDoubleNDArray(shape).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(new Complex(0, 0), 2, 2, 2);
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
    void testComplexCopyConstructorWithByte() {
        int[] shape = { 4, 5, 3 };
        byte[][][] real = new byte[4][5][3];
        byte[][][] imag = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToByte((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToByte(-((i * imag.length + j) * imag[i].length + k));
                }
        ComplexNDArray<?> array1 = new BasicComplexDoubleNDArray(shape).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(new Complex(0, 0), 2, 2, 2);
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
    void testComplexCopyConstructorWithShort() {
        int[] shape = { 4, 5, 3 };
        short[][][] real = new short[4][5][3];
        short[][][] imag = new short[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToShort((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToShort(-((i * imag.length + j) * imag[i].length + k));
                }
        ComplexNDArray<?> array1 = new BasicComplexDoubleNDArray(shape).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(new Complex(0, 0), 2, 2, 2);
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
    void testComplexCopyConstructorWithInt() {
        int[] shape = { 4, 5, 3 };
        int[][][] real = new int[4][5][3];
        int[][][] imag = new int[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToInteger((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToInteger(-((i * imag.length + j) * imag[i].length + k));
                }
        ComplexNDArray<?> array1 = new BasicComplexDoubleNDArray(shape).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(new Complex(0, 0), 2, 2, 2);
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
    void testComplexCopyConstructorWithLong() {
        int[] shape = { 4, 5, 3 };
        long[][][] real = new long[4][5][3];
        long[][][] imag = new long[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToLong((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToLong(-((i * imag.length + j) * imag[i].length + k));
                }
        ComplexNDArray<?> array1 = new BasicComplexDoubleNDArray(shape).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(new Complex(0, 0), 2, 2, 2);
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
    void testComplexCopyConstructorWithFloat() {
        int[] shape = { 4, 5, 3 };
        float[][][] real = new float[4][5][3];
        float[][][] imag = new float[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToFloat((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToFloat(-((i * imag.length + j) * imag[i].length + k));
                }
        ComplexNDArray<?> array1 = new BasicComplexFloatNDArray(shape).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(new Complex(0, 0), 2, 2, 2);
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
    void testComplexCopyConstructorWithDouble() {
        int[] shape = { 4, 5, 3 };
        double[][][] real = new double[4][5][3];
        double[][][] imag = new double[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++) {
                    real[i][j][k] = wrapToDouble((i * real.length + j) * real[i].length + k);
                    imag[i][j][k] = wrapToDouble(-((i * imag.length + j) * imag[i].length + k));
                }
        ComplexNDArray<?> array1 = new BasicComplexDoubleNDArray(shape).copyFrom(real, imag);
        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(array1);
        array1.set(new Complex(0, 0), 2, 2, 2);
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
    void testWrongCopyFrom1DArrayWithFloat() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        float[] real = new float[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4);
        float[] real2 = new float[5];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5", "4"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithDouble() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        double[] real = new double[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4);
        double[] real2 = new double[5];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5", "4"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithByte() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        byte[] real = new byte[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4);
        byte[] real2 = new byte[5];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5", "4"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithShort() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        short[] real = new short[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4);
        short[] real2 = new short[5];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5", "4"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithInt() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        int[] real = new int[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4);
        int[] real2 = new int[5];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5", "4"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithLong() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        long[] real = new long[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4);
        long[] real2 = new long[5];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5", "4"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithBigInteger() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        BigInteger[] real = new BigInteger[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4);
        BigInteger[] real2 = new BigInteger[5];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5", "4"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithBigDecimal() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        BigDecimal[] real = new BigDecimal[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4);
        BigDecimal[] real2 = new BigDecimal[5];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5", "4"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArrayWithComplex() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        Complex[] real = new Complex[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4);
        Complex[] real2 = new Complex[5];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "5", "4"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromComplex1DArrayWithFloat() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        float[] real = new float[4];
        float[] imag = new float[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromComplex1DArrayWithDouble() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        double[] real = new double[4];
        double[] imag = new double[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromComplex1DArrayWithByte() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        byte[] real = new byte[4];
        byte[] imag = new byte[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromComplex1DArrayWithShort() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        short[] real = new short[4];
        short[] imag = new short[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromComplex1DArrayWithInt() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        int[] real = new int[4];
        int[] imag = new int[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromComplex1DArrayWithLong() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        long[] real = new long[4];
        long[] imag = new long[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromComplex1DArrayWithBigInteger() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        BigInteger[] real = new BigInteger[4];
        BigInteger[] imag = new BigInteger[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromComplex1DArrayWithBigDecimal() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        BigDecimal[] real = new BigDecimal[4];
        BigDecimal[] imag = new BigDecimal[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFromComplex1DArrayWithComplex() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        Complex[] real = new Complex[4];
        Complex[] imag = new Complex[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeWithFloat() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        float[] real = new float[4];
        float[] imag = new float[3];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4, 3, 5);
        float[] real2 = new float[3];
        float[] imag2 = new float[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2, imag2));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array3 = new BasicComplexFloatNDArray(4);
        float[] real3 = new float[3];
        float[] imag3 = new float[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(real3, imag3));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array4 = new BasicComplexFloatNDArray(4);
        exception = assertThrows(IllegalArgumentException.class, () -> array4.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeWithDouble() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        double[] real = new double[4];
        double[] imag = new double[3];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4, 3, 5);
        double[] real2 = new double[3];
        double[] imag2 = new double[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2, imag2));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array3 = new BasicComplexFloatNDArray(4);
        double[] real3 = new double[3];
        double[] imag3 = new double[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(real3, imag3));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array4 = new BasicComplexFloatNDArray(4);
        exception = assertThrows(IllegalArgumentException.class, () -> array4.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeWithByte() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        byte[] real = new byte[4];
        byte[] imag = new byte[3];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4, 3, 5);
        byte[] real2 = new byte[3];
        byte[] imag2 = new byte[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2, imag2));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array3 = new BasicComplexFloatNDArray(4);
        byte[] real3 = new byte[3];
        byte[] imag3 = new byte[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(real3, imag3));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array4 = new BasicComplexFloatNDArray(4);
        exception = assertThrows(IllegalArgumentException.class, () -> array4.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeWithShort() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        short[] real = new short[4];
        short[] imag = new short[3];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4, 3, 5);
        short[] real2 = new short[3];
        short[] imag2 = new short[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2, imag2));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array3 = new BasicComplexFloatNDArray(4);
        short[] real3 = new short[3];
        short[] imag3 = new short[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(real3, imag3));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array4 = new BasicComplexFloatNDArray(4);
        exception = assertThrows(IllegalArgumentException.class, () -> array4.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeWithInt() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        int[] real = new int[4];
        int[] imag = new int[3];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4, 3, 5);
        int[] real2 = new int[3];
        int[] imag2 = new int[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2, imag2));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array3 = new BasicComplexFloatNDArray(4);
        int[] real3 = new int[3];
        int[] imag3 = new int[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(real3, imag3));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array4 = new BasicComplexFloatNDArray(4);
        exception = assertThrows(IllegalArgumentException.class, () -> array4.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeWithLong() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        long[] real = new long[4];
        long[] imag = new long[3];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4, 3, 5);
        long[] real2 = new long[3];
        long[] imag2 = new long[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2, imag2));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array3 = new BasicComplexFloatNDArray(4);
        long[] real3 = new long[3];
        long[] imag3 = new long[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(real3, imag3));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array4 = new BasicComplexFloatNDArray(4);
        exception = assertThrows(IllegalArgumentException.class, () -> array4.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeWithBigInteger() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        BigInteger[] real = new BigInteger[4];
        BigInteger[] imag = new BigInteger[3];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4, 3, 5);
        BigInteger[] real2 = new BigInteger[3];
        BigInteger[] imag2 = new BigInteger[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2, imag2));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array3 = new BasicComplexFloatNDArray(4);
        BigInteger[] real3 = new BigInteger[3];
        BigInteger[] imag3 = new BigInteger[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(real3, imag3));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array4 = new BasicComplexFloatNDArray(4);
        exception = assertThrows(IllegalArgumentException.class, () -> array4.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeWithBigDecimal() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        BigDecimal[] real = new BigDecimal[4];
        BigDecimal[] imag = new BigDecimal[3];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4, 3, 5);
        BigDecimal[] real2 = new BigDecimal[3];
        BigDecimal[] imag2 = new BigDecimal[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2, imag2));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array3 = new BasicComplexFloatNDArray(4);
        BigDecimal[] real3 = new BigDecimal[3];
        BigDecimal[] imag3 = new BigDecimal[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(real3, imag3));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array4 = new BasicComplexFloatNDArray(4);
        exception = assertThrows(IllegalArgumentException.class, () -> array4.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeWithComplex() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        Complex[] real = new Complex[4];
        Complex[] imag = new Complex[3];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array2 = new BasicComplexFloatNDArray(4, 3, 5);
        Complex[] real2 = new Complex[3];
        Complex[] imag2 = new Complex[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(real2, imag2));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array3 = new BasicComplexFloatNDArray(4);
        Complex[] real3 = new Complex[3];
        Complex[] imag3 = new Complex[4];
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(real3, imag3));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());

        ComplexNDArray<Float> array4 = new BasicComplexFloatNDArray(4);
        exception = assertThrows(IllegalArgumentException.class, () -> array4.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeLengthWithFloat() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        float[][] real = new float[3][4];
        float[][][] imag = new float[1][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeLengthWithDouble() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        double[][] real = new double[3][4];
        double[][][] imag = new double[1][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeLengthWithByte() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        byte[][] real = new byte[3][4];
        byte[][][] imag = new byte[1][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeLengthWithShort() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        short[][] real = new short[3][4];
        short[][][] imag = new short[1][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeLengthWithInt() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        int[][] real = new int[3][4];
        int[][][] imag = new int[1][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeLengthWithLong() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        long[][] real = new long[3][4];
        long[][][] imag = new long[1][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeLengthWithBigInteger() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        BigInteger[][] real = new BigInteger[3][4];
        BigInteger[][][] imag = new BigInteger[1][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeLengthWithBigDecimal() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        BigDecimal[][] real = new BigDecimal[3][4];
        BigDecimal[][][] imag = new BigDecimal[1][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom1DArraysDifferInShapeLengthWithComplex() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        Complex[][] real = new Complex[3][4];
        Complex[][][] imag = new Complex[1][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom3DArraysDifferInShapeWithFloat() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        float[][][] real = new float[4][3][5];
        float[][][] imag = new float[4][5][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom3DArraysDifferInShapeWithDouble() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        double[][][] real = new double[4][3][5];
        double[][][] imag = new double[4][5][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom3DArraysDifferInShapeWithByte() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        byte[][][] real = new byte[4][3][5];
        byte[][][] imag = new byte[4][5][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom3DArraysDifferInShapeWithShort() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        short[][][] real = new short[4][3][5];
        short[][][] imag = new short[4][5][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom3DArraysDifferInShapeWithInt() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        int[][][] real = new int[4][3][5];
        int[][][] imag = new int[4][5][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom3DArraysDifferInShapeWithLong() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        long[][][] real = new long[4][3][5];
        long[][][] imag = new long[4][5][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom3DArraysDifferInShapeWithBigInteger() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        BigInteger[][][] real = new BigInteger[4][3][5];
        BigInteger[][][] imag = new BigInteger[4][5][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom3DArraysDifferInShapeWithBigDecimal() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        BigDecimal[][][] real = new BigDecimal[4][3][5];
        BigDecimal[][][] imag = new BigDecimal[4][5][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFrom3DArraysDifferInShapeWithComplex() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        Complex[][][] real = new Complex[4][3][5];
        Complex[][][] imag = new Complex[4][5][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    void testWrongCopyFromArraysDifferInType() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        float[][][] real = new float[4][3][5];
        double[][][] imag = new double[4][3][5];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.COPYFROM_INPUT_TYPE_DIFERS), exception.getMessage());
    }

    @Test
    void testWrongCopyFromArraysDifferInShapeWithNDArrays() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        NDArray<Double> real = new BasicDoubleNDArray(4, 5, 3);
        NDArray<Double> imag = new BasicDoubleNDArray(4, 3, 5);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

}
