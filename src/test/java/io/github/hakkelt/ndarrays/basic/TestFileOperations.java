/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestFileOperations.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.hakkelt.ndarrays.ComplexNDArray;
import io.github.hakkelt.ndarrays.NDArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

class TestFileOperations extends TestBase {

    @Test
    void testByteToShort() throws IOException {
        File file = new File("ByteArray.dat");
        NDArray<Byte> array = new BasicByteNDArray(4, 5, 3).fillUsingLinearIndices(TestBase::wrapToByte);
        array.writeToFile(file);
        NDArray<Short> array2 = BasicShortNDArray.readFromFile(file);
        Files.delete(file.toPath());
        assertEquals(array, array2);
    }

    @Test
    void testIntegerToInteger() throws IOException {
        File file = new File("IntegerArray.dat");
        NDArray<Integer> array = new BasicIntegerNDArray(4, 5, 3).fillUsingLinearIndices(TestBase::wrapToInteger);
        array.writeToFile(file);
        NDArray<Integer> array2 = BasicIntegerNDArray.readFromFile(file);
        Files.delete(file.toPath());
        assertEquals(array, array2);
    }

    @Test
    void testIntegerToLong() throws IOException {
        File file = new File("IntegerArray.dat");
        NDArray<Integer> array = new BasicIntegerNDArray(4, 5, 3).fillUsingLinearIndices(TestBase::wrapToInteger);
        array.writeToFile(file);
        NDArray<Long> array2 = BasicLongNDArray.readFromFile(file);
        Files.delete(file.toPath());
        assertEquals(array, array2);
    }

    @Test
    void testLongToFloat() throws IOException {
        File file = new File("LongArray.dat");
        NDArray<Long> array = new BasicLongNDArray(4, 5, 3).fillUsingLinearIndices(TestBase::wrapToLong);
        array.writeToFile(file);
        NDArray<Float> array2 = BasicFloatNDArray.readFromFile(file);
        Files.delete(file.toPath());
        assertEquals(array, array2);
    }

    @Test
    void testFloatToDouble() throws IOException {
        File file = new File("FloatArray.dat");
        NDArray<Float> array = new BasicFloatNDArray(4, 5, 3).fillUsingLinearIndices(TestBase::wrapToFloat);
        array.writeToFile(file);
        NDArray<Double> array2 = BasicDoubleNDArray.readFromFile(file);
        Files.delete(file.toPath());
        assertEquals(array, array2);
    }

    @Test
    void testDoubleToByte() throws IOException {
        File file = new File("DoubleArray.dat");
        NDArray<Double> array = new BasicDoubleNDArray(4, 5, 3).fillUsingLinearIndices(TestBase::wrapToDouble);
        array.writeToFile(file);
        NDArray<Byte> array2 = BasicByteNDArray.readFromFile(file);
        Files.delete(file.toPath());
        assertEquals(array, array2);
    }

    @Test
    void testComplexFloatToComplexDouble() throws IOException {
        File file = new File("ComplexFloatArray.dat");
        ComplexNDArray<Float> array = new BasicComplexFloatNDArray(4, 5, 3)
            .fillUsingLinearIndices(TestBase::wrapToComplex);
        array.writeToFile(file);
        ComplexNDArray<Double> array2 = BasicComplexDoubleNDArray.readFromFile(file);
        Files.delete(file.toPath());
        assertEquals(array, array2);
    }

    @Test
    void testComplexDoubleToComplexFloat() throws IOException {
        File file = new File("ComplexDoubleArray.dat");
        ComplexNDArray<Double> array = new BasicComplexDoubleNDArray(4, 5, 3)
            .fillUsingLinearIndices(TestBase::wrapToComplex);
        array.writeToFile(file);
        ComplexNDArray<Float> array2 = BasicComplexFloatNDArray.readFromFile(file);
        Files.delete(file.toPath());
        assertEquals(array, array2);
    }

}
