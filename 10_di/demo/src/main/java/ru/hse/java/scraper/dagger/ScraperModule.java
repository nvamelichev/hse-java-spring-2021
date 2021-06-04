package ru.hse.java.scraper.dagger;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
import ru.hse.java.scraper.Scraper;
import ru.hse.java.scraper.Sniffer;
import ru.hse.java.scraper.impl.AsciiTextScraper;
import ru.hse.java.scraper.impl.HtmlOuterTextScraper;
import ru.hse.java.scraper.impl.HtmlSniffer;
import ru.hse.java.scraper.impl.TextSniffer;

@Module
public abstract class ScraperModule {
//    @Binds
//    public abstract Fignya1 fignya1(@Named("xyz") ConcreteFignya1 __);

    @Provides
    @IntoSet
    public static Scraper asciiTextScraper() {
        return new AsciiTextScraper();
    }

    @Provides
    @IntoSet
    public static Scraper htmlOuterTextScraper() {
        return new HtmlOuterTextScraper();
    }

    @Provides
    @IntoSet
    public static Sniffer textSniffer() {
        return new TextSniffer();
    }

    @Provides
    @IntoSet
    public static Sniffer htmlSniffer() {
        return new HtmlSniffer();
    }
}
