package ru.hse.java.scraper.impl;

import ru.hse.java.scraper.DetectedContentType;
import ru.hse.java.scraper.Sniffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Set;

import static java.nio.charset.CodingErrorAction.REPORT;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_8;
import static ru.hse.java.scraper.ContentType.TEXT;

public class TextSniffer implements Sniffer {
    @Override
    public Set<DetectedContentType> sniff(byte[] bytes) {
        for (Charset cs : new Charset[]{US_ASCII, UTF_8}) {
            if (isDecodableAs(bytes, cs)) {
                return Set.of(new DetectedContentType(TEXT.withCharset(cs), 1.0));
            }
        }
        return Set.of();
    }

    private static boolean isDecodableAs(byte[] bytes, Charset cs) {
        try {
            cs
                    .newDecoder()
                    .onUnmappableCharacter(REPORT)
                    .onMalformedInput(REPORT)
                    .decode(ByteBuffer.wrap(bytes));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
