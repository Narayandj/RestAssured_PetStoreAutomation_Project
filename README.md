# RestAssured PetStore Automation Framework

A comprehensive REST API automation framework built with **RestAssured**, **TestNG**, and **Maven** for testing the PetStore Swagger API.

## 🎯 Project Overview

This framework provides:
- **REST API Testing** with RestAssured
- **Test Automation** using TestNG
- **Data-Driven Testing** with test data generators
- **Extent Reports** for comprehensive test reporting
- **Multi-Environment Support** (DEV, QA, PROD)
- **Configurable Logging** with SLF4J
- **Reusable Utilities** for common API operations

## 📋 Prerequisites

- **Java**: JDK 11 or higher
- **Maven**: 3.6+
- **Git**: For version control
- **IDE**: IntelliJ IDEA or Eclipse (optional)

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/Narayandj/RestAssured_PetStoreAutomation_Project.git
cd RestAssured_PetStoreAutomation_Project
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Verify Installation
```bash
mvn --version
java -version
```

## 📁 Project Structure

```
src/
  main/java/com/petstore/
    config/              Configuration & constants
      ├── APIConfig.java          API endpoints and constants
      └── ConfigManager.java      Multi-environment config manager
    utils/               Utility classes
      ├── APIClient.java          Base API client setup
      ├── ResponseValidator.java  Response validation methods
      └── DataGenerator.java      Test data generation
    listeners/           TestNG listeners
      └── TestListener.java       Test lifecycle listener
      
  test/java/api/
    endpoints/           API endpoint classes
      ├── UserEndPoint.java
      ├── Routes.java
      └── PropertiesUserEndPoint2.java
    payloads/            Request/Response POJOs
      ├── User.java
      └── [Other POJOs]
    test/                Test classes
      ├── UserTests.java
      └── PropertieReaderUserTests2.java
    utilities/           Test utilities
      └── ExtentReportManager.java
      
  test/resources/
    ├── config.properties      Default configuration
    ├── routes.properties      API routes
    └── [Test data files]

reports/                 Generated test reports
test-output/            TestNG output
screenshots/            Test screenshots (if applicable)

pom.xml                  Maven configuration
testng.xml              TestNG suite configuration
.gitignore              Git ignore rules
README.md               This file
```

## 🔧 Configuration

### Environment Configuration

Create environment-specific property files:

- `config.properties` - Default (PROD)
- `config-dev.properties` - Development
- `config-qa.properties` - QA

```properties
# config.properties
environment=prod
base_url=https://petstore.swagger.io/v2
request_timeout=30
```

### Load Configuration in Code

```java
// Load default config
ConfigManager.loadConfig();

// Load environment-specific config
ConfigManager.loadConfig("qa");

// Get configuration values
String baseUrl = ConfigManager.getBaseUrl();
int timeout = ConfigManager.getRequestTimeout();
```

## 📝 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Specific Test Class
```bash
mvn test -Dtest=UserTests
```

### Run Specific Test Method
```bash
mvn test -Dtest=UserTests#testPostUser
```

### Run with TestNG XML Suite
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Run Parallel Tests
```bash
mvn test -DparallelMode=methods -DthreadCount=5
```

## 📊 Test Reports

After test execution, reports are generated in the `reports/` directory:

- **Extent Reports**: HTML report with detailed test execution info
- **TestNG Reports**: Standard TestNG output in `test-output/`

Open the HTML report in your browser:
```bash
open reports/Test-Report-*.html  # macOS
start reports/Test-Report-*.html # Windows
xdg-open reports/Test-Report-*.html # Linux
```

## 🔗 API Endpoints

### User Endpoints
- **POST** `/user` - Create user
- **GET** `/user/{username}` - Get user by username
- **PUT** `/user/{username}` - Update user
- **DELETE** `/user/{username}` - Delete user

### Pet Endpoints
- **POST** `/pet` - Create pet
- **GET** `/pet/{petId}` - Get pet by ID
- **GET** `/pet/findByStatus` - Find pets by status

### Store Endpoints
- **POST** `/store/order` - Create order
- **GET** `/store/order/{orderId}` - Get order by ID

## 🛠️ Key Utilities

### APIClient
Manages request and response specifications:
```java
APIClient.initialize();
RequestSpecification spec = APIClient.getRequestSpec();
```

### ResponseValidator
Validates API responses:
```java
ResponseValidator.validateStatusCode(response, 200);
ResponseValidator.validateJsonPath(response, "$.username", "john_doe");
ResponseValidator.validateResponseTime(response, 5000);
```

### DataGenerator
Generates test data:
```java
String username = DataGenerator.generateUsername();
String email = DataGenerator.generateEmail();
String password = DataGenerator.generatePassword();
```

## 📚 Example Test

```java
public class UserTests {
    
    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new User();
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());
    }
    
    @Test(priority = 1)
    public void testCreateUser() {
        Response response = UserEndPoint.createUser(userPayload);
        
        response.then()
            .statusCode(200)
            .body("username", equalTo(userPayload.getUsername()));
        
        Assert.assertTrue(ResponseValidator.validateStatusCode(response, 200));
    }
}
```

## 🔐 Best Practices

1. **Use ConfigManager** for all configuration values
2. **Use DataGenerator** for test data creation
3. **Use ResponseValidator** for common validations
4. **Use APIClient** for consistent request specifications
5. **Implement Base Test Classes** for common setup
6. **Use meaningful test names** and descriptions
7. **Add logging** for debugging
8. **Separate concerns** - tests, endpoints, payloads, utilities

## 🐛 Troubleshooting

### Maven Build Fails
```bash
mvn clean install -U
```

### Tests Not Running
- Verify TestNG XML configuration in `pom.xml`
- Check TestNG listener configuration
- Ensure test class names end with `Test` or `Tests`

### Connection Timeout
- Increase timeout in `config.properties`
- Check API server availability
- Verify network connectivity

### Report Not Generated
- Check `reports/` directory exists
- Verify ExtentReportManager configuration
- Check logs for errors

## 📖 Dependencies

| Dependency | Version | Purpose |
|-----------|---------|---------|
| RestAssured | 5.5.0 | REST API Testing |
| TestNG | 7.10.2 | Test Framework |
| Extent Reports | 5.1.2 | Test Reporting |
| JavaFaker | 1.0.2 | Test Data Generation |
| SLF4J | 2.0.16 | Logging |
| Jackson | 2.16.1 | JSON Serialization |
| Lombok | 1.18.30 | Code Generation |
| Selenium | 4.27.0 | Browser Automation (optional) |

## 📈 Roadmap

- [ ] Add Pet and Store API tests
- [ ] Implement Data-Driven Testing
- [ ] Add API Contract Testing
- [ ] Implement CI/CD Pipeline (GitHub Actions)
- [ ] Add Performance Testing
- [ ] Add Security Testing
- [ ] Implement BDD with Cucumber
- [ ] Add Allure Reports integration

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👤 Author

**Narayana D J**
- GitHub: [@Narayandj](https://github.com/Narayandj)

## 📞 Support

For issues, questions, or suggestions:
- Open an [Issue](https://github.com/Narayandj/RestAssured_PetStoreAutomation_Project/issues)
- Create a [Discussion](https://github.com/Narayandj/RestAssured_PetStoreAutomation_Project/discussions)

## 🎓 Resources

- [RestAssured Documentation](https://rest-assured.io/)
- [TestNG Documentation](https://testng.org/)
- [PetStore Swagger API](https://petstore.swagger.io/)
- [Maven Documentation](https://maven.apache.org/)
- [SLF4J Documentation](https://www.slf4j.org/)

---

**Last Updated**: 2025-01-20  
**Framework Version**: 1.0.0
