/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */

package com.jemonjam.versioneer.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.jemonjam.versioneer.api.VersionLocator;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.junit.Test;

public class FileVersionLocatorTests {
    @Test
    public final void testGetVersion() {
        Path path = Paths.get("src/test/resources/version.file");

        VersionLocator locator = new FileVersionLocator(path);
        Optional<String> version = locator.getVersion();
        assertEquals("0.3.0-tag", version.get());
    }

    @Test
    public final void testNoVersion() {
        VersionLocator locator = new FileVersionLocator(Paths.get("does/not/exist"));
        Optional<String> version = locator.getVersion();
        assertFalse(version.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testBadArgs() {
        new FileVersionLocator(null);
    }
}
