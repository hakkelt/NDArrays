package io.github.hakkelt.ndarrays.template;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.*;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;
import io.github.hakkelt.ndarrays.basic.*;

@ClassTemplate(outputDirectory = "test/java/io/github/hakkelt/ndarrays/basic", newName = "Test$1Arithmetics")
@Patterns({ "BasicByteNDArray", "/Byte/", "return 0;" })
@Replacements({ "BasicShortNDArray", "Short", "return 0;" })
@Replacements({ "BasicIntegerNDArray", "Integer", "return 0;" })
@Replacements({ "BasicLongNDArray", "Long", "return 0;" })
@Replacements({ "BasicFloatNDArray", "Float", "return 1e-6;" })
@Replacements({ "BasicDoubleNDArray", "Double", "return 1e-15;" })
@Replacements(value = { "BasicComplexFloatNDArray", "Complex", "return 1e-6;" }, extraImports = "org.apache.commons.math3.complex.Complex")
@Replacements(value = { "BasicComplexDoubleNDArray", "Complex", "return 1e-15;" }, extraImports = "org.apache.commons.math3.complex.Complex")
@Replacements(value = { "BasicBigIntegerNDArray", "BigInteger", "return 0;" }, extraImports = "java.math.BigInteger")
@Replacements(value = { "BasicBigDecimalNDArray", "BigDecimal", "return 0;" }, extraImports = "java.math.BigDecimal")
class TestArithmetics extends TestBase {
    NDArray<Byte> arrayOriginal;
    NDArray<Byte> array;
    NDArray<Byte> mask;
    NDArray<Byte> masked;
    NDArray<Byte> pArray;
    NDArray<Byte> reshaped;
    NDArray<Byte> slice;

    double getDelta() {
        return 0;
    }

    @BeforeEach
    void setup() {
        array = arrayOriginal = new BasicByteNDArray(4, 5, 3);
        array.applyWithLinearIndices((value, index) -> wrapToByte(index + 1));
        slice = array.slice(1, "1:4", ":");
        reshaped = array.reshape(20, 3);
        pArray = array.permuteDims(0, 2, 1);
        mask = array.mapWithLinearIndices((value, index) -> wrapToByte(index % 2));
        masked = array.mask(mask);
    }

    @Test
    @Patterns({"add", "testAddArrayToArray", "lhs = array", "rhs = array", " + "})
    @Replacements({"add", "testAddSliceToArray", "lhs = slice.copy()", "rhs = slice", " + "})
    @Replacements({"add", "testAddArrayToSlice", "lhs = slice", "rhs = slice.copy()", " + "})
    @Replacements({"add", "testAddSliceToSlice", "lhs = slice", "rhs = slice", " + "})
    @Replacements({"add", "testAddReshapedToArray", "lhs = reshaped.copy()", "rhs = reshaped", " + "})
    @Replacements({"add", "testAddArrayToReshaped", "lhs = reshaped", "rhs = reshaped.copy()", " + "})
    @Replacements({"add", "testAddReshapedToReshaped", "lhs = reshaped", "rhs = reshaped", " + "})
    @Replacements({"add", "testAddPArrayToArray", "lhs = pArray.copy()", "rhs = pArray", " + "})
    @Replacements({"add", "testAddArrayToPArray", "lhs = pArray", "rhs = pArray.copy()", " + "})
    @Replacements({"add", "testAddPArrayToPArray", "lhs = pArray", "rhs = pArray", " + "})
    @Replacements({"add", "testAddMaskedToArray", "lhs = masked.copy()", "rhs = masked", " + "})
    @Replacements({"add", "testAddArrayToMasked", "lhs = masked", "rhs = masked.copy()", " + "})
    @Replacements({"add", "testAddMaskedToMasked", "lhs = masked", "rhs = masked", " + "})
    @Replacements({"subtract", "testSubtractArrayFromArray", "lhs = array", "rhs = array", " - "})
    @Replacements({"subtract", "testSubtractSliceFromArray", "lhs = slice.copy()", "rhs = slice", " - "})
    @Replacements({"subtract", "testSubtractArrayFromSlice", "lhs = slice", "rhs = slice.copy()", " - "})
    @Replacements({"subtract", "testSubtractSliceFromSlice", "lhs = slice", "rhs = slice", " - "})
    @Replacements({"subtract", "testSubtractReshapedFromArray", "lhs = reshaped.copy()", "rhs = reshaped", " - "})
    @Replacements({"subtract", "testSubtractArrayFromReshaped", "lhs = reshaped", "rhs = reshaped.copy()", " - "})
    @Replacements({"subtract", "testSubtractReshapedFromReshaped", "lhs = reshaped", "rhs = reshaped", " - "})
    @Replacements({"subtract", "testSubtractPArrayFromArray", "lhs = pArray.copy()", "rhs = pArray", " - "})
    @Replacements({"subtract", "testSubtractArrayFromPArray", "lhs = pArray", "rhs = pArray.copy()", " - "})
    @Replacements({"subtract", "testSubtractPArrayFromPArray", "lhs = pArray", "rhs = pArray", " - "})
    @Replacements({"subtract", "testSubtractMaskedFromArray", "lhs = masked.copy()", "rhs = masked", " - "})
    @Replacements({"subtract", "testSubtractArrayFromMasked", "lhs = masked", "rhs = masked.copy()", " - "})
    @Replacements({"subtract", "testSubtractMaskedFromMasked", "lhs = masked", "rhs = masked", " - "})
    @Replacements({"multiply", "testMultiplyArrayFromArray", "lhs = array", "rhs = array", " * "})
    @Replacements({"multiply", "testMultiplySliceByArray", "lhs = slice", "rhs = slice.copy()", " * "})
    @Replacements({"multiply", "testMultiplyArrayBySlice", "lhs = slice.copy()", "rhs = slice", " * "})
    @Replacements({"multiply", "testMultiplySliceBySlice", "lhs = slice", "rhs = slice", " * "})
    @Replacements({"multiply", "testMultiplyReshapedByArray", "lhs = reshaped", "rhs = reshaped.copy()", " * "})
    @Replacements({"multiply", "testMultiplyArrayByReshaped", "lhs = reshaped.copy()", "rhs = reshaped", " * "})
    @Replacements({"multiply", "testMultiplyReshapedByReshaped", "lhs = reshaped", "rhs = reshaped", " * "})
    @Replacements({"multiply", "testMultiplyPArrayByArray", "lhs = pArray", "rhs = pArray.copy()", " * "})
    @Replacements({"multiply", "testMultiplyArrayByPArray", "lhs = pArray.copy()", "rhs = pArray", " * "})
    @Replacements({"multiply", "testMultiplyPArrayByPArray", "lhs = pArray", "rhs = pArray", " * "})
    @Replacements({"multiply", "testMultiplyMaskedByArray", "lhs = masked", "rhs = masked.copy()", " * "})
    @Replacements({"multiply", "testMultiplyArrayByMasked", "lhs = masked.copy()", "rhs = masked", " * "})
    @Replacements({"multiply", "testMultiplyMaskedByMasked", "lhs = masked", "rhs = masked", " * "})
    @Replacements({"divide", "testDivideArrayFromArray", "lhs = array", "rhs = array", " / "})
    @Replacements({"divide", "testDivideSliceByArray", "lhs = slice", "rhs = slice.copy()", " / "})
    @Replacements({"divide", "testDivideArrayBySlice", "lhs = slice.copy()", "rhs = slice", " / "})
    @Replacements({"divide", "testDivideSliceBySlice", "lhs = slice", "rhs = slice", " / "})
    @Replacements({"divide", "testDivideReshapedByArray", "lhs = reshaped", "rhs = reshaped.copy()", " / "})
    @Replacements({"divide", "testDivideArrayByReshaped", "lhs = reshaped.copy()", "rhs = reshaped", " / "})
    @Replacements({"divide", "testDivideReshapedByReshaped", "lhs = reshaped", "rhs = reshaped", " / "})
    @Replacements({"divide", "testDividePArrayByArray", "lhs = pArray", "rhs = pArray.copy()", " / "})
    @Replacements({"divide", "testDivideArrayByPArray", "lhs = pArray.copy()", "rhs = pArray", " / "})
    @Replacements({"divide", "testDividePArrayByPArray", "lhs = pArray", "rhs = pArray", " / "})
    @Replacements({"divide", "testDivideMaskedByArray", "lhs = masked", "rhs = masked.copy()", " / "})
    @Replacements({"divide", "testDivideArrayByMasked", "lhs = masked.copy()", "rhs = masked", " / "})
    @Replacements({"divide", "testDivideMaskedByMasked", "lhs = masked", "rhs = masked", " / "})
    void testAddArrayToArray() {
        NDArray<Byte> lhs = array;
        NDArray<Byte> rhs = array;
        try {
            NDArray<Byte> result = lhs.add(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(lhs.get(i), rhs.get(i)), result.get(i), getDelta());
        } catch (ArithmeticException e) {

        }
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
            NDArray<Byte> result = array.add((byte) 4);
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
        NDArray<Byte> lhs = array;
        NDArray<Byte> rhs = array;
        try {
            NDArray<Byte> result = lhs.add(rhs, 1.25, rhs, 4);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(lhs.get(i), rhs.get(i)), wrapToByte(1.25)), rhs.get(i)), 4), result.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    @Patterns({"add", "addInplace", "testAddInplaceArrayToArray", "lhs = array", "rhs = array", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceSliceToArray", "lhs = slice.copy()", "rhs = slice", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceArrayToSlice", "lhs = slice", "rhs = slice.copy()", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceSliceToSlice", "lhs = slice", "rhs = slice", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceReshapedToArray", "lhs = reshaped.copy()", "rhs = reshaped", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceArrayToReshaped", "lhs = reshaped", "rhs = reshaped.copy()", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceReshapedToReshaped", "lhs = reshaped", "rhs = reshaped", " + "})
    @Replacements({"add", "addInplace", "testAddInplacePArrayToArray", "lhs = pArray.copy()", "rhs = pArray", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceArrayToPArray", "lhs = pArray", "rhs = pArray.copy()", " + "})
    @Replacements({"add", "addInplace", "testAddInplacePArrayToPArray", "lhs = pArray", "rhs = pArray", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMaskedToArray", "lhs = masked.copy()", "rhs = masked", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceArrayToMasked", "lhs = masked", "rhs = masked.copy()", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMaskedToMasked", "lhs = masked", "rhs = masked", " + "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceArrayFromArray", "lhs = array", "rhs = array", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceSliceFromArray", "lhs = slice.copy()", "rhs = slice", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceArrayFromSlice", "lhs = slice", "rhs = slice.copy()", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceSliceFromSlice", "lhs = slice", "rhs = slice", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceReshapedFromArray", "lhs = reshaped.copy()", "rhs = reshaped", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceArrayFromReshaped", "lhs = reshaped", "rhs = reshaped.copy()", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceReshapedFromReshaped", "lhs = reshaped", "rhs = reshaped", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplacePArrayFromArray", "lhs = pArray.copy()", "rhs = pArray", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceArrayFromPArray", "lhs = pArray", "rhs = pArray.copy()", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplacePArrayFromPArray", "lhs = pArray", "rhs = pArray", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMaskedFromArray", "lhs = masked.copy()", "rhs = masked", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceArrayFromMasked", "lhs = masked", "rhs = masked.copy()", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMaskedFromMasked", "lhs = masked", "rhs = masked", " - "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceArrayFromArray", "lhs = array", "rhs = array", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceSliceByArray", "lhs = slice", "rhs = slice.copy()", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceArrayBySlice", "lhs = slice.copy()", "rhs = slice", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceSliceBySlice", "lhs = slice", "rhs = slice", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceReshapedByArray", "lhs = reshaped", "rhs = reshaped.copy()", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceArrayByReshaped", "lhs = reshaped.copy()", "rhs = reshaped", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceReshapedByReshaped", "lhs = reshaped", "rhs = reshaped", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplacePArrayByArray", "lhs = pArray", "rhs = pArray.copy()", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceArrayByPArray", "lhs = pArray.copy()", "rhs = pArray", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplacePArrayByPArray", "lhs = pArray", "rhs = pArray", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMaskedByArray", "lhs = masked", "rhs = masked.copy()", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceArrayByMasked", "lhs = masked.copy()", "rhs = masked", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMaskedByMasked", "lhs = masked", "rhs = masked", " * "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceArrayFromArray", "lhs = array", "rhs = array", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceSliceByArray", "lhs = slice", "rhs = slice.copy()", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceArrayBySlice", "lhs = slice.copy()", "rhs = slice", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceSliceBySlice", "lhs = slice", "rhs = slice", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceReshapedByArray", "lhs = reshaped", "rhs = reshaped.copy()", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceArrayByReshaped", "lhs = reshaped.copy()", "rhs = reshaped", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceReshapedByReshaped", "lhs = reshaped", "rhs = reshaped", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplacePArrayByArray", "lhs = pArray", "rhs = pArray.copy()", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceArrayByPArray", "lhs = pArray.copy()", "rhs = pArray", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplacePArrayByPArray", "lhs = pArray", "rhs = pArray", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMaskedByArray", "lhs = masked", "rhs = masked.copy()", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceArrayByMasked", "lhs = masked.copy()", "rhs = masked", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMaskedByMasked", "lhs = masked", "rhs = masked", " / "})
    void testAddInplaceArrayToArray() {
        NDArray<Byte> lhs = array;
        NDArray<Byte> original = lhs.copy();
        NDArray<Byte> rhs = array;
        try {
            lhs.addInplace(rhs);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(original.get(i), original.get(i)), lhs.get(i), getDelta());
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
        NDArray<Byte> lhs = array;
        NDArray<Byte> original = lhs.copy();
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
    @Patterns({"add", "addInplace", "testAddInplaceMultipleArrayToArray", "lhs = array", "rhs = array", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultipleSliceToArray", "lhs = slice.copy()", "rhs = slice", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultipleArrayToSlice", "lhs = slice", "rhs = slice.copy()", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultipleSliceToSlice", "lhs = slice", "rhs = slice", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultipleReshapedToArray", "lhs = reshaped.copy()", "rhs = reshaped", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultipleArrayToReshaped", "lhs = reshaped", "rhs = reshaped.copy()", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultipleReshapedToReshaped", "lhs = reshaped", "rhs = reshaped", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultiplePArrayToArray", "lhs = pArray.copy()", "rhs = pArray", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultipleArrayToPArray", "lhs = pArray", "rhs = pArray.copy()", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultiplePArrayToPArray", "lhs = pArray", "rhs = pArray", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultipleMaskedToArray", "lhs = masked.copy()", "rhs = masked", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultipleArrayToMasked", "lhs = masked", "rhs = masked.copy()", " + "})
    @Replacements({"add", "addInplace", "testAddInplaceMultipleMaskedToMasked", "lhs = masked", "rhs = masked", " + "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultipleArrayFromArray", "lhs = array", "rhs = array", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultipleSliceFromArray", "lhs = slice.copy()", "rhs = slice", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultipleArrayFromSlice", "lhs = slice", "rhs = slice.copy()", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultipleSliceFromSlice", "lhs = slice", "rhs = slice", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultipleReshapedFromArray", "lhs = reshaped.copy()", "rhs = reshaped", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultipleArrayFromReshaped", "lhs = reshaped", "rhs = reshaped.copy()", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultipleReshapedFromReshaped", "lhs = reshaped", "rhs = reshaped", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultiplePArrayFromArray", "lhs = pArray.copy()", "rhs = pArray", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultipleArrayFromPArray", "lhs = pArray", "rhs = pArray.copy()", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultiplePArrayFromPArray", "lhs = pArray", "rhs = pArray", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultipleMaskedFromArray", "lhs = masked.copy()", "rhs = masked", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultipleArrayFromMasked", "lhs = masked", "rhs = masked.copy()", " - "})
    @Replacements({"subtract", "subtractInplace", "testSubtractInplaceMultipleMaskedFromMasked", "lhs = masked", "rhs = masked", " - "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultipleArrayFromArray", "lhs = array", "rhs = array", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultipleSliceByArray", "lhs = slice", "rhs = slice.copy()", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultipleArrayBySlice", "lhs = slice.copy()", "rhs = slice", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultipleSliceBySlice", "lhs = slice", "rhs = slice", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultipleReshapedByArray", "lhs = reshaped", "rhs = reshaped.copy()", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultipleArrayByReshaped", "lhs = reshaped.copy()", "rhs = reshaped", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultipleReshapedByReshaped", "lhs = reshaped", "rhs = reshaped", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultiplePArrayByArray", "lhs = pArray", "rhs = pArray.copy()", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultipleArrayByPArray", "lhs = pArray.copy()", "rhs = pArray", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultiplePArrayByPArray", "lhs = pArray", "rhs = pArray", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultipleMaskedByArray", "lhs = masked", "rhs = masked.copy()", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultipleArrayByMasked", "lhs = masked.copy()", "rhs = masked", " * "})
    @Replacements({"multiply", "multiplyInplace", "testMultiplyInplaceMultipleMaskedByMasked", "lhs = masked", "rhs = masked", " * "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultipleArrayFromArray", "lhs = array", "rhs = array", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultipleSliceByArray", "lhs = slice", "rhs = slice.copy()", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultipleArrayBySlice", "lhs = slice.copy()", "rhs = slice", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultipleSliceBySlice", "lhs = slice", "rhs = slice", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultipleReshapedByArray", "lhs = reshaped", "rhs = reshaped.copy()", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultipleArrayByReshaped", "lhs = reshaped.copy()", "rhs = reshaped", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultipleReshapedByReshaped", "lhs = reshaped", "rhs = reshaped", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultiplePArrayByArray", "lhs = pArray", "rhs = pArray.copy()", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultipleArrayByPArray", "lhs = pArray.copy()", "rhs = pArray", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultiplePArrayByPArray", "lhs = pArray", "rhs = pArray", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultipleMaskedByArray", "lhs = masked", "rhs = masked.copy()", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultipleArrayByMasked", "lhs = masked.copy()", "rhs = masked", " / "})
    @Replacements({"divide", "divideInplace", "testDivideInplaceMultipleMaskedByMasked", "lhs = masked", "rhs = masked", " / "})
    void testAddInplaceMultipleArrayToArray() {
        NDArray<Byte> lhs = array;
        NDArray<Byte> original = lhs.copy();
        NDArray<Byte> rhs = array;
        try {
            lhs.addInplace(rhs, 1.25, rhs, 3);
            for (int i = 0; i < lhs.length(); i++)
                assertSpecializedEquals(add(add(add(add(original.get(i), original.get(i)), wrapToByte(1.25)), original.get(i)), 3), lhs.get(i), getDelta());
        } catch (ArithmeticException e) {
            
        }
    }

    @Test
    @Replace(pattern = "array", replacements = {"slice", "reshaped", "pArray", "masked"})
    void testSum() {
        Byte sum = array.sum();
        Byte acc = wrapToByte(0);
        for (int i = 0; i < array.length(); i++)
            acc = add(acc, array.get(i));
        assertSpecializedEquals(acc, sum);
    }

    @Test
    void testSumByDim() {
        NDArray<Byte> sum = array.sum(1, 2);
        for (int i = 0; i < array.shape(0); i++) {
            Byte acc = wrapToByte(0);
            for (int j = 0; j < array.shape(1); j++) {
                for (int k = 0; k < array.shape(2); k++) {
                    acc = add(acc, array.get(i, j, k));
                }
            }
            assertSpecializedEquals(acc, sum.get(i));
        }
    }

}
