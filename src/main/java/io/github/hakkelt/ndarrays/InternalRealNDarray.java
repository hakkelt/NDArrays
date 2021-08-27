package io.github.hakkelt.ndarrays;

interface InternalRealNDArray<T extends Number> extends NDArray<T> {

    @SuppressWarnings("unchecked")
    public default NDArray<T> sum(int... selectedDims) {
        return new ArrayOperations<T,T>().sum((AbstractNDArray<T,T>)this, selectedDims);
    }
    
    
    @SuppressWarnings("unchecked")
    public default NDArray<T> add(Object ...addends) {
        return new ArrayOperations<T,T>().add((AbstractNDArray<T,T>)this, addends);
    }


    @SuppressWarnings("unchecked")
    public default NDArray<T> addInplace(Object ...addends) {
        return new ArrayOperations<T,T>().addInplace((AbstractNDArray<T,T>)this, addends);
    }

    
    @SuppressWarnings("unchecked")
    public default NDArray<T> subtract(Object ...substrahends) {
        return new ArrayOperations<T,T>().subtract((AbstractNDArray<T,T>)this, substrahends);
    }

    
    @SuppressWarnings("unchecked")
    public default NDArray<T> subtractInplace(Object ...substrahends) {
        return new ArrayOperations<T,T>().subtractInplace((AbstractNDArray<T,T>)this, substrahends);
    }

    
    @SuppressWarnings("unchecked")
    public default NDArray<T> multiply(Object... multiplicands) {
        return new ArrayOperations<T,T>().multiply((AbstractNDArray<T,T>)this, multiplicands);
    }

    
    @SuppressWarnings("unchecked")
    public default NDArray<T> multiplyInplace(Object... multiplicands) {
        return new ArrayOperations<T,T>().multiplyInplace((AbstractNDArray<T,T>)this, multiplicands);
    }

    
    @SuppressWarnings("unchecked")
    public default NDArray<T> divide(Object... divisors) {
        return new ArrayOperations<T,T>().divide((AbstractNDArray<T,T>)this, divisors);
    }

    
    @SuppressWarnings("unchecked")
    public default NDArray<T> divideInplace(Object... divisors) {
        return new ArrayOperations<T,T>().divideInplace((AbstractNDArray<T,T>)this, divisors);
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<T> fill(T value) {
        return new ArrayOperations<T,T>().fill((AbstractNDArray<T,T>)this, value);
    }

    
    @SuppressWarnings("unchecked")
    public default NDArray<T> fill(float value) {
        return new ArrayOperations<T,T>().fill((AbstractNDArray<T,T>)this, value);
    }

    
    @SuppressWarnings("unchecked")
    public default NDArray<T> fill(double value) {
        return new ArrayOperations<T,T>().fill((AbstractNDArray<T,T>)this, value);
    }


    public default NDArray<T> slice(Object ...slicingExpressions) {
        return new ViewOperations<T,T>().slice(this, slicingExpressions);
    }

    public NDArray<T> abs();

    
    public default NDArray<T> permuteDims(int... permutation) {
        return new ViewOperations<T,T>().permuteDims(this, permutation);
    }

    
    public default NDArray<T> reshape(int... newShape) {
        return new ViewOperations<T,T>().reshape(this, newShape);
    }

    
    @SuppressWarnings("unchecked")
    public default NDArray<T> concatenate(int axis, NDArray<?> ...arrays) {
        return new ViewOperations<T,T>().concatenate((AbstractNDArray<T,T>)this, axis, arrays);
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<T> selectDims(int... selectedDims) {
        return new ViewOperations<T,T>().selectDims((AbstractNDArray<T,T>)this, selectedDims);
    }
    
    
    @SuppressWarnings("unchecked")
    public default NDArray<T> dropDims(int... selectedDims) {
        return new ViewOperations<T,T>().dropDims((AbstractNDArray<T,T>)this, selectedDims);
    }
    

    @SuppressWarnings("unchecked")
    public default NDArray<T> squeeze() {
        return new ViewOperations<T,T>().squeeze((AbstractNDArray<T,T>)this);
    }


    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(float[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }


    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(double[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }


    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(byte[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }


    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(short[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }


    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(int[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }


    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(long[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }


    @SuppressWarnings("unchecked")
    public default NDArray<T> copyFrom(Object[] array) {
        return new CopyFromOperations<T,T>().copyFrom((AbstractNDArray<T,T>)this, array);
    }

}
