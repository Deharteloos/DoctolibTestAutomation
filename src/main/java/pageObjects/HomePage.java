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

    @FindBy(css = "body > div.js-navbar > nav > div > div:nth-child(2) > a:nth-child(3)")
    private WebElement connectBtn;

    public void navigateToEnv() {
        get(config.getEnvironment());
    }

    public void goToAboutus() {
        scrollToElement(aboutUsLink);
        clickOn(aboutUsLink);
    }

    public void goToPractitionerStartingWith(String letter) {
        clickOn(driver.findElement(By.linkText(letter)));
    }

    public void goToConnectionPage() {
        clickOn(connectBtn);
        waitForLoadingPage();
    }

}
