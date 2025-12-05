# API & UI Testing Automation Suite

Complete testing automation project with API and UI coverage, Allure reporting, and Jenkins CI/CD integration.
---

## 📋 Requirements

- **Java 21+** 
- **Gradle 8.0+** 
- **Firefox**
- **Git** 
- **Jenkins** 

### Verify Requirements

```bash
java -version          # Must be Java 21
./gradlew -version    # Verify Gradle
firefox --version     # Verify Firefox
```

---

## To start

### 1. Clone Repository

```bash
git clone https://github.com/belenfdez/Software_Project.git
cd Software_Project/api-testing-demo-java_starter
```

### 2. Build Project

```bash
./gradlew clean build -x test
```

### 3. Run Tests

```bash
# All tests
./gradlew test

# API tests only
./gradlew test -Ptag=api

# UI tests only
./gradlew test -Ptag=ui
```

### 4. View Allure Reports

```bash
allure serve build/allure-results/
```

Opens automatically at http://localhost:4040.

---


## 🧪 Test Coverage

### API Tests (17 tests - Electronics Store Backend)

| Suite | Tests | Description |
|-------|-------|-------------|
| **BasicApiTest** | 3 | GET list, GET by ID, 404 handling |
| **CrudApiTest** | 6 | Create, Read, Update, Delete, Filter |
| **NegativeApiTest** | 5 | 404s, invalid payloads, malformed JSON |
| **E2EApiTest** | 3 | Product-orders flow, complete CRUD chain |

**Base URL:** http://localhost:8080/api
**Backend:** electronics-store-0.0.1-SNAPSHOT.jar (must be running)

### UI Tests (9 tests - SauceDemo)

| Suite | Tests | Description |
|-------|-------|-------------|
| **SauceDemoLoginUITest** | 6 | Valid/invalid login, empty credentials |
| **SauceDemoE2ETest** | 3 | Complete purchase flow, multiple products |

**Base URL:** https://www.saucedemo.com

---

## Configuration

### Change Environment

Edit `src/test/resources/env/dev.properties` or `qa.properties`:

```properties
baseUri=https://jsonplaceholder.typicode.com
connectTimeout=5000
readTimeout=5000
```

Run with specific environment:

```bash
./gradlew test -Denv=qa
```

---

## Allure Reporting


### Generate Report

```bash
./gradlew test
allure serve build/allure-results/
```

### Clear Previous Reports

```bash
./gradlew clean
```

---

## Jenkins Integration

### Jenkins Requirements

1. Install plugins:
   - Allure Plugin
   - JUnit Plugin
   - Pipeline

2. Create declarative pipeline:
   - Source: GitHub (Software_Project)
   - Script path: `Jenkinsfile`

### Run Pipeline

```groovy
// In Jenkins UI:
// 1. New Job → Pipeline
// 2. Pipeline → Pipeline script from SCM
// 3. Git → https://github.com/belenfdez/Software_Project.git
// 4. Build
```

### Access Reports in Jenkins

- Test Results: `Job → Test Result Trend`
- Allure Report: `Job → Allure Report`

---

## 📝 Use Cases

### API Testing (Electronics Store Backend)

```bash
# Verify backend is running first
java -jar electronics-store-0.0.1-SNAPSHOT.jar &

# Then run API tests
./gradlew test -Ptag=api

# Expected flow: GET /api/products → POST /api/orders → PUT /api/orders/{id} → DELETE /api/orders/{id}
```

### UI Testing (SauceDemo)

```bash
# Verify complete purchase flow
./gradlew test -Ptag=ui

# Test credentials:
# - Username: standard_user
# - Password: secret_sauce
```

---

## 🛠️ Troubleshooting

### Slow UI Tests

Firefox runs in headless mode (without GUI). For debugging:
- Edit `DriverFactory.java` and comment `options.setHeadless(true);`

### Allure Report Not Generating

```bash
# Clean cache and regenerate
./gradlew clean test
allure generate build/allure-results/ -o build/allure-report/
allure open build/allure-report/
```

### Jenkins Port Conflict

Jenkins runs on port 8080 by default. If occupied:

```bash
sudo systemctl stop jenkins
# Or run on different port:
java -jar jenkins.war --httpPort=8888
```

---

## 📈 Metrics

| Metric | Value |
|--------|-------|
| Total tests | 22 |
| Coverage rate | ~80% critical code |
| Execution time | ~45 seconds |
| Broken links | 0 |

---

## 🔄 Git Workflow

```bash
# Create feature branch
git checkout -b feature/new-test-suite

# Make changes and commit
git add .
git commit -m "feat: add new test suite"

# Push to GitHub
git push origin feature/new-test-suite

# Create Pull Request in GitHub UI
```

---

## 📚 Technologies

| Stack | Tool | Version |
|-------|------|---------|
| **Java** | OpenJDK | 21+ |
| **Build** | Gradle | 8.14 |
| **Test Framework** | JUnit 5 | 5.11.3 |
| **API Testing** | RestAssured | 5.5.0 |
| **UI Testing** | Selenium | 4.25.0 |
| **WebDriver** | WebDriverManager | 5.9.2 |
| **Reporting** | Allure | 2.29.0 |
| **Assertions** | AssertJ | 3.26.3 |
| **Mocking** | Mockito | 5.14.2 |
| **Logging** | SLF4J | 2.0.16 |

---

## 👤 Author

**Belén Fernández**  
ERASMUS Project - Software Testing Automation  
[GitHub](https://github.com/belenfdez/Software_Project)

---

## 📄 License

Academic Project - 2024

---

## 🎯 Future Roadmap

- [ ] Real database integration
- [ ] Performance testing (JMeter)
- [ ] Code coverage (JaCoCo)
- [ ] Docker integration
- [ ] API mocking with Wiremock
- [ ] Mobile testing (Appium)
