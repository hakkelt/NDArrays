package com.mediso.mri.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector.Characteristics;

abstract class AbstractNDArrayCollector<T> {
    int[] dims;

    static final String ERROR_TYPE_MISMATCH = "Cannot collect type %s!";

    protected abstract T wrapValue(Object value);

    public Supplier<List<Object>> generalSupplier() {
        return ArrayList<Object>::new;
    }

    public BiConsumer<List<Object>, Object> generalAccumulator() {
        return (list, value) -> list.add(wrapValue(value));
    }

    public BinaryOperator<List<Object>> generalCombiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    public Set<Characteristics> generalCharacteristics() {
        return Set.of();
    }
    
}
