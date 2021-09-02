package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class JoinUsPage extends Page {

    private static final Logger LOG = LogManager.getLogger(JoinUsPage.class);

    /**
     * Find By
     */
    @FindBy(linkText = "Trouvez votre prochain emploi")
    private WebElement findJobLink;



    public String goToPage(String linkText) {
        String url =driver.findElement(By.linkText(linkText)).getAttribute("href");
        get(url);
        //clickOn(driver.findElement(By.linkText(linkText)));
//        if(linkText.equals("Trouvez votre prochain emploi")) {
//            ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
//            driver.switchTo().window(tabs.get(0));
//            driver.close();
//            driver.switchTo().window(tabs.get(1));
//        }
        return url;
    }

}
