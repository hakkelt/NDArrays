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

class TestBasicBigDecimalNDArrayArithmetics extends TestBase {

    NDArray<BigDecimal> arrayOriginal;
    NDArray<BigDecimal> array;
    NDArray<BigDecimal> mask;
    NDArray<BigDecimal> masked;
    NDArray<BigDecimal> pArray;
    NDArray<BigDecimal> reshaped;
    NDArray<BigDecimal> slice;

    double getDelta() {
        return 0;
    }

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicBigDecimalNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToBigDecimal(index + 1));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = array.mapWithLinearIndices((value, index) -> wrapToBigDecimal(index % 2));
        masked = array.mask(mask);
    }

    @Test
    void testAddArrayToArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> rhs = array;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddSliceToArray() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddSliceToSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddReshapedToArray() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddReshapedToReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddPArrayToArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddPArrayToPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddMaskedToArray() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddMaskedToMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> rhs = array;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractSliceFromArray() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractSliceFromSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractReshapedFromArray() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractReshapedFromReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractPArrayFromArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractPArrayFromPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractMaskedFromArray() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractMaskedFromMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> rhs = array;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplySliceByArray() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayBySlice() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplySliceBySlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyReshapedByArray() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByReshaped() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyReshapedByReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyPArrayByArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyPArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyMaskedByArray() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByMasked() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyMaskedByMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> rhs = array;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideSliceByArray() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayBySlice() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideSliceBySlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideReshapedByArray() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByReshaped() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideReshapedByReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDividePArrayByArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDividePArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideMaskedByArray() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByMasked() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideMaskedByMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddScalarToArray() {
        try {
            NDArray<BigDecimal> result = array.add((byte) 4);
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
            NDArray<BigDecimal> result = slice.add((byte) 4);
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
            NDArray<BigDecimal> result = reshaped.add((byte) 4);
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
            NDArray<BigDecimal> result = pArray.add((byte) 4);
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
            NDArray<BigDecimal> result = masked.add((byte) 4);
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
            NDArray<BigDecimal> result = array.subtract((byte) 4);
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
            NDArray<BigDecimal> result = slice.subtract((byte) 4);
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
            NDArray<BigDecimal> result = reshaped.subtract((byte) 4);
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
            NDArray<BigDecimal> result = pArray.subtract((byte) 4);
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
            NDArray<BigDecimal> result = masked.subtract((byte) 4);
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
            NDArray<BigDecimal> result = array.multiply((byte) 4);
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
            NDArray<BigDecimal> result = slice.multiply((byte) 4);
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
            NDArray<BigDecimal> result = reshaped.multiply((byte) 4);
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
            NDArray<BigDecimal> result = pArray.multiply((byte) 4);
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
            NDArray<BigDecimal> result = masked.multiply((byte) 4);
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
            NDArray<BigDecimal> result = array.divide((byte) 4);
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
            NDArray<BigDecimal> result = slice.divide((byte) 4);
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
            NDArray<BigDecimal> result = reshaped.divide((byte) 4);
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
            NDArray<BigDecimal> result = pArray.divide((byte) 4);
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
            NDArray<BigDecimal> result = masked.divide((byte) 4);
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
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> rhs = array;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToArray() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToArray() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToArray() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> rhs = array;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromArray() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromArray() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromArray() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> rhs = array;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceByArray() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayBySlice() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceBySlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByArray() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByReshaped() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByArray() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByMasked() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> rhs = array;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceByArray() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayBySlice() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceBySlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> rhs = slice;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByArray() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByReshaped() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> rhs = reshaped;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> rhs = pArray;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByArray() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByMasked() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> rhs = masked;
        try {
            NDArray<BigDecimal> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToBigDecimal(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = array;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceSliceToArray() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceSliceToSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceReshapedToArray() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceReshapedToReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplacePArrayToArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplacePArrayToPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMaskedToArray() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMaskedToMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = array;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceSliceFromArray() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceSliceFromSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceReshapedFromArray() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceReshapedFromReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplacePArrayFromArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplacePArrayFromPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMaskedFromArray() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMaskedFromMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = array;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceByArray() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayBySlice() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceBySlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByArray() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByReshaped() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByArray() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByMasked() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = array;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceByArray() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayBySlice() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceBySlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByArray() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByReshaped() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByArray() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByMasked() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
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
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = array;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleSliceToArray() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleSliceToSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleReshapedToArray() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleReshapedToReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultiplePArrayToArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultiplePArrayToPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleMaskedToArray() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleMaskedToMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = array;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleSliceFromArray() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleSliceFromSlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleReshapedFromArray() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleReshapedFromReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultiplePArrayFromArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultiplePArrayFromPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleMaskedFromArray() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleMaskedFromMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = array;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleSliceByArray() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayBySlice() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleSliceBySlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleReshapedByArray() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByReshaped() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleReshapedByReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultiplePArrayByArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultiplePArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleMaskedByArray() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByMasked() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleMaskedByMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayFromArray() {
        NDArray<BigDecimal> lhs = array;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = array;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleSliceByArray() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayBySlice() {
        NDArray<BigDecimal> lhs = slice.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleSliceBySlice() {
        NDArray<BigDecimal> lhs = slice;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = slice;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleReshapedByArray() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByReshaped() {
        NDArray<BigDecimal> lhs = reshaped.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleReshapedByReshaped() {
        NDArray<BigDecimal> lhs = reshaped;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = reshaped;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultiplePArrayByArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultiplePArrayByPArray() {
        NDArray<BigDecimal> lhs = pArray;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = pArray;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleMaskedByArray() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByMasked() {
        NDArray<BigDecimal> lhs = masked.copy();
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleMaskedByMasked() {
        NDArray<BigDecimal> lhs = masked;
        NDArray<BigDecimal> original = lhs.copy();
        NDArray<BigDecimal> rhs = masked;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToBigDecimal(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSumWithArray() {
        BigDecimal sum = array.sum();
        BigDecimal acc = wrapToBigDecimal(0);
        for (int i = 0; i < array.length(); i++)
            acc = add(acc, array.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithSlice() {
        BigDecimal sum = slice.sum();
        BigDecimal acc = wrapToBigDecimal(0);
        for (int i = 0; i < slice.length(); i++)
            acc = add(acc, slice.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithReshaped() {
        BigDecimal sum = reshaped.sum();
        BigDecimal acc = wrapToBigDecimal(0);
        for (int i = 0; i < reshaped.length(); i++)
            acc = add(acc, reshaped.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithPArray() {
        BigDecimal sum = pArray.sum();
        BigDecimal acc = wrapToBigDecimal(0);
        for (int i = 0; i < pArray.length(); i++)
            acc = add(acc, pArray.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithMasked() {
        BigDecimal sum = masked.sum();
        BigDecimal acc = wrapToBigDecimal(0);
        for (int i = 0; i < masked.length(); i++)
            acc = add(acc, masked.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumByDim() {
        NDArray<BigDecimal> sum = array.sum(1, 2);
        for (int i = 0; i < array.shape(0); i++) {
            BigDecimal acc = wrapToBigDecimal(0);
            for (int j = 0; j < array.shape(1); j++) {
                for (int k = 0; k < array.shape(2); k++) {
                    acc = add(acc, array.get(i, j, k));
                }
            }
            assertSpecializedEquals(acc, sum.get(i));
        }
    }

}
