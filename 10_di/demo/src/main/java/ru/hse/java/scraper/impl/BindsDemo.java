package ru.hse.java.scraper.impl;

import ru.hse.java.scraper.BindsDemoInterface;
import ru.hse.java.scraper.ZeLogger;
import ru.hse.java.scraper.ZeLoggerQualifier;

import javax.inject.Inject;

import static ru.hse.java.scraper.ZeLoggerQualifier.LogLevel.ERROR;

public class BindsDemo implements BindsDemoInterface {
    private final ZeLogger logger;

    @Inject
    public BindsDemo(@ZeLoggerQualifier(ERROR) ZeLogger logger) {
        this.logger = logger;
    }

    @Override
    public void run() {
        logger.error("hey hey from @Binds SomeDemoInterface!");
    }
}
