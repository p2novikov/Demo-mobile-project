package framework.services;

import framework.utils.ProjectException;
import framework.utils.Logger;
import framework.utils.PropertiesResourceManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public abstract class Helpers {

    private static AndroidDriver driver;
    protected static AppiumServer appiumServer;
    protected static WebDriverWait driverWait;
    protected static Logger logger = Logger.getInstance();
    public static PropertiesResourceManager appiumProperties = new PropertiesResourceManager("appium.properties");
    public static int DEVICE_WIDTH;
    public static int DEVICE_HEIGHT;
    protected int stepNumber = 1;

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void setDriver(AndroidDriver driver) {
        Helpers.driver = driver;
    }

    public static void init(AndroidDriver webDriver) {
        driver = webDriver;
        int timeoutInSeconds = Integer.parseInt(appiumProperties.getProperty("appiumTimeout", "5"));
        driverWait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
        File screenshot = getDriver().getScreenshotAs(OutputType.FILE);
        try {
            DEVICE_WIDTH = javax.imageio.ImageIO.read(screenshot).getWidth();
            DEVICE_HEIGHT = javax.imageio.ImageIO.read(screenshot).getHeight();
        } catch (IOException e) {
            throw new ProjectException("Can't get screen resolution\n" + e.getMessage());
        }
    }

    public static DesiredCapabilities makeCapabilities() {
        try {
            var capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, appiumProperties.getProperty("platformVersion"));
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, appiumProperties.getProperty("deviceName"));
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            capabilities.setCapability(MobileCapabilityType.UDID, appiumProperties.getProperty("udid"));
            capabilities.setCapability(MobileCapabilityType.APP, appiumProperties.getProperty("appFileName"));

            return capabilities;
        } catch (Exception e) {
            throw new ProjectException(String.format("Exception: %s", e));// Just example
        }
    }

    public static void logStep(final int step) {
        logger.step(step);
    }

    protected void logStep(final String info) {
        logStep(stepNumber++);
        logger.info(String.format("----==[ %1$s ]==----", info));
    }

    protected String makeScreen(final Class<? extends Helpers> name) {
        String fileName = name.getPackage().getName() + "." + name.getSimpleName();
        try {
            File screen = getDriver().getScreenshotAs(OutputType.FILE);
            File addedNewFile = new File(String.format("surefire-reports%2$shtml%2$sScreenshots/%1$s.png", fileName, File.separator));
            FileUtils.copyFile(screen, addedNewFile);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        return new File(String.format(
                "surefire-reports%2$shtml%2$sScreenshots/%1$s.png", fileName, File.separator)).getAbsolutePath();
    }

}
