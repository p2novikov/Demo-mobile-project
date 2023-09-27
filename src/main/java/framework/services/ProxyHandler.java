package framework.services;

import framework.utils.ProjectException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class ProxyHandler extends Helpers {

    private static final int PROXY_SERVER_PORT = 9191;
    private static final int PROXY_PORT = 9292;
    private static final String PROXY_URL = "http://%s:%d/proxy/%d";
    private static final String PROXY_HOST = System.getProperty("PROXY_HOST", "localhost");
    private static boolean proxy = false;

    private static String getProxyUrl() {
        return String.format(PROXY_URL, PROXY_HOST, PROXY_SERVER_PORT, PROXY_PORT);
    }

    private static String getProxyServUrl() {
        return String.format("http://%s:%d/proxy", PROXY_HOST, PROXY_SERVER_PORT);
    }

    public static void initProxy() throws IOException {
        Request.Post(getProxyServUrl())
                .bodyForm(Form.form().add("port", String.valueOf(PROXY_PORT)).build())
                .execute();
        proxy = true;
    }

    public static void clearProxy() {
        if (proxy) {
            try {
                Request.Delete(getProxyUrl()).execute();
            } catch (IOException e) {
                throw new ProjectException("Clear Proxy Exception\n" + e.getMessage());
            }
        }
    }
}
