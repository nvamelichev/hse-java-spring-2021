package ru.hse.java.sql;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.hse.java.sql.Filter.eq;
import static ru.hse.java.sql.Filter.neq;

public class FilterTest {
    @Test
    public void composite() {
        var filter = eq("$param1", "xyzzy")
                .and(
                        neq("$param2", "uzhos").or(neq("$answer", 42L))
        );
        //Predicate<X> xx = x -> x.param1.equals("xyzzy") && ( !x.param2.equals("uzhos") || x.answer != 42L );
        assertThat(filter.toSql()).isEqualTo("($param1=xyzzy) AND (($param2<>uzhos) OR ($answer<>42))");
        assertThat(filter.negate().toSql()).isEqualTo("NOT (($param1=xyzzy) AND (($param2<>uzhos) OR ($answer<>42)))");
        assertThat(filter.negate().negate()).isEqualTo(filter);
        assertThat(filter).isEqualTo(filter);
        assertThat(filter).isEqualTo(eq("$param1", "xyzzy").and(neq("$param2", "uzhos").or(neq("$answer", 42L))));
    }
}
