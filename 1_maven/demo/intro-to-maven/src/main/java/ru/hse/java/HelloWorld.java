package ru.hse.java;

import com.google.common.base.Joiner;

import java.util.Objects;

/**
 * @author entropia
 */
public class HelloWorld {
    private final Printer printer;

    HelloWorld(Printer printer) {
        this.printer = Objects.requireNonNull(printer, "printer");
    }

    public static void main(String[] args) {
        new HelloWorld(System.out::println).run();
        new HelloWorld(Printer.bitbucket()).run();

        System.out.println(Joiner.on(',').useForNull("???").join("hello", "goodbye", null, "hi"));
    }

    public void run() {
        printer.println("Hello, world!");
    }

    interface Printer {
        void println(String message);

        static Printer bitbucket() {
            return __ -> {};
        }
    }
}
