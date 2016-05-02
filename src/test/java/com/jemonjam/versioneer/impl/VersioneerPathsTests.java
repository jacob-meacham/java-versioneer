/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */

package com.jemonjam.versioneer.impl;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import org.junit.Test;

public class VersioneerPathsTests {
    @Test
    public final void testGetRunningPath() {
        Path path = VersioneerPaths.getRunningPath();
        assertTrue(path.toString().contains("java-versioneer"));
    }
}
