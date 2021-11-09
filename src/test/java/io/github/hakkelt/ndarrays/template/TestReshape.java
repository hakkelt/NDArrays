package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;
import io.github.hakkelt.ndarrays.internal.*;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1Reshape")
@Patterns({ "BasicByteNDArray", "NDArrayView<Byte>", "/Byte/", "BasicLongNDArray", "RealNDArrayReshapeView", "%6d" })
@Replacements({ "BasicShortNDArray", "NDArrayView<Short>", "Short", "BasicLongNDArray", "RealNDArrayReshapeView", "%6d" })
@Replacements({ "BasicIntegerNDArray", "NDArrayView<Integer>", "Integer", "BasicLongNDArray", "RealNDArrayReshapeView", "%6d" })
@Replacements({ "BasicLongNDArray", "NDArrayView<Long>", "Long", "BasicShortNDArray", "RealNDArrayReshapeView", "%6d" })
@Replacements({ "BasicFloatNDArray", "NDArrayView<Float>", "Float", "BasicLongNDArray", "RealNDArrayReshapeView", "%10.5e" })
@Replacements({ "BasicDoubleNDArray", "NDArrayView<Double>", "Double", "BasicLongNDArray", "RealNDArrayReshapeView", "%10.5e" })
@Replacements(value = { "BasicBigIntegerNDArray", "NDArrayView<BigInteger>", "BigInteger", "BasicLongNDArray", "RealNDArrayReshapeView",
        "%6d" }, extraImports = "java.math.BigInteger")
@Replacements(value = { "BasicBigDecimalNDArray", "NDArrayView<BigDecimal>", "BigDecimal", "BasicLongNDArray", "RealNDArrayReshapeView",
        "%10.5e" }, extraImports = "java.math.BigDecimal")
@Replacements(value = { "BasicComplexFloatNDArray", "NDArrayView<Complex Float>", "Complex", "BasicComplexDoubleNDArray", "ComplexNDArrayReshapeView",
        "%10.5e+%10.5ei" }, extraImports = "org.apache.commons.math3.complex.Complex")
@Replacements(value = { "BasicComplexDoubleNDArray", "NDArrayView<Complex Double>", "Complex", "BasicComplexFloatNDArray", "ComplexNDArrayReshapeView",
        "%10.5e+%10.5ei" }, extraImports = "org.apache.commons.math3.complex.Complex")
class TestReshape extends TestBase {
    NDArray<Byte> arrayOriginal;
    NDArray<Byte> array;
    NDArray<Byte> mask;
    NDArray<Byte> masked;
    NDArray<Byte> pArray;
    NDArray<Byte> reshaped;
    NDArray<Byte> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicByteNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToByte(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = array.mapWithLinearIndices((value, index) -> wrapToByte(index % 2));
        masked = array.mask(mask);
    }

    @Test
    void testNotEqual() {
        assertNotEquals(reshaped, new int[0]); // NOSONAR
        assertNotEquals(reshaped, new BasicComplexFloatNDArray(reshaped.shape()));
        assertNotEquals(reshaped, new BasicByteNDArray(10, 2, 3));
        assertNotEquals(reshaped, new BasicByteNDArray(6, 10));
    }

    @Test
    void testEqualWithSameType() {
        NDArray<Byte> reshaped2 = array.reshape(20, 3);
        assertEquals(reshaped, reshaped2);
        NDArray<Byte> reshaped3 = array.copy().reshape(20, 3);
        assertEquals(reshaped, reshaped3);
    }

    @Test
    void testEqualWithDifferentType() {
        NDArray<?> array2 = new BasicLongNDArray(reshaped);
        assertEquals(reshaped, array2);
        array2.set(0, 5);
        assertNotEquals(reshaped, array2);
    }

    @Test
    void testToString() {
        String str = reshaped.toString();
        assertEquals(array.getNamePrefix() + " NDArrayView<Byte>(20 × 3)", str);
    }

    @Test
    void testContentToString() {
        String str = reshaped.contentToString();
        String lineFormat = "%6d\t%6d\t%6d\t%n";
        StringBuilder sb = new StringBuilder();
        sb.append(array.getNamePrefix() + " NDArrayView<Byte>(20 × 3)" + System.lineSeparator());
        for (int i = 0; i < 20; i++)
            if (reshaped.dtype() == Complex.class)
                sb.append(String.format(lineFormat, wrapToDouble(i), 0., wrapToDouble(i + 20), 0., wrapToDouble(i + 40), 0.));
            else
                sb.append(String.format(lineFormat, wrapToByte(i), wrapToByte(i + 20), wrapToByte(i + 40)));
        String expected = sb.toString();
        assertEquals(expected, str);
    }

    @Test
    void testConcatenate() {
        NDArray<Byte> array2 = new BasicByteNDArray(13, 3).fill(1);
        NDArray<Byte> array3 = reshaped.concatenate(0, array2);
        for (int i = 0; i < reshaped.shape(0); i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertEquals(reshaped.get(i, j), array3.get(i, j));
        for (int i = reshaped.shape(0); i < reshaped.shape(0) + array2.shape(0); i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertSpecializedEquals(wrapToByte(1), array3.get(i, j));
    }

    @Test
    void testConcatenateMultiple() {
        NDArray<Byte> array2 = reshaped.copy().fill(1).slice(":10", ":");
        NDArray<Byte> array3 = new BasicByteNDArray(3, 5).permuteDims(1, 0);
        NDArray<Byte> array4 = new BasicByteNDArray(3, 6).fill(2).reshape(6, 3);
        NDArray<Byte> array5 = reshaped.concatenate(0, array2, array3, array4);
        int start = 0;
        int end = reshaped.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertEquals(reshaped.get(i, j), array5.get(i, j));
        start = end;
        end += array2.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertSpecializedEquals(wrapToByte(1), array5.get(i, j));
        start = end;
        end += array3.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertSpecializedEquals(wrapToByte(0), array5.get(i, j));
        start = end;
        end += array4.shape(0);
        for (int i = start; i < end; i++)
            for (int j = 0; j < reshaped.shape(1); j++)
                assertSpecializedEquals(wrapToByte(2), array5.get(i, j));
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testDimensionMismatch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> array.reshape(4, 10));
        String shapeString = String.join(" × ", IntStream.of(array.shape()).mapToObj(item -> "" + item).collect(Collectors.toList()));
        assertEquals(String.format(Errors.RESHAPE_LENGTH_MISMATCH, shapeString, "4 × 10"), exception.getMessage());
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testIdentityReshape() {
        assertSame(array, array.reshape(array.shape()));
    }

    @Test
    @Patterns({"array", "20, 3"})
    @Replacements({"slice", "9"})
    @Replacements({"reshaped", "3, 20"})
    @Replacements({"pArray", "20, 3"})
    @Replacements({"masked", "3, 10"})
    void testReshaped() {
        reshaped = array.reshape(20, 3);
        reshaped.forEachWithLinearIndices((value, index) -> assertSpecializedEquals(value, array.get(index)));
        reshaped.fill(0);
        array.forEach(value -> assertSpecializedEquals(wrapToByte(0), value));
    }

    @Test
    void testReshapeReshaped() {
        NDArray<Byte> reshaped2 = reshaped.reshape(4, 15);
        array.forEachWithLinearIndices((value, index) -> assertEquals(value, reshaped2.get(index)));
        assertArrayEquals(new int[]{ 4, 15 }, reshaped2.shape());
        assertSame(array, ((RealNDArrayReshapeView<?>) reshaped2).getParent());
    }
    
}
