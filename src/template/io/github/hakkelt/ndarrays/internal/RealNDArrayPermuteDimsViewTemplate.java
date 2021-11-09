package io.github.hakkelt.ndarrays.internal;

import io.github.hakkelt.generator.*;
import io.github.hakkelt.ndarrays.*;

/**
 * A view for an NDArray holding real values that permutes the order of dimensions.
 * When permuteDims(...) is called for a real NDArray, an instance of this class is returned.
 */
@ClassTemplate(outputDirectory = "main/java/io/github/hakkelt/ndarrays/internal", newName = "RealNDArrayPermuteDimsView")
public class RealNDArrayPermuteDimsViewTemplate<T extends Number> extends AbstractNDArrayPermuteDimsView<T,T> implements RealNDArray<T> {
    
    public RealNDArrayPermuteDimsViewTemplate(NDArray<T> parent, int ...dimsOrder) {
        super(parent, dimsOrder);
    }

    @Override
    public NDArray<T> copyFrom(NDArray<?> array) {
        return new CopyFromOperations<T,T>().copyFrom(this, array);
    }

    @Override
    @Patterns({"int linearIndex", "linearIndex", "length"})
    @Replacements({"int... indices", "indices", "shape"})
    public void set(Number value, int linearIndex) {
        NDArrayUtils.boundaryCheck(linearIndex, length());
        setUnchecked(wrapValue(value), NDArrayUtils.unwrap(linearIndex, length()));
    }

    @Override
    @Patterns({"getRealUnchecked", "int linearIndex"})
    @Replacements({"getRealUnchecked", "int... indices"})
    @Replacements({"getImagUnchecked", "int linearIndex"})
    @Replacements({"getImagUnchecked", "int... indices"})
    protected T getRealUnchecked(int linearIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Patterns({"setRealUnchecked", "int linearIndex"})
    @Replacements({"setRealUnchecked", "int... indices"})
    @Replacements({"setImagUnchecked", "int linearIndex"})
    @Replacements({"setImagUnchecked", "int... indices"})
    protected void setRealUnchecked(T value, int linearIndex) {
        throw new UnsupportedOperationException();
    }

}
