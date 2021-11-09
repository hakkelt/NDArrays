/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestArithmetics.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:53 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import io.github.hakkelt.ndarrays.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.*;

class TestBasicDoubleNDArrayArithmetics extends TestBase {

    NDArray<Double> arrayOriginal;
    NDArray<Double> array;
    NDArray<Double> mask;
    NDArray<Double> masked;
    NDArray<Double> pArray;
    NDArray<Double> reshaped;
    NDArray<Double> slice;

    double getDelta() {
        return 1e-15;
    }

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicDoubleNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToDouble(index + 1));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = array.mapWithLinearIndices((value, index) -> wrapToDouble(index % 2));
        masked = array.mask(mask);
    }

    @Test
    void testAddArrayToArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> rhs = array;
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddSliceToArray() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice.copy();
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddSliceToSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddReshapedToArray() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped.copy();
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddReshapedToReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddPArrayToArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray.copy();
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddPArrayToPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddMaskedToArray() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked.copy();
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddMaskedToMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> rhs = array;
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractSliceFromArray() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice.copy();
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractSliceFromSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractReshapedFromArray() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped.copy();
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractReshapedFromReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractPArrayFromArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray.copy();
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractPArrayFromPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractMaskedFromArray() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked.copy();
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractMaskedFromMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> rhs = array;
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplySliceByArray() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice.copy();
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayBySlice() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplySliceBySlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyReshapedByArray() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped.copy();
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByReshaped() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyReshapedByReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyPArrayByArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray.copy();
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByPArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyPArrayByPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyMaskedByArray() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked.copy();
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByMasked() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyMaskedByMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> rhs = array;
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideSliceByArray() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice.copy();
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayBySlice() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideSliceBySlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideReshapedByArray() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped.copy();
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByReshaped() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideReshapedByReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDividePArrayByArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray.copy();
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByPArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDividePArrayByPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideMaskedByArray() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked.copy();
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByMasked() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideMaskedByMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddScalarToArray() {
        try {
            NDArray<Double> result = array.add((byte) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(add(array.get(i), 4), result.get(i), getDelta());
            result = array.add((short) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(add(array.get(i), 4), result.get(i), getDelta());
            result = array.add(4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(add(array.get(i), 4), result.get(i), getDelta());
            result = array.add((long) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(add(array.get(i), 4), result.get(i), getDelta());
            result = array.add((float) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(add(array.get(i), 4), result.get(i), getDelta());
            result = array.add(4.);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(add(array.get(i), 4), result.get(i), getDelta());
            result = array.add(BigInteger.valueOf(4));
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(add(array.get(i), 4), result.get(i), getDelta());
            result = array.add(new BigDecimal(4));
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(add(array.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToSlice() {
        try {
            NDArray<Double> result = slice.add((byte) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(add(slice.get(i), 4), result.get(i), getDelta());
            result = slice.add((short) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(add(slice.get(i), 4), result.get(i), getDelta());
            result = slice.add(4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(add(slice.get(i), 4), result.get(i), getDelta());
            result = slice.add((long) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(add(slice.get(i), 4), result.get(i), getDelta());
            result = slice.add((float) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(add(slice.get(i), 4), result.get(i), getDelta());
            result = slice.add(4.);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(add(slice.get(i), 4), result.get(i), getDelta());
            result = slice.add(BigInteger.valueOf(4));
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(add(slice.get(i), 4), result.get(i), getDelta());
            result = slice.add(new BigDecimal(4));
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(add(slice.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToReshaped() {
        try {
            NDArray<Double> result = reshaped.add((byte) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(add(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.add((short) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(add(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.add(4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(add(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.add((long) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(add(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.add((float) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(add(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.add(4.);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(add(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.add(BigInteger.valueOf(4));
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(add(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.add(new BigDecimal(4));
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(add(reshaped.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToPArray() {
        try {
            NDArray<Double> result = pArray.add((byte) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(add(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.add((short) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(add(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.add(4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(add(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.add((long) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(add(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.add((float) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(add(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.add(4.);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(add(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.add(BigInteger.valueOf(4));
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(add(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.add(new BigDecimal(4));
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(add(pArray.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToMasked() {
        try {
            NDArray<Double> result = masked.add((byte) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(add(masked.get(i), 4), result.get(i), getDelta());
            result = masked.add((short) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(add(masked.get(i), 4), result.get(i), getDelta());
            result = masked.add(4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(add(masked.get(i), 4), result.get(i), getDelta());
            result = masked.add((long) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(add(masked.get(i), 4), result.get(i), getDelta());
            result = masked.add((float) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(add(masked.get(i), 4), result.get(i), getDelta());
            result = masked.add(4.);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(add(masked.get(i), 4), result.get(i), getDelta());
            result = masked.add(BigInteger.valueOf(4));
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(add(masked.get(i), 4), result.get(i), getDelta());
            result = masked.add(new BigDecimal(4));
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(add(masked.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromArray() {
        try {
            NDArray<Double> result = array.subtract((byte) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(subtract(array.get(i), 4), result.get(i), getDelta());
            result = array.subtract((short) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(subtract(array.get(i), 4), result.get(i), getDelta());
            result = array.subtract(4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(subtract(array.get(i), 4), result.get(i), getDelta());
            result = array.subtract((long) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(subtract(array.get(i), 4), result.get(i), getDelta());
            result = array.subtract((float) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(subtract(array.get(i), 4), result.get(i), getDelta());
            result = array.subtract(4.);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(subtract(array.get(i), 4), result.get(i), getDelta());
            result = array.subtract(BigInteger.valueOf(4));
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(subtract(array.get(i), 4), result.get(i), getDelta());
            result = array.subtract(new BigDecimal(4));
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(subtract(array.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromSlice() {
        try {
            NDArray<Double> result = slice.subtract((byte) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(subtract(slice.get(i), 4), result.get(i), getDelta());
            result = slice.subtract((short) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(subtract(slice.get(i), 4), result.get(i), getDelta());
            result = slice.subtract(4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(subtract(slice.get(i), 4), result.get(i), getDelta());
            result = slice.subtract((long) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(subtract(slice.get(i), 4), result.get(i), getDelta());
            result = slice.subtract((float) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(subtract(slice.get(i), 4), result.get(i), getDelta());
            result = slice.subtract(4.);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(subtract(slice.get(i), 4), result.get(i), getDelta());
            result = slice.subtract(BigInteger.valueOf(4));
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(subtract(slice.get(i), 4), result.get(i), getDelta());
            result = slice.subtract(new BigDecimal(4));
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(subtract(slice.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromReshaped() {
        try {
            NDArray<Double> result = reshaped.subtract((byte) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(subtract(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.subtract((short) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(subtract(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.subtract(4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(subtract(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.subtract((long) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(subtract(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.subtract((float) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(subtract(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.subtract(4.);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(subtract(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.subtract(BigInteger.valueOf(4));
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(subtract(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.subtract(new BigDecimal(4));
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(subtract(reshaped.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromPArray() {
        try {
            NDArray<Double> result = pArray.subtract((byte) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(subtract(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.subtract((short) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(subtract(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.subtract(4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(subtract(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.subtract((long) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(subtract(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.subtract((float) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(subtract(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.subtract(4.);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(subtract(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.subtract(BigInteger.valueOf(4));
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(subtract(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.subtract(new BigDecimal(4));
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(subtract(pArray.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromMasked() {
        try {
            NDArray<Double> result = masked.subtract((byte) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(subtract(masked.get(i), 4), result.get(i), getDelta());
            result = masked.subtract((short) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(subtract(masked.get(i), 4), result.get(i), getDelta());
            result = masked.subtract(4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(subtract(masked.get(i), 4), result.get(i), getDelta());
            result = masked.subtract((long) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(subtract(masked.get(i), 4), result.get(i), getDelta());
            result = masked.subtract((float) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(subtract(masked.get(i), 4), result.get(i), getDelta());
            result = masked.subtract(4.);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(subtract(masked.get(i), 4), result.get(i), getDelta());
            result = masked.subtract(BigInteger.valueOf(4));
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(subtract(masked.get(i), 4), result.get(i), getDelta());
            result = masked.subtract(new BigDecimal(4));
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(subtract(masked.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyArrayByScalar() {
        try {
            NDArray<Double> result = array.multiply((byte) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(multiply(array.get(i), 4), result.get(i), getDelta());
            result = array.multiply((short) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(multiply(array.get(i), 4), result.get(i), getDelta());
            result = array.multiply(4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(multiply(array.get(i), 4), result.get(i), getDelta());
            result = array.multiply((long) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(multiply(array.get(i), 4), result.get(i), getDelta());
            result = array.multiply((float) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(multiply(array.get(i), 4), result.get(i), getDelta());
            result = array.multiply(4.);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(multiply(array.get(i), 4), result.get(i), getDelta());
            result = array.multiply(BigInteger.valueOf(4));
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(multiply(array.get(i), 4), result.get(i), getDelta());
            result = array.multiply(new BigDecimal(4));
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(multiply(array.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplySliceByScalar() {
        try {
            NDArray<Double> result = slice.multiply((byte) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(multiply(slice.get(i), 4), result.get(i), getDelta());
            result = slice.multiply((short) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(multiply(slice.get(i), 4), result.get(i), getDelta());
            result = slice.multiply(4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(multiply(slice.get(i), 4), result.get(i), getDelta());
            result = slice.multiply((long) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(multiply(slice.get(i), 4), result.get(i), getDelta());
            result = slice.multiply((float) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(multiply(slice.get(i), 4), result.get(i), getDelta());
            result = slice.multiply(4.);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(multiply(slice.get(i), 4), result.get(i), getDelta());
            result = slice.multiply(BigInteger.valueOf(4));
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(multiply(slice.get(i), 4), result.get(i), getDelta());
            result = slice.multiply(new BigDecimal(4));
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(multiply(slice.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyReshapedByScalar() {
        try {
            NDArray<Double> result = reshaped.multiply((byte) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(multiply(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.multiply((short) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(multiply(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.multiply(4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(multiply(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.multiply((long) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(multiply(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.multiply((float) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(multiply(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.multiply(4.);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(multiply(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.multiply(BigInteger.valueOf(4));
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(multiply(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.multiply(new BigDecimal(4));
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(multiply(reshaped.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyPArrayByScalar() {
        try {
            NDArray<Double> result = pArray.multiply((byte) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(multiply(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.multiply((short) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(multiply(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.multiply(4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(multiply(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.multiply((long) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(multiply(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.multiply((float) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(multiply(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.multiply(4.);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(multiply(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.multiply(BigInteger.valueOf(4));
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(multiply(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.multiply(new BigDecimal(4));
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(multiply(pArray.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMaskedByScalar() {
        try {
            NDArray<Double> result = masked.multiply((byte) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(multiply(masked.get(i), 4), result.get(i), getDelta());
            result = masked.multiply((short) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(multiply(masked.get(i), 4), result.get(i), getDelta());
            result = masked.multiply(4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(multiply(masked.get(i), 4), result.get(i), getDelta());
            result = masked.multiply((long) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(multiply(masked.get(i), 4), result.get(i), getDelta());
            result = masked.multiply((float) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(multiply(masked.get(i), 4), result.get(i), getDelta());
            result = masked.multiply(4.);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(multiply(masked.get(i), 4), result.get(i), getDelta());
            result = masked.multiply(BigInteger.valueOf(4));
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(multiply(masked.get(i), 4), result.get(i), getDelta());
            result = masked.multiply(new BigDecimal(4));
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(multiply(masked.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideArrayByScalar() {
        try {
            NDArray<Double> result = array.divide((byte) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(divide(array.get(i), 4), result.get(i), getDelta());
            result = array.divide((short) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(divide(array.get(i), 4), result.get(i), getDelta());
            result = array.divide(4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(divide(array.get(i), 4), result.get(i), getDelta());
            result = array.divide((long) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(divide(array.get(i), 4), result.get(i), getDelta());
            result = array.divide((float) 4);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(divide(array.get(i), 4), result.get(i), getDelta());
            result = array.divide(4.);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(divide(array.get(i), 4), result.get(i), getDelta());
            result = array.divide(BigInteger.valueOf(4));
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(divide(array.get(i), 4), result.get(i), getDelta());
            result = array.divide(new BigDecimal(4));
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(divide(array.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideSliceByScalar() {
        try {
            NDArray<Double> result = slice.divide((byte) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(divide(slice.get(i), 4), result.get(i), getDelta());
            result = slice.divide((short) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(divide(slice.get(i), 4), result.get(i), getDelta());
            result = slice.divide(4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(divide(slice.get(i), 4), result.get(i), getDelta());
            result = slice.divide((long) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(divide(slice.get(i), 4), result.get(i), getDelta());
            result = slice.divide((float) 4);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(divide(slice.get(i), 4), result.get(i), getDelta());
            result = slice.divide(4.);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(divide(slice.get(i), 4), result.get(i), getDelta());
            result = slice.divide(BigInteger.valueOf(4));
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(divide(slice.get(i), 4), result.get(i), getDelta());
            result = slice.divide(new BigDecimal(4));
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(divide(slice.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideReshapedByScalar() {
        try {
            NDArray<Double> result = reshaped.divide((byte) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(divide(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.divide((short) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(divide(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.divide(4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(divide(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.divide((long) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(divide(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.divide((float) 4);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(divide(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.divide(4.);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(divide(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.divide(BigInteger.valueOf(4));
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(divide(reshaped.get(i), 4), result.get(i), getDelta());
            result = reshaped.divide(new BigDecimal(4));
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(divide(reshaped.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDividePArrayByScalar() {
        try {
            NDArray<Double> result = pArray.divide((byte) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(divide(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.divide((short) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(divide(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.divide(4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(divide(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.divide((long) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(divide(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.divide((float) 4);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(divide(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.divide(4.);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(divide(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.divide(BigInteger.valueOf(4));
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(divide(pArray.get(i), 4), result.get(i), getDelta());
            result = pArray.divide(new BigDecimal(4));
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(divide(pArray.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMaskedByScalar() {
        try {
            NDArray<Double> result = masked.divide((byte) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(divide(masked.get(i), 4), result.get(i), getDelta());
            result = masked.divide((short) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(divide(masked.get(i), 4), result.get(i), getDelta());
            result = masked.divide(4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(divide(masked.get(i), 4), result.get(i), getDelta());
            result = masked.divide((long) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(divide(masked.get(i), 4), result.get(i), getDelta());
            result = masked.divide((float) 4);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(divide(masked.get(i), 4), result.get(i), getDelta());
            result = masked.divide(4.);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(divide(masked.get(i), 4), result.get(i), getDelta());
            result = masked.divide(BigInteger.valueOf(4));
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(divide(masked.get(i), 4), result.get(i), getDelta());
            result = masked.divide(new BigDecimal(4));
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(divide(masked.get(i), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> rhs = array;
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToArray() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice.copy();
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToArray() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped.copy();
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray.copy();
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToArray() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked.copy();
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> rhs = array;
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromArray() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice.copy();
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromArray() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped.copy();
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray.copy();
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromArray() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked.copy();
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> rhs = array;
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceByArray() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice.copy();
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayBySlice() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceBySlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByArray() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped.copy();
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByReshaped() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray.copy();
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByPArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByArray() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked.copy();
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByMasked() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> rhs = array;
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceByArray() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice.copy();
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayBySlice() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceBySlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> rhs = slice;
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByArray() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped.copy();
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByReshaped() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> rhs = reshaped;
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray.copy();
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByPArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> rhs = pArray;
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByArray() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked.copy();
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByMasked() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> rhs = masked;
        try {
            NDArray<Double> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToDouble(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = array;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceSliceToArray() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceSliceToSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceReshapedToArray() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceReshapedToReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplacePArrayToArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplacePArrayToPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMaskedToArray() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMaskedToMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = array;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceSliceFromArray() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceSliceFromSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceReshapedFromArray() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceReshapedFromReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplacePArrayFromArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplacePArrayFromPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMaskedFromArray() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMaskedFromMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = array;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceByArray() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayBySlice() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceBySlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByArray() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByReshaped() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByPArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByArray() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByMasked() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = array;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceByArray() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayBySlice() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceBySlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByArray() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByReshaped() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByPArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByArray() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByMasked() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.addInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.addInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.addInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.addInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.addInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.addInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.subtractInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.subtractInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.subtractInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.subtractInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.subtractInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.subtractInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByScalar() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.multiplyInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceByScalar() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.multiplyInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByScalar() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.multiplyInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByScalar() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.multiplyInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByScalar() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.multiplyInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.multiplyInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByScalar() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.divideInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceByScalar() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.divideInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByScalar() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.divideInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByScalar() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.divideInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByScalar() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        try {
            lhs.divideInplace((byte) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((short) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((long) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace((float) 5);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(5.);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(BigInteger.valueOf(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
            original = lhs.copy();
            lhs.divideInplace(new BigDecimal(5));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), 5), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = array;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleSliceToArray() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleSliceToSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleReshapedToArray() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleReshapedToReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultiplePArrayToArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultiplePArrayToPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleMaskedToArray() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleMaskedToMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = array;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleSliceFromArray() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleSliceFromSlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleReshapedFromArray() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleReshapedFromReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultiplePArrayFromArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultiplePArrayFromPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleMaskedFromArray() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleMaskedFromMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = array;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleSliceByArray() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayBySlice() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleSliceBySlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleReshapedByArray() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByReshaped() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleReshapedByReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultiplePArrayByArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByPArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultiplePArrayByPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleMaskedByArray() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByMasked() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleMaskedByMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayFromArray() {
        NDArray<Double> lhs = array;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = array;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleSliceByArray() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayBySlice() {
        NDArray<Double> lhs = slice.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleSliceBySlice() {
        NDArray<Double> lhs = slice;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = slice;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleReshapedByArray() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByReshaped() {
        NDArray<Double> lhs = reshaped.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleReshapedByReshaped() {
        NDArray<Double> lhs = reshaped;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = reshaped;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultiplePArrayByArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByPArray() {
        NDArray<Double> lhs = pArray.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultiplePArrayByPArray() {
        NDArray<Double> lhs = pArray;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = pArray;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleMaskedByArray() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByMasked() {
        NDArray<Double> lhs = masked.copy();
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleMaskedByMasked() {
        NDArray<Double> lhs = masked;
        NDArray<Double> original = lhs.copy();
        NDArray<Double> rhs = masked;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToDouble(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSumWithArray() {
        Double sum = array.sum();
        Double acc = wrapToDouble(0);
        for (int i = 0; i < array.length(); i++)
            acc = add(acc, array.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithSlice() {
        Double sum = slice.sum();
        Double acc = wrapToDouble(0);
        for (int i = 0; i < slice.length(); i++)
            acc = add(acc, slice.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithReshaped() {
        Double sum = reshaped.sum();
        Double acc = wrapToDouble(0);
        for (int i = 0; i < reshaped.length(); i++)
            acc = add(acc, reshaped.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithPArray() {
        Double sum = pArray.sum();
        Double acc = wrapToDouble(0);
        for (int i = 0; i < pArray.length(); i++)
            acc = add(acc, pArray.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithMasked() {
        Double sum = masked.sum();
        Double acc = wrapToDouble(0);
        for (int i = 0; i < masked.length(); i++)
            acc = add(acc, masked.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumByDim() {
        NDArray<Double> sum = array.sum(1, 2);
        for (int i = 0; i < array.shape(0); i++) {
            Double acc = wrapToDouble(0);
            for (int j = 0; j < array.shape(1); j++) {
                for (int k = 0; k < array.shape(2); k++) {
                    acc = add(acc, array.get(i, j, k));
                }
            }
            assertSpecializedEquals(acc, sum.get(i));
        }
    }

}
