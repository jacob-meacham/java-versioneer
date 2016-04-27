/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */

package com.jemonjam.versioneer.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;
import org.junit.Test;

public class PathVersionLocatorTests {
    @Test
    public final void testPathVersionLocator() {
        VersionLocator locator = new PathVersionLocator("a/b/0.2.0", PathVersionLocator.DEFAULT_VERSION_FORMAT);
        Optional<String> version = locator.getVersion();
        assertEquals("0.2.0", version.get());

        locator = new PathVersionLocator("a/b/0.2.0/c/d", PathVersionLocator.DEFAULT_VERSION_FORMAT);
        version = locator.getVersion();
        assertEquals("0.2.0", version.get());

        locator = new PathVersionLocator("a/b/0.2.0-tag", PathVersionLocator.DEFAULT_VERSION_FORMAT);
        version = locator.getVersion();
        assertEquals("0.2.0-tag", version.get());

        locator = new PathVersionLocator("a/b", PathVersionLocator.DEFAULT_VERSION_FORMAT);
        version = locator.getVersion();
        assertFalse(version.isPresent());

        locator = new PathVersionLocator("a/b/0/c/d", PathVersionLocator.DEFAULT_VERSION_FORMAT);
        version = locator.getVersion();
        assertFalse(version.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testBadPath() {
        new PathVersionLocator(null, PathVersionLocator.DEFAULT_VERSION_FORMAT);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testBadPattern() {
        new PathVersionLocator("a/b/0/c/d", null);
    }
}
