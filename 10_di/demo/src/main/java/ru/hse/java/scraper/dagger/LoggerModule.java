package ru.hse.java.scraper.dagger;

import dagger.Module;
import dagger.Provides;
import ru.hse.java.scraper.ZeLogger;
import ru.hse.java.scraper.ZeLoggerQualifier;

import static ru.hse.java.scraper.ZeLoggerQualifier.LogLevel.ERROR;
import static ru.hse.java.scraper.ZeLoggerQualifier.LogLevel.OFF;

@Module
public abstract class LoggerModule {
    @Provides
    @ZeLoggerQualifier(ERROR)
    public static ZeLogger stderrLogger() {
        return System.err::println;
    }

    @Provides
    @ZeLoggerQualifier(OFF)
    public static ZeLogger silentLogger() {
        return __ -> {
        };
    }
}
