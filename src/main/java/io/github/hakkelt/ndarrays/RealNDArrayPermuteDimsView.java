package io.github.hakkelt.ndarrays;

public class RealNDArrayPermuteDimsView<T extends Number> extends AbstractNDArrayPermuteDimsView<T,T> implements InternalRealNDArray<T> {
    
    public RealNDArrayPermuteDimsView(NDArray<T> parent, int ...dimsOrder) {
        super(parent, dimsOrder);
    }


    public RealNDArrayPermuteDimsView(AbstractNDArraySliceView<T,T> parent, int ...dimsOrder) {
        super(parent, dimsOrder);
    }

    @Override
    public void set(Number value, int linearIndex) {
        set(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    public void set(Number value, int ...indices) {
        boundaryCheck(indices, dims);
        parent.set(value, permuteArray(indices, dimsOrder));
    }

    public NDArray<T> copyFrom(NDArray<?> array) {
        streamLinearIndices().parallel().forEach(i -> set(wrapValue(array.get(i)), i));
        return this;
    }

}
