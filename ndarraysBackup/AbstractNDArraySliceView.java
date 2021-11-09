package io.github.hakkelt.ndarrays;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

abstract class AbstractNDArraySliceView<T,T2 extends Number> extends AbstractNDArrayView<T,T2> { // NOSONAR
    protected SlicingExpression slicingExpression;
    
    public static class SlicingExpression {
        protected Range[] expressions;
        protected int[] parentDims;
        protected int[] viewDims;

        protected static int parseGroup(String value, int defaultValue) {
            return value.equals("") ? defaultValue : Integer.parseInt(value);
        }
    
        protected static Range parseRange(String str, int[] shape, int dimension) {
            final Pattern r = Pattern.compile("(-?\\d*):(-?\\d*):?(-?\\d*)");
            Matcher m = r.matcher(str);
            if (m.find()) {
                if (m.group(3).equals(""))
                    return new Range(parseGroup(m.group(1), 0), parseGroup(m.group(2), shape[dimension]));
                else
                    return new Range(parseGroup(m.group(1), 0), parseGroup(m.group(2), 1), parseGroup(m.group(3), shape[dimension]));
            } else {
                throw new IllegalArgumentException(String.format(Errors.INVALID_RANGE_EXPRESSION, str));
            }
        }

        public SlicingExpression(int[] parentDims, Object ...expressions) {
            this.expressions = IntStream.range(0, expressions.length).mapToObj(i -> {
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
            this.parentDims = parentDims;
            checkAgainstParentDims();
        }

        protected Range mergeWithPreviousExpression(Object newExpr, Range prevExpr, int prevExprIndex) {
            if (newExpr == ":")
                return prevExpr;
            if (newExpr instanceof Number && !(newExpr instanceof Double || newExpr instanceof Float)) {
                int value = ((Number) newExpr).intValue();
                return prevExpr == Range.ALL ? new Range(value) : new Range(prevExpr.start() + value);
            }
            if (newExpr instanceof String)
                newExpr = parseRange((String) newExpr, parentDims, prevExprIndex);
            if (newExpr instanceof Range) {
                Range expr = (Range) newExpr;
                int start = prevExpr.start() + expr.start();
                return new Range(start, start + expr.length());
            }
            throw new IllegalArgumentException(String.format(Errors.ILLEGAL_SLICING_EXPRESSION, newExpr));
        }

        public SlicingExpression(SlicingExpression prevSlicingExpression, Object ...newExpressions) {
            for (Object expr : newExpressions)
                if (!(expr instanceof Integer) && expr != ":" && !(expr instanceof Range))
                    throw new IllegalArgumentException(String.format(Errors.ILLEGAL_SLICING_EXPRESSION, expr));
            parentDims = prevSlicingExpression.parentDims;
            int[] newExprIndex = { 0 };
            this.expressions = IntStream.range(0, parentDims.length).mapToObj(prevExprIndex -> {
                Range prevExpr = prevSlicingExpression.expressions[prevExprIndex];
                if (prevExpr.isScalar())
                    return prevExpr;
                Object newExpr = newExpressions[newExprIndex[0]++];
                return mergeWithPreviousExpression(newExpr, prevExpr, prevExprIndex);
            }).toArray(Range[]::new);
            checkAgainstParentDims();
        }

        public void checkAgainstParentDims() {
            int parentDimsIndex = 0;
            for (Range range : expressions) {
                if (range.start() < 0)
                    range.addToStart(parentDims[parentDimsIndex]);
                if (range.end() < 0)
                    range.addToEnd(parentDims[parentDimsIndex]);
                else if (range.end() > parentDims[parentDimsIndex])
                    throw new ArrayIndexOutOfBoundsException(
                        String.format(Errors.SLICE_OUT_OF_BOUNDS, Printer.dimsToString(parentDims), toString()));
                parentDimsIndex++;
            }
        }

        public int[] resolveViewDims() {
            if (expressions.length != parentDims.length)
                throw new IllegalArgumentException(
                    String.format(Errors.SLICE_DIMENSION_MISMATCH, Printer.dimsToString(parentDims), toString()));
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
            IndexingOperations.boundaryCheck(viewIndices, viewDims);
            int[] parentIndices = new int[parentDims.length];
            int viewIndicesIndex = 0;
            int parentIndicesIndex = 0;
            for (Range expr : expressions) {
                if (expr == Range.ALL)
                    parentIndices[parentIndicesIndex++] = viewIndices[viewIndicesIndex++];
                else if (expr.isScalar())
                    parentIndices[parentIndicesIndex++] = expr.start();
                else if (expr.step() < 0)
                    parentIndices[parentIndicesIndex++] = expr.end() + viewIndices[viewIndicesIndex++] * expr.step();
                else
                    parentIndices[parentIndicesIndex++] = viewIndices[viewIndicesIndex++] * expr.step() + expr.start();
            }
            return parentIndices;
        }

        public Object[] getExpressions() {
            return expressions;
        }

        public String toString() {
            return "[" + Arrays.stream(expressions).map(Range::toString).collect(Collectors.joining(", ")) + "]";
        }

    }

    @SuppressWarnings("unchecked")
    protected AbstractNDArraySliceView(NDArray<T> parent, Object ...slicingExpressions) {
        if (parent instanceof AbstractNDArraySliceView) {
            this.parent = ((AbstractNDArraySliceView<T,T2>)parent).parent;
            this.slicingExpression = new SlicingExpression(((AbstractNDArraySliceView<T,T2>)parent).slicingExpression, slicingExpressions);
        } else {
            this.parent = (AbstractNDArray<T,T2>)parent;
            this.slicingExpression = new SlicingExpression(parent.shape(), slicingExpressions);
        }
        this.shape = slicingExpression.resolveViewDims();
        this.multipliers = IndexingOperations.calculateMultipliers(this.shape);
        this.dataLength = IndexingOperations.length(this.shape);
    }

    @Override
    protected T getUnchecked(int linearIndex) {
        return getUnchecked(linearIndexToViewIndices(linearIndex));
    }

    @Override
    protected T getUnchecked(int... indices) {
        return parent.getUnchecked(slicingExpression.resolveToParentIndices(indices));
    }

    @Override
    protected void setUnchecked(T value, int linearIndex) {
        setUnchecked(value, linearIndexToViewIndices(linearIndex));
    }

    @Override
    protected void setUnchecked(T value, int... indices) {
        parent.setUnchecked(value, slicingExpression.resolveToParentIndices(indices));
    }

    @Override
    protected String printItem(int linearIndex, String format) {
        int[] parentIndices = slicingExpression.resolveToParentIndices(linearIndexToViewIndices(linearIndex));
        int parentIndex = IndexingOperations.cartesianIndicesToLinearIndex(parentIndices, parent.multipliers);
        return parent.printItem(parentIndex, format);
    }

}
