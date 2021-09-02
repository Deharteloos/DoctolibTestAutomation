package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class JobsPage extends Page {

    private static final Logger LOG = LogManager.getLogger(JobsPage.class);

    /**
     * Find By
     */
    @FindBy(id = "country-button")
    private WebElement countryButton;

    @FindBy(id = "country-menu")
    private WebElement countryMenu;

    @FindBy(css = "ul#country-menu > li.ui-menu-item > div")
    private List<WebElement> countryMenuItems;

    @FindBy(id = "location-button")
    private WebElement locationButton;

    @FindBy(id = "location-menu")
    private WebElement locationMenu;

    @FindBy(css = "ul#location-menu > li.ui-menu-item > div")
    private List<WebElement> locationMenuItems;

    @FindBy(css = ".jobs-board__section")
    private List<WebElement> jobsBoardSection;

    @FindBy(css = ".jobs-board-container p")
    private WebElement noOfferMessage;

    @FindBy(css = ".flags_language_selector")
    private WebElement languageSelector;

    @FindBy(css = ".flags_language_selector .active")
    private WebElement activeLanguage;

    @FindBy(linkText = "FR")
    private WebElement linkToFrenchVersion;

    @FindBy(tagName = "footer")
    private WebElement footer;

    /**
     * Static selectors
     */
    private static final String linkToFrenchVersionText = "FR";
    private static final String activeLanguageCssSelector = ".flags_language_selector .active";

    public void changeLanguageToFrench() {
        waitForLoadingPage();
        if(!activeLanguage.getText().equals("FR")) {
            action.moveToElement(languageSelector).perform();
            clickOn(linkToFrenchVersion);
            waitForLoadingPage();
        }
    }

    public void setCountryFilter(String country) {
        clickOn(countryButton);
        if(waitUntil(visibilityOf(countryMenu))) {
            for(WebElement webElt : countryMenuItems) {
                if(webElt.getText().equals(country)) {
                    clickOn(webElt);
                    break;
                }
            }
        } else {
            LOG.warn("Country could not be set");
        }
    }

    public void setCityFilter(String city) {
        clickOn(locationButton);
        if(waitUntil(visibilityOf(locationMenu))) {
            for(WebElement webElt : locationMenuItems) {
                if(webElt.getText().equals(city)) {
                    clickOn(webElt);
                    break;
                }
            }
            waitUntil(visibilityOf(noOfferMessage));
        } else {
            LOG.warn("City could not be set");
        }
    }

    public boolean thereAreResults() {
        saveScreenShotPNG();
        return jobsBoardSection.size() != 0;
    }

    public void goDownToFooter() {
        scrollToElement(footer);
    }

    public void dropdownCities() {
        clickOn(locationButton);
    }

    public boolean isCityDuplicated(String city) {
        saveScreenShotPNG();
        List<String> cities = new ArrayList<>();
        for(WebElement webElt : locationMenuItems)
            cities.add(webElt.getText());
        return Collections.frequency(cities, city) > 1;
    }

    public boolean isNotErrorPage(String url) {
        return verifyLink(url);
    }

}
