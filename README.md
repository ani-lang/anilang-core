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
    <version>0.4.0</version>
</dependency>
```

## Syntax example
This is an example of the supported syntax.
```
v = v + 1 + ((2 - 3) * 4) / p.g(a)
# a method
def greeting(name, suffix):
    return 'hello ' + name + suffix
end
/*
 * A Hello class
 */
class Hello:
    struct Person:
        age
        name
        struct Address:
            name
            number
        end
    end
    def foo():
        if (a.r > 2 and 1 < 3 or d >= 2 or (9 <= 4 and s = 2)) != 4:
            a = 'hello' + 'world'
            if true or false:
                g = g.f + 0
                continue
            else
                c = 2.3 + 54
                continue
            end
            return
        end
    end
end
a = i.u
for g in h.p:
    a = 9
    f(a, 4)
    break
end
while a < 10:
    a = a + 1
end
c(8,9)
```

## Quick start

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
