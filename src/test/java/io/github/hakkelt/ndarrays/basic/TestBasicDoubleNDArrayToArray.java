/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestBasicTypeToArray.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.*;

import io.github.hakkelt.ndarrays.*;

import org.junit.jupiter.api.*;

class TestBasicDoubleNDArrayToArray extends TestBase {

    NDArray<Double> arrayOriginal;
    NDArray<Double> array;
    NDArray<Double> mask;
    NDArray<Double> masked;
    NDArray<Double> pArray;
    NDArray<Double> reshaped;
    NDArray<Double> slice;

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicDoubleNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToDouble(index));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = new BasicDoubleNDArray(array.mapWithLinearIndices((value, index) -> wrapToDouble(index.intValue() % 2)));
        masked = array.mask(mask);
    }

    @Test
    void testToArrayWithArray() {
        Double[][][] arr = (Double[][][]) array.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        Double[][][] arr2 = array.toArray(new Double[array.shape(0)][array.shape(1)][array.shape(2)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        Double[][][] arr3 = array.toArray(new Double[array.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        Double[][][] arr4 = array.toArray(Double[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(array.shape()).forEach(idx ->
            assertEquals(array.get(linearIndex[0]++), arr4[idx[0]][idx[1]][idx[2]]));
    }

    @Test
    void testToArrayWithSlice() {
        Double[][] arr = (Double[][]) slice.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr[idx[0]][idx[1]]));

        Double[][] arr2 = slice.toArray(new Double[slice.shape(0)][slice.shape(1)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr2[idx[0]][idx[1]]));

        Double[][] arr3 = slice.toArray(new Double[slice.shape(0)][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr3[idx[0]][idx[1]]));

        Double[][] arr4 = slice.toArray(Double[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(slice.shape()).forEach(idx ->
            assertEquals(slice.get(linearIndex[0]++), arr4[idx[0]][idx[1]]));
    }

    @Test
    void testToArrayWithReshaped() {
        Double[][] arr = (Double[][]) reshaped.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr[idx[0]][idx[1]]));

        Double[][] arr2 = reshaped.toArray(new Double[reshaped.shape(0)][reshaped.shape(1)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr2[idx[0]][idx[1]]));

        Double[][] arr3 = reshaped.toArray(new Double[reshaped.shape(0)][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr3[idx[0]][idx[1]]));

        Double[][] arr4 = reshaped.toArray(Double[][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(reshaped.shape()).forEach(idx ->
            assertEquals(reshaped.get(linearIndex[0]++), arr4[idx[0]][idx[1]]));
    }

    @Test
    void testToArrayWithPArray() {
        Double[][][] arr = (Double[][][]) pArray.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr[idx[0]][idx[1]][idx[2]]));

        Double[][][] arr2 = pArray.toArray(new Double[pArray.shape(0)][pArray.shape(1)][pArray.shape(2)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr2[idx[0]][idx[1]][idx[2]]));

        Double[][][] arr3 = pArray.toArray(new Double[pArray.shape(0)][][]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr3[idx[0]][idx[1]][idx[2]]));

        Double[][][] arr4 = pArray.toArray(Double[][][]::new);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(pArray.shape()).forEach(idx ->
            assertEquals(pArray.get(linearIndex[0]++), arr4[idx[0]][idx[1]][idx[2]]));
    }

    @Test
    void testToArrayWithMasked() {
        Double[] arr = (Double[]) masked.toArray();
        int[] linearIndex = {0};
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr[idx[0]]));

        Double[] arr2 = masked.toArray(new Double[masked.shape(0)]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr2[idx[0]]));

        Double[] arr3 = masked.toArray(new Double[0]);
        linearIndex[0] = 0;
        NDArray.streamCartesianIndices(masked.shape()).forEach(idx ->
            assertEquals(masked.get(linearIndex[0]++), arr3[idx[0]]));

        Double[] arr4 = masked.toArray(Double[]::new);
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
