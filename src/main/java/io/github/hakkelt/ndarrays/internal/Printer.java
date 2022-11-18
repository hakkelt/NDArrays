package io.github.hakkelt.ndarrays.internal;

import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import io.github.hakkelt.ndarrays.NDArrayUtils;

public class Printer {

    static final String DEFAULT_INTEGER_FORMAT = "%6d";
    static final String DEFAULT_FLOATING_POINT_FORMAT = "%10.5e";

    private Printer() {}

    public static String contentToString(
            String dataType,
            String typeName,
            BiFunction<Integer, String, String> printItem,
            String name,
            int[] shape,
            int[] multipliers) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s<%s>(%s)%n", name, typeName, dataType, dimsToString(shape)));
        boolean isFloatingPointType = dataType.equals("Float") || dataType.equals("Double")
            || dataType.equals("BigDecimal") || dataType.startsWith("Complex");
        String format = isFloatingPointType ? DEFAULT_FLOATING_POINT_FORMAT : DEFAULT_INTEGER_FORMAT;
        if (shape.length == 1)
            printRow(sb, 0, shape[0], 1, format, printItem);
        else if (shape.length == 2)
            printMatrix(sb, 0, shape[1], shape[0], format, printItem);
        else
            printNDArray(sb, shape.length - 1, new int[shape.length - 2], printItem, format, shape, multipliers);
        return sb.toString();
    }
    
    public static String dimsToString(int[] shape) {
        return String.join(" × ", IntStream.of(shape).mapToObj(String::valueOf).collect(Collectors.toList()));
    }

    public static String printVector(int[] indices) {
        return "[" + StringUtils.join(ArrayUtils.toObject(indices), ", ") + "]";
    }
    
    private static void printRow(StringBuilder sb, int startIndex, int width, int height, String format, BiFunction<Integer, String, String> printItem) {
        for (int i = 0; i < width; i++) {
            sb.append(printItem.apply(startIndex + i * height, format));
            sb.append("\t");
        }
    }

    private static void printMatrix(StringBuilder sb, int startIndex, int width, int height, String format, BiFunction<Integer, String, String> printItem) {
        for (int i = 0; i < height; i++) {
            printRow(sb, startIndex + i, width, height, format, printItem);
            sb.append(System.lineSeparator());
        }
    }

    private static void printIndices(StringBuilder sb, int[] indices) {
        sb.append("[:, :, ").append(StringUtils.join(ArrayUtils.toObject(indices), ", ")).append("]");
    }

    private static void printNDArray(
            StringBuilder sb,
            int dimension,
            int[] indices,
            BiFunction<Integer, String, String> printItem,
            String format,
            int[] shape,
            int[] multipliers) {
        for (indices[dimension - 2] = 0; indices[dimension - 2] < shape[dimension]; indices[dimension - 2]++) {
            if (dimension == 2) {
                printIndices(sb, indices);
                sb.append(" =").append(System.lineSeparator());
                int[] startIndices = new int[shape.length];
                for (int j = 2; j < shape.length; j++)
                    startIndices[j] = indices[j - 2];
                printMatrix(sb, NDArrayUtils.cartesianIndicesToLinearIndex(startIndices, multipliers), shape[1], shape[0], format, printItem);
                sb.append(System.lineSeparator());
            } else {
                printNDArray(sb, dimension - 1, indices, printItem, format, shape, multipliers);
            }
        }
    }
}
