package steps;

import context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.HomePage;
import pageObjects.JobsPage;

public final class Hooks {

    private static final Logger LOG = LogManager.getLogger(Hooks.class);

    private ScenarioContext context;
    private JobsPage jobsPage;

    public Hooks(ScenarioContext context, JobsPage jobsPage) {
        this.context = context;
        this.jobsPage = jobsPage;
    }

//    @Before("@jobs_page")
//    public void changeLanguageToFrench() {
//        jobsPage.changeLanguageToFrench();
//    }

}
