package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PractitionerPage extends Page {

    private static final Logger LOG = LogManager.getLogger(PractitionerPage.class);

    /**
     * Find By
     */
    @FindBy(xpath = "/html/body/div[5]/div/div[144]/div[2]")
    private WebElement practitionerAddress;

    private String badCharacter = "�";

    public void scrollToPractitioner(String practitioner) {
        scrollToElement(driver.findElement(By.linkText(practitioner)));
    }

    public boolean isCorrectEncoding() {
        saveScreenShotPNG();
        return !practitionerAddress.getText().contains(badCharacter);
    }

}
