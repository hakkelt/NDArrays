package com.github.hakkelt.ndarrays;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.github.hakkelt.ndarrays.NDArrayCollectors.ComplexF32;

import org.apache.commons.math3.complex.Complex;

class ComplexNDArrayCollector extends AbstractNDArrayCollector<Complex> implements Collector<Object, List<Object>, NDArray<Complex>> {
    Class<?> dataType;

    public ComplexNDArrayCollector(Class<?> dataType, int[] dims) {
        this.dataType = dataType;
        this.dims = dims;
    }

    @Override
    public Supplier<List<Object>> supplier() {
        return generalSupplier();
    }

    @Override
    public BiConsumer<List<Object>, Object> accumulator() {
        return generalAccumulator();
    }

    @Override
    public BinaryOperator<List<Object>> combiner() {
        return generalCombiner();
    }

    public Function<List<Object>, NDArray<Complex>> finisher() {
        return list -> {
            NDArray<Complex> array = dataType == ComplexF32.class ?
                new ComplexF32NDArray(dims) :
                new ComplexF64NDArray(dims);
            for (int i = 0; i < array.length(); i++)
                array.set(wrapValue(list.get(i)), i);
            return array;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return generalCharacteristics();
    }

    @Override
    protected Complex wrapValue(Object value) {
        if (value instanceof Float)
            return new Complex((Float)value, 0);
        if (value instanceof Double)
            return new Complex((Double)value, 0);
        if (value instanceof Integer)
            return new Complex((Integer)value, 0);
        if (value instanceof Complex)
            return (Complex)value;
        throw new UnsupportedOperationException(String.format(ERROR_TYPE_MISMATCH, value.getClass()));
    }
    
}
