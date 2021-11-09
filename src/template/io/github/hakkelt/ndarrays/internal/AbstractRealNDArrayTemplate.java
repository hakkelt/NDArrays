package io.github.hakkelt.ndarrays.internal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.NDArray;
import io.github.hakkelt.ndarrays.NDArrayUtils;

@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays/internal", newName = "AbstractRealNDArray")
public abstract class AbstractRealNDArrayTemplate<T extends Number> extends AbstractNDArray<T,T> implements RealNDArray<T> {

    @Override
    public String dataTypeAsString() {
        return ((Class<?>) dtype2()).getSimpleName();
    }

    @Override
    public NDArray<T> copyFrom(NDArray<?> array) {
        NDArrayUtils.checkShapeCompatibility(this, array.shape());
        return new CopyFromOperations<T,T>().copyFrom(this, array);
    }

    @Override
    @Patterns({"T", "getRealUnchecked", "int linearIndex"})
    @Replacements({"T", "getRealUnchecked", "int... indices"})
    @Replacements({"T", "getImagUnchecked", "int linearIndex"})
    @Replacements({"T", "getImagUnchecked", "int... indices"})
    @Replacements({"void", "setRealUnchecked", "T value, int linearIndex"})
    @Replacements({"void", "setRealUnchecked", "T value, int... indices"})
    @Replacements({"void", "setImagUnchecked", "T value, int linearIndex"})
    @Replacements({"void", "setImagUnchecked", "T value, int... indices"})
    protected T getRealUnchecked(int linearIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String printItem(int index, String format) {
        return String.format(format, get(index));
    }

    @Override
    @Replace(pattern = "Float", replacements = {"Double", "Byte", "Short", "Integer", "Long", "BigInteger", "BigDecimal"})
    protected T accumulate(Float acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        return accumulate(acc, (Number)array.get(linearIndex), operation);
    }

    @Override
    protected T accumulate(Complex acc, NDArray<?> array, int linearIndex, AccumulateOperators operation) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Patterns({"Byte", "byteValue"})
    @Replacements({"Short", "shortValue"})
    @Replacements({"Integer", "intValue"})
    @Replacements({"Long", "longValue"})
    @Replacements({"Float", "floatValue"})
    @Replacements({"Double", "doubleValue"})
    protected T accumulate(Byte acc, Number value, AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.byteValue() + value.byteValue());
            case SUBTRACT: return wrapValue(acc.byteValue() - value.byteValue());
            case MULTIPLY: return wrapValue(acc.byteValue() * value.byteValue());
            case DIVIDE: default: return wrapValue(acc.byteValue() / value.byteValue());
        }
    }

    protected BigInteger toBigInteger(Number value) {
        if (value instanceof BigInteger)
            return (BigInteger) value;
        if (value instanceof BigDecimal)
            return ((BigDecimal) value).toBigInteger();
        return BigDecimal.valueOf(value.doubleValue()).toBigInteger();
    }

    @Override
    protected T accumulate(BigInteger acc, Number value, AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.add(toBigInteger(value)));
            case SUBTRACT: return wrapValue(acc.subtract(toBigInteger(value)));
            case MULTIPLY: return wrapValue(acc.multiply(toBigInteger(value)));
            case DIVIDE: default: return wrapValue(acc.divide(toBigInteger(value)));
        }
    }

    protected BigDecimal toBigDecimal(Number value) {
        if (value instanceof BigInteger)
            return new BigDecimal((BigInteger) value);
        if (value instanceof BigDecimal)
            return (BigDecimal) value;
        return BigDecimal.valueOf(value.doubleValue());
    }

    @Override
    protected T accumulate(BigDecimal acc, Number value, AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return wrapValue(acc.add(toBigDecimal(value)));
            case SUBTRACT: return wrapValue(acc.subtract(toBigDecimal(value)));
            case MULTIPLY: return wrapValue(acc.multiply(toBigDecimal(value)));
            case DIVIDE: default: return wrapValue(acc.divide(toBigDecimal(value)));
        }
    }
    
    @Override
    @Replace(pattern = "Number", replacements = "Complex")
    protected T accumulate(Complex acc, Number value, AccumulateOperators operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected AbstractRealNDArrayTemplate<T> createNewRealNDArrayOfSameTypeAsMe(int... shape) {
        return (AbstractRealNDArrayTemplate<T>)createNewNDArrayOfSameTypeAsMe(shape);
    }

    @Override
    protected T wrapRealValue(Number value) {
        return wrapValue(value);
    }

    @Override
    protected T zeroT2() {
        return zeroT();
    }

    @Override
    protected long countNonZero() {
        return stream().filter(item -> item.doubleValue() != 0.).count();
    }
    
    @Override
    protected Class<?> dtype2() {
        return dtype();
    }
    
    @Override
    protected Collector<Object, List<Object>, NDArray<T>> getCollectorInternal(int[] shape) {
        return new RealNDArrayCollector<>(createNewNDArrayOfSameTypeAsMe(shape));
    }

    @Override
    protected Collector<Object, List<Object>, NDArray<T>> getRealCollectorInternal(int[] shape) {
        return getCollectorInternal(shape);
    }

}