/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */

package com.jemonjam.versioneer.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class VersioneerPaths {
    private VersioneerPaths() { /* Prevent instantiation */ }

    public static Path getRunningPath() {
        try {
            return Paths
                    .get(VersioneerPaths.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (Exception e) {
            return Paths.get(ClassLoader.getSystemClassLoader().getResource(".").getPath());
        }

    }
}
