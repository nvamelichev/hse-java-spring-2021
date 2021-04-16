package ru.hse.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@lombok.Log4j2
public class LoggingDemo {
    private static final Logger log = LogManager.getLogger(LoggingDemo.class);

    public static void main(String[] args) {
        log.error("Hello, world! {}", args[0]); // SLF4j / Log4j2, Logback
        log.error(() -> "Hello, world " + args[0]);
        log.debug("Debug");

        // MDC NDC
        // %{request-id}

        System.console().reader();
    }
}
