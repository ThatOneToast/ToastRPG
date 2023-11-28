---
description: >-
  Just learn about ToastRPG? Follow this quick and easy guide to get started.
  This guide works under the assumption you know basic java, and can
  create/navigate through a project.
---

# ToastRPG - Getting Started!

For best compatibility use `Kotlin 1.9.20` and `Paper >= 1.20.2`&#x20;

Editing your `pom.xml` If you have created your project with the Minecraft Development plugin inside your IntelliJ editor you only need to add the follow dependency and repository

{% code overflow="wrap" fullWidth="true" %}
```xml
</repositories>

    <dependencies>

        <dependency>
            <groupId>io.papermc.paper</groupId>
            <artifactId>paper-api</artifactId>
            <version>1.20.2-R0.1-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>org.jetbrains.kotlin</groupId>
            <artifactId>kotlin-stdlib-jdk8</artifactId>
            <version>${kotlin.version}</version>
        </dependency>
        <dependency>
            <groupId>org.jetbrains.kotlin</groupId>
            <artifactId>kotlin-test</artifactId>
            <version>${kotlin.version}</version>
            <scope>test</scope>
        </dependency>



        <dependency>
            <groupId>com.github.ToastArgumentative</groupId>
            <artifactId>ToastRPG</artifactId>
            <version>v1.0.6-ALPHA-patch12</version>
            <scope>compile</scope>
        </dependency>

    </dependencies>
```
{% endcode %}

You also want to make sure that you are shading the library alongside your plugin.

```xml
<build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.5.1</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>

                            <relocations>
                                <relocation>
                                    <!-- Relocate ToastRPG classes -->
                                    <pattern>pine.toast.toastrpg</pattern>
                                    <shadedPattern>shaded.pine.toast.toastrpg</shadedPattern>
                                </relocation>
                            </relocations>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.jetbrains.kotlin</groupId>
                <artifactId>kotlin-maven-plugin</artifactId>
                <version>${kotlin.version}</version>
                <executions>
                    <execution>
                        <id>compile</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>compile</goal>
                        </goals>
                        <configuration>
                            <sourceDirs>
                                <source>src/main/java</source>
                                <source>target/generated-sources/annotations</source>
                            </sourceDirs>
                        </configuration>
                    </execution>
                    <execution>
                        <id>test-compile</id>
                        <phase>test-compile</phase>
                        <goals>
                            <goal>test-compile</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <jvmTarget>17</jvmTarget>
                </configuration>
            </plugin>
            <!-- Other plugins... -->
        </plugins>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
```

You have now setup your project and are ready to use the ToastRPG Library!
