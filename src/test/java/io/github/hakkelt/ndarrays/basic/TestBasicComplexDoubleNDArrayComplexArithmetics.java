/**
 * ---------------------------------------------------------------------------------------------------------------------
 * This file was generated, so instead of changing it, consider updating the template:
 * src\test\java\io\github\hakkelt\ndarrays\template\TestComplexArithmetics.java
 * ---------------------------------------------------------------------------------------------------------------------
 */

package io.github.hakkelt.ndarrays.basic;

import io.github.hakkelt.ndarrays.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

class TestBasicComplexDoubleNDArrayComplexArithmetics extends TestBase {

    ComplexNDArray<Double> arrayOriginal;
    ComplexNDArray<Double> array;
    ComplexNDArray<Double> mask;
    ComplexNDArray<Double> masked;
    ComplexNDArray<Double> pArray;
    ComplexNDArray<Double> reshaped;
    ComplexNDArray<Double> slice;

    double getDelta() {
        return 1e-15;
    }

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicComplexDoubleNDArray(4, 5, 3);
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
            ComplexNDArray<Double> result = array.add(scalar);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(add(array.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToSlice() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = slice.add(scalar);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(add(slice.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToReshaped() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = reshaped.add(scalar);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(add(reshaped.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToPArray() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = pArray.add(scalar);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(add(pArray.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddScalarToMasked() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = masked.add(scalar);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(add(masked.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromArray() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = array.subtract(scalar);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(subtract(array.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromSlice() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = slice.subtract(scalar);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(subtract(slice.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromReshaped() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = reshaped.subtract(scalar);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(subtract(reshaped.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromPArray() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = pArray.subtract(scalar);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(subtract(pArray.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractScalarFromMasked() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = masked.subtract(scalar);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(subtract(masked.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyArrayByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = array.multiply(scalar);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(multiply(array.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplySliceByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = slice.multiply(scalar);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(multiply(slice.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyReshapedByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = reshaped.multiply(scalar);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(multiply(reshaped.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyPArrayByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = pArray.multiply(scalar);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(multiply(pArray.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMaskedByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = masked.multiply(scalar);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(multiply(masked.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideArrayByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = array.divide(scalar);
            for (int i = 0; i < array.length(); i++)
                assertSpecializedEquals(divide(array.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideSliceByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = slice.divide(scalar);
            for (int i = 0; i < slice.length(); i++)
                assertSpecializedEquals(divide(slice.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideReshapedByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = reshaped.divide(scalar);
            for (int i = 0; i < reshaped.length(); i++)
                assertSpecializedEquals(divide(reshaped.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDividePArrayByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = pArray.divide(scalar);
            for (int i = 0; i < pArray.length(); i++)
                assertSpecializedEquals(divide(pArray.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMaskedByScalar() {
        try {
            Complex scalar = new Complex(4, 3);
            ComplexNDArray<Double> result = masked.divide(scalar);
            for (int i = 0; i < masked.length(); i++)
                assertSpecializedEquals(divide(masked.get(i), scalar), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToArray() {
        ComplexNDArray<Double> lhs = array;
        ComplexNDArray<Double> rhs = array;
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToArray() {
        ComplexNDArray<Double> lhs = slice.copy();
        ComplexNDArray<Double> rhs = slice;
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToSlice() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> rhs = slice.copy();
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleSliceToSlice() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> rhs = slice;
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToArray() {
        ComplexNDArray<Double> lhs = reshaped.copy();
        ComplexNDArray<Double> rhs = reshaped;
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToReshaped() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> rhs = reshaped.copy();
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleReshapedToReshaped() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> rhs = reshaped;
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToArray() {
        ComplexNDArray<Double> lhs = pArray.copy();
        ComplexNDArray<Double> rhs = pArray;
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToPArray() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> rhs = pArray.copy();
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultiplePArrayToPArray() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> rhs = pArray;
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToArray() {
        ComplexNDArray<Double> lhs = masked.copy();
        ComplexNDArray<Double> rhs = masked;
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleArrayToMasked() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> rhs = masked.copy();
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddMultipleMaskedToMasked() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> rhs = masked;
        try {
            ComplexNDArray<Double> result = lhs.add(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromArray() {
        ComplexNDArray<Double> lhs = array;
        ComplexNDArray<Double> rhs = array;
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromArray() {
        ComplexNDArray<Double> lhs = slice.copy();
        ComplexNDArray<Double> rhs = slice;
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromSlice() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> rhs = slice.copy();
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleSliceFromSlice() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> rhs = slice;
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromArray() {
        ComplexNDArray<Double> lhs = reshaped.copy();
        ComplexNDArray<Double> rhs = reshaped;
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromReshaped() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> rhs = reshaped.copy();
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleReshapedFromReshaped() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> rhs = reshaped;
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromArray() {
        ComplexNDArray<Double> lhs = pArray.copy();
        ComplexNDArray<Double> rhs = pArray;
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromPArray() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> rhs = pArray.copy();
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultiplePArrayFromPArray() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> rhs = pArray;
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromArray() {
        ComplexNDArray<Double> lhs = masked.copy();
        ComplexNDArray<Double> rhs = masked;
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleArrayFromMasked() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> rhs = masked.copy();
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractMultipleMaskedFromMasked() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> rhs = masked;
        try {
            ComplexNDArray<Double> result = lhs.subtract(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(subtract(subtract(subtract(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayFromArray() {
        ComplexNDArray<Double> lhs = array;
        ComplexNDArray<Double> rhs = array;
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceByArray() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> rhs = slice.copy();
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayBySlice() {
        ComplexNDArray<Double> lhs = slice.copy();
        ComplexNDArray<Double> rhs = slice;
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleSliceBySlice() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> rhs = slice;
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByArray() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> rhs = reshaped.copy();
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByReshaped() {
        ComplexNDArray<Double> lhs = reshaped.copy();
        ComplexNDArray<Double> rhs = reshaped;
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleReshapedByReshaped() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> rhs = reshaped;
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByArray() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> rhs = pArray.copy();
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByPArray() {
        ComplexNDArray<Double> lhs = pArray.copy();
        ComplexNDArray<Double> rhs = pArray;
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultiplePArrayByPArray() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> rhs = pArray;
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByArray() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> rhs = masked.copy();
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleArrayByMasked() {
        ComplexNDArray<Double> lhs = masked.copy();
        ComplexNDArray<Double> rhs = masked;
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyMultipleMaskedByMasked() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> rhs = masked;
        try {
            ComplexNDArray<Double> result = lhs.multiply(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(multiply(multiply(multiply(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayFromArray() {
        ComplexNDArray<Double> lhs = array;
        ComplexNDArray<Double> rhs = array;
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceByArray() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> rhs = slice.copy();
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayBySlice() {
        ComplexNDArray<Double> lhs = slice.copy();
        ComplexNDArray<Double> rhs = slice;
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleSliceBySlice() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> rhs = slice;
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByArray() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> rhs = reshaped.copy();
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByReshaped() {
        ComplexNDArray<Double> lhs = reshaped.copy();
        ComplexNDArray<Double> rhs = reshaped;
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleReshapedByReshaped() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> rhs = reshaped;
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByArray() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> rhs = pArray.copy();
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByPArray() {
        ComplexNDArray<Double> lhs = pArray.copy();
        ComplexNDArray<Double> rhs = pArray;
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultiplePArrayByPArray() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> rhs = pArray;
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByArray() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> rhs = masked.copy();
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleArrayByMasked() {
        ComplexNDArray<Double> lhs = masked.copy();
        ComplexNDArray<Double> rhs = masked;
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideMultipleMaskedByMasked() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> rhs = masked;
        try {
            ComplexNDArray<Double> result = lhs.divide(rhs, new Complex(4.3, -0.2), rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(divide(divide(divide(lhs.get(i), rhs.get(i)), new Complex(4.3, -0.2)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToArray() {
        ComplexNDArray<Double> lhs = array;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.addInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToSlice() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.addInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToReshaped() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.addInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToPArray() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.addInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testAddInplaceScalarToMasked() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.addInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromArray() {
        ComplexNDArray<Double> lhs = array;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.subtractInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromSlice() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.subtractInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromReshaped() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.subtractInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromPArray() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.subtractInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testSubtractInplaceScalarFromMasked() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.subtractInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(subtract(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceArrayByScalar() {
        ComplexNDArray<Double> lhs = array;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.multiplyInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceSliceByScalar() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.multiplyInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceReshapedByScalar() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.multiplyInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplacePArrayByScalar() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.multiplyInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testMultiplyInplaceMaskedByScalar() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.multiplyInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(multiply(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceArrayByScalar() {
        ComplexNDArray<Double> lhs = array;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.divideInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceSliceByScalar() {
        ComplexNDArray<Double> lhs = slice;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.divideInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceReshapedByScalar() {
        ComplexNDArray<Double> lhs = reshaped;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.divideInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplacePArrayByScalar() {
        ComplexNDArray<Double> lhs = pArray;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.divideInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    void testDivideInplaceMaskedByScalar() {
        ComplexNDArray<Double> lhs = masked;
        ComplexNDArray<Double> original = lhs.copy();
        try {
            lhs.divideInplace(new Complex(3.9, 0.1));
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(divide(original.get(i), new Complex(3.9, 0.1)), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

}
