package io.github.hakkelt.ndarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class to help conversion between linear indexing and Cartesian indexing,
 * and ease checks against out of boundary errors.
 */
public class IndexingOperations {

    private IndexingOperations() {}

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

    public static void boundaryCheck(int linearIndex, int length) {
        if (linearIndex < -length || linearIndex >= length)
            throw new ArrayIndexOutOfBoundsException(
                String.format(Errors.LINEAR_BOUNDS_ERROR, length, linearIndex));
    }

    public static int unwrap(int linearIndex, int length) {
        return linearIndex < 0 ? linearIndex + length : linearIndex;
    }

    public static int[] computeDims(Object[] input) {
        List<Integer> dimsList = new ArrayList<>();
        return listToArray(computeDimsInternal(dimsList, input));
    }

    protected static List<Integer> computeDimsInternal(List<Integer> dimsSoFar, Object[] input) {
        dimsSoFar.add(input.length);
        if (input[0].getClass().equals(float[].class)) {
            dimsSoFar.add(((float[])input[0]).length);
            return dimsSoFar;
        } else if (input[0].getClass().equals(double[].class)) {
            dimsSoFar.add(((double[])input[0]).length);
            return dimsSoFar;
        } else if (input[0].getClass().equals(byte[].class)) {
            dimsSoFar.add(((byte[])input[0]).length);
            return dimsSoFar;
        } else if (input[0].getClass().equals(short[].class)) {
            dimsSoFar.add(((short[])input[0]).length);
            return dimsSoFar;
        } else if (input[0].getClass().equals(int[].class)) {
            dimsSoFar.add(((int[])input[0]).length);
            return dimsSoFar;
        } else if (input[0].getClass().equals(long[].class)) {
            dimsSoFar.add(((long[])input[0]).length);
            return dimsSoFar;
        } else if (input[0].getClass().isArray())
            return computeDimsInternal(dimsSoFar, (Object[])input[0]);
        else
            throw new IllegalArgumentException(Errors.INPUT_MUST_BE_ARRAY);
    }

    protected static int[] listToArray(List<Integer> input) {
        return input.stream().mapToInt(Integer::intValue).toArray();
    }

}
