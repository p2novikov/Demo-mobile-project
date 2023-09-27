package secretcloset.steps;

import io.qameta.allure.Step;
import secretcloset.views.screens.SelectCityScreen;

public class SelectCityScreenSteps {

    @Step("Type {city}")
    public static void typeCity(String city) {
        SelectCityScreen citiesPage = new SelectCityScreen();
        citiesPage.typeCity(city);
    }

    @Step("Click city search result")
    public static void clickCitySearchResult() {
        SelectCityScreen citiesPage = new SelectCityScreen();
        citiesPage.clickCityItem();
    }
}