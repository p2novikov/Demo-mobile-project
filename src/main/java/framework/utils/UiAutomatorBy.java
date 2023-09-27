package framework.utils;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public abstract class UiAutomatorBy extends AppiumBy {

    private static final String text = "new UiSelector().text(\"%s\")";

    protected UiAutomatorBy(String selector, String locatorString, String locatorName) {
        super(selector, locatorString, locatorName);
    }

    public static By text(final String t) {
        if (t == null) {
            throw new IllegalArgumentException("Example of exception");
        }

        return new ByAndroidUIAutomator(String.format(text, t));
    }
}
