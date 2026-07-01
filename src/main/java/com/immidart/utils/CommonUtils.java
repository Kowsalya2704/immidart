package com.immidart.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Utility class for common operations
 */
public class CommonUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * Wait for specified seconds
     * @param seconds - Number of seconds to wait
     */
    public static void waitForSeconds(long seconds) {
        logger.info("Waiting for " + seconds + " seconds");
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Generate random string
     * @param length - Length of random string
     * @return Random string
     */
    public static String generateRandomString(int length) {
        logger.info("Generating random string of length: " + length);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        
        return sb.toString();
    }

    /**
     * Generate random email
     * @return Random email
     */
    public static String generateRandomEmail() {
        logger.info("Generating random email");
        return "user" + generateRandomString(8) + "@example.com";
    }

    /**
     * Generate random number
     * @param min - Minimum value
     * @param max - Maximum value
     * @return Random number between min and max
     */
    public static int generateRandomNumber(int min, int max) {
        logger.info("Generating random number between " + min + " and " + max);
        return new Random().nextInt((max - min) + 1) + min;
    }

    /**
     * Check if string is null or empty
     * @param value - String value
     * @return true if null or empty, false otherwise
     */
    public static boolean isNullOrEmpty(String value) {
        logger.info("Checking if string is null or empty");
        return value == null || value.trim().isEmpty();
    }

    /**
     * Get current timestamp
     * @return Current timestamp as string
     */
    public static String getCurrentTimestamp() {
        logger.info("Getting current timestamp");
        return Long.toString(System.currentTimeMillis());
    }

    /**
     * Compare two strings (case-insensitive)
     * @param actual - Actual string
     * @param expected - Expected string
     * @return true if strings match, false otherwise
     */
    public static boolean compareStrings(String actual, String expected) {
        logger.info("Comparing strings. Actual: " + actual + ", Expected: " + expected);
        return actual != null && actual.equalsIgnoreCase(expected);
    }

    /**
     * Extract number from string
     * @param text - Text containing number
     * @return Extracted number
     */
    public static String extractNumber(String text) {
        logger.info("Extracting number from: " + text);
        return text.replaceAll("[^0-9]", "");
    }
}
