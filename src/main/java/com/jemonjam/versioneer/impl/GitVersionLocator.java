/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 * Returns a version from a git repo using the command git describe.
 *
 * git describe uses annotated tags to determine the current version of the repo. To use these,
 * <br>
 * git tag -a 0.2.1 -m "My 0.2.1 release"
 * git describe -&gt; 0.2.1
 * <br>
 * Later on, after I've made some commits to the branch:
 * git describe -&gt; 0.2.1-4-g7147b14
 * <br>
 * See the <a href=https://git-scm.com/docs/git-describe>git documentation</a> for more details.
 *
 * @author jmeacham
 */
public class GitVersionLocator implements VersionLocator {
    private final Path path;

    public GitVersionLocator() {
        this(VersioneerPaths.getRunningPath());
    }

    public GitVersionLocator(Path path) {
        this.path = path;
    }

    @Override
    public final Optional<String> getVersion() {
        try {
            FileRepositoryBuilder fileRepoBuilder = new FileRepositoryBuilder().findGitDir(path.toFile());
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
