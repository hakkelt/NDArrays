package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;
import io.github.hakkelt.ndarrays.internal.Errors;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$2Constructors")
@Patterns({ "Float", "BasicComplexFloatNDArray" })
@Replacements({ "Double", "BasicComplexDoubleNDArray" })
class TestComplexNDArrayConstructors extends TestBase {

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
    @Patterns({"byte", "wrapToByte"})
    @Replacements({"short", "wrapToShort"})
    @Replacements({"int", "wrapToInteger"})
    @Replacements({"long", "wrapToLong"})
    @Replacements({"float", "wrapToFloat"})
    @Replacements({"double", "wrapToDouble"})
    @Replacements({"BigInteger", "wrapToBigInteger"})
    @Replacements({"BigDecimal", "wrapToBigDecimal"})
    @Replacements({"Complex", "wrapToComplex"})
    void test1DArraySingleParameterConstructors() {
        byte[] real = new byte[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToByte(i);
        ComplexNDArray<Float> array = BasicComplexFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertEquals(new Complex(i, 0), array.get(i));
    }

    @Test
    @Patterns({"byte", "wrapToByte"})
    @Replacements({"short", "wrapToShort"})
    @Replacements({"int", "wrapToInteger"})
    @Replacements({"long", "wrapToLong"})
    @Replacements({"float", "wrapToFloat"})
    @Replacements({"double", "wrapToDouble"})
    @Replacements({"BigInteger", "wrapToBigInteger"})
    @Replacements({"BigDecimal", "wrapToBigDecimal"})
    @Replacements({"Complex", "wrapToComplex"})
    void test2DArraySingleParameterConstructors() {
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
    @Patterns({"byte", "wrapToByte"})
    @Replacements({"short", "wrapToShort"})
    @Replacements({"int", "wrapToInteger"})
    @Replacements({"long", "wrapToLong"})
    @Replacements({"float", "wrapToFloat"})
    @Replacements({"double", "wrapToDouble"})
    @Replacements({"BigInteger", "wrapToBigInteger"})
    @Replacements({"BigDecimal", "wrapToBigDecimal"})
    @Replacements({"Complex", "wrapToComplex"})
    void test3DArraySingleParameterConstructors() {
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
    @Patterns({"byte", "wrapToByte"})
    @Replacements({"short", "wrapToShort"})
    @Replacements({"int", "wrapToInteger"})
    @Replacements({"long", "wrapToLong"})
    @Replacements({"float", "wrapToFloat"})
    @Replacements({"double", "wrapToDouble"})
    @Replacements({"BigInteger", "wrapToBigInteger"})
    @Replacements({"BigDecimal", "wrapToBigDecimal"})
    void test1DArrayComplexConstructors() {
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
    @Patterns({"byte", "wrapToByte"})
    @Replacements({"short", "wrapToShort"})
    @Replacements({"int", "wrapToInteger"})
    @Replacements({"long", "wrapToLong"})
    @Replacements({"float", "wrapToFloat"})
    @Replacements({"double", "wrapToDouble"})
    @Replacements({"BigInteger", "wrapToBigInteger"})
    @Replacements({"BigDecimal", "wrapToBigDecimal"})
    void test2DArrayComplexConstructors() {
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
    @Patterns({"byte", "wrapToByte"})
    @Replacements({"short", "wrapToShort"})
    @Replacements({"int", "wrapToInteger"})
    @Replacements({"long", "wrapToLong"})
    @Replacements({"float", "wrapToFloat"})
    @Replacements({"double", "wrapToDouble"})
    @Replacements({"BigInteger", "wrapToBigInteger"})
    @Replacements({"BigDecimal", "wrapToBigDecimal"})
    void test3DArrayComplexConstructors() {
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
    @Patterns({"BigInteger", "wrapToBigInteger", "BasicBigIntegerNDArray"})
    @Replacements({"BigDecimal", "wrapToBigDecimal", "BasicBigDecimalNDArray"})
    @Replacements({"byte", "wrapToByte", "BasicByteNDArray"})
    @Replacements({"short", "wrapToShort", "BasicShortNDArray"})
    @Replacements({"int", "wrapToInteger", "BasicIntegerNDArray"})
    @Replacements({"long", "wrapToLong", "BasicLongNDArray"})
    @Replacements({"float", "wrapToFloat", "BasicFloatNDArray"})
    @Replacements({"double", "wrapToDouble", "BasicDoubleNDArray"})
    void testRealCopyConstructor() {
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
    @Patterns({"BigDecimal", "wrapToBigDecimal", "BasicComplexDoubleNDArray"})
    @Replacements({"BigInteger", "wrapToBigInteger", "BasicComplexDoubleNDArray"})
    @Replacements({"byte", "wrapToByte", "BasicComplexDoubleNDArray"})
    @Replacements({"short", "wrapToShort", "BasicComplexDoubleNDArray"})
    @Replacements({"int", "wrapToInteger", "BasicComplexDoubleNDArray"})
    @Replacements({"long", "wrapToLong", "BasicComplexDoubleNDArray"})
    @Replacements({"float", "wrapToFloat", "BasicComplexFloatNDArray"})
    @Replacements({"double", "wrapToDouble", "BasicComplexDoubleNDArray"})
    void testComplexCopyConstructor() {
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
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long", "BigInteger", "BigDecimal", "Complex"})
    void testWrongCopyFrom1DArray() {
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
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long", "BigInteger", "BigDecimal", "Complex"})
    void testWrongCopyFromComplex1DArray() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        float[] real = new float[4];
        float[] imag = new float[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
    }

    @Test
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long", "BigInteger", "BigDecimal", "Complex"})
    void testWrongCopyFrom1DArraysDifferInShape() {
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
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long", "BigInteger", "BigDecimal", "Complex"})
    void testWrongCopyFrom1DArraysDifferInShapeLength() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        float[][] real = new float[3][4];
        float[][][] imag = new float[1][3][4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(real, imag));
        assertEquals(String.format(Errors.ARRAYS_DIFFER_IN_SHAPE), exception.getMessage());
    }

    @Test
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long", "BigInteger", "BigDecimal", "Complex"})
    void testWrongCopyFrom3DArraysDifferInShape() {
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 3, 5);
        float[][][] real = new float[4][3][5];
        float[][][] imag = new float[4][5][5];
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
