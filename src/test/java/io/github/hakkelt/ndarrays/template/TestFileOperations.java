package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;

import io.github.hakkelt.generator.ClassTemplate;
import io.github.hakkelt.generator.Patterns;
import io.github.hakkelt.generator.Replacements;
import io.github.hakkelt.ndarrays.ComplexNDArray;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.basic.BasicByteNDArray;
import io.github.hakkelt.ndarrays.basic.BasicComplexDoubleNDArray;
import io.github.hakkelt.ndarrays.basic.BasicComplexFloatNDArray;
import io.github.hakkelt.ndarrays.basic.BasicShortNDArray;
import io.github.hakkelt.ndarrays.basic.TestBase;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "TestFileOperations")
class TestFileOperations extends TestBase {
    
    @Test
    @Patterns({"/Byte/", "/Short/"})
    @Replacements({"Short", "Integer"})
    @Replacements({"Integer", "Long"})
    @Replacements({"Long", "Float"})
    @Replacements({"Float", "Double"})
    @Replacements({"Double", "Byte"})
    void testByteToShort() throws IOException {
        File file = new File("ByteArray.dat");
        NDArray<Byte> array = new BasicByteNDArray(4, 5, 3).fillUsingLinearIndices(TestBase::wrapToByte);
        array.writeToFile(file);
        NDArray<Short> array2 = BasicShortNDArray.readFromFile(file);
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
