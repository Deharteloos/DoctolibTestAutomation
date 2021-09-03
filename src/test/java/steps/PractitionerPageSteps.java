package steps;

import context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.PractitionerPage;

import static org.testng.Assert.assertTrue;

public class PractitionerPageSteps {

    /**
     * Page objects declaration
     */
    private ScenarioContext context;
    private PractitionerPage practitionerPage;

    public PractitionerPageSteps(ScenarioContext context, PractitionerPage practitionerPage) {
        this.context = context;
        this.practitionerPage = practitionerPage;
    }

    /**
     * Steps definitions
     */
    @And("Scroll to the {string}")
    public void scrollToThe(String practitioner) {
        practitionerPage.scrollToPractitioner(practitioner);
    }


    @Then("We see characters that are replaced by question marks in the address")
    public void weSeeCharactersThatAreReplacedByQuestionMarksInTheAddress() {
        assertTrue(practitionerPage.isCorrectEncoding(), "There is problem on characters encoding.");
    }
}
