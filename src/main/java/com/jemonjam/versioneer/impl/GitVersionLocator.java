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

public class GitVersionLocator implements VersionLocator {

    @Override
    public final Optional<String> getVersion() {
        try {
            String path = ClassLoader.getSystemClassLoader().getResource(".").getPath();
            Git git = Git.open(new File(path));
            String version = git.describe().call();
            return Optional.ofNullable(version);
        } catch (IOException | GitAPIException e) {
            return Optional.empty();
        }

    }

}
