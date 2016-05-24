/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;

/**
 * Always returns a static version.
 *
 * The default static version is "Development Version"
 *
 * @author jmeacham
 */
public class StaticVersionLocator implements VersionLocator {
    public static final String DEFAULT_VERSION = "Development Version";
    private final String staticVersion;

    public StaticVersionLocator() {
        this(DEFAULT_VERSION);
    }

    public StaticVersionLocator(String staticVersion) {
        this.staticVersion = staticVersion;
    }

    @Override
    public final Optional<String> getVersion() {
        return Optional.ofNullable(staticVersion);
    }

}
