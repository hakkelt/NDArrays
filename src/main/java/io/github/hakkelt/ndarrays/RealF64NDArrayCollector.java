package io.github.hakkelt.ndarrays;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

class RealF64NDArrayCollector extends AbstractNDArrayCollector<Double> implements Collector<Object, List<Object>, NDArray<Double>> {
    Class<?> dataType;

    public RealF64NDArrayCollector(Class<?> dataType, int[] dims) {
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

    public Function<List<Object>, NDArray<Double>> finisher() {
        return list -> {
            NDArray<Double> array = new RealF64NDArray(dims);
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
    protected Double wrapValue(Object value) {
        if (value instanceof Float)
            return ((Float)value).doubleValue();
        if (value instanceof Double)
            return (Double)value;
        if (value instanceof Integer)
            return ((Integer)value).doubleValue();
        throw new UnsupportedOperationException(String.format(ERROR_TYPE_MISMATCH, value.getClass()));
    }
    
}