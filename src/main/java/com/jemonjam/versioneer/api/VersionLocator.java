/*
 * Copyright 2016 Jacob Meacham (jemonjam.com).
 */
package com.jemonjam.versioneer.api;

import java.util.Optional;

/**
 * A VersionLocator (optionally) returns a version from an underlying resource.
 * VersionLocators should NEVER throw exceptions (except for JVM-crashing exceptions like OOM).
 * All exceptions should be caught and an Optional.empty() should be returned instead.
 *
 * @author jmeacham
 */
public interface VersionLocator {
    /**
     * Gets a version.
     * @return The optional version. If no version could be found, Optional.empty() is returned
     */
    Optional<String> getVersion();
}
