/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class FileVersionLocator implements VersionLocator {
    private final String path;

    public FileVersionLocator(final String path) {
        this.path = path;
    }

    @Override
    public final Optional<String> getVersion() {
        try {
            String version = new String(Files.readAllBytes(Paths.get(path)));
            return Optional.ofNullable(version);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}
