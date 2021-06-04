package ru.hse.java.scraper;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static ru.hse.java.scraper.ZeLoggerQualifier.LogLevel.ERROR;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface ZeLoggerQualifier {
    LogLevel value() default ERROR;

    enum LogLevel {
        ERROR,
        OFF
    }
}
