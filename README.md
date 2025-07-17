# shadow-utils

A simplified extraction of [Selenide's](https://selenide.org) internal logic for interacting with nested shadow DOM elements using deep CSS traversal.

---

## 🌟 Features

- ✅ Support nested Shadow DOM
- ✅ Minimal dependency footprint (Selenide-based)
- ✅ Designed for integration testing in modern web apps

---

## 🧩 Why?

Modern frontend frameworks (like Lit, Stencil, or raw Web Components) use Shadow DOM, which encapsulates elements and blocks access via regular CSS selectors.
This utility helps you pierce shadow boundaries cleanly in your Selenide tests.

---

## 🏗️ Installation

1.	Add to your pom.xml:
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
<dependency>
    <groupId>com.github.yenleagest</groupId>
    <artifactId>shadow-utils</artifactId>
    <version>v1.0.1-alpha</version>
</dependency>
```
2.	Usage example:
```
import org.yenleagest.ShadowUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

WebElement element = driver.findElement(ShadowUtils.byShadowCss(By.id("id")));
```
## ⛑️ Note:
By default, a JAR file is built on JitPack when a new tag is created.
If Maven can’t find the artifact when building your project, trigger the build manually:
1. Visit https://jitpack.io/#yenleagest/shadow-utils
2. Click the Get It button to start the build process
