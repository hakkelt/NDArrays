package io.github.hakkelt.ndarrays;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collector;

public interface LongNDArrayConstructorTrait extends AbstractConstructorTrait {
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> createLongNDArray(int... dims) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getConstructor(int[].class).newInstance((Object)dims);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public default NDArray<Long> createLongNDArray(NDArray<?> array) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getConstructor(NDArray.class).newInstance((Object)array);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(float[] real) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", float[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(double[] real) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", double[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(byte[] real) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", byte[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(short[] real) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", short[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(int[] real) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", int[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(long[] real) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", long[].class).invoke(null, (Object)real);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(Object[] realOr) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", Object[].class).invoke(null, (Object)realOr);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(float[] real, float[] imag) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", float[].class, float[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(double[] real, double[] imag) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", double[].class, double[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(byte[] real, byte[] imag) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", byte[].class, byte[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(short[] real, short[] imag) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", short[].class, short[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(int[] real, int[] imag) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", int[].class, int[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(long[] real, long[] imag) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", long[].class, long[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default NDArray<Long> getLongNDArrayOf(Object[] real, Object[] imag) {
        try {
            return (NDArray<Long>)getLongNDArrayClass().getMethod("of", Object[].class, Object[].class)
                .invoke(null, (Object)real, (Object)imag);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public default Collector<Object, List<Object>, NDArray<Long>> getLongNDArrayCollector(int... dims) {
        try {
            return (Collector<Object, List<Object>, NDArray<Long>>)getLongNDArrayClass().getMethod("getCollector", int[].class)
                .invoke(null, (Object)dims);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
