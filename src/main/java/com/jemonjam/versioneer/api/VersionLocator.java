package com.jemonjam.versioneer.api;

import java.util.Optional;

public interface VersionLocator {
    Optional<String> getVersion();
}
