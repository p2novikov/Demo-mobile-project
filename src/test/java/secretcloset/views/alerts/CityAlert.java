package secretcloset.views.alerts;

import framework.elements.BasePage;
import framework.elements.Button;
import framework.utils.UiAutomatorBy;

public class CityAlert extends BasePage {

    private Button btnNotShowAgain = new Button(UiAutomatorBy.id("button2"), "Not show again button");

    public CityAlert() {
        super(UiAutomatorBy.id("alertTitle"), "City Alert");
    }

    public void tapNotShowAgain() {
        btnNotShowAgain.tap();
    }

}
