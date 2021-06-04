package ru.hse.java.scraper;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Scraper {
    @Nonnull
    ContentType type();

    /**
     * Processes content of the input stream specified and outputs scraping results
     * to the output stream specified.
     *
     * @param in  input stream, containing data of {@link #type() scraper's content type}
     * @param out stream to write scraper output to
     */
    void scrape(@Nonnull InputStream in, @Nonnull OutputStream out) throws IOException;
}
