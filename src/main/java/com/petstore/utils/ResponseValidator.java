package com.petstore.utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jayway.jsonpath.JsonPath;

/**
 * ResponseValidator - Utility class for validating API responses
 * Provides common validation methods for status codes, headers, and body content
 */
public class ResponseValidator {
    
    private static final Logger logger = LoggerFactory.getLogger(ResponseValidator.class);
    
    /**
     * Validate response status code
     * @param response API Response
     * @param expectedStatusCode Expected status code
     * @return true if status code matches
     */
    public static boolean validateStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        boolean isValid = actualStatusCode == expectedStatusCode;
        logger.info("Status Code Validation: Expected={}, Actual={}, Result={}", 
                expectedStatusCode, actualStatusCode, isValid ? "PASS" : "FAIL");
        return isValid;
    }
    
    /**
     * Validate response header
     * @param response API Response
     * @param headerName Header name
     * @param expectedValue Expected header value
     * @return true if header matches
     */
    public static boolean validateHeader(Response response, String headerName, String expectedValue) {
        String actualValue = response.getHeader(headerName);
        boolean isValid = actualValue != null && actualValue.equals(expectedValue);
        logger.info("Header Validation: Header={}, Expected={}, Actual={}, Result={}", 
                headerName, expectedValue, actualValue, isValid ? "PASS" : "FAIL");
        return isValid;
    }
    
    /**
     * Validate response contains header
     * @param response API Response
     * @param headerName Header name
     * @return true if header exists
     */
    public static boolean validateHeaderExists(Response response, String headerName) {
        String headerValue = response.getHeader(headerName);
        boolean isValid = headerValue != null && !headerValue.isEmpty();
        logger.info("Header Exists Validation: Header={}, Result={}", headerName, isValid ? "PASS" : "FAIL");
        return isValid;
    }
    
    /**
     * Validate response JSON path value
     * @param response API Response
     * @param jsonPath JsonPath expression
     * @param expectedValue Expected value
     * @return true if value matches
     */
    public static boolean validateJsonPath(Response response, String jsonPath, Object expectedValue) {
        try {
            String body = response.getBody().asString();
            Object actualValue = JsonPath.read(body, jsonPath);
            boolean isValid = actualValue != null && actualValue.equals(expectedValue);
            logger.info("JsonPath Validation: Path={}, Expected={}, Actual={}, Result={}", 
                    jsonPath, expectedValue, actualValue, isValid ? "PASS" : "FAIL");
            return isValid;
        } catch (Exception e) {
            logger.error("Error validating JsonPath: {}", jsonPath, e);
            return false;
        }
    }
    
    /**
     * Validate response JSON path exists
     * @param response API Response
     * @param jsonPath JsonPath expression
     * @return true if path exists
     */
    public static boolean validateJsonPathExists(Response response, String jsonPath) {
        try {
            String body = response.getBody().asString();
            Object value = JsonPath.read(body, jsonPath);
            boolean isValid = value != null;
            logger.info("JsonPath Exists Validation: Path={}, Result={}", jsonPath, isValid ? "PASS" : "FAIL");
            return isValid;
        } catch (Exception e) {
            logger.debug("JsonPath not found: {}", jsonPath);
            return false;
        }
    }
    
    /**
     * Validate response contains text
     * @param response API Response
     * @param text Expected text
     * @return true if response contains text
     */
    public static boolean validateResponseContains(Response response, String text) {
        String body = response.getBody().asString();
        boolean isValid = body.contains(text);
        logger.info("Response Contains Validation: Text={}, Result={}", text, isValid ? "PASS" : "FAIL");
        return isValid;
    }
    
    /**
     * Get response time
     * @param response API Response
     * @return Response time in milliseconds
     */
    public static long getResponseTime(Response response) {
        long responseTime = response.getTime();
        logger.info("Response Time: {} ms", responseTime);
        return responseTime;
    }
    
    /**
     * Validate response time
     * @param response API Response
     * @param maxTimeInMs Maximum allowed time in milliseconds
     * @return true if response time is within limit
     */
    public static boolean validateResponseTime(Response response, long maxTimeInMs) {
        long responseTime = response.getTime();
        boolean isValid = responseTime <= maxTimeInMs;
        logger.info("Response Time Validation: Expected <= {} ms, Actual={} ms, Result={}", 
                maxTimeInMs, responseTime, isValid ? "PASS" : "FAIL");
        return isValid;
    }
}
