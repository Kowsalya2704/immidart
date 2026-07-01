package com.immidart.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Utility class for file operations
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * Read CSV file and return as List of Maps
     * @param filePath - Path to CSV file
     * @return List of Maps containing CSV data
     */
    public static List<Map<String, String>> readCSV(String filePath) {
        logger.info("Reading CSV file: " + filePath);
        List<Map<String, String>> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String[] headers = null;
            String line;
            int lineNumber = 0;
            
            while ((line = br.readLine()) != null) {
                if (lineNumber == 0) {
                    headers = line.split(",");
                    lineNumber++;
                    continue;
                }
                
                String[] values = line.split(",");
                Map<String, String> row = new LinkedHashMap<>();
                
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i].trim(), 
                        i < values.length ? values[i].trim() : "");
                }
                
                data.add(row);
                lineNumber++;
            }
            
            logger.info("CSV file read successfully. Total rows: " + data.size());
            return data;
            
        } catch (IOException e) {
            logger.error("Error reading CSV file: " + e.getMessage());
            throw new RuntimeException("Failed to read CSV file: " + filePath, e);
        }
    }

    /**
     * Read JSON file and return as Object
     * @param filePath - Path to JSON file
     * @param className - Class to map JSON to
     * @return Object mapped from JSON
     */
    public static <T> T readJSON(String filePath, Class<T> className) {
        logger.info("Reading JSON file: " + filePath);
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            T data = mapper.readValue(new File(filePath), className);
            logger.info("JSON file read successfully");
            return data;
            
        } catch (IOException e) {
            logger.error("Error reading JSON file: " + e.getMessage());
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }

    /**
     * Write data to JSON file
     * @param filePath - Path to JSON file
     * @param data - Data to write
     */
    public static void writeJSON(String filePath, Object data) {
        logger.info("Writing JSON file: " + filePath);
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(filePath), data);
            logger.info("JSON file written successfully");
            
        } catch (IOException e) {
            logger.error("Error writing JSON file: " + e.getMessage());
            throw new RuntimeException("Failed to write JSON file: " + filePath, e);
        }
    }

    /**
     * Read properties file
     * @param filePath - Path to properties file
     * @return Properties object
     */
    public static Properties readProperties(String filePath) {
        logger.info("Reading properties file: " + filePath);
        Properties properties = new Properties();
        
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
            logger.info("Properties file read successfully");
            return properties;
            
        } catch (IOException e) {
            logger.error("Error reading properties file: " + e.getMessage());
            throw new RuntimeException("Failed to read properties file: " + filePath, e);
        }
    }

    /**
     * Check if file exists
     * @param filePath - Path to file
     * @return true if file exists, false otherwise
     */
    public static boolean fileExists(String filePath) {
        boolean exists = new File(filePath).exists();
        logger.info("File " + filePath + " exists: " + exists);
        return exists;
    }

    /**
     * Delete file
     * @param filePath - Path to file
     */
    public static void deleteFile(String filePath) {
        logger.info("Deleting file: " + filePath);
        
        if (new File(filePath).delete()) {
            logger.info("File deleted successfully");
        } else {
            logger.warn("Failed to delete file");
        }
    }

    /**
     * Create directory
     * @param dirPath - Path to directory
     */
    public static void createDirectory(String dirPath) {
        logger.info("Creating directory: " + dirPath);
        
        if (new File(dirPath).mkdirs()) {
            logger.info("Directory created successfully");
        }
    }

    /**
     * Get file content as string
     * @param filePath - Path to file
     * @return File content as string
     */
    public static String readFileAsString(String filePath) {
        logger.info("Reading file as string: " + filePath);
        StringBuilder content = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            logger.info("File read successfully");
            return content.toString();
            
        } catch (IOException e) {
            logger.error("Error reading file: " + e.getMessage());
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }
}
