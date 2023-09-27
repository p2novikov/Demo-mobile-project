package framework.services;


import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class AppiumServer extends Helpers {

    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder = new AppiumServiceBuilder();
    private int appiumPort = Integer.parseInt(appiumProperties.getProperty("appiumPort"));
    private String host = appiumProperties.getProperty("appiumHost");


    public void startServer() {
        builder.withAppiumJS(new File(appiumProperties.getProperty("appiumJsPath")).getAbsoluteFile());
        builder.withIPAddress(host);
        builder.usingPort(appiumPort);
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public void stopServer() {
        if (service != null) {
            service.stop();
        }
    }

    public boolean checkIfServerIsRunnning() {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(appiumPort);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
}