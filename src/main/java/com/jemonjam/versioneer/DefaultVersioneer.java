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
