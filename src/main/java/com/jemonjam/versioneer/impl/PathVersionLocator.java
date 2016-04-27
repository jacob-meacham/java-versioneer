/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathVersionLocator implements VersionLocator {
    public static final String DEFAULT_VERSION_FORMAT = "(\\d+\\.\\d+\\.\\d+-*[0-9A-Za-z-]*)";
    private final Pattern pattern;
    private final String path;

    public PathVersionLocator() {
        this(ClassLoader.getSystemClassLoader().getResource(".").getPath(), DEFAULT_VERSION_FORMAT);
    }

    public PathVersionLocator(final String path, final String versionFormat) {
        if (path == null) {
            throw new IllegalArgumentException("path is required.");
        }

        if (versionFormat == null) {
            throw new IllegalArgumentException("versionFormat is required.");
        }

        this.pattern = Pattern.compile(versionFormat);
        this.path = path;
    }

    @Override
    public final Optional<String> getVersion() {
        try {
            Matcher matcher = pattern.matcher(path);
            Optional<String> version = Optional.empty();
            if (matcher.find()) {
                version = Optional.ofNullable(matcher.group(1));
            }
            return version;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
