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

    public static int cartesianIndicesToLinearIndex(int[] indices, int[] dims, int[] multipliers) {
        indices = boundaryCheck(indices, dims);
        int internalIndex = 0;
        for (int i = 0; i < indices.length; i++)
            internalIndex += indices[i] * multipliers[i];
        return internalIndex;
    }

    public static int[] linearIndexToCartesianIndices(int linearIndex, int[] multipliers, int ndims, int length) {
        linearIndex = boundaryCheck(linearIndex, length);
        int[] indices = new int[ndims];
        for (int i = ndims - 1; i >=0; i--) {
            indices[i] = linearIndex / multipliers[i];
            linearIndex -= indices[i] * multipliers[i];
        }
        return indices;
    }

    public static int[] boundaryCheck(int[] indices, int[] dims) {
        indices = indices.clone();
        if (indices.length != dims.length)
            throw new IllegalArgumentException(
                String.format(Errors.DIMENSION_MISMATCH, indices.length, dims.length));
        for (int i = 0; i < indices.length; i++) {
            if (indices[i] < -dims[i] || indices[i] >= dims[i])
                throw new ArrayIndexOutOfBoundsException(
                    String.format(Errors.CARTESIAN_BOUNDS_ERROR, Printer.dimsToString(dims), Arrays.toString(indices)));
            if (indices[i] < 0)
                indices[i] += dims[i];
        }
        return indices;
    }

    public static int boundaryCheck(int linearIndex, int length) {
        if (linearIndex < -length || linearIndex >= length)
            throw new ArrayIndexOutOfBoundsException(
                String.format(Errors.LINEAR_BOUNDS_ERROR, length, linearIndex));
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
