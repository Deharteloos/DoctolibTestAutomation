package steps;

import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pageObjects.HomePage;

public class HomePageSteps {

    /**
     * Page objects
     */
    private HomePage homePage;
    private ScenarioContext context;

    public HomePageSteps(ScenarioContext context, HomePage homePage) {
        this.context = context;
        this.homePage = homePage;
    }

    /**
     * Static selectors
     */
    private static final By cookiesDialogBoxId = By.id("didomi-notice");
    private static final By cookiesAcceptBtnId = By.id("didomi-notice-agree-button");

    /**
     * Steps definitions
     */
    @Given("Go to the home page")
    public void goToTheHomePage() {
        homePage.navigateToEnv();
        homePage.acceptsCookies(cookiesDialogBoxId, cookiesAcceptBtnId);
    }

    @When("Click on about us in the footer")
    public void clickOnAboutUsInTheFooter() {
        homePage.goToAboutus();
    }

}
