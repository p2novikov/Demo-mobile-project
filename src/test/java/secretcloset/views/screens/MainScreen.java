package secretcloset.views.screens;

import framework.elements.BasePage;
import framework.elements.Label;
import framework.utils.UiAutomatorBy;
import secretcloset.views.forms.DiscountedProductForm;

import static secretcloset.views.constants.Locators.*;

public class MainScreen extends BasePage {
    private String productLoc = RELATIVE_LAYOUT;
    private String productNameLoc = productLoc + TEXT_VIEW + String.format(CONTAINS_RESOURCE_ID, "tvBrand");
    private Label lblCity = new Label(UiAutomatorBy.id("tvToolbarCity"), "City Label ");

    public MainScreen() {
        super(UiAutomatorBy.text("Search brands"), "Main Screen");

    }

    private Label getLblDefiniteCity(String city) {
        return new Label(UiAutomatorBy.text(city), String.format("%s %s", city, "City Label"));
    }

    public DiscountedProductForm getDiscountedProductForm(int index) {
        return new DiscountedProductForm(index);
    }

    public void clickCityLbl() {
        lblCity.tap();
    }

    public boolean isCityLblPresent(String city) {
        return getLblDefiniteCity(city).isPresent();
    }

    public void tapProduct(int index) {
        Label lblName = new Label(UiAutomatorBy.xpath(String.format(productNameLoc, index)), "Product Name Label");
        lblName.tap();
    }
}

