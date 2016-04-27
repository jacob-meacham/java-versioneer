/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */

package com.jemonjam.versioneer.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import org.junit.Test;

public class GitVersionLocatorTests {
    @Test
    public final void testGetsVersion() {
        GitVersionLocator locator = new GitVersionLocator();
        Optional<String> version = locator.getVersion();

        assertTrue(version.isPresent());
    }

    @Test
    public final void testNoVersion() {
        GitVersionLocator locator = new GitVersionLocator("/does/not/exist");
        Optional<String> version = locator.getVersion();
        assertFalse(version.isPresent());
    }
}
