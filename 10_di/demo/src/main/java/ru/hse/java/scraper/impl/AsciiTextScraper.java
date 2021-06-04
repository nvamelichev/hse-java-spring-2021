package ru.hse.java.scraper.impl;

import ru.hse.java.scraper.ContentType;
import ru.hse.java.scraper.Scraper;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.US_ASCII;

/**
 * Strips everything outside US-ASCII printable characters.
 */
public class AsciiTextScraper implements Scraper {
    private static final byte[] ASCII_PRINTABLE_CHARACTERS =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz `~!@#$%^&*()_-+={}[]\"|<>?,:;./\\'\t\n\r".getBytes(US_ASCII);

    static {
        Arrays.sort(ASCII_PRINTABLE_CHARACTERS);
    }

    @Nonnull
    @Override
    public ContentType type() {
        return ContentType.TEXT;
    }

    @Override
    public void scrape(@Nonnull InputStream in, @Nonnull OutputStream out) throws IOException {
        int b;
        while (-1 != (b = in.read())) {
            if (Arrays.binarySearch(ASCII_PRINTABLE_CHARACTERS, (byte) b) >= 0) {
                out.write(b);
            }
        }
    }
}
