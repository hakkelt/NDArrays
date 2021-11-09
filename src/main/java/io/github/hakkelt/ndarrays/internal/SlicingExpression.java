package io.github.hakkelt.ndarrays.internal;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.github.hakkelt.ndarrays.NDArrayUtils;
import io.github.hakkelt.ndarrays.Range;

public class SlicingExpression {

    protected Range[] originalExpressions;
    protected Range[] expressions;
    protected int[] parentDims;
    protected int[] viewDims;

    public SlicingExpression(int[] parentDims, Range[] expressions) {
        this.originalExpressions = this.expressions = expressions;
        this.parentDims = parentDims;
        checkAgainstParentDims();
    }

    public SlicingExpression(SlicingExpression prevSlicingExpression, Range[] expressions) {
        this(prevSlicingExpression.viewDims, expressions);
        mergeWithPrevSlicingExpression(prevSlicingExpression);
    }
    
    public Range[] getExpressions() {
        return expressions;
    }

    protected void mergeWithPrevSlicingExpression(SlicingExpression prevSlicingExpression) {
        parentDims = prevSlicingExpression.parentDims;
        int[] newExprIndex = { 0 };
        this.expressions = IntStream.range(0, parentDims.length).mapToObj(prevExprIndex -> {
            Range prevExpr = prevSlicingExpression.expressions[prevExprIndex];
            if (prevExpr.isScalar())
                return prevExpr;
            return mergeWithPreviousExpression(this.expressions[newExprIndex[0]++], prevExpr);
        }).toArray(Range[]::new);
        checkAgainstParentDims();
    }

    protected Range mergeWithPreviousExpression(Range newExpr, Range prevExpr) {
        if (newExpr == Range.ALL)
            return prevExpr;
        if (newExpr.isScalar())
            return prevExpr == Range.ALL ? newExpr : new Range(prevExpr.start() + newExpr.start());
        if (newExpr instanceof Range) {
            int start = prevExpr.start() + newExpr.start();
            return new Range(start, start + newExpr.length());
        }
        throw new IllegalArgumentException(String.format(Errors.ILLEGAL_SLICING_EXPRESSION, newExpr));
    }

    public void checkAgainstParentDims() {
        if (expressions.length != parentDims.length)
            throw new IllegalArgumentException(String.format(Errors.SLICE_DIMENSION_MISMATCH,
                Printer.dimsToString(parentDims), toString()));
        int[] parentDimsIndex = {0};
        this.expressions = Stream.of(expressions).map(range -> {
            range = Range.normalize(range, parentDims[parentDimsIndex[0]]);
            if (range.start() < 0 || range.start() >= parentDims[parentDimsIndex[0]]
                    || range.end() < (range.step() < 0 ? -1 : 0) || range.end() > parentDims[parentDimsIndex[0]])
                throw new ArrayIndexOutOfBoundsException(
                    String.format(Errors.SLICE_OUT_OF_BOUNDS, Printer.dimsToString(parentDims), toString()));
            parentDimsIndex[0]++;
            return range;
        }).toArray(Range[]::new);
    }

    public int[] resolveViewDims() {
        int newndim = parentDims.length - (int)Stream.of(expressions).filter(Range::isScalar).count();
        viewDims = new int[newndim];
        int viewDimsIndex = 0;
        int parentDimsIndex = 0;
        for (Range expr : expressions) {
            if (expr == Range.ALL)
                viewDims[viewDimsIndex++] = parentDims[parentDimsIndex];
            else if (!expr.isScalar())
                viewDims[viewDimsIndex++] = expr.length();
            parentDimsIndex++;
        }
        return viewDims;
    }

    public int[] resolveToParentIndices(int[] viewIndices) {
        NDArrayUtils.boundaryCheck(viewIndices, viewDims);
        int[] parentIndices = new int[parentDims.length];
        int viewIndicesIndex = 0;
        int parentIndicesIndex = 0;
        for (Range expr : expressions) {
            if (expr == Range.ALL)
                parentIndices[parentIndicesIndex++] = viewIndices[viewIndicesIndex++];
            else if (expr.isScalar())
                parentIndices[parentIndicesIndex++] = expr.start();
            else
                parentIndices[parentIndicesIndex++] = viewIndices[viewIndicesIndex++] * expr.step() + expr.start();
        }
        return parentIndices;
    }

    public String toString() {
        return "[" + Arrays.stream(expressions).map(Range::toString).collect(Collectors.joining(", ")) + "]";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SlicingExpression))
            return false;
        SlicingExpression other = (SlicingExpression) obj;
        if (!Arrays.equals(parentDims, other.parentDims) || !Arrays.equals(viewDims, other.viewDims))
            return false;
        for (int i = 0; i < expressions.length; i++) {
            Range left = expressions[i];
            Range right = other.expressions[i];
            if (left == right || areBothScalarAndEqual(left, right))
                continue;
            if (isOnlyOneOfThemIsScalar(left, right)
                    || (left == Range.ALL && notEqualsToAll(right, parentDims[i]))
                    || (right == Range.ALL && notEqualsToAll(left, parentDims[i]))
                    || areInequal(left, right))
                return false;
        }
        return true;
    }

    protected static boolean areBothScalarAndEqual(Range left, Range right) {
        return left.isScalar() && right.isScalar() && left.start() == right.start();
    }

    protected static boolean isOnlyOneOfThemIsScalar(Range left, Range right) {
        return left.isScalar() != right.isScalar();
    }

    protected static boolean notEqualsToAll(Range range, int dimensionLength) {
        return range.start() != 0 || range.step() != 1 || range.end() != dimensionLength;
    }

    protected static boolean areInequal(Range left, Range right) {
        return left.start() != right.start() || left.step() != right.step() || left.end() != right.end();
    }

    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}

