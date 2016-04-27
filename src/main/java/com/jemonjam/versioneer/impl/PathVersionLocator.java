/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathVersionLocator implements VersionLocator {
    private static final String DEFAULT_FORMAT = "(\\d\\.\\d\\.\\d[A-Za-z][0-9A-Za-z-]*)?";
    private final Pattern pattern;

    public PathVersionLocator() {
        this(DEFAULT_FORMAT);
    }

    public PathVersionLocator(final String versionFormat) {
        if (versionFormat == null) {
            throw new IllegalArgumentException("versionFormat is required.");
        }

        pattern = Pattern.compile(versionFormat);
    }

    @Override
    public final Optional<String> getVersion() {
        String path = ClassLoader.getSystemClassLoader().getResource(".").getPath();
        Matcher matcher = pattern.matcher(path);
        String version = matcher.group(0);

        return Optional.ofNullable(version);
    }
}
