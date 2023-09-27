package secretcloset.steps;

import io.qameta.allure.Step;
import secretcloset.models.Seller;
import secretcloset.views.screens.SellerScreen;

public class SellerScreenSteps {

    @Step("Get seller {sellerName} info")
    public static Seller getSellerInfo(String sellerName) {
        SellerScreen sellerScreen = new SellerScreen(sellerName);
        return new Seller(sellerScreen.getSellerName(), sellerScreen.getSellerCity());
    }
}
