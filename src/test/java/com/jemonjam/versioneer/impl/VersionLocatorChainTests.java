/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */

package com.jemonjam.versioneer.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.mockito.Mockito;

public class VersionLocatorChainTests {
    @Test
    public final void testUsesFirstNonEmptyInChain() {
        VersionLocator mockEmpty = Mockito.mock(VersionLocator.class);
        when(mockEmpty.getVersion()).thenReturn(Optional.empty());

        VersionLocator mockGood = Mockito.mock(VersionLocator.class);
        when(mockGood.getVersion()).thenReturn(Optional.of("0.2.0-good"));

        VersionLocator locator = new VersionLocatorChain(mockEmpty, mockEmpty, mockGood, mockEmpty);
        Optional<String> version = locator.getVersion();

        assertEquals("0.2.0-good", version.get());

        verify(mockEmpty, times(2)).getVersion();
        verify(mockGood, times(1)).getVersion();

    }

    @Test
    public final void testEmptyIfAllEmpty() {
        VersionLocator mockDelegate = Mockito.mock(VersionLocator.class);
        when(mockDelegate.getVersion()).thenReturn(Optional.empty());

        List<VersionLocator> locators = new ArrayList<>();
        locators.add(mockDelegate);
        locators.add(mockDelegate);
        locators.add(mockDelegate);
        locators.add(mockDelegate);
        VersionLocator locator = new VersionLocatorChain(locators);
        Optional<String> version = locator.getVersion();
        assertFalse(version.isPresent());

        verify(mockDelegate, times(4)).getVersion();
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testBadArgs() {
        new VersionLocatorChain(new VersionLocator[0]);
    }
}
