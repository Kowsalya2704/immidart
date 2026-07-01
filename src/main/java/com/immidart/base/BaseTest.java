package com.immidart.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base test class for all Selenium WebDriver tests
 * Handles WebDriver initialization, teardown, and configuration
 */
public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    
    // Configuration constants
    private static final long IMPLICIT_WAIT = 10;
    private static final long EXPLICIT_WAIT = 15;
    private static final String BASE_URL = "https://example.com"; // Update with your base URL

    /**
     * Setup method to initialize WebDriver before each test
     * @param browser - Browser type (chrome, firefox, edge)
     */
    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        logger.info("Setting up WebDriver for browser: " + browser);
        
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = initializeChromeDriver();
                break;
            case "firefox":
                driver = initializeFirefoxDriver();
                break;
            case "edge":
                driver = initializeEdgeDriver();
                break;
            default:
                logger.warn("Unknown browser: " + browser + ". Defaulting to Chrome");
                driver = initializeChromeDriver();
        }
        
        // Initialize WebDriverWait with explicit wait time
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        
        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        
        // Maximize window
        driver.manage().window().maximize();
        
        logger.info("WebDriver initialized successfully");
    }

    /**
     * Initialize Chrome WebDriver
     * @return WebDriver instance for Chrome
     */
    private WebDriver initializeChromeDriver() {
        logger.info("Initializing Chrome WebDriver");
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        // Add Chrome options as needed
        // options.addArguments("--start-maximized");
        // options.addArguments("--disable-blink-features=AutomationControlled");
        // options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        
        return new ChromeDriver(options);
    }

    /**
     * Initialize Firefox WebDriver
     * @return WebDriver instance for Firefox
     */
    private WebDriver initializeFirefoxDriver() {
        logger.info("Initializing Firefox WebDriver");
        WebDriverManager.firefoxdriver().setup();
        
        FirefoxOptions options = new FirefoxOptions();
        // Add Firefox options as needed
        // options.addArguments("--start-maximized");
        
        return new FirefoxDriver(options);
    }

    /**
     * Initialize Edge WebDriver
     * @return WebDriver instance for Edge
     */
    private WebDriver initializeEdgeDriver() {
        logger.info("Initializing Edge WebDriver");
        WebDriverManager.edgedriver().setup();
        
        EdgeOptions options = new EdgeOptions();
        // Add Edge options as needed
        
        return new EdgeDriver(options);
    }

    /**
     * Teardown method to close WebDriver after each test
     */
    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down WebDriver");
        
        if (driver != null) {
            try {
                driver.quit();
                logger.info("WebDriver closed successfully");
            } catch (Exception e) {
                logger.error("Error while closing WebDriver: " + e.getMessage());
            }
        }
    }

    /**
     * Navigate to base URL
     */
    public void navigateToBaseURL() {
        logger.info("Navigating to URL: " + BASE_URL);
        driver.navigate().to(BASE_URL);
    }

    /**
     * Navigate to specific URL
     * @param url - URL to navigate to
     */
    public void navigateTo(String url) {
        logger.info("Navigating to URL: " + url);
        driver.navigate().to(url);
    }

    /**
     * Get current page title
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current page URL
     * @return Current URL
     */
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Refresh the page
     */
    public void refreshPage() {
        logger.info("Refreshing the page");
        driver.navigate().refresh();
    }

    /**
     * Navigate back
     */
    public void navigateBack() {
        logger.info("Navigating back");
        driver.navigate().back();
    }

    /**
     * Navigate forward
     */
    public void navigateForward() {
        logger.info("Navigating forward");
        driver.navigate().forward();
    }

    /**
     * Wait time in seconds
     * @param seconds - Number of seconds to wait
     */
    public void waitForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted: " + e.getMessage());
        }
    }

    /**
     * Get WebDriver instance
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Get WebDriverWait instance
     * @return WebDriverWait
     */
    public WebDriverWait getWait() {
        return wait;
    }
}
