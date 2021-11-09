package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;
import io.github.hakkelt.ndarrays.internal.Errors;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$3Constructors")
@Patterns({ "Float.valueOf((float)", "Float", "BasicFloatNDArray" })
@Replacements({ "Byte.valueOf((byte)", "Byte", "BasicByteNDArray" })
@Replacements({ "Short.valueOf((short)", "Short", "BasicShortNDArray" })
@Replacements({ "Integer.valueOf((int)", "Integer", "BasicIntegerNDArray" })
@Replacements({ "Long.valueOf((long)", "Long", "BasicLongNDArray" })
@Replacements({ "Double.valueOf((double)", "Double", "BasicDoubleNDArray" })
@Replacements(value = { "BigInteger.valueOf((long)", "BigInteger", "BasicBigIntegerNDArray" }, extraImports = "java.math.BigInteger")
@Replacements(value = { "BigDecimal.valueOf((double)", "BigDecimal", "BasicBigDecimalNDArray" }, extraImports = "java.math.BigDecimal")
class TestRealNDArrayConstructors extends TestBase {

    @Test
    void testDimsConstructor() {
        int[] shape = { 2, 4 };
        NDArray<Float> array = new BasicFloatNDArray(shape);
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
        Exception exception = assertThrows(IllegalArgumentException.class, () -> BasicFloatNDArray.of(array));
        assertEquals(String.format(Errors.UNSUPPORTED_TYPE, "String"), exception.getMessage());

        String[] array2 = {"asdf", "", null};
        exception = assertThrows(IllegalArgumentException.class, () -> BasicFloatNDArray.of(array2));
        assertEquals(String.format(Errors.UNSUPPORTED_TYPE, "String"), exception.getMessage());

        char[][][] array3 = new char[4][5][3];
        exception = assertThrows(IllegalArgumentException.class, () -> BasicFloatNDArray.of(array3));
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
    void test1DArrayConstructors() {
        byte[] real = new byte[16];
        for (int i = 0; i < real.length; i++)
            real[i] = wrapToByte(i);
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            assertSpecializedEquals(Float.valueOf((float) i), array.get(i));
    }

    @Test
    @Patterns({"BigInteger", "wrapToBigInteger"})
    @Replacements({"BigDecimal", "wrapToBigDecimal"})
    @Replacements({"byte", "wrapToByte"})
    @Replacements({"short", "wrapToShort"})
    @Replacements({"int", "wrapToInteger"})
    @Replacements({"long", "wrapToLong"})
    @Replacements({"float", "wrapToFloat"})
    @Replacements({"double", "wrapToDouble"})
    void test2DFloatArrayConstructors() {
        BigInteger[][] real = new BigInteger[4][5];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                real[i][j] = wrapToBigInteger(i * real.length + j);
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                assertSpecializedEquals(Float.valueOf((float) (i * real.length + j)), array.get(i, j));
    }

    @Test
    @Patterns({"BigDecimal", "wrapToBigDecimal"})
    @Replacements({"BigInteger", "wrapToBigInteger"})
    @Replacements({"byte", "wrapToByte"})
    @Replacements({"short", "wrapToShort"})
    @Replacements({"int", "wrapToInteger"})
    @Replacements({"long", "wrapToLong"})
    @Replacements({"float", "wrapToFloat"})
    @Replacements({"double", "wrapToDouble"})
    void test3DFloatArrayConstructors() {
        BigDecimal[][][] real = new BigDecimal[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToBigDecimal((i * real.length + j) * real[i].length + k);
        NDArray<Float> array = BasicFloatNDArray.of(real);
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    assertSpecializedEquals(Float.valueOf((float) ((i * real.length + j) * real[i].length + k)),
                            array.get(i, j, k));
    }

    @Test
    @Patterns({"byte", "Byte", "wrapToByte", "BasicByteNDArray"})
    @Replacements({"short", "Short", "wrapToShort", "BasicShortNDArray"})
    @Replacements({"int", "Integer", "wrapToInteger", "BasicIntegerNDArray"})
    @Replacements({"long", "Long", "wrapToLong", "BasicLongNDArray"})
    @Replacements({"float", "Float", "wrapToFloat", "BasicFloatNDArray"})
    @Replacements({"double", "Double", "wrapToDouble", "BasicDoubleNDArray"})
    @Replacements({"BigInteger", "BigInteger", "wrapToBigInteger", "BasicBigIntegerNDArray"})
    @Replacements({"BigDecimal", "BigDecimal", "wrapToBigDecimal", "BasicBigDecimalNDArray"})
    void testCopyConstructor() {
        int[] shape = { 4, 5, 3 };
        byte[][][] real = new byte[4][5][3];
        for (int i = 0; i < real.length; i++)
            for (int j = 0; j < real[i].length; j++)
                for (int k = 0; k < real[i][j].length; k++)
                    real[i][j][k] = wrapToByte((i * real.length + j) * real[i].length + k);
        NDArray<Byte> array1 = new BasicByteNDArray(shape).copyFrom(real);
        NDArray<Float> array2 = new BasicFloatNDArray(array1);
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
    @Replace(pattern = "float", replacements = {"double", "byte", "short", "int", "long", "BigInteger", "BigDecimal"})
    void testWrongCopyFrom1DArray() {
        NDArray<Float> array = new BasicFloatNDArray(4, 3, 5);
        float[] wrongshapedArray = new float[4];
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "4 × 3 × 5"), exception.getMessage());
        
        NDArray<Float> array2 = new BasicFloatNDArray(5);
        exception = assertThrows(IllegalArgumentException.class, () -> array2.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "5"), exception.getMessage());

        NDArray<Float> array3 = new BasicFloatNDArray(3, 5);
        exception = assertThrows(IllegalArgumentException.class, () -> array3.copyFrom(wrongshapedArray));
        assertEquals(String.format(Errors.INCOMPATIBLE_SHAPE, "4", "3 × 5"), exception.getMessage());
    }

}
