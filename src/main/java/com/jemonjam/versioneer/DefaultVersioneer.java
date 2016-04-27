/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer;

import com.jemonjam.versioneer.api.VersionLocator;
import com.jemonjam.versioneer.impl.CachedVersionLocator;
import com.jemonjam.versioneer.impl.ClasspathVersionLocator;
import com.jemonjam.versioneer.impl.FallbackVersionLocator;
import com.jemonjam.versioneer.impl.GitVersionLocator;
import com.jemonjam.versioneer.impl.PathVersionLocator;
import com.jemonjam.versioneer.impl.VersionLocatorChain;
import java.util.Optional;

/**
 * A good default {@link VersionLocator}.
 *
 * Returns versions in this order:
 * <ul>
 * <li>Classpath for .VERSIONEER file ({@link ClasspathVersionLocator})</li>
 * <li>The current directory as a git repo, using git describe ({@link GitVersionLocator})</li>
 * <li>The current directory for a semver version in the path ({@link PathVersionLocator})</li>
 * <li>Development Version ({@link FallbackVersionLocator})</li>
 * </ul>
 *
 * The resulting value is cached on the first call to {@link #getVersion()}
 *
 * @author jmeacham
 */
public class DefaultVersioneer implements VersionLocator {
    private final VersionLocator delegate;

    public DefaultVersioneer() {
        VersionLocator chain = new VersionLocatorChain(
                new ClasspathVersionLocator(),
                new GitVersionLocator(),
                new PathVersionLocator(),
                new FallbackVersionLocator());

        delegate = new CachedVersionLocator(chain);
    }

    @Override
    public final Optional<String> getVersion() {
        return delegate.getVersion();
    }

}
