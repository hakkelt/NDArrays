package io.github.hakkelt.ndarrays.internal;

import java.util.Spliterator;
import java.util.function.Consumer;

import io.github.hakkelt.ndarrays.NDArrayUtils;

public class CartesianIndexSpliterator implements Spliterator<int[]> {

    private final int[] shape;
    private final int[] multipliers;
    private int[] origin;
    private final int[] fence;
    private final int splitDimension;
    private boolean hasMoreItems;

    public CartesianIndexSpliterator(int[] shape) {
        this.shape = shape;
        this.multipliers = NDArrayUtils.calculateMultipliers(shape);
        this.origin = new int[shape.length];
        this.fence = NDArrayUtils.getEndCartesianIndex(shape);
        this.splitDimension = shape.length - 1;
        this.hasMoreItems = true;
    }

    public CartesianIndexSpliterator(int[] shape, int[] multipliers) {
        this.shape = shape;
        this.multipliers = multipliers;
        this.origin = new int[shape.length];
        this.fence = NDArrayUtils.getEndCartesianIndex(shape);
        this.splitDimension = shape.length - 1;
        this.hasMoreItems = true;
    }

    public CartesianIndexSpliterator(int[] shape, int[] multipliers, int[] origin, int[] fence, int splitDimension) {
        this.shape = shape;
        this.multipliers = multipliers;
        this.origin = origin;
        this.fence = fence;
        this.splitDimension = splitDimension;
        this.hasMoreItems = true;
    }

    @Override
    public void forEachRemaining(Consumer<? super int[]> action) {
        while (tryAdvance(action));
    }

    public boolean tryAdvance(Consumer<? super int[]> action) {
        if (!hasMoreItems)
            return false;
        action.accept(origin);
        hasMoreItems = increaseOrigin();
        return true;
    }

    public Spliterator<int[]> trySplit() {
        for (int dimension = splitDimension; dimension >= 0; dimension--) {
            var spliterator = trySplitAlongDimension(dimension);
            if (spliterator != null)
                return spliterator;
        }
        return null;
    }

    public long estimateSize() {
        int start = NDArrayUtils.cartesianIndicesToLinearIndex(origin, multipliers);
        int end = NDArrayUtils.cartesianIndicesToLinearIndex(fence, multipliers);
        return (long) end - start + 1;
    }

    public int characteristics() {
        return ORDERED | SIZED | IMMUTABLE | SUBSIZED;
    }

    public int[] getMultipliers() {
        return multipliers;
    }

    private Spliterator<int[]> trySplitAlongDimension(int dimension) {
        int lo = origin[dimension];
        int mid = origin[dimension] + (fence[dimension] - origin[dimension]) / 2;
        if (lo < mid) { // split out left half
            int[] newOrigin = origin.clone();
            origin[dimension] = mid; // reset this Spliterator's origin
            int[] newFence = getPreviousIndex(origin);
            return new CartesianIndexSpliterator(shape, multipliers, newOrigin, newFence, dimension);
        } else // too small to split
            return null;
    }

    private boolean increaseOrigin() {
        for (int i = 0; i < origin.length; i++) {
            if (origin[i] == shape[i] - 1) {
                origin[i] = 0;
            } else {
                if (i == splitDimension && origin[i] == fence[i])
                    return false;
                origin[i]++;
                return true;
            }
        }
        return false;
    }

    private int[] getPreviousIndex(int[] index) {
        int[] newIndex = index.clone();
        for (int i = 0; i < index.length; i++) {
            if (newIndex[i] == 0) {
                newIndex[i] = shape[i] - 1;
            } else {
                newIndex[i]--;
                return newIndex;
            }
        }
        throw new IllegalStateException();
    }

}