package BrowserUtils;

import io.cucumber.java.Scenario;
import loopcamp.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class BrowserUtils {
    public static Scenario myScenario;

    public static void takeScreenshot() {
        try {
            myScenario.log("Current URL is: " + Driver.getDriver().getCurrentUrl());
            final byte [] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            myScenario.attach(screenshot, "image/png", myScenario.getName());
        } catch (WebDriverException wbd) {
            wbd.getMessage();
        } catch (ClassCastException cce) {
            cce.getMessage();
        }

    }



    /**
     * validate if driver switched to expected URL and title
     * @param driver
     * @param expectedURL
     * @param expectedTitle
     * @author anna
     * implements assertion
     */

    public static void switchWindowAndValidate(WebDriver driver, String expectedURL, String expectedTitle){
        expectedURL = expectedURL.toLowerCase();
        expectedTitle = expectedTitle.toLowerCase();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String each : windowHandles) {
            driver.switchTo().window(each);
            if (driver.getCurrentUrl().toLowerCase().contains(expectedURL)) {
                break;
            }
        }
        assertTrue(driver.getTitle().toLowerCase().contains(expectedTitle));
    }

    /**
     * switches to the new window by the exact title
     * return to original window if the window with given title not found
     * @param driver
     * @param targetTitle
     */
    public static void switchToWindow(WebDriver driver, String targetTitle) {
        String origin = driver.getWindowHandle();
        for (String each : driver.getWindowHandles()) {
            driver.switchTo().window(each);
            if (driver.getTitle().contains(targetTitle)) {
                return;
            }
        }
        driver.switchTo().window(origin);
    }
    /**
     * @param driver
     * @param expectedTitle
     * returns void, assertion is implemented
     * @author anna
     */

    public static void validateTitle (WebDriver driver, String expectedTitle) {
        assertTrue(driver.getTitle().contains(expectedTitle));
    }


    /**
     * @param nameOfPage
     * Click any element on loop practice
     * @author anna
     */
    public static void loopLinkClick (String nameOfPage) {
        WebElement element = Driver.getDriver().findElement(By.xpath("//a[.='" + nameOfPage+"']"));
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    /**
     * @param element on which on hover
     * Moves the mouse to given element
     * @author anna
     */
    public static void hover (WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    /**
     * Scroll to element using Javascript
     * @param element
     */
    public static void scrollToElement (WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Click on an element using Javascript
     * @param element
     * @author anna
     */

    public static void clickWithJS (WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    /**
     * DoubleClick on an element using Javascript
     * @param element
     * @author anna
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick().build().perform();
    }

    /**
     * Waits for the provided element to be visible on the page
     * @param element
     * @param timeToWaitInSec
     * return WebElement
     * @author anna
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for the provided element to be INvisible on the page
     * @param element
     * @param timeToWaitInSec
     * void
     * @author anna
     */
    public static void waitForInvisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Waits for the provided element to be clicable
     * @param element
     * @param timeout
     * return WebElement
     * @author anna
     */
    public static WebElement waitForClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * performs a pause
     * @param seconds
     * @author anna
     */
    public static void justWait(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}