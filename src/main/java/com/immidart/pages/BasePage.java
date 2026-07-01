package com.immidart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

/**
 * Base Page class for all page objects
 * Contains common methods for element interactions
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    private static final long TIMEOUT = 15;

    /**
     * Constructor to initialize WebDriver and WebDriverWait
     * @param driver - WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        logger.info("Initializing page: " + this.getClass().getSimpleName());
    }

    /**
     * Wait for element to be visible
     * @param locator - By locator of the element
     * @return WebElement
     */
    protected WebElement waitForElementVisible(By locator) {
        logger.info("Waiting for element to be visible: " + locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     * @param locator - By locator of the element
     * @return WebElement
     */
    protected WebElement waitForElementClickable(By locator) {
        logger.info("Waiting for element to be clickable: " + locator);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to be present in DOM
     * @param locator - By locator of the element
     * @return WebElement
     */
    protected WebElement waitForElementPresent(By locator) {
        logger.info("Waiting for element to be present: " + locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Click on element
     * @param locator - By locator of the element
     */
    public void click(By locator) {
        logger.info("Clicking on element: " + locator);
        waitForElementClickable(locator).click();
    }

    /**
     * Type text in element
     * @param locator - By locator of the element
     * @param text - Text to type
     */
    public void type(By locator, String text) {
        logger.info("Typing in element: " + locator + " with text: " + text);
        WebElement element = waitForElementVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Get text from element
     * @param locator - By locator of the element
     * @return Text from element
     */
    public String getText(By locator) {
        logger.info("Getting text from element: " + locator);
        return waitForElementVisible(locator).getText();
    }

    /**
     * Get attribute value from element
     * @param locator - By locator of the element
     * @param attributeName - Name of the attribute
     * @return Attribute value
     */
    public String getAttribute(By locator, String attributeName) {
        logger.info("Getting attribute: " + attributeName + " from element: " + locator);
        return waitForElementVisible(locator).getAttribute(attributeName);
    }

    /**
     * Check if element is displayed
     * @param locator - By locator of the element
     * @return true if element is displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator) {
        logger.info("Checking if element is displayed: " + locator);
        try {
            return waitForElementVisible(locator).isDisplayed();
        } catch (Exception e) {
            logger.warn("Element not displayed: " + locator);
            return false;
        }
    }

    /**
     * Check if element exists
     * @param locator - By locator of the element
     * @return true if element exists, false otherwise
     */
    public boolean isElementPresent(By locator) {
        logger.info("Checking if element is present: " + locator);
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator);
            return false;
        }
    }

    /**
     * Wait for element to be invisible
     * @param locator - By locator of the element
     */
    public void waitForElementInvisible(By locator) {
        logger.info("Waiting for element to be invisible: " + locator);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Get current page title
     * @return Page title
     */
    public String getPageTitle() {
        logger.info("Getting page title");
        return driver.getTitle();
    }

    /**
     * Get current page URL
     * @return Current URL
     */
    public String getCurrentURL() {
        logger.info("Getting current URL");
        return driver.getCurrentUrl();
    }

    /**
     * Refresh the page
     */
    public void refreshPage() {
        logger.info("Refreshing page");
        driver.navigate().refresh();
    }

    /**
     * Scroll to element
     * @param locator - By locator of the element
     */
    public void scrollToElement(By locator) {
        logger.info("Scrolling to element: " + locator);
        WebElement element = driver.findElement(locator);
        ((org.openqa.selenium.JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Execute JavaScript
     * @param script - JavaScript to execute
     * @return Result of script execution
     */
    public Object executeJavaScript(String script) {
        logger.info("Executing JavaScript: " + script);
        return ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(script);
    }

    /**
     * Switch to alert and accept
     */
    public void acceptAlert() {
        logger.info("Accepting alert");
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    /**
     * Switch to alert and dismiss
     */
    public void dismissAlert() {
        logger.info("Dismissing alert");
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    /**
     * Get alert text
     * @return Alert text
     */
    public String getAlertText() {
        logger.info("Getting alert text");
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }
}
