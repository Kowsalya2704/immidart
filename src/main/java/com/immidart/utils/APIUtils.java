package com.immidart.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Utility class for API operations
 */
public class APIUtils {

    private static final Logger logger = LoggerFactory.getLogger(APIUtils.class);

    /**
     * Send GET request
     * @param baseURI - Base URI
     * @param endpoint - API endpoint
     * @return Response object
     */
    public static Response getRequest(String baseURI, String endpoint) {
        logger.info("Sending GET request to: " + baseURI + endpoint);
        RestAssured.baseURI = baseURI;
        
        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .when()
            .get(endpoint);
        
        logger.info("Response status code: " + response.getStatusCode());
        return response;
    }

    /**
     * Send GET request with headers
     * @param baseURI - Base URI
     * @param endpoint - API endpoint
     * @param headers - Request headers
     * @return Response object
     */
    public static Response getRequestWithHeaders(String baseURI, String endpoint, Map<String, String> headers) {
        logger.info("Sending GET request with headers to: " + baseURI + endpoint);
        RestAssured.baseURI = baseURI;
        
        Response response = RestAssured
            .given()
            .headers(headers)
            .contentType(ContentType.JSON)
            .when()
            .get(endpoint);
        
        logger.info("Response status code: " + response.getStatusCode());
        return response;
    }

    /**
     * Send POST request
     * @param baseURI - Base URI
     * @param endpoint - API endpoint
     * @param body - Request body
     * @return Response object
     */
    public static Response postRequest(String baseURI, String endpoint, String body) {
        logger.info("Sending POST request to: " + baseURI + endpoint);
        RestAssured.baseURI = baseURI;
        
        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .post(endpoint);
        
        logger.info("Response status code: " + response.getStatusCode());
        return response;
    }

    /**
     * Send POST request with headers
     * @param baseURI - Base URI
     * @param endpoint - API endpoint
     * @param headers - Request headers
     * @param body - Request body
     * @return Response object
     */
    public static Response postRequestWithHeaders(String baseURI, String endpoint, 
                                                   Map<String, String> headers, String body) {
        logger.info("Sending POST request with headers to: " + baseURI + endpoint);
        RestAssured.baseURI = baseURI;
        
        Response response = RestAssured
            .given()
            .headers(headers)
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .post(endpoint);
        
        logger.info("Response status code: " + response.getStatusCode());
        return response;
    }

    /**
     * Send PUT request
     * @param baseURI - Base URI
     * @param endpoint - API endpoint
     * @param body - Request body
     * @return Response object
     */
    public static Response putRequest(String baseURI, String endpoint, String body) {
        logger.info("Sending PUT request to: " + baseURI + endpoint);
        RestAssured.baseURI = baseURI;
        
        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(body)
            .when()
            .put(endpoint);
        
        logger.info("Response status code: " + response.getStatusCode());
        return response;
    }

    /**
     * Send DELETE request
     * @param baseURI - Base URI
     * @param endpoint - API endpoint
     * @return Response object
     */
    public static Response deleteRequest(String baseURI, String endpoint) {
        logger.info("Sending DELETE request to: " + baseURI + endpoint);
        RestAssured.baseURI = baseURI;
        
        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .when()
            .delete(endpoint);
        
        logger.info("Response status code: " + response.getStatusCode());
        return response;
    }

    /**
     * Verify response status code
     * @param response - Response object
     * @param expectedCode - Expected status code
     * @return true if status code matches, false otherwise
     */
    public static boolean verifyStatusCode(Response response, int expectedCode) {
        logger.info("Verifying response status code. Expected: " + expectedCode + 
            ", Actual: " + response.getStatusCode());
        return response.getStatusCode() == expectedCode;
    }

    /**
     * Get response body as string
     * @param response - Response object
     * @return Response body as string
     */
    public static String getResponseBody(Response response) {
        logger.info("Getting response body");
        return response.getBody().asString();
    }

    /**
     * Get response header value
     * @param response - Response object
     * @param headerName - Header name
     * @return Header value
     */
    public static String getResponseHeader(Response response, String headerName) {
        logger.info("Getting response header: " + headerName);
        return response.getHeader(headerName);
    }
}
