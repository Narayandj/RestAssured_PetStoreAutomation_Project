package com.petstore.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigManager - Manages configuration from properties files
 * Supports loading environment-specific configurations
 */
public class ConfigManager {
    
    private static Properties properties;
    private static final String DEFAULT_CONFIG = "config.properties";
    
    /**
     * Load configuration from properties file
     */
    public static void loadConfig() {
        properties = new Properties();
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(DEFAULT_CONFIG)) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("Config file not found: " + DEFAULT_CONFIG);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config: " + e.getMessage(), e);
        }
    }
    
    /**
     * Load configuration from specific environment
     * @param environment Environment name (dev, qa, prod)
     */
    public static void loadConfig(String environment) {
        properties = new Properties();
        String configFile = "config-" + environment + ".properties";
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(configFile)) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("Config file not found: " + configFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config: " + e.getMessage(), e);
        }
    }
    
    /**
     * Get property value
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        if (properties == null) {
            loadConfig();
        }
        return properties.getProperty(key);
    }
    
    /**
     * Get property value with default fallback
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Property value or default
     */
    public static String getProperty(String key, String defaultValue) {
        if (properties == null) {
            loadConfig();
        }
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Get base URL based on environment
     * @return Base URL
     */
    public static String getBaseUrl() {
        String env = getProperty("environment", "prod");
        switch (env.toLowerCase()) {
            case "dev":
                return APIConfig.BASE_URL_DEV;
            case "qa":
                return APIConfig.BASE_URL_QA;
            default:
                return APIConfig.BASE_URL;
        }
    }
    
    /**
     * Get request timeout
     * @return Timeout in seconds
     */
    public static int getRequestTimeout() {
        String timeout = getProperty("request.timeout", String.valueOf(APIConfig.REQUEST_TIMEOUT));
        return Integer.parseInt(timeout);
    }
}
