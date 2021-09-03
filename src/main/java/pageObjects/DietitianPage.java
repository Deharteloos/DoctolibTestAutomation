package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DietitianPage extends Page {

    private static final Logger LOG = LogManager.getLogger(DietitianPage.class);

    /**
     * Find By
     */
    @FindBy(css = ".dropdown-toggle.language_dropdown")
    private WebElement languageDropdown;

    @FindBy(css = "div.dropdown-toggle.language_dropdown ~ ul.dropdown-menu > li > a," +
            " div.dropdown-toggle.language_dropdown ~ ul.dropdown-menu > li > span")
    private List<WebElement> spokenLanguages;

    public void clickOnSpokenLanguages() {
        clickOn(languageDropdown);
    }

    public boolean isLanguagePresent(String language) {
        saveScreenShotPNG();
        List<String> languages = new ArrayList<>();
        for(WebElement webElt : spokenLanguages)
            languages.add(webElt.getText());
        return Collections.frequency(languages, language) > 0;
    }

}
