package org.yenleagest.impl;


import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;

import static java.lang.Thread.currentThread;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;
import static org.yenleagest.impl.Lazy.lazyEvaluated;

/**
 * Read file content from classpath
 * <p>
 * The point is in lazy loading: the content is loaded only on the first usage, and only once.
 */
public class FileContent {
    private final String filePath;
    private final Lazy<String> content = lazyEvaluated(this::readContent);

    public FileContent(String filePath) {
        this.filePath = filePath;
    }

    public String content() {
        return content.get();
    }

    private String readContent() {
        try {
            URL url = requireNonNull(currentThread().getContextClassLoader().getResource(filePath));
            return IOUtils.toString(url, UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot load %s from classpath".formatted(filePath), e);
        }
    }
}
