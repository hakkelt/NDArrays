package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;
import io.github.hakkelt.ndarrays.internal.Errors;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1ToArray")
@Patterns({ "BasicComplexFloatNDArray", "Complex", "Float" })
@Replacements({ "BasicComplexDoubleNDArray", "Complex", "Double" })
class TestComplexToArray extends TestBase {
    ComplexNDArray<Float> arrayOriginal;
    ComplexNDArray<Float> array;
    ComplexNDArray<Float> mask;
    ComplexNDArray<Float> masked;
    ComplexNDArray<Float> pArray;
    ComplexNDArray<Float> reshaped;
    ComplexNDArray<Float> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicComplexFloatNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToComplex(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicComplexFloatNDArray(array.mapWithLinearIndices((value, index) -> wrapToComplex(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    @Patterns({"[][][]", "[idx[0]][idx[1]][idx[2]]", "[array.shape(0)][array.shape(1)][array.shape(2)]", "[array.shape(0)][][]", "array"})
    @Replacements({"[][]", "[idx[0]][idx[1]]", "[array.shape(0)][array.shape(1)]", "[array.shape(0)][]", "slice"})
    @Replacements({"[][]", "[idx[0]][idx[1]]", "[array.shape(0)][array.shape(1)]", "[array.shape(0)][]", "reshaped"})
    @Replacements({"[][][]", "[idx[0]][idx[1]][idx[2]]", "[array.shape(0)][array.shape(1)][array.shape(2)]", "[array.shape(0)][][]", "pArray"})
    @Replacements({"[]", "[idx[0]]", "[array.shape(0)]", "[0]", "masked"})
    void testToArray() {
        Complex[][][] arr = (Complex[][][]) array.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        Complex[][][] arr2 = array.toArray(new Complex[array.shape(0)][array.shape(1)][array.shape(2)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        Complex[][][] arr3 = array.toArray(new Complex[array.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        Complex[][][] arr4 = array.toArray(Complex[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr4[idx[0]][idx[1]][idx[2]]));
    }

    @Test
    @Patterns({"[][][]", "[idx[0]][idx[1]][idx[2]]", "[array.shape(0)][array.shape(1)][array.shape(2)]", "[array.shape(0)][][]", "array"})
    @Replacements({"[][]", "[idx[0]][idx[1]]", "[array.shape(0)][array.shape(1)]", "[array.shape(0)][]", "slice"})
    @Replacements({"[][]", "[idx[0]][idx[1]]", "[array.shape(0)][array.shape(1)]", "[array.shape(0)][]", "reshaped"})
    @Replacements({"[][][]", "[idx[0]][idx[1]][idx[2]]", "[array.shape(0)][array.shape(1)][array.shape(2)]", "[array.shape(0)][][]", "pArray"})
    @Replacements({"[]", "[idx[0]]", "[array.shape(0)]", "[0]", "masked"})
    void testToArrayDifferentType() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> array.toArray(Double[][][]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
        exception = assertThrows(ArrayStoreException.class, () -> array.toArray(byte[][][]::new));
        assertEquals(Errors.TOARRAY_COMPLEX_TO_REAL_ARRAY, exception.getMessage());
    }

}
