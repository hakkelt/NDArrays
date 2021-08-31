package io.github.hakkelt.ndarrays;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collector;

import org.apache.commons.math3.complex.Complex;

public interface ComplexDoubleNDArrayConstructorTrait extends AbstractConstructorTrait {
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> createComplexDoubleNDArray(int... dims) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getConstructor(int[].class).newInstance((Object)dims);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> createComplexDoubleNDArray(NDArray<?> array) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getConstructor(NDArray.class).newInstance((Object)array);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(float[] real) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", float[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(double[] real) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", double[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(byte[] real) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", byte[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(short[] real) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", short[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(int[] real) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", int[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(long[] real) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", long[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(Object[] realOrComplex) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", Object[].class).invoke(null, (Object)realOrComplex);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(float[] real, float[] imag) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", float[].class, float[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(double[] real, double[] imag) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", double[].class, double[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(byte[] real, byte[] imag) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", byte[].class, byte[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(short[] real, short[] imag) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", short[].class, short[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(int[] real, int[] imag) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", int[].class, int[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(long[] real, long[] imag) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", long[].class, long[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default ComplexNDArray<Double> getComplexDoubleNDArrayOf(Object[] real, Object[] imag) {
        try {
            return (ComplexNDArray<Double>)getComplexDoubleNDArrayClass().getMethod("of", Object[].class, Object[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default Collector<Object, List<Object>, NDArray<Complex>> getComplexDoubleNDArrayCollector(int... dims) { 
        try {
            return (Collector<Object, List<Object>, NDArray<Complex>>)getComplexDoubleNDArrayClass().getMethod("getCollector", int[].class)
                .invoke(null, (Object)dims);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
