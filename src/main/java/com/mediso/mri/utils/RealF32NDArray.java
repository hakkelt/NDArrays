package com.mediso.mri.utils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;
import org.itk.simple.Image;

/**
 * N-dimensional arrays holding single-precision (32bit) real values.
 * 
 * The sole reason why it would be a better option than RealF64NDArray,
 * it that it uses half of the memory RealF64NDArray would require.
 * 
 */
public class RealF32NDArray extends RealNDArray<Float> {
    protected FloatBuffer data;

    protected RealF32NDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public RealF32NDArray(int... dims)
        { super(dims); }

    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter array.
     * 
     * @param real array of float values to be copied to the NDArray
     */
    public RealF32NDArray(float[] real)
        { super(real); }
    
    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter array.
     * 
     * @param real array of double values to be copied to the NDArray
     */
    public RealF32NDArray(double[] real)
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
    public RealF32NDArray(Object[] real)
        { super(real); }
    
    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of float values to be copied to the NDArray
     */
    public RealF32NDArray(int[] dims, float[] real)
        { super(dims, real); }
    
    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of double values to be copied to the NDArray
     */
    public RealF32NDArray(int[] dims, double[] real)
        { super(dims, real); }
    
    /**
     * Copy constructor.
     * 
     * @param array NDArray from which elements are copied from
     */
    public RealF32NDArray(NDArray<?> array)
        { super(array); }
    
    /**
     * Copy constructor.
     * 
     * @param array NDArray from which elements are copied from
     */
    public RealF32NDArray(RealF32NDArray array) {
        baseConstructor(array.dims());
        data.put(array.data);
        if (array.areBartDimsSpecified()) {
            bartDims = array.getBartDims();
            areBartDimsSpecified = true;
        }
    }
    
    /**
     * Conversion constructor that copies values from a Simple ITK Image object.
     * 
     * Note that only real-valued Image objects are accepted.
     * 
     * @param image a Simple ITK Image object from which values are copied from
     */
    public RealF32NDArray(Image image)
        { super(image); }

    public Float get(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data.get(linearIndex);
    }

    public String dataTypeAsString() {
        return "RealF32";
    }

    protected ByteBuffer createBuffer(int dataLength) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(dataLength * Float.BYTES * 2);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer;
    }

    @Override
    protected void baseConstructor(int[] dims) {
        this.dims = dims.clone();
        this.dataLength = length(dims);
        ByteBuffer buffer = createBuffer(this.dataLength);
        this.data = buffer.asFloatBuffer();
        this.multipliers = calculateMultipliers(dims);
    }

    @Override
    protected void baseConstructor(int[] dims, ByteBuffer buffer) {
        this.dims = dims.clone();
        this.dataLength = length(dims);
        this.data = buffer.asFloatBuffer();
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
    protected Collector<Object, List<Object>, NDArray<Float>> getCollector(int[] dims) {
        return NDArrayCollectors.toRealF32NDArray(dims);
    }

    @Override
    public NDArray<Float> similar() {
        return new RealF32NDArray(dims);
    }
    
    @Override
    protected NDArray<Float> similar(NDArray<?> array) {
        return new RealF32NDArray(array.dims());
    }

    @Override
    public NDArray<Float> copy() {
        return new RealF32NDArray(this);
    }

    @Override
    public Buffer getBuffer() {
        return data;
    }
    
    @Override
    protected NDArray<Float> copy(NDArray<?> array) {
        return new RealF32NDArray(array);
    }

    @Override
    protected AbstractNDArray<Float> createNewNDArrayOfSameTypeAsMe(int[] dims) {
        return new RealF32NDArray(dims);
    }

    @Override
    public void set(Float value, int linearIndex) {
        setReal(value, linearIndex);
    }

    protected Float accumulate(Float acc, Float value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return Float.valueOf(acc.floatValue() + value.floatValue());
            case SUBTRACT: return Float.valueOf(acc.floatValue() - value.floatValue());
            case MULTIPLY: return Float.valueOf(acc.floatValue() * value.floatValue());
            case DIVIDE: return Float.valueOf(acc.floatValue() / value.floatValue());
            default: throw new IllegalArgumentException();
        }
    }

    protected Float accumulate(Float acc, Double value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return Float.valueOf(acc.floatValue() + value.floatValue());
            case SUBTRACT: return Float.valueOf(acc.floatValue() - value.floatValue());
            case MULTIPLY: return Float.valueOf(acc.floatValue() * value.floatValue());
            case DIVIDE: return Float.valueOf(acc.floatValue() / value.floatValue());
            default: throw new IllegalArgumentException();
        }
    }

    protected Float accumulate(Float acc, Integer value, AbstractNDArray.AccumulateOperators operation) {
        switch (operation) { 
            case ADD: return Float.valueOf(acc.floatValue() + value.floatValue());
            case SUBTRACT: return Float.valueOf(acc.floatValue() - value.floatValue());
            case MULTIPLY: return Float.valueOf(acc.floatValue() * value.floatValue());
            case DIVIDE: return Float.valueOf(acc.floatValue() / value.floatValue());
            default: throw new IllegalArgumentException();
        }
    }
    @Override
    public Object eltype() {
        return Float.class;
    }

    @Override
    protected Float zeroT() {
        return Float.valueOf(0.0f);
    }
    @Override
    protected Float oneT() {
        return Float.valueOf(1.0f);
    }
    @Override
    protected Float accumulate(Float acc, Complex value, AccumulateOperators operator) {
        throw new IllegalArgumentException();
    }
    
}
