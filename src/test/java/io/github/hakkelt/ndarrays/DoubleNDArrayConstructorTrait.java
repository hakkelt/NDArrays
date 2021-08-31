package io.github.hakkelt.ndarrays;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collector;

public interface DoubleNDArrayConstructorTrait extends AbstractConstructorTrait {
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> createDoubleNDArray(int... dims) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getConstructor(int[].class).newInstance((Object)dims);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public default NDArray<Double> createDoubleNDArray(NDArray<?> array) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getConstructor(NDArray.class).newInstance((Object)array);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(float[] real) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", float[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(double[] real) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", double[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(byte[] real) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", byte[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(short[] real) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", short[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(int[] real) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", int[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(long[] real) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", long[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(Object[] realOr) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", Object[].class).invoke(null, (Object)realOr);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(float[] real, float[] imag) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", float[].class, float[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(double[] real, double[] imag) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", double[].class, double[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(byte[] real, byte[] imag) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", byte[].class, byte[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(short[] real, short[] imag) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", short[].class, short[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(int[] real, int[] imag) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", int[].class, int[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(long[] real, long[] imag) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", long[].class, long[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Double> getDoubleNDArrayOf(Object[] real, Object[] imag) {
        try {
            return (NDArray<Double>)getDoubleNDArrayClass().getMethod("of", Object[].class, Object[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default Collector<Object, List<Object>, NDArray<Double>> getDoubleNDArrayCollector(int... dims) {
        try {
            return (Collector<Object, List<Object>, NDArray<Double>>)getDoubleNDArrayClass().getMethod("getCollector", int[].class)
                .invoke(null, (Object)dims);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
