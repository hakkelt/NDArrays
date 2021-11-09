package io.github.hakkelt.ndarrays.internal;

import java.util.function.*;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;

/**
 * A view for a ComplexNDArray that selects values based on a specified mask.
 * When mask(...) is called for a ComplexNDArray, an instance of this class is returned.
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays/internal", newName = "ComplexNDArrayMaskView")
public class ComplexNDArrayMaskViewTemplate<T extends Number> extends AbstractNDArrayMaskView<Complex,T> implements ComplexNDArray<T> {
    
    @SuppressWarnings("unchecked")
    public ComplexNDArrayMaskViewTemplate(NDArray<Complex> parent, NDArray<?> mask, boolean isInverse) {
        super(parent instanceof AbstractNDArrayMaskView ? (AbstractNDArrayMaskView<Complex,T>)parent : parent, mask, isInverse);
    }
    
    @SuppressWarnings("unchecked")
    public ComplexNDArrayMaskViewTemplate(NDArray<Complex> parent, Predicate<Complex> func) {
        super(parent instanceof AbstractNDArrayMaskView ? (AbstractNDArrayMaskView<Complex,T>)parent : parent, func);
    }
    
    @SuppressWarnings("unchecked")
    public ComplexNDArrayMaskViewTemplate(NDArray<Complex> parent, BiPredicate<Complex,?> func, boolean withLinearIndices) {
        super(parent instanceof AbstractNDArrayMaskView ? (AbstractNDArrayMaskView<Complex,T>)parent : parent, func, withLinearIndices);
    }
    
    @Override
    @Patterns({"real", "this::getReal"})
    @Replacements({"imaginary", "this::getImag"})
    @Replacements({"abs", "i -> get(i).abs()"})
    @Replacements({"angle", "i -> get(i).getArgument()"})
    public NDArray<T> real() {
        return streamLinearIndices()
            .mapToObj(this::getReal)
            .collect(parent.getRealCollectorInternal(shape));
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> similar() {
        return (ComplexNDArray<T>)parent.createNewNDArrayOfSameTypeAsMe(shape);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> copy() {
        ComplexNDArray<T> newInstance = (ComplexNDArray<T>)parent.createNewNDArrayOfSameTypeAsMe(shape);
        return newInstance.copyFrom(this);
    }

    @Override
    @Patterns({"apply", "UnaryOperator<Complex>"})
    @Replacements({"applyWithLinearIndices", "BiFunction<Complex,Integer,Complex>"})
    @Replacements({"applyWithCartesianIndices", "BiFunction<Complex,int[],Complex>"})
    @Replacements({"fillUsingLinearIndices", "IntFunction<Complex>"})
    @Replacements({"fillUsingCartesianIndices", "Function<int[],Complex>"})
    public ComplexNDArray<T> apply(UnaryOperator<Complex> func) {
        super.apply(func);
        return this;
    }

    @Override
    @Patterns({"int linearIndex", "linearIndex", "length"})
    @Replacements({"int... indices", "indices", "shape"})
    public void set(Number value, int linearIndex) {
        NDArrayUtils.boundaryCheck(linearIndex, length());
        linearIndex = NDArrayUtils.unwrap(linearIndex, length());
        setRealUnchecked(wrapRealValue(value), linearIndex);
        setImagUnchecked(zeroT2(), linearIndex);
    }

    @Override
    @Patterns({"setReal", "int linearIndex", "linearIndex"})
    @Replacements({"setReal", "int... indices", "indices[0]"})
    @Replacements({"setImag", "int linearIndex", "linearIndex"})
    @Replacements({"setImag", "int... indices", "indices[0]"})
    public void setReal(Number value, int linearIndex) {
        super.setReal(wrapRealValue(value), linearIndex);
    }

    @Override
    @Patterns({"getRealUnchecked", "int linearIndex", "linearIndex"})
    @Replacements({"getRealUnchecked", "int... indices", "indices[0]"})
    @Replacements({"getImagUnchecked", "int linearIndex", "linearIndex"})
    @Replacements({"getImagUnchecked", "int... indices", "indices[0]"})
    protected T getRealUnchecked(int linearIndex) {
        return parent.getRealUnchecked(indexMapper.get(linearIndex));
    }

    @Override
    @Patterns({"setRealUnchecked", "int linearIndex", "linearIndex"})
    @Replacements({"setRealUnchecked", "int... indices", "indices[0]"})
    @Replacements({"setImagUnchecked", "int linearIndex", "linearIndex"})
    @Replacements({"setImagUnchecked", "int... indices", "indices[0]"})
    protected void setRealUnchecked(T value, int linearIndex) {
        parent.setRealUnchecked(value, indexMapper.get(linearIndex).intValue());
    }

    @Override
    protected Complex accumulate(Complex acc, NDArray<?> array, int linearIndex, AccumulateOperators operator) {
        return parent.accumulate(acc, array, linearIndex, operator);
    }

    @Override
    @Replace(pattern = "Float", replacements = {"Double", "Byte", "Short", "Integer", "Long"})
    protected Complex accumulate(Float acc, Number item, AccumulateOperators operator) {
        throw new UnsupportedOperationException();   
    }

    @Override
    protected Complex accumulate(Complex acc, Complex value, AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value);
            case SUBTRACT: return acc.subtract(value);
            case MULTIPLY: return acc.multiply(value);
            case DIVIDE: default: return acc.divide(value);
        }
    }

    @Override
    protected Complex accumulate(Complex acc, Number value, AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value.doubleValue());
            case SUBTRACT: return acc.subtract(value.doubleValue());
            case MULTIPLY: return acc.multiply(value.doubleValue());
            case DIVIDE: default: return acc.divide(value.doubleValue());
        }
    }
    
}
