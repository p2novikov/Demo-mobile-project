package secretcloset.views.screens;

import framework.elements.BasePage;
import framework.elements.Label;
import framework.utils.UiAutomatorBy;

public class SellerScreen extends BasePage {

    private Label lblCity = new Label(UiAutomatorBy.id("tvItemSellerCity"), "Seller City Label");
    private Label lblName;

    public SellerScreen(String sellerName) {
        super(UiAutomatorBy.text(sellerName), "Seller Screen");
        lblName = new Label(UiAutomatorBy.text(sellerName), "Seller Name Label");
    }

    public String getSellerCity() {
        return lblCity.getText();
    }

    public String getSellerName() {
        return lblName.getText();
    }
}
