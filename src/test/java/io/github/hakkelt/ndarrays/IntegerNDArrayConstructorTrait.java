package io.github.hakkelt.ndarrays;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collector;

public interface IntegerNDArrayConstructorTrait extends AbstractConstructorTrait {
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> createIntegerNDArray(int... dims) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getConstructor(int[].class).newInstance((Object)dims);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public default NDArray<Integer> createIntegerNDArray(NDArray<?> array) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getConstructor(NDArray.class).newInstance((Object)array);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(float[] real) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", float[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(double[] real) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", double[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(byte[] real) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", byte[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(short[] real) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", short[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(int[] real) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", int[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(long[] real) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", long[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(Object[] realOr) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", Object[].class).invoke(null, (Object)realOr);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(float[] real, float[] imag) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", float[].class, float[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(double[] real, double[] imag) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", double[].class, double[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(byte[] real, byte[] imag) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", byte[].class, byte[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(short[] real, short[] imag) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", short[].class, short[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(int[] real, int[] imag) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", int[].class, int[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(long[] real, long[] imag) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", long[].class, long[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Integer> getIntegerNDArrayOf(Object[] real, Object[] imag) {
        try {
            return (NDArray<Integer>)getIntegerNDArrayClass().getMethod("of", Object[].class, Object[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default Collector<Object, List<Object>, NDArray<Integer>> getIntegerNDArrayCollector(int... dims) {
        try {
            return (Collector<Object, List<Object>, NDArray<Integer>>)getIntegerNDArrayClass().getMethod("getCollector", int[].class)
                .invoke(null, (Object)dims);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
