package ru.hse.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class RangeTest {
    @Test
    public void bounds() {
        // Setup
        Range range = new Range(15, 20);

        // Act

        // Verify
        assertThat(range.min()).isEqualTo(15);
        assertThat(range.max()).isEqualTo(19);
        assertThat(range.rightExclusive()).isEqualTo(20);

        // Teardown
    }

    @Test
    public void contains() {
        Range range = new Range(15, 20);

        assertThat(range.contains(17)).isTrue();
    }

    @Test
    public void does_not_contain() {
        Range range = new Range(15, 20);

        assertThat(range.contains(1)).isFalse();
    }

    @Test
    public void stream() {
        assertThat(new Range(1, 3).stream()).containsExactly(1, 2);
    }

    @Test
    public void invalid_range() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Range(3, 1));
    }

    @Test
    public void empty_range() {
        assertThat(new Range(1, 1).isEmpty()).isTrue();
        assertThat(new Range(1, 1).stream()).isEmpty();
    }

    @Test
    public void negative() {
        assertThat(new Range(-5, 2).stream())
                .containsExactly(-5, -4, -3, -2, -1, 0, 1);
        assertThat(new Range(-5, 2).contains(-3)).isTrue();
    }

    @Test
    public void max_int() {
        assertThat(new Range(Integer.MAX_VALUE, Integer.MAX_VALUE).isEmpty())
                .withFailMessage("max int to max int is empty")
                .isTrue();
        assertThatIllegalArgumentException().isThrownBy(() -> new Range(Integer.MAX_VALUE, Integer.MAX_VALUE+1));
        assertThat(new Range(Integer.MAX_VALUE, Integer.MAX_VALUE).size()).isZero();
    }

    @Test
    public void min_int() {
        assertThat(new Range(Integer.MIN_VALUE, Integer.MIN_VALUE).size()).isZero();

        assertThat(new Range(Integer.MIN_VALUE, Integer.MAX_VALUE).isEmpty()).isFalse();
        assertThat(new Range(Integer.MIN_VALUE, Integer.MAX_VALUE).size()).isEqualTo((long) Integer.MAX_VALUE*2L+1);

        assertThatIllegalArgumentException().isThrownBy(() -> new Range(Integer.MIN_VALUE-1, Integer.MIN_VALUE));
    }
}
