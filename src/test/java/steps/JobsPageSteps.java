package steps;

import context.ScenarioContext;
import enums.Context;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.JobsPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class JobsPageSteps {

    /**
     * Page objects declarations
     */
    private JobsPage jobsPage;
    private ScenarioContext context;

    public JobsPageSteps(ScenarioContext context, JobsPage jobsPage) {
        this.context = context;
        this.jobsPage = jobsPage;
    }

    /**
     * Steps definitions
     */
    @When("Apply {string} on the country filter")
    public void applyOnTheCountryFilter(String country) {
        jobsPage.setCountryFilter(country);
    }

    @And("Apply {string} on the city filter")
    public void applyOnTheCityFilter(String city) {
        jobsPage.setCityFilter(city);
    }

    @Then("No results should be shown")
    public void noResultsShouldBeShown() {
        assertFalse(jobsPage.thereAreResults(), "There are results found for the given parameters");
    }

    @When("Scroll to the footer")
    public void scrollToTheFooter() {
        jobsPage.goDownToFooter();
    }

    @Then("An {int} error page should not be returned")
    public void anErrorPageShouldNotBeReturned(int errorCode) {
        assertTrue(jobsPage.isNotErrorPage(context.get(Context.URL)), "The link returned a 404 error page");
    }

    @When("Unroll the filter on the cities")
    public void unrollTheFilterOnTheCities() {
        jobsPage.dropdownCities();
    }

    @Then("The city {string} is not duplicated")
    public void theCityIsNotDuplicated(String city) {
        assertFalse(jobsPage.isCityDuplicated(city), "The city "+ city +" is duplicated");
    }

    @And("Language is set to french")
    public void languageIsSetToFrench() {
        jobsPage.changeLanguageToFrench();
    }
}
