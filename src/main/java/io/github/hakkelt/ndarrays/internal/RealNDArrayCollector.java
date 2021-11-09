package io.github.hakkelt.ndarrays.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import io.github.hakkelt.ndarrays.NDArray;

/**
 * Collector to collect values from a stream of Number values into the specified NDArray.
 * NDArray interface requires getCollector function to be implemented and to return a NDArrayCollector.
 */
public class RealNDArrayCollector<T extends Number> implements Collector<Object, List<Object>, NDArray<T>> {
    int[] shape;
    NDArray<T> destination;

    public RealNDArrayCollector(NDArray<T> destination) {
        this.destination = destination;
    }

    public Supplier<List<Object>> supplier() {
        return ArrayList::new;
    }

    public BiConsumer<List<Object>, Object> accumulator() {
        return (list, value) -> list.add(value);
    }

    public BinaryOperator<List<Object>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    public Set<Characteristics> characteristics() {
        return Set.of();
    }

    public Function<List<Object>, NDArray<T>> finisher() {
        return list -> {
            destination.applyWithLinearIndices(
                (value, index) -> ((AbstractRealNDArray<T>)destination).wrapValue(list.get(index))
            );
            return destination;
        };
    }
    
}
