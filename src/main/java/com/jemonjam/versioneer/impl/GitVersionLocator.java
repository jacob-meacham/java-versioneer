/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class GitVersionLocator implements VersionLocator {
    private final String path;

    public GitVersionLocator() {
        this(ClassLoader.getSystemClassLoader().getResource(".").getPath());
    }

    public GitVersionLocator(String path) {
        this.path = path;
    }

    @Override
    public final Optional<String> getVersion() {
        try {
            FileRepositoryBuilder fileRepoBuilder = new FileRepositoryBuilder().findGitDir(new File(path));
            if (fileRepoBuilder.getGitDir() == null) {
                // Not found
                return Optional.empty();
            }

            try (Git git = new Git(fileRepoBuilder.build())) {
                String version = git.describe().call();
                return Optional.ofNullable(version);
            }
        } catch (IOException | GitAPIException e) {
            return Optional.empty();
        }
    }
}
