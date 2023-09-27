package secretcloset.views.forms;

import framework.elements.BasePage;
import framework.elements.Label;
import framework.utils.Regex;
import framework.utils.UiAutomatorBy;

import static secretcloset.constants.RegularExpressions.NUMERIC_REG_EXPRESSION;
import static secretcloset.views.constants.Locators.*;

public class DiscountedProductForm extends BasePage {

    private String productLoc;
    private String productItemLoc;
    private String discountLoc;
    private String discountedPriceLoc;
    private String priceLoc;
    private String nameLoc;

    public DiscountedProductForm(int formIndex) {
        super(UiAutomatorBy.xpath(String.format(RELATIVE_LAYOUT,
                formIndex)), String.format("%s %d", "Discounted Product Form", formIndex));
        productLoc = String.format(RELATIVE_LAYOUT, formIndex);
        productItemLoc = productLoc + TEXT_VIEW + CONTAINS_RESOURCE_ID;
        discountLoc = String.format(productItemLoc, "tvDiscount");
        discountedPriceLoc = String.format(productItemLoc, "tvPrice");
        priceLoc = String.format(productItemLoc, "tvSumm");
        nameLoc = String.format(productItemLoc, "tvBrand");
    }

    private Label getLblPrice() {
        return new Label(UiAutomatorBy.xpath(priceLoc), "Price Label");
    }

    private Label getLblName() {
        return new Label(UiAutomatorBy.xpath(nameLoc), "Name Label");
    }

    private Label getLblDiscount() {
        return new Label(UiAutomatorBy.xpath(discountLoc), "Discount Label");
    }

    private Label getLblDiscountedPrice() {
        return new Label(UiAutomatorBy.xpath(discountedPriceLoc), "Discounted Price Label");
    }

    public int getPrice() {
        return Integer.parseInt(Regex.getMatchInStr(NUMERIC_REG_EXPRESSION, getLblPrice().getText()));
    }

    public String getName() {
        return getLblName().getText();
    }

    public int getDiscount() {
        return Integer.parseInt(Regex.getMatchInStr(NUMERIC_REG_EXPRESSION, getLblDiscount().getText()));
    }

    public int getDiscountedPrice() {
        return Integer.parseInt(Regex.getMatchInStr(NUMERIC_REG_EXPRESSION, getLblDiscountedPrice().getText()));
    }
}
