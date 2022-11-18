package io.github.hakkelt.ndarrays.internal;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import io.github.hakkelt.ndarrays.Range;
import lombok.Getter;

public class NormalizedRange extends Range {

    @Getter protected boolean isAll;

    public NormalizedRange(Range range, int length) {
        super(null, null, Range.END);
        this.startIndex = resolveStartIndex(range, length);
        this.endIndex = resolveEndIndex(range, length);
        this.step = range.getStep() == null ? 1 : range.getStep();
        this.isScalar = range.isScalar();
        this.isAll = !this.isScalar && this.startIndex == 0 && this.step == 1 && this.endIndex == length;
    }

    protected NormalizedRange(int scalarIndex) {
        super(scalarIndex);
        this.isAll = false;
    }

    protected NormalizedRange(int start, int end) {
        super(start, 1, end);
        this.isAll = false;
    }

    private int resolveStartIndex(Range range, int length) {
        if (range.getStartIndex() == null)
            return range.getStep() == null || range.getStep() > 0 ? 0 : length - 1;
        else if (range.getStartIndex() < 0)
            return range.getStartIndex() + length;
        else
            return range.getStartIndex();
    }

    private int resolveEndIndex(Range range, int length) {
        if (range.getEndIndex() == null && range.isScalar())
            return startIndex + 1;
        else if (range.getEndIndex() == null)
            return range.getStep() == null || range.getStep() > 0 ? length : -1;
        else if (range.getEndIndex() > length)
            return range.getEndIndex() + length;
        else if (range.getEndIndex() < 0 && !(range.getStep() != null && range.getStep() < 0 && range.getEndIndex() == -1))
            return length + range.getEndIndex();
        else
            return range.getEndIndex();
    }

    public void checkAgainstParentDims(int[] parentDims, int length, Supplier<String> exprToString) {
        if (startIndex < 0
                || startIndex >= length
                || endIndex < (step < 0 ? -1 : 0)
                || endIndex > length)
            throw new ArrayIndexOutOfBoundsException(
                String.format(Errors.SLICE_OUT_OF_BOUNDS, Printer.dimsToString(parentDims), exprToString.get()));
    }

    public int startIndex() {
        return startIndex;
    }

    public int step() {
        return step;
    }

    public int endIndex() {
        return endIndex;
    }

    @Override
    public boolean isScalar() {
        return isScalar;
    }

    public int length() {
        return step > 0 ? (endIndex - startIndex - 1) / step + 1 : (startIndex - endIndex - 1) / -step + 1;
    }

    public static NormalizedRange[] normalizeRanges(Range[] ranges, int[] parentDims) {
        if (ranges instanceof NormalizedRange[])
            return (NormalizedRange[]) ranges;
        if (ranges.length != parentDims.length)
            throw new IllegalArgumentException(String.format(Errors.SLICE_DIMENSION_MISMATCH,
                Printer.dimsToString(parentDims), SlicingExpression.toString(ranges)));
        return IntStream.range(0, ranges.length)
            .mapToObj(i -> {
                NormalizedRange range = new NormalizedRange(ranges[i], parentDims[i]);
                range.checkAgainstParentDims(parentDims, parentDims[i], () -> SlicingExpression.toString(ranges));
                return range;
            })
            .toArray(NormalizedRange[]::new);
    }
}