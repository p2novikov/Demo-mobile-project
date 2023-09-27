package secretcloset.steps;

import io.qameta.allure.Step;
import secretcloset.models.DiscountedProduct;
import secretcloset.models.Seller;
import secretcloset.views.screens.DiscountedProductScreen;

import static org.testng.Assert.assertTrue;
import static secretcloset.constants.ErrorMessages.ITEM_ERROR_MSG;

public class DiscountedProductScreenSteps {

    @Step("Assert item {productName} is displayed")
    public static void assertItemIsDisplayed(String productName) {
        DiscountedProductScreen productScreen = new DiscountedProductScreen(productName);
        assertTrue(productScreen.isOpened(), ITEM_ERROR_MSG);
    }

    @Step("Get item {productName} info")
    public static DiscountedProduct getDiscountedItemInfo(String productName) {
        DiscountedProductScreen productScreen = new DiscountedProductScreen(productName);
        int price = productScreen.getPrice();
        int discount = productScreen.getDiscount();
        int disk_price = productScreen.getNewPrice();
        return new DiscountedProduct(productName, price, discount, disk_price);
    }

    @Step("Get seller info of product {productName}")
    public static Seller getSellerInfo(String productName) {
        DiscountedProductScreen productScreen = new DiscountedProductScreen(productName);
        return new Seller(productScreen.getSellerName(), productScreen.getSellerCity());
    }

    @Step("Click seller of product {productName}")
    public static void clickSeller(String productName) {
        DiscountedProductScreen productScreen = new DiscountedProductScreen(productName);
        productScreen.tapSeller();
    }
}
