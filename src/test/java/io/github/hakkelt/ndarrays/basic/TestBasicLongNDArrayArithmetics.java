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

class TestBasicLongNDArrayArithmetics extends TestBase {

    NDArray<Long> arrayOriginal;
    NDArray<Long> array;
    NDArray<Long> mask;
    NDArray<Long> masked;
    NDArray<Long> pArray;
    NDArray<Long> reshaped;
    NDArray<Long> slice;

    double getDelta() {
        return 0;
    }

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicLongNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToLong(index + 1));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = array.mapWithLinearIndices((value, index) -> wrapToLong(index % 2));
        masked = array.mask(mask);
    }

    @Test
    void testAddArrayToArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> rhs = array;
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddSliceToArray() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice.copy();
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddSliceToSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddReshapedToArray() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped.copy();
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddReshapedToReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddPArrayToArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray.copy();
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddPArrayToPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddMaskedToArray() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked.copy();
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddMaskedToMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> rhs = array;
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractSliceFromArray() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice.copy();
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractSliceFromSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractReshapedFromArray() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped.copy();
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractReshapedFromReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractPArrayFromArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray.copy();
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractPArrayFromPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractMaskedFromArray() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked.copy();
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractMaskedFromMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> rhs = array;
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplySliceByArray() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice.copy();
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayBySlice() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplySliceBySlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyReshapedByArray() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped.copy();
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByReshaped() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyReshapedByReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyPArrayByArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray.copy();
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByPArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyPArrayByPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyMaskedByArray() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked.copy();
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByMasked() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyMaskedByMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> rhs = array;
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideSliceByArray() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice.copy();
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayBySlice() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideSliceBySlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideReshapedByArray() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped.copy();
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByReshaped() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideReshapedByReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDividePArrayByArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray.copy();
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByPArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDividePArrayByPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideMaskedByArray() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked.copy();
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByMasked() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideMaskedByMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddScalarToArray() {
        try {
            NDArray<Long> result = array.add((byte) 4);
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
            NDArray<Long> result = slice.add((byte) 4);
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
            NDArray<Long> result = reshaped.add((byte) 4);
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
            NDArray<Long> result = pArray.add((byte) 4);
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
            NDArray<Long> result = masked.add((byte) 4);
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
            NDArray<Long> result = array.subtract((byte) 4);
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
            NDArray<Long> result = slice.subtract((byte) 4);
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
            NDArray<Long> result = reshaped.subtract((byte) 4);
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
            NDArray<Long> result = pArray.subtract((byte) 4);
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
            NDArray<Long> result = masked.subtract((byte) 4);
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
            NDArray<Long> result = array.multiply((byte) 4);
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
            NDArray<Long> result = slice.multiply((byte) 4);
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
            NDArray<Long> result = reshaped.multiply((byte) 4);
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
            NDArray<Long> result = pArray.multiply((byte) 4);
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
            NDArray<Long> result = masked.multiply((byte) 4);
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
            NDArray<Long> result = array.divide((byte) 4);
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
            NDArray<Long> result = slice.divide((byte) 4);
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
            NDArray<Long> result = reshaped.divide((byte) 4);
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
            NDArray<Long> result = pArray.divide((byte) 4);
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
            NDArray<Long> result = masked.divide((byte) 4);
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
        NDArray<Long> lhs = array;
        NDArray<Long> rhs = array;
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToArray() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice.copy();
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToArray() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped.copy();
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray.copy();
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToArray() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked.copy();
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> rhs = array;
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromArray() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice.copy();
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromArray() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped.copy();
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray.copy();
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromArray() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked.copy();
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> rhs = array;
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceByArray() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice.copy();
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayBySlice() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceBySlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByArray() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped.copy();
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByReshaped() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray.copy();
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByPArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByArray() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked.copy();
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByMasked() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> rhs = array;
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceByArray() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice.copy();
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayBySlice() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceBySlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> rhs = slice;
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByArray() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped.copy();
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByReshaped() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> rhs = reshaped;
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray.copy();
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByPArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> rhs = pArray;
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByArray() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked.copy();
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByMasked() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> rhs = masked;
        try {
            NDArray<Long> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToLong(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = array;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceSliceToArray() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceSliceToSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceReshapedToArray() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceReshapedToReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplacePArrayToArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplacePArrayToPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMaskedToArray() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMaskedToMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = array;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceSliceFromArray() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceSliceFromSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceReshapedFromArray() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceReshapedFromReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplacePArrayFromArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplacePArrayFromPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMaskedFromArray() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMaskedFromMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = array;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceByArray() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayBySlice() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceBySlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByArray() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByReshaped() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByPArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByArray() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByMasked() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = array;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceByArray() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayBySlice() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceBySlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByArray() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByReshaped() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByPArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByArray() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByMasked() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
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
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = array;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleSliceToArray() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleSliceToSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleReshapedToArray() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleReshapedToReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultiplePArrayToArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultiplePArrayToPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleMaskedToArray() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleMaskedToMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = array;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleSliceFromArray() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleSliceFromSlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleReshapedFromArray() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleReshapedFromReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultiplePArrayFromArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultiplePArrayFromPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleMaskedFromArray() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleMaskedFromMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = array;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleSliceByArray() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayBySlice() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleSliceBySlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleReshapedByArray() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByReshaped() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleReshapedByReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultiplePArrayByArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByPArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultiplePArrayByPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleMaskedByArray() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByMasked() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleMaskedByMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayFromArray() {
        NDArray<Long> lhs = array;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = array;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleSliceByArray() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayBySlice() {
        NDArray<Long> lhs = slice.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleSliceBySlice() {
        NDArray<Long> lhs = slice;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = slice;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleReshapedByArray() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByReshaped() {
        NDArray<Long> lhs = reshaped.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleReshapedByReshaped() {
        NDArray<Long> lhs = reshaped;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = reshaped;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultiplePArrayByArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByPArray() {
        NDArray<Long> lhs = pArray.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultiplePArrayByPArray() {
        NDArray<Long> lhs = pArray;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = pArray;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleMaskedByArray() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByMasked() {
        NDArray<Long> lhs = masked.copy();
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleMaskedByMasked() {
        NDArray<Long> lhs = masked;
        NDArray<Long> original = lhs.copy();
        NDArray<Long> rhs = masked;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToLong(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSumWithArray() {
        Long sum = array.sum();
        Long acc = wrapToLong(0);
        for (int i = 0; i < array.length(); i++)
            acc = add(acc, array.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithSlice() {
        Long sum = slice.sum();
        Long acc = wrapToLong(0);
        for (int i = 0; i < slice.length(); i++)
            acc = add(acc, slice.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithReshaped() {
        Long sum = reshaped.sum();
        Long acc = wrapToLong(0);
        for (int i = 0; i < reshaped.length(); i++)
            acc = add(acc, reshaped.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithPArray() {
        Long sum = pArray.sum();
        Long acc = wrapToLong(0);
        for (int i = 0; i < pArray.length(); i++)
            acc = add(acc, pArray.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithMasked() {
        Long sum = masked.sum();
        Long acc = wrapToLong(0);
        for (int i = 0; i < masked.length(); i++)
            acc = add(acc, masked.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumByDim() {
        NDArray<Long> sum = array.sum(1, 2);
        for (int i = 0; i < array.shape(0); i++) {
            Long acc = wrapToLong(0);
            for (int j = 0; j < array.shape(1); j++) {
                for (int k = 0; k < array.shape(2); k++) {
                    acc = add(acc, array.get(i, j, k));
                }
            }
            assertSpecializedEquals(acc, sum.get(i));
        }
    }

}
