/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */

package com.jemonjam.versioneer.impl;

import static org.junit.Assert.assertEquals;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;
import org.junit.Test;

public class StaticVersionLocatorTests {
    @Test
    public final void testStatic() {
        final String staticVersion = "static version" + System.currentTimeMillis();
        VersionLocator locator = new StaticVersionLocator(staticVersion);
        Optional<String> version = locator.getVersion();
        assertEquals(staticVersion, version.get());
    }

    @Test
    public final void testDefaultStaticVersion() {
        VersionLocator locator = new StaticVersionLocator();
        Optional<String> version = locator.getVersion();
        assertEquals(StaticVersionLocator.DEFAULT_VERSION, version.get());
    }
}
