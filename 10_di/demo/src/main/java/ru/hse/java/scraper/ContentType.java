package ru.hse.java.scraper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.charset.Charset;
import java.util.Objects;

public record ContentType(@Nonnull String type,
                          @Nullable String subtype,
                          @Nullable Charset charset) {
    public static final ContentType TEXT = new ContentType("text");
    public static final ContentType HTML = new ContentType("text", "html");

    public ContentType(@Nonnull String type) {
        this(type, null);
    }

    public ContentType(@Nonnull String type, @Nullable String subtype) {
        this(type, subtype, null);
    }

    public ContentType {
        Objects.requireNonNull(type, "type");
    }

    public int specificity() {
        return 1 + (subtype == null ? 0 : 2) + (charset == null ? 0 : 1);
    }

    public ContentType moreGenericType() {
        if (charset != null) {
            return new ContentType(this.type, this.subtype);
        }
        if (subtype != null) {
            return new ContentType(this.type);
        }
        return null;
    }

    public ContentType withCharset(@Nullable Charset charset) {
        return new ContentType(this.type, this.subtype, charset);
    }

    public boolean isCompatibleWith(@Nonnull ContentType other) {
        return type.equals(other.type)
                && (subtype == null || subtype.equals(other.subtype))
                && (charset == null || charset.equals(other.charset));
    }

    public String toString() {
        return type + "/"
                + (subtype == null ? "*" : subtype)
                + ";charset=" + (charset == null ? "*" : charset.name());
    }
}
