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

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

class TestBasicComplexFloatNDArrayArithmetics extends TestBase {

    NDArray<Complex> arrayOriginal;
    NDArray<Complex> array;
    NDArray<Complex> mask;
    NDArray<Complex> masked;
    NDArray<Complex> pArray;
    NDArray<Complex> reshaped;
    NDArray<Complex> slice;

    double getDelta() {
        return 1e-6;
    }

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicComplexFloatNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToComplex(index + 1));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = array.mapWithLinearIndices((value, index) -> wrapToComplex(index % 2));
        masked = array.mask(mask);
    }

    @Test
    void testAddArrayToArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> rhs = array;
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddSliceToArray() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice.copy();
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddSliceToSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddReshapedToArray() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped.copy();
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddReshapedToReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddPArrayToArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray.copy();
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddPArrayToPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddMaskedToArray() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddArrayToMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked.copy();
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddMaskedToMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> rhs = array;
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractSliceFromArray() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice.copy();
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractSliceFromSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractReshapedFromArray() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped.copy();
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractReshapedFromReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractPArrayFromArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray.copy();
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractPArrayFromPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractMaskedFromArray() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractArrayFromMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked.copy();
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testSubtractMaskedFromMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.subtract(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> rhs = array;
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplySliceByArray() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice.copy();
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayBySlice() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplySliceBySlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyReshapedByArray() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped.copy();
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByReshaped() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyReshapedByReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyPArrayByArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray.copy();
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByPArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyPArrayByPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyMaskedByArray() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked.copy();
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyArrayByMasked() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testMultiplyMaskedByMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.multiply(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> rhs = array;
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideSliceByArray() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice.copy();
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayBySlice() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideSliceBySlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideReshapedByArray() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped.copy();
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByReshaped() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideReshapedByReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDividePArrayByArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray.copy();
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByPArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDividePArrayByPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideMaskedByArray() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked.copy();
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideArrayByMasked() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testDivideMaskedByMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.divide(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
    }

    @Test
    void testAddScalarToArray() {
        try {
            NDArray<Complex> result = array.add((byte) 4);
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
            NDArray<Complex> result = slice.add((byte) 4);
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
            NDArray<Complex> result = reshaped.add((byte) 4);
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
            NDArray<Complex> result = pArray.add((byte) 4);
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
            NDArray<Complex> result = masked.add((byte) 4);
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
            NDArray<Complex> result = array.subtract((byte) 4);
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
            NDArray<Complex> result = slice.subtract((byte) 4);
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
            NDArray<Complex> result = reshaped.subtract((byte) 4);
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
            NDArray<Complex> result = pArray.subtract((byte) 4);
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
            NDArray<Complex> result = masked.subtract((byte) 4);
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
            NDArray<Complex> result = array.multiply((byte) 4);
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
            NDArray<Complex> result = slice.multiply((byte) 4);
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
            NDArray<Complex> result = reshaped.multiply((byte) 4);
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
            NDArray<Complex> result = pArray.multiply((byte) 4);
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
            NDArray<Complex> result = masked.multiply((byte) 4);
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
            NDArray<Complex> result = array.divide((byte) 4);
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
            NDArray<Complex> result = slice.divide((byte) 4);
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
            NDArray<Complex> result = reshaped.divide((byte) 4);
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
            NDArray<Complex> result = pArray.divide((byte) 4);
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
            NDArray<Complex> result = masked.divide((byte) 4);
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
        NDArray<Complex> lhs = array;
        NDArray<Complex> rhs = array;
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToArray() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice.copy();
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToArray() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped.copy();
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray.copy();
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToArray() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked.copy();
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> rhs = array;
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromArray() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice.copy();
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromArray() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped.copy();
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray.copy();
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromArray() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked.copy();
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.subtract(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> rhs = array;
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceByArray() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice.copy();
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayBySlice() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceBySlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByArray() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped.copy();
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByReshaped() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray.copy();
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByPArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByArray() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked.copy();
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByMasked() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.multiply(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> rhs = array;
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceByArray() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice.copy();
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayBySlice() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceBySlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> rhs = slice;
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByArray() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped.copy();
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByReshaped() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> rhs = reshaped;
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray.copy();
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByPArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> rhs = pArray;
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByArray() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked.copy();
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByMasked() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> rhs = masked;
        try {
            NDArray<Complex> result = lhs.divide(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), wrapToComplex(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = array;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceSliceToArray() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceSliceToSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceReshapedToArray() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceReshapedToReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplacePArrayToArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplacePArrayToPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMaskedToArray() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceArrayToMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked.copy();
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMaskedToMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = array;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceSliceFromArray() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceSliceFromSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceReshapedFromArray() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceReshapedFromReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplacePArrayFromArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplacePArrayFromPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMaskedFromArray() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceArrayFromMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked.copy();
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMaskedFromMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.subtractInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = array;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceByArray() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayBySlice() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceBySlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByArray() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByReshaped() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByPArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByArray() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked.copy();
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByMasked() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.multiplyInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = array;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceByArray() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayBySlice() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceBySlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByArray() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByReshaped() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByPArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByArray() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked.copy();
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByMasked() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.divideInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), original.get(i)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
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
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = array;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleSliceToArray() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleSliceToSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleReshapedToArray() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleReshapedToReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultiplePArrayToArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultiplePArrayToPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleMaskedToArray() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleArrayToMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked.copy();
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceMultipleMaskedToMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = array;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleSliceFromArray() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleSliceFromSlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleReshapedFromArray() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleReshapedFromReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultiplePArrayFromArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultiplePArrayFromPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleMaskedFromArray() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleArrayFromMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked.copy();
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceMultipleMaskedFromMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.subtractInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = array;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleSliceByArray() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayBySlice() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleSliceBySlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleReshapedByArray() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByReshaped() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleReshapedByReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultiplePArrayByArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByPArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultiplePArrayByPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleMaskedByArray() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked.copy();
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleArrayByMasked() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMultipleMaskedByMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.multiplyInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayFromArray() {
        NDArray<Complex> lhs = array;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = array;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleSliceByArray() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayBySlice() {
        NDArray<Complex> lhs = slice.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleSliceBySlice() {
        NDArray<Complex> lhs = slice;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = slice;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleReshapedByArray() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByReshaped() {
        NDArray<Complex> lhs = reshaped.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleReshapedByReshaped() {
        NDArray<Complex> lhs = reshaped;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = reshaped;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultiplePArrayByArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByPArray() {
        NDArray<Complex> lhs = pArray.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultiplePArrayByPArray() {
        NDArray<Complex> lhs = pArray;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = pArray;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleMaskedByArray() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked.copy();
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleArrayByMasked() {
        NDArray<Complex> lhs = masked.copy();
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMultipleMaskedByMasked() {
        NDArray<Complex> lhs = masked;
        NDArray<Complex> original = lhs.copy();
        NDArray<Complex> rhs = masked;
        try {
            lhs.divideInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(original.get(i), original.get(i)), wrapToComplex(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSumWithArray() {
        Complex sum = array.sum();
        Complex acc = wrapToComplex(0);
        for (int i = 0; i < array.length(); i++)
            acc = add(acc, array.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithSlice() {
        Complex sum = slice.sum();
        Complex acc = wrapToComplex(0);
        for (int i = 0; i < slice.length(); i++)
            acc = add(acc, slice.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithReshaped() {
        Complex sum = reshaped.sum();
        Complex acc = wrapToComplex(0);
        for (int i = 0; i < reshaped.length(); i++)
            acc = add(acc, reshaped.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithPArray() {
        Complex sum = pArray.sum();
        Complex acc = wrapToComplex(0);
        for (int i = 0; i < pArray.length(); i++)
            acc = add(acc, pArray.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumWithMasked() {
        Complex sum = masked.sum();
        Complex acc = wrapToComplex(0);
        for (int i = 0; i < masked.length(); i++)
            acc = add(acc, masked.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumByDim() {
        NDArray<Complex> sum = array.sum(1, 2);
        for (int i = 0; i < array.shape(0); i++) {
            Complex acc = wrapToComplex(0);
            for (int j = 0; j < array.shape(1); j++) {
                for (int k = 0; k < array.shape(2); k++) {
                    acc = add(acc, array.get(i, j, k));
                }
            }
            assertSpecializedEquals(acc, sum.get(i));
        }
    }

}
