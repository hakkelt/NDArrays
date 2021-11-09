/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestBasicComplexFloatNDArrayComplexArithmetics.java
 * 
 * Generated at Mon, 8 Nov 2021 11:40:54 +0100
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import io.github.hakkelt.ndarrays.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

class TestBasicComplexFloatNDArrayComplexArithmetics extends TestBase {

    ComplexNDArray<Float> arrayOriginal;
    ComplexNDArray<Float> array;
    ComplexNDArray<Float> mask;
    ComplexNDArray<Float> masked;
    ComplexNDArray<Float> pArray;
    ComplexNDArray<Float> reshaped;
    ComplexNDArray<Float> slice;

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
    void testAddScalarToArray() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = array.add(scalar);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(add(array.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToSlice() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = slice.add(scalar);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(add(slice.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToReshaped() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = reshaped.add(scalar);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(add(reshaped.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToPArray() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = pArray.add(scalar);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(add(pArray.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToMasked() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = masked.add(scalar);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(add(masked.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromArray() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = array.subtract(scalar);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(subtract(array.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromSlice() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = slice.subtract(scalar);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(subtract(slice.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromReshaped() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = reshaped.subtract(scalar);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(subtract(reshaped.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromPArray() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = pArray.subtract(scalar);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(subtract(pArray.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromMasked() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = masked.subtract(scalar);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(subtract(masked.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyArrayByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = array.multiply(scalar);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(multiply(array.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplySliceByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = slice.multiply(scalar);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(multiply(slice.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyReshapedByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = reshaped.multiply(scalar);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(multiply(reshaped.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyPArrayByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = pArray.multiply(scalar);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(multiply(pArray.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMaskedByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = masked.multiply(scalar);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(multiply(masked.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideArrayByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = array.divide(scalar);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(divide(array.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideSliceByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = slice.divide(scalar);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(divide(slice.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideReshapedByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = reshaped.divide(scalar);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(divide(reshaped.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDividePArrayByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = pArray.divide(scalar);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(divide(pArray.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMaskedByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Float> result = masked.divide(scalar);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(divide(masked.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToArray() {
        ComplexNDArray<Float> lhs = array;
        ComplexNDArray<Float> rhs = array;
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToArray() {
        ComplexNDArray<Float> lhs = slice.copy();
        ComplexNDArray<Float> rhs = slice;
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToSlice() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> rhs = slice.copy();
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToSlice() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> rhs = slice;
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToArray() {
        ComplexNDArray<Float> lhs = reshaped.copy();
        ComplexNDArray<Float> rhs = reshaped;
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToReshaped() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> rhs = reshaped.copy();
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToReshaped() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> rhs = reshaped;
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToArray() {
        ComplexNDArray<Float> lhs = pArray.copy();
        ComplexNDArray<Float> rhs = pArray;
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToPArray() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> rhs = pArray.copy();
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToPArray() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> rhs = pArray;
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToArray() {
        ComplexNDArray<Float> lhs = masked.copy();
        ComplexNDArray<Float> rhs = masked;
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToMasked() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> rhs = masked.copy();
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToMasked() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> rhs = masked;
        try {
            ComplexNDArray<Float> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromArray() {
        ComplexNDArray<Float> lhs = array;
        ComplexNDArray<Float> rhs = array;
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromArray() {
        ComplexNDArray<Float> lhs = slice.copy();
        ComplexNDArray<Float> rhs = slice;
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromSlice() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> rhs = slice.copy();
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromSlice() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> rhs = slice;
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromArray() {
        ComplexNDArray<Float> lhs = reshaped.copy();
        ComplexNDArray<Float> rhs = reshaped;
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromReshaped() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> rhs = reshaped.copy();
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromReshaped() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> rhs = reshaped;
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromArray() {
        ComplexNDArray<Float> lhs = pArray.copy();
        ComplexNDArray<Float> rhs = pArray;
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromPArray() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> rhs = pArray.copy();
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromPArray() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> rhs = pArray;
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromArray() {
        ComplexNDArray<Float> lhs = masked.copy();
        ComplexNDArray<Float> rhs = masked;
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromMasked() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> rhs = masked.copy();
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromMasked() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> rhs = masked;
        try {
            ComplexNDArray<Float> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayFromArray() {
        ComplexNDArray<Float> lhs = array;
        ComplexNDArray<Float> rhs = array;
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceByArray() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> rhs = slice.copy();
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayBySlice() {
        ComplexNDArray<Float> lhs = slice.copy();
        ComplexNDArray<Float> rhs = slice;
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceBySlice() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> rhs = slice;
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByArray() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> rhs = reshaped.copy();
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByReshaped() {
        ComplexNDArray<Float> lhs = reshaped.copy();
        ComplexNDArray<Float> rhs = reshaped;
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByReshaped() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> rhs = reshaped;
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByArray() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> rhs = pArray.copy();
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByPArray() {
        ComplexNDArray<Float> lhs = pArray.copy();
        ComplexNDArray<Float> rhs = pArray;
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByPArray() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> rhs = pArray;
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByArray() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> rhs = masked.copy();
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByMasked() {
        ComplexNDArray<Float> lhs = masked.copy();
        ComplexNDArray<Float> rhs = masked;
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByMasked() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> rhs = masked;
        try {
            ComplexNDArray<Float> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayFromArray() {
        ComplexNDArray<Float> lhs = array;
        ComplexNDArray<Float> rhs = array;
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceByArray() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> rhs = slice.copy();
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayBySlice() {
        ComplexNDArray<Float> lhs = slice.copy();
        ComplexNDArray<Float> rhs = slice;
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceBySlice() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> rhs = slice;
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByArray() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> rhs = reshaped.copy();
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByReshaped() {
        ComplexNDArray<Float> lhs = reshaped.copy();
        ComplexNDArray<Float> rhs = reshaped;
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByReshaped() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> rhs = reshaped;
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByArray() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> rhs = pArray.copy();
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByPArray() {
        ComplexNDArray<Float> lhs = pArray.copy();
        ComplexNDArray<Float> rhs = pArray;
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByPArray() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> rhs = pArray;
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByArray() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> rhs = masked.copy();
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByMasked() {
        ComplexNDArray<Float> lhs = masked.copy();
        ComplexNDArray<Float> rhs = masked;
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByMasked() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> rhs = masked;
        try {
            ComplexNDArray<Float> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToArray() {
        ComplexNDArray<Float> lhs = array;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.addInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToSlice() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.addInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToReshaped() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.addInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToPArray() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.addInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToMasked() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.addInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromArray() {
        ComplexNDArray<Float> lhs = array;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.subtractInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromSlice() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.subtractInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromReshaped() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.subtractInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromPArray() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.subtractInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromMasked() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.subtractInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByScalar() {
        ComplexNDArray<Float> lhs = array;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.multiplyInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceByScalar() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.multiplyInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByScalar() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.multiplyInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByScalar() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.multiplyInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByScalar() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.multiplyInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByScalar() {
        ComplexNDArray<Float> lhs = array;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.divideInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceByScalar() {
        ComplexNDArray<Float> lhs = slice;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.divideInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByScalar() {
        ComplexNDArray<Float> lhs = reshaped;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.divideInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByScalar() {
        ComplexNDArray<Float> lhs = pArray;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.divideInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByScalar() {
        ComplexNDArray<Float> lhs = masked;
        ComplexNDArray<Float> original = lhs.copy();
        try {
            lhs.divideInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

}
