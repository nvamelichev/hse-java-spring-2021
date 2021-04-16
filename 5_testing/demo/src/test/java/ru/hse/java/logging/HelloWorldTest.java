package ru.hse.java.logging;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldTest {
    @Test
    public void print_hello_world() {
        var printer = new Printer() {
            String lastPrinted;
            int count;

            @Override
            public void println(String message) {
                lastPrinted = message;
                count++;
            }

            @Override
            public int printedCount() {
                return count;
            }
        };

        new HelloWorld(printer).run();

        assertThat(printer.lastPrinted).isEqualTo("Hello, world!");
        assertThat(printer.count).isOne();
    }
}
