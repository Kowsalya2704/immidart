package com.immidart.tests;

import com.immidart.base.BaseTest;
import com.immidart.pages.LoginPage;
import com.immidart.utils.FileUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Data-Driven Login Test Cases
 * Demonstrates how to write data-driven tests using CSV files
 */
public class LoginDataDrivenTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginDataDrivenTest.class);
    private LoginPage loginPage;
    private static final String TEST_DATA_FILE = "src/test/resources/loginTestData.csv";

    /**
     * Data Provider - Reads test data from CSV file
     * @return 2D array of test data
     */
    @DataProvider(name = "loginData")
    public Object[][] getLoginTestData() {
        logger.info("Loading test data from CSV file: " + TEST_DATA_FILE);
        
        List<Map<String, String>> data = FileUtils.readCSV(TEST_DATA_FILE);
        Object[][] testData = new Object[data.size()][data.get(0).size()];
        
        int rowIndex = 0;
        for (Map<String, String> row : data) {
            testData[rowIndex] = row.values().toArray();
            rowIndex++;
        }
        
        logger.info("Test data loaded successfully. Total test cases: " + testData.length);
        return testData;
    }

    /**
     * Data-Driven Test: Login with various credentials from CSV
     * @param username - Username from CSV
     * @param password - Password from CSV
     * @param expectedResult - Expected result (Success/Failure/Validation Error)
     */
    @Test(
        dataProvider = "loginData",
        description = "Data-driven login test with CSV data"
    )
    public void testLoginWithCSVData(String username, String password, String expectedResult) {
        logger.info("===== Data-Driven Test: Username=" + username + 
            ", Password=" + password + ", Expected=" + expectedResult + " =====");
        
        // Navigate to application
        navigateToBaseURL();
        
        // Initialize LoginPage
        loginPage = new LoginPage(driver);
        
        // Verify login page is displayed
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "Login page should be displayed");
        
        // Perform login
        loginPage.login(username, password);
        
        // Wait for result
        waitForSeconds(2);
        
        // Verify result based on expected output from CSV
        switch (expectedResult.toLowerCase().trim()) {
            case "success":
                verifyLoginSuccess();
                break;
            case "failure":
                verifyLoginFailure();
                break;
            case "validation error":
                verifyValidationError();
                break;
            default:
                logger.warn("Unknown expected result: " + expectedResult);
        }
    }

    /**
     * Verify successful login
     */
    private void verifyLoginSuccess() {
        logger.info("Verifying successful login");
        
        String currentURL = loginPage.getCurrentURL();
        logger.info("Current URL after login: " + currentURL);
        
        Assert.assertFalse(currentURL.contains("login"), 
            "User should be redirected away from login page");
        
        logger.info("Login successful as expected");
    }

    /**
     * Verify login failure with error message
     */
    private void verifyLoginFailure() {
        logger.info("Verifying login failure");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message should be displayed");
        
        String errorMessage = loginPage.getErrorMessage();
        logger.info("Error message received: " + errorMessage);
        
        logger.info("Login failed as expected");
    }

    /**
     * Verify validation error
     */
    private void verifyValidationError() {
        logger.info("Verifying validation error");
        
        // User should still be on login page
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), 
            "User should remain on login page for validation error");
        
        logger.info("Validation error occurred as expected");
    }
}
