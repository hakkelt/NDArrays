package com.mediso.mri.utils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;
import org.itk.simple.Image;

/**
 * N-dimensional arrays holding double-precision (64bit) real values.
 */
public class RealF64NDArray extends RealNDArray<Double> {
    protected DoubleBuffer data;

    protected RealF64NDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public RealF64NDArray(int... dims)
        { super(dims); }
        
    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter array.
     * 
     * @param real array of float values to be copied to the NDArray
     */
    public RealF64NDArray(float[] real)
        { super(real); }
        
    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter array.
     * 
     * @param real array of double values to be copied to the NDArray
     */
    public RealF64NDArray(double[] real)
        { super(real); }

    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * The resulting NDArray has the same dimensionality as the input array.
     * 
     * @param real a multidimensional primitive array of floats or doubles
     * that stores values to be copied to this NDArray as initial value
     * 
     */
    public RealF64NDArray(Object[] real)
        { super(real); }
    
    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of float values to be copied to the NDArray
     */
    public RealF64NDArray(int[] dims, float[] real)
        { super(dims, real); }
        
    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of double values to be copied to the NDArray
     */
    public RealF64NDArray(int[] dims, double[] real)
        { super(dims, real); }
    
    /**
     * Copy constructor.
     * 
     * @param array NDArray from which elements are copied from
     */
    public RealF64NDArray(NDArray<?> array)
        { super(array); }
    
    /**
     * Conversion constructor that copies values from a Simple ITK Image object.
     * 
     * Note that only real-valued Image objects are accepted.
     * 
     * @param image a Simple ITK Image object from which values are copied from
     */
    public RealF64NDArray(Image image)
        { super(image); }
    
    /**
     * Copy constructor.
     * 
     * @param array NDArray from which elements are copied from
     */
    public RealF64NDArray(RealF64NDArray array) {
        baseConstructor(array.dims());
        data.put(array.data);
        if (array.areBartDimsSpecified()) {
            bartDims = array.getBartDims();
            areBartDimsSpecified = true;
        }
    }

    public Double get(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data.get(linearIndex);
    }

    public String dataTypeAsString() {
        return "RealF64";
    }

    protected ByteBuffer createBuffer(int dataLength) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(dataLength * Double.BYTES * 2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer;
    }

    @Override
    protected void baseConstructor(int[] dims) {
        this.dims = dims.clone();
        this.dataLength = length(dims);
        ByteBuffer buffer = createBuffer(this.dataLength);
        this.data = buffer.asDoubleBuffer();
        this.multipliers = calculateMultipliers(dims);
    }

    @Override
    protected void baseConstructor(int[] dims, ByteBuffer buffer) {
        this.dims = dims.clone();
        this.dataLength = length(dims);
        this.data = buffer.asDoubleBuffer();
        this.multipliers = calculateMultipliers(dims);
    }

    @Override
    public double getReal(int linearIndex) {
        return get(linearIndex).doubleValue();
    }

    @Override
    public double getImag(int linearIndex) {
        boundaryCheck(linearIndex, length());
        return 0;
    }

    @Override
    public void setReal(float real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data.put(linearIndex, real);
    }

    @Override
    public void setReal(double real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data.put(linearIndex, (float)real);
    }
    
    @Override
    protected Collector<Object, List<Object>, NDArray<Double>> getCollector(int[] dims) {
        return NDArrayCollectors.toRealF64NDArray(dims);
    }

    @Override
    public NDArray<Double> similar() {
        return new RealF64NDArray(dims);
    }
    
    @Override
    protected NDArray<Double> similar(NDArray<?> array) {
        return new RealF64NDArray(array.dims());
    }

    @Override
    public NDArray<Double> copy() {
        return new RealF64NDArray(this);
    }
    
    @Override
    protected NDArray<Double> copy(NDArray<?> array) {
        return new RealF64NDArray(array);
    }

    @Override
    public Buffer getBuffer() {
        return data;
    }

    @Override
    protected AbstractNDArray<Double> createNewNDArrayOfSameTypeAsMe(int[] dims) {
        return new RealF64NDArray(dims);
    }

    @Override
    public void set(Double value, int linearIndex) {
        setReal(value, linearIndex);
    }

    protected Double accumulate(Double acc, Float value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return Double.valueOf(acc.floatValue() + value.floatValue());
            case SUBTRACT: return Double.valueOf(acc.floatValue() - value.floatValue());
            case MULTIPLY: return Double.valueOf(acc.floatValue() * value.floatValue());
            case DIVIDE: return Double.valueOf(acc.floatValue() / value.floatValue());
            default: throw new IllegalArgumentException();
        }
    }

    protected Double accumulate(Double acc, Double value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return Double.valueOf(acc.floatValue() + value.floatValue());
            case SUBTRACT: return Double.valueOf(acc.floatValue() - value.floatValue());
            case MULTIPLY: return Double.valueOf(acc.floatValue() * value.floatValue());
            case DIVIDE: return Double.valueOf(acc.floatValue() / value.floatValue());
            default: throw new IllegalArgumentException();
        }
    }

    protected Double accumulate(Double acc, Integer value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return Double.valueOf(acc.floatValue() + value.floatValue());
            case SUBTRACT: return Double.valueOf(acc.floatValue() - value.floatValue());
            case MULTIPLY: return Double.valueOf(acc.floatValue() * value.floatValue());
            case DIVIDE: return Double.valueOf(acc.floatValue() / value.floatValue());
            default: throw new IllegalArgumentException();
        }
    }
    @Override
    public Object eltype() {
        return Double.class;
    }

    @Override
    protected Double zeroT() {
        return Double.valueOf(0.0f);
    }
    @Override
    protected Double oneT() {
        return Double.valueOf(1.0f);
    }
    @Override
    protected Double accumulate(Double acc, Complex value, AccumulateOperators operator) {
        throw new IllegalArgumentException();
    }
    
}
