package io.github.hakkelt.ndarrays;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

/**
 * N-dimensional arrays holding double-precision (64bit) complex values.
 * 
 * Compared to ComplexF32NDArray, this implementation uses two times more memory,
 * but might perform better because there is no need to cast from float to double.
 * Also, it might be more convenient as Java uses double for numeric literals.
 * 
 */
public class ComplexF64NDArray extends ComplexNDArray {
    protected DoubleBuffer data;

    protected ComplexF64NDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public ComplexF64NDArray(int... dims)
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
    public ComplexF64NDArray(float[] real)
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
    public ComplexF64NDArray(double[] real)
        { super(real); }

    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter array.
     * 
     * @param compl array of complex to be copied to the NDArray
     */
    public ComplexF64NDArray(Complex[] compl)
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
    public ComplexF64NDArray(Object[] realOrComplex)
        { super(realOrComplex); }
    
    /**
     * Constructor that copies values from the arrays given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter arrays.
     * 
     * @param real array of float values which are interpreted as the real part of the elements of the NDArray
     * @param imag array of float values which are interpreted as the imaginary part of the elements of the NDArray
     */
    public ComplexF64NDArray(float[] real, float[] imag)
        { super(real, imag); }
    
    /**
     * Constructor that copies values from the arrays given as parameter.
     * 
     * The resulting NDArray is 1-dimensional, and has the same length as the parameter arrays.
     * 
     * @param real array of double values which are interpreted as the real part of the elements of the NDArray
     * @param imag array of double values which are interpreted as the imaginary part of the elements of the NDArray
     */
    public ComplexF64NDArray(double[] real, double[] imag)
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
    public ComplexF64NDArray(Object[] real, Object[] imag)
        { super(real, imag); }
    
    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of float values which are interpreted as the real part of the elements of the NDArray
     */
    public ComplexF64NDArray(int[] dims, float[] real)
        { super(dims, real); }
    
    /**
     * Constructor that copies values from the array given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of double values which are interpreted as the real part of the elements of the NDArray
     */
    public ComplexF64NDArray(int[] dims, double[] real)
        { super(dims, real); }
    
    /**
     * Constructor that copies values from the arrays given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of float values which are interpreted as the real part of the elements of the NDArray
     * @param imag array of float values which are interpreted as the imaginary part of the elements of the NDArray
     */
    public ComplexF64NDArray(int[] dims, float[] real, float[] imag)
        { super(dims, real, imag); }
    
    /**
     * Constructor that copies values from the arrays given as parameter.
     * 
     * @param dims dimensionality of the resulting NDArray
     * @param real array of double values which are interpreted as the real part of the elements of the NDArray
     * @param imag array of double values which are interpreted as the imaginary part of the elements of the NDArray
     */
    public ComplexF64NDArray(int[] dims, double[] real, double[] imag)
        { super(dims, real, imag); }
    
    /**
     * Copy constructor.
     * 
     * @param array NDArray from which elements are copied from
     */
    public ComplexF64NDArray(NDArray<?> array)
        { super(array); }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which elements are copied from
     */
    public ComplexF64NDArray(ComplexF64NDArray array) {
        baseConstructor(array.dims());
        data.put(array.data);
        if (array.areBartDimsSpecified()) {
            bartDims = array.getBartDims();
            areBartDimsSpecified = true;
        }
    }

    public Complex get(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return new Complex(data.get(linearIndex * 2), data.get(linearIndex * 2 + 1));
    }

    public String dataTypeAsString() {
        return "ComplexF64";
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
        return NDArrayCollectors.toComplexF64NDArray(dims);
    }

    @Override
    public NDArray<Complex> similar() {
        return new ComplexF64NDArray(dims);
    }
    
    @Override
    protected NDArray<Complex> similar(NDArray<?> array) {
        return new ComplexF64NDArray(array.dims());
    }

    @Override
    public NDArray<Complex> copy() {
        return new ComplexF64NDArray(this);
    }
    
    @Override
    protected NDArray<Complex> copy(NDArray<?> array) {
        return new ComplexF64NDArray(array);
    }

    @Override
    public Buffer getBuffer() {
        return data;
    }

    @Override
    protected AbstractNDArray<Complex> createNewNDArrayOfSameTypeAsMe(int[] dims) {
        return new ComplexF64NDArray(dims);
    }
    
}
