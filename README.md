Versioneer
==========
[ ![Download](https://api.bintray.com/packages/jacob-meacham/maven/java-versioneer/images/download.svg) ](https://bintray.com/jacob-meacham/maven/java-versioneer/_latestVersion)

Versioneer is a composable java package for introspecting the version of a Java application.

Quick Start
-----------
If you're using gradle, to your build.gradle, add
```
compile 'com.jemonjam.versioneer:java-versioneer:0.2.1'
```

If you're using maven, to you pom.xml, add
```
<dependency>
  <groupId>com.jemonjam.versioneer</groupId>
  <artifactId>java-versioneer</artifactId>
  <version>0.2.1</version>
  <type>pom</type>
</dependency>
```

Then, all you need to do is
```
VersionLocator versioneer = new DefaultVersioneer();
// ... Later on
version.getVersion().orElse("UNSPECIFIED");
```

The DefaultVersioneer searches in this order:
* A .VERSIONEER file on your classpath
* A version from git, using git describe
* A version in the path of the application, using a relaxed semver pattern (an application running at a/b/c-10.0.1-foo/myapp would return 10.0.1-foo)
* Development Version

The result is cached so the path is only searched once

Advanced Usage
--------------
All Versioneer functionality is exposed as a VersionLocator. Currently supported VersionLocators are:
* GitVersionLocator - Returns the output of `git describe` from the specified repo (current directory by default)
* FileVersionLocator - Returns the contets of the specified file
* PathVersionLocator - Returns a version parsed from the specified path with the specified pattern (current directory and a relaxed [semver](http://semver.org/) version.)
* ClasspathVersionLocator - Returns the contents of the version file found on the classpath (default file is .VERSIONEER)
* FallbackVersionLocator - Returns a specified string (default: Development Version)
* CachedVersionLocator - Caches the result of an underlying locator

For convenience, VersionLocatorChain allows you to chain together other locators. So, if you wanted to check a specific file, and then a path, and finally the version of a specified git repo, you would write:

```
VersionLocator versionChain = new VersionLocatorChain(
    new FileVersionLocator("path/to/file"),
    new PathVersionLocator("some/path", "\\d+\\-\\d+"), // Finds version of the form xx-yy
    new GitVersionLocator("path/to/git/repo")
    );

String myVersion = versionChain.getVersion().orElse("NOT FOUND");
```

This will check each locator in the chain each time getVersion is called and greedily return the first non-empty result. If (as is usual) you don't expect the version to change, you can wrap the whole thing in a CachedVersionLocator

```
VersionLocator cachedVersion = new CachedVersionLocator(versionChain);

cachedVersion.getVersion();
cachedVersion.getVersion(); // Will return the cached result, whether or not the version was specified.
```
