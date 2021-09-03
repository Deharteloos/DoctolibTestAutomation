package steps;

import context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.DietitianPage;

import static org.testng.Assert.assertTrue;

public class DietitianPageSteps {

    /**
     * Page objects declaration
     */
    private ScenarioContext context;
    private DietitianPage dietitianPage;

    public DietitianPageSteps(ScenarioContext context, DietitianPage dietitianPage) {
        this.context = context;
        this.dietitianPage = dietitianPage;
    }

    /**
     * Steps definitions
     */
    @And("Unroll the filter on spoken languages")
    public void unrollTheFilterOnSpokenLanguages() {
        dietitianPage.clickOnSpokenLanguages();
    }

    @Then("The language {string} is not present")
    public void theLanguageIsNotPresent(String language) {
        assertTrue(dietitianPage.isLanguagePresent(language), "The language "+ language +" is not present in the list of spoken languages.");
    }
}
