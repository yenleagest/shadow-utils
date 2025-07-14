# shadow-utils

A simplified extraction of [Selenide's](https://selenide.org) internal logic for interacting with shadow DOM elements using deep CSS traversal.

---

## 🌟 Features

- ✅ Shadow DOM support with `::shadow` / `>>>` / `/deep/`-like behavior
- ✅ Custom locator logic for working across nested shadow roots
- ✅ Minimal dependency footprint (Selenide-based)
- ✅ Designed for integration testing in modern web apps

---

## 🧩 Why?

Modern frontend frameworks (like Lit, Stencil, or even raw Web Components) use **Shadow DOM**, which encapsulates elements and prevents access via regular CSS selectors.  
This utility helps you **pierce the shadow boundaries** cleanly in your Selenide tests.