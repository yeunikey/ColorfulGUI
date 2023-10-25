# ColorfulGUI
A feature-rich library for Bukkit API plugins to create and manage inventories. By using ColorfulGUI you can easily build your inventory.

## Features

* Creating menus of different types
  * Basic menus
  * Multi-page system
* Universal menu provider
* All types of listeners
* Different types of items
  * Static item
  * Dynamic item
* Mask system

## Setup

Basic requirements for working with the library

### Requirements

* Paper API 1.14+

Once all requirements have been met, you must add the dependency to Maven

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.xflyiwnl</groupId>
    <artifactId>ColorfulGUI</artifactId>
    <version>2.0</version>
</dependency>
```

now register the ColorfulGUI class.
```java
ColorfulGUI colorfulGUI = new ColorfulGUI(yourMainClass);
```

then follow the documentation [here](https://github.com/xflyiwnl/ColorfulGUI/wiki)

## Communication
If you need help, you can ask a question in our discord server - https://discord.gg/jpwRyUqg3G
