package io.github.hakkelt.ndarrays;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collector;

public interface FloatNDArrayConstructorTrait extends AbstractConstructorTrait {
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> createFloatNDArray(int... dims) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getConstructor(int[].class).newInstance((Object)dims);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public default NDArray<Float> createFloatNDArray(NDArray<?> array) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getConstructor(NDArray.class).newInstance((Object)array);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(float[] real) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", float[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(double[] real) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", double[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(byte[] real) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", byte[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(short[] real) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", short[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(int[] real) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", int[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(long[] real) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", long[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(Object[] realOr) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", Object[].class).invoke(null, (Object)realOr);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(float[] real, float[] imag) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", float[].class, float[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(double[] real, double[] imag) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", double[].class, double[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(byte[] real, byte[] imag) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", byte[].class, byte[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(short[] real, short[] imag) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", short[].class, short[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(int[] real, int[] imag) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", int[].class, int[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(long[] real, long[] imag) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", long[].class, long[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Float> getFloatNDArrayOf(Object[] real, Object[] imag) {
        try {
            return (NDArray<Float>)getFloatNDArrayClass().getMethod("of", Object[].class, Object[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default Collector<Object, List<Object>, NDArray<Float>> getFloatNDArrayCollector(int... dims) {
        try {
            return (Collector<Object, List<Object>, NDArray<Float>>)getFloatNDArrayClass().getMethod("getCollector", int[].class)
                .invoke(null, (Object)dims);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
