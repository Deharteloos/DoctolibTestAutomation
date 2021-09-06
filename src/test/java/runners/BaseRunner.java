package runners;

import config.Properties;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.AllureFileManager;

import java.io.IOException;

public class BaseRunner extends AbstractTestNGCucumberTests {

    @Parameters({"browser", "device"})
    @BeforeClass
    public static void beforeTest(@Optional String browser, @Optional String device){
        String browserA;

        browserA = java.util.Optional
                .ofNullable(browser)
                .orElse(Properties.Config.getBrowser());

        Properties.DriverManager.setDriver(browserA);
    }

    @AfterClass
    public static void tearDown(){
        Properties.DriverManager.getDriver().quit();
        try {
            AllureFileManager.createEnvironmentPropertiesFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AllureFileManager.createCategorieJsonFile();
        AllureFileManager.createExecutorJsonFile();
    }

}
