package com.petstore.utils;

import com.github.javafaker.Faker;
import java.util.UUID;

/**
 * DataGenerator - Utility class for generating test data
 * Uses JavaFaker library to generate realistic fake data
 */
public class DataGenerator {
    
    private static final Faker faker = new Faker();
    
    /**
     * Generate random username
     * @return Generated username
     */
    public static String generateUsername() {
        return faker.name().username();
    }
    
    /**
     * Generate random first name
     * @return Generated first name
     */
    public static String generateFirstName() {
        return faker.name().firstName();
    }
    
    /**
     * Generate random last name
     * @return Generated last name
     */
    public static String generateLastName() {
        return faker.name().lastName();
    }
    
    /**
     * Generate random email address
     * @return Generated email
     */
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }
    
    /**
     * Generate random password
     * @return Generated password
     */
    public static String generatePassword() {
        return faker.internet().password();
    }
    
    /**
     * Generate random phone number
     * @return Generated phone number
     */
    public static String generatePhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }
    
    /**
     * Generate random numeric ID
     * @return Generated ID
     */
    public static int generateNumericId() {
        return faker.idNumber().hashCode();
    }
    
    /**
     * Generate random UUID
     * @return Generated UUID
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * Generate random street address
     * @return Generated address
     */
    public static String generateAddress() {
        return faker.address().fullAddress();
    }
    
    /**
     * Generate random city
     * @return Generated city
     */
    public static String generateCity() {
        return faker.address().city();
    }
    
    /**
     * Generate random country
     * @return Generated country
     */
    public static String generateCountry() {
        return faker.address().country();
    }
    
    /**
     * Generate random company name
     * @return Generated company name
     */
    public static String generateCompanyName() {
        return faker.company().name();
    }
    
    /**
     * Generate random job title
     * @return Generated job title
     */
    public static String generateJobTitle() {
        return faker.job().title();
    }
}
