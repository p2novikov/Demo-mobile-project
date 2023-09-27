package framework.services;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public abstract class AppiumTest extends Helpers {

    public abstract void runTest();

    @Test
    public void xTest() throws Throwable {
        Class<? extends AppiumTest> currentClass = this.getClass();
        try {
            runTest();
        } catch (Throwable e) {
            logger.warn("");
            throw e;
        }
        makeScreen(currentClass);
    }

    @BeforeClass
    public void before() {
        appiumServer = new AppiumServer();
        if (!appiumServer.checkIfServerIsRunnning()) {
            appiumServer.startServer();
        } else {
            logger.info("Appium Server already running");
        }

        try {
            setDriver(new AndroidDriver(new URL(appiumProperties.getProperty("driverURL", "127.0.0.1:4723/wd/hub")),
                    makeCapabilities()));
        } catch (MalformedURLException e) {
            logger.error(String.format("Unable to set driver (%s)", e.getMessage()));
            e.printStackTrace();
        }
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Long.parseLong(appiumProperties.getProperty("appiumImplicityTimeout", "5"))));
        Helpers.init(getDriver());
    }

    @AfterClass
    public void after() {
        if (getDriver() != null) {
            try {
                getDriver().close();
            } catch (Exception e) {
                logger.error(String.format("Unable to close app (%s)", e.getMessage()));
                e.printStackTrace();
            }
            getDriver().quit();
        }
        if (appiumServer.checkIfServerIsRunnning()) {
            appiumServer.stopServer();
        } else {
            logger.info("Appium Server already stopped");
        }
        ProxyHandler.clearProxy();
    }
}
