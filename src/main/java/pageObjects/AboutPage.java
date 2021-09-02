package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutPage extends Page {

    private static final Logger LOG = LogManager.getLogger(AboutPage.class);

    /**
     * Find By
     */
    // @FindBy(linkText = "Rejoignez notre équipe")
    // private WebElement joinOurTeamLink;

    public void goToPage(String linkText) {
        clickOn(driver.findElement(By.linkText(linkText)));
    }

}
