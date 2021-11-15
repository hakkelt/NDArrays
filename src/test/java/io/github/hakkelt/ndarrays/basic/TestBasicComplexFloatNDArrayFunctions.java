/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestFunctions.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.internal.Errors;

import java.math.BigDecimal;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestBasicComplexFloatNDArrayFunctions extends TestBase {

    NDArray<Complex> array;

    @BeforeEach
    void setup() {
        array = new BasicComplexFloatNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToComplex(index));
    }

    @Test
    void testToString() {
        String str = array.toString();
        assertEquals(array.getNamePrefix() + " NDArray<Complex Float>(4 × 5 × 3)", str);
    }

    @Test
    void testcontentToString() {
        String str = array.contentToString();
        String firstLine = array.getNamePrefix() + " NDArray<Complex Float>(4 × 5 × 3)" + System.lineSeparator();
        if (array.dtype().equals(Float.class) || array.dtype().equals(Double.class) || array.dtype().equals(BigDecimal.class)) {
            String lineFormat = "%10.5e\t%10.5e\t%10.5e\t%10.5e\t%10.5e\t%n";
            String expected = new StringBuilder()
                .append(firstLine)
                .append("[:, :, 0] =" + System.lineSeparator())
                .append(String.format(lineFormat, 0.0e+00, 4.0e+00, 8.0e+00, 1.2e+01, 1.6e+01))
                .append(String.format(lineFormat, 1.0e+00, 5.0e+00, 9.0e+00, 1.3e+01, 1.7e+01))
                .append(String.format(lineFormat, 2.0e+00, 6.0e+00, 1.0e+01, 1.4e+01, 1.8e+01))
                .append(String.format(lineFormat, 3.0e+00, 7.0e+00, 1.1e+01, 1.5e+01, 1.9e+01))
                .append(System.lineSeparator())
                .append("[:, :, 1] =" + System.lineSeparator())
                .append(String.format(lineFormat, 2.0e+01, 2.4e+01, 2.8e+01, 3.2e+01, 3.6e+01))
                .append(String.format(lineFormat, 2.1e+01, 2.5e+01, 2.9e+01, 3.3e+01, 3.7e+01))
                .append(String.format(lineFormat, 2.2e+01, 2.6e+01, 3.0e+01, 3.4e+01, 3.8e+01))
                .append(String.format(lineFormat, 2.3e+01, 2.7e+01, 3.1e+01, 3.5e+01, 3.9e+01))
                .append(System.lineSeparator())
                .append("[:, :, 2] =" + System.lineSeparator())
                .append(String.format(lineFormat, 4.0e+01, 4.4e+01, 4.8e+01, 5.2e+01, 5.6e+01))
                .append(String.format(lineFormat, 4.1e+01, 4.5e+01, 4.9e+01, 5.3e+01, 5.7e+01))
                .append(String.format(lineFormat, 4.2e+01, 4.6e+01, 5.0e+01, 5.4e+01, 5.8e+01))
                .append(String.format(lineFormat, 4.3e+01, 4.7e+01, 5.1e+01, 5.5e+01, 5.9e+01))
                .append(System.lineSeparator())
                .toString();
            assertEquals(expected, str);
        }else if (array.dtype().equals(Complex.class)) {
            String lineFormat = "%10.5e+%10.5ei\t%10.5e+%10.5ei\t%10.5e+%10.5ei\t%10.5e+%10.5ei\t%10.5e+%10.5ei\t%n";
            String expected = new StringBuilder()
                .append(firstLine)
                .append("[:, :, 0] =" + System.lineSeparator())
                .append(String.format(lineFormat, 0.0e+00, 0., 4.0e+00, 0., 8.0e+00, 0., 1.2e+01, 0., 1.6e+01, 0.))
                .append(String.format(lineFormat, 1.0e+00, 0., 5.0e+00, 0., 9.0e+00, 0., 1.3e+01, 0., 1.7e+01, 0.))
                .append(String.format(lineFormat, 2.0e+00, 0., 6.0e+00, 0., 1.0e+01, 0., 1.4e+01, 0., 1.8e+01, 0.))
                .append(String.format(lineFormat, 3.0e+00, 0., 7.0e+00, 0., 1.1e+01, 0., 1.5e+01, 0., 1.9e+01, 0.))
                .append(System.lineSeparator())
                .append("[:, :, 1] =" + System.lineSeparator())
                .append(String.format(lineFormat, 2.0e+01, 0., 2.4e+01, 0., 2.8e+01, 0., 3.2e+01, 0., 3.6e+01, 0.))
                .append(String.format(lineFormat, 2.1e+01, 0., 2.5e+01, 0., 2.9e+01, 0., 3.3e+01, 0., 3.7e+01, 0.))
                .append(String.format(lineFormat, 2.2e+01, 0., 2.6e+01, 0., 3.0e+01, 0., 3.4e+01, 0., 3.8e+01, 0.))
                .append(String.format(lineFormat, 2.3e+01, 0., 2.7e+01, 0., 3.1e+01, 0., 3.5e+01, 0., 3.9e+01, 0.))
                .append(System.lineSeparator())
                .append("[:, :, 2] =" + System.lineSeparator())
                .append(String.format(lineFormat, 4.0e+01, 0., 4.4e+01, 0., 4.8e+01, 0., 5.2e+01, 0., 5.6e+01, 0.))
                .append(String.format(lineFormat, 4.1e+01, 0., 4.5e+01, 0., 4.9e+01, 0., 5.3e+01, 0., 5.7e+01, 0.))
                .append(String.format(lineFormat, 4.2e+01, 0., 4.6e+01, 0., 5.0e+01, 0., 5.4e+01, 0., 5.8e+01, 0.))
                .append(String.format(lineFormat, 4.3e+01, 0., 4.7e+01, 0., 5.1e+01, 0., 5.5e+01, 0., 5.9e+01, 0.))
                .append(System.lineSeparator())
                .toString();
            assertEquals(expected, str);
        } else {
            String lineFormat = "%6d\t%6d\t%6d\t%6d\t%6d\t%n";
            String expected = new StringBuilder()
                    .append(firstLine)
                    .append("[:, :, 0] =" + System.lineSeparator()).append(String.format(lineFormat, 0, 4, 8, 12, 16))
                    .append(String.format(lineFormat, 1, 5, 9, 13, 17))
                    .append(String.format(lineFormat, 2, 6, 10, 14, 18))
                    .append(String.format(lineFormat, 3, 7, 11, 15, 19))
                    .append(System.lineSeparator())
                    .append("[:, :, 1] =" + System.lineSeparator())
                    .append(String.format(lineFormat, 20, 24, 28, 32, 36))
                    .append(String.format(lineFormat, 21, 25, 29, 33, 37))
                    .append(String.format(lineFormat, 22, 26, 30, 34, 38))
                    .append(String.format(lineFormat, 23, 27, 31, 35, 39))
                    .append(System.lineSeparator())
                    .append("[:, :, 2] =" + System.lineSeparator()).append(String.format(lineFormat, 40, 44, 48, 52, 56))
                    .append(String.format(lineFormat, 41, 45, 49, 53, 57))
                    .append(String.format(lineFormat, 42, 46, 50, 54, 58))
                    .append(String.format(lineFormat, 43, 47, 51, 55, 59))
                    .append(System.lineSeparator()).toString();
            assertEquals(expected, str);
        }
    }

    @Test
    void testSelectDims() {
        array = new BasicComplexFloatNDArray(4, 1, 5, 1, 3);
        array.applyWithLinearIndices((value, index) -> wrapToComplex(index));
        NDArray<Complex> slice = array.selectDims(0, 2, 3, 4);
        assertArrayEquals(new int[]{4, 5, 1, 3}, slice.shape());
    }

    @Test
    void testSqueeze() {
        array = new BasicComplexFloatNDArray(4, 1, 5, 1, 3);
        array.applyWithLinearIndices((value, index) -> wrapToComplex(index));
        NDArray<Complex> slice = array.squeeze();
        assertArrayEquals(new int[]{4, 5, 3}, slice.shape());
    }

    @Test
    void testDropDims() {
        array = new BasicComplexFloatNDArray(4, 1, 5, 1, 3);
        array.applyWithLinearIndices((value, index) -> wrapToComplex(index));
        NDArray<Complex> slice = array.dropDims(1, 3);
        assertArrayEquals(new int[]{4, 5, 3}, slice.shape());
    }

    @Test
    void testPermuteDimsTooShortPermutationVector() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0, 2));
        assertEquals(String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2]", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testPermuteDimsTooLongPermutationVector() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0, 2, 1, 4));
        assertEquals(String.format(Errors.PERMUTATOR_SHAPE_MISMATCH, "[0, 2, 1, 4]", "4 × 5 × 3"),
                exception.getMessage());
    }

    @Test
    void testPermuteDimsRepeatedDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0, 1, 1));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[0, 1, 1]", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testPermuteDimsNegativeDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0, -1, 1));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[0, -1, 1]", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testPermuteDimsTooLargeDimension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.permuteDims(0, 3, 1));
        assertEquals(String.format(Errors.INVALID_PERMUTATOR, "[0, 3, 1]", "4 × 5 × 3"), exception.getMessage());
    }

    @Test
    void testConcatenate() {
        NDArray<Complex> array2 = new BasicComplexFloatNDArray(4, 2, 3).fill(1);
        NDArray<Complex> array3 = array.concatenate(1, array2);
        for (int i = 0; i < array.shape(0); i++)
            for (int j = 0; j < array.shape(1); j++)
                for (int k = 0; k < array.shape(2); k++)
                    assertSpecializedEquals(array.get(i, j, k), array3.get(i, j, k));
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = array.shape(1); j < array.shape(1) + array2.shape(1); j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertSpecializedEquals(wrapToComplex(1), array3.get(i, j, k));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Complex> array2 = array.copy().fill(1).slice(":", "1:3", ":");
        NDArray<Complex> array3 = new BasicComplexFloatNDArray(3, 4, 4).permuteDims(2, 1, 0);
        NDArray<Complex> array4 = new BasicComplexFloatNDArray(12).fill(2).reshape(4, 1, 3);
        NDArray<Complex> array5 = array.concatenate(1, array2, array3, array4);
        int start = 0;
        int end = array.shape(1);
        for (int i = 0; i < array.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array.shape(2); k++)
                    assertSpecializedEquals(array.get(i, j, k), array5.get(i, j, k));
        start = end;
        end += array2.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertSpecializedEquals(wrapToComplex(1), array5.get(i, j, k));
        start = end;
        end += array3.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertSpecializedEquals(wrapToComplex(0), array5.get(i, j, k));
        start = end;
        end += array4.shape(1);
        for (int i = 0; i < array2.shape(0); i++)
            for (int j = start; j < end; j++)
                for (int k = 0; k < array2.shape(2); k++)
                    assertSpecializedEquals(wrapToComplex(2), array5.get(i, j, k));
    }

}
