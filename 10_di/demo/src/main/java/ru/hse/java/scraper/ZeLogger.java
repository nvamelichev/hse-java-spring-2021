package ru.hse.java.scraper;

public interface ZeLogger {
    void error(String message);
    
    default void error(String message, Object... args) {
        error(String.format(message, args));
    }
}
