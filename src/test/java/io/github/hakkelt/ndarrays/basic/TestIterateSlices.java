package io.github.hakkelt.ndarrays.basic;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.Test;

import io.github.hakkelt.ndarrays.ComplexNDArray;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.NDArrayUtils;

class TestIterateSlices {
    
    @Test
    void testApplyWithSlicesInplace() {
        NDArray<Float> data = new BasicFloatNDArray(5, 7, 9, 11).fillUsingLinearIndices(i -> (float) i);
        data.applyOnSlices((slice, idx) -> {
            assertEquals(data.slice(idx[0], ":", idx[1], ":"), slice);
            return slice.apply(value -> value * value);
        }, 0, 2);
        data.forEachWithLinearIndices((value, i) -> assertEquals(i * i, value));
    }

    @Test
    void testApplyWithSlices() {
        NDArray<Double> data = new BasicDoubleNDArray(5, 7, 9, 11).fillUsingLinearIndices(i -> (double) i);
        data.applyOnSlices((slice, idx) -> {
            assertEquals(data.slice(":", idx[0], idx[1], ":"), slice);
            return slice.map(value -> value * value);
        }, 1, 2);
        data.forEachWithLinearIndices((value, i) -> assertEquals(i * i, value));
    }

    @Test
    void testMapWithSlicesInplace() {
        NDArray<Integer> data = new BasicIntegerNDArray(5, 7, 9, 11).fillUsingLinearIndices(i -> i);
        NDArray<Integer> result = data.mapOnSlices((slice, idx) -> {
            assertEquals(data.slice(idx[0], ":", ":", idx[1]), slice);
            return slice.apply(value -> value * value);
        }, 0, 3);
        result.forEachWithLinearIndices((value, i) -> assertEquals(i * i, value));
    }

    @Test
    void testMapWithSlices() {
        NDArray<Long> data = new BasicLongNDArray(5, 7, 9, 11).fillUsingLinearIndices(i -> (long) i);
        NDArray<Long> result = data.mapOnSlices((slice, idx) -> {
            assertEquals(data.slice(":", ":", idx[0], idx[1]), slice);
            return slice.map(value -> value * value);
        }, 2, 3);
        result.forEachWithLinearIndices((value, i) -> assertEquals(i * i, value));
    }

    @Test
    void testApplyWithSlicesInplaceOnSlice() {
        NDArray<Short> data = new BasicShortNDArray(5, 7, 9, 11).fillUsingLinearIndices(i -> (short) i);
        NDArray<Short> slice = data.slice("1:4", ":", 3, ":");
        slice.applyOnSlices((slice2, idx) -> {
            assertEquals(slice.slice(idx[0], ":", idx[1]), slice2);
            return slice2.apply(value -> (short) (value * value));
        }, 0, 2);
        int[] multipliers = NDArrayUtils.calculateMultipliers(data.shape());
        data.forEachWithCartesianIndices((value, idx) -> {
            int i = NDArrayUtils.cartesianIndicesToLinearIndex(idx, multipliers);
            assertEquals((short) (idx[0] >= 1 && idx[0] < 4 && idx[2] == 3 ? i * i : i), value);
        });
    }

    @Test
    void testApplyWithSlicesOnReshaped() {
        NDArray<BigDecimal> data = new BasicBigDecimalNDArray(5, 7, 9, 11).fillUsingLinearIndices(i -> BigDecimal.valueOf(i));
        NDArray<BigDecimal> reshaped = data.reshape(5, 63, 11);
        reshaped.applyOnSlices((slice, idx) -> {
            assertEquals(reshaped.slice(":", idx[0], ":"), slice);
            return slice.map(value -> value.multiply(value));
        }, 1);
        data.forEachWithLinearIndices((value, i) -> assertEquals(BigDecimal.valueOf(i * i), value));
    }

    @Test
    void testMapWithSlicesInplaceOnTransposed() {
        ComplexNDArray<Float> data = new BasicComplexFloatNDArray(5, 9, 7, 11)
            .permuteDims(0, 2, 1, 3)
            .fillUsingLinearIndices(i -> new Complex(i));
        ComplexNDArray<Float> result = data.mapOnComplexSlices((slice, idx) -> {
            assertEquals(data.slice(idx[0], ":", ":", idx[1]), slice);
            return slice.apply(value -> value.multiply(value));
        }, 0, 3);
        result.forEachWithLinearIndices((value, i) -> assertEquals(new Complex(i * i), value));
    }

    @Test
    void testMapWithSlicesOnMasked() {
        ComplexNDArray<Float> data = new BasicComplexFloatNDArray(5, 7, 9, 11)
            .fillUsingLinearIndices(i -> new Complex(i));
        ComplexNDArray<Float> masked = data.maskWithLinearIndices((value, i) -> i % 2 == 0);
        ComplexNDArray<Float> result = masked.mapOnComplexSlices((slice, idx) -> {
            assertEquals(masked.slice(idx[0]), slice);
            return slice.map(value -> value.multiply(value));
        }, 0);
        assertArrayEquals(masked.shape(), result.shape());
        assertEquals(result, masked.multiply(masked));
        data.forEachWithLinearIndices((value, i) -> assertEquals(new Complex(i), value));
    }
}
