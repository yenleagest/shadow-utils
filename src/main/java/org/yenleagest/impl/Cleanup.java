package org.yenleagest.impl;


import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriverException;

import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;
import static java.util.regex.Pattern.DOTALL;

public class Cleanup {
    private static final Pattern REGEX_FIRST_LINE = Pattern.compile("([^\\n]*)\\n.*", DOTALL);
    private static final Pattern REGEX_SELENIUM_WARNING = Pattern.compile("(.*)\\(WARNING: The server did not provide any stacktrace.*");
    private static final Pattern REGEX_SELENIUM_PACKAGE = Pattern.compile("org\\.openqa\\.selenium\\.(.*)");
    public static Cleanup of = new Cleanup();

    public String webdriverExceptionMessage(Throwable e) {
        return e instanceof WebDriverException ?
                requireNonNull(webdriverExceptionMessage("%s: %s".formatted(e.getClass().getSimpleName(), e.getMessage()))) :
                e.toString();
    }

    @Nullable
    public String webdriverExceptionMessage(@Nullable String webDriverExceptionInfo) {
        if (webDriverExceptionInfo == null) return null;

        return cleanupSeleniumPackage(cleanupSeleniumWarning(extractFirstLine(webDriverExceptionInfo))).trim();
    }

    private String extractFirstLine(String text) {
        return REGEX_FIRST_LINE.matcher(text).replaceFirst("$1");
    }

    private String cleanupSeleniumWarning(String firstLine) {
        return REGEX_SELENIUM_WARNING.matcher(firstLine).replaceFirst("$1");
    }

    private String cleanupSeleniumPackage(String withoutSeleniumBloat) {
        return REGEX_SELENIUM_PACKAGE.matcher(withoutSeleniumBloat).replaceFirst("$1");
    }
}

