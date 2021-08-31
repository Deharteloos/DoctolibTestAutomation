package pageObjects;

import config.Configuration;
import config.Properties;
import context.ScenarioContext;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Page {


    protected ScenarioContext context = new ScenarioContext();
    private static final Logger LOG = LogManager.getLogger(Page.class);

    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected Actions action;

    //Waiters declaration
    protected WebDriverWait wait;
    protected WebDriverWait shortWait;
    protected WebDriverWait middleWait;
    protected WebDriverWait longWait;

    protected Configuration config = Properties.Config;

    Page(){
        // Init
        driver = Properties.DriverManager.getDriver();
        PageFactory.initElements(driver, this);

        js = (JavascriptExecutor) driver;
        action = new Actions(driver);

        //Waiters initialization
        wait        = new WebDriverWait(driver, Duration.ofSeconds(2));
        shortWait   = new WebDriverWait(driver, Duration.ofSeconds(10));
        middleWait  = new WebDriverWait(driver, Duration.ofSeconds(20));
        longWait    = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    /**
     * Wait until the condition in the function is satisfied
     *
     * @param isTrue the condition
     * @param <V>    the condition return type
     * @return true is thee condition is satisfied, false if the condition hasn't been satisfied in the given time
     */
    protected <V>boolean waitUntil(Function<? super WebDriver, V> isTrue){
        try{
            wait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    protected <V>boolean shortUntil(Function<? super WebDriver, V> isTrue){
        try{
            shortWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    protected <V>boolean middleUntil(Function<? super WebDriver, V> isTrue){
        try{
            middleWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    protected <V>boolean longUntil(Function<? super WebDriver, V> isTrue){
        try{
            longWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Wait until the page is loaded
     */
    protected void waitForLoadingPage(){
        if(!longUntil(driver->js.executeScript("return document.readyState").equals("complete") || js.executeScript("return document.readyState").equals("interactive") )){
            LOG.warn(driver.getCurrentUrl() + " not loaded");
            return;
        }
        LOG.info(driver.getCurrentUrl() + " successfully loaded");
    }

    /**
     * Open the page with the given url
     * @param url
     */
    protected void get(String url){
        driver.get(url);
        waitForLoadingPage();
    }

    protected void clickOn(WebElement element){
        if( !shortUntil(visibilityOf(element)) ){
            LOG.warn("Element not visible after click");
        }

        if( !shortUntil(elementToBeClickable(element))){
            LOG.warn("Element not clickable");
        }
        element.click();
    }

    protected Boolean verifyLink(WebElement element) throws IOException {
        String url = element.getAttribute("href");
        clickOn(element);
        saveScreenShotPNG();
        try{
            HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
            c.setRequestMethod("HEAD");
            c.connect();
            int r = c.getResponseCode();
            if(r == 200) return true;
            return false;
        }catch (Exception e){
            return false;
        }
    }

    @Attachment(value = "screenshot", type = "image/png")
    public void saveScreenShotPNG(){
        Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    protected void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true); scrollBy(0, -window.innerHeight/2 + arguments[0].offsetHeight/2);", element);
    }

    protected void scrollUpToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true); scrollBy(0, -window.innerHeight/2 - arguments[0].offsetHeight/2);", element);
    }

    protected void scrollDownToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);arguments[0].scrollTop=arguments[0].scrollHeight", element);
    }

    /**
     * Checks whether the cookies dialog box is shown and accepts the cookies
     * @param byDialogBox
     * @param byAcceptBtn
     */
    public void acceptsCookies(By byDialogBox, By byAcceptBtn) {
        if(!shortUntil(ExpectedConditions.visibilityOfElementLocated(byDialogBox))) {
            LOG.warn("The cookies dialog box was not shown.");
        } else {
            WebElement acceptCookieBtn = driver.findElement(byAcceptBtn);
            if(acceptCookieBtn != null) {
                acceptCookieBtn.click();
                LOG.info("Cookies accepted");
            } else {
                LOG.warn("Button for cookies acceptance not found.");
            }
        }
    }

    public void sendKeysSlowly(WebElement webElement, String key) {
        for (int i = 0; i < key.length(); i++) {
            webElement.sendKeys(key.substring(i, i + 1));
        }
    }

}
