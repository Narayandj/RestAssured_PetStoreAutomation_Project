# FRAMEWORK_SETUP.md - Enhanced Framework Setup & Architecture

## Project Enhancement Summary

Your RestAssured PetStore Automation project has been enhanced with an enterprise-grade framework setup. Below is a comprehensive guide to the improvements and best practices.

---

## ✅ What Was Enhanced

### 1. **POM.xml (Maven Build Configuration)**
- ✓ Centralized dependency versioning using `<properties>`
- ✓ Added Lombok for reducing boilerplate code
- ✓ Added Jackson for JSON serialization/deserialization
- ✓ Configured Maven Surefire plugin for parallel test execution
- ✓ Added Maven Shade plugin for creating executable JARs
- ✓ Proper Java 11 compilation configuration

### 2. **Configuration Management**
- ✓ `APIConfig.java` - Centralized API constants
- ✓ `ConfigManager.java` - Multi-environment configuration support (DEV, QA, PROD)
- ✓ Environment-specific property files:
  - `config.properties` - Production
  - `config-qa.properties` - QA environment
  - `config-dev.properties` - Development environment

### 3. **Utility Classes**
- ✓ `APIClient.java` - Centralized REST client setup with request/response specs
- ✓ `ResponseValidator.java` - Common response validation methods
- ✓ `DataGenerator.java` - Test data generation using JavaFaker

### 4. **Test Infrastructure**
- ✓ `TestListener.java` - TestNG lifecycle listener for test logging and reporting
- ✓ Enhanced ExtentReportManager with better formatting
- ✓ Integrated SLF4J for structured logging

### 5. **Project Documentation**
- ✓ Comprehensive README.md with setup instructions
- ✓ .gitignore with proper exclusions
- ✓ This setup documentation

---

## 🏗️ Project Structure

```
RestAssured_PetStoreAutomation_Project/
│
├── src/
│   ├── main/java/com/petstore/
│   │   ├── config/
│   │   │   ├── APIConfig.java              ← API constants
│   │   │   └── ConfigManager.java          ← Environment config
│   │   ├── utils/
│   │   │   ├── APIClient.java              ← REST client setup
│   │   │   ├── ResponseValidator.java      ← Response validation
│   │   │   └── DataGenerator.java          ← Test data generation
│   │   └── listeners/
│   │       └── TestListener.java           ← Test lifecycle listener
│   │
│   └── test/
│       ├── java/api/
│       │   ├── endpoints/
│       │   │   ├── UserEndPoint.java
│       │   │   ├── Routes.java
│       │   │   └── PropertiesUserEndPoint2.java
│       │   ├── payloads/
│       │   │   ├── User.java
│       │   │   └── [Other POJOs]
│       │   ├── test/
│       │   │   ├── UserTests.java
│       │   │   └── PropertieReaderUserTests2.java
│       │   └── utilities/
│       │       └── ExtentReportManager.java
│       │
│       └── resources/
│           ├── config.properties           ← Production config
│           ├── config-qa.properties        ← QA config
│           ├── config-dev.properties       ← Dev config
│           └── routes.properties           ← API routes
│
├── reports/                                ← Test reports (generated)
├── test-output/                            ← TestNG output (generated)
├── screenshots/                            ← Screenshots (generated)
│
├── pom.xml                                 ← Enhanced Maven configuration
├── testng.xml                              ← TestNG suite configuration
├── .gitignore                              ← Git ignore rules
└── README.md                               ← Project documentation
```

---

## 🚀 Quick Start Guide

### 1. **Clone and Install Dependencies**
```bash
git clone https://github.com/Narayandj/RestAssured_PetStoreAutomation_Project.git
cd RestAssured_PetStoreAutomation_Project
mvn clean install
```

### 2. **Run Tests**
```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=UserTests

# Run with QA environment
mvn test -Denvironment=qa

# Run in parallel (5 threads)
mvn test -DparallelMode=methods -DthreadCount=5
```

### 3. **View Test Reports**
```bash
# Reports are generated in: ./reports/Test-Report-*.html
open reports/Test-Report-*.html
```

---

## 📖 How to Use Enhanced Features

### **1. Using ConfigManager for Multi-Environment Support**

```java
// Load default (production) configuration
ConfigManager.loadConfig();

// Load QA environment configuration
ConfigManager.loadConfig("qa");

// Get configuration values
String baseUrl = ConfigManager.getBaseUrl();
int timeout = ConfigManager.getRequestTimeout();
String property = ConfigManager.getProperty("custom_key", "default_value");
```

### **2. Using APIClient for Consistent Requests**

```java
// Initialize API client in BeforeClass
@BeforeClass
public void setup() {
    APIClient.initialize();
}

// Use in test methods
@Test
public void testGetUser() {
    Response response = given()
        .spec(APIClient.getRequestSpec())
        .pathParam("username", "john_doe")
    .when()
        .get(Routes.get_url);
}
```

### **3. Using ResponseValidator for Common Validations**

```java
// Validate status code
ResponseValidator.validateStatusCode(response, 200);

// Validate JSON path
ResponseValidator.validateJsonPath(response, "$.username", "john_doe");

// Validate response time
ResponseValidator.validateResponseTime(response, 5000); // 5 seconds

// Validate header
ResponseValidator.validateHeader(response, "Content-Type", "application/json");
```

### **4. Using DataGenerator for Test Data**

```java
// Generate test data
String username = DataGenerator.generateUsername();
String email = DataGenerator.generateEmail();
String password = DataGenerator.generatePassword();

// Use in tests
User user = new User();
user.setUsername(DataGenerator.generateUsername());
user.setEmail(DataGenerator.generateEmail());
user.setFirstName(DataGenerator.generateFirstName());
user.setLastName(DataGenerator.generateLastName());
user.setPassword(DataGenerator.generatePassword());
user.setPhone(DataGenerator.generatePhoneNumber());
```

### **5. Using TestListener for Automatic Logging**

The TestListener is configured to automatically log:
- Test start/finish with timestamps
- Pass/fail status with execution time
- Skip reasons if applicable
- Suite summary statistics

No additional configuration needed - it works automatically!

---

## 🔧 Configuration Property Files

### **config.properties (Production)**
```properties
environment=prod
base_url=https://petstore.swagger.io/v2
request_timeout=30
connection_timeout=10
log_level=INFO
```

### **config-qa.properties (QA)**
```properties
environment=qa
base_url=https://petstore-qa.swagger.io/v2
request_timeout=30
connection_timeout=10
log_level=DEBUG
enable_retry=true
retry_count=3
```

### **config-dev.properties (Development)**
```properties
environment=dev
base_url=https://petstore-dev.swagger.io/v2
request_timeout=60
connection_timeout=20
log_level=DEBUG
enable_retry=true
```

---

## 📊 New Dependencies Added

| Dependency | Version | Purpose |
|-----------|---------|---------|
| Jackson Core | 2.16.1 | JSON serialization/deserialization |
| Lombok | 1.18.30 | Reduces boilerplate with annotations |
| SLF4J API | 2.0.16 | Logging facade |
| Maven Shade | 3.5.0 | Creates fat JARs |

---

## ✨ Key Improvements

### **Before Enhancement**
- ❌ Hardcoded configurations
- ❌ No multi-environment support
- ❌ Inconsistent request setup
- ❌ Manual response validation
- ❌ No centralized logging
- ❌ No test data generation utilities

### **After Enhancement**
- ✅ Centralized configuration management
- ✅ Support for DEV/QA/PROD environments
- ✅ Reusable APIClient for all requests
- ✅ Pre-built validation methods
- ✅ Structured logging with SLF4J
- ✅ DataGenerator for realistic test data
- ✅ TestListener for automatic reporting
- ✅ Lombok for cleaner POJOs
- ✅ Jackson for JSON handling

---

## 🔄 Best Practices to Follow

### **1. Always Initialize APIClient**
```java
@BeforeClass
public void setup() {
    APIClient.initialize();  // Initialize once per test class
}
```

### **2. Use ConfigManager for All URLs**
```java
// ✓ Good
String baseUrl = ConfigManager.getBaseUrl();

// ✗ Avoid
String baseUrl = "https://petstore.swagger.io/v2";
```

### **3. Use Validators Instead of Assertions**
```java
// ✓ Good - Logs result
ResponseValidator.validateStatusCode(response, 200);

// ✗ Less informative
assertEquals(response.getStatusCode(), 200);
```

### **4. Use DataGenerator for Realistic Data**
```java
// ✓ Good
String email = DataGenerator.generateEmail();

// ✗ Hardcoded
String email = "test@example.com";
```

### **5. Create POJOs for Request/Response Bodies**
```java
// ✓ Good
User user = new User("john", "john@example.com", "password123");
Response response = given().body(user).post(url);

// ✗ Avoid
JSONObject obj = new JSONObject();
obj.put("username", "john");
```

---

## 🐛 Troubleshooting

### **Issue: Tests not finding configuration files**
**Solution**: Ensure property files are in `src/test/resources/` directory

### **Issue: ConfigManager throws null pointer**
**Solution**: Call `ConfigManager.loadConfig()` before using it

### **Issue: APIClient not working**
**Solution**: Initialize with `APIClient.initialize()` in @BeforeClass

### **Issue: Reports not generating**
**Solution**: Check that `./reports/` directory exists or create it manually

---

## 📈 Next Steps

1. **Refactor existing tests** to use new utilities
2. **Add Pet and Store API tests** using same pattern
3. **Implement Data-Driven Testing** with Excel files
4. **Add CI/CD pipeline** (GitHub Actions)
5. **Add API Contract Testing** with Swagger
6. **Implement BDD** with Cucumber

---

## 📞 Support & Documentation

- **Docs**: See README.md for detailed documentation
- **Framework Classes**: src/main/java/com/petstore/
- **API Constants**: APIConfig.java
- **Configuration**: src/test/resources/config*.properties

---

**Last Updated**: January 2025  
**Framework Version**: 1.0.0  
**Compatibility**: Java 11+, Maven 3.6+
