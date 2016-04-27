/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

public class ClasspathVersionLocator implements VersionLocator {
    public static final String DEFAULT_VERSION_FILE = ".VERSIONEER";
    private final String versionFile;

    public ClasspathVersionLocator() {
        this(DEFAULT_VERSION_FILE);
    }

    public ClasspathVersionLocator(String versionFile) {
        if (versionFile == null) {
            throw new IllegalArgumentException("versionFile is required.");
        }

        this.versionFile = versionFile;
    }

    @Override
    public final Optional<String> getVersion() {
        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream(versionFile);
                Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name()).useDelimiter("\\A")) {
            return Optional.of(scanner.next());
        } catch (NullPointerException e) {
            return Optional.empty();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
