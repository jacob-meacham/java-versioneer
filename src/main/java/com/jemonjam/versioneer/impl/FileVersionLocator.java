/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.impl;

import com.jemonjam.versioneer.api.VersionLocator;
import java.util.Optional;

public class FileVersionLocator implements VersionLocator {

    @Override
    public final Optional<String> getVersion() {
        return Optional.empty();
    }

}
