/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;

public class CachedVersionLocator implements VersionLocator {
    private final VersionLocator delegate;
    private Optional<String> cachedVersion;

    public CachedVersionLocator(VersionLocator delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException("delegate is required.");
        }

        this.delegate = delegate;
    }

    @Override
    public final Optional<String> getVersion() {
        if (cachedVersion != null) {
            return cachedVersion;
        }

        cachedVersion = delegate.getVersion();
        return cachedVersion;
    }

}
