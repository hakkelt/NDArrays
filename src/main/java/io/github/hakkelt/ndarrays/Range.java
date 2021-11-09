package io.github.hakkelt.ndarrays;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import io.github.hakkelt.ndarrays.internal.Errors;

public class Range {
    private int start;
    private int step;
    private int end;
    private boolean isScalar = false;
    private boolean isAll = false;
    private boolean omittedStart = false;
    private boolean omittedStep = false;
    private boolean omittedEnd = false;

    public static final Range ALL = new Range();

    public static Range normalize(Range range, int length) {
        boolean startNeedsAdjustment = range.start < 0;
        boolean endNeedsAdjustment = range.end < (range.step > 0 ? 0 : -1);
        if (!startNeedsAdjustment && !endNeedsAdjustment)
            return range;
        Range newInstance = new Range(range);
        if (startNeedsAdjustment)
            newInstance.start += length;
        if (endNeedsAdjustment)
            newInstance.end += length;
        return newInstance;
    }

    protected Range() {
        this.isAll = true;
    }

    public Range(int value) {
        this.start = value;
        this.isScalar = true;
    }

    public Range(int start, int end) {
        this(start, 1, end, false, true, false);
    }
    
    public Range(int start, int step, int end) {
        this(start, step, end, false, false, false);
    }

    public Range(int start, int step, int end, boolean omittedStart, boolean omittedStep, boolean omittedEnd) {
        if (step == 0)
            throw new IllegalArgumentException(Errors.RANGE_ZERO_STEPSIZE);
        this.start = start;
        this.step = step;
        this.end = end;
        this.omittedStart = omittedStart;
        this.omittedStep = omittedStep;
        this.omittedEnd = omittedEnd;
    }

    protected Range(Range range) {
        this.start = range.start;
        this.step = range.step;
        this.end = range.end;
        this.isScalar = range.isScalar;
        this.isAll = range.isAll;
        this.omittedStart = range.omittedStart;
        this.omittedStep = range.omittedStep;
        this.omittedEnd = range.omittedEnd;
    }

    public int start() {
        return start;
    }

    public int step() {
        return step;
    }

    public int end() {
        return end;
    }

    public boolean isScalar() {
        return isScalar;
    }

    public int length() {
        return step > 0 ? (end - start - 1) / step + 1 : (start - end - 1) / -step + 1;
    }

    public String toString() {
        if (isAll)
            return ":";
        if (isScalar)
            return String.valueOf(start);
        return (omittedStart ? "" : start)  + ":" + (omittedStep ? "" : step + ":") + (omittedEnd ? "" : end);
    }

    protected static int parseGroup(String value, int defaultValue) {
        return value.equals("") ? defaultValue : Integer.parseInt(value);
    }

    protected static Range parseRange(String str, int[] shape, int dimension) {
        final Pattern r = Pattern.compile("(-?\\d*):(-?\\d*):(-?\\d*)");
        Matcher m = r.matcher(str);
        if (m.find()) {
            int step = parseGroup(m.group(2), 1);
            int start = parseGroup(m.group(1), step < 0 ? shape[dimension] - 1 : 0);
            int end = parseGroup(m.group(3), step < 0 ? -1 : shape[dimension]);
            return new Range(start, step, end, m.group(1).equals(""), m.group(2).equals(""), m.group(3).equals(""));
        }
        final Pattern r2 = Pattern.compile("(-?\\d*):(-?\\d*)");
        m = r2.matcher(str);
        if (m.find()) {
            int start = parseGroup(m.group(1), 0);
            int end = parseGroup(m.group(2), shape[dimension]);
            return new Range(start, 1, end, m.group(1).equals(""), true, m.group(2).equals(""));
        }
        throw new IllegalArgumentException(String.format(Errors.INVALID_RANGE_EXPRESSION, str));
    }

    public static Range[] parseExpressions(int[] parentDims, Object... expressions) {
        return IntStream.range(0, expressions.length).mapToObj(i -> {
            Object expr = expressions[i];
            if (expr == ":")
                return Range.ALL;
            if (expr instanceof Number && !(expr instanceof Double || expr instanceof Float))
                return new Range(((Number) expr).intValue());
            if (expr instanceof Range)
                return (Range)expr;
            if (expr instanceof String)
                return parseRange((String) expr, parentDims, i);
            throw new IllegalArgumentException(String.format(Errors.ILLEGAL_SLICING_EXPRESSION, expr));
        }).toArray(Range[]::new);
    }
}