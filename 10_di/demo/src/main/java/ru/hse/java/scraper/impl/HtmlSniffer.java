package ru.hse.java.scraper.impl;

import ru.hse.java.scraper.ContentType;
import ru.hse.java.scraper.DetectedContentType;
import ru.hse.java.scraper.Sniffer;

import java.util.Set;

public class HtmlSniffer implements Sniffer {
    @Override
    public Set<DetectedContentType> sniff(byte[] bytes) {
        boolean hasOpeningTag = false;
        boolean hasClosingTag = false;
        for (byte b : bytes) {
            hasOpeningTag = hasOpeningTag || b == '<';
            hasClosingTag = hasClosingTag || b == '>';
        }

        if (hasOpeningTag && hasClosingTag) {
            return Set.of(new DetectedContentType(ContentType.HTML, 1.0));
        } else if (hasOpeningTag) {
            return Set.of(new DetectedContentType(ContentType.HTML, 0.85));
        } else if (hasClosingTag) {
            return Set.of(new DetectedContentType(ContentType.HTML, 0.65));
        } else {
            return Set.of();
        }
    }
}
