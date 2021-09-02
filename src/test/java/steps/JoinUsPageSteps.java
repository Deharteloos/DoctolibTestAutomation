package steps;

import context.ScenarioContext;
import enums.Context;
import io.cucumber.java.en.And;
import pageObjects.AboutPage;
import pageObjects.HomePage;
import pageObjects.JoinUsPage;

public class JoinUsPageSteps {

    /**
     * Page objects
     */
    private JoinUsPage joinUsPage;
    private ScenarioContext context;

    public JoinUsPageSteps(ScenarioContext context, JoinUsPage joinUsPage) {
        this.context = context;
        this.joinUsPage = joinUsPage;
    }

    /**
     * Steps definitions
     */
    @And("Click on the link {string}")
    public void clickOnTheLink(String linkText) {
        context.set(Context.URL, joinUsPage.goToPage(linkText));
    }
}
