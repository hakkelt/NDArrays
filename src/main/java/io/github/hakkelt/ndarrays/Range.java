package io.github.hakkelt.ndarrays;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import io.github.hakkelt.ndarrays.internal.Errors;
import io.github.hakkelt.ndarrays.internal.NormalizedRange;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Range {
    @Getter protected Integer startIndex;
    @Getter protected Integer step;
    @Getter protected Integer endIndex;
    @Getter protected boolean isScalar;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    protected static class EndType {}
    public static final EndType END = new EndType();
    public static final Range ALL = new Range();

    public Range(Integer singleIndex) {
        this.startIndex = singleIndex;
        this.step = null;
        this.endIndex = null;
        this.isScalar = true;
    }

    public Range(Integer start, Integer end) {
        this.startIndex = start;
        this.endIndex = end;
        this.step = null;
        this.isScalar = false;
    }

    @SuppressWarnings("java:S1172")
    public Range(Integer start, EndType end) {
        this.startIndex = start;
        this.step = null;
        this.endIndex = null;
        this.isScalar = false;
    }
    
    public Range(Integer start, Integer step, Integer end) {
        if (step != null && step == 0)
            throw new IllegalArgumentException(Errors.RANGE_ZERO_STEPSIZE);
        this.startIndex = start;
        this.step = step;
        this.endIndex = end;
        this.isScalar = false;
    }
    
    @SuppressWarnings("java:S1172")
    public Range(Integer start, Integer step, EndType end) {
        if (step != null && step == 0)
            throw new IllegalArgumentException(Errors.RANGE_ZERO_STEPSIZE);
        this.startIndex = start;
        this.step = step;
        this.endIndex = null;
        this.isScalar = false;
    }

    public String toString() {
        if (isScalar)
            return String.valueOf(startIndex);
        return (startIndex == null ? "" : startIndex)  + ":" + (step == null ? "" : step + ":") + (endIndex == null ? "" : endIndex);
    }

    protected static Integer parseGroup(String value) {
        return value.equals("") ? null : Integer.parseInt(value);
    }

    protected static Range parseRange(String str) {
        final Pattern r = Pattern.compile("^(-?\\d*):(-?\\d*):(?:end)?(-?\\d*)$");
        Matcher m = r.matcher(str);
        if (m.find()) {
            return new Range(parseGroup(m.group(1)), parseGroup(m.group(2)), parseGroup(m.group(3)));
        }
        final Pattern r2 = Pattern.compile("^(-?\\d*):(?:end)?(-?\\d*)$");
        m = r2.matcher(str);
        if (m.find()) {
            return new Range(parseGroup(m.group(1)), parseGroup(m.group(2)));
        }
        if (str.equals("end"))
            return new Range(-1);
        final Pattern r3 = Pattern.compile("^(?:end)?(-?\\d+)$");
        m = r3.matcher(str);
        if (m.find()) {
            return new Range(parseGroup(m.group(1)));
        }
        throw new IllegalArgumentException(String.format(Errors.INVALID_RANGE_EXPRESSION, str));
    }

    public static Range[] parseExpressions(Object... expressions) {
        return IntStream.range(0, expressions.length).mapToObj(i -> {
            Object expr = expressions[i];
            if (expr == ":")
                return Range.ALL;
            if (expr instanceof Number && !(expr instanceof Double || expr instanceof Float))
                return new Range(((Number) expr).intValue());
            if (expr instanceof Range || expr instanceof NormalizedRange)
                return (Range) expr;
            if (expr instanceof String)
                return parseRange((String) expr);
            throw new IllegalArgumentException(String.format(Errors.ILLEGAL_SLICING_EXPRESSION, expr));
        }).toArray(Range[]::new);
    }
}