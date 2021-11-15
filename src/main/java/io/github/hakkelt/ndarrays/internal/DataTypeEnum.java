package io.github.hakkelt.ndarrays.internal;

import java.util.List;

public class DataTypeEnum {

    private DataTypeEnum() {
        throw new IllegalStateException("Enum-type class cannot by instantiated!");
    }

    public static final String BYTE = "Byte";
    public static final String SHORT = "Short";
    public static final String INTEGER = "Integer";
    public static final String LONG = "Long";
    public static final String FLOAT = "Float";
    public static final String DOUBLE = "Double";
    public static final String BIGINTEGER = "BigInteger";
    public static final String BIGDECIMAL = "BigDecimal";
    public static final String COMPLEX_BYTE = "Complex Byte";
    public static final String COMPLEX_SHORT = "Complex Short";
    public static final String COMPLEX_INTEGER = "Complex Integer";
    public static final String COMPLEX_LONG = "Complex Long";
    public static final String COMPLEX_FLOAT = "Complex Float";
    public static final String COMPLEX_DOUBLE = "Complex Double";
    public static final String COMPLEX_BIGINTEGER = "Complex BigInteger";
    public static final String COMPLEX_BIGDECIMAL = "Complex BigDecimal";

    private static List<String> values = List.of(
        BYTE, SHORT, INTEGER, LONG,
        FLOAT, DOUBLE, BIGINTEGER, BIGDECIMAL,
        COMPLEX_BYTE, COMPLEX_SHORT, COMPLEX_INTEGER, COMPLEX_LONG,
        COMPLEX_FLOAT, COMPLEX_DOUBLE, COMPLEX_BIGINTEGER, COMPLEX_BIGDECIMAL
    );

    public static byte getIndex(String dtype) {
        return (byte) values.indexOf(dtype);
    }

    public static String getStringRepresentation(byte index) {
        return values.get(index);
    }
}
