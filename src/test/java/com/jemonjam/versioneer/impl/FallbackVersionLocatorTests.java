/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */

package com.jemonjam.versioneer.impl;

import static org.junit.Assert.assertEquals;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;
import org.junit.Test;

public class FallbackVersionLocatorTests {
    @Test
    public final void testFallback() {
        final String fallback = "fallback version" + System.currentTimeMillis();
        VersionLocator locator = new FallbackVersionLocator(fallback);
        Optional<String> version = locator.getVersion();
        assertEquals(version.get(), fallback);
    }

    @Test
    public final void testDefaultFallback() {
        VersionLocator locator = new FallbackVersionLocator();
        Optional<String> version = locator.getVersion();
        assertEquals(version.get(), FallbackVersionLocator.DEFAULT_FALLBACK);
    }
}
