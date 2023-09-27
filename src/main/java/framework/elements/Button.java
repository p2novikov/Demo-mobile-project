package framework.elements;

import org.openqa.selenium.By;

public class Button extends BaseElement {

    public Button(By loc) {
        super(loc);
    }

    public Button(By loc, String nameOf) {
        super(loc, nameOf);
    }

    public Button(String xpath, String nameOf) {
        super(xpath, nameOf);
    }

    public Button(String xpath) {
        super(xpath);
    }

    public String getButtonText() {
        return getElement().getAttribute("name");
    }
}
