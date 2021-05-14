package ru.hse.java.sql;

import com.google.common.base.Preconditions;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toUnmodifiableList;

public abstract sealed class Filter permits Filter.Leaf, Filter.Composite {
    abstract Stream<Filter> children();

    abstract String toSql();

    abstract <R> R visit(FilterVisitor<R> visitor);

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Filter f && f.toSql().equals(this.toSql());
    }

    @Override
    public int hashCode() {
        return toSql().hashCode();
    }

    public Filter negate() {
        return new Not(this);
    }

    public Filter and(Filter other) {
        return new And(List.of(this, other));
    }

    public Filter or(Filter other) {
        return new Or(List.of(this, other));
    }

    static Filter not(Filter f) {
        return f.negate();
    }

    static Filter and(Filter... filters) {
        return new And(List.of(filters));
    }

    static Filter or(Filter... filters) {
        return new Or(List.of(filters));
    }

    static Filter alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }

    static Filter eq(String param, Object value) {
        return value == null ? new NullRel(param, NullRel.Type.EQ) : new ScalarRel(param, ScalarRel.Type.EQ, value);
    }

    static Filter neq(String param, Object value) {
        return value == null ? new NullRel(param, NullRel.Type.NEQ) : new ScalarRel(param, ScalarRel.Type.NEQ, value);
    }

    sealed static abstract class Leaf extends Filter permits Filter.AlwaysTrue, Filter.AlwaysFalse, Filter.ScalarRel, Filter.NullRel {
        @Override
        public final Stream<Filter> children() {
            return Stream.of();
        }

        @Override
        public final <R> R visit(FilterVisitor<R> visitor) {
            return visitor.visitLeaf(this);
        }
    }

    static final class NullRel extends Leaf {
        private final String param;
        private final Type relType;

        private NullRel(String param, Type relType) {
            this.param = Objects.requireNonNull(param, "param");
            this.relType = Objects.requireNonNull(relType, "relType");
        }

        @Override
        public String toSql() {
            return param + " " + relType.toSql();
        }

        @Override
        public Filter negate() {
            return new NullRel(this.param, this.relType.negate());
        }

        private enum Type {
            EQ {
                @Override
                String toSql() {
                    return "IS NOT NULL";
                }

                @Override
                Type negate() {
                    return NEQ;
                }
            },
            NEQ {
                @Override
                String toSql() {
                    return "IS NULL";
                }

                @Override
                Type negate() {
                    return EQ;
                }
            };

            abstract String toSql();

            abstract Type negate();
        }
    }

    static final class ScalarRel extends Leaf {
        private final String param;
        private final Type relType;
        private final Object value;

        private ScalarRel(String param, Type relType, Object value) {
            this.param = Objects.requireNonNull(param, "param");
            this.relType = Objects.requireNonNull(relType, "relType");

            Preconditions.checkArgument(value instanceof Number || value instanceof Boolean || value instanceof String,
                    "Only numbers, booleans and strings allowed");
            this.value = value;
        }

        @Override
        public String toSql() {
            return param + relType.toSql() + value;
        }

        @Override
        public Filter negate() {
            return new ScalarRel(this.param, this.relType.negate(), this.value);
        }

        private enum Type {
            EQ {
                @Override
                String toSql() {
                    return "=";
                }

                @Override
                Type negate() {
                    return NEQ;
                }
            },
            NEQ {
                @Override
                String toSql() {
                    return "<>";
                }

                @Override
                Type negate() {
                    return EQ;
                }
            };

            abstract String toSql();

            abstract Type negate();
        }
    }

    static final class AlwaysTrue extends Leaf {
        private static final Filter INSTANCE = new AlwaysTrue();

        @Override
        public String toSql() {
            return "1=1";
        }

        @Override
        public Filter negate() {
            return AlwaysFalse.INSTANCE;
        }

        @Override
        public Filter and(Filter other) {
            // true && ... = ...
            return other;
        }

        @Override
        public Filter or(Filter other) {
            // true || ... = true
            return this;
        }
    }

    static final class AlwaysFalse extends Leaf {
        private static final Filter INSTANCE = new AlwaysTrue();

        @Override
        public String toSql() {
            return "0=1";
        }

        @Override
        public Filter negate() {
            return AlwaysFalse.INSTANCE;
        }

        @Override
        public Filter and(Filter other) {
            // false && ... = false
            return this;
        }

        @Override
        public Filter or(Filter other) {
            // false || ... = ...
            return other;
        }
    }

    sealed static abstract class Composite extends Filter permits Filter.And, Filter.Or, Filter.Not {
        @Override
        public final <R> R visit(FilterVisitor<R> visitor) {
            return visitor.visitComposite(this);
        }
    }

    static final class And extends Filter.Composite {
        private final List<Filter> children;

        private And(List<Filter> children) {
            this.children = List.copyOf(children);
        }

        @Override
        public Stream<Filter> children() {
            return children.stream();
        }

        @Override
        public Filter and(Filter other) {
            return new And(Stream.concat(children(), Stream.of(other)).collect(toUnmodifiableList()));
        }

        @Override
        public String toSql() {
            return children().map(c -> "(" + c.toSql() + ")").collect(joining(" AND "));
        }
    }

    static final class Or extends Filter.Composite {
        private final List<Filter> children;

        private Or(List<Filter> children) {
            this.children = List.copyOf(children);
        }

        @Override
        public Stream<Filter> children() {
            return children.stream();
        }

        @Override
        public Filter or(Filter other) {
            return new Or(Stream.concat(children(), Stream.of(other)).collect(toUnmodifiableList()));
        }

        @Override
        public String toSql() {
            return children().map(c -> "(" + c.toSql() + ")").collect(joining(" OR "));
        }
    }

    static final class Not extends Filter.Composite {
        private final Filter delegate;

        private Not(Filter delegate) {
            this.delegate = Objects.requireNonNull(delegate, "delegate");
        }

        @Override
        public Stream<Filter> children() {
            return Stream.of(delegate);
        }

        @Override
        public Filter negate() {
            return delegate;
        }

        @Override
        public String toSql() {
            return "NOT (" + delegate.toSql() + ")";
        }
    }
}
