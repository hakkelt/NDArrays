package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1ToArray")
@Patterns({ "BasicByteNDArray", "/Byte/" })
@Replacements({ "BasicShortNDArray", "Short" })
@Replacements({ "BasicIntegerNDArray", "Integer" })
@Replacements({ "BasicLongNDArray", "Long" })
@Replacements({ "BasicFloatNDArray", "Float" })
@Replacements({ "BasicDoubleNDArray", "Double" })
class TestBasicTypeToArray extends TestBase {
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
        mask = new BasicByteNDArray(array.mapWithLinearIndices((value, index) -> wrapToByte(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    @Patterns({"[][][]", "[idx[0]][idx[1]][idx[2]]", "[array.shape(0)][array.shape(1)][array.shape(2)]", "[array.shape(0)][][]", "array"})
    @Replacements({"[][]", "[idx[0]][idx[1]]", "[array.shape(0)][array.shape(1)]", "[array.shape(0)][]", "slice"})
    @Replacements({"[][]", "[idx[0]][idx[1]]", "[array.shape(0)][array.shape(1)]", "[array.shape(0)][]", "reshaped"})
    @Replacements({"[][][]", "[idx[0]][idx[1]][idx[2]]", "[array.shape(0)][array.shape(1)][array.shape(2)]", "[array.shape(0)][][]", "pArray"})
    @Replacements({"[]", "[idx[0]]", "[array.shape(0)]", "[0]", "masked"})
    void testToArray() {
        Byte[][][] arr = (Byte[][][]) array.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        Byte[][][] arr2 = array.toArray(new Byte[array.shape(0)][array.shape(1)][array.shape(2)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        Byte[][][] arr3 = array.toArray(new Byte[array.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        Byte[][][] arr4 = array.toArray(Byte[][][]::new);
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
        Double[][][] arr = array.toArray(Double[][][]::new);
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(wrapToDouble(array.get(linearIndex[0]++)), arr[idx[0]][idx[1]][idx[2]]));
    }

    @Test
    @Patterns({"[][][]", "[idx[0]][idx[1]][idx[2]]", "[array.shape(0)][array.shape(1)][array.shape(2)]", "[array.shape(0)][][]", "array"})
    @Replacements({"[][]", "[idx[0]][idx[1]]", "[array.shape(0)][array.shape(1)]", "[array.shape(0)][]", "slice"})
    @Replacements({"[][]", "[idx[0]][idx[1]]", "[array.shape(0)][array.shape(1)]", "[array.shape(0)][]", "reshaped"})
    @Replacements({"[][][]", "[idx[0]][idx[1]][idx[2]]", "[array.shape(0)][array.shape(1)][array.shape(2)]", "[array.shape(0)][][]", "pArray"})
    @Replacements({"[]", "[idx[0]]", "[array.shape(0)]", "[0]", "masked"})
    void testToPrimitiveArray() {
        byte[][][] arr = array.toArray(new byte[array.shape(0)][array.shape(1)][array.shape(2)]);
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        byte[][][] arr2 = array.toArray(new byte[array.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        byte[][][] arr3 = array.toArray(byte[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        double[][][] arr4 = array.toArray(double[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(wrapToDouble(array.get(linearIndex[0]++)), arr4[idx[0]][idx[1]][idx[2]]));
    }
}
