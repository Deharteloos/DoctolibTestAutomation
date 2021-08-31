package driverManager;

import config.Properties;
import drivers.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class WebDriverManager {

    private static final Logger LOG = LogManager.getLogger(WebDriverManager.class);

    private static final WebDriverManager INSTANCE = new WebDriverManager();

    private ThreadLocal<DriverFactory> driver = new ThreadLocal<>();

    private WebDriverManager() { }

    public WebDriver getDriver() { return driver.get().getDriver(); }

    public void setDriver(String browser) {
        LOG.info("===== DRIVER SET =====");
        driver.set(new DriverFactory(browser));
        setWindowSize();
    }

    private void setWindowSize() {
        WebDriver webDriver = driver.get().getDriver();
        WebDriver.Window window =  webDriver.manage().window();

        if(Properties.Config.getHeadless()) window.setPosition(new Point(0, 0));
        if (!Properties.Config.getDevice().isReal()) {
            window.setSize(Properties.Config.getDevice().getDimension());
        } else {
            window.maximize();
        }
    }


    public static WebDriverManager getInstance() {
        return INSTANCE;
    }

}
