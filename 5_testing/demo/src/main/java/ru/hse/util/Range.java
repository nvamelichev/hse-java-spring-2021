package ru.hse.util;

import com.google.common.base.Preconditions;

import java.util.stream.IntStream;

public record Range(int leftInclusive, int rightExclusive) {
    public Range {
        Preconditions.checkArgument(leftInclusive <= rightExclusive,
                "leftInclusive (%s) <= rightExclusive (%s)",
                leftInclusive, rightExclusive);
    }

    int min() {
        return leftInclusive;
    }

    int max() {
        return rightExclusive - 1;
    }

    long size() {
        return (long) rightExclusive - (long) leftInclusive;
    }

    boolean contains(int value) {
        return value >= leftInclusive && value < rightExclusive;
    }

    IntStream stream() {
        return IntStream.range(leftInclusive, rightExclusive);
    }

    boolean isEmpty() {
        return leftInclusive == rightExclusive;
    }
}
