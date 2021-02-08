package ru.hse.java.util;

import java.time.Instant;

public final class Logging {
    private Logging() {
    }

    public static void log(String message) {
        System.out.println(Instant.now() + " INFO - " + message);
    }
}
