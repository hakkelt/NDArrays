package io.github.hakkelt.ndarrays.internal;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.github.hakkelt.ndarrays.NDArrayUtils;
import io.github.hakkelt.ndarrays.Range;

public class SlicingExpression {

    protected NormalizedRange[] expressions;
    protected int[] parentDims;
    protected int[] viewDims;

    public SlicingExpression(int[] parentDims, Range[] expressions) {
        this(parentDims, NormalizedRange.normalizeRanges(expressions, parentDims));
    }

    public SlicingExpression(int[] parentDims, NormalizedRange[] expressions) {
        this.parentDims = parentDims;
        this.expressions = expressions;
    }

    public SlicingExpression(SlicingExpression prevSlicingExpression, NormalizedRange[] expressions) {
        this(prevSlicingExpression.viewDims, expressions);
        mergeWithPrevSlicingExpression(prevSlicingExpression);
    }

    public SlicingExpression(SlicingExpression prevSlicingExpression, Range[] expressions) {
        this(prevSlicingExpression.viewDims, expressions);
        mergeWithPrevSlicingExpression(prevSlicingExpression);
    }
    
    public NormalizedRange[] getExpressions() {
        return expressions;
    }

    protected void mergeWithPrevSlicingExpression(SlicingExpression prevSlicingExpression) {
        this.parentDims = prevSlicingExpression.parentDims;
        NormalizedRange[] newExpressions = new NormalizedRange[parentDims.length];
        int newExprIndex = 0;
        for (int i = 0; i < newExpressions.length; i++) {
            NormalizedRange prevExpr = prevSlicingExpression.expressions[i];
            if (prevExpr.isScalar()) {
                newExpressions[i] = prevExpr;
            } else {
                newExpressions[i] = new NormalizedRange(
                        mergeWithPreviousExpression(this.expressions[newExprIndex++], prevExpr),
                        parentDims[i]);
                newExpressions[i].checkAgainstParentDims(parentDims, parentDims[i], this::toString);
            }
        }
        this.expressions = newExpressions;
    }

    protected Range mergeWithPreviousExpression(NormalizedRange newExpr, NormalizedRange prevExpr) {
        if (newExpr.isAll())
            return prevExpr;
        if (newExpr.isScalar())
            return prevExpr.isAll() ? newExpr : new Range(prevExpr.startIndex() + newExpr.startIndex());
        if (prevExpr.isAll())
            return newExpr;
        int start = prevExpr.startIndex() + prevExpr.step() * newExpr.startIndex();
        return new Range(start, prevExpr.step() * newExpr.step(), start + prevExpr.step() * newExpr.length());
    }

    public int[] resolveViewDims() {
        int newndim = parentDims.length - (int) Stream.of(expressions).filter(NormalizedRange::isScalar).count();
        viewDims = new int[newndim];
        int viewDimsIndex = 0;
        int parentDimsIndex = 0;
        for (NormalizedRange expr : expressions) {
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
        for (NormalizedRange expr : expressions) {
            if (expr.isAll())
                parentIndices[parentIndicesIndex++] = viewIndices[viewIndicesIndex++];
            else if (expr.isScalar())
                parentIndices[parentIndicesIndex++] = expr.startIndex();
            else
                parentIndices[parentIndicesIndex++] = expr.startIndex() + viewIndices[viewIndicesIndex++] * expr.step();
        }
        return parentIndices;
    }

    public String toString() {
        return toString(this.expressions);
    }

    public static String toString(Range[] expressions) {
        return "[" + Arrays.stream(expressions).map(Range::toString).collect(Collectors.joining(", ")) + "]";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SlicingExpression))
            return false;
        SlicingExpression other = (SlicingExpression) obj;
        if (!Arrays.equals(parentDims, other.parentDims) || !Arrays.equals(viewDims, other.viewDims))
            return false;
        for (int i = 0; i < expressions.length; i++) {
            NormalizedRange left = expressions[i];
            NormalizedRange right = other.expressions[i];
            if (left == right || areBothScalarAndEqual(left, right))
                continue;
            if (isOnlyOneOfThemIsScalar(left, right)
                    || (left.isAll() && !right.isAll())
                    || (!left.isAll() && right.isAll())
                    || areInequal(left, right))
                return false;
        }
        return true;
    }

    protected static boolean areBothScalarAndEqual(NormalizedRange left, NormalizedRange right) {
        return left.isScalar() && right.isScalar() && left.startIndex() == right.startIndex();
    }

    protected static boolean isOnlyOneOfThemIsScalar(NormalizedRange left, NormalizedRange right) {
        return left.isScalar() != right.isScalar();
    }

    protected static boolean areInequal(NormalizedRange left, NormalizedRange right) {
        return left.startIndex() != right.startIndex() || left.step() != right.step() || left.endIndex() != right.endIndex();
    }

    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}

