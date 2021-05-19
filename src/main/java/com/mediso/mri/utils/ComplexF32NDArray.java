package com.mediso.mri.utils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;
import org.itk.simple.Image;

import rs2d.spinlab.data.DataSetInterface;

/**
 * N-dimensional arrays holding single-precision (32bit) complex values.
 * 
 * For technical reasons, the values returned by get() are of type Complex and
 * this type contains two double values, so single-precision values are casted to
 * double-precision values when they are fetched from the NDArray.
 * 
 * There are two reasons to use this NDArray implementation instead of ComplexF64NDArray:
 * Save memory, and because both RS2D DataSet and all BART commands requires single precision
 * numbers. 
 * 
 */
public class ComplexF32NDArray extends ComplexNDArray {
    protected FloatBuffer data;

    protected ComplexF32NDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public ComplexF32NDArray(int... dims)
        { super(dims); }

    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter array.
     * The float values present in the array are interpreted as the real part of the elements of the NDArray,
     * while the imaginary part is set to zero.
     * 
     * @param real array of float values which are interpreted as the real part of the elements of the NDArray
     */
    public ComplexF32NDArray(float[] real)
        { super(real); }

    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter array.
     * The double values present in the array are interpreted as the real part of the elements of the NDArray,
     * while the imaginary part is set to zero.
     * 
     * @param real array of double values which are interpreted as the real part of the elements of the NDArray
     */
    public ComplexF32NDArray(double[] real)
        { super(real); }

    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter array.
     * 
     * @param compl array of complex to be copied to the NDArray
     */
    public ComplexF32NDArray(Complex[] compl)
        { super(compl); }

    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * The resulting NDArray has the same dimensionality as the input array.
     * 
     * @param realOrComplex a multidimensional primitive array of float, double, or Complex type
     * that stores values to be copied to this NDArray as initial value
     * 
     */
    public ComplexF32NDArray(Object[] realOrComplex)
        { super(realOrComplex); }
    
    /**
     * Constructor that copies values from the arrays given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter arrays.
     * 
     * @param real array of float values which are interpreted as the real part of the elements of the NDArray
     * @param imag array of float values which are interpreted as the imaginary part of the elements of the NDArray
     */
    public ComplexF32NDArray(float[] real, float[] imag)
        { super(real, imag); }

    /**
     * Constructor that copies values from the arrays given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter arrays.
     * 
     * @param real array of double values which are interpreted as the real part of the elements of the NDArray
     * @param imag array of double values which are interpreted as the imaginary part of the elements of the NDArray
     */
    public ComplexF32NDArray(double[] real, double[] imag)
        { super(real, imag); }

    /**
     * Constructor that copies values from the arrays given as parameter.
     * 
     * The resulting NDArray has the same dimensionality as the input arrays (which must have the same dimensionality).
     * 
     * @param real multidimensional array of either float or double values which are interpreted
     * as the real part of the elements of the NDArray
     * @param imag multidimensional array of either float or double values which are interpreted
     * as the imaginary part of the elements of the NDArray
     */
    public ComplexF32NDArray(Object[] real, Object[] imag)
        { super(real, imag); }

    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of float values which are interpreted as the real part of the elements of the NDArray
     */
    public ComplexF32NDArray(int[] dims, float[] real)
        { super(dims, real); }

    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of double values which are interpreted as the real part of the elements of the NDArray
     */
    public ComplexF32NDArray(int[] dims, double[] real)
        { super(dims, real); }

    /**
     * Constructor that copies values from the arrays given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of float values which are interpreted as the real part of the elements of the NDArray
     * @param imag array of float values which are interpreted as the imaginary part of the elements of the NDArray
     */
    public ComplexF32NDArray(int[] dims, float[] real, float[] imag)
        { super(dims, real, imag); }

    /**
     * Constructor that copies values from the arrays given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of double values which are interpreted as the real part of the elements of the NDArray
     * @param imag array of double values which are interpreted as the imaginary part of the elements of the NDArray
     */
    public ComplexF32NDArray(int[] dims, double[] real, double[] imag)
        { super(dims, real, imag); }
    
    /**
     * Copy constructor.
     * 
     * @param array NDArray from which elements are copied from
     */
    public ComplexF32NDArray(NDArray<?> array)
        { super(array); }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which elements are copied from
     */
    public ComplexF32NDArray(ComplexF32NDArray array) {
        baseConstructor(array.dims());
        data.put(array.data);
        if (array.areBartDimsSpecified()) {
            bartDims = array.getBartDims();
            areBartDimsSpecified = true;
        }
    }

    /**
     * Conversion constructor that copies values from an RS2D DataSet.
     * 
     * The resulting NDArray is always 5-dimensional.
     * 
     * @param dataSet an RS2D DataSet from which elements are copied from
     */
    public ComplexF32NDArray(DataSetInterface dataSet)
        { super(dataSet); }
    
    /**
     * Constructor that wraps a FloatBuffer that stores the values of the elements.
     * 
     * Note: this constructor is intended to be called within BART wrapper internally only.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param data FloatBuffer that stores the values of the elements
     */
    public ComplexF32NDArray(int[] dims, FloatBuffer data) {
        this.dims = dims;
        this.multipliers = calculateMultipliers(dims);
        this.dataLength = length(dims);
        this.data = data;
    }

    /**
     * Conversion constructor that copies values from a Simple ITK Image object.
     * 
     * Note that only real-valued Image objects are accepted, and their values
     * are interpreted as the real part of the resulting NDArray.
     * 
     * @param image a Simple ITK Image object from which values are copied from
     */
    public ComplexF32NDArray(Image image)
        { super(image); }

    /**
     * Conversion constructor that copies values from a Simple ITK Image objects.
     * 
     * Note that only real-valued Image objects are accepted.
     * 
     * @param real a Simple ITK Image object whose values are interpreted as the real
     * part of the resulting NDArray
     * @param imag a Simple ITK Image object whose values are interpreted as the imaginary
     * part of the resulting NDArray
     */
    public ComplexF32NDArray(Image real, Image imag)
        { super(real, imag); }

    public Complex get(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return new Complex(data.get(linearIndex * 2), data.get(linearIndex * 2 + 1));
    }

    public String dataTypeAsString() {
        return "ComplexF32";
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
        linearIndex = boundaryCheck(linearIndex, length());
        return data.get(linearIndex * 2);
    }

    @Override
    public double getImag(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data.get(linearIndex * 2 + 1);
    }

    @Override
    public void setReal(float real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data.put(linearIndex * 2, real);
    }

    @Override
    public void setReal(double real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data.put(linearIndex * 2, (float)real);
    }

    @Override
    public void setImag(float imag, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data.put(linearIndex * 2 + 1, imag);
    }

    @Override
    public void setImag(double imag, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data.put(linearIndex * 2 + 1, (float)imag);
    }
    
    @Override
    protected Collector<Object, List<Object>, NDArray<Complex>> getCollector(int[] dims) {
        return NDArrayCollectors.toComplexF32NDArray(dims);
    }

    @Override
    public NDArray<Complex> similar() {
        return new ComplexF32NDArray(dims);
    }
    
    @Override
    protected NDArray<Complex> similar(NDArray<?> array) {
        return new ComplexF32NDArray(array.dims());
    }

    @Override
    public NDArray<Complex> copy() {
        return new ComplexF32NDArray(this);
    }
    
    @Override
    protected NDArray<Complex> copy(NDArray<?> array) {
        return new ComplexF32NDArray(array);
    }

    @Override
    public Buffer getBuffer() {
        return data;
    }

    @Override
    protected AbstractNDArray<Complex> createNewNDArrayOfSameTypeAsMe(int[] dims) {
        return new ComplexF32NDArray(dims);
    }
    
}
