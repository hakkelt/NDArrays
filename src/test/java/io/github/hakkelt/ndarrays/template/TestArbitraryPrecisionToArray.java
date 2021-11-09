package io.github.hakkelt.ndarrays.template;

import static org.junit.jupiter.api.Assertions.*;

import java.math.*;

import org.junit.jupiter.api.*;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1toArray")
@Patterns({ "BasicBigIntegerNDArray", "/BigInteger/" })
@Replacements({ "BasicBigDecimalNDArray", "BigDecimal" })
class TestArbitraryPrecisionToArrayTemplate extends TestBase {
    NDArray<BigInteger> arrayOriginal;
    NDArray<BigInteger> array;
    NDArray<BigInteger> mask;
    NDArray<BigInteger> masked;
    NDArray<BigInteger> pArray;
    NDArray<BigInteger> reshaped;
    NDArray<BigInteger> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicBigIntegerNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToBigInteger(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicBigIntegerNDArray(array.mapWithLinearIndices((value, index) -> wrapToBigInteger(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    @Patterns({"[][][]", "[idx[0]][idx[1]][idx[2]]", "[array.shape(0)][array.shape(1)][array.shape(2)]", "[array.shape(0)][][]", "array"})
    @Replacements({"[][]", "[idx[0]][idx[1]]", "[array.shape(0)][array.shape(1)]", "[array.shape(0)][]", "slice"})
    @Replacements({"[][]", "[idx[0]][idx[1]]", "[array.shape(0)][array.shape(1)]", "[array.shape(0)][]", "reshaped"})
    @Replacements({"[][][]", "[idx[0]][idx[1]][idx[2]]", "[array.shape(0)][array.shape(1)][array.shape(2)]", "[array.shape(0)][][]", "pArray"})
    @Replacements({"[]", "[idx[0]]", "[array.shape(0)]", "[0]", "masked"})
    void testToArray() {
        BigInteger[][][] arr = (BigInteger[][][]) array.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        BigInteger[][][] arr2 = array.toArray(new BigInteger[array.shape(0)][array.shape(1)][array.shape(2)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        BigInteger[][][] arr3 = array.toArray(new BigInteger[array.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        BigInteger[][][] arr4 = array.toArray(BigInteger[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr4[idx[0]][idx[1]][idx[2]]));

        BigDecimal[][][] arr5 = array.toArray(BigDecimal[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(wrapToDouble(array.get(linearIndex[0]++)), wrapToDouble(arr5[idx[0]][idx[1]][idx[2]])));

        Double[][][] arr6 = array.toArray(Double[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(wrapToDouble(array.get(linearIndex[0]++)), wrapToDouble(arr6[idx[0]][idx[1]][idx[2]])));
    }

}
