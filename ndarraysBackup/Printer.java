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
            Supplier<String> getTypeName,
            BiFunction<Integer, String, String> printItem,
            String name,
            int[] shape,
            int[] multipliers) {
        String dataType = dataTypeAsString.get();
        String str = name + " " + getTypeName.get() + "<" + dataType + ">(" + dimsToString(shape) + ")"
            + System.lineSeparator();
        boolean isIntegerType = dataType.equals("Byte") || dataType.equals("Short")
            || dataType.equals("Integer") || dataType.equals("Long");
        String format = isIntegerType ? DEFAULT_INTEGER_FORMAT : DEFAULT_FLOATING_POINT_FORMAT;
        if (shape.length == 1)
            str += printRow(0, shape[0], 1, format, printItem);
        else if (shape.length == 2)
            str += printMatrix(0, shape[1], shape[0], format, printItem);
        else
            str += printNDArray(shape.length - 1, new int[shape.length - 2], printItem, format, shape, multipliers);
        return str;
    }
    
    static String dimsToString(int[] shape) {
        return String.join(" × ", IntStream.of(shape).mapToObj(String::valueOf).collect(Collectors.toList()));
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
            int[] shape,
            int[] multipliers) {
        StringBuilder str = new StringBuilder();
        for (indices[dimension - 2] = 0; indices[dimension - 2] < shape[dimension]; indices[dimension - 2]++) {
            if (dimension == 2) {
                str.append(printIndices(indices));
                str.append(" =" + System.lineSeparator());
                int[] startIndices = new int[shape.length];
                for (int j = 2; j < shape.length; j++)
                    startIndices[j] = indices[j - 2];
                str.append(printMatrix(IndexingOperations.cartesianIndicesToLinearIndex(startIndices, multipliers), shape[1], shape[0], format, printItem));
                str.append(System.lineSeparator());
            } else {
                str.append(printNDArray(dimension - 1, indices, printItem, format, shape, multipliers));
            }
        }
        return str.toString();
    }
}
