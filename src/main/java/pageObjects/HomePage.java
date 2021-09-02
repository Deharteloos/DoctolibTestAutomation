package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    private static final Logger LOG = LogManager.getLogger(HomePage.class);

    /**
     * Find BY
     */
    @FindBy(linkText = "À propos de nous")
    private WebElement aboutUsLink;


    public void navigateToEnv() {
        get(config.getEnvironment());
    }

    public void goToAboutus() {
        scrollToElement(aboutUsLink);
        clickOn(aboutUsLink);
    }

}
