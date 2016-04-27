/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */

package com.jemonjam.versioneer.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;
import org.junit.Test;
import org.mockito.Mockito;

public class CachedVersionLocatorTests {
    @Test
    public final void testCacheWorks() {
        VersionLocator mockDelegate = Mockito.mock(VersionLocator.class);
        when(mockDelegate.getVersion()).thenReturn(Optional.of("foo"));

        VersionLocator locator = new CachedVersionLocator(mockDelegate);
        Optional<String> version = locator.getVersion();
        Optional<String> newVersion = locator.getVersion();

        verify(mockDelegate, times(1)).getVersion();
        assertEquals(version.get(), newVersion.get());
    }
}
