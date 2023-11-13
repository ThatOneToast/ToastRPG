# ToastRPG
Set of tools to help create an RPG based plugin.

# Quickly get started!

Create a new Spigot or Paper project, go to your pom.xml and add the following repository and dependency.

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.ToastArgumentative</groupId>
    <artifactId>ToastRPG</artifactId>
    <version>v1.0.1-3</version>
    <scope>jar</scope>
</dependency>

```

Once these are added, you want to reload maven and head to your `onEnable()` method. Here you want to pass the plugin
onto Toast me :). 

```java
private static Instance plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        ToastRPG.passPluginToToast(this);

    }

    public static Instance getPlugin() {
        return plugin;
    }
```

Passing the plugin to Toast also Registers the managers. So always use `ToastRPG.getManager()` when accessing those. 

Documentation will be coming soon.

