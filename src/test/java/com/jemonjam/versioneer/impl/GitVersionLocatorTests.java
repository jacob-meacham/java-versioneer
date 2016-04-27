/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */

package com.jemonjam.versioneer.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GitVersionLocatorTests {
    private Path tempDirectory;

    @Before
    public final void setup() throws IOException {
        tempDirectory = Files.createTempDirectory("versioneer").toAbsolutePath();
    }

    @After
    public final void teardown() throws IOException {
        FileUtils.deleteDirectory(tempDirectory.toFile());
    }

    @Test
    public final void testGetsVersion() throws IllegalStateException, GitAPIException {
        final String versionString = "10.1.100-abc";

        final Git git = Git.init().setDirectory(tempDirectory.toFile()).call();
        git.commit().setAllowEmpty(true).setMessage("root commit").call();
        git.tag().setAnnotated(true).setName(versionString).setMessage(versionString).call();

        GitVersionLocator locator = new GitVersionLocator(tempDirectory.toString());
        Optional<String> version = locator.getVersion();

        assertEquals(versionString, version.get());
    }

    @Test
    public final void testNoVersion() {
        GitVersionLocator locator = new GitVersionLocator("/does/not/exist");
        Optional<String> version = locator.getVersion();
        assertFalse(version.isPresent());
    }
}
