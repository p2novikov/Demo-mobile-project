package framework.elements;

import framework.services.Helpers;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseElement extends Helpers {

    protected String name;
    protected By locator;
    protected WebElement element;

    protected BaseElement(By loc) {
        locator = loc;
        logger.warn("Please name element");
    }

    protected BaseElement(By loc, String nameOf) {
        locator = loc;
        name = nameOf;
    }

    protected BaseElement(String xpath, String nameOf) {
        locator = AppiumBy.xpath(xpath);
        name = nameOf;
    }

    public BaseElement(String xpath) {
        locator = AppiumBy.xpath(xpath);
        logger.warn("Please name element");
    }

    public String getText() {
        return getElement().getText();
    }

    public String getName() {
        return name;
    }

    public By getLocator() {
        return locator;
    }

    public WebElement getElement() {
        return element(locator);
    }

    public Dimension getSize() {
        return getElement().getSize();
    }

    public Point getLocation() {
        return getElement().getLocation();
    }

    public Map<String, Integer> getCoordinates() {
        Map<String, Integer> coordinates = new HashMap<>(4);
        coordinates.put("x1", getLocation().getX());
        coordinates.put("y1", getLocation().getY());
        coordinates.put("x2", getLocation().getX() + getSize().getWidth() - 1);
        coordinates.put("y2", getLocation().getY() + getSize().getHeight() - 1);
        return coordinates;
    }

    public String getValue() {
        return getElement().getAttribute("value");
    }


    public void sendKeys(String text) {
        logger.info(text);
        getElement().clear();
        getElement().sendKeys(text);
    }

    public void tap() {
        logger.info(name);
        getElement().click();
    }

    public boolean isPresent() {
        try {
            waitForElementExists();
            try {
                return getDriver().findElement(locator).isDisplayed();
            } catch (Exception e) {
                logger.warn(e.getMessage());
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled() {
        return getElement().isEnabled();
    }

    private static WebElement w(WebElement element) {
        return (WebElement) element;
    }

    private static List<WebElement> w(List<WebElement> elements) {
        List<WebElement> list = new ArrayList<WebElement>(elements.size());
        for (WebElement element : elements) {
            list.add(w(element));
        }
        return list;
    }

    public WebElement element(By locator) {
        try {
            return w(getDriver().findElement(locator));
        } catch (NoSuchElementException ex) {
            logger.fatal(String.format("Locator is absent: %s", ex));
            return null;
        }
    }

    public static List<WebElement> elements(By locator) {
        return w(getDriver().findElements(locator));
    }

    public static List<WebElement> tags(String tagName) {
        return elements(By.className(tagName));
    }

    public static WebElement wait(By locator) {
        return w(driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    public void waitForElementIsVisible() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementExists() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForNotExist() {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
