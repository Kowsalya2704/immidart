package com.immidart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Login Page Object Model
 * Demonstrates how to create a page object using BasePage
 */
public class LoginPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    // ========== Page Locators ==========
    // Username/Email field
    private static final By USERNAME_INPUT = By.id("username");
    // Or use other locator strategies:
    // private static final By USERNAME_INPUT = By.name("email");
    // private static final By USERNAME_INPUT = By.xpath("//input[@type='email']");
    // private static final By USERNAME_INPUT = By.cssSelector("input.email-field");

    // Password field
    private static final By PASSWORD_INPUT = By.id("password");

    // Login button
    private static final By LOGIN_BUTTON = By.id("loginButton");
    // Or: private static final By LOGIN_BUTTON = By.xpath("//button[text()='Login']");

    // Error message
    private static final By ERROR_MESSAGE = By.className("error-message");

    // Remember me checkbox
    private static final By REMEMBER_ME_CHECKBOX = By.id("rememberMe");

    // Forgot password link
    private static final By FORGOT_PASSWORD_LINK = By.linkText("Forgot Password?");

    // Sign up link
    private static final By SIGNUP_LINK = By.linkText("Sign Up");

    // Page title/heading
    private static final By PAGE_HEADING = By.xpath("//h1[text()='Login']");

    /**
     * Constructor
     * @param driver - WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ========== Page Actions/Methods ==========

    /**
     * Verify Login page is displayed
     * @return true if login page is displayed, false otherwise
     */
    public boolean isLoginPageDisplayed() {
        logger.info("Verifying Login page is displayed");
        return isElementDisplayed(PAGE_HEADING);
    }

    /**
     * Enter username
     * @param username - Username to enter
     */
    public void enterUsername(String username) {
        logger.info("Entering username: " + username);
        type(USERNAME_INPUT, username);
    }

    /**
     * Enter password
     * @param password - Password to enter
     */
    public void enterPassword(String password) {
        logger.info("Entering password");
        type(PASSWORD_INPUT, password);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        logger.info("Clicking login button");
        click(LOGIN_BUTTON);
    }

    /**
     * Perform login with username and password
     * @param username - Username
     * @param password - Password
     */
    public void login(String username, String password) {
        logger.info("Performing login with username: " + username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Perform login with remember me option
     * @param username - Username
     * @param password - Password
     */
    public void loginWithRememberMe(String username, String password) {
        logger.info("Performing login with remember me option");
        enterUsername(username);
        enterPassword(password);
        clickRememberMeCheckbox();
        clickLoginButton();
    }

    /**
     * Get error message
     * @return Error message text
     */
    public String getErrorMessage() {
        logger.info("Getting error message");
        return getText(ERROR_MESSAGE);
    }

    /**
     * Verify error message is displayed
     * @return true if error message is displayed, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        logger.info("Checking if error message is displayed");
        return isElementDisplayed(ERROR_MESSAGE);
    }

    /**
     * Click remember me checkbox
     */
    public void clickRememberMeCheckbox() {
        logger.info("Clicking remember me checkbox");
        click(REMEMBER_ME_CHECKBOX);
    }

    /**
     * Check if remember me checkbox is selected
     * @return true if checked, false otherwise
     */
    public boolean isRememberMeChecked() {
        logger.info("Checking if remember me is checked");
        String isChecked = getAttribute(REMEMBER_ME_CHECKBOX, "checked");
        return isChecked != null && isChecked.equals("checked");
    }

    /**
     * Click forgot password link
     */
    public void clickForgotPasswordLink() {
        logger.info("Clicking forgot password link");
        click(FORGOT_PASSWORD_LINK);
    }

    /**
     * Click sign up link
     */
    public void clickSignUpLink() {
        logger.info("Clicking sign up link");
        click(SIGNUP_LINK);
    }

    /**
     * Verify username field is enabled
     * @return true if enabled, false otherwise
     */
    public boolean isUsernameFieldEnabled() {
        logger.info("Checking if username field is enabled");
        return driver.findElement(USERNAME_INPUT).isEnabled();
    }

    /**
     * Verify password field is enabled
     * @return true if enabled, false otherwise
     */
    public boolean isPasswordFieldEnabled() {
        logger.info("Checking if password field is enabled");
        return driver.findElement(PASSWORD_INPUT).isEnabled();
    }

    /**
     * Clear username field
     */
    public void clearUsernameField() {
        logger.info("Clearing username field");
        driver.findElement(USERNAME_INPUT).clear();
    }

    /**
     * Clear password field
     */
    public void clearPasswordField() {
        logger.info("Clearing password field");
        driver.findElement(PASSWORD_INPUT).clear();
    }
}
