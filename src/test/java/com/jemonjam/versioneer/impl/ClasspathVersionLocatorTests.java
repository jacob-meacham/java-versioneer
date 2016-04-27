/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */

package com.jemonjam.versioneer.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;
import org.junit.Test;

public class ClasspathVersionLocatorTests {
    @Test
    public final void testGetDefaultVersion() {
        VersionLocator locator = new ClasspathVersionLocator();
        Optional<String> version = locator.getVersion();

        assertEquals("11.0.0-VERSIONEER", version.get());
    }

    @Test
    public final void testGetSpecifiedVersion() {
        VersionLocator locator = new ClasspathVersionLocator("version.file");
        Optional<String> version = locator.getVersion();

        assertEquals("0.3.0-tag", version.get());
    }

    @Test
    public final void testNoVersion() {
        VersionLocator locator = new ClasspathVersionLocator("does/not/exist");
        Optional<String> version = locator.getVersion();

        assertFalse(version.isPresent());
    }
}
