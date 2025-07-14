package org.yenleagest.impl;


import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WrapsDriver;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Matcher.quoteReplacement;
import static java.util.regex.Pattern.DOTALL;
import static org.yenleagest.impl.Lazy.lazyEvaluated;

public class JavaScript {
    private static final Pattern RE = Pattern.compile("import '(.+?\\.js)'", DOTALL);
    private final FileContent jsSource;
    private final Lazy<String> content = lazyEvaluated(this::readContent);

    public JavaScript(String jsFileName) {
        jsSource = new FileContent(jsFileName);
    }

    public static JavascriptExecutor jsExecutor(SearchContext context) {
        return asJsExecutor(context)
                .orElseThrow(() -> new IllegalArgumentException("Context is not JS-aware: %s".formatted(context)));
    }

    public static Optional<JavascriptExecutor> asJsExecutor(SearchContext context) {
        SearchContext unwrappedContext = context instanceof WrapsDriver wrapsDriver ?
                wrapsDriver.getWrappedDriver() : context;

        return unwrappedContext instanceof JavascriptExecutor jsExecutor ?
                Optional.of(jsExecutor) :
                Optional.empty();
    }

    String content() {
        return content.get();
    }

    private String readContent() {
        String js = jsSource.content();
        Matcher matcher = RE.matcher(js);
        while (matcher.find()) {
            String fileName = matcher.group(1);
            String includedScript = new FileContent(fileName).content();
            js = matcher.replaceFirst(quoteReplacement(includedScript));
            matcher = RE.matcher(js);
        }
        return js;
    }

    @Nullable
    @CanIgnoreReturnValue
    @SuppressWarnings("unchecked")
    public <T> T execute(SearchContext context, @Nullable Object... arguments) {
        return (T) jsExecutor(context).executeScript("return %s".formatted(content()), arguments);
    }
}

