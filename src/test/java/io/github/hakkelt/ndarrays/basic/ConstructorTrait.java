package io.github.hakkelt.ndarrays.basic;

import io.github.hakkelt.ndarrays.AbstractConstructorTrait;

interface ConstructorTrait extends AbstractConstructorTrait {
    
    @Override
    public default Class<BasicComplexFloatNDArray> getComplexFloatNDArrayClass() {
        return BasicComplexFloatNDArray.class;
    }

    @Override
    public default Class<BasicComplexDoubleNDArray> getComplexDoubleNDArrayClass() {
        return BasicComplexDoubleNDArray.class;
    }

    @Override
    public default Class<BasicFloatNDArray> getFloatNDArrayClass() {
        return BasicFloatNDArray.class;
    }

    @Override
    public default Class<BasicDoubleNDArray> getDoubleNDArrayClass() {
        return BasicDoubleNDArray.class;
    }

    @Override
    public default Class<BasicByteNDArray> getByteNDArrayClass() {
        return BasicByteNDArray.class;
    }

    @Override
    public default Class<BasicShortNDArray> getShortNDArrayClass() {
        return BasicShortNDArray.class;
    }

    @Override
    public default Class<BasicIntegerNDArray> getIntegerNDArrayClass() {
        return BasicIntegerNDArray.class;
    }

    @Override
    public default Class<BasicLongNDArray> getLongNDArrayClass() {
        return BasicLongNDArray.class;
    }

    public default String name() {
        return "basic";
    }

}
