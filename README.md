[![](https://jitpack.io/v/ani-lang/anilang-core.svg)](https://jitpack.io/#ani-lang/anilang-core)

# ani lang

Core tools for the programming language **ani**.

# Install

Step 1: Add the JitPack repository to your build file

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Step 2: Add the dependency

```xml
<dependency>
    <groupId>com.github.ani-lang</groupId>
    <artifactId>anilang-core</artifactId>
    <version>0.3.1</version>
</dependency>
```

## Quick guide

You can parse an **ani** piece of code

```java
// file.ani => a = 1 + (2 - 3)
final AniParser parser = new AniFile(input).parse(); 
```

If the parsed content has grammar errors it will throw an exception when parsing `AniParser.file()`. To avoid this
situation you can scan for errors before you parse

```java
// file.ani => a = .
final List<ParseError> errors = new AniFile(input).errors();
```

## Contribute

````shell
$ ./mvnw clean package -Pqulice
````

It's required to set up the ANTLR plugin for intellij
following [these instructions](https://docs.google.com/document/d/1gQ2lsidvN2cDUUsHEkT05L-wGbX5mROB7d70Aaj3R64/edit#).
This plugin will help when working on the grammar.
