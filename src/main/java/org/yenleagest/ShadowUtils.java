package org.yenleagest;


import org.openqa.selenium.By;
import org.yenleagest.selector.ByDeepShadowCss;

public class ShadowUtils {
    /**
     * @see ByDeepShadowCss#cssSelector(java.lang.String)
     * @since v6.8.0
     */
    public static By byShadowCss(String target) {
        return new ByDeepShadowCss(target);
    }
}

