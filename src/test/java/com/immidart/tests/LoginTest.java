package com.immidart.tests;

import com.immidart.base.BaseTest;
import com.immidart.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Login Test Cases
 * Demonstrates how to write test cases using LoginPage and BaseTest
 */
public class LoginTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    private LoginPage loginPage;

    // Test data constants
    private static final String VALID_USERNAME = "testuser@example.com";
    private static final String VALID_PASSWORD = "TestPassword123";
    private static final String INVALID_USERNAME = "invalid@example.com";
    private static final String INVALID_PASSWORD = "WrongPassword";

    /**
     * Test Case 1: Verify login page is displayed
     */
    @Test(priority = 1, description = "Verify login page is displayed")
    public void testLoginPageIsDisplayed() {
        logger.info("===== Test Case 1: Verify login page is displayed =====");
        
        // Navigate to application
        navigateToBaseURL();
        
        // Initialize LoginPage
        loginPage = new LoginPage(driver);
        
        // Verify login page heading is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page heading should be displayed");
        
        logger.info("Login page is displayed successfully");
    }

    /**
     * Test Case 2: Successful login with valid credentials
     */
    @Test(priority = 2, description = "Successful login with valid credentials")
    public void testSuccessfulLoginWithValidCredentials() {
        logger.info("===== Test Case 2: Successful login with valid credentials =====");
        
        // Navigate to application
        navigateToBaseURL();
        
        // Initialize LoginPage
        loginPage = new LoginPage(driver);
        
        // Verify login page is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
        
        // Perform login
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        
        // Wait for page to load after login
        waitForSeconds(2);
        
        // Verify successful login (change URL/title based on your application)
        String currentURL = loginPage.getCurrentURL();
        logger.info("Current URL after login: " + currentURL);
        
        // Assert that user is redirected to dashboard or home page
        Assert.assertFalse(currentURL.contains("login"), 
            "User should be redirected away from login page");
        
        logger.info("Login successful with valid credentials");
    }

    /**
     * Test Case 3: Login with invalid credentials
     */
    @Test(priority = 3, description = "Login with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        logger.info("===== Test Case 3: Login with invalid credentials =====");
        
        // Navigate to application
        navigateToBaseURL();
        
        // Initialize LoginPage
        loginPage = new LoginPage(driver);
        
        // Verify login page is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
        
        // Perform login with invalid credentials
        loginPage.login(INVALID_USERNAME, INVALID_PASSWORD);
        
        // Wait for error message to appear
        waitForSeconds(1);
        
        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message should be displayed for invalid credentials");
        
        // Get error message text
        String errorMessage = loginPage.getErrorMessage();
        logger.info("Error message: " + errorMessage);
        
        logger.info("Login failed as expected with invalid credentials");
    }

    /**
     * Test Case 4: Login with empty username
     */
    @Test(priority = 4, description = "Login with empty username")
    public void testLoginWithEmptyUsername() {
        logger.info("===== Test Case 4: Login with empty username =====");
        
        // Navigate to application
        navigateToBaseURL();
        
        // Initialize LoginPage
        loginPage = new LoginPage(driver);
        
        // Verify login page is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
        
        // Enter only password and click login
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLoginButton();
        
        // Wait for validation message
        waitForSeconds(1);
        
        // Verify user is still on login page (not logged in)
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "User should remain on login page");
        
        logger.info("Validation working correctly for empty username");
    }

    /**
     * Test Case 5: Login with empty password
     */
    @Test(priority = 5, description = "Login with empty password")
    public void testLoginWithEmptyPassword() {
        logger.info("===== Test Case 5: Login with empty password =====");
        
        // Navigate to application
        navigateToBaseURL();
        
        // Initialize LoginPage
        loginPage = new LoginPage(driver);
        
        // Verify login page is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
        
        // Enter only username and click login
        loginPage.enterUsername(VALID_USERNAME);
        loginPage.clickLoginButton();
        
        // Wait for validation message
        waitForSeconds(1);
        
        // Verify user is still on login page (not logged in)
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "User should remain on login page");
        
        logger.info("Validation working correctly for empty password");
    }

    /**
     * Test Case 6: Login with remember me option
     */
    @Test(priority = 6, description = "Login with remember me option")
    public void testLoginWithRememberMe() {
        logger.info("===== Test Case 6: Login with remember me option =====");
        
        // Navigate to application
        navigateToBaseURL();
        
        // Initialize LoginPage
        loginPage = new LoginPage(driver);
        
        // Verify login page is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
        
        // Verify remember me is not checked initially
        Assert.assertFalse(loginPage.isRememberMeChecked(), 
            "Remember me should not be checked initially");
        
        // Perform login with remember me
        loginPage.loginWithRememberMe(VALID_USERNAME, VALID_PASSWORD);
        
        // Wait for page to load
        waitForSeconds(2);
        
        // Verify successful login
        String currentURL = loginPage.getCurrentURL();
        Assert.assertFalse(currentURL.contains("login"), 
            "User should be redirected away from login page");
        
        logger.info("Login with remember me successful");
    }

    /**
     * Test Case 7: Test forgot password link navigation
     */
    @Test(priority = 7, description = "Test forgot password link navigation")
    public void testForgotPasswordLinkNavigation() {
        logger.info("===== Test Case 7: Test forgot password link navigation =====");
        
        // Navigate to application
        navigateToBaseURL();
        
        // Initialize LoginPage
        loginPage = new LoginPage(driver);
        
        // Verify login page is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
        
        // Click forgot password link
        loginPage.clickForgotPasswordLink();
        
        // Wait for forgot password page to load
        waitForSeconds(1);
        
        // Verify navigation to forgot password page
        String currentURL = loginPage.getCurrentURL();
        logger.info("Current URL after clicking forgot password: " + currentURL);
        
        Assert.assertTrue(currentURL.contains("forgot") || currentURL.contains("reset"), 
            "Should navigate to forgot password page");
        
        logger.info("Forgot password link navigation successful");
    }

    /**
     * Test Case 8: Test sign up link navigation
     */
    @Test(priority = 8, description = "Test sign up link navigation")
    public void testSignUpLinkNavigation() {
        logger.info("===== Test Case 8: Test sign up link navigation =====");
        
        // Navigate to application
        navigateToBaseURL();
        
        // Initialize LoginPage
        loginPage = new LoginPage(driver);
        
        // Verify login page is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
        
        // Click sign up link
        loginPage.clickSignUpLink();
        
        // Wait for sign up page to load
        waitForSeconds(1);
        
        // Verify navigation to sign up page
        String currentURL = loginPage.getCurrentURL();
        logger.info("Current URL after clicking sign up: " + currentURL);
        
        Assert.assertTrue(currentURL.contains("signup") || currentURL.contains("register"), 
            "Should navigate to sign up page");
        
        logger.info("Sign up link navigation successful");
    }

    /**
     * Test Case 9: Verify input fields are enabled
     */
    @Test(priority = 9, description = "Verify input fields are enabled")
    public void testInputFieldsAreEnabled() {
        logger.info("===== Test Case 9: Verify input fields are enabled =====");
        
        // Navigate to application
        navigateToBaseURL();
        
        // Initialize LoginPage
        loginPage = new LoginPage(driver);
        
        // Verify login page is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
        
        // Verify username field is enabled
        Assert.assertTrue(loginPage.isUsernameFieldEnabled(), 
            "Username field should be enabled");
        
        // Verify password field is enabled
        Assert.assertTrue(loginPage.isPasswordFieldEnabled(), 
            "Password field should be enabled");
        
        logger.info("All input fields are enabled");
    }

    /**
     * Test Case 10: Clear fields and enter new credentials
     */
    @Test(priority = 10, description = "Clear fields and enter new credentials")
    public void testClearAndEnterCredentials() {
        logger.info("===== Test Case 10: Clear fields and enter new credentials =====");
        
        // Navigate to application
        navigateToBaseURL();
        
        // Initialize LoginPage
        loginPage = new LoginPage(driver);
        
        // Verify login page is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
        
        // Enter credentials
        loginPage.enterUsername("tempuser@example.com");
        loginPage.enterPassword("TempPassword123");
        
        // Clear fields
        loginPage.clearUsernameField();
        loginPage.clearPasswordField();
        
        // Enter valid credentials
        loginPage.enterUsername(VALID_USERNAME);
        loginPage.enterPassword(VALID_PASSWORD);
        
        logger.info("Fields cleared and new credentials entered successfully");
    }
}
