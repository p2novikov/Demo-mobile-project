package secretcloset.views.screens;

import framework.elements.BasePage;
import framework.elements.Label;
import framework.utils.Regex;
import framework.utils.UiAutomatorBy;

import static secretcloset.constants.RegularExpressions.NUMERIC_REG_EXPRESSION;

public class DiscountedProductScreen extends BasePage {

    private Label lblPrice = new Label(UiAutomatorBy.id("tvItemOriginalPrice"), "Price Label");
    private Label lblDiscount = new Label(UiAutomatorBy.id("tvItemDiscount"), "Discount Label");
    private Label lblNewPrice = new Label(UiAutomatorBy.id("tvItemPrice"), "New Price Label");
    private Label lblSeller = new Label(UiAutomatorBy.id("rlSellerInfo"), "Seller Label");
    private Label lblSellerName = new Label(UiAutomatorBy.id("tvItemSellerName"), "Seller Name Label");
    private Label lblSellerCity = new Label(UiAutomatorBy.id("tvItemSellerCity"), "Seller City Label");

    public DiscountedProductScreen(String productName) {
        super(UiAutomatorBy.text(productName), String.format("%s %s", productName, "Product Screen"));
    }

    public int getPrice() {
        return Integer.parseInt(Regex.getMatchInStr(NUMERIC_REG_EXPRESSION, lblPrice.getText()));
    }

    public int getDiscount() {
        return Integer.parseInt(Regex.getMatchInStr(NUMERIC_REG_EXPRESSION, lblDiscount.getText()));
    }

    public int getNewPrice() {
        return Integer.parseInt(Regex.getMatchInStr(NUMERIC_REG_EXPRESSION, lblNewPrice.getText()));
    }

    public String getSellerName() {
        return lblSellerName.getText();
    }

    public String getSellerCity() {
        return lblSellerCity.getText();
    }

    public void tapSeller() {
        lblSeller.tap();
    }
}
