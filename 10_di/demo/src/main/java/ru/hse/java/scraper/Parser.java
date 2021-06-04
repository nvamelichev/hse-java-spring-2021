package ru.hse.java.scraper;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;

import static ru.hse.java.scraper.ZeLoggerQualifier.LogLevel.ERROR;

public class Parser {
    private final ZeLogger logger;
    private final String fileName;

    @AssistedInject
    public Parser(@ZeLoggerQualifier(ERROR) ZeLogger logger,
                  @Assisted String fileName) {
        this.logger = logger;
        this.fileName = fileName;
    }

    public String parse() {
        logger.error("Message from Parser{%s}", fileName);
        return "parse demo - " + fileName;
    }

    @AssistedFactory
    public interface Factory {
        Parser create(String fileName);
    }
}
