package io.github.hakkelt.ndarrays.internal;

import java.util.function.*;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;

/**
 * A view for a ComplexNDArray that changes the shape of the parent NDArray.
 * When reshape(...) is called for a ComplexNDArray, an instance of this class is returned.
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays/internal", newName = "ComplexNDArrayReshapeView")
public class ComplexNDArrayReshapeViewTemplate<T extends Number> extends AbstractNDArrayReshapeView<Complex,T> implements ComplexNDArray<T> {
    
    public ComplexNDArrayReshapeViewTemplate(NDArray<Complex> parent, int ...newShape) {
        super(parent, newShape);
    }

    @Patterns({"mapOnComplexSlices", "applyOnSlices", "BiFunction<ComplexNDArray<T>,int[],NDArray<?>>", "copy()"})
    @Replacements({"applyOnComplexSlices", "applyOnSlices", "BiFunction<ComplexNDArray<T>,int[],NDArray<?>>", "this"})
    @Replacements({"reduceComplexSlices", "reduceComplexSlices", "BiFunction<ComplexNDArray<T>,int[],Complex>", "this"})
    @Override
    public ComplexNDArray<T> mapOnComplexSlices(BiFunction<ComplexNDArray<T>,int[],NDArray<?>> func, int... iterationDims) {
        return SliceOperations.applyOnSlices(copy(), func, iterationDims);
    }
    
    @Override
    @Patterns({"real", "this::getReal"})
    @Replacements({"imaginary", "this::getImag"})
    @Replacements({"abs", "i -> get(i).abs()"})
    @Replacements({"argument", "i -> get(i).getArgument()"})
    public NDArray<T> real() {
        return streamLinearIndices()
            .mapToObj(this::getReal)
            .collect(parent.getRealCollectorInternal(shape));
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T> similar() {
        return ((ComplexNDArray<T>)parent.createNewNDArrayOfSameTypeAsMe(shape));
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
    @Replace(pattern = "setReal", replacements = "setImag")
    public void setReal(Number value, int linearIndex) {
        super.setReal(wrapRealValue(value), linearIndex);
    }

    @Override
    @Replace(pattern = "setReal", replacements = "setImag")
    public void setReal(Number value, int... indices) {
        super.setReal(wrapRealValue(value), indices);
    }

    @Override
    @Replace(pattern = "getRealUnchecked", replacements = "getImagUnchecked")
    protected T getRealUnchecked(int linearIndex) {
        return parent.getRealUnchecked(linearIndex);
    }

    @Override
    @Replace(pattern = "getRealUnchecked", replacements = "getImagUnchecked")
    protected T getRealUnchecked(int ...indices) {
        return parent.getRealUnchecked(NDArrayUtils.cartesianIndicesToLinearIndex(indices, multipliers));
    }

    @Override
    @Replace(pattern = "setRealUnchecked", replacements = "setImagUnchecked")
    protected void setRealUnchecked(T value, int linearIndex) {
        parent.setRealUnchecked(value, linearIndex);
    }

    @Override
    @Replace(pattern = "setRealUnchecked", replacements = "setImagUnchecked")
    protected void setRealUnchecked(T value, int ...indices) {
        parent.setRealUnchecked(value, NDArrayUtils.cartesianIndicesToLinearIndex(indices, multipliers));
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
            case DIVIDE: return acc.divide(value);
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    protected Complex accumulate(Complex acc, Number value, AccumulateOperators operation) {
        switch (operation) {
            case ADD: return acc.add(value.doubleValue());
            case SUBTRACT: return acc.subtract(value.doubleValue());
            case MULTIPLY: return acc.multiply(value.doubleValue());
            case DIVIDE: return acc.divide(value.doubleValue());
            default: throw new IllegalArgumentException();
        }
    }
    
}
