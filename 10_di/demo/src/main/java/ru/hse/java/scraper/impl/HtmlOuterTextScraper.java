package ru.hse.java.scraper.impl;

import ru.hse.java.scraper.ContentType;
import ru.hse.java.scraper.Scraper;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HtmlOuterTextScraper implements Scraper {
    @Nonnull
    @Override
    public ContentType type() {
        return ContentType.HTML;
    }

    @Override
    public void scrape(@Nonnull InputStream in, @Nonnull OutputStream out) throws IOException {
        int b;
        boolean insideTag = false;
        while (-1 != (b = in.read())) {
            switch (b) {
                case '<' -> insideTag = true;
                case '>' -> insideTag = false;
                default -> {
                    if (!insideTag) {
                        out.write(b);
                    }
                }
            }
        }
    }
}
