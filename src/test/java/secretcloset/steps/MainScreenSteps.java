package secretcloset.steps;

import io.qameta.allure.Step;
import secretcloset.models.DiscountedProduct;
import secretcloset.views.alerts.CityAlert;
import secretcloset.views.screens.MainScreen;

import static org.testng.Assert.assertTrue;
import static secretcloset.constants.ErrorMessages.CITY_ERROR_MSG;

public class MainScreenSteps {

    @Step("Click city label")
    public static void clickCityLbl() {
        MainScreen mainScreen = new MainScreen();
        mainScreen.clickCityLbl();
        CityAlert warningForm = new CityAlert();
        warningForm.tapNotShowAgain();
    }

    @Step("Assert city is equal to {expectedCity}")
    public static void assertCityIsEqualToExpected(String expectedCity) {
        MainScreen mainScreen = new MainScreen();
        assertTrue(mainScreen.isCityLblPresent(expectedCity), CITY_ERROR_MSG);
    }

    @Step("Get discounted item info of form {itemNumber}")
    public static DiscountedProduct getDiscountedItemInfo(int itemNumber) {
        MainScreen mainScreen = new MainScreen();
        String name = mainScreen.getDiscountedProductForm(itemNumber).getName();
        int price = mainScreen.getDiscountedProductForm(itemNumber).getPrice();
        int discount = mainScreen.getDiscountedProductForm(itemNumber).getDiscount();
        int disk_price = mainScreen.getDiscountedProductForm(itemNumber).getDiscountedPrice();
        return new DiscountedProduct(name, price, discount, disk_price);
    }

    @Step("Click discounted item of form {itemNumber}")
    public static void clickDiscountedItem(int itemNumber) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.tapProduct(itemNumber);
    }

}
