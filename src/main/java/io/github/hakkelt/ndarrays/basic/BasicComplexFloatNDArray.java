package io.github.hakkelt.ndarrays.basic;

import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

import io.github.hakkelt.ndarrays.AbstractComplexNDArray;
import io.github.hakkelt.ndarrays.ComplexNDArray;
import io.github.hakkelt.ndarrays.ComplexNDArrayCollector;
import io.github.hakkelt.ndarrays.NDArray;

/**
 * N-dimensional arrays holding single-precision (32bit) complex values.
 * 
 * For technical reasons, the values returned by get() are of type Complex and
 * this type contains two double values, so single-precision values are casted to
 * double-precision values when they are fetched from the NDArray.
 * 
 */
public class BasicComplexFloatNDArray extends AbstractComplexNDArray<Float> {
    protected float[] data;

    protected BasicComplexFloatNDArray() {}

    /**
     * Simple constructor that defines only the shape of the NDArray and fills it with zeros.
     * 
     * @param dims dimensions / shape of the NDArray
     */
    public BasicComplexFloatNDArray(int... dims) {
        baseConstuctor(dims);
        this.data = new float[length() * 2];
    }

    /**
     * Copy constructor.
     * 
     * @param array NDArray from which entries are copied from.
     */
    public BasicComplexFloatNDArray(NDArray<?> array) {
        baseConstuctor(array.dims());
        this.data = new float[length() * 2];
        copyFrom(array);
    }

    public static ComplexNDArray<Float> of(float[] array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Float> of(double[] array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Float> of(byte[] array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Float> of(short[] array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Float> of(int[] array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Float> of(long[] array) {
        return new BasicComplexFloatNDArray(array.length).copyFrom(array);
    }
    
    public static ComplexNDArray<Float> of(Object[] realOrComplex) {
        return new BasicComplexFloatNDArray(computeDims(realOrComplex)).copyFrom(realOrComplex);
    }
    
    public static ComplexNDArray<Float> of(float[] real, float[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Float> of(double[] real, double[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Float> of(byte[] real, byte[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Float> of(short[] real, short[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Float> of(int[] real, int[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Float> of(long[] real, long[] imag) {
        return new BasicComplexFloatNDArray(real.length).copyFrom(real, imag);
    }
    
    public static ComplexNDArray<Float> of(Object[] real, Object[] imag) {
        return new BasicComplexFloatNDArray(computeDims(real)).copyFrom(real, imag);
    }

    protected String name() {
        return "basic";
    }

    public ComplexNDArray<Float> copyFrom(BasicComplexFloatNDArray array) {
        data = array.data.clone();
        return this;
    }

    public Float getReal(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data[linearIndex * 2];
    }

    public Float getImag(int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        return data[linearIndex * 2 + 1];
    }

    public void setReal(Number real, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data[linearIndex * 2] = real.floatValue();
    }

    public void setImag(Number imag, int linearIndex) {
        linearIndex = boundaryCheck(linearIndex, length());
        data[linearIndex * 2 + 1] = imag.floatValue();
    }

    public static Collector<Object, List<Object>, NDArray<Complex>> getCollector(int[] dims) {
        return new ComplexNDArrayCollector<>(new BasicComplexFloatNDArray(dims));
    }

    protected ComplexNDArray<Float> createNewNDArrayOfSameTypeAsMe(int... dims) {
        return new BasicComplexFloatNDArray(dims);
    }

    protected NDArray<Float> createNewRealNDArrayOfSameTypeAsMe(int... dims) {
        return new BasicFloatNDArray(dims);
    }
    
}
