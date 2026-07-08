package com.petstore.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.petstore.config.APIConfig;
import com.petstore.config.ConfigManager;

/**
 * APIClient - Base API client for setting up requests and responses
 * Provides reusable RequestSpec and ResponseSpec configurations
 */
public class APIClient {
    
    private static final Logger logger = LoggerFactory.getLogger(APIClient.class);
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    
    /**
     * Setup base request specification
     */
    public static void setupRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(ConfigManager.getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("User-Agent", "RestAssured-Automation-Framework")
                .setConfig(RestAssured.config()
                        .httpClient(io.restassured.http.HttpClientConfig.httpClientConfig()
                                .setParam(org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT, 
                                        APIConfig.CONNECTION_TIMEOUT * 1000)
                                .setParam(org.apache.http.params.CoreConnectionPNames.SO_TIMEOUT, 
                                        APIConfig.REQUEST_TIMEOUT * 1000)));
        
        requestSpec = builder.build();
        logger.info("Request specification configured with base URI: {}", ConfigManager.getBaseUrl());
    }
    
    /**
     * Setup base response specification
     */
    public static void setupResponseSpec() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectContentType(ContentType.JSON)
                .expectStatusCode(200);
        
        responseSpec = builder.build();
        logger.info("Response specification configured");
    }
    
    /**
     * Get the configured request specification
     * @return RequestSpecification
     */
    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {
            setupRequestSpec();
        }
        return requestSpec;
    }
    
    /**
     * Get the configured response specification
     * @return ResponseSpecification
     */
    public static ResponseSpecification getResponseSpec() {
        if (responseSpec == null) {
            setupResponseSpec();
        }
        return responseSpec;
    }
    
    /**
     * Reset specifications
     */
    public static void resetSpecs() {
        requestSpec = null;
        responseSpec = null;
        logger.info("API specifications reset");
    }
    
    /**
     * Initialize API client with base configurations
     */
    public static void initialize() {
        ConfigManager.loadConfig();
        setupRequestSpec();
        setupResponseSpec();
        logger.info("API Client initialized successfully");
    }
}
