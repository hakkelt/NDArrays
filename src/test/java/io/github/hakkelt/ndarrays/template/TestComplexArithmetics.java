package io.github.hakkelt.ndarrays.template;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.*;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1ComplexArithmetics")
@Patterns({ "BasicComplexFloatNDArray", "Float", "1e-6;" })
@Replacements({ "BasicComplexDoubleNDArray", "Double", "1e-15;" })
class TestComplexArithmetics extends TestBase {
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
    @Patterns({"add", "testAddScalarToArray", "array", " + "})
    @Replacements({"add", "testAddScalarToSlice", "slice", " + "})
    @Replacements({"add", "testAddScalarToReshaped", "reshaped", " + "})
    @Replacements({"add", "testAddScalarToPArray", "pArray", " + "})
    @Replacements({"add", "testAddScalarToMasked", "masked", " + "})
    @Replacements({"subtract", "testSubtractScalarFromArray", "array", " - "})
    @Replacements({"subtract", "testSubtractScalarFromSlice", "slice", " - "})
    @Replacements({"subtract", "testSubtractScalarFromReshaped", "reshaped", " - "})
    @Replacements({"subtract", "testSubtractScalarFromPArray", "pArray", " - "})
    @Replacements({"subtract", "testSubtractScalarFromMasked", "masked", " - "})
    @Replacements({"multiply", "testMultiplyArrayByScalar", "array", " * "})
    @Replacements({"multiply", "testMultiplySliceByScalar", "slice", " * "})
    @Replacements({"multiply", "testMultiplyReshapedByScalar", "reshaped", " * "})
    @Replacements({"multiply", "testMultiplyPArrayByScalar", "pArray", " * "})
    @Replacements({"multiply", "testMultiplyMaskedByScalar", "masked", " * "})
    @Replacements({"divide", "testDivideArrayByScalar", "array", " / "})
    @Replacements({"divide", "testDivideSliceByScalar", "slice", " / "})
    @Replacements({"divide", "testDivideReshapedByScalar", "reshaped", " / "})
    @Replacements({"divide", "testDividePArrayByScalar", "pArray", " / "})
    @Replacements({"divide", "testDivideMaskedByScalar", "masked", " / "})
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
    @Patterns({"add", "testAddMultipleArrayToArray", "lhs = array", "rhs = array", " + "})
    @Replacements({"add", "testAddMultipleSliceToArray", "lhs = slice.copy()", "rhs = slice", " + "})
    @Replacements({"add", "testAddMultipleArrayToSlice", "lhs = slice", "rhs = slice.copy()", " + "})
    @Replacements({"add", "testAddMultipleSliceToSlice", "lhs = slice", "rhs = slice", " + "})
    @Replacements({"add", "testAddMultipleReshapedToArray", "lhs = reshaped.copy()", "rhs = reshaped", " + "})
    @Replacements({"add", "testAddMultipleArrayToReshaped", "lhs = reshaped", "rhs = reshaped.copy()", " + "})
    @Replacements({"add", "testAddMultipleReshapedToReshaped", "lhs = reshaped", "rhs = reshaped", " + "})
    @Replacements({"add", "testAddMultiplePArrayToArray", "lhs = pArray.copy()", "rhs = pArray", " + "})
    @Replacements({"add", "testAddMultipleArrayToPArray", "lhs = pArray", "rhs = pArray.copy()", " + "})
    @Replacements({"add", "testAddMultiplePArrayToPArray", "lhs = pArray", "rhs = pArray", " + "})
    @Replacements({"add", "testAddMultipleMaskedToArray", "lhs = masked.copy()", "rhs = masked", " + "})
    @Replacements({"add", "testAddMultipleArrayToMasked", "lhs = masked", "rhs = masked.copy()", " + "})
    @Replacements({"add", "testAddMultipleMaskedToMasked", "lhs = masked", "rhs = masked", " + "})
    @Replacements({"subtract", "testSubtractMultipleArrayFromArray", "lhs = array", "rhs = array", " - "})
    @Replacements({"subtract", "testSubtractMultipleSliceFromArray", "lhs = slice.copy()", "rhs = slice", " - "})
    @Replacements({"subtract", "testSubtractMultipleArrayFromSlice", "lhs = slice", "rhs = slice.copy()", " - "})
    @Replacements({"subtract", "testSubtractMultipleSliceFromSlice", "lhs = slice", "rhs = slice", " - "})
    @Replacements({"subtract", "testSubtractMultipleReshapedFromArray", "lhs = reshaped.copy()", "rhs = reshaped", " - "})
    @Replacements({"subtract", "testSubtractMultipleArrayFromReshaped", "lhs = reshaped", "rhs = reshaped.copy()", " - "})
    @Replacements({"subtract", "testSubtractMultipleReshapedFromReshaped", "lhs = reshaped", "rhs = reshaped", " - "})
    @Replacements({"subtract", "testSubtractMultiplePArrayFromArray", "lhs = pArray.copy()", "rhs = pArray", " - "})
    @Replacements({"subtract", "testSubtractMultipleArrayFromPArray", "lhs = pArray", "rhs = pArray.copy()", " - "})
    @Replacements({"subtract", "testSubtractMultiplePArrayFromPArray", "lhs = pArray", "rhs = pArray", " - "})
    @Replacements({"subtract", "testSubtractMultipleMaskedFromArray", "lhs = masked.copy()", "rhs = masked", " - "})
    @Replacements({"subtract", "testSubtractMultipleArrayFromMasked", "lhs = masked", "rhs = masked.copy()", " - "})
    @Replacements({"subtract", "testSubtractMultipleMaskedFromMasked", "lhs = masked", "rhs = masked", " - "})
    @Replacements({"multiply", "testMultiplyMultipleArrayFromArray", "lhs = array", "rhs = array", " * "})
    @Replacements({"multiply", "testMultiplyMultipleSliceByArray", "lhs = slice", "rhs = slice.copy()", " * "})
    @Replacements({"multiply", "testMultiplyMultipleArrayBySlice", "lhs = slice.copy()", "rhs = slice", " * "})
    @Replacements({"multiply", "testMultiplyMultipleSliceBySlice", "lhs = slice", "rhs = slice", " * "})
    @Replacements({"multiply", "testMultiplyMultipleReshapedByArray", "lhs = reshaped", "rhs = reshaped.copy()", " * "})
    @Replacements({"multiply", "testMultiplyMultipleArrayByReshaped", "lhs = reshaped.copy()", "rhs = reshaped", " * "})
    @Replacements({"multiply", "testMultiplyMultipleReshapedByReshaped", "lhs = reshaped", "rhs = reshaped", " * "})
    @Replacements({"multiply", "testMultiplyMultiplePArrayByArray", "lhs = pArray", "rhs = pArray.copy()", " * "})
    @Replacements({"multiply", "testMultiplyMultipleArrayByPArray", "lhs = pArray.copy()", "rhs = pArray", " * "})
    @Replacements({"multiply", "testMultiplyMultiplePArrayByPArray", "lhs = pArray", "rhs = pArray", " * "})
    @Replacements({"multiply", "testMultiplyMultipleMaskedByArray", "lhs = masked", "rhs = masked.copy()", " * "})
    @Replacements({"multiply", "testMultiplyMultipleArrayByMasked", "lhs = masked.copy()", "rhs = masked", " * "})
    @Replacements({"multiply", "testMultiplyMultipleMaskedByMasked", "lhs = masked", "rhs = masked", " * "})
    @Replacements({"divide", "testDivideMultipleArrayFromArray", "lhs = array", "rhs = array", " / "})
    @Replacements({"divide", "testDivideMultipleSliceByArray", "lhs = slice", "rhs = slice.copy()", " / "})
    @Replacements({"divide", "testDivideMultipleArrayBySlice", "lhs = slice.copy()", "rhs = slice", " / "})
    @Replacements({"divide", "testDivideMultipleSliceBySlice", "lhs = slice", "rhs = slice", " / "})
    @Replacements({"divide", "testDivideMultipleReshapedByArray", "lhs = reshaped", "rhs = reshaped.copy()", " / "})
    @Replacements({"divide", "testDivideMultipleArrayByReshaped", "lhs = reshaped.copy()", "rhs = reshaped", " / "})
    @Replacements({"divide", "testDivideMultipleReshapedByReshaped", "lhs = reshaped", "rhs = reshaped", " / "})
    @Replacements({"divide", "testDivideMultiplePArrayByArray", "lhs = pArray", "rhs = pArray.copy()", " / "})
    @Replacements({"divide", "testDivideMultipleArrayByPArray", "lhs = pArray.copy()", "rhs = pArray", " / "})
    @Replacements({"divide", "testDivideMultiplePArrayByPArray", "lhs = pArray", "rhs = pArray", " / "})
    @Replacements({"divide", "testDivideMultipleMaskedByArray", "lhs = masked", "rhs = masked.copy()", " / "})
    @Replacements({"divide", "testDivideMultipleArrayByMasked", "lhs = masked.copy()", "rhs = masked", " / "})
    @Replacements({"divide", "testDivideMultipleMaskedByMasked", "lhs = masked", "rhs = masked", " / "})
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
    @Patterns({"add", "addInplace", "testAddInplaceScalarToArray", "array", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceScalarToSlice", "slice", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceScalarToReshaped", "reshaped", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceScalarToPArray", "pArray", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceScalarToMasked", "masked", " + "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceScalarFromArray", "array", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceScalarFromSlice", "slice", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceScalarFromReshaped", "reshaped", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceScalarFromPArray", "pArray", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceScalarFromMasked", "masked", " - "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceArrayByScalar", "array", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceSliceByScalar", "slice", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceReshapedByScalar", "reshaped", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplacePArrayByScalar", "pArray", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMaskedByScalar", "masked", " * "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceArrayByScalar", "array", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceSliceByScalar", "slice", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceReshapedByScalar", "reshaped", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplacePArrayByScalar", "pArray", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMaskedByScalar", "masked", " / "})
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

}
