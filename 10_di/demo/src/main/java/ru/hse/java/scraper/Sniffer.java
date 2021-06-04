package ru.hse.java.scraper;

import java.io.InputStream;
import java.util.Set;

/**
 * Tries to detect an {@link InputStream input stream}'s {@link ContentType content type}
 * by reading a small amount of stream content.
 */
public interface Sniffer {
    /**
     * Tries to detect {@link ContentType content type(s)} matching the specified byte prefix.
     *
     * @param bytes byte array to inspect
     * @return set of {@link DetectedContentType content types} detected; allowed to be empty!
     */
    Set<DetectedContentType> sniff(byte[] bytes);
}
