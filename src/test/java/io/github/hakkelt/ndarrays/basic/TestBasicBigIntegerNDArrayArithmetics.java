/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestArithmetics.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import io.github.hakkelt.ndarrays.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.*;

class TestBasicBigIntegerNDArrayArithmetics extends TestBase {

    NDArray<BigInteger> arrayOriginal;
    NDArray<BigInteger> array;
    NDArray<BigInteger> mask;
    NDArray<BigInteger> masked;
    NDArray<BigInteger> pArray;
    NDArray<BigInteger> reshaped;
    NDArray<BigInteger> slice;

    double getDelta() {
        return 0;
    }

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicBigIntegerNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToBigInteger(index + 1));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = array.mapWithLinearIndices((value, index) -> wrapToBigInteger(index % 2));
        masked = array.mask(mask);
    }

    @Test
    void testAddArrayToArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> rhs = array;
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddSliceToArray() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice.copy();
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddSliceToSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddReshapedToArray() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddReshapedToReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddPArrayToArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddPArrayToPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddMaskedToArray() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked.copy();
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddMaskedToMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> rhs = array;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractSliceFromArray() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice.copy();
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractSliceFromSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractReshapedFromArray() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractReshapedFromReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractPArrayFromArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractPArrayFromPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractMaskedFromArray() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked.copy();
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractMaskedFromMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> rhs = array;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplySliceByArray() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice.copy();
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayBySlice() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplySliceBySlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyReshapedByArray() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByReshaped() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyReshapedByReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyPArrayByArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByPArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyPArrayByPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyMaskedByArray() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked.copy();
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByMasked() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyMaskedByMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> rhs = array;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideSliceByArray() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice.copy();
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayBySlice() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideSliceBySlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideReshapedByArray() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByReshaped() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideReshapedByReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDividePArrayByArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByPArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDividePArrayByPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideMaskedByArray() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked.copy();
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByMasked() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideMaskedByMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddScalarToArray() {
        try {
            NDArray<BigInteger> result = array.add((byte) 4);
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
            NDArray<BigInteger> result = slice.add((byte) 4);
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
            NDArray<BigInteger> result = reshaped.add((byte) 4);
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
            NDArray<BigInteger> result = pArray.add((byte) 4);
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
            NDArray<BigInteger> result = masked.add((byte) 4);
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
            NDArray<BigInteger> result = array.subtract((byte) 4);
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
            NDArray<BigInteger> result = slice.subtract((byte) 4);
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
            NDArray<BigInteger> result = reshaped.subtract((byte) 4);
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
            NDArray<BigInteger> result = pArray.subtract((byte) 4);
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
            NDArray<BigInteger> result = masked.subtract((byte) 4);
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
            NDArray<BigInteger> result = array.multiply((byte) 4);
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
            NDArray<BigInteger> result = slice.multiply((byte) 4);
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
            NDArray<BigInteger> result = reshaped.multiply((byte) 4);
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
            NDArray<BigInteger> result = pArray.multiply((byte) 4);
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
            NDArray<BigInteger> result = masked.multiply((byte) 4);
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
            NDArray<BigInteger> result = array.divide((byte) 4);
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
            NDArray<BigInteger> result = slice.divide((byte) 4);
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
            NDArray<BigInteger> result = reshaped.divide((byte) 4);
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
            NDArray<BigInteger> result = pArray.divide((byte) 4);
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
            NDArray<BigInteger> result = masked.divide((byte) 4);
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
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> rhs = array;
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToArray() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice.copy();
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToArray() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToArray() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked.copy();
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> rhs = array;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromArray() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice.copy();
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromArray() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromArray() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked.copy();
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> rhs = array;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceByArray() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice.copy();
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayBySlice() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceBySlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByArray() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByReshaped() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByPArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByArray() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked.copy();
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByMasked() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> rhs = array;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceByArray() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice.copy();
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayBySlice() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceBySlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> rhs = slice;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByArray() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByReshaped() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> rhs = reshaped;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByPArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> rhs = pArray;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByArray() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked.copy();
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByMasked() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> rhs = masked;
        try {
            NDArray<BigInteger> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigInteger(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = array;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceSliceToArray() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceSliceToSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceReshapedToArray() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceReshapedToReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplacePArrayToArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplacePArrayToPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMaskedToArray() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMaskedToMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = array;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceSliceFromArray() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceSliceFromSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceReshapedFromArray() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceReshapedFromReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplacePArrayFromArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplacePArrayFromPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMaskedFromArray() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMaskedFromMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = array;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceByArray() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayBySlice() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceBySlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByArray() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByReshaped() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByPArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByArray() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByMasked() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = array;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceByArray() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayBySlice() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceBySlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByArray() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByReshaped() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByPArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByArray() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByMasked() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
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
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = array;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleSliceToArray() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleSliceToSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleReshapedToArray() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleReshapedToReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultiplePArrayToArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultiplePArrayToPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleMaskedToArray() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleMaskedToMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = array;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleSliceFromArray() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleSliceFromSlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleReshapedFromArray() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleReshapedFromReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultiplePArrayFromArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultiplePArrayFromPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleMaskedFromArray() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleMaskedFromMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = array;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleSliceByArray() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayBySlice() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleSliceBySlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleReshapedByArray() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByReshaped() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleReshapedByReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultiplePArrayByArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByPArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultiplePArrayByPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleMaskedByArray() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByMasked() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleMaskedByMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayFromArray() {
        NDArray<BigInteger> lhs = array;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = array;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleSliceByArray() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayBySlice() {
        NDArray<BigInteger> lhs = slice.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleSliceBySlice() {
        NDArray<BigInteger> lhs = slice;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = slice;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleReshapedByArray() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByReshaped() {
        NDArray<BigInteger> lhs = reshaped.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleReshapedByReshaped() {
        NDArray<BigInteger> lhs = reshaped;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = reshaped;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultiplePArrayByArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByPArray() {
        NDArray<BigInteger> lhs = pArray.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultiplePArrayByPArray() {
        NDArray<BigInteger> lhs = pArray;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = pArray;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleMaskedByArray() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByMasked() {
        NDArray<BigInteger> lhs = masked.copy();
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleMaskedByMasked() {
        NDArray<BigInteger> lhs = masked;
        NDArray<BigInteger> original = lhs.copy();
        NDArray<BigInteger> rhs = masked;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigInteger(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSumWithArray() {
        BigInteger sum = array.sum();
        BigInteger acc = wrapToBigInteger(0);
        for (int i = 0; i < array.length(); i++)
            acc = add(acc, array.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithSlice() {
        BigInteger sum = slice.sum();
        BigInteger acc = wrapToBigInteger(0);
        for (int i = 0; i < slice.length(); i++)
            acc = add(acc, slice.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithReshaped() {
        BigInteger sum = reshaped.sum();
        BigInteger acc = wrapToBigInteger(0);
        for (int i = 0; i < reshaped.length(); i++)
            acc = add(acc, reshaped.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithPArray() {
        BigInteger sum = pArray.sum();
        BigInteger acc = wrapToBigInteger(0);
        for (int i = 0; i < pArray.length(); i++)
            acc = add(acc, pArray.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithMasked() {
        BigInteger sum = masked.sum();
        BigInteger acc = wrapToBigInteger(0);
        for (int i = 0; i < masked.length(); i++)
            acc = add(acc, masked.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumByDim() {
        NDArray<BigInteger> sum = array.sum(1, 2);
        for (int i = 0; i < array.shape(0); i++) {
            BigInteger acc = wrapToBigInteger(0);
            for (int j = 0; j < array.shape(1); j++) {
                for (int k = 0; k < array.shape(2); k++) {
                    acc = add(acc, array.get(i, j, k));
                }
            }
            assertSpecializedEquals(acc, sum.get(i));
        }
    }

}
