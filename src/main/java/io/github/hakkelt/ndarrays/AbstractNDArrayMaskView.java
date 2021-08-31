package io.github.hakkelt.ndarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

abstract class AbstractNDArrayMaskView<T,T2 extends Number> extends AbstractNDArrayView<T,T2> {
    List<Integer> indexMapper = new ArrayList<>();

    protected AbstractNDArrayMaskView() {}
    
    @SuppressWarnings("unchecked")
    protected AbstractNDArrayMaskView(NDArray<T> parent, NDArray<?> mask, boolean isInverse) {
        if (!Arrays.equals(parent.dims(), mask.dims()))
            throw new IllegalArgumentException(String.format(
                Errors.MASK_DIMENSION_MISMATCH, Printer.dimsToString(parent.dims()), Printer.dimsToString(mask.dims())));
        if (parent instanceof AbstractNDArrayMaskView) {
            this.parent = ((AbstractNDArrayMaskView<T,T2>)parent).parent;
            mask.streamLinearIndices()
                .filter(i -> isInverse ^ !this.parent.wrapValue(mask.get(i)).equals(this.parent.zeroT()))
                .forEach(i -> indexMapper.add(((AbstractNDArrayMaskView<T2,T2>)parent).indexMapper.get(i)));
        } else {
            this.parent = (AbstractNDArray<T,T2>)parent;
            mask.streamLinearIndices()
                .filter(i -> isInverse ^ !this.parent.wrapValue(mask.get(i)).equals(this.parent.zeroT()))
                .forEach(i -> indexMapper.add(i));
        }
        this.dataLength = indexMapper.size();
        this.dims = new int[]{ dataLength };
        this.multipliers = calculateMultipliers(this.dims);
    }
    
    @SuppressWarnings("unchecked")
    protected AbstractNDArrayMaskView(NDArray<T> parent, Predicate<T> func) {
        if (parent instanceof AbstractNDArrayMaskView) {
            this.parent = ((AbstractNDArrayMaskView<T,T2>)parent).parent;
            parent.streamLinearIndices()
                .filter(i -> func.test(parent.get(i)))
                .forEach(i -> indexMapper.add(((AbstractNDArrayMaskView<T,T2>)parent).indexMapper.get(i)));
        } else {
            this.parent = (AbstractNDArray<T,T2>)parent;
            parent.streamLinearIndices()
                .filter(i -> func.test(parent.get(i)))
                .forEach(i -> indexMapper.add(i));
        }
        this.dataLength = indexMapper.size();
        this.dims = new int[]{ dataLength };
        this.multipliers = calculateMultipliers(this.dims);
    }
    
    @SuppressWarnings("unchecked")
    protected AbstractNDArrayMaskView(NDArray<T> parent, BiPredicate<T,?> func, boolean withLinearIndices) {
        if (parent instanceof AbstractNDArrayMaskView) {
            this.parent = ((AbstractNDArrayMaskView<T,T2>)parent).parent;
            if (withLinearIndices)
                parent.streamLinearIndices()
                    .filter(i -> ((BiPredicate<T,Integer>)func).test(parent.get(i), i))
                    .forEach(i -> indexMapper.add(((AbstractNDArrayMaskView<T,T2>)parent).indexMapper.get(i)));
            else
                parent.streamCartesianIndices()
                    .filter(indices -> ((BiPredicate<T,int[]>)func).test(parent.get(indices), indices))
                    .mapToInt(indices -> this.parent.resolveIndices(indices))
                    .forEach(i -> indexMapper.add(((AbstractNDArrayMaskView<T,T2>)parent).indexMapper.get(i)));
        } else {
            this.parent = (AbstractNDArray<T,T2>)parent;
            if (withLinearIndices)
                parent.streamLinearIndices()
                    .filter(i -> ((BiPredicate<T,Integer>)func).test(parent.get(i), i))
                    .forEach(i -> indexMapper.add(i));
            else
                parent.streamCartesianIndices()
                    .filter(indices -> ((BiPredicate<T,int[]>)func).test(parent.get(indices), indices))
                    .mapToInt(indices -> this.parent.resolveIndices(indices))
                    .forEach(i -> indexMapper.add(i));
        }
        this.dataLength = indexMapper.size();
        this.dims = new int[]{ dataLength };
        this.multipliers = calculateMultipliers(this.dims);
    }

    @Override
    public T get(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return parent.get(indexMapper.get(linearIndex));
    }

    @Override
    public T get(int... indices) {
        indices = boundaryCheck(indices, dims());
        return parent.get(indexMapper.get(indices[0]));
    }

    @Override
    public void set(T value, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        parent.set(value, indexMapper.get(linearIndex));
    }

    @Override
    public void set(T value, int... indices) {
        indices = boundaryCheck(indices, dims());
        parent.set(value, indexMapper.get(indices[0]));
    }

    @Override
    protected String printItem(int linearIndex, String format) {
        linearIndex = boundaryCheck(linearIndex, length());
        return parent.printItem(indexMapper.get(linearIndex), format);
    }

}
