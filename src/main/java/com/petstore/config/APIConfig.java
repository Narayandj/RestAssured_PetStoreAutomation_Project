package com.petstore.config;

/**
 * APIConfig - Centralized API Configuration Constants
 * Contains base URLs and endpoints for different environments
 */
public class APIConfig {
    
    // Base URLs
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    public static final String BASE_URL_QA = "https://petstore-qa.swagger.io/v2";
    public static final String BASE_URL_DEV = "https://petstore-dev.swagger.io/v2";
    
    // User Endpoints
    public static final String USER_ENDPOINT = "/user";
    public static final String USER_LOGIN_ENDPOINT = "/user/login";
    public static final String USER_LOGOUT_ENDPOINT = "/user/logout";
    public static final String USER_BY_USERNAME = "/user/{username}";
    
    // Pet Endpoints
    public static final String PET_ENDPOINT = "/pet";
    public static final String PET_BY_ID = "/pet/{petId}";
    public static final String PET_BY_STATUS = "/pet/findByStatus";
    
    // Store Endpoints
    public static final String STORE_ENDPOINT = "/store";
    public static final String STORE_ORDER = "/store/order";
    public static final String STORE_ORDER_BY_ID = "/store/order/{orderId}";
    
    // Content Types
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_XML = "application/xml";
    
    // Request Timeouts (in seconds)
    public static final int REQUEST_TIMEOUT = 30;
    public static final int CONNECTION_TIMEOUT = 10;
    
    // Response Validation
    public static final int SUCCESS_STATUS_CODE = 200;
    public static final int CREATED_STATUS_CODE = 201;
    public static final int NO_CONTENT_STATUS_CODE = 204;
    public static final int BAD_REQUEST_STATUS_CODE = 400;
    public static final int UNAUTHORIZED_STATUS_CODE = 401;
    public static final int NOT_FOUND_STATUS_CODE = 404;
    public static final int SERVER_ERROR_STATUS_CODE = 500;
    
    // Report Configuration
    public static final String REPORT_DIRECTORY = "reports";
    public static final String SCREENSHOT_DIRECTORY = "screenshots";
    public static final String TEST_DATA_DIRECTORY = "testdata";
}
