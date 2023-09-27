package framework.elements;

import framework.services.Helpers;
import org.openqa.selenium.By;

public abstract class BasePage extends Helpers {

    protected By titleLocator;
    protected String title;
    protected String pageName;

    public By getTitleLocator() {
        return titleLocator;
    }

    public String getTitle() {
        return title;
    }

    public String getPageName() {
        return pageName;
    }

    public BasePage(By loc, String titleOf) {
        titleLocator = loc;
        title = titleOf;
    }

    public boolean isOpened() {
        try {
            BaseElement.wait(titleLocator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
