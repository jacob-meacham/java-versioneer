/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.api;

import java.util.Optional;

public interface VersionLocator {
    Optional<String> getVersion();
}
