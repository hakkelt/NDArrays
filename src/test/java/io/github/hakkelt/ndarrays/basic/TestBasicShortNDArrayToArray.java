/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestBasicTypeToArray.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:53 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;

import org.junit.jupiter.api.*;

class TestBasicShortNDArrayToArray extends TestBase {

    NDArray<Short> arrayOriginal;
    NDArray<Short> array;
    NDArray<Short> mask;
    NDArray<Short> masked;
    NDArray<Short> pArray;
    NDArray<Short> reshaped;
    NDArray<Short> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicShortNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToShort(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicShortNDArray(array.mapWithLinearIndices((value, index) -> wrapToShort(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    void testToArrayWithArray() {
        Short[][][] arr = (Short[][][]) array.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        Short[][][] arr2 = array.toArray(new Short[array.shape(0)][array.shape(1)][array.shape(2)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        Short[][][] arr3 = array.toArray(new Short[array.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        Short[][][] arr4 = array.toArray(Short[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr4[idx[0]][idx[1]][idx[2]]));
    }

    @Test
    void testToArrayWithSlice() {
        Short[][] arr = (Short[][]) slice.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr[idx[0]][idx[1]]));

        Short[][] arr2 = slice.toArray(new Short[slice.shape(0)][slice.shape(1)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr2[idx[0]][idx[1]]));

        Short[][] arr3 = slice.toArray(new Short[slice.shape(0)][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr3[idx[0]][idx[1]]));

        Short[][] arr4 = slice.toArray(Short[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr4[idx[0]][idx[1]]));
    }

    @Test
    void testToArrayWithReshaped() {
        Short[][] arr = (Short[][]) reshaped.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr[idx[0]][idx[1]]));

        Short[][] arr2 = reshaped.toArray(new Short[reshaped.shape(0)][reshaped.shape(1)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr2[idx[0]][idx[1]]));

        Short[][] arr3 = reshaped.toArray(new Short[reshaped.shape(0)][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr3[idx[0]][idx[1]]));

        Short[][] arr4 = reshaped.toArray(Short[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr4[idx[0]][idx[1]]));
    }

    @Test
    void testToArrayWithPArray() {
        Short[][][] arr = (Short[][][]) pArray.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        Short[][][] arr2 = pArray.toArray(new Short[pArray.shape(0)][pArray.shape(1)][pArray.shape(2)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        Short[][][] arr3 = pArray.toArray(new Short[pArray.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        Short[][][] arr4 = pArray.toArray(Short[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr4[idx[0]][idx[1]][idx[2]]));
    }

    @Test
    void testToArrayWithMasked() {
        Short[] arr = (Short[]) masked.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr[idx[0]]));

        Short[] arr2 = masked.toArray(new Short[masked.shape(0)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr2[idx[0]]));

        Short[] arr3 = masked.toArray(new Short[0]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr3[idx[0]]));

        Short[] arr4 = masked.toArray(Short[]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr4[idx[0]]));
    }

    @Test
    void testToArrayDifferentTypeWithArray() {
        Double[][][] arr = array.toArray(Double[][][]::new);
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(wrapToDouble(array.get(linearIndex[0]++)), arr[idx[0]][idx[1]][idx[2]]));
    }

    @Test
    void testToArrayDifferentTypeWithSlice() {
        Double[][] arr = slice.toArray(Double[][]::new);
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(wrapToDouble(slice.get(linearIndex[0]++)), arr[idx[0]][idx[1]]));
    }

    @Test
    void testToArrayDifferentTypeWithReshaped() {
        Double[][] arr = reshaped.toArray(Double[][]::new);
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(wrapToDouble(reshaped.get(linearIndex[0]++)), arr[idx[0]][idx[1]]));
    }

    @Test
    void testToArrayDifferentTypeWithPArray() {
        Double[][][] arr = pArray.toArray(Double[][][]::new);
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(wrapToDouble(pArray.get(linearIndex[0]++)), arr[idx[0]][idx[1]][idx[2]]));
    }

    @Test
    void testToArrayDifferentTypeWithMasked() {
        Double[] arr = masked.toArray(Double[]::new);
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(wrapToDouble(masked.get(linearIndex[0]++)), arr[idx[0]]));
    }

    @Test
    void testToPrimitiveArrayWithArray() {
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

    @Test
    void testToPrimitiveArrayWithSlice() {
        byte[][] arr = slice.toArray(new byte[slice.shape(0)][slice.shape(1)]);
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr[idx[0]][idx[1]]));

        byte[][] arr2 = slice.toArray(new byte[slice.shape(0)][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr2[idx[0]][idx[1]]));

        byte[][] arr3 = slice.toArray(byte[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr3[idx[0]][idx[1]]));

        double[][] arr4 = slice.toArray(double[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(wrapToDouble(slice.get(linearIndex[0]++)), arr4[idx[0]][idx[1]]));
    }

    @Test
    void testToPrimitiveArrayWithReshaped() {
        byte[][] arr = reshaped.toArray(new byte[reshaped.shape(0)][reshaped.shape(1)]);
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr[idx[0]][idx[1]]));

        byte[][] arr2 = reshaped.toArray(new byte[reshaped.shape(0)][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr2[idx[0]][idx[1]]));

        byte[][] arr3 = reshaped.toArray(byte[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr3[idx[0]][idx[1]]));

        double[][] arr4 = reshaped.toArray(double[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(wrapToDouble(reshaped.get(linearIndex[0]++)), arr4[idx[0]][idx[1]]));
    }

    @Test
    void testToPrimitiveArrayWithPArray() {
        byte[][][] arr = pArray.toArray(new byte[pArray.shape(0)][pArray.shape(1)][pArray.shape(2)]);
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        byte[][][] arr2 = pArray.toArray(new byte[pArray.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        byte[][][] arr3 = pArray.toArray(byte[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        double[][][] arr4 = pArray.toArray(double[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(wrapToDouble(pArray.get(linearIndex[0]++)), arr4[idx[0]][idx[1]][idx[2]]));
    }

    @Test
    void testToPrimitiveArrayWithMasked() {
        byte[] arr = masked.toArray(new byte[masked.shape(0)]);
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr[idx[0]]));

        byte[] arr2 = masked.toArray(new byte[0]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr2[idx[0]]));

        byte[] arr3 = masked.toArray(byte[]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr3[idx[0]]));

        double[] arr4 = masked.toArray(double[]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(wrapToDouble(masked.get(linearIndex[0]++)), arr4[idx[0]]));
    }

}
