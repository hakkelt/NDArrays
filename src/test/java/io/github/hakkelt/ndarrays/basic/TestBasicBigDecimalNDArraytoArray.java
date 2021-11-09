/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestArbitraryPrecisionToArray.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:53 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;

import java.math.*;

import org.junit.jupiter.api.*;

class TestBasicBigDecimalNDArraytoArray extends TestBase {

    NDArray<BigDecimal> arrayOriginal;
    NDArray<BigDecimal> array;
    NDArray<BigDecimal> mask;
    NDArray<BigDecimal> masked;
    NDArray<BigDecimal> pArray;
    NDArray<BigDecimal> reshaped;
    NDArray<BigDecimal> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicBigDecimalNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToBigDecimal(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicBigDecimalNDArray(array.mapWithLinearIndices((value, index) -> wrapToBigDecimal(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    void testToArrayWithArray() {
        BigDecimal[][][] arr = (BigDecimal[][][]) array.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        BigDecimal[][][] arr2 = array.toArray(new BigDecimal[array.shape(0)][array.shape(1)][array.shape(2)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        BigDecimal[][][] arr3 = array.toArray(new BigDecimal[array.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        BigDecimal[][][] arr4 = array.toArray(BigDecimal[][][]::new);
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

    @Test
    void testToArrayWithSlice() {
        BigDecimal[][] arr = (BigDecimal[][]) slice.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr[idx[0]][idx[1]]));

        BigDecimal[][] arr2 = slice.toArray(new BigDecimal[slice.shape(0)][slice.shape(1)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr2[idx[0]][idx[1]]));

        BigDecimal[][] arr3 = slice.toArray(new BigDecimal[slice.shape(0)][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr3[idx[0]][idx[1]]));

        BigDecimal[][] arr4 = slice.toArray(BigDecimal[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr4[idx[0]][idx[1]]));

        BigDecimal[][] arr5 = slice.toArray(BigDecimal[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(wrapToDouble(slice.get(linearIndex[0]++)), wrapToDouble(arr5[idx[0]][idx[1]])));

        Double[][] arr6 = slice.toArray(Double[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(wrapToDouble(slice.get(linearIndex[0]++)), wrapToDouble(arr6[idx[0]][idx[1]])));
    }

    @Test
    void testToArrayWithReshaped() {
        BigDecimal[][] arr = (BigDecimal[][]) reshaped.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr[idx[0]][idx[1]]));

        BigDecimal[][] arr2 = reshaped.toArray(new BigDecimal[reshaped.shape(0)][reshaped.shape(1)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr2[idx[0]][idx[1]]));

        BigDecimal[][] arr3 = reshaped.toArray(new BigDecimal[reshaped.shape(0)][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr3[idx[0]][idx[1]]));

        BigDecimal[][] arr4 = reshaped.toArray(BigDecimal[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr4[idx[0]][idx[1]]));

        BigDecimal[][] arr5 = reshaped.toArray(BigDecimal[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(wrapToDouble(reshaped.get(linearIndex[0]++)), wrapToDouble(arr5[idx[0]][idx[1]])));

        Double[][] arr6 = reshaped.toArray(Double[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(wrapToDouble(reshaped.get(linearIndex[0]++)), wrapToDouble(arr6[idx[0]][idx[1]])));
    }

    @Test
    void testToArrayWithPArray() {
        BigDecimal[][][] arr = (BigDecimal[][][]) pArray.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        BigDecimal[][][] arr2 = pArray.toArray(new BigDecimal[pArray.shape(0)][pArray.shape(1)][pArray.shape(2)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        BigDecimal[][][] arr3 = pArray.toArray(new BigDecimal[pArray.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        BigDecimal[][][] arr4 = pArray.toArray(BigDecimal[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr4[idx[0]][idx[1]][idx[2]]));

        BigDecimal[][][] arr5 = pArray.toArray(BigDecimal[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(wrapToDouble(pArray.get(linearIndex[0]++)), wrapToDouble(arr5[idx[0]][idx[1]][idx[2]])));

        Double[][][] arr6 = pArray.toArray(Double[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(wrapToDouble(pArray.get(linearIndex[0]++)), wrapToDouble(arr6[idx[0]][idx[1]][idx[2]])));
    }

    @Test
    void testToArrayWithMasked() {
        BigDecimal[] arr = (BigDecimal[]) masked.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr[idx[0]]));

        BigDecimal[] arr2 = masked.toArray(new BigDecimal[masked.shape(0)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr2[idx[0]]));

        BigDecimal[] arr3 = masked.toArray(new BigDecimal[0]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr3[idx[0]]));

        BigDecimal[] arr4 = masked.toArray(BigDecimal[]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr4[idx[0]]));

        BigDecimal[] arr5 = masked.toArray(BigDecimal[]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(wrapToDouble(masked.get(linearIndex[0]++)), wrapToDouble(arr5[idx[0]])));

        Double[] arr6 = masked.toArray(Double[]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(wrapToDouble(masked.get(linearIndex[0]++)), wrapToDouble(arr6[idx[0]])));
    }

}
