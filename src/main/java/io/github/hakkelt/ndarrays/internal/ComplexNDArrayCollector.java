package io.github.hakkelt.ndarrays.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.ndarrays.*;

/**
 * Collector to collect values from a stream of Complex values into the specified ComplexNDArray.
 * ComplexNDArray interface requires getCollector function to be implemented and to return a ComplexNDArrayCollector.
 */
public class ComplexNDArrayCollector<T extends Number> implements Collector<Object, List<Object>, NDArray<Complex>> {
    int[] shape;
    ComplexNDArray<T> destination;

    public ComplexNDArrayCollector(ComplexNDArray<T> destination) {
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

    @SuppressWarnings("unchecked")
    public Function<List<Object>, NDArray<Complex>> finisher() {
        return list -> {
            destination.applyWithLinearIndices(
                (value, index) -> ((AbstractNDArray<Complex,T>)destination).wrapValue(list.get(index))
            );
            return destination;
        };
    }
    
}
