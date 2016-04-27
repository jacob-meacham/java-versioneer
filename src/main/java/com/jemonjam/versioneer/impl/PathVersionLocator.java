/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Returns a version parsed from the specified path with the specified pattern.
 *
 * The default path is the current directory, and the default pattern is a relaxed semver version.
 * Example versions
 * <ul>
 *  <li>/path/to/server-0.1.0/myjar.jar -- 0.1.0</li>
 *  <li>/path/to/server-0.1.0-foo/myjar.jar -- 0.1.0-foo</li>
 *  <li>/path/to/server-11.1.100-foo/myjar.jar -- 11.1.100-foo</li>
 *  <li>/path/to/server-11.1/myjar.jar -- Not present</li>
 *  <li>/path/to/server-11-1-1/myjar.jar -- Not present</li>
 * </ul>
 *
 * @author jmeacham
 */
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
