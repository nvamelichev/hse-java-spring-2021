package ru.hse.java.sql;

public interface FilterVisitor<R> {
    default R visitLeaf(Filter.Leaf l) {
        throw new UnsupportedOperationException();
    }

    default R visitComposite(Filter.Composite c) {
        throw new UnsupportedOperationException();
    }
}
