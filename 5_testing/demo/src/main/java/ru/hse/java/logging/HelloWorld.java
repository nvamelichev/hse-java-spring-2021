package ru.hse.java.logging;

import com.google.common.base.Joiner;

import java.util.Objects;

public class HelloWorld {
    private final Printer printer;

    HelloWorld(Printer printer) {
        this.printer = Objects.requireNonNull(printer, "printer");
    }

    public static void main(String[] args) {
        new HelloWorld(Printer.SYSTEM_OUT).run().run();
        System.err.println("Printed to sysout: " + Printer.SYSTEM_OUT.printedCount());

        new HelloWorld(Printer.BIT_BUCKET).run().run();
        System.err.println("Printed to bit bucket: " + Printer.BIT_BUCKET.printedCount());
    }

    public HelloWorld run() {
        printer.println("Hello, world!");
        return this;
    }
}
