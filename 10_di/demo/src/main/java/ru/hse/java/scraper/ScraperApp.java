package ru.hse.java.scraper;

import dagger.Component;
import ru.hse.java.scraper.dagger.AssistedInjectModule;
import ru.hse.java.scraper.dagger.BindsDemoModule;
import ru.hse.java.scraper.dagger.LoggerModule;
import ru.hse.java.scraper.dagger.ScraperModule;
import ru.hse.java.scraper.impl.BindsDemo;

import javax.inject.Inject;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;

import static ru.hse.java.scraper.ZeLoggerQualifier.LogLevel.OFF;

public final class ScraperApp {
    private static final int MAX_SNIFF_BYTES = 20;

    private final Set<Scraper> scrapers;
    private final Set<Sniffer> sniffers;
    private final ZeLogger logger;

    private final BindsDemo someDemo;
    private final Parser.Factory parserFactory;

    @Component(modules = {
            ScraperModule.class,
            LoggerModule.class,
            BindsDemoModule.class,
            AssistedInjectModule.class
    })
    public interface Factory {
        ScraperApp app();
    }

    @Inject
    public ScraperApp(Set<Scraper> scrapers, Set<Sniffer> sniffers,
                      @ZeLoggerQualifier(OFF) ZeLogger logger,
                      BindsDemo someDemo,
                      Parser.Factory parserFactory) {
        this.scrapers = scrapers;
        this.sniffers = sniffers;
        this.logger = logger;

        this.someDemo = someDemo;
        this.parserFactory = parserFactory;
    }

    public static void main(String[] args) throws IOException {
        // Manual DI:
//        new ScraperApp(
//                Set.of(new AsciiTextScraper(), new HtmlOuterTextScraper()),
//                Set.of(new TextSniffer(), new HtmlSniffer())
//        ).run(args);

        // DI via Google Dagger framework:
        DaggerScraperApp_Factory
                .create().app()
                .run(args);
    }

    public void run(String... paths) throws IOException {
        someDemo.run();
        for (String path : paths) {
            parserFactory.create(path).parse();
            scrape(path);
        }
    }

    private void scrape(String path) throws IOException {
        DetectedContentType bestCurrentGuess = null;
        try (FileInputStream fis = new FileInputStream(path)) {
            byte[] bytes = readAll(fis, MAX_SNIFF_BYTES);
            for (Sniffer sniffer : sniffers) {
                for (DetectedContentType guess : sniffer.sniff(bytes)) {
                    if (bestCurrentGuess == null || guess.compareTo(bestCurrentGuess) > 0) {
                        bestCurrentGuess = guess;
                    }
                }
            }
        }
        if (bestCurrentGuess == null) {
            logger.error("Could not guess content type for %s", path);
            return;
        }

        ContentType contentType = bestCurrentGuess.contentType();
        Scraper scraper = findScraper(contentType);
        if (scraper == null) {
            logger.error("No scraper found for Content-Type: %s", contentType);
            return;
        }

        try (FileInputStream fis = new FileInputStream(path);
             BufferedInputStream bis = new BufferedInputStream(fis)) {
            scraper.scrape(bis, System.out);
        }
    }

    private static byte[] readAll(InputStream in, int length) throws IOException {
        byte[] bytes = new byte[length];

        int pos = 0;
        int remaining = length;
        int nBytesRead;
        while (pos < length
                && -1 != (nBytesRead = in.read(bytes, pos, remaining))) {
            pos += nBytesRead;
            remaining -= nBytesRead;
        }

        return Arrays.copyOf(bytes, pos);
    }

    private Scraper findScraper(ContentType contentType) {
        ContentType current = contentType;
        while (current != null) {
            Scraper s = findScraperExact(current);
            if (s != null) {
                return s;
            }
            current = current.moreGenericType();
        }
        return null;
    }

    private Scraper findScraperExact(ContentType contentType) {
        for (Scraper scraper : scrapers) {
            if (contentType.equals(scraper.type())) {
                return scraper;
            }
        }
        return null;
    }
}
