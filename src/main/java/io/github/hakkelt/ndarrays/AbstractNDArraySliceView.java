package io.github.hakkelt.ndarrays;

import java.util.Arrays;
import java.util.stream.Collectors;

abstract class AbstractNDArraySliceView<T,T2 extends Number> extends AbstractNDArrayView<T,T2> {
    protected SlicingExpression slicingExpression;

    protected static final String ERROR_ILLEGAL_SLICING_EXPRESSION =
        "Illegal slicing expression: %s";
    protected static final String ERROR_DIMENSION_MISMATCH =
        "Dimension mismatch: cannot slice %s array with the following slicing expression: %s";
    protected static final String ERROR_SLICE_OUT_OF_BOUNDS =
        "Bounds error: cannot slice %s array with the following slicing expression: %s";
    static final String ERROR_INVALID_RANGE =
        "Invalid range: [%d, %d)";
    
    static class Range {
        private int start;
        private int end;

        public Range(int start, int end) {
            if (end < start && end > 0)
                throw new IllegalArgumentException(String.format(ERROR_INVALID_RANGE, start, end));
            this.start = start;
            this.end = end;
        }

        public int start() {
            return start;
        }

        public int end() {
            return end;
        }

        public void addToEnd(int length) {
            end += length;
        }

        public int length() {
            return end - start;
        }

        public String toString() {
            return String.format("[%d, %d)", start, end);
        }
    }

    public static class SlicingExpression {
        protected Object[] expressions;
        protected int[] parentDims;
        protected int[] viewDims;

        public SlicingExpression(int[] parentDims, Object ...expressions) {
            for (Object expr : expressions)
                if (!(expr instanceof Integer) && expr != ":" && !(expr instanceof Range))
                    throw new IllegalArgumentException(String.format(ERROR_ILLEGAL_SLICING_EXPRESSION, expr));
            this.expressions = expressions;
            this.parentDims = parentDims;
            checkAgainstParentDims();
        }

        public SlicingExpression(SlicingExpression prevSlicingExpression, Object ...newExpressions) {
            for (Object expr : newExpressions)
                if (!(expr instanceof Integer) && expr != ":" && !(expr instanceof Range))
                    throw new IllegalArgumentException(String.format(ERROR_ILLEGAL_SLICING_EXPRESSION, expr));
            parentDims = prevSlicingExpression.parentDims;
            expressions = new Object[prevSlicingExpression.expressions.length];
            int exprIndex = 0;
            int newExprIndex = 0;
            for (Object prevExpr : prevSlicingExpression.expressions) {
                if (prevExpr instanceof Integer)
                    expressions[exprIndex++] = prevExpr;
                else if (prevExpr == ":")
                    expressions[exprIndex++] = newExpressions[newExprIndex++];
                else if (prevExpr instanceof Range) {
                    Range range = (Range)prevExpr;
                    if (newExpressions[newExprIndex] instanceof Integer)
                        expressions[exprIndex++] = range.start() + (int)newExpressions[newExprIndex++];
                    else if (newExpressions[newExprIndex] instanceof Range) {
                        Range range2 = (Range)newExpressions[newExprIndex++];
                        int newStart = range.start() + range2.start();
                        expressions[exprIndex++] = new Range(newStart, newStart + range2.length());
                    } else if (newExpressions[newExprIndex++] == ":")
                        expressions[exprIndex++] = prevExpr;
                }
            }
            checkAgainstParentDims();
        }

        public void checkAgainstParentDims() {
            int parentDimsIndex = 0;
            for (Object expr : expressions) {
                if (expr instanceof Range) {
                    Range range = (Range)expr;
                    if (range.end() < 0)
                        range.addToEnd(parentDims[parentDimsIndex]);
                    else if (range.end() > parentDims[parentDimsIndex])
                        throw new ArrayIndexOutOfBoundsException(
                            String.format(ERROR_SLICE_OUT_OF_BOUNDS, Printer.dimsToString(parentDims), toString()));
                }
                parentDimsIndex++;
            }
        }

        public int[] resolveViewDims() {
            if (expressions.length != parentDims.length)
                throw new IllegalArgumentException(
                    String.format(ERROR_DIMENSION_MISMATCH, Printer.dimsToString(parentDims), toString()));
            int newNDims = parentDims.length;
            for (Object expr : expressions)
                if (expr instanceof Integer) newNDims--;
            viewDims = new int[newNDims];
            int viewDimsIndex = 0;
            int parentDimsIndex = 0;
            for (Object expr : expressions) {
                if (expr == ":")
                    viewDims[viewDimsIndex++] = parentDims[parentDimsIndex];
                else if (expr instanceof Range)
                    viewDims[viewDimsIndex++] = ((Range)expr).length();
                parentDimsIndex++;
            }
            return viewDims;
        }

        public int[] resolveToParentIndices(int[] viewIndices) {
            boundaryCheck(viewIndices, viewDims);
            int[] parentIndices = new int[parentDims.length];
            int viewIndicesIndex = 0;
            int parenIndicestIndex = 0;
            for (Object expr : expressions) {
                if (expr == ":") {
                    parentIndices[parenIndicestIndex++] = viewIndices[viewIndicesIndex++];
                } else if (expr instanceof Range) {
                    Range range = (Range)expr;
                    parentIndices[parenIndicestIndex++] = viewIndices[viewIndicesIndex++] + range.start();
                } else {
                    parentIndices[parenIndicestIndex++] = (int)expr;
                }
            }
            return parentIndices;
        }

        public String toString() {
            return "[" + Arrays.stream(expressions).map(expr -> {
                if (expr == ":")
                    return ":";
                else if (expr instanceof Range) {
                    Range range = (Range)expr;
                    return range.start() + ":" + range.end();
                } else
                    return (Integer)expr;
            }).map( Object::toString ).collect(Collectors.joining(", ")) + "]";
        }

    }

    @SuppressWarnings("unchecked")
    protected AbstractNDArraySliceView(NDArray<T> parent, Object ...slicingExpressions) {
        this.parent = (AbstractNDArray<T,T2>)parent;
        this.slicingExpression = new SlicingExpression(parent.dims(), slicingExpressions);
        this.dims = slicingExpression.resolveViewDims();
        this.multipliers = calculateMultipliers(this.dims);
        this.dataLength = length(this.dims);
    }

    protected AbstractNDArraySliceView(AbstractNDArraySliceView<T,T2> parent, Object ...slicingExpressions) {
        this.parent = parent.parent;
        this.slicingExpression = new SlicingExpression(parent.dims(), slicingExpressions);
        this.dims = slicingExpression.resolveViewDims();
        this.multipliers = AbstractNDArray.calculateMultipliers(this.dims);
        this.dataLength = AbstractNDArray.length(this.dims);
    }

    @Override
    public T get(int linearIndex) {
        return get(linearIndexToViewIndices(linearIndex));
    }
    @Override
    public T get(int... indices) {
        return parent.get(slicingExpression.resolveToParentIndices(indices));
    }
    @Override
    public void set(T value, int linearIndex) {
        set(value, linearIndexToViewIndices(linearIndex));
    }
    @Override
    public void set(T value, int... indices) {
        parent.set(value, slicingExpression.resolveToParentIndices(indices));
    }

    @Override
    protected String printItem(int linearIndex, String format) {
        int[] parentIndices = slicingExpression.resolveToParentIndices(linearIndexToViewIndices(linearIndex));
        int parentIndex = parent.resolveIndices(parentIndices);
        return parent.printItem(parentIndex, format);
    }

}
