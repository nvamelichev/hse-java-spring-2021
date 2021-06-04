package ru.hse.java.scraper;

import javax.annotation.Nonnull;
import java.util.Objects;

public record DetectedContentType(@Nonnull ContentType contentType, double certainty) implements Comparable<DetectedContentType> {
    public DetectedContentType {
        Objects.requireNonNull(contentType, "contentType");
    }

    @Override
    public int compareTo(@Nonnull DetectedContentType other) {
        int res = Integer.compare(this.contentType.specificity(), other.contentType.specificity());
        if (res != 0) {
            return res;
        }

        res = Double.compare(this.certainty, other.certainty);

        return res;
    }
}
