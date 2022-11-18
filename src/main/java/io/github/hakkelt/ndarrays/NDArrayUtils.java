package io.github.hakkelt.ndarrays;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import io.github.hakkelt.ndarrays.internal.Errors;
import io.github.hakkelt.ndarrays.internal.Printer;

/**
 * Utility class to help conversion between linear indexing and Cartesian indexing,
 * and ease checks against "out of bounds" and "incompatible shape" errors.
 */
public class NDArrayUtils {

    /*
     * Number of element over which streamLinearIndices produces a parallel stream by default.
     */
    public static final int PARALLEL_STREAM_THRESHOLD = 10000;

    private NDArrayUtils() {}

    public static void checkShapeCompatibility(NDArray<?> me, int[] arrayDims) {
        if (!Arrays.equals(me.shape(), arrayDims))
            throw new IllegalArgumentException(String.format(
                Errors.INCOMPATIBLE_SHAPE, Printer.dimsToString(arrayDims), Printer.dimsToString(me.shape())));
    }

    public static void checkDTypeCompatibility(NDArray<?> me, Object array) {
        Class<?> arrayDType = getDType(array);
        if (!arrayDType.equals(byte.class) && !arrayDType.equals(short.class) && !arrayDType.equals(int.class)
                && !arrayDType.equals(long.class) && !arrayDType.equals(float.class) && !arrayDType.equals(double.class)
                && !arrayDType.equals(Byte.class) && !arrayDType.equals(Short.class) && !arrayDType.equals(Integer.class)
                && !arrayDType.equals(Long.class) && !arrayDType.equals(Float.class) && !arrayDType.equals(Double.class)
                && !arrayDType.equals(BigInteger.class) && !arrayDType.equals(BigDecimal.class) && !arrayDType.equals(me.dtype()))
            throw new IllegalArgumentException(String.format(Errors.UNSUPPORTED_TYPE, arrayDType.getSimpleName()));
    }

    public static int calculateLength(int[] shape) {
        return IntStream.of(shape).reduce(1, (a,b) -> a * b);
    }

    public static int[] calculateMultipliers(int[] shape) {
        int arrayshape = 1;
        int[] multipliers = new int[shape.length];
        for (int idx = 0; idx < shape.length; idx++) {
            multipliers[idx] = arrayshape;
            arrayshape *= shape[idx];
        }
        return multipliers;
    }

    public static int cartesianIndicesToLinearIndex(int[] indices, int[] multipliers) {
        int internalIndex = 0;
        for (int i = 0; i < indices.length; i++)
            internalIndex += indices[i] * multipliers[i];
        return internalIndex;
    }

    public static int[] linearIndexToCartesianIndices(int linearIndex, int[] multipliers) {
        int[] indices = new int[multipliers.length];
        for (int i = multipliers.length - 1; i >=0; i--) {
            indices[i] = linearIndex / multipliers[i];
            linearIndex -= indices[i] * multipliers[i];
        }
        return indices;
    }

    public static void boundaryCheck(int[] indices, int[] shape) {
        indices = indices.clone();
        if (indices.length != shape.length)
            throw new IllegalArgumentException(
                String.format(Errors.DIMENSION_MISMATCH, indices.length, shape.length));
        for (int i = 0; i < indices.length; i++)
            if (indices[i] < -shape[i] || indices[i] >= shape[i])
                throw new ArrayIndexOutOfBoundsException(
                    String.format(Errors.CARTESIAN_BOUNDS_ERROR, Printer.dimsToString(shape), Arrays.toString(indices)));
    }

    public static int[] unwrap(int[] indices, int[] shape) {
        for (int i = 0; i < indices.length; i++)
            if (indices[i] < 0)
                indices[i] += shape[i];
        return indices;
    }

    public static int unwrap(int linearIndex, int length) {
        return linearIndex < 0 ? linearIndex + length : linearIndex;
    }

    public static void boundaryCheck(int linearIndex, int length) {
        if (linearIndex < -length || linearIndex >= length)
            throw new ArrayIndexOutOfBoundsException(
                String.format(Errors.LINEAR_BOUNDS_ERROR, length, linearIndex));
    }

    public static int[] computeDims(Object[] input) {
        List<Integer> dimsList = new ArrayList<>();
        return listToArray(computeDimsInternal(dimsList, input));
    }

    protected static List<Integer> computeDimsInternal(List<Integer> dimsSoFar, Object array) {
        Class<?> arrayClass = array.getClass();
        while (arrayClass.isArray()) {
            dimsSoFar.add(Array.getLength(array));
            array = Array.get(array, 0);
            arrayClass = arrayClass.getComponentType();
        }
        return dimsSoFar;
    }

    protected static int[] listToArray(List<Integer> input) {
        return input.stream().mapToInt(Integer::intValue).toArray();
    }

    public static Class<?> getDType(Object array) {
        Class<?> dtype = array.getClass();
        while (dtype.isArray())
            dtype = dtype.getComponentType();
        return dtype;
    }

    public static int[] getEndCartesianIndex(int[] shape) {
        int[] end = shape.clone();
        for (int i = 0; i < shape.length; i++)
            end[i]--;
        return end;
    }

}
