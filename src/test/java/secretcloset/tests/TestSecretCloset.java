package secretcloset.tests;

import framework.services.AppiumTest;
import org.testng.Assert;
import secretcloset.models.DiscountedProduct;
import secretcloset.models.Seller;
import secretcloset.steps.DiscountedProductScreenSteps;
import secretcloset.steps.MainScreenSteps;
import secretcloset.steps.SelectCityScreenSteps;
import secretcloset.steps.SellerScreenSteps;

import static secretcloset.constants.ErrorMessages.PRODUCTS_ERROR_MSG;
import static secretcloset.constants.ErrorMessages.SELLERS_ERROR_MSG;

public class TestSecretCloset extends AppiumTest {

    private String city = "Paris";
    private int itemNumber = 2;

    public void runTest() {
        logStep("Click on city label");
        MainScreenSteps.clickCityLbl();

        logStep(String.format("Type %s in search field", city));
        SelectCityScreenSteps.typeCity(city);

        logStep(String.format("Click on search result %s", city));
        SelectCityScreenSteps.clickCitySearchResult();

        logStep(String.format("Check %s is selected as region", city));
        MainScreenSteps.assertCityIsEqualToExpected(city);

        logStep(String.format("Select %d item with discount", itemNumber));
        DiscountedProduct discProductFromMainScreen = MainScreenSteps.getDiscountedItemInfo(itemNumber);
        MainScreenSteps.clickDiscountedItem(itemNumber);

        logStep("Check that selected item is displaying");
        DiscountedProductScreenSteps.assertItemIsDisplayed(discProductFromMainScreen.getName());
        DiscountedProduct discProductFromProdScreen = DiscountedProductScreenSteps.getDiscountedItemInfo(discProductFromMainScreen.getName());

        logStep("Check that selected item info is correct");
        Assert.assertEquals(discProductFromProdScreen, discProductFromMainScreen, PRODUCTS_ERROR_MSG);

        logStep("Click on seller");
        Seller sellerFromProductScreen = DiscountedProductScreenSteps.getSellerInfo(discProductFromMainScreen.getName());
        DiscountedProductScreenSteps.clickSeller(discProductFromMainScreen.getName());
        Seller sellerFromSellerScr = SellerScreenSteps.getSellerInfo(sellerFromProductScreen.getName());

        logStep("Check that seller info is correct");
        Assert.assertEquals(sellerFromSellerScr, sellerFromProductScreen, SELLERS_ERROR_MSG);
    }
}
