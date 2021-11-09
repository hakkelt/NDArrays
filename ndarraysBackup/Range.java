package io.github.hakkelt.ndarrays;

public class Range {
    private int start;
    private int step;
    private int end;
    private boolean isScalar = false;
    private boolean isAll = false;

    public static final Range ALL = new Range();

    protected Range() {
        this.isAll = true;
    }

    protected Range(int value) {
        this.start = value;
        this.isScalar = true;
    }

    public Range(int start, int end) {
        this(start, 1, end);
    }

    public Range(int start, int step, int end) {
        if (end < start && end > 0)
            throw new IllegalArgumentException(String.format(Errors.INVALID_RANGE, start, end));
        this.start = start;
        this.step = step;
        this.end = end;
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

    protected boolean isScalar() {
        return isScalar;
    }

    protected void addToStart(int length) {
        start += length;
    }

    protected void addToEnd(int length) {
        end += length;
    }

    public int length() {
        return step > 0 ? (end - start - 1) / step + 1 : (start - end - 1) / -step + 1;
    }

    public String toString() {
        if (isAll)
            return ":";
        if (isScalar)
            return String.valueOf(start);
        if (step != 1)
            return String.format("%d:%d:%d", start, step, end);
        return String.format("%d:%d", start, end);
    }
}