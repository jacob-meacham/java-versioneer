package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;

public class FallbackVersionLocator implements VersionLocator {
    public static final String DEFAULT_FALLBACK = "Development Version";
    private final String fallbackString;

    public FallbackVersionLocator() {
        this(DEFAULT_FALLBACK);
    }

    public FallbackVersionLocator(String fallbackString) {
        this.fallbackString = fallbackString;
    }

    @Override
    public final Optional<String> getVersion() {
        return Optional.ofNullable(fallbackString);
    }

}
