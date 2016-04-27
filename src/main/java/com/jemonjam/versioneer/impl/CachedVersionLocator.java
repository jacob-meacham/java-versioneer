/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;

/**
 * Caches the version from the underlying {@link VersionLocator}
 *
 * This will lazily check the delegate on the first call to {@link CachedVersionLocator#getVersion()}.
 * All subsequent calls will return the cached version, even if the version returned by the delegate was empty.
 *
 * @author jmeacham
 */
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
