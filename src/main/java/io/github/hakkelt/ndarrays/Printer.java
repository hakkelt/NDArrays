package io.github.hakkelt.ndarrays;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

class Printer {

    static final String DEFAULT_INTEGER_FORMAT = "%6d";
    static final String DEFAULT_FLOATING_POINT_FORMAT = "%10.5e";

    private Printer() {}

    public static String contentToString(
            Supplier<String> dataTypeAsString,
            BiFunction<Integer, String, String> printItem,
            String name,
            int[] dims,
            int[] multipliers) {
        String dataType = dataTypeAsString.get();
        String str = name + " NDArray<" + dataType + ">(" + dimsToString(dims) + ")" + System.lineSeparator();
        String format = dataType.equals("Byte") || dataType.equals("Short") || dataType.equals("Integer") || dataType.equals("Long") ?
            DEFAULT_INTEGER_FORMAT : DEFAULT_FLOATING_POINT_FORMAT;
        if (dims.length == 1)
            str += printRow(0, dims[0], 1, format, printItem);
        else if (dims.length == 2)
            str += printMatrix(0, dims[1], dims[0], format, printItem);
        else
            str += printNDArray(dims.length - 1, new int[dims.length - 2], printItem, format, dims, multipliers);
        return str;
    }
    
    static String dimsToString(int[] dims) {
        return String.join(" × ", IntStream.of(dims).mapToObj(item -> "" + item).collect(Collectors.toList()));
    }

    static String printVector(int[] indices) {
        return "[" + StringUtils.join(ArrayUtils.toObject(indices), ", ") + "]";
    }
    
    static String printRow(int startIndex, int width, int height, String format, BiFunction<Integer, String, String> printItem) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < width; i++) {
            str.append(printItem.apply(startIndex + i * height, format));
            str.append("\t");
        }
        return str.toString();
    }

    static String printMatrix(int startIndex, int width, int height, String format, BiFunction<Integer, String, String> printItem) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < height; i++) {
            str.append(printRow(startIndex + i, width, height, format, printItem));
            str.append(System.lineSeparator());
        }
        return str.toString();
    }

    private static String printIndices(int[] indices) {
        return "[:, :, " + StringUtils.join(ArrayUtils.toObject(indices), ", ") + "]";
    }

    static String printNDArray(
            int dimension,
            int[] indices,
            BiFunction<Integer, String, String> printItem,
            String format,
            int[] dims,
            int[] multipliers) {
        StringBuilder str = new StringBuilder();
        for (indices[dimension - 2] = 0; indices[dimension - 2] < dims[dimension]; indices[dimension - 2]++) {
            if (dimension == 2) {
                str.append(printIndices(indices));
                str.append(" =" + System.lineSeparator());
                int[] startIndices = new int[dims.length];
                for (int j = 2; j < dims.length; j++)
                    startIndices[j] = indices[j - 2];
                str.append(printMatrix(IndexingOperations.cartesianIndicesToLinearIndex(startIndices, dims, multipliers), dims[1], dims[0], format, printItem));
                str.append(System.lineSeparator());
            } else {
                str.append(printNDArray(dimension - 1, indices, printItem, format, dims, multipliers));
            }
        }
        return str.toString();
    }
}
