package com.immidart.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Utility class for database operations
 */
public class DatabaseUtils {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtils.class);
    private static Connection connection;

    /**
     * Establish database connection
     * @param url - Database URL
     * @param username - Database username
     * @param password - Database password
     * @param driverClassName - JDBC driver class name
     */
    public static void connectToDatabase(String url, String username, String password, String driverClassName) {
        logger.info("Connecting to database: " + url);
        
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
            logger.info("Database connection established successfully");
            
        } catch (ClassNotFoundException e) {
            logger.error("JDBC Driver not found: " + e.getMessage());
            throw new RuntimeException("Failed to load JDBC driver", e);
        } catch (SQLException e) {
            logger.error("Database connection failed: " + e.getMessage());
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    /**
     * Close database connection
     */
    public static void closeDatabase() {
        logger.info("Closing database connection");
        
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("Database connection closed successfully");
            }
        } catch (SQLException e) {
            logger.error("Error closing database connection: " + e.getMessage());
        }
    }

    /**
     * Execute SELECT query
     * @param query - SQL query
     * @return ResultSet
     */
    public static ResultSet executeSelectQuery(String query) {
        logger.info("Executing SELECT query: " + query);
        
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
            
        } catch (SQLException e) {
            logger.error("Error executing SELECT query: " + e.getMessage());
            throw new RuntimeException("Failed to execute SELECT query", e);
        }
    }

    /**
     * Execute INSERT/UPDATE/DELETE query
     * @param query - SQL query
     * @return Number of rows affected
     */
    public static int executeUpdateQuery(String query) {
        logger.info("Executing UPDATE query: " + query);
        
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
            
        } catch (SQLException e) {
            logger.error("Error executing UPDATE query: " + e.getMessage());
            throw new RuntimeException("Failed to execute UPDATE query", e);
        }
    }

    /**
     * Get value from ResultSet
     * @param resultSet - ResultSet object
     * @param columnName - Column name
     * @return Column value
     */
    public static String getResultSetValue(ResultSet resultSet, String columnName) {
        logger.info("Getting value from column: " + columnName);
        
        try {
            return resultSet.getString(columnName);
        } catch (SQLException e) {
            logger.error("Error getting ResultSet value: " + e.getMessage());
            throw new RuntimeException("Failed to get ResultSet value", e);
        }
    }

    /**
     * Check if ResultSet has rows
     * @param resultSet - ResultSet object
     * @return true if ResultSet has rows, false otherwise
     */
    public static boolean resultSetHasRows(ResultSet resultSet) {
        logger.info("Checking if ResultSet has rows");
        
        try {
            return resultSet.first();
        } catch (SQLException e) {
            logger.error("Error checking ResultSet: " + e.getMessage());
            return false;
        }
    }
}
