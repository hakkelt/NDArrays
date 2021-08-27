package io.github.hakkelt.ndarrays;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.complex.Complex;

class ArrayOperations<T,T2 extends Number> {
    
    public NDArray<T> sum(AbstractNDArray<T,T2> me, int... selectedDims) {
        Set<Integer> dimSelection = IntStream.of(selectedDims).boxed().collect(Collectors.toSet());
        Object[] expressions = IntStream.range(0, me.ndims())
                .mapToObj(i -> dimSelection.contains(i) ? ":" : 0).toArray();
        int[] remainingDimsIndices = me.calculateRemainingDims(selectedDims);
        int[] remainingDims = IntStream.of(remainingDimsIndices).map(me::dims).toArray();
        return IntStream.range(0, AbstractNDArray.length(remainingDims))
            .mapToObj(i -> {
                T value = me.slice(expressions).sum();
                if (i < AbstractNDArray.length(remainingDims) - 1)
                    me.incrementSlicingExpression(expressions, 0, remainingDimsIndices);
                return value;
            })
            .collect(me.getCollectorInternal(remainingDims));
    }
    
    
    public NDArray<T> add(AbstractNDArray<T,T2> me, Object ...addends) {
        checkDimensionMatchBeforeCombine(me, addends, "add");
        return me.streamLinearIndices().parallel()
            .mapToObj(i -> me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.ADD, addends)).collect(me.getCollectorInternal(me.dims));
    }

    
    public NDArray<T> addInplace(AbstractNDArray<T,T2> me, Object ...addends) {
        checkDimensionMatchBeforeCombine(me, addends, "addInplace");
        me.streamLinearIndices().parallel()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.ADD, addends), i));
        return me;
    }

    
    public NDArray<T> subtract(AbstractNDArray<T,T2> me, Object ...substrahends) {
        checkDimensionMatchBeforeCombine(me, substrahends, "substract");
        return me.streamLinearIndices().parallel()
            .mapToObj(i -> me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.SUBTRACT, substrahends)).collect(me.getCollectorInternal(me.dims));
    }

    
    public NDArray<T> subtractInplace(AbstractNDArray<T,T2> me, Object ...substrahends) {
        checkDimensionMatchBeforeCombine(me, substrahends, "substractInplace");
        me.streamLinearIndices().parallel()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.SUBTRACT, substrahends), i));
        return me;
    }

    
    public NDArray<T> multiply(AbstractNDArray<T,T2> me, Object... multiplicands) {
        checkDimensionMatchBeforeCombine(me, multiplicands, "multiply");
        return me.streamLinearIndices().parallel()
            .mapToObj(i -> me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.MULTIPLY, multiplicands)).collect(me.getCollectorInternal(me.dims));
    }

    
    public NDArray<T> multiplyInplace(AbstractNDArray<T,T2> me, Object... multiplicands) {
        checkDimensionMatchBeforeCombine(me, multiplicands, "multiplyInplace");
        me.streamLinearIndices().parallel()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.MULTIPLY, multiplicands), i));
        return me;
    }

    
    public NDArray<T> divide(AbstractNDArray<T,T2> me, Object... divisors) {
        checkDimensionMatchBeforeCombine(me, divisors, "divide");
        return me.streamLinearIndices().parallel()
            .mapToObj(i -> me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.DIVIDE, divisors)).collect(me.getCollectorInternal(me.dims));
    }

    
    public NDArray<T> divideInplace(AbstractNDArray<T,T2> me, Object... divisors) {
        checkDimensionMatchBeforeCombine(me, divisors, "divideInplace");
        me.streamLinearIndices().parallel()
            .forEach(i -> me.set(me.accumulateAtIndex(i, AbstractNDArray.AccumulateOperators.DIVIDE, divisors), i));
        return me;
    }


    public NDArray<T> fill(AbstractNDArray<T,T2> me, T value) {
        me.streamLinearIndices().parallel().forEach(i -> me.set(value, i));
        return me;
    }

    
    public NDArray<T> fill(AbstractNDArray<T,T2> me, float value) {
        me.streamLinearIndices().parallel().forEach(i -> {
            me.set(value, i);
        });
        return me;
    }

    
    public NDArray<T> fill(AbstractNDArray<T,T2> me, double value) {
        me.streamLinearIndices().parallel().forEach(i -> me.set(value, i));
        return me;
    }

    
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> sum(ComplexNDArray<T2> me, int... selectedDims) {
        return (ComplexNDArray<T2>)sum((AbstractNDArray<T,T2>)me, selectedDims);
    }
    
    
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> add(ComplexNDArray<T2> me, Object ...addends) {
        return (ComplexNDArray<T2>)add((AbstractNDArray<T,T2>)me, addends);
    }

    
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> addInplace(ComplexNDArray<T2> me, Object ...addends) {
        return (ComplexNDArray<T2>)addInplace((AbstractNDArray<T,T2>)me, addends);
    }

    
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> subtract(ComplexNDArray<T2> me, Object ...substrahends) {
        return (ComplexNDArray<T2>)subtract((AbstractNDArray<T,T2>)me, substrahends);
    }

    
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> subtractInplace(ComplexNDArray<T2> me, Object ...substrahends) {
        return (ComplexNDArray<T2>)subtractInplace((AbstractNDArray<T,T2>)me, substrahends);
    }

    
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> multiply(ComplexNDArray<T2> me, Object... multiplicands) {
        return (ComplexNDArray<T2>)multiply((AbstractNDArray<T,T2>)me, multiplicands);
    }

    
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> multiplyInplace(ComplexNDArray<T2> me, Object... multiplicands) {
        return (ComplexNDArray<T2>)multiplyInplace((AbstractNDArray<T,T2>)me, multiplicands);
    }

    
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> divide(ComplexNDArray<T2> me, Object... divisors) {
        return (ComplexNDArray<T2>)divide((AbstractNDArray<T,T2>)me, divisors);
    }

    
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> divideInplace(ComplexNDArray<T2> me, Object... divisors) {
        return (ComplexNDArray<T2>)divideInplace((AbstractNDArray<T,T2>)me, divisors);
    }


    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, T2 value) {
        me.streamLinearIndices().parallel().forEach(i -> me.set(value, i));
        return me;
    }

    
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, float value) {
        return (ComplexNDArray<T2>)fill((AbstractNDArray<T,T2>)me, value);
    }

    
    @SuppressWarnings("unchecked")
    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, double value) {
        return (ComplexNDArray<T2>)fill((AbstractNDArray<T,T2>)me, value);
    }


    public ComplexNDArray<T2> fill(ComplexNDArray<T2> me, Complex value) {
        me.streamLinearIndices().parallel().forEach(i -> me.set(value, i));
        return me;
    }

    
    protected void checkDimensionMatchBeforeCombine(AbstractNDArray<T, T2> me, Object[] arrays, String funcName) {
        for (Object array : arrays) {
            if (array instanceof NDArray<?>) {
                if (!Arrays.equals(me.dims, ((NDArray<?>)array).dims()))
                    throw new IllegalArgumentException(String.format(
                        AbstractNDArray.ERROR_COMBINE_SIZE_MISMATCH,
                        Printer.dimsToString(((NDArray<?>)array).dims()),
                        Printer.dimsToString(me.dims)));
            } else {
                checkIfNumber(me, array, funcName);
            }
        }
    }
    
    protected void checkIfNumber(AbstractNDArray<T,T2> me, Object item, String funcName) {
        if (me.eltype() == Complex.class) {
            if (!(item instanceof Integer) && !(item instanceof Float) &&
                    !(item instanceof Double) && !(item instanceof Complex))
                throw new IllegalArgumentException(String.format(
                    AbstractNDArray.ERROR_COMBINE_TYPE_MISMATCH_WITH_COMPLEX, funcName, item.getClass()));
        } else {
            if (!(item instanceof Integer) && !(item instanceof Float) && !(item instanceof Double))
                throw new IllegalArgumentException(String.format(
                    AbstractNDArray.ERROR_COMBINE_TYPE_MISMATCH, funcName, item.getClass()));
        }
    }
    
}
