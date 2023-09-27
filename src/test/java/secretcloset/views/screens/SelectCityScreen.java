package secretcloset.views.screens;

import framework.elements.BasePage;
import framework.elements.Label;
import framework.elements.SearchBar;
import framework.utils.UiAutomatorBy;

public class SelectCityScreen extends BasePage {

    private SearchBar searchBar = new SearchBar(UiAutomatorBy.id("etSearchTest"), "City Search Bar");
    private Label lblCityItem = new Label(UiAutomatorBy.id("tvCityItemName"), "City Item Label");

    public SelectCityScreen() {
        super(UiAutomatorBy.text("Select city"), "Select City Screen");
    }

    public void typeCity(String cityName) {
        searchBar.sendKeys(cityName);
    }

    public void clickCityItem() {
        lblCityItem.tap();
    }
}
